package com.amazon.ion;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Collection;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public interface ValueFactory {
    <T extends IonValue> T clone(T t) throws IonException;

    IonBlob newBlob(byte[] bArr);

    IonBlob newBlob(byte[] bArr, int i, int i2);

    IonBool newBool(Boolean bool);

    IonBool newBool(boolean z);

    IonClob newClob(byte[] bArr);

    IonClob newClob(byte[] bArr, int i, int i2);

    IonDecimal newDecimal(double d);

    IonDecimal newDecimal(long j);

    IonDecimal newDecimal(BigDecimal bigDecimal);

    IonDecimal newDecimal(BigInteger bigInteger);

    IonList newEmptyList();

    IonSexp newEmptySexp();

    IonStruct newEmptyStruct();

    IonFloat newFloat(double d);

    IonFloat newFloat(long j);

    IonInt newInt(int i);

    IonInt newInt(long j);

    IonInt newInt(Number number);

    IonList newList(IonSequence ionSequence) throws ContainedValueException, NullPointerException;

    @Deprecated
    IonList newList(Collection<? extends IonValue> collection) throws ContainedValueException, NullPointerException;

    IonList newList(int[] iArr);

    IonList newList(long[] jArr);

    IonList newList(IonValue... ionValueArr) throws ContainedValueException, NullPointerException;

    IonNull newNull();

    IonValue newNull(IonType ionType);

    IonBlob newNullBlob();

    IonBool newNullBool();

    IonClob newNullClob();

    IonDecimal newNullDecimal();

    IonFloat newNullFloat();

    IonInt newNullInt();

    IonList newNullList();

    IonSexp newNullSexp();

    IonString newNullString();

    IonStruct newNullStruct();

    IonSymbol newNullSymbol();

    IonTimestamp newNullTimestamp();

    IonSexp newSexp(IonSequence ionSequence) throws ContainedValueException, NullPointerException;

    @Deprecated
    IonSexp newSexp(Collection<? extends IonValue> collection) throws ContainedValueException, NullPointerException;

    IonSexp newSexp(int[] iArr);

    IonSexp newSexp(long[] jArr);

    IonSexp newSexp(IonValue... ionValueArr) throws ContainedValueException, NullPointerException;

    IonString newString(String str);

    IonSymbol newSymbol(SymbolToken symbolToken);

    IonSymbol newSymbol(String str);

    IonTimestamp newTimestamp(Timestamp timestamp);
}
