package br.com.cursojsf;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.cursojsf.dao.DaoGeneric;
import br.com.cursojsf.entidades.Pessoa;

/**
 * 
 * @author vinicius Scopos do managed Bean descrevem os limites superiores e
 *         inferiores de determinado assunto
 *
 */
@ViewScoped // permanece ativa até que o usuário navegue para uma proxima pagina
@ManagedBean(name = "pessoaBean") // nome do managedBean que será pego no template
public class PessoaBean {

	Pessoa pessoa = new Pessoa();
	private DaoGeneric<Pessoa> daoGeneric = new DaoGeneric<Pessoa>();

	/*
	 * public String salvar() { daoGeneric.salvar(pessoa); pessoa = new Pessoa();
	 * return ""; }
	 */

	public String salvar() {
		pessoa = daoGeneric.merge(pessoa);
		return "";
	}
	
	public String remove() {
		daoGeneric.deletePorId(pessoa);
		pessoa = new Pessoa();
		return "";
	}

	public String novo() {
		pessoa = new Pessoa();
		return "";
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

}
