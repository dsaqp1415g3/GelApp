package edu.upc.eetac.dsa.dsaqp1415g3.gelapp.api;

import org.glassfish.jersey.linking.DeclarativeLinkingFeature;
import org.glassfish.jersey.server.ResourceConfig;

public class GelAppApplication extends ResourceConfig{
	public GelAppApplication() {
		super();
		register(DeclarativeLinkingFeature.class);
	}
}
