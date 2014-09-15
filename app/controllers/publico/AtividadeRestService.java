package controllers.publico;

import java.util.ArrayList;
import java.util.List;

import json.EnumOrdinalSerializer;
import json.HibernateProxyTypeAdapter;
import json.SkipStrategy;
import models.Atividade;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import controllers.BaseController;
import controllers.Seguranca;
import dominio.TipoSituacaoAtividadeEnum;

/**
 * Copyright (C) 2014 CONAB
 * Companhia Nacional de Abastecimento
 * www.conab.gov.br
 *
 * AtividadeRestService.java
 *
 * @date 31/07/2014
 * @author apoena.machado
 */
public class AtividadeRestService extends BaseController {

	/**
	 * Gets the atividades em progresso.
	 *
	 * @return the atividades em progresso
	 */
	public static void getAtividadesEmProgresso()  {
		List<Atividade> lstAtividade = atividadeService.recuperaAtividadesEmProgresso();
	    renderJSON(toJson(lstAtividade));
	}
	
	/**
	 * Gets the atividades sem usuario.
	 *
	 * @return the atividades sem usuario
	 */
	public static void getAtividadesSemUsuario()  {
		List<Atividade> lstAtividade = new ArrayList<Atividade>();
		if (Seguranca.isScrumMaster()){
			lstAtividade = atividadeService.recuperaAtividadesSemUsuarioDeProjetosPorScrumMaster(session.get("username"));
		} else if (Seguranca.isGerente()){
			lstAtividade = atividadeService.recuperaAtividadesSemUsuarioDeProjetosPorGerente(session.get("username"));
		} else if (Seguranca.isGestor()) {
			lstAtividade = atividadeService.recuperaTodasAtividadesSemUsuario();	
		}
	    renderJSON(toJson(lstAtividade));
	}
	
	/**
	 * Atualiza situacao.
	 *
	 * @param idAtividade the id atividade
	 * @param situacao the situacao
	 */
	public static void atualizaSituacao(Long idAtividade, Integer situacao)  {
		atividadeService.atualizaSituacao(idAtividade, TipoSituacaoAtividadeEnum.values()[situacao]);
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
