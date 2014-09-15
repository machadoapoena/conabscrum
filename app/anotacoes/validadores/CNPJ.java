package anotacoes.validadores;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import net.sf.oval.configuration.annotation.Constraint;
import validacao.CnpjCheck;

/**
 * Copyright (C) 2014 CONAB
 * Companhia Nacional de Abastecimento
 * www.conab.gov.br
 *
 * CNPJ.java
 *
 * @date 30/07/2014
 * @author apoena.machado
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.FIELD, ElementType.PARAMETER })
@Constraint(checkWith = CnpjCheck.class)
public @interface CNPJ {

	/**
	 * Message.
	 *
	 * @return the string
	 */
	String message() default CnpjCheck.mes;
}
