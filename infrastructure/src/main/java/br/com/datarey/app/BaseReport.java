package br.com.datarey.app;

import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.Map;

public interface BaseReport {
	List<Map<String, Object>> listarDicionario();
	String gerarRelatorio(List<?> lista, List<Map<String, Object>> metadados);

	public ByteArrayOutputStream exportToPDF(String reportId);

	public ByteArrayOutputStream exportToRTF(String reportId);

	public ByteArrayOutputStream exportToDOCX(String reportId);

	public ByteArrayOutputStream exportToXLSX(String reportId);

	public ByteArrayOutputStream exportToPPTX(String reportId);

	public ByteArrayOutputStream exportToODT(String reportId);

	public ByteArrayOutputStream exportToODS(String reportId);

	public ByteArrayOutputStream exportToHTML(String reportId);

	public ByteArrayOutputStream exportToTXT(String reportId);

	public ByteArrayOutputStream exportToSWF(String reportId);
}