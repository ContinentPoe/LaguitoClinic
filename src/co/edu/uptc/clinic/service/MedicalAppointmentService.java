package co.edu.uptc.clinic.service;

import java.util.List;
import java.util.Objects;
import java.util.Set;

import co.edu.uptc.clinic.domain.Doctor;
import co.edu.uptc.clinic.domain.MedicalAppointment;
import co.edu.uptc.clinic.domain.Patient;
import co.edu.uptc.clinic.enums.IdentificationTypeEnum;
import co.edu.uptc.clinic.repository.MedicalAppointmentRepository;

/**
 * <b>Descripción:</b> Servicio que contiene la lógica de negocio relacionada con las citas médicas de la Clínica El Laguito.<br>
 * <p>
 * Valida que el paciente y el médico existan en el sistema antes de registrar la cita, y que el ID de la cita no se repita.
 * </p>
 *
 * @author Noah Padilla
 * @version 1
 */
public class MedicalAppointmentService {

    private MedicalAppointmentRepository appointmentRepository;

    private PatientService patientService;

    private DoctorService doctorService;

    public MedicalAppointmentService(PatientService patientService, DoctorService doctorService) {
        this.appointmentRepository = new MedicalAppointmentRepository();
        this.patientService = patientService;
        this.doctorService = doctorService;
    }

    public boolean registerAppointment(String idAppointment, String timeAppointment,
                                        IdentificationTypeEnum patientIdType, Long patientId,
                                        IdentificationTypeEnum doctorIdType, Long doctorId) {
        if (Objects.nonNull(this.appointmentRepository.findById(idAppointment))) {
            return false;
        }

        Patient patient = this.patientService.findByIdentification(patientIdType, patientId);
        if (Objects.isNull(patient)) {
            return false;
        }

        Doctor doctor = this.doctorService.findByIdentification(doctorIdType, doctorId);
        if (Objects.isNull(doctor)) {
            return false;
        }
        MedicalAppointment appointment = new MedicalAppointment(
                idAppointment, timeAppointment, patient, doctor);
        return this.appointmentRepository.addAppointment(appointment);
    }

    public Set<MedicalAppointment> findAll() {
        return this.appointmentRepository.findAll();
    }

    public List<MedicalAppointment> getAttentionQueue() {
        return this.appointmentRepository.getAttentionQueue();
    }
}