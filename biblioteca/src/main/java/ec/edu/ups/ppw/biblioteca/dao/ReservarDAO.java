package ec.edu.ups.ppw.biblioteca.dao;

import java.util.List;

import ec.edu.ups.ppw.biblioteca.model.Libro;
import ec.edu.ups.ppw.biblioteca.model.Reservar;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;

@Stateless
public class ReservarDAO {
	
	@PersistenceContext
    private EntityManager em;
	
	public void insert(Reservar reserva) {
        em.persist(reserva);
    }
	
	public void update(Reservar reserva) {
        em.merge(reserva);
    }
	
	public void update(Libro libros) {
        em.merge(libros);
    }
	
	public void delete(int id) throws Exception {
        Reservar reserva = this.read(id);
        if (reserva != null) {
            em.remove(reserva);
            System.out.println("Eliminado");
        } else {
            throw new Exception("No se puede eliminar, reserva con id " + id + " no encontrada.");
        }
    }
	
	public Reservar read(int id) throws Exception {
        Reservar reserva = em.find(Reservar.class, id);
        if (reserva == null) {
            throw new Exception("Reserva con id " + id + " no encontrada.");
        }
        return reserva;
    }
	
	public List<Reservar> getAll() {
        String jpql = "SELECT r FROM Reservar r";
        Query query = em.createQuery(jpql, Reservar.class);
        return query.getResultList();
    }
	
	public void marcarComoDevuelto(int id) throws Exception {
        Reservar reservado = this.read(id);
        if (reservado == null) {
            throw new Exception("Reserva no existe");
        }
        reservado.setDevuelto(true);
        em.merge(reservado);
    }
	
	public List<Reservar> getReservasActivasPorUsuario(String username) {
        String jpql = "SELECT r FROM Reservar r WHERE r.devuelto = false AND r.usuario.username = :username";
        TypedQuery<Reservar> query = em.createQuery(jpql, Reservar.class);
        query.setParameter("username", username);
        return query.getResultList();
    }
	
	public List<Reservar> getReservasActivas() {
    	String jpql = "SELECT r FROM Reservar r WHERE r.devuelto = false";
        TypedQuery<Reservar> query = em.createQuery(jpql, Reservar.class);
        return query.getResultList();
    }
}
