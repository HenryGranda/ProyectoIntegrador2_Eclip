package ec.edu.ups.ppw.biblioteca.views;

import java.util.List;
import ec.edu.ups.ppw.biblioteca.business.GestionUsuarios;
import ec.edu.ups.ppw.biblioteca.model.Usuario;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@Named("registroUsuario")
@RequestScoped
public class RegistroUsuarios {

	@Inject
    private GestionUsuarios gUsuarios;
	private String errorMessage;
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
    
    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String registrar() {
        try {
            usuario.setRole("user");
            gUsuarios.createUsuario(usuario);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro exitoso", null));
            clearFields();
            return "LoginUsu?faces-redirect=true";
        } catch (Exception e) {
            e.printStackTrace();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al registrar: " + e.getMessage(), null));
            return null;
        }
    }

    private void clearFields() {
        this.usuario = new Usuario();
    }
}
