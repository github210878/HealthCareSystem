package screens;

import java.util.Scanner;

import healthCare.HealthCareSystem;
import healthCare.Hospital;

import java.util.ArrayList;

public class UserInterface {
	
	Scanner inputScanner;
	HealthCareSystem healthManager;
	String inputString;
	boolean exit;

	public UserInterface(ArrayList<Hospital> list)
	{
		inputScanner = new Scanner(System.in);
		healthManager = new HealthCareSystem(list);
		inputString = ""; 
		exit = false;
	}
	
	public void welcomeScreen()
	{
		System.out.println("Welcome to the Healthcare Network");
		
		ArrayList<Hospital> hospitalList = healthManager.hospitalList;
		
		while (!exit) {
            System.out.println("\nPlease choose the Hospital you would like to visit:");

            // Display list of hospitals
            for (int i = 1; i <= hospitalList.size(); i++) {
                System.out.println(i + ": " + hospitalList.get(i - 1).getName());
            }
            System.out.println("Type 'exit' to exit the program.");

            inputString = inputScanner.next();

            // Handle numerical input for hospital selection
            try {
                int input = Integer.parseInt(inputString);

                if (input >= 1 && input <= hospitalList.size()) {
                    // Open the selected hospital's menu
                    HospitalScreen hospitalScreen = new HospitalScreen(hospitalList.get(input - 1));
                    boolean backToMenu = !hospitalScreen.display();  // display() returns false on "back"

                    if (backToMenu) {
                        continue;  // Bring the user back to the hospital selection menu
                    }
                } else {
                    System.out.println("Invalid selection. Please enter a number from the list.");
                }
            } catch (NumberFormatException e) {
                if (inputString.equalsIgnoreCase("exit")) {
                    exit = true;
                    System.out.println("Shutting down...");
                } else {
                    System.out.println("Invalid input, please try again.");
                }
            }
        }
        inputScanner.close();
    }
}