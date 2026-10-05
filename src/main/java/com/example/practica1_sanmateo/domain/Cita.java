package com.example.practica1_sanmateo.domain;

import java.sql.Date;

public class Cita {
    private int idCita;
    private String nombreEspecialidad;
    private Date fecha;
    private int idPaciente;

    public Cita() {
    }

    public Cita(int idCita, String nombreEspecialidad, Date fecha, int idPaciente) {
        this.idCita = idCita;
        this.nombreEspecialidad = nombreEspecialidad;
        this.fecha = fecha;
        this.idPaciente = idPaciente;
    }

    public int getIdCita() {
        return idCita;
    }

    public void setIdCita(int idCita) {
        this.idCita = idCita;
    }

    public String getNombreEspecialidad() {
        return nombreEspecialidad;
    }

    public void setNombreEspecialidad(String nombreEspecialidad) {
        this.nombreEspecialidad = nombreEspecialidad;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(int idPaciente) {
        this.idPaciente = idPaciente;
    }
}
