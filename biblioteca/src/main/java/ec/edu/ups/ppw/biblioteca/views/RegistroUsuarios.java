package ec.edu.ups.ppw.biblioteca.views;

import java.util.List;

import ec.edu.ups.ppw.biblioteca.business.GestionUsuarios;
import ec.edu.ups.ppw.biblioteca.model.Usuario;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@Named("registroUsuario")
@RequestScoped
public class RegistroUsuarios {

	@Inject
    private GestionUsuarios gUsuarios;

    private Usuario usuario = new Usuario();
    private List<Usuario> listadoUsuarios;
    private String message;

    @PostConstruct
    public void init() {
        listadoUsuarios = gUsuarios.getUsuarios();
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<Usuario> getListadoUsuarios() {
        return listadoUsuarios;
    }

    public void setListadoUsuarios(List<Usuario> listadoUsuarios) {
        this.listadoUsuarios = listadoUsuarios;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String registrar() {
        try {
            usuario.setRole("user"); // Asignar el rol automáticamente
            gUsuarios.createUsuario(usuario);
            this.message = "Registro exitoso";
            clearFields();
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            this.message = "Error al registrar";
            return null;
        }
    }

    private void clearFields() {
        this.usuario = new Usuario();
    }
}
