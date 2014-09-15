package validacao;

import anotacoes.validadores.CNPJ;
import net.sf.oval.Validator;
import net.sf.oval.configuration.annotation.AbstractAnnotationCheck;
import net.sf.oval.context.OValContext;
import net.sf.oval.exception.OValException;
import utils.BrasilUtil;

/**
 * Copyright (C) 2014 CONAB
 * Companhia Nacional de Abastecimento
 * www.conab.gov.br
 *
 * CpfCheck.java
 *
 * @date 30/07/2014
 * @author apoena.machado
 */
public class CpfCheck extends AbstractAnnotationCheck<CNPJ> {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1522109926195795451L;
	/** The Constant mes. */
	public static final String mes = "validation.cpf";

	// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	// * @see net.sf.oval.Check#isSatisfied(java.lang.Object, java.lang.Object,
	// net.sf.oval.context.OValContext, net.sf.oval.Validator)
	// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	@Override
	public boolean isSatisfied(final Object validatedObject, final Object value, final OValContext context, final Validator validator) throws OValException {
		return BrasilUtil.isCPF(value.toString());
	}
}