import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalTime;

public class Main {

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        OperacionesMateria op = new OperacionesMateria();
        Horario horario = new Horario();

        LocalTime hora1 = LocalTime.of(4,45);
        LocalTime hora2 = LocalTime.of(10,00);
        String[][] tablero = horario.tableroHorario(hora1,hora2);

        Materia fisica = new Materia();
        fisica.setNombre("fis");
        fisica.setDia(new Dia("lunes", LocalTime.of(4,45), LocalTime.of(6,14)));
        fisica.setDia(new Dia("jueves", LocalTime.of(4,45), LocalTime.of(6,14)));
        fisica.setNRC("1");
        op.guardarMateria(fisica);

        Materia fisica2 = new Materia();
        fisica2.setNombre("fis");
        fisica2.setDia(new Dia("martes", LocalTime.of(6,15), LocalTime.of(7,44)));
        fisica2.setDia(new Dia("viernes", LocalTime.of(4,45), LocalTime.of(6,14)));
        fisica2.setNRC("2");
        op.guardarMateria(fisica2);

        Materia etica = new Materia();
        etica.setNombre("eti");
        etica.setDia(new Dia("martes", LocalTime.of(6,15), LocalTime.of(7,44)));
        op.guardarMateria(etica);

        /*Materia etica = new Materia();
        etica.setNombre("eti");
        etica.setDia(new Dia("martes", LocalTime.of(6,15), LocalTime.of(7,44)));
        op.guardarMateria(etica);

        Materia disenio = new Materia();
        disenio.setNombre("dis");
        disenio.setDia(new Dia("martes", LocalTime.of(8,30), LocalTime.of(9,59)));
        disenio.setDia(new Dia("jueves", LocalTime.of(8,30), LocalTime.of(9,59)));
        op.guardarMateria(disenio);

        Materia electiva = new Materia();
        electiva.setNombre("ele");
        electiva.setDia(new Dia("miercoles", LocalTime.of(6,15), LocalTime.of(7,44)));
        op.guardarMateria(electiva);

        Materia arquitectura = new Materia();
        arquitectura.setNombre("arq");
        arquitectura.setDia(new Dia("jueves", LocalTime.of(6,15), LocalTime.of(7,44)));
        arquitectura.setDia(new Dia("sabado", LocalTime.of(19,00), LocalTime.of(20,30)));
        op.guardarMateria(arquitectura);

        Materia prueba = new Materia();
        prueba.setNombre("fis");
        prueba.setDia(new Dia("martes", LocalTime.of(4,45), LocalTime.of(6,14)));
        op.guardarMateria(prueba);*/

        op.imprimirMateriasRegistradas(op.materiasRegistradas);
        op.generarHorarioAcademico(tablero, 0);
    }

}
