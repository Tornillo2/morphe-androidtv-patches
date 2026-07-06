package com.amazon.ion.impl;

import com.amazon.ion.Decimal;
import com.amazon.ion.IntegerSize;
import com.amazon.ion.IonBool;
import com.amazon.ion.IonContainer;
import com.amazon.ion.IonDatagram;
import com.amazon.ion.IonDecimal;
import com.amazon.ion.IonException;
import com.amazon.ion.IonFloat;
import com.amazon.ion.IonInt;
import com.amazon.ion.IonLob;
import com.amazon.ion.IonNull;
import com.amazon.ion.IonReader;
import com.amazon.ion.IonStruct;
import com.amazon.ion.IonSymbol;
import com.amazon.ion.IonSystem;
import com.amazon.ion.IonText;
import com.amazon.ion.IonTimestamp;
import com.amazon.ion.IonType;
import com.amazon.ion.IonValue;
import com.amazon.ion.SymbolTable;
import com.amazon.ion.SymbolToken;
import com.amazon.ion.Timestamp;
import com.amazon.ion.impl._Private_IonValue;
import com.amazon.ion.impl.lite.IonSystemLite;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.Iterator;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public class IonReaderTreeSystem implements IonReader, _Private_ReaderWriter {
    public static final /* synthetic */ boolean $assertionsDisabled = false;
    public _Private_IonValue _curr;
    public boolean _eof;
    public Iterator<IonValue> _iter;
    public _Private_IonValue _next;
    public IonValue _parent;
    public Object[] _stack = new Object[10];
    public final _Private_IonValue.SymbolTableProvider _symbolTableAccessor;
    public final SymbolTable _system_symtab;
    public int _top;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class Children implements Iterator<IonValue> {
        public IonValue _curr;
        public boolean _eof;
        public int _next_idx;
        public _Private_IonContainer _parent;

        public Children(IonContainer ionContainer) {
            if (!(ionContainer instanceof _Private_IonContainer)) {
                throw new UnsupportedOperationException("this only supports IonContainerImpl instances");
            }
            _Private_IonContainer _private_ioncontainer = (_Private_IonContainer) ionContainer;
            this._parent = _private_ioncontainer;
            this._next_idx = 0;
            this._curr = null;
            if (_private_ioncontainer.isNullValue()) {
                this._eof = true;
            }
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            if (this._eof) {
                return false;
            }
            int i = this._parent.get_child_count();
            int i2 = this._next_idx;
            if (i2 > 0) {
                int i3 = i2 - 1;
                this._next_idx = i;
                while (true) {
                    if (i3 >= i) {
                        break;
                    }
                    if (this._curr == this._parent.get_child(i3)) {
                        this._next_idx = i3 + 1;
                        break;
                    }
                    i3++;
                }
            }
            if (this._next_idx >= this._parent.get_child_count()) {
                this._eof = true;
            }
            return !this._eof;
        }

        @Override // java.util.Iterator
        public void remove() {
            throw new UnsupportedOperationException();
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // java.util.Iterator
        public IonValue next() {
            if (hasNext()) {
                this._curr = this._parent.get_child(this._next_idx);
                this._next_idx++;
            } else {
                this._curr = null;
            }
            return this._curr;
        }
    }

    public IonReaderTreeSystem(IonValue ionValue) {
        if (ionValue == null) {
            this._system_symtab = null;
            this._symbolTableAccessor = null;
        } else {
            this._system_symtab = ((IonSystemLite) ionValue.getSystem())._system_symbol_table;
            re_init(ionValue, false);
            this._symbolTableAccessor = new _Private_IonValue.SymbolTableProvider() { // from class: com.amazon.ion.impl.IonReaderTreeSystem.1
                @Override // com.amazon.ion.impl._Private_IonValue.SymbolTableProvider
                public SymbolTable getSymbolTable() {
                    return IonReaderTreeSystem.this.getSymbolTable();
                }
            };
        }
    }

    @Override // com.amazon.ion.facet.Faceted
    public <T> T asFacet(Class<T> cls) {
        return null;
    }

    @Override // com.amazon.ion.IonReader
    public BigDecimal bigDecimalValue() {
        _Private_IonValue _private_ionvalue = this._curr;
        if (_private_ionvalue instanceof IonDecimal) {
            return ((IonDecimal) _private_ionvalue).bigDecimalValue();
        }
        throw new IllegalStateException("current value is not an ion decimal");
    }

    @Override // com.amazon.ion.IonReader
    public BigInteger bigIntegerValue() {
        _Private_IonValue _private_ionvalue = this._curr;
        if (_private_ionvalue instanceof IonInt) {
            return ((IonInt) _private_ionvalue).bigIntegerValue();
        }
        if (_private_ionvalue instanceof IonFloat) {
            BigDecimal bigDecimalBigDecimalValue = ((IonFloat) _private_ionvalue).bigDecimalValue();
            if (bigDecimalBigDecimalValue == null) {
                return null;
            }
            return bigDecimalBigDecimalValue.toBigInteger();
        }
        if (!(_private_ionvalue instanceof IonDecimal)) {
            throw new IllegalStateException("current value is not an ion int, float, or decimal");
        }
        BigDecimal bigDecimalBigDecimalValue2 = ((IonDecimal) _private_ionvalue).bigDecimalValue();
        if (bigDecimalBigDecimalValue2 == null) {
            return null;
        }
        return bigDecimalBigDecimalValue2.toBigInteger();
    }

    @Override // com.amazon.ion.IonReader
    public boolean booleanValue() {
        _Private_IonValue _private_ionvalue = this._curr;
        if (_private_ionvalue instanceof IonBool) {
            return ((IonBool) _private_ionvalue).booleanValue();
        }
        throw new IllegalStateException("current value is not a boolean");
    }

    @Override // com.amazon.ion.IonReader
    public int byteSize() {
        _Private_IonValue _private_ionvalue = this._curr;
        if (_private_ionvalue instanceof IonLob) {
            return ((IonLob) _private_ionvalue).byteSize();
        }
        throw new IllegalStateException("current value is not an ion blob or clob");
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        this._eof = true;
    }

    @Override // com.amazon.ion.IonReader
    public Date dateValue() {
        _Private_IonValue _private_ionvalue = this._curr;
        if (_private_ionvalue instanceof IonTimestamp) {
            return ((IonTimestamp) _private_ionvalue).dateValue();
        }
        throw new IllegalStateException("current value is not an ion timestamp");
    }

    @Override // com.amazon.ion.IonReader
    public Decimal decimalValue() {
        _Private_IonValue _private_ionvalue = this._curr;
        if (_private_ionvalue instanceof IonDecimal) {
            return ((IonDecimal) _private_ionvalue).decimalValue();
        }
        throw new IllegalStateException("current value is not an ion decimal");
    }

    @Override // com.amazon.ion.IonReader
    public double doubleValue() {
        _Private_IonValue _private_ionvalue = this._curr;
        if (_private_ionvalue instanceof IonFloat) {
            return ((IonFloat) _private_ionvalue).doubleValue();
        }
        if (_private_ionvalue instanceof IonDecimal) {
            return ((IonDecimal) _private_ionvalue).doubleValue();
        }
        throw new IllegalStateException("current value is not an ion float or decimal");
    }

    @Override // com.amazon.ion.IonReader
    public int getBytes(byte[] bArr, int i, int i2) {
        _Private_IonValue _private_ionvalue = this._curr;
        if (!(_private_ionvalue instanceof IonLob)) {
            throw new IllegalStateException("current value is not an ion blob or clob");
        }
        IonLob ionLob = (IonLob) _private_ionvalue;
        int iByteSize = ionLob.byteSize();
        if (iByteSize > i2) {
            throw new IllegalArgumentException("insufficient space in buffer for this value");
        }
        InputStream inputStreamNewInputStream = ionLob.newInputStream();
        try {
            int fully = _Private_Utils.readFully(inputStreamNewInputStream, bArr, 0, iByteSize);
            inputStreamNewInputStream.close();
            return fully;
        } catch (IOException e) {
            throw new IonException(e.getMessage(), e);
        }
    }

    @Override // com.amazon.ion.IonReader
    public final int getDepth() {
        return this._top / 2;
    }

    @Override // com.amazon.ion.IonReader
    public int getFieldId() {
        _Private_IonValue _private_ionvalue = this._curr;
        if (_private_ionvalue == null || this._top == 0) {
            return -1;
        }
        return _private_ionvalue.getFieldId();
    }

    @Override // com.amazon.ion.IonReader
    public String getFieldName() {
        _Private_IonValue _private_ionvalue = this._curr;
        if (_private_ionvalue == null || this._top == 0) {
            return null;
        }
        return _private_ionvalue.getFieldName();
    }

    @Override // com.amazon.ion.IonReader
    public final SymbolToken getFieldNameSymbol() {
        _Private_IonValue _private_ionvalue = this._curr;
        if (_private_ionvalue == null || this._top == 0) {
            return null;
        }
        return _private_ionvalue.getFieldNameSymbol(this._symbolTableAccessor);
    }

    @Override // com.amazon.ion.IonReader
    public IntegerSize getIntegerSize() {
        _Private_IonValue _private_ionvalue = this._curr;
        if (_private_ionvalue instanceof IonInt) {
            return ((IonInt) _private_ionvalue).getIntegerSize();
        }
        return null;
    }

    public IonValue getIonValue(IonSystem ionSystem) {
        return this._curr;
    }

    @Override // com.amazon.ion.IonReader
    public SymbolTable getSymbolTable() {
        return this._system_symtab;
    }

    @Override // com.amazon.ion.IonReader
    public IonType getType() {
        _Private_IonValue _private_ionvalue = this._curr;
        if (_private_ionvalue == null) {
            return null;
        }
        return _private_ionvalue.getType();
    }

    @Override // com.amazon.ion.IonReader
    public final SymbolToken[] getTypeAnnotationSymbols() {
        _Private_IonValue _private_ionvalue = this._curr;
        if (_private_ionvalue != null) {
            return _private_ionvalue.getTypeAnnotationSymbols(this._symbolTableAccessor);
        }
        throw new IllegalStateException();
    }

    @Override // com.amazon.ion.IonReader
    public final String[] getTypeAnnotations() {
        _Private_IonValue _private_ionvalue = this._curr;
        if (_private_ionvalue != null) {
            return _private_ionvalue.getTypeAnnotations();
        }
        throw new IllegalStateException();
    }

    @Override // com.amazon.ion.IonReader
    public boolean hasNext() {
        return next_helper_system() != null;
    }

    @Override // com.amazon.ion.IonReader
    public int intValue() {
        _Private_IonValue _private_ionvalue = this._curr;
        if (_private_ionvalue instanceof IonInt) {
            return ((IonInt) _private_ionvalue).intValue();
        }
        if (_private_ionvalue instanceof IonFloat) {
            return (int) ((IonFloat) _private_ionvalue).doubleValue();
        }
        if (_private_ionvalue instanceof IonDecimal) {
            return (int) ((IonDecimal) _private_ionvalue).doubleValue();
        }
        throw new IllegalStateException("current value is not an ion int, float, or decimal");
    }

    @Override // com.amazon.ion.IonReader
    public boolean isInStruct() {
        return this._top / 2 > 0 && (this._parent instanceof IonStruct);
    }

    @Override // com.amazon.ion.IonReader
    public boolean isNullValue() {
        _Private_IonValue _private_ionvalue = this._curr;
        if (_private_ionvalue instanceof IonNull) {
            return true;
        }
        if (_private_ionvalue != null) {
            return _private_ionvalue.isNullValue();
        }
        throw new IllegalStateException("must call next() before isNullValue()");
    }

    @Override // com.amazon.ion.IonReader
    public final Iterator<String> iterateTypeAnnotations() {
        return _Private_Utils.stringIterator(getTypeAnnotations());
    }

    @Override // com.amazon.ion.IonReader
    public long longValue() {
        _Private_IonValue _private_ionvalue = this._curr;
        if (_private_ionvalue instanceof IonInt) {
            return ((IonInt) _private_ionvalue).longValue();
        }
        if (_private_ionvalue instanceof IonFloat) {
            return (long) ((IonFloat) _private_ionvalue).doubleValue();
        }
        if (_private_ionvalue instanceof IonDecimal) {
            return (long) ((IonDecimal) _private_ionvalue).doubleValue();
        }
        throw new IllegalStateException("current value is not an ion int, float, or decimal");
    }

    @Override // com.amazon.ion.IonReader
    public byte[] newBytes() {
        _Private_IonValue _private_ionvalue = this._curr;
        if (!(_private_ionvalue instanceof IonLob)) {
            throw new IllegalStateException("current value is not an ion blob or clob");
        }
        IonLob ionLob = (IonLob) _private_ionvalue;
        int iByteSize = ionLob.byteSize();
        byte[] bArr = new byte[iByteSize];
        InputStream inputStreamNewInputStream = ionLob.newInputStream();
        try {
            _Private_Utils.readFully(inputStreamNewInputStream, bArr, 0, iByteSize);
            inputStreamNewInputStream.close();
            return bArr;
        } catch (IOException e) {
            throw new IonException(e.getMessage(), e);
        }
    }

    @Override // com.amazon.ion.IonReader
    public IonType next() {
        if (this._next == null && !hasNext()) {
            this._curr = null;
            return null;
        }
        _Private_IonValue _private_ionvalue = this._next;
        this._curr = _private_ionvalue;
        this._next = null;
        return _private_ionvalue.getType();
    }

    public IonType next_helper_system() {
        if (this._eof) {
            return null;
        }
        _Private_IonValue _private_ionvalue = this._next;
        if (_private_ionvalue != null) {
            return _private_ionvalue.getType();
        }
        Iterator<IonValue> it = this._iter;
        if (it != null && it.hasNext()) {
            this._next = (_Private_IonValue) this._iter.next();
        }
        _Private_IonValue _private_ionvalue2 = this._next;
        boolean z = _private_ionvalue2 == null;
        this._eof = z;
        if (z) {
            return null;
        }
        return _private_ionvalue2.getType();
    }

    public final void pop() {
        int i = this._top;
        int i2 = i - 1;
        this._top = i2;
        Object[] objArr = this._stack;
        this._iter = (Iterator) objArr[i2];
        objArr[i2] = null;
        int i3 = i - 2;
        this._top = i3;
        this._parent = (IonValue) objArr[i3];
        objArr[i3] = null;
        this._eof = false;
    }

    @Override // com.amazon.ion.impl._Private_ReaderWriter
    public SymbolTable pop_passed_symbol_table() {
        return null;
    }

    public final void push() {
        Object[] objArr = this._stack;
        int length = objArr.length;
        if (this._top + 1 >= length) {
            Object[] objArr2 = new Object[length * 2];
            System.arraycopy(objArr, 0, objArr2, 0, length);
            this._stack = objArr2;
        }
        Object[] objArr3 = this._stack;
        int i = this._top;
        int i2 = i + 1;
        this._top = i2;
        objArr3[i] = this._parent;
        this._top = i + 2;
        objArr3[i2] = this._iter;
    }

    public void re_init(IonValue ionValue, boolean z) {
        this._curr = null;
        this._eof = false;
        this._top = 0;
        if (!(ionValue instanceof IonDatagram)) {
            this._parent = z ? null : ionValue.getContainer();
            this._next = (_Private_IonValue) ionValue;
        } else {
            IonDatagram ionDatagram = (IonDatagram) ionValue;
            this._parent = ionDatagram;
            this._next = null;
            this._iter = ionDatagram.systemIterator();
        }
    }

    @Override // com.amazon.ion.IonReader
    public final void stepIn() {
        if (!(this._curr instanceof IonContainer)) {
            throw new IllegalStateException("current value must be a container");
        }
        push();
        _Private_IonValue _private_ionvalue = this._curr;
        this._parent = _private_ionvalue;
        this._iter = new Children((IonContainer) _private_ionvalue);
        this._curr = null;
    }

    @Override // com.amazon.ion.IonReader
    public final void stepOut() {
        if (this._top < 1) {
            throw new IllegalStateException(IonMessages.CANNOT_STEP_OUT);
        }
        pop();
        this._curr = null;
    }

    @Override // com.amazon.ion.IonReader
    public String stringValue() {
        _Private_IonValue _private_ionvalue = this._curr;
        if (_private_ionvalue instanceof IonText) {
            return ((IonText) _private_ionvalue).stringValue();
        }
        throw new IllegalStateException("current value is not a symbol or string");
    }

    @Override // com.amazon.ion.IonReader
    public SymbolToken symbolValue() {
        _Private_IonValue _private_ionvalue = this._curr;
        if (!(_private_ionvalue instanceof IonSymbol)) {
            throw new IllegalStateException();
        }
        if (_private_ionvalue.isNullValue()) {
            return null;
        }
        return ((IonSymbol) this._curr).symbolValue();
    }

    @Override // com.amazon.ion.IonReader
    public Timestamp timestampValue() {
        _Private_IonValue _private_ionvalue = this._curr;
        if (_private_ionvalue instanceof IonTimestamp) {
            return ((IonTimestamp) _private_ionvalue).timestampValue();
        }
        throw new IllegalStateException("current value is not a timestamp");
    }

    public String valueToString() {
        _Private_IonValue _private_ionvalue = this._curr;
        if (_private_ionvalue == null) {
            return null;
        }
        return _private_ionvalue.toString();
    }
}
