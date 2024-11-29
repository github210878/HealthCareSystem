package screens;

import healthCare.Doctor;
import java.util.Scanner;

public class DoctorScreen implements screen{
	
	Doctor doctor;
	Scanner scanner;

	public DoctorScreen(Doctor d)
	{
		doctor = d;
		scanner = new Scanner(System.in);

	}

	@Override
	public boolean display() {
		System.out.println("Welcome, Dr. " + doctor.getName());
		boolean exit = false;
		while (!exit) {
			System.out.println("Choose an option: \n1. View Patients\n2. Update Schedule\n3. Back");
			String input = scanner.nextLine();
			if(input.equalsIgnoreCase("exit"))
			{
				return true;
			}
			switch (input) {
				case "1":
					System.out.println("Patients: ");
					break;
				case "2":
					System.out.println("Enter new schedule:");
					String schedule = scanner.nextLine();
					//doctor.updateSchedule(schedule);
					break;
				case "3":
					exit = true;
					return false;
				default:
					System.out.println("Invalid option. Please try again.");
					break;
			}
			
			
		}

		// TODO Auto-generated method stub
		return true;
	}

}
