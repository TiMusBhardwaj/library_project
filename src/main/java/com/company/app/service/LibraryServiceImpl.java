package com.company.app.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.company.app.advice.InvalidArgumentException;
import com.company.app.controller.LibararyController;
import com.company.domain.library.dto.Book;
import com.company.domain.library.dto.User;
import com.company.domain.library.entity.BookEntity;
import com.company.domain.library.entity.IssuedBookEntity;
import com.company.domain.library.entity.UserBookIssueRecordEntity;
import com.company.domain.library.entity.UserEntity;
import com.company.domain.library.repository.BookIssueRecordRepo;
import com.company.domain.library.repository.BookRepo;
import com.company.domain.library.repository.UserBookIssueRecordRepo;
import com.company.domain.library.repository.UserRepo;


/**
 * @author sumit.bhardwaj
 *
 */
@Service
public class LibraryServiceImpl implements LibraryService{
	
	private static final Logger logger = LoggerFactory.getLogger(LibraryServiceImpl.class);
	
	@Autowired
	private BookRepo bookRepo;
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private BookIssueRecordRepo bookIssueRepo;
	
	@Autowired
	private UserBookIssueRecordRepo userBookRepo;
	
	public BookEntity addBook(Book book) {
		//Not using a mapping library as field count is small
		// and mapping is straight forward
		BookEntity entity = new BookEntity();
		entity.setName(book.getName());
		entity.setAuthor(book.getAuthor());
		entity.setCategory(book.getCategory().toString());
		entity.setPrice(book.getPrice());
		return bookRepo.save(entity);
		
	}
	
	/**
	 * @return List of All books in Library
	 */
	public List<BookEntity> listBooks(){
		// Not a nice way to do it. But it will work
		//As Book count is not huge, else we had to use pagination
		List<BookEntity> list = new ArrayList<BookEntity>();
		bookRepo.findAll().forEach(new Consumer<BookEntity>() {

			@Override
			public void accept(BookEntity t) {
				list.add(t);
			}
			
		});
		
		return list;
	}

	/**
	 * @param user
	 * @return user after persisting in Database
	 */
	@Transactional
	public UserEntity createRecordForUser(User user) {
		UserBookIssueRecordEntity userbookIssueRecordEntity = new UserBookIssueRecordEntity();
		
		UserEntity entity = new UserEntity();
		entity.setAge(user.getAge());
		entity.setName(user.getName());
		entity.setBookLimit(user.getBookLimit());
		userbookIssueRecordEntity.setUser(entity);
		entity.setUserbirEntity(userbookIssueRecordEntity);
		return userRepo.save(entity);
		
	}
	
	public List<BookEntity> searchBooksByAuthor(String author) {
		return bookRepo.findByAuthor(author);
	}
	

	public List<BookEntity> searchBooksByTitle(String bookName) {
		return bookRepo.findByName(bookName);
	}

	/**
	 * @param bookId
	 * @param userId
	 * @return Message or Error Message
	 * 
	 *
	 */
	//TODO: Making it synchronize will hamper performance.
	// And this is also not scalable. But i don't want to go the retry way right now.
	//Dont have enough Time for that.
	@Transactional
	public synchronized String issueBook(Integer bookId, Integer userId) {
		Optional<BookEntity> bookEntity = bookRepo.findById(bookId);
		if (!bookEntity.isPresent()) {
			throw new InvalidArgumentException("INVALID BOOK ID");
		}
		List<IssuedBookEntity> bookIsEntities = bookIssueRepo.findByBook_Id(bookId);
		if (bookIsEntities.size() >0) {
			throw new InvalidArgumentException( "BOOK IS OUT (ALREADYY ISSUED), CANNOT BE ISSUED");
		}
		
		Optional<UserEntity> user = userRepo.findById(userId);
		if (!user.isPresent()) {
			throw new InvalidArgumentException("INVALID USER ID");
		}
		
		if (user.get().getBookLimit() == user.get().getUserbirEntity().getIssuedBooks().size()) {
			throw new InvalidArgumentException("USER's BOOK ISSUE LIMIT REACHED");
		}
		logger.info("Issuing Book : {} to User {}", bookEntity, user);
		IssuedBookEntity ibEntity = new IssuedBookEntity();
		ibEntity.setBook(bookEntity.get());
		user.get().getUserbirEntity().getIssuedBooks().add(ibEntity);
		bookIssueRepo.save(ibEntity);
		userBookRepo.save(user.get().getUserbirEntity());
		return "BOOK ISSUED";
	} 
	
	
	/**
	 * @param bookId
	 * @return
	 */
	@Transactional
	public String returnBook(Integer bookId) {
		Optional<BookEntity> bookEntity = bookRepo.findById(bookId);
		if (!bookEntity.isPresent()) {
			throw new InvalidArgumentException( "INVALID BOOK ID");
		}
		List<IssuedBookEntity> bookIsEntities = bookIssueRepo.findByBook_Id(bookId);
		if (bookIsEntities.size() == 0) {
			throw new InvalidArgumentException("BOOK IS NOT IN ISSUED STATE");
		}
		
		bookIssueRepo.delete(bookIsEntities.get(0));
		return "BOOK RETURNED";
	} 

}
