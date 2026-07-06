package org.tukaani.xz;

/* JADX INFO: loaded from: classes4.dex */
public class DeltaEncoder extends DeltaCoder implements FilterEncoder {
    public final DeltaOptions options;
    public final byte[] props;

    public DeltaEncoder(DeltaOptions deltaOptions) {
        this.props = new byte[]{(byte) (deltaOptions.getDistance() - 1)};
        this.options = (DeltaOptions) deltaOptions.clone();
    }

    @Override // org.tukaani.xz.FilterEncoder
    public long getFilterID() {
        return 3L;
    }

    @Override // org.tukaani.xz.FilterEncoder
    public byte[] getFilterProps() {
        return this.props;
    }

    @Override // org.tukaani.xz.FilterEncoder
    public FinishableOutputStream getOutputStream(FinishableOutputStream finishableOutputStream) {
        return this.options.getOutputStream(finishableOutputStream);
    }

    @Override // org.tukaani.xz.FilterEncoder
    public boolean supportsFlushing() {
        return true;
    }
}
