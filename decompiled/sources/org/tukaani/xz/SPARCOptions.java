package org.tukaani.xz;

import java.io.InputStream;
import org.tukaani.xz.simple.SPARC;

/* JADX INFO: loaded from: classes4.dex */
public class SPARCOptions extends BCJOptions {
    public static final int ALIGNMENT = 4;

    public SPARCOptions() {
        super(4);
    }

    @Override // org.tukaani.xz.FilterOptions
    public FilterEncoder getFilterEncoder() {
        return new BCJEncoder(this, 9L);
    }

    @Override // org.tukaani.xz.FilterOptions
    public InputStream getInputStream(InputStream inputStream) {
        return new SimpleInputStream(inputStream, new SPARC(false, this.startOffset));
    }

    @Override // org.tukaani.xz.FilterOptions
    public FinishableOutputStream getOutputStream(FinishableOutputStream finishableOutputStream) {
        return new SimpleOutputStream(finishableOutputStream, new SPARC(true, this.startOffset));
    }
}
