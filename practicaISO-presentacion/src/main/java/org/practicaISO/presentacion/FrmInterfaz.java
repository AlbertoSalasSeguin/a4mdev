package org.practicaISO.presentacion;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JSplitPane;
import javax.swing.JScrollPane;
import java.awt.Dimension;
import java.awt.CardLayout;
import javax.swing.JTree;
import javax.swing.tree.DefaultTreeModel;

import org.practicaISO.dominio.Cliente;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.event.TreeSelectionListener;
import javax.swing.event.TreeSelectionEvent;
import java.awt.Color;
import java.awt.Font;

public class FrmInterfaz extends JFrame {
	
	private final JPanel contentPane;
	private final JSplitPane splitPane;
	private final JScrollPane scrollPane;
	private final JPanel panel;
	private final JTree tree;
	private final JPanel miPanelComprar;
	private final JPanel miPanelCanciones;
	private final JPanel miPanelArtistas;
	private final JPanel miPanelAlbumes;
	private final JPanel miPanelInfo;

	/**
	 * Launch the application.
	 */
	public FrmInterfaz(final Cliente cliente) {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1157, 634);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		{
			splitPane = new JSplitPane();
			contentPane.add(splitPane, BorderLayout.CENTER);
			{
				scrollPane = new JScrollPane();
				scrollPane.setMinimumSize(new Dimension(200, 27));
				splitPane.setLeftComponent(scrollPane);
				{
					tree = new JTree();
					tree.setModel(new DefaultTreeModel(new DefaultMutableTreeNode("Menú") {
						{
							DefaultMutableTreeNode node_1;
							node_1 = new DefaultMutableTreeNode("Biblioteca");
							node_1.add(new DefaultMutableTreeNode("Canciones"));
							node_1.add(new DefaultMutableTreeNode("Artistas"));
							node_1.add(new DefaultMutableTreeNode("Álbumes"));
							add(node_1);
							node_1 = new DefaultMutableTreeNode("Mi cuenta");
							node_1.add(new DefaultMutableTreeNode("Información de mi cuenta"));
							node_1.add(new DefaultMutableTreeNode("Comprar canciones"));
							add(node_1);
						}
					}));
					tree.setFont(new Font("Tahoma", Font.BOLD, 18));
					tree.setForeground(new Color(102, 153, 255));
					tree.setBackground(Color.WHITE);
					tree.addTreeSelectionListener(new TreeTreeSelectionListener());
					/*
					 * tree.setModel(new DefaultTreeModel( new
					 * DefaultMutableTreeNode("Menu principal") { { DefaultMutableTreeNode node_1;
					 * node_1 = new DefaultMutableTreeNode("Biblioteca"); node_1.add(new
					 * DefaultMutableTreeNode("Canciones")); node_1.add(new
					 * DefaultMutableTreeNode("Artistas")); node_1.add(new
					 * DefaultMutableTreeNode("Álbumes")); add(node_1); node_1 = new
					 * DefaultMutableTreeNode("Mi cuenta"); node_1.add(new
					 * DefaultMutableTreeNode("Información de mi cuenta")); node_1.add(new
					 * DefaultMutableTreeNode("Comprar canciones")); add(node_1); } } ));
					 */
					scrollPane.setViewportView(tree);
				}
				// Menú
				// Biblioteca
				// Canciones
				// Artistas
				// Álbumes
				// Mi cuenta
				// Información de mi cuenta
				// Comprar canciones
			}
			{
				panel = new JPanel();
				splitPane.setRightComponent(panel);
				panel.setLayout(new CardLayout(0, 0));
				{
					miPanelComprar = new MiPanelComprar(cliente);
					panel.add(miPanelComprar, "Comprar canciones");
				}
				{
					miPanelCanciones = new MiPanelCanciones(cliente);
					panel.add(miPanelCanciones, "Canciones");
				}
				{
					miPanelArtistas = new MiPanelArtistas(cliente);
					panel.add(miPanelArtistas, "Artistas");
				}
				{
					miPanelAlbumes = new MiPanelAlbumes(cliente);
					panel.add(miPanelAlbumes, "Álbumes");
				}
				{
					miPanelInfo = new MiPanelInfo(cliente);
					panel.add(miPanelInfo, "Información de mi cuenta");
				}
			}
		}
	}

	private class TreeTreeSelectionListener implements TreeSelectionListener {
		public void valueChanged(TreeSelectionEvent e) {
			System.out.println("Nodo seleccionado " + e.getPath().getLastPathComponent());
			final String nodo = e.getPath().getLastPathComponent().toString();
			if (nodo.equals("Canciones") || nodo.equals("Artistas") || nodo.equals("Álbumes")
					|| nodo.equals("Información de mi cuenta") || nodo.equals("Comprar canciones")) {

				((CardLayout) panel.getLayout()).show(panel, nodo);
			}
		}
	}
}
