package json;

import java.lang.reflect.Type;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

/**
 * Copyright (C) 2014 CONAB
 * Companhia Nacional de Abastecimento
 * www.conab.gov.br
 * 
 * EnumOrdinalSerializer.java
 *
 * @author apoena.machado
 * @date 31/07/2014
 */
public class EnumOrdinalSerializer implements JsonSerializer<Enum<?>> {

	/** The Constant parser. */
	private static final JsonParser parser = new JsonParser();

	/* (non-Javadoc)
	 * @see com.google.gson.JsonSerializer#serialize(java.lang.Object, java.lang.reflect.Type, com.google.gson.JsonSerializationContext)
	 */
	@Override
	public JsonElement serialize(final Enum<?> object, final Type type, final JsonSerializationContext context) {
		final Integer ordinal = object.ordinal();
		return parser.parse(ordinal.toString());
	}
}