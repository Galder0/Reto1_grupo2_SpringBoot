package com.grupo2.reto1.song.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class SongPostRequest {
	
	private Integer id;
	
	@NotNull(message="Song title can't be null.")
    @NotEmpty
    @NotBlank
	private String title;
	
	@NotNull(message="Author can't be null.")
    @NotEmpty
    @NotBlank
	private String author;
	
	private String url;
	
	private String views;
	
	public SongPostRequest() {
		
	}
	
	public SongPostRequest(Integer id) {
		this.id = id;
	}

	public SongPostRequest(@NotNull(message = "Song title can't be null.") @NotEmpty @NotBlank String title,
			@NotNull(message = "Author can't be null.") @NotEmpty @NotBlank String author, String url) {
		super();
		this.title = title;
		this.author = author;
		this.url = url;
	}
	
	public SongPostRequest(Integer id, @NotNull(message = "Song title can't be null.") @NotEmpty @NotBlank String title,
			@NotNull(message = "Author can't be null.") @NotEmpty @NotBlank String author, String url, String views) {
		super();
		this.id = id;
		this.title = title;
		this.author = author;
		this.url = url;
		this.views = views;
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

	public String getViews() {
		return views;
	}

	public void setViews(String views) {
		this.views = views;
	}

	@Override
	public String toString() {
		return "SongPostRequest [id=" + id + ", title=" + title + ", author=" + author + ", url=" + url + ", views="
				+ views + "]";
	}

}
