package br.com.cursojsf.filter;

import java.io.IOException;
import java.io.Serializable;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import br.com.cursojsf.entidades.Pessoa;
import br.com.cursojsf.jpautil.JPAUtil;

@WebFilter(urlPatterns = { "/*" }) /* Verifica as urls */
public class FilterAutenticacao implements Filter, Serializable {

	private static final long serialVersionUID = 1L;

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) request; // pega a requisição
		HttpSession session = req.getSession(); // add na sessão
		
		Pessoa usuarioLogado = (Pessoa) session.getAttribute("usuarioLogado");
		
		String url = req.getServletPath();
		
		/*Verifica se tem usuario */
		if (!url.equalsIgnoreCase("index.jsf") && usuarioLogado == null) {
			RequestDispatcher redireciona = request.getRequestDispatcher("index.jsf");
			redireciona.forward(request, response);
			return;
		} else {

			/*Auth*/
			System.out.println("Invocando o filtro");
			
			/*Executa as ações do req e res*/
			chain.doFilter(request, response);
		}
		
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		JPAUtil.getEntityManager();
	}
	
	@Override
	public void destroy() {
	}

}
