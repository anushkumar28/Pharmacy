package pharmacy;

import java.util.Scanner;

public class Pharmacy {

	public static void main(String[] args) {
		Database db = new Database();
		
		System.out.println("- Pharmacy -");
		Scanner read = new Scanner(System.in);
		char choice = 'Z';
		for(;;){
			Menu.showMenu();
			
			try{
				choice = read.nextLine().charAt(0);
			}catch(StringIndexOutOfBoundsException exc){}
			switch(choice){
			case 'A':
				db.addProduct();	
				break;
			case 'R':
				db.removeProduct();
				break;
			case 'S':
				db.showAll();
				break;
			case 'E':
				db.editProduct();
				break;
			case 'X':
				return;
			default:
				System.out.println("Unknown choice!");
				break;
			}
		}
	}
}
