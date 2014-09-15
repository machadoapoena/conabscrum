package controllers.publico;

import java.util.List;

import json.EnumOrdinalSerializer;
import json.HibernateProxyTypeAdapter;
import json.SkipStrategy;
import models.Usuario;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import controllers.BaseController;

/**
 * Copyright (C) 2014 CONAB
 * Companhia Nacional de Abastecimento
 * www.conab.gov.br
 *
 * UsuarioRestService.java
 *
 * @date 31/07/2014
 * @author apoena.machado
 */
public class UsuarioRestService extends BaseController {

	/**
	 * Gets the usuarios sem demanda.
	 *
	 * @return the usuarios sem demanda
	 */
	public static void getUsuariosSemAtividade()  {
		List<Usuario> lstUsuario = usuarioService.recuperaUsuariosSemAtividadeComQtdFinalizadas();
	    renderJSON(toJson(lstUsuario));
	}
	
	
	public static String toJson(final List<?> list) {
		final GsonBuilder builder = buildJsonParser();
		final Gson gson = builder.create();
		return gson.toJson(list);
	}
	
	public static GsonBuilder buildJsonParser() {
		final GsonBuilder builder = new GsonBuilder();
		builder.registerTypeHierarchyAdapter(Enum.class, new EnumOrdinalSerializer());
		builder.registerTypeAdapterFactory(HibernateProxyTypeAdapter.FACTORY);
		builder.setExclusionStrategies(new SkipStrategy());
		builder.setDateFormat("yyyy-MM-dd HH:mm:ss.SSS Z");
		builder.setPrettyPrinting();
		return builder;
	}
}
