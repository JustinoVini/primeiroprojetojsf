package br.com.cursojsf.jpautil;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAUtil {
	
	private static EntityManagerFactory factory;
	
	/*Recurso que toda vez fizer referencia pra essa classe
	 * se nao existir uma entity manag será criado*/
	static {
		if (factory == null) { // se null cria uma única vez			
			factory = Persistence.createEntityManagerFactory("primeiroprojetojsf");
		}
	}
	
	public static EntityManager getEntityManager() {
		return factory.createEntityManager();
	}

}
