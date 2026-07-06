package com.amazon.ion.impl.lite;

import com.amazon.ion.ContainedValueException;
import com.amazon.ion.IonStruct;
import com.amazon.ion.IonType;
import com.amazon.ion.IonValue;
import com.amazon.ion.IonWriter;
import com.amazon.ion.SymbolToken;
import com.amazon.ion.UnknownSymbolException;
import com.amazon.ion.ValueFactory;
import com.amazon.ion.ValueVisitor;
import com.amazon.ion.impl.IonTokenConstsX;
import com.amazon.ion.impl._Private_CurriedValueFactory;
import com.amazon.ion.impl._Private_IonValue;
import com.amazon.ion.impl.lite.IonContainerLite;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.ListIterator;
import java.util.Map;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public final class IonStructLite extends IonContainerLite implements IonStruct {
    public static final /* synthetic */ boolean $assertionsDisabled = false;
    public static final int HASH_SIGNATURE = IonType.STRUCT.toString().hashCode();
    public Map<String, Integer> _field_map;
    public int _field_map_duplicate_count;
    public boolean hasNullFieldName;

    public IonStructLite(ContainerlessContext containerlessContext, boolean z) {
        super(containerlessContext, z);
        this.hasNullFieldName = false;
    }

    public static boolean isListedField(IonValue ionValue, String[] strArr) {
        String fieldName = ionValue.getFieldName();
        for (String str : strArr) {
            if (str.equals(fieldName)) {
                return true;
            }
        }
        return false;
    }

    public static void validateFieldName(String str) {
        if (str == null) {
            throw new NullPointerException("fieldName is null");
        }
    }

    public final void _add(String str, IonValueLite ionValueLite) {
        this.hasNullFieldName |= str == null;
        add(this._child_count, ionValueLite);
        if (this._field_map != null) {
            add_field(str, ionValueLite._elementid());
        }
    }

    @Override // com.amazon.ion.impl.lite.IonContainerLite, com.amazon.ion.impl.lite.IonValueLite, com.amazon.ion.IonValue
    public void accept(ValueVisitor valueVisitor) throws Exception {
        valueVisitor.visit(this);
    }

    @Override // com.amazon.ion.impl.lite.IonContainerLite, com.amazon.ion.IonSequence
    public boolean add(IonValue ionValue) throws ContainedValueException, IllegalArgumentException, NullPointerException {
        _add(ionValue.getFieldNameSymbol().getText(), (IonValueLite) ionValue);
        return true;
    }

    public final void add_field(String str, int i) {
        Integer num = this._field_map.get(str);
        if (num != null) {
            this._field_map_duplicate_count++;
            if (num.intValue() > i) {
                i = num.intValue();
            }
        }
        this._field_map.put(str, Integer.valueOf(i));
    }

    public void build_field_map() {
        IonValueLite[] ionValueLiteArr = this._children;
        this._field_map = new HashMap(ionValueLiteArr == null ? 0 : ionValueLiteArr.length);
        this._field_map_duplicate_count = 0;
        int i = this._child_count;
        for (int i2 = 0; i2 < i; i2++) {
            String text = get_child(i2).getFieldNameSymbol().getText();
            if (this._field_map.get(text) != null) {
                this._field_map_duplicate_count++;
            }
            this._field_map.put(text, Integer.valueOf(i2));
        }
    }

    @Override // com.amazon.ion.impl.lite.IonContainerLite, com.amazon.ion.IonContainer
    public void clear() {
        super.clear();
        this._field_map = null;
        this._field_map_duplicate_count = 0;
    }

    @Override // com.amazon.ion.IonStruct
    public IonStruct cloneAndRemove(String... strArr) {
        return doClone(false, strArr);
    }

    @Override // com.amazon.ion.IonStruct
    public IonStruct cloneAndRetain(String... strArr) {
        return doClone(true, strArr);
    }

    @Override // com.amazon.ion.IonStruct
    public boolean containsKey(Object obj) {
        return get((String) obj) != null;
    }

    @Override // com.amazon.ion.IonStruct
    public boolean containsValue(Object obj) {
        return ((IonValue) obj).getContainer() == this;
    }

    public final IonStruct doClone(boolean z, String... strArr) {
        IonStructLite ionStructLiteNewNullStruct;
        if (is_true(4)) {
            ionStructLiteNewNullStruct = this.ionSystem.newNullStruct();
        } else {
            HashSet hashSet = new HashSet(Arrays.asList(strArr));
            if (z && hashSet.contains(null)) {
                throw new NullPointerException("Can't retain an unknown field name");
            }
            IonStructLite ionStructLiteNewEmptyStruct = this.ionSystem.newEmptyStruct();
            ListIterator<IonValue> listIterator = listIterator(0);
            while (true) {
                IonContainerLite.SequenceContentIterator sequenceContentIterator = (IonContainerLite.SequenceContentIterator) listIterator;
                if (!sequenceContentIterator.hasNext()) {
                    break;
                }
                IonValue next = sequenceContentIterator.next();
                if (hashSet.contains(next.getFieldNameSymbol().getText()) == z) {
                    ionStructLiteNewEmptyStruct.add(next.getFieldName(), next.mo245clone());
                }
            }
            ionStructLiteNewNullStruct = ionStructLiteNewEmptyStruct;
        }
        ionStructLiteNewNullStruct.setTypeAnnotationSymbols(getTypeAnnotationSymbols());
        return ionStructLiteNewNullStruct;
    }

    @Override // com.amazon.ion.impl.lite.IonValueLite, com.amazon.ion.impl._Private_IonValue
    public void dump(PrintWriter printWriter) {
        printWriter.println(this);
        if (this._field_map == null) {
            return;
        }
        printWriter.println("   dups: " + this._field_map_duplicate_count);
        printWriter.print("   map: [");
        boolean z = true;
        for (Map.Entry<String, Integer> entry : this._field_map.entrySet()) {
            if (!z) {
                printWriter.print(",");
            }
            printWriter.print(entry.getKey() + ":" + entry.getValue());
            z = false;
        }
        printWriter.println("]");
    }

    public final int find_field_helper(String str) {
        validateFieldName(str);
        if (is_true(4)) {
            return -1;
        }
        Map<String, Integer> map = this._field_map;
        if (map != null) {
            Integer num = map.get(str);
            if (num != null) {
                return num.intValue();
            }
            return -1;
        }
        int i = this._child_count;
        for (int i2 = 0; i2 < i; i2++) {
            if (str.equals(get_child(i2).getFieldName())) {
                return i2;
            }
        }
        return -1;
    }

    public final int find_last_duplicate(String str, int i) {
        while (i > 0) {
            i--;
            if (str.equals(get_child(i).getFieldName())) {
                return i;
            }
        }
        return -1;
    }

    @Override // com.amazon.ion.IonStruct
    public IonValue get(String str) {
        int iFind_field_helper = find_field_helper(str);
        if (iFind_field_helper >= 0) {
            return get_child(iFind_field_helper);
        }
        if (this.hasNullFieldName) {
            throw new UnknownSymbolException("Unable to determine whether the field exists because the struct contains field names with unknown text.");
        }
        return null;
    }

    @Override // com.amazon.ion.impl.lite.IonValueLite, com.amazon.ion.IonValue
    public IonType getType() {
        return IonType.STRUCT;
    }

    @Override // com.amazon.ion.impl.lite.IonValueLite
    public int hashCode(_Private_IonValue.SymbolTableProvider symbolTableProvider) {
        int i = HASH_SIGNATURE;
        if (!is_true(4)) {
            ListIterator<IonValue> listIterator = listIterator(0);
            while (true) {
                IonContainerLite.SequenceContentIterator sequenceContentIterator = (IonContainerLite.SequenceContentIterator) listIterator;
                if (!sequenceContentIterator.hasNext()) {
                    break;
                }
                IonValueLite ionValueLite = (IonValueLite) sequenceContentIterator.next();
                SymbolToken fieldNameSymbol = ionValueLite.getFieldNameSymbol(symbolTableProvider);
                String text = fieldNameSymbol.getText();
                int sid = text == null ? fieldNameSymbol.getSid() * 127 : text.hashCode() * 31;
                int iHashCode = ((ionValueLite.hashCode(symbolTableProvider) + (HASH_SIGNATURE * IonTokenConstsX.KW_ALL_BITS)) * 16777619) + (sid ^ ((sid << 17) ^ (sid >> 15)));
                i += iHashCode ^ ((iHashCode << 19) ^ (iHashCode >> 13));
            }
        }
        return hashTypeAnnotations(i, symbolTableProvider);
    }

    @Override // com.amazon.ion.impl.lite.IonContainerLite, com.amazon.ion.IonSequence, java.util.List
    public ListIterator<IonValue> listIterator(int i) {
        return new IonContainerLite.SequenceContentIterator(i, is_true(1)) { // from class: com.amazon.ion.impl.lite.IonStructLite.3
            public static final /* synthetic */ boolean $assertionsDisabled = false;

            @Override // com.amazon.ion.impl.lite.IonContainerLite.SequenceContentIterator, java.util.ListIterator, java.util.Iterator
            public void remove() {
                if (this.__readOnly) {
                    throw new UnsupportedOperationException();
                }
                force_position_sync();
                int i2 = this.__pos;
                if (!this.__lastMoveWasPrevious) {
                    i2--;
                }
                if (i2 < 0) {
                    throw new ArrayIndexOutOfBoundsException();
                }
                IonValueLite ionValueLite = this.__current;
                ionValueLite._elementid();
                if (IonStructLite.this._field_map != null) {
                    IonStructLite.this.remove_field_from_field_map(ionValueLite.getFieldName(), i2);
                }
                super.remove();
                IonStructLite ionStructLite = IonStructLite.this;
                if (ionStructLite._field_map != null) {
                    ionStructLite.patch_map_elements_helper(i2);
                }
            }
        };
    }

    public final void patch_map_elements_helper(int i) {
        if (this._field_map != null && i < this._child_count) {
            while (i < this._child_count) {
                String fieldName = get_child(i).getFieldName();
                if (this._field_map.get(fieldName).intValue() != i) {
                    this._field_map.put(fieldName, Integer.valueOf(i));
                }
                i++;
            }
        }
    }

    @Override // com.amazon.ion.IonStruct
    public ValueFactory put(final String str) {
        return new _Private_CurriedValueFactory(this.ionSystem) { // from class: com.amazon.ion.impl.lite.IonStructLite.2
            @Override // com.amazon.ion.impl._Private_CurriedValueFactory
            public void handle(IonValue ionValue) {
                IonStructLite.this.put(str, ionValue);
            }
        };
    }

    @Override // com.amazon.ion.IonStruct
    public void putAll(Map<? extends String, ? extends IonValue> map) {
        for (Map.Entry<? extends String, ? extends IonValue> entry : map.entrySet()) {
            put(entry.getKey(), entry.getValue());
        }
    }

    @Override // com.amazon.ion.IonStruct
    public IonValue remove(String str) {
        checkForLock();
        IonValue ionValue = get(str);
        if (ionValue == null) {
            return null;
        }
        int i_elementid = ((IonValueLite) ionValue)._elementid();
        if (this._field_map != null) {
            remove_field_from_field_map(str, i_elementid);
        }
        super.remove(ionValue);
        if (this._field_map != null) {
            patch_map_elements_helper(i_elementid);
        }
        return ionValue;
    }

    @Override // com.amazon.ion.IonStruct
    public boolean removeAll(String... strArr) {
        checkForLock();
        int i = this._child_count;
        boolean z = false;
        while (i > 0) {
            i--;
            IonValueLite ionValueLite = get_child(i);
            if (isListedField(ionValueLite, strArr)) {
                ionValueLite.removeFromContainer();
                z = true;
            }
        }
        return z;
    }

    public final void remove_field(String str, int i, int i2) {
        Map<String, Integer> map = this._field_map;
        if (map == null) {
            return;
        }
        map.get(str);
        this._field_map.remove(str);
        this._field_map_duplicate_count -= i2 - 1;
    }

    public final void remove_field_from_field_map(String str, int i) {
        if (this._field_map.get(str).intValue() != i) {
            this._field_map_duplicate_count--;
            return;
        }
        if (this._field_map_duplicate_count <= 0) {
            this._field_map.remove(str);
            return;
        }
        int iFind_last_duplicate = find_last_duplicate(str, i);
        if (iFind_last_duplicate == -1) {
            this._field_map.remove(str);
        } else {
            this._field_map.put(str, Integer.valueOf(iFind_last_duplicate));
            this._field_map_duplicate_count--;
        }
    }

    @Override // com.amazon.ion.IonStruct
    public boolean retainAll(String... strArr) {
        checkForLock();
        int i = this._child_count;
        boolean z = false;
        while (i > 0) {
            i--;
            IonValueLite ionValueLite = get_child(i);
            if (!isListedField(ionValueLite, strArr)) {
                ionValueLite.removeFromContainer();
                z = true;
            }
        }
        return z;
    }

    public final boolean there_is_only_one(String str, int i) {
        int i2 = 0;
        for (int i3 = 0; i3 < this._child_count; i3++) {
            if (get_child(i3).getFieldName().equals(str)) {
                i2++;
            }
        }
        return i2 == 1 || i2 == 0;
    }

    @Override // com.amazon.ion.impl.lite.IonContainerLite
    public void transitionToLargeSize(int i) {
        if (this._field_map != null) {
            return;
        }
        build_field_map();
    }

    @Override // com.amazon.ion.impl.lite.IonValueLite, com.amazon.ion.impl._Private_IonValue
    public String validate() {
        Map<String, Integer> map = this._field_map;
        if (map == null) {
            return null;
        }
        String str = "";
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            int iIntValue = entry.getValue().intValue();
            IonValueLite ionValueLite = (iIntValue < 0 || iIntValue >= this._child_count) ? null : get_child(iIntValue);
            if (ionValueLite == null || iIntValue != ionValueLite._elementid() || !entry.getKey().equals(ionValueLite.getFieldName())) {
                str = str + "map entry [" + entry + "] doesn't match list value [" + ionValueLite + "]\n";
            }
        }
        if (str == "") {
            return null;
        }
        return str;
    }

    @Override // com.amazon.ion.impl.lite.IonValueLite
    public final void writeBodyTo(IonWriter ionWriter, _Private_IonValue.SymbolTableProvider symbolTableProvider) throws IOException {
        if (is_true(4)) {
            ionWriter.writeNull(IonType.STRUCT);
            return;
        }
        ionWriter.stepIn(IonType.STRUCT);
        writeChildren(ionWriter, this, symbolTableProvider);
        ionWriter.stepOut();
    }

    public IonStructLite(IonStructLite ionStructLite, IonContext ionContext) {
        super(ionStructLite, ionContext, true);
        this.hasNullFieldName = false;
        this._field_map = ionStructLite._field_map == null ? null : new HashMap(ionStructLite._field_map);
        this._field_map_duplicate_count = ionStructLite._field_map_duplicate_count;
        this.hasNullFieldName = ionStructLite.hasNullFieldName;
    }

    @Override // com.amazon.ion.IonStruct
    public ValueFactory add(final String str) {
        return new _Private_CurriedValueFactory(this.ionSystem) { // from class: com.amazon.ion.impl.lite.IonStructLite.1
            @Override // com.amazon.ion.impl._Private_CurriedValueFactory
            public void handle(IonValue ionValue) {
                IonStructLite.this.add(str, ionValue);
            }
        };
    }

    @Override // com.amazon.ion.IonStruct
    public void put(String str, IonValue ionValue) {
        checkForLock();
        validateFieldName(str);
        if (ionValue != null) {
            validateNewChild(ionValue);
        }
        int iIntValue = this._child_count;
        Map<String, Integer> map = this._field_map;
        boolean z = false;
        if (map == null || this._field_map_duplicate_count != 0) {
            int i = iIntValue;
            int i2 = 0;
            while (iIntValue > 0) {
                iIntValue--;
                if (str.equals(get_child(iIntValue).getFieldNameSymbol().getText())) {
                    remove_child(iIntValue);
                    i2++;
                    i = iIntValue;
                    z = true;
                }
            }
            if (z) {
                remove_field(str, i, i2);
            }
            iIntValue = i;
        } else {
            Integer num = map.get(str);
            if (num != null) {
                iIntValue = num.intValue();
                remove_field_from_field_map(str, iIntValue);
                remove_child(iIntValue);
                z = true;
            }
        }
        if (z) {
            patch_map_elements_helper(iIntValue);
            patch_elements_helper(iIntValue);
        }
        if (ionValue != null) {
            add(str, ionValue);
        }
    }

    @Override // com.amazon.ion.impl.lite.IonValueLite
    public IonStructLite clone(IonContext ionContext) {
        return new IonStructLite(this, ionContext);
    }

    @Override // com.amazon.ion.impl.lite.IonValueLite
    public IonValueLite clone(IonContext ionContext) {
        return new IonStructLite(this, ionContext);
    }

    @Override // com.amazon.ion.IonStruct
    public void add(String str, IonValue ionValue) {
        checkForLock();
        validateNewChild(ionValue);
        validateFieldName(str);
        IonValueLite ionValueLite = (IonValueLite) ionValue;
        _add(str, ionValueLite);
        ionValueLite._fieldName = str;
    }

    @Override // com.amazon.ion.impl.lite.IonContainerLite, com.amazon.ion.impl.lite.IonValueLite
    /* JADX INFO: renamed from: clone */
    public IonStructLite mo245clone() {
        return new IonStructLite(this, ContainerlessContext.wrap(this.ionSystem));
    }

    @Override // com.amazon.ion.impl.lite.IonContainerLite, com.amazon.ion.IonContainer
    public boolean remove(IonValue ionValue) {
        ionValue.getClass();
        checkForLock();
        if (ionValue.getContainer() != this) {
            return false;
        }
        IonValueLite ionValueLite = (IonValueLite) ionValue;
        int i_elementid = ionValueLite._elementid();
        if (this._field_map != null) {
            remove_field_from_field_map(ionValueLite.getFieldName(), i_elementid);
        }
        super.remove(ionValueLite);
        if (this._field_map == null) {
            return true;
        }
        patch_map_elements_helper(i_elementid);
        return true;
    }

    @Override // com.amazon.ion.IonStruct
    public void add(SymbolToken symbolToken, IonValue ionValue) {
        String text = symbolToken.getText();
        if (text != null) {
            add(text, ionValue);
            return;
        }
        if (symbolToken.getSid() >= 0) {
            checkForLock();
            validateNewChild(ionValue);
            IonValueLite ionValueLite = (IonValueLite) ionValue;
            ionValueLite.setFieldNameSymbol(symbolToken);
            _add(text, ionValueLite);
            return;
        }
        throw new IllegalArgumentException("fieldName has no text or ID");
    }
}
