package co.edu.uptc.clinic.domain;

/** 
 * <b>Descripción: </b> Clase de modelo que representa
 * la información del paciente <br>
 * 
 * @author Noah Padilla
*/

public class Patient {
    private Long idPatient;
    private String firstName;
    private String lastName;
    private String email;
	
    public Patient() {
		super();
	}

	public Patient(Long idPatient, String firstName, String lastName, String email) {
		super();
		this.idPatient = idPatient;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
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
	
    
    
}
