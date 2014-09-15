package controllers;

import java.io.File;
import java.io.FileInputStream;

import org.apache.commons.io.IOUtils;

import models.Usuario;

import controllers.CRUD.ObjectType;
import play.data.binding.Binder;
import play.db.Model;
import play.exceptions.TemplateNotFoundException;
import play.mvc.With;

/**
 * Copyright (C) 2014 CONAB
 * Companhia Nacional de Abastecimento
 * www.conab.gov.br
 *
 * Usuarios.java
 *
 * @date 29/07/2014
 * @author apoena.machado
 */
@With(Secure.class)
public class Usuarios extends BaseAdmController {

	
    public static void save(String id, File avatar) throws Exception {
        ObjectType type = ObjectType.get(getControllerClass());
        notFoundIfNull(type);
        Usuario object = Usuario.findById(Long.valueOf(id));
        notFoundIfNull(object);
        Binder.bindBean(params.getRootParamNode(), "object", object);
        object.avatar = IOUtils.toByteArray(new FileInputStream(avatar));
        validation.valid(object);
        if (validation.hasErrors()) {
            renderArgs.put("error", play.i18n.Messages.get("crud.hasErrors"));
            try {
                render(request.controller.replace(".", "/") + "/show.html", type, object);
            } catch (TemplateNotFoundException e) {
                render("CRUD/show.html", type, object);
            }
        }
        object._save();
        flash.success(play.i18n.Messages.get("crud.saved", type.modelName));
        if (params.get("_save") != null) {
            redirect(request.controller + ".list");
        }
        redirect(request.controller + ".show", object._key());
    }
}
