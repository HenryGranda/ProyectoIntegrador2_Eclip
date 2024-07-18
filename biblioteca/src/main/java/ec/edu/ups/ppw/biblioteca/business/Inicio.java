package ec.edu.ups.ppw.biblioteca.business;

import ec.edu.ups.ppw.biblioteca.dao.LibroDAO;
import ec.edu.ups.ppw.biblioteca.dao.UsuarioDAO;
import ec.edu.ups.ppw.biblioteca.model.Libro;
import ec.edu.ups.ppw.biblioteca.model.Usuario;
import jakarta.annotation.PostConstruct;
import jakarta.ejb.Singleton;
import jakarta.ejb.Startup;
import jakarta.inject.Inject;

@Singleton
@Startup
public class Inicio {
	
	@Inject
	private LibroDAO daoLibro;
	
	@Inject
	private UsuarioDAO daoUsuario;
	
	@PostConstruct
	public void init(){
		Libro libro = new Libro();
		libro.setTitulo("Ludopatía");
		libro.setAutor("Henry Granda");
		libro.setGenero("Ciencia Ficción");
		libro.setEdicion(2);
		libro.setEjemplares(30);
		libro.setContenido("asdfasdasdasdasdasdas");
		libro.setPortada("https://www.tematika.com/media/catalog/Ilhsa/Imagenes/701102.jpg");
		daoLibro.insert(libro);
	
		Libro libro2 = new Libro();
		libro2.setTitulo("Alya");
		libro2.setAutor("Erick Zhigue");
		libro2.setGenero("Ciencia Ficción");
		libro2.setEdicion(2);
		libro2.setEjemplares(30);
		libro2.setContenido("asdfasdasdasdasdasdas");
		libro2.setPortada("https://images.cdn2.buscalibre.com/fit-in/360x360/fa/14/fa148b6ed6ee485f82661b44d6fa1b80.jpg");
		daoLibro.insert(libro2);
		
		
		Usuario usuario = new Usuario();
		usuario.setUsername("adminprueba");
		usuario.setPassword("abc123");
		usuario.setEmail("admin@gmail.com");
		usuario.setRole("admin");
		daoUsuario.insert(usuario);
		
		Usuario usuario2 = new Usuario();
		usuario2.setUsername("userprueba");
		usuario2.setPassword("xyz987");
		usuario2.setEmail("user@gmail.com");
		usuario2.setRole("user");
		daoUsuario.insert(usuario2);
		
		
		
		
	}
}
