package com.amazon.ion.impl;

import android.support.v4.media.MediaBrowserCompat$CustomActionResultReceiver$$ExternalSyntheticOutline0;
import com.amazon.ion.impl.UnifiedDataPageX;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public abstract class UnifiedInputBufferX {
    public static final /* synthetic */ boolean $assertionsDisabled = false;
    public int _buffer_count;
    public int _buffer_current;
    public UnifiedDataPageX[] _buffers;
    public int _locks;
    public int _page_size;

    /* JADX INFO: renamed from: com.amazon.ion.impl.UnifiedInputBufferX$1, reason: invalid class name */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static /* synthetic */ class AnonymousClass1 {
        public static final /* synthetic */ int[] $SwitchMap$com$amazon$ion$impl$UnifiedInputBufferX$BufferType;

        static {
            int[] iArr = new int[BufferType.values().length];
            $SwitchMap$com$amazon$ion$impl$UnifiedInputBufferX$BufferType = iArr;
            try {
                iArr[BufferType.CHARS.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$ion$impl$UnifiedInputBufferX$BufferType[BufferType.BYTES.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public enum BufferType {
        BYTES,
        CHARS
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static class Bytes extends UnifiedInputBufferX {
        public Bytes(int i) {
            super(i);
        }

        @Override // com.amazon.ion.impl.UnifiedInputBufferX
        public final BufferType getType() {
            return BufferType.BYTES;
        }

        @Override // com.amazon.ion.impl.UnifiedInputBufferX
        public final UnifiedDataPageX make_page(int i) {
            return new UnifiedDataPageX.Bytes(i);
        }

        @Override // com.amazon.ion.impl.UnifiedInputBufferX
        public final int maxValue() {
            return 255;
        }

        public Bytes(byte[] bArr, int i, int i2) {
            super(i2);
            this._buffers[0] = new UnifiedDataPageX.Bytes(bArr, i, i2);
            this._buffer_current = 0;
            this._buffer_count = 1;
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static class Chars extends UnifiedInputBufferX {
        public Chars(int i) {
            super(i);
        }

        @Override // com.amazon.ion.impl.UnifiedInputBufferX
        public final BufferType getType() {
            return BufferType.CHARS;
        }

        @Override // com.amazon.ion.impl.UnifiedInputBufferX
        public final UnifiedDataPageX make_page(int i) {
            return new UnifiedDataPageX.Chars(i);
        }

        @Override // com.amazon.ion.impl.UnifiedInputBufferX
        public final int maxValue() {
            return 65535;
        }

        public Chars(char[] cArr, int i, int i2) {
            super(i + i2);
            this._buffers[0] = new UnifiedDataPageX.Chars(cArr, i, i2);
            this._buffer_current = 0;
            this._buffer_count = 1;
        }

        public Chars(CharSequence charSequence, int i, int i2) {
            this(UnifiedInputBufferX.chars_make_char_array(charSequence, i, i2), 0, i2);
        }
    }

    public /* synthetic */ UnifiedInputBufferX(int i, AnonymousClass1 anonymousClass1) {
        this(i);
    }

    public static final char[] chars_make_char_array(CharSequence charSequence, int i, int i2) {
        char[] cArr = new char[i2];
        while (i < i2) {
            cArr[i] = charSequence.charAt(i);
            i++;
        }
        return cArr;
    }

    public static UnifiedInputBufferX makePageBuffer(byte[] bArr, int i, int i2) {
        return new Bytes(bArr, i, i2);
    }

    public final void clear() {
        UnifiedDataPageX[] unifiedDataPageXArr;
        UnifiedDataPageX currentPage = getCurrentPage();
        int i = 0;
        while (true) {
            unifiedDataPageXArr = this._buffers;
            if (i >= unifiedDataPageXArr.length) {
                break;
            }
            unifiedDataPageXArr[i] = null;
            i++;
        }
        if (currentPage != null) {
            unifiedDataPageXArr[0] = currentPage;
            currentPage._base_offset = 0;
            currentPage._page_limit = 0;
        }
        this._buffer_count = 0;
        this._buffer_current = 0;
    }

    public final boolean decLock() {
        int i = this._locks - 1;
        this._locks = i;
        return i == 0;
    }

    public final UnifiedDataPageX getCurrentPage() {
        return this._buffers[this._buffer_current];
    }

    public final int getCurrentPageIdx() {
        return this._buffer_current;
    }

    public final UnifiedDataPageX getEmptyPageIdx() {
        int i = this._buffer_count;
        UnifiedDataPageX[] unifiedDataPageXArr = this._buffers;
        UnifiedDataPageX unifiedDataPageX = i < unifiedDataPageXArr.length ? unifiedDataPageXArr[i] : null;
        return unifiedDataPageX == null ? make_page(this._page_size) : unifiedDataPageX;
    }

    public final int getNextFilledPageIdx() {
        int i = this._buffer_current + 1;
        if (i >= this._buffer_count || this._buffers[i] == null) {
            return -1;
        }
        this._buffer_current = i;
        return i;
    }

    public final UnifiedDataPageX getPage(int i) {
        if (i < 0 || i >= this._buffer_count) {
            throw new IndexOutOfBoundsException();
        }
        return this._buffers[i];
    }

    public final int getPageCount() {
        return this._buffer_count;
    }

    public abstract BufferType getType();

    public final void incLock() {
        this._locks++;
    }

    public abstract UnifiedDataPageX make_page(int i);

    public abstract int maxValue();

    public final void putCharAt(long j, int i) {
        UnifiedDataPageX unifiedDataPageX;
        if (i < 0 || i > maxValue()) {
            StringBuilder sbM = MediaBrowserCompat$CustomActionResultReceiver$$ExternalSyntheticOutline0.m("value (", i, ")is out of range (0 to ");
            sbM.append(maxValue());
            sbM.append(")");
            throw new IllegalArgumentException(sbM.toString());
        }
        int i2 = this._buffer_current;
        while (true) {
            if (i2 < 0) {
                unifiedDataPageX = null;
                break;
            } else {
                if (this._buffers[i2].containsOffset(j)) {
                    unifiedDataPageX = this._buffers[i2];
                    break;
                }
                i2--;
            }
        }
        if (unifiedDataPageX == null) {
            throw new IllegalArgumentException();
        }
        unifiedDataPageX.putValue((int) (j - unifiedDataPageX.getStartingFileOffset()), i);
    }

    public final void release_pages_to(int i) {
        int i2;
        int i3 = 0;
        UnifiedDataPageX unifiedDataPageX = this._buffers[0];
        int i4 = i;
        while (true) {
            i2 = this._buffer_count;
            if (i4 >= i2) {
                break;
            }
            UnifiedDataPageX[] unifiedDataPageXArr = this._buffers;
            unifiedDataPageXArr[i3] = unifiedDataPageXArr[i4];
            i3++;
            i4++;
        }
        int length = i2 + 1;
        UnifiedDataPageX[] unifiedDataPageXArr2 = this._buffers;
        if (length >= unifiedDataPageXArr2.length) {
            length = unifiedDataPageXArr2.length;
        }
        while (i3 < length) {
            this._buffers[i3] = null;
            i3++;
        }
        this._buffer_current -= i;
        int i5 = this._buffer_count - i;
        this._buffer_count = i5;
        this._buffers[i5] = unifiedDataPageX;
    }

    public final void resetToCurrentPage() {
        int i = this._buffer_current;
        if (i > 0) {
            release_pages_to(i);
        }
    }

    public final UnifiedDataPageX setCurrentPage(int i, UnifiedDataPageX unifiedDataPageX) {
        setPage(i, unifiedDataPageX, true);
        if (i != this._buffer_current) {
            this._buffer_current = i;
            if (i >= this._buffer_count) {
                this._buffer_count = i + 1;
            }
        }
        return this._buffers[i];
    }

    public final void setPage(int i, UnifiedDataPageX unifiedDataPageX, boolean z) {
        int i2;
        UnifiedDataPageX[] unifiedDataPageXArr = this._buffers;
        int length = unifiedDataPageXArr.length;
        if (i >= length) {
            UnifiedDataPageX[] unifiedDataPageXArr2 = new UnifiedDataPageX[length * 2];
            System.arraycopy(unifiedDataPageXArr, 0, unifiedDataPageXArr2, 0, length);
            this._buffers = unifiedDataPageXArr2;
        }
        UnifiedDataPageX[] unifiedDataPageXArr3 = this._buffers;
        UnifiedDataPageX unifiedDataPageX2 = unifiedDataPageXArr3[i];
        unifiedDataPageXArr3[i] = unifiedDataPageX;
        if (i >= this._buffer_count) {
            this._buffer_count = i + 1;
        }
        if (!z || unifiedDataPageX2 == null || unifiedDataPageX2 == unifiedDataPageX || (i2 = i + 1) >= unifiedDataPageXArr3.length) {
            return;
        }
        unifiedDataPageXArr3[i2] = unifiedDataPageX2;
    }

    public UnifiedInputBufferX(int i) {
        if (i < 0) {
            throw new IllegalArgumentException("page size must be > 0");
        }
        this._page_size = i;
        this._buffers = new UnifiedDataPageX[10];
    }

    public static UnifiedInputBufferX makePageBuffer(char[] cArr, int i, int i2) {
        return new Chars(cArr, i, i2);
    }

    public static UnifiedInputBufferX makePageBuffer(CharSequence charSequence, int i, int i2) {
        return new Chars(chars_make_char_array(charSequence, i, i2), 0, i2);
    }

    public static UnifiedInputBufferX makePageBuffer(BufferType bufferType, int i) {
        int i2 = AnonymousClass1.$SwitchMap$com$amazon$ion$impl$UnifiedInputBufferX$BufferType[bufferType.ordinal()];
        if (i2 == 1) {
            return new Chars(i);
        }
        if (i2 == 2) {
            return new Bytes(i);
        }
        throw new IllegalArgumentException("invalid buffer type");
    }
}
