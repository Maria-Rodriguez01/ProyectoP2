/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyectoprogra2;

import proyectoprogra2.piezas;

/**
 *
 * @author Maria Gabriela
 */
public class Horse2 extends piezas{
    public Horse2(int x, int y) {
        super("src/imagenes/horser", x, y);
    }
    
    @Override
    public boolean esRoja() {
        return true;
    }
    
    @Override
    public boolean esMovimientoValido(int nuevaX, int nuevaY, piezas[][] tablero) {
        int deltaX = Math.abs(nuevaX - x);
        int deltaY = Math.abs(nuevaY - y);
        
        // El Caballo se mueve en patrón de 2-1
        if (!((deltaX == 2 && deltaY == 1) || (deltaX == 1 && deltaY == 2))) {
            return false;
        }
        
        // Verificar que no hay pieza bloqueando el movimiento
        int bloqueoX = x;
        int bloqueoY = y;
        
        if (deltaX == 2) {
            bloqueoX = x + (nuevaX - x) / 2;
        } else {
            bloqueoY = y + (nuevaY - y) / 2;
        }
        
        return tablero[bloqueoY][bloqueoX] == null;
    }
}
