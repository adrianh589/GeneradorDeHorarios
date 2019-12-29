import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalTime;
import java.util.Stack;

/**
 * Clase principal para pruebas
 *
 * ///////////////////PARA TENER EN CUENTA//////////////////////////////////////////////////
 * Las horas de inicio deben tener digito terminal de 0 o 5
 * Las horas finales deben tener digito terminal de 4 o 9
 * Todo esto con el fin de evitar cruces entre las materias
 * /////////////////////////////////////////////////////////////////////////////////////////
 *
 * @author adrian
 */
public class Main {

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        OperacionesMateria op = new OperacionesMateria();
        Horario horario = new Horario();
        Hora h = new Hora();

        LocalTime hora1 = LocalTime.of(18,00);
        LocalTime hora2 = LocalTime.of(22,45);
        String[][] tablero = horario.tableroHorario(hora1,hora2);

        /************************ARQUITECTURA DE SOFTWARE********************************************/
        Materia arq = new Materia();
        arq.setNombre("arq");
        arq.setDia(new Dia("lunes", h.leerHora("18:15"), h.leerHora("19:44")));
        arq.setDia(new Dia("jueves", h.leerHora("20:30"), h.leerHora("21:59")));
        arq.setNRC("3460");
        op.guardarMateria(arq);

        Materia arq2 = new Materia();
        arq2.setNombre("arq");
        arq2.setDia(new Dia("lunes", h.leerHora("20:30"), h.leerHora("21:59")));
        arq2.setDia(new Dia("jueves", h.leerHora("18:15"), h.leerHora("19:44")));
        arq2.setNRC("8213");
        op.guardarMateria(arq2);

        Materia arq3 = new Materia();
        arq3.setNombre("arq");
        arq3.setDia(new Dia("miercoles", h.leerHora("20:30"), h.leerHora("21:59")));
        arq3.setDia(new Dia("viernes", h.leerHora("18:15"), h.leerHora("19:44")));
        arq3.setNRC("8217");
        op.guardarMateria(arq3);

        Materia arq4 = new Materia();
        arq4.setNombre("arq");
        arq4.setDia(new Dia("miercoles", h.leerHora("18:15"), h.leerHora("19:44")));
        arq4.setDia(new Dia("sabado", h.leerHora("20:30"), h.leerHora("21:59")));
        arq4.setNRC("11602");
        op.guardarMateria(arq4);
        /*********************************************************************************************/

        /**************************BASES DE DATOS MASIVAS*********************************************/
        Materia bd = new Materia();
        bd.setNombre("bdm");
        bd.setDia(new Dia("martes", h.leerHora("18:15"), h.leerHora("19:44")));
        bd.setDia(new Dia("sabado", h.leerHora("21:15"), h.leerHora("22:44")));
        bd.setNRC("3468");
        op.guardarMateria(bd);

        Materia bd2 = new Materia();
        bd2.setNombre("bdm");
        bd2.setDia(new Dia("martes", h.leerHora("20:30"), h.leerHora("21:59")));
        bd2.setDia(new Dia("viernes", h.leerHora("18:15"), h.leerHora("19:44")));
        bd2.setNRC("13811");
        op.guardarMateria(bd2);
        /************************************************************************************************/

        /*********************************ECUACIONES DIFERENCIALES*****************************************/
        Materia ecuaciones = new Materia();
        ecuaciones.setNombre("dif");
        ecuaciones.setDia(new Dia("martes", h.leerHora("20:30"), h.leerHora("21:59")));
        ecuaciones.setDia(new Dia("viernes", h.leerHora("20:30"), h.leerHora("21:59")));
        ecuaciones.setNRC("3906");
        op.guardarMateria(ecuaciones);

        Materia ecuaciones2 = new Materia();
        ecuaciones2.setNombre("dif");
        ecuaciones2.setDia(new Dia("miercoles", h.leerHora("20:30"), h.leerHora("21:59")));
        ecuaciones2.setDia(new Dia("viernes", h.leerHora("18:15"), h.leerHora("19:44")));
        ecuaciones2.setNRC("3909");
        op.guardarMateria(ecuaciones2);

        Materia ecuaciones3 = new Materia();
        ecuaciones3.setNombre("dif");
        ecuaciones3.setDia(new Dia("lunes", h.leerHora("20:30"), h.leerHora("21:59")));
        ecuaciones3.setDia(new Dia("miercoles", h.leerHora("20:30"), h.leerHora("21:59")));
        ecuaciones3.setNRC("3912");
        op.guardarMateria(ecuaciones3);

        Materia ecuaciones4 = new Materia();
        ecuaciones4.setNombre("dif");
        ecuaciones4.setDia(new Dia("martes", h.leerHora("20:30"), h.leerHora("21:59")));
        ecuaciones4.setDia(new Dia("viernes", h.leerHora("18:15"), h.leerHora("19:44")));
        ecuaciones4.setNRC("10605");
        op.guardarMateria(ecuaciones4);

        Materia ecuaciones5 = new Materia();
        ecuaciones5.setNombre("dif");
        ecuaciones5.setDia(new Dia("martes", h.leerHora("20:30"), h.leerHora("21:59")));
        ecuaciones5.setDia(new Dia("sabado", h.leerHora("19:00"), h.leerHora("20:30")));
        ecuaciones5.setNRC("10606");
        op.guardarMateria(ecuaciones5);

        Materia ecuaciones6 = new Materia();
        ecuaciones6.setNombre("dif");
        ecuaciones6.setDia(new Dia("lunes", h.leerHora("18:15"), h.leerHora("19:44")));
        ecuaciones6.setDia(new Dia("jueves", h.leerHora("20:30"), h.leerHora("21:59")));
        ecuaciones6.setNRC("11954");
        op.guardarMateria(ecuaciones6);
        /**********************************ESTRUCTURA DE INTERNET SERVICIOS Y SERVIDORES*********************/
        Materia edis = new Materia();
        edis.setNombre("edis");
        edis.setDia(new Dia("martes", h.leerHora("18:15"), h.leerHora("19:44")));
        edis.setDia(new Dia("jueves", h.leerHora("20:30"), h.leerHora("21:59")));
        edis.setNRC("3463");
        op.guardarMateria(edis);

        Materia edis2 = new Materia();
        edis2.setNombre("edis");
        edis2.setDia(new Dia("jueves", h.leerHora("18:15"), h.leerHora("19:44")));
        edis2.setDia(new Dia("sabado", h.leerHora("19:00"), h.leerHora("20:30")));
        edis2.setNRC("8309");
        op.guardarMateria(edis2);

        Materia edis3 = new Materia();
        edis3.setNombre("edis");
        edis3.setDia(new Dia("lunes", h.leerHora("20:30"), h.leerHora("21:59")));
        edis3.setDia(new Dia("miercoles", h.leerHora("20:30"), h.leerHora("21:59")));
        edis3.setNRC("11619");
        op.guardarMateria(edis3);
        /*****************************************************************************************************/

        /***********************************MATEMATICA DISCRETA***********************************************/
        Materia matemaricaDiscreta = new Materia();
        matemaricaDiscreta.setNombre("mat");
        matemaricaDiscreta.setDia(new Dia("martes", h.leerHora("18:15"), h.leerHora("19:44")));
        matemaricaDiscreta.setDia(new Dia("sabado", h.leerHora("21:15"), h.leerHora("22:44")));
        matemaricaDiscreta.setNRC("3866");
        op.guardarMateria(matemaricaDiscreta);

        Materia matemaricaDiscreta2 = new Materia();
        matemaricaDiscreta2.setNombre("mat");
        matemaricaDiscreta2.setDia(new Dia("jueves", h.leerHora("18:15"), h.leerHora("19:44")));
        matemaricaDiscreta2.setDia(new Dia("sabado", h.leerHora("19:00"), h.leerHora("20:29")));
        matemaricaDiscreta2.setNRC("3870");
        op.guardarMateria(matemaricaDiscreta2);
        /*****************************************************************************************************/

        /*********************************PLAN DE NEGOCIOS*****************************************************/
        Materia planDeNegocios = new Materia();
        planDeNegocios.setNombre("neg");
        planDeNegocios.setDia(new Dia("miercoles", h.leerHora("20:30"), h.leerHora("21:59")));
        planDeNegocios.setNRC("3464");
        op.guardarMateria(planDeNegocios);

        Materia planDeNegocios2 = new Materia();
        planDeNegocios2.setNombre("neg");
        planDeNegocios2.setDia(new Dia("jueves", h.leerHora("20:30"), h.leerHora("21:59")));
        planDeNegocios2.setNRC("7493");
        op.guardarMateria(planDeNegocios2);
        /******************************************************************************************************/

        /**********************************PROBABILIDAD Y ESTADISTICA*******************************************/
        Materia probabilidad = new Materia();
        probabilidad.setNombre("prob");
        probabilidad.setDia(new Dia("martes", h.leerHora("20:30"), h.leerHora("21:59")));
        probabilidad.setDia(new Dia("sabado", h.leerHora("19:00"), h.leerHora("20:29")));
        probabilidad.setNRC("3866");
        op.guardarMateria(probabilidad);
        /*******************************************************************************************************/

        op.imprimirMateriasRegistradas(op.materiasRegistradas);
        System.out.println("Minimo cuantas materias quiere meter? ");
        int filtro = Integer.parseInt(br.readLine());
        op.generarHorarioAcademico(tablero, 0, new Stack<String>(), filtro);
    }

}
