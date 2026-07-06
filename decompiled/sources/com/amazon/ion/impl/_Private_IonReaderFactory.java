package com.amazon.ion.impl;

import com.amazon.ion.IonCatalog;
import com.amazon.ion.IonException;
import com.amazon.ion.IonReader;
import com.amazon.ion.IonSystem;
import com.amazon.ion.IonTextReader;
import com.amazon.ion.IonValue;
import com.amazon.ion.impl.UnifiedInputStreamX;
import com.amazon.ion.system.IonReaderBuilder;
import com.amazon.ion.util.GzipOrRawInputStream;
import com.amazon.ion.util.IonStreamUtils;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.util.zip.GZIPInputStream;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public final class _Private_IonReaderFactory {
    public static final boolean has_binary_cookie(UnifiedInputStreamX unifiedInputStreamX) throws IOException {
        int i;
        byte[] bArr = new byte[_Private_IonConstants.BINARY_VERSION_MARKER_SIZE];
        int i2 = 0;
        while (i2 < _Private_IonConstants.BINARY_VERSION_MARKER_SIZE && (i = unifiedInputStreamX.read()) != -1) {
            bArr[i2] = (byte) i;
            i2++;
        }
        int i3 = i2;
        while (i3 > 0) {
            i3--;
            unifiedInputStreamX.unread(bArr[i3] & 255);
        }
        return IonStreamUtils.cookieMatches(_Private_IonConstants.BINARY_VERSION_MARKER_1_0, bArr, 0, i2);
    }

    public static final IonReader makeIncrementalReader(IonReaderBuilder ionReaderBuilder, InputStream inputStream) {
        return new IonReaderBinaryIncremental(ionReaderBuilder, inputStream);
    }

    public static final IonReader makeReader(IonCatalog ionCatalog, byte[] bArr) {
        return makeReader(ionCatalog, bArr, 0, bArr.length);
    }

    public static IonReader makeSystemReader(byte[] bArr) {
        return makeSystemReader(bArr, 0, bArr.length);
    }

    public static UnifiedInputStreamX makeUnifiedStream(byte[] bArr, int i, int i2) throws IOException {
        return IonStreamUtils.cookieMatches(GzipOrRawInputStream.GZIP_HEADER, bArr, i, i2) ? new UnifiedInputStreamX.FromByteStream(new GZIPInputStream(new ByteArrayInputStream(bArr, i, i2))) : new UnifiedInputStreamX.FromByteArray(bArr, i, i2);
    }

    public static final IonReader makeReader(IonCatalog ionCatalog, byte[] bArr, _Private_LocalSymbolTableFactory _private_localsymboltablefactory) {
        return makeReader(ionCatalog, bArr, 0, bArr.length, _private_localsymboltablefactory);
    }

    public static final IonReader makeSystemReader(CharSequence charSequence, int i, int i2) {
        return new IonReaderTextSystemX(new UnifiedInputStreamX.FromCharArray(charSequence, i, i2));
    }

    public static final IonReader makeReader(IonCatalog ionCatalog, byte[] bArr, int i, int i2) {
        try {
            return makeReader(ionCatalog, makeUnifiedStream(bArr, i, i2), i, LocalSymbolTable.DEFAULT_LST_FACTORY);
        } catch (IOException e) {
            throw new IonException(e.getMessage(), e);
        }
    }

    public static IonReader makeSystemReader(byte[] bArr, int i, int i2) {
        try {
            return makeSystemReader(makeUnifiedStream(bArr, i, i2));
        } catch (IOException e) {
            throw new IonException(e.getMessage(), e);
        }
    }

    public static UnifiedInputStreamX makeUnifiedStream(InputStream inputStream) throws IOException {
        inputStream.getClass();
        return new UnifiedInputStreamX.FromByteStream(new GzipOrRawInputStream(inputStream));
    }

    public static final IonReader makeReader(IonCatalog ionCatalog, byte[] bArr, int i, int i2, _Private_LocalSymbolTableFactory _private_localsymboltablefactory) {
        try {
            return makeReader(ionCatalog, makeUnifiedStream(bArr, i, i2), i, _private_localsymboltablefactory);
        } catch (IOException e) {
            throw new IonException(e.getMessage(), e);
        }
    }

    public static final IonReader makeSystemReader(char[] cArr, int i, int i2) {
        return new IonReaderTextSystemX(new UnifiedInputStreamX.FromCharArray(cArr, i, i2));
    }

    public static final IonReader makeSystemReader(Reader reader) {
        try {
            return new IonReaderTextSystemX(new UnifiedInputStreamX.FromCharStream(reader));
        } catch (IOException e) {
            throw new IonException(e.getMessage(), e);
        }
    }

    public static final IonTextReader makeReader(IonCatalog ionCatalog, CharSequence charSequence, int i, int i2) {
        return new IonReaderTextUserX(ionCatalog, LocalSymbolTable.DEFAULT_LST_FACTORY, new UnifiedInputStreamX.FromCharArray(charSequence, i, i2), i);
    }

    public static final IonTextReader makeReader(IonCatalog ionCatalog, char[] cArr, int i, int i2) {
        return new IonReaderTextUserX(ionCatalog, LocalSymbolTable.DEFAULT_LST_FACTORY, new UnifiedInputStreamX.FromCharArray(cArr, i, i2), i);
    }

    public static final IonReader makeSystemReader(char[] cArr) {
        return new IonReaderTextSystemX(UnifiedInputStreamX.makeStream(cArr));
    }

    public static final IonTextReader makeReader(IonCatalog ionCatalog, Reader reader, _Private_LocalSymbolTableFactory _private_localsymboltablefactory) {
        try {
            return new IonReaderTextUserX(ionCatalog, _private_localsymboltablefactory, new UnifiedInputStreamX.FromCharStream(reader), 0);
        } catch (IOException e) {
            throw new IonException(e.getMessage(), e);
        }
    }

    public static final IonReader makeSystemReader(CharSequence charSequence) {
        return new IonReaderTextSystemX(UnifiedInputStreamX.makeStream(charSequence));
    }

    public static IonReader makeSystemReader(InputStream inputStream) {
        try {
            return makeSystemReader(makeUnifiedStream(inputStream));
        } catch (IOException e) {
            throw new IonException(e.getMessage(), e);
        }
    }

    public static final IonTextReader makeReader(IonCatalog ionCatalog, char[] cArr) {
        return makeReader(ionCatalog, cArr, 0, cArr.length);
    }

    public static final IonTextReader makeReader(IonCatalog ionCatalog, CharSequence charSequence) {
        return makeReader(ionCatalog, charSequence, LocalSymbolTable.DEFAULT_LST_FACTORY);
    }

    public static final IonTextReader makeReader(IonCatalog ionCatalog, CharSequence charSequence, _Private_LocalSymbolTableFactory _private_localsymboltablefactory) {
        return new IonReaderTextUserX(ionCatalog, _private_localsymboltablefactory, UnifiedInputStreamX.makeStream(charSequence), 0);
    }

    public static final IonReader makeSystemReader(IonSystem ionSystem, IonValue ionValue) {
        if (ionSystem != null && ionSystem != ionValue.getSystem()) {
            throw new IonException("you can't mix values from different systems");
        }
        return new IonReaderTreeSystem(ionValue);
    }

    public static final IonReader makeReader(IonCatalog ionCatalog, InputStream inputStream) {
        return makeReader(ionCatalog, inputStream, LocalSymbolTable.DEFAULT_LST_FACTORY);
    }

    public static final IonReader makeReader(IonCatalog ionCatalog, InputStream inputStream, _Private_LocalSymbolTableFactory _private_localsymboltablefactory) {
        try {
            return makeReader(ionCatalog, makeUnifiedStream(inputStream), 0, _private_localsymboltablefactory);
        } catch (IOException e) {
            throw new IonException(e.getMessage(), e);
        }
    }

    public static IonReader makeSystemReader(UnifiedInputStreamX unifiedInputStreamX) throws IOException {
        if (has_binary_cookie(unifiedInputStreamX)) {
            return new IonReaderBinarySystemX(unifiedInputStreamX);
        }
        return new IonReaderTextSystemX(unifiedInputStreamX);
    }

    public static final IonTextReader makeReader(IonCatalog ionCatalog, Reader reader) {
        return makeReader(ionCatalog, reader, LocalSymbolTable.DEFAULT_LST_FACTORY);
    }

    public static final IonReader makeReader(IonCatalog ionCatalog, IonValue ionValue, _Private_LocalSymbolTableFactory _private_localsymboltablefactory) {
        return new IonReaderTreeUserX(ionValue, ionCatalog, _private_localsymboltablefactory);
    }

    public static IonReader makeReader(IonCatalog ionCatalog, UnifiedInputStreamX unifiedInputStreamX, int i, _Private_LocalSymbolTableFactory _private_localsymboltablefactory) throws IOException {
        if (has_binary_cookie(unifiedInputStreamX)) {
            return new IonReaderBinaryUserX(ionCatalog, _private_localsymboltablefactory, unifiedInputStreamX, i);
        }
        return new IonReaderTextUserX(ionCatalog, _private_localsymboltablefactory, unifiedInputStreamX, i);
    }
}
