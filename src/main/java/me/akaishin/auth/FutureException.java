package me.akaishin.auth;

public class FutureException {
    public static <T extends Throwable> void manjearExcepcion(Throwable throwable) throws Throwable {
        throw throwable;
    }
}
