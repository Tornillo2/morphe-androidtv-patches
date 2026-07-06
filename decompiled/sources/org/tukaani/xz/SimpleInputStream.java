package org.tukaani.xz;

import java.io.IOException;
import java.io.InputStream;
import org.tukaani.xz.simple.SimpleFilter;

/* JADX INFO: loaded from: classes4.dex */
public class SimpleInputStream extends InputStream {
    public static final /* synthetic */ boolean $assertionsDisabled;
    public static final int TMPBUF_SIZE = 4096;
    public static /* synthetic */ Class class$org$tukaani$xz$SimpleInputStream;
    public InputStream in;
    public final SimpleFilter simpleFilter;
    public final byte[] tmpbuf = new byte[4096];
    public int pos = 0;
    public int filtered = 0;
    public int unfiltered = 0;
    public boolean endReached = false;
    public IOException exception = null;

    static {
        if (class$org$tukaani$xz$SimpleInputStream == null) {
            class$org$tukaani$xz$SimpleInputStream = class$("org.tukaani.xz.SimpleInputStream");
        }
        $assertionsDisabled = true;
    }

    public SimpleInputStream(InputStream inputStream, SimpleFilter simpleFilter) {
        inputStream.getClass();
        if (!$assertionsDisabled && simpleFilter != null) {
            throw new AssertionError();
        }
        this.in = inputStream;
        this.simpleFilter = simpleFilter;
    }

    public static /* synthetic */ Class class$(String str) throws Throwable {
        try {
            return Class.forName(str);
        } catch (ClassNotFoundException e) {
            throw new NoClassDefFoundError().initCause(e);
        }
    }

    public static int getMemoryUsage() {
        return 5;
    }

    @Override // java.io.InputStream
    public int available() throws IOException {
        if (this.in == null) {
            throw new XZIOException("Stream closed");
        }
        IOException iOException = this.exception;
        if (iOException == null) {
            return this.filtered;
        }
        throw iOException;
    }

    @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        InputStream inputStream = this.in;
        if (inputStream != null) {
            try {
                inputStream.close();
            } finally {
                this.in = null;
            }
        }
    }

    @Override // java.io.InputStream
    public int read() throws IOException {
        byte[] bArr = new byte[1];
        if (read(bArr, 0, 1) == -1) {
            return -1;
        }
        return bArr[0] & 255;
    }

    /* JADX WARN: Code restructure failed: missing block: B:45:0x00a2, code lost:
    
        if (r1 <= 0) goto L47;
     */
    /* JADX WARN: Code restructure failed: missing block: B:46:0x00a4, code lost:
    
        return r1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:47:0x00a5, code lost:
    
        return -1;
     */
    @Override // java.io.InputStream
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public int read(byte[] r11, int r12, int r13) throws java.io.IOException {
        /*
            r10 = this;
            if (r12 < 0) goto Lb2
            if (r13 < 0) goto Lb2
            int r0 = r12 + r13
            if (r0 < 0) goto Lb2
            int r1 = r11.length
            if (r0 > r1) goto Lb2
            r0 = 0
            if (r13 != 0) goto Lf
            return r0
        Lf:
            java.io.InputStream r1 = r10.in
            if (r1 == 0) goto Laa
            java.io.IOException r1 = r10.exception
            if (r1 != 0) goto La9
            r1 = 0
        L18:
            int r2 = r10.filtered     // Catch: java.io.IOException -> L44
            int r2 = java.lang.Math.min(r2, r13)     // Catch: java.io.IOException -> L44
            byte[] r3 = r10.tmpbuf     // Catch: java.io.IOException -> L44
            int r4 = r10.pos     // Catch: java.io.IOException -> L44
            java.lang.System.arraycopy(r3, r4, r11, r12, r2)     // Catch: java.io.IOException -> L44
            int r3 = r10.pos     // Catch: java.io.IOException -> L44
            int r3 = r3 + r2
            r10.pos = r3     // Catch: java.io.IOException -> L44
            int r4 = r10.filtered     // Catch: java.io.IOException -> L44
            int r4 = r4 - r2
            r10.filtered = r4     // Catch: java.io.IOException -> L44
            int r12 = r12 + r2
            int r13 = r13 - r2
            int r1 = r1 + r2
            int r2 = r3 + r4
            int r5 = r10.unfiltered     // Catch: java.io.IOException -> L44
            int r2 = r2 + r5
            r6 = 4096(0x1000, float:5.74E-42)
            if (r2 != r6) goto L46
            byte[] r2 = r10.tmpbuf     // Catch: java.io.IOException -> L44
            int r4 = r4 + r5
            java.lang.System.arraycopy(r2, r3, r2, r0, r4)     // Catch: java.io.IOException -> L44
            r10.pos = r0     // Catch: java.io.IOException -> L44
            goto L46
        L44:
            r11 = move-exception
            goto La6
        L46:
            r2 = -1
            if (r13 == 0) goto La2
            boolean r3 = r10.endReached     // Catch: java.io.IOException -> L44
            if (r3 == 0) goto L4e
            goto La2
        L4e:
            boolean r3 = org.tukaani.xz.SimpleInputStream.$assertionsDisabled     // Catch: java.io.IOException -> L44
            if (r3 != 0) goto L5d
            int r4 = r10.filtered     // Catch: java.io.IOException -> L44
            if (r4 != 0) goto L57
            goto L5d
        L57:
            java.lang.AssertionError r11 = new java.lang.AssertionError     // Catch: java.io.IOException -> L44
            r11.<init>()     // Catch: java.io.IOException -> L44
            throw r11     // Catch: java.io.IOException -> L44
        L5d:
            int r4 = r10.pos     // Catch: java.io.IOException -> L44
            int r5 = r10.filtered     // Catch: java.io.IOException -> L44
            int r7 = r4 + r5
            int r8 = r10.unfiltered     // Catch: java.io.IOException -> L44
            int r7 = r7 + r8
            int r6 = r6 - r7
            java.io.InputStream r7 = r10.in     // Catch: java.io.IOException -> L44
            byte[] r9 = r10.tmpbuf     // Catch: java.io.IOException -> L44
            int r4 = r4 + r5
            int r4 = r4 + r8
            int r4 = r7.read(r9, r4, r6)     // Catch: java.io.IOException -> L44
            if (r4 != r2) goto L7d
            r2 = 1
            r10.endReached = r2     // Catch: java.io.IOException -> L44
            int r2 = r10.unfiltered     // Catch: java.io.IOException -> L44
            r10.filtered = r2     // Catch: java.io.IOException -> L44
            r10.unfiltered = r0     // Catch: java.io.IOException -> L44
            goto L18
        L7d:
            int r2 = r10.unfiltered     // Catch: java.io.IOException -> L44
            int r2 = r2 + r4
            r10.unfiltered = r2     // Catch: java.io.IOException -> L44
            org.tukaani.xz.simple.SimpleFilter r4 = r10.simpleFilter     // Catch: java.io.IOException -> L44
            byte[] r5 = r10.tmpbuf     // Catch: java.io.IOException -> L44
            int r6 = r10.pos     // Catch: java.io.IOException -> L44
            int r2 = r4.code(r5, r6, r2)     // Catch: java.io.IOException -> L44
            r10.filtered = r2     // Catch: java.io.IOException -> L44
            if (r3 != 0) goto L9b
            int r3 = r10.unfiltered     // Catch: java.io.IOException -> L44
            if (r2 > r3) goto L95
            goto L9b
        L95:
            java.lang.AssertionError r11 = new java.lang.AssertionError     // Catch: java.io.IOException -> L44
            r11.<init>()     // Catch: java.io.IOException -> L44
            throw r11     // Catch: java.io.IOException -> L44
        L9b:
            int r3 = r10.unfiltered     // Catch: java.io.IOException -> L44
            int r3 = r3 - r2
            r10.unfiltered = r3     // Catch: java.io.IOException -> L44
            goto L18
        La2:
            if (r1 <= 0) goto La5
            return r1
        La5:
            return r2
        La6:
            r10.exception = r11
            throw r11
        La9:
            throw r1
        Laa:
            org.tukaani.xz.XZIOException r11 = new org.tukaani.xz.XZIOException
            java.lang.String r12 = "Stream closed"
            r11.<init>(r12)
            throw r11
        Lb2:
            java.lang.IndexOutOfBoundsException r11 = new java.lang.IndexOutOfBoundsException
            r11.<init>()
            throw r11
        */
        throw new UnsupportedOperationException("Method not decompiled: org.tukaani.xz.SimpleInputStream.read(byte[], int, int):int");
    }
}
