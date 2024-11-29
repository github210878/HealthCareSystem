package screens;

import java.util.Scanner;

import healthCare.Doctor;
import healthCare.Hospital;
import healthCare.Patient;

public class HospitalScreen implements screen {

	Hospital hospital;
	Scanner inputScanner;
	String input;
	boolean exit;

	public HospitalScreen(Hospital h) {
		hospital = h;
	}

	public boolean display() {

		exit = false;	// initialize the flag to false
		while (!exit) {
			inputScanner = new Scanner(System.in);
			System.out.println("Input 'patient' for the patient page or input 'doctor' for the employee page. At any time type 'back' to go back or 'exit' to quit.");

			input = inputScanner.nextLine();

			switch (input.toLowerCase().trim()) {
				case "doctor":
					handleDoctor();
					break;
				case "patient":
					handlePatient();
					break;
				case "exit":
					exitProgram(input);
				case "back":
                    return false;
				default:
					System.out.println("Invalid input. Please try again");
			}
		}
		return true;
	}

	// add exit program method
	public void exitProgram(String userInput) {
		if (userInput.trim().equalsIgnoreCase("exit")) {
			inputScanner.close(); // close the scanner
			System.out.println("Shutting down");
			System.exit(0); // exit the program
		}
	}

	// method to handle 'patient' as user input
	public void handlePatient() {
		// initialize attributes for patient
		String name;
		int age;
		String sick;
		Doctor doctor = null;
		Patient patient = null;

		System.out.println("Enter your name: ");
		input = inputScanner.nextLine().trim();
		exitProgram(input);

		// check if hospital already has user input patient
		if (hospital.getPatient(input) != null) {
			// use that Patient instance if found in hospital
			Patient oldPatient = hospital.getPatient(input);
			PatientScreen oldPatientScreen = new PatientScreen(oldPatient);
			exit = oldPatientScreen.display();

		// create a new Patient instance if not found in hospital
		} else {
			name = input;

			// error checking for age input
			age = 0;
			while (age <= 0) {
				System.out.println("Enter your age: ");
				String ageInput = inputScanner.nextLine().trim();
				exitProgram(ageInput.trim());
				try {
					age = Integer.parseInt(ageInput);
					if (age <= 0) {
						System.out.println("Invalid age. Age must be a positive number. Please try again.");
						break; // valid age entered
					}
				} catch (NumberFormatException e) {
					System.out.println("Invalid input. Please enter a valid age (a positive integer).");
				}
			}

			// prompt for user input sickness
			System.out.println("Enter your sickness: ");
			input = inputScanner.nextLine().trim();
			exitProgram(input);
			sick = input;

			while (hospital.getDoctor(input) == null) {
				// display list of doctors before prompting the user to input doctor
				hospital.displayDoctors();

				System.out.println("Enter your doctor: ");
				input = inputScanner.nextLine().trim();
				exitProgram(input);
				if (hospital.getDoctor(input) == null) {
					System.out.println("We don't have that doctor. Please try again.");
				} else {
					doctor = hospital.getDoctor(input);
					// create a patient based on user inputs
					patient = new Patient(name, age, sick, doctor);
					// add the patient to the hospital
					hospital.addPatient(patient);
					doctor.getPatientsList().add(patient);
					PatientScreen patientScreen = new PatientScreen(patient);
					exit = patientScreen.display();
				}
			}
		}
	}

	// method to handle 'doctor' as user input
	public void handleDoctor() {
		System.out.println("Enter your name: ");
		input = inputScanner.nextLine().trim();

		exitProgram(input);

		// check if input doctor found in hospital
		if (hospital.getDoctor(input) != null) {
			// use that Doctor instance if found in hospital
			Doctor oldDoctor = hospital.getDoctor(input);
			DoctorScreen oldDoctorScreen = new DoctorScreen(oldDoctor);
			exit = oldDoctorScreen.display();

		} else {
			// add new doctor
			Doctor doctor = new Doctor();
			doctor.setName(input);

			// error checking for age input
			int age = 0;
			while (age <= 0) {
				System.out.print("Enter your age: ");
				String ageInput = inputScanner.nextLine().trim();
				exitProgram(ageInput);
				try {
					age = Integer.parseInt(ageInput);
					if (age <= 0) {
						System.out.println("Invalid age. Age must be a positive number. Please try again.");
						break; // valid age entered
					}
				} catch (NumberFormatException e) {
					System.out.println("Invalid input. Please enter a valid age (a positive integer).");
				}
				exit = true;
				doctor.setAge(age);
			}
			// create a doctor based on user inputs
			hospital.addDoctor(doctor);
			DoctorScreen doctorScreen = new DoctorScreen(hospital.getDoctor(input));
			exit = doctorScreen.display();
		}
	}
}
