package model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "log_actividad")
public class LogActividad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_log;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario; // puede ser null

    @ManyToOne
    @JoinColumn(name = "id_evento")
    private Evento evento; // puede ser null

    @Column(nullable = false)
    private String accion;

    @Column(nullable = false)
    private LocalDateTime fecha;

    @Column
    private String descripcion;


    public int getId_log() {
        return id_log;
    }

    public void setId_log(int id_log) {
        this.id_log = id_log;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Evento getEvento() {
        return evento;
    }

    public void setEvento(Evento evento) {
        this.evento = evento;
    }

    public String getAccion() {
        return accion;
    }

    public void setAccion(String accion) {
        this.accion = accion;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}

