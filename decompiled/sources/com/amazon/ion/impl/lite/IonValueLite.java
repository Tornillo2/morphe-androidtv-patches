package com.amazon.ion.impl.lite;

import com.amazon.ion.IonDatagram;
import com.amazon.ion.IonException;
import com.amazon.ion.IonType;
import com.amazon.ion.IonValue;
import com.amazon.ion.IonWriter;
import com.amazon.ion.NullValueException;
import com.amazon.ion.ReadOnlyValueException;
import com.amazon.ion.SymbolTable;
import com.amazon.ion.SymbolToken;
import com.amazon.ion.UnknownSymbolException;
import com.amazon.ion.ValueVisitor;
import com.amazon.ion.impl.IonTokenConstsX;
import com.amazon.ion.impl.IonWriterUser;
import com.amazon.ion.impl.SymbolTokenImpl;
import com.amazon.ion.impl._Private_IonValue;
import com.amazon.ion.impl._Private_IonWriter;
import com.amazon.ion.impl._Private_Utils;
import com.amazon.ion.system.IonTextWriterBuilder;
import com.amazon.ion.util.Equivalence;
import com.amazon.ion.util.Printer;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public abstract class IonValueLite implements _Private_IonValue {
    public static final /* synthetic */ boolean $assertionsDisabled = false;
    public static final int ELEMENT_MASK = 255;
    public static final int ELEMENT_SHIFT = 8;
    public static final int IS_AUTO_CREATED = 32;
    public static final int IS_BOOL_TRUE = 8;
    public static final int IS_IVM = 16;
    public static final int IS_LOCKED = 1;
    public static final int IS_NULL_VALUE = 4;
    public static final int IS_SYMBOL_ID_PRESENT = 128;
    public static final int IS_SYMBOL_PRESENT = 64;
    public static final int IS_SYSTEM_VALUE = 2;
    public static final int TYPE_ANNOTATION_HASH_SIGNATURE = -88227883;
    public SymbolToken[] _annotations;
    public IonContext _context;
    public int _fieldId = -1;
    public String _fieldName;
    public int _flags;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static class LazySymbolTableProvider implements _Private_IonValue.SymbolTableProvider {
        public SymbolTable symtab = null;
        public final IonValueLite value;

        public LazySymbolTableProvider(IonValueLite ionValueLite) {
            this.value = ionValueLite;
        }

        @Override // com.amazon.ion.impl._Private_IonValue.SymbolTableProvider
        public SymbolTable getSymbolTable() {
            if (this.symtab == null) {
                this.symtab = this.value.getSymbolTable();
            }
            return this.symtab;
        }
    }

    public IonValueLite(ContainerlessContext containerlessContext, boolean z) {
        this._context = containerlessContext;
        if (z) {
            set_flag(4);
        }
    }

    public final void _elementid(int i) {
        this._flags = (i << 8) | (this._flags & 255);
    }

    public final int _getMetadata(int i, int i2) {
        return (i & this._flags) >>> i2;
    }

    public final boolean _isAutoCreated() {
        return is_true(32);
    }

    public final boolean _isBoolTrue() {
        return is_true(8);
    }

    public final boolean _isIVM() {
        return is_true(16);
    }

    public final boolean _isLocked() {
        return is_true(1);
    }

    public final boolean _isNullValue() {
        return is_true(4);
    }

    public final boolean _isSymbolIdPresent() {
        return is_true(128);
    }

    public final boolean _isSymbolPresent() {
        return is_true(64);
    }

    public final boolean _isSystemValue() {
        return is_true(2);
    }

    public final void _setMetadata(int i, int i2, int i3) {
        this._flags = ((i << i3) & i2) | (this._flags & (~i2));
    }

    @Override // com.amazon.ion.IonValue
    public abstract void accept(ValueVisitor valueVisitor) throws Exception;

    @Override // com.amazon.ion.IonValue
    public void addTypeAnnotation(String str) {
        checkForLock();
        if (str != null) {
            if (str.length() >= 1) {
                if (hasTypeAnnotation(str)) {
                    return;
                }
                SymbolTokenImpl symbolTokenImplNewSymbolToken = _Private_Utils.newSymbolToken(str, -1);
                SymbolToken[] symbolTokenArr = this._annotations;
                int length = symbolTokenArr == null ? 0 : symbolTokenArr.length;
                if (length > 0) {
                    for (int i = 0; i < length; i++) {
                        SymbolToken[] symbolTokenArr2 = this._annotations;
                        if (symbolTokenArr2[i] == null) {
                            symbolTokenArr2[i] = symbolTokenImplNewSymbolToken;
                            return;
                        }
                    }
                }
                SymbolToken[] symbolTokenArr3 = new SymbolToken[length != 0 ? length * 2 : 1];
                if (length > 0) {
                    System.arraycopy(this._annotations, 0, symbolTokenArr3, 0, length);
                }
                this._annotations = symbolTokenArr3;
                symbolTokenArr3[length] = symbolTokenImplNewSymbolToken;
                return;
            }
        }
        throw new IllegalArgumentException("a user type annotation must be a non-empty string");
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x0015 A[LOOP:0: B:11:0x0015->B:21:0x0033, LOOP_START, PHI: r3
      0x0015: PHI (r3v1 int) = (r3v0 int), (r3v2 int) binds: [B:10:0x0013, B:21:0x0033] A[DONT_GENERATE, DONT_INLINE]] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean attemptClearSymbolIDValues() {
        /*
            r6 = this;
            java.lang.String r0 = r6._fieldName
            r1 = 1
            r2 = -1
            r3 = 0
            if (r0 == 0) goto La
            r6._fieldId = r2
            goto L10
        La:
            int r0 = r6._fieldId
            if (r0 <= r2) goto L10
            r0 = 1
            goto L11
        L10:
            r0 = 0
        L11:
            com.amazon.ion.SymbolToken[] r4 = r6._annotations
            if (r4 == 0) goto L36
        L15:
            com.amazon.ion.SymbolToken[] r4 = r6._annotations
            int r5 = r4.length
            if (r3 >= r5) goto L36
            r4 = r4[r3]
            if (r4 != 0) goto L1f
            goto L36
        L1f:
            java.lang.String r5 = r4.getText()
            if (r5 == 0) goto L33
            int r4 = r4.getSid()
            if (r4 == r2) goto L33
            com.amazon.ion.SymbolToken[] r4 = r6._annotations
            com.amazon.ion.impl.SymbolTokenImpl r5 = com.amazon.ion.impl._Private_Utils.newSymbolToken(r5, r2)
            r4[r3] = r5
        L33:
            int r3 = r3 + 1
            goto L15
        L36:
            r0 = r0 ^ r1
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.ion.impl.lite.IonValueLite.attemptClearSymbolIDValues():boolean");
    }

    public void cascadeSIDPresentToContextRoot() {
        for (IonValueLite container = this; container != null && !container.is_true(128); container = container.getContainer()) {
            container.set_flag(128);
        }
    }

    public final void checkForLock() throws ReadOnlyValueException {
        if (is_true(1)) {
            throw new ReadOnlyValueException();
        }
    }

    public final boolean clearSymbolIDValues() {
        if (!is_true(128)) {
            return true;
        }
        boolean zAttemptClearSymbolIDValues = attemptClearSymbolIDValues();
        if (zAttemptClearSymbolIDValues) {
            clear_flag(128);
        }
        return zAttemptClearSymbolIDValues;
    }

    @Override // com.amazon.ion.IonValue
    public final void clearTypeAnnotations() {
        checkForLock();
        SymbolToken[] symbolTokenArr = this._annotations;
        int length = symbolTokenArr == null ? 0 : symbolTokenArr.length;
        if (length > 0) {
            for (int i = 0; i < length; i++) {
                SymbolToken[] symbolTokenArr2 = this._annotations;
                if (symbolTokenArr2[i] == null) {
                    return;
                }
                symbolTokenArr2[i] = null;
            }
        }
    }

    public final void clear_flag(int i) {
        this._flags = (~i) & this._flags;
    }

    @Override // 
    /* JADX INFO: renamed from: clone */
    public abstract IonValue mo245clone();

    public abstract IonValueLite clone(IonContext ionContext);

    public final void detachFromContainer() {
        checkForLock();
        clearSymbolIDValues();
        this._context = ContainerlessContext.wrap(getSystem());
        this._fieldName = null;
        this._fieldId = -1;
        _elementid(0);
    }

    @Override // com.amazon.ion.impl._Private_IonValue
    public void dump(PrintWriter printWriter) {
        printWriter.println(this);
    }

    @Override // com.amazon.ion.IonValue
    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof IonValue) {
            return Equivalence.ionEquals(this, (IonValue) obj);
        }
        return false;
    }

    @Override // com.amazon.ion.impl._Private_IonValue
    public final int findTypeAnnotation(String str) {
        SymbolToken symbolToken;
        if (this._annotations == null) {
            return -1;
        }
        int i = 0;
        while (true) {
            SymbolToken[] symbolTokenArr = this._annotations;
            if (i >= symbolTokenArr.length || (symbolToken = symbolTokenArr[i]) == null) {
                return -1;
            }
            if (str.equals(symbolToken.getText())) {
                return i;
            }
            i++;
        }
    }

    @Override // com.amazon.ion.impl._Private_IonValue
    public SymbolTable getAssignedSymbolTable() {
        return this._context.getContextSymbolTable();
    }

    public IonContext getContext() {
        return this._context;
    }

    @Override // com.amazon.ion.impl._Private_IonValue
    public final int getElementId() {
        return _elementid();
    }

    @Override // com.amazon.ion.IonValue
    public final int getFieldId() {
        int i = this._fieldId;
        if (i != -1 || this._fieldName == null) {
            return i;
        }
        SymbolToken symbolTokenFind = getSymbolTable().find(this._fieldName);
        if (symbolTokenFind != null) {
            return symbolTokenFind.getSid();
        }
        return -1;
    }

    @Override // com.amazon.ion.IonValue
    public final String getFieldName() {
        String str = this._fieldName;
        if (str != null) {
            return str;
        }
        if (this._fieldId <= 0) {
            return null;
        }
        throw new UnknownSymbolException(this._fieldId);
    }

    public final int getFieldNameId() {
        return getFieldId();
    }

    @Override // com.amazon.ion.IonValue
    public SymbolToken getFieldNameSymbol() {
        return getFieldNameSymbol(new LazySymbolTableProvider(this));
    }

    public final SymbolToken getKnownFieldNameSymbol() {
        SymbolToken fieldNameSymbol = getFieldNameSymbol();
        if (fieldNameSymbol.getText() != null || fieldNameSymbol.getSid() == 0) {
            return fieldNameSymbol;
        }
        throw new UnknownSymbolException(this._fieldId);
    }

    @Override // com.amazon.ion.IonValue
    public SymbolTable getSymbolTable() {
        SymbolTable contextSymbolTable = topLevelValue()._context.getContextSymbolTable();
        return contextSymbolTable != null ? contextSymbolTable : getSystem()._system_symbol_table;
    }

    @Override // com.amazon.ion.IonValue
    public IonType getType() {
        throw new UnsupportedOperationException("this type " + getClass().getSimpleName() + " should not be instantiated, there is not IonType associated with it");
    }

    @Override // com.amazon.ion.IonValue
    public SymbolToken[] getTypeAnnotationSymbols() {
        return getTypeAnnotationSymbols(new LazySymbolTableProvider(this));
    }

    @Override // com.amazon.ion.IonValue
    public final String[] getTypeAnnotations() {
        int i = 0;
        if (this._annotations != null) {
            int i2 = 0;
            while (true) {
                SymbolToken[] symbolTokenArr = this._annotations;
                if (i >= symbolTokenArr.length || symbolTokenArr[i] == null) {
                    break;
                }
                i2 = i + 1;
                i = i2;
            }
            i = i2;
        }
        return i == 0 ? _Private_Utils.EMPTY_STRING_ARRAY : _Private_Utils.toStrings(this._annotations, i);
    }

    @Override // com.amazon.ion.IonValue
    public final boolean hasTypeAnnotation(String str) {
        return str != null && str.length() > 0 && findTypeAnnotation(str) >= 0;
    }

    @Override // com.amazon.ion.IonValue, java.util.List, java.util.Collection
    public int hashCode() {
        return hashCode(new LazySymbolTableProvider(this));
    }

    public abstract int hashCode(_Private_IonValue.SymbolTableProvider symbolTableProvider);

    public int hashTypeAnnotations(int i, _Private_IonValue.SymbolTableProvider symbolTableProvider) {
        SymbolToken[] typeAnnotationSymbols = getTypeAnnotationSymbols(symbolTableProvider);
        if (typeAnnotationSymbols.length == 0) {
            return i;
        }
        int length = (i * IonTokenConstsX.KW_ALL_BITS) + typeAnnotationSymbols.length;
        for (SymbolToken symbolToken : typeAnnotationSymbols) {
            String text = symbolToken.getText();
            int sid = text == null ? symbolToken.getSid() * 127 : text.hashCode() * 31;
            int i2 = (length * IonTokenConstsX.KW_ALL_BITS) + (sid ^ ((sid << 19) ^ (sid >> 13)));
            length = i2 ^ ((i2 << 25) ^ (i2 >> 7));
        }
        return length;
    }

    @Override // com.amazon.ion.IonValue
    public final boolean isNullValue() {
        return is_true(4);
    }

    @Override // com.amazon.ion.IonValue
    public final boolean isReadOnly() {
        return is_true(1);
    }

    public final boolean is_true(int i) {
        return (i & this._flags) != 0;
    }

    @Override // com.amazon.ion.IonValue
    public void makeReadOnly() {
        if (is_true(1)) {
            return;
        }
        makeReadOnlyInternal();
    }

    public void makeReadOnlyInternal() {
        clearSymbolIDValues();
        set_flag(1);
    }

    @Override // com.amazon.ion.IonValue
    public boolean removeFromContainer() {
        checkForLock();
        IonContainerLite contextContainer = this._context.getContextContainer();
        if (contextContainer != null) {
            return contextContainer.remove(this);
        }
        return false;
    }

    @Override // com.amazon.ion.IonValue
    public void removeTypeAnnotation(String str) {
        int iFindTypeAnnotation;
        SymbolToken[] symbolTokenArr;
        int i;
        SymbolToken symbolToken;
        checkForLock();
        if (str == null || str.length() <= 0 || (iFindTypeAnnotation = findTypeAnnotation(str)) < 0) {
            return;
        }
        while (true) {
            symbolTokenArr = this._annotations;
            if (iFindTypeAnnotation >= symbolTokenArr.length - 1 || (symbolToken = symbolTokenArr[(i = iFindTypeAnnotation + 1)]) == null) {
                break;
            }
            symbolTokenArr[iFindTypeAnnotation] = symbolToken;
            iFindTypeAnnotation = i;
        }
        if (iFindTypeAnnotation < symbolTokenArr.length) {
            symbolTokenArr[iFindTypeAnnotation] = null;
        }
    }

    public final void setContext(IonContext ionContext) {
        checkForLock();
        clearSymbolIDValues();
        this._context = ionContext;
    }

    public final void setFieldName(String str) {
        this._fieldName = str;
    }

    public final void setFieldNameSymbol(SymbolToken symbolToken) {
        this._fieldName = symbolToken.getText();
        int sid = symbolToken.getSid();
        this._fieldId = sid;
        if (-1 == sid || is_true(128)) {
            return;
        }
        cascadeSIDPresentToContextRoot();
    }

    @Override // com.amazon.ion.impl._Private_IonValue
    public void setSymbolTable(SymbolTable symbolTable) {
        if (getContext() instanceof TopLevelContext) {
            ((IonDatagramLite) getContainer()).setSymbolTableAtIndex(_elementid(), symbolTable);
        } else {
            if (topLevelValue() != this) {
                throw new UnsupportedOperationException("can't set the symboltable of a child value");
            }
            setContext(new ContainerlessContext(getContext().getSystem(), symbolTable));
        }
    }

    @Override // com.amazon.ion.IonValue
    public void setTypeAnnotationSymbols(SymbolToken... symbolTokenArr) {
        checkForLock();
        if (symbolTokenArr == null || symbolTokenArr.length == 0) {
            this._annotations = SymbolToken.EMPTY_ARRAY;
            return;
        }
        this._annotations = (SymbolToken[]) symbolTokenArr.clone();
        if (is_true(128)) {
            return;
        }
        for (SymbolToken symbolToken : this._annotations) {
            if (symbolToken != null && -1 != symbolToken.getSid()) {
                cascadeSIDPresentToContextRoot();
                return;
            }
        }
    }

    @Override // com.amazon.ion.IonValue
    public void setTypeAnnotations(String... strArr) {
        checkForLock();
        this._annotations = _Private_Utils.newSymbolTokens(getSymbolTable(), strArr);
    }

    public final void set_flag(int i) {
        this._flags = i | this._flags;
    }

    @Override // com.amazon.ion.IonValue
    public String toPrettyString() {
        return toString(IonTextWriterBuilder.pretty());
    }

    @Override // com.amazon.ion.IonValue
    public String toString() {
        StringBuilder sb = new StringBuilder(1024);
        try {
            new Printer().print(this, sb);
            return sb.toString();
        } catch (IOException e) {
            throw new IonException(e.getMessage(), e);
        }
    }

    @Override // com.amazon.ion.impl._Private_IonValue
    public String validate() {
        return null;
    }

    public final void validateThisNotNull() throws NullValueException {
        if (is_true(4)) {
            throw new NullValueException();
        }
    }

    public abstract void writeBodyTo(IonWriter ionWriter, _Private_IonValue.SymbolTableProvider symbolTableProvider) throws IOException;

    public final void writeChildren(IonWriter ionWriter, Iterable<IonValue> iterable, _Private_IonValue.SymbolTableProvider symbolTableProvider) {
        boolean z = this instanceof IonDatagram;
        Iterator<IonValue> it = iterable.iterator();
        while (it.hasNext()) {
            IonValueLite ionValueLite = (IonValueLite) it.next();
            if (z) {
                ionValueLite.writeTo(ionWriter);
            } else {
                ionValueLite.writeTo(ionWriter, symbolTableProvider);
            }
        }
    }

    @Override // com.amazon.ion.IonValue
    public void writeTo(IonWriter ionWriter) {
        writeTo(ionWriter, new LazySymbolTableProvider(this));
    }

    public final boolean _isAutoCreated(boolean z) {
        if (z) {
            set_flag(32);
            return z;
        }
        clear_flag(32);
        return z;
    }

    public final boolean _isBoolTrue(boolean z) {
        if (z) {
            set_flag(8);
            return z;
        }
        clear_flag(8);
        return z;
    }

    public final boolean _isIVM(boolean z) {
        if (z) {
            set_flag(16);
            return z;
        }
        clear_flag(16);
        return z;
    }

    public final boolean _isLocked(boolean z) {
        if (z) {
            set_flag(1);
            return z;
        }
        clear_flag(1);
        return z;
    }

    public final boolean _isNullValue(boolean z) {
        if (z) {
            set_flag(4);
            return z;
        }
        clear_flag(4);
        return z;
    }

    public final boolean _isSymbolIdPresent(boolean z) {
        if (z) {
            set_flag(128);
            return z;
        }
        clear_flag(128);
        return z;
    }

    public final boolean _isSymbolPresent(boolean z) {
        if (z) {
            set_flag(64);
            return z;
        }
        clear_flag(64);
        return z;
    }

    public final boolean _isSystemValue(boolean z) {
        if (z) {
            set_flag(2);
            return z;
        }
        clear_flag(2);
        return z;
    }

    @Override // com.amazon.ion.IonValue
    public IonContainerLite getContainer() {
        return this._context.getContextContainer();
    }

    @Override // com.amazon.ion.impl._Private_IonValue
    public final SymbolToken getFieldNameSymbol(_Private_IonValue.SymbolTableProvider symbolTableProvider) {
        SymbolToken symbolTokenFind;
        int i = this._fieldId;
        String strFindKnownSymbol = this._fieldName;
        if (strFindKnownSymbol != null) {
            if (i == -1 && (symbolTokenFind = symbolTableProvider.getSymbolTable().find(strFindKnownSymbol)) != null) {
                return symbolTokenFind;
            }
        } else if (i > 0) {
            strFindKnownSymbol = symbolTableProvider.getSymbolTable().findKnownSymbol(i);
        } else if (i != 0) {
            return null;
        }
        return _Private_Utils.newSymbolToken(strFindKnownSymbol, i);
    }

    @Override // com.amazon.ion.IonValue
    public IonSystemLite getSystem() {
        return this._context.getSystem();
    }

    @Override // com.amazon.ion.impl._Private_IonValue
    public final SymbolToken[] getTypeAnnotationSymbols(_Private_IonValue.SymbolTableProvider symbolTableProvider) {
        int i;
        SymbolToken symbolTokenFind;
        if (this._annotations != null) {
            int i2 = 0;
            i = 0;
            while (true) {
                SymbolToken[] symbolTokenArr = this._annotations;
                if (i2 >= symbolTokenArr.length) {
                    break;
                }
                if (symbolTokenArr[i2] != null) {
                    i++;
                }
                i2++;
            }
        } else {
            i = 0;
        }
        if (i == 0) {
            return SymbolToken.EMPTY_ARRAY;
        }
        SymbolToken[] symbolTokenArr2 = new SymbolToken[i];
        for (int i3 = 0; i3 < i; i3++) {
            SymbolToken symbolToken = this._annotations[i3];
            String text = symbolToken.getText();
            if (text != null && symbolToken.getSid() == -1 && (symbolTokenFind = symbolTableProvider.getSymbolTable().find(text)) != null) {
                symbolToken = symbolTokenFind;
            }
            symbolTokenArr2[i3] = symbolToken;
        }
        return symbolTokenArr2;
    }

    @Override // com.amazon.ion.IonValue
    public IonValueLite topLevelValue() {
        IonValueLite ionValueLite = this;
        while (true) {
            IonContainerLite contextContainer = ionValueLite._context.getContextContainer();
            if (contextContainer == null || (contextContainer instanceof IonDatagram)) {
                break;
            }
            ionValueLite = contextContainer;
        }
        return ionValueLite;
    }

    public final void writeTo(IonWriter ionWriter, _Private_IonValue.SymbolTableProvider symbolTableProvider) {
        if (ionWriter.isInStruct() && !((_Private_IonWriter) ionWriter).isFieldNameSet()) {
            SymbolToken fieldNameSymbol = getFieldNameSymbol(symbolTableProvider);
            if (fieldNameSymbol == null) {
                throw new IllegalStateException("Field name not set");
            }
            ionWriter.setFieldNameSymbol(fieldNameSymbol);
        }
        ionWriter.setTypeAnnotationSymbols(getTypeAnnotationSymbols(symbolTableProvider));
        try {
            writeBodyTo(ionWriter, symbolTableProvider);
        } catch (IOException e) {
            throw new IonException(e.getMessage(), e);
        }
    }

    public final int _elementid() {
        return this._flags >>> 8;
    }

    public IonValueLite(IonValueLite ionValueLite, IonContext ionContext) {
        SymbolToken[] symbolTokenArr = ionValueLite._annotations;
        boolean z = false;
        if (symbolTokenArr == null) {
            this._annotations = null;
        } else {
            int length = symbolTokenArr.length;
            this._annotations = new SymbolToken[length];
            boolean z2 = false;
            for (int i = 0; i < length; i++) {
                SymbolToken symbolToken = ionValueLite._annotations[i];
                if (symbolToken != null) {
                    String text = symbolToken.getText();
                    if (text != null) {
                        this._annotations[i] = _Private_Utils.newSymbolToken(text, -1);
                    } else {
                        SymbolToken[] symbolTokenArr2 = this._annotations;
                        SymbolToken symbolToken2 = ionValueLite._annotations[i];
                        symbolTokenArr2[i] = symbolToken2;
                        z2 |= symbolToken2.getSid() > -1;
                    }
                }
            }
            z = z2;
        }
        this._flags = ionValueLite._flags;
        this._context = ionContext;
        clear_flag(1);
        _isSymbolIdPresent(z);
    }

    @Override // com.amazon.ion.IonValue
    public String toString(IonTextWriterBuilder ionTextWriterBuilder) {
        StringBuilder sb = new StringBuilder(1024);
        try {
            IonWriter ionWriterBuild = ionTextWriterBuilder.build(sb);
            writeTo(ionWriterBuild);
            ((IonWriterUser) ionWriterBuild).finish();
            return sb.toString();
        } catch (IOException e) {
            throw new IonException(e.getMessage(), e);
        }
    }
}
