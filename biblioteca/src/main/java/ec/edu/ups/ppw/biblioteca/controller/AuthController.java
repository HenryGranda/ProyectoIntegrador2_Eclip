package ec.edu.ups.ppw.biblioteca.controller;

import ec.edu.ups.ppw.biblioteca.business.GestionUsuarios;
import ec.edu.ups.ppw.biblioteca.model.Usuario;
import ec.edu.ups.ppw.biblioteca.util.JwtProvider;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/auth")
public class AuthController {
	
    @Inject
    private GestionUsuarios gestionUsuarios;

    /*@POST
    @Path("/login")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response login(Usuario usuario) {
        Usuario foundUser = gestionUsuarios.findByUsername(usuario.getUsername());
        if (foundUser != null && foundUser.getPassword().equals(usuario.getPassword())) {
            String token = JWTutil.generateToken(foundUser);
            return Response.ok().entity("{\"token\": \"" + token + "\"}").build();
        } else {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
    }*/

}
