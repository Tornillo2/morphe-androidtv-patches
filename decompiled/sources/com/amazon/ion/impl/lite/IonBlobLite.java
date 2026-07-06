package com.amazon.ion.impl.lite;

import com.amazon.ion.IonBlob;
import com.amazon.ion.IonType;
import com.amazon.ion.IonWriter;
import com.amazon.ion.ValueVisitor;
import com.amazon.ion.impl._Private_IonValue;
import com.amazon.ion.impl._Private_Utils;
import java.io.IOException;
import java.io.InputStream;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public final class IonBlobLite extends IonLobLite implements IonBlob {
    public static final int HASH_SIGNATURE = IonType.BLOB.toString().hashCode();

    public IonBlobLite(ContainerlessContext containerlessContext, boolean z) {
        super(containerlessContext, z);
    }

    @Override // com.amazon.ion.impl.lite.IonValueLite, com.amazon.ion.IonValue
    public void accept(ValueVisitor valueVisitor) throws Exception {
        valueVisitor.visit(this);
    }

    @Override // com.amazon.ion.impl.lite.IonValueLite, com.amazon.ion.IonValue
    public IonType getType() {
        return IonType.BLOB;
    }

    @Override // com.amazon.ion.impl.lite.IonValueLite
    public int hashCode(_Private_IonValue.SymbolTableProvider symbolTableProvider) {
        return lobHashCode(HASH_SIGNATURE, symbolTableProvider);
    }

    @Override // com.amazon.ion.IonBlob
    public void printBase64(Appendable appendable) throws IOException {
        validateThisNotNull();
        InputStream inputStreamNewInputStream = newInputStream();
        try {
            _Private_Utils.writeAsBase64(inputStreamNewInputStream, appendable);
        } finally {
            inputStreamNewInputStream.close();
        }
    }

    @Override // com.amazon.ion.impl.lite.IonValueLite
    public final void writeBodyTo(IonWriter ionWriter, _Private_IonValue.SymbolTableProvider symbolTableProvider) throws IOException {
        ionWriter.writeBlob(this._lob_value);
    }

    public IonBlobLite(IonBlobLite ionBlobLite, IonContext ionContext) {
        super(ionBlobLite, ionContext);
    }

    @Override // com.amazon.ion.impl.lite.IonValueLite
    public IonBlobLite clone(IonContext ionContext) {
        return new IonBlobLite((IonLobLite) this, ionContext);
    }

    @Override // com.amazon.ion.impl.lite.IonValueLite
    public IonValueLite clone(IonContext ionContext) {
        return new IonBlobLite((IonLobLite) this, ionContext);
    }

    @Override // com.amazon.ion.impl.lite.IonLobLite, com.amazon.ion.impl.lite.IonValueLite
    /* JADX INFO: renamed from: clone */
    public IonBlobLite mo245clone() {
        return new IonBlobLite((IonLobLite) this, (IonContext) ContainerlessContext.wrap(this._context.getSystem()));
    }
}
