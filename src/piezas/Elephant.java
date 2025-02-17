/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package piezas;

import proyectoprogra2.piezas;


public class Elephant extends piezas {
    public Elephant(int x, int y) {
        super("ELEFANTE_NEGRO", x, y);
    }
    
    @Override
    public boolean esRoja() {
        return false;
    }
    
    @Override
    public boolean esMovimientoValido(int nuevaX, int nuevaY, piezas[][] tablero) {
        // El Elefante no puede cruzar el río
        if (nuevaY < 5) return false;
        
        // El Elefante se mueve exactamente dos casillas en diagonal
        int deltaX = Math.abs(nuevaX - x);
        int deltaY = Math.abs(nuevaY - y);
        
        if (deltaX != 2 || deltaY != 2) return false;
        
        // Verificar que no hay pieza en el punto medio (no puede saltar)
        int puntoMedioX = (x + nuevaX) / 2;
        int puntoMedioY = (y + nuevaY) / 2;
        
        return tablero[puntoMedioY][puntoMedioX] == null;
    }
}