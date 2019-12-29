import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

public class OperacionesMateria {

    public static ArrayList<ArrayList<Materia>> materiasRegistradas = new ArrayList<ArrayList<Materia>>();

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
            //materia.setDia();
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


    public void generarHorarioAcademico(String[][] tableroHorario) throws IOException {

        //********************OBTENER ARRAY MAESTRO*****************************************************************
        for (int arrayMaestro = 0; arrayMaestro < materiasRegistradas.size(); arrayMaestro++) {
            System.out.println(materiasRegistradas.size());

            //******************Obtener materia individualmente*******
            for (int materiaIndividual = 0; materiaIndividual < materiasRegistradas.get(arrayMaestro).size(); materiaIndividual++) {

                //*************DATOS MATERIA*********************
                Materia materia = materiasRegistradas.get(arrayMaestro).get(materiaIndividual);
                String nombreMateria = materiasRegistradas.get(arrayMaestro).get(materiaIndividual).getNombre();
                ArrayList<Dia> diaMateria = materiasRegistradas.get(arrayMaestro).get(materiaIndividual).getDia();
                String NRC = materiasRegistradas.get(arrayMaestro).get(materiaIndividual).getNRC();
                //**************FIN DATOS MATERIA****************

                //***********PARA EL DIA ACTUAL DE LA MATERIA**********************************************************
                for (int diaMatPos = 0; diaMatPos < diaMateria.size(); diaMatPos++) {
                    String diaActMat = diaMateria.get(diaMatPos).getNombre();
                    String horaInicio = materiasRegistradas.get(arrayMaestro).get(materiaIndividual).getDia().get(diaMatPos).getHoraInicio().toString();
                    String horaFinal = materiasRegistradas.get(arrayMaestro).get(materiaIndividual).getDia().get(diaMatPos).getHoraFinal().toString();

                    boolean cruce = hayCruce(tableroHorario, diaMateria, materia);
                    if (cruce == true) {
                        break;
                    } else {
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
                //*********************************FIN DIA ACTUAL DE LA MATERIA********************************************

            }//**************************************FIN MATERIA INDIVIDUAL************************************************

        }//********************************** FIN ARRAY MAESTRO ***********************************************************
        Horario.mostrarHorario(tableroHorario);
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

}
