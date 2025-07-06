package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import conexion.Conexion;
import vo.MascotaVo;
import vo.PersonaVo;

public class MascotaDao {
	private Connection conexion;
	
	public MascotaDao() {
		conexion = Conexion.getInstancia().getConnection();
		}

	public String registrarMascota(MascotaVo mascota) {
		String resultadoMasc = "";
		String consultaMasc = "INSERT INTO mascota( documento_persona, nombreMasc, raza, sexo )" + "VALUES (?,?,?,?)";
		
		try {
			PreparedStatement pre = conexion.prepareStatement(consultaMasc);
			
			pre.setString(1, mascota.getDocumentoPersona());
			pre.setString(2, mascota.getNombreMasc());
			pre.setString(3, mascota.getRaza());
			pre.setString(4, mascota.getSexo());
			
			int filasAfectadas = pre.executeUpdate();
			if (filasAfectadas > 0) {
                resultadoMasc = "Mascota registrada exitosamente en la base de datos." + "\n";
                System.out.println(resultadoMasc);
            } else {
                resultadoMasc = "No se registró ninguna fila.";
                System.out.println(resultadoMasc);
            }
			
		} catch (Exception e) {
			 resultadoMasc = "Error al registrar: " + e.getMessage();
	            System.err.println(resultadoMasc);
		}
		
		
		return resultadoMasc;
	}

	public MascotaVo consultarMascota(String documentoMasc) {
	    MascotaVo mascota = null;

	    String sql = "SELECT m.nombreMasc, m.raza, m.sexo, m.documento_persona, p.nombre AS nombre_dueno " +
	                 "FROM mascota m " +
	                 "JOIN persona p ON m.documento_persona = p.documento " +
	                 "WHERE m.documento_persona = ?";

	    try {
	        PreparedStatement ms = conexion.prepareStatement(sql);
	        ms.setString(1, documentoMasc);
	        ResultSet rs = ms.executeQuery();

	        if (rs.next()) {
	            mascota = new MascotaVo();
	            mascota.setNombreMasc(rs.getString("nombreMasc"));
	            mascota.setRaza(rs.getString("raza"));
	            mascota.setSexo(rs.getString("sexo"));
	            mascota.setDocumentoPersona(rs.getString("documento_persona"));
	            mascota.setNombreDueno(rs.getString("nombre_dueno")); 
	        }
	    } catch (Exception e) {
	        System.err.println("Error al consultar Mascota: " + e.getMessage());
	    }

	    return mascota;
	}

	public String actualizarMascota(MascotaVo mascota) {
		Connection con = Conexion.getInstancia().getConnection();
		String sql = "UPDATE mascota SET  nombreMasc = ?, raza = ?, sexo = ? WHERE documento_persona = ? ";
		String resultado = "";
		
		try {
			PreparedStatement ms = conexion.prepareStatement(sql);
			
			
			ms.setString(1, mascota.getNombreMasc());
			ms.setString(2, mascota.getRaza());
			ms.setString(3, mascota.getSexo());
			ms.setString(4, mascota.getDocumentoPersona());
			
			int filas = ms.executeUpdate();
			
			if(filas > 0) {
				resultado = "Mascota Actualizada con exito";
			}else {
				resultado = "No se econtro la Mascota con ese documento";
			}
			
		} catch (Exception e) {
			resultado = "Eroro al actualizar persona " + e.getMessage();

		}

		return resultado;
	}

	public List<MascotaVo> consultarListaMasc() {
		
		List<MascotaVo> lista = new ArrayList<MascotaVo>();
		String sql = "SELECT  m.nombreMasc, m.raza, m.sexo, p.nombre AS nombreDueno " +
	             "FROM mascota m JOIN persona p ON m.documento_persona = p.documento";


		try {
			Connection conectar = Conexion.getInstancia().getConnection();
	        PreparedStatement ps = conectar.prepareStatement(sql);
	        ResultSet rs = ps.executeQuery();
	        
	        while (rs.next()) {
				MascotaVo mascota = new MascotaVo();
				mascota.setNombreDueno(rs.getString("nombreDueno"));
				mascota.setNombreMasc(rs.getString("nombreMasc"));
				mascota.setRaza(rs.getString("raza"));
				mascota.setSexo(rs.getString("sexo"));
				
				lista.add(mascota);
				
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
