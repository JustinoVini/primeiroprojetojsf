package br.com.cursojsf.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import br.com.cursojsf.jpautil.JPAUtil;

public class DaoGeneric<E> {

	private EntityManager entityManager = JPAUtil.getEntityManager(); // pega o em

	/* Cria uma nova entidade */
	public void salvar(E entidade) {
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin(); // inicia
		entityManager.persist(entidade);
		transaction.commit();
	}

	/* Salva ou atualiza */
	public E updateMerge(E entidade) {
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin(); // inicia
		E entidadeSalva = entityManager.merge(entidade); // salva se não existir, ou atualiza se existir
		transaction.commit();

		return entidadeSalva;
	}
	
}
