package co.edu.udec;

public class InscripcionClase {

    public static final String PENDIENTE = "PENDIENTE";
    public static final String PROCESADA = "PROCESADA";
    public static final String CANCELADA = "CANCELADA";

    private String codigo;
    private String nombreSocio;
    private String nombreClase;
    private String horario;
    private String estado;

    public InscripcionClase(String codigo, String nombreSocio, String nombreClase, String horario) {
        this.codigo = codigo;
        this.nombreSocio = nombreSocio;
        this.nombreClase = nombreClase;
        this.horario = horario;
        this.estado = PENDIENTE; // toda inscripcion nueva empieza pendiente
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombreSocio() {
        return nombreSocio;
    }

    public void setNombreSocio(String nombreSocio) {
        this.nombreSocio = nombreSocio;
    }

    public String getNombreClase() {
        return nombreClase;
    }

    public void setNombreClase(String nombreClase) {
        this.nombreClase = nombreClase;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        InscripcionClase otra = (InscripcionClase) obj;
        return codigo != null && codigo.equalsIgnoreCase(otra.codigo);
    }

    @Override
    public int hashCode() {
        return codigo == null ? 0 : codigo.toLowerCase().hashCode();
    }

    @Override
    public String toString() {
        return "[" + codigo + "] Socio: " + nombreSocio + " | Clase: " + nombreClase
                + " | Horario: " + horario + " | Estado: " + estado;
    }
}
