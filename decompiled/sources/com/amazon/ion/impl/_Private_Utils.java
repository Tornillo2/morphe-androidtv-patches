package com.amazon.ion.impl;

import com.amazon.ion.IonException;
import com.amazon.ion.IonReader;
import com.amazon.ion.IonStruct;
import com.amazon.ion.IonType;
import com.amazon.ion.IonValue;
import com.amazon.ion.SubstituteSymbolTableException;
import com.amazon.ion.SymbolTable;
import com.amazon.ion.SymbolToken;
import com.amazon.ion.SystemSymbols;
import com.amazon.ion.UnknownSymbolException;
import com.amazon.ion.ValueFactory;
import com.amazon.ion.impl.Base64Encoder;
import com.amazon.ion.impl.IonBinary;
import com.amazon.ion.impl.LocalSymbolTableAsStruct;
import com.amazon.ion.util.IonStreamUtils;
import j$.util.DesugarTimeZone;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PushbackInputStream;
import java.io.Reader;
import java.nio.CharBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.util.Collection;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import java.util.TimeZone;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public final class _Private_Utils {
    public static final /* synthetic */ boolean $assertionsDisabled = false;
    public static final String ASCII_CHARSET_NAME = "US-ASCII";
    public static final int MAX_LOOKAHEAD_UTF16 = 11;
    public static final boolean READER_HASNEXT_REMOVED = true;
    public static final String UTF8_CHARSET_NAME = "UTF-8";
    public static final byte[] EMPTY_BYTE_ARRAY = new byte[0];
    public static final String[] EMPTY_STRING_ARRAY = new String[0];
    public static final int[] EMPTY_INT_ARRAY = new int[0];
    public static final Charset ASCII_CHARSET = Charset.forName("US-ASCII");
    public static final Charset UTF8_CHARSET = Charset.forName("UTF-8");
    public static final TimeZone UTC = DesugarTimeZone.getTimeZone("UTC");
    public static final ListIterator<?> EMPTY_ITERATOR = new AnonymousClass1();

    /* JADX INFO: renamed from: com.amazon.ion.impl._Private_Utils$1, reason: invalid class name */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static class AnonymousClass1 implements ListIterator {
        @Override // java.util.ListIterator
        public void add(Object obj) {
            throw new UnsupportedOperationException();
        }

        @Override // java.util.ListIterator, java.util.Iterator
        public boolean hasNext() {
            return false;
        }

        @Override // java.util.ListIterator
        public boolean hasPrevious() {
            return false;
        }

        @Override // java.util.ListIterator, java.util.Iterator
        public Object next() {
            throw new NoSuchElementException();
        }

        @Override // java.util.ListIterator
        public int nextIndex() {
            return 0;
        }

        @Override // java.util.ListIterator
        public Object previous() {
            throw new NoSuchElementException();
        }

        @Override // java.util.ListIterator
        public int previousIndex() {
            return -1;
        }

        @Override // java.util.ListIterator, java.util.Iterator
        public void remove() {
            throw new IllegalStateException();
        }

        @Override // java.util.ListIterator
        public void set(Object obj) {
            throw new UnsupportedOperationException();
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class IntIterator implements Iterator<Integer> {
        public final int _len;
        public int _pos;
        public final int[] _values;

        public IntIterator(int[] iArr) {
            this(iArr, 0, iArr.length);
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this._pos < this._len;
        }

        @Override // java.util.Iterator
        public void remove() {
            throw new UnsupportedOperationException();
        }

        public IntIterator(int[] iArr, int i, int i2) {
            this._values = iArr;
            this._len = i2;
            this._pos = i;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // java.util.Iterator
        public Integer next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            int[] iArr = this._values;
            int i = this._pos;
            this._pos = i + 1;
            return Integer.valueOf(iArr[i]);
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class StringIterator implements Iterator<String> {
        public final int _len;
        public int _pos;
        public final String[] _values;

        public StringIterator(String[] strArr, int i) {
            this._values = strArr;
            this._len = i;
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this._pos < this._len;
        }

        @Override // java.util.Iterator
        public void remove() {
            throw new UnsupportedOperationException();
        }

        @Override // java.util.Iterator
        public String next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            String[] strArr = this._values;
            int i = this._pos;
            this._pos = i + 1;
            return strArr[i];
        }
    }

    public static <T> void addAll(Collection<T> collection, Iterator<T> it) {
        if (it != null) {
            while (it.hasNext()) {
                collection.add(it.next());
            }
        }
    }

    public static <T> void addAllNonNull(Collection<T> collection, Iterator<T> it) {
        if (it != null) {
            while (it.hasNext()) {
                T next = it.next();
                if (next != null) {
                    collection.add(next);
                }
            }
        }
    }

    public static byte[] convertUtf16UnitsToUtf8(String str) {
        int length = str.length() * 4;
        byte[] bArr = new byte[length];
        int iConvertToUTF8Bytes = 0;
        for (int i = 0; i < str.length(); i++) {
            iConvertToUTF8Bytes += IonUTF8.convertToUTF8Bytes(str.charAt(i), bArr, iConvertToUTF8Bytes, length - iConvertToUTF8Bytes);
        }
        byte[] bArr2 = new byte[iConvertToUTF8Bytes];
        System.arraycopy(bArr, 0, bArr2, 0, iConvertToUTF8Bytes);
        return bArr2;
    }

    public static SymbolTable copyLocalSymbolTable(SymbolTable symbolTable) throws SubstituteSymbolTableException {
        if (!symbolTable.isLocalTable()) {
            throw new IllegalArgumentException("symtab should be a local symtab");
        }
        LocalSymbolTable localSymbolTable = (LocalSymbolTable) symbolTable;
        for (SymbolTable symbolTable2 : localSymbolTable.getImportedTablesNoCopy()) {
            if (symbolTable2.isSubstitute()) {
                throw new SubstituteSymbolTableException("local symtabs with substituted symtabs for imports (indicating no exact match within the catalog) cannot be copied");
            }
        }
        return localSymbolTable.makeCopy();
    }

    public static byte[] copyOf(byte[] bArr, int i) {
        byte[] bArr2 = new byte[i];
        System.arraycopy(bArr, 0, bArr2, 0, Math.min(i, bArr.length));
        return bArr2;
    }

    public static String decode(byte[] bArr, Charset charset) {
        try {
            return charset.newDecoder().decode(java.nio.ByteBuffer.wrap(bArr)).toString();
        } catch (CharacterCodingException e) {
            throw new IonException("Input is not valid " + charset.displayName() + " data", e);
        }
    }

    public static final <T> ListIterator<T> emptyIterator() {
        return (ListIterator<T>) EMPTY_ITERATOR;
    }

    public static byte[] encode(String str, Charset charset) {
        try {
            java.nio.ByteBuffer byteBufferEncode = charset.newEncoder().encode(CharBuffer.wrap(str));
            byte[] bArrArray = byteBufferEncode.array();
            int iLimit = byteBufferEncode.limit();
            return iLimit < bArrArray.length ? copyOf(bArrArray, iLimit) : bArrArray;
        } catch (CharacterCodingException e) {
            throw new IonException("Invalid string data", e);
        }
    }

    public static final int getSidForSymbolTableField(String str) {
        if (str == null || str.length() < 4) {
            return -1;
        }
        char cCharAt = str.charAt(0);
        return cCharAt != 'i' ? cCharAt != 's' ? cCharAt != 'v' ? cCharAt != 'm' ? (cCharAt == 'n' && "name".equals(str)) ? 4 : -1 : SystemSymbols.MAX_ID.equals(str) ? 8 : -1 : "version".equals(str) ? 5 : -1 : SystemSymbols.SYMBOLS.equals(str) ? 7 : -1 : SystemSymbols.IMPORTS.equals(str) ? 6 : -1;
    }

    public static SymbolTable initialSymtab(_Private_LocalSymbolTableFactory _private_localsymboltablefactory, SymbolTable symbolTable, SymbolTable... symbolTableArr) {
        return (symbolTableArr == null || symbolTableArr.length == 0) ? symbolTable : (symbolTableArr.length == 1 && symbolTableArr[0].isSystemTable()) ? symbolTableArr[0] : _private_localsymboltablefactory.newLocalSymtab(symbolTable, symbolTableArr);
    }

    public static final Iterator<Integer> intIterator(int... iArr) {
        return (iArr == null || iArr.length == 0) ? EMPTY_ITERATOR : new IntIterator(iArr, 0, iArr.length);
    }

    public static boolean isNonSymbolScalar(IonType ionType) {
        return (IonType.isContainer(ionType) || ionType.equals(IonType.SYMBOL)) ? false : true;
    }

    public static boolean isTrivialTable(SymbolTable symbolTable) {
        if (symbolTable == null || symbolTable.isSystemTable()) {
            return true;
        }
        return symbolTable.isLocalTable() && symbolTable.getMaxId() == symbolTable.getSystemSymbolTable().getMaxId();
    }

    public static Iterator<IonValue> iterate(ValueFactory valueFactory, IonReader ionReader) {
        return new IonIteratorImpl(valueFactory, ionReader);
    }

    public static byte[] loadFileBytes(File file) throws IOException {
        long length = file.length();
        if (length < 0 || length > 2147483647L) {
            throw new IllegalArgumentException("File too long: " + file);
        }
        byte[] bArr = new byte[(int) length];
        FileInputStream fileInputStream = new FileInputStream(file);
        try {
            if (fileInputStream.read(bArr) == length && fileInputStream.read() == -1) {
                return bArr;
            }
            throw new IOException("Read the wrong number of bytes from " + file);
        } finally {
            fileInputStream.close();
        }
    }

    public static String loadReader(Reader reader) throws IOException {
        StringBuilder sb = new StringBuilder(2048);
        char[] cArr = new char[2048];
        while (true) {
            int i = reader.read(cArr);
            if (i == -1) {
                return sb.toString();
            }
            sb.append(cArr, 0, i);
        }
    }

    public static byte[] loadStreamBytes(InputStream inputStream) throws IOException {
        IonBinary.Reader reader = new IonBinary.BufferManager(inputStream)._reader;
        reader.sync();
        reader.setPosition(0);
        return reader.getBytes();
    }

    public static boolean localSymtabExtends(SymbolTable symbolTable, SymbolTable symbolTable2) {
        if (symbolTable2.getMaxId() <= symbolTable.getMaxId()) {
            SymbolTable[] importedTables = symbolTable.getImportedTables();
            SymbolTable[] importedTables2 = symbolTable2.getImportedTables();
            if (importedTables.length == importedTables2.length) {
                int i = 0;
                while (true) {
                    if (i >= importedTables.length) {
                        Iterator<String> itIterateDeclaredSymbolNames = symbolTable.iterateDeclaredSymbolNames();
                        Iterator<String> itIterateDeclaredSymbolNames2 = symbolTable2.iterateDeclaredSymbolNames();
                        while (itIterateDeclaredSymbolNames2.hasNext()) {
                            if (!itIterateDeclaredSymbolNames2.next().equals(itIterateDeclaredSymbolNames.next())) {
                            }
                        }
                        return true;
                    }
                    SymbolTable symbolTable3 = importedTables[i];
                    SymbolTable symbolTable4 = importedTables2[i];
                    if (!symbolTable3.getName().equals(symbolTable4.getName()) || symbolTable3.getVersion() != symbolTable4.getVersion()) {
                        break;
                    }
                    i++;
                }
            }
        }
        return false;
    }

    public static SymbolToken localize(SymbolTable symbolTable, SymbolToken symbolToken) {
        String text = symbolToken.getText();
        int sid = symbolToken.getSid();
        if (symbolTable != null) {
            if (text == null) {
                String strFindKnownSymbol = symbolTable.findKnownSymbol(sid);
                if (strFindKnownSymbol != null) {
                    return new SymbolTokenImpl(strFindKnownSymbol, sid);
                }
            } else {
                SymbolToken symbolTokenFind = symbolTable.find(text);
                if (symbolTokenFind != null) {
                    return symbolTokenFind;
                }
                if (sid >= 0) {
                    return new SymbolTokenImpl(text, -1);
                }
            }
        } else if (text != null && sid >= 0) {
            return new SymbolTokenImpl(text, -1);
        }
        return symbolToken;
    }

    @Deprecated
    public static _Private_LocalSymbolTableFactory newLocalSymbolTableAsStructFactory(ValueFactory valueFactory) {
        return new LocalSymbolTableAsStruct.Factory(valueFactory);
    }

    public static SymbolTable newSharedSymtab(IonStruct ionStruct) {
        return SharedSymbolTable.newSharedSymbolTable(ionStruct);
    }

    public static SymbolTable newSubstituteSymtab(SymbolTable symbolTable, int i, int i2) {
        return new SubstituteSymbolTable(symbolTable, i, i2);
    }

    public static SymbolTokenImpl newSymbolToken(String str, int i) {
        return new SymbolTokenImpl(str, i);
    }

    public static SymbolToken[] newSymbolTokens(SymbolTable symbolTable, String... strArr) {
        int length;
        if (strArr == null || (length = strArr.length) == 0) {
            return SymbolToken.EMPTY_ARRAY;
        }
        SymbolToken[] symbolTokenArr = new SymbolToken[length];
        for (int i = 0; i < length; i++) {
            symbolTokenArr[i] = newSymbolToken(symbolTable, strArr[i]);
        }
        return symbolTokenArr;
    }

    public static int readFully(InputStream inputStream, byte[] bArr) throws IOException {
        return readFully(inputStream, bArr, 0, bArr.length);
    }

    public static boolean safeEquals(Object obj, Object obj2) {
        return obj != null ? obj.equals(obj2) : obj2 == null;
    }

    public static boolean streamIsIonBinary(PushbackInputStream pushbackInputStream) throws IOException, IonException {
        int i = _Private_IonConstants.BINARY_VERSION_MARKER_SIZE;
        byte[] bArr = new byte[i];
        int fully = readFully(pushbackInputStream, bArr, 0, i);
        boolean zIsIonBinary = fully == i ? IonStreamUtils.isIonBinary(bArr) : false;
        if (fully > 0) {
            pushbackInputStream.unread(bArr, 0, fully);
        }
        return zIsIonBinary;
    }

    public static final Iterator<String> stringIterator(String... strArr) {
        return (strArr == null || strArr.length == 0) ? EMPTY_ITERATOR : new StringIterator(strArr, strArr.length);
    }

    public static boolean symtabExtends(SymbolTable symbolTable, SymbolTable symbolTable2) {
        if (symbolTable == symbolTable2 || symbolTable2.isSystemTable()) {
            return true;
        }
        return symbolTable.isLocalTable() ? ((symbolTable instanceof LocalSymbolTable) && (symbolTable2 instanceof LocalSymbolTable)) ? ((LocalSymbolTable) symbolTable).symtabExtends(symbolTable2) : localSymtabExtends(symbolTable, symbolTable2) : symbolTable2.getMaxId() == symbolTable.getMaxId();
    }

    public static boolean symtabIsLocalAndNonTrivial(SymbolTable symbolTable) {
        if (symbolTable != null && symbolTable.isLocalTable()) {
            return symbolTable.getImportedTables().length > 0 || symbolTable.getImportedMaxId() < symbolTable.getMaxId();
        }
        return false;
    }

    public static final boolean symtabIsSharedNotSystem(SymbolTable symbolTable) {
        return (symbolTable == null || !symbolTable.isSharedTable() || symbolTable.isSystemTable()) ? false : true;
    }

    public static IonStruct symtabTree(SymbolTable symbolTable, ValueFactory valueFactory) {
        SymbolTableAsStruct symbolTableAsStruct;
        if (symbolTable instanceof SymbolTableAsStruct) {
            symbolTableAsStruct = (SymbolTableAsStruct) symbolTable;
        } else {
            LocalSymbolTableAsStruct localSymbolTableAsStruct = (LocalSymbolTableAsStruct) new LocalSymbolTableAsStruct.Factory(valueFactory).newLocalSymtab(symbolTable.getSystemSymbolTable(), symbolTable.getImportedTables());
            Iterator<String> itIterateDeclaredSymbolNames = symbolTable.iterateDeclaredSymbolNames();
            while (itIterateDeclaredSymbolNames.hasNext()) {
                String next = itIterateDeclaredSymbolNames.next();
                if (next != null) {
                    localSymbolTableAsStruct.intern(next);
                }
            }
            symbolTableAsStruct = localSymbolTableAsStruct;
        }
        return symbolTableAsStruct.getIonRepresentation(valueFactory);
    }

    public static SymbolTable systemSymtab(int i) {
        return SharedSymbolTable.getSystemSymbolTable(i);
    }

    public static int[] toSids(SymbolToken[] symbolTokenArr, int i) {
        if (i == 0) {
            return EMPTY_INT_ARRAY;
        }
        int[] iArr = new int[i];
        for (int i2 = 0; i2 < i; i2++) {
            iArr[i2] = symbolTokenArr[i2].getSid();
        }
        return iArr;
    }

    public static String[] toStrings(SymbolToken[] symbolTokenArr, int i) {
        if (i == 0) {
            return EMPTY_STRING_ARRAY;
        }
        String[] strArr = new String[i];
        for (int i2 = 0; i2 < i; i2++) {
            SymbolToken symbolToken = symbolTokenArr[i2];
            String text = symbolToken.getText();
            if (text == null) {
                throw new UnknownSymbolException(symbolToken.getSid());
            }
            strArr[i2] = text;
        }
        return strArr;
    }

    public static byte[] utf8(String str) {
        return encode(str, UTF8_CHARSET);
    }

    public static String utf8FileToString(File file) throws IOException, IonException {
        return decode(loadFileBytes(file), UTF8_CHARSET);
    }

    public static boolean valueIsLocalSymbolTable(_Private_IonValue _private_ionvalue) {
        return (_private_ionvalue instanceof IonStruct) && _private_ionvalue.findTypeAnnotation(SystemSymbols.ION_SYMBOL_TABLE) == 0;
    }

    public static void writeAsBase64(InputStream inputStream, Appendable appendable) throws IOException {
        Base64Encoder.TextStream textStream = new Base64Encoder.TextStream(inputStream);
        while (true) {
            int i = textStream.read();
            if (i == -1) {
                return;
            } else {
                appendable.append((char) i);
            }
        }
    }

    public static SymbolTable newSharedSymtab(IonReader ionReader, boolean z) {
        return SharedSymbolTable.newSharedSymbolTable(ionReader, z);
    }

    public static SymbolTokenImpl newSymbolToken(int i) {
        return new SymbolTokenImpl(i);
    }

    public static int readFully(InputStream inputStream, byte[] bArr, int i, int i2) throws IOException {
        int i3 = 0;
        while (i3 < i2) {
            int i4 = inputStream.read(bArr, i, i2 - i3);
            if (i4 < 0) {
                break;
            }
            i3 += i4;
            i += i4;
        }
        return i3;
    }

    public static String utf8(byte[] bArr) {
        return decode(bArr, UTF8_CHARSET);
    }

    public static SymbolTable newSharedSymtab(String str, int i, SymbolTable symbolTable, Iterator<String> it) {
        return SharedSymbolTable.newSharedSymbolTable(str, i, symbolTable, it);
    }

    public static SymbolToken newSymbolToken(SymbolTable symbolTable, String str) {
        str.getClass();
        SymbolToken symbolTokenFind = symbolTable == null ? null : symbolTable.find(str);
        return symbolTokenFind == null ? new SymbolTokenImpl(str, -1) : symbolTokenFind;
    }

    public static final Iterator<String> stringIterator(String[] strArr, int i) {
        if (strArr != null && strArr.length != 0 && i != 0) {
            return new StringIterator(strArr, i);
        }
        return EMPTY_ITERATOR;
    }

    public static String[] copyOf(String[] strArr, int i) {
        String[] strArr2 = new String[i];
        System.arraycopy(strArr, 0, strArr2, 0, Math.min(i, strArr.length));
        return strArr2;
    }

    public static final Iterator<Integer> intIterator(int[] iArr, int i) {
        if (iArr != null && iArr.length != 0 && i != 0) {
            return new IntIterator(iArr, 0, i);
        }
        return EMPTY_ITERATOR;
    }

    public static SymbolToken newSymbolToken(SymbolTable symbolTable, int i) {
        if (i >= 1) {
            return new SymbolTokenImpl(symbolTable == null ? null : symbolTable.findKnownSymbol(i), i);
        }
        throw new IllegalArgumentException();
    }

    public static SymbolToken[] newSymbolTokens(SymbolTable symbolTable, int... iArr) {
        if (iArr != null) {
            int length = iArr.length;
            if (iArr.length != 0) {
                SymbolToken[] symbolTokenArr = new SymbolToken[length];
                for (int i = 0; i < length; i++) {
                    symbolTokenArr[i] = newSymbolToken(symbolTable, iArr[i]);
                }
                return symbolTokenArr;
            }
        }
        return SymbolToken.EMPTY_ARRAY;
    }

    public static void localize(SymbolTable symbolTable, SymbolToken[] symbolTokenArr, int i) {
        for (int i2 = 0; i2 < i; i2++) {
            SymbolToken symbolToken = symbolTokenArr[i2];
            SymbolToken symbolTokenLocalize = localize(symbolTable, symbolToken);
            if (symbolTokenLocalize != symbolToken) {
                symbolTokenArr[i2] = symbolTokenLocalize;
            }
        }
    }

    public static void localize(SymbolTable symbolTable, SymbolToken[] symbolTokenArr) {
        localize(symbolTable, symbolTokenArr, symbolTokenArr.length);
    }
}
