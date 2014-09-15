package json;

import java.io.IOException;

import org.hibernate.Hibernate;
import org.hibernate.proxy.HibernateProxy;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

/**
 * Copyright (C) 2014 CONAB
 * Companhia Nacional de Abastecimento
 * www.conab.gov.br
 * 
 * HibernateProxyTypeAdapter.java
 *
 * @author apoena.machado
 * @date 31/07/2014
 */
public class HibernateProxyTypeAdapter extends TypeAdapter<HibernateProxy> {

	/** The Constant FACTORY. */
	public static final TypeAdapterFactory FACTORY;
	
	/** The gson context. */
	private final Gson gsonContext;
	
	// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	// Static Block.
	// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	static {
		FACTORY = new TypeAdapterFactory() {

			@Override
			public <T> TypeAdapter<T> create(final Gson gson, final TypeToken<T> type) {
				final boolean assignableFromType = HibernateProxy.class.isAssignableFrom(type.getRawType());
				if (assignableFromType) {
					return (TypeAdapter<T>) new HibernateProxyTypeAdapter(gson);
				}
				return null;
			}
		};
	}

	// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	// Contrutores
	// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	/**
	 * Instantiates a new hibernate proxy type adapter.
	 *
	 * @param gsonContext the gson context
	 */
	private HibernateProxyTypeAdapter(final Gson gsonContext) {
		this.gsonContext = gsonContext;
	}
	
	/* (non-Javadoc)
	 * @see com.google.gson.TypeAdapter#read(com.google.gson.stream.JsonReader)
	 */
	@Override
	public HibernateProxy read(final JsonReader arg0) throws IOException {
		return null;
	}
	
	/* (non-Javadoc)
	 * @see com.google.gson.TypeAdapter#write(com.google.gson.stream.JsonWriter, java.lang.Object)
	 */
	@Override
	public void write(final JsonWriter writer, final HibernateProxy proxy) throws IOException {
		if (proxy == null) {
			writer.nullValue();
			return;
		}
		final Class<?> baseType = Hibernate.getClass(proxy);
		final TypeAdapter adapter = this.gsonContext.getAdapter(TypeToken.get(baseType));
		final Object originalObject = proxy.getHibernateLazyInitializer().getImplementation();
		adapter.write(writer, originalObject);
	}
}