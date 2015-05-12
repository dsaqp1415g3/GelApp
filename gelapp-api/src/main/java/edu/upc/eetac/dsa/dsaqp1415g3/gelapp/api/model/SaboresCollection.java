package edu.upc.eetac.dsa.dsaqp1415g3.gelapp.api.model;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.Link;

import org.glassfish.jersey.linking.InjectLink;
import org.glassfish.jersey.linking.InjectLinks;
import org.glassfish.jersey.linking.InjectLink.Style;

import edu.upc.eetac.dsa.dsaqp1415g3.gelapp.api.MediaType;
import edu.upc.eetac.dsa.dsaqp1415g3.gelapp.api.SaboresResource;


public class SaboresCollection {
	@InjectLinks({
	@InjectLink(resource = SaboresResource.class, style = Style.ABSOLUTE, rel = "create-sting", title = "Create sting", type = MediaType.GELAPP_API_SABOR),
	})
	
	
	private List<Link> links;

	private List<Sabores> sabores;
	
	public List<Link> getLinks() {
		return links;
	}

	public void setLinks(List<Link> links) {
		this.links = links;
	}
	
	public SaboresCollection() {
		super();
		sabores = new ArrayList<>();
	}
 
	public List<Sabores> getStings() {
		return sabores;
	}
 
	public void setSabores(List<Sabores> sabores) {
		this.sabores = sabores;
	}
 
	public void addSabor(Sabores sabor) {
		sabores.add(sabor);
	}
}
