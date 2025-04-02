package br.com.company.xml.service;

import java.io.File;
import java.io.StringReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.IntStream;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import static br.com.company.xml.util.function.LambdaExceptionUtil.throwingConsumerWrapper;
import br.com.company.xml.dto.ResponseDto;
import br.com.company.xml.exception.BusinessException;
import br.com.company.xml.util.DateUtil;
import br.com.company.xml.util.FileUtilService;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class XmlService {
	
	@Value("${xml-api.file.source}")
	private String fileSourcePath;
	
	@Value("${xml-api.file.process}")
	private String fileProcessPath;
	
	@Value("${xml-api.file.processed}")
	private String fileProcessedPath;
	
	@Value("${xml-api.file.extension.xml}")
	private String fileExtension;
	
	
	public ResponseDto processar() {
		
		FileUtilService.validateDirectoryAndFiles(fileSourcePath, fileProcessPath, fileExtension);
		
		FileUtilService.processAndMoveFiles(fileSourcePath, fileProcessPath, fileExtension, fileProcessPath);
		
		try {
			
			
			List<Path> files = FileUtilService.getFilesByExtension( Path.of(fileProcessPath), fileExtension);	
				
			List<String> paths = files.stream()
					                  .map(file -> Path.of(fileProcessPath).resolve(file.getFileName()).normalize())
					                  .map(Path::toAbsolutePath)
                                      .map(Path :: toString)
                                      .toList();
			
			
						paths.forEach(throwingConsumerWrapper(p -> {
																    Path source = Path.of(p).normalize(); 
																    Path target = Path.of(fileProcessedPath).normalize().resolve(source.getFileName());
													
																    Document doc = loadDocument(p);
																    Document modificado = atualizarTagsXml(p, doc);
																    sobrescreverXml(modificado, p);
													
																    FileUtilService.moveFile(source, target);
													
																    log.info("Arquivo XML '{}' processado com sucesso.", source.getFileName());
																}));
			
		

			return messageReturn(200,"Arquivos Xml processados com sucesso!",DateUtil.retornarDataHoraAtual());
			
		} catch (Exception e) {
			log.error("********** Erro ao processar arquivo {}",e.getMessage());
			throw new BusinessException("Erro ao processar arquivo", e);
		}
		
	}
	
	
	
	
	
	

	private Document loadDocument(String pathXml) throws Exception {

		File file = new File(pathXml);
		log.info("********** carrega o XML " + file.getName());

		byte[] content = Files.readAllBytes(file.toPath());
		String xmlString = new String(content, StandardCharsets.UTF_8).trim();
		InputSource inputSource = new InputSource(new StringReader(xmlString));

		DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
		Document doc = builder.parse(inputSource);
		doc.getDocumentElement().normalize();

		return doc;
	}

	private void sobrescreverXml(Document doc, String caminhoSaida) throws Exception {
		
		log.info("********** salvando o XML "+getFileName(caminhoSaida));
		
		Transformer transformer = TransformerFactory.newInstance().newTransformer();
		
		transformer.setOutputProperty(OutputKeys.INDENT, "yes");
		
		DOMSource source = new DOMSource(doc);
		
		StreamResult result = new StreamResult(new File(caminhoSaida));
		
		transformer.transform(source, result);
	}
	
	
	
	private Document atualizarTagsXml(String path, Document doc) {

		try {

			log.info("********** modificando o XML " + getFileName(path));

			alterarTags(doc, "serie", "99");
			alterarTags(doc, "verProc", "FIT DFe CloudFiscalXXXX");

			return doc;

		} catch (Exception e) {
			log.error("********** Erro ao modificar o arquivo xml {}", e.getMessage());
			throw new BusinessException("Erro ao modificar o arquivo xml", e);
		}
		
	
		
		
	}
	
	private void alterarTags(Document doc, String tagName, String novoValor) {
	    NodeList nodeList = doc.getElementsByTagName(tagName);

	    if (nodeList.getLength() == 0) {
	        log.warn("Tag <{}> não encontrada no XML.", tagName);
	        return;
	    }

	    
	    IntStream.range(0, nodeList.getLength())
	             .mapToObj(nodeList::item)
	             .filter(node -> node.getNodeType() == Node.ELEMENT_NODE)
	             .forEach(node -> {
				                 node.setTextContent(novoValor);
				                 //log.info("Tag <{}> atualizada para: {}", tagName, novoValor);
	             				});
	}
	
	

	private Path getFileName(String filePath) {
		
		return Path.of(filePath).getFileName();
	}

	
	private ResponseDto messageReturn(int statusCode,String message,String data) {
		
		return ResponseDto.builder()
				          .statusCode(statusCode)
				          .message(message)
				          .description(message)
				          .data(data)
				          .build();
	}
	
}
