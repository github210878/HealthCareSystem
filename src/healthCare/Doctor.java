package healthCare;

import java.util.ArrayList;

/**
 * 
 * @author Edmond
 * @author
 */
public class Doctor extends Staff {

    private String contact;
    private String schedule;
    private ArrayList<Patient> listOfPatient;

     public Doctor(String name, int age){
        super(name, age);
        this.listOfPatient = new ArrayList<>();
     }
    
     public Doctor() {
    	 super();
        this.name = "";
        this.age = 0;
        // Default constructor
    }

    /* #--- Getter and Setters ---# */ 
    
    public String getName(){
        return this.name;
    }

    public void setName(String name){
        this.name = name;
    }

    public int getAge(){
        return this.age;
    }

    public void setAge(int age){
        this.age = age;
    }

    public String getContact() {
        return this.contact;
    }
    
    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getSchedule() {
        return this.schedule;
    }
    
    public void updateSchedule(String schedule) {
        this.schedule = schedule;
    }

    public ArrayList<Patient>  getPatientsList(){
        return this.listOfPatient;
    }

    /* #--- Doctor Methods ---# */

    public Patient getPatient(String patientName) {
        for (Patient patient : listOfPatient) {
            if (patient.getName().equalsIgnoreCase(patientName)) {
                return patient;
            }
        }
        return null; // Return null if the patient is not found
    }
    
    /** Add a prescription for the patient
     * 
     * @param patient
     * @param medication
     */
     public void prescribeMedication(Patient patient, Medication medication){
          patient.addMedication(medication);
     }

     /** Update the diagnosis of the Patient
      * 
      * @param patient
      */
     public void updateDiagnosis(Patient patient, String diagnosis){
        patient.setSick(diagnosis);
     }
     /** Remove one Patient off the list
      * 
      * @param patient
      * @return message of a patient removed
      */
     public void removePatient(Patient patient){
        if (!listOfPatient.isEmpty()) {
            for (int i = 0; i < listOfPatient.size(); i++){
                if (listOfPatient.get(i).equals(patient)) {
                    listOfPatient.remove(i);
                    return; // Stop once we find and remove the patient
                }   
            }
        }
     }

     /** Add one patient to the list of patient
      * 
      * @param patient
      * @return
      */
     public void addPatient(Patient patient) {
    	 listOfPatient.add(patient);
     }

    public String givePrescriptionRefill(Medication medication, int amount, Patient patient){
        return medication.requestRefill(patient, amount);
    }

    @Override
    public String toString(){
        return "Doctor: " + super.toString() + ", Patients: " + listOfPatient.size();
    }
	

}
