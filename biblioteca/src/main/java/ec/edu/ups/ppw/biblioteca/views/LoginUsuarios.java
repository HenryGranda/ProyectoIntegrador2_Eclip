package ec.edu.ups.ppw.biblioteca.views;

import java.util.HashMap;
import java.util.Map;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import ec.edu.ups.ppw.biblioteca.business.GestionUsuarios;
import ec.edu.ups.ppw.biblioteca.dao.UsuarioDAO;
import ec.edu.ups.ppw.biblioteca.model.Usuario;
import ec.edu.ups.ppw.biblioteca.util.JWTutil;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.ws.rs.Path;


@Named("loginusu")
@RequestScoped
@Path("/auth")
public class LoginUsuarios {
	
	@Inject
    private GestionUsuarios gestionUsuarios;
	
    @Inject
    private UsuarioDAO usuarioDAO;
    
    @Inject
    private JWTutil jwtutil; // Inyectar JwtProvider

	private String username;
    private String password;

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

   /* @POST
    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(Usuario usuario) throws JSONException {
        Usuario user = gestionUsuarios.iniciarSesion(usuario.getUsername(),usuario.getPassword());

        if (user != null && user.getPassword().equals(usuario.getPassword())) {
            String token = jwtutil.createToken(user);
            JSONObject response = new JSONObject();
            response.put("token", token);
            return Response.ok(response.toString()).build();
        } else {
            return Response.status(Response.Status.UNAUTHORIZED).entity("Login incorrecto").build();
        }
    }*/


    
    /*public String loginpage() {
        Usuario usuario = gestionUsuarios.iniciarSesion(username, password);
        if (usuario != null) {
            // Login exitoso, puedes redirigir al usuario a la página principal
            return "/RegistroUsu.xhtml?faces-redirect=true";
        } else {
            // Login incorrecto, muestra un mensaje de error
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Usuario o contraseña incorrectos"));
            return null;
        }
    }*/
    
    public void login() {
        Usuario usuario = usuarioDAO.validarusu(username, password);
        if (usuario != null) {
            String jwt = jwtutil.createToken(usuario.getUsername(), usuario.getRole(), usuario.getEmail());// Generar el JWT

            // Redirigir a Angular con el token JWT
            try {
                HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
                response.sendRedirect("http://localhost:4200?token=" + jwt);
                FacesContext.getCurrentInstance().responseComplete(); // Marcar la respuesta como completada
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            // Redirigir a la página de error
        	FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Usuario o contraseña incorrectos"));
            try {
                HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
                //response.sendRedirect("");
                FacesContext.getCurrentInstance().responseComplete(); // Marcar la respuesta como completada
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
	

}
