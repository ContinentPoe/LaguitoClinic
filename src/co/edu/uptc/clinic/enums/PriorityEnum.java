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
 
    /** Atributo que determina el valor numérico de la prioridad */
    private final int value;
 
    /**
     * <b>Descripción:</b> Constructor de la enumeración.<br>
     *
     * @param value Valor numérico de la prioridad
     */
    PriorityEnum(int value) {
        this.value = value;
    }
 
    /**
     * <b>Descripción:</b> Retorna el valor numérico de la prioridad.<br>
     *
     * @return Valor numérico de la prioridad
     */
    public int getValue() {
        return value;
    }
 
    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return name() + "(" + value + ")";
    }
}