package com.adrian.juego;

import com.adrian.clases.Casillas;
import com.adrian.clases.Jugador;
import com.adrian.clases.Tablero;
import com.adrian.excepciones.NJugadoresException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws NJugadoresException {
        ArrayList<Casillas> listaCasillas = new ArrayList<>();
        ArrayList<Jugador> listaJugadores = new ArrayList<>();
        Tablero.crearCasilla(listaCasillas);
        boolean partida = true;
        System.out.println("introduce el numero de jugadores");
        Scanner leerJugadores = new Scanner(System.in);
        int nJugadores = leerJugadores.nextInt();
        if (nJugadores < 2 || nJugadores > 4) {
            throw new NJugadoresException("El numero de jugadores de estar entre 2 y 4, numero introducido: " + nJugadores);
        }
        for (int i = 0; i < nJugadores; i++) {
            Tablero.crearJugador(listaJugadores, 1500);
        }
        while (partida) {
            for (int i = 0; i < nJugadores; i++) {
                Tablero.turnos(listaCasillas, listaJugadores, listaJugadores.get(i));
                if (listaJugadores.get(i).getDinero() == 0) {
                    System.out.println("El jugador " + listaJugadores.get(i).getNombre() + " queda eliminado por banca rota");
                    listaJugadores.remove(i);
                }
                if (listaJugadores.size() == 1) {
                    System.out.println("El jugador: " + listaJugadores.get(0).getNombre() + " consigue la victoria ser el ultimo en pie");
                    partida = false;
                    break;
                }
                if (Tablero.victoria(listaJugadores.get(i))) {
                    System.out.println("El jugador: " + listaJugadores.get(i).getNombre() + " consigue la victoria por monopolio");
                    partida = false;
                    break;
                }
            }
        }
    }
}
