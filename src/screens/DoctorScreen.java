package screens;

import healthCare.Doctor;
import healthCare.Patient;

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
					viewPatients();
					break;
				case "2":
					updateSchedule();
					break;
				case "3":
					updateContactInfo();
					break;
				case "4":
					System.out.println("Logging out...");
					exit = true;
					break;
				case "5":
					exit = true;
					return false;
				default:
					System.out.println("Invalid option. Please try again.");
					break;
			}
		}
		return true;
	}

	// Method to view patients
	private void viewPatients() {
        System.out.println("Patients: " + doctor.getPatientsList());
        System.out.println("Would you like to view details of a specific patient? (yes/no)");
        String viewDetails = scanner.nextLine();
        if (viewDetails.equalsIgnoreCase("yes")) {
            System.out.println("Enter patient's name:");
            String patientName = scanner.nextLine();
            Patient patient = doctor.getPatient(patientName);
            if (patient != null) {
                System.out.println(patient.toString());
            } else {
                System.out.println("Patient not found.");
            }
        }
    }

	// Method to update schedule
	private void updateSchedule() {
        System.out.println("Current Schedule: " + doctor.getSchedule());
        System.out.println("Enter new schedule (or type 'cancel' to keep current schedule):");
        String schedule = scanner.nextLine();
        if (!schedule.equalsIgnoreCase("cancel")) {
            doctor.updateSchedule(schedule);
            System.out.println("Schedule updated.");
        } else {
            System.out.println("Schedule unchanged.");
        }
    }

	//Method to update contact information
    private void updateContactInfo() {
        System.out.println("Enter your new contact details:");
        String newContact = scanner.nextLine();
        doctor.setContact(newContact); // Assuming `setContact` method is implemented in Doctor class
        System.out.println("Contact updated.");
    }
}
