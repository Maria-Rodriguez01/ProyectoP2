/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyectoprogra2;

import proyectoprogra2.piezas;



public class Elephant extends piezas {
    public Elephant(int x, int y, String color, String rutaImagen) {
        super(x, y, color, rutaImagen);
    }

    @Override
    public boolean movimientoValido(int nuevoX, int nuevoY, piezas[][] tablero) {
        if (Math.abs(nuevoX - x) != 2 || Math.abs(nuevoY - y) != 2) {
            return false;
        }

        if (color.equals("rojo") && nuevoY < 5) return false;
        if (color.equals("negro") && nuevoY > 4) return false;

        return tablero[(y + nuevoY) / 2][(x + nuevoX) / 2] == null;
    }
}
