/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package piezas;



public class Cannon extends piezas {
    public Cannon(int x, int y) {
        super("CANON_NEGRO", x, y);
    }
    
    @Override
    public boolean esRoja() {
        return false;
    }
    
    @Override
    public boolean esMovimientoValido(int nuevaX, int nuevaY, piezas[][] tablero) {
        // El Cañón solo puede moverse ortogonalmente
        if (nuevaX != x && nuevaY != y) {
            return false;
        }
        
        int piezasEnMedio = 0;
        
        // Contar piezas en el camino
        if (nuevaX == x) {
            int inicio = Math.min(y, nuevaY);
            int fin = Math.max(y, nuevaY);
            for (int tempY = inicio + 1; tempY < fin; tempY++) {
                if (tablero[tempY][x] != null) piezasEnMedio++;
            }
        } else {
            int inicio = Math.min(x, nuevaX);
            int fin = Math.max(x, nuevaX);
            for (int tempX = inicio + 1; tempX < fin; tempX++) {
                if (tablero[y][tempX] != null) piezasEnMedio++;
            }
        }
        
        // Si hay una pieza en la posición destino
        boolean hayPiezaEnDestino = tablero[nuevaY][nuevaX] != null;
        
        // Para capturar necesita exactamente una pieza en medio
        if (hayPiezaEnDestino) return piezasEnMedio == 1;
        
        // Para moverse no debe haber piezas en medio
        return piezasEnMedio == 0;
    }
}
