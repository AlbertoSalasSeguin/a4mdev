package org.practicaISO.presentacion;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.practicaISO.dominio.Cliente;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JSplitPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.Dimension;
import javax.swing.JScrollPane;
import javax.swing.event.TreeSelectionListener;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.border.TitledBorder;

public class frmInterfazPrincipal extends JFrame {

	private JPanel contentPane;
	private JLabel lblBienvenido;
	private JSplitPane splitPane;
	private JScrollPane scrollPane;
	private JPanel panelCard;
	private JPanel pnlCanciones;
	private JPanel pnlArtistas;
	private JPanel pnlAlnumes;
	private JPanel pnlComprar;
	private JPanel pnlCuenta;
	private JTree tree;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frmInterfazPrincipal frame = new frmInterfazPrincipal(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @param c 
	 */
	public frmInterfazPrincipal(Cliente c) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 827, 521);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		{
			lblBienvenido = new JLabel("Bienvenido "+c.getNombre()+" "+c.getApellidos());
			lblBienvenido.setHorizontalAlignment(SwingConstants.CENTER);
			lblBienvenido.setFont(new Font("Tahoma", Font.BOLD, 20));
			contentPane.add(lblBienvenido, BorderLayout.NORTH);
		}
		{
			splitPane = new JSplitPane();
			contentPane.add(splitPane, BorderLayout.CENTER);
			{
				scrollPane = new JScrollPane();
				scrollPane.setMinimumSize(new Dimension(200, 27));
				splitPane.setLeftComponent(scrollPane);
				{
					tree = new JTree();
					tree.addTreeSelectionListener(new TreeTreeSelectionListener());
					tree.setModel(new DefaultTreeModel(
						new DefaultMutableTreeNode("Canciones") {
							{
							}
						}
					));
					scrollPane.setViewportView(tree);
				}
			}
			{
				panelCard = new JPanel();
				splitPane.setRightComponent(panelCard);
				panelCard.setLayout(null);
				{
					pnlCanciones = new MiPanelCanciones(c);
					pnlCanciones.setBorder(new TitledBorder(null, "Canciones", TitledBorder.LEADING, TitledBorder.TOP, null, null));
					pnlCanciones.setBounds(0, 28, 592, 409);
					panelCard.add(pnlCanciones);
				}
				{
					pnlArtistas = new JPanel();
					pnlArtistas.setBounds(308, 5, 10, 10);
					panelCard.add(pnlArtistas);
				}
				{
					pnlAlnumes = new JPanel();
					pnlAlnumes.setBounds(323, 5, 10, 10);
					panelCard.add(pnlAlnumes);
				}
				{
					pnlComprar = new JPanel();
					pnlComprar.setBounds(338, 5, 10, 10);
					panelCard.add(pnlComprar);
				}
				{
					pnlCuenta = new JPanel();
					pnlCuenta.setBounds(353, 5, 10, 10);
					panelCard.add(pnlCuenta);
				}
			}
		}
	}

	private class TreeTreeSelectionListener implements TreeSelectionListener {
		public void valueChanged(TreeSelectionEvent e) {
			System.out.println("Nodo seleccionado "+e.getPath().getLastPathComponent());
			String nodo = (e.getPath().getLastPathComponent()).toString();
			switch (nodo){
				case "Canciones":
					((CardLayout) panelCard.getLayout()).show(panelCard, nodo);
					System.out.println("hola");
			}
		}
	}
}

