package screens;

import java.util.Scanner;
import healthCare.Patient;
import healthCare.Medication;

public class PatientScreen implements screen {

    private Patient patient;
    private Scanner inputScanner = new Scanner(System.in);
    private boolean exit = false;

    public PatientScreen(Patient patient) {
        this.patient = patient;
    }

    @Override
    public boolean display() {
        while (!exit) {
            if (patient.getRefillNotification()) {
                System.out.println("It's time to refill your medication!\n");
            }

            System.out.println("Hello " + patient.getName() + ", how can we help you?");
            System.out.println("1. Check prescription status");
            System.out.println("2. View Doctor's contact information");
            System.out.println("3. Back to main menu");

            String input = inputScanner.nextLine();

            switch (input) {
                case "1":
                    viewPrescription();
                    break;
                case "2":
                    viewDoctorContact();
                    break;
                case "3":
                    exit = true;
                    break;
                default:
                    System.out.println("Unrecognized input, please try again.");
            }
        }
        return exit;
    }

    private void viewPrescription() {
        if (patient.getPatientPrescription().isEmpty()) {
            System.out.println("You currently have no prescriptions.");
        } else {
            for (Medication m : patient.getPatientPrescription()) {
                System.out.println("You have a prescription for " + m.getMedicationName());
                System.out.println("Info: " + m.medicationInfo());
            }
        }
    }

    private void viewDoctorContact() {
        System.out.println("Doctor: Dr. " + patient.getDoctor().getName());
    }
}
