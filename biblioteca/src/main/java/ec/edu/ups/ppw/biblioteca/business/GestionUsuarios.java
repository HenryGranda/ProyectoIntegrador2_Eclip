package ec.edu.ups.ppw.biblioteca.business;

import java.util.List;

import ec.edu.ups.ppw.biblioteca.dao.UsuarioDAO;
import ec.edu.ups.ppw.biblioteca.model.Usuario;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Stateless
public class GestionUsuarios {

    @PersistenceContext(unitName = "bibliotecaPersistenceUnit")
    private EntityManager em;

    @Inject
    private UsuarioDAO daoUsuario;

    public Usuario getUsuario(int id) throws Exception {
        Usuario usuario = daoUsuario.read(id);
        if (usuario == null)
            throw new Exception("Usuario no existe");
        return usuario;
    }

    public List<Usuario> getUsuarios() {
        return daoUsuario.getAll();
    }

    public void createUsuario(Usuario usuario) throws Exception {
        if (usuario.getUsuarioId() < 0) {
            throw new Exception("Id Incorrecto");
        }
        // Verificar si el usuario ya existe
        Usuario existingUsuario = daoUsuario.findByUsername(usuario.getUsername());
        if (existingUsuario != null) {
            throw new Exception("El nombre de usuario ya está en uso");
        }
        daoUsuario.insert(usuario);
    }

    public void updateUsuario(Usuario usuario) throws Exception {
        if (usuario.getUsuarioId() < 0) {
            throw new Exception("Id Incorrecta");
        }
        daoUsuario.update(usuario);
    }

    public void deleteUsuario(int id) throws Exception {
        Usuario usuario = daoUsuario.read(id);
        if (usuario == null) {
            throw new Exception("Id no existe");
        } else {
            daoUsuario.delete(id);
        }
    }

    public Usuario findByUsername(String username) {
        return daoUsuario.findByUsername(username);
    }

    public Usuario iniciarSesion(String username, String password) {
        return daoUsuario.validarusu(username, password);
    }
}

