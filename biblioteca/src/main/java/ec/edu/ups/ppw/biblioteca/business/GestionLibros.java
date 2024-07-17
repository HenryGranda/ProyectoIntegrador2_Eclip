package ec.edu.ups.ppw.biblioteca.business;

import java.util.List;

import ec.edu.ups.ppw.biblioteca.dao.LibroDAO;
import ec.edu.ups.ppw.biblioteca.model.Libro;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

@Stateless
public class GestionLibros {

	@Inject
	private LibroDAO daoLibro;
	
	public Libro getLibro(int id) throws Exception {
		Libro libro = daoLibro.read(id);
		if(libro == null)
			throw new Exception("Cliente no existe");
		return libro;
	}
	
	public List<Libro> getLibros(){
		return daoLibro.getAll();
	}
	
	public void createLibro(Libro libro) throws Exception {
		if(libro.getLibroId() < 0) {
			throw new Exception("Id Incorrecto");
		}
		daoLibro.insert(libro);
	}
	
	public void updateLibro(Libro libro) throws Exception{
		if(libro.getLibroId() < 0) {
			throw new Exception("Cedula Incorrecta");
		}
		daoLibro.update(libro);
	}
	
	public void deleteLibro(int id) throws Exception{
		Libro cliente = daoLibro.read(id);
		if(cliente == null) {				
			throw new Exception("Cliente no existe");
		}
		else {
			daoLibro.delete(id);
		}
		
	}
}
