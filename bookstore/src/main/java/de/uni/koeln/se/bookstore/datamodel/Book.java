package de.uni.koeln.se.bookstore.datamodel;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Book {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	private String name;
	private String author;
	private Integer publishedYear;
	private double price;
	
	public Book() {}
	
	public Book(String name, String author, Integer publishedYear, double price) {
		this.name = name;
		this.author = author;
		this.publishedYear = publishedYear;
		this.price = price;
	}

	public synchronized Integer getId() {
		return id;
	}

	public synchronized void setId(Integer id) {
		this.id = id;
	}

	public synchronized String getName() {
		return name;
	}

	public synchronized void setName(String name) {
		this.name = name;
	}

	public synchronized String getAuthor() {
		return author;
	}

	public synchronized void setAuthor(String author) {
		this.author = author;
	}

	public synchronized Integer getPublishedYear() {
		return publishedYear;
	}

	public synchronized void setPublishedYear(Integer publishedYear) {
		this.publishedYear = publishedYear;
	}

	public synchronized double getPrice() {
		return price;
	}

	public synchronized void setPrice(double price) {
		this.price = price;
	}
	
	
	
}
