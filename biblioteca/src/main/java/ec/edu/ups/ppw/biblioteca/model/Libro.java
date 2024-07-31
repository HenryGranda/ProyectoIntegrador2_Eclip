package ec.edu.ups.ppw.biblioteca.model;

import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name="TBL_LIBRO")
public class Libro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="lib_id")
    private int libroId;
    
    @Column(name="lib_titulo")
    private String titulo;
    
    @Column(name="lib_edicion")
    private int edicion;
    
    @Column(name="lib_genero")
    private String genero;
    
    @Column(name="lib_autor")
    private String autor;
    
    @Column(name="lib_contenido")
    private String contenido;
    
    @Column(name="lib_portada")
    private String portada;
    
    @Column(name="lib_disponibilidad")
    private Boolean disponibilidad;
    
    @Column(name = "lib_reservado")
    private Boolean reservado;
    
    @OneToMany(mappedBy="libro")
    private List<Prestamo> prestamo;

    // Getters y setters
    public int getLibroId() {
        return libroId;
    }

    public void setLibroId(int libroId) {
        this.libroId = libroId;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getEdicion() {
        return edicion;
    }

    public void setEdicion(int edicion) {
        this.edicion = edicion;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public String getPortada() {
        return portada;
    }

    public void setPortada(String portada) {
        this.portada = portada;
    }

	public Boolean getDisponibilidad() {
		return disponibilidad;
	}

	public void setDisponibilidad(Boolean disponibilidad) {
		this.disponibilidad = disponibilidad;
	}

	public Boolean getReservado() {
		return reservado;
	}

	public void setReservado(Boolean reservado) {
		this.reservado = reservado;
	}
    
    

}
