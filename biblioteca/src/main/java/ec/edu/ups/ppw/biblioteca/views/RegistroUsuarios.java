package ec.edu.ups.ppw.biblioteca.views;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import ec.edu.ups.ppw.biblioteca.business.GestionUsuarios;
import ec.edu.ups.ppw.biblioteca.dao.UsuarioDAO;
import ec.edu.ups.ppw.biblioteca.enums.Rolnombres;
import ec.edu.ups.ppw.biblioteca.model.Usuario;
import jakarta.annotation.PostConstruct;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.context.ExternalContext;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.transaction.Transactional;

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
            return "LoginUsu?faces-redirect=true";
        } catch (Exception e) {
            e.printStackTrace();
            this.errorMessage = "Error al registrar. Inténtalo de nuevo.";
            FacesContext facesContext = FacesContext.getCurrentInstance();
            ExternalContext externalContext = facesContext.getExternalContext();
            try {
                externalContext.redirect(externalContext.getRequestContextPath() + "/error2.xhtml");
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
            return null; // Indica que no se realizó redirección automática
        }
    }

    private void clearFields() {
        this.usuario = new Usuario();
    }
}
