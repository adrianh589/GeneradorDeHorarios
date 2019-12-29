import java.time.LocalTime;

public class Dia {

    private String nombre;
    private LocalTime horaInicio;
    private LocalTime horaFinal;

    Dia() { }

    Dia(String nombre, LocalTime horaInicio, LocalTime horaFinal){
        setNombre(nombre);
        setHoraInicio(horaInicio);
        setHoraFinal(horaFinal);
    }

    /*Getters y Setters*/

    public String getNombre() {  return nombre; }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public LocalTime getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(LocalTime horaInicio) {
        this.horaInicio = horaInicio;
    }

    public LocalTime getHoraFinal() {
        return horaFinal;
    }

    public void setHoraFinal(LocalTime horaFinal) {
        this.horaFinal = horaFinal;
    }
}
