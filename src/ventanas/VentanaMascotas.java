package ventanas;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dao.MascotaDao;
import principal.Coordinador;
import vo.MascotaVo;
import vo.PersonaVo;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;

public class VentanaMascotas extends JDialog implements ActionListener{

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField textIdDUEÑO;
	private JTextField textNombre;
	private JTextField textRaza;
	private JTextField textSexo;
	private Coordinador miCoordinador;
	private JButton btnRegistrarMasc;
	private JButton btnConsultarMasc;
	private JButton btnActualizar;
	private JButton btnEliminarMasc;
	private JButton btnConsultarListaMasc;
	private JTextArea textAreaResultadoMasc;
	
	
	public void setCoordinador(Coordinador miCoordinador) {
		this.miCoordinador = miCoordinador;
		
	}

	
	/**
	 * Create the dialog.
	 */
	public VentanaMascotas() {
		setBounds(100, 100, 635, 427);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblTitleMascota = new JLabel("GESTIONAR MASCOTAS");
			lblTitleMascota.setBounds(253, 11, 156, 27);
			contentPanel.add(lblTitleMascota);
		
			JLabel lblIDdueño = new JLabel("ID dueño");
			lblIDdueño.setBounds(72, 61, 49, 14);
			contentPanel.add(lblIDdueño);
		
			textIdDUEÑO = new JTextField();
			textIdDUEÑO.setBounds(143, 58, 96, 20);
			contentPanel.add(textIdDUEÑO);
			textIdDUEÑO.setColumns(10);
		
			JLabel lblnombre = new JLabel("Nombre");
			lblnombre.setBounds(72, 95, 49, 14);
			contentPanel.add(lblnombre);
		
			textNombre = new JTextField();
			textNombre.setBounds(143, 92, 96, 20);
			contentPanel.add(textNombre);
			textNombre.setColumns(10);
		
			JLabel lblraza = new JLabel("Raza");
			lblraza.setBounds(387, 61, 49, 14);
			contentPanel.add(lblraza);
		
			textRaza = new JTextField();
			textRaza.setBounds(437, 58, 96, 20);
			contentPanel.add(textRaza);
			textRaza.setColumns(10);
		
			JLabel lblsexo = new JLabel("Sexo");
			lblsexo.setBounds(387, 95, 49, 14);
			contentPanel.add(lblsexo);
		
			textSexo = new JTextField();
			textSexo.setBounds(437, 92, 96, 20);
			contentPanel.add(textSexo);
			textSexo.setColumns(10);
		
			btnRegistrarMasc = new JButton("Registrar");
			btnRegistrarMasc.setBounds(72, 159, 89, 23);
			contentPanel.add(btnRegistrarMasc);
			btnRegistrarMasc.addActionListener(this);
		
			btnConsultarMasc = new JButton("Consultar");
			btnConsultarMasc.setBounds(212, 159, 89, 23);
			contentPanel.add(btnConsultarMasc);
			btnConsultarMasc.addActionListener(this);
		
			btnActualizar = new JButton("Actualizar");
			btnActualizar.setBounds(347, 159, 89, 23);
			contentPanel.add(btnActualizar);
			btnActualizar.addActionListener(this);
		
			btnEliminarMasc = new JButton("Eliminar");
			btnEliminarMasc.setBounds(481, 159, 89, 23);
			contentPanel.add(btnEliminarMasc);
			btnEliminarMasc.addActionListener(this);
		
			btnConsultarListaMasc = new JButton("Consultar Lista");
			btnConsultarListaMasc.setBounds(167, 193, 313, 23);
			contentPanel.add(btnConsultarListaMasc);
			btnConsultarListaMasc.addActionListener(this);
		
		
			textAreaResultadoMasc = new JTextArea();
			textAreaResultadoMasc.setBounds(72, 224, 498, 155);
			contentPanel.add(textAreaResultadoMasc);
		}
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnRegistrarMasc) {
			registrarMascota();
			
		
		}
		
		if(e.getSource() == btnConsultarMasc) {
			consultarMascota();
		}
		
		if(e.getSource() == btnActualizar) {
			actualizarMascota();
			limpiar();	
		}
		
		if(e.getSource() == btnEliminarMasc) {
			eliminarMascota();
		}
		
		if(e.getSource() == btnConsultarListaMasc) {
			consultarListaMasc();
		}
	}


	private void consultarListaMasc() {
	
		List<MascotaVo> lista = miCoordinador.consultarListaMasc();
		
		if(lista.isEmpty()) {
			JOptionPane.showMessageDialog(this, "No hay Mascotas registradas");

		}else {
			StringBuilder resultado = new StringBuilder();
			
			for(MascotaVo mascota : lista) {
				resultado.append("Nombre Mascota: ").append(mascota.getNombreMasc() + "\n")
						.append("Dueno: ").append(mascota.getNombreDueno() + "\n")
						.append("Raza: ").append(mascota.getRaza() + "\n")
						.append("Sexo: ").append(mascota.getSexo() + "\n")
						.append("**************************" + "\n");
						
			}
			textAreaResultadoMasc.setText(resultado.toString());

		}
		
		
	}
	
	

	private void eliminarMascota() {
	    String documento = textIdDUEÑO.getText();

	    if (documento.isEmpty()) {
	        JOptionPane.showMessageDialog(this, "Debe ingresar un documento");
	        return;
	    }

	    int confirmacion = JOptionPane.showConfirmDialog(this, "¿Seguro que desea eliminar esta persona y sus mascotas?");
	    if (confirmacion != JOptionPane.YES_OPTION) {
	        return;
	    }

	    String resultado = miCoordinador.eliminarPersonaConMascotas(documento);
	    JOptionPane.showMessageDialog(this, resultado);
	}

	



	private void limpiar() {		
		textNombre.setText("");
		textRaza.setText("");
		textSexo.setText("");
		
	}


	private void actualizarMascota() {
		
		if(textIdDUEÑO == null) {
			JOptionPane.showMessageDialog(this, "ingrese informacion en los campos");
			return;

		}
		
		
		MascotaVo  mascota = new MascotaVo();
		
		mascota.setDocumentoPersona(textIdDUEÑO.getText().trim());

		mascota.setNombreMasc(textNombre.getText().trim());
		mascota.setRaza(textRaza.getText().trim());
		mascota.setSexo(textSexo.getText().trim());
		
		String mensaje = miCoordinador.actualizarMascota(mascota);
		 textAreaResultadoMasc.append(mensaje + "\n");
		
		
	}


	private void consultarMascota() {
	    String documentoMasc = textIdDUEÑO.getText();

	    if (documentoMasc.isEmpty()) {
	        JOptionPane.showMessageDialog(this, "Por favor ingrese un documento");
	        return;
	    }

	    MascotaVo mascota = miCoordinador.consultarMascota(documentoMasc);

	    if (mascota != null) {
	        textAreaResultadoMasc.setText("Datos de la mascota: \n");
	        textAreaResultadoMasc.append("Dueño: " + mascota.getNombreDueno() + "\n");
	        textAreaResultadoMasc.append("Nombre: " + mascota.getNombreMasc() + "\n");
	        textAreaResultadoMasc.append("Raza: " + mascota.getRaza() + "\n");
	        textAreaResultadoMasc.append("Sexo: " + mascota.getSexo());
	        
	        textIdDUEÑO.setText(documentoMasc);
	        textNombre.setText(mascota.getNombreMasc());
	        textRaza.setText(mascota.getRaza());
	        textSexo.setText(mascota.getSexo());
	       

	    } else {
	        textAreaResultadoMasc.setText("Mascota no encontrada");
	    }
	}

	
	

	private void registrarMascota() {
		if(textNombre.getText().trim().isEmpty() || textRaza.getText().trim().isEmpty() || 
			textIdDUEÑO.getText().trim().isEmpty() || textSexo.getText().trim().isEmpty()) {
			   
		    JOptionPane.showMessageDialog(this, "Todos los campos son obligatorios");
		    return;
	}
		
		MascotaVo mascota = new MascotaVo();
		
		mascota.setNombreMasc(textNombre.getText());
		mascota.setRaza(textRaza.getText());
		mascota.setSexo(textSexo.getText());
		mascota.setDocumentoPersona(textIdDUEÑO.getText());
	
		String mensaje = miCoordinador.registrarMascota(mascota);
		 textAreaResultadoMasc.append(mensaje + "\n");
		
	}

	

}
