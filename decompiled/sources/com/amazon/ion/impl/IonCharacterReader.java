package com.amazon.ion.impl;

import java.io.IOException;
import java.io.PushbackReader;
import java.io.Reader;
import java.util.LinkedList;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public final class IonCharacterReader extends PushbackReader {
    public static final /* synthetic */ boolean $assertionsDisabled = false;
    public static final int BUFFER_PADDING = 1;
    public static final int DEFAULT_BUFFER_SIZE = 12;
    public int m_column;
    public LinkedList<Integer> m_columns;
    public long m_consumed;
    public int m_line;
    public int m_size;

    /* JADX WARN: Illegal instructions before constructor call */
    public IonCharacterReader(Reader reader, int i) {
        int i2 = i + 1;
        super(reader, i2);
        this.m_consumed = 0L;
        this.m_line = 1;
        this.m_column = 0;
        this.m_columns = new LinkedList<>();
        this.m_size = i2;
    }

    public final int getColumn() {
        return this.m_column;
    }

    public final long getConsumedAmount() {
        return this.m_consumed;
    }

    public final int getLineNumber() {
        return this.m_line;
    }

    public final int popColumn() throws IOException {
        if (this.m_columns.isEmpty()) {
            throw new IOException("Cannot unread past buffer");
        }
        return this.m_columns.removeLast().intValue();
    }

    public final void pushColumn(int i) {
        if (this.m_columns.size() == this.m_size) {
            this.m_columns.removeFirst();
        }
        this.m_columns.addLast(Integer.valueOf(i));
    }

    @Override // java.io.PushbackReader, java.io.FilterReader, java.io.Reader
    public int read() throws IOException {
        int i = super.read();
        if (i != -1) {
            if (i == 10) {
                this.m_line++;
                pushColumn(this.m_column);
                this.m_column = 0;
            } else if (i == 13) {
                int i2 = super.read();
                if (i2 != 10) {
                    unreadImpl(i2, false);
                }
                this.m_line++;
                pushColumn(this.m_column);
                this.m_column = 0;
                i = 10;
            } else {
                this.m_column++;
            }
            this.m_consumed++;
        }
        return i;
    }

    @Override // java.io.PushbackReader, java.io.FilterReader, java.io.Reader
    public long skip(long j) throws IOException {
        long j2 = j;
        while (j2 > 0 && read() != -1) {
            j2--;
        }
        return j - j2;
    }

    @Override // java.io.PushbackReader
    public void unread(char[] cArr, int i, int i2) throws IOException {
        for (int i3 = (i2 + i) - 1; i3 >= i; i3--) {
            unread(cArr[i3]);
        }
    }

    public final void unreadImpl(int i, boolean z) throws IOException {
        if (i != -1) {
            if (z) {
                if (i == 10) {
                    this.m_line--;
                    this.m_column = popColumn();
                } else {
                    this.m_column--;
                }
                this.m_consumed--;
            }
            super.unread(i);
        }
    }

    @Override // java.io.PushbackReader
    public void unread(char[] cArr) throws IOException {
        unread(cArr, 0, cArr.length);
    }

    @Override // java.io.PushbackReader
    public void unread(int i) throws IOException {
        if (i != 13) {
            unreadImpl(i, true);
            return;
        }
        throw new IOException("Cannot unread a carriage return");
    }

    public IonCharacterReader(Reader reader) {
        this(reader, 12);
    }

    @Override // java.io.PushbackReader, java.io.FilterReader, java.io.Reader
    public int read(char[] cArr, int i, int i2) throws IOException {
        int i3;
        int i4 = i2 + i;
        int i5 = 0;
        while (i < i4 && (i3 = read()) != -1) {
            cArr[i] = (char) i3;
            i5++;
            i++;
        }
        if (i5 == 0) {
            return -1;
        }
        return i5;
    }
}
