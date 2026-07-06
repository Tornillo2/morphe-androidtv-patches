package com.amazon.ion.impl;

import android.support.v4.media.MediaBrowserCompat$CustomActionResultReceiver$$ExternalSyntheticOutline0;
import com.amazon.ion.impl.IonReaderTextRawTokensX;
import com.amazon.ion.impl.UnifiedInputBufferX;
import com.amazon.ion.impl.UnifiedSavePointManagerX;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public abstract class UnifiedInputStreamX implements Closeable {
    public static final /* synthetic */ boolean $assertionsDisabled = false;
    public static int DEFAULT_PAGE_SIZE = 32768;
    public static final int EOF = -1;
    public static final int UNREAD_LIMIT = 10;
    public static final boolean _debug = false;
    public UnifiedInputBufferX _buffer;
    public byte[] _bytes;
    public char[] _chars;
    public boolean _eof;
    public boolean _is_byte_data;
    public boolean _is_stream;
    public int _limit;
    public int _max_char_value;
    public int _pos;
    public Reader _reader;
    public UnifiedSavePointManagerX _save_points;
    public InputStream _stream;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static class FromByteArray extends UnifiedInputStreamX {
        public FromByteArray(byte[] bArr, int i, int i2) {
            this._is_byte_data = true;
            this._is_stream = false;
            UnifiedInputBufferX.Bytes bytes = new UnifiedInputBufferX.Bytes(bArr, i, i2);
            this._buffer = bytes;
            make_page_current(bytes.getCurrentPage(), 0, i, i2 + i);
            init();
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static class FromByteStream extends UnifiedInputStreamX {
        public FromByteStream(InputStream inputStream) throws IOException {
            this._is_byte_data = true;
            this._is_stream = true;
            this._stream = inputStream;
            this._buffer = UnifiedInputBufferX.makePageBuffer(UnifiedInputBufferX.BufferType.BYTES, UnifiedInputStreamX.DEFAULT_PAGE_SIZE);
            init();
            this._limit = refill();
        }

        @Override // com.amazon.ion.impl.UnifiedInputStreamX, java.io.Closeable, java.lang.AutoCloseable
        public void close() throws IOException {
            super.close();
            this._stream.close();
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static class FromCharStream extends UnifiedInputStreamX {
        public FromCharStream(Reader reader) throws IOException {
            this._is_byte_data = false;
            this._is_stream = true;
            this._reader = reader;
            this._buffer = UnifiedInputBufferX.makePageBuffer(UnifiedInputBufferX.BufferType.CHARS, UnifiedInputStreamX.DEFAULT_PAGE_SIZE);
            init();
            this._limit = refill();
        }

        @Override // com.amazon.ion.impl.UnifiedInputStreamX, java.io.Closeable, java.lang.AutoCloseable
        public void close() throws IOException {
            super.close();
            this._reader.close();
        }
    }

    public static UnifiedInputStreamX makeStream(CharSequence charSequence) {
        return new FromCharArray(charSequence, 0, charSequence.length());
    }

    public final boolean can_fill_new_page() {
        return this._is_stream;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this._eof = true;
        this._buffer.clear();
    }

    public final byte[] getByteArray() {
        return this._bytes;
    }

    public final char[] getCharArray() {
        return this._chars;
    }

    public final InputStream getInputStream() {
        return this._stream;
    }

    public long getPosition() {
        UnifiedDataPageX currentPage = this._buffer.getCurrentPage();
        if (currentPage == null) {
            return 0L;
        }
        return currentPage._file_offset + ((long) this._pos);
    }

    public final Reader getReader() {
        return this._reader;
    }

    public final void init() {
        this._eof = false;
        this._max_char_value = this._buffer.maxValue();
        this._save_points = new UnifiedSavePointManagerX(this);
    }

    public final boolean isEOF() {
        return this._eof;
    }

    public final boolean is_byte_data() {
        return this._is_byte_data;
    }

    public final int load(UnifiedDataPageX unifiedDataPageX, int i, long j) throws IOException {
        if (this._is_stream) {
            return this._is_byte_data ? unifiedDataPageX.load(this._stream, i, j) : unifiedDataPageX.load(this._reader, i, j);
        }
        return 0;
    }

    public final void make_page_current(UnifiedDataPageX unifiedDataPageX, int i, int i2, int i3) {
        this._limit = i3;
        this._pos = i2;
        this._eof = false;
        if (this._is_byte_data) {
            this._bytes = unifiedDataPageX._bytes;
        } else {
            this._chars = unifiedDataPageX._characters;
        }
        this._buffer.setCurrentPage(i, unifiedDataPageX);
        if (i2 > i3) {
            refill_is_eof();
        }
    }

    public final int read(byte[] bArr, int i, int i2) throws IOException {
        if (!this._is_byte_data) {
            throw new IOException("byte read is not support over character sources");
        }
        int i3 = i2;
        while (i3 > 0 && !this._eof) {
            int i4 = this._limit;
            int i5 = this._pos;
            int i6 = i4 - i5;
            if (i6 > i3) {
                i6 = i3;
            }
            System.arraycopy(this._bytes, i5, bArr, i, i6);
            int i7 = this._pos + i6;
            this._pos = i7;
            i += i6;
            i3 -= i6;
            if (i3 == 0 || i7 < this._limit || refill_helper()) {
                break;
            }
        }
        return i2 - i3;
    }

    public final int read_helper() throws IOException {
        if (this._eof || refill_helper()) {
            return -1;
        }
        if (this._is_byte_data) {
            byte[] bArr = this._bytes;
            int i = this._pos;
            this._pos = i + 1;
            return bArr[i] & 255;
        }
        char[] cArr = this._chars;
        int i2 = this._pos;
        this._pos = i2 + 1;
        return cArr[i2];
    }

    public final int read_utf8(int i) throws IOException {
        int uTF8LengthFromFirstByte = IonUTF8.getUTF8LengthFromFirstByte(i);
        for (int i2 = 1; i2 < uTF8LengthFromFirstByte; i2++) {
            int i3 = read();
            if (i3 == -1) {
                throw new IonReaderTextRawTokensX.IonReaderTextTokenException("invalid UTF8 sequence encountered in stream");
            }
            i |= i3 << (i2 * 8);
        }
        return IonUTF8.getScalarFrom4BytesReversed(i);
    }

    public int refill() throws IOException {
        int i;
        int i2;
        UnifiedDataPageX currentPage = this._buffer.getCurrentPage();
        UnifiedSavePointManagerX.SavePoint savePoint = this._save_points._active_stack;
        if (!this._is_stream) {
            refill_is_eof();
            return -1;
        }
        if (savePoint != null && savePoint._end_idx == this._buffer._buffer_current) {
            refill_is_eof();
            return -1;
        }
        long j = 0;
        if (currentPage == null) {
            i = 0;
        } else {
            long j2 = currentPage._file_offset + ((long) this._pos);
            if (j2 == 0) {
                j = j2;
                i = 0;
            } else {
                i = 10;
                j = j2;
            }
        }
        int nextFilledPageIdx = this._buffer.getNextFilledPageIdx();
        if (nextFilledPageIdx < 0) {
            UnifiedDataPageX currentPage2 = this._buffer.getCurrentPage();
            boolean z = currentPage2 == null;
            int i3 = this._buffer._buffer_current;
            if (this._save_points.isSavePointOpen()) {
                i3++;
                z = true;
            }
            if (z) {
                currentPage2 = this._buffer.getEmptyPageIdx();
            }
            if (load(currentPage2, i, j) < 1) {
                refill_is_eof();
                return -1;
            }
            set_current_page(i3, currentPage2, i);
        } else {
            if (savePoint != null && (i2 = savePoint._end_idx) != -1 && i2 < nextFilledPageIdx) {
                refill_is_eof();
                return -1;
            }
            UnifiedDataPageX page = this._buffer.getPage(nextFilledPageIdx);
            set_current_page(nextFilledPageIdx, page, page.getStartingOffset());
            if (savePoint != null && savePoint._end_idx == nextFilledPageIdx) {
                this._limit = savePoint._end_pos;
            }
        }
        return this._limit;
    }

    public final boolean refill_helper() throws IOException {
        int iRefill = refill();
        this._limit = iRefill;
        if (this._pos < iRefill) {
            return false;
        }
        this._eof = true;
        return true;
    }

    public final int refill_is_eof() {
        this._eof = true;
        this._limit = -1;
        return -1;
    }

    public final UnifiedSavePointManagerX.SavePoint savePointAllocate() {
        return this._save_points.savePointAllocate();
    }

    public final void save_point_reset_to_prev(UnifiedSavePointManagerX.SavePoint savePoint) {
        int i = savePoint._prev_idx;
        make_page_current(this._buffer.getPage(i), i, savePoint._prev_pos, savePoint._prev_limit);
    }

    public final void set_current_page(int i, UnifiedDataPageX unifiedDataPageX, int i2) {
        UnifiedInputBufferX unifiedInputBufferX = this._buffer;
        if (unifiedDataPageX != (i < unifiedInputBufferX._buffer_count ? unifiedInputBufferX.getPage(i) : null)) {
            this._buffer.setPage(i, unifiedDataPageX, true);
        }
        make_page_current(unifiedDataPageX, i, i2, unifiedDataPageX.getBufferLimit());
    }

    public final void skip(int i) throws IOException {
        int i2;
        int i3 = this._limit;
        int i4 = this._pos;
        if (i3 - i4 < i) {
            i2 = i;
            while (i2 > 0) {
                int i5 = this._limit;
                int i6 = this._pos;
                int i7 = i5 - i6;
                if (i7 > i2) {
                    i7 = i2;
                }
                this._pos = i6 + i7;
                i2 -= i7;
                if (i2 > 0 && refill_helper()) {
                    break;
                }
            }
        } else {
            this._pos = i4 + i;
            i2 = 0;
        }
        if (i2 <= 0) {
            return;
        }
        StringBuilder sbM = MediaBrowserCompat$CustomActionResultReceiver$$ExternalSyntheticOutline0.m("unexpected EOF encountered during skip of ", i, " at position ");
        sbM.append(getPosition());
        throw new IOException(sbM.toString());
    }

    public final void unread(int i) {
        if (i == -1) {
            return;
        }
        if (i < 0 || i > this._max_char_value) {
            throw new IllegalArgumentException();
        }
        if (this._eof) {
            this._eof = false;
            if (this._limit == -1) {
                this._limit = this._pos;
            }
        }
        int i2 = this._pos - 1;
        this._pos = i2;
        if (i2 < 0) {
            this._buffer.putCharAt(getPosition(), i);
            return;
        }
        UnifiedDataPageX currentPage = this._buffer.getCurrentPage();
        if (this._pos < currentPage.getStartingOffset()) {
            currentPage.inc_unread_count();
            if (this._is_byte_data) {
                this._bytes[this._pos] = (byte) i;
            } else {
                this._chars[this._pos] = (char) i;
            }
        }
    }

    public final boolean unread_optional_cr() {
        if (this._pos > this._buffer.getCurrentPage().getStartingOffset()) {
            if ((this._is_byte_data ? this._bytes[this._pos - 1] & 255 : this._chars[this._pos - 1]) == 13) {
                this._pos--;
            }
        }
        return false;
    }

    public static UnifiedInputStreamX makeStream(CharSequence charSequence, int i, int i2) {
        return new FromCharArray(charSequence, i, i2);
    }

    public static UnifiedInputStreamX makeStream(char[] cArr) {
        return new FromCharArray(cArr, 0, cArr.length);
    }

    public static UnifiedInputStreamX makeStream(char[] cArr, int i, int i2) {
        return new FromCharArray(cArr, i, i2);
    }

    public static UnifiedInputStreamX makeStream(Reader reader) throws IOException {
        return new FromCharStream(reader);
    }

    public static UnifiedInputStreamX makeStream(byte[] bArr) {
        return new FromByteArray(bArr, 0, bArr.length);
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static class FromCharArray extends UnifiedInputStreamX {
        public FromCharArray(CharSequence charSequence, int i, int i2) {
            this._is_byte_data = false;
            this._is_stream = false;
            UnifiedInputBufferX unifiedInputBufferXMakePageBuffer = UnifiedInputBufferX.makePageBuffer(charSequence, i, i2);
            this._buffer = unifiedInputBufferXMakePageBuffer;
            make_page_current(unifiedInputBufferXMakePageBuffer.getCurrentPage(), 0, i, i2 + i);
            init();
        }

        public FromCharArray(char[] cArr, int i, int i2) {
            this._is_byte_data = false;
            this._is_stream = false;
            UnifiedInputBufferX.Chars chars = new UnifiedInputBufferX.Chars(cArr, i, i2);
            this._buffer = chars;
            make_page_current(chars.getCurrentPage(), 0, i, i2 + i);
            init();
        }
    }

    public static UnifiedInputStreamX makeStream(byte[] bArr, int i, int i2) {
        return new FromByteArray(bArr, i, i2);
    }

    public static UnifiedInputStreamX makeStream(InputStream inputStream) throws IOException {
        return new FromByteStream(inputStream);
    }

    public final int read() throws IOException {
        int i = this._pos;
        if (i >= this._limit) {
            return read_helper();
        }
        if (this._is_byte_data) {
            byte[] bArr = this._bytes;
            this._pos = i + 1;
            return bArr[i] & 255;
        }
        char[] cArr = this._chars;
        this._pos = i + 1;
        return cArr[i];
    }

    public final void verify_matched_unread(int i) {
    }
}
