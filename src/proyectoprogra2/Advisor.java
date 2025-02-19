package proyectoprogra2;

public class Advisor extends piezas {

    public Advisor(int x, int y, String color, String rutaImagen) {
        super(x, y, color, rutaImagen);
    }

    private boolean verificarMovimientosDiagonales(int dx, int dy, int pasos) {
        // Caso base 1: Si ya dimos un paso, verificamos si llegamos a destino
        if (pasos == 1) {
            return dx == 0 && dy == 0;
        }
        
        // Caso base 2: Si las coordenadas no son válidas para un movimiento diagonal
        if (Math.abs(dx) != 1 || Math.abs(dy) != 1) {
            return false;
        }
        
        // Verificar si estamos dentro del palacio
        int nuevaX = x + dx;
        int nuevaY = y + dy;
        
        if (nuevaX < 3 || nuevaX > 5) {
            return false;
        }
        
        if (color.equals("rojo")) {
            if (nuevaY < 7 || nuevaY > 9) {
                return false;
            }
        } else { // negro
            if (nuevaY < 0 || nuevaY > 2) {
                return false;
            }
        }
        
        // Llamada recursiva avanzando un paso
        return verificarMovimientosDiagonales(0, 0, pasos + 1);
    }

    @Override
    public boolean movimientoValido(int nuevoX, int nuevoY, piezas[][] tablero) {
        return verificarMovimientosDiagonales(nuevoX - x, nuevoY - y, 0);
    }
    public boolean verificarMovimientosPalacioRecursivo(int startX, int startY, int profundidad) {
   
    if (profundidad > 2) { 
        return false;
    }
    
    
    if (!dentroPalacio(startX, startY)) {
        return false;
    }
    
    return verificarMovimientosPalacioRecursivo(startX + 1, startY + 1, profundidad + 1) ||
           verificarMovimientosPalacioRecursivo(startX + 1, startY - 1, profundidad + 1) ||
           verificarMovimientosPalacioRecursivo(startX - 1, startY + 1, profundidad + 1) ||
           verificarMovimientosPalacioRecursivo(startX - 1, startY - 1, profundidad + 1);
}

private boolean dentroPalacio(int x, int y) {
    if (x < 3 || x > 5) return false;
    if (color.equals("rojo")) {
        return y >= 7 && y <= 9;
    } else {
        return y >= 0 && y <= 2;
    }
}

    
}
