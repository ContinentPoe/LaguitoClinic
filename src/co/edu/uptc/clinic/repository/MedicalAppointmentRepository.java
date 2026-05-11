package co.edu.uptc.clinic.repository;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import co.edu.uptc.clinic.domain.MedicalAppointment;

/**
 * <b>Descripción:</b> Repositorio encargado de gestionar el almacenamiento
 * de las citas médicas en la Clínica El Laguito.<br>
 * <p>
 * Usa HashSet para garantizar unicidad por ID de cita
 * </p>
 *
 * @author Noah Padilla
 * @version 1
 */
public class MedicalAppointmentRepository {

    private Set<MedicalAppointment> appointments;

    public MedicalAppointmentRepository() {
        this.appointments = new HashSet<>();
    }

    public boolean addAppointment(MedicalAppointment appointment) {
        return this.appointments.add(appointment);
    }

    public Set<MedicalAppointment> findAll() {
        return new HashSet<>(this.appointments);
    }

    public MedicalAppointment findById(String id) {
        return this.appointments.stream()
                .filter(a -> Objects.equals(a.getIdMedicalAppointment(), id))
                .findFirst().orElse(null);
    }

    public List<MedicalAppointment> getAttentionQueue() {
        List<MedicalAppointment> list = new ArrayList<>(this.appointments);
        list.sort(Comparator.comparing(MedicalAppointment::getTimeAppointment).thenComparing(a -> -a.getPatient().getPriority().getValue()));
        return list;
    }
}