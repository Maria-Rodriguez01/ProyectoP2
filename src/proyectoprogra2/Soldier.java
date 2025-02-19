package proyectoprogra2;

import proyectoprogra2.piezas;

public class Soldier extends piezas {
    public Soldier(int x, int y, String color, String rutaImagen) {
        super(x, y, color, rutaImagen);
    }

    @Override
    public boolean movimientoValido(int nuevoX, int nuevoY, piezas[][] tablero) {
        return (color.equals("rojo") && nuevoY == y - 1) || (color.equals("negro") && nuevoY == y + 1);
    }
}
