package proyectoprogra2;
import javax.swing.*;
import java.awt.*;


public abstract class piezas {
    protected int x, y;
    protected String color; 
    protected ImageIcon imagen;

    public piezas(int x, int y, String color, String rutaImagen) {
        this.x = x;
        this.y = y;
        this.color = color;
        this.imagen = new ImageIcon(rutaImagen);
    }

    public void dibujar(Graphics g, int tamano) {
        g.drawImage(imagen.getImage(), x * 60, y * 60, 60, 60, null);
    }

    public boolean esMismoColor(piezas otra) {
        return otra != null && this.color.equals(otra.color);
    }

    public abstract boolean movimientoValido(int nuevoX, int nuevoY, piezas[][] tablero);

    public void mover(int nuevoX, int nuevoY, piezas[][] tablero) {
        if (movimientoValido(nuevoX, nuevoY, tablero)) {
            if (tablero[nuevoY][nuevoX] == null || !esMismoColor(tablero[nuevoY][nuevoX])) {
                tablero[y][x] = null;
                tablero[nuevoY][nuevoX] = this;
                this.x = nuevoX;
                this.y = nuevoY;
            }
        }
    }
    
    public void setLocation(int x, int y){
        this.x=x;
        this.y=y;
    }

    public String getColor() {
        return color; }
    public int getX() {
        return x; }
    public int getY() {
        return y; }
}
