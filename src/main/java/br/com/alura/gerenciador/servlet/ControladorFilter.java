package br.com.alura.gerenciador.servlet;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.alura.gerenciador.acao.Acao;

/**
 * Servlet Filter implementation class AutorizacaoFilter
 */
//@WebFilter("/entrada")
public class ControladorFilter extends HttpFilter implements Filter {

	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {
		
		System.out.println("ControladorFilter");
		
		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpServletResponse response = (HttpServletResponse) servletResponse;
		
		String paramAcao = request.getParameter("acao");
		
		String nomeDaClasse = "br.com.alura.gerenciador.acao." + paramAcao;
		
		String nome;
		try {
			Class classe = Class.forName(nomeDaClasse); // Carrega a classe com o nome
			Object obj = classe.newInstance(); // Cria uma instancia da classe
			Acao acao = (Acao) obj; // faz um "parsing" da classe, e assim poder chamar os metodos da classe instanciada
			nome = acao.executa(request, response);
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | ServletException
				| IOException e) {
			throw new ServletException(e);
		}
		
		String[] tipoEEndereco = nome.split(":");
		
		if(tipoEEndereco[0].equals("forward")) {
			// Encaminha para uma VIEW (JSP)
			RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/views/" + tipoEEndereco[1]);
			rd.forward(request, response);
		} else if(tipoEEndereco[0].equals("redirect")) {
//			Solicita para o navegado redirecionar para 
//			uma acao (controller que usara model e redirecionará novamente ao servlet e ao view) especifica
			response.sendRedirect(tipoEEndereco[1]);
		} 
	}

}
