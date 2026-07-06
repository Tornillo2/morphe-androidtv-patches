package com.amazon.ion.impl.lite;

import android.support.v4.media.MediaBrowserCompat$MediaBrowserImplBase$$ExternalSyntheticOutline0;
import com.amazon.ion.ContainedValueException;
import com.amazon.ion.IonCatalog;
import com.amazon.ion.IonContainer;
import com.amazon.ion.IonDatagram;
import com.amazon.ion.IonException;
import com.amazon.ion.IonSequence;
import com.amazon.ion.IonSymbol;
import com.amazon.ion.IonSystem;
import com.amazon.ion.IonType;
import com.amazon.ion.IonValue;
import com.amazon.ion.IonWriter;
import com.amazon.ion.SymbolTable;
import com.amazon.ion.SymbolToken;
import com.amazon.ion.SystemSymbols;
import com.amazon.ion.UnknownSymbolException;
import com.amazon.ion.ValueFactory;
import com.amazon.ion.ValueVisitor;
import com.amazon.ion.impl.IonTokenConstsX;
import com.amazon.ion.impl.SymbolTokenImpl;
import com.amazon.ion.impl._Private_CurriedValueFactory;
import com.amazon.ion.impl._Private_IonDatagram;
import com.amazon.ion.impl._Private_IonValue;
import com.amazon.ion.impl._Private_Utils;
import com.amazon.ion.impl.lite.IonContainerLite;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public final class IonDatagramLite extends IonSequenceLite implements IonDatagram, IonContext, _Private_IonDatagram {
    public static final /* synthetic */ boolean $assertionsDisabled = false;
    public static final int HASH_SIGNATURE = IonType.DATAGRAM.toString().hashCode();
    public static final int REVERSE_BINARY_ENCODER_INITIAL_SIZE = 32768;
    public final IonCatalog _catalog;
    public IonSymbolLite _ivm;
    public SymbolTable _pending_symbol_table;
    public int _pending_symbol_table_idx;
    public final IonSystemLite _system;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public final class SystemContentIterator implements ListIterator<IonValue> {
        public static final /* synthetic */ boolean $assertionsDisabled = false;
        public IonValueLite __current;
        public SystemIteratorPosition __pos;
        public final boolean __readOnly;
        public SystemIteratorPosition __temp_pos;

        public SystemContentIterator(boolean z) {
            if (IonDatagramLite.this.is_true(1) && !z) {
                throw new IllegalStateException("you can't open an updatable iterator on a read only value");
            }
            this.__readOnly = z;
            this.__temp_pos = new SystemIteratorPosition(this);
            SystemIteratorPosition systemIteratorPosition = new SystemIteratorPosition(this);
            this.__pos = systemIteratorPosition;
            systemIteratorPosition.load_initial_position();
        }

        @Override // java.util.ListIterator
        public /* bridge */ /* synthetic */ void add(IonValue ionValue) {
            add2(ionValue);
            throw null;
        }

        public boolean datagram_contains(IonValueLite ionValueLite) {
            return IonDatagramLite.this.contains(ionValueLite);
        }

        public final void force_position_sync() {
            IonValueLite ionValueLite;
            SystemIteratorPosition systemIteratorPosition = this.__pos;
            int i = systemIteratorPosition.__user_index;
            if (i >= 0) {
                IonDatagramLite ionDatagramLite = IonDatagramLite.this;
                if (i >= ionDatagramLite._child_count || (ionValueLite = systemIteratorPosition.__current_user_value) == null || ionValueLite == ionDatagramLite._children[i]) {
                    return;
                }
                if (this.__readOnly) {
                    throw new IonException("read only sequence was changed");
                }
                systemIteratorPosition.force_position_sync_helper();
            }
        }

        public final IonSystem getSystem() {
            return IonDatagramLite.this._system;
        }

        public IonValueLite get_datagram_child(int i) {
            return IonDatagramLite.this.get_child(i);
        }

        public int get_datagram_child_count() {
            return IonDatagramLite.this._child_count;
        }

        public IonSymbolLite get_datagram_ivm() {
            return IonDatagramLite.this.get_ivm();
        }

        public IonSystem get_datagram_system() {
            return IonDatagramLite.this._system;
        }

        @Override // java.util.ListIterator, java.util.Iterator
        public final boolean hasNext() {
            return this.__pos.has_next();
        }

        @Override // java.util.ListIterator
        public final boolean hasPrevious() {
            return this.__pos.has_prev();
        }

        @Override // java.util.ListIterator
        public final int nextIndex() {
            SystemIteratorPosition systemIteratorPositionNext_index_helper = next_index_helper();
            return systemIteratorPositionNext_index_helper == null ? this.__pos.get_external_pos() + 1 : systemIteratorPositionNext_index_helper.get_external_pos();
        }

        public final SystemIteratorPosition next_index_helper() {
            force_position_sync();
            if (!this.__pos.has_next()) {
                return null;
            }
            SystemIteratorPosition systemIteratorPosition = this.__temp_pos;
            systemIteratorPosition.copyFrom(this.__pos);
            int i = systemIteratorPosition.__local_index + 1;
            systemIteratorPosition.__local_index = i;
            if (i < systemIteratorPosition.__local_value_count) {
                return systemIteratorPosition;
            }
            systemIteratorPosition.__user_index++;
            systemIteratorPosition.load_updated_position();
            systemIteratorPosition.__local_index = 0;
            return systemIteratorPosition;
        }

        @Override // java.util.ListIterator
        public final int previousIndex() {
            SystemIteratorPosition systemIteratorPositionPrevious_index_helper = previous_index_helper();
            if (systemIteratorPositionPrevious_index_helper == null) {
                return -1;
            }
            return systemIteratorPositionPrevious_index_helper.get_external_pos();
        }

        public final SystemIteratorPosition previous_index_helper() {
            force_position_sync();
            if (!this.__pos.has_prev()) {
                return null;
            }
            SystemIteratorPosition systemIteratorPosition = this.__temp_pos;
            systemIteratorPosition.copyFrom(this.__pos);
            int i = systemIteratorPosition.__local_index - 1;
            systemIteratorPosition.__local_index = i;
            if (i >= 0) {
                return systemIteratorPosition;
            }
            systemIteratorPosition.__index_adjustment -= systemIteratorPosition.__local_value_count;
            systemIteratorPosition.__user_index--;
            systemIteratorPosition.load_updated_position();
            systemIteratorPosition.__local_index = systemIteratorPosition.__local_value_count - 1;
            return systemIteratorPosition;
        }

        @Override // java.util.ListIterator, java.util.Iterator
        public void remove() {
            SystemIteratorPosition systemIteratorPosition;
            if (this.__readOnly) {
                throw new UnsupportedOperationException();
            }
            force_position_sync();
            if (this.__current == null || (systemIteratorPosition = this.__pos) == null) {
                throw new NoSuchElementException();
            }
            if (systemIteratorPosition.on_system_value()) {
                throw new UnsupportedOperationException();
            }
            int i = this.__pos.__user_index;
            int i_elementid = this.__current._elementid();
            IonDatagramLite.this.remove_child(i);
            IonDatagramLite.this.patch_elements_helper(i_elementid);
            SystemIteratorPosition systemIteratorPosition2 = this.__pos;
            systemIteratorPosition2.__index_adjustment -= systemIteratorPosition2.__local_value_count;
            if (systemIteratorPosition2.__user_index < IonDatagramLite.this._child_count - 1) {
                systemIteratorPosition2.load_updated_position();
                this.__pos.__local_index = -1;
            }
            this.__current = null;
        }

        @Override // java.util.ListIterator
        public /* bridge */ /* synthetic */ void set(IonValue ionValue) {
            set2(ionValue);
            throw null;
        }

        public IonValueLite set_position(SystemIteratorPosition systemIteratorPosition) {
            this.__temp_pos = this.__pos;
            this.__pos = systemIteratorPosition;
            IonValueLite ionValueLiteLoad_position = systemIteratorPosition.load_position();
            this.__current = ionValueLiteLoad_position;
            return ionValueLiteLoad_position;
        }

        /* JADX INFO: renamed from: add, reason: avoid collision after fix types in other method */
        public void add2(IonValue ionValue) {
            throw new UnsupportedOperationException();
        }

        @Override // java.util.ListIterator, java.util.Iterator
        public IonValue next() {
            SystemIteratorPosition systemIteratorPositionNext_index_helper = next_index_helper();
            if (systemIteratorPositionNext_index_helper != null) {
                return set_position(systemIteratorPositionNext_index_helper);
            }
            throw new NoSuchElementException();
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // java.util.ListIterator
        public IonValue previous() {
            SystemIteratorPosition systemIteratorPositionPrevious_index_helper = previous_index_helper();
            if (systemIteratorPositionPrevious_index_helper != null) {
                return set_position(systemIteratorPositionPrevious_index_helper);
            }
            throw new NoSuchElementException();
        }

        /* JADX INFO: renamed from: set, reason: avoid collision after fix types in other method */
        public void set2(IonValue ionValue) {
            throw new UnsupportedOperationException();
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static class SystemIteratorPosition {
        public static final /* synthetic */ boolean $assertionsDisabled = false;
        public SymbolTable __current_symbols;
        public int __current_symbols_index;
        public IonValueLite __current_user_value;
        public int __index_adjustment;
        public final SystemContentIterator __iterator;
        public int __local_index;
        public int __local_value_count;
        public IonValueLite[] __local_values = new IonValueLite[3];
        public int __user_index;

        public SystemIteratorPosition(SystemContentIterator systemContentIterator) {
            this.__iterator = systemContentIterator;
        }

        public static int count_system_values(IonSystem ionSystem, SymbolTable symbolTable, SymbolTable symbolTable2) {
            int i = 0;
            while (symbolTable2.isLocalTable()) {
                i++;
                symbolTable2 = _Private_Utils.symtabTree(symbolTable2, ionSystem).getSymbolTable();
            }
            return (symbolTable == null || symbolTable.getIonVersionId().equals(symbolTable2.getIonVersionId())) ? i + 1 : i;
        }

        public static final boolean is_ivm(IonValue ionValue) {
            SymbolToken symbolTokenSymbolValue;
            return (ionValue instanceof IonSymbol) && ionValue.getTypeAnnotationSymbols().length == 0 && (symbolTokenSymbolValue = ((IonSymbol) ionValue).symbolValue()) != null && SystemSymbols.ION_1_0.equals(((SymbolTokenImpl) symbolTokenSymbolValue).myText);
        }

        public void copyFrom(SystemIteratorPosition systemIteratorPosition) {
            this.__index_adjustment = systemIteratorPosition.__index_adjustment;
            this.__user_index = systemIteratorPosition.__user_index;
            this.__local_index = systemIteratorPosition.__local_index;
            this.__current_user_value = systemIteratorPosition.__current_user_value;
            this.__current_symbols = systemIteratorPosition.__current_symbols;
            this.__current_symbols_index = systemIteratorPosition.__current_symbols_index;
            int i = systemIteratorPosition.__local_value_count;
            if (i > 0) {
                IonValueLite[] ionValueLiteArr = this.__local_values;
                if (ionValueLiteArr == null || i >= ionValueLiteArr.length) {
                    this.__local_values = new IonValueLite[systemIteratorPosition.__local_values.length];
                }
                System.arraycopy(systemIteratorPosition.__local_values, 0, this.__local_values, 0, i);
            }
            this.__local_value_count = systemIteratorPosition.__local_value_count;
        }

        public final void force_position_sync_helper() {
            if (!IonDatagramLite.this.contains(this.__current_user_value)) {
                throw new IonException("current user value removed outside this iterator - position lost");
            }
            int i = this.__user_index;
            int i_elementid = this.__current_user_value._elementid();
            if (i != i_elementid) {
                int i2 = 0;
                SymbolTable symbolTable = null;
                int iCount_system_values = 0;
                while (i2 < i_elementid) {
                    SymbolTable symbolTable2 = IonDatagramLite.this.get_child(i2).getSymbolTable();
                    if (symbolTable2 != symbolTable) {
                        iCount_system_values = count_system_values(IonDatagramLite.this._system, symbolTable, symbolTable2) + iCount_system_values;
                    }
                    i2--;
                    symbolTable = symbolTable2;
                }
                this.__index_adjustment = iCount_system_values + this.__local_value_count;
                this.__user_index = i_elementid;
            }
        }

        public int get_external_pos() {
            return ((this.__user_index + this.__index_adjustment) - this.__local_value_count) + this.__local_index;
        }

        public boolean has_next() {
            return this.__local_index + 1 < this.__local_value_count || this.__user_index + 1 < IonDatagramLite.this._child_count;
        }

        public boolean has_prev() {
            return this.__user_index > 0 || this.__local_index > 0;
        }

        /* JADX WARN: Removed duplicated region for block: B:12:0x002e  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public void load_current_symbol_table(com.amazon.ion.impl.lite.IonValueLite r8) {
            /*
                r7 = this;
                com.amazon.ion.impl.lite.IonValueLite r0 = r7.__current_user_value
                int r1 = r7.__user_index
                com.amazon.ion.SymbolTable r2 = r7.__current_symbols
                int r3 = r7.__current_symbols_index
                r4 = 0
                r7.__current_symbols = r4
                r7.__current_symbols_index = r1
                if (r0 == 0) goto L16
                com.amazon.ion.SymbolTable r5 = r0.getAssignedSymbolTable()
                r7.__current_symbols = r5
                goto L17
            L16:
                r5 = r4
            L17:
                int r6 = r1 + (-1)
                if (r6 == r3) goto L2f
                if (r6 < 0) goto L2e
                com.amazon.ion.impl.lite.IonDatagramLite$SystemContentIterator r2 = r7.__iterator
                com.amazon.ion.impl.lite.IonDatagramLite r2 = com.amazon.ion.impl.lite.IonDatagramLite.this
                int r3 = r2._child_count
                if (r6 >= r3) goto L2e
                com.amazon.ion.impl.lite.IonValueLite r8 = r2.get_child(r6)
                com.amazon.ion.SymbolTable r2 = r8.getAssignedSymbolTable()
                goto L2f
            L2e:
                r2 = r4
            L2f:
                if (r5 == r2) goto L81
            L31:
                if (r5 == 0) goto L81
                boolean r2 = r5.isSystemTable()
                if (r2 == 0) goto L42
                com.amazon.ion.impl.lite.IonDatagramLite$SystemContentIterator r3 = r7.__iterator
                com.amazon.ion.impl.lite.IonDatagramLite r3 = com.amazon.ion.impl.lite.IonDatagramLite.this
                com.amazon.ion.impl.lite.IonSymbolLite r3 = r3.get_ivm()
                goto L4e
            L42:
                com.amazon.ion.impl.lite.IonDatagramLite$SystemContentIterator r3 = r7.__iterator
                com.amazon.ion.impl.lite.IonDatagramLite r3 = com.amazon.ion.impl.lite.IonDatagramLite.this
                com.amazon.ion.impl.lite.IonSystemLite r3 = com.amazon.ion.impl.lite.IonDatagramLite.access$200(r3)
                com.amazon.ion.IonStruct r3 = com.amazon.ion.impl._Private_Utils.symtabTree(r5, r3)
            L4e:
                if (r3 == r8) goto L61
                boolean r5 = is_ivm(r0)
                if (r5 == 0) goto L59
                if (r2 == 0) goto L59
                goto L61
            L59:
                r8 = r3
                com.amazon.ion.impl.lite.IonValueLite r8 = (com.amazon.ion.impl.lite.IonValueLite) r8
                r7.push_system_value(r8)
            L5f:
                r8 = r4
                goto L75
            L61:
                if (r8 != 0) goto L65
                r8 = -1
                goto L6b
            L65:
                int r8 = r8._elementid()
                int r8 = r8 + (-1)
            L6b:
                if (r8 < 0) goto L5f
                com.amazon.ion.impl.lite.IonDatagramLite$SystemContentIterator r2 = r7.__iterator
                com.amazon.ion.impl.lite.IonDatagramLite r2 = com.amazon.ion.impl.lite.IonDatagramLite.this
                com.amazon.ion.impl.lite.IonValueLite r8 = r2.get_child(r8)
            L75:
                com.amazon.ion.SymbolTable r5 = r3.getSymbolTable()
                if (r5 == 0) goto L81
                boolean r2 = r5.isSystemTable()
                if (r2 == 0) goto L31
            L81:
                if (r1 != 0) goto L94
                boolean r8 = is_ivm(r0)
                if (r8 != 0) goto L94
                com.amazon.ion.impl.lite.IonDatagramLite$SystemContentIterator r8 = r7.__iterator
                com.amazon.ion.impl.lite.IonDatagramLite r8 = com.amazon.ion.impl.lite.IonDatagramLite.this
                com.amazon.ion.impl.lite.IonSymbolLite r8 = r8.get_ivm()
                r7.push_system_value(r8)
            L94:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.amazon.ion.impl.lite.IonDatagramLite.SystemIteratorPosition.load_current_symbol_table(com.amazon.ion.impl.lite.IonValueLite):void");
        }

        public void load_initial_position() {
            this.__user_index = 0;
            this.__local_index = -1;
            this.__current_symbols_index = -1;
            load_updated_position();
        }

        public IonValueLite load_position() {
            return this.__local_values[(this.__local_value_count - this.__local_index) - 1];
        }

        public final void load_updated_position() {
            IonValueLite ionValueLite = this.__current_user_value;
            int i = this.__user_index;
            if (i < 0 || (i > 0 && i >= IonDatagramLite.this._child_count)) {
                throw new IonException("attempt to position iterator past end of values");
            }
            IonDatagramLite ionDatagramLite = IonDatagramLite.this;
            if (i < ionDatagramLite._child_count) {
                this.__current_user_value = ionDatagramLite.get_child(i);
            } else {
                this.__current_user_value = null;
            }
            int i2 = this.__local_value_count;
            this.__local_value_count = 0;
            IonValueLite ionValueLite2 = this.__current_user_value;
            if (ionValueLite2 != null) {
                push_system_value(ionValueLite2);
            }
            load_current_symbol_table(ionValueLite);
            for (int i3 = this.__local_value_count; i3 < i2; i3++) {
                this.__local_values[i3] = null;
            }
            this.__index_adjustment = (this.__local_value_count - 1) + this.__index_adjustment;
        }

        public boolean on_system_value() {
            return this.__current_user_value != this.__local_values[0];
        }

        public final void push_system_value(IonValueLite ionValueLite) {
            int i = this.__local_value_count;
            IonValueLite[] ionValueLiteArr = this.__local_values;
            if (i >= ionValueLiteArr.length) {
                IonValueLite[] ionValueLiteArr2 = new IonValueLite[ionValueLiteArr != null ? ionValueLiteArr.length * 2 : 2];
                if (i > 0) {
                    System.arraycopy(ionValueLiteArr, 0, ionValueLiteArr2, 0, i);
                }
                this.__local_values = ionValueLiteArr2;
            }
            IonValueLite[] ionValueLiteArr3 = this.__local_values;
            int i2 = this.__local_value_count;
            this.__local_value_count = i2 + 1;
            ionValueLiteArr3[i2] = ionValueLite;
        }
    }

    public IonDatagramLite(IonSystemLite ionSystemLite, IonCatalog ionCatalog) {
        super(ContainerlessContext.wrap(ionSystemLite), false);
        this._system = ionSystemLite;
        this._catalog = ionCatalog;
        this._pending_symbol_table_idx = -1;
    }

    @Override // com.amazon.ion.impl.lite.IonContainerLite, com.amazon.ion.impl.lite.IonValueLite, com.amazon.ion.IonValue
    public void accept(ValueVisitor valueVisitor) throws Exception {
        valueVisitor.visit(this);
    }

    @Override // com.amazon.ion.impl.lite.IonSequenceLite, com.amazon.ion.IonSequence, java.util.List, java.util.Collection
    public boolean addAll(Collection<? extends IonValue> collection) {
        Iterator<? extends IonValue> it = collection.iterator();
        boolean z = false;
        while (it.hasNext()) {
            add(it.next());
            z = true;
        }
        return z;
    }

    @Override // com.amazon.ion.impl.lite.IonValueLite, com.amazon.ion.IonValue
    public void addTypeAnnotation(String str) {
        throw new UnsupportedOperationException("Datagrams do not have annotations");
    }

    @Override // com.amazon.ion.impl._Private_IonDatagram
    public void appendTrailingSymbolTable(SymbolTable symbolTable) {
        this._pending_symbol_table = symbolTable;
        this._pending_symbol_table_idx = this._child_count;
    }

    @Override // com.amazon.ion.IonDatagram
    public int byteSize() throws IonException {
        ReverseBinaryEncoder reverseBinaryEncoder = new ReverseBinaryEncoder(32768);
        reverseBinaryEncoder.serialize(this);
        return reverseBinaryEncoder.byteSize();
    }

    @Override // com.amazon.ion.impl.lite.IonValueLite
    public /* bridge */ /* synthetic */ IonValueLite clone(IonContext ionContext) {
        clone(ionContext);
        throw null;
    }

    @Override // com.amazon.ion.impl.lite.IonSequenceLite, com.amazon.ion.IonSequence
    public <T extends IonValue> T[] extract(Class<T> cls) {
        if (is_true(4)) {
            return null;
        }
        T[] tArr = (T[]) ((IonValue[]) Array.newInstance((Class<?>) cls, size()));
        toArray(tArr);
        clear();
        return tArr;
    }

    @Override // com.amazon.ion.impl.lite.IonValueLite, com.amazon.ion.impl._Private_IonValue
    public SymbolTable getAssignedSymbolTable() {
        throw new UnsupportedOperationException();
    }

    @Override // com.amazon.ion.IonDatagram
    public byte[] getBytes() throws IonException {
        ReverseBinaryEncoder reverseBinaryEncoder = new ReverseBinaryEncoder(32768);
        reverseBinaryEncoder.serialize(this);
        return reverseBinaryEncoder.toNewByteArray();
    }

    @Override // com.amazon.ion.impl.lite.IonValueLite, com.amazon.ion.IonValue
    public /* bridge */ /* synthetic */ IonContainer getContainer() {
        return null;
    }

    @Override // com.amazon.ion.impl.lite.IonContainerLite
    public IonContext getContextForIndex(IonValue ionValue, int i) {
        IonContext ionContext;
        if (i != this._pending_symbol_table_idx) {
            IonValueLite ionValueLite = i > 0 ? get_child(i - 1) : null;
            return (ionValueLite == null || (ionContext = ionValueLite._context) == this) ? new TopLevelContext(null, this) : ionContext;
        }
        SymbolTable symbolTable = this._pending_symbol_table;
        this._pending_symbol_table = null;
        this._pending_symbol_table_idx = -1;
        return new TopLevelContext(symbolTable, this);
    }

    @Override // com.amazon.ion.impl.lite.IonValueLite, com.amazon.ion.IonValue
    public SymbolToken getFieldNameSymbol() {
        return null;
    }

    @Override // com.amazon.ion.impl.lite.IonValueLite, com.amazon.ion.IonValue
    public SymbolTable getSymbolTable() {
        throw new UnsupportedOperationException();
    }

    @Override // com.amazon.ion.impl.lite.IonContainerLite, com.amazon.ion.impl.lite.IonValueLite, com.amazon.ion.IonValue
    public IonSystem getSystem() {
        return this._system;
    }

    @Override // com.amazon.ion.impl.lite.IonValueLite, com.amazon.ion.IonValue
    public IonType getType() {
        return IonType.DATAGRAM;
    }

    @Override // com.amazon.ion.impl.lite.IonValueLite, com.amazon.ion.IonValue
    public SymbolToken[] getTypeAnnotationSymbols() {
        return SymbolToken.EMPTY_ARRAY;
    }

    public synchronized IonSymbolLite get_ivm() {
        try {
            if (this._ivm == null) {
                this._ivm = this._system.newSymbol(SystemSymbols.ION_1_0);
            }
        } catch (Throwable th) {
            throw th;
        }
        return this._ivm;
    }

    @Override // com.amazon.ion.impl.lite.IonValueLite, com.amazon.ion.IonValue, java.util.List, java.util.Collection
    public int hashCode() {
        int i = HASH_SIGNATURE;
        if (!is_true(4)) {
            ListIterator<IonValue> listIterator = listIterator(0);
            while (true) {
                IonContainerLite.SequenceContentIterator sequenceContentIterator = (IonContainerLite.SequenceContentIterator) listIterator;
                if (!sequenceContentIterator.hasNext()) {
                    break;
                }
                IonValue next = sequenceContentIterator.next();
                int iHashCode = next.hashCode() + (IonTokenConstsX.KW_ALL_BITS * i);
                i = iHashCode ^ ((iHashCode << 29) ^ (iHashCode >> 3));
            }
        }
        return i;
    }

    @Override // com.amazon.ion.impl.lite.IonContainerLite, com.amazon.ion.IonSequence, java.util.List
    public ListIterator<IonValue> listIterator(int i) {
        return new IonContainerLite.SequenceContentIterator(i, is_true(1));
    }

    @Override // com.amazon.ion.impl.lite.IonValueLite, com.amazon.ion.IonValue
    public void makeReadOnly() {
        if (is_true(1)) {
            return;
        }
        if (this._children != null) {
            for (int i = 0; i < this._child_count; i++) {
                this._children[i].makeReadOnly();
            }
        }
        set_flag(1);
    }

    @Override // com.amazon.ion.impl.lite.IonSequenceLite, java.util.List
    public Object set(int i, Object obj) {
        throw new UnsupportedOperationException();
    }

    @Override // com.amazon.ion.impl.lite.IonValueLite, com.amazon.ion.impl._Private_IonValue
    public void setSymbolTable(SymbolTable symbolTable) {
        throw new UnsupportedOperationException();
    }

    public void setSymbolTableAtIndex(int i, SymbolTable symbolTable) {
        TopLevelContext topLevelContext = new TopLevelContext(symbolTable, this);
        TopLevelContext topLevelContext2 = (TopLevelContext) this._children[i].getContext();
        while (i < this._child_count && this._children[i].getContext() == topLevelContext2) {
            this._children[i].setContext(topLevelContext);
            i++;
        }
    }

    @Override // com.amazon.ion.IonDatagram
    public IonValue systemGet(int i) throws IndexOutOfBoundsException {
        ListIterator<IonValue> listIteratorSystemIterator = systemIterator();
        if (i < 0) {
            throw new IndexOutOfBoundsException(MediaBrowserCompat$MediaBrowserImplBase$$ExternalSyntheticOutline0.m("", i));
        }
        IonValue ionValue = null;
        for (int i2 = 0; i2 <= i; i2++) {
            SystemContentIterator systemContentIterator = (SystemContentIterator) listIteratorSystemIterator;
            if (!systemContentIterator.__pos.has_next()) {
                throw new IndexOutOfBoundsException(MediaBrowserCompat$MediaBrowserImplBase$$ExternalSyntheticOutline0.m("", i));
            }
            ionValue = (IonValue) systemContentIterator.next();
        }
        return ionValue;
    }

    @Override // com.amazon.ion.IonDatagram
    public ListIterator<IonValue> systemIterator() {
        return new SystemContentIterator(is_true(1));
    }

    @Override // com.amazon.ion.IonDatagram
    public int systemSize() {
        ListIterator<IonValue> listIteratorSystemIterator = systemIterator();
        int i = 0;
        while (true) {
            SystemContentIterator systemContentIterator = (SystemContentIterator) listIteratorSystemIterator;
            if (!systemContentIterator.__pos.has_next()) {
                return i;
            }
            i++;
        }
    }

    @Override // com.amazon.ion.IonDatagram
    public byte[] toBytes() throws IonException {
        return getBytes();
    }

    @Override // com.amazon.ion.impl.lite.IonValueLite, com.amazon.ion.IonValue
    public IonValue topLevelValue() {
        throw new UnsupportedOperationException();
    }

    @Override // com.amazon.ion.impl.lite.IonSequenceLite, com.amazon.ion.impl.lite.IonValueLite
    public final void writeBodyTo(IonWriter ionWriter, _Private_IonValue.SymbolTableProvider symbolTableProvider) throws IOException {
        throw new UnsupportedOperationException("IonDatagram does not operate with a Symbol Table");
    }

    @Override // com.amazon.ion.impl.lite.IonValueLite, com.amazon.ion.IonValue
    public final void writeTo(IonWriter ionWriter) {
        if (ionWriter.getSymbolTable().isSystemTable()) {
            try {
                ionWriter.writeSymbol(SystemSymbols.ION_1_0);
            } catch (IOException e) {
                throw new IonException(e.getMessage(), e);
            }
        }
        ListIterator<IonValue> listIterator = listIterator(0);
        while (true) {
            IonContainerLite.SequenceContentIterator sequenceContentIterator = (IonContainerLite.SequenceContentIterator) listIterator;
            if (!sequenceContentIterator.hasNext()) {
                return;
            } else {
                sequenceContentIterator.next().writeTo(ionWriter);
            }
        }
    }

    @Override // com.amazon.ion.impl.lite.IonSequenceLite, java.util.List, java.util.Collection
    public /* bridge */ /* synthetic */ boolean add(Object obj) {
        add((IonValue) obj);
        return true;
    }

    @Override // com.amazon.ion.impl.lite.IonValueLite
    public IonDatagramLite clone(IonContext ionContext) {
        throw new UnsupportedOperationException("IonDatagram cannot have a parent context (be nested)");
    }

    @Override // com.amazon.ion.impl.lite.IonValueLite, com.amazon.ion.IonValue
    public IonContainerLite getContainer() {
        return null;
    }

    @Override // com.amazon.ion.impl.lite.IonContainerLite, com.amazon.ion.impl.lite.IonValueLite, com.amazon.ion.IonValue
    public IonSystemLite getSystem() {
        return this._system;
    }

    @Override // com.amazon.ion.impl.lite.IonValueLite, com.amazon.ion.IonValue
    public IonValueLite topLevelValue() {
        throw new UnsupportedOperationException();
    }

    @Override // com.amazon.ion.impl.lite.IonSequenceLite, com.amazon.ion.impl.lite.IonContainerLite, com.amazon.ion.IonSequence
    public boolean add(IonValue ionValue) throws ContainedValueException, NullPointerException {
        add(this._child_count, ionValue);
        return true;
    }

    @Override // com.amazon.ion.impl.lite.IonSequenceLite, com.amazon.ion.IonSequence, java.util.List
    public boolean addAll(int i, Collection<? extends IonValue> collection) {
        if (i >= 0 && i <= size()) {
            Iterator<? extends IonValue> it = collection.iterator();
            boolean z = false;
            while (it.hasNext()) {
                add(i, it.next());
                z = true;
                i++;
            }
            if (z) {
                patch_elements_helper(i);
            }
            return z;
        }
        throw new IndexOutOfBoundsException();
    }

    @Override // com.amazon.ion.impl.lite.IonSequenceLite, com.amazon.ion.impl.lite.IonContainerLite, com.amazon.ion.impl.lite.IonValueLite, com.amazon.ion.IonValue
    /* JADX INFO: renamed from: clone */
    public IonContainer mo245clone() throws UnknownSymbolException {
        return new IonDatagramLite(this);
    }

    @Override // com.amazon.ion.impl.lite.IonSequenceLite, com.amazon.ion.IonSequence
    public IonValue set(int i, IonValue ionValue) {
        throw new UnsupportedOperationException();
    }

    @Override // com.amazon.ion.impl.lite.IonSequenceLite, com.amazon.ion.impl.lite.IonContainerLite, com.amazon.ion.impl.lite.IonValueLite, com.amazon.ion.IonValue
    /* JADX INFO: renamed from: clone */
    public IonDatagram mo245clone() throws UnknownSymbolException {
        return new IonDatagramLite(this);
    }

    @Override // com.amazon.ion.IonDatagram
    public int getBytes(byte[] bArr) throws IonException {
        ReverseBinaryEncoder reverseBinaryEncoder = new ReverseBinaryEncoder(32768);
        reverseBinaryEncoder.serialize(this);
        return reverseBinaryEncoder.toNewByteArray(bArr);
    }

    @Override // com.amazon.ion.impl.lite.IonSequenceLite, com.amazon.ion.IonSequence
    public ValueFactory add() {
        return new _Private_CurriedValueFactory(this._system) { // from class: com.amazon.ion.impl.lite.IonDatagramLite.1
            @Override // com.amazon.ion.impl._Private_CurriedValueFactory
            public void handle(IonValue ionValue) {
                IonDatagramLite.this.add(ionValue);
            }
        };
    }

    @Override // com.amazon.ion.impl.lite.IonSequenceLite, com.amazon.ion.impl.lite.IonContainerLite, com.amazon.ion.impl.lite.IonValueLite, com.amazon.ion.IonValue
    /* JADX INFO: renamed from: clone */
    public IonSequence mo245clone() throws UnknownSymbolException {
        return new IonDatagramLite(this);
    }

    public IonDatagramLite(IonDatagramLite ionDatagramLite) {
        super(ionDatagramLite, ContainerlessContext.wrap(ionDatagramLite._system), false);
        this._system = ionDatagramLite._system;
        this._catalog = ionDatagramLite._catalog;
    }

    @Override // com.amazon.ion.impl.lite.IonSequenceLite, com.amazon.ion.impl.lite.IonContainerLite, com.amazon.ion.impl.lite.IonValueLite, com.amazon.ion.IonValue
    /* JADX INFO: renamed from: clone */
    public IonValue mo245clone() throws UnknownSymbolException {
        return new IonDatagramLite(this);
    }

    @Override // com.amazon.ion.impl.lite.IonSequenceLite, com.amazon.ion.impl.lite.IonContainerLite, com.amazon.ion.impl.lite.IonValueLite, com.amazon.ion.IonValue
    /* JADX INFO: renamed from: clone */
    public IonDatagramLite mo245clone() {
        return new IonDatagramLite(this);
    }

    @Override // com.amazon.ion.IonDatagram
    public int getBytes(byte[] bArr, int i) throws IonException {
        ReverseBinaryEncoder reverseBinaryEncoder = new ReverseBinaryEncoder(32768);
        reverseBinaryEncoder.serialize(this);
        return reverseBinaryEncoder.toNewByteArray(bArr, i);
    }

    @Override // com.amazon.ion.impl.lite.IonValueLite
    public int hashCode(_Private_IonValue.SymbolTableProvider symbolTableProvider) {
        throw new UnsupportedOperationException("IonDatagrams do not need a resolved Symbol table use #hashCode()");
    }

    @Override // com.amazon.ion.impl.lite.IonSequenceLite, java.util.List
    public void add(int i, IonValue ionValue) throws ContainedValueException, NullPointerException {
        ionValue.getClass();
        if (ionValue instanceof IonValueLite) {
            add(i, (IonValueLite) ionValue);
            this._pending_symbol_table = null;
            this._pending_symbol_table_idx = -1;
            return;
        }
        throw new IllegalArgumentException("IonValue implementation can't be mixed");
    }

    @Override // com.amazon.ion.impl.lite.IonSequenceLite, com.amazon.ion.impl.lite.IonContainerLite, com.amazon.ion.impl.lite.IonValueLite, com.amazon.ion.IonValue
    /* JADX INFO: renamed from: clone */
    public IonSequenceLite mo245clone() {
        return new IonDatagramLite(this);
    }

    @Override // com.amazon.ion.impl.lite.IonSequenceLite, com.amazon.ion.impl.lite.IonContainerLite, com.amazon.ion.impl.lite.IonValueLite
    /* JADX INFO: renamed from: clone */
    public Object mo245clone() throws CloneNotSupportedException {
        return new IonDatagramLite(this);
    }

    @Override // com.amazon.ion.IonDatagram
    public int getBytes(OutputStream outputStream) throws IOException, IonException {
        ReverseBinaryEncoder reverseBinaryEncoder = new ReverseBinaryEncoder(32768);
        reverseBinaryEncoder.serialize(this);
        return reverseBinaryEncoder.writeBytes(outputStream);
    }

    @Override // com.amazon.ion.impl.lite.IonSequenceLite, com.amazon.ion.IonSequence
    public ValueFactory add(final int i) {
        return new _Private_CurriedValueFactory(this._system) { // from class: com.amazon.ion.impl.lite.IonDatagramLite.2
            @Override // com.amazon.ion.impl._Private_CurriedValueFactory
            public void handle(IonValue ionValue) {
                IonDatagramLite.this.add(i, ionValue);
            }
        };
    }
}
