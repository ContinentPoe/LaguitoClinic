package co.edu.uptc.clinic.enums;

/**
 * <b>Descripción:</b> Enumeración que representa los niveles de prioridad
 * de un paciente en la cola de atención de la Clínica El Laguito.<br>
 *
 * @author Noah Padilla
 * @version 1
 */

public enum PriorityEnum {
 
    /** Prioridad baja */
    LOW(0),
 
    /** Prioridad media */
    MEDIUM(1),
 
    /** Prioridad alta */
    HIGH(2),
 
    /** Prioridad crítica */
    CRITICAL(3);
}