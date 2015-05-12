package edu.upc.eetac.dsa.dsaqp1415g3.gelapp.api.model;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.Link;

public class HeladoCollection {
	
	private List<Link> links;
	
	private List<Helado> helados;
	private long newestTimestamp;
	private long oldestTimestamp;
	
	public List<Link> getLinks() {
		return links;
	}
	public void setLinks(List<Link> links) {
		this.links = links;
	}
	
	public HeladoCollection() {
		super();
		helados = new ArrayList<>();
	}
	
	public List<Helado> getHelados() {
		return helados;
	}
	public void setHelados(List<Helado> helados) {
		this.helados = helados;
	}
	public long getNewestTimestamp() {
		return newestTimestamp;
	}
	public void setNewestTimestamp(long newestTimestamp) {
		this.newestTimestamp = newestTimestamp;
	}
	public long getOldestTimestamp() {
		return oldestTimestamp;
	}
	public void setOldestTimestamp(long oldestTimestamp) {
		this.oldestTimestamp = oldestTimestamp;
	}
	
	public void addHelado(Helado helado) {
		helados.add(helado);
	}
}
