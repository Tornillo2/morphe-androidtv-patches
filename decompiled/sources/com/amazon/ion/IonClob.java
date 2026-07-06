package com.amazon.ion;

import java.io.Reader;
import java.nio.charset.Charset;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public interface IonClob extends IonValue, IonLob {
    @Override // com.amazon.ion.IonValue
    IonClob clone() throws UnknownSymbolException;

    Reader newReader(Charset charset);

    String stringValue(Charset charset);
}
