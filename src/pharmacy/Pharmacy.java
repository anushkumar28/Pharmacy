package pharmacy;

import java.util.Scanner;

public class Pharmacy {

	public static void main(String[] args) {
		Database db = new Database();
		
		System.out.println("- Pharmacy -");
		Scanner read = new Scanner(System.in);
		
		for(;;){
			Menu.showMenu();
			
			char choice = read.nextLine().charAt(0);
			switch(choice){
			case 'A':
				
				
				System.out.println("Product was successfully added!");
				break;
			case 'R':
				db.removeProduct();
				break;
			case 'S':
				System.out.println("- Showing all products -");
				System.out.print("Sort by(id, name, producer, price, quantity): ");
				String sort = read.nextLine();
				db.showAll(sort);
				break;
			case 'E':
				db.editProduct();
				break;
			case 'X':
				return;
			}
			System.out.println();
		}

	}

}
