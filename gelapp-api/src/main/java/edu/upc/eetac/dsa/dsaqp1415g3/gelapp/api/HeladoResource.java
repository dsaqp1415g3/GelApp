package edu.upc.eetac.dsa.dsaqp1415g3.gelapp.api;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.ForbiddenException;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.ServerErrorException;
import javax.ws.rs.core.CacheControl;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.EntityTag;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import edu.upc.eetac.dsa.dsaqp1415g3.gelapp.api.model.Helado;
import edu.upc.eetac.dsa.dsaqp1415g3.gelapp.api.model.HeladoCollection;
import edu.upc.eetac.dsa.dsaqp1415g3.gelapp.api.model.User;




@Path("/helados")
public class HeladoResource {
	private DataSource ds = DataSourceSPA.getInstance().getDataSource();
	
	@Context
	private SecurityContext security;
	/*			LISTA HELADOS		*/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	//private String GET_HELADOS_QUERY = "select * from helado order by helado_id asc limit 5 offset ?";
	//private String GET_HELADOS_QUERY = "select * from helado order by helado_id asc";
	private String GET_HELADOS_QUERY = "select helado.*, usuario.username from helado inner join usuario on helado.autor_id = usuario.usuario_id order by helado_id asc";
	//private String GET_COUNT_HELADOS_QUERY = "select count(*) as helado_id from helado";
	
	//@SuppressWarnings("resource")
	@GET
	@Produces(MediaType.GELAPP_API_HELADO_COLLECTION)
	public HeladoCollection getHelados(/*@QueryParam ("page") int page*/) {
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
			//stmt.setInt(1, page);
			ResultSet rs = stmt.executeQuery();		
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
				helado.setAutor(rs.getString("username"));
				helados.addHelado(helado);
			}
				
				/*helados.setNextPage(page + 10);
				helados.setPreviousPage(page - 10);*/
				
				/*stmt = conn.prepareStatement(GET_COUNT_HELADOS_QUERY);
				rs = stmt.executeQuery();
				
				int count = rs.getInt("helado_id");
				
				if (count > page + 10 && page-10 > 0){
					helados.setNextPage(page + 10);
					helados.setPreviousPage(page - 10);
				}else{
					if (count < page + 10){
						helados.setNextPage(count);
						helados.setPreviousPage(page - 10);
					}else{
						helados.setNextPage(page + 10);
						helados.setPreviousPage(0);
					}
				}*/				
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
	
	
	/*    HELADO POR ID 			*//////////////////////////////////////////////////////////////////////////
	
	private String GET_HELADO_BY_ID_QUERY ="select helado.*, usuario.username from helado inner join usuario on helado.autor_id = usuario.usuario_id where helado_id =?";
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
	
	/*		HELADO POR INDICIO NOMBRE HELADO		*////////////////////////////////////////////////////////////////////////
	
private String GET_HELADOS_BY_NAME_HELADO_QUERY ="select helado.*, usuario.username from helado inner join usuario on helado.autor_id = usuario.usuario_id where nombre_helado like ? order by helado_id asc";
	
	@GET
	@Path("/nombre-helado/{nombre}")
	@Produces(MediaType.GELAPP_API_HELADO_COLLECTION)
	public HeladoCollection getHeladoByName(@PathParam ("nombre") String nombre) {
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
			
			stmt = conn.prepareStatement(GET_HELADOS_BY_NAME_HELADO_QUERY);
			String nom = "%"+nombre+"%";
			stmt.setString(1, nom);
			ResultSet rs = stmt.executeQuery();		
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
				helado.setAutor(rs.getString("username"));
				helados.addHelado(helado);
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
	
	/*		BUSCAR POR SABORES			*//////////////////////////////////////////////////////////////////////
	
	private String GET_HELADOS_BY_SABOR_QUERY ="select helado.*, usuario.username from helado inner join usuario on helado.autor_id = usuario.usuario_id where capa_1_topping like ? or capa_2_helado like ? or capa_3_topping like ? or capa_4_helado like ? or capa_5_topping like ? order by helado_id asc";
	
	@GET
	@Path("/missabores/{sabors}")
	@Produces(MediaType.GELAPP_API_HELADO_COLLECTION)
	public HeladoCollection getHeladoBySabor(@PathParam ("sabors") String sabors) {
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
			
			stmt = conn.prepareStatement(GET_HELADOS_BY_SABOR_QUERY);
			String sabor = "%"+sabors+"%";
			stmt.setString(1, sabor);
			stmt.setString(2, sabor);
			stmt.setString(3, sabor);
			stmt.setString(4, sabor);
			stmt.setString(5, sabor);
			ResultSet rs = stmt.executeQuery();		
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
				helado.setAutor(rs.getString("username"));
				helados.addHelado(helado);
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
	
	/*		RANKING HELADOS				*/////////////////////////////////////////////////////////////////////////////////////////////////
	
private String GET_RANKING_HELADOS_QUERY ="select h.*, usuario.username, (select count(id_helado) from votos v where v.id_helado=h.helado_id) votos from helado h inner join usuario on h.autor_id = usuario.usuario_id order by votos desc limit 5";
	
	@GET
	@Path("/ranking")
	@Produces(MediaType.GELAPP_API_HELADO_COLLECTION)
	public HeladoCollection getRankingHelados() {
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
			
			stmt = conn.prepareStatement(GET_RANKING_HELADOS_QUERY);
			ResultSet rs = stmt.executeQuery();		
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
				helado.setAutor(rs.getString("username"));
				helado.setVotos(rs.getInt("votos"));
				helados.addHelado(helado);
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
	
	
	/*		HELADO POR NOMBRE AUTOR		*////////////////////////////////////////////////////////////////////////////////////////////////
	
	private String GET_HELADO_BY_AUTOR_QUERY ="select * from helado where autor_id = (select usuario_id from usuario where username = ? ) order by helado_id asc";
	@GET
	@Path("/user/{username}")
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
	
	private String INSERT_HELADO_QUERY = "insert into helado (autor_id, nombre_helado, capa_1_topping,capa_2_helado, capa_3_topping, capa_4_helado, capa_5_topping) values (?, ?, ?, ?, ?, ?, ?)";

	@POST
	@Consumes(MediaType.GELAPP_API_HELADO)
	@Produces(MediaType.GELAPP_API_HELADO)
	public Helado createHelado(Helado helado) {
		Connection conn = null;
		validateHelado(helado);
		validateAutor(helado.getAutorid());
		try {
			conn = ds.getConnection();
		} catch (SQLException e) {
			throw new ServerErrorException("Could not connect to the database",
					Response.Status.SERVICE_UNAVAILABLE);
		}

		PreparedStatement stmt = null;
		try {
			stmt = conn.prepareStatement(INSERT_HELADO_QUERY,
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
				int heladoid = rs.getInt(1);

				helado = getHeladoFromDatabase(Integer.toString(heladoid));
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
				helado.setAutorid(rs.getInt("autor_id"));
				helado.setNombreHelado(rs.getString("nombre_helado"));
				helado.setCapa1Topping(rs.getString("capa_1_topping"));
				helado.setCapa2Helado(rs.getString("capa_2_helado"));
				helado.setCapa3Topping(rs.getString("capa_3_topping"));
				helado.setCapa4Helado(rs.getString("capa_4_helado"));
				helado.setCapa5Topping(rs.getString("capa_5_topping"));
				helado.setLastModified(rs.getTimestamp("last_modified").getTime());
				helado.setCreationTimestamp(rs.getTimestamp("creation_timestamp").getTime());
				helado.setAutor(rs.getString("username"));
			} else {
				throw new NotFoundException("There's no helado with heladoid="
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
	
	
	/*			ELIMINAR HELADO		*//////////////////////////////////////////////////////////////////////////////////////
	
	private String DELETE_HELADO_QUERY = "delete from helado where helado_id=?";
	 
	@DELETE
	@Path("/{helado_id}")
	public void deleteHelado(@PathParam("helado_id") String helado_id) {
		validateUser(helado_id);
		Connection conn = null;
		try {
			conn = ds.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	 
		PreparedStatement stmt = null;
		try {
			stmt = conn.prepareStatement(DELETE_HELADO_QUERY);
			stmt.setInt(1, Integer.valueOf(helado_id));
			
			int rows = stmt.executeUpdate();
			if (rows == 0)
				;
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
	}
	
	private String GET_USER_HELADO_QUERY = "select * from usuario where usuario_id=?";
	
	private User getUserFromDatabase(Integer userid) {
		User user = new User();

		Connection conn = null;
		try {
			conn = ds.getConnection();
		} catch (SQLException e) {
			throw new ServerErrorException("Could not connect to the database",
					Response.Status.SERVICE_UNAVAILABLE);
		}

		PreparedStatement stmt = null;
		try {
			stmt = conn.prepareStatement(GET_USER_HELADO_QUERY);
			stmt.setInt(1, userid);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				user.setUsuarioid(rs.getInt("usuario_id"));
				user.setUsername(rs.getString("username"));
			} else {
				throw new NotFoundException("There's no username with userid ="
						+ userid);
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

		return user;
	}
	
	private void validateUser(String heladoid) {
	    Helado helado = getHeladoFromDatabase(heladoid);
	    String autor = helado.getAutor();
		if (!security.getUserPrincipal().getName()
				.equals(autor))
			throw new ForbiddenException(
					"You are not allowed to delete this Helado.");
	}
	
	private void validateAutor(Integer autorid) {
	    User user = getUserFromDatabase(autorid);
	    String autor = user.getUsername();	    
		if (!security.getUserPrincipal().getName()
				.equals(autor))
			throw new ForbiddenException(
					"You are not allowed to create this Helado.");
	}
	
}
