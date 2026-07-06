package com.amazon.ion.impl;

import android.support.v4.media.MediaBrowserCompat$CustomActionResultReceiver$$ExternalSyntheticOutline0;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public abstract class UnifiedDataPageX {
    public int _base_offset;
    public byte[] _bytes;
    public char[] _characters;
    public long _file_offset;
    public int _page_limit;
    public PageType _page_type;
    public int _unread_count;

    /* JADX INFO: renamed from: com.amazon.ion.impl.UnifiedDataPageX$1, reason: invalid class name */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static /* synthetic */ class AnonymousClass1 {
        public static final /* synthetic */ int[] $SwitchMap$com$amazon$ion$impl$UnifiedDataPageX$PageType;

        static {
            int[] iArr = new int[PageType.values().length];
            $SwitchMap$com$amazon$ion$impl$UnifiedDataPageX$PageType = iArr;
            try {
                iArr[PageType.CHARS.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$ion$impl$UnifiedDataPageX$PageType[PageType.BYTES.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class Chars extends UnifiedDataPageX {
        public Chars(char[] cArr, int i, int i2) {
            this._page_type = PageType.CHARS;
            this._characters = cArr;
            this._base_offset = i;
            this._page_limit = i + i2;
        }

        @Override // com.amazon.ion.impl.UnifiedDataPageX
        public int getBufferLimit() {
            return this._page_limit;
        }

        @Override // com.amazon.ion.impl.UnifiedDataPageX
        public int getOriginalStartingOffset() {
            return this._base_offset;
        }

        @Override // com.amazon.ion.impl.UnifiedDataPageX
        public /* bridge */ /* synthetic */ int getStartingOffset() {
            return super.getStartingOffset();
        }

        @Override // com.amazon.ion.impl.UnifiedDataPageX
        public int getUnreadCount() {
            return this._unread_count;
        }

        @Override // com.amazon.ion.impl.UnifiedDataPageX
        public int getValue(int i) {
            if (i >= 0 && i <= this._page_limit - this._base_offset) {
                return this._characters[i];
            }
            StringBuilder sbM = MediaBrowserCompat$CustomActionResultReceiver$$ExternalSyntheticOutline0.m("offset ", i, " is not contained in page, limit is ");
            sbM.append(this._page_limit - this._base_offset);
            throw new IllegalArgumentException(sbM.toString());
        }

        @Override // com.amazon.ion.impl.UnifiedDataPageX
        public /* bridge */ /* synthetic */ void inc_unread_count() {
            super.inc_unread_count();
        }

        @Override // com.amazon.ion.impl.UnifiedDataPageX
        public void putValue(int i, int i2) {
            this._characters[i] = (char) i2;
        }

        @Override // com.amazon.ion.impl.UnifiedDataPageX
        public final int readFrom(int i, byte[] bArr, int i2, int i3) {
            throw new UnsupportedOperationException("character pages can't read bytes");
        }

        @Override // com.amazon.ion.impl.UnifiedDataPageX
        public final int readFrom(int i, char[] cArr, int i2, int i3) {
            int i4 = this._page_limit;
            if (i >= i4) {
                return -1;
            }
            if (i3 > i4 - i) {
                i3 = i4 - i;
            }
            System.arraycopy(this._characters, i, cArr, i2, i3);
            return i3;
        }

        public Chars(int i) {
            this(new char[i], 0, i);
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public enum PageType {
        BYTES,
        CHARS
    }

    public UnifiedDataPageX() {
    }

    public static final UnifiedDataPageX makePage(byte[] bArr, int i, int i2) {
        return new Bytes(bArr, i, i2);
    }

    public final boolean containsOffset(long j) {
        long j2 = this._file_offset;
        return ((long) this._base_offset) + j2 <= j && j < j2 + ((long) this._page_limit);
    }

    public int getBufferLimit() {
        return this._page_limit;
    }

    public final byte[] getByteBuffer() {
        return this._bytes;
    }

    public final char[] getCharBuffer() {
        return this._characters;
    }

    public final long getFilePosition(int i) {
        return this._file_offset + ((long) i);
    }

    public final int getLengthFollowingFilePosition(long j) {
        return this._page_limit - getOffsetOfFilePosition(j);
    }

    public final int getOffsetOfFilePosition(long j) {
        if (containsOffset(j)) {
            return (int) (j - this._file_offset);
        }
        throw new IllegalArgumentException("requested file position [" + Long.toString(j) + "] is not in this page [" + Long.toString(getStartingFileOffset()) + "-" + Long.toString(this._file_offset + ((long) this._page_limit)) + "]");
    }

    public int getOriginalStartingOffset() {
        return this._base_offset;
    }

    public final PageType getPageType() {
        return this._page_type;
    }

    public final long getStartingFileOffset() {
        return this._file_offset + ((long) this._base_offset);
    }

    public int getStartingOffset() {
        return this._base_offset - this._unread_count;
    }

    public int getUnreadCount() {
        return this._unread_count;
    }

    public abstract int getValue(int i);

    public void inc_unread_count() {
        this._unread_count++;
    }

    public final boolean isBytes() {
        return this._page_type == PageType.BYTES;
    }

    public int load(Reader reader, int i, long j) throws IOException {
        if (isBytes()) {
            throw new UnsupportedOperationException("byte pages can't load characters");
        }
        char[] cArr = this._characters;
        int i2 = reader.read(cArr, i, cArr.length - i);
        if (i2 > 0) {
            this._page_limit = i + i2;
            this._base_offset = i;
            this._unread_count = 0;
            setFilePosition(j, i);
        }
        return i2;
    }

    public abstract void putValue(int i, int i2);

    public abstract int readFrom(int i, byte[] bArr, int i2, int i3);

    public abstract int readFrom(int i, char[] cArr, int i2, int i3);

    public final void reset(int i) {
        this._base_offset = i;
        this._page_limit = i;
    }

    public final void setFilePosition(long j, int i) {
        if (j < 0) {
            throw new IllegalArgumentException();
        }
        this._file_offset = j - ((long) i);
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class Bytes extends UnifiedDataPageX {
        public Bytes(byte[] bArr, int i, int i2) {
            this._page_type = PageType.BYTES;
            this._bytes = bArr;
            this._base_offset = i;
            this._page_limit = i + i2;
        }

        @Override // com.amazon.ion.impl.UnifiedDataPageX
        public int getBufferLimit() {
            return this._page_limit;
        }

        @Override // com.amazon.ion.impl.UnifiedDataPageX
        public int getOriginalStartingOffset() {
            return this._base_offset;
        }

        @Override // com.amazon.ion.impl.UnifiedDataPageX
        public /* bridge */ /* synthetic */ int getStartingOffset() {
            return super.getStartingOffset();
        }

        @Override // com.amazon.ion.impl.UnifiedDataPageX
        public int getUnreadCount() {
            return this._unread_count;
        }

        @Override // com.amazon.ion.impl.UnifiedDataPageX
        public int getValue(int i) {
            return this._bytes[i] & 255;
        }

        @Override // com.amazon.ion.impl.UnifiedDataPageX
        public /* bridge */ /* synthetic */ void inc_unread_count() {
            super.inc_unread_count();
        }

        @Override // com.amazon.ion.impl.UnifiedDataPageX
        public void putValue(int i, int i2) {
            this._bytes[this._base_offset] = (byte) i2;
        }

        @Override // com.amazon.ion.impl.UnifiedDataPageX
        public final int readFrom(int i, byte[] bArr, int i2, int i3) {
            int i4 = this._page_limit;
            if (i >= i4) {
                return -1;
            }
            if (i3 > i4 - i) {
                i3 = i4 - i;
            }
            System.arraycopy(this._bytes, i, bArr, i2, i3);
            return i3;
        }

        @Override // com.amazon.ion.impl.UnifiedDataPageX
        public final int readFrom(int i, char[] cArr, int i2, int i3) {
            throw new UnsupportedOperationException("byte pages can't read characters");
        }

        public Bytes(int i) {
            this(new byte[i], 0, i);
        }
    }

    public UnifiedDataPageX(AnonymousClass1 anonymousClass1) {
    }

    public static final UnifiedDataPageX makePage(char[] cArr, int i, int i2) {
        return new Chars(cArr, i, i2);
    }

    public static final UnifiedDataPageX makePage(PageType pageType, int i) {
        if (i >= 1) {
            int i2 = AnonymousClass1.$SwitchMap$com$amazon$ion$impl$UnifiedDataPageX$PageType[pageType.ordinal()];
            if (i2 == 1) {
                return new Chars(i);
            }
            if (i2 == 2) {
                return new Bytes(i);
            }
            throw new IllegalArgumentException("invalid page type, s/b 1 or 2");
        }
        throw new IllegalArgumentException("invalid page size must be > 0");
    }

    public int load(InputStream inputStream, int i, long j) throws IOException {
        if (isBytes()) {
            byte[] bArr = this._bytes;
            int i2 = inputStream.read(bArr, i, bArr.length - i);
            if (i2 > 0) {
                this._base_offset = i;
                this._unread_count = 0;
                this._page_limit = i + i2;
                setFilePosition(j, i);
            }
            return i2;
        }
        throw new UnsupportedOperationException("character pages can't load bytes");
    }
}
