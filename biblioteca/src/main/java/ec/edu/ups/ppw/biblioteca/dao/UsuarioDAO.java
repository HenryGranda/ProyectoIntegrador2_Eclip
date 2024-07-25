package ec.edu.ups.ppw.biblioteca.dao;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import ec.edu.ups.ppw.biblioteca.enums.Rolnombres;
import ec.edu.ups.ppw.biblioteca.model.Libro;
import ec.edu.ups.ppw.biblioteca.model.Usuario;
import ec.edu.ups.ppw.biblioteca.util.PercistenceManager;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;

@Stateless
public class UsuarioDAO {
	
	@PersistenceContext
	private EntityManager em; 
	
	public void insert(Usuario usuario) {
		em.persist(usuario);
	}
	
	public void update(Usuario usuario) {
		em.merge(usuario);
	}
	
	public void delete(int id) throws Exception{
	    Usuario usuario = this.read(id);
	    if (usuario != null) {
	        em.remove(usuario);
	        System.out.println("Eliminado");
	    } else {
	        throw new Exception("No se puede eliminar el usuario. Usuario con id " + id + " no encontrado.");
	    }
	}
	
	public Usuario read(int id) throws Exception{
		Usuario usuario = em.find(Usuario.class, id);
	    if (usuario == null) {
	        throw new Exception("Usuario con id " + id + " no encontrado.");
	    }
	    return usuario;
	}
		
	public List<Usuario> getAll(){
		String jpql = "SELECT u FROM Usuario u";
		Query query = em.createQuery(jpql, Usuario.class);
		return query.getResultList();
	}
	
	public Usuario validarusu(String username, String password) {
	    TypedQuery<Usuario> query = em.createQuery("SELECT u FROM Usuario u WHERE u.username = :username AND u.password = :password", Usuario.class);
	    query.setParameter("username", username);
	    query.setParameter("password", password);
	    try {
	        return query.getSingleResult();
	    } catch (NoResultException e) {
	        return null;
	    }
	}
    

}
