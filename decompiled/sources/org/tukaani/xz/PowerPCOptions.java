package org.tukaani.xz;

import java.io.InputStream;
import org.tukaani.xz.simple.PowerPC;

/* JADX INFO: loaded from: classes4.dex */
public class PowerPCOptions extends BCJOptions {
    public static final int ALIGNMENT = 4;

    public PowerPCOptions() {
        super(4);
    }

    @Override // org.tukaani.xz.FilterOptions
    public FilterEncoder getFilterEncoder() {
        return new BCJEncoder(this, 5L);
    }

    @Override // org.tukaani.xz.FilterOptions
    public InputStream getInputStream(InputStream inputStream) {
        return new SimpleInputStream(inputStream, new PowerPC(false, this.startOffset));
    }

    @Override // org.tukaani.xz.FilterOptions
    public FinishableOutputStream getOutputStream(FinishableOutputStream finishableOutputStream) {
        return new SimpleOutputStream(finishableOutputStream, new PowerPC(true, this.startOffset));
    }
}
