package mayton.chess;

import java.io.OutputStream;
import java.io.PrintStream;

public class NullPrintStream extends PrintStream {

    public NullPrintStream(OutputStream out) {
        super(out);
    }
}
