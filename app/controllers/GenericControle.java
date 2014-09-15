package controllers;

import static constantes.DiretorioConstantes.JASPER_TEMPLATE_PATH;
import static constantes.RelatorioConstantes.RPT_LOGO;
import static constantes.TipoArquivoConstantes.JASPER;
import static constantes.TipoArquivoConstantes.PDF;
import static constantes.TipoArquivoConstantes.XLSX;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;

import net.sf.jasperreports.engine.JRBreak;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimpleXlsReportConfiguration;

import org.springframework.beans.factory.annotation.Autowired;

import servicos.AtividadeService;
import servicos.IteracaoService;
import servicos.MensagemService;
import servicos.ProjetoService;
import servicos.RelatorioService;
import servicos.UsuarioService;
import constantes.ControleConstantes;
import excecoes.JasperFileNotFoundException;

/**
 * Copyright (C) 2014 CONAB
 * Companhia Nacional de Abastecimento
 * www.conab.gov.br
 *
 * GenericControle.java
 *
 * @date 30/07/2014
 * @author apoena.machado
 */
public class GenericControle extends CRUD implements ControleConstantes {

	/** The atividade service. */
	public static AtividadeService atividadeService;
	
	/** The usuario service. */
	public static UsuarioService usuarioService;
	
	/** The iteracao service. */
	public static IteracaoService iteracaoService;
	
	/** The relatorio service. */
	public static RelatorioService relatorioService;
	
	/** The mensagem service. */
	public static MensagemService mensagemService;
	
	/** The projeto service. */
	public static ProjetoService projetoService;

	
	/**
	 * Sets the atividade service.
	 *
	 * @param atividadeService the new atividade service
	 */
	@Autowired
	public void setAtividadeService(AtividadeService atividadeService) {
		this.atividadeService = atividadeService;
	}
	
	/**
	 * Sets the usuario service.
	 *
	 * @param usuarioService the new usuario service
	 */
	@Autowired
	public void setUsuarioService(UsuarioService usuarioService) {
		this.usuarioService = usuarioService;
	}
	
	/**
	 * Sets the iteracao service.
	 *
	 * @param iteracaoService the new iteracao service
	 */
	@Autowired
	public void setIteracaoService(IteracaoService iteracaoService) {
		this.iteracaoService = iteracaoService;
	}
	
	/**
	 * Sets the relatorio service.
	 *
	 * @param relatorioService the new relatorio service
	 */ 
	@Autowired
	public void setRelatorioService(RelatorioService relatorioService) {
		this.relatorioService = relatorioService;
	}
	
	/**
	 * Sets the mensagem service.
	 *
	 * @param mensagemService the new mensagem service
	 */
	@Autowired
	public void setMensagemService(MensagemService mensagemService) {
		this.mensagemService = mensagemService;
	}
	
	/**
	 * Sets the projeto service.
	 *
	 * @param projetoService the new projeto service
	 */
	@Autowired
	public void setProjetoService(ProjetoService projetoService) {
		this.projetoService = projetoService;
	}
	
	// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	// Methods - protected static
	// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	/**
	 * Render pdf.
	 *
	 * @param lstResult the lst result
	 * @param reportParametersMap the report parameters map
	 * @param fileName the file name
	 * @throws Exception the exception
	 */
	protected static void renderPdf(final List<?> lstResult, final Map<String, Object> reportParametersMap, String fileName) throws Exception {
		fileName = fileName + "." + PDF;
		renderBinary(new ByteArrayInputStream(getJasperByteArray(lstResult, reportParametersMap, fileName)), fileName);
	}
	
	/**
	 * Render pdf.
	 *
	 * @param lstResult the lst result
	 * @param reportParametersMap the report parameters map
	 * @param jasperFile the jasper file
	 * @param fileName the file name
	 * @throws Exception the exception
	 */
	protected static void renderPdf(final List<?> lstResult, final Map<String, Object> reportParametersMap, final String jasperFile, String fileName) throws Exception {
		fileName = fileName + "." + PDF;
		renderBinary(new ByteArrayInputStream(getJasperByteArray(lstResult, reportParametersMap, jasperFile, fileName)), fileName);
	}
	
	/**
	 * Render xlsx.
	 *
	 * @param lstResult the lst result
	 * @param reportParametersMap the report parameters map
	 * @param fileName the file name
	 * @throws Exception the exception
	 */
	protected static void renderXlsx(final List<?> lstResult, final Map<String, Object> reportParametersMap, String fileName) throws Exception {
		fileName = fileName + "." + XLSX;
		renderBinary(new ByteArrayInputStream(getJasperByteArray(lstResult, reportParametersMap, fileName)), fileName);
	}
	
	/**
	 * Render xlsx.
	 *
	 * @param lstResult the lst result
	 * @param reportParametersMap the report parameters map
	 * @param jasperFile the jasper file
	 * @param fileName the file name
	 * @throws Exception the exception
	 */
	protected static void renderXlsx(final List<?> lstResult, final Map<String, Object> reportParametersMap, final String jasperFile, String fileName) throws Exception {
		fileName = fileName + "." + XLSX;
		renderBinary(new ByteArrayInputStream(getJasperByteArray(lstResult, reportParametersMap, jasperFile, fileName)), fileName);
	}
	
	/**
	 * Gets the jasper byte array.
	 *
	 * @param lstResult the lst result
	 * @param reportParametersMap the report parameters map
	 * @param pdfFileName the pdf file name
	 * @return the jasper byte array
	 * @throws Exception the exception
	 */
	protected static byte[] getJasperByteArray(final List<?> lstResult, final Map<String, Object> reportParametersMap, final String pdfFileName) throws Exception {
		return getJasperByteArray(lstResult, reportParametersMap, null, pdfFileName);
	}
	protected static byte[] getJasperByteArray(final List<?> lstResult, final Map<String, Object> paramsMap, final String jasperFile, final String fileName) throws Exception {

		BufferedImage image = ImageIO.read(new java.io.File("public/images/img_conabscrum.png"));
		final JRBeanCollectionDataSource jrBeanCollection = new JRBeanCollectionDataSource(lstResult, true);
		final InputStream inputStream = getJasperFileInputStream(jasperFile);
		if (inputStream == null) {
			throw new JasperFileNotFoundException();
		}
		 System.setProperty("java.awt.headless", "true");
		 System.out.println(java.awt.GraphicsEnvironment.isHeadless());
		paramsMap.put(RPT_LOGO, image);
		final JasperPrint print = JasperFillManager.fillReport(inputStream, paramsMap, jrBeanCollection);
		final int suffixSeparator = fileName.lastIndexOf(".") + 1;
		final String sheetName = fileName.substring(0, suffixSeparator);
		final ByteArrayOutputStream out = new ByteArrayOutputStream();
		if (XLSX.toString().equalsIgnoreCase(fileName.substring(suffixSeparator))) {
			JRXlsxExporter exporter = new JRXlsxExporter();
			SimpleXlsReportConfiguration configuration = new SimpleXlsReportConfiguration();
			configuration.setWhitePageBackground(false);
			configuration.setDetectCellType(true);
			configuration.setRemoveEmptySpaceBetweenRows(true);
			configuration.setCollapseRowSpan(true);
			configuration.setSheetNames(new String[] { sheetName });
			//configuration.setOnePagePerSheet(true);
			paramsMap.put(JRBreak.PAGE_BREAK_NO_PAGINATION_IGNORE, true);
			exporter.setExporterInput(new SimpleExporterInput(print));
			exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(out));
			exporter.exportReport();
		} else {
			JRPdfExporter exporter = new JRPdfExporter();
			exporter.setExporterInput(new SimpleExporterInput(print));
			exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(out));
			exporter.exportReport();
		}
		inputStream.close();
		out.close();
		return out.toByteArray();
	}
	
	/**
	 * Gets the jasper file input stream.
	 *
	 * @param jasperFile the jasper file
	 * @return the jasper file input stream
	 * @throws FileNotFoundException the file not found exception
	 */
	private static InputStream getJasperFileInputStream(final String jasperFile) throws FileNotFoundException {
		String jasperFileName = jasperFile;
		if (utils.StringUtil.isEmpty(jasperFile)) {
			final int lastIndexOf = request.controller.lastIndexOf(".");
			jasperFileName = request.controller.substring(lastIndexOf + 1) + "." + JASPER;
		}
		final InputStream inputStream = BaseController.class.getClassLoader().getResourceAsStream(JASPER_TEMPLATE_PATH + jasperFileName);
		return inputStream;
	}
	
	/**
	 * Alerta info.
	 *
	 * @param mensagem the mensagem
	 */
	public static void alertaInfo(String mensagem){
		flash.put(ALERTA_INFO, mensagem);
		flash.keep();
	}
}