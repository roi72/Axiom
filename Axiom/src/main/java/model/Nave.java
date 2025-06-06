package model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "nave")
public class Nave {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_nave;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private int capacidad;

    @Column(nullable = false)
    private double velocidad;

    @Column(nullable = false)
    private double tamaño;

    @OneToMany(mappedBy = "nave", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Usuario> usuarios;

    @OneToMany(mappedBy = "nave", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Recurso> recursos;


    public int getId_nave() {
        return id_nave;
    }

    public void setId_nave(int id_nave) {
        this.id_nave = id_nave;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    public double getVelocidad() {
        return velocidad;
    }

    public void setVelocidad(double velocidad) {
        this.velocidad = velocidad;
    }

    public double getTamaño() {
        return tamaño;
    }

    public void setTamaño(double tamaño) {
        this.tamaño = tamaño;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public List<Recurso> getRecursos() {
        return recursos;
    }

    public void setRecursos(List<Recurso> recursos) {
        this.recursos = recursos;
    }
}

