package pe.edu.pucp.lp2soft.user.mysql;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import pe.edu.pucp.lp2soft.config.DBManager;
import pe.edu.pucp.lp2soft.user.dao.PersonaDAO;
import pe.edu.pucp.lp2soft.usuario.model.Actividad;
import pe.edu.pucp.lp2soft.usuario.model.Persona;
import pe.edu.pucp.lp2soft.usuario.model.Sector;


public class PersonaMySQL implements PersonaDAO {
    private Connection con ; 
    private ResultSet rs ;
    private CallableStatement cs ;
    
    @Override
    public ArrayList<Persona> listarPersonasTodas() {
        ArrayList<Persona> personas = new ArrayList<>();
        try {
            con = DBManager.getInstance().getConnection();
            cs = con.prepareCall("{call LISTAR_PERSONAS_TODAS()}");
            rs = cs.executeQuery();
            while (rs.next()){
                Persona persona = new Persona();
                persona.setId_persona(rs.getInt("id_persona"));
                persona.setNombre(rs.getString("nombre"));
                persona.setApellido_paterno(rs.getString("apellido_paterno"));
                persona.setApellido_materno(rs.getString("apellido_materno"));
                persona.setDNI(rs.getString("DNI"));
                persona.setRazon_social(rs.getString("razon_social"));
                persona.setRuc(rs.getString("RUC"));
                persona.setTipo(rs.getString("fid_tipo").charAt(0));
                Actividad act = new Actividad();
                act.setSector(new Sector());
                act.getSector().setId_sector(rs.getInt("id_sector"));
                act.getSector().setDescripcion(rs.getString("sector"));
                act.setId_actividad(rs.getInt("id_actividad"));
                act.setDescripcion(rs.getString("actividad"));
                persona.setActividad(act);
                
                personas.add(persona);
            }
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }finally{
            try{con.close();}catch(Exception ex){System.out.println(ex.getMessage());}
        }
        
        return personas ;
    }
    
    @Override
    public ArrayList<Persona> listarClientesXNombre(String nombre, int sector,int actividad) {
        ArrayList<Persona> personas = new ArrayList<>();
        try {
            con = DBManager.getInstance().getConnection();
            cs = con.prepareCall("{call LISTAR_CLIENTES_X_NOMBRE(?,?,?)}");
            cs.setString("_nombre", nombre);
            cs.setInt("_sector", sector);
            cs.setInt("_actividad", actividad);
            rs = cs.executeQuery();
            while (rs.next()){
                Persona persona = new Persona();
                persona.setId_persona(rs.getInt("id_persona"));
                persona.setNombre(rs.getString("nombre"));
                persona.setApellido_paterno(rs.getString("apellido_paterno"));
                persona.setApellido_materno(rs.getString("apellido_materno"));
                persona.setDNI(rs.getString("DNI"));
                persona.setRazon_social(rs.getString("razon_social"));
                persona.setRuc(rs.getString("RUC"));
                persona.setTipo(rs.getString("fid_tipo").charAt(0));
                persona.setAsociado(rs.getBoolean("asociado"));
                persona.setVIP(rs.getBoolean("VIP")); 
                Actividad act = new Actividad();
                act.setSector(new Sector());
                act.getSector().setId_sector(rs.getInt("id_sector"));
                act.getSector().setDescripcion(rs.getString("sector"));
                act.setId_actividad(rs.getInt("id_actividad"));
                act.setDescripcion(rs.getString("actividad"));
                persona.setActividad(act);
                personas.add(persona);
            }
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }finally{
            try{con.close();}catch(Exception ex){System.out.println(ex.getMessage());}
        }
        return personas ;
    }
    
    @Override
    public ArrayList<Persona> listarPersonasXnombre(String nombre) {
        ArrayList<Persona> personas = new ArrayList<>();
        try {
            con = DBManager.getInstance().getConnection();
            cs = con.prepareCall("{call LISTAR_PERSONAS_X_NOMBRE(?)}");
            cs.setString("_nombre", nombre);
            rs = cs.executeQuery();
            while (rs.next()){
                Persona persona = new Persona();
                persona.setId_persona(rs.getInt("id_persona"));
                persona.setNombre(rs.getString("nombre"));
                persona.setApellido_paterno(rs.getString("apellido_paterno"));
                persona.setApellido_materno(rs.getString("apellido_materno"));
                persona.setDNI(rs.getString("DNI"));
                persona.setTipo(rs.getString("fid_tipo").charAt(0));
                persona.setAsociado(rs.getBoolean("asociado"));
                persona.setVIP(rs.getBoolean("VIP")); 
                personas.add(persona);
            }
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }finally{
            try{con.close();}catch(Exception ex){System.out.println(ex.getMessage());}
        }
        
        return personas ;
    }

    @Override
    public int insertarPersona(Persona persona) {
        int resultado = 0 ; 
        try {
            con = DBManager.getInstance().getConnection();
            
            cs = con.prepareCall("{call INSERTAR_PERSONA(?,?,?,?,?,?,?,?,?)}");
            cs.registerOutParameter("_id_persona", java.sql.Types.INTEGER);
            cs.setString("_fid_tipo",String.valueOf('N'));
            cs.setString("_nombre", persona.getNombre());
            cs.setString("_apellido_paterno", persona.getApellido_paterno());
            cs.setString("_apellido_materno", persona.getApellido_materno());
            cs.setString("_DNI", persona.getDNI());
            cs.setBoolean("_VIP", persona.isVIP());
            cs.setBoolean("_asociado", persona.isAsociado());
            cs.setBoolean("_cliente", true);
            cs.executeUpdate();
            persona.setId_persona(cs.getInt("_id_persona"));
            resultado = persona.getId_persona() ; 
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }finally{
            try{con.close();}catch(Exception ex){System.out.println(ex.getMessage());}
        }
        
        return resultado ; 
    }
    
    @Override
    public int modificarPersona(Persona persona) {
        int resultado = 0 ; 
        try {
            con = DBManager.getInstance().getConnection();
            cs = con.prepareCall("{call MODIFICAR_PERSONA(?,?,?,?,?,?,?)}");
            cs.setInt("_id_persona",persona.getId_persona());
            cs.setString("_nombre" , persona.getNombre());
            cs.setString("_apellido_paterno" , persona.getApellido_paterno());
            cs.setString("_apellido_materno" , persona.getApellido_materno());
            cs.setString("_DNI" , persona.getDNI());
            cs.setBoolean("_VIP", persona.isVIP());
            cs.setBoolean("_asociado", persona.isAsociado());
            cs.executeUpdate();
            resultado  = 1; 
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }finally{
            try{con.close();}catch(Exception ex){System.out.println(ex.getMessage());}
        }
        
        return resultado ;
    }

    @Override
    public int eliminarPersona(int idPersona) {
        int resultado = 0 ; 
        try {
            con = DBManager.getInstance().getConnection();
            cs = con.prepareCall("{call ELIMINAR_PERSONA(?)}");
            cs.setInt("_id_persona",idPersona);
            cs.executeUpdate();
            resultado  = 1; 
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }finally{
            try{con.close();}catch(Exception ex){System.out.println(ex.getMessage());}
        }
        
        return resultado ;
    }

    
    @Override
    public ArrayList<Persona> listarEmpresas() {
        ArrayList<Persona> personas = new ArrayList<>();
        try {
            con = DBManager.getInstance().getConnection();
            cs = con.prepareCall("{call LISTAR_EMPRESAS()}");
            rs = cs.executeQuery();
            while (rs.next()){
                Persona persona = new Persona();
                persona.setId_persona(rs.getInt("id_persona"));
                persona.setNombre(rs.getString("nombre"));
                persona.setRazon_social(rs.getString("razon_social"));
                persona.setRuc(rs.getString("RUC"));
                persona.setTipo(rs.getString("fid_tipo").charAt(0));
                personas.add(persona);
            }
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }finally{
            try{con.close();}catch(Exception ex){System.out.println(ex.getMessage());}
        }
        
        return personas ;
    }
    
    @Override
    public int insertarEmpresa(Persona persona) {
        int resultado = 0 ; 
        try {
            con = DBManager.getInstance().getConnection();
           
            cs = con.prepareCall("{call INSERTAR_EMPRESA(?,?,?,?,?,?,?,?,?)}");
            cs.registerOutParameter("_id_persona", java.sql.Types.INTEGER);
            cs.setString("_nombre", persona.getNombre());
            cs.setString("_fid_tipo", String.valueOf(persona.getTipo()));
            cs.setInt("_fid_actividad", persona.getActividad().getId_actividad());
            cs.setString("_razon_social",persona.getRazon_social());
            cs.setString("_ruc", persona.getRuc());
            cs.setBoolean("_VIP", persona.isVIP());
            cs.setBoolean("_asociado", persona.isAsociado());
            cs.setBoolean("_cliente", true);
            cs.executeUpdate();
            persona.setId_persona(cs.getInt("_id_persona"));
            resultado = persona.getId_persona() ; 
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }finally{
            try{con.close();}catch(Exception ex){System.out.println(ex.getMessage());}
        }
        
        return resultado ; 
    }

    @Override
    public int modificarEmpresa(Persona persona) {
        int resultado = 0 ; 
        try {
            con = DBManager.getInstance().getConnection();
            cs = con.prepareCall("{call MODIFICAR_EMPRESA(?,?,?,?,?,?,?)}");
            cs.setInt("_id_persona",persona.getId_persona());
            cs.setString("_nombre" , persona.getNombre());
            cs.setString("_razon_social" , persona.getRazon_social());
            cs.setString("_RUC" , persona.getRuc());
            cs.setInt("_fid_actividad", persona.getActividad().getId_actividad());
            cs.setBoolean("_VIP", persona.isVIP());
            cs.setBoolean("_asociado", persona.isAsociado());
            cs.executeUpdate();
            resultado  = 1; 
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }finally{
            try{con.close();}catch(Exception ex){System.out.println(ex.getMessage());}
        }
        
        return resultado ;
    }
    
    @Override
    public Persona listarPorId(int idPersona) {
        Persona persona = null;
        int resultado = 0;
        try{
            con = DBManager.getInstance().getConnection();
            cs = con.prepareCall("{call BUSCAR_PERSONA_POR_ID(?)}");
            cs.setInt("_id_persona", idPersona);
            rs = cs.executeQuery();
            if(rs.next()){
                persona = new Persona();
                persona.setId_persona(idPersona);
                persona.setNombre(rs.getString("nombre"));
                persona.setApellido_paterno(rs.getString("apellido_paterno"));
                persona.setApellido_materno(rs.getString("apellido_materno"));
                persona.setDNI(rs.getString("DNI"));
                
                persona.setTipo(rs.getString("fid_tipo").charAt(0));
                persona.setRuc(rs.getString("RUC"));
            }
            resultado = 1;
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }finally{
            try{con.close();}catch(Exception ex){System.out.println(ex.getMessage());}
        }
        return persona;
    }

}
