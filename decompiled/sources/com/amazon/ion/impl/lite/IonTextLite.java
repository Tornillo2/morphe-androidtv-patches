package com.amazon.ion.impl.lite;

import com.amazon.ion.IonText;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public abstract class IonTextLite extends IonValueLite implements IonText {
    public String _text_value;

    public IonTextLite(ContainerlessContext containerlessContext, boolean z) {
        super(containerlessContext, z);
    }

    public final String _get_value() {
        return this._text_value;
    }

    public final void _set_value(String str) {
        this._text_value = str;
        _isNullValue(str == null);
    }

    @Override // com.amazon.ion.impl.lite.IonValueLite
    /* JADX INFO: renamed from: clone */
    public abstract IonTextLite mo245clone();

    public void setValue(String str) {
        checkForLock();
        _set_value(str);
    }

    public String stringValue() {
        return this._text_value;
    }

    public IonTextLite(IonTextLite ionTextLite, IonContext ionContext) {
        super(ionTextLite, ionContext);
        this._text_value = ionTextLite._text_value;
    }
}
