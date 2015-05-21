package edu.upc.eetac.dsa.dsaqp1415g3.gelapp.api.model;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.Link;

import org.glassfish.jersey.linking.Binding;
import org.glassfish.jersey.linking.InjectLink;
import org.glassfish.jersey.linking.InjectLinks;
import org.glassfish.jersey.linking.InjectLink.Style;

import edu.upc.eetac.dsa.dsaqp1415g3.gelapp.api.HeladoResource;
import edu.upc.eetac.dsa.dsaqp1415g3.gelapp.api.MediaType;


public class HeladoCollection {
	@InjectLinks({
		@InjectLink(resource = HeladoResource.class, style = Style.ABSOLUTE, rel = "create-helado", title = "Create new helado", type = MediaType.GELAPP_API_HELADO),
		@InjectLink(value = "/helados?page={previous}", style = Style.ABSOLUTE, rel = "previous", title = "Previous helados", type = MediaType.GELAPP_API_HELADO_COLLECTION, bindings = { @Binding(name = "previous", value = "${instance.previousPage}") }),
		@InjectLink(value = "/helados?page={next}", style = Style.ABSOLUTE, rel = "next", title = "Next helados", type = MediaType.GELAPP_API_HELADO_COLLECTION, bindings = { @Binding(name = "next", value = "${instance.nextPage}") }) })
	
	private List<Link> links;
	
	private List<Helado> helados;
	private int previousPage;
	private int nextPage;
	
	
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
	public void addHelado(Helado helado) {
		helados.add(helado);
	}
	public int getPreviousPage() {
		return previousPage;
	}
	public void setPreviousPage(int previousPage) {
		this.previousPage = previousPage;
	}
	public int getNextPage() {
		return nextPage;
	}
	public void setNextPage(int nextPage) {
		this.nextPage = nextPage;
	}
}
