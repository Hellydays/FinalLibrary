package by.htp.library.dao.implementation;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Scanner;

import by.htp.library.dao.BookDao;
import by.htp.library.dao.DaoFabrique;
import by.htp.library.entity.Book;
import by.htp.library.entity.User;
import by.htp.library.logic.ListOfBooks;

public class DaoImplementation extends DaoFabrique implements BookDao {

	private static final String SQL_SELECT_BOOK = "SELECT * FROM book WHERE id_book = ?";
	private static final String SQL_SELECT_LISTBOOK = "SELECT * FROM book";
	private static final String SQL_UPDATE_BOOK = "UPDATE book SET title = ? WHERE id_book = ?";
	private static final String SQL_DELETE_BOOK = "DELETE FROM book WHERE id_book = ?";
	private static final String SQL_INSERT_BOOK = "INSERT INTO book VALUES (?, ?, ?)";

	private static final String SQL_GET_USER = "SELECT * FROM Users WHERE ticket = ? AND password = ?";
	
	DaoFabrique daofabrique = new DaoFabrique();
	Connection connection = daofabrique.connect();
	
	
	public void mainMenu() {
		
		User user = login();
		
		if(user!=null) {
			System.out.println("Please choose an option: ");
//			Scanner scanner = new Scanner(System.in);
//			int choice = scanner.nextInt();
//			
//			scanner.close();
//			
//			switch(choice) {
//			case 1: 
//				ListOfBooks list = new ListOfBooks(connection);
//				list.getAllBooks();
//				break;
//			case 2: 
//				
//				break;
//				
//			}
			
		} else {
			System.out.println("Sorry, it looks like such user doesn't exist");
		}

	}
	

//	private Connection connect() {
//		Connection connection = null;
//		ResourceBundle rb = ResourceBundle.getBundle("db_config");
//
//		String driver = rb.getString("db.driver");
//		String login = rb.getString("db.login");
//		String pass = rb.getString("db.pass");
//		String url = rb.getString("db.url");
//
//		try {
//			Class.forName(driver);
//			connection = DriverManager.getConnection(url, login, pass);
//
//		} catch (ClassNotFoundException e) {
//			e.printStackTrace();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//
//		return connection;
//	}
//
//	private void closeConnection(Connection connection) {
//		if (connection != null) {
//			try {
//				connection.close();
//			} catch (SQLException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
//	}

//	public User login() {
//		Connection connection = connect();
//		User user = null;
//		Scanner scanner = new Scanner(System.in);
//		System.out.println("Please enter your ticket number: ");
//		int ticketNumber = scanner.nextInt();
//		
//		try {
//			PreparedStatement ps = connection.prepareStatement(SQL_GET_USER);
//			ps.setInt(1, ticketNumber);
//
//			ResultSet rs = ps.executeQuery();
//
//			if (rs.next()) {
//				user = new User();
//
//				user.setId(rs.getInt("idUsers"));
//				user.setTicket(rs.getInt("ticket"));
//				user.setPassword(rs.getString("password"));
//			}
//			
//			Boolean exists = password(user.getPassword());
//			if(exists) {
//				System.out.println("Success");
//			} else {
//				System.out.println("Not found");
//			}
//
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} finally {
//			closeConnection(connection);
//		}
//
//		return user;
//	}

//	
//	public boolean password(String password) {
//		
//		Boolean exists = false;
//		System.out.println("Please enter password:");
//		Scanner scanner = new Scanner(System.in);
//		String enteredPass = scanner.nextLine();
//		if(enteredPass.equals(password)) {
//			exists = true;
//		}
//		return exists;
//		
//	}
	

	public String[] askLoginAndPassword() {

		Scanner scanner = new Scanner(System.in);
		
		System.out.println("Please enter your ticket number:");
		String enteredLogin = scanner.nextLine();
		
		System.out.println("Please enter password:");
		
		String enteredPassword = scanner.nextLine();
		String[]creds = new String[2];
		creds[0] = enteredLogin;
		creds[1] = enteredPassword;
		
		scanner.close();
		return creds;

	}

	
	public User login() {
		String[] creds = askLoginAndPassword();
		User user = getUserDataFromDB(creds[0], creds[1]);
		return user;
	}

	public User getUserDataFromDB(String login, String password) {

		User user = null;

		PreparedStatement ps;
		try {
			ps = connection.prepareStatement(SQL_GET_USER);
			ps.setString(1, login);
			ps.setString(2, password);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				user = new User();

				user.setId(rs.getInt("idUsers"));
				user.setTicket(rs.getString("ticket"));
				user.setPassword(rs.getString("password"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return user;

	}
	



//	public Book read() {
//		Connection connection = connect();
//		Book book = null;
//		Scanner sc = new Scanner(System.in);
//		int id = sc.nextInt();
//
//		try {
//			PreparedStatement ps = connection.prepareStatement(SQL_SELECT_BOOK);
//			ps.setInt(1, id);
//
//			ResultSet rs = ps.executeQuery();
//
//			if (rs.next()) {
//				book = new Book();
//
//				book.setId(rs.getInt("id_book"));
//				book.setName(rs.getString("title"));
//			}
//
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} finally {
//			closeConnection(connection);
//		}
//
////		Statement st = connection.createStatement();		
//
//		return book;
//	}

//	public List<Book> getAllBooks() {
//
//		Connection connection = connect();
//		List<Book> listOfBooks = new ArrayList<>();
//		Book book = null;
//
//		try {
//			PreparedStatement ps = connection.prepareStatement(SQL_SELECT_LISTBOOK);
//			ResultSet rs = ps.executeQuery();
//
//			while (rs.next()) {
//				book = new Book();
//				book.setId(rs.getInt("id_book"));
//				book.setName(rs.getString("title"));
//				listOfBooks.add(book);
//			}
//
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//		return listOfBooks;
//	}
//
//	public void update(Book book) {
//		Connection connection = connect();
//
//		try {
//			PreparedStatement ps = connection.prepareStatement(SQL_UPDATE_BOOK);
//
//			ps.setString(1, "new title");
//			ps.setInt(2, book.getId());
//
//			ps.executeUpdate();
//
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//
//	}
//
//	public void delete(Book book) {
//		Connection connection = connect();
//
//		try {
//			PreparedStatement ps = connection.prepareStatement(SQL_DELETE_BOOK);
//			ps.setInt(1, book.getId());
//
//			ps.executeUpdate();
//
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//
//	}
//
//	public void insert() {
//		Connection connection = connect();
//		List<Book> listBook = listBooks();
//		Book lastBook = listBook.get(listBook.size() - 1);
//		int insertId = lastBook.getId() + 1;
//
//		try {
//			PreparedStatement ps = connection.prepareStatement(SQL_INSERT_BOOK);
//			ps.setInt(1, insertId);
//			ps.setString(2, "new book");
//			ps.setInt(3, 1);
//
//			ps.executeUpdate();
//
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//	}

}
