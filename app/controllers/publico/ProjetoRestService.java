package controllers.publico;

import models.Projeto;
import play.mvc.Controller;


public class ProjetoRestService extends Controller {

	public static void getQuantidade()  {
	    Long quantidade = Projeto.count();
	    renderJSON(quantidade);
	}
}
