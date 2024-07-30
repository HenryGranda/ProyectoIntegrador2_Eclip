package ec.edu.ups.ppw.biblioteca.dao;

import java.util.List;

import ec.edu.ups.ppw.biblioteca.model.Libro;
import ec.edu.ups.ppw.biblioteca.model.Prestamo;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;

@Stateless
public class PrestamoDAO {

	@PersistenceContext
	private EntityManager em; 
	
	public void insert(Prestamo prestamo) {
		em.persist(prestamo);
	}
	
	public void update(Prestamo prestamo) {
		em.merge(prestamo);
	}
	
	public void update(Libro libros) {
        em.merge(libros);
    }
	
	public void delete(int id) throws Exception{
		Prestamo prestamo = this.read(id);
	    if (prestamo != null) {
	        em.remove(prestamo);
	        System.out.println("Eliminado");
	    } else {
	        throw new Exception("No se puede eliminar el prestamo. Prestamo con id " + id + " no encontrado.");
	    }
	}
	
	public Prestamo read(int id) throws Exception{
		Prestamo prestamo = em.find(Prestamo.class, id);
	    if (prestamo == null) {
	        throw new Exception("Prestamo con id " + id + " no encontrado.");
	    }
	    return prestamo;
	}
	
	public List<Prestamo> getAll(){
		String jpql = "SELECT p FROM Prestamo p";
		Query query = em.createQuery(jpql, Prestamo.class);
		return query.getResultList();
	}

	
	public List<Prestamo> getPrestamosActivos() {
        String jpql = "SELECT p FROM Prestamo p WHERE p.devuelto = false";
        TypedQuery<Prestamo> query = em.createQuery(jpql, Prestamo.class);
        return query.getResultList();
    }
	
	// Método en el DAO para obtener préstamos no entregados de un usuario
	public List<Prestamo> getPrestamosNoEntregadosPorUsuario(String username) {
	    String jpql = "SELECT p FROM Prestamo p WHERE p.entregado = false AND p.usuario.username = :username";
	    TypedQuery<Prestamo> query = em.createQuery(jpql, Prestamo.class);
	    query.setParameter("username", username);
	    return query.getResultList();
	}

}

