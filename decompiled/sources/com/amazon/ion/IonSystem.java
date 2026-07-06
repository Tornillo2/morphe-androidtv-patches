package com.amazon.ion;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.util.Date;
import java.util.Iterator;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public interface IonSystem extends ValueFactory {
    IonCatalog getCatalog();

    IonLoader getLoader();

    SymbolTable getSystemSymbolTable();

    SymbolTable getSystemSymbolTable(String str) throws UnsupportedIonVersionException;

    Iterator<IonValue> iterate(IonReader ionReader);

    @Deprecated
    Iterator<IonValue> iterate(InputStream inputStream);

    Iterator<IonValue> iterate(Reader reader);

    Iterator<IonValue> iterate(String str);

    @Deprecated
    Iterator<IonValue> iterate(byte[] bArr);

    @Deprecated
    IonBinaryWriter newBinaryWriter();

    @Deprecated
    IonBinaryWriter newBinaryWriter(SymbolTable... symbolTableArr);

    IonWriter newBinaryWriter(OutputStream outputStream, SymbolTable... symbolTableArr);

    IonTimestamp newCurrentUtcTimestamp();

    IonDatagram newDatagram();

    IonDatagram newDatagram(IonValue ionValue);

    IonDatagram newDatagram(SymbolTable... symbolTableArr);

    IonLoader newLoader();

    IonLoader newLoader(IonCatalog ionCatalog);

    SymbolTable newLocalSymbolTable(SymbolTable... symbolTableArr);

    IonReader newReader(IonValue ionValue);

    IonReader newReader(InputStream inputStream);

    IonReader newReader(Reader reader);

    IonReader newReader(byte[] bArr);

    IonReader newReader(byte[] bArr, int i, int i2);

    IonTextReader newReader(String str);

    SymbolTable newSharedSymbolTable(IonReader ionReader);

    SymbolTable newSharedSymbolTable(IonReader ionReader, boolean z);

    SymbolTable newSharedSymbolTable(String str, int i, Iterator<String> it, SymbolTable... symbolTableArr);

    IonWriter newTextWriter(OutputStream outputStream);

    IonWriter newTextWriter(OutputStream outputStream, SymbolTable... symbolTableArr) throws IOException;

    IonWriter newTextWriter(Appendable appendable);

    IonWriter newTextWriter(Appendable appendable, SymbolTable... symbolTableArr) throws IOException;

    IonTimestamp newUtcTimestamp(Date date);

    IonTimestamp newUtcTimestampFromMillis(long j);

    IonValue newValue(IonReader ionReader);

    IonWriter newWriter(IonContainer ionContainer);

    IonValue singleValue(String str);

    IonValue singleValue(byte[] bArr);

    IonValue singleValue(byte[] bArr, int i, int i2);
}
