package com.amazon.ion.impl.lite;

import com.amazon.ion.IonClob;
import com.amazon.ion.IonType;
import com.amazon.ion.IonWriter;
import com.amazon.ion.ValueVisitor;
import com.amazon.ion.impl._Private_IonValue;
import com.amazon.ion.impl._Private_Utils;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public final class IonClobLite extends IonLobLite implements IonClob {
    public static final int HASH_SIGNATURE = IonType.CLOB.toString().hashCode();

    public IonClobLite(ContainerlessContext containerlessContext, boolean z) {
        super(containerlessContext, z);
    }

    @Override // com.amazon.ion.impl.lite.IonValueLite, com.amazon.ion.IonValue
    public void accept(ValueVisitor valueVisitor) throws Exception {
        valueVisitor.visit(this);
    }

    @Override // com.amazon.ion.impl.lite.IonValueLite, com.amazon.ion.IonValue
    public IonType getType() {
        return IonType.CLOB;
    }

    @Override // com.amazon.ion.impl.lite.IonValueLite
    public int hashCode(_Private_IonValue.SymbolTableProvider symbolTableProvider) {
        return lobHashCode(HASH_SIGNATURE, symbolTableProvider);
    }

    @Override // com.amazon.ion.IonClob
    public Reader newReader(Charset charset) {
        InputStream inputStreamNewInputStream = newInputStream();
        if (inputStreamNewInputStream == null) {
            return null;
        }
        return new InputStreamReader(inputStreamNewInputStream, charset);
    }

    @Override // com.amazon.ion.IonClob
    public String stringValue(Charset charset) {
        byte[] bytes = getBytes();
        if (bytes == null) {
            return null;
        }
        return _Private_Utils.decode(bytes, charset);
    }

    @Override // com.amazon.ion.impl.lite.IonValueLite
    public final void writeBodyTo(IonWriter ionWriter, _Private_IonValue.SymbolTableProvider symbolTableProvider) throws IOException {
        ionWriter.writeClob(this._lob_value);
    }

    public IonClobLite(IonClobLite ionClobLite, IonContext ionContext) {
        super(ionClobLite, ionContext);
    }

    @Override // com.amazon.ion.impl.lite.IonValueLite
    public IonClobLite clone(IonContext ionContext) {
        return new IonClobLite((IonLobLite) this, ionContext);
    }

    @Override // com.amazon.ion.impl.lite.IonValueLite
    public IonValueLite clone(IonContext ionContext) {
        return new IonClobLite((IonLobLite) this, ionContext);
    }

    @Override // com.amazon.ion.impl.lite.IonLobLite, com.amazon.ion.impl.lite.IonValueLite
    /* JADX INFO: renamed from: clone */
    public IonClobLite mo245clone() {
        return new IonClobLite((IonLobLite) this, (IonContext) ContainerlessContext.wrap(this._context.getSystem()));
    }
}
