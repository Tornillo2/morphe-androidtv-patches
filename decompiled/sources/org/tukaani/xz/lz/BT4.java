package org.tukaani.xz.lz;

/* JADX INFO: loaded from: classes4.dex */
public final class BT4 extends LZEncoder {
    public int cyclicPos;
    public final int cyclicSize;
    public final int depthLimit;
    public final Hash234 hash;
    public int lzPos;
    public final Matches matches;
    public final int[] tree;

    public BT4(int i, int i2, int i3, int i4, int i5, int i6) {
        super(i, i2, i3, i4, i5);
        this.cyclicPos = -1;
        int i7 = i + 1;
        this.cyclicSize = i7;
        this.lzPos = i7;
        this.hash = new Hash234(i);
        this.tree = new int[i7 * 2];
        this.matches = new Matches(i4 - 1);
        this.depthLimit = i6 <= 0 ? (i4 / 2) + 16 : i6;
    }

    public static int getMemoryUsage(int i) {
        return (i / 128) + Hash234.getMemoryUsage(i) + 10;
    }

    /* JADX WARN: Code restructure failed: missing block: B:64:0x0150, code lost:
    
        r1 = r19.tree;
        r1[r7] = 0;
        r1[r5] = 0;
     */
    /* JADX WARN: Removed duplicated region for block: B:15:0x0063  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x00af A[PHI: r11
      0x00af: PHI (r11v3 int) = (r11v2 int), (r11v8 int) binds: [B:23:0x0089, B:29:0x00a7] A[DONT_GENERATE, DONT_INLINE]] */
    @Override // org.tukaani.xz.lz.LZEncoder
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public org.tukaani.xz.lz.Matches getMatches() {
        /*
            Method dump skipped, instruction units count: 344
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.tukaani.xz.lz.BT4.getMatches():org.tukaani.xz.lz.Matches");
    }

    public final int movePos() {
        int iMovePos = movePos(this.niceLen, 4);
        if (iMovePos != 0) {
            int i = this.lzPos + 1;
            this.lzPos = i;
            if (i == Integer.MAX_VALUE) {
                int i2 = Integer.MAX_VALUE - this.cyclicSize;
                this.hash.normalize(i2);
                LZEncoder.normalize(this.tree, i2);
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

    @Override // org.tukaani.xz.lz.LZEncoder
    public void skip(int i) {
        while (true) {
            int i2 = i - 1;
            if (i <= 0) {
                return;
            }
            int i3 = this.niceLen;
            int iMovePos = movePos();
            if (iMovePos >= i3) {
                this.hash.calcHashes(this.buf, this.readPos);
                int hash4Pos = this.hash.getHash4Pos();
                this.hash.updateTables(this.lzPos);
                skip(i3, hash4Pos);
            } else if (iMovePos != 0) {
                i3 = iMovePos;
                this.hash.calcHashes(this.buf, this.readPos);
                int hash4Pos2 = this.hash.getHash4Pos();
                this.hash.updateTables(this.lzPos);
                skip(i3, hash4Pos2);
            }
            i = i2;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:25:0x007c, code lost:
    
        r13 = r12.tree;
        r13[r2] = 0;
        r13[r1] = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x0082, code lost:
    
        return;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void skip(int r13, int r14) {
        /*
            r12 = this;
            int r0 = r12.depthLimit
            int r1 = r12.cyclicPos
            int r2 = r1 << 1
            int r2 = r2 + 1
            int r1 = r1 << 1
            r3 = 0
            r4 = 0
            r5 = 0
        Ld:
            int r6 = r12.lzPos
            int r6 = r6 - r14
            int r7 = r0 + (-1)
            if (r0 == 0) goto L7c
            int r0 = r12.cyclicSize
            if (r6 < r0) goto L19
            goto L7c
        L19:
            int r8 = r12.cyclicPos
            int r9 = r8 - r6
            if (r6 <= r8) goto L20
            goto L21
        L20:
            r0 = 0
        L21:
            int r9 = r9 + r0
            int r0 = r9 << 1
            int r8 = java.lang.Math.min(r4, r5)
            byte[] r9 = r12.buf
            int r10 = r12.readPos
            int r11 = r10 + r8
            int r11 = r11 - r6
            r11 = r9[r11]
            int r10 = r10 + r8
            r9 = r9[r10]
            if (r11 != r9) goto L55
        L36:
            int r8 = r8 + 1
            if (r8 != r13) goto L47
            int[] r13 = r12.tree
            r14 = r13[r0]
            r13[r1] = r14
            int r0 = r0 + 1
            r14 = r13[r0]
            r13[r2] = r14
            return
        L47:
            byte[] r9 = r12.buf
            int r10 = r12.readPos
            int r11 = r10 + r8
            int r11 = r11 - r6
            r11 = r9[r11]
            int r10 = r10 + r8
            r9 = r9[r10]
            if (r11 == r9) goto L36
        L55:
            byte[] r9 = r12.buf
            int r10 = r12.readPos
            int r11 = r10 + r8
            int r11 = r11 - r6
            r6 = r9[r11]
            r6 = r6 & 255(0xff, float:3.57E-43)
            int r10 = r10 + r8
            r9 = r9[r10]
            r9 = r9 & 255(0xff, float:3.57E-43)
            if (r6 >= r9) goto L72
            int[] r5 = r12.tree
            r5[r1] = r14
            int r0 = r0 + 1
            r14 = r5[r0]
            r1 = r0
            r5 = r8
            goto L7a
        L72:
            int[] r4 = r12.tree
            r4[r2] = r14
            r14 = r4[r0]
            r2 = r0
            r4 = r8
        L7a:
            r0 = r7
            goto Ld
        L7c:
            int[] r13 = r12.tree
            r13[r2] = r3
            r13[r1] = r3
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.tukaani.xz.lz.BT4.skip(int, int):void");
    }
}
