package jobs;

import models.Backlog;
import models.Iteracao;
import models.Projeto;
import models.Usuario;
import play.jobs.Job;
import play.jobs.OnApplicationStart;
import play.test.Fixtures;

/**
 * Copyright (C) 2014 CONAB
 * Companhia Nacional de Abastecimento
 * www.conab.gov.br
 *
 * InitialDataLoad.java
 *
 * @date 29/07/2014
 * @author apoena.machado
 */
@OnApplicationStart
public class InitialDataLoad extends Job {

    public void doJob() {
//        if(Usuario.count() == 0) {
//            Fixtures.loadModels("initial-data-usuario.yml");
//        }
//        if(Projeto.count() == 0) {
//            Fixtures.loadModels("initial-data-projeto.yml");
//        }
//        if(Iteracao.count() == 0) {
//            Fixtures.loadModels("initial-data-iteracao.yml");
//        }
//        if(Backlog.count() == 0) {
//            Fixtures.loadModels("initial-data-backlog.yml");
//        }
    }
}
