package proyectoprogra2;

public class usuarios implements players {

    private String usuario;
    private String contrasena;
    private int puntaje;
    private int cantpart;
    private int partjugadas;

    private static usuarios instancia;
    private final usuarios[] jugador;
    private int cantusuarios;
    private usuarios pa;
    private static final int MAX_USUARIOS = 100;

    // Constructor para usuarios individuales
    public usuarios(String usuario, String contrasena) {
        if (usuario == null && contrasena == null) {
            this.jugador = new usuarios[MAX_USUARIOS];
            this.cantusuarios = 0;
            this.pa = null;
        } else {
            this.usuario = usuario;
            this.contrasena = contrasena;
            this.puntaje = 0;
            this.cantpart = 0;
            this.partjugadas = 0;
            this.jugador = null;
        }
    }

    public static usuarios obtenerInstancia() {
        if (instancia == null) {
            instancia = new usuarios(null, null);
        }
        return instancia;
    }

    // Implementación de la interfaz players
    @Override
    public usuarios[] getjugador() {
        return jugador;
    }

    @Override
    public int getcantusuarios() {
        return cantusuarios;
    }

    @Override
    public usuarios getpa() {
        return pa;
    }

    @Override
    public void setpa(usuarios player) { 
            pa = player;
        
    }

    @Override
    public void addusuario(usuarios player) {
        if (player != null && cantusuarios < MAX_USUARIOS) {
            jugador[cantusuarios] = player;
            cantusuarios++;
        }
    }

    @Override
    public void eliminarusuario(usuarios player) {
        for (int i = 0; i < cantusuarios; i++) {
            if (jugador[i] == player) {
                for (int j = i; j < cantusuarios - 1; j++) {
                    jugador[j] = jugador[j + 1];
                }
                jugador[cantusuarios - 1] = null;
                cantusuarios--;
                if (pa == player) {
                    pa = null;
                }
                break;
            }
        }
    }

    public String getUsuario() {
        return usuario;
    }

    public String getContrasena() {
        return contrasena;
    }
        public void setContrasena(String nuevacontrasena) {
        this.contrasena=nuevacontrasena;
    }

    public int getPuntaje() {
        return puntaje;
    }

    public int getCantpart() {
        return cantpart;
    }

    public int getpartjugadas() {
        return partjugadas;
    }

    public void cambiocontrasena(String nueva) {
        if (nueva != null && !nueva.trim().isEmpty()) {
            this.contrasena = nueva;
        }
    }

    public void agregarp() {
        this.puntaje += 3;
    }

    public void nuevapartida() {
        this.partjugadas++;
        this.cantpart++;
    }
}