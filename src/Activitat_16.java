import java.io.*;
import java.util.Arrays;
import java.util.Scanner;

public class Activitat_16 {
    public static void main(String[] args) {
        //Supongo un tamaño máximo de 200 nombres pero fácilemte modificable
        int numMaxNombres=200;
        String[] nombres = new String[numMaxNombres];
        int numNombres = 0;

        //Abro el flujo de entrada para leer los nombres existentes en nombres.dat
        try (ObjectInputStream entrada = new ObjectInputStream(new FileInputStream("nombres.dat"))) {
            //Supongo que los nombres están guardados como un array de Strings
            nombres = (String[]) entrada.readObject();
            numNombres = nombres.length;
        } catch (EOFException ex) {
            System.out.println("Fin de fichero");
        } catch (IOException ex) {
            System.out.println(ex);
        } catch (ClassNotFoundException e) {
            System.out.println("El fichero no almacena un objeto tabla");
        }

        //Leo nombres desde teclado
        Scanner in = new Scanner(System.in);
        String nuevoNombre;
        System.out.println("Introduce nombres nuevos e introduce 'fin' para terminar de introducirlos: ");
        while (true) {
            nuevoNombre = in.nextLine();
            if (nuevoNombre.equals("fin")) {
                break;
            }
            //añado el nuevo nombre y subo el contador
            nombres[numNombres] = nuevoNombre;
            numNombres++;
            //ordeno el Array
            Arrays.sort(nombres);
            //compruebo que no hayamos llegado al máximo arbitrario de 200 nombres
            if (numNombres == numMaxNombres) {
                System.out.println("Se ha alcanzado el límite máximo de 200 nombres");
                break;
            }
        }

        // Guardo la lista de nombres actualizada y ordenada alfabéticamente en nombres.dat
        try (ObjectOutputStream salida = new ObjectOutputStream(new FileOutputStream("nombres.dat"))) {
            salida.writeObject(nombres);
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }
}

