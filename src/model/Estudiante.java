package model;

import java.time.LocalDate;

public class Estudiante extends Persona{

    private int id;
    private LocalDate fechaDeNacimiento;
    private Estado estado;

    public enum Estado {
        MATRICULADO, INACTIVO, GRADUADO
    }

    public Estudiante() {
    }

    public Estudiante(int id, String nombre, String apellido, LocalDate fechaDeNacimiento, Estado estado) {
        super(nombre, apellido);
        this.id = id;
        this.fechaDeNacimiento = fechaDeNacimiento;
        this.estado = estado;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getFechaDeNacimiento() {
        return fechaDeNacimiento;
    }

    public void setFechaDeNacimiento(LocalDate fechaDeNacimiento) {
        this.fechaDeNacimiento = fechaDeNacimiento;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }
}
