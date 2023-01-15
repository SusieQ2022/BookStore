package de.uni.koeln.se.bookstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;

import java.util.ArrayList;
import java.util.List;

import de.uni.koeln.se.bookstore.datamodel.Book;
import de.uni.koeln.se.bookstore.service.BookService;


@RequestMapping("/bookStore")
@RestController
public class BookController {

	@Autowired
	BookService bookSer;
	
	@GetMapping
	public ResponseEntity<List<Book>> getAllbooks(){
		
		List<Book> books = new ArrayList<Book> ();
		books = bookSer.findBooks();
		
		return new ResponseEntity<>(books, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Book> getBookById(@PathVariable int id){
		
		return new ResponseEntity<>(bookSer.fetchBook(id).get(), HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<Book> addBook(@RequestBody Book book){
		
		bookSer.addBook(book);
		
		return new ResponseEntity<>(book, HttpStatus.CREATED);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Book> removeBookById(@PathVariable int id){
		
		Book book = bookSer.fetchBook(id).get();
		
		if(bookSer.deleteBook(id)) {
			return new ResponseEntity<>(book, HttpStatus.OK);
		}else {
			return new ResponseEntity<>(book, HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/oldestBook")
	public ResponseEntity<Book> getOldestBook(){
		List<Book> books = new ArrayList<Book> ();
		books = bookSer.findBooks();
		
		Book oldestBook = books.get(0);
		int minYear = books.get(0).getPublishedYear();
		for(int i=1; i<books.size(); i++) {
			if(books.get(i).getPublishedYear() < minYear) {
				minYear = books.get(i).getPublishedYear();
				oldestBook = books.get(i);
			}
		}
		
		int idOfOldestBook = oldestBook.getId();
		return new ResponseEntity<>(bookSer.fetchBook(idOfOldestBook).get(), HttpStatus.OK);
	}
	
	@GetMapping("/latestBook")
	public ResponseEntity<Book> getLatestBook(){
		List<Book> books = new ArrayList<Book> ();
		books = bookSer.findBooks();
		
		Book latestBook = books.get(0);
		int maxYear = books.get(0).getPublishedYear();
		for(int i=1; i<books.size(); i++) {
			if(books.get(i).getPublishedYear() > maxYear) {
				maxYear = books.get(i).getPublishedYear();
				latestBook = books.get(i);
			}
		}
		
		int idOfLatestBook = latestBook.getId();
		return new ResponseEntity<>(bookSer.fetchBook(idOfLatestBook).get(), HttpStatus.OK);
	}
}
