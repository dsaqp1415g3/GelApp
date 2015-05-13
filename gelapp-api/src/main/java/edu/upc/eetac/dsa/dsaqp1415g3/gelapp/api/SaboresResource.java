package edu.upc.eetac.dsa.dsaqp1415g3.gelapp.api;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.ServerErrorException;
import javax.ws.rs.core.Response;

import edu.upc.eetac.dsa.dsaqp1415g3.gelapp.api.model.Sabores;
import edu.upc.eetac.dsa.dsaqp1415g3.gelapp.api.model.SaboresCollection;
import edu.upc.eetac.dsa.dsaqp1415g3.gelapp.api.MediaType;
import edu.upc.eetac.dsa.dsaqp1415g3.gelapp.api.DataSourceSPA;

@Path("/sabores")
public class SaboresResource {
	private DataSource ds = DataSourceSPA.getInstance().getDataSource();
	
	private String GET_SABORES_QUERY ="select * from sabor";
	
	@GET
	@Produces(MediaType.GELAPP_API_SABOR_COLLECTION)
	public SaboresCollection getSabores() {
		SaboresCollection sabores = new SaboresCollection();

		Connection conn = null;
		try {
			conn = ds.getConnection();
		} catch (SQLException e) {
			throw new ServerErrorException("Could not connect to the database",
					Response.Status.SERVICE_UNAVAILABLE);
		}

		PreparedStatement stmt = null;
		try {			
			stmt = conn.prepareStatement(GET_SABORES_QUERY);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Sabores sabor = new Sabores();
				sabor.setSaborid(rs.getInt("sabor_id"));
				sabor.setName(rs.getString("nombre"));
				sabor.setCode_color(rs.getString("codigo_color"));			
				
				sabores.addSabor(sabor);
			}			
		} catch (SQLException e) {
			throw new ServerErrorException(e.getMessage(),
					Response.Status.INTERNAL_SERVER_ERROR);
		} finally {
			try {
				if (stmt != null)
					stmt.close();
				conn.close();
			} catch (SQLException e) {
			}
		}

		return sabores;
	}
	
	private String GET_SABOR_BY_ID_QUERY ="select * from sabor where sabor_id =?";
	@GET
	@Path("/{sabor_id}")
	@Produces(MediaType.GELAPP_API_SABOR)
	
	public Sabores getSabor(@PathParam("sabor_id") String saborid) {
		Sabores sabor = new Sabores();

		Connection conn = null;
		try {
			conn = ds.getConnection();
		} catch (SQLException e) {
			throw new ServerErrorException("Could not connect to the database",
					Response.Status.SERVICE_UNAVAILABLE);
		}

		PreparedStatement stmt = null;
		try {
			stmt = conn.prepareStatement(GET_SABOR_BY_ID_QUERY);
			stmt.setInt(1, Integer.valueOf(saborid));
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				sabor.setSaborid(rs.getInt("sabor_id"));
				sabor.setName(rs.getString("nombre"));
				sabor.setCode_color(rs.getString("codigo_color"));
			} else {
				throw new NotFoundException("There's no sting with saborid="
						+ saborid);
			}
		} catch (SQLException e) {
			throw new ServerErrorException(e.getMessage(),
					Response.Status.INTERNAL_SERVER_ERROR);
		} finally {
			try {
				if (stmt != null)
					stmt.close();
				conn.close();
			} catch (SQLException e) {
			}
		}

		return sabor;
	}
	
	/*private Sabor getSaborFromDatabase(String saborid) {
		Sabor sabor = new Sabor();

		Connection conn = null;
		try {
			conn = ds.getConnection();
		} catch (SQLException e) {
			throw new ServerErrorException("Could not connect to the database",
					Response.Status.SERVICE_UNAVAILABLE);
		}

		PreparedStatement stmt = null;
		try {
			stmt = conn.prepareStatement(GET_SABOR_BY_ID_QUERY);
			stmt.setInt(1, Integer.valueOf(saborid));
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				sabor.setSaborid(rs.getInt("saborid"));
				sabor.setName(rs.getString("nombre"));
				sabor.setCode_color(rs.getString("codigo_color"));
			} else {
				throw new NotFoundException("There's no sting with stingid="
						+ saborid);
			}
		} catch (SQLException e) {
			throw new ServerErrorException(e.getMessage(),
					Response.Status.INTERNAL_SERVER_ERROR);
		} finally {
			try {
				if (stmt != null)
					stmt.close();
				conn.close();
			} catch (SQLException e) {
			}
		}

		return sabor;
	}*/
}

