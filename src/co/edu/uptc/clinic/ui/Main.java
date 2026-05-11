package co.edu.uptc.clinic.ui;

import javax.swing.JOptionPane;
import java.util.List;
import java.util.Set;

import co.edu.uptc.clinic.domain.*;
import co.edu.uptc.clinic.enums.*;
import co.edu.uptc.clinic.service.*;

/**
 * <b>Descripción:</b> Clase principal de la interfaz de usuario para el sistema
 * de administración de la Clínica El Laguito.<br>
 * <p>
 * Permite registrar pacientes, médicos, citas, agregar medicamentos al historial, consultar la cola de atención y generar reportes de médicos por experiencia.
 * Toda la interacción se realiza mediante.
 * </p>
 *
 * @author Noah Padilla
 * @version 1
 */
public class Main {

    private static PatientService patientService = new PatientService();
    private static DoctorService doctorService = new DoctorService();
    private static MedicalAppointmentService appointmentService = new MedicalAppointmentService(patientService, doctorService);
    public static void main(String[] args) {
        int opcion;
        do {
            String menu =
                    "☺ CLÍNICA EL LAGUITO ☺\n" +
                    "1. Registrar paciente\n" +
                    "2. Registrar médico\n" +
                    "3. Registrar cita médica\n" +
                    "4. Agregar medicamento a historial de paciente\n" +
                    "5. Ver cola de atención (por hora y prioridad)\n" +
                    "6. Reporte de médicos por experiencia\n" +
                    "7. Listar todos los pacientes\n" +
                    "8. Listar todos los médicos\n" +
                    "0. Salir";
            String input = JOptionPane.showInputDialog(null, menu, "Menú Principal",
                    JOptionPane.PLAIN_MESSAGE);
            if (input == null) break;
            try {
                opcion = Integer.parseInt(input.trim());
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Opción inválida.");
                opcion = -1;
            }
            switch (opcion) {
                case 1: registrarPaciente(); break;
                case 2: registrarMedico(); break;
                case 3: registrarCita(); break;
                case 4: agregarMedicamento(); break;
                case 5: verColaAtencion(); break;
                case 6: reporteMedicosPorExperiencia(); break;
                case 7: listarPacientes(); break;
                case 8: listarMedicos(); break;
                case 0: JOptionPane.showMessageDialog(null, "¡Hasta luego!"); break;
                default: break;
            }
        } while (true);
    }

    private static void registrarPaciente() {
        try {
            IdentificationTypeEnum tipo = seleccionarTipoIdentificacion("paciente");
            if (tipo == null) return;

            String idStr = JOptionPane.showInputDialog("Número de identificación del paciente:");
            if (idStr == null) return;
            Long id = Long.parseLong(idStr.trim());

            String firstName = JOptionPane.showInputDialog("Nombre(s) del paciente:");
            if (firstName == null) return;

            String lastName = JOptionPane.showInputDialog("Apellido(s) del paciente:");
            if (lastName == null) return;

            String email = JOptionPane.showInputDialog("Correo electrónico del paciente:");
            if (email == null) return;

            PriorityEnum priority = seleccionarPrioridad();
            if (priority == null) return;

            Patient patient = new Patient(tipo, id, firstName.trim(), lastName.trim(), email.trim(), priority);
            boolean resultado = patientService.registerPatient(patient);

            if (resultado) {
                JOptionPane.showMessageDialog(null, "✔ Paciente registrado exitosamente.");
            } else {
                JOptionPane.showMessageDialog(null,"✘ No se pudo registrar. La identificación o el correo ya existen.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "El número de identificación debe ser numérico.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private static void registrarMedico() {
        try {
            IdentificationTypeEnum tipo = seleccionarTipoIdentificacion("médico");
            if (tipo == null) return;

            String idStr = JOptionPane.showInputDialog("Número de identificación del médico:");
            if (idStr == null) return;
            Long id = Long.parseLong(idStr.trim());

            String firstName = JOptionPane.showInputDialog("Nombre(s) del médico:");
            if (firstName == null) return;

            String lastName = JOptionPane.showInputDialog("Apellido(s) del médico:");
            if (lastName == null) return;

            String specialty = JOptionPane.showInputDialog("Especialidad del médico:");
            if (specialty == null) return;

            String yearsStr = JOptionPane.showInputDialog("Años de experiencia:");
            if (yearsStr == null) return;
            int years = Integer.parseInt(yearsStr.trim());

            Doctor doctor = new Doctor(tipo, id, firstName.trim(), lastName.trim(),
                    specialty.trim(), years);
            boolean resultado = doctorService.registerDoctor(doctor);

            if (resultado) {
                JOptionPane.showMessageDialog(null, "✔ Médico registrado exitosamente.");
            } else {
                JOptionPane.showMessageDialog(null,"✘ No se pudo registrar. El médico ya existe en el sistema.","Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Los datos numéricos son inválidos.",
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private static void registrarCita() {
        try {
            String idCita = JOptionPane.showInputDialog("ID único de la cita:");
            if (idCita == null) return;

            String hora = JOptionPane.showInputDialog("Hora de la cita (HH:mm, ej. 08:30):");
            if (hora == null) return;

            JOptionPane.showMessageDialog(null, "Ahora ingrese los datos del PACIENTE:");
            IdentificationTypeEnum tipoPaciente = seleccionarTipoIdentificacion("paciente");
            if (tipoPaciente == null) return;
            String idPacienteStr = JOptionPane.showInputDialog("Número de identificación del paciente:");
            if (idPacienteStr == null) return;
            Long idPaciente = Long.parseLong(idPacienteStr.trim());

            JOptionPane.showMessageDialog(null, "Ahora ingrese los datos del MÉDICO:");
            IdentificationTypeEnum tipoMedico = seleccionarTipoIdentificacion("médico");
            if (tipoMedico == null) return;
            String idMedicoStr = JOptionPane.showInputDialog("Número de identificación del médico:");
            if (idMedicoStr == null) return;
            Long idMedico = Long.parseLong(idMedicoStr.trim());

            boolean resultado = appointmentService.registerAppointment(
                    idCita.trim(), hora.trim(),
                    tipoPaciente, idPaciente,
                    tipoMedico, idMedico);

            if (resultado) {
                JOptionPane.showMessageDialog(null, "✔ Cita médica registrada exitosamente.");
            } else {
                JOptionPane.showMessageDialog(null,
                        "✘ No se pudo registrar la cita.\n" +
                        "Verifique que el ID de la cita sea único,\n" +
                        "y que el paciente y médico existan en el sistema.",
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Los datos numéricos son inválidos.",
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private static void agregarMedicamento() {
        try {
            IdentificationTypeEnum tipo = seleccionarTipoIdentificacion("paciente");
            if (tipo == null) return;

            String idStr = JOptionPane.showInputDialog("Número de identificación del paciente:");
            if (idStr == null) return;
            Long id = Long.parseLong(idStr.trim());

            String medicamento = JOptionPane.showInputDialog("Nombre del medicamento a agregar:");
            if (medicamento == null) return;

            boolean resultado = patientService.addMedication(tipo, id, medicamento.trim());

            if (resultado) {
                JOptionPane.showMessageDialog(null, "✔ Medicamento agregado al historial.");
            } else {
                JOptionPane.showMessageDialog(null,
                        "✘ No se agregó. Paciente no encontrado o el medicamento ya estaba registrado.",
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "El número de identificación debe ser numérico.",
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private static void verColaAtencion() {
        List<MedicalAppointment> cola = appointmentService.getAttentionQueue();
        if (cola.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay citas registradas.");
            return;
        }
        StringBuilder sb = new StringBuilder("=== COLA DE ATENCIÓN ===\n");
        int pos = 1;
        for (MedicalAppointment a : cola) {
            sb.append(pos++).append(". ")
              .append("Hora: ").append(a.getTimeAppointment())
              .append(" | Paciente: ").append(a.getPatient().getFirstName())
              .append(" ").append(a.getPatient().getLastName())
              .append(" | Prioridad: ").append(a.getPatient().getPriority())
              .append(" | Médico: ").append(a.getDoctor().getFirstName())
              .append(" ").append(a.getDoctor().getLastName())
              .append("\n");
        }
        JOptionPane.showMessageDialog(null, sb.toString(), "Cola de Atención",
                JOptionPane.INFORMATION_MESSAGE);
    }

    private static void reporteMedicosPorExperiencia() {
        List<Doctor> lista = doctorService.reportByExperience();
        if (lista.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay médicos registrados.");
            return;
        }
        StringBuilder sb = new StringBuilder("=== REPORTE MÉDICOS POR EXPERIENCIA ===\n");
        int pos = 1;
        for (Doctor d : lista) {
            sb.append(pos++).append(". ")
              .append(d.getFirstName()).append(" ").append(d.getLastName())
              .append(" | Especialidad: ").append(d.getSpecialty())
              .append(" | Experiencia: ").append(d.getYearsOfExperience()).append(" años")
              .append("\n");
        }
        JOptionPane.showMessageDialog(null, sb.toString(), "Reporte por Experiencia",
                JOptionPane.INFORMATION_MESSAGE);
    }

    private static void listarPacientes() {
        Set<Patient> pacientes = patientService.findAll();
        if (pacientes.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay pacientes registrados.");
            return;
        }
        StringBuilder sb = new StringBuilder("=== PACIENTES REGISTRADOS ===\n");
        for (Patient p : pacientes) {
            sb.append(p.toString()).append("\n");
        }
        JOptionPane.showMessageDialog(null, sb.toString(), "Pacientes",
                JOptionPane.INFORMATION_MESSAGE);
    }

    private static void listarMedicos() {
        Set<Doctor> medicos = doctorService.findAll();
        if (medicos.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay médicos registrados.");
            return;
        }
        StringBuilder sb = new StringBuilder("=== MÉDICOS REGISTRADOS ===\n");
        for (Doctor d : medicos) {
            sb.append(d.toString()).append("\n");
        }
        JOptionPane.showMessageDialog(null, sb.toString(), "Médicos",
                JOptionPane.INFORMATION_MESSAGE);
    }

    private static IdentificationTypeEnum seleccionarTipoIdentificacion(String rol) {
        IdentificationTypeEnum[] tipos = IdentificationTypeEnum.values();
        String[] opciones = new String[tipos.length];
        for (int i = 0; i < tipos.length; i++) {
            opciones[i] = tipos[i].toString();
        }
        String seleccion = (String) JOptionPane.showInputDialog(
                null,
                "Tipo de identificación del " + rol + ":",
                "Tipo de identificación",
                JOptionPane.PLAIN_MESSAGE,
                null, opciones, opciones[0]);
        if (seleccion == null) return null;
        for (IdentificationTypeEnum t : tipos) {
            if (t.toString().equals(seleccion)) return t;
        }
        return null;
    }

    private static PriorityEnum seleccionarPrioridad() {
        PriorityEnum[] prioridades = PriorityEnum.values();
        String[] opciones = new String[prioridades.length];
        for (int i = 0; i < prioridades.length; i++) {
            opciones[i] = prioridades[i].toString();
        }
        String seleccion = (String) JOptionPane.showInputDialog(
                null,
                "Prioridad del paciente:",
                "Prioridad",
                JOptionPane.PLAIN_MESSAGE,
                null, opciones, opciones[0]);
        if (seleccion == null) return null;
        for (PriorityEnum p : prioridades) {
            if (p.toString().equals(seleccion)) return p;
        }
        return null;
    }
}