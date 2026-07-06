package com.amazon.ion.impl;

import com.amazon.ion.IonCatalog;
import com.amazon.ion.IonDatagram;
import com.amazon.ion.IonSymbol;
import com.amazon.ion.IonType;
import com.amazon.ion.IonValue;
import com.amazon.ion.SeekableReader;
import com.amazon.ion.Span;
import com.amazon.ion.SpanProvider;
import com.amazon.ion.SymbolTable;
import com.amazon.ion.SystemSymbols;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public final class IonReaderTreeUserX extends IonReaderTreeSystem implements _Private_ReaderWriter {
    public static final /* synthetic */ boolean $assertionsDisabled = false;
    public IonCatalog _catalog;
    public final _Private_LocalSymbolTableFactory _lstFactory;
    public SymbolTable[] _symbol_table_stack;
    public int _symbol_table_top;
    public SymbolTable _symbols;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public class SeekableReaderFacet implements SeekableReader {
        public SeekableReaderFacet() {
        }

        @Override // com.amazon.ion.SpanProvider
        public Span currentSpan() {
            return IonReaderTreeUserX.this.currentSpanImpl();
        }

        @Override // com.amazon.ion.SeekableReader
        public void hoist(Span span) {
            IonReaderTreeUserX.this.hoistImpl(span);
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class TreeSpan extends DowncastingFaceted implements Span {
        public IonValue _value;

        public TreeSpan() {
        }

        public TreeSpan(AnonymousClass1 anonymousClass1) {
        }
    }

    public IonReaderTreeUserX(IonValue ionValue, IonCatalog ionCatalog, _Private_LocalSymbolTableFactory _private_localsymboltablefactory) {
        super(ionValue);
        this._symbol_table_top = 0;
        this._symbol_table_stack = new SymbolTable[3];
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

    /* JADX INFO: Access modifiers changed from: private */
    public final Span currentSpanImpl() {
        _Private_IonValue _private_ionvalue = this._curr;
        if (_private_ionvalue == null) {
            throw new IllegalStateException("Reader has no current value");
        }
        TreeSpan treeSpan = new TreeSpan();
        treeSpan._value = _private_ionvalue;
        return treeSpan;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void hoistImpl(Span span) {
        if (!(span instanceof TreeSpan)) {
            throw new IllegalArgumentException("Span not appropriate for this reader");
        }
        re_init(((TreeSpan) span)._value, true);
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

    @Override // com.amazon.ion.impl.IonReaderTreeSystem, com.amazon.ion.facet.Faceted
    public <T> T asFacet(Class<T> cls) {
        if (cls == SeekableReader.class || cls == SpanProvider.class) {
            return cls.cast(new SeekableReaderFacet());
        }
        return null;
    }

    @Override // com.amazon.ion.impl.IonReaderTreeSystem, com.amazon.ion.IonReader
    public SymbolTable getSymbolTable() {
        return this._symbols;
    }

    @Override // com.amazon.ion.impl.IonReaderTreeSystem, com.amazon.ion.IonReader
    public boolean hasNext() {
        return next_helper_user();
    }

    @Override // com.amazon.ion.impl.IonReaderTreeSystem, com.amazon.ion.IonReader
    public IonType next() {
        if (!next_helper_user()) {
            this._curr = null;
            return null;
        }
        _Private_IonValue _private_ionvalue = this._next;
        this._curr = _private_ionvalue;
        this._next = null;
        return _private_ionvalue.getType();
    }

    public boolean next_helper_user() {
        IonType ionTypeNext_helper_system;
        String strStringValue;
        if (!this._eof) {
            if (this._next != null) {
                return true;
            }
            clear_system_value_stack();
            while (true) {
                ionTypeNext_helper_system = next_helper_system();
                if (this._top != 0 || !(this._parent instanceof IonDatagram)) {
                    break;
                }
                if (IonType.SYMBOL.equals(ionTypeNext_helper_system)) {
                    IonSymbol ionSymbol = (IonSymbol) this._next;
                    if (ionSymbol.isNullValue()) {
                        break;
                    }
                    int symbolId = ionSymbol.getSymbolId();
                    if (symbolId == -1 && (strStringValue = ionSymbol.stringValue()) != null) {
                        symbolId = this._system_symtab.findSymbol(strStringValue);
                    }
                    if (symbolId != 2 || this._next.getTypeAnnotationSymbols().length != 0) {
                        break;
                    }
                    SymbolTable symbolTable = this._system_symtab;
                    this._symbols = symbolTable;
                    push_symbol_table(symbolTable);
                    this._next = null;
                } else {
                    if (!IonType.STRUCT.equals(ionTypeNext_helper_system) || this._next.findTypeAnnotation(SystemSymbols.ION_SYMBOL_TABLE) != 0) {
                        break;
                    }
                    SymbolTable symbolTableNewLocalSymtab = this._lstFactory.newLocalSymtab(this._catalog, new IonReaderTreeUserX(this._next, this._catalog, this._lstFactory), false);
                    this._symbols = symbolTableNewLocalSymtab;
                    push_symbol_table(symbolTableNewLocalSymtab);
                    this._next = null;
                }
            }
            if (ionTypeNext_helper_system != null) {
                return true;
            }
        }
        return false;
    }

    @Override // com.amazon.ion.impl.IonReaderTreeSystem, com.amazon.ion.impl._Private_ReaderWriter
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

    @Override // com.amazon.ion.impl.IonReaderTreeSystem
    public void re_init(IonValue ionValue, boolean z) {
        super.re_init(ionValue, z);
        this._symbols = this._system_symtab;
    }
}
