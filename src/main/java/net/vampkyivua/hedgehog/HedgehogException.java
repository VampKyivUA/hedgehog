package net.vampkyivua.hedgehog;

/**
 * Author: Mykhailo Drozdov
 * Created: 19.08.19 18:01
 */
public class HedgehogException extends RuntimeException {

    public HedgehogException() {
    }

    public HedgehogException(String s) {
        super(s);
    }

    public HedgehogException(String s, Throwable throwable) {
        super(s, throwable);
    }
}
