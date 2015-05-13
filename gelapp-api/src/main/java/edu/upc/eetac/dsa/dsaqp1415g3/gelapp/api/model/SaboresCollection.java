package edu.upc.eetac.dsa.dsaqp1415g3.gelapp.api.model;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.Link;


public class SaboresCollection {	
	
	private List<Link> links;

	private List<Sabores> sabores;
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
 
	public List<Sabores> getStings() {
		return sabores;
	}
 
	public void setSabores(List<Sabores> sabores) {
		this.sabores = sabores;
	}
 
	public void addSabor(Sabores sabor) {
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
