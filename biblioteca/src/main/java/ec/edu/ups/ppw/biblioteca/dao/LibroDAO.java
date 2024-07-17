package ec.edu.ups.ppw.biblioteca.dao;


import java.util.List;

import ec.edu.ups.ppw.biblioteca.model.Libro;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

@Stateless
public class LibroDAO {

	@PersistenceContext
	private EntityManager em;
	
	public void insert(Libro libro) {
		em.persist(libro);
	}
	
	public void update(Libro libro) {
		em.merge(libro);
	}
	
	public void delete(int id) throws Exception{
	    Libro libro = this.read(id);
	    if (libro != null) {
	        em.remove(libro);
	        System.out.println("Eliminado");
	    } else {
	        throw new Exception("No se puede eliminar el libro. Libro con id " + id + " no encontrado.");
	    }
	}
	
	public Libro read(int id) throws Exception{
		Libro libro = em.find(Libro.class, id);
	    if (libro == null) {
	        throw new Exception("Libro con id " + id + " no encontrado.");
	    }
	    return libro;
	}
	
	public List<Libro> getAll(){
		String jpql = "SELECT l FROM Libro l";
		Query query = em.createQuery(jpql, Libro.class);
		return query.getResultList();
	}
}
