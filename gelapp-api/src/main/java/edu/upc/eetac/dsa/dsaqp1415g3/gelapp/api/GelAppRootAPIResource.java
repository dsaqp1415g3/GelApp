package edu.upc.eetac.dsa.dsaqp1415g3.GelApp.api;

import javax.ws.rs.GET;

import edu.upc.eetac.dsa.dsaqp1415g3.GelApp.api.model.GelAppRootAPI;

public class GelAppRootAPIResource {
	@GET
	public GelAppRootAPI getRootAPI() {
		GelAppRootAPI api = new GelAppRootAPI();
		return api;
	}
}
