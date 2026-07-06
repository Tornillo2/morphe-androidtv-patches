package com.amazon.ion.impl.lite;

import android.support.v4.media.session.PlaybackStateCompat;
import com.amazon.ion.Decimal;
import com.amazon.ion.IonBlob;
import com.amazon.ion.IonBool;
import com.amazon.ion.IonClob;
import com.amazon.ion.IonDatagram;
import com.amazon.ion.IonDecimal;
import com.amazon.ion.IonException;
import com.amazon.ion.IonFloat;
import com.amazon.ion.IonInt;
import com.amazon.ion.IonList;
import com.amazon.ion.IonSequence;
import com.amazon.ion.IonSexp;
import com.amazon.ion.IonString;
import com.amazon.ion.IonStruct;
import com.amazon.ion.IonSymbol;
import com.amazon.ion.IonSystem;
import com.amazon.ion.IonTimestamp;
import com.amazon.ion.IonType;
import com.amazon.ion.IonValue;
import com.amazon.ion.SymbolTable;
import com.amazon.ion.SymbolToken;
import com.amazon.ion.Timestamp;
import com.amazon.ion.impl._Private_IonConstants;
import com.amazon.ion.impl.bin.WriteBuffer;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public class ReverseBinaryEncoder {
    public static final /* synthetic */ boolean $assertionsDisabled = false;
    public static final int NULL_LENGTH_MASK = 15;
    public static final int TYPE_ANNOTATIONS = 224;
    public static final int TYPE_BLOB = 160;
    public static final int TYPE_BOOL = 16;
    public static final int TYPE_CLOB = 144;
    public static final int TYPE_DECIMAL = 80;
    public static final int TYPE_FLOAT = 64;
    public static final int TYPE_LIST = 176;
    public static final int TYPE_NEG_INT = 48;
    public static final int TYPE_NULL = 0;
    public static final int TYPE_POS_INT = 32;
    public static final int TYPE_SEXP = 192;
    public static final int TYPE_STRING = 128;
    public static final int TYPE_STRUCT = 208;
    public static final int TYPE_SYMBOL = 112;
    public static final int TYPE_TIMESTAMP = 96;
    public byte[] myBuffer;
    public IonSystem myIonSystem;
    public int myOffset;
    public SymbolTable mySymbolTable;
    public static final BigInteger MAX_LONG_VALUE = BigInteger.valueOf(Long.MAX_VALUE);
    public static final byte[] negativeZeroBitArray = {-128};
    public static final byte[] positiveZeroBitArray = new byte[0];

    /* JADX INFO: renamed from: com.amazon.ion.impl.lite.ReverseBinaryEncoder$1, reason: invalid class name */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static /* synthetic */ class AnonymousClass1 {
        public static final /* synthetic */ int[] $SwitchMap$com$amazon$ion$IonType;
        public static final /* synthetic */ int[] $SwitchMap$com$amazon$ion$Timestamp$Precision;

        static {
            int[] iArr = new int[Timestamp.Precision.values().length];
            $SwitchMap$com$amazon$ion$Timestamp$Precision = iArr;
            try {
                iArr[Timestamp.Precision.FRACTION.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$ion$Timestamp$Precision[Timestamp.Precision.SECOND.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$ion$Timestamp$Precision[Timestamp.Precision.MINUTE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$amazon$ion$Timestamp$Precision[Timestamp.Precision.DAY.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$amazon$ion$Timestamp$Precision[Timestamp.Precision.MONTH.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$amazon$ion$Timestamp$Precision[Timestamp.Precision.YEAR.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            int[] iArr2 = new int[IonType.values().length];
            $SwitchMap$com$amazon$ion$IonType = iArr2;
            try {
                iArr2[IonType.BLOB.ordinal()] = 1;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$com$amazon$ion$IonType[IonType.BOOL.ordinal()] = 2;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                $SwitchMap$com$amazon$ion$IonType[IonType.CLOB.ordinal()] = 3;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                $SwitchMap$com$amazon$ion$IonType[IonType.DECIMAL.ordinal()] = 4;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                $SwitchMap$com$amazon$ion$IonType[IonType.FLOAT.ordinal()] = 5;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                $SwitchMap$com$amazon$ion$IonType[IonType.INT.ordinal()] = 6;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                $SwitchMap$com$amazon$ion$IonType[IonType.NULL.ordinal()] = 7;
            } catch (NoSuchFieldError unused13) {
            }
            try {
                $SwitchMap$com$amazon$ion$IonType[IonType.STRING.ordinal()] = 8;
            } catch (NoSuchFieldError unused14) {
            }
            try {
                $SwitchMap$com$amazon$ion$IonType[IonType.SYMBOL.ordinal()] = 9;
            } catch (NoSuchFieldError unused15) {
            }
            try {
                $SwitchMap$com$amazon$ion$IonType[IonType.TIMESTAMP.ordinal()] = 10;
            } catch (NoSuchFieldError unused16) {
            }
            try {
                $SwitchMap$com$amazon$ion$IonType[IonType.LIST.ordinal()] = 11;
            } catch (NoSuchFieldError unused17) {
            }
            try {
                $SwitchMap$com$amazon$ion$IonType[IonType.SEXP.ordinal()] = 12;
            } catch (NoSuchFieldError unused18) {
            }
            try {
                $SwitchMap$com$amazon$ion$IonType[IonType.STRUCT.ordinal()] = 13;
            } catch (NoSuchFieldError unused19) {
            }
            try {
                $SwitchMap$com$amazon$ion$IonType[IonType.DATAGRAM.ordinal()] = 14;
            } catch (NoSuchFieldError unused20) {
            }
        }
    }

    public ReverseBinaryEncoder(int i) {
        this.myBuffer = new byte[i];
        this.myOffset = i;
    }

    public int byteSize() {
        return this.myBuffer.length - this.myOffset;
    }

    public final void checkLocalSymbolTablePlacement(IonValue ionValue) {
        SymbolTable symbolTable = ionValue.getSymbolTable();
        if (symbolTable == null) {
            throw new IllegalStateException("Binary reverse encoder isn't using LiteImpl");
        }
        if (this.mySymbolTable == null) {
            this.mySymbolTable = symbolTable;
            return;
        }
        if (!symbolTable.isLocalTable()) {
            if (!this.mySymbolTable.isSystemTable() || this.mySymbolTable.getIonVersionId().equals(symbolTable.getIonVersionId())) {
                return;
            }
            writeBytes(_Private_IonConstants.BINARY_VERSION_MARKER_1_0);
            this.mySymbolTable = symbolTable;
            return;
        }
        if (this.mySymbolTable.isSystemTable()) {
            writeBytes(_Private_IonConstants.BINARY_VERSION_MARKER_1_0);
            this.mySymbolTable = symbolTable;
            return;
        }
        SymbolTable symbolTable2 = this.mySymbolTable;
        if (symbolTable != symbolTable2) {
            writeLocalSymbolTable(symbolTable2);
            this.mySymbolTable = symbolTable;
        }
    }

    public final int findSid(SymbolToken symbolToken) {
        int sid = symbolToken.getSid();
        String text = symbolToken.getText();
        if (sid != -1) {
            return sid;
        }
        if (this.mySymbolTable.isSystemTable()) {
            this.mySymbolTable = this.myIonSystem.newLocalSymbolTable(new SymbolTable[0]);
        }
        return this.mySymbolTable.intern(text).getSid();
    }

    public final int growBuffer(int i) {
        byte[] bArr = this.myBuffer;
        int length = bArr.length;
        int i2 = ((-i) + length) << 1;
        byte[] bArr2 = new byte[i2];
        int i3 = i2 - length;
        System.arraycopy(bArr, 0, bArr2, i3, length);
        this.myBuffer = bArr2;
        this.myOffset += i3;
        return i + i3;
    }

    public void serialize(IonDatagram ionDatagram) throws IonException {
        this.myIonSystem = ionDatagram.getSystem();
        this.mySymbolTable = null;
        writeIonValue(ionDatagram);
        SymbolTable symbolTable = this.mySymbolTable;
        if (symbolTable != null && symbolTable.isLocalTable()) {
            writeLocalSymbolTable(this.mySymbolTable);
        }
        writeBytes(_Private_IonConstants.BINARY_VERSION_MARKER_1_0);
    }

    public byte[] toNewByteArray() {
        byte[] bArr = this.myBuffer;
        int length = bArr.length;
        int i = this.myOffset;
        int i2 = length - i;
        byte[] bArr2 = new byte[i2];
        System.arraycopy(bArr, i, bArr2, 0, i2);
        return bArr2;
    }

    public final void writeAnnotations(IonValue ionValue, int i) {
        SymbolToken[] typeAnnotationSymbols = ionValue.getTypeAnnotationSymbols();
        if (typeAnnotationSymbols.length <= 0) {
            return;
        }
        int length = this.myBuffer.length - this.myOffset;
        int length2 = typeAnnotationSymbols.length;
        while (true) {
            length2--;
            if (length2 < 0) {
                writeVarUInt((this.myBuffer.length - this.myOffset) - length);
                writePrefix(224, (this.myBuffer.length - this.myOffset) - i);
                return;
            }
            writeVarUInt(findSid(typeAnnotationSymbols[length2]));
        }
    }

    public final void writeByte(int i) {
        int iGrowBuffer = this.myOffset - 1;
        if (iGrowBuffer < 0) {
            iGrowBuffer = growBuffer(iGrowBuffer);
        }
        this.myBuffer[iGrowBuffer] = (byte) i;
        this.myOffset = iGrowBuffer;
    }

    public int writeBytes(OutputStream outputStream) throws IOException {
        byte[] bArr = this.myBuffer;
        int length = bArr.length;
        int i = this.myOffset;
        int i2 = length - i;
        byte[] bArr2 = new byte[i2];
        System.arraycopy(bArr, i, bArr2, 0, i2);
        outputStream.write(bArr2);
        return i2;
    }

    public final void writeImport(SymbolTable symbolTable) {
        int length = this.myBuffer.length - this.myOffset;
        int maxId = symbolTable.getMaxId();
        if (maxId == 0) {
            writeByte(32);
        } else {
            writeUInt(maxId);
            writePrefix(32, (this.myBuffer.length - this.myOffset) - length);
        }
        writeByte(-120);
        int length2 = this.myBuffer.length - this.myOffset;
        writeUInt(symbolTable.getVersion());
        writePrefix(32, (this.myBuffer.length - this.myOffset) - length2);
        writeByte(-123);
        writeIonStringContent(symbolTable.getName());
        writeByte(-124);
        writePrefix(TYPE_STRUCT, (this.myBuffer.length - this.myOffset) - length);
    }

    public final void writeImportsField(SymbolTable symbolTable) {
        SymbolTable[] importedTables = symbolTable.getImportedTables();
        if (importedTables.length == 0) {
            return;
        }
        int length = this.myBuffer.length - this.myOffset;
        int length2 = importedTables.length;
        while (true) {
            length2--;
            if (length2 < 0) {
                writePrefix(176, (this.myBuffer.length - this.myOffset) - length);
                writeByte(-122);
                return;
            }
            writeImport(importedTables[length2]);
        }
    }

    public final void writeIonBlobContent(IonBlob ionBlob) {
        if (ionBlob.isNullValue()) {
            writeByte(-81);
            return;
        }
        byte[] bytes = ionBlob.getBytes();
        writeLobContent(bytes);
        writePrefix(160, bytes.length);
    }

    public final void writeIonBoolContent(IonBool ionBool) {
        writeByte(ionBool.isNullValue() ? 31 : ionBool.booleanValue() ? 17 : 16);
    }

    public final void writeIonClobContent(IonClob ionClob) {
        if (ionClob.isNullValue()) {
            writeByte(-97);
            return;
        }
        byte[] bytes = ionClob.getBytes();
        writeLobContent(bytes);
        writePrefix(144, bytes.length);
    }

    public final void writeIonDatagramContent(IonDatagram ionDatagram) {
        ListIterator<IonValue> listIterator = ionDatagram.listIterator(ionDatagram.size());
        while (listIterator.hasPrevious()) {
            IonValue ionValuePrevious = listIterator.previous();
            checkLocalSymbolTablePlacement(ionValuePrevious);
            writeIonValue(ionValuePrevious);
        }
    }

    public final void writeIonDecimalContent(BigDecimal bigDecimal) {
        byte[] byteArray;
        BigInteger bigIntegerUnscaledValue = bigDecimal.unscaledValue();
        int iSignum = bigIntegerUnscaledValue.signum();
        if (iSignum == -1) {
            byteArray = bigIntegerUnscaledValue.negate().toByteArray();
            byteArray[0] = (byte) (byteArray[0] | 128);
        } else if (iSignum == 0) {
            byteArray = Decimal.isNegativeZero(bigDecimal) ? negativeZeroBitArray : positiveZeroBitArray;
        } else {
            if (iSignum != 1) {
                throw new IllegalStateException("mantissa signum out of range");
            }
            byteArray = bigIntegerUnscaledValue.toByteArray();
        }
        writeBytes(byteArray);
        writeVarInt(-bigDecimal.scale());
    }

    public final void writeIonFloatContent(IonFloat ionFloat) {
        if (ionFloat.isNullValue()) {
            writeByte(79);
            return;
        }
        long jDoubleToRawLongBits = Double.doubleToRawLongBits(ionFloat.doubleValue());
        int iGrowBuffer = this.myOffset - 8;
        if (iGrowBuffer < 0) {
            iGrowBuffer = growBuffer(iGrowBuffer);
        }
        byte[] bArr = this.myBuffer;
        bArr[iGrowBuffer] = (byte) (jDoubleToRawLongBits >>> 56);
        bArr[iGrowBuffer + 1] = (byte) (jDoubleToRawLongBits >>> 48);
        bArr[iGrowBuffer + 2] = (byte) (jDoubleToRawLongBits >>> 40);
        bArr[iGrowBuffer + 3] = (byte) (jDoubleToRawLongBits >>> 32);
        bArr[iGrowBuffer + 4] = (byte) (jDoubleToRawLongBits >>> 24);
        bArr[iGrowBuffer + 5] = (byte) (jDoubleToRawLongBits >>> 16);
        bArr[iGrowBuffer + 6] = (byte) (jDoubleToRawLongBits >>> 8);
        bArr[iGrowBuffer + 7] = (byte) jDoubleToRawLongBits;
        this.myOffset = iGrowBuffer;
        writePrefix(64, 8);
    }

    public final void writeIonIntContent(IonInt ionInt) {
        if (ionInt.isNullValue()) {
            writeByte(47);
            return;
        }
        BigInteger bigIntegerBigIntegerValue = ionInt.bigIntegerValue();
        int iSignum = bigIntegerBigIntegerValue.signum();
        int length = this.myBuffer.length - this.myOffset;
        int i = 32;
        if (iSignum == 0) {
            writeByte(32);
            return;
        }
        if (iSignum < 0) {
            bigIntegerBigIntegerValue = bigIntegerBigIntegerValue.negate();
            i = 48;
        }
        if (bigIntegerBigIntegerValue.compareTo(MAX_LONG_VALUE) < 0) {
            writeUInt(bigIntegerBigIntegerValue.longValue());
        } else {
            byte[] byteArray = bigIntegerBigIntegerValue.toByteArray();
            int i2 = 0;
            while (i2 < byteArray.length && byteArray[i2] == 0) {
                i2++;
            }
            int length2 = byteArray.length - i2;
            int iGrowBuffer = this.myOffset - length2;
            if (iGrowBuffer < 0) {
                iGrowBuffer = growBuffer(iGrowBuffer);
            }
            System.arraycopy(byteArray, i2, this.myBuffer, iGrowBuffer, length2);
            this.myOffset = iGrowBuffer;
        }
        writePrefix(i, (this.myBuffer.length - this.myOffset) - length);
    }

    public final void writeIonListContent(IonList ionList) {
        if (ionList.isNullValue()) {
            writeByte(-65);
        } else {
            writeIonSequenceContent(ionList);
        }
    }

    public final void writeIonNullContent() {
        writeByte(15);
    }

    public final void writeIonSequenceContent(IonSequence ionSequence) {
        int length = this.myBuffer.length - this.myOffset;
        IonValue[] array = ionSequence.toArray();
        int length2 = array.length;
        while (true) {
            length2--;
            if (length2 < 0) {
                break;
            } else {
                writeIonValue(array[length2]);
            }
        }
        int i = AnonymousClass1.$SwitchMap$com$amazon$ion$IonType[ionSequence.getType().ordinal()];
        if (i == 11) {
            writePrefix(176, (this.myBuffer.length - this.myOffset) - length);
        } else {
            if (i != 12) {
                throw new IonException("cannot identify instance of IonSequence");
            }
            writePrefix(192, (this.myBuffer.length - this.myOffset) - length);
        }
    }

    public final void writeIonSexpContent(IonSexp ionSexp) {
        if (ionSexp.isNullValue()) {
            writeByte(-49);
        } else {
            writeIonSequenceContent(ionSexp);
        }
    }

    public final void writeIonStringContent(IonString ionString) {
        if (ionString.isNullValue()) {
            writeByte(-113);
        } else {
            writeIonStringContent(ionString.stringValue());
        }
    }

    public final void writeIonStructContent(IonStruct ionStruct) {
        if (ionStruct.isNullValue()) {
            writeByte(-33);
            return;
        }
        int length = this.myBuffer.length - this.myOffset;
        ArrayList arrayList = new ArrayList();
        Iterator<IonValue> it = ionStruct.iterator();
        while (it.hasNext()) {
            arrayList.add(it.next());
        }
        int size = arrayList.size();
        while (true) {
            size--;
            if (size < 0) {
                writePrefix(TYPE_STRUCT, (this.myBuffer.length - this.myOffset) - length);
                return;
            }
            IonValue ionValue = (IonValue) arrayList.get(size);
            SymbolToken fieldNameSymbol = ionValue.getFieldNameSymbol();
            writeIonValue(ionValue);
            writeVarUInt(findSid(fieldNameSymbol));
        }
    }

    public final void writeIonSymbolContent(IonSymbol ionSymbol) {
        if (ionSymbol.isNullValue()) {
            writeByte(127);
            return;
        }
        int length = this.myBuffer.length - this.myOffset;
        writeUInt(findSid(ionSymbol.symbolValue()));
        writePrefix(112, (this.myBuffer.length - this.myOffset) - length);
    }

    public final void writeIonTimestampContent(IonTimestamp ionTimestamp) {
        if (ionTimestamp.isNullValue()) {
            writeByte(111);
            return;
        }
        int length = this.myBuffer.length - this.myOffset;
        Timestamp timestampTimestampValue = ionTimestamp.timestampValue();
        switch (AnonymousClass1.$SwitchMap$com$amazon$ion$Timestamp$Precision[timestampTimestampValue._precision.ordinal()]) {
            case 1:
            case 2:
                BigDecimal bigDecimal = timestampTimestampValue._fraction;
                if (bigDecimal != null) {
                    writeIonDecimalContent(bigDecimal);
                }
                writeVarUInt(timestampTimestampValue._second);
            case 3:
                writeVarUInt(timestampTimestampValue._minute);
                writeVarUInt(timestampTimestampValue._hour);
            case 4:
                writeVarUInt(timestampTimestampValue._day);
            case 5:
                writeVarUInt(timestampTimestampValue._month);
            case 6:
                writeVarUInt(timestampTimestampValue._year);
                Integer num = timestampTimestampValue._offset;
                if (num == null) {
                    writeByte(-64);
                } else {
                    writeVarInt(num.intValue());
                }
                writePrefix(96, (this.myBuffer.length - this.myOffset) - length);
                return;
            default:
                throw new IllegalStateException("unrecognized Timestamp precision: " + timestampTimestampValue._precision);
        }
    }

    public final void writeIonValue(IonValue ionValue) throws IonException {
        int length = this.myBuffer.length - this.myOffset;
        switch (AnonymousClass1.$SwitchMap$com$amazon$ion$IonType[ionValue.getType().ordinal()]) {
            case 1:
                writeIonBlobContent((IonBlob) ionValue);
                break;
            case 2:
                writeIonBoolContent((IonBool) ionValue);
                break;
            case 3:
                writeIonClobContent((IonClob) ionValue);
                break;
            case 4:
                writeIonDecimalContent((IonDecimal) ionValue);
                break;
            case 5:
                writeIonFloatContent((IonFloat) ionValue);
                break;
            case 6:
                writeIonIntContent((IonInt) ionValue);
                break;
            case 7:
                writeIonNullContent();
                break;
            case 8:
                writeIonStringContent((IonString) ionValue);
                break;
            case 9:
                writeIonSymbolContent((IonSymbol) ionValue);
                break;
            case 10:
                writeIonTimestampContent((IonTimestamp) ionValue);
                break;
            case 11:
                writeIonListContent((IonList) ionValue);
                break;
            case 12:
                writeIonSexpContent((IonSexp) ionValue);
                break;
            case 13:
                writeIonStructContent((IonStruct) ionValue);
                break;
            case 14:
                writeIonDatagramContent((IonDatagram) ionValue);
                break;
            default:
                throw new IonException("IonType is unknown: " + ionValue.getType());
        }
        writeAnnotations(ionValue, length);
    }

    public final void writeLobContent(byte[] bArr) {
        int length = bArr.length;
        int iGrowBuffer = this.myOffset - length;
        if (iGrowBuffer < 0) {
            iGrowBuffer = growBuffer(iGrowBuffer);
        }
        System.arraycopy(bArr, 0, this.myBuffer, iGrowBuffer, length);
        this.myOffset = iGrowBuffer;
    }

    public final void writeLocalSymbolTable(SymbolTable symbolTable) {
        int length = this.myBuffer.length - this.myOffset;
        writeSymbolsField(symbolTable);
        writeImportsField(symbolTable);
        writePrefix(TYPE_STRUCT, (this.myBuffer.length - this.myOffset) - length);
        writeBytes(new byte[]{-127, -125});
        writePrefix(224, (this.myBuffer.length - this.myOffset) - length);
    }

    public final void writePrefix(int i, int i2) {
        if (i2 >= 14) {
            writeVarUInt(i2);
            i2 = 14;
        }
        int iGrowBuffer = this.myOffset - 1;
        if (iGrowBuffer < 0) {
            iGrowBuffer = growBuffer(iGrowBuffer);
        }
        this.myBuffer[iGrowBuffer] = (byte) (i | i2);
        this.myOffset = iGrowBuffer;
    }

    public final void writeSymbolsField(SymbolTable symbolTable) {
        int importedMaxId = symbolTable.getImportedMaxId();
        int maxId = symbolTable.getMaxId();
        if (importedMaxId == maxId) {
            return;
        }
        int length = this.myBuffer.length - this.myOffset;
        while (maxId > importedMaxId) {
            String strFindKnownSymbol = symbolTable.findKnownSymbol(maxId);
            if (strFindKnownSymbol == null) {
                writeByte(-113);
            } else {
                writeIonStringContent(strFindKnownSymbol);
            }
            maxId--;
        }
        writePrefix(176, (this.myBuffer.length - this.myOffset) - length);
        writeByte(-121);
    }

    public final void writeUInt(long j) {
        int iGrowBuffer;
        int i = this.myOffset;
        if (j < 256) {
            iGrowBuffer = i - 1;
            if (iGrowBuffer < 0) {
                iGrowBuffer = growBuffer(iGrowBuffer);
            }
            this.myBuffer[iGrowBuffer] = (byte) j;
        } else if (j < PlaybackStateCompat.ACTION_PREPARE_FROM_SEARCH) {
            iGrowBuffer = i - 2;
            if (iGrowBuffer < 0) {
                iGrowBuffer = growBuffer(iGrowBuffer);
            }
            byte[] bArr = this.myBuffer;
            bArr[iGrowBuffer] = (byte) (j >>> 8);
            bArr[iGrowBuffer + 1] = (byte) j;
        } else if (j < 16777216) {
            iGrowBuffer = i - 3;
            if (iGrowBuffer < 0) {
                iGrowBuffer = growBuffer(iGrowBuffer);
            }
            byte[] bArr2 = this.myBuffer;
            bArr2[iGrowBuffer] = (byte) (j >>> 16);
            bArr2[iGrowBuffer + 1] = (byte) (j >>> 8);
            bArr2[iGrowBuffer + 2] = (byte) j;
        } else if (j < 4294967296L) {
            iGrowBuffer = i - 4;
            if (iGrowBuffer < 0) {
                iGrowBuffer = growBuffer(iGrowBuffer);
            }
            byte[] bArr3 = this.myBuffer;
            bArr3[iGrowBuffer] = (byte) (j >>> 24);
            bArr3[iGrowBuffer + 1] = (byte) (j >>> 16);
            bArr3[iGrowBuffer + 2] = (byte) (j >>> 8);
            bArr3[iGrowBuffer + 3] = (byte) j;
        } else if (j < 1099511627776L) {
            iGrowBuffer = i - 5;
            if (iGrowBuffer < 0) {
                iGrowBuffer = growBuffer(iGrowBuffer);
            }
            byte[] bArr4 = this.myBuffer;
            bArr4[iGrowBuffer] = (byte) (j >>> 32);
            bArr4[iGrowBuffer + 1] = (byte) (j >>> 24);
            bArr4[iGrowBuffer + 2] = (byte) (j >>> 16);
            bArr4[iGrowBuffer + 3] = (byte) (j >>> 8);
            bArr4[iGrowBuffer + 4] = (byte) j;
        } else if (j < WriteBuffer.VAR_INT_8_OCTET_MIN_VALUE) {
            iGrowBuffer = i - 6;
            if (iGrowBuffer < 0) {
                iGrowBuffer = growBuffer(iGrowBuffer);
            }
            byte[] bArr5 = this.myBuffer;
            bArr5[iGrowBuffer] = (byte) (j >>> 40);
            bArr5[iGrowBuffer + 1] = (byte) (j >>> 32);
            bArr5[iGrowBuffer + 2] = (byte) (j >>> 24);
            bArr5[iGrowBuffer + 3] = (byte) (j >>> 16);
            bArr5[iGrowBuffer + 4] = (byte) (j >>> 8);
            bArr5[iGrowBuffer + 5] = (byte) j;
        } else if (j < WriteBuffer.VAR_UINT_9_OCTET_MIN_VALUE) {
            iGrowBuffer = i - 7;
            if (iGrowBuffer < 0) {
                iGrowBuffer = growBuffer(iGrowBuffer);
            }
            byte[] bArr6 = this.myBuffer;
            bArr6[iGrowBuffer] = (byte) (j >>> 48);
            bArr6[iGrowBuffer + 1] = (byte) (j >>> 40);
            bArr6[iGrowBuffer + 2] = (byte) (j >>> 32);
            bArr6[iGrowBuffer + 3] = (byte) (j >>> 24);
            bArr6[iGrowBuffer + 4] = (byte) (j >>> 16);
            bArr6[iGrowBuffer + 5] = (byte) (j >>> 8);
            bArr6[iGrowBuffer + 6] = (byte) j;
        } else {
            iGrowBuffer = i - 8;
            if (iGrowBuffer < 0) {
                iGrowBuffer = growBuffer(iGrowBuffer);
            }
            byte[] bArr7 = this.myBuffer;
            bArr7[iGrowBuffer] = (byte) (j >>> 56);
            bArr7[iGrowBuffer + 1] = (byte) (j >>> 48);
            bArr7[iGrowBuffer + 2] = (byte) (j >>> 40);
            bArr7[iGrowBuffer + 3] = (byte) (j >>> 32);
            bArr7[iGrowBuffer + 4] = (byte) (j >>> 24);
            bArr7[iGrowBuffer + 5] = (byte) (j >>> 16);
            bArr7[iGrowBuffer + 6] = (byte) (j >>> 8);
            bArr7[iGrowBuffer + 7] = (byte) j;
        }
        this.myOffset = iGrowBuffer;
    }

    public final void writeVarInt(int i) {
        int iGrowBuffer;
        if (i == 0) {
            writeByte(128);
            return;
        }
        int i2 = this.myOffset;
        boolean z = i < 0;
        if (z) {
            i = -i;
        }
        if (i < 64) {
            iGrowBuffer = i2 - 1;
            if (iGrowBuffer < 0) {
                iGrowBuffer = growBuffer(iGrowBuffer);
            }
            if (z) {
                i |= 64;
            }
            this.myBuffer[iGrowBuffer] = (byte) (i | 128);
        } else if (i < 8192) {
            iGrowBuffer = i2 - 2;
            if (iGrowBuffer < 0) {
                iGrowBuffer = growBuffer(iGrowBuffer);
            }
            if (z) {
                i |= 8192;
            }
            byte[] bArr = this.myBuffer;
            bArr[iGrowBuffer] = (byte) (i >>> 7);
            bArr[iGrowBuffer + 1] = (byte) (i | 128);
        } else if (i < 1048576) {
            iGrowBuffer = i2 - 3;
            if (iGrowBuffer < 0) {
                iGrowBuffer = growBuffer(iGrowBuffer);
            }
            if (z) {
                i |= 1048576;
            }
            byte[] bArr2 = this.myBuffer;
            bArr2[iGrowBuffer] = (byte) (i >>> 14);
            bArr2[iGrowBuffer + 1] = (byte) ((i >>> 7) & 127);
            bArr2[iGrowBuffer + 2] = (byte) (i | 128);
        } else if (i < 134217728) {
            iGrowBuffer = i2 - 4;
            if (iGrowBuffer < 0) {
                iGrowBuffer = growBuffer(iGrowBuffer);
            }
            if (z) {
                i |= 134217728;
            }
            byte[] bArr3 = this.myBuffer;
            bArr3[iGrowBuffer] = (byte) (i >>> 21);
            bArr3[iGrowBuffer + 1] = (byte) ((i >>> 14) & 127);
            bArr3[iGrowBuffer + 2] = (byte) ((i >>> 7) & 127);
            bArr3[iGrowBuffer + 3] = (byte) (i | 128);
        } else {
            iGrowBuffer = i2 - 5;
            if (iGrowBuffer < 0) {
                iGrowBuffer = growBuffer(iGrowBuffer);
            }
            byte[] bArr4 = this.myBuffer;
            byte b = (byte) ((i >>> 28) & 127);
            bArr4[iGrowBuffer] = b;
            if (z) {
                bArr4[iGrowBuffer] = (byte) (b | 64);
            }
            bArr4[iGrowBuffer + 1] = (byte) ((i >>> 21) & 127);
            bArr4[iGrowBuffer + 2] = (byte) ((i >>> 14) & 127);
            bArr4[iGrowBuffer + 3] = (byte) ((i >>> 7) & 127);
            bArr4[iGrowBuffer + 4] = (byte) (i | 128);
        }
        this.myOffset = iGrowBuffer;
    }

    public final void writeVarUInt(int i) {
        int iGrowBuffer;
        int i2 = this.myOffset;
        if (i < 128) {
            iGrowBuffer = i2 - 1;
            if (iGrowBuffer < 0) {
                iGrowBuffer = growBuffer(iGrowBuffer);
            }
            this.myBuffer[iGrowBuffer] = (byte) (i | 128);
        } else if (i < 16384) {
            iGrowBuffer = i2 - 2;
            if (iGrowBuffer < 0) {
                iGrowBuffer = growBuffer(iGrowBuffer);
            }
            byte[] bArr = this.myBuffer;
            bArr[iGrowBuffer] = (byte) (i >>> 7);
            bArr[iGrowBuffer + 1] = (byte) (i | 128);
        } else if (i < 2097152) {
            iGrowBuffer = i2 - 3;
            if (iGrowBuffer < 0) {
                iGrowBuffer = growBuffer(iGrowBuffer);
            }
            byte[] bArr2 = this.myBuffer;
            bArr2[iGrowBuffer] = (byte) (i >>> 14);
            bArr2[iGrowBuffer + 1] = (byte) ((i >>> 7) & 127);
            bArr2[iGrowBuffer + 2] = (byte) (i | 128);
        } else if (i < 268435456) {
            iGrowBuffer = i2 - 4;
            if (iGrowBuffer < 0) {
                iGrowBuffer = growBuffer(iGrowBuffer);
            }
            byte[] bArr3 = this.myBuffer;
            bArr3[iGrowBuffer] = (byte) (i >>> 21);
            bArr3[iGrowBuffer + 1] = (byte) ((i >>> 14) & 127);
            bArr3[iGrowBuffer + 2] = (byte) ((i >>> 7) & 127);
            bArr3[iGrowBuffer + 3] = (byte) (i | 128);
        } else {
            iGrowBuffer = i2 - 5;
            if (iGrowBuffer < 0) {
                iGrowBuffer = growBuffer(iGrowBuffer);
            }
            byte[] bArr4 = this.myBuffer;
            bArr4[iGrowBuffer] = (byte) (i >>> 28);
            bArr4[iGrowBuffer + 1] = (byte) ((i >>> 21) & 127);
            bArr4[iGrowBuffer + 2] = (byte) ((i >>> 14) & 127);
            bArr4[iGrowBuffer + 3] = (byte) ((i >>> 7) & 127);
            bArr4[iGrowBuffer + 4] = (byte) (i | 128);
        }
        this.myOffset = iGrowBuffer;
    }

    public int toNewByteArray(byte[] bArr) {
        byte[] bArr2 = this.myBuffer;
        int length = bArr2.length;
        int i = this.myOffset;
        int i2 = length - i;
        System.arraycopy(bArr2, i, bArr, 0, i2);
        return i2;
    }

    public final void writeIonStringContent(String str) {
        char cCharAt;
        int length = str.length();
        byte[] bArr = this.myBuffer;
        int iGrowBuffer = this.myOffset - length;
        if (iGrowBuffer < 0) {
            iGrowBuffer = growBuffer(iGrowBuffer);
            bArr = this.myBuffer;
        }
        int iGrowBuffer2 = iGrowBuffer + length;
        while (true) {
            length--;
            if (length < 0 || (cCharAt = str.charAt(length)) > 127) {
                break;
            }
            iGrowBuffer2--;
            bArr[iGrowBuffer2] = (byte) cCharAt;
        }
        while (length >= 0) {
            char cCharAt2 = str.charAt(length);
            if (cCharAt2 <= 127) {
                iGrowBuffer2--;
                if (iGrowBuffer2 < 0) {
                    iGrowBuffer2 = growBuffer(iGrowBuffer2);
                    bArr = this.myBuffer;
                }
                bArr[iGrowBuffer2] = (byte) cCharAt2;
            } else if (cCharAt2 <= 2047) {
                iGrowBuffer2 -= 2;
                if (iGrowBuffer2 < 0) {
                    iGrowBuffer2 = growBuffer(iGrowBuffer2);
                    bArr = this.myBuffer;
                }
                bArr[iGrowBuffer2] = (byte) (((cCharAt2 >> 6) & 31) | 192);
                bArr[iGrowBuffer2 + 1] = (byte) (128 | (cCharAt2 & '?'));
            } else if (cCharAt2 < 55296 || cCharAt2 > 57343) {
                iGrowBuffer2 -= 3;
                if (iGrowBuffer2 < 0) {
                    iGrowBuffer2 = growBuffer(iGrowBuffer2);
                    bArr = this.myBuffer;
                }
                bArr[iGrowBuffer2] = (byte) (((cCharAt2 >> '\f') & 15) | 224);
                bArr[iGrowBuffer2 + 1] = (byte) (((cCharAt2 >> 6) & 63) | 128);
                bArr[iGrowBuffer2 + 2] = (byte) (128 | (cCharAt2 & '?'));
            } else {
                if (cCharAt2 <= 56319) {
                    throw new IonException("invalid string, unpaired high surrogate character");
                }
                if (length != 0) {
                    length--;
                    char cCharAt3 = str.charAt(length);
                    if (cCharAt3 >= 55296 && cCharAt3 <= 56319) {
                        int i = ((cCharAt2 & 1023) | ((cCharAt3 & 1023) << 10)) + 65536;
                        iGrowBuffer2 -= 4;
                        if (iGrowBuffer2 < 0) {
                            iGrowBuffer2 = growBuffer(iGrowBuffer2);
                            bArr = this.myBuffer;
                        }
                        bArr[iGrowBuffer2] = (byte) (((i >> 18) & 7) | 240);
                        bArr[iGrowBuffer2 + 1] = (byte) (((i >> 12) & 63) | 128);
                        bArr[iGrowBuffer2 + 2] = (byte) (((i >> 6) & 63) | 128);
                        bArr[iGrowBuffer2 + 3] = (byte) (128 | (i & 63));
                    } else {
                        throw new IonException("invalid string, unpaired low surrogate character");
                    }
                } else {
                    throw new IonException("invalid string, unpaired low surrogate character");
                }
            }
            length--;
        }
        int i2 = this.myOffset - iGrowBuffer2;
        this.myOffset = iGrowBuffer2;
        writePrefix(128, i2);
    }

    public final void writeBytes(byte[] bArr) {
        int length = bArr.length;
        int iGrowBuffer = this.myOffset - length;
        if (iGrowBuffer < 0) {
            iGrowBuffer = growBuffer(iGrowBuffer);
        }
        System.arraycopy(bArr, 0, this.myBuffer, iGrowBuffer, length);
        this.myOffset = iGrowBuffer;
    }

    public int toNewByteArray(byte[] bArr, int i) {
        byte[] bArr2 = this.myBuffer;
        int length = bArr2.length;
        int i2 = this.myOffset;
        int i3 = length - i2;
        System.arraycopy(bArr2, i2, bArr, i, i3);
        return i3;
    }

    public void serialize(SymbolTable symbolTable) throws IonException {
        writeLocalSymbolTable(symbolTable);
    }

    public final void writeIonDecimalContent(IonDecimal ionDecimal) {
        if (ionDecimal.isNullValue()) {
            writeByte(95);
            return;
        }
        int length = this.myBuffer.length - this.myOffset;
        writeIonDecimalContent(ionDecimal.decimalValue());
        writePrefix(80, (this.myBuffer.length - this.myOffset) - length);
    }
}
