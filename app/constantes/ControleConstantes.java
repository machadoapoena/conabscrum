package constantes;

/**
 * Copyright (C) 2014 CONAB
 * Companhia Nacional de Abastecimento
 * www.conab.gov.br
 *
 * ControleConstantes.java
 *
 * @date 30/07/2014
 * @author apoena.machado
 */
public interface ControleConstantes {

	// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	// Listeners @Before | @After | @Finally prioridade.
	// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	/** The Constant MAX_PRIORITY. */
	public static final short MAX_PRIORITY = 0;
	/** The Constant HIGH_PRIORITY. */
	public static final short HIGH_PRIORITY = 10;
	/** The Constant NORM_PRIORITY. */
	public static final short NORM_PRIORITY = 20;
	/** The Constant MIN_PRIORITY. */
	public static final short MIN_PRIORITY = 40;

	// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	// Variaveis de sessao
	// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	/** The Constant CACHE_TIMEOUT. */
	public static final String CACHE_TIMEOUT = "1h";

	// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	// Variáveis Genericas
	// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	/** The Constant CONNECTED_USER. */
	public static final String CONNECTED_USER = "connectedUser";
	/** The Constant LIST_HTML. */
	public static final String LIST_HTML = "list.html";
	/** The Constant INDEX_HTML. */
	public static final String INDEX_HTML = "index.html";
	/** The Constant SHOW_HTML. */
	public static final String SHOW_HTML = "show.html";
	/** The Constant BLANK_HTML. */
	public static final String BLANK_HTML = "blank.html";
	/** The Constant CRUD_BLANK_HTML. */
	public static final String CRUD_BLANK_HTML = "CRUD/blank.html";
	/** The Constant CRUD_SHOW_HTML. */
	public static final String CRUD_SHOW_HTML = "CRUD/show.html";
	/** The Constant CRUD_LIST_HTML. */
	public static final String CRUD_LIST_HTML = "CRUD/list.html";
	/** The Constant CRUD_INDEX_HTML. */
	public static final String CRUD_INDEX_HTML = "CRUD/index.html";
	
	// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	// Variáveis Tipo Alerta
	// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~	
	/** The Constant ALERTA_INFO. */
	public static final String ALERTA_INFO = "info";
}