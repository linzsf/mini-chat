package org.lzsf.mc.client.manager;

public class AuthManager {

    private static String jwtToken;

    public static void setJwtToken(String jwtToken) {
        AuthManager.jwtToken = jwtToken;
    }
}
