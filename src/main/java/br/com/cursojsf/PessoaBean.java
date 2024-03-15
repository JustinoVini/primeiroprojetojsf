package br.com.cursojsf;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.html.HtmlCommandButton;

/**
 * 
 * @author vinicius Scopos do managed Bean descrevem os limites superiores e
 *         inferiores de determinado assunto
 *
 */
// @NoneScoped // o bean será instanciado a cada vez que for referenciado
// @RequestScoped // padrão, vida curta começa quando é referenciado em um req e termina na res
@ViewScoped // permanece ativa até que o usuário navegue para uma proxima pagina
// @SessionScoped // mantem a sessao durante varias req e ate mesmo navegacoes entre paginas ate o final da sessao do user
// @ApplicationScoped // mantem a instancia durante o tempo de execucao 
@ManagedBean(name = "pessoaBean") // nome do managedBean que será pego no template
public class PessoaBean {

	// dados que vou receber da tela
	private String nome;
	
	private List<String> nomes = new ArrayList<String>();
	
	/*Amarrando um componetne de backend usando biding*/
	private HtmlCommandButton commandButton;

	public String addNome() { // método em String
		nomes.add(nome);
		
		// testando
		if (nomes.size() > 3) {
			commandButton.setDisabled(true);
			return "paginanavegada?faces-redirect=true";
		}
		
		return ""; // dessa forma com action ele permanece na tela
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<String> getNomes() {
		return nomes;
	}

	public void setNomes(List<String> nomes) {
		this.nomes = nomes;
	}
	
	public HtmlCommandButton getCommandButton() {
		return commandButton;
	}
	
	public void setCommandButton(HtmlCommandButton commandButton) {
		this.commandButton = commandButton;
	}

}
