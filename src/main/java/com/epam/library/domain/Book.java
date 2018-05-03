package com.epam.library.domain;

public class Book extends Entity {

	private String title;
	private String brief;
	private String author;
	private int publishYear;

	public Book() {
		super();
	}

	public Book(int id, String title, String brief, String author, int publishYear) {
		super(id);
		this.title = title;
		this.brief = brief;
		this.author = author;
		this.publishYear = publishYear;
	}

	public Book(String title, String brief, String author, int publishYear) {
		this.title = title;
		this.brief = brief;
		this.author = author;
		this.publishYear = publishYear;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getBrief() {
		return brief;
	}

	public void setBrief(String brief) {
		this.brief = brief;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public int getPublishYear() {
		return publishYear;
	}

	public void setPublishYear(int publishYear) {
		this.publishYear = publishYear;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((author == null) ? 0 : author.hashCode());
		result = prime * result + ((brief == null) ? 0 : brief.hashCode());
		result = prime * result + publishYear;
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Book other = (Book) obj;
		if (author == null) {
			if (other.author != null)
				return false;
		} else if (!author.equals(other.author))
			return false;
		if (brief == null) {
			if (other.brief != null)
				return false;
		} else if (!brief.equals(other.brief))
			return false;
		if (publishYear != other.publishYear)
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "★ id: " + getId() + ", title = " + title + ", brief = " + brief + ", author = " + author + ", publishYear = " + publishYear + " ★";
	}

}
