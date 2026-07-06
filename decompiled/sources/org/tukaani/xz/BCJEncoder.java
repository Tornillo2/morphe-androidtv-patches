package org.tukaani.xz;

/* JADX INFO: loaded from: classes4.dex */
public class BCJEncoder extends BCJCoder implements FilterEncoder {
    public static final /* synthetic */ boolean $assertionsDisabled;
    public static /* synthetic */ Class class$org$tukaani$xz$BCJEncoder;
    public final long filterID;
    public final BCJOptions options;
    public final byte[] props;

    static {
        if (class$org$tukaani$xz$BCJEncoder == null) {
            class$org$tukaani$xz$BCJEncoder = class$("org.tukaani.xz.BCJEncoder");
        }
        $assertionsDisabled = true;
    }

    public BCJEncoder(BCJOptions bCJOptions, long j) {
        if (!$assertionsDisabled && !BCJCoder.isBCJFilterID(j)) {
            throw new AssertionError();
        }
        int startOffset = bCJOptions.getStartOffset();
        if (startOffset == 0) {
            this.props = new byte[0];
        } else {
            this.props = new byte[4];
            for (int i = 0; i < 4; i++) {
                this.props[i] = (byte) (startOffset >>> (i * 8));
            }
        }
        this.filterID = j;
        this.options = (BCJOptions) bCJOptions.clone();
    }

    public static /* synthetic */ Class class$(String str) throws Throwable {
        try {
            return Class.forName(str);
        } catch (ClassNotFoundException e) {
            throw new NoClassDefFoundError().initCause(e);
        }
    }

    @Override // org.tukaani.xz.FilterEncoder
    public long getFilterID() {
        return this.filterID;
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
        return false;
    }
}
