package edu.upc.eetac.dsa.dsaqp1415g3.gelapp.api.model;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.Link;

import org.glassfish.jersey.linking.InjectLink.Style;

import edu.upc.eetac.dsa.dsaqp1415g3.gelapp.api.MediaType;
import edu.upc.eetac.dsa.dsaqp1415g3.gelapp.api.SaboresResource;


public class SaboresCollection {	
	
	
	
	private List<Link> links;
	private List<Sabor> sabores;
	private long newestTimestamp;
	private long oldestTimestamp;
	
	

	
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
 
	public List<Sabor> getStings() {
		return sabores;
	}
 
	public void setSabores(List<Sabor> sabores) {
		this.sabores = sabores;
	}
 
	public void addSabor(Sabor sabor) {
		sabores.add(sabor);
	}

	public long getOldestTimestamp() {
		return oldestTimestamp;
	}

	public void setOldestTimestamp(long oldestTimestamp) {
		this.oldestTimestamp = oldestTimestamp;
	}

	public long getNewestTimestamp() {
		return newestTimestamp;
	}

	public void setNewestTimestamp(long newestTimestamp) {
		this.newestTimestamp = newestTimestamp;
	}
}
