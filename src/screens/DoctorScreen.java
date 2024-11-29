package screens;

import healthCare.Doctor;
import healthCare.Patient;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.Set;

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
			System.out.println("Choose an option or type 'exit' to quit: \n1. View Patients\n2. View Schedule\n3. Update Schedule\n4. Back");
			String input = scanner.nextLine();

			switch (input.toLowerCase().trim()) {
				case "1":
					handleViewPatients();
					break;
				// add View Schedule option for user understandability
				case "2":
					handleViewSchedule();
					break;
				case "3":
					handleUpdateSchedule();
					break;
				case "4":
					exit = true;
					return false;
				case "exit":
					exitProgram(input.toLowerCase().trim());
				default:
					System.out.println("Invalid option. Please try again.");
					break;
			}
		}
		return true;
	}

	// method to handle View Patients option
	public void handleViewPatients() {
		if (doctor.getPatientsList().isEmpty()) {
			System.out.println("You don't have any patients currently.");
		} else {
			System.out.println("List of your patients: ");
			for (Patient p : doctor.getPatientsList()) {
				System.out.println(p.getName());
			}
		}
	}

	// method to handle Update Schedule option
	public void handleUpdateSchedule() {
		// set format for date as mm-dd-yyyy
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
		dateFormat.setLenient(false);

		while (true) {
			System.out.println("Enter the date you want to update schedule for (MM-dd-yyyy): ");
			String dateInput = scanner.nextLine().trim();
			exitProgram(dateInput);

			try {
				Date date = dateFormat.parse(dateInput); // parse the input date

				// get today's date and set the time to 00:00:00 to compare only the date
				Date today = new Date();
				SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM-dd-yyyy");
				today = simpleDateFormat.parse(simpleDateFormat.format(today));

				// compare the input date with today's date
				if (date.before(today)) {
					System.out.println("Invalid date. The date cannot be in the past.");
					continue;
				}

				// call the method to update the schedule for the given date
				doctor.updateSchedule(date);
				System.out.println("Updated schedule: " + dateFormat.format(date));
				break;

			} catch (ParseException e) {
				System.out.println("Invalid date format. Please enter the date in MM-dd-yyyy.");
			}
		}
	}

	// method to handle View Schedule option
	public void handleViewSchedule() {
		Set<Date> schedule = doctor.getScheduledDates();
		if (schedule.isEmpty()) {
			System.out.println("No schedule found.");
		} else {
			System.out.println("Your schedule: ");
			SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
			for (Date date : schedule) {
				System.out.println(dateFormat.format(date));
			}
		}
	}

	// add exit program method
	public void exitProgram(String userInput) {
		if (userInput.trim().equalsIgnoreCase("exit")) {
			scanner.close(); // close the scanner
			System.out.println("Shutting down");
			System.exit(0); // exit the program
		}
	}
}
