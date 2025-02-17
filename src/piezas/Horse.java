package piezas;


/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


public class Horse extends piezas{
   public Horse(int x, int y) {
        super("CABALLO_NEGRO", x, y);
    }
    
    @Override
    public boolean esRoja() {
        return false;
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

