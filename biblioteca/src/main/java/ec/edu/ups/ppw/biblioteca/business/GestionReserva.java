package ec.edu.ups.ppw.biblioteca.business;

import java.util.List;

import ec.edu.ups.ppw.biblioteca.dao.ReservarDAO;
import ec.edu.ups.ppw.biblioteca.model.Libro;
import ec.edu.ups.ppw.biblioteca.model.Reservar;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

@Stateless
public class GestionReserva {

    @Inject
    private ReservarDAO daoReserva;
    
    public Reservar getReserva(int id) throws Exception {
        Reservar reserva = daoReserva.read(id);
        if (reserva == null)
            throw new Exception("Reserva no existe");
        return reserva;
    }
    
    public List<Reservar> getReservas() {
        return daoReserva.getAll();
    }
    
    public List<Reservar> getReservasActivas() {
        return daoReserva.getReservasActivas();
    }
    
    public void createReserva(Reservar reserva) throws Exception {
        if (reserva.getReservaId() < 0) {
            throw new Exception("Id Incorrecto");
        }
        daoReserva.insert(reserva);
    }
    
    public void updateReserva(Reservar reserva) throws Exception {
        if (reserva.getReservaId() < 0) {
            throw new Exception("Id Incorrecta");
        }
        daoReserva.update(reserva);
    }
    
    public void deleteReserva(int id) throws Exception {
        Reservar reserva = daoReserva.read(id);
        if (reserva == null) {
            throw new Exception("Reserva no existe");
        } else {
            daoReserva.delete(id);
        }
    }
    
    public List<Reservar> getReservasActivasDelUsuario(String username) {
        return daoReserva.getReservasActivasPorUsuario(username);
    }
    
    public void cancelarReserva(int id) throws Exception {
        Reservar reserva = daoReserva.read(id);
        if (reserva == null) {
            throw new Exception("Reserva no existe");
        }
        reserva.setDevuelto(true); // Marcar la reserva como cancelada
        daoReserva.update(reserva);

        // Actualizar la disponibilidad del libro y el estado de reserva
        Libro libro = reserva.getLibro();
        libro.setDisponibilidad(true); // Marcar el libro como disponible
        libro.setReservado(false); // Marcar el libro como no reservado
        daoReserva.update(libro);
    }

}
