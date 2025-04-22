package archivos;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 *
 * @author Bryan
 */
public class Archivos {

    public static void main(String[] args) {
        //Añadir texto a un archivo existente
        LocalDate fechaActual = LocalDate.now();

        DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String fechaFormateada = fechaActual.format(formato);

        try {
            FileWriter archivo = new FileWriter("C:\\Users\\Usuario\\OneDrive\\Escritorio\\Ejercicio\\PrimerTexto.txt", true);

            archivo.write("Agregar texto\n");
            archivo.write("A un txt Existente" + "\n");
            archivo.write("La fecha es:"+fechaFormateada+"\n");

            archivo.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Agregar un array de strings a un archivo txt
        ArrayList<String> fruta = new ArrayList<>();
        fruta.add("Manzana");
        fruta.add("pera");
        fruta.add("fresa");

        try {
            FileWriter archivo = new FileWriter("C:\\Users\\Usuario\\OneDrive\\Escritorio\\Ejercicio\\PrimerTexto.txt", true);

            for (String linea : fruta) {
                archivo.write(linea + "\n");
            }

            archivo.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Escribir datos de un objeto en un archivo txt
        Persona personaNueva = new Persona("Bryan", "Esparza", "esparzabryan@gmail.com");

        try {
            FileWriter archivo = new FileWriter("C:\\Users\\Usuario\\OneDrive\\Escritorio\\Ejercicio\\PrimerTexto.txt", true);

            archivo.write(personaNueva.getNombre() + "\n");
            archivo.write(personaNueva.getApellido() + "\n");
            archivo.write(personaNueva.getCorreo() + "\n");

            archivo.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (FileWriter archivo = new FileWriter("C:\\Users\\Usuario\\OneDrive\\Escritorio\\Ejercicio\\PrimerTexto.txt", true)) {
            archivo.write("Texto escrito con try-with-resources.\n");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
