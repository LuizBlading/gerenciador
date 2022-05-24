package br.com.alura.gerenciador.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.alura.gerenciador.acao.Acao;

/**
 * Servlet implementation class UnicaEntradaServlet
 */
//@WebServlet(urlPatterns="/entrada")
public class UnicaEntradaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String paramAcao = request.getParameter("acao");
		
//		HttpSession sessao = request.getSession();
//		boolean usuarioNaoLogado = (sessao.getAttribute("usuarioLogado") == null);
//		boolean isAcaoProtegida = !(paramAcao.equals("Login") || paramAcao.equals("LoginForm"));
//		
//		if(isAcaoProtegida && usuarioNaoLogado) {
//			response.sendRedirect("entrada?acao=LoginForm");
//			return; // So sai do fluxo
//		}
		
		
		
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
		
//		String nome = null;
//
//		if (paramAcao.equals("ListaEmpresas")) {
//
//			ListaEmpresas acao = new ListaEmpresas();
//			nome = acao.executa(request, response);
//
//		} else if (paramAcao.equals("RemoveEmpresas")) {
//
//			RemoveEmpresas acao = new RemoveEmpresas();
//			nome = acao.executa(request, response);
//
//		} else if (paramAcao.equals("MostraEmpresas")) {
//
//			MostraEmpresas acao = new MostraEmpresas();
//			nome = acao.executa(request, response);
//		} else if (paramAcao.equals("AlteraEmpresas")) {
//
//			AlteraEmpresas acao = new AlteraEmpresas();
//			nome = acao.executa(request, response);
//		} else if (paramAcao.equals("NovaEmpresa")) {
//
//			NovaEmpresa acao = new NovaEmpresa();
//			nome = acao.executa(request, response);
//		} else if (paramAcao.equals("NovaEmpresaForm")) {
//
//			NovaEmpresaForm acao = new NovaEmpresaForm();
//			nome = acao.executa(request, response);
//		}
				
	}

}
