package by.htp.library.logic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import by.htp.library.entity.Book;

public class ListOfBooks {
	
	private static final String SQL_SELECT_LISTBOOK = "SELECT * FROM book";
	
	private Connection connection;
	
	public ListOfBooks(Connection connection) {
		super();
		this.connection = connection;
	}


	public List<Book> getAllBooks() {

		List<Book> listOfBooks = new ArrayList<>();
		Book book = null;

		try {
			PreparedStatement ps = connection.prepareStatement(SQL_SELECT_LISTBOOK);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				book = new Book();
				book.setId(rs.getInt("idBook"));
				book.setName(rs.getString("Name"));
				book.setAuthor("Author");
				listOfBooks.add(book);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return listOfBooks;
	}

}
