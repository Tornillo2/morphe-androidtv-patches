package com.amazon.ion.impl;

import androidx.collection.ObjectListKt$$ExternalSyntheticOutline1;
import com.amazon.ion.IonException;
import com.amazon.ion.IonType;
import com.amazon.ion.SymbolTable;
import com.amazon.ion.Timestamp;
import com.amazon.ion.impl.BlockedBuffer;
import com.amazon.ion.impl.IonBinary;
import com.amazon.ion.impl.lite._Private_LiteDomTrampoline;
import com.amazon.ion.system.IonWriterBuilder;
import com.google.common.collect.ObjectCountHashMap;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.LinkedList;
import java.util.Queue;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public final class IonWriterSystemBinary extends IonWriterSystem implements _Private_ListWriter {
    public static final /* synthetic */ boolean $assertionsDisabled = false;
    public static final int DEFAULT_PATCH_COUNT = 10;
    public static final int DEFAULT_PATCH_DEPTH = 10;
    public static final int NOT_A_SYMBOL_TABLE_IDX = -1;
    public static final int TID_ANNOTATION_PATCH = 17;
    public static final int TID_FOR_SYMBOL_TABLE_PATCH = 17;
    public static final int TID_RAW = 19;
    public static final int TID_SYMBOL_TABLE_PATCH = 18;
    public static final int UNKNOWN_LENGTH = -1;
    public final boolean _auto_flush;
    public boolean _closed;
    public boolean _in_struct;
    public IonBinary.BufferManager _manager;
    public PatchedValues _patch;
    public int _patch_count;
    public boolean[] _patch_in_struct;
    public int[] _patch_lengths;
    public int[] _patch_offsets;
    public int[] _patch_stack;
    public int _patch_symbol_table_count;
    public SymbolTable[] _patch_symbol_tables;
    public int[] _patch_table_idx;
    public int[] _patch_types;
    public int _top;
    public int _user_depth;
    public final OutputStream _user_output_stream;
    public IonBinary.Writer _writer;

    /* JADX INFO: renamed from: com.amazon.ion.impl.IonWriterSystemBinary$1, reason: invalid class name */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static /* synthetic */ class AnonymousClass1 {
        public static final /* synthetic */ int[] $SwitchMap$com$amazon$ion$IonType;

        static {
            int[] iArr = new int[IonType.values().length];
            $SwitchMap$com$amazon$ion$IonType = iArr;
            try {
                iArr[IonType.LIST.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$ion$IonType[IonType.SEXP.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$ion$IonType[IonType.STRUCT.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$amazon$ion$IonType[IonType.NULL.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$amazon$ion$IonType[IonType.BOOL.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$amazon$ion$IonType[IonType.INT.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$amazon$ion$IonType[IonType.FLOAT.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$com$amazon$ion$IonType[IonType.DECIMAL.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                $SwitchMap$com$amazon$ion$IonType[IonType.TIMESTAMP.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                $SwitchMap$com$amazon$ion$IonType[IonType.SYMBOL.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                $SwitchMap$com$amazon$ion$IonType[IonType.STRING.ordinal()] = 11;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                $SwitchMap$com$amazon$ion$IonType[IonType.BLOB.ordinal()] = 12;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                $SwitchMap$com$amazon$ion$IonType[IonType.CLOB.ordinal()] = 13;
            } catch (NoSuchFieldError unused13) {
            }
        }
    }

    public IonWriterSystemBinary(SymbolTable symbolTable, OutputStream outputStream, boolean z, boolean z2) {
        super(symbolTable, z2 ? IonWriterBuilder.InitialIvmHandling.ENSURE : null, IonWriterBuilder.IvmMinimizing.ADJACENT);
        this._patch = new PatchedValues();
        this._patch_count = 0;
        this._patch_lengths = new int[10];
        this._patch_offsets = new int[10];
        this._patch_table_idx = new int[10];
        this._patch_types = new int[10];
        this._patch_in_struct = new boolean[10];
        this._patch_symbol_table_count = 0;
        this._patch_symbol_tables = new SymbolTable[10];
        this._patch_stack = new int[10];
        outputStream.getClass();
        this._user_output_stream = outputStream;
        IonBinary.BufferManager bufferManager = new IonBinary.BufferManager();
        this._manager = bufferManager;
        this._writer = bufferManager.openWriter();
        this._auto_flush = z;
    }

    private final void closeValue() throws IOException {
        endValue();
        this._patch.endPatch();
        if (this._patch.getParent() == null || this._patch.getParent().getType() != 14) {
            return;
        }
        PatchedValues parent = this._patch.getParent();
        this._patch = parent;
        parent.endPatch();
    }

    private final int topType() {
        int i = this._top;
        if (i == 0) {
            return 16;
        }
        return this._patch_types[this._patch_stack[i - 1]];
    }

    public int XXX_get_pending_length_with_no_symbol_tables() {
        int i = this._manager.buffer()._buf_limit;
        int iLenVarUInt = 0;
        for (int i2 = 0; i2 < this._patch_count; i2++) {
            int i3 = this._patch_lengths[i2];
            if (i3 >= 14) {
                iLenVarUInt = IonBinary.lenVarUInt(i3) + iLenVarUInt;
            }
        }
        return i + iLenVarUInt;
    }

    public final boolean atDatagramLevel() {
        return topType() == 16;
    }

    @Override // com.amazon.ion.IonWriter, java.io.Closeable, java.lang.AutoCloseable
    public final void close() throws IOException {
        if (this._closed) {
            return;
        }
        try {
            if (this._user_depth == 0) {
                finish();
            }
        } finally {
            this._closed = true;
            this._user_output_stream.close();
        }
    }

    @Override // com.amazon.ion.impl.IonWriterSystem, com.amazon.ion.IonWriter
    public void finish() throws IOException {
        if (this._user_depth != 0) {
            throw new IllegalStateException(_Private_IonWriterBase.ERROR_FINISH_NOT_AT_TOP_LEVEL);
        }
        writeAllBufferedData();
        super.finish();
    }

    @Override // com.amazon.ion.IonWriter, java.io.Flushable
    public final void flush() throws IOException {
        SymbolTable symbolTable;
        if (this._closed) {
            return;
        }
        if (atDatagramLevel() && !hasAnnotations() && (symbolTable = this._symbol_table) != null && symbolTable.isReadOnly() && symbolTable.isLocalTable()) {
            writeAllBufferedData();
        }
        this._user_output_stream.flush();
    }

    public final IonType getContainer() {
        int iParentType = parentType();
        if (iParentType == 16) {
            return IonType.DATAGRAM;
        }
        switch (iParentType) {
            case 11:
                return IonType.LIST;
            case 12:
                return IonType.SEXP;
            case 13:
                return IonType.STRUCT;
            default:
                throw new IonException(ObjectListKt$$ExternalSyntheticOutline1.m("unexpected parent type ", iParentType, " does not represent a container"));
        }
    }

    @Override // com.amazon.ion.impl._Private_IonWriterBase
    public final int getDepth() {
        return this._user_depth;
    }

    public final OutputStream getOutputStream() {
        return this._user_output_stream;
    }

    @Override // com.amazon.ion.impl.IonWriterSystem
    public final SymbolTable inject_local_symbol_table() throws IOException {
        SymbolTable symbolTableInject_local_symbol_table = super.inject_local_symbol_table();
        PatchedValues parent = this._patch;
        while (parent.getParent() != null) {
            parent = parent.getParent();
        }
        startValue();
        parent.injectSymbolTable(symbolTableInject_local_symbol_table, this._patch.getParent() != null);
        endValue();
        return symbolTableInject_local_symbol_table;
    }

    @Override // com.amazon.ion.IonWriter
    public final boolean isInStruct() {
        return this._in_struct;
    }

    public final int parentType() {
        for (int i = this._top - 2; i >= 0; i--) {
            int i2 = this._patch_types[this._patch_stack[i]];
            if (i2 != 14) {
                return i2;
            }
        }
        return 16;
    }

    public final void startValue(int i) throws IOException {
        int[] iArrInternAnnotationsAndGetSids;
        startValue();
        int i2 = this._annotation_count;
        if (i2 > 0) {
            iArrInternAnnotationsAndGetSids = internAnnotationsAndGetSids();
            this._patch.startPatch(14, this._writer._pos);
        } else {
            this._patch.startPatch(i, this._writer._pos);
            iArrInternAnnotationsAndGetSids = null;
        }
        if (this._in_struct) {
            if (!isFieldNameSet()) {
                throw new IllegalStateException(_Private_IonWriterBase.ERROR_MISSING_FIELD_NAME);
            }
            int fieldId = getFieldId();
            if (fieldId < 0) {
                throw new UnsupportedOperationException("symbol resolution must be handled by the user writer");
            }
            this._patch.patchFieldName(this._writer.writeVarUIntValue(fieldId, true));
            clearFieldName();
        }
        if (i2 > 0) {
            PatchedValues patchedValuesAddChild = this._patch.addChild();
            this._patch = patchedValuesAddChild;
            patchedValuesAddChild.startPatch(17, this._writer._pos);
            int iWriteVarUIntValue = 0;
            for (int i3 = 0; i3 < i2; i3++) {
                iWriteVarUIntValue += this._writer.writeVarUIntValue(iArrInternAnnotationsAndGetSids[i3], true);
            }
            this._patch.patchValue(iWriteVarUIntValue);
            this._patch.endPatch();
            this._annotation_count = 0;
            this._patch.startPatch(i, this._writer._pos);
        }
    }

    @Override // com.amazon.ion.IonWriter
    public final void stepIn(IonType ionType) throws IOException {
        int i;
        int i2 = AnonymousClass1.$SwitchMap$com$amazon$ion$IonType[ionType.ordinal()];
        if (i2 == 1) {
            i = 11;
        } else if (i2 == 2) {
            i = 12;
        } else {
            if (i2 != 3) {
                throw new IllegalArgumentException();
            }
            i = 13;
        }
        startValue(i);
        this._patch = this._patch.addChild();
        this._in_struct = i == 13;
        this._user_depth++;
    }

    @Override // com.amazon.ion.IonWriter
    public final void stepOut() throws IOException {
        if (this._patch.getParent() == null) {
            throw new IllegalStateException(IonMessages.CANNOT_STEP_OUT);
        }
        this._patch = this._patch.getParent();
        closeValue();
        if (this._patch.getParent() == null) {
            this._in_struct = false;
            if (this._auto_flush) {
                flush();
            }
        } else {
            this._in_struct = this._patch.getParent().getType() == 13;
        }
        this._user_depth--;
    }

    public final boolean topInStruct() {
        int i = this._top;
        if (i == 0) {
            return false;
        }
        return this._patch_in_struct[this._patch_stack[i - 1]];
    }

    public final int topLength() {
        return this._patch_lengths[this._patch_stack[this._top - 1]];
    }

    public final void writeAllBufferedData() throws IOException {
        writeBytes(this._user_output_stream);
        clearFieldName();
        this._annotation_count = 0;
        this._in_struct = false;
        this._patch_count = 0;
        this._patch_symbol_table_count = 0;
        this._top = 0;
        try {
            this._writer.setPosition(0);
            this._writer.truncate();
        } catch (IOException e) {
            throw new IonException(e.getMessage(), e);
        }
    }

    @Override // com.amazon.ion.IonWriter
    public void writeBlob(byte[] bArr, int i, int i2) throws IOException {
        if (bArr == null) {
            writeNull(IonType.BLOB);
            return;
        }
        if (i < 0 || i2 < 0 || i + i2 > bArr.length) {
            throw new IllegalArgumentException("the start and len must be contained in the byte array");
        }
        startValue(10);
        this._writer.write(bArr, i, i2);
        this._patch.patchValue(i2);
        closeValue();
    }

    @Override // com.amazon.ion.IonWriter
    public void writeBool(boolean z) throws IOException {
        startValue(19);
        this._writer.write((z ? 1 : 0) | 16);
        this._patch.patchValue(1);
        closeValue();
    }

    @Override // com.amazon.ion.impl._Private_ListWriter
    public void writeBoolList(boolean[] zArr) throws IOException {
        stepIn(IonType.LIST);
        for (boolean z : zArr) {
            writeBool(z);
        }
        stepOut();
    }

    public int writeBytes(OutputStream outputStream) throws IOException {
        if (this._patch.getParent() != null) {
            throw new IllegalStateException("Tried to flush while not on top-level");
        }
        try {
            return writeRecursive(new BlockedBuffer.BlockedByteInputStream(0, this._manager.buffer()), outputStream, this._patch);
        } finally {
            this._patch.reset();
        }
    }

    @Override // com.amazon.ion.IonWriter
    public void writeClob(byte[] bArr, int i, int i2) throws IOException {
        if (bArr == null) {
            writeNull(IonType.CLOB);
            return;
        }
        if (i < 0 || i2 < 0 || i + i2 > bArr.length) {
            throw new IllegalArgumentException("the start and len must be contained in the byte array");
        }
        startValue(9);
        this._writer.write(bArr, i, i2);
        this._patch.patchValue(i2);
        closeValue();
    }

    @Override // com.amazon.ion.impl._Private_IonWriterBase, com.amazon.ion.IonWriter
    public void writeDecimal(BigDecimal bigDecimal) throws IOException {
        if (bigDecimal == null) {
            writeNull(IonType.DECIMAL);
            return;
        }
        startValue(5);
        this._patch.patchValue(this._writer.writeDecimalContent(bigDecimal));
        closeValue();
    }

    @Override // com.amazon.ion.IonWriter
    public void writeFloat(double d) throws IOException {
        IonBinary.lenIonFloat(d);
        startValue(4);
        this._patch.patchValue(this._writer.writeFloatValue(d));
        closeValue();
    }

    @Override // com.amazon.ion.impl._Private_ListWriter
    public void writeFloatList(float[] fArr) throws IOException {
        stepIn(IonType.LIST);
        for (float f : fArr) {
            writeFloat(f);
        }
        stepOut();
    }

    @Override // com.amazon.ion.IonWriter
    public void writeInt(long j) throws IOException {
        int iWriteUIntValue;
        if (j < 0) {
            startValue(3);
            iWriteUIntValue = this._writer.writeUIntValue(-j);
        } else {
            startValue(2);
            iWriteUIntValue = this._writer.writeUIntValue(j);
        }
        this._patch.patchValue(iWriteUIntValue);
        closeValue();
    }

    @Override // com.amazon.ion.impl._Private_ListWriter
    public void writeIntList(byte[] bArr) throws IOException {
        stepIn(IonType.LIST);
        for (byte b : bArr) {
            writeInt(b);
        }
        stepOut();
    }

    @Override // com.amazon.ion.impl.IonWriterSystem
    public void writeIonVersionMarkerAsIs(SymbolTable symbolTable) throws IOException {
        if (this._user_depth != 0) {
            throw new IllegalStateException("IVM not on top-level");
        }
        startValue();
        this._patch.startPatch(19, this._writer._pos);
        this._patch.patchValue(4);
        this._writer.write(_Private_IonConstants.BINARY_VERSION_MARKER_1_0);
        this._patch.endPatch();
        endValue();
    }

    @Override // com.amazon.ion.impl.IonWriterSystem
    public void writeLocalSymtab(SymbolTable symbolTable) throws IOException {
        PatchedValues parent = this._patch;
        while (parent.getParent() != null) {
            parent = parent.getParent();
        }
        startValue();
        parent.injectSymbolTable(symbolTable, this._patch.getParent() != null);
        endValue();
        this._symbol_table = symbolTable;
    }

    @Override // com.amazon.ion.IonWriter
    public void writeNull(IonType ionType) throws IOException {
        int i;
        switch (AnonymousClass1.$SwitchMap$com$amazon$ion$IonType[ionType.ordinal()]) {
            case 1:
                i = 11;
                break;
            case 2:
                i = 12;
                break;
            case 3:
                i = 13;
                break;
            case 4:
                i = 0;
                break;
            case 5:
                i = 1;
                break;
            case 6:
                i = 2;
                break;
            case 7:
                i = 4;
                break;
            case 8:
                i = 5;
                break;
            case 9:
                i = 6;
                break;
            case 10:
                i = 7;
                break;
            case 11:
                i = 8;
                break;
            case 12:
                i = 10;
                break;
            case 13:
                i = 9;
                break;
            default:
                throw new IllegalArgumentException("Invalid type: " + ionType);
        }
        startValue(19);
        this._writer.write((i << 4) | 15);
        this._patch.patchValue(1);
        closeValue();
    }

    public void writeRaw(byte[] bArr, int i, int i2) throws IOException {
        startValue(19);
        this._writer.write(bArr, i, i2);
        this._patch.patchValue(i2);
        closeValue();
    }

    public int writeRecursive(BlockedBuffer.BlockedByteInputStream blockedByteInputStream, OutputStream outputStream, PatchedValues patchedValues) throws IOException {
        int length = 0;
        for (int i = 0; i <= patchedValues._freePos; i++) {
            int i2 = patchedValues._types[i];
            int i3 = patchedValues._positions[i];
            long j = patchedValues._lengths[i];
            int i4 = (int) (j >> 32);
            int i5 = (int) j;
            if (patchedValues.getParent() == null) {
                if (i3 > length) {
                    blockedByteInputStream.writeTo(outputStream, i3 - length);
                    length = i3;
                }
                length += i4 + i5;
            }
            if (i4 > 0) {
                blockedByteInputStream.writeTo(outputStream, i4);
            }
            switch (i2) {
                case 17:
                    IonBinary.writeVarUInt(outputStream, i5);
                    blockedByteInputStream.writeTo(outputStream, i5);
                    break;
                case 18:
                    SymbolTable symbolTableRemove = patchedValues._symtabs.remove();
                    if (!symbolTableRemove.isSystemTable()) {
                        byte[] bArrReverseEncode = _Private_LiteDomTrampoline.reverseEncode(1024, symbolTableRemove);
                        outputStream.write(bArrReverseEncode);
                        length += bArrReverseEncode.length;
                    }
                    break;
                case 19:
                    blockedByteInputStream.writeTo(outputStream, i5);
                    break;
                default:
                    if (i5 >= 14) {
                        outputStream.write(14 | (i2 << 4));
                        IonBinary.writeVarUInt(outputStream, i5);
                    } else {
                        outputStream.write((i2 << 4) | i5);
                    }
                    switch (i2) {
                        case 11:
                        case 12:
                        case 13:
                        case 14:
                            writeRecursive(blockedByteInputStream, outputStream, patchedValues._children.remove());
                            break;
                        default:
                            blockedByteInputStream.writeTo(outputStream, i5);
                            break;
                    }
                    break;
            }
        }
        return length;
    }

    @Override // com.amazon.ion.IonWriter
    public void writeString(String str) throws IOException {
        if (str == null) {
            writeNull(IonType.STRING);
            return;
        }
        startValue(8);
        this._patch.patchValue(this._writer.writeStringData(str));
        closeValue();
    }

    @Override // com.amazon.ion.impl._Private_ListWriter
    public void writeStringList(String[] strArr) throws IOException {
        stepIn(IonType.LIST);
        for (String str : strArr) {
            writeString(str);
        }
        stepOut();
    }

    @Override // com.amazon.ion.impl.IonWriterSystem
    public void writeSymbolAsIs(int i) throws IOException {
        startValue(7);
        this._patch.patchValue(this._writer.writeUIntValue(i));
        closeValue();
    }

    @Override // com.amazon.ion.IonWriter
    public void writeTimestamp(Timestamp timestamp) throws IOException {
        if (timestamp == null) {
            writeNull(IonType.TIMESTAMP);
            return;
        }
        startValue(6);
        this._patch.patchValue(this._writer.writeTimestamp(timestamp));
        closeValue();
    }

    public int write_symbol_table(OutputStream outputStream, SymbolTable symbolTable) throws IOException {
        CountingStream countingStream = new CountingStream(outputStream);
        IonWriterSystemBinary ionWriterSystemBinary = new IonWriterSystemBinary(this._default_system_symbol_table, countingStream, false, false);
        symbolTable.writeTo(ionWriterSystemBinary);
        ionWriterSystemBinary.finish();
        return countingStream._written;
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static class CountingStream extends OutputStream {
        public final OutputStream _wrapped;
        public int _written;

        public CountingStream(OutputStream outputStream) {
            this._wrapped = outputStream;
        }

        public int getBytesWritten() {
            return this._written;
        }

        @Override // java.io.OutputStream
        public void write(int i) throws IOException {
            this._wrapped.write(i);
            this._written++;
        }

        @Override // java.io.OutputStream
        public void write(byte[] bArr) throws IOException {
            this._wrapped.write(bArr);
            this._written += bArr.length;
        }

        @Override // java.io.OutputStream
        public void write(byte[] bArr, int i, int i2) throws IOException {
            this._wrapped.write(bArr, i, i2);
            this._written += i2;
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static class PatchedValues {
        public static final int DEFAULT_PATCH_COUNT = 10;
        public Queue<PatchedValues> _children;
        public PatchedValues _parent;
        public Queue<SymbolTable> _symtabs;
        public int _freePos = -1;
        public int[] _types = new int[10];
        public int[] _positions = new int[10];
        public long[] _lengths = new long[10];

        public static int[] growOne(int[] iArr, int i) {
            int[] iArr2 = new int[i];
            System.arraycopy(iArr, 0, iArr2, 0, iArr.length);
            return iArr2;
        }

        public PatchedValues addChild() {
            PatchedValues patchedValues = new PatchedValues();
            patchedValues._parent = this;
            if (this._children == null) {
                this._children = new LinkedList();
            }
            this._children.add(patchedValues);
            return patchedValues;
        }

        public void endPatch() {
            int iLenVarUInt;
            if (this._parent != null) {
                long[] jArr = this._lengths;
                int i = this._freePos;
                long j = jArr[i];
                int i2 = (int) j;
                int i3 = ((int) (j >> 32)) + i2;
                switch (this._types[i]) {
                    case 17:
                        iLenVarUInt = IonBinary.lenVarUInt(i2);
                        i3 += iLenVarUInt;
                        break;
                    case 18:
                    case 19:
                        break;
                    default:
                        i3++;
                        if (i2 >= 14) {
                            iLenVarUInt = IonBinary.lenVarUInt(i2);
                            i3 += iLenVarUInt;
                        }
                        break;
                }
                this._parent.patchValue(i3);
            }
        }

        public PatchedValues getParent() {
            return this._parent;
        }

        public int getType() {
            return this._types[this._freePos];
        }

        public final void grow() {
            int length = this._positions.length * 2;
            this._types = growOne(this._types, length);
            this._positions = growOne(this._positions, length);
            this._lengths = growOne(this._lengths, length);
        }

        public void injectSymbolTable(SymbolTable symbolTable, boolean z) {
            if (this._parent != null) {
                throw new IllegalStateException("Cannot inject a symbol table when not on top-level");
            }
            if (this._symtabs == null) {
                this._symtabs = new LinkedList();
            }
            int i = this._freePos + 1;
            this._freePos = i;
            if (i == this._positions.length) {
                grow();
            }
            if (z) {
                int[] iArr = this._types;
                int i2 = this._freePos;
                iArr[i2] = iArr[i2 - 1];
                long[] jArr = this._lengths;
                jArr[i2] = jArr[i2 - 1];
                iArr[i2 - 1] = 18;
                jArr[i2 - 1] = 0;
            } else {
                int[] iArr2 = this._types;
                int i3 = this._freePos;
                iArr2[i3] = 18;
                this._lengths[i3] = 0;
            }
            this._symtabs.add(symbolTable);
        }

        public void patchFieldName(int i) {
            this._lengths[this._freePos] = ((long) i) << 32;
        }

        public void patchValue(int i) {
            long[] jArr = this._lengths;
            int i2 = this._freePos;
            long j = jArr[i2];
            jArr[i2] = ((j & 4294967295L) + ((long) i)) | (ObjectCountHashMap.HASH_MASK & j);
        }

        public void reset() {
            this._freePos = -1;
            this._children = null;
            this._symtabs = null;
        }

        public void startPatch(int i, int i2) {
            int i3 = this._freePos + 1;
            this._freePos = i3;
            if (i3 == this._positions.length) {
                grow();
            }
            int[] iArr = this._types;
            int i4 = this._freePos;
            iArr[i4] = i;
            this._lengths[i4] = 0;
            this._positions[i4] = i2;
        }

        public static long[] growOne(long[] jArr, int i) {
            long[] jArr2 = new long[i];
            System.arraycopy(jArr, 0, jArr2, 0, jArr.length);
            return jArr2;
        }
    }

    @Override // com.amazon.ion.impl._Private_ListWriter
    public void writeFloatList(double[] dArr) throws IOException {
        stepIn(IonType.LIST);
        for (double d : dArr) {
            writeFloat(d);
        }
        stepOut();
    }

    @Override // com.amazon.ion.impl._Private_ListWriter
    public void writeIntList(short[] sArr) throws IOException {
        stepIn(IonType.LIST);
        for (short s : sArr) {
            writeInt(s);
        }
        stepOut();
    }

    @Override // com.amazon.ion.impl.IonWriterSystem
    public void writeSymbolAsIs(String str) throws IOException {
        if (str == null) {
            writeNull(IonType.SYMBOL);
        } else {
            writeSymbolAsIs(add_symbol(str));
        }
    }

    @Override // com.amazon.ion.IonWriter
    public void writeInt(BigInteger bigInteger) throws IOException {
        if (bigInteger == null) {
            writeNull(IonType.INT);
            return;
        }
        boolean z = bigInteger.signum() < 0;
        if (z) {
            bigInteger = bigInteger.negate();
        }
        int iLenIonInt = IonBinary.lenIonInt(bigInteger);
        startValue(z ? 3 : 2);
        this._writer.writeUIntValue(bigInteger, iLenIonInt);
        this._patch.patchValue(iLenIonInt);
        closeValue();
    }

    @Override // com.amazon.ion.impl._Private_ListWriter
    public void writeIntList(int[] iArr) throws IOException {
        stepIn(IonType.LIST);
        for (int i : iArr) {
            writeInt(i);
        }
        stepOut();
    }

    @Override // com.amazon.ion.impl._Private_ListWriter
    public void writeIntList(long[] jArr) throws IOException {
        stepIn(IonType.LIST);
        for (long j : jArr) {
            writeInt(j);
        }
        stepOut();
    }
}
