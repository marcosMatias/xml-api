package br.com.company.xml.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateUtil {

	private static final DateTimeFormatter FORMAT_INVERSO = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
	private static final DateTimeFormatter FORMAT_PADRAO = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
	private static final DateTimeFormatter FORMAT_DATA = DateTimeFormatter.ofPattern("yyyy-MM-dd");

	
	
	private DateUtil() {
	    throw new UnsupportedOperationException("Classe utilitária - não instanciar.");
	}

	
	
	public static String gerarDataInvertida() {

		return LocalDateTime.now().format(FORMAT_INVERSO);
	}

	public static String retornarDataHoraAtual() {
		return LocalDateTime.now().format(FORMAT_PADRAO);
	}

	public static LocalDate converterStringParaLocalDate(String data) {
		return LocalDate.parse(data, FORMAT_DATA);
	}

	public static String converterLocalDateTimeParaString(LocalDateTime localDateTime) {
		return localDateTime.format(FORMAT_PADRAO);
	}

	public static LocalDateTime converterStringParaLocalDateTime(String data) {
		return LocalDateTime.parse(data, FORMAT_PADRAO);
	}

}
