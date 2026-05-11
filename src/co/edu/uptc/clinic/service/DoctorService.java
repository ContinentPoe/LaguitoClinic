package co.edu.uptc.clinic.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import co.edu.uptc.clinic.domain.Doctor;
import co.edu.uptc.clinic.enums.IdentificationTypeEnum;
import co.edu.uptc.clinic.repository.DoctorRepository;

/**
 * <b>Descripción:</b> Servicio que contiene la lógica de negocio relacionada con los médicos de la Clínica El Laguito.<br>
 * <p>
 * Valida unicidad de identificación antes de registrar y genera reportes ordenados por experiencia.
 * </p>
 *
 * @author Noah Padilla
 * @version 1
 */
public class DoctorService {

    private DoctorRepository doctorRepository;

    public DoctorService() {
        this.doctorRepository = new DoctorRepository();
    }

    public boolean registerDoctor(Doctor doctor) {
        if (Objects.isNull(doctor)) {
            return false;
        }
        return this.doctorRepository.addDoctor(doctor);
    }

    public Set<Doctor> findAll() {
        return this.doctorRepository.findAll();
    }

    public Doctor findByIdentification(IdentificationTypeEnum type, Long medicalId) {
        if (Objects.isNull(type) || Objects.isNull(medicalId)) {
            return null;
        }
        return this.doctorRepository.findByIdentification(type, medicalId);
    }

    public List<Doctor> reportByExperience() {
        List<Doctor> list = new ArrayList<>(this.doctorRepository.findAll());
        list.sort(Comparator
                .comparingInt(Doctor::getYearsOfExperience)
                .thenComparing(d -> d.getFirstName() + " " + d.getLastName()));
        return list;
    }
}