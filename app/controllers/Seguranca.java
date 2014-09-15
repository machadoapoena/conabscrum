package controllers;

import models.Usuario;
import controllers.Secure.Security;
import dominio.TipoPerfilEnum;

/**
 * Copyright (C) 2014 CONAB
 * Companhia Nacional de Abastecimento
 * www.conab.gov.br
 *
 * Seguranca.java
 *
 * @date 06/08/2014
 * @author apoena.machado
 */
public class Seguranca extends Security {

	/**
	 * Authenticate.
	 *
	 * @param username the username
	 * @param password the password
	 * @return true, if successful
	 */
	static boolean authenticate(String username, String password) {
		
		Usuario usuario = Usuario.find("byLogin", username).first();
		if (usuario != null && usuario.senha.equals(password)){
			session.put("perfil", usuario.perfil);
			return true;
		}
		return false;
	}
	
	/**
	 * Check.
	 *
	 * @param profile the profile
	 * @return true, if successful
	 */
	static boolean check(String profile) {
       
		if (session.get("perfil").equals(profile)) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Checks if is gestor.
	 *
	 * @return true, if is gestor
	 */
	public static boolean isGestor(){
		return session.get("perfil").equals(TipoPerfilEnum.GESTOR.getLabel());
	}
	
	/**
	 * Checks if is gerente.
	 *
	 * @return true, if is gerente
	 */
	public static boolean isGerente(){
		return session.get("perfil").equals(TipoPerfilEnum.GERENTE.getLabel());
	}
	
	/**
	 * Checks if is desenvolvedor.
	 *
	 * @return true, if is desenvolvedor
	 */
	public static boolean isDesenvolvedor(){
		return session.get("perfil").equals(TipoPerfilEnum.DESENVOLVEDOR.getLabel());
	}
	
	/**
	 * Checks if is scrum master.
	 *
	 * @return true, if is scrum master
	 */
	public static boolean isScrumMaster(){
		return session.get("perfil").equals(TipoPerfilEnum.SCRUMMASTER.getLabel());
	}	
}