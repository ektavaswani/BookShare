package com.bookshare.controller;

import java.util.List;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bookshare.exception.UnAuthorizedException;
import com.bookshare.model.Book;
import com.bookshare.model.LoggedUser;
import com.bookshare.model.User;
import com.bookshare.mongoconfig.MongoDBAdapter;
import com.bookshare.utility.DataUtility;

/**
 * Handles requests for the application home page.
 */
@Controller
@Configuration
@PropertySource("classpath:bookshare.properties")
public class HomeController {
	
	@Autowired
	Environment env;
	
	Random rand = new Random();
	HomeController(){
		
	}
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	MongoDBAdapter mongoDBAdapter = new MongoDBAdapter();
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(@RequestParam(required=false) String error_message, Model model) {
		
		model.addAttribute("error_message", error_message );
		
		return "index";
	}
	
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public @ResponseBody String login(@RequestBody User user, Model model) throws UnAuthorizedException{
		logger.info(user.getEmail()+"email : password "+user.getPassword());
		User loggedInUser =mongoDBAdapter.getUser(user.getEmail(), user.getPassword());
		if(null != loggedInUser){
			model.addAttribute("user",loggedInUser);
			LoggedUser loggedUser = new LoggedUser(loggedInUser.getEmail(), DataUtility.getHash(loggedInUser.getEmail()));
			mongoDBAdapter.addLoggedInUser(user.getEmail(), loggedUser);
			return "{\"success\":\"true\","+loggedUser.toString()+"}";
		}else{
			model.addAttribute("error_message","Invalid username and password!");
			throw new UnAuthorizedException("Invalid username and password!");
		}
	}
	
	
	@RequestMapping(value="/getuser", method=RequestMethod.POST)
	public @ResponseBody User getUser(@RequestParam String token,@RequestParam String email,  Model model) {
		LoggedUser loggedUser = new LoggedUser(email, token);
		logger.info("get user for email : "+loggedUser.getEmail());
			return mongoDBAdapter.getUserDetails(loggedUser.getEmail());
		
		}
	
	@RequestMapping(value="/logout", method=RequestMethod.POST)
	public @ResponseBody String logout(@RequestParam String token,@RequestParam String email,  Model model) {
		LoggedUser loggedUser = new LoggedUser(email, token);
		logger.info("Email to delete"+loggedUser.getEmail());
			mongoDBAdapter.logoutUser(loggedUser.getEmail());
			return "{\"success\":\"true\"}";
		}
	
	@RequestMapping(value="/signup", method=RequestMethod.POST)
	public @ResponseBody String signup(@RequestBody User user, Model model){
		
		logger.info(user.getEmail()+"email : password "+user.getPassword());
		user.setEnabled(true);
		
		return mongoDBAdapter.addUser(user.getEmail(), user);
	}
	
	@RequestMapping(value="/home", method=RequestMethod.GET)
	public String home(Model model){
		return "home";
	}
	
	@RequestMapping(value="/book", method=RequestMethod.POST)
	public @ResponseBody String addBook(@RequestBody Book book, Model model) throws UnAuthorizedException{
		//TODO: if same book exists for that user
		logger.info("book : "+book.getName() + ", seller : " +book.getSeller());
		int  bookId = rand.nextInt();
		book.setBookId(bookId);
			mongoDBAdapter.addBook(bookId, book);
			return "{\"success\":\"true\","+book.toString()+"}";
		
	}
	@RequestMapping(value="/book/{bookid}", method=RequestMethod.DELETE)
	public @ResponseBody String deleteBook(@PathVariable ("bookid") String bookId, Model model) throws UnAuthorizedException{
		//add logger
		logger.info("book Id : "+bookId);
			String result =mongoDBAdapter.deleteBook(Integer.parseInt(bookId));
			logger.info("result : "+result);
			return "{\"success\":\"true\","+result+"}";
	}
	
	@RequestMapping(value="/book", method=RequestMethod.GET)
	public @ResponseBody List<Book> getBooks(Model model) throws UnAuthorizedException{
		//add logger
		logger.info("list all books");
			List<Book> result =mongoDBAdapter.getBooks();
		logger.info("result : "+result);
			return result;
	}
	
	@RequestMapping(value="/user/{emailid}/rate/{sellerid}/{rating}", method=RequestMethod.POST)
	public @ResponseBody String rateSeller(@PathVariable ("emailid") String raterId, @PathVariable ("sellerid") String sellerId, @PathVariable ("rating") String rating, @RequestParam (value	="comment", required=false) String comment, Model model) throws UnAuthorizedException{
		//TODO: min and max for rating
		logger.info("rater Id : "+raterId+", seller Id : "+sellerId+", rating : "+rating);
		String result =mongoDBAdapter.rateSeller(raterId, sellerId, rating, comment);
		logger.info("result : "+result);
		return "{\"success\":\"true\","+result+"}";
	}
	
	@RequestMapping(value="/user/{email}/{isEnabled}", method=RequestMethod.POST)
	public @ResponseBody String disableUser(@PathVariable String email,@PathVariable boolean isEnabled, Model model){
		//TODO: if already disabled
		logger.info("email : "+email);
		String result =mongoDBAdapter.setEnabled(email, isEnabled);
		logger.info("result : "+result);
		return "{\"success\":\"true\","+result+"}";
	}
	
	@RequestMapping(value="/user/{email}/passwordreset", method=RequestMethod.POST)
	public @ResponseBody String changePassword(@PathVariable ("email") String email,@RequestParam ("old") String oldPassword,@RequestParam ("new") String newPassword, @RequestParam ("confirm") String confirmPassword, Model model){
		//TODO: if already disabled
		logger.info("change password for email : "+email);
		logger.info("new : "+newPassword +", confirm : "+confirmPassword);

		if(!newPassword.equals(confirmPassword))
		{
			return "{\"success\":\"false\",\"error\":\"new and confirm passwords do not match\"}";
		}
		else
		{
		String result =mongoDBAdapter.changePassword(email, oldPassword, newPassword);
		logger.info("result : "+result);
		return "{\"success\":\"true\","+result+"}";
		}
	}
	
	@RequestMapping(value="/user", method=RequestMethod.PUT)
	public @ResponseBody String changeMajor( @RequestBody User user, Model model){
		//TODO: if already disabled
		logger.info("email : "+user.getEmail()+", major : "+user.getMajor());
		if(null != user.getMajor())
		{
		 String result = mongoDBAdapter.changeMajor(user.getEmail(), user.getMajor());
		logger.info("result : "+result);
		return "{\"success\":\"true\","+result+"}";
		}else
			return "{\"success\":\"false\",\"error\":\"No new value for major\"}";

	}
}
