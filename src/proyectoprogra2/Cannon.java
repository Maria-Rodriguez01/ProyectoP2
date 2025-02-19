/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyectoprogra2;




public class Cannon extends piezas {
    public Cannon(int x, int y, String color, String rutaImagen) {
        super(x, y, color, rutaImagen);
    }

    @Override
    public boolean movimientoValido(int nuevoX, int nuevoY, piezas[][] tablero) {
        if (x != nuevoX && y != nuevoY) return false;

        int obstaculos = 0;
        int min = Math.min(y, nuevoY);
        int max = Math.max(y, nuevoY);
        for (int i = min + 1; i < max; i++) {
            if (tablero[i][x] != null) obstaculos++;
        }

        min = Math.min(x, nuevoX);
        max = Math.max(x, nuevoX);
        for (int i = min + 1; i < max; i++) {
            if (tablero[y][i] != null) obstaculos++;
        }

        return (tablero[nuevoY][nuevoX] == null && obstaculos == 0) ||
               (tablero[nuevoY][nuevoX] != null && obstaculos == 1);
    }
}
