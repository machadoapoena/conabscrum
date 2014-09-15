package json.adaptadores;

import java.lang.reflect.Type;

import models.Mensagem;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;


public class MensagemSerializer implements JsonSerializer<Mensagem> {

	public JsonElement serialize(Mensagem src, Type typeOfSrc, JsonSerializationContext context) {

		Gson gson = new GsonBuilder().setExclusionStrategies(new LocalExclusionStrategy()).create();
		return gson.toJsonTree(src);
    }   

    public static class LocalExclusionStrategy implements ExclusionStrategy {
        public boolean shouldSkipClass(Class<?> clazz) {
            return false;
        }   

        public boolean shouldSkipField(FieldAttributes f) {
            return f.getName().toLowerCase().equals("lstMensagemUsuario");
        }   
    }
}
