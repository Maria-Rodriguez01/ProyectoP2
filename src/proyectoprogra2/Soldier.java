package proyectoprogra2;

public class Soldier extends piezas {
    public Soldier(int x, int y, String color, String rutaImagen) {
        super(x, y, color, rutaImagen);
    }
    
    @Override
    public boolean movimientoValido(int nuevoX, int nuevoY, piezas[][] tablero) {
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
                return difX == 0 && difY == -1;
            } else {
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
