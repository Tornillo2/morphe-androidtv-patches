package com.amazon.ion.impl;

import com.amazon.ion.IonType;
import com.amazon.ion.SymbolToken;
import com.amazon.ion.util._Private_FastAppendable;
import java.io.IOException;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public abstract class _Private_MarkupCallback {
    public final _Private_FastAppendable myAppendable;

    public _Private_MarkupCallback(_Private_FastAppendable _private_fastappendable) {
        this.myAppendable = _private_fastappendable;
    }

    public final _Private_FastAppendable getAppendable() {
        return this.myAppendable;
    }

    public void afterAnnotations(IonType ionType) throws IOException {
    }

    public void afterSeparator(IonType ionType) throws IOException {
    }

    public void afterStepIn(IonType ionType) throws IOException {
    }

    public void afterValue(IonType ionType) throws IOException {
    }

    public void beforeAnnotations(IonType ionType) throws IOException {
    }

    public void beforeSeparator(IonType ionType) throws IOException {
    }

    public void beforeStepOut(IonType ionType) throws IOException {
    }

    public void beforeValue(IonType ionType) throws IOException {
    }

    public void afterEachAnnotation(IonType ionType, SymbolToken symbolToken) throws IOException {
    }

    public void afterFieldName(IonType ionType, SymbolToken symbolToken) throws IOException {
    }

    public void beforeEachAnnotation(IonType ionType, SymbolToken symbolToken) throws IOException {
    }

    public void beforeFieldName(IonType ionType, SymbolToken symbolToken) throws IOException {
    }
}
