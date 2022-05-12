package br.com.alura.gerenciador.acao;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.alura.gerenciador.modelo.Banco;

public class RemoveEmpresas implements Acao{
	
	public String executa(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Removendo empresas");
		
		String paramId = request.getParameter("id");
		Integer id = Integer.valueOf(paramId);
		
		System.out.println("ID: " + id);
		
		Banco banco = new Banco();
		banco.removeEmpresa(id);
		
		// Redirecionamento para a rota de listaEmpresas
//		response.sendRedirect("entrada?acao=ListaEmpresas"); NÃO USO MAIS, APENAS ANOTAÇÃO PARA ESTUDO
		
		return "redirect:entrada?acao=ListaEmpresas";
	}
}
