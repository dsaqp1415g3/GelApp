package edu.upc.eetac.dsa.dsaqp1415g3.gelapp.api.model;

import java.util.List;
import javax.ws.rs.core.Link;

public class Sabor {
	
	private int saborid;
	private String name;
	private String code_color;
	private long lastModified;
	private long creationTimestamp;
	private List<Link> links;	
	
	
	public List<Link> getLinks() {
		return links;
	}
	public void setLinks(List<Link> links) {
		this.links = links;
	}
	public int getSaborid() {
		return saborid;
	}
	public void setSaborid(int saborid) {
		this.saborid = saborid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCode_color() {
		return code_color;
	}
	public void setCode_color(String code_color) {
		this.code_color = code_color;
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
