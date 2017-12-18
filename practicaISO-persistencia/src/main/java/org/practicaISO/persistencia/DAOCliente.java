package org.practicaISO.persistencia;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import org.practicaISO.dominio.Cancion;
import org.practicaISO.dominio.Cliente;


public class DAOCliente {
	private Cliente cliente;
	private Connection con;
	private PreparedStatement pst;
	private ResultSet rs;
	private Statement st;

	public Cliente logearCliente(Cliente client) {

		try {
			con=Agente.getConexion();
			String sql = "select*from tb_cliente where nick = ? and pass = ? ";
			pst=con.prepareStatement(sql);
			pst.setString(1, client.getNick());
			pst.setString(2, client.getPass());
			rs=pst.executeQuery();

			while(rs.next()) {
				cliente= new Cliente (rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),
						rs.getString(5),rs.getString(6),rs.getString(7));

			}
		} catch (Exception e) {
			System.out.println("Error al obtener el cliente");
		}

		return cliente;
	}

	public Cliente obtenerCliente(Cliente client) {

		try {
			con=Agente.getConexion();
			String sql = "select*from tb_cliente where nick = ?";
			pst=con.prepareStatement(sql);
			pst.setString(1, client.getNick());
			rs=pst.executeQuery();

			while(rs.next()) {
				cliente= new Cliente (rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),
						rs.getString(5),rs.getString(6),rs.getString(7));

			}
		} catch (Exception e) {
			System.out.println("Error al obtener el cliente");
		}

		return cliente;
	}
	
	public ArrayList<Cancion> obtenerCanciones(Cliente client) {
		ArrayList<Cancion> ac = new ArrayList<Cancion>();
		try {
			con=Agente.getConexion();
			String sql = "select canciones from tb_playlist where nick = ?";
			pst=con.prepareStatement(sql);
			pst.setString(1, client.getNick());

			rs=pst.executeQuery();
			while(rs.next()) {
				ac.add(new Cancion(rs.getInt(1)));
			}
			client.setListaCanciones(ac);
		} catch (Exception e) {
			System.out.println("Error al cargar playlist del usuario");
		}
		return ac;

	}

	public void insertarCliente(Cliente client) {
		try {

			con=Agente.getConexion();
			st=con.createStatement();
			String sql = "insert into tb_cliente values(?,?,?,?,?,?,?)";
			pst=con.prepareStatement(sql);
			pst.setString(1, client.getNick());
			pst.setString(2, client.getPass());
			pst.setString(3, client.getEmail());
			pst.setString(4, client.getNombre());
			pst.setString(5, client.getApellidos());
			pst.setString(6, client.getSuscripcion());
			pst.setString(7, client.getRol());
			pst.executeUpdate();
		} catch (Exception e) {
			System.out.println("Error al insertar cliente en la base de datos");
		}

	}

	public void actualizarCliente(Cliente client) {
		try {
			con=Agente.getConexion();
			st=con.createStatement();
			String sql = "update tb_cliente set pass = ?, email = ?, nombre = ?, apellidos = ?, suscripcion = ?, rol = ? where nick = ?";
			pst=con.prepareStatement(sql);
			pst.setString(1, client.getPass());
			pst.setString(2, client.getEmail());
			pst.setString(3, client.getNombre());
			pst.setString(4, client.getApellidos());
			pst.setString(5, client.getSuscripcion());
			pst.setString(6, client.getRol());
			pst.setString(7, client.getNick());
			pst.executeUpdate();
		} catch (Exception e) {
			System.out.println("Error al actualizar cliente en la base de datos");
		}
	}
	
	public void comprarCancion(Cliente client, Cancion canc) {
		try {

			con=Agente.getConexion();
			st=con.createStatement();
			String sql = "insert into tb_playlist values(?,?)";
			pst=con.prepareStatement(sql);
			pst.setString(1, client.getNick());
			pst.setInt(2, canc.getIdCancion());
			pst.executeUpdate();
		} catch (Exception e) {
			System.out.println("Error al comprar canción");
		}

	}
	
	public void eliminarCliente(Cliente client) {
		try {
			con=Agente.getConexion();
			st=con.createStatement();
			String sql = "delete from tb_cliente where nick = '"+client.getNick()+"'";
			pst=con.prepareStatement(sql);
			pst.executeUpdate();
		} catch (Exception e) {
			System.out.println("Error al eliminar cliente en la base de datos");
		}	
	}
}