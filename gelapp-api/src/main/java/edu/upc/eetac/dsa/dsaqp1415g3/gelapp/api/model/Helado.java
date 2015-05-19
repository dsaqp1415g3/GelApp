package edu.upc.eetac.dsa.dsaqp1415g3.gelapp.api.model;

import java.util.List;

import javax.ws.rs.core.Link;

import org.glassfish.jersey.linking.Binding;
import org.glassfish.jersey.linking.InjectLink;
import org.glassfish.jersey.linking.InjectLinks;
import org.glassfish.jersey.linking.InjectLink.Style;

import edu.upc.eetac.dsa.dsaqp1415g3.gelapp.api.GelAppRootAPIResource;
import edu.upc.eetac.dsa.dsaqp1415g3.gelapp.api.HeladoResource;
import edu.upc.eetac.dsa.dsaqp1415g3.gelapp.api.MediaType;



public class Helado {
	private int heladoid;
	private String nombreHelado;
	private int autorid;
	private String capa1Topping;
	private String capa2Helado;
	private String capa3Topping;
	private String capa4Helado;
	private String capa5Topping;
	private String autor;
	private long lastModified;
	private long creationTimestamp;
	@InjectLinks({
        @InjectLink(resource = GelAppRootAPIResource.class, style = Style.ABSOLUTE, rel = "self bookmark home", title = "GelApp Root API"),
        @InjectLink(resource = HeladoResource.class, style = Style.ABSOLUTE, rel = "collection", title = "Latest helado", type = MediaType.GELAPP_API_HELADO_COLLECTION),
        @InjectLink(value = "/helados/{heladoid}", style = Style.ABSOLUTE, rel = "heladoid", title = "helado", type = MediaType.GELAPP_API_HELADO, bindings = { @Binding(name = "heladoid", value = "${instance.heladoid}") }) })
	
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


	public int getAutorid() {
		return autorid;
	}


	public void setAutorid(int autorid) {
		this.autorid = autorid;
	}


	public String getNombreHelado() {
		return nombreHelado;
	}


	public void setNombreHelado(String nombreHelado) {
		this.nombreHelado = nombreHelado;
	}


	public String getAutor() {
		return autor;
	}


	public void setAutor(String autor) {
		this.autor = autor;
	}	
}
