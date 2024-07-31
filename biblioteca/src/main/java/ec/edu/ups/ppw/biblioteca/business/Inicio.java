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

		// Ciencia Ficción
		Libro libro1 = new Libro();
		libro1.setTitulo("Neuromante");
		libro1.setAutor("William Gibson");
		libro1.setGenero("Ciencia Ficción");
		libro1.setEdicion(1);
		libro1.setContenido("Un viaje al ciberespacio en un futuro distópico.");
		libro1.setPortada("https://images.cdn3.buscalibre.com/fit-in/360x360/f1/2f/f12f5e6380d39492ee34805c6875cda7.jpg");
		libro1.setDisponibilidad(true);
		libro1.setReservado(false);
		daoLibro.insert(libro1);

		Libro libro2 = new Libro();
		libro2.setTitulo("Dune");
		libro2.setAutor("Frank Herbert");
		libro2.setGenero("Ciencia Ficción");
		libro2.setEdicion(1);
		libro2.setContenido("La épica saga de un desierto planetario y su gente.");
		libro2.setPortada("https://images-na.ssl-images-amazon.com/images/S/compressed.photo.goodreads.com/books/1555447414i/44767458.jpg");
		libro2.setDisponibilidad(true);
		libro2.setReservado(false);
		daoLibro.insert(libro2);

		Libro libro3 = new Libro();
		libro3.setTitulo("El Fin de la Infancia");
		libro3.setAutor("Arthur C. Clarke");
		libro3.setGenero("Ciencia Ficción");
		libro3.setEdicion(1);
		libro3.setContenido("Una misteriosa raza alienígena transforma la humanidad.");
		libro3.setPortada("https://www.planetadelibros.com.ec/usuaris/libros/fotos/216/m_libros/215441_portada_el-fin-de-la-infancia__202105251258.jpg");
		libro3.setDisponibilidad(true);
		libro3.setReservado(false);
		daoLibro.insert(libro3);

		Libro libro4 = new Libro();
		libro4.setTitulo("1984");
		libro4.setAutor("George Orwell");
		libro4.setGenero("Ciencia Ficción");
		libro4.setEdicion(1);
		libro4.setContenido("Una distopía sobre un régimen totalitario.");
		libro4.setPortada("https://images.cdn2.buscalibre.com/fit-in/360x360/85/64/8564963be6e21ee55d0bd7b532c3a9bb.jpg");
		libro4.setDisponibilidad(true);
		libro4.setReservado(false);
		daoLibro.insert(libro4);

		// Fantasía
		Libro libro5 = new Libro();
		libro5.setTitulo("El Hobbit");
		libro5.setAutor("J.R.R. Tolkien");
		libro5.setGenero("Fantasía");
		libro5.setEdicion(1);
		libro5.setContenido("La aventura de Bilbo Bolsón en la Tierra Media.");
		libro5.setPortada("https://images.cdn3.buscalibre.com/fit-in/360x360/82/a7/82a7badc78d25426ba3d419ef8685253.jpg");
		libro5.setDisponibilidad(true);
		libro5.setReservado(false);
		daoLibro.insert(libro5);

		Libro libro6 = new Libro();
		libro6.setTitulo("Juego de Tronos");
		libro6.setAutor("George R.R. Martin");
		libro6.setGenero("Fantasía");
		libro6.setEdicion(1);
		libro6.setContenido("Las luchas de poder en los Siete Reinos.");
		libro6.setPortada("https://images.cdn3.buscalibre.com/fit-in/520x520/1e/bd/1ebdf12d1e1f8f1de83c7fdfe3c2b1a5.jpg");
		libro6.setDisponibilidad(true);
		libro6.setReservado(false);
		daoLibro.insert(libro6);

		Libro libro7 = new Libro();
		libro7.setTitulo("El Nombre del Viento");
		libro7.setAutor("Patrick Rothfuss");
		libro7.setGenero("Fantasía");
		libro7.setEdicion(1);
		libro7.setContenido("La historia de Kvothe y su búsqueda de la verdad.");
		libro7.setPortada("https://images.cdn3.buscalibre.com/fit-in/360x360/a7/90/a790dff70defe5c61b66fd73716b6e30.jpg");
		libro7.setDisponibilidad(true);
		libro7.setReservado(false);
		daoLibro.insert(libro7);

		Libro libro8 = new Libro();
		libro8.setTitulo("Harry Potter y la Piedra Filosofal");
		libro8.setAutor("J.K. Rowling");
		libro8.setGenero("Fantasía");
		libro8.setEdicion(1);
		libro8.setContenido("La primera aventura de Harry Potter en Hogwarts.");
		libro8.setPortada("https://www.mrbooks.com/mrbooks/portadas/9789585234048.webp");
		libro8.setDisponibilidad(true);
		libro8.setReservado(false);
		daoLibro.insert(libro8);

		// Terror
		Libro libro9 = new Libro();
		libro9.setTitulo("It");
		libro9.setAutor("Stephen King");
		libro9.setGenero("Terror");
		libro9.setEdicion(1);
		libro9.setContenido("Un grupo de niños se enfrenta a un mal antiguo.");
		libro9.setPortada("https://i.ebayimg.com/images/g/rgkAAOSw969f~26A/s-l500.jpg");
		libro9.setDisponibilidad(true);
		libro9.setReservado(false);
		daoLibro.insert(libro9);

		Libro libro10 = new Libro();
		libro10.setTitulo("El Resplandor");
		libro10.setAutor("Stephen King");
		libro10.setGenero("Terror");
		libro10.setEdicion(1);
		libro10.setContenido("Un hombre lucha contra su locura en un hotel aislado.");
		libro10.setPortada("https://images.cdn3.buscalibre.com/fit-in/360x360/49/66/49661480fa1f78034b80bae7ed020841.jpg");
		libro10.setDisponibilidad(true);
		libro10.setReservado(false);
		daoLibro.insert(libro10);

		Libro libro11 = new Libro();
		libro11.setTitulo("Drácula");
		libro11.setAutor("Bram Stoker");
		libro11.setGenero("Terror");
		libro11.setEdicion(1);
		libro11.setContenido("La historia del famoso vampiro y su caza.");
		libro11.setPortada("https://es.web.img2.acsta.net/medias/nmedia/18/67/72/10/20134015.jpg");
		libro11.setDisponibilidad(true);
		libro11.setReservado(false);
		daoLibro.insert(libro11);

		Libro libro12 = new Libro();
		libro12.setTitulo("Frankenstein");
		libro12.setAutor("Mary Shelley");
		libro12.setGenero("Terror");
		libro12.setEdicion(1);
		libro12.setContenido("Un científico crea vida con consecuencias trágicas.");
		libro12.setPortada("https://images.cdn1.buscalibre.com/fit-in/360x360/15/54/1554d01d226679a6e8402fad007b31a6.jpg");
		libro12.setDisponibilidad(true);
		libro12.setReservado(false);
		daoLibro.insert(libro12);

		// Romance
		Libro libro13 = new Libro();
		libro13.setTitulo("Orgullo y Prejuicio");
		libro13.setAutor("Jane Austen");
		libro13.setGenero("Romance");
		libro13.setEdicion(1);
		libro13.setContenido("La historia de amor entre Elizabeth Bennet y Mr. Darcy.");
		libro13.setPortada("https://www.elejandria.com/covers/Orgullo_y_prejuicio-Jane_Austen-lg.png");
		libro13.setDisponibilidad(true);
		libro13.setReservado(false);
		daoLibro.insert(libro13);

		Libro libro14 = new Libro();
		libro14.setTitulo("Cumbres Borrascosas");
		libro14.setAutor("Emily Brontë");
		libro14.setGenero("Romance");
		libro14.setEdicion(1);
		libro14.setContenido("Un amor tormentoso en los páramos ingleses.");
		libro14.setPortada("https://cdn.prod.website-files.com/6034d7d1f3e0f52c50b2adee/625454187128ea32cdb140e8_6034d7d1f3e0f5072bb2b1ca_Cumbres-borrascosas-emily-bronte-editorial-alma.jpeg");
		libro14.setDisponibilidad(true);
		libro14.setReservado(false);
		daoLibro.insert(libro14);

		Libro libro15 = new Libro();
		libro15.setTitulo("Jane Eyre");
		libro15.setAutor("Charlotte Brontë");
		libro15.setGenero("Romance");
		libro15.setEdicion(1);
		libro15.setContenido("La vida y amores de una joven institutriz.");
		libro15.setPortada("https://cdn.prod.website-files.com/6034d7d1f3e0f52c50b2adee/625453f3986326e6a6ff7a9d_6034d7d1f3e0f54abdb2b276_Jane-eyre-charlotte-bronte-editorial-alma.jpeg");
		libro15.setDisponibilidad(true);
		libro15.setReservado(false);
		daoLibro.insert(libro15);

		Libro libro16 = new Libro();
		libro16.setTitulo("Lo que el viento se llevó");
		libro16.setAutor("Margaret Mitchell");
		libro16.setGenero("Romance");
		libro16.setEdicion(1);
		libro16.setContenido("Una historia de amor y supervivencia durante la Guerra Civil Americana.");
		libro16.setPortada("https://images.cdn3.buscalibre.com/fit-in/360x360/62/cb/62cb338f1b9f617b52bc5fe938a93223.jpg");
		libro16.setDisponibilidad(true);
		libro16.setReservado(false);
		daoLibro.insert(libro16);

	
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
