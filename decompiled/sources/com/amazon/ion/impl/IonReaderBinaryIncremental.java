package com.amazon.ion.impl;

import com.amazon.ion.Decimal;
import com.amazon.ion.IntegerSize;
import com.amazon.ion.IonBufferConfiguration;
import com.amazon.ion.IonCatalog;
import com.amazon.ion.IonException;
import com.amazon.ion.IonReader;
import com.amazon.ion.IonStruct;
import com.amazon.ion.IonType;
import com.amazon.ion.IonWriter;
import com.amazon.ion.ReadOnlyValueException;
import com.amazon.ion.SymbolTable;
import com.amazon.ion.SymbolToken;
import com.amazon.ion.SystemSymbols;
import com.amazon.ion.Timestamp;
import com.amazon.ion.UnknownSymbolException;
import com.amazon.ion.ValueFactory;
import com.amazon.ion.impl.IonReaderLookaheadBuffer;
import com.amazon.ion.impl._Private_RecyclingStack;
import com.amazon.ion.impl._Private_ScalarConversions;
import com.amazon.ion.impl.bin.utf8.Utf8StringDecoder;
import com.amazon.ion.impl.bin.utf8.Utf8StringDecoderPool;
import com.amazon.ion.system.IonReaderBuilder;
import com.amazon.ion.system.SimpleCatalog;
import j$.util.DesugarCollections;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public class IonReaderBinaryIncremental implements IonReader, _Private_ReaderWriter, _Private_IncrementalReader {
    public static final int ANNOTATIONS_LIST_INITIAL_CAPACITY = 8;
    public static final int CONTAINER_STACK_INITIAL_CAPACITY = 8;
    public static final IonCatalog EMPTY_CATALOG;
    public static final Iterator<String> EMPTY_ITERATOR;
    public static final int FLOAT_32_BYTE_LENGTH = 4;
    public static final int HIGHEST_BIT_BITMASK = 128;
    public static final int INT_SIZE_IN_BYTES = 4;
    public static final int IVM_FINAL_BYTE = 234;
    public static final int LONG_SIZE_IN_BYTES = 8;
    public static final int LOWER_SEVEN_BITS_BITMASK = 127;
    public static final int LOWER_SIX_BITS_BITMASK = 63;
    public static final int MOST_SIGNIFICANT_BYTE_OF_MAX_LONG = 127;
    public static final int MOST_SIGNIFICANT_BYTE_OF_MIN_LONG = 128;
    public static final byte[][] SCRATCH_FOR_SIZE;
    public static final int SYMBOLS_LIST_INITIAL_CAPACITY = 128;
    public static final List<String> SYSTEM_SYMBOLS_1_0;
    public static final int SYSTEM_SYMBOLS_1_0_SIZE;
    public static final int VALUE_BITS_PER_UINT_BYTE = 8;
    public static final int VALUE_BITS_PER_VARUINT_BYTE = 7;
    public static final int VAR_INT_NEGATIVE_ZERO = 192;
    public static final int VAR_INT_SIGN_BITMASK = 64;
    public final AnnotationIterator annotationIterator;
    public final List<Integer> annotationSids;
    public final ResizingPipedInputStream buffer;
    public final IonCatalog catalog;
    public final _Private_RecyclingStack<ContainerInfo> containerStack;
    public int importMaxId;
    public final TreeMap<Integer, SymbolTable> imports;
    public final InputStream inputStream;
    public final boolean isAnnotationIteratorReuseEnabled;
    public final IonReaderLookaheadBuffer lookahead;
    public final _Private_ScalarConversions.ValueVariant scalarConverter;
    public final List<String> symbols;
    public static final IonBufferConfiguration STANDARD_BUFFER_CONFIGURATION = new IonBufferConfiguration(new IonBufferConfiguration.Builder());
    public static final _Private_RecyclingStack.ElementFactory<ContainerInfo> CONTAINER_INFO_FACTORY = new AnonymousClass1();
    public final Utf8StringDecoder utf8Decoder = Utf8StringDecoderPool.getInstance().getOrCreate();
    public List<SymbolToken> symbolTokensById = null;
    public SymbolTable cachedReadOnlySymbolTable = null;
    public SymbolTable symbolTableLastTransferred = null;
    public int fieldNameSid = -1;
    public int majorVersion = 1;
    public int minorVersion = 0;
    public int lobBytesRead = 0;
    public IonType valueType = null;
    public IonTypeID valueTypeID = null;
    public boolean hasAnnotations = false;
    public boolean completeValueBuffered = false;
    public int valueStartPosition = -1;
    public int valueEndPosition = -1;
    public int annotationStartPosition = -1;
    public int annotationsLength = -1;
    public int peekIndex = -1;

    /* JADX INFO: renamed from: com.amazon.ion.impl.IonReaderBinaryIncremental$2, reason: invalid class name */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static class AnonymousClass2 implements Iterator<String> {
        @Override // java.util.Iterator
        public boolean hasNext() {
            return false;
        }

        @Override // java.util.Iterator
        public /* bridge */ /* synthetic */ String next() {
            return null;
        }

        @Override // java.util.Iterator
        public void remove() {
            throw new UnsupportedOperationException("Cannot remove from an empty iterator.");
        }

        @Override // java.util.Iterator
        /* JADX INFO: renamed from: next, reason: avoid collision after fix types in other method */
        public String next2() {
            return null;
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public class AnnotationIterator implements Iterator<String> {
        public List<Integer> annotationSids;
        public int index;

        public AnnotationIterator() {
            this.annotationSids = Collections.EMPTY_LIST;
            this.index = 0;
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.index < this.annotationSids.size();
        }

        @Override // java.util.Iterator
        public void remove() {
            throw new UnsupportedOperationException("This iterator does not support element removal.");
        }

        public void reset() {
            this.index = 0;
            this.annotationSids = IonReaderBinaryIncremental.this.getAnnotationSids();
        }

        @Override // java.util.Iterator
        public String next() {
            int iIntValue = this.annotationSids.get(this.index).intValue();
            String symbol = IonReaderBinaryIncremental.this.getSymbol(iIntValue);
            if (symbol == null) {
                throw new UnknownSymbolException(iIntValue);
            }
            this.index++;
            return symbol;
        }

        public /* synthetic */ AnnotationIterator(IonReaderBinaryIncremental ionReaderBinaryIncremental, AnonymousClass1 anonymousClass1) {
            this();
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static class ContainerInfo {
        public int endPosition;
        public IonType type;

        public ContainerInfo() {
        }

        public ContainerInfo(AnonymousClass1 anonymousClass1) {
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static class ImportLocation {
        public final String name;
        public final int sid;

        public ImportLocation(String str, int i) {
            this.name = str;
            this.sid = i;
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof ImportLocation)) {
                return false;
            }
            ImportLocation importLocation = (ImportLocation) obj;
            return getName().equals(importLocation.getName()) && getSid() == importLocation.getSid();
        }

        public String getName() {
            return this.name;
        }

        public int getSid() {
            return this.sid;
        }

        public int hashCode() {
            return (getSid() * 31) + (getName().hashCode() * 31) + 17;
        }

        public String toString() {
            return String.format("ImportLocation::{name: %s, sid: %d}", this.name, Integer.valueOf(this.sid));
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public class SingleUseAnnotationIterator extends AnnotationIterator {
        public SingleUseAnnotationIterator() {
            super();
            this.index = 0;
            this.annotationSids = new ArrayList(IonReaderBinaryIncremental.this.getAnnotationSids());
        }

        @Override // com.amazon.ion.impl.IonReaderBinaryIncremental.AnnotationIterator
        public void reset() {
            throw new IllegalStateException("Single-use annotation iterators cannot be reset.");
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static class SymbolTokenImpl implements _Private_SymbolToken {
        public final ImportLocation importLocation;
        public final int sid;
        public final String text;

        public SymbolTokenImpl(String str, int i, ImportLocation importLocation) {
            this.text = str;
            this.sid = i;
            this.importLocation = importLocation;
        }

        @Override // com.amazon.ion.SymbolToken
        public String assumeText() {
            String str = this.text;
            if (str != null) {
                return str;
            }
            throw new UnknownSymbolException(this.sid);
        }

        @Override // com.amazon.ion.impl._Private_SymbolToken
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof SymbolToken)) {
                return false;
            }
            SymbolToken symbolToken = (SymbolToken) obj;
            return (getText() == null || symbolToken.getText() == null) ? getText() == symbolToken.getText() : getText().equals(symbolToken.getText());
        }

        public ImportLocation getImportLocation() {
            return this.importLocation;
        }

        @Override // com.amazon.ion.SymbolToken
        public int getSid() {
            return this.sid;
        }

        @Override // com.amazon.ion.SymbolToken
        public String getText() {
            return this.text;
        }

        @Override // com.amazon.ion.impl._Private_SymbolToken
        public int hashCode() {
            if (getText() != null) {
                return getText().hashCode();
            }
            return 0;
        }

        public String toString() {
            return String.format("SymbolToken::{text: %s, sid: %d, importLocation: %s}", this.text, Integer.valueOf(this.sid), this.importLocation);
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static class SystemSymbolIDs {
        public static final int IMPORTS_ID = 6;
        public static final int ION_SYMBOL_TABLE_ID = 3;
        public static final int MAX_ID_ID = 8;
        public static final int NAME_ID = 4;
        public static final int SYMBOLS_ID = 7;
        public static final int VERSION_ID = 5;
    }

    static {
        List<String> listUnmodifiableList = DesugarCollections.unmodifiableList(Arrays.asList(null, SystemSymbols.ION, SystemSymbols.ION_1_0, SystemSymbols.ION_SYMBOL_TABLE, "name", "version", SystemSymbols.IMPORTS, SystemSymbols.SYMBOLS, SystemSymbols.MAX_ID, SystemSymbols.ION_SHARED_SYMBOL_TABLE));
        SYSTEM_SYMBOLS_1_0 = listUnmodifiableList;
        SYSTEM_SYMBOLS_1_0_SIZE = listUnmodifiableList.size();
        EMPTY_CATALOG = new SimpleCatalog();
        SCRATCH_FOR_SIZE = new byte[][]{new byte[0], new byte[1], new byte[2], new byte[3], new byte[4], new byte[5], new byte[6], new byte[7], new byte[8], new byte[9], new byte[10], new byte[11], new byte[12]};
        EMPTY_ITERATOR = new AnonymousClass2();
    }

    public IonReaderBinaryIncremental(IonReaderBuilder ionReaderBuilder, InputStream inputStream) {
        this.inputStream = inputStream;
        this.catalog = ionReaderBuilder.getCatalog() == null ? EMPTY_CATALOG : ionReaderBuilder.getCatalog();
        if (ionReaderBuilder.isAnnotationIteratorReuseEnabled()) {
            this.isAnnotationIteratorReuseEnabled = true;
            this.annotationIterator = new AnnotationIterator();
        } else {
            this.isAnnotationIteratorReuseEnabled = false;
            this.annotationIterator = null;
        }
        if (ionReaderBuilder.getBufferConfiguration() == null) {
            this.lookahead = new IonReaderLookaheadBuffer(STANDARD_BUFFER_CONFIGURATION, inputStream);
        } else {
            this.lookahead = new IonReaderLookaheadBuffer(ionReaderBuilder.getBufferConfiguration(), inputStream);
        }
        this.buffer = this.lookahead.pipe;
        this.containerStack = new _Private_RecyclingStack<>(8, CONTAINER_INFO_FACTORY);
        this.annotationSids = new ArrayList(8);
        ArrayList arrayList = new ArrayList(128);
        this.symbols = arrayList;
        arrayList.addAll(SYSTEM_SYMBOLS_1_0);
        this.imports = new TreeMap<>();
        this.scalarConverter = new _Private_ScalarConversions.ValueVariant();
        resetImports();
    }

    private int readVarUInt() {
        int iPeek = 0;
        int i = 0;
        while ((iPeek & 128) == 0) {
            ResizingPipedInputStream resizingPipedInputStream = this.buffer;
            int i2 = this.peekIndex;
            this.peekIndex = i2 + 1;
            iPeek = resizingPipedInputStream.peek(i2);
            i = (i << 7) | (iPeek & 127);
        }
        return i;
    }

    public final void addImport(String str, int i, int i2) {
        SymbolTable table = this.catalog.getTable(str, i);
        int i3 = this.importMaxId + i2;
        this.importMaxId = i3;
        if (table == null) {
            this.imports.put(Integer.valueOf(i3), new SubstituteSymbolTable(str, i, i2));
        } else if (table.getMaxId() == i2 && table.getVersion() == i) {
            this.imports.put(Integer.valueOf(this.importMaxId), table);
        } else {
            this.imports.put(Integer.valueOf(this.importMaxId), new SubstituteSymbolTable(table, i, i2));
        }
    }

    public final void addSymbolsFromImport(SymbolTable symbolTable, int i) {
        int maxId = SharedSymbolTable.getSystemSymbolTable(this.majorVersion).getMaxId();
        Integer numLowerKey = lowerKey(i);
        if (numLowerKey == null) {
            numLowerKey = Integer.valueOf(maxId);
        }
        int iIntValue = i - numLowerKey.intValue();
        Iterator<String> itIterateDeclaredSymbolNames = symbolTable.iterateDeclaredSymbolNames();
        for (int i2 = 1; itIterateDeclaredSymbolNames.hasNext() && i2 <= iIntValue; i2++) {
            this.symbols.add(itIterateDeclaredSymbolNames.next());
        }
    }

    public final void addSymbolsFromImports() {
        for (Map.Entry<Integer, SymbolTable> entry : this.imports.entrySet()) {
            addSymbolsFromImport(entry.getValue(), entry.getKey().intValue());
        }
    }

    @Override // com.amazon.ion.facet.Faceted
    public <T> T asFacet(Class<T> cls) {
        return null;
    }

    @Override // com.amazon.ion.IonReader
    public BigDecimal bigDecimalValue() {
        requireType(IonType.DECIMAL);
        if (isNullValue()) {
            return null;
        }
        this.peekIndex = this.valueStartPosition;
        return readBigDecimal();
    }

    @Override // com.amazon.ion.IonReader
    public BigInteger bigIntegerValue() {
        IonType ionType = this.valueType;
        if (ionType == IonType.INT) {
            if (isNullValue()) {
                return null;
            }
            IonTypeID ionTypeID = this.valueTypeID;
            if (ionTypeID.length == 0) {
                return BigInteger.ZERO;
            }
            BigInteger uIntAsBigInteger = readUIntAsBigInteger(ionTypeID.isNegativeInt);
            if (this.valueTypeID.isNegativeInt && uIntAsBigInteger.signum() == 0) {
                throw new IonException("Int zero may not be negative.");
            }
            return uIntAsBigInteger;
        }
        if (ionType == IonType.FLOAT) {
            if (isNullValue()) {
                return null;
            }
            this.scalarConverter.addValue(doubleValue());
            this.scalarConverter.setAuthoritativeType(7);
            _Private_ScalarConversions.ValueVariant valueVariant = this.scalarConverter;
            valueVariant.cast(_Private_ScalarConversions.getConversionFnid(valueVariant._authoritative_type_idx, 5));
            BigInteger bigInteger = this.scalarConverter.getBigInteger();
            this.scalarConverter.clear();
            return bigInteger;
        }
        if (ionType != IonType.DECIMAL) {
            throw new IllegalStateException("longValue() may only be called on values of type int, float, or decimal.");
        }
        if (isNullValue()) {
            return null;
        }
        this.scalarConverter.addValue(decimalValue());
        this.scalarConverter.setAuthoritativeType(6);
        _Private_ScalarConversions.ValueVariant valueVariant2 = this.scalarConverter;
        valueVariant2.cast(_Private_ScalarConversions.getConversionFnid(valueVariant2._authoritative_type_idx, 5));
        BigInteger bigInteger2 = this.scalarConverter.getBigInteger();
        this.scalarConverter.clear();
        return bigInteger2;
    }

    @Override // com.amazon.ion.IonReader
    public boolean booleanValue() {
        requireType(IonType.BOOL);
        return this.valueTypeID.lowerNibble == 1;
    }

    @Override // com.amazon.ion.IonReader
    public int byteSize() {
        if (IonType.isLob(this.valueType) || isNullValue()) {
            return this.valueEndPosition - this.valueStartPosition;
        }
        throw new IonException("Reader must be positioned on a blob or clob.");
    }

    public final void calculateEndPosition(IonTypeID ionTypeID) {
        if (ionTypeID.variableLength) {
            this.valueEndPosition = readVarUInt() + this.peekIndex;
        } else {
            this.valueEndPosition = ionTypeID.length + this.peekIndex;
        }
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        requireCompleteValue();
        this.inputStream.close();
        this.utf8Decoder.close();
    }

    public final byte[] copyBytesToScratch(int i, int i2) {
        byte[][] bArr = SCRATCH_FOR_SIZE;
        byte[] bArr2 = i2 < bArr.length ? bArr[i2] : null;
        if (bArr2 == null) {
            bArr2 = new byte[i2];
        }
        this.buffer.copyBytes(i, bArr2, 0, bArr2.length);
        return bArr2;
    }

    @Override // com.amazon.ion.IonReader
    public Date dateValue() {
        Timestamp timestampTimestampValue = timestampValue();
        if (timestampTimestampValue == null) {
            return null;
        }
        return timestampTimestampValue.dateValue();
    }

    @Override // com.amazon.ion.IonReader
    public Decimal decimalValue() {
        requireType(IonType.DECIMAL);
        if (isNullValue()) {
            return null;
        }
        this.peekIndex = this.valueStartPosition;
        return readDecimal();
    }

    @Override // com.amazon.ion.IonReader
    public double doubleValue() {
        IonType ionType = this.valueType;
        if (ionType == IonType.FLOAT) {
            int i = this.valueEndPosition;
            int i2 = this.valueStartPosition;
            int i3 = i - i2;
            if (i3 == 0) {
                return 0.0d;
            }
            return i3 == 4 ? r0.getFloat() : this.buffer.getByteBuffer(i2, i).getDouble();
        }
        if (ionType != IonType.DECIMAL) {
            throw new IllegalStateException("doubleValue() may only be called on values of type float or decimal.");
        }
        this.scalarConverter.addValue(decimalValue());
        this.scalarConverter.setAuthoritativeType(6);
        _Private_ScalarConversions.ValueVariant valueVariant = this.scalarConverter;
        valueVariant.cast(_Private_ScalarConversions.getConversionFnid(valueVariant._authoritative_type_idx, 7));
        double d = this.scalarConverter.getDouble();
        this.scalarConverter.clear();
        return d;
    }

    public final void endContainer() {
        this.valueType = null;
        this.valueTypeID = null;
        this.annotationStartPosition = -1;
        this.annotationsLength = -1;
        this.hasAnnotations = false;
    }

    public final int getAndClearSignBit(byte[] bArr) {
        byte b = bArr[0];
        boolean z = (b & 128) != 0;
        int i = z ? -1 : 1;
        if (z) {
            bArr[0] = (byte) (b & 127);
        }
        return i;
    }

    public final List<Integer> getAnnotationSids() {
        if (this.containerStack.isEmpty()) {
            return this.lookahead.annotationSids;
        }
        if (this.annotationSids.isEmpty()) {
            int i = this.peekIndex;
            int i2 = this.annotationStartPosition;
            this.peekIndex = i2;
            long j = i2 + this.annotationsLength;
            while (this.peekIndex < j) {
                this.annotationSids.add(Integer.valueOf(readVarUInt()));
            }
            this.peekIndex = i;
        }
        return this.annotationSids;
    }

    @Override // com.amazon.ion.IonReader
    public int getBytes(byte[] bArr, int i, int i2) {
        int iMin = Math.min(i2, byteSize() - this.lobBytesRead);
        this.buffer.copyBytes(this.valueStartPosition + this.lobBytesRead, bArr, i, iMin);
        this.lobBytesRead += iMin;
        return iMin;
    }

    @Override // com.amazon.ion.IonReader
    public int getDepth() {
        return this.containerStack.currentIndex + 1;
    }

    @Override // com.amazon.ion.IonReader
    public int getFieldId() {
        return this.fieldNameSid;
    }

    @Override // com.amazon.ion.IonReader
    public String getFieldName() {
        int i = this.fieldNameSid;
        if (i < 0) {
            return null;
        }
        String symbol = getSymbol(i);
        if (symbol != null) {
            return symbol;
        }
        throw new UnknownSymbolException(this.fieldNameSid);
    }

    @Override // com.amazon.ion.IonReader
    public SymbolToken getFieldNameSymbol() {
        int i = this.fieldNameSid;
        if (i < 0) {
            return null;
        }
        return getSymbolToken(i);
    }

    public final ImportLocation getImportLocation(int i) {
        int maxId = SharedSymbolTable.getSystemSymbolTable(this.majorVersion).getMaxId();
        int i2 = i - maxId;
        Map.Entry<Integer, SymbolTable> next = this.imports.tailMap(Integer.valueOf(i2)).entrySet().iterator().next();
        Integer numLowerKey = lowerKey(i2);
        if (numLowerKey == null) {
            numLowerKey = Integer.valueOf(maxId);
        }
        return new ImportLocation(next.getValue().getName(), i - numLowerKey.intValue());
    }

    @Override // com.amazon.ion.IonReader
    public IntegerSize getIntegerSize() {
        if (this.valueType != IonType.INT || isNullValue()) {
            return null;
        }
        IonTypeID ionTypeID = this.valueTypeID;
        byte b = ionTypeID.length;
        if (b < 4) {
            return IntegerSize.INT;
        }
        if (b < 8) {
            return IntegerSize.LONG;
        }
        if (b != 8) {
            return IntegerSize.BIG_INTEGER;
        }
        if (ionTypeID.isNegativeInt) {
            int iPeek = this.buffer.peek(this.valueStartPosition);
            if (iPeek < 128) {
                return IntegerSize.LONG;
            }
            if (iPeek > 128) {
                return IntegerSize.BIG_INTEGER;
            }
            int i = this.valueStartPosition;
            do {
                i++;
                if (i < this.valueEndPosition) {
                }
            } while (this.buffer.peek(i) == 0);
            return IntegerSize.BIG_INTEGER;
        }
        if (this.buffer.peek(this.valueStartPosition) > 127) {
            return IntegerSize.BIG_INTEGER;
        }
        return IntegerSize.LONG;
    }

    public final String getSymbol(int i) {
        if (i < this.symbols.size()) {
            return this.symbols.get(i);
        }
        throw new IonException("Symbol ID exceeds the max ID of the symbol table.");
    }

    @Override // com.amazon.ion.IonReader
    public SymbolTable getSymbolTable() {
        if (this.cachedReadOnlySymbolTable == null) {
            if (this.symbols.size() == SYSTEM_SYMBOLS_1_0_SIZE) {
                this.cachedReadOnlySymbolTable = SharedSymbolTable.getSystemSymbolTable(this.majorVersion);
            } else {
                this.cachedReadOnlySymbolTable = new LocalSymbolTableSnapshot();
            }
        }
        return this.cachedReadOnlySymbolTable;
    }

    public final SymbolToken getSymbolToken(int i) {
        if (this.symbolTokensById == null) {
            this.symbolTokensById = new ArrayList(this.symbols.size());
        }
        ImportLocation importLocation = null;
        if (this.symbolTokensById.size() < this.symbols.size()) {
            for (int size = this.symbolTokensById.size(); size < this.symbols.size(); size++) {
                this.symbolTokensById.add(null);
            }
        }
        if (i >= this.symbols.size()) {
            throw new IonException("Symbol ID exceeds the max ID of the symbol table.");
        }
        SymbolToken symbolToken = this.symbolTokensById.get(i);
        if (symbolToken != null) {
            return symbolToken;
        }
        String str = this.symbols.get(i);
        if (str == null) {
            if (i <= 0 || i > this.importMaxId) {
                i = 0;
            } else {
                importLocation = getImportLocation(i);
            }
        }
        SymbolTokenImpl symbolTokenImpl = new SymbolTokenImpl(str, i, importLocation);
        this.symbolTokensById.set(i, symbolTokenImpl);
        return symbolTokenImpl;
    }

    @Override // com.amazon.ion.IonReader
    public IonType getType() {
        return this.valueType;
    }

    @Override // com.amazon.ion.IonReader
    public SymbolToken[] getTypeAnnotationSymbols() {
        if (!this.hasAnnotations) {
            return SymbolToken.EMPTY_ARRAY;
        }
        List<Integer> annotationSids = getAnnotationSids();
        int size = annotationSids.size();
        SymbolToken[] symbolTokenArr = new SymbolToken[size];
        for (int i = 0; i < size; i++) {
            symbolTokenArr[i] = getSymbolToken(annotationSids.get(i).intValue());
        }
        return symbolTokenArr;
    }

    @Override // com.amazon.ion.IonReader
    public String[] getTypeAnnotations() {
        if (!this.hasAnnotations) {
            return _Private_Utils.EMPTY_STRING_ARRAY;
        }
        List<Integer> annotationSids = getAnnotationSids();
        int size = annotationSids.size();
        String[] strArr = new String[size];
        for (int i = 0; i < size; i++) {
            String symbol = getSymbol(annotationSids.get(i).intValue());
            if (symbol == null) {
                throw new UnknownSymbolException(annotationSids.get(i).intValue());
            }
            strArr[i] = symbol;
        }
        return strArr;
    }

    @Override // com.amazon.ion.IonReader
    public boolean hasNext() {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override // com.amazon.ion.IonReader
    public int intValue() {
        return (int) longValue();
    }

    @Override // com.amazon.ion.IonReader
    public boolean isInStruct() {
        return !this.containerStack.isEmpty() && this.containerStack.top.type == IonType.STRUCT;
    }

    @Override // com.amazon.ion.IonReader
    public boolean isNullValue() {
        IonTypeID ionTypeID = this.valueTypeID;
        return ionTypeID != null && ionTypeID.isNull;
    }

    @Override // com.amazon.ion.IonReader
    public Iterator<String> iterateTypeAnnotations() {
        if (!this.hasAnnotations) {
            return EMPTY_ITERATOR;
        }
        if (!this.isAnnotationIteratorReuseEnabled) {
            return new SingleUseAnnotationIterator();
        }
        this.annotationIterator.reset();
        return this.annotationIterator;
    }

    @Override // com.amazon.ion.IonReader
    public long longValue() {
        IonType ionType = this.valueType;
        if (ionType == IonType.INT) {
            if (this.valueTypeID.length == 0) {
                return 0L;
            }
            long uInt = readUInt();
            if (!this.valueTypeID.isNegativeInt) {
                return uInt;
            }
            if (uInt != 0) {
                return uInt * (-1);
            }
            throw new IonException("Int zero may not be negative.");
        }
        if (ionType == IonType.FLOAT) {
            this.scalarConverter.addValue(doubleValue());
            this.scalarConverter.setAuthoritativeType(7);
            _Private_ScalarConversions.ValueVariant valueVariant = this.scalarConverter;
            valueVariant.cast(_Private_ScalarConversions.getConversionFnid(valueVariant._authoritative_type_idx, 4));
            long j = this.scalarConverter.getLong();
            this.scalarConverter.clear();
            return j;
        }
        if (ionType != IonType.DECIMAL) {
            throw new IllegalStateException("longValue() may only be called on values of type int, float, or decimal.");
        }
        this.scalarConverter.addValue(decimalValue());
        this.scalarConverter.setAuthoritativeType(6);
        _Private_ScalarConversions.ValueVariant valueVariant2 = this.scalarConverter;
        valueVariant2.cast(_Private_ScalarConversions.getConversionFnid(valueVariant2._authoritative_type_idx, 4));
        long j2 = this.scalarConverter.getLong();
        this.scalarConverter.clear();
        return j2;
    }

    public final Integer lowerKey(int i) {
        SortedMap<Integer, SymbolTable> sortedMapHeadMap = this.imports.headMap(Integer.valueOf(i));
        if (sortedMapHeadMap.isEmpty()) {
            return null;
        }
        return sortedMapHeadMap.lastKey();
    }

    @Override // com.amazon.ion.IonReader
    public byte[] newBytes() {
        int iByteSize = byteSize();
        byte[] bArr = new byte[iByteSize];
        this.buffer.copyBytes(this.valueStartPosition, bArr, 0, iByteSize);
        return bArr;
    }

    @Override // com.amazon.ion.IonReader
    public IonType next() {
        this.fieldNameSid = -1;
        this.lobBytesRead = 0;
        this.valueStartPosition = -1;
        this.hasAnnotations = false;
        if (this.containerStack.isEmpty()) {
            nextAtTopLevel();
        } else {
            nextBelowTopLevel();
        }
        IonType ionType = this.valueType;
        if (ionType == IonType.STRUCT && this.valueTypeID.lowerNibble == 1 && this.valueStartPosition == this.valueEndPosition) {
            throw new IonException("Ordered struct must not be empty.");
        }
        return ionType;
    }

    public final void nextAtTopLevel() {
        if (this.completeValueBuffered) {
            this.buffer.seekTo(this.valueEndPosition);
            this.completeValueBuffered = false;
        }
        try {
            this.lookahead.fillInput();
            if (this.lookahead.moreDataRequired()) {
                this.valueType = null;
                this.valueTypeID = null;
                return;
            }
            this.completeValueBuffered = true;
            int i = this.lookahead.ivmSecondByteIndex;
            if (i > -1) {
                ResizingPipedInputStream resizingPipedInputStream = this.buffer;
                this.peekIndex = i + 1;
                this.majorVersion = resizingPipedInputStream.peek(i);
                ResizingPipedInputStream resizingPipedInputStream2 = this.buffer;
                int i2 = this.peekIndex;
                this.peekIndex = i2 + 1;
                this.minorVersion = resizingPipedInputStream2.peek(i2);
                ResizingPipedInputStream resizingPipedInputStream3 = this.buffer;
                int i3 = this.peekIndex;
                this.peekIndex = i3 + 1;
                if (resizingPipedInputStream3.peek(i3) != 234) {
                    throw new IonException("Invalid Ion version marker.");
                }
                requireSupportedIonVersion();
                resetSymbolTable();
                resetImports();
                this.lookahead.ivmSecondByteIndex = -1;
            } else if (this.peekIndex < 0) {
                throw new IonException("Binary Ion must start with an Ion version marker.");
            }
            List<IonReaderLookaheadBuffer.SymbolTableMarker> list = this.lookahead.symbolTableMarkers;
            if (!list.isEmpty()) {
                this.cachedReadOnlySymbolTable = null;
                Iterator<IonReaderLookaheadBuffer.SymbolTableMarker> it = list.iterator();
                while (it.hasNext()) {
                    readSymbolTable(it.next());
                }
                this.lookahead.resetSymbolTableMarkers();
            }
            IonReaderLookaheadBuffer ionReaderLookaheadBuffer = this.lookahead;
            this.peekIndex = ionReaderLookaheadBuffer.valuePostHeaderIndex;
            if (ionReaderLookaheadBuffer.annotationSids.isEmpty()) {
                IonTypeID ionTypeID = this.lookahead.valueTid;
                this.valueTypeID = ionTypeID;
                this.valueType = ionTypeID.type;
            } else {
                this.hasAnnotations = true;
                IonTypeID[] ionTypeIDArr = IonTypeID.TYPE_IDS;
                ResizingPipedInputStream resizingPipedInputStream4 = this.buffer;
                int i4 = this.peekIndex;
                this.peekIndex = i4 + 1;
                IonTypeID ionTypeID2 = ionTypeIDArr[resizingPipedInputStream4.peek(i4)];
                this.valueTypeID = ionTypeID2;
                int varUInt = ionTypeID2.length;
                if (ionTypeID2.variableLength) {
                    varUInt = readVarUInt();
                }
                IonType ionType = this.valueTypeID.type;
                this.valueType = ionType;
                if (ionType == IonTypeID.ION_TYPE_ANNOTATION_WRAPPER) {
                    throw new IonException("Nested annotations are invalid.");
                }
                if (this.peekIndex + varUInt != this.lookahead.valueEndIndex) {
                    throw new IonException("Mismatched annotation wrapper length.");
                }
            }
            this.valueStartPosition = this.peekIndex;
            IonReaderLookaheadBuffer ionReaderLookaheadBuffer2 = this.lookahead;
            this.valueEndPosition = ionReaderLookaheadBuffer2.valueEndIndex;
            ionReaderLookaheadBuffer2.nopPadStartIndex = -1;
        } catch (Exception e) {
            throw new IonException(e.getMessage(), e);
        }
    }

    public final void nextBelowTopLevel() {
        int i = this.peekIndex;
        int i2 = this.valueEndPosition;
        if (i < i2) {
            this.peekIndex = i2;
        }
        int i3 = this.peekIndex;
        ContainerInfo containerInfo = this.containerStack.top;
        if (i3 >= containerInfo.endPosition) {
            endContainer();
            return;
        }
        if (containerInfo.type == IonType.STRUCT) {
            this.fieldNameSid = readVarUInt();
        }
        IonTypeID typeId = readTypeId();
        while (typeId.isNopPad) {
            calculateEndPosition(typeId);
            int i4 = this.valueEndPosition;
            this.peekIndex = i4;
            ContainerInfo containerInfo2 = this.containerStack.top;
            if (i4 >= containerInfo2.endPosition) {
                endContainer();
                return;
            } else {
                if (containerInfo2.type == IonType.STRUCT) {
                    this.fieldNameSid = readVarUInt();
                }
                typeId = readTypeId();
            }
        }
        calculateEndPosition(typeId);
        IonType ionType = this.valueType;
        IonType ionType2 = IonTypeID.ION_TYPE_ANNOTATION_WRAPPER;
        if (ionType == ionType2) {
            this.hasAnnotations = true;
            this.annotationSids.clear();
            int varUInt = readVarUInt();
            this.annotationsLength = varUInt;
            int i5 = this.peekIndex;
            this.annotationStartPosition = i5;
            this.peekIndex = i5 + varUInt;
            IonTypeID typeId2 = readTypeId();
            if (typeId2.isNopPad) {
                throw new IonException("Invalid annotation wrapper: NOP pad may not occur inside an annotation wrapper.");
            }
            if (this.valueType == ionType2) {
                throw new IonException("Nested annotations are invalid.");
            }
            long j = this.valueEndPosition;
            calculateEndPosition(typeId2);
            if (j != this.valueEndPosition) {
                throw new IonException("Invalid annotation wrapper: end of the wrapper did not match end of the value.");
            }
        } else {
            this.annotationStartPosition = -1;
            this.annotationsLength = -1;
            this.hasAnnotations = false;
            if (this.valueEndPosition > this.containerStack.top.endPosition) {
                throw new IonException("Value overflowed its container.");
            }
        }
        if (!this.valueTypeID.isValid) {
            throw new IonException("Invalid type ID.");
        }
        this.valueStartPosition = this.peekIndex;
    }

    @Override // com.amazon.ion.impl._Private_ReaderWriter
    public SymbolTable pop_passed_symbol_table() {
        SymbolTable symbolTable = getSymbolTable();
        if (symbolTable == this.symbolTableLastTransferred) {
            return null;
        }
        this.symbolTableLastTransferred = symbolTable;
        return symbolTable;
    }

    public final BigDecimal readBigDecimal() {
        long jPeek;
        int i = this.valueEndPosition - this.peekIndex;
        if (i == 0) {
            return BigDecimal.ZERO;
        }
        int i2 = -readVarInt();
        if (i >= 8) {
            return new BigDecimal(readIntAsBigInteger(this.valueEndPosition), i2);
        }
        int i3 = this.peekIndex;
        if (i3 < this.valueEndPosition) {
            ResizingPipedInputStream resizingPipedInputStream = this.buffer;
            this.peekIndex = i3 + 1;
            int iPeek = resizingPipedInputStream.peek(i3);
            i = (iPeek & 128) != 0 ? -1 : 1;
            jPeek = iPeek & 127;
        } else {
            jPeek = 0;
        }
        while (true) {
            int i4 = this.peekIndex;
            if (i4 >= this.valueEndPosition) {
                return BigDecimal.valueOf(jPeek * ((long) i), i2);
            }
            ResizingPipedInputStream resizingPipedInputStream2 = this.buffer;
            this.peekIndex = i4 + 1;
            jPeek = (jPeek << 8) | ((long) resizingPipedInputStream2.peek(i4));
        }
    }

    public final Decimal readDecimal() {
        BigInteger bigInteger;
        if (this.valueEndPosition - this.peekIndex == 0) {
            return Decimal.ZERO;
        }
        int i = -readVarInt();
        int i2 = this.valueEndPosition;
        int i3 = this.peekIndex;
        int i4 = i2 - i3;
        if (i4 > 0) {
            byte[] bArrCopyBytesToScratch = copyBytesToScratch(i3, i4);
            int andClearSignBit = getAndClearSignBit(bArrCopyBytesToScratch);
            bigInteger = new BigInteger(andClearSignBit, bArrCopyBytesToScratch);
            if (bigInteger.signum() == 0 && andClearSignBit < 0) {
                return Decimal.negativeZero(i);
            }
        } else {
            bigInteger = BigInteger.ZERO;
        }
        return Decimal.valueOf(bigInteger, i);
    }

    public final BigInteger readIntAsBigInteger(int i) {
        int i2 = this.peekIndex;
        int i3 = i - i2;
        if (i3 <= 0) {
            return BigInteger.ZERO;
        }
        byte[] bArrCopyBytesToScratch = copyBytesToScratch(i2, i3);
        return new BigInteger(getAndClearSignBit(bArrCopyBytesToScratch), bArrCopyBytesToScratch);
    }

    public final String readString(int i, int i2) {
        return this.utf8Decoder.decode(this.buffer.getByteBuffer(i, i2), i2 - i);
    }

    public final void readSymbolTable(IonReaderLookaheadBuffer.SymbolTableMarker symbolTableMarker) {
        String strStringValue;
        int iIntValue;
        int iIntValue2;
        this.peekIndex = symbolTableMarker.startIndex;
        boolean z = false;
        int i = -1;
        int i2 = -1;
        boolean z2 = false;
        boolean z3 = false;
        while (this.peekIndex < symbolTableMarker.endIndex) {
            this.fieldNameSid = readVarUInt();
            IonTypeID typeId = readTypeId();
            calculateEndPosition(typeId);
            int i3 = this.valueEndPosition;
            int i4 = this.fieldNameSid;
            if (i4 == 6) {
                if (z) {
                    throw new IonException("Symbol table contained multiple imports fields.");
                }
                IonType ionType = typeId.type;
                if (ionType == IonType.SYMBOL) {
                    z3 = readUInt(this.peekIndex, i3) == 3;
                    this.peekIndex = i3;
                } else if (ionType == IonType.LIST) {
                    resetImports();
                    stepIn();
                    IonType next = next();
                    while (next != null) {
                        if (next == IonType.STRUCT) {
                            stepIn();
                            IonType next2 = next();
                            strStringValue = null;
                            iIntValue = -1;
                            iIntValue2 = -1;
                            while (next2 != null) {
                                int fieldId = getFieldId();
                                if (fieldId == 4) {
                                    if (next2 == IonType.STRING) {
                                        strStringValue = stringValue();
                                    }
                                } else if (fieldId == 5) {
                                    if (next2 == IonType.INT) {
                                        iIntValue = intValue();
                                    }
                                } else if (fieldId == 8 && next2 == IonType.INT) {
                                    iIntValue2 = intValue();
                                }
                                next2 = next();
                            }
                            stepOut();
                        } else {
                            strStringValue = null;
                            iIntValue = -1;
                            iIntValue2 = -1;
                        }
                        addImport(strStringValue, iIntValue, iIntValue2);
                        next = next();
                    }
                    stepOut();
                }
                if (!z3) {
                    resetSymbolTable();
                    addSymbolsFromImports();
                }
                z = true;
            } else if (i4 != 7) {
                continue;
            } else {
                if (z2) {
                    throw new IonException("Symbol table contained multiple symbols fields.");
                }
                if (typeId.type == IonType.LIST) {
                    i = this.peekIndex;
                    i2 = i3;
                }
                z2 = true;
            }
            this.peekIndex = i3;
        }
        if (!z) {
            resetSymbolTable();
            resetImports();
        }
        if (i > -1) {
            this.peekIndex = i;
            this.valueType = IonType.LIST;
            this.valueEndPosition = i2;
            stepIn();
            while (next() != null) {
                if (this.valueType != IonType.STRING) {
                    this.symbols.add(null);
                } else {
                    this.symbols.add(stringValue());
                }
            }
            stepOut();
            this.peekIndex = this.valueEndPosition;
        }
    }

    public final IonTypeID readTypeId() {
        IonTypeID[] ionTypeIDArr = IonTypeID.TYPE_IDS;
        ResizingPipedInputStream resizingPipedInputStream = this.buffer;
        int i = this.peekIndex;
        this.peekIndex = i + 1;
        IonTypeID ionTypeID = ionTypeIDArr[resizingPipedInputStream.peek(i)];
        this.valueTypeID = ionTypeID;
        this.valueType = ionTypeID.type;
        return ionTypeID;
    }

    public final long readUInt(int i, int i2) {
        long jPeek = 0;
        while (i < i2) {
            jPeek = (jPeek << 8) | ((long) this.buffer.peek(i));
            i++;
        }
        return jPeek;
    }

    public final BigInteger readUIntAsBigInteger(boolean z) {
        int i = this.valueEndPosition;
        int i2 = this.valueStartPosition;
        return new BigInteger(z ? -1 : 1, copyBytesToScratch(i2, i - i2));
    }

    public final int readVarInt(int i) {
        int i2 = (i & 64) == 0 ? 1 : -1;
        int i3 = i & 63;
        while ((i & 128) == 0) {
            ResizingPipedInputStream resizingPipedInputStream = this.buffer;
            int i4 = this.peekIndex;
            this.peekIndex = i4 + 1;
            i = resizingPipedInputStream.peek(i4);
            i3 = (i3 << 7) | (i & 127);
        }
        return i3 * i2;
    }

    @Override // com.amazon.ion.impl._Private_IncrementalReader
    public void requireCompleteValue() {
        IonReaderLookaheadBuffer ionReaderLookaheadBuffer = this.lookahead;
        if (ionReaderLookaheadBuffer.isSkippingCurrentValue) {
            throw new IonException("Unexpected EOF.");
        }
        if (ionReaderLookaheadBuffer.pipe.available() <= 0 || !this.lookahead.moreDataRequired()) {
            return;
        }
        IonReaderLookaheadBuffer ionReaderLookaheadBuffer2 = this.lookahead;
        if (ionReaderLookaheadBuffer2.ivmSecondByteIndex < 0 || ionReaderLookaheadBuffer2.pipe.available() != _Private_IonConstants.BINARY_VERSION_MARKER_SIZE) {
            throw new IonException("Unexpected EOF.");
        }
    }

    public final void requireSupportedIonVersion() {
        if (this.majorVersion != 1 || this.minorVersion != 0) {
            throw new IonException(String.format("Unsupported Ion version: %d.%d", Integer.valueOf(this.majorVersion), Integer.valueOf(this.minorVersion)));
        }
    }

    public final void requireType(IonType ionType) {
        if (ionType != this.valueType) {
            throw new IllegalStateException(String.format("Invalid type. Required %s but found %s.", ionType, this.valueType));
        }
    }

    public final void resetImports() {
        this.imports.clear();
        this.importMaxId = SharedSymbolTable.getSystemSymbolTable(this.majorVersion).getMaxId();
    }

    public final void resetSymbolTable() {
        this.symbols.clear();
        this.symbols.addAll(SYSTEM_SYMBOLS_1_0);
        this.cachedReadOnlySymbolTable = null;
        List<SymbolToken> list = this.symbolTokensById;
        if (list != null) {
            list.clear();
        }
    }

    @Override // com.amazon.ion.IonReader
    public void stepIn() {
        if (!IonType.isContainer(this.valueType)) {
            throw new IonException("Must be positioned on a container to step in.");
        }
        ContainerInfo containerInfoPush = this.containerStack.push();
        containerInfoPush.type = this.valueType;
        containerInfoPush.endPosition = this.valueEndPosition;
        this.valueType = null;
        this.valueTypeID = null;
        this.valueEndPosition = -1;
        this.fieldNameSid = -1;
        this.valueStartPosition = -1;
    }

    @Override // com.amazon.ion.IonReader
    public void stepOut() {
        if (this.containerStack.isEmpty()) {
            throw new IllegalStateException("Cannot step out at top level.");
        }
        this.valueEndPosition = this.containerStack.pop().endPosition;
        this.valueType = null;
        this.valueTypeID = null;
        this.fieldNameSid = -1;
        this.valueStartPosition = -1;
    }

    @Override // com.amazon.ion.IonReader
    public String stringValue() {
        IonType ionType = this.valueType;
        if (ionType == IonType.STRING) {
            if (isNullValue()) {
                return null;
            }
            return readString(this.valueStartPosition, this.valueEndPosition);
        }
        if (ionType != IonType.SYMBOL) {
            throw new IllegalStateException("Invalid type requested.");
        }
        if (isNullValue()) {
            return null;
        }
        int uInt = (int) readUInt();
        String symbol = getSymbol(uInt);
        if (symbol != null) {
            return symbol;
        }
        throw new UnknownSymbolException(uInt);
    }

    @Override // com.amazon.ion.IonReader
    public SymbolToken symbolValue() {
        requireType(IonType.SYMBOL);
        if (isNullValue()) {
            return null;
        }
        return getSymbolToken((int) readUInt());
    }

    @Override // com.amazon.ion.IonReader
    public Timestamp timestampValue() {
        Timestamp.Precision precision;
        BigDecimal bigDecimal;
        int i;
        int i2;
        int i3;
        int i4;
        requireType(IonType.TIMESTAMP);
        BigDecimal bigDecimal2 = null;
        if (isNullValue()) {
            return null;
        }
        int i5 = this.valueStartPosition;
        ResizingPipedInputStream resizingPipedInputStream = this.buffer;
        this.peekIndex = i5 + 1;
        int iPeek = resizingPipedInputStream.peek(i5);
        Integer numValueOf = iPeek != 192 ? Integer.valueOf(readVarInt(iPeek)) : null;
        int varUInt = readVarUInt();
        Timestamp.Precision precision2 = Timestamp.Precision.YEAR;
        int i6 = 0;
        try {
            if (this.peekIndex < this.valueEndPosition) {
                int varUInt2 = readVarUInt();
                precision = Timestamp.Precision.MONTH;
                if (this.peekIndex < this.valueEndPosition) {
                    int varUInt3 = readVarUInt();
                    Timestamp.Precision precision3 = Timestamp.Precision.DAY;
                    if (this.peekIndex < this.valueEndPosition) {
                        int varUInt4 = readVarUInt();
                        if (this.peekIndex >= this.valueEndPosition) {
                            throw new IonException("Timestamps may not specify hour without specifying minute.");
                        }
                        int varUInt5 = readVarUInt();
                        Timestamp.Precision precision4 = Timestamp.Precision.MINUTE;
                        if (this.peekIndex < this.valueEndPosition) {
                            int varUInt6 = readVarUInt();
                            Timestamp.Precision precision5 = Timestamp.Precision.SECOND;
                            if (this.peekIndex < this.valueEndPosition) {
                                bigDecimal2 = readBigDecimal();
                                if (bigDecimal2.signum() < 0 || bigDecimal2.compareTo(BigDecimal.ONE) >= 0) {
                                    throw new IonException("The fractional seconds value in a timestamp must be greaterthan or equal to zero and less than one.");
                                }
                            }
                            bigDecimal = bigDecimal2;
                            i4 = varUInt6;
                            i6 = varUInt3;
                            precision = precision5;
                        } else {
                            bigDecimal = null;
                            i6 = varUInt3;
                            precision = precision4;
                            i4 = 0;
                        }
                        i3 = varUInt5;
                        i2 = varUInt4;
                    } else {
                        bigDecimal = null;
                        i6 = varUInt3;
                        precision = precision3;
                        i2 = 0;
                        i3 = 0;
                        i4 = 0;
                    }
                    i = varUInt2;
                    return Timestamp.createFromUtcFields(precision, varUInt, i, i6, i2, i3, i4, bigDecimal, numValueOf);
                }
                i = varUInt2;
                bigDecimal = null;
            } else {
                precision = precision2;
                bigDecimal = null;
                i = 0;
            }
            return Timestamp.createFromUtcFields(precision, varUInt, i, i6, i2, i3, i4, bigDecimal, numValueOf);
        } catch (IllegalArgumentException e) {
            throw new IonException("Illegal timestamp encoding. ", e);
        }
        i2 = 0;
        i3 = 0;
        i4 = 0;
    }

    /* JADX INFO: renamed from: com.amazon.ion.impl.IonReaderBinaryIncremental$1, reason: invalid class name */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static class AnonymousClass1 implements _Private_RecyclingStack.ElementFactory<ContainerInfo> {
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.amazon.ion.impl._Private_RecyclingStack.ElementFactory
        public ContainerInfo newElement() {
            return new ContainerInfo();
        }

        @Override // com.amazon.ion.impl._Private_RecyclingStack.ElementFactory
        public ContainerInfo newElement() {
            return new ContainerInfo();
        }
    }

    private int readVarInt() {
        ResizingPipedInputStream resizingPipedInputStream = this.buffer;
        int i = this.peekIndex;
        this.peekIndex = i + 1;
        return readVarInt(resizingPipedInputStream.peek(i));
    }

    public final long readUInt() {
        return readUInt(this.valueStartPosition, this.valueEndPosition);
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public class LocalSymbolTableSnapshot implements SymbolTable, SymbolTableAsStruct {
        public final SymbolTable[] importedTables;
        public final int importedTablesMaxId;
        public final List<String> listView;
        public final Map<String, Integer> mapView;
        public final int maxId;
        public SymbolTableStructCache structCache = null;
        public final SymbolTable system;

        public LocalSymbolTableSnapshot() {
            this.system = SharedSymbolTable.getSystemSymbolTable(IonReaderBinaryIncremental.this.majorVersion);
            int size = IonReaderBinaryIncremental.this.symbols.size();
            this.maxId = size - 1;
            this.importedTablesMaxId = IonReaderBinaryIncremental.this.importMaxId;
            int i = 0;
            this.listView = new ArrayList(IonReaderBinaryIncremental.this.symbols.subList(0, size));
            this.mapView = new HashMap((int) Math.ceil(((double) size) / 0.75d), 0.75f);
            for (int i2 = 0; i2 < size; i2++) {
                String str = this.listView.get(i2);
                if (str != null) {
                    this.mapView.put(str, Integer.valueOf(i2));
                }
            }
            this.importedTables = new SymbolTable[IonReaderBinaryIncremental.this.imports.size()];
            Iterator<SymbolTable> it = IonReaderBinaryIncremental.this.imports.values().iterator();
            while (it.hasNext()) {
                this.importedTables[i] = it.next();
                i++;
            }
        }

        @Override // com.amazon.ion.SymbolTable
        public SymbolToken find(String str) {
            Integer num = this.mapView.get(str);
            if (num == null) {
                return null;
            }
            return new SymbolTokenImpl(str, num.intValue(), null);
        }

        @Override // com.amazon.ion.SymbolTable
        public String findKnownSymbol(int i) {
            if (i < 0) {
                throw new IllegalArgumentException("Symbol IDs must be at least 0.");
            }
            if (i >= IonReaderBinaryIncremental.this.symbols.size()) {
                return null;
            }
            return this.listView.get(i);
        }

        @Override // com.amazon.ion.SymbolTable
        public int findSymbol(String str) {
            Integer num = this.mapView.get(str);
            if (num == null) {
                return -1;
            }
            return num.intValue();
        }

        @Override // com.amazon.ion.SymbolTable
        public int getImportedMaxId() {
            return this.importedTablesMaxId;
        }

        @Override // com.amazon.ion.SymbolTable
        public SymbolTable[] getImportedTables() {
            return this.importedTables;
        }

        @Override // com.amazon.ion.impl.SymbolTableAsStruct
        public IonStruct getIonRepresentation(ValueFactory valueFactory) {
            if (this.structCache == null) {
                this.structCache = new SymbolTableStructCache(this, getImportedTables(), null);
            }
            return this.structCache.getIonRepresentation(valueFactory);
        }

        @Override // com.amazon.ion.SymbolTable
        public String getIonVersionId() {
            return this.system.getIonVersionId();
        }

        @Override // com.amazon.ion.SymbolTable
        public int getMaxId() {
            return this.maxId;
        }

        @Override // com.amazon.ion.SymbolTable
        public String getName() {
            return null;
        }

        @Override // com.amazon.ion.SymbolTable
        public SymbolTable getSystemSymbolTable() {
            return this.system;
        }

        @Override // com.amazon.ion.SymbolTable
        public int getVersion() {
            return 0;
        }

        @Override // com.amazon.ion.SymbolTable
        public SymbolToken intern(String str) {
            SymbolToken symbolTokenFind = find(str);
            if (symbolTokenFind != null) {
                return symbolTokenFind;
            }
            throw new ReadOnlyValueException();
        }

        @Override // com.amazon.ion.SymbolTable
        public boolean isLocalTable() {
            return true;
        }

        @Override // com.amazon.ion.SymbolTable
        public boolean isReadOnly() {
            return true;
        }

        @Override // com.amazon.ion.SymbolTable
        public boolean isSharedTable() {
            return false;
        }

        @Override // com.amazon.ion.SymbolTable
        public boolean isSubstitute() {
            return false;
        }

        @Override // com.amazon.ion.SymbolTable
        public boolean isSystemTable() {
            return false;
        }

        @Override // com.amazon.ion.SymbolTable
        public Iterator<String> iterateDeclaredSymbolNames() {
            return new Iterator<String>() { // from class: com.amazon.ion.impl.IonReaderBinaryIncremental.LocalSymbolTableSnapshot.1
                public int index;

                {
                    this.index = LocalSymbolTableSnapshot.this.getImportedMaxId() + 1;
                }

                @Override // java.util.Iterator
                public boolean hasNext() {
                    return this.index <= LocalSymbolTableSnapshot.this.getMaxId();
                }

                @Override // java.util.Iterator
                public void remove() {
                    throw new UnsupportedOperationException("This iterator does not support element removal.");
                }

                @Override // java.util.Iterator
                public String next() {
                    String str = LocalSymbolTableSnapshot.this.listView.get(this.index);
                    this.index++;
                    return str;
                }
            };
        }

        public String toString() {
            return "(LocalSymbolTable max_id:" + getMaxId() + ')';
        }

        @Override // com.amazon.ion.SymbolTable
        public void writeTo(IonWriter ionWriter) throws IOException {
            ionWriter.writeValues(new SymbolTableReader(this));
        }

        @Override // com.amazon.ion.SymbolTable
        public void makeReadOnly() {
        }
    }
}
