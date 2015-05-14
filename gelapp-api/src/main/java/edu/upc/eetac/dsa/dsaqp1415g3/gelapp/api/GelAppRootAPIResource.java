package edu.upc.eetac.dsa.dsaqp1415g3.gelapp.api;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

import edu.upc.eetac.dsa.dsaqp1415g3.gelapp.api.model.GelAppRootAPI;

@Path("/")
public class GelAppRootAPIResource {
	@GET
	public GelAppRootAPI getRootAPI() {
		GelAppRootAPI api = new GelAppRootAPI();
		return api;
	}
}
