package healthCare;

import java.util.ArrayList;

import screens.UserInterface;

public class Runner {

	public static void main(String[] args) {

		ArrayList<Hospital> hospitalList = new ArrayList<Hospital>();

		Hospital sampleHospital = new Hospital("Sample Hospital", "123 Subway Ave", "555-5555");

		Doctor doctor1 = new Doctor("Danny Phan", 40);
		sampleHospital.addDoctor(doctor1);

		Patient patient1 = new Patient("John Smith", 35, "Cough", doctor1);
		sampleHospital.addPatient(patient1);
		doctor1.getPatientsList().add(patient1);

		hospitalList.add(sampleHospital);


		UserInterface screen = new UserInterface(hospitalList);
		screen.welcomeScreen();
	}

}
