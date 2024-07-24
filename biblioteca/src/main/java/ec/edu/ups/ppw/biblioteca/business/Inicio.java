package ec.edu.ups.ppw.biblioteca.business;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import ec.edu.ups.ppw.biblioteca.dao.LibroDAO;
import ec.edu.ups.ppw.biblioteca.dao.PrestamoDAO;
import ec.edu.ups.ppw.biblioteca.dao.RolDAO;
import ec.edu.ups.ppw.biblioteca.dao.UsuarioDAO;
import ec.edu.ups.ppw.biblioteca.enums.Rolnombres;
import ec.edu.ups.ppw.biblioteca.model.DateUtil;
import ec.edu.ups.ppw.biblioteca.model.Libro;
import ec.edu.ups.ppw.biblioteca.model.Prestamo;
import ec.edu.ups.ppw.biblioteca.model.Rol;
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
	
    @Inject
    private RolDAO daoRol;
	
	@PostConstruct
	public void init(){
		
        Rol rolAdmin = new Rol(Rolnombres.ROLE_ADMIN);
        daoRol.insert(rolAdmin);
        
        Rol rolUser = new Rol(Rolnombres.ROLE_USER);
        daoRol.insert(rolUser);

        // Crear un conjunto de roles
        Set<Rol> rolesAdmin = new HashSet<>();
        rolesAdmin.add(rolAdmin);

        Set<Rol> rolesUser = new HashSet<>();
        rolesUser.add(rolUser);

        // Crear y persistir usuarios
        Usuario usuarioAdmin = new Usuario();
        usuarioAdmin.setUsername("admin123");
        usuarioAdmin.setPassword("admin123");
        usuarioAdmin.setEmail("admin@gmail.com");
        usuarioAdmin.setRoles(rolesAdmin);
        daoUsuario.insert(usuarioAdmin);

        Usuario usuarioUser = new Usuario();
        usuarioUser.setUsername("user123");
        usuarioUser.setPassword("user123");
        usuarioUser.setEmail("user@gmail.com");
        usuarioUser.setRoles(rolesUser);
        daoUsuario.insert(usuarioUser);
		
		Libro libro = new Libro();
		libro.setTitulo("Ludopatía");
		libro.setAutor("Henry Granda");
		libro.setGenero("Ciencia Ficción");
		libro.setEdicion(2);
		libro.setContenido("asdfasdasdasdasdasdas");
		libro.setPortada("https://www.tematika.com/media/catalog/Ilhsa/Imagenes/701102.jpg");
		daoLibro.insert(libro);
	
		Libro libro2 = new Libro();
		libro2.setTitulo("Alya");
		libro2.setAutor("Erick Zhigue");
		libro2.setGenero("Ciencia Ficción");
		libro2.setEdicion(2);
		libro2.setContenido("asdfasdasdasdasdasdas");
		libro2.setPortada("https://images.cdn2.buscalibre.com/fit-in/360x360/fa/14/fa148b6ed6ee485f82661b44d6fa1b80.jpg");
		daoLibro.insert(libro2);
		
		

		
		Prestamo prestamo = new Prestamo();
		prestamo.setUsuario(usuarioUser);
		prestamo.setLibro(libro);
		Date fechaPrestamo = DateUtil.createDate(2023, 7, 18);
		Date fechaDevolucion = DateUtil.createDate(2023, 7, 25);
		prestamo.setFechaPrestamo(fechaPrestamo);
		prestamo.setFechaDevolucion(fechaDevolucion);
		daoPrestamo.insert(prestamo);
		
		Prestamo prestamo2 = new Prestamo();
		prestamo2.setUsuario(usuarioUser);
		prestamo2.setLibro(libro2);
		prestamo2.setFechaPrestamo(fechaPrestamo);
		prestamo2.setFechaDevolucion(fechaDevolucion);
		daoPrestamo.insert(prestamo2);
		
		System.out.println(prestamo2.toString());

	}
}
