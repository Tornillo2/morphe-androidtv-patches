package com.amazon.ion.impl;

import com.amazon.ion.IonType;
import com.amazon.ion.SymbolTable;
import com.amazon.ion.SymbolToken;
import com.amazon.ion.Timestamp;
import com.amazon.ion.util._Private_FastAppendable;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public class IonWriterSystemTextMarkup extends IonWriterSystemText {
    public final _Private_MarkupCallback myCallback;
    public IonType myTypeBeingWritten;

    public IonWriterSystemTextMarkup(SymbolTable symbolTable, _Private_IonTextWriterBuilder _private_iontextwriterbuilder, _Private_FastAppendable _private_fastappendable, _Private_CallbackBuilder _private_callbackbuilder) {
        this(symbolTable, _private_iontextwriterbuilder, _private_callbackbuilder.build(_private_fastappendable));
    }

    @Override // com.amazon.ion.impl.IonWriterSystemText
    public void closeValue() throws IOException {
        this.myCallback.getClass();
        super.closeValue();
    }

    @Override // com.amazon.ion.impl.IonWriterSystemText, com.amazon.ion.impl.IonWriterSystem
    public void startValue() throws IOException {
        super.startValue();
        this.myCallback.getClass();
    }

    @Override // com.amazon.ion.impl.IonWriterSystemText, com.amazon.ion.IonWriter
    public void stepIn(IonType ionType) throws IOException {
        this.myTypeBeingWritten = ionType;
        super.stepIn(ionType);
        this.myCallback.getClass();
        this.myTypeBeingWritten = null;
    }

    @Override // com.amazon.ion.impl.IonWriterSystemText, com.amazon.ion.IonWriter
    public void stepOut() throws IOException {
        this.myTypeBeingWritten = getContainer();
        this.myCallback.getClass();
        super.stepOut();
        this.myTypeBeingWritten = null;
    }

    @Override // com.amazon.ion.impl.IonWriterSystemText
    public void writeAnnotationToken(SymbolToken symbolToken) throws IOException {
        this.myCallback.getClass();
        super.writeAnnotationToken(symbolToken);
        this.myCallback.getClass();
    }

    @Override // com.amazon.ion.impl.IonWriterSystemText
    public void writeAnnotations(SymbolToken[] symbolTokenArr) throws IOException {
        this.myCallback.getClass();
        super.writeAnnotations(symbolTokenArr);
        this.myCallback.getClass();
    }

    @Override // com.amazon.ion.impl.IonWriterSystemText, com.amazon.ion.IonWriter
    public void writeBlob(byte[] bArr, int i, int i2) throws IOException {
        this.myTypeBeingWritten = IonType.BLOB;
        super.writeBlob(bArr, i, i2);
        this.myTypeBeingWritten = null;
    }

    @Override // com.amazon.ion.impl.IonWriterSystemText, com.amazon.ion.IonWriter
    public void writeBool(boolean z) throws IOException {
        this.myTypeBeingWritten = IonType.BOOL;
        super.writeBool(z);
        this.myTypeBeingWritten = null;
    }

    @Override // com.amazon.ion.impl.IonWriterSystemText, com.amazon.ion.IonWriter
    public void writeClob(byte[] bArr, int i, int i2) throws IOException {
        this.myTypeBeingWritten = IonType.CLOB;
        super.writeClob(bArr, i, i2);
        this.myTypeBeingWritten = null;
    }

    @Override // com.amazon.ion.impl.IonWriterSystemText, com.amazon.ion.impl._Private_IonWriterBase, com.amazon.ion.IonWriter
    public void writeDecimal(BigDecimal bigDecimal) throws IOException {
        this.myTypeBeingWritten = IonType.DECIMAL;
        super.writeDecimal(bigDecimal);
        this.myTypeBeingWritten = null;
    }

    @Override // com.amazon.ion.impl.IonWriterSystemText
    public void writeFieldNameToken(SymbolToken symbolToken) throws IOException {
        this.myCallback.getClass();
        super.writeFieldNameToken(symbolToken);
        this.myCallback.getClass();
    }

    @Override // com.amazon.ion.impl.IonWriterSystemText, com.amazon.ion.IonWriter
    public void writeFloat(double d) throws IOException {
        this.myTypeBeingWritten = IonType.FLOAT;
        super.writeFloat(d);
        this.myTypeBeingWritten = null;
    }

    @Override // com.amazon.ion.impl.IonWriterSystemText, com.amazon.ion.IonWriter
    public void writeInt(long j) throws IOException {
        this.myTypeBeingWritten = IonType.INT;
        super.writeInt(j);
        this.myTypeBeingWritten = null;
    }

    @Override // com.amazon.ion.impl.IonWriterSystemText, com.amazon.ion.impl._Private_IonWriterBase, com.amazon.ion.IonWriter
    public void writeNull() throws IOException {
        this.myTypeBeingWritten = IonType.NULL;
        super.writeNull();
        this.myTypeBeingWritten = null;
    }

    @Override // com.amazon.ion.impl.IonWriterSystemText
    public boolean writeSeparator(boolean z) throws IOException {
        getContainer();
        if (this._pending_separator) {
            this.myCallback.getClass();
        }
        boolean zWriteSeparator = super.writeSeparator(z);
        if (this._pending_separator) {
            this.myCallback.getClass();
        }
        return zWriteSeparator;
    }

    @Override // com.amazon.ion.impl.IonWriterSystemText, com.amazon.ion.IonWriter
    public void writeString(String str) throws IOException {
        this.myTypeBeingWritten = IonType.STRING;
        super.writeString(str);
        this.myTypeBeingWritten = null;
    }

    @Override // com.amazon.ion.impl.IonWriterSystemText, com.amazon.ion.impl.IonWriterSystem
    public void writeSymbolAsIs(String str) throws IOException {
        this.myTypeBeingWritten = IonType.SYMBOL;
        super.writeSymbolAsIs(str);
        this.myTypeBeingWritten = null;
    }

    @Override // com.amazon.ion.impl.IonWriterSystemText, com.amazon.ion.IonWriter
    public void writeTimestamp(Timestamp timestamp) throws IOException {
        this.myTypeBeingWritten = IonType.TIMESTAMP;
        super.writeTimestamp(timestamp);
        this.myTypeBeingWritten = null;
    }

    public IonWriterSystemTextMarkup(SymbolTable symbolTable, _Private_IonTextWriterBuilder _private_iontextwriterbuilder, _Private_MarkupCallback _private_markupcallback) {
        super(symbolTable, _private_iontextwriterbuilder, _private_markupcallback.myAppendable);
        this.myCallback = _private_markupcallback;
    }

    @Override // com.amazon.ion.impl.IonWriterSystemText, com.amazon.ion.IonWriter
    public void writeInt(BigInteger bigInteger) throws IOException {
        this.myTypeBeingWritten = IonType.INT;
        super.writeInt(bigInteger);
        this.myTypeBeingWritten = null;
    }

    @Override // com.amazon.ion.impl.IonWriterSystemText, com.amazon.ion.IonWriter
    public void writeNull(IonType ionType) throws IOException {
        this.myTypeBeingWritten = ionType;
        super.writeNull(ionType);
        this.myTypeBeingWritten = null;
    }

    @Override // com.amazon.ion.impl.IonWriterSystemText, com.amazon.ion.impl.IonWriterSystem
    public void writeSymbolAsIs(int i) throws IOException {
        this.myTypeBeingWritten = IonType.SYMBOL;
        super.writeSymbolAsIs(i);
        this.myTypeBeingWritten = null;
    }

    public IonWriterSystemTextMarkup(SymbolTable symbolTable, _Private_IonTextWriterBuilder _private_iontextwriterbuilder, _Private_FastAppendable _private_fastappendable) {
        this(symbolTable, _private_iontextwriterbuilder, _private_fastappendable, _private_iontextwriterbuilder._callback_builder);
    }
}
