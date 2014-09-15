package utils;

/**
 * Copyright (C) 2014 CONAB
 * Companhia Nacional de Abastecimento
 * www.conab.gov.br
 *
 * BrasilUtil.java
 *
 * @date 30/07/2014
 * @author apoena.machado
 */
public class BrasilUtil {

	/**
	 * Checks if is cnpj.
	 *
	 * @param cnpj the cnpj
	 * @return true, if is cnpj
	 */
	public static boolean isCNPJ(String cnpj) {
		try {
			cnpj = cnpj.replaceAll("[./-]", "");
			if (cnpj.equals("00000000000000") || cnpj.equals("11111111111111") || cnpj.equals("22222222222222") || cnpj.equals("33333333333333") || cnpj.equals("44444444444444")
					|| cnpj.equals("55555555555555") || cnpj.equals("66666666666666") || cnpj.equals("77777777777777") || cnpj.equals("88888888888888") || cnpj.equals("99999999999999")
					|| cnpj.length() != 14) {
				return false;
			}
			// First step
			int count = 0;
			final char[] cnpjCharArray = cnpj.toCharArray();
			for (int i = 0; i < 4; i++) {
				if (cnpjCharArray[i] - 48 >= 0 && cnpjCharArray[i] - 48 <= 9) {
					count += (cnpjCharArray[i] - 48) * (6 - (i + 1));
				}
			}
			for (int i = 0; i < 8; i++) {
				if (cnpjCharArray[i + 4] - 48 >= 0 && cnpjCharArray[i + 4] - 48 <= 9) {
					count += (cnpjCharArray[i + 4] - 48) * (10 - (i + 1));
				}
			}
			int checksum = 11 - count % 11;
			String aux = cnpj.substring(0, 12);
			aux += checksum == 10 || checksum == 11 ? "0" : Integer.toString(checksum);
			// Seconde step
			count = 0;
			for (int i = 0; i < 5; i++) {
				if (cnpjCharArray[i] - 48 >= 0 && cnpjCharArray[i] - 48 <= 9) {
					count += (cnpjCharArray[i] - 48) * (7 - (i + 1));
				}
			}
			for (int i = 0; i < 8; i++) {
				if (cnpjCharArray[i + 5] - 48 >= 0 && cnpjCharArray[i + 5] - 48 <= 9) {
					count += (cnpjCharArray[i + 5] - 48) * (10 - (i + 1));
				}
			}
			checksum = 11 - count % 11;
			aux += checksum == 10 || checksum == 11 ? "0" : Integer.toString(checksum);
			return cnpj.equals(aux);
		} catch (final Exception e) {
			return false;
		}
	}
	
	/**
	 * Checks if is cpf.
	 *
	 * @param cpf the cpf
	 * @return true, if is cpf
	 */
	public static boolean isCPF(String cpf) {
		try {
			cpf = cpf.replaceAll("[.-]", "");
			if (cpf.equals("0000000000") || cpf.equals("11111111111") || cpf.equals("22222222222") || cpf.equals("33333333333") || cpf.equals("44444444444") || cpf.equals("55555555555")
					|| cpf.equals("66666666666") || cpf.equals("77777777777") || cpf.equals("88888888888") || cpf.equals("99999999999") || cpf.length() != 14) {
				return false;
			}
			int aux_digit1 = 0, aux_digit2 = 0, cpf_digit;
			for (int i = 1; i < cpf.length() - 1; i++) {
				cpf_digit = Integer.valueOf(cpf.substring(i - 1, i)).intValue();
				aux_digit1 = aux_digit1 + (11 - i) * cpf_digit;
				aux_digit2 = aux_digit2 + (12 - i) * cpf_digit;
			}
			int digit1 = 0, digit2 = 0;
			int remainder = aux_digit1 % 11;
			if (remainder < 2) {
				digit1 = 0;
			} else {
				digit1 = 11 - remainder;
			}
			aux_digit2 += 2 * digit1;
			remainder = aux_digit2 % 11;
			if (remainder < 2) {
				digit2 = 0;
			} else {
				digit2 = 11 - remainder;
			}
			final String checksum = cpf.substring(cpf.length() - 2, cpf.length());
			final String result = String.valueOf(digit1) + String.valueOf(digit2);
			return checksum.equals(result);
		} catch (final Exception e) {
			return false;
		}
	}
}