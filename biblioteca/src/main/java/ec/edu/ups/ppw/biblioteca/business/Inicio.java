package ec.edu.ups.ppw.biblioteca.business;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import ec.edu.ups.ppw.biblioteca.dao.LibroDAO;
import ec.edu.ups.ppw.biblioteca.dao.PrestamoDAO;
import ec.edu.ups.ppw.biblioteca.dao.UsuarioDAO;
import ec.edu.ups.ppw.biblioteca.enums.Rolnombres;
import ec.edu.ups.ppw.biblioteca.model.DateUtil;
import ec.edu.ups.ppw.biblioteca.model.Libro;
import ec.edu.ups.ppw.biblioteca.model.Prestamo;
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
	
	@Inject
	private PrestamoDAO daoPrestamo;
	
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
		usuario.setUsername("adminprueba");
		usuario.setPassword("abc123");
		usuario.setEmail("admin@gmail.com");
		usuario.setRole("admin");
		daoUsuario.insert(usuario);
		
		Usuario usuario2 = new Usuario();
		usuario2.setUsername("erick");
		usuario2.setPassword("erick123");
		usuario2.setEmail("user@gmail.com");
		usuario2.setRole("user");
		daoUsuario.insert(usuario2);

	}
}
