package ec.edu.ups.ppw.biblioteca.dao;
import java.util.List;

import ec.edu.ups.ppw.biblioteca.model.Libro;
import ec.edu.ups.ppw.biblioteca.model.Usuario;
import ec.edu.ups.ppw.biblioteca.util.PercistenceManager;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
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
	
    /*public Usuario validar(String username, String password) {
        EntityManager em = PercistenceManager.getEntityManager();
        TypedQuery<Usuario> query = em.createQuery("SELECT u FROM Usuario u WHERE u.username = :username AND u.password = :password", Usuario.class);
        query.setParameter("username", username);
        query.setParameter("password", password);
        try {
            return query.getSingleResult();
        } catch (Exception e) {
            return null;
        } finally {
            em.close();
        }
    }*/
    
    public Usuario validar3(String username, String password) {
        // Simulación de usuario válido
        if (username.equals("usuario_prueba") && password.equals("contraseña_prueba")) {
            return new Usuario("usuario_prueba","usuario_password", "usuario_prueba@gmail.com", "ROLE_USER");
        }
        return null; // Simulación de usuario no válido
    }
    
    public static boolean validar(String username, String password) {
		return (username.equals("admin") && password.equals("admin"));
	}

}
