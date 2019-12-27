import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.LinkedList;

public class OperacionesMateria {

    public static ArrayList<Materia> materiasRegistradas = new ArrayList<Materia>();

    /**
     * Metodo para capturar la materia
     * @param br Lector
     * @throws IOException
     */
    public static void leerMateria(BufferedReader br) throws IOException {

        String respuesta = "";
        while (respuesta.equals(""))
        {
            Materia materia = new Materia();
            System.out.print("Introduzca el nombre de la materia: ");
            materia.setNombre(br.readLine());
            System.out.print("¿Cuantos dias ve esta materia?: ");
            materia.setDia();
            System.out.print("Introduzca el NRC de la materia: ");
            materia.setNRC(Integer.parseInt(br.readLine()));
            materia.imprimirMateriaRegistrada();
            System.out.print("Desea registrar otra materia? ENTER para registrar otra materia o escriba NO para no regisrar mas: ");
            materiasRegistradas.add(materia);
            respuesta = br.readLine();
        }
        imprimirMateriasRegistradas(materiasRegistradas);
    }

    /**
     * Metodo que imprime las materias que se han registrado actualmente
     * @param materiasRegistradas
     */
    public static void imprimirMateriasRegistradas(ArrayList<Materia> materiasRegistradas){
        for (int i = 0; i < materiasRegistradas.size(); i++) {

            System.out.println(materiasRegistradas.get(i).getNombre());

            for (int j = 0; j < materiasRegistradas.get(i).getDia().size(); j++) {
                System.out.println("Dia: "+materiasRegistradas.get(i).getDia().get(j).getNombre());
                System.out.println("Hora inicio: "+materiasRegistradas.get(i).getDia().get(j).getHoraInicio());
                System.out.println("Hora final: "+materiasRegistradas.get(i).getDia().get(j).getHoraFinal());
            }

            System.out.println("NRC: "+materiasRegistradas.get(i).getNRC());
            System.out.println("-------------------------------------------------");

        }
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
    public LinkedList<LocalTime> generarRango(LocalTime hora1, LocalTime hora2){
        LinkedList<LocalTime> rangos = new LinkedList<LocalTime>();
        while (hora1.compareTo(hora2) != 0){
            rangos.add(hora1);
            hora1 = hora1.plusMinutes(14);
            rangos.add(hora1);
            hora1 = hora1.plusMinutes(1);
        }
        return rangos;
    }

    /**
     * Metodo que genera el tablero
     * @param hora1
     * @param hora2
     * @return
     */
    public String[][] tableroHorario(LocalTime hora1, LocalTime hora2){
        LinkedList<LocalTime> rangosHoras = generarRango(hora1, hora2);
        String[][] horario = new String[rangosHoras.size()+1][7];

        for (int i = 0; i < horario.length; i++) {
            for (int j = 0; j < horario[i].length; j++) {
                if(i == 0 && j == 1){ horario[i][j] = "Lunes";}else if(i == 0 && j == 2){ horario[i][j] = "Martes";}else if(i == 0 && j == 3){ horario[i][j] = "Miercoles";}else if(i == 0 && j == 4){ horario[i][j] = "Jueves";}else if(i == 0 && j == 5){ horario[i][j] = "Viernes";}else if(i == 0 && j == 6){ horario[i][j] = "Sabado";}
                if(i > 0 && j == 0){horario[i][j] = rangosHoras.pop().toString();}
            }
        }

        return horario;
    }

    /**
     * Metodo que imrpime un horario
     * @param tablero
     */
    public void mostrarHorario(String[][] tablero){
        for (int i = 0; i < tablero.length; i++) {
            for (int j = 0; j < tablero[i].length; j++) {
                System.out.print(tablero[i][j]+" ");
            }
            System.out.println();
        }
    }

    /**
     * Metodo que buscara todos los posibles horarios para armar
     * @param materias
     * @param tableroHorario
     * @throws IOException
     */
    public void generarHorarioAcademico(ArrayList<Materia> materias, String[][] tableroHorario) throws IOException {
        for (int i = 0; i < materias.size(); i++) {//Obtener la materia

            //***************************DATOS MATERIA******************************
            Materia materia = materias.get(i);
            String nombreMateria = materia.getNombre();

            ArrayList<Dia> diasDeLaMateria = materia.getDia();

            int NRC = materia.getNRC();
            //***********************************************************************

            for (int j = 1; j < tableroHorario.length; j++) {       //Obtener
                for (int k = 1; k < tableroHorario[i].length; k++) {//El tablero


                    String diaSemanaHorario = tableroHorario[0][k];//Obtener dia de la semana del horario
                    String horaHorario = tableroHorario[j][0];//Obtener la hora en la que se recorre el horario

                    for(int l = 0; l < diasDeLaMateria.size(); l++){//Recorrer los dias que se ve la materia

                        //************* DATOS DEL DIA ACTUAL DE LA MATERIA********************
                        String nombreDiaMateria = diasDeLaMateria.get(l).getNombre();
                        String horaInicio = diasDeLaMateria.get(l).getHoraInicio().toString();
                        String horaFinal = diasDeLaMateria.get(l).getHoraFinal().toString();
                        //********************************************************************

                        if( diaSemanaHorario.equals( nombreDiaMateria ) &&//Si el nombre de la materia concuerda con el dia del horario
                            dentroDelRango(horaHorario, horaInicio, horaFinal)//Si sus horas estan dentro del rango del horario
                        ){
                            tableroHorario[j][k] = nombreMateria;
                         }

                    }//Fin de recorrer los dias de la materia


                }//Fin de las columnas del tablero
            }//Fin de las filas del tablero



        }
        mostrarHorario(tableroHorario);
    }

    /**
     * Verificar si la hora1 es igual a la hora2
     * @param hora1
     * @param hora2
     * @return
     * @throws IOException
     */
    boolean esIgual(String hora1, String hora2) throws IOException {
        LocalTime horaInicial = leerHora(hora1);
        LocalTime horaAComparar = leerHora(hora2);
        boolean comparador = (horaInicial.equals(horaAComparar)) ? true : false;
        return  comparador;
    }

    /**
     * Verificar si la hora 1 es menor que la hora2
     * @param hora1
     * @param hora2
     * @return
     * @throws IOException
     */
    boolean esMenor(String hora1, String hora2) throws IOException {
        LocalTime horaInicial = leerHora(hora1);
        LocalTime horaAComparar = leerHora(hora2);
        boolean comparador = (horaInicial.isBefore(horaAComparar)) ? true : false;
        return  comparador;
    }

    /**
     * Verificar si la hora1 es mayor que la hora2
     * @param hora1
     * @param hora2
     * @return
     * @throws IOException
     */
    boolean esMayor( String hora1, String hora2 ) throws IOException {
        LocalTime horaInicial = leerHora(hora1);
        LocalTime horaAComparar = leerHora(hora2);
        boolean comparador = (horaInicial.isAfter(horaAComparar)) ? true : false;
        return  comparador;
    }


    boolean dentroDelRango(String horaTablero, String horaInicio, String horaFinal) throws IOException {
        if( ( esIgualOMayor(horaTablero, horaInicio) && esIgualOMenor(horaTablero, horaFinal) ) ){
            return true;
        }
        else{
            return false;
        }
    }

    boolean esIgualOMayor(String horaAcomparar, String horaLimite) throws IOException {
        boolean resultado = (esIgual( horaAcomparar, horaLimite) || esMayor(horaAcomparar, horaLimite)) ? true : false;
        return resultado;
    }

    boolean esIgualOMenor(String horaAComparar, String horaLimite) throws IOException {
        boolean resultado = (esIgual( horaAComparar, horaLimite) || esMenor(horaAComparar, horaLimite)) ? true : false;
        return resultado;
    }

}
