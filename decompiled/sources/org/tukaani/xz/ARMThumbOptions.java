package org.tukaani.xz;

import java.io.InputStream;
import org.tukaani.xz.simple.ARMThumb;

/* JADX INFO: loaded from: classes4.dex */
public class ARMThumbOptions extends BCJOptions {
    public static final int ALIGNMENT = 2;

    public ARMThumbOptions() {
        super(2);
    }

    @Override // org.tukaani.xz.FilterOptions
    public FilterEncoder getFilterEncoder() {
        return new BCJEncoder(this, 8L);
    }

    @Override // org.tukaani.xz.FilterOptions
    public InputStream getInputStream(InputStream inputStream) {
        return new SimpleInputStream(inputStream, new ARMThumb(false, this.startOffset));
    }

    @Override // org.tukaani.xz.FilterOptions
    public FinishableOutputStream getOutputStream(FinishableOutputStream finishableOutputStream) {
        return new SimpleOutputStream(finishableOutputStream, new ARMThumb(true, this.startOffset));
    }
}
