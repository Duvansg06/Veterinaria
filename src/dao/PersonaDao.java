package dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import conexion.Conexion;
import principal.Coordinador;
import vo.MascotaVo;
import vo.PersonaVo;

public class PersonaDao {

	private Connection conexion;
	
	public PersonaDao() {
		conexion = Conexion.getInstancia().getConnection();
	}
	
	

	public String registrarPersona(PersonaVo persona) {
		String resultado = "";
		String consulta = "INSERT INTO persona(documento, nombre, telefono)" + "VALUES (?,?,?)";
		
		try {
			PreparedStatement pre = conexion.prepareStatement(consulta);
			
			pre.setString(1, persona.getDocumento());
			pre.setString(2, persona.getNombre());
			pre.setString(3, persona.getTelefono());
			
			
			int filasAfectadas = pre.executeUpdate();
            if (filasAfectadas > 0) {
                resultado = "Persona registrada exitosamente en la base de datos.";
                System.out.println(resultado);
            } else {
                resultado = "No se registró ninguna fila.";
                System.out.println(resultado);
            }
			
			
		} catch (Exception e) {
			 resultado = "Error al registrar: " + e.getMessage();
	            System.err.println(resultado);
		}
		
		return resultado;
		
		
	}
	
	public PersonaVo consultarPorDocumento(String documento) {
	    PersonaVo persona = null;

	    String sql = "SELECT * FROM persona WHERE documento = ?";

	    try {
	        PreparedStatement ps = conexion.prepareStatement(sql);
	        ps.setString(1, documento);
	        ResultSet rs = ps.executeQuery();

	        if (rs.next()) {
	        	
	        	
	            persona = new PersonaVo();
	            

	            persona.setDocumento(rs.getString("documento"));
	            persona.setNombre(rs.getString("nombre"));
	            persona.setTelefono(rs.getString("telefono"));
	        }

	    } catch (SQLException e) {
	        System.err.println("Error al consultar persona: " + e.getMessage());
	    }

	    return persona;
	}



	public String actualizarPersona(PersonaVo persona) {
		
		Connection con = Conexion.getInstancia().getConnection();
		String sql = "UPDATE persona SET  nombre = ?, telefono = ? WHERE documento = ? ";
		String resultado = "";
		
		try {
			
			PreparedStatement ps = conexion.prepareStatement(sql);
			
			
			ps.setString(1, persona.getNombre());
			ps.setString(2, persona.getTelefono());
			ps.setString(3, persona.getDocumento().trim());
			
			int filas = ps.executeUpdate();
			
			if(filas > 0) {
				resultado = "Persona Actualizada con exito";
			}else {
				resultado = "No se econtro la persona con ese documento";
			}
			
		} catch (Exception e) {
			resultado = "Eroro al actualizar persona " + e.getMessage();
		}
		
		return resultado	;
	}



	
	public String eliminarPersonaConMascotas(String documento) {
	    String resultado = "";

	    try {
	     
	        String sqlEliminarMascotas = "DELETE FROM mascota WHERE documento_persona = ?";
	        PreparedStatement psMascota = conexion.prepareStatement(sqlEliminarMascotas);
	        psMascota.setString(1, documento);
	        psMascota.executeUpdate(); 

	       
	        String sqlEliminarPersona = "DELETE FROM persona WHERE documento = ?";
	        PreparedStatement psPersona = conexion.prepareStatement(sqlEliminarPersona);
	        psPersona.setString(1, documento);
	        int filasAfectadas = psPersona.executeUpdate();

	        if (filasAfectadas > 0) {
	            resultado = "Persona y sus mascotas eliminadas correctamente.";
	        } else {
	            resultado = "No se encontró la persona para eliminar.";
	        }

	    } catch (SQLException e) {
	        resultado = "Error al eliminar: " + e.getMessage();
	        System.err.println(resultado);
	    }

	    return resultado;
	}




	public List<PersonaVo> consultarListaPersonas() {
		List<PersonaVo> lista = new ArrayList<PersonaVo>();
		
		String sql = "SELECT * FROM persona";
		
		try {
			Connection conectar = Conexion.getInstancia().getConnection();
	        PreparedStatement ps = conectar.prepareStatement(sql);
	        ResultSet rs = ps.executeQuery();
	        
	        while (rs.next()) {
	            PersonaVo persona = new PersonaVo();
	            persona.setDocumento(rs.getString("documento"));
	            persona.setNombre(rs.getString("nombre"));
	            persona.setTelefono(rs.getString("telefono"));
	            
	            lista.add(persona);
	        }
	        
	        rs.close();
	        ps.close();
	        conectar.close();

			
		} catch (Exception e) {
			 System.out.println("Error al consultar lista: " + e.getMessage());
		}
		return lista;
	}





	
	

	
	
	
	
	


	


	
}
