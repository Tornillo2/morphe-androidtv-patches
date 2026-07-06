package com.google.common.io;

import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.J2ktIncompatible;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.io.IOException;
import java.io.Reader;
import java.nio.CharBuffer;
import java.util.ArrayDeque;
import java.util.Queue;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@J2ktIncompatible
@GwtIncompatible
public final class LineReader {
    public final char[] buf;
    public final CharBuffer cbuf;
    public final LineBuffer lineBuf;
    public final Queue<String> lines;
    public final Readable readable;
    public final Reader reader;

    public LineReader(Readable readable) {
        CharBuffer charBufferAllocate = CharBuffer.allocate(2048);
        this.cbuf = charBufferAllocate;
        this.buf = charBufferAllocate.array();
        this.lines = new ArrayDeque();
        this.lineBuf = new LineBuffer() { // from class: com.google.common.io.LineReader.1
            @Override // com.google.common.io.LineBuffer
            public void handleLine(String line, String end) {
                LineReader.this.lines.add(line);
            }
        };
        readable.getClass();
        this.readable = readable;
        this.reader = readable instanceof Reader ? (Reader) readable : null;
    }

    @CanIgnoreReturnValue
    public String readLine() throws IOException {
        int i;
        while (true) {
            if (this.lines.peek() != null) {
                break;
            }
            this.cbuf.clear();
            Reader reader = this.reader;
            if (reader != null) {
                char[] cArr = this.buf;
                i = reader.read(cArr, 0, cArr.length);
            } else {
                i = this.readable.read(this.cbuf);
            }
            if (i == -1) {
                this.lineBuf.finish();
                break;
            }
            this.lineBuf.add(this.buf, 0, i);
        }
        return this.lines.poll();
    }
}
