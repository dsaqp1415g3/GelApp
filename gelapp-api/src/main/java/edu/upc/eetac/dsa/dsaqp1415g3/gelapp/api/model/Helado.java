package edu.upc.eetac.dsa.dsaqp1415g3.gelapp.api.model;

import java.util.List;

import javax.ws.rs.core.Link;

public class Helado {
	private int heladoid;
	private String capa1Topping;
	private String capa2Helado;
	private String capa3Topping;
	private String capa4Helado;
	private String capa5Topping;
	private long lastModified;
	private long creationTimestamp;
	
	
	private List<Link> links;
	
	public List<Link> getLinks() {
		return links;
	}


	public void setLinks(List<Link> links) {
		this.links = links;
	}


	public int getHeladoid() {
		return heladoid;
	}


	public void setHeladoid(int heladoid) {
		this.heladoid = heladoid;
	}


	public String getCapa1Topping() {
		return capa1Topping;
	}


	public void setCapa1Topping(String capa1Topping) {
		this.capa1Topping = capa1Topping;
	}


	public String getCapa2Helado() {
		return capa2Helado;
	}


	public void setCapa2Helado(String capa2Helado) {
		this.capa2Helado = capa2Helado;
	}


	public String getCapa3Topping() {
		return capa3Topping;
	}


	public void setCapa3Topping(String capa3Topping) {
		this.capa3Topping = capa3Topping;
	}


	public String getCapa4Helado() {
		return capa4Helado;
	}


	public void setCapa4Helado(String capa4Helado) {
		this.capa4Helado = capa4Helado;
	}


	public String getCapa5Topping() {
		return capa5Topping;
	}


	public void setCapa5Topping(String capa5Topping) {
		this.capa5Topping = capa5Topping;
	}


	public long getLastModified() {
		return lastModified;
	}


	public void setLastModified(long lastModified) {
		this.lastModified = lastModified;
	}


	public long getCreationTimestamp() {
		return creationTimestamp;
	}


	public void setCreationTimestamp(long creationTimestamp) {
		this.creationTimestamp = creationTimestamp;
	}	
}
