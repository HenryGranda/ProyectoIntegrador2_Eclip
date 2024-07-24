package ec.edu.ups.ppw.biblioteca.views;

import java.util.HashMap;
import java.util.Map;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import ec.edu.ups.ppw.biblioteca.business.GestionUsuarios;
import ec.edu.ups.ppw.biblioteca.dao.UsuarioDAO;
import ec.edu.ups.ppw.biblioteca.enums.Rolnombres;
import ec.edu.ups.ppw.biblioteca.model.Usuario;
import ec.edu.ups.ppw.biblioteca.util.JwtProvider;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.ws.rs.Path;


@Named("loginusu")
@RequestScoped
public class LoginUsuarios {
	private String username;
    private String password;

    @Inject
    private UsuarioDAO usuarioDAO;
    
    @Inject
    private JwtProvider jwtProvider; // Inyectar JwtProvider

    public void login() {
        Usuario usuario = usuarioDAO.validateUser(username, password);
        if (usuario != null) {
            String jwt = jwtProvider.createToken(username, usuario.getRoles(), usuario.getEmail()); // Generar el JWT

            // Determinar el rol del usuario
            boolean isAdmin = usuario.getRoles().stream()
                .anyMatch(rol -> rol.getRolNombre().equals(Rolnombres.ROLE_ADMIN));

            // Redirigir a Angular con el token JWT y el rol
            try {
                HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
                String redirectUrl = "http://localhost:4200/";
                if (isAdmin) {
                    redirectUrl += "admin-dashboard?token=" + jwt;
                } else {
                    redirectUrl += "user-dashboard?token=" + jwt;
                }
                response.sendRedirect(redirectUrl);
                FacesContext.getCurrentInstance().responseComplete(); // Marcar la respuesta como completada
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            // Redirigir a la página de error
            try {
                HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
                response.sendRedirect("error.xhtml");
                FacesContext.getCurrentInstance().responseComplete(); // Marcar la respuesta como completada
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    // Getters y setters para username y password
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
