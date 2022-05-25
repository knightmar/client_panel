package fr.knightmar.client_tcp_rust.utils;

import fr.knightmar.client_tcp_rust.connexion.ConnexionManager;

public class ConnexionManagerInstanceSaver {
    public static ConnexionManager connexionManager;

    public static ConnexionManager getConnexionManager() {
        return connexionManager;
    }
}
