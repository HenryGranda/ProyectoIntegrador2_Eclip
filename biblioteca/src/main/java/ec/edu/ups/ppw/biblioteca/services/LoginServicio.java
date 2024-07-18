package ec.edu.ups.ppw.biblioteca.services;


import java.util.Date;
import java.util.List;

import ec.edu.ups.ppw.biblioteca.business.GestionUsuarios;
import ec.edu.ups.ppw.biblioteca.dao.UsuarioDAO;
import ec.edu.ups.ppw.biblioteca.model.Usuario;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
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
import jakarta.json.Json;
import jakarta.json.JsonObject;





@Path("/auth")
public class LoginServicio  {
	@Inject
	private GestionUsuarios gUsuarios;
	
    /*@POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response validar(Usuario usuario) {
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        Usuario user = usuarioDAO.validar3(usuario.getUsername(), usuario.getPassword());
        if (user != null) {
            String KEY = "mi_clave";
            long tiempo = System.currentTimeMillis();
            String jwt = Jwts.builder()
                    .signWith(SignatureAlgorithm.HS256, KEY)
                    //.setSubject(user.getUsername())
                    .setIssuedAt(new Date(tiempo))
                    .setExpiration(new Date(tiempo + 900000))
                    .claim("email", user.getEmail())
                    .claim("role", user.getRole()) // Añadir el rol al token
                    .compact();
            JsonObject json = Json.createObjectBuilder()
                    .add("JWT", jwt).build();
            return Response.status(Response.Status.CREATED).entity(json).build();
        }
        return Response.status(Response.Status.UNAUTHORIZED).build();
    }*/
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
