package edu.upc.eetac.dsa.dsaqp1415g3.gelapp.api;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.sql.DataSource;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.ServerErrorException;
import javax.ws.rs.core.Response;

import edu.upc.eetac.dsa.dsaqp1415g3.gelapp.api.model.Votos;

@Path("/votos")
public class VotosResource {
	
	private DataSource ds = DataSourceSPA.getInstance().getDataSource();
	
	private String INSERT_VOTO_QUERY = "insert into votos (id_usuario, id_helado) values (?, ?)";

	@POST
	@Consumes(MediaType.GELAPP_API_VOTOS)
	@Produces(MediaType.GELAPP_API_VOTOS)
	public Votos AñadirVoto(Votos voto) {
		Connection conn = null;		
		validateVoto(voto);
		try {
			conn = ds.getConnection();
		} catch (SQLException e) {
			throw new ServerErrorException("Could not connect to the database",
					Response.Status.SERVICE_UNAVAILABLE);
		}

		PreparedStatement stmt = null;
		try {
			stmt = conn.prepareStatement(INSERT_VOTO_QUERY);

			stmt.setInt(1, voto.getId_usuario());

			//stmt.setString(1, security.getUserPrincipal().getName());
			stmt.setInt(2, voto.getId_helado());
			stmt.executeUpdate();
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

		return voto;
	}
	
	private void validateVoto(Votos voto) {
		if (voto.getId_usuario() == 0)
			throw new BadRequestException("Usuarioid can't be null.");
		if (voto.getId_helado() == 0)
			throw new BadRequestException("NombreHelado can't be null.");
	}
}
