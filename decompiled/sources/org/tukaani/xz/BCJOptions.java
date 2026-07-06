package org.tukaani.xz;

/* JADX INFO: loaded from: classes4.dex */
public abstract class BCJOptions extends FilterOptions {
    public static final /* synthetic */ boolean $assertionsDisabled;
    public static /* synthetic */ Class class$org$tukaani$xz$BCJOptions;
    public final int alignment;
    public int startOffset = 0;

    static {
        if (class$org$tukaani$xz$BCJOptions == null) {
            class$org$tukaani$xz$BCJOptions = class$("org.tukaani.xz.BCJOptions");
        }
        $assertionsDisabled = true;
    }

    public BCJOptions(int i) {
        this.alignment = i;
    }

    public static /* synthetic */ Class class$(String str) throws Throwable {
        try {
            return Class.forName(str);
        } catch (ClassNotFoundException e) {
            throw new NoClassDefFoundError().initCause(e);
        }
    }

    public Object clone() {
        try {
            return super.clone();
        } catch (CloneNotSupportedException unused) {
            if ($assertionsDisabled) {
                throw new RuntimeException();
            }
            throw new AssertionError();
        }
    }

    @Override // org.tukaani.xz.FilterOptions
    public int getDecoderMemoryUsage() {
        SimpleInputStream.getMemoryUsage();
        return 5;
    }

    @Override // org.tukaani.xz.FilterOptions
    public int getEncoderMemoryUsage() {
        SimpleOutputStream.getMemoryUsage();
        return 5;
    }

    public int getStartOffset() {
        return this.startOffset;
    }

    public void setStartOffset(int i) throws UnsupportedOptionsException {
        if (((this.alignment - 1) & i) == 0) {
            this.startOffset = i;
        } else {
            StringBuffer stringBuffer = new StringBuffer("Start offset must be a multiple of ");
            stringBuffer.append(this.alignment);
            throw new UnsupportedOptionsException(stringBuffer.toString());
        }
    }
}
