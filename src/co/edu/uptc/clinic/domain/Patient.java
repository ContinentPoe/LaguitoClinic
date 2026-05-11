package co.edu.uptc.clinic.domain;

import java.util.LinkedHashSet;
import java.util.Objects;

import co.edu.uptc.clinic.enums.IdentificationTypeEnum;
import co.edu.uptc.clinic.enums.PriorityEnum;

/**
 * <b>Descripción:</b> Clase de modelo que representa la información
 * de un paciente registrado en la Clínica El Laguito.<br>
 * </p>
 *
 * @author Noah Padilla
 * @version 1
 */
public class Patient {

    private IdentificationTypeEnum identificationType;

    private Long idPatient;
    private String firstName;
    private String lastName;
    private String email;
    private LinkedHashSet<String> medicationHistory;
    private PriorityEnum priority;
    
    public Patient() {
        this.medicationHistory = new LinkedHashSet<>();
    }

    public Patient(IdentificationTypeEnum identificationType, Long idPatient,
                   String firstName, String lastName, String email, PriorityEnum priority) {
        this.identificationType = identificationType;
        this.idPatient = idPatient;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.priority = priority;
        this.medicationHistory = new LinkedHashSet<>();
    }
    
    public IdentificationTypeEnum getIdentificationType() {
    	return identificationType;
    }

    public void setIdentificationType(IdentificationTypeEnum identificationType) {
        this.identificationType = identificationType;
    }

    public Long getIdPatient() {
        return idPatient;
    }

    public void setIdPatient(Long idPatient) {
        this.idPatient = idPatient;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LinkedHashSet<String> getMedicationHistory() {
        return medicationHistory;
    }

    public void setMedicationHistory(LinkedHashSet<String> medicationHistory) {
        this.medicationHistory = medicationHistory;
    }

    public PriorityEnum getPriority() {
        return priority;
    }

    public void setPriority(PriorityEnum priority) {
        this.priority = priority;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Patient)) return false;
        Patient other = (Patient) obj;
        return Objects.equals(this.identificationType, other.identificationType)
                && Objects.equals(this.idPatient, other.idPatient);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.identificationType, this.idPatient);
    }

    @Override
    public String toString() {
        return "Patient [" + identificationType.name() + " - " + idPatient
                + ", nombre=" + firstName + " " + lastName
                + ", email=" + email
                + ", prioridad=" + priority
                + ", medicamentos=" + medicationHistory + "]";
    }
}