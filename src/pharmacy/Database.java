package pharmacy;

import java.sql.*;
import java.util.Scanner;

public class Database {
	private Connection conn;
	private Statement stat;
	private ResultSet result;
	Scanner read = new Scanner(System.in);
	
	public Database(){
		try{
			Class.forName("com.mysql.jdbc.Driver");
			
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/pharmacy", "root", "");
			stat = conn.createStatement();
		}catch(Exception exc){
			System.out.println("Cannot connect to the database!");
		}
	}
	
	void addProduct(){
		System.out.println(" - Adding new product - ");
		System.out.print("Name: ");
		String name = read.nextLine();
		
		System.out.print("Producer: ");
		String producer = read.nextLine();
		
		System.out.print("Price: ");
		float price = read.nextFloat();
		
		System.out.print("Quantity: ");
		int quantity = read.nextInt();
		
		sendManipulateQuery("INSERT INTO medicines (name, producer, price, quantity) VALUES ('" + name + "', '" + producer + "', '" + price + "', '" + quantity + "')");
	}
	
	void removeProduct(){
		System.out.println("- Removing product -");
		System.out.print("ID: ");
		int id = read.nextInt();
		sendManipulateQuery("DELETE FROM medicines WHERE id=" + id);
		System.out.println("Successfully removed product!");
	}
	
	void showAll(String sort){
		try{
			sendQuery("SELECT * FROM medicines ORDER BY " + sort);
			//System.out.println("ID\tNAME\tPRODUCER\tPRICE\tQUANTITY");
			while(result.next()){
				int id = result.getInt("id");
				String name = result.getString("name");
				String producer = result.getString("producer");
				float price = result.getFloat("price");
				int quantity = result.getInt("quantity");
				
				System.out.println(id + ".\t" + name + "\t\t" + producer + "\t\t" + price + "$\t" + quantity + "pcs.");
			}
		}catch(Exception exc){
			System.out.println("showAll Exception: " + exc);
		}
	}
	
	void editProduct(){
		System.out.println("ID: ");
		int id = read.nextInt();
		System.out.print("Edit(name, producer, price, quantity): ");
		read.next();
		String edit = read.nextLine();
		System.out.print("Value: ");
		String value = read.nextLine();
		
		sendManipulateQuery("UPDATE medicines SET " + edit + "='" + value + "' WHERE id=" + id);
		System.out.println("Successfully edited product!");
	}
	
	void sendManipulateQuery(String query){
		try{
			stat.executeUpdate(query);
		}catch(Exception exc){
			System.out.println(exc);
		}
	}
	
	void sendQuery(String query){
		try{	
			stat.executeQuery(query);
		}catch(Exception exc){
			System.out.println(exc);
		}

	}
}
