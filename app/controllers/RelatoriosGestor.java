package controllers;

import static constantes.MensagemConstantes.MSG_ERRO_GERACAO_RELATORIO;
import static constantes.MensagemConstantes.MSG_INFO_GERACAO_RELATORIO_VAZIO;
import static constantes.RelatorioConstantes.RPT_NOME;

import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import models.VwProjetoAtividadeQuantidade;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.PieSectionLabelGenerator;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.util.Rotation;

import play.i18n.Messages;
import vos.RelatorioProjetosPorGerenteVO;
import constantes.TipoArquivoConstantes;

/**
 * Copyright (C) 2014 CONAB
 * Companhia Nacional de Abastecimento
 * www.conab.gov.br
 *
 * RelatoriosGestor.java
 *
 * @date 15/08/2014
 * @author apoena.machado
 */
public class RelatoriosGestor extends BaseAdmController {

	/** The Constant RELATORIO_PROJETOS_POR_GERENTE. */
	private static final String RELATORIO_PROJETOS_POR_GERENTE = "RelatorioProjetosPorGerente";
	
	/** The Constant RPT_NOME_RELATORIO_PROJETOS_POR_GERENTE. */
	private static final String RPT_NOME_RELATORIO_PROJETOS_POR_GERENTE = "Relatorio Projetos Por Gerente";

	/** The Constant RELATORIO_PROJETOS_ATIVIDADE_GRAFICO. */
	private static final String RELATORIO_PROJETOS_ATIVIDADE_GRAFICO = "RelatorioProjetosAtividadesGrafico";

	/** The Constant RPT_NOME_RELATORIO_PROJETOS_ATIVIDADE_GRAFICO. */
	private static final String RPT_NOME_RELATORIO_PROJETOS_ATIVIDADE_GRAFICO = "Relatorio Projetos Atividade Gráfico";
	
	/**
	 * Imprimir projetos por gerente.
	 *
	 * @param tipoArquivo the tipo arquivo
	 * @param login the login
	 */
	public static void imprimirProjetosPorGerente(String tipoArquivo, String login){
		
		try {
			List<RelatorioProjetosPorGerenteVO> lstDadosRelatorio = relatorioService.recuperarDadosProjetosPorGerente(login);
			if (!lstDadosRelatorio.isEmpty()){
				final HashMap<String, Object> reportParametersMap = new HashMap<String, Object>();
				reportParametersMap.put(RPT_NOME, RPT_NOME_RELATORIO_PROJETOS_POR_GERENTE);
				if (tipoArquivo.equals(TipoArquivoConstantes.XLS.name())){
					renderXlsx(lstDadosRelatorio, reportParametersMap, RELATORIO_PROJETOS_POR_GERENTE+".jasper", RELATORIO_PROJETOS_POR_GERENTE);				
				} else {
					renderPdf(lstDadosRelatorio, reportParametersMap, RELATORIO_PROJETOS_POR_GERENTE+".jasper", RELATORIO_PROJETOS_POR_GERENTE);
				}
			} else {
				alertaInfo(Messages.get(MSG_INFO_GERACAO_RELATORIO_VAZIO));
			}
		} catch (final Throwable t) {
			flash.error(Messages.get(Messages.get(MSG_ERRO_GERACAO_RELATORIO)));
			t.printStackTrace();
		}
		redirect("publico.HomeController.index");
	}
	
	/**
	 * Imprimir projetos atividade grafico.
	 *
	 * @param tipoArquivo the tipo arquivo
	 * @param login the login
	 */
	public static void imprimirProjetosAtividadeGrafico(String tipoArquivo){
		
		try {
			List<VwProjetoAtividadeQuantidade> lstDadosRelatorio = relatorioService.recuperarDadosProjetosAtividadeQuantidade(Seguranca.isGestor() ? null : session.get("username"));
			if (!lstDadosRelatorio.isEmpty()){
				
				List charts = new ArrayList();
				for (VwProjetoAtividadeQuantidade vwProjetoAtividadeQuantidade : lstDadosRelatorio) {
					
//					DefaultPieDataset piedataset = new DefaultPieDataset();
//			        piedataset.setValue("Finalizadas", vwProjetoAtividadeQuantidade.getQtdAtividadeFinalizada());
//			        piedataset.setValue("Total", vwProjetoAtividadeQuantidade.getQtdAtividadeTotal());
//			        JFreeChart jfreechart = ChartFactory.createRingChart("This is chart number: " + vwProjetoAtividadeQuantidade.getNomeProjeto(), piedataset, false, true, false);
//			        RingPlot ringplot = (RingPlot) jfreechart.getPlot();
//			        ringplot.setLabelFont(new Font("SansSerif", 0, 12));
//			        ringplot.setNoDataMessage("No data available");
//			        ringplot.setSectionDepth(0.34999999999999998D);
//			        ringplot.setCircular(false);
//			        ringplot.setLabelGap(0.02D);
//					BufferedImage img = new BufferedImage(800, 600, BufferedImage.TYPE_INT_RGB);
//					Graphics2D g2 = img.createGraphics();
//					jfreechart.draw(g2, new Rectangle2D.Double(0, 0, 800, 600));
//					g2.dispose();				
//					BufferedImage bi = img;
//		            ChartBean chartBean = new ChartBean(bi, "This is description for Chart: " + vwProjetoAtividadeQuantidade.getNomeProjeto());
//		            charts.add(chartBean);
		            
					DefaultPieDataset piedataset = new DefaultPieDataset();
			        piedataset.setValue("Finalizadas", Double.valueOf(vwProjetoAtividadeQuantidade.getQtdAtividadeFinalizada()));
			        piedataset.setValue("A fazer", Double.valueOf(vwProjetoAtividadeQuantidade.getQtdAtividadeTotal()));
			        JFreeChart jfreechart = ChartFactory.createPieChart3D("Projeto: " + vwProjetoAtividadeQuantidade.getNomeProjeto(), piedataset, true, true, false);
			        jfreechart.setTextAntiAlias(RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_VRGB);
			        jfreechart.setBorderVisible(false);
			        jfreechart.getTitle().setFont(new Font("SansSerif", java.awt.Font.BOLD, 14));
			        PiePlot3D ringplot = (PiePlot3D) jfreechart.getPlot();
			        ringplot.setStartAngle(290);
			        ringplot.setDirection(Rotation.CLOCKWISE);
			        ringplot.setForegroundAlpha(0.5f);
			        ringplot.setBackgroundAlpha(0.0f);
			        ringplot.setNoDataMessage("No data to display");
			        ringplot.setLabelFont(new Font("SansSerif", 0, 8));
			        ringplot.setExplodePercent("Finalizadas", 0.10);
					PieSectionLabelGenerator gen = new StandardPieSectionLabelGenerator("{0}: {1} ({2})", new DecimalFormat("0"), new DecimalFormat("0%"));
					ringplot.setLabelGenerator(gen);			        
					BufferedImage img = new BufferedImage(300, 230, BufferedImage.TYPE_INT_RGB);
					Graphics2D g2 = img.createGraphics();
					jfreechart.draw(g2, new Rectangle2D.Double(0, 0, 300, 230));
					g2.dispose();				
					BufferedImage bi = img;
		            ChartBean chartBean = new ChartBean(bi, "This is description for Chart: " + vwProjetoAtividadeQuantidade.getNomeProjeto());
		            charts.add(chartBean);
		            
				}
				
				final HashMap<String, Object> reportParametersMap = new HashMap<String, Object>();
				reportParametersMap.put(RPT_NOME, RPT_NOME_RELATORIO_PROJETOS_ATIVIDADE_GRAFICO);
				if (tipoArquivo.equals(TipoArquivoConstantes.XLS.name())){
					renderXlsx(charts, reportParametersMap, RELATORIO_PROJETOS_ATIVIDADE_GRAFICO+".jasper", RELATORIO_PROJETOS_ATIVIDADE_GRAFICO);				
				} else {
					renderPdf(charts, reportParametersMap, RELATORIO_PROJETOS_ATIVIDADE_GRAFICO+".jasper", RELATORIO_PROJETOS_ATIVIDADE_GRAFICO);
				}
			} else {
				alertaInfo(Messages.get(MSG_INFO_GERACAO_RELATORIO_VAZIO));
			}
		} catch (final Throwable t) {
			flash.error(Messages.get(Messages.get(MSG_ERRO_GERACAO_RELATORIO)));
			t.printStackTrace();
		}
		redirect("publico.HomeController.index");
	}
	
	public static class ChartBean {      
		
		public ChartBean(BufferedImage image, String description) { 
			setImage(image); 
			setDescription(description);     
		}           
		
		private java.awt.image.BufferedImage image;     
		private String description;
		
		public String getDescription() {         
			return description;     
		}      
		public void setDescription(String description) {        
			this.description = description;     
		}     
		public BufferedImage getImage() {         
			return image;     
		}      
		public void setImage(BufferedImage image) {         
			this.image = image;     
		}   
	} 
}