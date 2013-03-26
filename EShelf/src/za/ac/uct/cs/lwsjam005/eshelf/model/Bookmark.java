package za.ac.uct.cs.lwsjam005.eshelf.model;

import java.io.Serializable;

public class Bookmark implements Serializable {

	private static final long serialVersionUID = 1L;

	private int pageNumber;

	private String annotation;

	public Bookmark(int pageNumber, String annotation) {
		this.pageNumber = pageNumber;
		this.annotation = annotation;
	}

	public int getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}

	public String getAnnotation() {
		return annotation;
	}

	public void setAnnotation(String annotation) {
		this.annotation = annotation;
	}
	
	@Override
	public String toString() {
		return "Page " + (pageNumber+1) + " - " + annotation;
	}

}
