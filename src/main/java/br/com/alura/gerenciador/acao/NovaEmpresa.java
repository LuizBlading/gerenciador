package br.com.alura.gerenciador.acao;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.alura.gerenciador.modelo.Banco;
import br.com.alura.gerenciador.modelo.Empresa;

public class NovaEmpresa implements Acao{
	
	public String executa(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		System.out.println("Cadastrando nova empresa");
		
		String nomeEmpresa = request.getParameter("nome");
		String dataAberturaEmpresa = request.getParameter("data");
		
		Date dataAbertura = null;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			dataAbertura = sdf.parse(dataAberturaEmpresa);
		} catch (ParseException e) {
			throw new ServletException(e); // Mostra o erro original da excessao ao stacktrace
		}
		
		Empresa empresa = new Empresa();
		empresa.setNome(nomeEmpresa);
		empresa.setDataAbertura(dataAbertura);
		
		Banco banco = new Banco();
		banco.adiciona(empresa);
		
		request.setAttribute("empresa", empresa.getNome());  // Passa 'empresa' como apelido para o jsp no request
		
		//response.sendRedirect("entrada?acao=ListaEmpresas"); // retorna ao navegador fazendo um redirecionamento ao servlet listaEmpresas
		return "redirect:entrada?acao=ListaEmpresas";

		//		Chamar o JSP
//		Distara e retorna um jsp com o html
//		RequestDispatcher rd = request.getRequestDispatcher("/listaEmpresas");

//		rd.forward(request, response);  Manda para o navegador/JSP
	}
}
