package controllers;

import java.util.List;

import models.Iteracao;
import play.db.Model;
import play.exceptions.TemplateNotFoundException;
import play.mvc.With;
import controllers.CRUD.For;

/**
 * Copyright (C) 2014 CONAB
 * Companhia Nacional de Abastecimento
 * www.conab.gov.br
 *
 * Iteracoes.java
 *
 * @date 30/07/2014
 * @author apoena.machado
 */
@Check({"Gerente","Gestor","Scrum Master"})
@With(Secure.class)
@For(Iteracao.class)
public class Iteracoes extends BaseAdmController {

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
    	Long idUsuario = usuarioService.recuperaUsuarioLogado(session.get("username")).id;
		if (Seguranca.isGerente()){
    		where +=  "projeto.gerente.id = " + idUsuario;
    	} else if (Seguranca.isScrumMaster()) {
    		where +=  "projeto.scrumMaster.id = " + idUsuario;	        		
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
}