package co.edu.uptc.clinic.repository;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
 
import co.edu.uptc.clinic.domain.Doctor;
import co.edu.uptc.clinic.enums.IdentificationTypeEnum;

public class DoctorRepository {
	private Set<Doctor> doctors;
	    public DoctorRepository() {
	    	this.doctors = new HashSet<>();
	    }

	    public boolean addDoctor(Doctor doctor) {
	        return this.doctors.add(doctor);
	    }
	    
	    public Set<Doctor> findAll() {
	        return new HashSet<>(this.doctors);
	    }
	    
	    public Doctor findByIdentification(IdentificationTypeEnum type, Long medicalId) {
	        return this.doctors.stream()
	                .filter(d -> Objects.equals(d.getIdentificationType(), type)
	                          && Objects.equals(d.getMedicalId(), medicalId))
	                .findFirst().orElse(null);
	    }

}
