package com.amazon.ion.impl;

import com.amazon.ion.IonCatalog;
import com.amazon.ion.IonException;
import com.amazon.ion.IonStruct;
import com.amazon.ion.IonType;
import com.amazon.ion.SymbolTable;
import com.amazon.ion.SymbolToken;
import com.amazon.ion.SystemSymbols;
import com.amazon.ion.Timestamp;
import com.amazon.ion.ValueFactory;
import com.amazon.ion.impl.LocalSymbolTableAsStruct;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public class IonWriterUser extends _Private_IonWriterBase implements _Private_IonWriter {
    public static final /* synthetic */ boolean $assertionsDisabled = false;
    public final IonCatalog _catalog;
    public IonWriterSystem _current_writer;
    public IonStruct _symbol_table_value;
    public final ValueFactory _symtab_value_factory;
    public final IonWriterSystem _system_writer;

    public IonWriterUser(IonCatalog ionCatalog, ValueFactory valueFactory, IonWriterSystem ionWriterSystem) {
        this._symtab_value_factory = valueFactory;
        this._catalog = ionCatalog;
        this._system_writer = ionWriterSystem;
        this._current_writer = ionWriterSystem;
    }

    public SymbolTable activeSystemSymbolTable() {
        return this._system_writer._symbol_table.getSystemSymbolTable();
    }

    @Override // com.amazon.ion.IonWriter
    public void addTypeAnnotation(String str) {
        this._current_writer.addTypeAnnotation(str);
    }

    @Override // com.amazon.ion.impl._Private_IonWriterBase
    public final String assumeKnownSymbol(int i) {
        return this._system_writer.assumeKnownSymbol(i);
    }

    @Override // com.amazon.ion.IonWriter, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        try {
            try {
                if (getDepth() == 0) {
                    finish();
                }
            } finally {
                this._current_writer.close();
            }
        } finally {
            this._system_writer.close();
        }
    }

    public final void close_local_symbol_table_copy() throws IOException {
        SymbolTable symbolTableNewLocalSymtab = ((LocalSymbolTableAsStruct.Factory) ((_Private_ValueFactory) this._symtab_value_factory).getLstFactory()).newLocalSymtab(this._catalog, this._symbol_table_value);
        this._symbol_table_value = null;
        this._current_writer = this._system_writer;
        setSymbolTable(symbolTableNewLocalSymtab);
    }

    @Override // com.amazon.ion.impl._Private_IonWriterBase
    public int findAnnotation(String str) {
        return this._current_writer.findAnnotation(str);
    }

    @Override // com.amazon.ion.IonWriter
    public final void finish() throws IOException {
        if (symbol_table_being_collected()) {
            throw new IllegalStateException(_Private_IonWriterBase.ERROR_FINISH_NOT_AT_TOP_LEVEL);
        }
        this._system_writer.finish();
    }

    @Override // com.amazon.ion.IonWriter, java.io.Flushable
    public void flush() throws IOException {
        this._current_writer.flush();
    }

    @Override // com.amazon.ion.impl._Private_IonWriter
    public IonCatalog getCatalog() {
        return this._catalog;
    }

    @Override // com.amazon.ion.impl._Private_IonWriterBase
    public int getDepth() {
        return this._current_writer.getDepth();
    }

    @Override // com.amazon.ion.IonWriter
    public final SymbolTable getSymbolTable() {
        return this._system_writer._symbol_table;
    }

    @Override // com.amazon.ion.impl._Private_IonWriterBase
    public int[] getTypeAnnotationIds() {
        return this._current_writer.getTypeAnnotationIds();
    }

    public final SymbolToken[] getTypeAnnotationSymbols() {
        return this._current_writer.getTypeAnnotationSymbols();
    }

    @Override // com.amazon.ion.impl._Private_IonWriterBase
    public String[] getTypeAnnotations() {
        return this._current_writer.getTypeAnnotations();
    }

    @Override // com.amazon.ion.impl._Private_IonWriterBase
    public final boolean isFieldNameSet() {
        return this._current_writer.isFieldNameSet();
    }

    @Override // com.amazon.ion.IonWriter
    public boolean isInStruct() {
        return this._current_writer.isInStruct();
    }

    public final void open_local_symbol_table_copy() {
        this._symbol_table_value = this._symtab_value_factory.newEmptyStruct();
        SymbolToken[] typeAnnotationSymbols = this._system_writer.getTypeAnnotationSymbols();
        this._system_writer._annotation_count = 0;
        this._symbol_table_value.setTypeAnnotationSymbols(typeAnnotationSymbols);
        this._current_writer = new IonWriterSystemTree(activeSystemSymbolTable(), this._catalog, this._symbol_table_value, null);
    }

    @Override // com.amazon.ion.IonWriter
    public final void setFieldName(String str) {
        this._current_writer.setFieldName(str);
    }

    @Override // com.amazon.ion.IonWriter
    public final void setFieldNameSymbol(SymbolToken symbolToken) {
        this._current_writer.setFieldNameSymbol(symbolToken);
    }

    @Override // com.amazon.ion.impl._Private_IonWriterBase
    public final void setSymbolTable(SymbolTable symbolTable) throws IOException {
        if (symbolTable == null || _Private_Utils.symtabIsSharedNotSystem(symbolTable)) {
            throw new IllegalArgumentException("symbol table must be local or system to be set, or reset");
        }
        if (getDepth() > 0) {
            throw new IllegalStateException("the symbol table cannot be set, or reset, while a container is open");
        }
        if (symbolTable.isSystemTable()) {
            writeIonVersionMarker(symbolTable);
        } else {
            this._system_writer.writeLocalSymtab(symbolTable);
        }
    }

    @Override // com.amazon.ion.IonWriter
    public void setTypeAnnotationSymbols(SymbolToken... symbolTokenArr) {
        this._current_writer.setTypeAnnotationSymbols(symbolTokenArr);
    }

    @Override // com.amazon.ion.IonWriter
    public void setTypeAnnotations(String... strArr) {
        this._current_writer.setTypeAnnotations(strArr);
    }

    @Override // com.amazon.ion.IonWriter
    public void stepIn(IonType ionType) throws IOException {
        if (ionType == IonType.STRUCT && this._current_writer.getDepth() == 0 && findAnnotation(SystemSymbols.ION_SYMBOL_TABLE) == 0) {
            open_local_symbol_table_copy();
        } else {
            this._current_writer.stepIn(ionType);
        }
    }

    @Override // com.amazon.ion.IonWriter
    public void stepOut() throws IOException {
        if (symbol_table_being_collected() && this._current_writer.getDepth() == 1) {
            close_local_symbol_table_copy();
        } else {
            this._current_writer.stepOut();
        }
    }

    public final boolean symbol_table_being_collected() {
        return this._current_writer != this._system_writer;
    }

    @Override // com.amazon.ion.IonWriter
    public void writeBlob(byte[] bArr, int i, int i2) throws IOException {
        this._current_writer.writeBlob(bArr, i, i2);
    }

    @Override // com.amazon.ion.IonWriter
    public void writeBool(boolean z) throws IOException {
        this._current_writer.writeBool(z);
    }

    @Override // com.amazon.ion.IonWriter
    public void writeClob(byte[] bArr, int i, int i2) throws IOException {
        this._current_writer.writeClob(bArr, i, i2);
    }

    @Override // com.amazon.ion.impl._Private_IonWriterBase, com.amazon.ion.IonWriter
    public void writeDecimal(BigDecimal bigDecimal) throws IOException {
        this._current_writer.writeDecimal(bigDecimal);
    }

    @Override // com.amazon.ion.IonWriter
    public void writeFloat(double d) throws IOException {
        this._current_writer.writeFloat(d);
    }

    public void writeInt(int i) throws IOException {
        this._current_writer.writeInt(i);
    }

    public final void writeIonVersionMarker(SymbolTable symbolTable) throws IOException {
        this._current_writer.writeIonVersionMarker(symbolTable);
    }

    @Override // com.amazon.ion.IonWriter
    public void writeNull(IonType ionType) throws IOException {
        this._current_writer.writeNull(ionType);
    }

    @Override // com.amazon.ion.IonWriter
    public void writeString(String str) throws IOException {
        this._current_writer.writeString(str);
    }

    @Override // com.amazon.ion.impl._Private_IonWriterBase
    public final void writeSymbol(int i) throws IOException {
        this._current_writer.writeSymbol(i);
    }

    @Override // com.amazon.ion.IonWriter
    public void writeTimestamp(Timestamp timestamp) throws IOException {
        this._current_writer.writeTimestamp(timestamp);
    }

    @Override // com.amazon.ion.IonWriter
    public void writeInt(long j) throws IOException {
        this._current_writer.writeInt(j);
    }

    @Override // com.amazon.ion.impl._Private_IonWriterBase
    public final void writeIonVersionMarker() throws IOException {
        this._current_writer.writeIonVersionMarker();
    }

    @Override // com.amazon.ion.IonWriter
    public final void writeSymbol(String str) throws IOException {
        this._current_writer.writeSymbol(str);
    }

    @Override // com.amazon.ion.IonWriter
    public void writeInt(BigInteger bigInteger) throws IOException {
        this._current_writer.writeInt(bigInteger);
    }

    public IonWriterUser(IonCatalog ionCatalog, ValueFactory valueFactory, IonWriterSystem ionWriterSystem, SymbolTable symbolTable) {
        this(ionCatalog, valueFactory, ionWriterSystem);
        SymbolTable symbolTable2 = ionWriterSystem._default_system_symbol_table;
        if (symbolTable.isLocalTable() || symbolTable != symbolTable2) {
            try {
                setSymbolTable(symbolTable);
            } catch (IOException e) {
                throw new IonException(e.getMessage(), e);
            }
        }
    }
}
