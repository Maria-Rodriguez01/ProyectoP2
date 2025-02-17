package piezas;

import proyectoprogra2.piezas;

public class Advisor extends piezas{
    public Advisor(int x, int y) {
        super("CONSEJERO_NEGRO", x, y);
    }
    
    @Override
    public boolean esRoja() {
        return false;
    }
    
    @Override
    public boolean esMovimientoValido(int nuevaX, int nuevaY, piezas[][] tablero) {
        // El Consejero solo puede moverse dentro del palacio
        if (!dentroDelPalacio(nuevaX, nuevaY)) {
            return false;
        }
        
        // El Consejero solo puede moverse una casilla diagonalmente
        int deltaX = Math.abs(nuevaX - x);
        int deltaY = Math.abs(nuevaY - y);
        
        return (deltaX == 1 && deltaY == 1);
    }
}

