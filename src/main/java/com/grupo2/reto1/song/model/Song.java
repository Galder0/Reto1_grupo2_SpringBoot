package com.grupo2.reto1.song.model;

import java.util.Objects;

public class Song {
	
	private Integer id;
	
	private String title;
	
	private String author;
	
	private String url;
	
	public Song () { 
		
	}
	
	public Song(String title, String author, String url) {
		super();
		this.title = title;
		this.author = author;
		this.url = url;
	}

	public Song(Integer id, String title, String author, String url) {
		super();
		this.id = id;
		this.title = title;
		this.author = author;
		this.url = url;
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

	@Override
	public String toString() {
		return "Song [id=" + id + ", title=" + title + ", author=" + author + ", url=" + url + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(author, id, title, url);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Song other = (Song) obj;
		return Objects.equals(author, other.author) && Objects.equals(id, other.id)
				&& Objects.equals(title, other.title) && Objects.equals(url, other.url);
	}
	
}
