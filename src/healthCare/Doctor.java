package healthCare;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.Date;

/**
 * 
 * @author Edmond
 * @author
 */
public class Doctor extends Staff {
    private ArrayList<Patient> listOfPatient;
    private Set<Date> scheduledDates;

     public Doctor(String name, int age){
        super(name, age);
        this.listOfPatient = new ArrayList<>();
        this.scheduledDates = new HashSet<>();
     }

     // Default constructor
     public Doctor() {
        super();
        this.name = "";
        this.age = 0;
        this.listOfPatient = new ArrayList<>();
        this.scheduledDates = new HashSet<>();

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

    public ArrayList<Patient>  getPatientsList(){
        return this.listOfPatient;
    }

    /* #--- Doctor Methods ---# */

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
                }   
            }
        }
     }

     /** Add one patient to the list of patient
      * 
      * @param patient
      * @return
      */
     public String admissionOfPatient(Patient patient){
          listOfPatient.add(patient);
          return "Added" + patient;
     }

    public String givePrescriptionRefill(Medication medication, int amount, Patient patient){
        return medication.requestRefill(patient, amount);
    }

    @Override
    public String toString(){
        return "Doctor: " + super.toString();
    }

    // method to update schedule
    public void updateSchedule(Date date) {
        scheduledDates.add(date); // Add the date to the scheduled dates set
    }

    // getter for scheduledDates
    public Set<Date> getScheduledDates() {
        return scheduledDates; // Retrieve scheduled dates if needed
    }

}
