package ec.edu.ups.ppw.biblioteca.services;

import java.util.List;

import ec.edu.ups.ppw.biblioteca.business.GestionUsuarios;
import ec.edu.ups.ppw.biblioteca.model.Usuario;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
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

    @PUT
    @Produces("application/json")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(Usuario usuario) {
        try {
            gUsuarios.updateUsuario(usuario);
            return Response.ok(usuario).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(503).entity(new Respuesta(Respuesta.ERROR, "Error en BD")).build();
        }
    }

    @DELETE
    @Produces("application/json")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response delete(@QueryParam("id") int id) {
        try {
            gUsuarios.deleteUsuario(id);
            return Response.ok(id).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(503).entity(new Respuesta(Respuesta.ERROR, "Error en BD")).build();
        }
    }

    @GET
    @Path("/{id}")
    @Produces("application/json")
    public Usuario get(@PathParam("id") int id) {
        Usuario usuario;
        try {
            usuario = gUsuarios.getUsuario(id);
            return usuario;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @GET
    @Produces("application/json")
    public List<Usuario> list() {
        return gUsuarios.getUsuarios();
    }

}
