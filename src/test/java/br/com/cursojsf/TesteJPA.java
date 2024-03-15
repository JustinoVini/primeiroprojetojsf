package br.com.cursojsf;

import org.junit.Test;

import br.com.cursojsf.dao.DaoGeneric;
import br.com.cursojsf.entidades.Pessoa;

public class TesteJPA {

	@Test
	public void testeHibernateUtil() { // teste insere novo
		DaoGeneric<Pessoa> daoGeneric = new DaoGeneric<Pessoa>();

		Pessoa pessoa = new Pessoa();

		pessoa.setNome("Primeiro Teste 4");
		pessoa.setIdade(45);
		pessoa.setSobrenome("Tester");

		daoGeneric.salvar(pessoa);
	}
	
}
