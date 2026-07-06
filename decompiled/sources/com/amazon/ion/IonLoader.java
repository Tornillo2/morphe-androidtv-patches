package com.amazon.ion;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public interface IonLoader {
    IonCatalog getCatalog();

    IonSystem getSystem();

    IonDatagram load(IonReader ionReader) throws IonException;

    IonDatagram load(File file) throws IOException, IonException;

    @Deprecated
    IonDatagram load(InputStream inputStream) throws IOException, IonException;

    IonDatagram load(Reader reader) throws IOException, IonException;

    IonDatagram load(String str) throws IonException;

    IonDatagram load(byte[] bArr) throws IonException;
}
