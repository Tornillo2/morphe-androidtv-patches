package com.amazon.ion.impl;

import com.amazon.ion.IonCatalog;
import com.amazon.ion.IonType;
import com.amazon.ion.OffsetSpan;
import com.amazon.ion.SeekableReader;
import com.amazon.ion.Span;
import com.amazon.ion.SpanProvider;
import com.amazon.ion.SymbolTable;
import com.amazon.ion.SymbolToken;
import com.amazon.ion.SystemSymbols;
import com.amazon.ion.TextSpan;
import com.amazon.ion.UnknownSymbolException;
import com.amazon.ion.UnsupportedIonVersionException;
import com.amazon.ion.impl.UnifiedInputStreamX;
import java.util.regex.Pattern;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public class IonReaderTextUserX extends IonReaderTextSystemX implements _Private_ReaderWriter {
    public static final /* synthetic */ boolean $assertionsDisabled = false;
    public static final Pattern ION_VERSION_MARKER_REGEX = Pattern.compile("^\\$ion_[0-9]+_[0-9]+$");
    public IonCatalog _catalog;
    public final _Private_LocalSymbolTableFactory _lstFactory;
    public final int _physical_start_offset;
    public SymbolTable[] _symbol_table_stack;
    public int _symbol_table_top;
    public SymbolTable _symbols;

    /* JADX INFO: renamed from: com.amazon.ion.impl.IonReaderTextUserX$1, reason: invalid class name */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static /* synthetic */ class AnonymousClass1 {
        public static final /* synthetic */ int[] $SwitchMap$com$amazon$ion$IonType;

        static {
            int[] iArr = new int[IonType.values().length];
            $SwitchMap$com$amazon$ion$IonType = iArr;
            try {
                iArr[IonType.STRUCT.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$ion$IonType[IonType.SYMBOL.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class IonReaderTextSpan extends DowncastingFaceted implements Span, TextSpan, OffsetSpan {
        public final IonType _container_type;
        public final UnifiedDataPageX _data_page;
        public final long _start_column;
        public final long _start_line;
        public final long _start_offset;

        public IonReaderTextSpan(IonReaderTextUserX ionReaderTextUserX) {
            this._data_page = ionReaderTextUserX._scanner._stream._buffer.getCurrentPage();
            this._container_type = ionReaderTextUserX.getContainerType();
            this._start_offset = ionReaderTextUserX._value_start_offset - ((long) ionReaderTextUserX._physical_start_offset);
            this._start_line = ionReaderTextUserX._value_start_line;
            this._start_column = ionReaderTextUserX._value_start_column;
        }

        public IonType getContainerType() {
            return this._container_type;
        }

        public UnifiedDataPageX getDataPage() {
            return this._data_page;
        }

        @Override // com.amazon.ion.TextSpan
        public long getFinishColumn() {
            return -1L;
        }

        @Override // com.amazon.ion.TextSpan
        public long getFinishLine() {
            return -1L;
        }

        @Override // com.amazon.ion.OffsetSpan
        public long getFinishOffset() {
            return -1L;
        }

        @Override // com.amazon.ion.TextSpan
        public long getStartColumn() {
            long j = this._start_column;
            if (j >= 0) {
                return j;
            }
            throw new IllegalStateException("not positioned on a reader");
        }

        @Override // com.amazon.ion.TextSpan
        public long getStartLine() {
            long j = this._start_line;
            if (j >= 1) {
                return j;
            }
            throw new IllegalStateException("not positioned on a reader");
        }

        @Override // com.amazon.ion.OffsetSpan
        public long getStartOffset() {
            return this._start_offset;
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public class SpanProviderFacet implements SpanProvider {
        public SpanProviderFacet() {
        }

        @Override // com.amazon.ion.SpanProvider
        public Span currentSpan() {
            return IonReaderTextUserX.this.currentSpanImpl();
        }

        public /* synthetic */ SpanProviderFacet(IonReaderTextUserX ionReaderTextUserX, AnonymousClass1 anonymousClass1) {
            this();
        }
    }

    public IonReaderTextUserX(IonCatalog ionCatalog, _Private_LocalSymbolTableFactory _private_localsymboltablefactory, UnifiedInputStreamX unifiedInputStreamX, int i) {
        super(unifiedInputStreamX);
        this._symbol_table_top = 0;
        this._symbol_table_stack = new SymbolTable[3];
        this._symbols = this._system_symtab;
        this._physical_start_offset = i;
        this._catalog = ionCatalog;
        this._lstFactory = _private_localsymboltablefactory;
    }

    private void clear_system_value_stack() {
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

    public static boolean isIonVersionMarker(String str) {
        return str != null && ION_VERSION_MARKER_REGEX.matcher(str).matches();
    }

    private void push_symbol_table(SymbolTable symbolTable) {
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

    private void validateSymbolToken(SymbolToken symbolToken) {
        if (symbolToken != null && symbolToken.getText() == null && symbolToken.getSid() > getSymbolTable().getMaxId()) {
            throw new UnknownSymbolException(symbolToken.getSid());
        }
    }

    @Override // com.amazon.ion.impl.IonReaderTextRawX, com.amazon.ion.facet.Faceted
    public <T> T asFacet(Class<T> cls) {
        if (cls == SpanProvider.class) {
            return cls.cast(new SpanProviderFacet());
        }
        if (cls == SeekableReader.class && this._scanner.isBufferedInput()) {
            return cls.cast(new SeekableReaderFacet());
        }
        return null;
    }

    public Span currentSpanImpl() {
        if (getType() != null) {
            return new IonReaderTextSpan(this);
        }
        throw new IllegalStateException("must be on a value");
    }

    @Override // com.amazon.ion.impl.IonReaderTextSystemX, com.amazon.ion.impl.IonReaderTextRawX, com.amazon.ion.IonReader
    public final SymbolToken getFieldNameSymbol() {
        SymbolToken fieldNameSymbol = super.getFieldNameSymbol();
        validateSymbolToken(fieldNameSymbol);
        return fieldNameSymbol;
    }

    @Override // com.amazon.ion.impl.IonReaderTextSystemX, com.amazon.ion.impl.IonReaderTextRawX, com.amazon.ion.IonReader
    public SymbolTable getSymbolTable() {
        return this._symbols;
    }

    @Override // com.amazon.ion.impl.IonReaderTextSystemX, com.amazon.ion.IonReader
    public SymbolToken[] getTypeAnnotationSymbols() {
        SymbolToken[] typeAnnotationSymbols = super.getTypeAnnotationSymbols();
        for (SymbolToken symbolToken : typeAnnotationSymbols) {
            validateSymbolToken(symbolToken);
        }
        return typeAnnotationSymbols;
    }

    @Override // com.amazon.ion.impl.IonReaderTextRawX, com.amazon.ion.IonReader
    public boolean hasNext() {
        return has_next_user_value();
    }

    public final boolean has_next_user_value() {
        clear_system_value_stack();
        while (!this._has_next_called) {
            has_next_raw_value();
            if (this._value_type != null && !isNullValue() && IonType.DATAGRAM.equals(getContainerType())) {
                int i = AnonymousClass1.$SwitchMap$com$amazon$ion$IonType[this._value_type.ordinal()];
                if (i != 1) {
                    if (i == 2 && this._annotation_count == 0) {
                        String str = ((SymbolTokenImpl) symbolValue()).myText;
                        if (!isIonVersionMarker(str)) {
                            continue;
                        } else {
                            if (!SystemSymbols.ION_1_0.equals(str)) {
                                throw new UnsupportedIonVersionException(str);
                            }
                            if (this._value_keyword != 17) {
                                symbol_table_reset();
                                push_symbol_table(this._system_symtab);
                            }
                            this._has_next_called = false;
                        }
                    }
                } else if (this._annotation_count > 0 && SystemSymbols.ION_SYMBOL_TABLE.equals(this._annotations[0].getText())) {
                    SymbolTable symbolTableNewLocalSymtab = this._lstFactory.newLocalSymtab(this._catalog, this, true);
                    this._symbols = symbolTableNewLocalSymtab;
                    push_symbol_table(symbolTableNewLocalSymtab);
                    this._has_next_called = false;
                }
            }
        }
        return !this._eof;
    }

    public final void hoistImpl(Span span) {
        if (!(span instanceof IonReaderTextSpan)) {
            throw new IllegalArgumentException("position must match the reader");
        }
        IonReaderTextSpan ionReaderTextSpan = (IonReaderTextSpan) span;
        UnifiedInputStreamX unifiedInputStreamX = this._scanner._stream;
        UnifiedDataPageX unifiedDataPageX = ionReaderTextSpan._data_page;
        int i = ((int) ionReaderTextSpan._start_offset) + this._physical_start_offset;
        int i2 = unifiedDataPageX._page_limit - i;
        re_init(unifiedInputStreamX._is_byte_data ? new UnifiedInputStreamX.FromByteArray(unifiedInputStreamX._bytes, i, i2) : new UnifiedInputStreamX.FromCharArray(unifiedInputStreamX._chars, i, i2), ionReaderTextSpan._container_type, ionReaderTextSpan._start_line, ionReaderTextSpan._start_column);
    }

    @Override // com.amazon.ion.impl.IonReaderTextSystemX, com.amazon.ion.impl._Private_ReaderWriter
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

    @Override // com.amazon.ion.impl.IonReaderTextSystemX, com.amazon.ion.IonReader
    public final SymbolToken symbolValue() {
        SymbolToken symbolTokenSymbolValue = super.symbolValue();
        validateSymbolToken(symbolTokenSymbolValue);
        return symbolTokenSymbolValue;
    }

    public final void symbol_table_reset() {
        next();
        this._symbols = this._system_symtab;
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public final class SeekableReaderFacet extends SpanProviderFacet implements SeekableReader {
        public SeekableReaderFacet() {
            super();
        }

        @Override // com.amazon.ion.SeekableReader
        public void hoist(Span span) {
            IonReaderTextUserX.this.hoistImpl(span);
        }

        public /* synthetic */ SeekableReaderFacet(IonReaderTextUserX ionReaderTextUserX, AnonymousClass1 anonymousClass1) {
            this();
        }
    }

    public IonReaderTextUserX(IonCatalog ionCatalog, _Private_LocalSymbolTableFactory _private_localsymboltablefactory, UnifiedInputStreamX unifiedInputStreamX) {
        this(ionCatalog, _private_localsymboltablefactory, unifiedInputStreamX, 0);
    }
}
