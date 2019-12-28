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
            materia.setDia();
            System.out.print("Introduzca el NRC de la materia: ");
            materia.setNRC(Integer.parseInt(br.readLine()));
            materia.imprimirMateriaRegistrada();
            System.out.print("Desea registrar otra materia? ENTER para registrar otra materia o escriba NO para no regisrar mas: ");
            agregarMateria(materia);
            respuesta = br.readLine();
        }
        imprimirMateriasRegistradas(materiasRegistradas);
    }

    public static void agregarMateria(Materia materia) {

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

}
