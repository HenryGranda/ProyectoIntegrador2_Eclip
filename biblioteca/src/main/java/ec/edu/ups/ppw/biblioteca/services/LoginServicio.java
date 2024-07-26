package ec.edu.ups.ppw.biblioteca.services;

import java.util.List;

import ec.edu.ups.ppw.biblioteca.business.GestionUsuarios;
import ec.edu.ups.ppw.biblioteca.model.Usuario;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.inject.Inject;


@Path("/auth")
public class LoginServicio  {


	@Inject
	private GestionUsuarios gUsuarios;
	
	@POST
    @Produces("application/json")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(Usuario usuario) {
        try {
            gUsuarios.createUsuario(usuario);
            return Response.ok(usuario).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(503).entity(new Respuesta(Respuesta.ERROR, "Error en BD")).build();
        }
    }

	@GET
	@Produces("application/json")
	public List<Usuario> list(){
		List<Usuario> usuarios = gUsuarios.getUsuarios();
		return usuarios;
	}

}
