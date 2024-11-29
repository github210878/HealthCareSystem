package screens;

import java.util.Scanner;

import healthCare.Medication;
import healthCare.Patient;

public class PatientScreen implements screen{
	
	Scanner inputScanner;
	String input;

	Patient patient;
	public PatientScreen(Patient p)
	{
		patient = p;
		inputScanner = new Scanner(System.in);
	}

	@Override
	public boolean display() {

		boolean exit = false;

		while (!exit) {
			if (patient.getRefillNotification()) {
				System.out.println("It's time to refill your medication! \n");
			}

			System.out.println("Hello " + patient.getName() + ", How can we help you?");
			System.out.println("Type 'Prescription' to check prescription status");
			System.out.println("Type 'Doctor' to view your Doctor's contact information to set up an appointment");
			System.out.println("Type 'Back' to go back or 'Exit' to quit the program");

			input = inputScanner.next();

			switch (input.toLowerCase().trim()) {
				case "prescription":
					handlePrescription();
					break;
				case "doctor":
					System.out.println("Doctor: Dr. " + patient.getDoctor().getName());
					break;
				case "exit":
					inputScanner.close();
					System.out.println("Shutting down");
					System.exit(0);
					break;
				case "back":
					return false;
				default:
					System.out.println("Invalid input. Please try again.");
			}
		}
		return true;
	}

	// method to handle prescription
	public void handlePrescription() {
		if(patient.getPatientPrescription().isEmpty())
		{
			System.out.println("You currently have no prescriptions");
		} else
		{
			for(Medication m : patient.getPatientPrescription())
			{
				System.out.println("You have a prescription for " + m.getMedicationName());
				System.out.println("Info: ");
				System.out.println(m.medicationInfo());
			}
		}
	}
}
