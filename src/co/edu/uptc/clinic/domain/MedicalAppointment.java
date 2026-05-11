package co.edu.uptc.clinic.domain;

public class MedicalAppointment {

    private String idMedicalAppointment;
    private String timeAppointment;
    private Patient patient;
    private Doctor doctor;

    public MedicalAppointment() {
    }
    
    public MedicalAppointment(String idMedicalAppointment, String timeAppointment,
            Patient patient, Doctor doctor) {
    	this.idMedicalAppointment = idMedicalAppointment;
    	this.timeAppointment = timeAppointment;
    	this.patient = patient;
    	this.doctor = doctor;
    }
	
    public String getIdMedicalAppointment() {
        return idMedicalAppointment;
    }
    
    public void setIdMedicalAppointment(String idMedicalAppointment) {
        this.idMedicalAppointment = idMedicalAppointment;
    }
    
    public String getTimeAppointment() {
        return timeAppointment;
    }
    
    public void setTimeAppointment(String timeAppointment) {
        this.timeAppointment = timeAppointment;
    }
    
    public Patient getPatient() {
        return patient;
    }
    
    public void setPatient(Patient patient) {
        this.patient = patient;
    }
    
    public Doctor getDoctor() {
        return doctor;
    }
    
    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }
    
    
}
