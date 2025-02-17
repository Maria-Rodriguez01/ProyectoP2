/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package piezas;

import piezas.piezas;
import proyectoprogra2.piezas;

/**
 *
 * @author Maria Gabriela
 */
class General2 extends piezas {
    public General2(int x,int y) {
        super("src/imagenes/generalr", x, y);
    }
    
    @Override
    public boolean esRoja() {
        return true;
    }
    
    @Override
    public boolean esMovimientoValido(int nuevaX, int nuevaY, piezas[][] tablero) {
        // El General solo puede moverse dentro del palacio
        if (!dentroDelPalacio(nuevaX, nuevaY)) {
            return false;
        }
        
        // El General solo puede moverse una casilla ortogonalmente
        int deltaX = Math.abs(nuevaX - x);
        int deltaY = Math.abs(nuevaY - y);
        
        return (deltaX + deltaY == 1);
    }
}
