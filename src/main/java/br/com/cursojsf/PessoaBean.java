package br.com.cursojsf;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import br.com.cursojsf.dao.DaoGeneric;
import br.com.cursojsf.entidades.Pessoa;
import br.com.cursojsf.repository.IDaoPessoa;
import br.com.cursojsf.repository.IDaoPessoaImpl;

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
	private List<Pessoa> pessoas = new ArrayList<Pessoa>();
	
	private IDaoPessoa iDaoPessoa = new IDaoPessoaImpl();
	
	/*
	 * public String salvar() { daoGeneric.salvar(pessoa); pessoa = new Pessoa();
	 * return ""; }
	 */

	public String salvar() {
		pessoa = daoGeneric.merge(pessoa);
		carregarPessoas();
		return "";
	}
	
	public String remove() {
		daoGeneric.deletePorId(pessoa);
		pessoa = new Pessoa();
		carregarPessoas();
		return "";
	}

	public String novo() {
		pessoa = new Pessoa();
		return "";
	}
	
	@PostConstruct // ao abrir a tela e instanciar o bean, carrega o método postconstruct
	public void carregarPessoas() {
		pessoas = daoGeneric.getListEntity(Pessoa.class); // carrega as pessoas
	}
	
	public String logar() {
		carregarPessoas();
		Pessoa pessoaUser = iDaoPessoa.consultarUsuario(pessoa.getLogin(), pessoa.getSenha());
		
		if (pessoa != null) { // achou user
			
			// add na sessão
			FacesContext context = FacesContext.getCurrentInstance();
			ExternalContext externalContext = context.getExternalContext();
			
			HttpServletRequest req = (HttpServletRequest) externalContext.getRequest();
			HttpSession session = req.getSession();
			
			session.setAttribute("usuarioLogado", pessoaUser.getLogin());
			
			return "primeirapagina.jsf";
		}
		
		System.out.println("Login: " + pessoa.getLogin() + " - " + pessoa.getSenha());
		
		return "index.jsf";
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public List<Pessoa> getPessoas() {
		return pessoas;
	}
	
}
