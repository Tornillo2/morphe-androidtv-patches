package com.amazon.ion.util;

import com.amazon.ion.IonBlob;
import com.amazon.ion.IonBool;
import com.amazon.ion.IonClob;
import com.amazon.ion.IonDatagram;
import com.amazon.ion.IonDecimal;
import com.amazon.ion.IonFloat;
import com.amazon.ion.IonInt;
import com.amazon.ion.IonList;
import com.amazon.ion.IonNull;
import com.amazon.ion.IonSexp;
import com.amazon.ion.IonString;
import com.amazon.ion.IonStruct;
import com.amazon.ion.IonSymbol;
import com.amazon.ion.IonTimestamp;
import com.amazon.ion.IonValue;
import com.amazon.ion.ValueVisitor;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractValueVisitor implements ValueVisitor {
    public void defaultVisit(IonValue ionValue) throws Exception {
        throw new UnsupportedOperationException();
    }

    @Override // com.amazon.ion.ValueVisitor
    public void visit(IonBlob ionBlob) throws Exception {
        defaultVisit(ionBlob);
        throw null;
    }

    @Override // com.amazon.ion.ValueVisitor
    public void visit(IonBool ionBool) throws Exception {
        defaultVisit(ionBool);
        throw null;
    }

    @Override // com.amazon.ion.ValueVisitor
    public void visit(IonClob ionClob) throws Exception {
        defaultVisit(ionClob);
        throw null;
    }

    @Override // com.amazon.ion.ValueVisitor
    public void visit(IonDatagram ionDatagram) throws Exception {
        defaultVisit(ionDatagram);
        throw null;
    }

    @Override // com.amazon.ion.ValueVisitor
    public void visit(IonDecimal ionDecimal) throws Exception {
        defaultVisit(ionDecimal);
        throw null;
    }

    @Override // com.amazon.ion.ValueVisitor
    public void visit(IonFloat ionFloat) throws Exception {
        defaultVisit(ionFloat);
        throw null;
    }

    @Override // com.amazon.ion.ValueVisitor
    public void visit(IonInt ionInt) throws Exception {
        defaultVisit(ionInt);
        throw null;
    }

    @Override // com.amazon.ion.ValueVisitor
    public void visit(IonList ionList) throws Exception {
        defaultVisit(ionList);
        throw null;
    }

    @Override // com.amazon.ion.ValueVisitor
    public void visit(IonNull ionNull) throws Exception {
        defaultVisit(ionNull);
        throw null;
    }

    @Override // com.amazon.ion.ValueVisitor
    public void visit(IonSexp ionSexp) throws Exception {
        defaultVisit(ionSexp);
        throw null;
    }

    @Override // com.amazon.ion.ValueVisitor
    public void visit(IonString ionString) throws Exception {
        defaultVisit(ionString);
        throw null;
    }

    @Override // com.amazon.ion.ValueVisitor
    public void visit(IonStruct ionStruct) throws Exception {
        defaultVisit(ionStruct);
        throw null;
    }

    @Override // com.amazon.ion.ValueVisitor
    public void visit(IonSymbol ionSymbol) throws Exception {
        defaultVisit(ionSymbol);
        throw null;
    }

    @Override // com.amazon.ion.ValueVisitor
    public void visit(IonTimestamp ionTimestamp) throws Exception {
        defaultVisit(ionTimestamp);
        throw null;
    }
}
