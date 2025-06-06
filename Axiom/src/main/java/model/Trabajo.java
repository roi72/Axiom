package model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "trabajo")
public class Trabajo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_trabajo;

    @Column(nullable = false, unique = true, length = 191)
    private String nombre;

    @Column
    private String descripcion;

    @OneToMany(mappedBy = "trabajo", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Usuario> usuarios;

    public Trabajo(String nombre, String descripcion) {
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    public Trabajo() {
    }

    public int getId_trabajo() {
        return id_trabajo;
    }

    public void setId_trabajo(int id_trabajo) {
        this.id_trabajo = id_trabajo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }
}

