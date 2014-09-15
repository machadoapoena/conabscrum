package anotacoes.validadores;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import net.sf.oval.configuration.annotation.Constraint;
import validacao.CpfCheck;

/**
 * Copyright (C) 2014 CONAB
 * Companhia Nacional de Abastecimento
 * www.conab.gov.br
 *
 * CPF.java
 *
 * @date 30/07/2014
 * @author apoena.machado
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.FIELD, ElementType.PARAMETER })
@Constraint(checkWith = CpfCheck.class)
public @interface CPF {

	/**
	 * Message.
	 *
	 * @return the string
	 */
	String message() default CpfCheck.mes;
}
