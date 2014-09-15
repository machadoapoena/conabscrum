package utils;

import org.apache.commons.lang.StringUtils;

/**
 * Copyright (C) 2014 CONAB
 * Companhia Nacional de Abastecimento
 * www.conab.gov.br
 * 
 * StringUtil.java
 *
 * @author apoena.machado
 * @date 30/07/2014
 */
public class StringUtil extends StringUtils {

	// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	// Atributos.
	// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	/** The Constant ACENTOS_A. */
	private static final String ACENTOS_A = "[ÀÁÂÃÄ]";
	/** The Constant ACENTOS_E. */
	private static final String ACENTOS_E = "[ÈÉÊË]";
	/** The Constant ACENTOS_I. */
	private static final String ACENTOS_I = "[ÌÍÎÏ]";
	/** The Constant ACENTOS_O. */
	private static final String ACENTOS_O = "[ÒÓÔÖÕ]";
	/** The Constant ACENTOS_U. */
	private static final String ACENTOS_U = "[ÙÚÛÜ]";
	/** The Constant ACENTOS_C. */
	private static final String ACENTOS_C = "Ç";

	/**
	 * Checks if is empty.
	 *
	 * @param string the string
	 * @return true, if is empty
	 */
	public static boolean isEmpty(final String string) {
		return string == null ? true : string.trim().length() == 0 ? true : false;
	}
	
	/**
	 * Checks if is not empty.
	 *
	 * @param string the string
	 * @return true, if is not empty
	 */
	public static boolean isNotEmpty(final String string) {
		return !isEmpty(string);
	}
	
	/**
	 * Removes the specials characters.
	 *
	 * @param string the string
	 * @return the string
	 */
	public static String removeSpecialsCharacters(String string) {
		string = string.toUpperCase();
		string = string.replaceAll(ACENTOS_A, "A");
		string = string.replaceAll(ACENTOS_E, "E");
		string = string.replaceAll(ACENTOS_I, "I");
		string = string.replaceAll(ACENTOS_O, "O");
		string = string.replaceAll(ACENTOS_U, "U");
		string = string.replaceAll(ACENTOS_C, "C");
		return string;
	}
	
	/**
	 * Compare.
	 *
	 * @param arg0 the arg0
	 * @param arg1 the arg1
	 * @return the int
	 */
	public static int compare(final String arg0, final String arg1) {
		return StringUtil.removeSpecialsCharacters(arg0).compareToIgnoreCase(StringUtil.removeSpecialsCharacters(arg1));
	}
}