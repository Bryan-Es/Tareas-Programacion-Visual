/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package ejerciciostream;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 *
 * @author Bryan
 */
public class EjercicioStream {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ArrayList<Producto> pro = new ArrayList<>();
        // Hogar
        Producto pro1 = new Producto("Lavadora", 680, "Hogar");
        Producto pro7 = new Producto("Refrigerador", 900, "Hogar");
        Producto pro8 = new Producto("Microondas", 250, "Hogar");
        Producto pro9 = new Producto("Aspiradora", 320, "Hogar");
        Producto pro10 = new Producto("Cafetera", 180, "Hogar");

// Electronica
        Producto pro2 = new Producto("Celular", 750, "Electronica");
        Producto pro3 = new Producto("Laptop", 1200, "Electronica");
        Producto pro4 = new Producto("Auriculares", 150, "Electronica");
        Producto pro5 = new Producto("Tablet", 400, "Electronica");
        Producto pro6 = new Producto("Smartwatch", 300, "Electronica");

// Ropa
        Producto pro11 = new Producto("Camisa", 40, "Ropa");
        Producto pro12 = new Producto("Pantalón", 60, "Ropa");
        Producto pro13 = new Producto("Chaqueta", 120, "Ropa");
        Producto pro14 = new Producto("Zapatos", 85, "Ropa");
        Producto pro15 = new Producto("Vestido", 90, "Ropa");

// Alimentacion
        Producto pro16 = new Producto("Manzanas", 5, "Alimentacion");
        Producto pro17 = new Producto("Leche", 2, "Alimentacion");
        Producto pro18 = new Producto("Arroz", 1, "Alimentacion");
        Producto pro19 = new Producto("Carne", 10, "Alimentacion");
        Producto pro20 = new Producto("Pan", 3, "Alimentacion");

        pro.add(pro1);
        pro.add(pro2);
        pro.add(pro3);
        pro.add(pro4);
        pro.add(pro5);
        pro.add(pro6);
        pro.add(pro7);
        pro.add(pro8);
        pro.add(pro9);
        pro.add(pro10);
        pro.add(pro11);
        pro.add(pro12);
        pro.add(pro13);
        pro.add(pro14);
        pro.add(pro15);
        pro.add(pro16);
        pro.add(pro17);
        pro.add(pro18);
        pro.add(pro19);
        pro.add(pro20);

        boolean bucle = true;
        do {
            System.out.println("Seleccione que desea ver:");
            System.out.println("1. Lista de productos con precio > 100");
            System.out.println("2. Lista con solo los nombres de los productos");
            System.out.println("3. Suma de los precios de todos los productos");
            System.out.println("4. Agrupa los productos por categoría");
            System.out.println("5. Encuentra el producto con mayor precio");
            System.out.println("6. Cuenta cuántos productos tienen precio < 80");
            System.out.println("7. Salir.");
            System.out.println("Ingrese el numero del literal que desea ver:");
            int opcion;
            Scanner scanner = new Scanner(System.in);
            opcion = scanner.nextInt();
            switch (opcion) {
                case 1: {
                    List<Producto> precios = pro.stream()
                            .filter(elemento -> elemento.getPrecio() > 100)
                            .collect(Collectors.toList());

                    for (Producto p : precios) {
                        System.out.println(p.getPrecio());
                    }
                    break;
                }
                case 2: {
                    List<String> nombres = pro.stream()
                            .map(Producto::getNombre)
                            .collect(Collectors.toList());
                    System.out.println(nombres);
                    break;
                }
                case 3: {
                    double total = pro.stream()
                            .mapToDouble(Producto::getPrecio)
                            .sum();
                    System.out.println(total);
                    break;
                }
                case 4: {
                    Map<String, List<Producto>> productosPorCategoria = pro.stream()
                            .collect(Collectors.groupingBy(Producto::getCategoria));
                    System.out.println(productosPorCategoria);
                    break;
                }
                case 5: {
                    Producto productoMasCaro = pro.stream()
                            .max(Comparator.comparingDouble(Producto::getPrecio))
                            .orElse(null);
                    System.out.println(productoMasCaro);
                    break;
                }
                case 6: {
                    long productosBaratos = pro.stream()
                            .filter(p -> p.getPrecio() < 80)
                            .count();
                    System.out.println(productosBaratos);
                    break;
                }
                case 7:{
                    bucle=false;
                    break;
                }
            }

        } while (bucle);
    }

}
