package piezas;

public class Soldier2 extends piezas{
     public Soldier2(int x, int y) {
        super("SOLDADO_ROJO", x, y);
    }
    
    @Override
    public boolean esRoja() {
        return true;
    }
    
    @Override
    public boolean esMovimientoValido(int nuevaX, int nuevaY, piezas[][] tablero) {
        int deltaX = Math.abs(nuevaX - x);
        int deltaY = nuevaY - y;
        
        // El Soldado solo puede moverse una casilla a la vez
        if (deltaX + Math.abs(deltaY) != 1) return false;
        
        // Antes de cruzar el río (para el rojo)
        if (y < 5) {
            // Solo puede moverse hacia adelante
            return deltaX == 0 && deltaY == 1;
        } else {
            // Puede moverse hacia adelante o lateralmente
            return deltaY >= 0 || deltaX > 0;
        }
    }
}
