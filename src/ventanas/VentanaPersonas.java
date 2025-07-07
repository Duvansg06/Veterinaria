package ventanas;

import java.awt.BorderLayout;


import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import principal.Coordinador;
import vo.PersonaVo;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JTextArea;

import procesos.ProcesosGenerales;
import procesos.ProcesosPersona;


public class VentanaPersonas extends JDialog implements ActionListener{

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField textDocumento;
	private JTextField textTelefono;
	private JTextField textNombre;
	private JLabel lbldocumento;
	private JLabel lbltelefono;
	private JButton btnRegistrar;
	private JButton btnActualizar;
	private JButton btnConsultar;
	private JButton btnEliminar;
	private JButton btnConsultarLista;
	private JTextArea textAreaResultados;
	private Coordinador miCoordinador;
	private PersonaVo miPersonaVo;
	private ProcesosGenerales procesosPersona = new ProcesosPersona();

	private JScrollPane scrollPane;
	

	
	public void setCoordinador(Coordinador miCoordinador) {
		this.miCoordinador = miCoordinador;
		
	}
	/**
	 * Create the dialog.
	 */
	public VentanaPersonas() {
		setBounds(100, 100, 617, 416);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblTitulo = new JLabel("GESTIONAR PERSONAS");
			lblTitulo.setBounds(270, 11, 208, 14);
			contentPanel.add(lblTitulo);
		}
		
		textDocumento = new JTextField();
		textDocumento.setBounds(163, 57, 96, 20);
		contentPanel.add(textDocumento);
		textDocumento.setColumns(10);
		
		lbldocumento = new JLabel("Documento");
		lbldocumento.setBounds(74, 60, 79, 14);
		contentPanel.add(lbldocumento);
		
		textTelefono = new JTextField();
		textTelefono.setBounds(455, 57, 96, 20);
		contentPanel.add(textTelefono);
		textTelefono.setColumns(10);
		
		lbltelefono = new JLabel("Telefono");
		lbltelefono.setBounds(384, 60, 79, 14);
		contentPanel.add(lbltelefono);
		
		JLabel lblnombre = new JLabel("Nombre");
		lblnombre.setBounds(74, 91, 96, 14);
		contentPanel.add(lblnombre);
		
		textNombre = new JTextField();
		textNombre.setBounds(173, 88, 365, 20);
		contentPanel.add(textNombre);
		textNombre.setColumns(10);
		
		btnRegistrar = new JButton("Registrar");
		btnRegistrar.setBounds(74, 144, 89, 23);
		contentPanel.add(btnRegistrar);
		btnRegistrar.addActionListener(this);
		
		btnConsultar = new JButton("Consultar");
		btnConsultar.setBounds(204, 144, 103, 23);
		contentPanel.add(btnConsultar);
		btnConsultar.addActionListener(this);
		
		btnActualizar = new JButton("Actualizar");
		btnActualizar.setBounds(344, 144, 103, 23);
		contentPanel.add(btnActualizar);
		btnActualizar.addActionListener(this);
		
		btnEliminar = new JButton("Eliminar");
		btnEliminar.setBounds(477, 144, 89, 23);
		contentPanel.add(btnEliminar);
		btnEliminar.addActionListener(this);
		
		btnConsultarLista = new JButton("Consultar Lista");
		btnConsultarLista.setBounds(163, 178, 315, 23);
		contentPanel.add(btnConsultarLista);
		btnConsultarLista.addActionListener(this);
		
		textAreaResultados = new JTextArea();
		textAreaResultados.setBounds(74, 211, 492, 157);
		contentPanel.add(textAreaResultados);
		
		scrollPane = new JScrollPane(textAreaResultados);
		scrollPane.setBounds(74, 211, 492, 157);
		contentPanel.add(scrollPane);	
		
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnRegistrar) {
			registrarPersona();
			mostrarRegistro();
			limpiar();	
		}else if(e.getSource()== btnConsultar) {
			consultarPersona();			
		}
		
		if(e.getSource() == btnActualizar) {
			actualizarPersona();
			limpiar();
		}
		
		if(e.getSource() == btnEliminar) {
			eliminarPersona();
			limpiar();
		}
		
		if(e.getSource() == btnConsultarLista) {
			consultarLista();
		}
		
	}
	private void consultarLista() {
		List<PersonaVo> lista = miCoordinador.consultarListaPersonas();
		
		if(lista.isEmpty()) {
			JOptionPane.showMessageDialog(this, "No hay personas registradas");
		}else {
			
			StringBuilder resultado = new StringBuilder();
			
			for (PersonaVo persona : lista) {
				
				resultado.append("Documento: ").append(persona.getDocumento()+ "\n")
						.append("Nombre: " ).append(persona.getNombre() + "\n")
						.append("Telefono: " ).append(persona.getTelefono() + "\n")
						.append("**************************" + "\n");
			}
			textAreaResultados.setText(resultado.toString());
		}
	
	}
	
	private void eliminarPersona() {
	    String documento = textDocumento.getText();

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

	
	private void actualizarPersona() {
		
		if(textDocumento == null ) {
			JOptionPane.showMessageDialog(this, "ingrese informacion en los campos");
			return;
		}
	
		PersonaVo persona = new PersonaVo();
		
		persona.setDocumento(textDocumento.getText().trim());
		persona.setNombre(textNombre.getText().trim());
		persona.setTelefono(textTelefono.getText().trim());
		
		String resultado = miCoordinador.actualizarPersona(persona);
		
		textAreaResultados.setText(resultado);
	
	}
	
	
	private void consultarPersona() {
		String documento = textDocumento.getText();
		
		if(documento.isEmpty()) {
			JOptionPane.showMessageDialog(this, "Porfavor ingrese un documento");
			return;
		}
		
		PersonaVo persona = (PersonaVo) procesosPersona.consultar(documento);
		
		if(persona != null) {
			textAreaResultados.setText("Datos de la persona:\n");
			textAreaResultados.append("Documento: " + persona.getDocumento() + "\n");
			textAreaResultados.append("Nombre: " + persona.getNombre() + "\n");
			textAreaResultados.append("Teléfono: " + persona.getTelefono() + "\n");
			textDocumento.setText(documento);
			textNombre.setText(persona.getNombre());
			textTelefono.setText(persona.getTelefono());
			
		}else {
			textAreaResultados.setText("Usuario no encontrado");
		}
		
	}
	private void limpiar() {
		textDocumento.setText("");
		textTelefono.setText("");
		textNombre.setText("");
		
		
	}
	private void mostrarRegistro() {
		String nombre = textNombre.getText();
		String documento = textDocumento.getText();
		String telefono = textTelefono.getText();

		String mensaje = "Persona registrada:\n"
				+ "Nombre: " + nombre + "\n"
				+ "Documento: " + documento + "\n"
				+ "Teléfono: " + telefono + "\n\n";

		textAreaResultados.append(mensaje);
		
	}
	private void registrarPersona() {
		
		
		if (textDocumento.getText().trim().isEmpty() || 
			textNombre.getText().trim().isEmpty() || 
			textTelefono.getText().trim().isEmpty()) {
			    
			    JOptionPane.showMessageDialog(this, "Todos los campos son obligatorios");
			    return;
			   
			}
		
		PersonaVo persona = new PersonaVo();
		persona.setNombre(textNombre.getText());
		persona.setDocumento(textDocumento.getText());
		persona.setTelefono(textTelefono.getText());
		
		String mensaje = procesosPersona.registrar(persona);
		 textAreaResultados.append(mensaje + "\n");
		
		
	}
	
	
	
	

}
