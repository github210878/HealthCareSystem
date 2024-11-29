package screens;

import healthCare.Hospital;
import healthCare.Patient;
import healthCare.Doctor;
import java.util.Scanner;

public class HospitalScreen implements screen {

    private Hospital hospital;
    private boolean exit = false;
    private Scanner scanner = new Scanner(System.in);

    public HospitalScreen(Hospital hospital) {
        this.hospital = hospital;
    }

    @Override
    public boolean display() {
        while (!exit) {
            System.out.println("\nWelcome to " + hospital.getName() + " - Please choose an option:");
            System.out.println("1. View Patients");
            System.out.println("2. View Doctors");
            System.out.println("3. Add Patient");
            System.out.println("4. Add Doctor");
            System.out.println("5. Go Back");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    viewPatients();
                    break;
                case "2":
                    viewDoctors();
                    break;
                case "3":
                    addPatient();
                    break;
                case "4":
                    addDoctor();
                    break;
                case "5":
                    return false;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
        return exit;
    }

    private void viewPatients() {
        System.out.println("List of Patients:");
        for (Patient patient : hospital.getPatients()) {
            System.out.println(patient.getName());
        }
    }

    private void viewDoctors() {
        System.out.println("List of Doctors:");
        for (Doctor doctor : hospital.getDoctors()) {
            System.out.println(doctor.getName());
        }
    }

    private void addPatient() {
        System.out.print("Enter patient name: ");
        String name = scanner.nextLine();
        System.out.print("Enter patient age: ");
        int age = Integer.parseInt(scanner.nextLine());
        System.out.print("Enter patient diagnosis: ");
        String diagnosis = scanner.nextLine();
        System.out.print("Assign a doctor: ");
        String doctorName = scanner.nextLine();

        Doctor assignedDoctor = hospital.getDoctor(doctorName);
        if (assignedDoctor == null) {
            System.out.println("Doctor not found.");
        } else {
            Patient newPatient = new Patient(name, age, diagnosis, assignedDoctor);
            if (hospital.addPatient(newPatient)) {
                System.out.println("Patient added successfully.");
            } else {
                System.out.println("Patient already exists.");
            }
        }
    }

    private void addDoctor() {
        System.out.print("Enter doctor name: ");
        String name = scanner.nextLine();
        System.out.print("Enter doctor age: ");
        int age = Integer.parseInt(scanner.nextLine());

        Doctor newDoctor = new Doctor(name, age);
        if (hospital.addDoctor(newDoctor)) {
            System.out.println("Doctor added successfully.");
        } else {
            System.out.println("Doctor already exists.");
        }
    }
}
