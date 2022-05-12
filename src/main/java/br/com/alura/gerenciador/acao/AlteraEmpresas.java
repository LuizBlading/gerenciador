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

public class AlteraEmpresas implements Acao{

 public String executa(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	 
	 	System.out.println("Alterando empresa...");
		
		String nomeEmpresa = request.getParameter("nome");
		String dataAberturaEmpresa = request.getParameter("data");
		String paramId = request.getParameter("id");
		Integer id = Integer.valueOf(paramId);
		
		Date dataAbertura = null;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			dataAbertura = sdf.parse(dataAberturaEmpresa);
		} catch (ParseException e) {
			throw new ServletException(e); // Mostra o erro original da excessao ao stacktrace
		}
		
		System.out.println("Id: "+ id);
		
		Banco banco = new Banco();
		Empresa empresa = banco.buscarEmpresaPorId(id);
		empresa.setNome(nomeEmpresa);
		empresa.setDataAbertura(dataAbertura);
		
		return "redirect:entrada?acao=ListaEmpresas";
		
//		response.sendRedirect("entrada?acao=ListaEmpresas");
 }
}
