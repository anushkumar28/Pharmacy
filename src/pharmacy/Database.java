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
		System.out.println("Product was successfully added!");
	}
	
	void removeProduct(){
		System.out.println("- Removing product -");
		System.out.print("ID: ");
		int id = read.nextInt();
		sendManipulateQuery("DELETE FROM medicines WHERE id=" + id);
		System.out.println("Successfully removed product!");
	}
	
	void showAll(){
		System.out.println("- Showing all products -");
		System.out.print("Sort by(id, name, producer, price, quantity): ");
		String sort = read.nextLine();
		try{
			sendQuery("SELECT * FROM medicines ORDER BY " + sort);

			while(result.next()){
				int id = result.getInt("id");
				String name = result.getString("name");
				String producer = result.getString("producer");
				float price = result.getFloat("price");
				int quantity = result.getInt("quantity");
				
				System.out.println(id + ".\t" + name + "\t\t" + producer + "\t\t" + price + "$\t" + quantity + "pcs.");
			}
		}catch(Exception exc){
			System.out.println("Unexpected error :(");
		}
	}
	
	void editProduct(){
		System.out.println("- Editing product -");
		System.out.print("ID: ");
		int id = read.nextInt();
		
		System.out.print("Edit(name, producer, price, quantity): ");
		String edit = read.next();
		
		String query = "UPDATE medicines SET ";
		
		System.out.print("Value: ");
		
		if(edit.equals("quantity")){
			int value = read.nextInt();
			query += "quantity='" + value;
		}else if(edit.equals("price")){
			float value = read.nextFloat();
			query += "price='" + value;
		}else if(edit.equals("name") || edit.equals("producer")){
			String value = read.next();
			query += edit + " = '" + value;
		}
		sendManipulateQuery(query + "' WHERE id=" + id);
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
			result = stat.executeQuery(query);
		}catch(Exception exc){
			System.out.println(exc);
		}

	}
}
