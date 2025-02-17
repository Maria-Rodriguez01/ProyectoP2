/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package piezas;


/**
 *
 * @author Maria Gabriela
 */
public class Chariot2 extends piezas{
    
    public Chariot2(int x, int y) {
        super("src/imagenes/chariotr", x, y);
    }
    
    @Override
    public boolean esRoja() {
        return true;
    }
    
    @Override
    public boolean esMovimientoValido(int nuevaX, int nuevaY, piezas[][] tablero) {
        // El Carro solo puede moverse ortogonalmente
        if (nuevaX != x && nuevaY != y) {
            return false;
        }
        
        // Verificar que no hay piezas en el camino
        if (nuevaX == x) {
            int inicio = Math.min(y, nuevaY);
            int fin = Math.max(y, nuevaY);
            for (int tempY = inicio + 1; tempY < fin; tempY++) {
                if (tablero[tempY][x] != null) return false;
            }
        } else {
            int inicio = Math.min(x, nuevaX);
            int fin = Math.max(x, nuevaX);
            for (int tempX = inicio + 1; tempX < fin; tempX++) {
                if (tablero[y][tempX] != null) return false;
            }
        }
        
        return true;
    }
}

