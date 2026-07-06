package com.amazon.ion.impl;

import androidx.collection.ObjectListKt$$ExternalSyntheticOutline1;
import androidx.core.app.ActivityCompat$$ExternalSyntheticOutline0;
import com.amazon.ion.Decimal;
import com.amazon.ion.IntegerSize;
import com.amazon.ion.IonException;
import com.amazon.ion.IonReader;
import com.amazon.ion.IonType;
import com.amazon.ion.SymbolTable;
import com.amazon.ion.SymbolToken;
import com.amazon.ion.SystemSymbols;
import com.amazon.ion.Timestamp;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public final class SymbolTableReader implements IonReader {
    public static final /* synthetic */ boolean $assertionsDisabled = false;
    public static final int HAS_IMPORT_LIST = 8;
    public static final int HAS_MAX_ID = 4;
    public static final int HAS_NAME = 1;
    public static final int HAS_SYMBOL_LIST = 16;
    public static final int HAS_VERSION = 2;
    public static final int S_AFTER_IMPORT_LIST = 15;
    public static final int S_BOF = 0;
    public static final int S_EOF = 21;
    public static final int S_IMPORT_LIST = 6;
    public static final int S_IMPORT_LIST_CLOSE = 14;
    public static final int S_IMPORT_MAX_ID = 12;
    public static final int S_IMPORT_NAME = 10;
    public static final int S_IMPORT_STRUCT = 8;
    public static final int S_IMPORT_STRUCT_CLOSE = 13;
    public static final int S_IMPORT_VERSION = 11;
    public static final int S_IN_IMPORTS = 7;
    public static final int S_IN_IMPORT_STRUCT = 9;
    public static final int S_IN_STRUCT = 2;
    public static final int S_IN_SYMBOLS = 17;
    public static final int S_MAX_ID = 5;
    public static final int S_NAME = 3;
    public static final int S_STRUCT = 1;
    public static final int S_STRUCT_CLOSE = 20;
    public static final int S_SYMBOL = 18;
    public static final int S_SYMBOL_LIST = 16;
    public static final int S_SYMBOL_LIST_CLOSE = 19;
    public static final int S_VERSION = 4;
    public SymbolTable _current_import;
    public int _current_state = 0;
    public int _flags;
    public Iterator<SymbolTable> _import_iterator;
    public SymbolTable[] _imported_tables;
    public long _int_value;
    public Iterator<String> _local_symbols;
    public final int _maxId;
    public String _string_value;
    public final SymbolTable _symbol_table;
    public static final SymbolToken ION_SYMBOL_TABLE_SYM = _Private_Utils.newSymbolToken(SystemSymbols.ION_SYMBOL_TABLE, 3);
    public static final SymbolToken ION_SHARED_SYMBOL_TABLE_SYM = new SymbolTokenImpl(SystemSymbols.ION_SHARED_SYMBOL_TABLE, 9);

    /* JADX INFO: renamed from: com.amazon.ion.impl.SymbolTableReader$1, reason: invalid class name */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static /* synthetic */ class AnonymousClass1 {
        public static final /* synthetic */ int[] $SwitchMap$com$amazon$ion$impl$SymbolTableReader$Op;

        static {
            int[] iArr = new int[Op.values().length];
            $SwitchMap$com$amazon$ion$impl$SymbolTableReader$Op = iArr;
            try {
                iArr[Op.NEXT.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$ion$impl$SymbolTableReader$Op[Op.STEPOUT.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public enum Op {
        NEXT,
        STEPOUT
    }

    public SymbolTableReader(SymbolTable symbolTable) {
        int maxId;
        this._symbol_table = symbolTable;
        synchronized (symbolTable) {
            maxId = symbolTable.getMaxId();
            this._maxId = maxId;
            this._local_symbols = symbolTable.iterateDeclaredSymbolNames();
        }
        if (!symbolTable.isLocalTable()) {
            set_flag(1, true);
            set_flag(2, true);
        }
        SymbolTable[] importedTables = symbolTable.getImportedTables();
        this._imported_tables = importedTables;
        if (importedTables != null && importedTables.length != 0) {
            set_flag(8, true);
        }
        if (symbolTable.getImportedMaxId() < maxId) {
            set_flag(16, true);
        }
    }

    private static final String get_state_name(int i) {
        switch (i) {
            case 0:
                return "S_BOF";
            case 1:
                return "S_STRUCT";
            case 2:
                return "S_IN_STRUCT";
            case 3:
                return "S_NAME";
            case 4:
                return "S_VERSION";
            case 5:
                return "S_MAX_ID";
            case 6:
                return "S_IMPORT_LIST";
            case 7:
                return "S_IN_IMPORTS";
            case 8:
                return "S_IMPORT_STRUCT";
            case 9:
                return "S_IN_IMPORT_STRUCT";
            case 10:
                return "S_IMPORT_NAME";
            case 11:
                return "S_IMPORT_VERSION";
            case 12:
                return "S_IMPORT_MAX_ID";
            case 13:
                return "S_IMPORT_STRUCT_CLOSE";
            case 14:
                return "S_IMPORT_LIST_CLOSE";
            case 15:
                return "S_AFTER_IMPORT_LIST";
            case 16:
                return "S_SYMBOL_LIST";
            case 17:
                return "S_IN_SYMBOLS";
            case 18:
                return "S_SYMBOL";
            case 19:
                return "S_SYMBOL_LIST_CLOSE";
            case 20:
                return "S_STRUCT_CLOSE";
            case 21:
                return "S_EOF";
            default:
                return ObjectListKt$$ExternalSyntheticOutline1.m("<Unrecognized state: ", i, ">");
        }
    }

    public static final int stateDepth(int i) {
        switch (i) {
            case 0:
            case 1:
                return 0;
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
                return 1;
            case 7:
            case 8:
                return 2;
            case 9:
            case 10:
            case 11:
            case 12:
            case 13:
                return 3;
            case 14:
                return 2;
            case 15:
            case 16:
                return 1;
            case 17:
            case 18:
            case 19:
                return 2;
            case 20:
                return 1;
            case 21:
                return 0;
            default:
                throwUnrecognizedState(i);
                throw null;
        }
    }

    public static final IonType stateType(int i) {
        switch (i) {
            case 0:
                return null;
            case 1:
                return IonType.STRUCT;
            case 2:
                return null;
            case 3:
                return IonType.STRING;
            case 4:
                return IonType.INT;
            case 5:
                return IonType.INT;
            case 6:
                return IonType.LIST;
            case 7:
                return null;
            case 8:
                return IonType.STRUCT;
            case 9:
                return null;
            case 10:
                return IonType.STRING;
            case 11:
                return IonType.INT;
            case 12:
                return IonType.INT;
            case 13:
            case 14:
            case 15:
                return null;
            case 16:
                return IonType.LIST;
            case 17:
                return null;
            case 18:
                return IonType.STRING;
            case 19:
            case 20:
            case 21:
                return null;
            default:
                throwUnrecognizedState(i);
                throw null;
        }
    }

    public static final void throwUnrecognizedState(int i) {
        throw new IonException("Internal error: UnifiedSymbolTableReader is in an unrecognized state: " + get_state_name(i));
    }

    @Override // com.amazon.ion.facet.Faceted
    public <T> T asFacet(Class<T> cls) {
        return null;
    }

    @Override // com.amazon.ion.IonReader
    public BigDecimal bigDecimalValue() {
        throw new IllegalStateException("only valid if the value is a decimal");
    }

    @Override // com.amazon.ion.IonReader
    public BigInteger bigIntegerValue() {
        return new BigInteger(Long.toString(this._int_value));
    }

    @Override // com.amazon.ion.IonReader
    public boolean booleanValue() {
        throw new IllegalStateException("only valid if the value is a boolean");
    }

    @Override // com.amazon.ion.IonReader
    public int byteSize() {
        throw new IllegalStateException("byteSize() is only valid if the reader is on a lob value, not a " + stateType(this._current_state) + " value");
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this._current_state = 21;
    }

    @Override // com.amazon.ion.IonReader
    public Date dateValue() {
        throw new IllegalStateException("only valid if the value is a timestamp");
    }

    @Override // com.amazon.ion.IonReader
    public Decimal decimalValue() {
        throw new IllegalStateException("only valid if the value is a decimal");
    }

    @Override // com.amazon.ion.IonReader
    public double doubleValue() {
        throw new IllegalStateException("only valid if the value is a double");
    }

    @Override // com.amazon.ion.IonReader
    public int getBytes(byte[] bArr, int i, int i2) {
        throw new IllegalStateException("getBytes() is only valid if the reader is on a lob value, not a " + stateType(this._current_state) + " value");
    }

    @Override // com.amazon.ion.IonReader
    public int getDepth() {
        return stateDepth(this._current_state);
    }

    @Override // com.amazon.ion.IonReader
    public int getFieldId() {
        switch (this._current_state) {
            case 1:
            case 2:
            case 7:
            case 8:
            case 9:
            case 13:
            case 14:
            case 15:
            case 17:
            case 18:
            case 19:
            case 20:
            case 21:
                return -1;
            case 3:
            case 10:
                return 4;
            case 4:
            case 11:
                return 5;
            case 5:
            case 12:
                return 8;
            case 6:
                return 6;
            case 16:
                return 7;
            default:
                throw new IonException("Internal error: UnifiedSymbolTableReader is in an unrecognized state: " + this._current_state);
        }
    }

    @Override // com.amazon.ion.IonReader
    public String getFieldName() {
        switch (this._current_state) {
            case 1:
            case 2:
            case 7:
            case 8:
            case 9:
            case 13:
            case 14:
            case 15:
            case 17:
            case 18:
            case 19:
            case 20:
            case 21:
                return null;
            case 3:
            case 10:
                return "name";
            case 4:
            case 11:
                return "version";
            case 5:
            case 12:
                return SystemSymbols.MAX_ID;
            case 6:
                return SystemSymbols.IMPORTS;
            case 16:
                return SystemSymbols.SYMBOLS;
            default:
                throw new IonException("Internal error: UnifiedSymbolTableReader is in an unrecognized state: " + this._current_state);
        }
    }

    @Override // com.amazon.ion.IonReader
    public SymbolToken getFieldNameSymbol() {
        switch (this._current_state) {
            case 1:
            case 2:
            case 7:
            case 8:
            case 9:
            case 13:
            case 14:
            case 15:
            case 17:
            case 18:
            case 19:
            case 20:
            case 21:
                return null;
            case 3:
            case 10:
                return new SymbolTokenImpl("name", 4);
            case 4:
            case 11:
                return new SymbolTokenImpl("version", 5);
            case 5:
            case 12:
                return new SymbolTokenImpl(SystemSymbols.MAX_ID, 8);
            case 6:
                return new SymbolTokenImpl(SystemSymbols.IMPORTS, 6);
            case 16:
                return new SymbolTokenImpl(SystemSymbols.SYMBOLS, 7);
            default:
                throw new IonException("Internal error: UnifiedSymbolTableReader is in an unrecognized state: " + this._current_state);
        }
    }

    @Override // com.amazon.ion.IonReader
    public IntegerSize getIntegerSize() {
        if (stateType(this._current_state) != IonType.INT) {
            return null;
        }
        return IntegerSize.INT;
    }

    @Override // com.amazon.ion.IonReader
    public SymbolTable getSymbolTable() {
        return null;
    }

    @Override // com.amazon.ion.IonReader
    public IonType getType() {
        return stateType(this._current_state);
    }

    @Override // com.amazon.ion.IonReader
    public SymbolToken[] getTypeAnnotationSymbols() {
        if (this._current_state == 1) {
            return new SymbolToken[]{(this._symbol_table.isLocalTable() || this._symbol_table.isSystemTable()) ? ION_SYMBOL_TABLE_SYM : ION_SHARED_SYMBOL_TABLE_SYM};
        }
        return SymbolToken.EMPTY_ARRAY;
    }

    @Override // com.amazon.ion.IonReader
    public String[] getTypeAnnotations() {
        return this._current_state == 1 ? (this._symbol_table.isLocalTable() || this._symbol_table.isSystemTable()) ? new String[]{SystemSymbols.ION_SYMBOL_TABLE} : new String[]{SystemSymbols.ION_SHARED_SYMBOL_TABLE} : _Private_Utils.EMPTY_STRING_ARRAY;
    }

    public final boolean hasImports() {
        return test_flag(8);
    }

    public final boolean hasLocalSymbols() {
        return test_flag(16);
    }

    public final boolean hasMaxId() {
        return test_flag(4);
    }

    public final boolean hasName() {
        return test_flag(1);
    }

    @Override // com.amazon.ion.IonReader
    public boolean hasNext() {
        return has_next_helper();
    }

    public final boolean hasVersion() {
        return test_flag(2);
    }

    public final boolean has_next_helper() {
        int i = this._current_state;
        switch (i) {
            case 0:
                return true;
            case 2:
                if (stateFirstInStruct() != 20) {
                    return true;
                }
            case 1:
                return false;
            case 3:
                return true;
            case 4:
                return test_flag(4) || stateFollowingMaxId() != 20;
            case 5:
                return stateFollowingMaxId() != 20;
            case 6:
                return test_flag(16);
            case 7:
            case 8:
                return this._import_iterator.hasNext();
            case 9:
            case 10:
            case 11:
                return true;
            case 15:
                if (test_flag(16)) {
                    return true;
                }
            case 12:
            case 13:
            case 14:
                return false;
            case 17:
            case 18:
                if (this._local_symbols.hasNext()) {
                    return true;
                }
            case 16:
                return false;
            case 19:
            case 20:
            case 21:
                return false;
            default:
                throwUnrecognizedState(i);
                throw null;
        }
    }

    @Override // com.amazon.ion.IonReader
    public int intValue() {
        return (int) this._int_value;
    }

    @Override // com.amazon.ion.IonReader
    public boolean isInStruct() {
        switch (this._current_state) {
            case 1:
            case 7:
            case 8:
            case 17:
            case 18:
                return false;
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 9:
            case 10:
            case 11:
            case 12:
            case 13:
            case 15:
            case 16:
            case 20:
                return true;
            case 14:
            case 19:
            case 21:
                return false;
            default:
                throw new IonException("Internal error: UnifiedSymbolTableReader is in an unrecognized state: " + this._current_state);
        }
    }

    @Override // com.amazon.ion.IonReader
    public boolean isNullValue() {
        switch (this._current_state) {
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
            case 12:
            case 13:
            case 14:
            case 15:
            case 16:
            case 17:
            case 18:
            case 19:
            case 20:
            case 21:
                return false;
            default:
                throw new IonException("Internal error: UnifiedSymbolTableReader is in an unrecognized state: " + this._current_state);
        }
    }

    @Override // com.amazon.ion.IonReader
    public Iterator<String> iterateTypeAnnotations() {
        return _Private_Utils.stringIterator(getTypeAnnotations());
    }

    public final void loadStateData(int i) {
        if (i == 3) {
            this._string_value = this._symbol_table.getName();
            return;
        }
        if (i == 4) {
            this._int_value = this._symbol_table.getVersion();
            return;
        }
        if (i == 5) {
            this._int_value = this._maxId;
            return;
        }
        if (i == 6 || i == 16) {
            return;
        }
        switch (i) {
            case 10:
                this._string_value = this._current_import.getName();
                return;
            case 11:
                this._string_value = null;
                this._int_value = this._current_import.getVersion();
                return;
            case 12:
                this._int_value = this._current_import.getMaxId();
                return;
            default:
                throw new IonException(ActivityCompat$$ExternalSyntheticOutline0.m(new StringBuilder("UnifiedSymbolTableReader in state "), get_state_name(i), " has no state to load."));
        }
    }

    @Override // com.amazon.ion.IonReader
    public long longValue() {
        return this._int_value;
    }

    @Override // com.amazon.ion.IonReader
    public byte[] newBytes() {
        throw new IllegalStateException("newBytes() is only valid if the reader is on a lob value, not a " + stateType(this._current_state) + " value");
    }

    @Override // com.amazon.ion.IonReader
    public IonType next() {
        if (!has_next_helper()) {
            return null;
        }
        int i = this._current_state;
        int iStateFirstInStruct = 4;
        switch (i) {
            case 0:
                iStateFirstInStruct = 1;
                this._current_state = iStateFirstInStruct;
                return stateType(iStateFirstInStruct);
            case 1:
            case 21:
                iStateFirstInStruct = 21;
                this._current_state = iStateFirstInStruct;
                return stateType(iStateFirstInStruct);
            case 2:
                iStateFirstInStruct = stateFirstInStruct();
                loadStateData(iStateFirstInStruct);
                this._current_state = iStateFirstInStruct;
                return stateType(iStateFirstInStruct);
            case 3:
                loadStateData(4);
                this._current_state = iStateFirstInStruct;
                return stateType(iStateFirstInStruct);
            case 4:
                if (test_flag(4)) {
                    iStateFirstInStruct = 5;
                    loadStateData(5);
                } else {
                    iStateFirstInStruct = stateFollowingMaxId();
                }
                this._current_state = iStateFirstInStruct;
                return stateType(iStateFirstInStruct);
            case 5:
                iStateFirstInStruct = stateFollowingMaxId();
                this._current_state = iStateFirstInStruct;
                return stateType(iStateFirstInStruct);
            case 6:
                iStateFirstInStruct = stateFollowingImportList(Op.NEXT);
                this._current_state = iStateFirstInStruct;
                return stateType(iStateFirstInStruct);
            case 7:
            case 8:
                iStateFirstInStruct = nextImport();
                this._current_state = iStateFirstInStruct;
                return stateType(iStateFirstInStruct);
            case 9:
                iStateFirstInStruct = 10;
                loadStateData(10);
                this._current_state = iStateFirstInStruct;
                return stateType(iStateFirstInStruct);
            case 10:
                iStateFirstInStruct = 11;
                loadStateData(11);
                this._current_state = iStateFirstInStruct;
                return stateType(iStateFirstInStruct);
            case 11:
                iStateFirstInStruct = 12;
                loadStateData(12);
                this._current_state = iStateFirstInStruct;
                return stateType(iStateFirstInStruct);
            case 12:
            case 13:
                iStateFirstInStruct = 13;
                this._current_state = iStateFirstInStruct;
                return stateType(iStateFirstInStruct);
            case 14:
                iStateFirstInStruct = 14;
                this._current_state = iStateFirstInStruct;
                return stateType(iStateFirstInStruct);
            case 15:
                iStateFirstInStruct = 16;
                this._current_state = iStateFirstInStruct;
                return stateType(iStateFirstInStruct);
            case 16:
            case 20:
                iStateFirstInStruct = 20;
                this._current_state = iStateFirstInStruct;
                return stateType(iStateFirstInStruct);
            case 17:
            case 18:
                if (this._local_symbols.hasNext()) {
                    this._string_value = this._local_symbols.next();
                    iStateFirstInStruct = 18;
                }
                this._current_state = iStateFirstInStruct;
                return stateType(iStateFirstInStruct);
            case 19:
                iStateFirstInStruct = 19;
                this._current_state = iStateFirstInStruct;
                return stateType(iStateFirstInStruct);
            default:
                throwUnrecognizedState(i);
                throw null;
        }
    }

    public final int nextImport() {
        if (this._import_iterator.hasNext()) {
            this._current_import = this._import_iterator.next();
            return 8;
        }
        this._current_import = null;
        return 14;
    }

    public final void set_flag(int i, boolean z) {
        if (z) {
            this._flags = i | this._flags;
        } else {
            this._flags = (~i) & this._flags;
        }
    }

    public final int stateFirstInStruct() {
        if (test_flag(1)) {
            return 3;
        }
        if (test_flag(4)) {
            return 5;
        }
        if (test_flag(8)) {
            return 6;
        }
        return test_flag(16) ? 16 : 20;
    }

    public final int stateFollowingImportList(Op op) {
        if (!test_flag(16)) {
            return 20;
        }
        int i = AnonymousClass1.$SwitchMap$com$amazon$ion$impl$SymbolTableReader$Op[op.ordinal()];
        if (i != 1) {
            return i != 2 ? -1 : 15;
        }
        return 16;
    }

    public final int stateFollowingLocalSymbols() {
        return 20;
    }

    public final int stateFollowingMaxId() {
        if (test_flag(8)) {
            return 6;
        }
        return test_flag(16) ? 16 : 20;
    }

    @Override // com.amazon.ion.IonReader
    public void stepIn() {
        int i;
        int i2 = this._current_state;
        if (i2 == 1) {
            i = 2;
        } else if (i2 == 6) {
            this._import_iterator = Arrays.asList(this._imported_tables).iterator();
            i = 7;
        } else if (i2 == 8) {
            i = 9;
        } else {
            if (i2 != 16) {
                throw new IllegalStateException("current value is not a container");
            }
            i = 17;
        }
        this._current_state = i;
    }

    @Override // com.amazon.ion.IonReader
    public void stepOut() {
        int iStateFollowingImportList;
        switch (this._current_state) {
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 15:
            case 16:
            case 20:
                iStateFollowingImportList = 21;
                break;
            case 7:
            case 8:
            case 14:
                this._current_import = null;
                this._import_iterator = null;
                iStateFollowingImportList = stateFollowingImportList(Op.STEPOUT);
                break;
            case 9:
            case 10:
            case 11:
            case 12:
            case 13:
                iStateFollowingImportList = !this._import_iterator.hasNext() ? 14 : 8;
                break;
            case 17:
            case 18:
            case 19:
                this._string_value = null;
                this._local_symbols = null;
                iStateFollowingImportList = 20;
                break;
            default:
                throw new IllegalStateException("current value is not in a container");
        }
        this._current_state = iStateFollowingImportList;
    }

    @Override // com.amazon.ion.IonReader
    public String stringValue() {
        return this._string_value;
    }

    @Override // com.amazon.ion.IonReader
    public SymbolToken symbolValue() {
        throw new UnsupportedOperationException();
    }

    public final boolean test_flag(int i) {
        return (i & this._flags) != 0;
    }

    @Override // com.amazon.ion.IonReader
    public Timestamp timestampValue() {
        throw new IllegalStateException("only valid if the value is a timestamp");
    }
}
