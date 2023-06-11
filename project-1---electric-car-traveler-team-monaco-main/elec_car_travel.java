// CPSC 535 Project 1: Electric Car Traveler
// Group Members: John Tu, Rosa Cho, and Sayali Ghorpade
import java.util.ArrayList; // Needed to handle ArrayLists.
import java.util.Scanner; // Needed to get user input.

public class elec_car_travel {

	public static void electric_car_travel(int capacity, int number_of_cities) {
		Scanner input_get = new Scanner(System.in);

		// First, verify if capacity and number of cities are within the valid range.
		// If they are not, then do not proceed further.
		if ((capacity < 250) || (capacity > 350)) {
			System.out.println("The capacity of the electric vehicle is not within the valid range.");
			System.out.println("The valid range is between 250 and 350 inclusively.");
			System.exit(-1);
		}
		if ((number_of_cities <= 3) || (number_of_cities >= 20)) {
			System.out.println("The number of cities is not within the valid range.");
			System.out.println("The valid range is between 3 and 20 exclusively.");
			System.exit(-1);
		}

		// First, declare the following array lists: a list of cities,
		// a list of distances between two cities, and the output list.
		ArrayList<String> city_list = new ArrayList<String>();
		ArrayList<Integer> dist_list = new ArrayList<Integer>();
		ArrayList<String> output_list = new ArrayList<String>();

		// Ask the user to input each city name and the distances between the two cities.
		// For the distances, also verify whether they are within the valid ranges.
		for (int i = 0; i < number_of_cities; i++) {
			System.out.print("Enter the city's name: ");
			String city_name = input_get.nextLine();
			city_list.add(city_name);
		}

		for (int j = 0; j < (number_of_cities - 1); j++) {
			System.out.printf("Enter the distance between City %s and City %s: ", city_list.get(j),
					city_list.get(j + 1));
			int city_dist = input_get.nextInt();
			dist_list.add(city_dist);
			if ((dist_list.get(j) <= 10) || (dist_list.get(j) >= (capacity / 2))) {
				System.out.println("The distance between cities is not within the valid range.");
				System.out.println("The valid range is between 10 and half the capacity exclusively.");
				System.exit(-1);
			}
		}

		// Print out the list of cities and the distances between each two cities.
		for (int k = 0; k < dist_list.size(); k++) {

			System.out.printf("Distance between City %s and City %s: %d", city_list.get(k), city_list.get(k + 1),
					dist_list.get(k));
			System.out.println();

		}

		output_list.add(city_list.get(0)); // The first city will be added into the list.

		// As long as the last destination is not reached yet, traverse through
		// the list and add the city in a way that minimizes the number of stops needed.
		int refill = capacity; // For every stop made, be sure to refill the capacity back to default.
		boolean final_dest = false; // This boolean value indicates if the car reaches its destination.
		int curr_loc_num = 0;
		// Keep track of the index where the car stopped to refuel. Index remains the
		// same incremented index when it is traveling.

		while (final_dest != true) {
			// Is current value less than or equal to the very last distance value?
			if ((curr_loc_num <= dist_list.size() - 1)) {
				// Check if the current location is at the very first city.
				if (curr_loc_num == 0) {
					System.out.printf("Begin traveling from City %s to City %s. Current Capacity is %d.",
							city_list.get(curr_loc_num), city_list.get(curr_loc_num+1), capacity);
					System.out.println();
				}

				// Expend the number of miles needed to travel from one city to another from the fuel capacity.
				capacity = capacity - dist_list.get(curr_loc_num);
				
				// If the current capacity is less than the distance required to backtrack
				// to the previous city, then stop and refuel the car.
				if (capacity < dist_list.get(curr_loc_num)) {
					capacity = refill;
					output_list.add(city_list.get(curr_loc_num));
					System.out.printf("Insufficient capacity to backtrack. Refueling at %s.", city_list.get(curr_loc_num));
					System.out.println();
					System.out.printf("After refueling, current capacity is now %d at City %s.",
							capacity, city_list.get(curr_loc_num));
					city_list.get(curr_loc_num);// Previous city
					dist_list.get(curr_loc_num);// Previous city
					System.out.println();
				}

				// Travel to the next city if we didn't stop to refuel the car.
				if (capacity != refill) {
					System.out.printf("Traveling from City %s to City %s. Current capacity is %d.",
							city_list.get(curr_loc_num), city_list.get(curr_loc_num + 1), capacity);
					System.out.println();
					System.out.printf("Current capacity is now %d after arriving at City %s.", capacity,
							city_list.get(curr_loc_num + 1));
					System.out.println();
					System.out.println();
					curr_loc_num++;

				}

			}

			// We've reached the final destination, so set to true to break out of the while loop.
			if (city_list.get(curr_loc_num) == city_list.get(city_list.size() - 1)) {
				output_list.add(city_list.get(curr_loc_num));
				final_dest = true;
			}
		}
		// Return the resulting list.
		System.out.println("Destination arrived.");
		System.out.print("Ideal route will be ");
		System.out.print(output_list);
	}

	public static void main(String[] args) {
		Scanner user_input = new Scanner(System.in);

		// Ask the user to enter the mileage for the electric vehicle
		// and the number of cities.
		System.out.print("Enter the mileage for the electric vehicle. ");
		int mileage = user_input.nextInt();
		System.out.print("Enter the number of cities. ");
		int num_cities = user_input.nextInt();

		// Call the function above main.
		electric_car_travel(mileage, num_cities);
	}

}
