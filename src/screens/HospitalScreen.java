package screens;

import java.time.LocalDate;
import java.util.Scanner;
import healthCare.Doctor;
import healthCare.Hospital;
import healthCare.Patient;
import healthCare.Reservation;

public class HospitalScreen implements screen{

	private Hospital hospital;
	private Scanner inputScanner;
	
	public HospitalScreen(Hospital h)
	{
		this.hospital = h;
		this.inputScanner = new Scanner(System.in);
	}
	
	public boolean display()
	{
		
		boolean exit = false;
		while(!exit){
			System.out.println("\n--- Hospital Menu ---");
			System.out.println("1. Access Doctor Page");
			System.out.println("2. Access Patient Page");
			System.out.println("3. Add New Doctor");
			System.out.println("4. Add New Patient");
			System.out.println("5. Add New Reservation");
			System.out.println("Type 'back' to go back or 'exit' to exit.");
			
			String input = inputScanner.nextLine().trim();
        
        // Only show "Invalid input" if the input isn't "back" or "exit"
        if (input.equalsIgnoreCase("back")) {
            return false;
        } else if (input.equalsIgnoreCase("exit")) {
            exit = true;
        } else {
            switch (input) {
                case "1":
                    accessDoctorPage();
                    break;
                case "2":
                    accessPatientPage();
                    break;
                case "3":
                    addNewDoctor();
                    break;
                case "4":
                    addNewPatient();
                    break;
                case "5":
                    addNewReservation();
                    break;
                default:
                    System.out.println("Invalid input, please try again.");
                    break;
            }
        }
    }
    return true;
	}
	
	private void accessDoctorPage() {
		System.out.println("Enter doctor's name: ");
		String name = inputScanner.nextLine();
		Doctor doctor = hospital.getDoctor(name);
		if (doctor != null) {
			DoctorScreen doctorScreen = new DoctorScreen(doctor);
			doctorScreen.display();
		} else {
			System.out.println("Doctor not found.");
		}
	}
	
	private void accessPatientPage() {
		System.out.println("Enter patient's name:");
        String name = inputScanner.nextLine();
        Patient patient = hospital.getPatient(name);
        if (patient != null) {
            PatientScreen patientScreen = new PatientScreen(patient);
            patientScreen.display();
        } else {
            System.out.println("Patient not found.");
        }
    }
	
	private void addNewDoctor() {
		System.out.println("Enter new doctor's name:");
        String name = inputScanner.nextLine();
        System.out.println("Enter new doctor's age:");
        int age = Integer.parseInt(inputScanner.nextLine());

        Doctor newDoctor = new Doctor(name, age);
        hospital.addDoctor(newDoctor);
        System.out.println("Doctor " + name + " added successfully.");
    }
	
	private void addNewPatient() {
        System.out.println("Enter new patient's name:");
        String name = inputScanner.nextLine();
        System.out.println("Enter new patient's age:");
        int age = Integer.parseInt(inputScanner.nextLine());
        System.out.println("Enter patient's reason for visit:");
        String reason = inputScanner.nextLine();

        System.out.println("Assign a doctor for the patient:");
        String doctorName = inputScanner.nextLine();
        Doctor assignedDoctor = hospital.getDoctor(doctorName);
        
        if (assignedDoctor != null) {
            Patient newPatient = new Patient(name, age, reason, assignedDoctor);
            hospital.addPatient(newPatient);
            assignedDoctor.addPatient(newPatient);
            System.out.println("Patient " + name + " added successfully.");
        } else {
            System.out.println("Doctor not found. Add doctor first or assign later.");
        }
    }
	
	// New method for adding a reservation
    private void addNewReservation() {
        System.out.println("Enter reservation date (YYYY-MM-DD):");
        String dateInput = inputScanner.nextLine();
        LocalDate date = LocalDate.parse(dateInput); // Parse the date

        System.out.println("Enter patient's name:");
        String patientName = inputScanner.nextLine();
        Patient patient = hospital.getPatient(patientName);
        if (patient == null) {
            System.out.println("Patient not found.");
            return;
        }

        System.out.println("Enter reason for visit:");
        String reason = inputScanner.nextLine();

        System.out.println("Enter doctor's name:");
        String doctorName = inputScanner.nextLine();
        Doctor doctor = hospital.getDoctor(doctorName);
        if (doctor == null) {
            System.out.println("Doctor not found.");
            return;
        }

        // Create the reservation
        Reservation reservation = new Reservation(date, patient, reason, doctor);
        Reservation.addReservation(reservation);
        System.out.println("Reservation added for " + patient.getName() + " with " + doctor.getName() + " on " + date);
    }
}
			
