package proyectoprogra2;

import proyectoprogra2.piezas;

public class General extends piezas {
    public General(int x, int y, String color, String rutaImagen) {
        super(x, y, color, rutaImagen);
    }

    @Override
    public boolean movimientoValido(int nuevoX, int nuevoY, piezas[][] tablero) {
        if (Math.abs(nuevoX - x) + Math.abs(nuevoY - y) != 1) {
            return false;
        }
        if (nuevoX < 3 || nuevoX > 5) return false;
        if (color.equals("rojo") && (nuevoY < 7 || nuevoY > 9)) return false;
        if (color.equals("negro") && (nuevoY < 0 || nuevoY > 2)) return false;

        return true;
    }   

    
}




