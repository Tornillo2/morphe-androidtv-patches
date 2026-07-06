package com.amazon.ion.impl;

import com.amazon.ion.IonCatalog;
import com.amazon.ion.IonType;
import com.amazon.ion.OffsetSpan;
import com.amazon.ion.RawValueSpanProvider;
import com.amazon.ion.SeekableReader;
import com.amazon.ion.Span;
import com.amazon.ion.SpanProvider;
import com.amazon.ion.SymbolTable;
import com.amazon.ion.SymbolToken;
import com.amazon.ion.UnknownSymbolException;
import com.amazon.ion.impl.IonReaderBinaryRawX;
import com.amazon.ion.impl.UnifiedInputStreamX;
import com.amazon.ion.impl.UnifiedSavePointManagerX;
import java.io.IOException;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public final class IonReaderBinaryUserX extends IonReaderBinarySystemX implements _Private_ReaderWriter {
    public static final /* synthetic */ boolean $assertionsDisabled = false;
    public IonCatalog _catalog;
    public final _Private_LocalSymbolTableFactory _lstFactory;
    public final int _physical_start_offset;
    public SymbolTable[] _symbol_table_stack;
    public int _symbol_table_top;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public class ByteTransferReaderFacet implements _Private_ByteTransferReader {
        public ByteTransferReaderFacet() {
        }

        @Override // com.amazon.ion.impl._Private_ByteTransferReader
        public void transferCurrentValue(_Private_ByteTransferSink _private_bytetransfersink) throws IOException {
            IonReaderBinaryUserX ionReaderBinaryUserX = IonReaderBinaryUserX.this;
            UnifiedInputStreamX unifiedInputStreamX = ionReaderBinaryUserX._input;
            if (!(unifiedInputStreamX instanceof UnifiedInputStreamX.FromByteArray)) {
                throw new UnsupportedOperationException();
            }
            _private_bytetransfersink.writeBytes(unifiedInputStreamX._bytes, (int) ionReaderBinaryUserX._position_start, (int) ionReaderBinaryUserX._position_len);
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static class IonReaderBinarySpan extends DowncastingFaceted implements Span, OffsetSpan {
        public final boolean _isSeekable;
        public long _limit;
        public long _offset;
        public IonReaderBinaryRawX.State _state;
        public SymbolTable _symbol_table;

        public IonReaderBinarySpan(boolean z) {
            this._isSeekable = z;
        }

        @Override // com.amazon.ion.OffsetSpan
        public long getFinishOffset() {
            return this._limit;
        }

        @Override // com.amazon.ion.OffsetSpan
        public long getStartOffset() {
            return this._offset;
        }

        public boolean isSeekable() {
            return this._isSeekable;
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public class RawValueSpanProviderFacet implements RawValueSpanProvider {
        public RawValueSpanProviderFacet() {
        }

        @Override // com.amazon.ion.RawValueSpanProvider
        public byte[] buffer() {
            return IonReaderBinaryUserX.this._input._bytes;
        }

        @Override // com.amazon.ion.RawValueSpanProvider
        public Span valueSpan() {
            return IonReaderBinaryUserX.this.getCurrentPosition(false);
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public class SeekableReaderFacet extends SpanProviderFacet implements SeekableReader {
        public SeekableReaderFacet() {
            super();
        }

        @Override // com.amazon.ion.SeekableReader
        public void hoist(Span span) {
            if (span instanceof IonReaderBinarySpan) {
                IonReaderBinarySpan ionReaderBinarySpan = (IonReaderBinarySpan) span;
                if (ionReaderBinarySpan.isSeekable()) {
                    IonReaderBinaryUserX.this.seek(ionReaderBinarySpan);
                    return;
                }
            }
            throw new IllegalArgumentException("Span isn't compatible with this reader.");
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public class SpanProviderFacet implements SpanProvider {
        public SpanProviderFacet() {
        }

        @Override // com.amazon.ion.SpanProvider
        public Span currentSpan() {
            return IonReaderBinaryUserX.this.getCurrentPosition(true);
        }
    }

    public IonReaderBinaryUserX(IonCatalog ionCatalog, _Private_LocalSymbolTableFactory _private_localsymboltablefactory, UnifiedInputStreamX unifiedInputStreamX, int i) {
        super(unifiedInputStreamX);
        this._symbol_table_top = 0;
        this._symbol_table_stack = new SymbolTable[3];
        this._physical_start_offset = i;
        init_user(ionCatalog);
        this._lstFactory = _private_localsymboltablefactory;
    }

    @Override // com.amazon.ion.impl.IonReaderBinaryRawX, com.amazon.ion.facet.Faceted
    public <T> T asFacet(Class<T> cls) {
        if (cls == SpanProvider.class) {
            return cls.cast(new SpanProviderFacet());
        }
        UnifiedInputStreamX unifiedInputStreamX = this._input;
        if (unifiedInputStreamX instanceof UnifiedInputStreamX.FromByteArray) {
            if (cls == SeekableReader.class) {
                return cls.cast(new SeekableReaderFacet());
            }
            if (cls == RawValueSpanProvider.class) {
                return cls.cast(new RawValueSpanProviderFacet());
            }
        }
        if (cls == _Private_ByteTransferReader.class && (unifiedInputStreamX instanceof UnifiedInputStreamX.FromByteArray) && getTypeAnnotationSymbols().length == 0 && !this._is_in_struct) {
            return cls.cast(new ByteTransferReaderFacet());
        }
        return null;
    }

    public final void clear_system_value_stack() {
        while (true) {
            int i = this._symbol_table_top;
            if (i <= 0) {
                return;
            }
            int i2 = i - 1;
            this._symbol_table_top = i2;
            this._symbol_table_stack[i2] = null;
        }
    }

    public byte[] getCurrentBuffer() {
        return this._input._bytes;
    }

    public Span getCurrentPosition(boolean z) {
        if (this._value_type == null) {
            throw new IllegalStateException("IonReader isn't positioned on a value");
        }
        IonReaderBinarySpan ionReaderBinarySpan = new IonReaderBinarySpan(z);
        long j = z ? this._position_start : this._value_start;
        long j2 = z ? this._position_len : this._value_len;
        long j3 = j - ((long) this._physical_start_offset);
        ionReaderBinarySpan._offset = j3;
        ionReaderBinarySpan._limit = j3 + j2;
        ionReaderBinarySpan._symbol_table = this._symbols;
        ionReaderBinarySpan._state = this._state;
        return ionReaderBinarySpan;
    }

    @Override // com.amazon.ion.impl.IonReaderBinarySystemX, com.amazon.ion.IonReader
    public final SymbolToken getFieldNameSymbol() {
        SymbolToken fieldNameSymbol = super.getFieldNameSymbol();
        validateSymbolToken(fieldNameSymbol);
        return fieldNameSymbol;
    }

    @Override // com.amazon.ion.impl.IonReaderBinarySystemX, com.amazon.ion.IonReader
    public SymbolToken[] getTypeAnnotationSymbols() {
        SymbolToken[] typeAnnotationSymbols = super.getTypeAnnotationSymbols();
        for (SymbolToken symbolToken : typeAnnotationSymbols) {
            validateSymbolToken(symbolToken);
        }
        return typeAnnotationSymbols;
    }

    @Override // com.amazon.ion.impl.IonReaderBinaryRawX, com.amazon.ion.IonReader
    public boolean hasNext() {
        if (!this._eof && this._has_next_needed) {
            clear_system_value_stack();
            while (!this._eof && this._has_next_needed) {
                try {
                    has_next_helper_user();
                } catch (IOException e) {
                    error(e);
                    throw null;
                }
            }
        }
        return !this._eof;
    }

    public final void has_next_helper_user() throws IOException {
        super.hasNext();
        if (this._container_top / 2 != 0 || this._value_is_null) {
            return;
        }
        int i = this._value_tid;
        if (i != 7) {
            if (i == 13 && load_annotations() > 0 && this._annotation_ids[0] == 3) {
                SymbolTable symbolTableNewLocalSymtab = this._lstFactory.newLocalSymtab(this._catalog, this, false);
                this._symbols = symbolTableNewLocalSymtab;
                push_symbol_table(symbolTableNewLocalSymtab);
                this._has_next_needed = true;
                return;
            }
            return;
        }
        if (load_annotations() == 0) {
            load_cached_value(3);
            if (this._v.getInt() == 2) {
                SymbolTable systemSymbolTable = SharedSymbolTable.getSystemSymbolTable(1);
                this._symbols = systemSymbolTable;
                push_symbol_table(systemSymbolTable);
                this._has_next_needed = true;
            }
        }
    }

    public final void init_user(IonCatalog ionCatalog) {
        this._symbols = SharedSymbolTable.getSystemSymbolTable(1);
        this._catalog = ionCatalog;
    }

    @Override // com.amazon.ion.impl.IonReaderBinaryRawX, com.amazon.ion.IonReader
    public IonType next() {
        if (!hasNext()) {
            return null;
        }
        this._has_next_needed = true;
        return this._value_type;
    }

    @Override // com.amazon.ion.impl.IonReaderBinarySystemX, com.amazon.ion.impl._Private_ReaderWriter
    public SymbolTable pop_passed_symbol_table() {
        int i = this._symbol_table_top;
        if (i <= 0) {
            return null;
        }
        int i2 = i - 1;
        this._symbol_table_top = i2;
        SymbolTable[] symbolTableArr = this._symbol_table_stack;
        SymbolTable symbolTable = symbolTableArr[i2];
        symbolTableArr[i2] = null;
        return symbolTable;
    }

    public final void push_symbol_table(SymbolTable symbolTable) {
        int i = this._symbol_table_top;
        SymbolTable[] symbolTableArr = this._symbol_table_stack;
        if (i >= symbolTableArr.length) {
            SymbolTable[] symbolTableArr2 = new SymbolTable[symbolTableArr.length * 2];
            System.arraycopy(symbolTableArr, 0, symbolTableArr2, 0, symbolTableArr.length);
            this._symbol_table_stack = symbolTableArr2;
        }
        SymbolTable[] symbolTableArr3 = this._symbol_table_stack;
        int i2 = this._symbol_table_top;
        this._symbol_table_top = i2 + 1;
        symbolTableArr3[i2] = symbolTable;
    }

    public void seek(IonReaderBinarySpan ionReaderBinarySpan) {
        if (ionReaderBinarySpan == null) {
            throw new IllegalArgumentException("Position invalid for binary reader");
        }
        UnifiedInputStreamX unifiedInputStreamX = this._input;
        if (!(unifiedInputStreamX instanceof UnifiedInputStreamX.FromByteArray)) {
            throw new UnsupportedOperationException("Binary seek not implemented for non-byte array backed sources");
        }
        UnifiedInputStreamX.FromByteArray fromByteArray = (UnifiedInputStreamX.FromByteArray) unifiedInputStreamX;
        long j = ionReaderBinarySpan._offset;
        int i = this._physical_start_offset;
        fromByteArray._pos = (int) (j + ((long) i));
        fromByteArray._limit = (int) (ionReaderBinarySpan._limit + ((long) i));
        fromByteArray._eof = false;
        while (true) {
            UnifiedSavePointManagerX unifiedSavePointManagerX = fromByteArray._save_points;
            UnifiedSavePointManagerX.SavePoint savePoint = unifiedSavePointManagerX._active_stack;
            if (savePoint == null) {
                re_init_raw();
                init_user(this._catalog);
                this._symbols = ionReaderBinarySpan._symbol_table;
                return;
            }
            unifiedSavePointManagerX.savePointPopActive(savePoint);
            savePoint.free();
        }
    }

    @Override // com.amazon.ion.impl.IonReaderBinarySystemX, com.amazon.ion.IonReader
    public final SymbolToken symbolValue() {
        SymbolToken symbolTokenSymbolValue = super.symbolValue();
        validateSymbolToken(symbolTokenSymbolValue);
        return symbolTokenSymbolValue;
    }

    public final void validateSymbolToken(SymbolToken symbolToken) {
        if (symbolToken != null && symbolToken.getText() == null && symbolToken.getSid() > this._symbols.getMaxId()) {
            throw new UnknownSymbolException(symbolToken.getSid());
        }
    }
}
