/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package tarea;

import java.util.Scanner;

/**
 *
 * @author Bryan
 */
public class Tarea {

    public static void main(String[] args) {
        boolean bucle = true;
        do {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Elija una opcion:");
            System.out.println("1. Hilos de numeros (1 seg), de letras (2 seg)");
            System.out.println("2. Sincronizacion de hilos.");
            System.out.println("3. Uso del Wait(), Notify()");
            System.out.println("4. Salir.");
            int opc = scanner.nextInt();
            switch (opc) {
                case 1: {
                    Runnable tarea = new Runnable() {
                        public void run() {
                            System.out.println("Inicio del hilo");
                            for (int i = 1; i <= 5; i++) {
                                System.out.println("Hilo 1: " + i);
                                try {
                                    Thread.sleep(1000);
                                } catch (InterruptedException e) {
                                    System.out.println("El hilo fue interrumpido");
                                }
                            }

                            System.out.println("Fin del hilo 1");
                        }
                    };

                    Thread hilo = new Thread(tarea);

                    hilo.start();

                    Runnable tarea2 = new Runnable() {
                        public void run() {
                            System.out.println("Inicio del hilo");

                            for (char letra = 'A'; letra <= 'E'; letra++) {
                                System.out.println("Hilo 2: " + letra);

                                try {
                                    Thread.sleep(2000);
                                } catch (InterruptedException e) {
                                    System.out.println("El hilo fue interrumpido");
                                }
                            }

                            System.out.println("Fin del hilo 2");
                        }
                    };
                    Thread hilo2 = new Thread(tarea2);
                    hilo2.start();
                    break;
                }
                case 2: {
                    final int[] contador = {10};
                    // Objeto de bloqueo para sincronización
                    final Object lock = new Object();
                    // Hilo que incrementa
                    Runnable incrementador = new Runnable() {
                        public void run() {
                            for (int i = 0; i < 5; i++) {
                                synchronized (lock) {
                                    contador[0]++;
                                    System.out.println("Incrementado: " + contador[0]);
                                }
                                try {
                                    Thread.sleep(1000);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    };
                    Thread hilo3 = new Thread(incrementador);
                    hilo3.start();

                    // Hilo que decrementa
                    Runnable decrementador = new Runnable() {

                        public void run() {
                            for (int i = 0; i < 5; i++) {
                                synchronized (lock) {
                                    contador[0]--;
                                    System.out.println("Decrementado: " + contador[0]);
                                }
                                try {
                                    Thread.sleep(1000);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    };
                    Thread hilo4 = new Thread(decrementador);
                    hilo4.start();
                    break;
                }
                case 3:{
                    // Objeto compartido que contiene el número y la bandera
                    final Object lock2 = new Object();
                    final int[] numero = new int[1];
                    final boolean[] disponible = {false};

                    // Hilo PRODUCTOR
                    Runnable productor = new Runnable() {
                        public void run() {
                            for (int i = 0; i < 5; i++) {
                                synchronized (lock2) {
                                    while (disponible[0]) {
                                        try {
                                            lock2.wait();
                                        } catch (InterruptedException e) {
                                        }
                                    }

                                    numero[0] = (int) (Math.random() * 100);
                                    disponible[0] = true;
                                    System.out.println("Hilo Productor genera: " + numero[0]);

                                    lock2.notify();
                                }

                                try {
                                    Thread.sleep(1000);
                                } catch (InterruptedException e) {
                                }
                            }
                        }
                    };
                    Thread hilo5 = new Thread(productor);
                    hilo5.start();

                    // Hilo CONSUMIDOR
                    Runnable consumidor = new Runnable() {
                        public void run() {
                            for (int i = 0; i < 5; i++) {
                                synchronized (lock2) {
                                    while (!disponible[0]) {
                                        try {
                                            lock2.wait();
                                        } catch (InterruptedException e) {
                                        }
                                    }

                                    System.out.println("Hilo Consumidor imprime: " + numero[0]);
                                    disponible[0] = false;

                                    lock2.notify();
                                }

                                try {
                                    Thread.sleep(1000);
                                } catch (InterruptedException e) {
                                }
                            }
                        }
                    };
                    Thread hilo6 = new Thread(consumidor);
                    hilo6.start();
                    break;
                }
                case 4:{
                    bucle=false;
                    break;
                }
            }
        } while (bucle);

    }

}
