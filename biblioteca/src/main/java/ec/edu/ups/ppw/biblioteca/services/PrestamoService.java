package ec.edu.ups.ppw.biblioteca.services;

import java.util.List;
import ec.edu.ups.ppw.biblioteca.business.GestionPrestamos;
import ec.edu.ups.ppw.biblioteca.model.EmailService;
import ec.edu.ups.ppw.biblioteca.model.Prestamo;
import jakarta.inject.Inject;
import jakarta.mail.MessagingException;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/prestamos")
public class PrestamoService {

    @Inject
    private GestionPrestamos gPrestamos;

    @Inject
    private EmailService emailService;

    @POST
    @Produces("application/json")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(Prestamo prestamo) {
        try {
            gPrestamos.createPrestamo(prestamo);
            
            // Enviar correo de confirmación
            String email = prestamo.getUsuario().getEmail();
            String subject = "Préstamo de Libro Confirmado";
            String body = "Estimado " + prestamo.getUsuario().getUsername() + ",\n\n"
                    + "El libro \"" + prestamo.getLibro().getTitulo() + "\" ha sido prestado exitosamente.\n"
                    + "Fecha de Préstamo: " + prestamo.getFechaPrestamo() + "\n"
                    + "Fecha de Devolución: " + prestamo.getFechaDevolucion() + "\n\n"
                    + "Gracias,\nTu Biblioteca";
            
            try {
                emailService.sendEmail(email, subject, body);
            } catch (MessagingException e) {
                e.printStackTrace();
                return Response.status(503).entity(new Respuesta(Respuesta.ERROR, "Error al enviar correo")).build();
            }

            return Response.ok(prestamo).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(503).entity(new Respuesta(Respuesta.ERROR, "Error en BD")).build();
        }
    }

    @GET
    @Path("/{id}")
    @Produces("application/json")
    public Prestamo getPrestamoById(@PathParam("id") int id) {
        try {
            return gPrestamos.getPrestamo(id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @GET
    @Produces("application/json")
    public List<Prestamo> list() {
        return gPrestamos.getPrestamos();
    }

    @GET
    @Path("/activos")
    @Produces("application/json")
    public List<Prestamo> getPrestamosActivos() {
        return gPrestamos.getPrestamosActivos();
    }
    
    @GET
    @Path("/entregados/usuario/{username}")
    @Produces("application/json")
    public List<Prestamo> getPrestamosNoEntregadosPorUsuario(@PathParam("username") String username) {
        return gPrestamos.getPrestamosNoEntregadosPorUsuario(username);
    }

    @POST
    @Path("/entregar/{id}")
    @Produces("application/json")
    public Response entregarPrestamo(@PathParam("id") int id) {
    	try {
            gPrestamos.prestamoEntregado(id);
            return Response.ok().build();
    	}catch (Exception e) {
                e.printStackTrace();
                return Response.status(503).entity(new Respuesta(Respuesta.ERROR, "Error al procesar devolución")).build();
        }
    }
    
    @POST
    @Path("/devolver/{id}")
    @Produces("application/json")
    public Response devolverPrestamo(@PathParam("id") int id) {
        try {
            gPrestamos.returnPrestamo(id);

            // Obtener el préstamo para enviar el correo
            Prestamo prestamo = gPrestamos.getPrestamo(id);
            if (prestamo != null) {
                // Enviar correo de confirmación de devolución
                String email = prestamo.getUsuario().getEmail();
                String subject = "Devolución de Libro Confirmada";
                String body = "Estimado " + prestamo.getUsuario().getUsername() + ",\n\n"
                        + "El libro \"" + prestamo.getLibro().getTitulo() + "\" ha sido devuelto exitosamente.\n"
                        + "Fecha de Devolución: " + prestamo.getFechaDevolucion() + "\n\n"
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
            return Response.status(503).entity(new Respuesta(Respuesta.ERROR, e.getMessage())).build();
        }
    }


}