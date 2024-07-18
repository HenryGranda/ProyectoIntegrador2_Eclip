package ec.edu.ups.ppw.biblioteca.services;

import java.util.List;
import ec.edu.ups.ppw.biblioteca.business.GestionPrestamos;
import ec.edu.ups.ppw.biblioteca.model.Prestamo;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;

@Path("/prestamos")
public class PrestamoService {

	@Inject
	private GestionPrestamos gPrestamos;
	
	@GET
    @Path("/{id}")
    @Produces("application/json")
    public Prestamo getPrestamoById(@PathParam("id") int id) {
		Prestamo prestamo;
		try {
			prestamo = gPrestamos.getPrestamo(id);
			return prestamo;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@GET
	@Produces("application/json")
	public List<Prestamo> list(){
		List<Prestamo> prestamos = gPrestamos.getPrestamos();
		return prestamos;
	}
}
