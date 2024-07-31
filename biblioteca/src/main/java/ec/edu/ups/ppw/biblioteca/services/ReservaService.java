package ec.edu.ups.ppw.biblioteca.services;

import java.security.Principal;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import ec.edu.ups.ppw.biblioteca.business.GestionReserva;
import ec.edu.ups.ppw.biblioteca.model.EmailService;
import ec.edu.ups.ppw.biblioteca.model.Reservar;
import jakarta.inject.Inject;
import jakarta.mail.MessagingException;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.SecurityContext;

@Path("/reservas")
@JsonIgnoreProperties(ignoreUnknown = true)
public class ReservaService {

	@Inject
    private GestionReserva gReservas;

    @Inject
    private EmailService emailService;
    
    @POST
    @Produces("application/json")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(Reservar reserva) {
        try {
            gReservas.createReserva(reserva);
            
            // Enviar correo de confirmación
            String email = reserva.getUsuario().getEmail();
            String subject = "Reserva de Libro Confirmada";
            String body = "Estimado " + reserva.getUsuario().getUsername() + ",\n\n"
                    + "El libro \"" + reserva.getLibro().getTitulo() + "\" ha sido reservado exitosamente.\n"
                    + "Fecha de Reserva: " + reserva.getFechaReserva() + "\n\n"
                    + "Gracias,\nTu Biblioteca";
            
            try {
                emailService.sendEmail(email, subject, body);
            } catch (MessagingException e) {
                e.printStackTrace();
                return Response.status(503).entity(new Respuesta(Respuesta.ERROR, "Error al enviar correo")).build();
            }

            return Response.ok(reserva).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(503).entity(new Respuesta(Respuesta.ERROR, "Error en BD")).build();
        }
    }
    
    @GET
    @Path("/{id}")
    @Produces("application/json")
    public Reservar getReservaById(@PathParam("id") int id) {
        try {
            return gReservas.getReserva(id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    @GET
    @Produces("application/json")
    public List<Reservar> list() {
        return gReservas.getReservas();
    }

    @GET
    @Path("/activas")
    @Produces("application/json")
    public List<Reservar> getReservasActivas() {
        return gReservas.getReservasActivas();
    }
    
    
    @GET
    @Path("/activas")
    @Produces("application/json")
    public List<Reservar> getReservasActivas(@Context SecurityContext securityContext) {
        Principal principal = securityContext.getUserPrincipal();
        
        if (principal == null) {
            throw new WebApplicationException("User not authenticated", Response.Status.UNAUTHORIZED);
        }
        
        String username = principal.getName();
        return gReservas.getReservasActivasDelUsuario(username);
    }


    
    @POST
    @Path("/cancelar/{id}")
    @Produces("application/json")
    public Response cancelarReserva(@PathParam("id") int id) {
        try {
            gReservas.cancelarReserva(id);

            // Obtener la reserva para enviar el correo
            Reservar reserva = gReservas.getReserva(id);
            if (reserva != null) {
                // Enviar correo de confirmación de cancelación
                String email = reserva.getUsuario().getEmail();
                String subject = "Cancelación de Reserva Confirmada";
                String body = "Estimado " + reserva.getUsuario().getUsername() + ",\n\n"
                        + "La reserva del libro \"" + reserva.getLibro().getTitulo() + "\" ha sido cancelada exitosamente.\n\n"
                        + "Gracias,\nTu Biblioteca";

                try {
                    emailService.sendEmail(email, subject, body);
                } catch (MessagingException e) {
                    e.printStackTrace();
                    return Response.status(503).entity(new Respuesta(Respuesta.ERROR, "Error al enviar correo")).build();
                }
            }

            return Response.ok().build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(503).entity(new Respuesta(Respuesta.ERROR, "Error al procesar cancelación")).build();
        }
    }
}
