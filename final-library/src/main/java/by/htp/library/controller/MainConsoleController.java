package by.htp.library.controller;

import java.util.List;
import java.util.Scanner;

import by.htp.library.dao.BookDao;
import by.htp.library.dao.DaoFabrique;
import by.htp.library.dao.implementation.DaoImplementation;

public class MainConsoleController {
	
	public static void main(String[] args) {
		
		
		
		DaoImplementation dao1 = new DaoImplementation();
		BookDao dao = new DaoImplementation();
		dao.mainMenu();
//		
		
//System.out.println("Please enter 2 to search book by id; 1 to view catalog");
//		
//		Scanner scanner = new Scanner(System.in);
//		int value = scanner.nextInt();		
//		
//		switch(value) {
//		case 1:
//			List<Book> listBook = dao.listBooks();	
//			System.out.println(listBook.toString());
//			break;
//		case 2:
//			System.out.println("Please enter book id:");
//			Book book = dao.read();
//			System.out.println(book.toString());
//			System.out.println("What to do with the book? 1 to update, 2 to delete");
//			
//			Scanner scanner2 = new Scanner(System.in);
//			int option = scanner.nextInt();	
//				switch(option) {
//					case 1:
//						dao1.update(book);
//						break;
//					case 2:		
//						dao1.delete(book);
//						break;
//				}
//			break;
//		case 3:
//			break;
//		}
//		
//		scanner.close();
//
//		dao1.insert();
//		
//	}
	}
}
