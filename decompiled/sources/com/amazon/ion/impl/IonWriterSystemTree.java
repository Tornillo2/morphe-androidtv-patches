package com.amazon.ion.impl;

import com.amazon.ion.IonCatalog;
import com.amazon.ion.IonContainer;
import com.amazon.ion.IonDatagram;
import com.amazon.ion.IonDecimal;
import com.amazon.ion.IonException;
import com.amazon.ion.IonFloat;
import com.amazon.ion.IonSequence;
import com.amazon.ion.IonStruct;
import com.amazon.ion.IonSystem;
import com.amazon.ion.IonType;
import com.amazon.ion.IonValue;
import com.amazon.ion.SymbolTable;
import com.amazon.ion.Timestamp;
import com.amazon.ion.ValueFactory;
import com.amazon.ion.impl.LocalSymbolTableAsStruct;
import com.amazon.ion.system.IonWriterBuilder;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public final class IonWriterSystemTree extends IonWriterSystem {
    public final IonCatalog _catalog;
    public IonContainer _current_parent;
    public final ValueFactory _factory;
    public boolean _in_struct;
    public final int _initialDepth;
    public final LocalSymbolTableAsStruct.Factory _lst_factory;
    public IonContainer[] _parent_stack;
    public int _parent_stack_top;

    /* JADX INFO: renamed from: com.amazon.ion.impl.IonWriterSystemTree$1, reason: invalid class name */
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
        }
    }

    public IonWriterSystemTree(SymbolTable symbolTable, IonCatalog ionCatalog, IonContainer ionContainer, IonWriterBuilder.InitialIvmHandling initialIvmHandling) {
        super(symbolTable, initialIvmHandling, IonWriterBuilder.IvmMinimizing.ADJACENT);
        int i = 0;
        this._parent_stack_top = 0;
        this._parent_stack = new IonContainer[10];
        IonSystem system = ionContainer.getSystem();
        this._factory = system;
        this._lst_factory = (LocalSymbolTableAsStruct.Factory) ((_Private_ValueFactory) system).getLstFactory();
        this._catalog = ionCatalog;
        this._current_parent = ionContainer;
        this._in_struct = ionContainer instanceof IonStruct;
        if (!(ionContainer instanceof IonDatagram)) {
            do {
                i++;
                ionContainer = ionContainer.getContainer();
            } while (ionContainer != null);
        }
        this._initialDepth = i;
    }

    public final void append(IonValue ionValue) {
        try {
            startValue();
            if (hasAnnotations()) {
                ((_Private_IonValue) ionValue).setTypeAnnotationSymbols(getTypeAnnotationSymbols());
                this._annotation_count = 0;
            }
            if (!this._in_struct) {
                ((IonSequence) this._current_parent).add(ionValue);
                return;
            }
            ((IonStruct) this._current_parent).add(assumeFieldNameSymbol(), ionValue);
            clearFieldName();
        } catch (IOException e) {
            throw new IonException(e.getMessage(), e);
        }
    }

    public IonType getContainer() {
        int i = this._parent_stack_top;
        return i > 0 ? this._parent_stack[i - 1].getType() : IonType.DATAGRAM;
    }

    @Override // com.amazon.ion.impl._Private_IonWriterBase
    public int getDepth() {
        return this._parent_stack_top + this._initialDepth;
    }

    public IonValue get_root() {
        return this._parent_stack_top > 0 ? this._parent_stack[0] : this._current_parent;
    }

    @Override // com.amazon.ion.impl.IonWriterSystem
    public final SymbolTable inject_local_symbol_table() throws IOException {
        return this._lst_factory.newLocalSymtab(this._symbol_table, new SymbolTable[0]);
    }

    @Override // com.amazon.ion.IonWriter
    public boolean isInStruct() {
        return this._in_struct;
    }

    public final void popParent() {
        int i = this._parent_stack_top;
        if (i < 1) {
            throw new IllegalStateException(IonMessages.CANNOT_STEP_OUT);
        }
        int i2 = i - 1;
        this._parent_stack_top = i2;
        IonContainer ionContainer = this._parent_stack[i2];
        this._current_parent = ionContainer;
        this._in_struct = ionContainer instanceof IonStruct;
    }

    public final void pushParent(IonContainer ionContainer) {
        IonContainer[] ionContainerArr = this._parent_stack;
        int length = ionContainerArr.length;
        if (this._parent_stack_top >= length) {
            IonContainer[] ionContainerArr2 = new IonContainer[length * 2];
            System.arraycopy(ionContainerArr, 0, ionContainerArr2, 0, length);
            this._parent_stack = ionContainerArr2;
        }
        IonContainer[] ionContainerArr3 = this._parent_stack;
        int i = this._parent_stack_top;
        this._parent_stack_top = i + 1;
        ionContainerArr3[i] = this._current_parent;
        this._current_parent = ionContainer;
        this._in_struct = ionContainer instanceof IonStruct;
    }

    @Override // com.amazon.ion.IonWriter
    public void stepIn(IonType ionType) throws IOException {
        IonContainer ionContainerNewEmptyList;
        int i = AnonymousClass1.$SwitchMap$com$amazon$ion$IonType[ionType.ordinal()];
        if (i == 1) {
            ionContainerNewEmptyList = this._factory.newEmptyList();
        } else if (i == 2) {
            ionContainerNewEmptyList = this._factory.newEmptySexp();
        } else {
            if (i != 3) {
                throw new IllegalArgumentException();
            }
            ionContainerNewEmptyList = this._factory.newEmptyStruct();
        }
        append(ionContainerNewEmptyList);
        pushParent(ionContainerNewEmptyList);
    }

    @Override // com.amazon.ion.IonWriter
    public void stepOut() throws IOException {
        _Private_IonValue _private_ionvalue = (_Private_IonValue) this._current_parent;
        popParent();
        if ((this._current_parent instanceof IonDatagram) && _Private_Utils.valueIsLocalSymbolTable(_private_ionvalue)) {
            setSymbolTable(this._lst_factory.newLocalSymtab(this._catalog, (IonStruct) _private_ionvalue));
        }
    }

    @Override // com.amazon.ion.IonWriter
    public void writeBlob(byte[] bArr, int i, int i2) throws IOException {
        append(this._factory.newBlob(bArr, i, i2));
    }

    @Override // com.amazon.ion.IonWriter
    public void writeBool(boolean z) throws IOException {
        append(this._factory.newBool(z));
    }

    @Override // com.amazon.ion.IonWriter
    public void writeClob(byte[] bArr, int i, int i2) throws IOException {
        append(this._factory.newClob(bArr, i, i2));
    }

    @Override // com.amazon.ion.impl._Private_IonWriterBase, com.amazon.ion.IonWriter
    public void writeDecimal(BigDecimal bigDecimal) throws IOException {
        IonDecimal ionDecimalNewNullDecimal = this._factory.newNullDecimal();
        ionDecimalNewNullDecimal.setValue(bigDecimal);
        append(ionDecimalNewNullDecimal);
    }

    @Override // com.amazon.ion.IonWriter
    public void writeFloat(double d) throws IOException {
        IonFloat ionFloatNewNullFloat = this._factory.newNullFloat();
        ionFloatNewNullFloat.setValue(d);
        append(ionFloatNewNullFloat);
    }

    public void writeInt(int i) throws IOException {
        append(this._factory.newInt(i));
    }

    @Override // com.amazon.ion.impl.IonWriterSystem
    public void writeIonVersionMarkerAsIs(SymbolTable symbolTable) throws IOException {
        startValue();
        ((_Private_IonDatagram) get_root()).appendTrailingSymbolTable(symbolTable);
        endValue();
    }

    @Override // com.amazon.ion.impl.IonWriterSystem
    public void writeLocalSymtab(SymbolTable symbolTable) throws IOException {
        ((_Private_IonDatagram) get_root()).appendTrailingSymbolTable(symbolTable);
        this._symbol_table = symbolTable;
    }

    @Override // com.amazon.ion.IonWriter
    public void writeNull(IonType ionType) throws IOException {
        append(this._factory.newNull(ionType));
    }

    @Override // com.amazon.ion.IonWriter
    public void writeString(String str) throws IOException {
        append(this._factory.newString(str));
    }

    @Override // com.amazon.ion.impl.IonWriterSystem
    public void writeSymbolAsIs(int i) {
        append(this._factory.newSymbol(new SymbolTokenImpl(this._symbol_table.findKnownSymbol(i), i)));
    }

    @Override // com.amazon.ion.IonWriter
    public void writeTimestamp(Timestamp timestamp) throws IOException {
        append(this._factory.newTimestamp(timestamp));
    }

    @Override // com.amazon.ion.IonWriter
    public void writeInt(long j) throws IOException {
        append(this._factory.newInt(j));
    }

    @Override // com.amazon.ion.IonWriter
    public void writeInt(BigInteger bigInteger) throws IOException {
        append(this._factory.newInt(bigInteger));
    }

    @Override // com.amazon.ion.impl.IonWriterSystem
    public void writeSymbolAsIs(String str) {
        append(this._factory.newSymbol(str));
    }

    @Override // com.amazon.ion.IonWriter, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
    }

    @Override // com.amazon.ion.IonWriter, java.io.Flushable
    public void flush() {
    }
}
