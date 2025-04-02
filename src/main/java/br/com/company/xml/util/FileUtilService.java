package br.com.company.xml.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.stream.Stream;
import br.com.company.xml.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FileUtilService {



	private FileUtilService() {
	    throw new UnsupportedOperationException("Classe utilitária - não instanciar.");
	}	
	
	
	
	public static void validateDirectoryAndFiles(String source, String target,String fileExtension) {

		
		Path sourceFolder = Path.of(source);		
		Path targetFolder = Path.of(target);

        try {
        	log.info("********** Diretório de origem: {}",source);
        	log.info("********** Diretório de destino: {}",target);
        	
        	
        	 // Verificar se o diretório de origem existe
            if (Files.notExists(sourceFolder) || !Files.isDirectory(sourceFolder)) {
                log.error("********** Diretório de origem nao existe ou nao é um diretorio valido: {}", sourceFolder);
                throw new BusinessException("Diretorio de origem nao existe ou nao é um diretorio valido: " + sourceFolder);
            }
        	
        	
            
         // Verificar se há arquivos com a extensão especificada no diretório de origem
            if (getFileCountByExtension(sourceFolder, fileExtension) == 0) {
                log.error("********** Nenhum arquivo encontrado com a extensão '{}' no diretório: {}", fileExtension, sourceFolder);
                throw new BusinessException("Nenhum arquivo encontrado com a extensao '" + fileExtension + "' no diretorio: " + sourceFolder);
            }
            
            
            // Cria o diretório de destino, se não existir
            if (Files.notExists(targetFolder)) {
            	log.info("********** Criando o diretorio: "+targetFolder);
                Files.createDirectories(targetFolder);
            }
	
	
        } catch (IOException e) {
            log.error("********** Erro ao validar arquivos", e);
            throw new BusinessException("Erro ao validar arquivos: "+e.getMessage());
        }

}
	
	
	public static void processAndMoveFiles(String source, String target, String fileExtension, String processPath) {
		
		Path sourceFolder = Path.of(source);
	    Path targetFolder = Path.of(target);
	    Path processedPath = Path.of(processPath);
	    
	    try {
	    	 List<Path> files = getFilesByExtension(sourceFolder, fileExtension);
	    	 
	    	  files.forEach(file -> {
	            	
	            	Path targetFile ;
	            	
					if (targetFolder.equals(processPath)) {
												
						 try {
						     
							if (getFileCountByExtension(targetFolder, fileExtension) >=1) {
							        log.info("********** Mover arquivo parado no diretorio  {} ",  processPath);
							        
							       
							        List<Path> stopFiles = getFilesByExtension(targetFolder, fileExtension);
							          stopFiles.forEach(st -> {
							        	  Path targetStopFile ;
							        	  targetStopFile = targetFolder.resolve(st.getFileName());
							        	  moveFile(st, targetStopFile);
							        	 
							          });
							        
							       
							    }
						} catch (IOException e) {
							log.error("********** Erro ao mover o arquivo parado no diretorio '{}' {} {} ",processedPath,e.getMessage(),e);
							 throw new BusinessException("Erro ao mover o arquivo parado no diretorio" +processedPath);
						}
						
						targetFile = targetFolder.resolve(generateNewFileName(file.getFileName().toString(), fileExtension));
					} else {
						targetFile = targetFolder.resolve(file.getFileName());

					}
	                
						moveFile(file, targetFile);
	               
	            });
	            
	    	 
	    	
	    }catch (IOException e) {
	        log.error("********** Erro ao processar movimentação de arquivos", e);
	        throw new BusinessException("Erro ao processar movimentação de arquivos: " + e.getMessage());
	    }
		
		
	}
	
	
	
	
	public static List<Path> getFilesByExtension(Path directory, String fileExtension) throws IOException {
	    try (Stream<Path> files = Files.list(directory)
	    		                       .filter(path -> path.toString()
	    		                                		   .endsWith(fileExtension.toLowerCase()))) {
	        return files.toList();
	    }
	}

	
	public static  long getFileCountByExtension(Path directory, String fileExtension) throws IOException {
	    try (Stream<Path> files = Files.list(directory).filter(path -> path.toString().endsWith(fileExtension.toLowerCase()))) {
	        return files.count();
	    }
	}
	
	
	public static  void deleteFile(Path file) {

		if (Files.exists(file)) {
			try {
				Files.delete(file);
				log.info("********** Arquivo {} deletado do diretório de origem", file.getFileName());

			} catch (IOException e) {
				log.error("********** Ocorreu um erro ao deletar o arquivo {} ",file.getFileName(),e);
				throw new BusinessException("Ocoreu um erro ao deletar o arquivo! "+e.getMessage(), e.getCause());
			}
		}

	}
	
	
	public static void moveFile(Path file, Path target) {

		try {

			Files.move(file, target, StandardCopyOption.REPLACE_EXISTING);
			log.info("********** Arquivo {} movido para {}", file.getFileName(), target);

		} catch (IOException e) {
			log.error("********** Não foi possível mover o arquivo: {}", file.getFileName() + " " + e.getMessage());
			throw new BusinessException(
					"Não foi possível mover o arquivo: " + file.getFileName() + " " + e.getMessage());
		}
	}
	
	
	
	
	
	
	public static String generateNewFileName(String originalFileName, String fileExtension) {
		
	    int index = originalFileName.lastIndexOf('.');
	    
	    String baseName = index > 0 ? originalFileName.substring(0, index) : originalFileName;
	    
	    return baseName + "_" + DateUtil.gerarDataInvertida() + fileExtension;
	}




}
