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
	
	/*			LISTA HELADOS		*/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	//private String GET_HELADOS_QUERY = "select * from helado where creation_timestamp < ifnull(?, now())  order by creation_timestamp desc limit ?";
	//private String GET_HELADOS_QUERY_FROM_LAST = "select * from helado where creation_timestamp > ? order by creation_timestamp desc";
	private String GET_HELADOS_QUERY = "select * from helado order by helado_id asc limit 5 offset ?";
	
	
	@GET
	@Produces(MediaType.GELAPP_API_HELADO_COLLECTION)
	public HeladoCollection getHelados(@QueryParam ("page") int page) {
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
			stmt = conn.prepareStatement(GET_HELADOS_QUERY);
			stmt.setInt(1, page);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				while (rs.next()) {
					Helado helado = new Helado();
					helado.setHeladoid(rs.getInt("helado_id"));
					helado.setAutorid(rs.getInt("autor_id"));
					helado.setNombreHelado(rs.getString("nombre_helado"));
					helado.setCapa1Topping(rs.getString("capa_1_topping"));
					helado.setCapa2Helado(rs.getString("capa_2_helado"));
					helado.setCapa3Topping(rs.getString("capa_3_topping"));
					helado.setCapa4Helado(rs.getString("capa_4_helado"));
					helado.setCapa5Topping(rs.getString("capa_5_topping"));
					helado.setLastModified(rs.getTimestamp("last_modified").getTime());
					helado.setCreationTimestamp(rs.getTimestamp("creation_timestamp").getTime());
				
					helados.addHelado(helado);
				}
				helados.setNextPage(page + 10);
				helados.setPreviousPage(page - 10);
			} else {
				throw new NotFoundException("There's no helado");
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
		/*try {
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
				length = (length <= 0) ? 30 : length;
				stmt.setInt(2, length);
			}
			ResultSet rs = stmt.executeQuery();
			boolean first = true;
			long oldestTimestamp = 0;
			while (rs.next()) {
				Helado helado = new Helado();
				helado.setHeladoid(rs.getInt("helado_id"));
				helado.setAutorid(rs.getInt("autor_id"));
				helado.setNombreHelado(rs.getString("nombre_helado"));
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
		}*/

			return helados;
	}
	
	
	/*    HELADO POR ID 			*//////////////////////////////////////////////////////////////////////////
	
	private String GET_HELADO_BY_ID_QUERY ="select * from helado where helado_id =?";
	@GET
	@Path("/{helado_id}")
	@Produces(MediaType.GELAPP_API_HELADO)
	public Response getHelado(@PathParam("helado_id") String heladoid,
			@Context Request request) {
		// Create CacheControl
		CacheControl cc = new CacheControl();

		Helado helado = getHeladoFromDatabase(heladoid);

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
	
	
	/*		HELADO POR NOMBRE AUTOR		*////////////////////////////////////////////////////////////////////////////////////////////////
	
	private String GET_HELADO_BY_AUTOR_QUERY ="select * from helado where autor_id = (select usuario_id from usuario where username = ? ) order by helado_id asc";
	@GET
	@Path("/{username}")
	@Produces(MediaType.GELAPP_API_HELADO_COLLECTION)
		public HeladoCollection getHelados(@PathParam("username") String username) {
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
			stmt = conn.prepareStatement(GET_HELADO_BY_AUTOR_QUERY);
			stmt.setString(1, String.valueOf(username));
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				while (rs.next()) {
					Helado helado = new Helado();
					helado.setHeladoid(rs.getInt("helado_id"));
					helado.setAutorid(rs.getInt("autor_id"));
					helado.setNombreHelado(rs.getString("nombre_helado"));
					helado.setCapa1Topping(rs.getString("capa_1_topping"));
					helado.setCapa2Helado(rs.getString("capa_2_helado"));
					helado.setCapa3Topping(rs.getString("capa_3_topping"));
					helado.setCapa4Helado(rs.getString("capa_4_helado"));
					helado.setCapa5Topping(rs.getString("capa_5_topping"));
					helado.setLastModified(rs.getTimestamp("last_modified").getTime());
					helado.setCreationTimestamp(rs.getTimestamp("creation_timestamp").getTime());
				
					helados.addHelado(helado);
				}
			} else {
				throw new NotFoundException("There's no helado");
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
			return helados;
	}
	
	/*			INSERTAR HELADO		*//////////////////////////////////////////////////////////////////////////////////////
	
	/*private String INSERT_STING_QUERY = "insert into helado (autor_id, nombre_helado,capa_1_topping,capa_2_helado,capa_3_topping,capa_4_helado,capa_5_topping) values (?, ?, ?)";

	@POST
	@Consumes(MediaType.GELAPP_API_HELADO)
	@Produces(MediaType.GELAPP_API_HELADO)
	public Helado createSting(Helado helado) {
		Connection conn = null;
		validateHelado(helado);
		try {
			conn = ds.getConnection();
		} catch (SQLException e) {
			throw new ServerErrorException("Could not connect to the database",
					Response.Status.SERVICE_UNAVAILABLE);
		}

		PreparedStatement stmt = null;
		try {
			stmt = conn.prepareStatement(INSERT_STING_QUERY,
					Statement.RETURN_GENERATED_KEYS);

			stmt.setInt(1, helado.getAutorid());
			//stmt.setString(1, security.getUserPrincipal().getName());
			stmt.setString(2, helado.getNombreHelado());
			stmt.setString(3, helado.getCapa1Topping());
			stmt.setString(4, helado.getCapa2Helado());
			stmt.setString(5, helado.getCapa3Topping());
			stmt.setString(6, helado.getCapa4Helado());
			stmt.setString(7, helado.getCapa5Topping());
			stmt.executeUpdate();
			ResultSet rs = stmt.getGeneratedKeys();
			if (rs.next()) {
				int stingid = rs.getInt(1);

				helado = getHeladoFromDatabase(Integer.toString(stingid));
			} else {
				// Something has failed...
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
	
	private void validateHelado(Helado helado) {
		if (helado.getAutorid() == 0)
			throw new BadRequestException("Autorid can't be null.");
		if (helado.getNombreHelado() == null)
			throw new BadRequestException("NombreHelado can't be null.");
		if (helado.getCapa1Topping() == null)
			throw new BadRequestException("Capa1 can't be null");
		if (helado.getCapa2Helado() == null)
			throw new BadRequestException("Capa2 can't be null");
		if (helado.getCapa3Topping() == null)
			throw new BadRequestException("Capa3 can't be null");
		if (helado.getCapa4Helado() == null)
			throw new BadRequestException("Capa4 can't be null");
		if (helado.getCapa5Topping() == null)
			throw new BadRequestException("Capa5 can't be null");
		
	}*/
	
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
				helado.setAutorid(rs.getInt("autor_id"));
				helado.setNombreHelado(rs.getString("nombre_helado"));
				helado.setCapa1Topping(rs.getString("capa_1_topping"));
				helado.setCapa2Helado(rs.getString("capa_2_helado"));
				helado.setCapa3Topping(rs.getString("capa_3_topping"));
				helado.setCapa4Helado(rs.getString("capa_4_helado"));
				helado.setCapa5Topping(rs.getString("capa_5_topping"));
				helado.setLastModified(rs.getTimestamp("last_modified").getTime());
				helado.setCreationTimestamp(rs.getTimestamp("creation_timestamp").getTime());
			} else {
				throw new NotFoundException("There's no helado with stingid="
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
	
	
	/*			FUNCION QUE PERMITE OBTENER LOS HELADOS DE LA BD A PARTIR DEL AUTOR		*////////////////////////////////////////////////////////////////
	/*private Helado getHeladoFromDatabaseByAutor(String username) {
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
			stmt = conn.prepareStatement(GET_HELADO_BY_AUTOR_QUERY);
			stmt.setString(1, String.valueOf(username));
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				helado.setHeladoid(rs.getInt("helado_id"));
				helado.setAutorid(rs.getInt("autor_id"));
				helado.setNombreHelado(rs.getString("nombre_helado"));
				helado.setCapa1Topping(rs.getString("capa_1_topping"));
				helado.setCapa2Helado(rs.getString("capa_2_helado"));
				helado.setCapa3Topping(rs.getString("capa_3_topping"));
				helado.setCapa4Helado(rs.getString("capa_4_helado"));
				helado.setCapa5Topping(rs.getString("capa_5_topping"));
				helado.setLastModified(rs.getTimestamp("last_modified").getTime());
				helado.setCreationTimestamp(rs.getTimestamp("creation_timestamp").getTime());
			} else {
				throw new NotFoundException("There's no helado with autor="
						+ username);
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
	}*/
}
