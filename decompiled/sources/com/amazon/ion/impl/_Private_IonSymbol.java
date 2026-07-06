package com.amazon.ion.impl;

import com.amazon.ion.IonSymbol;
import com.amazon.ion.SymbolToken;
import com.amazon.ion.impl._Private_IonValue;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public interface _Private_IonSymbol extends IonSymbol {
    SymbolToken symbolValue(_Private_IonValue.SymbolTableProvider symbolTableProvider);
}
