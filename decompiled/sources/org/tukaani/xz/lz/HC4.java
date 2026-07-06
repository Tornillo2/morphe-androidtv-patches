package org.tukaani.xz.lz;

/* JADX INFO: loaded from: classes4.dex */
public final class HC4 extends LZEncoder {
    public static final /* synthetic */ boolean $assertionsDisabled;
    public static /* synthetic */ Class class$org$tukaani$xz$lz$HC4;
    public final int[] chain;
    public int cyclicPos;
    public final int cyclicSize;
    public final int depthLimit;
    public final Hash234 hash;
    public int lzPos;
    public final Matches matches;

    static {
        if (class$org$tukaani$xz$lz$HC4 == null) {
            class$org$tukaani$xz$lz$HC4 = class$("org.tukaani.xz.lz.HC4");
        }
        $assertionsDisabled = true;
    }

    public HC4(int i, int i2, int i3, int i4, int i5, int i6) {
        super(i, i2, i3, i4, i5);
        this.cyclicPos = -1;
        this.hash = new Hash234(i);
        int i7 = i + 1;
        this.cyclicSize = i7;
        this.chain = new int[i7];
        this.lzPos = i7;
        this.matches = new Matches(i4 - 1);
        this.depthLimit = i6 <= 0 ? (i4 / 4) + 4 : i6;
    }

    public static /* synthetic */ Class class$(String str) throws Throwable {
        try {
            return Class.forName(str);
        } catch (ClassNotFoundException e) {
            throw new NoClassDefFoundError().initCause(e);
        }
    }

    public static int getMemoryUsage(int i) {
        return (i / 256) + Hash234.getMemoryUsage(i) + 10;
    }

    private int movePos() {
        int iMovePos = movePos(4, 4);
        if (iMovePos != 0) {
            int i = this.lzPos + 1;
            this.lzPos = i;
            if (i == Integer.MAX_VALUE) {
                int i2 = Integer.MAX_VALUE - this.cyclicSize;
                this.hash.normalize(i2);
                LZEncoder.normalize(this.chain, i2);
                this.lzPos -= i2;
            }
            int i3 = this.cyclicPos + 1;
            this.cyclicPos = i3;
            if (i3 == this.cyclicSize) {
                this.cyclicPos = 0;
            }
        }
        return iMovePos;
    }

    /* JADX WARN: Code restructure failed: missing block: B:60:0x0113, code lost:
    
        return r13.matches;
     */
    /* JADX WARN: Removed duplicated region for block: B:15:0x0067  */
    @Override // org.tukaani.xz.lz.LZEncoder
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public org.tukaani.xz.lz.Matches getMatches() {
        /*
            Method dump skipped, instruction units count: 276
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.tukaani.xz.lz.HC4.getMatches():org.tukaani.xz.lz.Matches");
    }

    @Override // org.tukaani.xz.lz.LZEncoder
    public void skip(int i) {
        if (!$assertionsDisabled && i < 0) {
            throw new AssertionError();
        }
        while (true) {
            int i2 = i - 1;
            if (i <= 0) {
                return;
            }
            if (movePos() != 0) {
                this.hash.calcHashes(this.buf, this.readPos);
                this.chain[this.cyclicPos] = this.hash.getHash4Pos();
                this.hash.updateTables(this.lzPos);
            }
            i = i2;
        }
    }
}
