package healthCare;

import java.util.ArrayList;

import screens.UserInterface;

public class Runner {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Hello World");
		
		ArrayList<Hospital> hospitalList = new ArrayList<Hospital>();

		Hospital sampleHospital = new Hospital("Sample Hospital", "123 Subway Ave", "555-5555");

		Doctor doctor1 = new Doctor("Dr. Danny Phan", 40);
		sampleHospital.addDoctor(doctor1);

		Patient patient1 = new Patient("John Smith", 35, "Cough", doctor1);
		sampleHospital.addPatient(patient1);

		hospitalList.add(sampleHospital);


		UserInterface screen = new UserInterface(sampleHospital); 
        screen.display(); // Use display instead of welcomeScreen
	}

}
