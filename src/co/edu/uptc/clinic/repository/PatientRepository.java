package co.edu.uptc.clinic.repository;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import co.edu.uptc.clinic.domain.Patient;
import co.edu.uptc.clinic.enums.IdentificationTypeEnum;

/**
 * <b>Descripción:</b> Repositorio encargado de gestionar el almacenamiento
 * de los pacientes en la Clínica El Laguito.<br>
 * </p>
 *
 * @author Noah Padilla
 * @version 1
 */
public class PatientRepository {

    private Set<Patient> patients;

    public PatientRepository() {
        this.patients = new HashSet<>();
    }

    public boolean addPatient(Patient patient) {
        return this.patients.add(patient);
    }

    public Set<Patient> findAll() {
        return new HashSet<>(this.patients);
    }

    public Patient findByIdentification(IdentificationTypeEnum type, Long idPatient) {
        return this.patients.stream()
                .filter(p -> Objects.equals(p.getIdentificationType(), type)
                          && Objects.equals(p.getIdPatient(), idPatient))
                .findFirst().orElse(null);
    }

    public boolean existsByEmail(String email) {
        return this.patients.stream()
                .anyMatch(p -> Objects.equals(p.getEmail(), email));
    }
