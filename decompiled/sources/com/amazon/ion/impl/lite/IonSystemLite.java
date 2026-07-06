package com.amazon.ion.impl.lite;

import com.amazon.ion.IonBinaryWriter;
import com.amazon.ion.IonCatalog;
import com.amazon.ion.IonContainer;
import com.amazon.ion.IonDatagram;
import com.amazon.ion.IonException;
import com.amazon.ion.IonLoader;
import com.amazon.ion.IonReader;
import com.amazon.ion.IonStruct;
import com.amazon.ion.IonTextReader;
import com.amazon.ion.IonTimestamp;
import com.amazon.ion.IonType;
import com.amazon.ion.IonValue;
import com.amazon.ion.IonWriter;
import com.amazon.ion.SymbolTable;
import com.amazon.ion.SystemSymbols;
import com.amazon.ion.UnexpectedEofException;
import com.amazon.ion.UnsupportedIonVersionException;
import com.amazon.ion.impl.IonIteratorImpl;
import com.amazon.ion.impl.IonReaderTreeUserX;
import com.amazon.ion.impl.SharedSymbolTable;
import com.amazon.ion.impl._Private_IonBinaryWriterBuilder;
import com.amazon.ion.impl._Private_IonReaderBuilder;
import com.amazon.ion.impl._Private_IonReaderFactory;
import com.amazon.ion.impl._Private_IonSystem;
import com.amazon.ion.impl._Private_IonWriterBase;
import com.amazon.ion.impl._Private_IonWriterFactory;
import com.amazon.ion.impl._Private_Utils;
import com.amazon.ion.system.IonReaderBuilder;
import com.amazon.ion.system.IonTextWriterBuilder;
import com.amazon.ion.util.IonTextUtils;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.NoSuchElementException;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public final class IonSystemLite extends ValueFactoryLite implements _Private_IonSystem {
    public static final /* synthetic */ boolean $assertionsDisabled = false;
    public final IonCatalog _catalog;
    public final IonLoader _loader;
    public final SymbolTable _system_symbol_table;
    public final _Private_IonBinaryWriterBuilder myBinaryWriterBuilder;
    public final IonReaderBuilder myReaderBuilder;
    public final IonTextWriterBuilder myTextWriterBuilder;

    /* JADX INFO: renamed from: com.amazon.ion.impl.lite.IonSystemLite$1, reason: invalid class name */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static /* synthetic */ class AnonymousClass1 {
        public static final /* synthetic */ int[] $SwitchMap$com$amazon$ion$IonType;

        static {
            int[] iArr = new int[IonType.values().length];
            $SwitchMap$com$amazon$ion$IonType = iArr;
            try {
                iArr[IonType.BOOL.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$ion$IonType[IonType.INT.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$ion$IonType[IonType.FLOAT.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$amazon$ion$IonType[IonType.DECIMAL.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$amazon$ion$IonType[IonType.TIMESTAMP.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$amazon$ion$IonType[IonType.SYMBOL.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$amazon$ion$IonType[IonType.STRING.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$com$amazon$ion$IonType[IonType.CLOB.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                $SwitchMap$com$amazon$ion$IonType[IonType.BLOB.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                $SwitchMap$com$amazon$ion$IonType[IonType.LIST.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                $SwitchMap$com$amazon$ion$IonType[IonType.SEXP.ordinal()] = 11;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                $SwitchMap$com$amazon$ion$IonType[IonType.STRUCT.ordinal()] = 12;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                $SwitchMap$com$amazon$ion$IonType[IonType.NULL.ordinal()] = 13;
            } catch (NoSuchFieldError unused13) {
            }
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static class ReaderIterator implements Iterator<IonValue>, Closeable {
        public IonType _next;
        public final IonReader _reader;
        public final IonSystemLite _system;

        public ReaderIterator(IonSystemLite ionSystemLite, IonReader ionReader) {
            this._reader = ionReader;
            this._system = ionSystemLite;
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            if (this._next == null) {
                this._next = this._reader.next();
            }
            return this._next != null;
        }

        @Override // java.util.Iterator
        public void remove() {
            throw new UnsupportedOperationException();
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // java.util.Iterator
        public IonValue next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            SymbolTable symbolTable = this._reader.getSymbolTable();
            IonValueLite ionValueLiteNewValue = this._system.newValue(this._reader);
            this._next = null;
            ionValueLiteNewValue.setSymbolTable(symbolTable);
            return ionValueLiteNewValue;
        }

        @Override // java.io.Closeable, java.lang.AutoCloseable
        public void close() throws IOException {
        }
    }

    public IonSystemLite(IonTextWriterBuilder ionTextWriterBuilder, _Private_IonBinaryWriterBuilder _private_ionbinarywriterbuilder, IonReaderBuilder ionReaderBuilder) {
        IonCatalog catalog = ionTextWriterBuilder.getCatalog();
        this._catalog = catalog;
        this.myReaderBuilder = ((_Private_IonReaderBuilder) ionReaderBuilder).withLstFactory(this._lstFactory).immutable();
        this._loader = new IonLoaderLite(this, catalog);
        this._system_symbol_table = _private_ionbinarywriterbuilder.getInitialSymbolTable();
        this.myTextWriterBuilder = ionTextWriterBuilder.immutable();
        set_system(this);
        _private_ionbinarywriterbuilder.setSymtabValueFactory(this);
        this.myBinaryWriterBuilder = _private_ionbinarywriterbuilder.immutable();
    }

    @Override // com.amazon.ion.ValueFactory
    public <T extends IonValue> T clone(T t) throws IonException {
        if (t.getSystem() == this) {
            return (T) t.mo245clone();
        }
        if (!(t instanceof IonDatagram)) {
            IonReader ionReaderBuild = this.myReaderBuilder.build(t);
            ((IonReaderTreeUserX) ionReaderBuild).next();
            return newValue(ionReaderBuild);
        }
        IonDatagramLite ionDatagramLiteNewDatagram = newDatagram(this._catalog);
        try {
            ((_Private_IonWriterBase) _Private_IonWriterFactory.makeWriter(ionDatagramLiteNewDatagram._system._catalog, ionDatagramLiteNewDatagram)).writeValues(_Private_IonReaderFactory.makeSystemReader(t.getSystem(), t));
            return ionDatagramLiteNewDatagram;
        } catch (IOException e) {
            throw new IonException(e.getMessage(), e);
        }
    }

    @Override // com.amazon.ion.IonSystem
    public IonCatalog getCatalog() {
        return this._catalog;
    }

    @Override // com.amazon.ion.IonSystem
    public IonLoader getLoader() {
        return this._loader;
    }

    public IonReaderBuilder getReaderBuilder() {
        return this.myReaderBuilder;
    }

    @Override // com.amazon.ion.IonSystem
    public final SymbolTable getSystemSymbolTable() {
        return this._system_symbol_table;
    }

    @Override // com.amazon.ion.impl._Private_IonSystem
    public boolean isStreamCopyOptimized() {
        return this.myBinaryWriterBuilder.isStreamCopyOptimized();
    }

    @Override // com.amazon.ion.IonSystem
    public Iterator<IonValue> iterate(Reader reader) {
        return new ReaderIterator(this, this.myReaderBuilder.build(reader));
    }

    public final boolean load_children(IonContainerLite ionContainerLite, IonReader ionReader) {
        ionReader.stepIn();
        boolean z = false;
        while (ionReader.next() != null) {
            IonValueLite ionValueLiteLoad_value_helper = load_value_helper(ionReader, false);
            ionContainerLite.add(ionValueLiteLoad_value_helper);
            if (ionValueLiteLoad_value_helper.is_true(64)) {
                z = true;
            }
        }
        ionReader.stepOut();
        return z;
    }

    /* JADX WARN: Removed duplicated region for block: B:38:0x00c4 A[LOOP:0: B:38:0x00c4->B:45:0x00db, LOOP_START, PHI: r4
      0x00c4: PHI (r4v2 int) = (r4v0 int), (r4v3 int) binds: [B:37:0x00c2, B:45:0x00db] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:49:0x00e8  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x0113  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x0116  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final com.amazon.ion.impl.lite.IonValueLite load_value_helper(com.amazon.ion.IonReader r9, boolean r10) {
        /*
            Method dump skipped, instruction units count: 340
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.ion.impl.lite.IonSystemLite.load_value_helper(com.amazon.ion.IonReader, boolean):com.amazon.ion.impl.lite.IonValueLite");
    }

    @Override // com.amazon.ion.IonSystem
    @Deprecated
    public IonBinaryWriter newBinaryWriter() {
        return this.myBinaryWriterBuilder.buildLegacy();
    }

    @Override // com.amazon.ion.IonSystem
    public IonTimestamp newCurrentUtcTimestamp() {
        IonTimestampLite ionTimestampLiteNewNullTimestamp = newNullTimestamp();
        ionTimestampLiteNewNullTimestamp.setCurrentTimeUtc();
        return ionTimestampLiteNewNullTimestamp;
    }

    @Override // com.amazon.ion.IonSystem
    public IonDatagram newDatagram() {
        return newDatagram(this._catalog);
    }

    @Override // com.amazon.ion.IonSystem
    public IonLoader newLoader(IonCatalog ionCatalog) {
        if (ionCatalog == null) {
            ionCatalog = this._catalog;
        }
        return new IonLoaderLite(this, ionCatalog);
    }

    @Override // com.amazon.ion.IonSystem
    public SymbolTable newLocalSymbolTable(SymbolTable... symbolTableArr) {
        return this._lstFactory.newLocalSymtab(this._system_symbol_table, symbolTableArr);
    }

    @Override // com.amazon.ion.IonSystem
    public IonReader newReader(byte[] bArr) {
        return this.myReaderBuilder.build(bArr);
    }

    @Override // com.amazon.ion.impl._Private_IonSystem
    public SymbolTable newSharedSymbolTable(IonStruct ionStruct) {
        return _Private_Utils.newSharedSymtab(ionStruct);
    }

    public IonSymbolLite newSystemIdSymbol(String str) {
        if (!SystemSymbols.ION_1_0.equals(str)) {
            throw new IllegalArgumentException("name isn't an ion version marker");
        }
        IonSymbolLite ionSymbolLiteNewSymbol = newSymbol(str);
        ionSymbolLiteNewSymbol.setIsIonVersionMarker(true);
        return ionSymbolLiteNewSymbol;
    }

    @Override // com.amazon.ion.impl._Private_IonSystem
    public IonReader newSystemReader(byte[] bArr) {
        return _Private_IonReaderFactory.makeSystemReader(bArr, 0, bArr.length);
    }

    @Override // com.amazon.ion.IonSystem
    public IonWriter newTextWriter(Appendable appendable) {
        return this.myTextWriterBuilder.build(appendable);
    }

    @Override // com.amazon.ion.impl._Private_IonSystem
    public IonWriter newTreeSystemWriter(IonContainer ionContainer) {
        return _Private_IonWriterFactory.makeSystemWriter(ionContainer);
    }

    @Override // com.amazon.ion.impl._Private_IonSystem
    public IonWriter newTreeWriter(IonContainer ionContainer) {
        return _Private_IonWriterFactory.makeWriter(ionContainer);
    }

    @Override // com.amazon.ion.IonSystem
    public IonTimestamp newUtcTimestamp(Date date) {
        IonTimestampLite ionTimestampLiteNewNullTimestamp = newNullTimestamp();
        if (date != null) {
            ionTimestampLiteNewNullTimestamp.setMillisUtc(date.getTime());
        }
        return ionTimestampLiteNewNullTimestamp;
    }

    @Override // com.amazon.ion.IonSystem
    public IonTimestamp newUtcTimestampFromMillis(long j) {
        IonTimestampLite ionTimestampLiteNewNullTimestamp = newNullTimestamp();
        ionTimestampLiteNewNullTimestamp.setMillisUtc(j);
        return ionTimestampLiteNewNullTimestamp;
    }

    @Override // com.amazon.ion.IonSystem
    public IonWriter newWriter(IonContainer ionContainer) {
        return _Private_IonWriterFactory.makeWriter(ionContainer);
    }

    public final IonValue singleValue(Iterator<IonValue> it) {
        try {
            IonValue next = it.next();
            if (it.hasNext()) {
                throw new IonException("not a single value");
            }
            return next;
        } catch (NoSuchElementException unused) {
            throw new UnexpectedEofException("no value found on input stream");
        }
    }

    @Override // com.amazon.ion.impl._Private_IonSystem
    public Iterator<IonValue> systemIterate(String str) {
        return new IonIteratorImpl(this, _Private_IonReaderFactory.makeSystemReader(str));
    }

    @Override // com.amazon.ion.impl._Private_IonSystem
    public boolean valueIsSharedSymbolTable(IonValue ionValue) {
        return (ionValue instanceof IonStruct) && ionValue.hasTypeAnnotation(SystemSymbols.ION_SYMBOL_TABLE);
    }

    @Override // com.amazon.ion.IonSystem
    public SymbolTable getSystemSymbolTable(String str) throws UnsupportedIonVersionException {
        if (SystemSymbols.ION_1_0.equals(str)) {
            return this._system_symbol_table;
        }
        throw new UnsupportedIonVersionException(str);
    }

    @Override // com.amazon.ion.IonSystem
    public IonReader newReader(byte[] bArr, int i, int i2) {
        return this.myReaderBuilder.build(bArr, i, i2);
    }

    @Override // com.amazon.ion.IonSystem
    public SymbolTable newSharedSymbolTable(IonReader ionReader) {
        return _Private_Utils.newSharedSymtab(ionReader, false);
    }

    @Override // com.amazon.ion.impl._Private_IonSystem
    public IonReader newSystemReader(byte[] bArr, int i, int i2) {
        return _Private_IonReaderFactory.makeSystemReader(bArr, i, i2);
    }

    @Override // com.amazon.ion.IonSystem
    public IonWriter newTextWriter(Appendable appendable, SymbolTable... symbolTableArr) throws IOException {
        return this.myTextWriterBuilder.withImports(symbolTableArr).build(appendable);
    }

    @Override // com.amazon.ion.IonSystem
    public IonValueLite newValue(IonReader ionReader) {
        IonValueLite ionValueLiteLoad_value_helper = load_value_helper(ionReader, true);
        if (ionValueLiteLoad_value_helper != null) {
            return ionValueLiteLoad_value_helper;
        }
        throw new IonException("No value available");
    }

    @Override // com.amazon.ion.IonSystem
    public Iterator<IonValue> iterate(InputStream inputStream) {
        return new ReaderIterator(this, this.myReaderBuilder.build(inputStream));
    }

    @Override // com.amazon.ion.IonSystem
    @Deprecated
    public IonBinaryWriter newBinaryWriter(SymbolTable... symbolTableArr) {
        return ((_Private_IonBinaryWriterBuilder) this.myBinaryWriterBuilder.withImports(symbolTableArr)).buildLegacy();
    }

    public IonDatagramLite newDatagram(IonCatalog ionCatalog) {
        if (ionCatalog == null) {
            ionCatalog = this._catalog;
        }
        return new IonDatagramLite(this, ionCatalog);
    }

    @Override // com.amazon.ion.IonSystem
    public IonLoader newLoader() {
        return new IonLoaderLite(this, this._catalog);
    }

    @Override // com.amazon.ion.IonSystem
    public IonTextReader newReader(String str) {
        return this.myReaderBuilder.build(str);
    }

    @Override // com.amazon.ion.IonSystem
    public SymbolTable newSharedSymbolTable(IonReader ionReader, boolean z) {
        return _Private_Utils.newSharedSymtab(ionReader, z);
    }

    @Override // com.amazon.ion.impl._Private_IonSystem
    public IonReader newSystemReader(String str) {
        return _Private_IonReaderFactory.makeSystemReader(str);
    }

    @Override // com.amazon.ion.IonSystem
    public IonWriter newTextWriter(OutputStream outputStream) {
        return this.myTextWriterBuilder.build(outputStream);
    }

    @Override // com.amazon.ion.impl._Private_IonSystem
    public Iterator<IonValue> systemIterate(Reader reader) {
        return _Private_Utils.iterate(this, _Private_IonReaderFactory.makeSystemReader(reader));
    }

    @Override // com.amazon.ion.IonSystem
    public IonReader newReader(InputStream inputStream) {
        return this.myReaderBuilder.build(inputStream);
    }

    @Override // com.amazon.ion.IonSystem
    public SymbolTable newSharedSymbolTable(String str, int i, Iterator<String> it, SymbolTable... symbolTableArr) {
        SymbolTable table;
        ArrayList arrayList = new ArrayList();
        if (i > 1) {
            int i2 = i - 1;
            table = this._catalog.getTable(str, i2);
            if (table == null || table.getVersion() != i2) {
                throw new IonException("Catalog does not contain symbol table " + IonTextUtils.printString(str) + " version " + i2 + " required to create version " + i);
            }
        } else {
            table = null;
        }
        for (SymbolTable symbolTable : symbolTableArr) {
            _Private_Utils.addAllNonNull(arrayList, symbolTable.iterateDeclaredSymbolNames());
        }
        _Private_Utils.addAllNonNull(arrayList, it);
        return SharedSymbolTable.newSharedSymbolTable(str, i, table, arrayList.iterator());
    }

    @Override // com.amazon.ion.impl._Private_IonSystem
    public IonReader newSystemReader(InputStream inputStream) {
        return _Private_IonReaderFactory.makeSystemReader(inputStream);
    }

    @Override // com.amazon.ion.IonSystem
    public IonWriter newTextWriter(OutputStream outputStream, SymbolTable... symbolTableArr) throws IOException {
        return this.myTextWriterBuilder.withImports(symbolTableArr).build(outputStream);
    }

    @Override // com.amazon.ion.IonSystem
    public Iterator<IonValue> iterate(String str) {
        return new ReaderIterator(this, this.myReaderBuilder.build(str));
    }

    public IonDatagram newDatagram(IonCatalog ionCatalog, SymbolTable... symbolTableArr) {
        SymbolTable symbolTableInitialSymtab = _Private_Utils.initialSymtab(this._lstFactory, this._system_symbol_table, symbolTableArr);
        IonDatagramLite ionDatagramLiteNewDatagram = newDatagram(ionCatalog);
        ionDatagramLiteNewDatagram.appendTrailingSymbolTable(symbolTableInitialSymtab);
        return ionDatagramLiteNewDatagram;
    }

    @Override // com.amazon.ion.IonSystem
    public IonReader newReader(Reader reader) {
        return this.myReaderBuilder.build(reader);
    }

    @Override // com.amazon.ion.impl._Private_IonSystem
    public IonReader newSystemReader(Reader reader) {
        return _Private_IonReaderFactory.makeSystemReader(reader);
    }

    @Override // com.amazon.ion.impl._Private_IonSystem
    public Iterator<IonValue> systemIterate(IonReader ionReader) {
        return _Private_Utils.iterate(this, ionReader);
    }

    @Override // com.amazon.ion.IonSystem
    public IonWriter newBinaryWriter(OutputStream outputStream, SymbolTable... symbolTableArr) {
        return this.myBinaryWriterBuilder.withImports(symbolTableArr).build(outputStream);
    }

    @Override // com.amazon.ion.IonSystem
    public IonReader newReader(IonValue ionValue) {
        return this.myReaderBuilder.build(ionValue);
    }

    @Override // com.amazon.ion.impl._Private_IonSystem
    public IonReader newSystemReader(IonValue ionValue) {
        return _Private_IonReaderFactory.makeSystemReader(this, ionValue);
    }

    public IonValueLite newValue(IonType ionType) {
        if (ionType != null) {
            switch (AnonymousClass1.$SwitchMap$com$amazon$ion$IonType[ionType.ordinal()]) {
                case 1:
                    return newNullBool();
                case 2:
                    return newNullInt();
                case 3:
                    return newNullFloat();
                case 4:
                    return newNullDecimal();
                case 5:
                    return newNullTimestamp();
                case 6:
                    return newNullSymbol();
                case 7:
                    return newNullString();
                case 8:
                    return newNullClob();
                case 9:
                    return newNullBlob();
                case 10:
                    return newEmptyList();
                case 11:
                    return newEmptySexp();
                case 12:
                    return newEmptyStruct();
                case 13:
                    return newNull();
                default:
                    throw new IonException("unexpected type encountered reading value: " + ionType);
            }
        }
        throw new IllegalArgumentException("the value type must be specified");
    }

    @Override // com.amazon.ion.IonSystem
    public Iterator<IonValue> iterate(byte[] bArr) {
        return new ReaderIterator(this, this.myReaderBuilder.build(bArr));
    }

    @Override // com.amazon.ion.IonSystem
    public Iterator<IonValue> iterate(IonReader ionReader) {
        return new ReaderIterator(this, ionReader);
    }

    @Override // com.amazon.ion.IonSystem
    public IonDatagram newDatagram(IonValue ionValue) {
        return newDatagram((IonCatalog) null, ionValue);
    }

    @Override // com.amazon.ion.IonSystem
    public IonValue singleValue(String str) {
        return singleValue(iterate(str));
    }

    public IonDatagram newDatagram(IonCatalog ionCatalog, IonValue ionValue) {
        IonDatagramLite ionDatagramLiteNewDatagram = newDatagram(ionCatalog);
        if (ionValue == null) {
            return ionDatagramLiteNewDatagram;
        }
        if (ionValue.getSystem() == this) {
            if (ionValue.getContainer() != null) {
                ionValue = clone(ionValue);
            }
            ionDatagramLiteNewDatagram.add(ionValue);
            return ionDatagramLiteNewDatagram;
        }
        throw new IonException("this Ion system can't mix with instances from other system impl's");
    }

    @Override // com.amazon.ion.IonSystem
    public IonValue singleValue(byte[] bArr) {
        return singleValue(bArr, 0, bArr.length);
    }

    @Override // com.amazon.ion.IonSystem
    public IonValue singleValue(byte[] bArr, int i, int i2) {
        IonReader ionReaderBuild = this.myReaderBuilder.build(bArr, i, i2);
        try {
            IonValue ionValueSingleValue = singleValue(new ReaderIterator(this, ionReaderBuild));
            try {
                ionReaderBuild.close();
                return ionValueSingleValue;
            } catch (IOException e) {
                throw new IonException(e.getMessage(), e);
            }
        } catch (Throwable th) {
            try {
                ionReaderBuild.close();
                throw th;
            } catch (IOException e2) {
                throw new IonException(e2.getMessage(), e2);
            }
        }
    }

    @Override // com.amazon.ion.IonSystem
    public IonDatagram newDatagram(SymbolTable... symbolTableArr) {
        return newDatagram((IonCatalog) null, symbolTableArr);
    }
}
