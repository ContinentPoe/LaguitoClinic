package co.edu.uptc.clinic.repository;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import co.edu.uptc.clinic.domain.Patient;
import co.edu.uptc.clinic.enums.IdentificationTypeEnum;

/**
 * Repositorio que gestiona el almacenamiento de pacientes.
 * Utiliza un HashSet para evitar duplicados por identificación.
 * 
 * @author Noah Padilla
 * @version 1
 */
public class PatientRepository {

    /** Conjunto de pacientes registrados */
    private Set<Patient> patients;

    /**
     * Constructor que inicializa el conjunto de pacientes.
     */
    public PatientRepository() {
        this.patients = new HashSet<>();
    }

    /**
     * Agrega un paciente al repositorio.
     * 
     * @param patient el paciente a agregar
     * @return true si se agregó, false si ya existía
     */
    public boolean addPatient(Patient patient) {
        return this.patients.add(patient);
    }

    /**
     * Obtiene todos los pacientes registrados.
     * 
     * @return conjunto con copia de todos los pacientes
     */
    public Set<Patient> findAll() {
        return new HashSet<>(this.patients);
    }

    /**
     * Busca un paciente por tipo e identificación.
     * 
     * @param type tipo de identificación del paciente
     * @param idPatient número de identificación del paciente
     * @return el paciente encontrado, o null si no existe
     */
    public Patient findByIdentification(IdentificationTypeEnum type, Long idPatient) {
        return this.patients.stream()
                .filter(p -> Objects.equals(p.getIdentificationType(), type)
                          && Objects.equals(p.getIdPatient(), idPatient))
                .findFirst().orElse(null);
    }

    /**
     * Verifica si existe un paciente con el correo especificado.
     * 
     * @param email el correo electrónico a buscar
     * @return true si el correo existe, false si no existe
     */
    public boolean existsByEmail(String email) {
        return this.patients.stream()
                .anyMatch(p -> Objects.equals(p.getEmail(), email));
    }
}