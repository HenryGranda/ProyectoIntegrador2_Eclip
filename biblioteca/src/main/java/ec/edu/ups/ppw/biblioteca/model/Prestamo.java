package ec.edu.ups.ppw.biblioteca.model;

import java.util.Date;
import jakarta.persistence.*;

@Entity
@Table(name="TBL_PRESTAMO")
public class Prestamo {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="pre_id")
    private int prestamoId;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="fecha_prestamo")
    private Date fechaPrestamo;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="fecha_devolucion")
    private Date fechaDevolucion;

    @ManyToOne
    @JoinColumn(name="lib_id")
    private Libro libro;

    @ManyToOne
    @JoinColumn(name="usu_id")
    private Usuario usuario;

    // Getters y setters
    public int getPrestamoId() {
        return prestamoId;
    }

    public void setPrestamoId(int prestamoId) {
        this.prestamoId = prestamoId;
    }

    public Date getFechaPrestamo() {
        return fechaPrestamo;
    }

    public void setFechaPrestamo(Date fechaPrestamo) {
        this.fechaPrestamo = fechaPrestamo;
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

}