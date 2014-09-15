package controllers.publico;

import java.util.List;

import json.EnumOrdinalSerializer;
import json.HibernateProxyTypeAdapter;
import json.SkipStrategy;
import models.Atividade;
import models.Iteracao;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import controllers.BaseController;

/**
 * Copyright (C) 2014 CONAB
 * Companhia Nacional de Abastecimento
 * www.conab.gov.br
 *
 * IteracaoRestService.java
 *
 * @date 05/08/2014
 * @author apoena.machado
 */
public class IteracaoRestService extends BaseController {

	/**
	 * Recupera iteracao vencendo.
	 */
	public static void recuperaIteracoesVencendo()  {
		List<Iteracao> lstIteracao = iteracaoService.recuperaIteracoesVencendo(15);
	    renderJSON(toJson(lstIteracao));
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
