package proyectoprogra2;
import proyectoprogra2.piezas;

public class Soldier extends piezas {
    public Soldier(int x, int y, String color, String rutaImagen) {
        super(x, y, color, rutaImagen);
    }
    
    @Override
    public boolean movimientoValido(int nuevoX, int nuevoY, piezas[][] tablero) {
        // Verificar que no se sale del tablero
        if (nuevoX < 0 || nuevoX > 8 || nuevoY < 0 || nuevoY > 9) {
            return false;
        }
        
        if (tablero[nuevoY][nuevoX] != null && tablero[nuevoY][nuevoX].getColor().equals(this.color)) {
            return false;
        }

        int difX = Math.abs(nuevoX - x);
        int difY = nuevoY - y;
        if (color.equals("rojo")) {
            
            if (y >= 5) {
                // Solo puede moverse una casilla hacia adelante (arriba)
                return difX == 0 && difY == -1;
            } else {
                // Después de cruzar el río puede moverse adelante o a los lados
                return (difX == 0 && difY == -1) || (difY == 0 && difX == 1);
            }
        } 
       
        else if (color.equals("negro")) {
            
            if (y <= 4) {
               
                return difX == 0 && difY == 1;
            } else {
               
                return (difX == 0 && difY == 1) || (difY == 0 && difX == 1);
            }
        }
        
        return false;
    }
} 
