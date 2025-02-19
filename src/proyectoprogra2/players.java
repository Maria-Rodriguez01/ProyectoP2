/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyectoprogra2;

/**
 *
 * @author Maria Gabriela
 */
public interface players {
    public usuarios[] getjugador();
    public int getcantusuarios();
    public usuarios getpa();
    void setpa(usuarios player);
    void addusuario(usuarios player);
    void eliminarusuario(usuarios player);
    public void cambiocontrasena(String nueva);
    public String getContrasena();
    
}
