package org.tukaani.xz;

import java.io.InputStream;
import org.tukaani.xz.simple.ARM;

/* JADX INFO: loaded from: classes4.dex */
public class ARMOptions extends BCJOptions {
    public static final int ALIGNMENT = 4;

    public ARMOptions() {
        super(4);
    }

    @Override // org.tukaani.xz.FilterOptions
    public FilterEncoder getFilterEncoder() {
        return new BCJEncoder(this, 7L);
    }

    @Override // org.tukaani.xz.FilterOptions
    public InputStream getInputStream(InputStream inputStream) {
        return new SimpleInputStream(inputStream, new ARM(false, this.startOffset));
    }

    @Override // org.tukaani.xz.FilterOptions
    public FinishableOutputStream getOutputStream(FinishableOutputStream finishableOutputStream) {
        return new SimpleOutputStream(finishableOutputStream, new ARM(true, this.startOffset));
    }
}
