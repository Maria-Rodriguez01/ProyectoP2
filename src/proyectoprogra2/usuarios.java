package proyectoprogra2;

public class usuarios implements players {

    private String usuario;
    private String contrasena;
    private int puntaje;
    private int cantpart;
    private usuarios[] jugador = new usuarios[100];
    private int cantusuarios = 0;
    private usuarios pa = null;
    private int partjugadas = 0;

    public usuarios(String usuario, String contrasena) {
        this.usuario = usuario;
        this.contrasena = contrasena;
        this.puntaje = 0;
        this.cantpart = 0;
    }

    @Override
    public int getcantusuarios() {
        return cantusuarios;
    }

    public String getUsuario() {
        return usuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    @Override
    public usuarios[] getjugador() {
        return jugador;
    }

    @Override
    public usuarios getpa() {
        return pa;
    }

    @Override
    public void setpa(usuarios player) {
        if (player != null) {
        pa = player;
    }
    }

    public int getpartjugadas() {
        return partjugadas;
    }

    //cambio de contrasena
    public void cambiocontrasena(String nueva) {
        this.contrasena = nueva;
    }

    @Override
    public void addusuario(usuarios player) {
        if (cantusuarios < jugador.length) {
            jugador[cantusuarios] = player;
            cantusuarios++;
        } 
    }

    public void agregarp() {
        this.puntaje +=3 ;

    }

    //Saber cuantas partidas tiene el user
    public void nuevapartida() {
        this.partjugadas++;
    }

    //eliminar usuario activo
    @Override
    public void eliminarusuario(usuarios player) {
        for (int i = 0; i < cantusuarios-1; i++) {
            if (jugador[i] == player) {
                for (int j = i; j < cantusuarios; j++) {
                    jugador[j] = jugador[j + 1];
                }
                jugador[cantusuarios - 1] = null;
                cantusuarios--;
                return;
            }

        }
    }

}
