package co.edu.uptc.clinic.repository;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import co.edu.uptc.clinic.domain.Doctor;
import co.edu.uptc.clinic.enums.IdentificationTypeEnum;

/**
 * Repositorio que gestiona el almacenamiento de médicos.
 * Utiliza un HashSet para evitar duplicados.
 */
public class DoctorRepository {

    /** Conjunto de médicos registrados */
    private Set<Doctor> doctors;

    /**
     * Constructor que inicializa el conjunto de médicos.
     */
    public DoctorRepository() {
        this.doctors = new HashSet<>();
    }

    /**
     * Agrega un médico al repositorio.
     * 
     * @param doctor el médico a agregar
     * @return true si se agregó, false si ya existía
     */
    public boolean addDoctor(Doctor doctor) {
        return this.doctors.add(doctor);
    }

    /**
     * Obtiene todos los médicos registrados.
     * 
     * @return conjunto con copia de todos los médicos
     */
    public Set<Doctor> findAll() {
        return new HashSet<>(this.doctors);
    }

    /**
     * Busca un médico por tipo e identificación.
     * 
     * @param type tipo de identificación del médico
     * @param medicalId número de identificación del médico
     * @return el médico encontrado, o null si no existe
     */
    public Doctor findByIdentification(IdentificationTypeEnum type, Long medicalId) {
        return this.doctors.stream()
                .filter(d -> Objects.equals(d.getIdentificationType(), type)
                          && Objects.equals(d.getMedicalId(), medicalId))
                .findFirst().orElse(null);
    }
}