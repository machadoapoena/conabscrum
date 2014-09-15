package anotacoes.validadores;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import net.sf.oval.Validator;
import net.sf.oval.configuration.annotation.AbstractAnnotationCheck;
import net.sf.oval.context.OValContext;
import net.sf.oval.exception.OValException;
import play.templates.JavaExtensions;

public class DateAfterDateCheck extends AbstractAnnotationCheck<DateAfterDate> {

    /** Mensagem de erro. */
    public final static String message = "validation.dateAfterDate";
 
    /** Data o qual verificar. */
    public static String dataFutura; 
	
    @Override
    public void configure(DateAfterDate dateAfterDate) {
        setMessage(dateAfterDate.message());
        this.dataFutura = dateAfterDate.data();
    }    
    
    /**
     * Add the URI schemes to the message variables so they can be included
     * in the error message.
     */
    @Override
    public Map<String, String> createMessageVariables() {
        final Map<String, String> variables = new TreeMap<String, String>();
        variables.put("2", dataFutura);
        return variables;
    }
    
	@Override
	public boolean isSatisfied(Object validatedObject, Object value, OValContext context, Validator validator) throws OValException {

        requireMessageVariablesRecreation();
        	
//            final java.net.URI uri = new java.net.URI(value.toString());
//            final boolean schemeValid = schemes.contains(uri.getScheme());
//            return schemes.size() == 0 || schemeValid;
		return false;
	}

}
