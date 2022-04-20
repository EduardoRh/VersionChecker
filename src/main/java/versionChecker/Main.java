package versionChecker;

import versionChecker.Util.VerifyUpdate;

public class Main {
    public static void main(String[] args) {
        System.out.println("Ejecutando...");
        VerifyUpdate verifyUpdate = new VerifyUpdate();

        verifyUpdate.verifyUpdate(result -> {
            System.out.println("Actualizacion: " + result);
        });
    }
}
