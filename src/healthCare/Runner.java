package healthCare;

import java.util.ArrayList;

import screens.UserInterface;

public class Runner {

	public static void main(String[] args) {
		
		ArrayList<Hospital> hospitalList = new ArrayList<Hospital>();

		Hospital sampleHospital = new Hospital("Sample Hospital", "123 Subway Ave", "555-5555");

		Doctor doctor1 = new Doctor("Dr. Danny Phan", 40);
		sampleHospital.addDoctor(doctor1);

		Patient patient1 = new Patient("John Smith", 35, "Cough", doctor1);
		sampleHospital.addPatient(patient1);

		hospitalList.add(sampleHospital);

		// Add additional hospitals
        Hospital downtownClinic = new Hospital("Downtown Clinic", "456 Main St", "555-1234");
        hospitalList.add(downtownClinic);

        Hospital eastsideMedicalCenter = new Hospital("Eastside Medical Center", "789 Oak Ave", "555-6789");
        hospitalList.add(eastsideMedicalCenter);

		UserInterface screen = new UserInterface(hospitalList);
		screen.welcomeScreen();
	}

}
