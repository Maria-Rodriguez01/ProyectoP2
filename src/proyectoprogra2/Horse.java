package proyectoprogra2;


public class Horse extends piezas {
    public Horse(int x, int y, String color, String rutaImagen) {
        super(x, y, color, rutaImagen);
    }

    @Override
    public boolean movimientoValido(int nuevoX, int nuevoY, piezas[][] tablero) {
        int dx = nuevoX - x;
        int dy = nuevoY - y;

        if (Math.abs(dx) == 2 && Math.abs(dy) == 1) {
            return tablero[y][x + dx / 2] == null;
        } else if (Math.abs(dx) == 1 && Math.abs(dy) == 2) {
            return tablero[y + dy / 2][x] == null;
        }
        return false;
    }
}
