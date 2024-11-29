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

		while(!exit)
		{
			System.out.println("Please choose the Hospital you would like to visit:");

			for(int i = 1; i <= hospitalList.size(); i++)
			{
				System.out.println(i + ": " + hospitalList.get(i-1).getName());
			}

			inputString = inputScanner.next();

			if(inputString.equalsIgnoreCase("exit") || exit)
			{
				exit = true;
				System.out.println("Shutting down");
				break;
			}

			try
			{
				int input = Integer.parseInt(inputString);
				for(int i = 1; i <= hospitalList.size(); i++)
				{
					if(i == input)
					{
						//to be coded --> we add methods accessing selected hospital here
						HospitalScreen hospitalScreen = new HospitalScreen(hospitalList.get(i-1));
						exit = hospitalScreen.display();
					}
					else {
						System.out.println("Invalid input. Please enter a number or 'exit' to quit.");
					}
				}
			} catch(NumberFormatException e)
			{
				System.out.println("Invalid input. Please enter a number or 'exit' to quit.");
			}
		}
		inputScanner.close();
	}
}
