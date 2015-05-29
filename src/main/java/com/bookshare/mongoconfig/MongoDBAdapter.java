package com.bookshare.mongoconfig;


import java.util.ArrayList;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.bookshare.configbean.SpringMongoConfig;
import com.bookshare.model.Book;
import com.bookshare.model.LoggedUser;
import com.bookshare.model.User;



/**
 * MongoDB operation adapter class
 *
 */
public class MongoDBAdapter {
	private ApplicationContext ctx;
	private MongoOperations mongoOperation;
	 
	public MongoDBAdapter() {
		this.ctx = new AnnotationConfigApplicationContext(SpringMongoConfig.class);
		this.mongoOperation = (MongoOperations)ctx.getBean("mongoTemplate");
	}
	
	public User getUser(String userId, String password) {
		Query searchUserQuery = new Query(Criteria.where("_id").is(userId).and("password").is(password));
		User searchedUser = mongoOperation.findOne(searchUserQuery, User.class);
		return searchedUser;
	}
	
	public String addUser(String emailId, User user){
		String opStatus;
		Query searchUserQuery = new Query(Criteria.where("_id").is(emailId));
		User searchedUser = mongoOperation.findOne(searchUserQuery, User.class);
		if (null != searchedUser) {
			opStatus = "User '".concat(emailId).concat(" already exists.");
		} else {
			
			mongoOperation.insert(user);
			//TODO test status of db operation
			opStatus = "success";
			//System.out.println("addToSet status: "+writeResult.isUpdateOfExisting());
		}
		return opStatus;
	}
	
	public String addBook(int bookId, Book book){
		String opStatus;
		Query searchBookQuery = new Query(Criteria.where("_id").is(bookId));
		Book searchBook = mongoOperation.findOne(searchBookQuery, Book.class);
		if (null != searchBook) {
			opStatus = "Book "+ bookId+" already exists.";
		} else {
			
			mongoOperation.insert(book);
			//TODO test status of db operation
			opStatus = "success";
		}
		return opStatus;
	}
	
	public String deleteBook(int bookId) {
		
		Query searchBookQuery = new Query(Criteria.where("_id").is(bookId));
		mongoOperation.remove(searchBookQuery, Book.class);
		return searchBookQuery.toString();
	}
	
	public String addLoggedInUser(String email, LoggedUser loggedUser) {
		String token;
		Query searchUserQuery = new Query(Criteria.where("_id").is(email));
		LoggedUser searchedUser = mongoOperation.findOne(searchUserQuery, LoggedUser.class);
		if (null == searchedUser) {
			mongoOperation.insert(loggedUser);
		}
		token = loggedUser.getToken();
		return token;
	}

	public void logoutUser(String email) {
		Query searchUserQuery = new Query(Criteria.where("_id").is(email));
		mongoOperation.remove(searchUserQuery, LoggedUser.class);
	}

	public User getUserDetails(String email) {
		Query searchUserQuery = new Query(Criteria.where("_id").is(email));
		User searchedUser = mongoOperation.findOne(searchUserQuery, User.class);
		return searchedUser;
	}

	public String rateSeller(String raterId, String sellerId, String rating,
			String comment) {
		Query searchUserQuery = new Query(Criteria.where("_id").is(sellerId));
		User searchedUser = mongoOperation.findOne(searchUserQuery, User.class);
		ArrayList<String> temp=new ArrayList<String>();
			temp= searchedUser.getRatedBy();
			temp.add(raterId);
			searchedUser.setRatedBy(temp);
			temp=searchedUser.getRating();
			temp.add(rating);
			searchedUser.setRating(temp);
			temp=searchedUser.getComments();
			temp.add(comment);
			searchedUser.setComments(temp);
			mongoOperation.save(searchedUser);
		
		return searchedUser.toString();
	}

	public String setEnabled(String email, boolean isEnabled) {
		Query searchUserQuery = new Query(Criteria.where("_id").is(email));
		User searchedUser = mongoOperation.findOne(searchUserQuery, User.class);
		searchedUser.setEnabled(isEnabled);
		mongoOperation.save(searchedUser);
		return searchedUser.toString();
	}

	public List<Book> getBooks() {
		List<Book> books=new ArrayList<Book>();
		books=	mongoOperation.findAll(Book.class);
		return books;
	}

	public String changePassword(String email, String oldPassword,
			String newPassword) {
		Query searchUserQuery = new Query(Criteria.where("_id").is(email));
		User searchedUser = mongoOperation.findOne(searchUserQuery, User.class);
		if(null == searchedUser){
			//return error
			return "User not found";
		}else if(!searchedUser.getPassword().equals(oldPassword)){
			//return old passwords do not match
			return "Incorrect old password";
		}else{
			searchedUser.setPassword(newPassword);
			mongoOperation.save(searchedUser);
			return searchedUser.toString();
		}
	}

	public String changeMajor(String email, String major) {
		Query searchUserQuery = new Query(Criteria.where("_id").is(email));
		User searchedUser = mongoOperation.findOne(searchUserQuery, User.class);
		if(null == searchedUser){
			//return error
			return "User not found";
		}else{
			searchedUser.setMajor(major);
			mongoOperation.save(searchedUser);
			return searchedUser.toString();
		}
	}

	
}