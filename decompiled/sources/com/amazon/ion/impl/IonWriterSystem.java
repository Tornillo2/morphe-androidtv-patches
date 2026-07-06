package com.amazon.ion.impl;

import com.amazon.ion.IonException;
import com.amazon.ion.IonType;
import com.amazon.ion.SymbolTable;
import com.amazon.ion.SymbolToken;
import com.amazon.ion.SystemSymbols;
import com.amazon.ion.UnknownSymbolException;
import com.amazon.ion.system.IonWriterBuilder;
import java.io.IOException;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public abstract class IonWriterSystem extends _Private_IonWriterBase {
    public static final /* synthetic */ boolean $assertionsDisabled = false;
    public static final int DEFAULT_ANNOTATION_COUNT = 4;
    public int _annotation_count;
    public boolean _anything_written;
    public final SymbolTable _default_system_symbol_table;
    public String _field_name;
    public IonType _field_name_type;
    public IonWriterBuilder.InitialIvmHandling _initial_ivm_handling;
    public final IonWriterBuilder.IvmMinimizing _ivm_minimizing;
    public boolean _previous_value_was_ivm;
    public SymbolTable _symbol_table;
    public int _field_name_sid = -1;
    public SymbolToken[] _annotations = new SymbolToken[4];

    /* JADX INFO: renamed from: com.amazon.ion.impl.IonWriterSystem$1, reason: invalid class name */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static /* synthetic */ class AnonymousClass1 {
        public static final /* synthetic */ int[] $SwitchMap$com$amazon$ion$IonType;

        static {
            int[] iArr = new int[IonType.values().length];
            $SwitchMap$com$amazon$ion$IonType = iArr;
            try {
                iArr[IonType.STRING.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$ion$IonType[IonType.INT.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    public IonWriterSystem(SymbolTable symbolTable, IonWriterBuilder.InitialIvmHandling initialIvmHandling, IonWriterBuilder.IvmMinimizing ivmMinimizing) {
        symbolTable.getClass();
        this._default_system_symbol_table = symbolTable;
        this._symbol_table = symbolTable;
        this._initial_ivm_handling = initialIvmHandling;
        this._ivm_minimizing = ivmMinimizing;
    }

    @Override // com.amazon.ion.IonWriter
    public final void addTypeAnnotation(String str) {
        SymbolToken symbolTokenNewSymbolToken = _Private_Utils.newSymbolToken(this._symbol_table, str);
        ensureAnnotationCapacity(this._annotation_count + 1);
        SymbolToken[] symbolTokenArr = this._annotations;
        int i = this._annotation_count;
        this._annotation_count = i + 1;
        symbolTokenArr[i] = symbolTokenNewSymbolToken;
    }

    public final int add_symbol(String str) throws IOException {
        if (this._symbol_table.isSystemTable()) {
            int iFindSymbol = this._symbol_table.findSymbol(str);
            if (iFindSymbol != -1) {
                return iFindSymbol;
            }
            this._symbol_table = inject_local_symbol_table();
        }
        return this._symbol_table.intern(str).getSid();
    }

    public final int annotationCount() {
        return this._annotation_count;
    }

    @Override // com.amazon.ion.impl._Private_IonWriterBase, com.amazon.ion.facet.Faceted
    public <T> T asFacet(Class<T> cls) {
        return null;
    }

    public final SymbolToken assumeFieldNameSymbol() {
        if (this._field_name_type != null) {
            return new SymbolTokenImpl(this._field_name, this._field_name_sid);
        }
        throw new IllegalStateException(_Private_IonWriterBase.ERROR_MISSING_FIELD_NAME);
    }

    @Override // com.amazon.ion.impl._Private_IonWriterBase
    public final String assumeKnownSymbol(int i) {
        String strFindKnownSymbol = this._symbol_table.findKnownSymbol(i);
        if (strFindKnownSymbol != null) {
            return strFindKnownSymbol;
        }
        throw new UnknownSymbolException(i);
    }

    public final void clearAnnotations() {
        this._annotation_count = 0;
    }

    public final void clearFieldName() {
        this._field_name_type = null;
        this._field_name = null;
        this._field_name_sid = -1;
    }

    public void endValue() {
        this._initial_ivm_handling = null;
        this._previous_value_was_ivm = false;
        this._anything_written = true;
    }

    public final void ensureAnnotationCapacity(int i) {
        SymbolToken[] symbolTokenArr = this._annotations;
        int length = symbolTokenArr == null ? 0 : symbolTokenArr.length;
        if (i < length) {
            return;
        }
        int length2 = symbolTokenArr == null ? 10 : symbolTokenArr.length * 2;
        if (i <= length2) {
            i = length2;
        }
        SymbolToken[] symbolTokenArr2 = new SymbolToken[i];
        if (length > 0) {
            System.arraycopy(symbolTokenArr, 0, symbolTokenArr2, 0, length);
        }
        this._annotations = symbolTokenArr2;
    }

    @Override // com.amazon.ion.impl._Private_IonWriterBase
    public final int findAnnotation(String str) {
        if (this._annotation_count <= 0) {
            return -1;
        }
        for (int i = 0; i < this._annotation_count; i++) {
            if (str.equals(this._annotations[i].getText())) {
                return i;
            }
        }
        return -1;
    }

    @Override // com.amazon.ion.IonWriter
    public void finish() throws IOException {
        if (getDepth() != 0) {
            throw new IllegalStateException(_Private_IonWriterBase.ERROR_FINISH_NOT_AT_TOP_LEVEL);
        }
        flush();
        this._previous_value_was_ivm = false;
        this._initial_ivm_handling = IonWriterBuilder.InitialIvmHandling.ENSURE;
        this._symbol_table = this._default_system_symbol_table;
    }

    public final SymbolTable getDefaultSystemSymtab() {
        return this._default_system_symbol_table;
    }

    public final int getFieldId() {
        IonType ionType = this._field_name_type;
        if (ionType == null) {
            throw new IllegalStateException("the field has not be set");
        }
        int i = AnonymousClass1.$SwitchMap$com$amazon$ion$IonType[ionType.ordinal()];
        if (i != 1) {
            if (i == 2) {
                return this._field_name_sid;
            }
            throw new IllegalStateException("the field has not be set");
        }
        try {
            return add_symbol(this._field_name);
        } catch (IOException e) {
            throw new IonException(e.getMessage(), e);
        }
    }

    @Override // com.amazon.ion.IonWriter
    public final SymbolTable getSymbolTable() {
        return this._symbol_table;
    }

    @Override // com.amazon.ion.impl._Private_IonWriterBase
    public final int[] getTypeAnnotationIds() {
        return _Private_Utils.toSids(this._annotations, this._annotation_count);
    }

    public final SymbolToken[] getTypeAnnotationSymbols() {
        int i = this._annotation_count;
        if (i == 0) {
            return SymbolToken.EMPTY_ARRAY;
        }
        SymbolToken[] symbolTokenArr = new SymbolToken[i];
        System.arraycopy(this._annotations, 0, symbolTokenArr, 0, i);
        return symbolTokenArr;
    }

    @Override // com.amazon.ion.impl._Private_IonWriterBase
    public final String[] getTypeAnnotations() {
        return _Private_Utils.toStrings(this._annotations, this._annotation_count);
    }

    public final boolean hasAnnotations() {
        return this._annotation_count != 0;
    }

    public SymbolTable inject_local_symbol_table() throws IOException {
        return LocalSymbolTable.DEFAULT_LST_FACTORY.newLocalSymtab(this._symbol_table, new SymbolTable[0]);
    }

    public final int[] internAnnotationsAndGetSids() throws IOException {
        int i = this._annotation_count;
        if (i == 0) {
            return _Private_Utils.EMPTY_INT_ARRAY;
        }
        int[] iArr = new int[i];
        for (int i2 = 0; i2 < i; i2++) {
            SymbolToken symbolToken = this._annotations[i2];
            int sid = symbolToken.getSid();
            if (sid == -1) {
                String text = symbolToken.getText();
                sid = add_symbol(text);
                this._annotations[i2] = new SymbolTokenImpl(text, sid);
            }
            iArr[i2] = sid;
        }
        return iArr;
    }

    @Override // com.amazon.ion.impl._Private_IonWriterBase
    public final boolean isFieldNameSet() {
        IonType ionType = this._field_name_type;
        if (ionType != null) {
            int i = AnonymousClass1.$SwitchMap$com$amazon$ion$IonType[ionType.ordinal()];
            if (i != 1) {
                return i == 2 && this._field_name_sid >= 0;
            }
            if (this._field_name != null) {
                return true;
            }
        }
        return false;
    }

    @Override // com.amazon.ion.IonWriter
    public final void setFieldName(String str) {
        if (!isInStruct()) {
            throw new IllegalStateException();
        }
        str.getClass();
        this._field_name_type = IonType.STRING;
        this._field_name = str;
        this._field_name_sid = -1;
    }

    @Override // com.amazon.ion.IonWriter
    public final void setFieldNameSymbol(SymbolToken symbolToken) {
        if (!isInStruct()) {
            throw new IllegalStateException();
        }
        String text = symbolToken.getText();
        if (text != null) {
            this._field_name_type = IonType.STRING;
            this._field_name = text;
            this._field_name_sid = -1;
            return;
        }
        int sid = symbolToken.getSid();
        if (sid < 0) {
            throw new IllegalArgumentException();
        }
        validateSymbolId(sid);
        this._field_name_type = IonType.INT;
        this._field_name_sid = sid;
        this._field_name = null;
    }

    @Override // com.amazon.ion.impl._Private_IonWriterBase
    public final void setSymbolTable(SymbolTable symbolTable) throws IOException {
        if (symbolTable == null || _Private_Utils.symtabIsSharedNotSystem(symbolTable)) {
            throw new IllegalArgumentException("symbol table must be local or system to be set, or reset");
        }
        if (getDepth() > 0) {
            throw new IllegalStateException("the symbol table cannot be set, or reset, while a container is open");
        }
        this._symbol_table = symbolTable;
    }

    @Override // com.amazon.ion.IonWriter
    public final void setTypeAnnotationSymbols(SymbolToken... symbolTokenArr) {
        if (symbolTokenArr == null || symbolTokenArr.length == 0) {
            this._annotation_count = 0;
            return;
        }
        int length = symbolTokenArr.length;
        ensureAnnotationCapacity(length);
        SymbolTable symbolTable = this._symbol_table;
        for (int i = 0; i < length; i++) {
            SymbolToken symbolToken = symbolTokenArr[i];
            if (symbolToken.getText() == null) {
                validateSymbolId(symbolToken.getSid());
            }
            this._annotations[i] = _Private_Utils.localize(symbolTable, symbolToken);
        }
        this._annotation_count = length;
    }

    @Override // com.amazon.ion.IonWriter
    public final void setTypeAnnotations(String... strArr) {
        if (strArr == null || strArr.length == 0) {
            this._annotation_count = 0;
            return;
        }
        SymbolToken[] symbolTokenArrNewSymbolTokens = _Private_Utils.newSymbolTokens(this._symbol_table, strArr);
        int length = symbolTokenArrNewSymbolTokens.length;
        ensureAnnotationCapacity(length);
        System.arraycopy(symbolTokenArrNewSymbolTokens, 0, this._annotations, 0, length);
        this._annotation_count = length;
    }

    public boolean shouldWriteIvm() {
        boolean z;
        IonWriterBuilder.InitialIvmHandling initialIvmHandling = this._initial_ivm_handling;
        if (initialIvmHandling == IonWriterBuilder.InitialIvmHandling.ENSURE) {
            return true;
        }
        if (initialIvmHandling == IonWriterBuilder.InitialIvmHandling.SUPPRESS) {
            return false;
        }
        IonWriterBuilder.IvmMinimizing ivmMinimizing = this._ivm_minimizing;
        if (ivmMinimizing == IonWriterBuilder.IvmMinimizing.ADJACENT) {
            z = this._previous_value_was_ivm;
        } else {
            if (ivmMinimizing != IonWriterBuilder.IvmMinimizing.DISTANT) {
                return true;
            }
            z = this._anything_written;
        }
        return !z;
    }

    public void startValue() throws IOException {
        if (this._initial_ivm_handling == IonWriterBuilder.InitialIvmHandling.ENSURE) {
            writeIonVersionMarker(this._default_system_symbol_table);
        }
    }

    public final void writeIonVersionMarker(SymbolTable symbolTable) throws IOException {
        if (getDepth() != 0) {
            throw new IllegalStateException("Ion Version Markers are only valid at the top level of a data stream");
        }
        if (!SystemSymbols.ION_1_0.equals(symbolTable.getIonVersionId())) {
            throw new UnsupportedOperationException("This library only supports Ion 1.0");
        }
        if (shouldWriteIvm()) {
            this._initial_ivm_handling = null;
            writeIonVersionMarkerAsIs(symbolTable);
            this._previous_value_was_ivm = true;
        }
        this._symbol_table = symbolTable;
    }

    public abstract void writeIonVersionMarkerAsIs(SymbolTable symbolTable) throws IOException;

    public void writeLocalSymtab(SymbolTable symbolTable) throws IOException {
        this._symbol_table = symbolTable;
    }

    @Override // com.amazon.ion.impl._Private_IonWriterBase
    public final void writeSymbol(int i) throws IOException {
        if (i < 0) {
            throw new IllegalArgumentException("symbol IDs are >= 0.");
        }
        if (i == 2 && getDepth() == 0 && this._annotation_count == 0) {
            writeIonVersionMarker();
        } else {
            writeSymbolAsIs(i);
        }
    }

    public abstract void writeSymbolAsIs(int i) throws IOException;

    public abstract void writeSymbolAsIs(String str) throws IOException;

    @Override // com.amazon.ion.IonWriter
    public final void writeSymbol(String str) throws IOException {
        if (SystemSymbols.ION_1_0.equals(str) && getDepth() == 0 && this._annotation_count == 0) {
            writeIonVersionMarker();
        } else {
            writeSymbolAsIs(str);
        }
    }

    @Override // com.amazon.ion.impl._Private_IonWriterBase
    public final void writeIonVersionMarker() throws IOException {
        writeIonVersionMarker(this._default_system_symbol_table);
    }
}
