package dev.syntax.step02_factory;

public class TapeReaderFactory {
    public static TapeReader CreateTapeReader(){
        return new TapeReader();
    }
}
