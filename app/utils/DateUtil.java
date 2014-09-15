package utils;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Copyright (C) 2014 CONAB
 * Companhia Nacional de Abastecimento
 * www.conab.gov.br
 *
 * DateUtil.java
 *
 * @date 29/07/2014
 * @author apoena.machado
 */
public class DateUtil {

	/** The Constant DATA_FORMATACAO_BR. */
	public final static String DATA_FORMATACAO_BR = "dd/MM/yyyy";
	
	/**
	 * Data atual.
	 *
	 * @return the date
	 */
	public static Date dataAtual() {
		return new Date();
	}
	
	/**
	 * Adiciona dias.
	 *
	 * @param data the data
	 * @param qtd the qtd
	 * @return the date
	 */
	public static final Date adicionaDias(Date data, int qtd) {
		
		Calendar c = Calendar.getInstance();
		c.setTime(data);
		c.add(Calendar.DATE, qtd);
		return c.getTime();
	}
	
	/**
	 * Quantidade de dias entre datas.
	 *
	 * @param dataInicial the data inicial
	 * @param dataFinal the data final
	 * @return the long
	 */
	public static final Long quantidadeDeDiasEntreDatas(Date dataInicial, Date dataFinal) {
		
		if (dataInicial == null || dataFinal == null) {
			return null;
		}
		final long differenceMilliSeconds = dataFinal.getTime() - dataInicial.getTime();
		return differenceMilliSeconds / 1000 / 60 / 60 / 24;
	}
	
	/**
	 * Formatar data.
	 *
	 * @param data the data
	 * @param padrao the padrao
	 * @return the string
	 */
	public static final String formatarData(Date data, String padrao) {
		DateFormat format = new SimpleDateFormat(padrao);
		String dataFormatada = format.format(data);
		return dataFormatada;
	}
	
    /**
     * Current timestamp.
     *
     * @return the timestamp
     * @throws SQLException the SQL exception
     * @throws ParseException the parse exception
     */
    public static Timestamp timestampAtual() {
        return new Timestamp(dataAtual().getTime());
    }
}