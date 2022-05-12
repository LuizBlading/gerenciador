package br.com.alura.gerenciador.modelo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Banco {
	
//	O static é um atributo da classe, e não da instancia do objeto
	private static List<Empresa> listaEmpresas = new ArrayList<Empresa>();
	private static List<Usuario> listaUsuarios = new ArrayList<Usuario>();
	private static Integer chaveSequencial = 1;
	
	static {
		Empresa empresa = new Empresa();
		empresa.setId(chaveSequencial++);
		empresa.setNome("Alura");
		Empresa empresa2 = new Empresa();
		empresa2.setId(chaveSequencial++);
		empresa2.setNome("Caelum");
		listaEmpresas.add(empresa);
		listaEmpresas.add(empresa2);
		
		// Criando usuarios
		Usuario user1 = new Usuario();
		user1.setLogin("luiz");
		user1.setSenha("123");
		
		Usuario user2 = new Usuario();
		user2.setLogin("nico");
		user2.setSenha("321");
		
		// Adicionando a lista
		listaUsuarios.add(user1);
		listaUsuarios.add(user2);
	}

	public void adiciona(Empresa empresa) {
		empresa.setId(Banco.chaveSequencial++);
		Banco.listaEmpresas.add(empresa);
		System.out.println(empresa.getNome() + empresa.getDataAbertura());
	}

	public List<Empresa> getEmpresas(){
		return Banco.listaEmpresas;
		// Retornando o atributo da classe
	}

	public void removeEmpresa(Integer id) {
		Iterator<Empresa> it = listaEmpresas.iterator();

		// hasNext verifca se tem um proximo elemento
		while (it.hasNext()) {
			Empresa emp = it.next(); // pega o proximo elemento

			if (emp.getId() == id) {
				it.remove(); // remove o elemento caso encontre este mesmo na lista
			}
		}

	}

	public Empresa buscarEmpresaPorId(Integer id) {
		
		for(Empresa empresa: listaEmpresas) {
			if(empresa.getId() == id) {
				return empresa;
			}
		}
		
		return null;
	}
}
