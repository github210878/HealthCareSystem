package healthCare;

import java.sql.Date;
import java.util.ArrayList;
import java.time.LocalDate;

public class Patient implements refillNotification{
    private String name;
    private int age;
    private ArrayList<Medication> prescription;
    private String sick;
    private boolean refillNotification;
    private Doctor doctor;
    private ArrayList<Reservation> reservationList;

    public Patient(String name, int age, String sick, Doctor doctor){
        this.name = name;
        this.age = age;
        this.sick = sick;
        this.refillNotification = false;
        this.doctor = doctor;
        prescription = new ArrayList<Medication>();
        reservationList = new ArrayList<Reservation>();
    }

    public Patient() {
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
    
    public Doctor getDoctor()
    {
    	return doctor;
    }

    public int getAge(){
        return this.age;
    }

    public ArrayList<Medication> getPatientPrescription(){
        return this.prescription;
    }

    public ArrayList<Reservation> getPatientReservations(){
        return this.reservationList;
    }

    public String getSick(){
        return this.sick;

    }

    public void setSick(String sickness){
        this.sick = sickness;
    }

    public boolean getRefillNotification(){
        return this.refillNotification;
    }

    public void setRefillNotification(boolean refillNotification){
        this.refillNotification = refillNotification;
    }

    /* #--- Patient Methods ---# */

    /** Add a reservation to the list
     * 
     * @param 
     */
    public void reserveAppointment(int year, int month, int day, String reason){
        LocalDate reservedDate = LocalDate.of(year, month, day);
        Reservation reservation = new Reservation(reservedDate, this, reason, doctor);
        this.reservationList.add(reservation);

    }

    /** Remove a reservation from the list
     * 
     * @param reservation the one reservation that need to be canceled
     */
    public void cancelReservation(Reservation reservation){
        if(!reservationList.isEmpty()){
            for (int i = 0; i < reservationList.size(); i++){
                if (reservationList.get(i).equals(reservation) == true){
                    reservationList.remove(i);
                }
            }
        } 
    }

    /** Add a medication to the prescription list
     * 
     * @param medication new medication
     */
    public void addMedication(Medication medication){
        this.prescription.add(medication);
    }
    
    /** Remove a prescription from the list
     * 
     * @param medication
     */
    public void removeMedication(Medication medication){
        for (int i = 0; i < prescription.size(); i++){
            if (prescription.get(i).equals(medication) == true){
                prescription.remove(i);
            }
        }
    }
    /** A toggle method on the refill notification
     * 
     * @return a message on whether the patient have the refill notification turn on or off
     */
    @Override
    public String refillNoti(){
        if (refillNotification == true) {
            refillNotification = false;
            return "Notification has been turned off";
        } else {
            refillNotification = true;
            return "We will notify you for your next refill";
        }
    }

    /** Give some information about the patient 
     * 
     * @return informaton about the patient
     */
    public String viewProfile(){
        String profile = this.name + ": age " + this.age + ", sickness: " + this.sick + ", Doctor: " + this.doctor
        + ", refill noification: " + this.refillNotification;
        return profile;
    }

    // add setter for patient's age
    public void setAge(int age) {
        this.age = age;
    }

}
