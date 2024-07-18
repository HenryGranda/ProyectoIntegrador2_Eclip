package ec.edu.ups.ppw.biblioteca.services;


import java.util.Date;

import ec.edu.ups.ppw.biblioteca.dao.UsuarioDAO;
import ec.edu.ups.ppw.biblioteca.model.Usuario;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.json.Json;
import jakarta.json.JsonObject;





@Path("/auth")
public class LoginServicio  {
	
    @POST
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
    }

}
