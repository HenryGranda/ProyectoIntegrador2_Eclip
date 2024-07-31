package ec.edu.ups.ppw.biblioteca.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "TBL_RESERVADO")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Reservar {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "res_id")
    private int reservaId;
	
	@Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fecha_reserva")
    private Date fechaReserva;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fecha_devolucion")
    private Date fechaDevolucion;
    
    @ManyToOne
    @JoinColumn(name = "lib_id")
    private Libro libro;

    @ManyToOne
    @JoinColumn(name = "usu_id")
    private Usuario usuario;
    
    @Column(name = "devuelto")
    private boolean devuelto;

	public int getReservaId() {
		return reservaId;
	}

	public void setReservaId(int reservaId) {
		this.reservaId = reservaId;
	}

	public Date getFechaReserva() {
		return fechaReserva;
	}

	public void setFechaReserva(Date fechaReserva) {
		this.fechaReserva = fechaReserva;
	}

	public Date getFechaDevolucion() {
		return fechaDevolucion;
	}

	public void setFechaDevolucion(Date fechaDevolucion) {
		this.fechaDevolucion = fechaDevolucion;
	}

	public Libro getLibro() {
		return libro;
	}

	public void setLibro(Libro libro) {
		this.libro = libro;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public boolean isDevuelto() {
		return devuelto;
	}

	public void setDevuelto(boolean devuelto) {
		this.devuelto = devuelto;
	}
    
    
}
