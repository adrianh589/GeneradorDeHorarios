import java.io.BufferedReader;
import java.io.IOException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

public class OperacionesMateria {

    public static ArrayList<ArrayList<Materia>> materiasRegistradas = new ArrayList<ArrayList<Materia>>();
    public static ArrayList<String[][]> posiblesHorarios = new ArrayList<String[][]>();

    /**
     * Metodo para capturar la materia
     *
     * @param br Lector
     * @throws IOException
     */
    public static void leerMateria(BufferedReader br) throws IOException {

        String respuesta = "";
        while (respuesta.equals("")) {
            Materia materia = new Materia();
            System.out.print("Introduzca el nombre de la materia: ");
            materia.setNombre(br.readLine().toLowerCase());
            System.out.print("¿Cuantos dias ve esta materia?: ");
            int numDias = Integer.parseInt(br.readLine());

            for (int i = 0; i < numDias; i++) {
                System.out.println("Introduzca el nombre del dia " + (i + 1));
                String nombreDia = br.readLine();
                System.out.println("Introduzca la hora de inicio del dia " + (i + 1));
                LocalTime horaInicio = Hora.leerHora(br.readLine());
                System.out.println("Introduzca la hora de final del dia " + (i + 1));
                LocalTime horaFinal = Hora.leerHora(br.readLine());
                materia.setDia(new Dia(nombreDia, horaInicio, horaFinal));
            }

            System.out.print("Introduzca el NRC de la materia: ");
            materia.setNRC(br.readLine());
            materia.imprimirMateriaRegistrada();
            System.out.print("Desea registrar otra materia? ENTER para registrar otra materia o escriba NO para no regisrar mas: ");
            guardarMateria(materia);
            respuesta = br.readLine();
        }
        imprimirMateriasRegistradas(materiasRegistradas);
    }

    /**
     * Metodo para guardar una materia en un mismo arreglo que tenga el mismo nombre
     *
     * @param materia Materia generada
     */
    public static void guardarMateria(Materia materia) {

        boolean encontrada = false;
        for (int i = 0; i < materiasRegistradas.size(); i++) {
            for (int j = 0; j < materiasRegistradas.get(i).size(); j++) {
                if (materiasRegistradas.get(i).get(j).getNombre().equals(materia.getNombre())) {
                    materiasRegistradas.get(i).add(materia);
                    encontrada = true;
                    break;
                }
            }
        }

        if (encontrada == false) {
            ArrayList<Materia> materiasIguales = new ArrayList<Materia>();
            materiasIguales.add(materia);
            materiasRegistradas.add(materiasIguales);
        }

    }

    /**
     * Metodo que imprime las materias que se han registrado actualmente
     *
     * @param materiasRegistradas
     */
    public static void imprimirMateriasRegistradas(ArrayList<ArrayList<Materia>> materiasRegistradas) {
        for (int i = 0; i < materiasRegistradas.size(); i++) {//Obtener el arrayList maestro

            for (int j = 0; j < materiasRegistradas.get(i).size(); j++) {//Recorremos los array list internos

                System.out.println(materiasRegistradas.get(i).get(j).getNombre());

                for (int k = 0; k < materiasRegistradas.get(i).get(j).getDia().size(); k++) {//Recorremos los dias de la materia
                    System.out.println("Dia " + materiasRegistradas.get(i).get(j).getDia().get(k).getNombre());
                    System.out.println("Hora inicio: " + materiasRegistradas.get(i).get(j).getDia().get(k).getHoraInicio());
                    System.out.println("Hora final: " + materiasRegistradas.get(i).get(j).getDia().get(k).getHoraFinal());
                }

                System.out.println("NRC: " + materiasRegistradas.get(i).get(j).getNRC());
                System.out.println("-------------------------------------------------");
            }
        }
    }

    /**
     * Metodo con Backtracking que genera los posibles horios con las materias deseadas
     *
     * @param tableroHorario Tablero academico
     * @param materiaElegida Materia a escoger (de cada grupo)
     * @param nrcs           Pila que guarda los nrc
     * @throws IOException
     */
    public void generarHorarioAcademico(String[][] tableroHorario, int materiaElegida, Stack<String> nrcs, int filtro) throws IOException {

        if (materiaElegida == materiasRegistradas.size()) {//Caso base
            if(nrcs.size() >= filtro && !horarioRepetido(tableroHorario)) {
                posiblesHorarios.add(tableroHorario);
                System.out.println("Horario generado");
                System.out.println(nrcs);
                Horario.mostrarHorario(tableroHorario);
                System.out.println(nrcs);
                System.out.println("********************************************************\n\n");
            }
            return;
        } else {
            //******************iterar materia individualmente*******
            for (int subMateria = 0; subMateria < materiasRegistradas.get(materiaElegida).size(); subMateria++) {
                //obtener materia individualmente
                Materia materia = materiasRegistradas.get(materiaElegida).get(subMateria);
                //introducir materia segun el dia
                boolean asignar = introducirMateriaDiaActual(materia, tableroHorario);
                if (asignar) {
                    nrcs.add(materia.getNombre() + ": " + materia.getNRC());
                }

                //Recursividad
                generarHorarioAcademico(duplicarTablero(tableroHorario), materiaElegida + 1, recordarNRCS(nrcs), filtro);

                //Limpiar la materia que introducimos
                tableroHorario = limpiarUltimaMateria(tableroHorario, materia.getNombre());
                if (!nrcs.isEmpty() && asignar == true) {
                    nrcs.pop();
                }
            }
        }
    }

    static boolean horarioRepetido(String[][] tablero){
        for (int i = 0; i < posiblesHorarios.size(); i++) {
            if(Arrays.deepEquals(posiblesHorarios.get(i), tablero)){
                return true;
            }
        }
        return false;
    }

    /**
     * Metodo para evitar modificaciones en la pila maestra
     *
     * @param nrcs
     * @return
     */
    static Stack<String> recordarNRCS(Stack<String> nrcs) {
        Stack<String> recordar = new Stack<String>();
        for (int i = 0; i < nrcs.size(); i++) {
            recordar.add(nrcs.get(i));
        }
        return recordar;
    }

    /**
     * Elimina rastro de la ultima materia introducida en el horario
     *
     * @param tableroHorario Tablero del horario
     * @param nombreMateria  Nombre de la materia elegida
     * @return
     */
    static String[][] limpiarUltimaMateria(String[][] tableroHorario, String nombreMateria) {
        String[][] aux = duplicarTablero(tableroHorario);
        for (int i = 1; i < aux.length; i++) {
            for (int j = 1; j < aux[i].length; j++) {
                if (aux[i][j].equals(nombreMateria)) {
                    aux[i][j] = " * ";
                }
            }
        }
        return aux;
    }

    static boolean introducirMateriaDiaActual(Materia materia, String[][] tableroHorario) throws IOException {
        //************DATOS MATERIA******************
        String nombreMateria = materia.getNombre();
        ArrayList<Dia> diaMateria = materia.getDia();
        //********************************************
        if (hayCruce(tableroHorario, materia) == true) {
            //Si hay cruce no se mete
            return false;
        }

        for (int diaMatPos = 0; diaMatPos < diaMateria.size(); diaMatPos++) {
            //****************DATOS DEL DIA ACTUAL DE LA MATERIA***************************
            String diaActMat = diaMateria.get(diaMatPos).getNombre();
            String horaInicio = materia.getDia().get(diaMatPos).getHoraInicio().toString();
            String horaFinal = materia.getDia().get(diaMatPos).getHoraFinal().toString();
            //*****************************************************************************

            int posJdia = posicionDelDia(tableroHorario, diaActMat);

            for (int d = 1; d < tableroHorario.length; d++) {
                if (Hora.dentroDelRango(tableroHorario[d][0], horaInicio, horaFinal)) {
                    tableroHorario[d][posJdia] = nombreMateria;
                }
            }

        }

        return true;//Si se metio la materia, retornamos true
    }

    static int posicionDelDia(String[][] tablero, String dia) {
        int pos = -1;
        for (int i = 0; i < tablero[0].length; i++) {
            if (tablero[0][i].equals(dia)) {
                pos = i;
                break;
            }
        }
        return pos;
    }

    /**
     * @param tablero
     * @param materia
     * @return
     * @throws IOException
     */
    static boolean hayCruce(String[][] tablero, Materia materia) throws IOException {

        ArrayList<Dia> dias = materia.getDia();
        String nombreMateria = materia.getNombre();

        for (int numDia = 0; numDia < dias.size(); numDia++) {

            //***********DATOS DEL DIA ACTUAL*****************
            String nombreDia = dias.get(numDia).getNombre();
            String horaInicio = dias.get(numDia).getHoraInicio().toString();
            String horaFinal = dias.get(numDia).getHoraFinal().toString();
            //************************************************

            //Comprobar que este dentro del rango del horario
            if (!dentroDelRangoDelHorario(horaInicio, horaFinal, tablero)) {
                System.out.println("La materia " + materia.getNombre() + " no se puede meter porque no esta dentro del rango " + tablero[1][0] + " - " + tablero[tablero.length - 1][0]);
                return true;
            }

            //Comprobar que no se cruce con ninguna materia
            int posJdia = posicionDelDia(tablero, nombreDia);

            for (int d = 1; d < tablero.length; d++) {
                if (Hora.dentroDelRango(tablero[d][0], horaInicio, horaFinal) &&
                        !tablero[d][posJdia].equals(" * ")) {
                    //System.out.println("La materia " + materia.getNombre() + " se cruza con la materia " + tablero[d][posJdia] + " el dia " + nombreDia);
                    return true;
                }
            }

        }
        return false;
    }

    /**
     * Metodo que indica si una materia se encuentra dentro del rango del usuario segun el dia
     *
     * @param horaInicio hora de inicio de la materia
     * @param horaFinal  hora final de la materia
     * @param tablero
     * @return
     * @throws IOException
     */
    static boolean dentroDelRangoDelHorario(String horaInicio, String horaFinal, String[][] tablero) throws IOException {
        if (Hora.esMenor(horaInicio, tablero[1][0]) || Hora.esMayor(horaFinal, tablero[tablero.length - 1][0])) {
            return false;
        }
        return true;
    }

    /**
     * Metodo que duplica el tablero para evitar modificaciones
     *
     * @param tablero
     * @return
     */
    static String[][] duplicarTablero(String[][] tablero) {
        String[][] aux = new String[tablero.length][tablero[0].length];
        for (int i = 0; i < tablero.length; i++) {
            for (int j = 0; j < tablero[i].length; j++) {
                aux[i][j] = tablero[i][j];
            }
        }
        return aux;
    }

}
