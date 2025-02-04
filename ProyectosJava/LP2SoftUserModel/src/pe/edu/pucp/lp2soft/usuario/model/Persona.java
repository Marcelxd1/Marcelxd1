package pe.edu.pucp.lp2soft.usuario.model;

public class Persona {
    private int id_persona ; 
    private String nombre ;
    private String apellido_paterno ; 
    private String apellido_materno ;
    private String DNI ; 
    private char tipo ; 
    private Actividad actividad;
    private String razon_social;
    private String ruc ;
    private boolean activo ; 
    private boolean VIP ; 
    private boolean asociado ; 
    
    public Persona(){}
    public Persona( String nombre, String apellido_paterno, String apellido_materno, String DNI, char tipo ) {
        this.nombre = nombre;
        this.apellido_paterno = apellido_paterno;
        this.apellido_materno = apellido_materno;
        this.DNI = DNI;
        this.tipo = tipo;
        this.activo = true;
    }
    public Persona (String nombre,String razon_social , String ruc, char tipo){ // PARA EMPRESAS 
        this.razon_social = razon_social ;
        this.ruc = ruc ; 
        this.activo = true;
        this.tipo = tipo;
        this.nombre = nombre;
    }
    
    
    
    public int getId_persona() {
        return id_persona;
    }

    public void setId_persona(int id_persona) {
        this.id_persona = id_persona;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido_paterno() {
        return apellido_paterno;
    }

    public void setApellido_paterno(String apellido_paterno) {
        this.apellido_paterno = apellido_paterno;
    }

    public String getApellido_materno() {
        return apellido_materno;
    }

    public void setApellido_materno(String apellido_materno) {
        this.apellido_materno = apellido_materno;
    }

    public String getDNI() {
        return DNI;
    }

    public void setDNI(String DNI) {
        this.DNI = DNI;
    }

    public char getTipo() {
        return tipo;
    }

    public void setTipo(char tipo) {
        this.tipo = tipo;
    }

    public String getRazon_social() {
        return razon_social;
    }

    public void setRazon_social(String razon_social) {
        this.razon_social = razon_social;
    }

    public String getRuc() {
        return ruc;
    }

    public void setRuc(String ruc) {
        this.ruc = ruc;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public Actividad getActividad() {
        return actividad;
    }

    public void setActividad(Actividad actividad) {
        this.actividad = actividad;
    }

    public boolean isVIP() {
        return VIP;
    }

    public void setVIP(boolean VIP) {
        this.VIP = VIP;
    }

    public boolean isAsociado() {
        return asociado;
    }

    public void setAsociado(boolean asociado) {
        this.asociado = asociado;
    }
    
    
    
}
