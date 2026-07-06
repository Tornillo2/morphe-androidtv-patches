package com.amazon.ion.impl.lite;

import com.amazon.ion.IonType;
import com.amazon.ion.IonWriter;
import com.amazon.ion.NullValueException;
import com.amazon.ion.SymbolTable;
import com.amazon.ion.SymbolToken;
import com.amazon.ion.UnknownSymbolException;
import com.amazon.ion.ValueVisitor;
import com.amazon.ion.impl.SymbolTokenImpl;
import com.amazon.ion.impl._Private_IonSymbol;
import com.amazon.ion.impl._Private_IonValue;
import com.amazon.ion.impl._Private_Utils;
import com.amazon.ion.impl.lite.IonValueLite;
import java.io.IOException;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public final class IonSymbolLite extends IonTextLite implements _Private_IonSymbol {
    public static final /* synthetic */ boolean $assertionsDisabled = false;
    public static final int HASH_SIGNATURE = IonType.SYMBOL.toString().hashCode();
    public int _sid;

    public IonSymbolLite(ContainerlessContext containerlessContext, SymbolToken symbolToken) {
        super(containerlessContext, symbolToken == null);
        this._sid = -1;
        if (symbolToken != null) {
            String text = symbolToken.getText();
            int sid = symbolToken.getSid();
            if (text != null) {
                super.setValue(text);
            } else {
                this._sid = sid;
                set_flag(128);
            }
        }
    }

    public final String _stringValue(_Private_IonValue.SymbolTableProvider symbolTableProvider) {
        String str = this._text_value;
        if (str != null) {
            return str;
        }
        String strFindKnownSymbol = symbolTableProvider.getSymbolTable().findKnownSymbol(this._sid);
        if (strFindKnownSymbol != null && !is_true(1)) {
            _set_value(strFindKnownSymbol);
        }
        return strFindKnownSymbol;
    }

    @Override // com.amazon.ion.impl.lite.IonValueLite, com.amazon.ion.IonValue
    public void accept(ValueVisitor valueVisitor) throws Exception {
        valueVisitor.visit(this);
    }

    @Override // com.amazon.ion.impl.lite.IonValueLite
    public boolean attemptClearSymbolIDValues() {
        boolean zAttemptClearSymbolIDValues = super.attemptClearSymbolIDValues();
        if (is_true(4) || this._sid == -1) {
            return zAttemptClearSymbolIDValues;
        }
        if (_stringValue() == null) {
            return false;
        }
        this._sid = -1;
        return zAttemptClearSymbolIDValues;
    }

    @Override // com.amazon.ion.IonSymbol
    @Deprecated
    public int getSymbolId() throws NullValueException {
        return getSymbolId(null);
    }

    @Override // com.amazon.ion.impl.lite.IonValueLite, com.amazon.ion.IonValue
    public IonType getType() {
        return IonType.SYMBOL;
    }

    @Override // com.amazon.ion.impl.lite.IonValueLite
    public int hashCode(_Private_IonValue.SymbolTableProvider symbolTableProvider) {
        int i = HASH_SIGNATURE;
        if (!is_true(4)) {
            SymbolToken symbolTokenSymbolValue = symbolValue(symbolTableProvider);
            String str = ((SymbolTokenImpl) symbolTokenSymbolValue).myText;
            int iHashCode = str == null ? ((SymbolTokenImpl) symbolTokenSymbolValue).mySid * 127 : str.hashCode() * 31;
            i ^= iHashCode ^ ((iHashCode << 29) ^ (iHashCode >> 3));
        }
        return hashTypeAnnotations(i, symbolTableProvider);
    }

    public boolean isIonVersionMarker() {
        return is_true(16);
    }

    public void setIsIonVersionMarker(boolean z) {
        _isIVM(z);
        _isSystemValue(z);
        this._sid = 2;
    }

    public final void setSID(int i) {
        this._sid = i;
        if (i > 0) {
            cascadeSIDPresentToContextRoot();
        }
    }

    @Override // com.amazon.ion.impl.lite.IonTextLite, com.amazon.ion.IonText
    public void setValue(String str) {
        super.setValue(str);
        this._sid = -1;
    }

    public final String stringValue(_Private_IonValue.SymbolTableProvider symbolTableProvider) throws UnknownSymbolException {
        if (is_true(4)) {
            return null;
        }
        String str_stringValue = _stringValue(symbolTableProvider);
        if (str_stringValue != null) {
            return str_stringValue;
        }
        throw new UnknownSymbolException(this._sid);
    }

    @Override // com.amazon.ion.impl._Private_IonSymbol
    public SymbolToken symbolValue(_Private_IonValue.SymbolTableProvider symbolTableProvider) {
        if (is_true(4)) {
            return null;
        }
        return _Private_Utils.newSymbolToken(_stringValue(symbolTableProvider), getSymbolId(symbolTableProvider));
    }

    @Override // com.amazon.ion.impl.lite.IonValueLite
    public final void writeBodyTo(IonWriter ionWriter, _Private_IonValue.SymbolTableProvider symbolTableProvider) throws IOException {
        ionWriter.writeSymbolToken(symbolValue(symbolTableProvider));
    }

    public final int getSymbolId(_Private_IonValue.SymbolTableProvider symbolTableProvider) throws NullValueException {
        validateThisNotNull();
        if (this._sid != -1 || is_true(1)) {
            return this._sid;
        }
        SymbolTable symbolTable = symbolTableProvider != null ? symbolTableProvider.getSymbolTable() : getSymbolTable();
        if (symbolTable == null) {
            symbolTable = this._context.getSystem()._system_symbol_table;
        }
        String str = this._text_value;
        if (!symbolTable.isLocalTable()) {
            setSID(symbolTable.findSymbol(str));
            if (this._sid > 0 || is_true(1)) {
                return this._sid;
            }
        }
        SymbolToken symbolTokenFind = symbolTable.find(str);
        if (symbolTokenFind != null) {
            setSID(symbolTokenFind.getSid());
            _set_value(symbolTokenFind.getText());
        }
        return this._sid;
    }

    @Override // com.amazon.ion.impl.lite.IonTextLite, com.amazon.ion.IonText
    public String stringValue() throws UnknownSymbolException {
        return stringValue(new IonValueLite.LazySymbolTableProvider(this));
    }

    @Override // com.amazon.ion.IonSymbol
    public SymbolToken symbolValue() {
        return symbolValue(new IonValueLite.LazySymbolTableProvider(this));
    }

    public final String _stringValue() {
        return _stringValue(new IonValueLite.LazySymbolTableProvider(this));
    }

    @Override // com.amazon.ion.impl.lite.IonValueLite
    public IonSymbolLite clone(IonContext ionContext) {
        IonSymbolLite ionSymbolLite = new IonSymbolLite(this, ionContext);
        if (this._sid == 0) {
            ionSymbolLite._sid = 0;
        }
        return ionSymbolLite;
    }

    public IonSymbolLite(ContainerlessContext containerlessContext, boolean z) {
        super(containerlessContext, z);
        this._sid = -1;
    }

    public IonSymbolLite(IonSymbolLite ionSymbolLite, IonContext ionContext) throws UnknownSymbolException {
        super(ionSymbolLite, ionContext);
        this._sid = -1;
    }

    @Override // com.amazon.ion.impl.lite.IonTextLite, com.amazon.ion.impl.lite.IonValueLite
    /* JADX INFO: renamed from: clone */
    public IonSymbolLite mo245clone() throws UnknownSymbolException {
        int i;
        if (!is_true(4) && (i = this._sid) != -1 && i != 0 && _stringValue() == null) {
            throw new UnknownSymbolException(this._sid);
        }
        return clone((IonContext) ContainerlessContext.wrap(this._context.getSystem()));
    }
}
