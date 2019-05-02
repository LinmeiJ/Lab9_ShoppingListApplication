package co.grandciruc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
/**************@Author:Linmei Mills ************/
/* What this program can do:
 * 1. A table to display a menu of each item and its price with HashMap.
 * 2. User can add multiple items into their chart.
 * 3. A table to display what items in the chart with its price and quantity.
 * 		If they alreay has the same item in the chart, increase the quantity.
 * 4. prompt user if they want to add more items.
 * 5. Display average price of total items in the chart.
 * 6. Display the more and least expensive items in the chart.
 * */

public class ShoppingLiApplication{
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Welcome to World's Unique Market!");
		
		HashMap<String, Double> lists = new HashMap<String, Double>();
		ArrayList<String> items = new ArrayList<String>();
		ArrayList<Double> price= new ArrayList<Double>();
		ArrayList<Integer> quantity = new ArrayList<Integer>();
		
		String userContinue = "y";
		do {
			lists = displayMenu(lists);
			String pickedItem = getStringMatchingRegex(sc, "What Item would you like to order: ", lists);
			if(items.contains(pickedItem)) {
				quantity.set(items.indexOf(pickedItem), quantity.get(items.indexOf(pickedItem)) + 1);
			}
			else {items.add(pickedItem);			
				price.add(lists.get(pickedItem));
				quantity.add(1);
			}
			System.out.println("Would you like to order anything else? (y/n): ");
			userContinue = sc.next();
			sc.nextLine();
		} while (userContinue.equalsIgnoreCase("y"));
		
		 	System.out.println("Here's what you got: ");
			System.out.printf(" Items\t Price\t  Quantity%n");
			System.out.printf(" =========================%n");
			getChartList(items, price, quantity); //print out the items in the chart
			averagePrice(price,quantity);
			getMaxPrice(price, items);
			getMinPrice(price, items);
			
			
			
			
		System.out.println("Thank you for shopping here!");
		
	}
	
	public static void getMinPrice(ArrayList<Double> price, ArrayList<String> items) {
		double minPrice = 1000.0;
		for(int i = 0; i < price.size(); i++) {
			if(price.get(i) < minPrice) {
				minPrice = price.get(i);
			}
		}
		int indexOfMin = price.indexOf(minPrice);
		System.out.println("The least expensive item is " + items.get(indexOfMin) + " at a price of $"
					+ price.get(indexOfMin) + ".");
	}
	
	
	public static void getMaxPrice(ArrayList<Double> price, ArrayList<String> items) {
		double maxPrice = 0;
		for(int i = 0; i < price.size(); i++) {
			if(price.get(i) > maxPrice) {
				maxPrice = price.get(i);
			}
		}
		int indexOfMin = price.indexOf(maxPrice);
		System.out.println("The most expensive item is " + items.get(indexOfMin) + " at a price of $"
					+ price.get(indexOfMin) + ".");
	}
		
		
	public static void averagePrice(ArrayList<Double> price, ArrayList<Integer> quantity) {
		double average = 0;
		double sumP = 0;
		Integer sumQ = 0;
		for(Double num : price) { 
			sumP += num * quantity.get(price.indexOf(num)) ;
			//System.out.printf("%.2f is sumP %n",sumP);//test
		}
		//System.out.println(quantity); //test
		for(Integer qty : quantity) {
			sumQ += qty;
			}
		average = sumP/sumQ;
		System.out.printf("You total is %.2f and avaerage price per item is %.2f. %n", sumP, average);
	}
	
	public static void getChartList(ArrayList<String> items, ArrayList<Double> price, ArrayList<Integer> quantity) {
		for(int i = 0; i < items.size(); i++) {
			for(int j = 0; j < price.size(); j++) {
				for(int k = 0; k < quantity.size(); k++) {
					
					System.out.printf(" %-5s\t $%.2f\t   %d%n",items.get(i), price.get(i), quantity.get(i));
					break;
				}
				break;
			}
		}
	}
	public static String getStringMatchingRegex(Scanner sc, String prompt, HashMap<String, Double> ItemsAndPrices) {
		boolean isValid = false;
		String input ;
		
		do {
			input = getString(sc, prompt);
			if (ItemsAndPrices.containsKey(input)) {
				isValid = true;
			} else {
				System.out.println("Sorry, we do not have this item. ");

			}
		} while (!isValid);

		return input;
	}
	
	public static String getString(Scanner sc, String prompt) {
		System.out.print(prompt);
		String s = sc.next(); // read user entry
		sc.nextLine(); // discard any other data entered on the line
		return s;
	}
	
	public static HashMap<String, Double> displayMenu(HashMap<String, Double> lists) {
		lists.put("HH", 6.99);// I named every item super simple just for the convinience of testing and grading. ;)
		lists.put("II", 11.49);
		lists.put("GG", 1.99);
		lists.put("KK", 5.99);
		lists.put("AA", 4.79);
		lists.put("BB", 0.99);
		lists.put("CC", 3.99);
		lists.put("DD", 1.99);
		lists.put("EE", 14.79);

		System.out.printf(" Items\t Price%n");
		System.out.printf(" ===== \t========%n");
		for (Map.Entry<String, Double> itemAndPriceList : lists.entrySet()) {
			String item = itemAndPriceList.getKey();
			Double price = itemAndPriceList.getValue();
			System.out.printf("  %-6s $%.2f %n", item, price);
		}
		return lists;
	}
}
