import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalTime;

public class Main {

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        OperacionesMateria op = new OperacionesMateria();
        Horario horario = new Horario();

        //LocalTime hora1 = LocalTime.of(4,45);
        //LocalTime hora2 = LocalTime.of(10,00);
        //String[][] tablero = horario.tableroHorario(hora1,hora2);
        op.leerMateria(br);

    }

}
