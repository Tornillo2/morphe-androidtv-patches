package com.amazon.ion.impl;

import com.amazon.ion.ContainedValueException;
import com.amazon.ion.IonBlob;
import com.amazon.ion.IonBool;
import com.amazon.ion.IonClob;
import com.amazon.ion.IonDecimal;
import com.amazon.ion.IonException;
import com.amazon.ion.IonFloat;
import com.amazon.ion.IonInt;
import com.amazon.ion.IonList;
import com.amazon.ion.IonNull;
import com.amazon.ion.IonSequence;
import com.amazon.ion.IonSexp;
import com.amazon.ion.IonString;
import com.amazon.ion.IonStruct;
import com.amazon.ion.IonSymbol;
import com.amazon.ion.IonTimestamp;
import com.amazon.ion.IonType;
import com.amazon.ion.IonValue;
import com.amazon.ion.SymbolToken;
import com.amazon.ion.Timestamp;
import com.amazon.ion.ValueFactory;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Collection;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public abstract class _Private_CurriedValueFactory implements ValueFactory {
    public final ValueFactory myFactory;

    public _Private_CurriedValueFactory(ValueFactory valueFactory) {
        this.myFactory = valueFactory;
    }

    @Override // com.amazon.ion.ValueFactory
    public <T extends IonValue> T clone(T t) throws IonException {
        T t2 = (T) this.myFactory.clone(t);
        handle(t2);
        return t2;
    }

    public abstract void handle(IonValue ionValue);

    @Override // com.amazon.ion.ValueFactory
    public IonBlob newBlob(byte[] bArr) {
        IonBlob ionBlobNewBlob = this.myFactory.newBlob(bArr);
        handle(ionBlobNewBlob);
        return ionBlobNewBlob;
    }

    @Override // com.amazon.ion.ValueFactory
    public IonBool newBool(boolean z) {
        IonBool ionBoolNewBool = this.myFactory.newBool(z);
        handle(ionBoolNewBool);
        return ionBoolNewBool;
    }

    @Override // com.amazon.ion.ValueFactory
    public IonClob newClob(byte[] bArr) {
        IonClob ionClobNewClob = this.myFactory.newClob(bArr);
        handle(ionClobNewClob);
        return ionClobNewClob;
    }

    @Override // com.amazon.ion.ValueFactory
    public IonDecimal newDecimal(long j) {
        IonDecimal ionDecimalNewDecimal = this.myFactory.newDecimal(j);
        handle(ionDecimalNewDecimal);
        return ionDecimalNewDecimal;
    }

    @Override // com.amazon.ion.ValueFactory
    public IonList newEmptyList() {
        IonList ionListNewEmptyList = this.myFactory.newEmptyList();
        handle(ionListNewEmptyList);
        return ionListNewEmptyList;
    }

    @Override // com.amazon.ion.ValueFactory
    public IonSexp newEmptySexp() {
        IonSexp ionSexpNewEmptySexp = this.myFactory.newEmptySexp();
        handle(ionSexpNewEmptySexp);
        return ionSexpNewEmptySexp;
    }

    @Override // com.amazon.ion.ValueFactory
    public IonStruct newEmptyStruct() {
        IonStruct ionStructNewEmptyStruct = this.myFactory.newEmptyStruct();
        handle(ionStructNewEmptyStruct);
        return ionStructNewEmptyStruct;
    }

    @Override // com.amazon.ion.ValueFactory
    public IonFloat newFloat(long j) {
        IonFloat ionFloatNewFloat = this.myFactory.newFloat(j);
        handle(ionFloatNewFloat);
        return ionFloatNewFloat;
    }

    @Override // com.amazon.ion.ValueFactory
    public IonInt newInt(int i) {
        IonInt ionIntNewInt = this.myFactory.newInt(i);
        handle(ionIntNewInt);
        return ionIntNewInt;
    }

    @Override // com.amazon.ion.ValueFactory
    @Deprecated
    public IonList newList(Collection<? extends IonValue> collection) throws ContainedValueException, NullPointerException {
        IonList ionListNewList = this.myFactory.newList(collection);
        handle(ionListNewList);
        return ionListNewList;
    }

    @Override // com.amazon.ion.ValueFactory
    public IonNull newNull() {
        IonNull ionNullNewNull = this.myFactory.newNull();
        handle(ionNullNewNull);
        return ionNullNewNull;
    }

    @Override // com.amazon.ion.ValueFactory
    public IonBlob newNullBlob() {
        IonBlob ionBlobNewNullBlob = this.myFactory.newNullBlob();
        handle(ionBlobNewNullBlob);
        return ionBlobNewNullBlob;
    }

    @Override // com.amazon.ion.ValueFactory
    public IonBool newNullBool() {
        IonBool ionBoolNewNullBool = this.myFactory.newNullBool();
        handle(ionBoolNewNullBool);
        return ionBoolNewNullBool;
    }

    @Override // com.amazon.ion.ValueFactory
    public IonClob newNullClob() {
        IonClob ionClobNewNullClob = this.myFactory.newNullClob();
        handle(ionClobNewNullClob);
        return ionClobNewNullClob;
    }

    @Override // com.amazon.ion.ValueFactory
    public IonDecimal newNullDecimal() {
        IonDecimal ionDecimalNewNullDecimal = this.myFactory.newNullDecimal();
        handle(ionDecimalNewNullDecimal);
        return ionDecimalNewNullDecimal;
    }

    @Override // com.amazon.ion.ValueFactory
    public IonFloat newNullFloat() {
        IonFloat ionFloatNewNullFloat = this.myFactory.newNullFloat();
        handle(ionFloatNewNullFloat);
        return ionFloatNewNullFloat;
    }

    @Override // com.amazon.ion.ValueFactory
    public IonInt newNullInt() {
        IonInt ionIntNewNullInt = this.myFactory.newNullInt();
        handle(ionIntNewNullInt);
        return ionIntNewNullInt;
    }

    @Override // com.amazon.ion.ValueFactory
    public IonList newNullList() {
        IonList ionListNewNullList = this.myFactory.newNullList();
        handle(ionListNewNullList);
        return ionListNewNullList;
    }

    @Override // com.amazon.ion.ValueFactory
    public IonSexp newNullSexp() {
        IonSexp ionSexpNewNullSexp = this.myFactory.newNullSexp();
        handle(ionSexpNewNullSexp);
        return ionSexpNewNullSexp;
    }

    @Override // com.amazon.ion.ValueFactory
    public IonString newNullString() {
        IonString ionStringNewNullString = this.myFactory.newNullString();
        handle(ionStringNewNullString);
        return ionStringNewNullString;
    }

    @Override // com.amazon.ion.ValueFactory
    public IonStruct newNullStruct() {
        IonStruct ionStructNewNullStruct = this.myFactory.newNullStruct();
        handle(ionStructNewNullStruct);
        return ionStructNewNullStruct;
    }

    @Override // com.amazon.ion.ValueFactory
    public IonSymbol newNullSymbol() {
        IonSymbol ionSymbolNewNullSymbol = this.myFactory.newNullSymbol();
        handle(ionSymbolNewNullSymbol);
        return ionSymbolNewNullSymbol;
    }

    @Override // com.amazon.ion.ValueFactory
    public IonTimestamp newNullTimestamp() {
        IonTimestamp ionTimestampNewNullTimestamp = this.myFactory.newNullTimestamp();
        handle(ionTimestampNewNullTimestamp);
        return ionTimestampNewNullTimestamp;
    }

    @Override // com.amazon.ion.ValueFactory
    @Deprecated
    public IonSexp newSexp(Collection<? extends IonValue> collection) throws ContainedValueException, NullPointerException {
        IonSexp ionSexpNewSexp = this.myFactory.newSexp(collection);
        handle(ionSexpNewSexp);
        return ionSexpNewSexp;
    }

    @Override // com.amazon.ion.ValueFactory
    public IonString newString(String str) {
        IonString ionStringNewString = this.myFactory.newString(str);
        handle(ionStringNewString);
        return ionStringNewString;
    }

    @Override // com.amazon.ion.ValueFactory
    public IonSymbol newSymbol(String str) {
        IonSymbol ionSymbolNewSymbol = this.myFactory.newSymbol(str);
        handle(ionSymbolNewSymbol);
        return ionSymbolNewSymbol;
    }

    @Override // com.amazon.ion.ValueFactory
    public IonTimestamp newTimestamp(Timestamp timestamp) {
        IonTimestamp ionTimestampNewTimestamp = this.myFactory.newTimestamp(timestamp);
        handle(ionTimestampNewTimestamp);
        return ionTimestampNewTimestamp;
    }

    @Override // com.amazon.ion.ValueFactory
    public IonBlob newBlob(byte[] bArr, int i, int i2) {
        IonBlob ionBlobNewBlob = this.myFactory.newBlob(bArr, i, i2);
        handle(ionBlobNewBlob);
        return ionBlobNewBlob;
    }

    @Override // com.amazon.ion.ValueFactory
    public IonBool newBool(Boolean bool) {
        IonBool ionBoolNewBool = this.myFactory.newBool(bool);
        handle(ionBoolNewBool);
        return ionBoolNewBool;
    }

    @Override // com.amazon.ion.ValueFactory
    public IonClob newClob(byte[] bArr, int i, int i2) {
        IonClob ionClobNewClob = this.myFactory.newClob(bArr, i, i2);
        handle(ionClobNewClob);
        return ionClobNewClob;
    }

    @Override // com.amazon.ion.ValueFactory
    public IonDecimal newDecimal(double d) {
        IonDecimal ionDecimalNewDecimal = this.myFactory.newDecimal(d);
        handle(ionDecimalNewDecimal);
        return ionDecimalNewDecimal;
    }

    @Override // com.amazon.ion.ValueFactory
    public IonFloat newFloat(double d) {
        IonFloat ionFloatNewFloat = this.myFactory.newFloat(d);
        handle(ionFloatNewFloat);
        return ionFloatNewFloat;
    }

    @Override // com.amazon.ion.ValueFactory
    public IonInt newInt(long j) {
        IonInt ionIntNewInt = this.myFactory.newInt(j);
        handle(ionIntNewInt);
        return ionIntNewInt;
    }

    @Override // com.amazon.ion.ValueFactory
    public IonList newList(IonSequence ionSequence) throws ContainedValueException, NullPointerException {
        IonList ionListNewList = this.myFactory.newList(ionSequence);
        handle(ionListNewList);
        return ionListNewList;
    }

    @Override // com.amazon.ion.ValueFactory
    public IonValue newNull(IonType ionType) {
        IonValue ionValueNewNull = this.myFactory.newNull(ionType);
        handle(ionValueNewNull);
        return ionValueNewNull;
    }

    @Override // com.amazon.ion.ValueFactory
    public IonSexp newSexp(IonSequence ionSequence) throws ContainedValueException, NullPointerException {
        IonSexp ionSexpNewSexp = this.myFactory.newSexp(ionSequence);
        handle(ionSexpNewSexp);
        return ionSexpNewSexp;
    }

    @Override // com.amazon.ion.ValueFactory
    public IonSymbol newSymbol(SymbolToken symbolToken) {
        IonSymbol ionSymbolNewSymbol = this.myFactory.newSymbol(symbolToken);
        handle(ionSymbolNewSymbol);
        return ionSymbolNewSymbol;
    }

    @Override // com.amazon.ion.ValueFactory
    public IonDecimal newDecimal(BigInteger bigInteger) {
        IonDecimal ionDecimalNewDecimal = this.myFactory.newDecimal(bigInteger);
        handle(ionDecimalNewDecimal);
        return ionDecimalNewDecimal;
    }

    @Override // com.amazon.ion.ValueFactory
    public IonInt newInt(Number number) {
        IonInt ionIntNewInt = this.myFactory.newInt(number);
        handle(ionIntNewInt);
        return ionIntNewInt;
    }

    @Override // com.amazon.ion.ValueFactory
    public IonList newList(IonValue... ionValueArr) throws ContainedValueException, NullPointerException {
        IonList ionListNewList = this.myFactory.newList(ionValueArr);
        handle(ionListNewList);
        return ionListNewList;
    }

    @Override // com.amazon.ion.ValueFactory
    public IonSexp newSexp(IonValue... ionValueArr) throws ContainedValueException, NullPointerException {
        IonSexp ionSexpNewSexp = this.myFactory.newSexp(ionValueArr);
        handle(ionSexpNewSexp);
        return ionSexpNewSexp;
    }

    @Override // com.amazon.ion.ValueFactory
    public IonDecimal newDecimal(BigDecimal bigDecimal) {
        IonDecimal ionDecimalNewDecimal = this.myFactory.newDecimal(bigDecimal);
        handle(ionDecimalNewDecimal);
        return ionDecimalNewDecimal;
    }

    @Override // com.amazon.ion.ValueFactory
    public IonList newList(int[] iArr) {
        IonList ionListNewList = this.myFactory.newList(iArr);
        handle(ionListNewList);
        return ionListNewList;
    }

    @Override // com.amazon.ion.ValueFactory
    public IonSexp newSexp(int[] iArr) {
        IonSexp ionSexpNewSexp = this.myFactory.newSexp(iArr);
        handle(ionSexpNewSexp);
        return ionSexpNewSexp;
    }

    @Override // com.amazon.ion.ValueFactory
    public IonList newList(long[] jArr) {
        IonList ionListNewList = this.myFactory.newList(jArr);
        handle(ionListNewList);
        return ionListNewList;
    }

    @Override // com.amazon.ion.ValueFactory
    public IonSexp newSexp(long[] jArr) {
        IonSexp ionSexpNewSexp = this.myFactory.newSexp(jArr);
        handle(ionSexpNewSexp);
        return ionSexpNewSexp;
    }
}
