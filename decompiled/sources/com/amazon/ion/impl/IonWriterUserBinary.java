package com.amazon.ion.impl;

import com.amazon.ion.IonReader;
import com.amazon.ion.IonType;
import com.amazon.ion.IonWriter;
import com.amazon.ion.util.IonStreamUtils;
import java.io.IOException;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public class IonWriterUserBinary extends IonWriterUser implements _Private_ListWriter {
    public final _Private_ByteTransferSink myCopySink;
    public final _Private_SymtabExtendsCache mySymtabExtendsCache;

    public IonWriterUserBinary(_Private_IonBinaryWriterBuilder _private_ionbinarywriterbuilder, IonWriterSystemBinary ionWriterSystemBinary) {
        super(_private_ionbinarywriterbuilder.getCatalog(), _private_ionbinarywriterbuilder.getSymtabValueFactory(), ionWriterSystemBinary, _private_ionbinarywriterbuilder.buildContextSymbolTable());
        if (_private_ionbinarywriterbuilder.isStreamCopyOptimized()) {
            this.mySymtabExtendsCache = new _Private_SymtabExtendsCache();
            this.myCopySink = new _Private_ByteTransferSink() { // from class: com.amazon.ion.impl.IonWriterUserBinary.1
                @Override // com.amazon.ion.impl._Private_ByteTransferSink
                public void writeBytes(byte[] bArr, int i, int i2) throws IOException {
                    ((IonWriterSystemBinary) IonWriterUserBinary.this._current_writer).writeRaw(bArr, i, i2);
                }
            };
        } else {
            this.mySymtabExtendsCache = null;
            this.myCopySink = null;
        }
    }

    @Override // com.amazon.ion.impl._Private_IonWriterBase, com.amazon.ion.impl._Private_IonWriter
    public boolean isStreamCopyOptimized() {
        return this.mySymtabExtendsCache != null;
    }

    @Override // com.amazon.ion.impl._Private_ListWriter
    public void writeBoolList(boolean[] zArr) throws IOException {
        IonStreamUtils.writeBoolList(this._current_writer, zArr);
    }

    @Override // com.amazon.ion.impl._Private_ListWriter
    public void writeFloatList(float[] fArr) throws IOException {
        IonStreamUtils.writeFloatList((IonWriter) this._current_writer, fArr);
    }

    @Override // com.amazon.ion.impl._Private_ListWriter
    public void writeIntList(byte[] bArr) throws IOException {
        IonStreamUtils.writeIntList((IonWriter) this._current_writer, bArr);
    }

    @Override // com.amazon.ion.impl._Private_ListWriter
    public void writeStringList(String[] strArr) throws IOException {
        IonStreamUtils.writeStringList(this._current_writer, strArr);
    }

    @Override // com.amazon.ion.impl._Private_IonWriterBase, com.amazon.ion.IonWriter
    public void writeValue(IonReader ionReader) throws IOException {
        _Private_ByteTransferReader _private_bytetransferreader;
        IonType type = ionReader.getType();
        if (isStreamCopyOptimized() && (this._current_writer instanceof IonWriterSystemBinary) && (_private_bytetransferreader = (_Private_ByteTransferReader) ionReader.asFacet(_Private_ByteTransferReader.class)) != null && (_Private_Utils.isNonSymbolScalar(type) || this.mySymtabExtendsCache.symtabsCompat(this._system_writer._symbol_table, ionReader.getSymbolTable()))) {
            _private_bytetransferreader.transferCurrentValue(this.myCopySink);
        } else {
            writeValueRecursively(ionReader);
        }
    }

    @Override // com.amazon.ion.impl._Private_ListWriter
    public void writeFloatList(double[] dArr) throws IOException {
        IonStreamUtils.writeFloatList(this._current_writer, dArr);
    }

    @Override // com.amazon.ion.impl._Private_ListWriter
    public void writeIntList(short[] sArr) throws IOException {
        IonStreamUtils.writeIntList((IonWriter) this._current_writer, sArr);
    }

    @Override // com.amazon.ion.impl._Private_ListWriter
    public void writeIntList(int[] iArr) throws IOException {
        IonStreamUtils.writeIntList((IonWriter) this._current_writer, iArr);
    }

    @Override // com.amazon.ion.impl._Private_ListWriter
    public void writeIntList(long[] jArr) throws IOException {
        IonStreamUtils.writeIntList(this._current_writer, jArr);
    }
}
