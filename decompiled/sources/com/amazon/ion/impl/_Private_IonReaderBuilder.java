package com.amazon.ion.impl;

import com.amazon.ion.IonException;
import com.amazon.ion.IonReader;
import com.amazon.ion.IonTextReader;
import com.amazon.ion.IonValue;
import com.amazon.ion.system.IonReaderBuilder;
import com.amazon.ion.util.GzipOrRawInputStream;
import com.amazon.ion.util.IonStreamUtils;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public class _Private_IonReaderBuilder extends IonReaderBuilder {
    public _Private_LocalSymbolTableFactory lstFactory;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static class Mutable extends _Private_IonReaderBuilder {
        public Mutable() {
        }

        @Override // com.amazon.ion.system.IonReaderBuilder
        public IonReaderBuilder immutable() {
            return new _Private_IonReaderBuilder(this);
        }

        public Mutable(IonReaderBuilder ionReaderBuilder) {
            super((_Private_IonReaderBuilder) ionReaderBuilder);
        }

        @Override // com.amazon.ion.system.IonReaderBuilder
        public IonReaderBuilder mutable() {
            return this;
        }

        @Override // com.amazon.ion.system.IonReaderBuilder
        public void mutationCheck() {
        }
    }

    public static boolean startsWithIvm(byte[] bArr, int i) {
        for (int i2 = 0; i2 < i; i2++) {
            if (_Private_IonConstants.BINARY_VERSION_MARKER_1_0[i2] != bArr[i2]) {
                return false;
            }
        }
        return true;
    }

    @Override // com.amazon.ion.system.IonReaderBuilder
    public IonReader build(byte[] bArr, int i, int i2) {
        if (isIncrementalReadingEnabled()) {
            if (IonStreamUtils.cookieMatches(GzipOrRawInputStream.GZIP_HEADER, bArr, i, i2)) {
                throw new IllegalArgumentException("Automatic GZIP detection is not supported with incrementalsupport enabled. Wrap the bytes with a GZIPInputStream and call build(InputStream).");
            }
            if (IonStreamUtils.cookieMatches(_Private_IonConstants.BINARY_VERSION_MARKER_1_0, bArr, i, i2)) {
                return new IonReaderBinaryIncremental(this, new ByteArrayInputStream(bArr, i, i2));
            }
        }
        return _Private_IonReaderFactory.makeReader(validateCatalog(), bArr, i, i2, this.lstFactory);
    }

    public void setLstFactory(_Private_LocalSymbolTableFactory _private_localsymboltablefactory) {
        mutationCheck();
        if (_private_localsymboltablefactory == null) {
            this.lstFactory = LocalSymbolTable.DEFAULT_LST_FACTORY;
        } else {
            this.lstFactory = _private_localsymboltablefactory;
        }
    }

    public IonReaderBuilder withLstFactory(_Private_LocalSymbolTableFactory _private_localsymboltablefactory) {
        _Private_IonReaderBuilder _private_ionreaderbuilder = (_Private_IonReaderBuilder) mutable();
        _private_ionreaderbuilder.setLstFactory(_private_localsymboltablefactory);
        return _private_ionreaderbuilder;
    }

    public _Private_IonReaderBuilder() {
        this.lstFactory = LocalSymbolTable.DEFAULT_LST_FACTORY;
    }

    public _Private_IonReaderBuilder(_Private_IonReaderBuilder _private_ionreaderbuilder) {
        super(_private_ionreaderbuilder);
        this.lstFactory = _private_ionreaderbuilder.lstFactory;
    }

    @Override // com.amazon.ion.system.IonReaderBuilder
    public IonReader build(InputStream inputStream) {
        if (isIncrementalReadingEnabled()) {
            if (!inputStream.markSupported()) {
                inputStream = new BufferedInputStream(inputStream);
            }
            int i = _Private_IonConstants.BINARY_VERSION_MARKER_SIZE;
            inputStream.mark(i);
            byte[] bArr = new byte[i];
            try {
                int i2 = inputStream.read(bArr);
                inputStream.reset();
                if (!IonStreamUtils.cookieMatches(GzipOrRawInputStream.GZIP_HEADER, bArr, 0, i)) {
                    if (startsWithIvm(bArr, i2)) {
                        return new IonReaderBinaryIncremental(this, inputStream);
                    }
                } else {
                    throw new IllegalArgumentException("Automatic GZIP detection is not supported with incrementalsupport enabled. Wrap the bytes with a GZIPInputStream and call build(InputStream).");
                }
            } catch (IOException e) {
                throw new IonException(e.getMessage(), e);
            }
        }
        return _Private_IonReaderFactory.makeReader(validateCatalog(), inputStream, this.lstFactory);
    }

    @Override // com.amazon.ion.system.IonReaderBuilder
    public IonReader build(Reader reader) {
        return _Private_IonReaderFactory.makeReader(validateCatalog(), reader, this.lstFactory);
    }

    @Override // com.amazon.ion.system.IonReaderBuilder
    public IonReader build(IonValue ionValue) {
        return new IonReaderTreeUserX(ionValue, validateCatalog(), this.lstFactory);
    }

    @Override // com.amazon.ion.system.IonReaderBuilder
    public IonTextReader build(String str) {
        return _Private_IonReaderFactory.makeReader(validateCatalog(), str, this.lstFactory);
    }
}
