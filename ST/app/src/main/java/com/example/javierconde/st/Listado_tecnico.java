package com.example.javierconde.st;

public class Listado_tecnico {
    private String no_ordenTec;
    private String estadoTec;
    private String no_cleinte;
    private String fechaTec;
    private String prioridadTec;
    private String domicilioTec;
    private String sProblema;
    private String sDesc;
    private String sFechaprog;

    public Listado_tecnico() {}

    public Listado_tecnico(String no_ordenTec, String estadoTec, String fechaTec, String prioridadTec, String domicilioTec, String sFechaprog,String sProblema, String sDesc, String no_cleinte) {
        this.no_ordenTec = no_ordenTec;
        this.estadoTec = estadoTec;
        this.fechaTec = fechaTec;
        this.prioridadTec = prioridadTec;
        this.domicilioTec = domicilioTec;
        this.sProblema= sProblema;
        this.sDesc = sDesc;
        this.sFechaprog = sFechaprog;
        this.no_cleinte= no_cleinte;
    }

    public String getNo_ordenTec() {
        return no_ordenTec;
    }

    public void setNo_ordenTec(String no_ordenTec) {
        this.no_ordenTec = no_ordenTec;
    }

    public String getEstadoTec() {
        return estadoTec;
    }

    public void setEstadoTec(String estadoTec) {
        this.estadoTec = estadoTec;
    }

    public String getNo_cleinte() {
        return no_cleinte;
    }

    public void setNo_cleinte(String no_cleinte) {
        this.no_cleinte = no_cleinte;
    }

    public String getFechaTec() {
        return fechaTec;
    }

    public void setFechaTec(String fechaTec) {
        this.fechaTec = fechaTec;
    }

    public String getPrioridadTec() {
        return prioridadTec;
    }

    public void setPrioridadTec(String prioridadTec) {
        this.prioridadTec = prioridadTec;
    }

    public String getDomicilioTec() {
        return domicilioTec;
    }

    public void setDomicilioTec(String domicilioTec) {
        this.domicilioTec = domicilioTec;
    }

    public String getsProblema() {
        return sProblema;
    }

    public void setsProblema(String sProblema) {
        this.sProblema = sProblema;
    }

    public String getsDesc() {
        return sDesc;
    }

    public void setsDesc(String sDesc) {
        this.sDesc = sDesc;
    }

    public String getsFechaprog() {
        return sFechaprog;
    }

    public void setsFechaprog(String sFechaprog) {
        this.sFechaprog = sFechaprog;
    }
}
