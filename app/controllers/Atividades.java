package controllers;

import java.lang.reflect.Constructor;
import java.util.List;

import models.Atividade;
import notifiers.EmailTemplate;
import play.data.binding.Binder;
import play.db.Model;
import play.exceptions.TemplateNotFoundException;
import play.mvc.With;

/**
 * Copyright (C) 2014 CONAB
 * Companhia Nacional de Abastecimento
 * www.conab.gov.br
 *
 * Atividades.java
 *
 * @date 29/07/2014
 * @author apoena.machado
 */
@With(Secure.class)
public class Atividades extends BaseAdmController {

	public static void list(int page, String search, String searchFields, String orderBy, String order) {
		
		ObjectType type = ObjectType.get(getControllerClass());
		notFoundIfNull(type);
		if (page < 1) {
			page = 1;
		}
		String where = (String) request.args.get("where");
		where = where != null ? where : "";
		Long idUsuario = usuarioService.recuperaUsuarioLogado(session.get("username")).id;
		if (Seguranca.isGerente()){
			where +=  " backLog.iteracao.projeto.gerente.id = " + idUsuario;
		} else if (Seguranca.isScrumMaster()) {
			where +=  " backLog.iteracao.projeto.scrumMaster.id = " + idUsuario;
		} else if (Seguranca.isDesenvolvedor()) {
			where +=  " usuario.id = " + idUsuario;
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
        Atividade object = (Atividade) constructor.newInstance();
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
        flash.success(play.i18n.Messages.get("crud.created", type.modelName));
        if (object.usuario != null){
        	EmailTemplate.novaAtividadeAtribuida(object.usuario, object, session.get("username"));        	
        }
        if (params.get("_save") != null) {
            redirect(request.controller + ".list");
        }
        if (params.get("_saveAndAddAnother") != null) {
            redirect(request.controller + ".blank");
        }
        redirect(request.controller + ".show", object._key());
    }
}