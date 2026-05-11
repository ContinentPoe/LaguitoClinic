package co.edu.uptc.clinic.repository;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import co.edu.uptc.clinic.domain.MedicalAppointment;

/**
 * Repositorio que gestiona el almacenamiento de citas médicas.
 * Utiliza un HashSet para evitar duplicados por ID de cita.
 * 
 * @author Noah Padilla
 * @version 1
 */
public class MedicalAppointmentRepository {

    /** Conjunto de citas médicas registradas */
    private Set<MedicalAppointment> appointments;

    /**
     * Constructor que inicializa el conjunto de citas médicas.
     */
    public MedicalAppointmentRepository() {
        this.appointments = new HashSet<>();
    }

    /**
     * Agrega una cita médica al repositorio.
     * 
     * @param appointment la cita a agregar
     * @return true si se agregó, false si ya existía
     */
    public boolean addAppointment(MedicalAppointment appointment) {
        return this.appointments.add(appointment);
    }

    /**
     * Obtiene todas las citas médicas registradas.
     * 
     * @return conjunto con copia de todas las citas
     */
    public Set<MedicalAppointment> findAll() {
        return new HashSet<>(this.appointments);
    }

    /**
     * Busca una cita médica por su identificador.
     * 
     * @param id el identificador de la cita
     * @return la cita encontrada, o null si no existe
     */
    public MedicalAppointment findById(String id) {
        return this.appointments.stream()
                .filter(a -> Objects.equals(a.getIdMedicalAppointment(), id))
                .findFirst().orElse(null);
    }

    /**
     * Obtiene la cola de atención ordenada por hora y prioridad.
     * Primero ordena por hora, luego por mayor prioridad del paciente.
     * 
     * @return lista de citas ordenada por hora y prioridad
     */
    public List<MedicalAppointment> getAttentionQueue() {
        List<MedicalAppointment> list = new ArrayList<>(this.appointments);
        list.sort(Comparator
                .comparing(MedicalAppointment::getTimeAppointment)
                .thenComparing(a -> -a.getPatient().getPriority().getValue()));
        return list;
    }
}