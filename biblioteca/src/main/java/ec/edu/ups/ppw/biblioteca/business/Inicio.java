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
		libro.setContenido("asdfasdasdasdasdasdas");
		libro.setPortada("https://www.tematika.com/media/catalog/Ilhsa/Imagenes/701102.jpg");
		libro.setDisponibilidad(true);
		daoLibro.insert(libro);
	
		Libro libro2 = new Libro();
		libro2.setTitulo("Alya");
		libro2.setAutor("Erick Zhigue");
		libro2.setGenero("Ciencia Ficción");
		libro2.setEdicion(2);
		libro2.setContenido("asdfasdasdasdasdasdas");
		libro2.setPortada("https://images.cdn2.buscalibre.com/fit-in/360x360/fa/14/fa148b6ed6ee485f82661b44d6fa1b80.jpg");
		libro2.setDisponibilidad(true);
		daoLibro.insert(libro2);
		
		Usuario usuario = new Usuario();
		usuario.setUsername("Henry");
		usuario.setPassword("admin123");
		usuario.setRole("admin");
		usuario.setEmail("admin@gmail.com");
		daoUsuario.insert(usuario);
		
		Usuario usuario2 = new Usuario();
		usuario2.setUsername("Erick");
		usuario2.setPassword("12345678963.");
		usuario2.setEmail("angeloulloa69@gmail.com");
		usuario2.setRole("user");
		daoUsuario.insert(usuario2);
		
		Usuario usuario3 = new Usuario();
		usuario3.setUsername("Angelo");
		usuario3.setPassword("123456789.");
		usuario3.setEmail("huambi123@gmail.com");
		usuario3.setRole("user");
		daoUsuario.insert(usuario3);
		
	}
}
