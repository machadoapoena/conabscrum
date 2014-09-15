package controllers;

import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.lang.reflect.Constructor;
import java.text.DecimalFormat;
import java.util.List;

import models.Projeto;
import models.Usuario;
import notifiers.EmailTemplate;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.PieSectionLabelGenerator;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.util.Rotation;

import play.data.binding.Binder;
import play.db.Model;
import play.exceptions.TemplateNotFoundException;
import play.mvc.With;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGEncodeParam;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

import dominio.MensagemEnum;

/**
 * Copyright (C) 2014 CONAB
 * Companhia Nacional de Abastecimento
 * www.conab.gov.br
 *
 * Projetos.java
 *
 * @date 29/07/2014
 * @author apoena.machado
 */
@Check({"Gerente","Gestor"})
@With(Secure.class)
public class Projetos extends BaseAdmController {
	
    /**
     * List.
     *
     * @param page the page
     * @param search the search
     * @param searchFields the search fields
     * @param orderBy the order by
     * @param order the order
     */
    public static void list(int page, String search, String searchFields, String orderBy, String order) {
    	
        ObjectType type = ObjectType.get(getControllerClass());
        notFoundIfNull(type);
        if (page < 1) {
            page = 1;
        }
        String where = (String) request.args.get("where");
        where = where != null ? where : "";
        if (Seguranca.isGerente()){
        	where = where + " gerente.id = " + usuarioService.recuperaUsuarioLogado(session.get("username")).id;        	
        }
        where = where != "" ? where : null;
        List<Model> objects = type.findPage(page, search, searchFields, orderBy, order, where);
        Long count = type.count(search, searchFields, where);
        Long totalCount = type.count(null, null, where);
        try {
            render(type, objects, count, totalCount, page, orderBy, order);
        } catch (TemplateNotFoundException e) {
            render(CRUD_LIST_HTML, type, objects, count, totalCount, page, orderBy, order);
        }
    }
    
    /**
     * Creates the.
     *
     * @throws Exception the exception
     */
    public static void create() throws Exception {
    	
        ObjectType type = ObjectType.get(getControllerClass());
        notFoundIfNull(type);
        Constructor<?> constructor = type.entityClass.getDeclaredConstructor();
        constructor.setAccessible(true);
        Model object = (Model) constructor.newInstance();
        Binder.bindBean(params.getRootParamNode(), "object", object);
        validation.valid(object);
        if (validation.hasErrors()) {
            renderArgs.put("error", play.i18n.Messages.get("crud.hasErrors"));
            try {
                render(request.controller.replace(".", "/") + "/blank.html", type, object);
            } catch (TemplateNotFoundException e) {
                render(CRUD_BLANK_HTML, type, object);
            }
        }
        object._save();
        Projeto projeto = (Projeto) object;
        Usuario usuario = Usuario.recuperaUsuariosPorLogin(session.get("username"));
        mensagemService.salvarMensagem(MensagemEnum.NOVO_PROJETO, usuario, projeto.nome, usuario.nome); 
        flash.success(play.i18n.Messages.get("crud.created", type.modelName));
        EmailTemplate.novoProjetoAtribuido((Projeto) object);
        if (params.get("_save") != null) {
            redirect(request.controller + ".list");
        }
        if (params.get("_saveAndAddAnother") != null) {
            redirect(request.controller + ".blank");
        }
        redirect(request.controller + ".show", object._key());
    }
    
    /**
     * Save.
     *
     * @param id the id
     * @throws Exception the exception
     */
    public static void save(String id) throws Exception {
    	
        ObjectType type = ObjectType.get(getControllerClass());
        notFoundIfNull(type);
        Projeto object = Projeto.findById(Long.valueOf(id));
        notFoundIfNull(object);
        final String scrumMaster = object.scrumMaster.login;
        Binder.bindBean(params.getRootParamNode(), "object", object);
        validation.valid(object);
        if (validation.hasErrors()) {
            renderArgs.put("error", play.i18n.Messages.get("crud.hasErrors"));
            try {
                render(request.controller.replace(".", "/") + "/show.html", type, object);
            } catch (TemplateNotFoundException e) {
                render(CRUD_SHOW_HTML, type, object);
            }
        }
        object._save();
        flash.success(play.i18n.Messages.get("crud.saved", type.modelName));
        if (!scrumMaster.equals(object.scrumMaster.login)){
        	EmailTemplate.novoProjetoAtribuido(object);
        }
        if (params.get("_save") != null) {
            redirect(request.controller + ".list");
        }
        redirect(request.controller + ".show", object._key());
    }
    
    public static void visualizarGrafico() throws Exception {
    	DefaultPieDataset piedataset = new DefaultPieDataset();
        piedataset.setValue("Finalizadas", Double.valueOf(10));
        piedataset.setValue("A fazer", Double.valueOf(20));
        JFreeChart jfreechart = ChartFactory.createPieChart3D("Projeto: dsdssd", piedataset, true, true, false);
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
				
				final ByteArrayOutputStream out = new ByteArrayOutputStream();
		JPEGImageEncoder encoder = 
		JPEGCodec.createJPEGEncoder(out); 
		JPEGEncodeParam param = encoder.getDefaultJPEGEncodeParam(img);
		param.setQuality(1.0f,true); 
		encoder.encode(img,param); 
		out.close(); 
		renderBinary(new ByteArrayInputStream(out.toByteArray()));
    }
}