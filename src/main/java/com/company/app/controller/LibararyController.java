package com.company.app.controller;

import java.util.List;

import javax.validation.constraints.NotNull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.company.app.service.LibraryService;
import com.company.domain.library.dto.Book;
import com.company.domain.library.dto.User;
import com.company.domain.library.entity.BookEntity;

@RestController
@RequestMapping("/library")

public class LibararyController {
	
	private static final Logger logger = LoggerFactory.getLogger(LibararyController.class);

	@Autowired
	private LibraryService libraryService;

	@PostMapping("/books")
	public String addBook(@Validated Book book) {
		logger.info("Add Book -- {}" , book);
		String bookDetails =  libraryService.addBook(book).toString();
		logger.info("Added Book {}",bookDetails);
		return bookDetails;
	}

	@GetMapping("/books/all")
	public List<BookEntity> listAllBooks() {

		return libraryService.listBooks();
	}

	@PostMapping("/users")
	public String addUser(@Validated User user) {
		
		logger.info("Add User -- {}" , user);
		String userDetails = libraryService.createRecordForUser(user).toString();
		logger.info("Added User {}",userDetails);
		return userDetails;
	}

	@PostMapping("/books/issue")
	@Validated
	public String issueBook(@RequestParam @NotNull Integer bookId, @RequestParam @NotNull Integer userId) {
		logger.info("Issue Book with id : {} to user with Id: {}" , bookId, userId);
		String message = libraryService.issueBook(bookId, userId);
		logger.info(message);
		return message;
	}
	
	@PostMapping("/books/return")
	public String returnBook(@Validated @RequestParam @NotNull Integer bookId) {
		logger.info("Return Book with Id : {}", bookId);
		String message=  libraryService.returnBook(bookId);
		logger.info(message);
		return message;
	}

	@GetMapping("/books/author/search")
	public List<BookEntity> searchBooksByAuthor(@Validated @RequestParam("authorName") @NotNull String authorName) {
		logger.info("serach Books by Author {}", authorName);
		List<BookEntity> books =  libraryService.searchBooksByAuthor(authorName);
		logger.info("Searched Books , {}", books);
		return books;
	}

	
	//TODO: Multiple Books by same name can be added to System.
	// Dont know should I restrict it
	
	@GetMapping("/books/title/search")
	public List<BookEntity> searchBooksByTitle(@Validated @RequestParam("bookName") @NotNull String bookName) {
		logger.info("serach Books by Title {}", bookName);
		List<BookEntity> books =   libraryService.searchBooksByTitle(bookName);
		logger.info("Searched Books , {}", books);
		return books;
	}

}
