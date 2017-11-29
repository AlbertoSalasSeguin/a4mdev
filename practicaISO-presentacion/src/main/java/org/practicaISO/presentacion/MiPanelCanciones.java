package org.practicaISO.presentacion;

import javax.swing.JPanel;

import org.practicaISO.dominio.Cancion;
import org.practicaISO.dominio.Cliente;
import org.practicaISO.persistencia.GestorCancion;
import org.practicaISO.persistencia.GestorCliente;

import javax.swing.JList;
import javax.swing.JOptionPane;

import java.awt.Color;

import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import java.awt.Font;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.awt.SystemColor;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.UIManager;

public class MiPanelCanciones extends JPanel {
	private JList list;
	private JLabel lblEstaEsTu;
	private JButton btnReproductor;
	private JButton btnParar;
	private JLabel lblReproduciendo;

	/**
	 * Create the panel.
	 */
	
	public MiPanelCanciones(Cliente c) {
		setBackground(Color.GRAY);
		setLayout(null);
		{
			list = new JList();
			list.addListSelectionListener(new ListListSelectionListener());
			list.setFont(new Font("Tahoma", Font.PLAIN, 17));
			list.setForeground(SystemColor.control);
			list.setBackground(Color.GRAY);
			list.setBounds(12, 69, 461, 188);
			add(list);
		}
		{
			lblEstaEsTu = new JLabel("Esta es tu lista de canciones");
			lblEstaEsTu.setFont(new Font("Tahoma", Font.BOLD, 21));
			lblEstaEsTu.setForeground(Color.WHITE);
			lblEstaEsTu.setBounds(12, 13, 340, 31);
			add(lblEstaEsTu);
		}
		GestorCliente gc = new GestorCliente();
		GestorCancion gcan= new GestorCancion();
		ArrayList<Cancion> ac=null;
		DefaultListModel<String> modelo = new DefaultListModel<String>();
		try {
			ac=gc.obtenerCanciones(c);		
		} catch (Exception e) {
			System.out.println("Error al obtener canciones del usuario");
		}
		if(ac!=null) {
			for (Cancion canc : ac) {
				canc=gcan.obtenerCancion(canc);
				modelo.addElement(canc.toString());
			}
		}else {
			JOptionPane.showMessageDialog(this, "No hay canciones que mostrar");
		}
		list.setModel(modelo);
		{
			btnReproductor = new JButton("Reproducir");
			btnReproductor.addActionListener(new BtnReproductorActionListener());
			btnReproductor.setEnabled(false);
			btnReproductor.setBounds(12, 278, 104, 31);
			add(btnReproductor);
		}
		{
			btnParar = new JButton("Parar");
			btnParar.addActionListener(new BtnPararActionListener());
			btnParar.setEnabled(false);
			btnParar.setBounds(165, 278, 104, 31);
			add(btnParar);
		}
		{
			lblReproduciendo = new JLabel("");
			lblReproduciendo.setForeground(UIManager.getColor("Button.light"));
			lblReproduciendo.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 17));
			lblReproduciendo.setBounds(12, 325, 521, 31);
			add(lblReproduciendo);
		}
		
	}
	private class BtnPararActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			btnParar.setEnabled(false);
			lblReproduciendo.setText("");
			btnReproductor.setEnabled(true);
		}
	}
	private class ListListSelectionListener implements ListSelectionListener {
		public void valueChanged(ListSelectionEvent e) {
			btnReproductor.setEnabled(true);
			
			
		}
	}
	private class BtnReproductorActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			btnReproductor.setEnabled(false);
			btnParar.setEnabled(true);
			GestorCancion gcan = new GestorCancion();
			String cancion = list.getSelectedValue().toString();
			StringTokenizer token = new StringTokenizer(cancion, "-");
			Cancion canc = new Cancion(Integer.parseInt(token.nextToken()));
			try {
				canc=gcan.obtenerCancion(canc);
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			lblReproduciendo.setText("Reproduciendo "+canc.getTitulo()+" - "+canc.getAutor());
		}
	}
}
