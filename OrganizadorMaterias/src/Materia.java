import java.io.BufferedReader;
import java.util.ArrayList;

public class Materia {
    OperacionesMateria op = new OperacionesMateria();
    BufferedReader br = Main.br;

    //Atributos
    private String nombre;
    private ArrayList<Dia> dia = new ArrayList<Dia>();
    private String NRC;

    Materia(){};

    Materia(String nombre, Dia dia, String NRC){
        setNombre(nombre);
        setDia(dia);
        setNRC(NRC);
    }

    //Getters y Setters

    public String getNombre() { return nombre; }

    public void setNombre(String nombre) { this.nombre = nombre; }

    public ArrayList<Dia> getDia() { return dia; }

    public void setDia(Dia dia) {
        this.dia.add(dia);
    }

    public String getNRC() {
        return NRC;
    }

    public void setNRC(String NRC) {
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
