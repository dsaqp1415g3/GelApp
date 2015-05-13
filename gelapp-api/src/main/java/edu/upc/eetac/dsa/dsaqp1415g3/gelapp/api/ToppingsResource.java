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

import edu.upc.eetac.dsa.dsaqp1415g3.gelapp.api.model.Topping;
import edu.upc.eetac.dsa.dsaqp1415g3.gelapp.api.MediaType;
import edu.upc.eetac.dsa.dsaqp1415g3.gelapp.api.DataSourceSPA;

@Path("/toppings")
public class ToppingsResource {
	private DataSource ds = DataSourceSPA.getInstance().getDataSource();
	
	private String GET_TOPPING_BY_ID_QUERY ="select * from topping where topping_id =?";
	@GET
	@Path("/{topping_id}")
	@Produces(MediaType.GELAPP_API_TOPPING)
	
	public Topping getTopping(@PathParam("topping_id") String toppingid) {
		Topping topping = new Topping();

		Connection conn = null;
		try {
			conn = ds.getConnection();
		} catch (SQLException e) {
			throw new ServerErrorException("Could not connect to the database",
					Response.Status.SERVICE_UNAVAILABLE);
		}

		PreparedStatement stmt = null;
		try {
			stmt = conn.prepareStatement(GET_TOPPING_BY_ID_QUERY);
			stmt.setInt(1, Integer.valueOf(toppingid));
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				topping.setToppingid(rs.getInt("topping_id"));
				topping.setName(rs.getString("nombre"));
				topping.setCode_color(rs.getString("codigo_color"));
			} else {
				throw new NotFoundException("There's no sting with saborid="
						+ toppingid);
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

		return topping;
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