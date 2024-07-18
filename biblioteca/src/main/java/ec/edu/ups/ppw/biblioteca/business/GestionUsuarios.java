package ec.edu.ups.ppw.biblioteca.business;

import java.util.List;

import ec.edu.ups.ppw.biblioteca.dao.UsuarioDAO;
import ec.edu.ups.ppw.biblioteca.model.Usuario;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

@Stateless
public class GestionUsuarios {
	
	@Inject
	private UsuarioDAO daoUsuario;
	
	public Usuario getUsuario(int id) throws Exception {
		Usuario usuario = daoUsuario.read(id);
		if(usuario == null)
			throw new Exception("Usuario no existe");
		return usuario;
	}
	
	public List<Usuario> getUsuarios(){
		return daoUsuario.getAll();
	}
	
	public void createUsuario(Usuario usuario) throws Exception {
		if(usuario.getUsuarioId() < 0) {
			throw new Exception("Id Incorrecto");
		}
		daoUsuario.insert(usuario);
	}
	
	public void updateUsuario(Usuario usuario) throws Exception{
		if(usuario.getUsuarioId() < 0) {
			throw new Exception("Id Incorrecta");
		}
		daoUsuario.update(usuario);
	}
	
	public void deleteUsuario(int id) throws Exception{
		Usuario usuario = daoUsuario.read(id);
		if(usuario == null) {				
			throw new Exception("Id no existe");
		}
		else {
			daoUsuario.delete(id);
		}
		
	}

}
