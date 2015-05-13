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

import edu.upc.eetac.dsa.dsaqp1415g3.gelapp.api.model.Helado;
import edu.upc.eetac.dsa.dsaqp1415g3.gelapp.api.model.HeladoCollection;


@Path("/helados")
public class HeladoResource {
	private DataSource ds = DataSourceSPA.getInstance().getDataSource();

	private String GET_HELADOS_QUERY = "select * from helado where creation_timestamp < ifnull(?, now())  order by creation_timestamp desc limit ?";
	private String GET_HELADOS_QUERY_FROM_LAST = "select * from helado where creation_timestamp > ? order by creation_timestamp desc";
			
	@GET
	@Produces(MediaType.GELAPP_API_HELADO_COLLECTION)
	public HeladoCollection getStings(@QueryParam("length") int length,
			@QueryParam("before") long before, @QueryParam("after") long after) {
		HeladoCollection helados = new HeladoCollection();
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
					.prepareStatement(GET_HELADOS_QUERY_FROM_LAST) : conn
					.prepareStatement(GET_HELADOS_QUERY);
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
				Helado helado = new Helado();
				helado.setHeladoid(rs.getInt("helado_id"));
				helado.setCapa1Topping(rs.getString("capa_1_topping"));
				helado.setCapa2Helado(rs.getString("capa_2_helado"));
				helado.setCapa3Topping(rs.getString("capa_3_topping"));
				helado.setCapa4Helado(rs.getString("capa_4_helado"));
				helado.setCapa5Topping(rs.getString("capa_5_topping"));
				helado.setLastModified(rs.getTimestamp("last_modified").getTime());
				helado.setCreationTimestamp(rs.getTimestamp("creation_timestamp").getTime());
				if (first) {
					first = false;
					helados.setNewestTimestamp(helado.getCreationTimestamp());
				}
				helados.addHelado(helado);
			}
			helados.setOldestTimestamp(oldestTimestamp);
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

			return helados;
	}
	
	private String GET_HELADO_BY_ID_QUERY ="select * from helado where helado_id =?";
	@GET
	@Path("/{helado_id}")
	@Produces(MediaType.GELAPP_API_HELADO)
	public Response getSting(@PathParam("helado_id") String stingid,
			@Context Request request) {
		// Create CacheControl
		CacheControl cc = new CacheControl();

		Helado helado = getHeladoFromDatabase(stingid);

		// Calculate the ETag on last modified date of user resource
		EntityTag eTag = new EntityTag(Long.toString(helado.getLastModified()));

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
		rb = Response.ok(helado).cacheControl(cc).tag(eTag);

		return rb.build();
	}
	
	private Helado getHeladoFromDatabase(String heladoid) {
		Helado helado = new Helado();

		Connection conn = null;
		try {
			conn = ds.getConnection();
		} catch (SQLException e) {
			throw new ServerErrorException("Could not connect to the database",
					Response.Status.SERVICE_UNAVAILABLE);
		}

		PreparedStatement stmt = null;
		try {
			stmt = conn.prepareStatement(GET_HELADO_BY_ID_QUERY);
			stmt.setInt(1, Integer.valueOf(heladoid));
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				helado.setHeladoid(rs.getInt("helado_id"));
				helado.setCapa1Topping(rs.getString("capa_1_topping"));
				helado.setCapa2Helado(rs.getString("capa_2_helado"));
				helado.setCapa3Topping(rs.getString("capa_3_topping"));
				helado.setCapa4Helado(rs.getString("capa_4_helado"));
				helado.setCapa5Topping(rs.getString("capa_5_topping"));
				helado.setLastModified(rs.getTimestamp("last_modified").getTime());
				helado.setCreationTimestamp(rs.getTimestamp("creation_timestamp").getTime());
			} else {
				throw new NotFoundException("There's no sting with stingid="
						+ heladoid);
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

		return helado;
	}
}
