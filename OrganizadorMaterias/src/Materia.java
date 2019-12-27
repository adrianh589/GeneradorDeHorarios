import java.io.BufferedReader;
import java.io.IOException;
import java.time.LocalTime;
import java.util.ArrayList;

public class Materia {
    OperacionesMateria op = new OperacionesMateria();
    BufferedReader br = Main.br;

    //Atributos
    private String nombre;
    private ArrayList<Dia> dia = new ArrayList<Dia>();
    private int NRC;

    Materia(){};

    //Getters y Setters

    public String getNombre() { return nombre; }

    public void setNombre(String nombre) { this.nombre = nombre; }

    public ArrayList<Dia> getDia() { return dia; }

    public void setDia() throws IOException {
        int numdias = Integer.parseInt(br.readLine());
        for (int i = 0; i < numdias; i++) {
            Dia diaSemana = new Dia();
            System.out.print("Nombre del dia "+(i+1)+" que la ve: ");
            diaSemana.setNombre(br.readLine());
            System.out.print("Hora de inicio del dia " + diaSemana.getNombre()+": ");
            diaSemana.setHoraInicio(op.leerHora(br.readLine()));
            System.out.print("Hora final del dia " + diaSemana.getNombre()+": ");
            diaSemana.setHoraFinal(op.leerHora(br.readLine()));
            this.dia.add(diaSemana);
        }
    }

    public int getNRC() {
        return NRC;
    }

    public void setNRC(int NRC) {
        this.NRC = NRC;
    }

    public void imprimirMateriaRegistrada(){
        System.out.println(this.getNombre());
        for (int i = 0; i < this.getDia().size(); i++) {
            System.out.println("Dia: "+this.dia.get(i).getNombre());
            System.out.println("Hora inicio: "+this.dia.get(i).getHoraInicio());
            System.out.println("Hora final: "+this.dia.get(i).getHoraFinal());
        }

        System.out.println("NRC: "+this.NRC);
        System.out.println("-------------------------------------------------");

    }
}
