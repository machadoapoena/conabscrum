package controllers.publico;

import java.util.List;

import json.EnumOrdinalSerializer;
import json.HibernateProxyTypeAdapter;
import json.SkipStrategy;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import models.Mensagem;
import models.MensagemUsuario;
import models.Projeto;
import controllers.BaseController;

/**
 * Copyright (C) 2014 CONAB
 * Companhia Nacional de Abastecimento
 * www.conab.gov.br
 *
 * MensagemRestService.java
 *
 * @date 25/08/2014
 * @author apoena.machado
 */
public class MensagemRestService extends BaseController {

	public static void getMensagensUsuario()  {
		
		List<MensagemUsuario> lstMensagem = mensagemService.recuperaTodasMensagensParaUsuario(session.get("username"));
	    renderJSON(toJson(lstMensagem));
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
