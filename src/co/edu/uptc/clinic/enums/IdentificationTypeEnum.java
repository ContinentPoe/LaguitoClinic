package co.edu.uptc.clinic.enums;

/**
 * <b>Descripción:</b> Enumeración que representa los tipos de identificación
 * disponibles en el sistema de la Clínica El Laguito.<br>
 *
 * @author Noah Padilla
 * @version 1
 */
public enum IdentificationTypeEnum {
	 
    /** Cédula de ciudadanía */
    CC("Cédula de ciudadanía"),
 
    /** Tarjeta de identidad */
    TI("Tarjeta de identidad"),
 
    /** Cédula de extranjería */
    CE("Cédula de extranjería"),
 
    /** Pasaporte */
    PA("Pasaporte");
 
    /** Atributo que determina el nombre oficial del tipo de identificación */
    private final String nombreOficial;
 
    /**
     * <b>Descripción:</b> Constructor de la enumeración.<br>
     *
     * @param nombreOficial Nombre oficial del tipo de identificación
     */
    IdentificationTypeEnum(String nombreOficial) {
        this.nombreOficial = nombreOficial;
    }
 
    /**
     * <b>Descripción:</b> Retorna el nombre oficial del tipo de identificación.<br>
     *
     * @return Nombre oficial del tipo de identificación
     */
    public String getNombreOficial() {
        return nombreOficial;
    }
 
    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return name() + " - " + nombreOficial;
    }
}
 