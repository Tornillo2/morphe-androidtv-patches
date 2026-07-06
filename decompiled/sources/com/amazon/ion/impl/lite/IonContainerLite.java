package com.amazon.ion.impl.lite;

import com.amazon.ion.ContainedValueException;
import com.amazon.ion.IonContainer;
import com.amazon.ion.IonDatagram;
import com.amazon.ion.IonException;
import com.amazon.ion.IonType;
import com.amazon.ion.IonValue;
import com.amazon.ion.NullValueException;
import com.amazon.ion.ReadOnlyValueException;
import com.amazon.ion.SymbolTable;
import com.amazon.ion.ValueVisitor;
import com.amazon.ion.impl._Private_IonContainer;
import com.amazon.ion.impl._Private_Utils;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public abstract class IonContainerLite extends IonValueLite implements _Private_IonContainer, IonContext {
    public static final /* synthetic */ boolean $assertionsDisabled = false;
    public static final int[] INITIAL_SIZE = make_initial_size_array();
    public static final int[] NEXT_SIZE = make_next_size_array();
    public int _child_count;
    public IonValueLite[] _children;
    public IonSystemLite ionSystem;
    public int structuralModificationCount;

    /* JADX INFO: renamed from: com.amazon.ion.impl.lite.IonContainerLite$1, reason: invalid class name */
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
                $SwitchMap$com$amazon$ion$IonType[IonType.DATAGRAM.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public class SequenceContentIterator implements ListIterator<IonValue> {
        public static final /* synthetic */ boolean $assertionsDisabled = false;
        public IonValueLite __current;
        public boolean __lastMoveWasPrevious;
        public int __pos;
        public final boolean __readOnly;

        public SequenceContentIterator(int i, boolean z) {
            if (IonContainerLite.this.is_true(1) && !z) {
                throw new IllegalStateException("you can't open an updatable iterator on a read only value");
            }
            if (i < 0 || i > IonContainerLite.this._child_count) {
                throw new IndexOutOfBoundsException(Integer.toString(i));
            }
            this.__pos = i;
            this.__readOnly = z;
        }

        @Override // java.util.ListIterator
        public /* bridge */ /* synthetic */ void add(IonValue ionValue) {
            add2(ionValue);
            throw null;
        }

        public final void force_position_sync() {
            IonValueLite ionValueLite;
            int i = this.__pos;
            if (i > 0) {
                IonContainerLite ionContainerLite = IonContainerLite.this;
                if (i > ionContainerLite._child_count || (ionValueLite = this.__current) == null || ionValueLite == ionContainerLite._children[i - 1]) {
                    return;
                }
                force_position_sync_helper();
            }
        }

        public final void force_position_sync_helper() {
            if (this.__readOnly) {
                throw new IonException("read only sequence was changed");
            }
            int i = this.__pos;
            while (true) {
                IonContainerLite ionContainerLite = IonContainerLite.this;
                if (i >= ionContainerLite._child_count) {
                    for (int i2 = this.__pos - 1; i2 >= 0; i2--) {
                        if (IonContainerLite.this._children[i2] == this.__current) {
                            this.__pos = i2;
                            if (this.__lastMoveWasPrevious) {
                                return;
                            }
                            this.__pos = i2 + 1;
                            return;
                        }
                    }
                    throw new IonException("current member of iterator has been removed from the containing sequence");
                }
                if (ionContainerLite._children[i] == this.__current) {
                    this.__pos = i;
                    if (this.__lastMoveWasPrevious) {
                        return;
                    }
                    this.__pos = i + 1;
                    return;
                }
                i++;
            }
        }

        @Override // java.util.ListIterator, java.util.Iterator
        public final boolean hasNext() {
            return nextIndex() < IonContainerLite.this._child_count;
        }

        @Override // java.util.ListIterator
        public final boolean hasPrevious() {
            return previousIndex() >= 0;
        }

        @Override // java.util.ListIterator
        public final int nextIndex() {
            force_position_sync();
            int i = this.__pos;
            int i2 = IonContainerLite.this._child_count;
            return i >= i2 ? i2 : i;
        }

        @Override // java.util.ListIterator
        public final int previousIndex() {
            force_position_sync();
            int i = this.__pos - 1;
            if (i < 0) {
                return -1;
            }
            return i;
        }

        @Override // java.util.ListIterator, java.util.Iterator
        public void remove() {
            if (this.__readOnly) {
                throw new UnsupportedOperationException();
            }
            force_position_sync();
            int i = this.__pos;
            if (!this.__lastMoveWasPrevious) {
                i--;
            }
            if (i < 0) {
                throw new ArrayIndexOutOfBoundsException();
            }
            int i_elementid = this.__current._elementid();
            IonContainerLite.this.remove_child(i);
            IonContainerLite.this.patch_elements_helper(i_elementid);
            if (!this.__lastMoveWasPrevious) {
                this.__pos--;
            }
            this.__current = null;
        }

        @Override // java.util.ListIterator
        public /* bridge */ /* synthetic */ void set(IonValue ionValue) {
            set2(ionValue);
            throw null;
        }

        /* JADX INFO: renamed from: add, reason: avoid collision after fix types in other method */
        public void add2(IonValue ionValue) {
            throw new UnsupportedOperationException();
        }

        @Override // java.util.ListIterator, java.util.Iterator
        public IonValue next() {
            int iNextIndex = nextIndex();
            IonContainerLite ionContainerLite = IonContainerLite.this;
            if (iNextIndex >= ionContainerLite._child_count) {
                throw new NoSuchElementException();
            }
            IonValueLite ionValueLite = ionContainerLite._children[iNextIndex];
            this.__current = ionValueLite;
            this.__pos = iNextIndex + 1;
            this.__lastMoveWasPrevious = false;
            return ionValueLite;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // java.util.ListIterator
        public IonValue previous() {
            force_position_sync();
            int iPreviousIndex = previousIndex();
            if (iPreviousIndex < 0) {
                throw new NoSuchElementException();
            }
            IonValueLite ionValueLite = IonContainerLite.this._children[iPreviousIndex];
            this.__current = ionValueLite;
            this.__pos = iPreviousIndex;
            this.__lastMoveWasPrevious = true;
            return ionValueLite;
        }

        /* JADX INFO: renamed from: set, reason: avoid collision after fix types in other method */
        public void set2(IonValue ionValue) {
            throw new UnsupportedOperationException();
        }
    }

    public IonContainerLite(ContainerlessContext containerlessContext, boolean z) {
        super(containerlessContext, z);
        this.ionSystem = containerlessContext.getSystem();
    }

    public static int[] make_initial_size_array() {
        int[] iArr = new int[17];
        iArr[11] = 1;
        iArr[12] = 4;
        iArr[13] = 5;
        iArr[16] = 3;
        return iArr;
    }

    public static int[] make_next_size_array() {
        int[] iArr = new int[17];
        iArr[11] = 4;
        iArr[12] = 8;
        iArr[13] = 8;
        iArr[16] = 10;
        return iArr;
    }

    @Override // com.amazon.ion.impl.lite.IonValueLite, com.amazon.ion.IonValue
    public abstract void accept(ValueVisitor valueVisitor) throws Exception;

    public void add(int i, IonValueLite ionValueLite) throws ContainedValueException, NullPointerException {
        if (i < 0 || i > this._child_count) {
            throw new IndexOutOfBoundsException();
        }
        checkForLock();
        validateNewChild(ionValueLite);
        add_child(i, ionValueLite);
        patch_elements_helper(i + 1);
    }

    public int add_child(int i, IonValueLite ionValueLite) {
        clear_flag(4);
        ionValueLite.setContext(getContextForIndex(ionValueLite, i));
        IonValueLite[] ionValueLiteArr = this._children;
        if (ionValueLiteArr == null || this._child_count >= ionValueLiteArr.length) {
            int length = ionValueLiteArr == null ? 0 : ionValueLiteArr.length;
            IonValueLite[] ionValueLiteArr2 = new IonValueLite[nextSize(length, true)];
            if (length > 0) {
                System.arraycopy(this._children, 0, ionValueLiteArr2, 0, length);
            }
            this._children = ionValueLiteArr2;
        }
        int i2 = this._child_count;
        if (i < i2) {
            IonValueLite[] ionValueLiteArr3 = this._children;
            System.arraycopy(ionValueLiteArr3, i, ionValueLiteArr3, i + 1, i2 - i);
        }
        this._child_count++;
        this._children[i] = ionValueLite;
        this.structuralModificationCount++;
        ionValueLite._elementid(i);
        if (!is_true(128) && ionValueLite.is_true(128)) {
            cascadeSIDPresentToContextRoot();
        }
        return i;
    }

    @Override // com.amazon.ion.impl.lite.IonValueLite
    public boolean attemptClearSymbolIDValues() {
        boolean zAttemptClearSymbolIDValues = super.attemptClearSymbolIDValues();
        for (int i = 0; i < this._child_count; i++) {
            zAttemptClearSymbolIDValues &= get_child(i).clearSymbolIDValues();
        }
        return zAttemptClearSymbolIDValues;
    }

    @Override // com.amazon.ion.IonContainer
    public void clear() {
        checkForLock();
        if (is_true(4)) {
            clear_flag(4);
        } else {
            if (isEmpty()) {
                return;
            }
            detachAllChildren();
            this._child_count = 0;
            this.structuralModificationCount++;
        }
    }

    @Override // com.amazon.ion.impl.lite.IonValueLite
    /* JADX INFO: renamed from: clone, reason: merged with bridge method [inline-methods] */
    public abstract IonContainer mo245clone();

    public final void detachAllChildren() {
        for (int i = 0; i < this._child_count; i++) {
            this._children[i].detachFromContainer();
            this._children[i] = null;
        }
    }

    public IonValue get(int i) throws NullValueException {
        validateThisNotNull();
        return get_child(i);
    }

    @Override // com.amazon.ion.impl.lite.IonContext
    public final SymbolTable getContextSymbolTable() {
        return null;
    }

    @Override // com.amazon.ion.impl._Private_IonContainer
    public final int get_child_count() {
        return this._child_count;
    }

    public final int initialSize() {
        int i = AnonymousClass1.$SwitchMap$com$amazon$ion$IonType[getType().ordinal()];
        if (i == 1) {
            return 1;
        }
        if (i != 3) {
            return i != 4 ? 4 : 3;
        }
        return 5;
    }

    @Override // com.amazon.ion.IonContainer
    public boolean isEmpty() throws NullValueException {
        validateThisNotNull();
        return size() == 0;
    }

    @Override // com.amazon.ion.IonContainer, java.lang.Iterable
    public final Iterator<IonValue> iterator() {
        return listIterator(0);
    }

    public ListIterator<IonValue> listIterator(int i) {
        if (!is_true(4)) {
            return new SequenceContentIterator(i, is_true(1));
        }
        if (i == 0) {
            return _Private_Utils.emptyIterator();
        }
        throw new IndexOutOfBoundsException();
    }

    @Override // com.amazon.ion.IonContainer
    public void makeNull() {
        clear();
        set_flag(4);
    }

    @Override // com.amazon.ion.impl.lite.IonValueLite
    public void makeReadOnlyInternal() {
        if (is_true(1)) {
            return;
        }
        if (this._children != null) {
            for (int i = 0; i < this._child_count; i++) {
                this._children[i].makeReadOnlyInternal();
            }
        }
        clearSymbolIDValues();
        set_flag(1);
    }

    public final int nextSize(int i, boolean z) {
        if (i == 0) {
            return initialSize();
        }
        int i2 = AnonymousClass1.$SwitchMap$com$amazon$ion$IonType[getType().ordinal()];
        int i3 = 4;
        if (i2 != 1) {
            if (i2 == 2 || i2 == 3) {
                i3 = 8;
            } else {
                if (i2 != 4) {
                    return i * 2;
                }
                i3 = 10;
            }
        }
        if (i3 <= i) {
            return i * 2;
        }
        if (z) {
            transitionToLargeSize(i3);
        }
        return i3;
    }

    public final void patch_elements_helper(int i) {
        while (i < this._child_count) {
            get_child(i)._elementid(i);
            i++;
        }
    }

    @Override // com.amazon.ion.IonContainer
    public boolean remove(IonValue ionValue) {
        checkForLock();
        if (ionValue.getContainer() != this) {
            return false;
        }
        IonValueLite ionValueLite = (IonValueLite) ionValue;
        int i_elementid = ionValueLite._elementid();
        if (get_child(i_elementid) != ionValueLite) {
            throw new AssertionError("element's index is not correct");
        }
        remove_child(i_elementid);
        patch_elements_helper(i_elementid);
        return true;
    }

    public void remove_child(int i) {
        this._children[i].detachFromContainer();
        int i2 = (this._child_count - i) - 1;
        if (i2 > 0) {
            IonValueLite[] ionValueLiteArr = this._children;
            System.arraycopy(ionValueLiteArr, i + 1, ionValueLiteArr, i, i2);
        }
        int i3 = this._child_count - 1;
        this._child_count = i3;
        this._children[i3] = null;
        this.structuralModificationCount++;
    }

    public final IonValueLite set_child(int i, IonValueLite ionValueLite) {
        if (i < 0 || i >= this._child_count) {
            throw new IndexOutOfBoundsException(Integer.toString(i));
        }
        ionValueLite.getClass();
        IonValueLite[] ionValueLiteArr = this._children;
        IonValueLite ionValueLite2 = ionValueLiteArr[i];
        ionValueLiteArr[i] = ionValueLite;
        return ionValueLite2;
    }

    @Override // com.amazon.ion.IonContainer, java.util.List, java.util.Collection
    public int size() {
        if (is_true(4)) {
            return 0;
        }
        return this._child_count;
    }

    public void validateNewChild(IonValue ionValue) throws ContainedValueException, IllegalArgumentException, NullPointerException {
        if (ionValue.getContainer() != null) {
            throw new ContainedValueException();
        }
        if (ionValue.isReadOnly()) {
            throw new ReadOnlyValueException();
        }
        if (ionValue instanceof IonDatagram) {
            throw new IllegalArgumentException("IonDatagram can not be inserted into another IonContainer.");
        }
    }

    @Override // com.amazon.ion.impl.lite.IonValueLite, com.amazon.ion.IonValue
    public IonSystemLite getSystem() {
        return this.ionSystem;
    }

    @Override // com.amazon.ion.impl._Private_IonContainer
    public final IonValueLite get_child(int i) {
        if (i < 0 || i >= this._child_count) {
            throw new IndexOutOfBoundsException(Integer.toString(i));
        }
        return this._children[i];
    }

    public IonContainerLite(IonContainerLite ionContainerLite, IonContext ionContext, boolean z) {
        super(ionContainerLite, ionContext);
        this.ionSystem = ionContainerLite.getSystem();
        int i = ionContainerLite._child_count;
        this._child_count = i;
        if (ionContainerLite._children != null) {
            boolean z2 = this instanceof IonDatagramLite;
            this._children = new IonValueLite[i];
            boolean zIs_true = false;
            for (int i2 = 0; i2 < i; i2++) {
                IonValueLite ionValueLite = ionContainerLite._children[i2];
                IonValueLite ionValueLiteClone = ionValueLite.clone(z2 ? new TopLevelContext(ionValueLite.getAssignedSymbolTable(), (IonDatagramLite) this) : this);
                if (z) {
                    if (ionValueLite.getFieldName() == null) {
                        ionValueLiteClone.setFieldNameSymbol(ionValueLite.getKnownFieldNameSymbol());
                    } else {
                        ionValueLiteClone._fieldName = ionValueLite.getFieldName();
                    }
                }
                this._children[i2] = ionValueLiteClone;
                zIs_true |= ionValueLiteClone.is_true(128);
            }
            _isSymbolIdPresent(zIs_true);
        }
    }

    public boolean add(IonValue ionValue) throws ContainedValueException, IllegalArgumentException, NullPointerException {
        add(this._child_count, (IonValueLite) ionValue);
        return true;
    }

    public final ListIterator<IonValue> listIterator() {
        return listIterator(0);
    }

    @Override // com.amazon.ion.impl.lite.IonContext
    public final IonContainerLite getContextContainer() {
        return this;
    }

    public void transitionToLargeSize(int i) {
    }

    public IonContext getContextForIndex(IonValue ionValue, int i) {
        return this;
    }
}
