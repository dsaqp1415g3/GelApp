package edu.upc.eetac.dsa.dsaqp1415g3.gelapp.api;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import javax.sql.DataSource;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.ServerErrorException;
import javax.ws.rs.core.CacheControl;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.EntityTag;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;

import edu.upc.eetac.dsa.dsaqp1415g3.gelapp.api.model.Sabor;
import edu.upc.eetac.dsa.dsaqp1415g3.gelapp.api.model.SaboresCollection;
import edu.upc.eetac.dsa.dsaqp1415g3.gelapp.api.MediaType;
import edu.upc.eetac.dsa.dsaqp1415g3.gelapp.api.DataSourceSPA;


@Path("/sabores")
public class SaboresResource {
	private DataSource ds = DataSourceSPA.getInstance().getDataSource();
	
	
	private String GET_SABORES_QUERY = "select * from sabor";
	 
	@GET
	@Produces(MediaType.GELAPP_API_SABOR_COLLECTION)
	public SaboresCollection getSabores() {
		SaboresCollection sabores = new SaboresCollection();
	 
		Connection conn = null;
		try {
			conn = ds.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	 
		PreparedStatement stmt = null;
		try {
			stmt = conn.prepareStatement(GET_SABORES_QUERY);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Sabor sabor = new Sabor();
				sabor.setSaborid(rs.getInt("sabor_id"));
				sabor.setName(rs.getString("nombre"));
				sabor.setCode_color(rs.getString("codigo_color"));
				sabor.setLastModified(rs.getTimestamp("last_modified").getTime());
				sabor.setCreationTimestamp(rs.getTimestamp("creation_timestamp").getTime());
				sabores.addSabor(sabor);
			}
		} catch (SQLException e) {
			e.printStackTrace();
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
	
	

	/*
	private String GET_SABORES_QUERY = "select * from sabor where creation_timestamp < ifnull(?, now())  order by creation_timestamp desc limit ?";
	private String GET_SABORES_QUERY_FROM_LAST = "select * from sabor where creation_timestamp > ? order by creation_timestamp desc";
			
	@GET
	@Produces(MediaType.GELAPP_API_SABOR_COLLECTION)
	public SaboresCollection getSabores(@QueryParam("length") int length,
			@QueryParam("before") long before, @QueryParam("after") long after) {
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
			boolean updateFromLast = after > 0;
			stmt = updateFromLast ? conn
					.prepareStatement(GET_SABORES_QUERY_FROM_LAST) : conn
					.prepareStatement(GET_SABORES_QUERY);
			if (updateFromLast) {
				stmt.setTimestamp(1, new Timestamp(after));
			} else {
				if (before > 0)
					stmt.setTimestamp(1, new Timestamp(before));
				else
					stmt.setTimestamp(1, null);
				length = (length <= 0) ? 5 : length;
				stmt.setInt(2, length);
			}
			ResultSet rs = stmt.executeQuery();
			boolean first = true;
			long oldestTimestamp = 0;
			while (rs.next()) {
				Sabores sabor = new Sabores();
				sabor.setSaborid(rs.getInt("sabor_id"));
				sabor.setName(rs.getString("nombre"));
				sabor.setCode_color(rs.getString("codigo_color"));
				sabor.setLastModified(rs.getTimestamp("last_modified").getTime());
				sabor.setCreationTimestamp(rs.getTimestamp("creation_timestamp").getTime());
				if (first) {
					first = false;
					sabores.setNewestTimestamp(sabor.getCreationTimestamp());
				}
				sabores.addSabor(sabor);
			}
			sabores.setOldestTimestamp(oldestTimestamp);
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
	}*/
	
	private String GET_SABOR_BY_ID_QUERY ="select * from sabor where sabor_id =?";
	@GET
	@Path("/{sabor_id}")
	@Produces(MediaType.GELAPP_API_SABOR)
	
	public Response getSabor(@PathParam("sabor_id") String saborid,
			@Context Request request) {
		// Create CacheControl
		CacheControl cc = new CacheControl();

		Sabor sabor= getSaborFromDatabase(saborid);

		// Calculate the ETag on last modified date of user resource
		EntityTag eTag = new EntityTag(Long.toString(sabor.getLastModified()));

		// Verify if it matched with etag available in http request
		Response.ResponseBuilder rb = request.evaluatePreconditions(eTag);

		// If ETag matches the rb will be non-null;
		// Use the rb to return the response without any further processing
		if (rb != null) {
			return rb.cacheControl(cc).tag(eTag).build();
		}

		// If rb is null then either it is first time request; or resource is
		// modified
		// Get the updated representation and return with Etag attached to it
		rb = Response.ok(sabor).cacheControl(cc).tag(eTag);

		return rb.build();
	}
	/*public Sabores getSabor(@PathParam("sabor_id") String saborid) {
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
	}*/
	
	private Sabor getSaborFromDatabase(String saborid) {
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
				sabor.setSaborid(rs.getInt("sabor_id"));
				sabor.setName(rs.getString("nombre"));
				sabor.setCode_color(rs.getString("codigo_color"));
				sabor.setLastModified(rs.getTimestamp("last_modified").getTime());
				sabor.setCreationTimestamp(rs.getTimestamp("creation_timestamp").getTime());
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
}

