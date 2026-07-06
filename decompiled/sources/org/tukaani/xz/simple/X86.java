package org.tukaani.xz.simple;

/* JADX INFO: loaded from: classes4.dex */
public final class X86 implements SimpleFilter {
    public static final boolean[] MASK_TO_ALLOWED_STATUS = {true, true, true, false, true, false, false, false};
    public static final int[] MASK_TO_BIT_NUMBER = {0, 1, 2, 2, 3, 3, 3, 3};
    public final boolean isEncoder;
    public int pos;
    public int prevMask = 0;

    public X86(boolean z, int i) {
        this.isEncoder = z;
        this.pos = i + 5;
    }

    public static boolean test86MSByte(byte b) {
        int i = b & 255;
        return i == 0 || i == 255;
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x003e  */
    @Override // org.tukaani.xz.simple.SimpleFilter
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public int code(byte[] r11, int r12, int r13) {
        /*
            Method dump skipped, instruction units count: 206
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.tukaani.xz.simple.X86.code(byte[], int, int):int");
    }
}
