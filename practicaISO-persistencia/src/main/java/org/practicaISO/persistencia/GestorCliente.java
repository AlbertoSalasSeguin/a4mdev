package org.practicaISO.persistencia;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import org.practicaISO.dominio.Cancion;
import org.practicaISO.dominio.Cliente;


public class GestorCliente {
	private Cliente cliente;
	private Connection con;
	private PreparedStatement pst;
	private ResultSet rs;
	private Statement st;

	public Cliente obtenerCliente(Cliente client) {

		try {
			con=MySQLConexion.getConexion();
			String sql = "select*from tb_cliente where nick = ? and pass = ? ";
			pst=con.prepareStatement(sql);
			pst.setString(1, client.getNick());
			pst.setString(2, client.getPass());
			rs=pst.executeQuery();

			while(rs.next()) {
				cliente= new Cliente (rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),
						rs.getString(5),rs.getString(6));

			}
		} catch (Exception e) {
			System.out.println("Error al obtener el cliente");
		}

		return cliente;
	}

	public void obtenerCanciones(Cliente client) {
		try {
			con=MySQLConexion.getConexion();
			String sql = "select canciones from tb_playlist where nick = ?";
			pst=con.prepareStatement(sql);
			pst.setString(1, client.getNick());

			rs=pst.executeQuery();
			ArrayList<Cancion> ac = new ArrayList<Cancion>();
			while(rs.next()) {
				ac.add(new Cancion(rs.getInt(0)));
			}
			client.setListaCanciones(ac);
		} catch (Exception e) {
			System.out.println("Error al cargar playlist del usuario");
		}


	}

	public void insertarCliente(Cliente client) {
		try {

			con=MySQLConexion.getConexion();
			st=con.createStatement();
			String sql = "insert into tb_cliente values(?,?,?,?,?,?)";
			pst=con.prepareStatement(sql);
			pst.setString(1, client.getNick());
			pst.setString(2, client.getPass());
			pst.setString(3, client.getEmail());
			pst.setString(4, client.getNombre());
			pst.setString(5, client.getApellidos());
			pst.setString(6, client.getSuscripcion());
			pst.executeUpdate();
		} catch (Exception e) {
			System.out.println("Error al insertar cliente en la base de datos");
		}

	}

	public void actualizarCliente(Cliente client) {
		try {
			con=MySQLConexion.getConexion();
			st=con.createStatement();
			String sql = "update tb_client set pass = '"+client.getPass()+"', email = '"+client.getEmail()+"', nombre = '"+client.getNombre()+"',"
					+ " apellidos = '"+client.getApellidos()+"', suscripcion = '"+client.getSuscripcion()+" where usuario='"+client.getNick()+"'";
			pst=con.prepareStatement(sql);
			pst.executeUpdate();
		} catch (Exception e) {
			System.out.println("Error al actualizar cliente en la base de datos");
		}
	}
	
	public void eliminarCliente(Cliente client) {
		try {
			con=MySQLConexion.getConexion();
			st=con.createStatement();
			String sql = "delete from tb_client where nick = '"+client.getNick()+"'";
			pst=con.prepareStatement(sql);
			pst.executeUpdate();
		} catch (Exception e) {
			System.out.println("Error al eliminar cliente en la base de datos");
		}	
	}
}