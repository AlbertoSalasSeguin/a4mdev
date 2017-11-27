package org.practicaISO.presentacion;

import javax.swing.JPanel;

import org.practicaISO.dominio.Cancion;
import org.practicaISO.persistencia.GestorCancion;

import java.util.ArrayList;

import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.AbstractListModel;
import javax.swing.DefaultListModel;

import java.awt.Dimension;
import javax.swing.JLabel;

public class MiPanelCanciones extends JPanel {
	private JList list;

	/**
	 * Create the panel.
	 */
	public MiPanelCanciones() {
		setLayout(null);
		{
			list = new JList();
			list.setBounds(0, 0, 587, 413);
			add(list);
		}
		{
			GestorCancion gc = new GestorCancion();
			ArrayList<Cancion> ac=null;
			DefaultListModel<String> modelo = new DefaultListModel<String>();
			try {
				ac=gc.leerCanciones();		
			} catch (Exception e) {
				System.out.println("Error ");
			}
			if(ac!=null) {
				for (Cancion canc : ac) {
					modelo.addElement(canc.toString());
				}
			}else {
				JOptionPane.showMessageDialog(this, "No hay canciones que mostrar");
			}
			list.setModel(modelo);
		
		}

	}
}
