import java.io.BufferedReader;
import java.io.IOException;
import java.time.LocalTime;
import java.util.ArrayList;

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
                System.out.println("Introduzca el nombre del dia "+(i+1));
                String nombreDia = br.readLine();
                System.out.println("Introduzca la hora de inicio del dia "+(i+1));
                LocalTime horaInicio = Hora.leerHora(br.readLine());
                System.out.println("Introduzca la hora de final del dia "+(i+1));
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


    public void generarHorarioAcademico(String[][] tableroHorario, int materiaElegida) throws IOException {
        String[][] aux = null;
        if(materiaElegida == materiasRegistradas.size()){//Caso base
            System.out.println("Horario generado");
            posiblesHorarios.add(tableroHorario);
            Horario.mostrarHorario(tableroHorario);
            return;
        }else {
            //******************iterar materia individualmente*******
            for (int subMateria = 0; subMateria < materiasRegistradas.get(materiaElegida).size(); subMateria++) {
                //obtener materia individualmente
                Materia materia = materiasRegistradas.get(materiaElegida).get(subMateria);
                //introducir materia segun el dia
                introducirMateriaDiaActual(materiaElegida,subMateria,tableroHorario,materia);
                //Verificar que se ha introducido
                //Horario.mostrarHorario(tableroHorario);
                //Recursividad
                generarHorarioAcademico(duplicarTablero(tableroHorario), materiaElegida+1);
                //Limpiar la materia que introducimos
                tableroHorario = limpiarUltimaMateria(tableroHorario, materia.getNombre());

            }
        }
    }

    static String[][] limpiarUltimaMateria(String[][] tableroHorario, String nombreMateria){
        String[][] aux = duplicarTablero(tableroHorario);
        for (int i = 1; i < aux.length; i++) {
            for (int j = 1; j < aux[i].length; j++) {
                if(aux[i][j].equals(nombreMateria)){
                    aux[i][j] = " * ";
                }
            }
        }
        return aux;
    }

    static void introducirMateriaDiaActual(int materiaElegida, int subMateria, String[][] tableroHorario, Materia materia) throws IOException {
        String nombreMateria = materia.getNombre();
        ArrayList<Dia> diaMateria = materiasRegistradas.get(materiaElegida).get(subMateria).getDia();
        for (int diaMatPos = 0; diaMatPos < diaMateria.size(); diaMatPos++) {
            String diaActMat = diaMateria.get(diaMatPos).getNombre();
            String horaInicio = materiasRegistradas.get(materiaElegida).get(subMateria).getDia().get(diaMatPos).getHoraInicio().toString();
            String horaFinal = materiasRegistradas.get(materiaElegida).get(subMateria).getDia().get(diaMatPos).getHoraFinal().toString();

            boolean cruce = hayCruce(tableroHorario, diaMateria, materia);
            if (cruce == true) {
                break;
            } else {//Si no hay cruce se procede a meterla
                for (int x = 1; x < tableroHorario.length; x++) {
                    for (int y = 0; y < tableroHorario[x].length; y++) {
                        if (tableroHorario[0][y].equals(diaActMat) &&
                                Hora.dentroDelRango(tableroHorario[x][0], horaInicio, horaFinal)) {
                            tableroHorario[x][y] = nombreMateria;
                        }
                    }
                }
            }
        }
    }

    static boolean hayCruce(String[][] tablero, ArrayList<Dia> dias, Materia materia) throws IOException {

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
            for (int i = 1; i < tablero.length; i++) {
                for (int j = 1; j < tablero[i].length; j++) {
                    if (tablero[0][j].equals(nombreDia) &&
                            !tablero[i][j].equals(" * ") &&
                            !tablero[i][j].equals(materia.getNombre()) &&
                            Hora.dentroDelRango(tablero[i][0], horaInicio, horaFinal)) {
                        System.out.println("La materia " + materia.getNombre() + " se cruza con la materia " + tablero[i][j] + " el dia " + nombreDia);
                        return true;
                    }
                }
            }

        }
        return false;
    }

    static boolean dentroDelRangoDelHorario(String horaInicio, String horaFinal, String[][] tablero) throws IOException {
        if (Hora.esMenor(horaInicio, tablero[1][0]) || Hora.esMayor(horaFinal, tablero[tablero.length - 1][0])) {
            return false;
        }
        return true;
    }

    static String[][] duplicarTablero(String[][] tablero){
        String[][] aux = new String[tablero.length][tablero[0].length];
        for (int i = 0; i < tablero.length; i++) {
            for (int j = 0; j < tablero[i].length; j++) {
                aux[i][j] = tablero[i][j];
            }
        }
        return aux;
    }

}
