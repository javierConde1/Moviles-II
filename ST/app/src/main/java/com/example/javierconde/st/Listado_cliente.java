package com.example.javierconde.st;

public class Listado_cliente {
    private int no_orden;
    private String estado;
    private String no_cleinte;
    private String fecha;
    private String prioridad;
    private String domicilio;
    private String problema;
    private String fechaProg;
    private String tecnico;
    private String sDesc;

    public Listado_cliente() {}

    public Listado_cliente(int no_orden, String estado, String fecha, String prioridad, String domicilio, String problema,String fechaProg,String tecnico, String sDesc) {
        this.no_orden = no_orden;
        this.estado = estado;
        this.fecha = fecha;
        this.prioridad = prioridad;
        this.domicilio = domicilio;
        this.problema = problema;
        this.fechaProg = fechaProg;
        this.tecnico = tecnico;
        this.sDesc = sDesc;
    }

    public int getNo_orden() {
        return no_orden;
    }

    public void setNo_orden(int no_orden) {
        this.no_orden = no_orden;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(String prioridad) {
        this.prioridad = prioridad;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    public String getProblema() {
        return problema;
    }

    public void setProblema(String problema) {
        this.problema = problema;
    }

    public String getFechaProg() {
        return fechaProg;
    }

    public void setFechaProg(String fechaProg) {
        this.fechaProg = fechaProg;
    }

    public String getTecnico() {
        return tecnico;
    }

    public void setTecnico(String tecnico) {
        this.tecnico = tecnico;
    }

    public String getsDesc() {
        return sDesc;
    }

    public void setsDesc(String sDesc) {
        this.sDesc = sDesc;
    }
}