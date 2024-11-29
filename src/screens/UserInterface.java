package screens;

import java.util.Scanner;
import healthCare.Hospital;
import healthCare.Patient;
import healthCare.Doctor;

public class UserInterface implements screen {

    private Hospital hospital;
    private Scanner scanner = new Scanner(System.in);
    private boolean exit = false;

    public UserInterface(Hospital hospital) {
        this.hospital = hospital;
    }

    @Override
    public boolean display() {
        while (!exit) {
            System.out.println("\nWelcome to the Healthcare System");
            System.out.println("Please type 'patient' or 'doctor' to enter the respective menu, or 'exit' to quit:");
            String choice = scanner.nextLine().toLowerCase();

            switch (choice) {
                case "patient":
                    selectPatient();
                    break;
                case "doctor":
                    selectDoctor();
                    break;
                case "exit":
                    exit = true;
                    System.out.println("Exiting the Healthcare System.");
                    break;
                default:
                    System.out.println("Invalid input. Please try again.");
            }
        }
        return exit;
    }

    private void selectPatient() {
        System.out.println("Enter the patient's name:");
        String patientName = scanner.nextLine();

        Patient patient = hospital.getPatient(patientName);
        if (patient != null) {
            PatientScreen patientScreen = new PatientScreen(patient);
            patientScreen.display();
        } else {
            System.out.println("Patient not found.");
        }
    }

    private void selectDoctor() {
        System.out.println("Enter the doctor's name:");
        String doctorName = scanner.nextLine();

        Doctor doctor = hospital.getDoctor(doctorName);
        if (doctor != null) {
            DoctorScreen doctorScreen = new DoctorScreen(doctor);
            doctorScreen.display();
        } else {
            System.out.println("Doctor not found.");
        }
    }
}
