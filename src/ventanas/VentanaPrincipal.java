package ventanas;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import principal.Coordinador;
import javax.swing.JButton;
import javax.swing.JLabel;

public class VentanaPrincipal extends JFrame implements ActionListener{

	private static final long serialVersionUID = 1L;
	private FondoPanel contentPane;
	private Coordinador miCoordinador;
	private JButton btnPersona;
	private JButton btnMascota;

	public void setCoordinador(Coordinador miCoordinador) {
		this.miCoordinador = miCoordinador;
		
	}


	/**
	 * Create the frame.
	 */
	public VentanaPrincipal() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		
		 	setTitle("SISTEMA VETERINARIO ABC");
	        setSize(600, 400);
	        setDefaultCloseOperation(EXIT_ON_CLOSE);
	        setLocationRelativeTo(null);
	        
	        FondoPanel fondo = new FondoPanel();
	        setContentPane(fondo); 
	        fondo.setLayout(null);
		
		
		contentPane = new FondoPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		btnPersona = new JButton("Gestionar Personas");
		btnPersona.setBounds(100, 300, 190, 30);
		contentPane.add(btnPersona);
		btnPersona.addActionListener(this);
		getContentPane().add(btnPersona);
	
		
		btnMascota = new JButton("Gestionar Mascotas");
		btnMascota.setBounds(310, 300, 190, 30);
		contentPane.add(btnMascota);
		btnMascota.addActionListener(this);
		getContentPane().add(btnMascota);
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnPersona) {
			miCoordinador.mostrarVentanaPersona();
		}else  {
			miCoordinador.mostrarVentanaMascotas();
		}
		
	}
}
