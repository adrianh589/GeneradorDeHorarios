import java.time.LocalTime;
import java.util.LinkedList;

public class Horario {

    /**
     * Metodo que genera el tablero
     * @param hora1
     * @param hora2
     * @return
     */
    public String[][] tableroHorario(LocalTime hora1, LocalTime hora2){
        LinkedList<LocalTime> rangosHoras = Hora.generarRango(hora1, hora2);
        String[][] horario = new String[rangosHoras.size()+1][8];

        for (int i = 0; i < horario.length; i++) {
            for (int j = 0; j < horario[i].length; j++) {
                if(i == 0 && j == 1){ horario[i][j] = "lunes";}else if(i == 0 && j == 2){ horario[i][j] = "martes";}else if(i == 0 && j == 3){ horario[i][j] = "miercoles";}else if(i == 0 && j == 4){ horario[i][j] = "jueves";}else if(i == 0 && j == 5){ horario[i][j] = "viernes";}else if(i == 0 && j == 6){ horario[i][j] = "sabado";}else if(i == 0 && j == 7){ horario[i][j] = "virtual";}
                if(i > 0 && j == 0){horario[i][j] = rangosHoras.pop().toString();}
                if(horario[i][j]==null){ horario[i][j]=" * "; }
            }
        }

        return horario;
    }

    /**
     * Metodo que imrpime un horario
     * @param tablero
     */
    public static void mostrarHorario(String[][] tablero){
        for (int i = 0; i < tablero.length; i++) {
            for (int j = 0; j < tablero[i].length; j++) {
                if(i==0 && j==0){System.out.print(tablero[i][j].substring(0,3)+"   ");}
                else if(i==0 && j!=0){System.out.print(tablero[i][j].substring(0,3)+" ");}
                else if(j==0){System.out.print(tablero[i][j]+" ");}
                else {System.out.print(tablero[i][j].substring(0,3)+" "); }
            }
            System.out.println();
        }
    }

}
