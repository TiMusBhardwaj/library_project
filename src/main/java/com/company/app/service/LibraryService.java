package com.company.app.service;

import java.util.List;

import javax.transaction.Transactional;

import com.company.domain.library.dto.Book;
import com.company.domain.library.dto.User;
import com.company.domain.library.entity.BookEntity;
import com.company.domain.library.entity.UserEntity;


/**
 * @author sumit.bhardwaj
 *
 */

public interface LibraryService {
	
	
	/**
	 * @param book
	 * @return  Persisted Book object
	 */
	public BookEntity addBook(Book book) ;
	
	/**
	 * @return List of All books in Library
	 */
	public List<BookEntity> listBooks();
		
	

	/**
	 * @param user
	 * @return user after persisting in Database
	 */
	
	public UserEntity createRecordForUser(User user) ;
	
	/**
	 * @param author
	 * @return Book
	 */
	public List<BookEntity> searchBooksByAuthor(String author);

	/**
	 * @param bookName
	 * @return Book
	 */
	public List<BookEntity> searchBooksByTitle(String bookName) ;

	/**
	 * @param bookId
	 * @param userId
	 * @return Message or Error Message
	 * 
	 *
	 */
	
	public String issueBook(Integer bookId, Integer userId) ;
	
	/**
	 * @param bookId
	 * @return
	 */
	@Transactional
	public String returnBook(Integer bookId) ;

}
