package piezas;

import javax.swing.*;


public abstract class piezas {
    protected String tipo;   
    protected int x;
    protected int y;
    protected ImageIcon pieza;
    
    public piezas(String tipo, int x, int y) {
        this.tipo = tipo;
        this.x = x;
        this.y = y;
        this.pieza= new ImageIcon(tipo);
    }

    public abstract boolean esMovimientoValido(int nuevaX, int nuevaY, piezas[][] tablero);

    protected boolean dentroDelTablero(int x, int y) {
        return x >= 0 && x < 9 && y >= 0 && y < 10;
    }
    
    public abstract boolean esRoja();
    

    protected boolean dentroDelPalacio(int x, int y) {
        if (esRoja()) {
            return x >= 3 && x <= 5 && y >= 0 && y <= 2;
        } else {
            return x >= 3 && x <= 5 && y >= 7 && y <= 9;
        }
    }
}

