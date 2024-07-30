package ec.edu.ups.ppw.biblioteca.business;

import java.util.List;

import ec.edu.ups.ppw.biblioteca.dao.PrestamoDAO;
import ec.edu.ups.ppw.biblioteca.model.Prestamo;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

@Stateless
public class GestionPrestamos {

	@Inject
	private PrestamoDAO daoPrestamo;
	
	public Prestamo getPrestamo(int id) throws Exception {
		Prestamo prestamo = daoPrestamo.read(id);
		if(prestamo == null)
			throw new Exception("Prestamo no existe");
		return prestamo;
	}
	
	public List<Prestamo> getPrestamos(){
		return daoPrestamo.getAll();
	}
	
	public void createPrestamo(Prestamo prestamo) throws Exception {
		if(prestamo.getPrestamoId() < 0) {
			throw new Exception("Id Incorrecto");
		}
		daoPrestamo.insert(prestamo);
	}
	
	public void updatePrestamo(Prestamo prestamo) throws Exception{
		if(prestamo.getPrestamoId() < 0) {
			throw new Exception("Id Incorrecta");
		}
		daoPrestamo.update(prestamo);
	}
	
	public void deletePrestamo(int id) throws Exception{
		Prestamo prestamo = daoPrestamo.read(id);
		if(prestamo == null) {				
			throw new Exception("Prestamo no existe");
		}
		else {
			daoPrestamo.delete(id);
		}
		
	}
	
	public List<Prestamo> getPrestamosActivos() {
        return daoPrestamo.getPrestamosActivos();
    }
	
	public List<Prestamo> getPrestamosNoEntregadosPorUsuario(String username) {
	    return daoPrestamo.getPrestamosNoEntregadosPorUsuario(username);
	}
	
	public void returnPrestamo(int id) throws Exception {
        Prestamo prestamo = daoPrestamo.read(id);
        if (prestamo == null) {
            throw new Exception("Prestamo no existe");
        }
        
        if (!prestamo.isEntregado()) { // Verifica si el préstamo ha sido entregado
            throw new Exception("El préstamo no ha sido entregado aún");
        }
        
        prestamo.setDevuelto(true);
        daoPrestamo.update(prestamo);

        // Actualizar la disponibilidad del libro
        prestamo.getLibro().setDisponibilidad(true);
        daoPrestamo.update(prestamo.getLibro());
    }
	
	public void prestamoEntregado(int id) throws Exception{
		Prestamo prestamo = daoPrestamo.read(id);
		if(prestamo == null) {
			throw new Exception("Prestamo no exite");
		}
		prestamo.setEntregado(true);
		daoPrestamo.update(prestamo);
	}
	
	
}
