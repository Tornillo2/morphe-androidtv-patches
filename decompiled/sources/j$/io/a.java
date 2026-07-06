package j$.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/* JADX INFO: loaded from: classes2.dex */
public final class a implements Iterator {
    public final BufferedReader a;
    public String b = null;

    public a(BufferedReader bufferedReader) {
        this.a = bufferedReader;
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        if (this.b != null) {
            return true;
        }
        try {
            String line = this.a.readLine();
            this.b = line;
            return line != null;
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    @Override // java.util.Iterator
    public final Object next() {
        if (this.b != null || hasNext()) {
            String str = this.b;
            this.b = null;
            return str;
        }
        throw new NoSuchElementException();
    }
}
