import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalTime;
import java.util.LinkedList;

public class Hora {

    /**
     * Metodo que indica si una hora es igual a otra
     * @param hora1 Recibe una hora
     * @param hora2 Recibe otra hora
     * @return Devuelve si son o no iguales
     * @throws IOException
     */
    public static boolean esIgual(String hora1, String hora2) throws IOException {
        LocalTime horaInicial = leerHora(hora1), horaAComparar = leerHora(hora2);
        return (horaInicial.equals(horaAComparar)) ? true : false;
    }

    /**
     * Metodo que indica si una hora es menor que otra
     * @param hora1 Recibe una hora
     * @param hora2 Recibe la hora a comparar
     * @return Devuelve true si la hora1 es menor que la hora 2 en caso contrario false
     * @throws IOException
     */
    public static boolean esMenor(String hora1, String hora2) throws IOException {
        LocalTime horaInicial = leerHora(hora1), horaAComparar = leerHora(hora2);
        return (horaInicial.isBefore(horaAComparar)) ? true : false;
    }

    /**
     * Metodo que indica si una hora es mayor que otra
     * @param hora1 Recibe una hora
     * @param hora2 Recibe la hora a comparar
     * @return Devuelve true si la hora1 es mayor que la hora 2 en caso contrario false
     * @throws IOException
     */
    public static boolean esMayor( String hora1, String hora2 ) throws IOException {
        LocalTime horaInicial = leerHora(hora1), horaAComparar = leerHora(hora2);
        return (horaInicial.isAfter(horaAComparar)) ? true : false;
    }

    /**
     * Metodo que indica si una hora esta dentro del rango para poder ingresar una materia
     * @param horaTablero Recibe la hora actual del tablero
     * @param horaInicio Recibe la hora de inicio
     * @param horaFinal Recible la hora final
     * @return Regresa true si la horaTablero esta dentro del rango de las horas de la materia
     * @throws IOException
     */
    public static  boolean dentroDelRango(String horaTablero, String horaInicio, String horaFinal) throws IOException {
        return ( esIgualOMayor(horaTablero, horaInicio) && esIgualOMenor(horaTablero, horaFinal) )  ? true : false;
    }

    /**
     *
     * @param horaAcomparar
     * @param horaLimite
     * @return
     * @throws IOException
     */
    public static boolean esIgualOMayor(String horaAcomparar, String horaLimite) throws IOException {
        return (esIgual( horaAcomparar, horaLimite) || esMayor(horaAcomparar, horaLimite)) ? true : false;
    }

    /**
     *
     * @param horaAComparar
     * @param horaLimite
     * @return
     * @throws IOException
     */
    public static boolean esIgualOMenor(String horaAComparar, String horaLimite) throws IOException {
        return (esIgual( horaAComparar, horaLimite) || esMenor(horaAComparar, horaLimite)) ? true : false;
    }

    /**
     * Metodo para capturar la hora
     * @param horas Recibe la hora
     * @return Devuelve la hora en formato LocalTime
     */
    public static LocalTime leerHora(String horas) throws IOException {
        LocalTime horaLeida = null;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try {
            String[] capturaHoras = horas.split(":");
            int[] intHoras = new int[2];
            int hora = intHoras[0] = Integer.parseInt(capturaHoras[0]);
            int minutos = intHoras[1] = Integer.parseInt(capturaHoras[1]);
            horaLeida = LocalTime.of(hora, minutos);
        }catch (Exception e){
            System.out.println("Hora invalida, vuelva a introducirla");
            return leerHora(br.readLine());
        }
        return horaLeida;
    }

    /**
     * Metodo que genera los rangos que desea el usuario
     * @param hora1 Hora inicial del horario
     * @param hora2 Hora final del horario
     */
    public static LinkedList<LocalTime> generarRango(LocalTime hora1, LocalTime hora2){
        LinkedList<LocalTime> rangos = new LinkedList<LocalTime>();
        while (hora1.compareTo(hora2) != 0){
            rangos.add(hora1);
            hora1 = hora1.plusMinutes(14);
            rangos.add(hora1);
            hora1 = hora1.plusMinutes(1);
        }
        return rangos;
    }
}
