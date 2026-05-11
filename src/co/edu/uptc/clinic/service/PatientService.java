package co.edu.uptc.clinic.service;

import java.util.Objects;
import java.util.Set;

import co.edu.uptc.clinic.domain.Patient;
import co.edu.uptc.clinic.enums.IdentificationTypeEnum;
import co.edu.uptc.clinic.repository.PatientRepository;

/**
 * <b>Descripción:</b> Servicio que contiene la lógica de negocio
 * relacionada con los pacientes de la Clínica El Laguito.<br>
 * <p>
 * Valida unicidad de identificación y de correo electrónico antes de registrar.
 * </p>
 *
 * @author Noah Padilla
 * @version 1
 */
public class PatientService {

    private PatientRepository patientRepository;

    public PatientService() {
        this.patientRepository = new PatientRepository();
    }

    public boolean registerPatient(Patient patient) {
        if (Objects.isNull(patient)) {
            return false;
        }

        if (this.patientRepository.existsByEmail(patient.getEmail())) {
            return false;
        }

        return this.patientRepository.addPatient(patient);
    }

    public boolean addMedication(IdentificationTypeEnum type, Long idPatient, String medication) {
        Patient patient = this.patientRepository.findByIdentification(type, idPatient);
        if (Objects.isNull(patient)) {
            return false;
        }
        return patient.getMedicationHistory().add(medication);
    }

    public Set<Patient> findAll() {
        return this.patientRepository.findAll();
    }

    public Patient findByIdentification(IdentificationTypeEnum type, Long idPatient) {
        if (Objects.isNull(type) || Objects.isNull(idPatient)) {
            return null;
        }
        return this.patientRepository.findByIdentification(type, idPatient);
    }
}