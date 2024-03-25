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

	public E merge(E entidade) {
		EntityManager entityManager = JPAUtil.getEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin(); // inicia
		E retorno = entityManager.merge(entidade); /* Salva/atualiza/retorna entidade */
		transaction.commit();
		entityManager.close();

		return retorno;
	}

	/* Salva ou atualiza */
	public E updateMerge(E entidade) {
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin(); // inicia
		E entidadeSalva = entityManager.merge(entidade); // salva se não existir, ou atualiza se existir
		transaction.commit();

		return entidadeSalva;
	}

	public void delete(E entidade) {
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin(); // inicia
		entityManager.remove(entidade);
		transaction.commit();
	}

	public void deletePorId(E entidade) {
	    EntityManager entityManager = JPAUtil.getEntityManager();
	    EntityTransaction transaction = entityManager.getTransaction();
	    transaction.begin(); // inicia a transação
	    
	    try {
	        Object id = JPAUtil.getPrimaryKey(entidade);
	        entityManager.createQuery("delete from " + entidade.getClass().getCanonicalName() + " where id = " + id)
	                     .executeUpdate();
	        
	        transaction.commit(); // finaliza a transação
	    } catch (Exception ex) {
	        if (transaction.isActive()) {
	            transaction.rollback(); // rollback em caso de exceção
	        }
	        throw ex; // relança a exceção para o chamador
	    } finally {
	        entityManager.close(); // fecha o EntityManager
	    }
	}

}
