package com.grupo2.reto1.song.model;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class SongServiceResponse {
	
	private Integer id;
	
	private String title;
	
	private String author;
	
	private String url;
	
	private Integer views;
	
	public SongServiceResponse() {
		
	}

	public SongServiceResponse(String title, String author, String url) {
		super();
		this.title = title;
		this.author = author;
		this.url = url;
	}
	
	public SongServiceResponse(Integer id, String title, String author, String url) {
		super();
		this.id = id;
		this.title = title;
		this.author = author;
		this.url = url;
	}

	public SongServiceResponse(Integer id, String title, String author, String url, Integer views) {
		super();
		this.id = id;
		this.title = title;
		this.author = author;
		this.url = url;
		this.views = views;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Integer getViews() {
		return views;
	}

	public void setViews(Integer views) {
		this.views = views;
	}

	@Override
	public String toString() {
		return "SongServiceResponse [id=" + id + ", title=" + title + ", author=" + author + ", url=" + url + ", views="
				+ views + "]";
	}
	
	

}
