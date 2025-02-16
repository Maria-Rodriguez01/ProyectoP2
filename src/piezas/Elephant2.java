/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package piezas;

/**
 *
 * @author Maria Gabriela
 */
public class Elephant2 extends piezas{
    public Elephant2(int x, int y) {
        super("src/imagenes/elephantr", x, y);
    }
    
    @Override
    public boolean esRoja() {
        return true;
    }
    
    @Override
    public boolean esMovimientoValido(int nuevaX, int nuevaY, piezas[][] tablero) {
        // El Elefante no puede cruzar el río
        if (nuevaY > 4) return false;
        
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

