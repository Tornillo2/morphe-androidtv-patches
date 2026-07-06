package com.amazon.ion.impl.lite;

import com.amazon.ion.IonString;
import com.amazon.ion.IonType;
import com.amazon.ion.IonWriter;
import com.amazon.ion.ValueVisitor;
import com.amazon.ion.impl._Private_IonValue;
import java.io.IOException;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public final class IonStringLite extends IonTextLite implements IonString {
    public static final int HASH_SIGNATURE = IonType.STRING.toString().hashCode();

    public IonStringLite(ContainerlessContext containerlessContext, boolean z) {
        super(containerlessContext, z);
    }

    @Override // com.amazon.ion.impl.lite.IonValueLite, com.amazon.ion.IonValue
    public void accept(ValueVisitor valueVisitor) throws Exception {
        valueVisitor.visit(this);
    }

    @Override // com.amazon.ion.impl.lite.IonValueLite, com.amazon.ion.IonValue
    public IonType getType() {
        return IonType.STRING;
    }

    @Override // com.amazon.ion.impl.lite.IonValueLite
    public int hashCode(_Private_IonValue.SymbolTableProvider symbolTableProvider) {
        int iHashCode = HASH_SIGNATURE;
        if (!is_true(4)) {
            iHashCode ^= this._text_value.hashCode();
        }
        return hashTypeAnnotations(iHashCode, symbolTableProvider);
    }

    @Override // com.amazon.ion.impl.lite.IonValueLite
    public final void writeBodyTo(IonWriter ionWriter, _Private_IonValue.SymbolTableProvider symbolTableProvider) throws IOException {
        ionWriter.writeString(this._text_value);
    }

    public IonStringLite(IonStringLite ionStringLite, IonContext ionContext) {
        super(ionStringLite, ionContext);
    }

    @Override // com.amazon.ion.impl.lite.IonValueLite
    public IonStringLite clone(IonContext ionContext) {
        return new IonStringLite((IonTextLite) this, ionContext);
    }

    @Override // com.amazon.ion.impl.lite.IonValueLite
    public IonValueLite clone(IonContext ionContext) {
        return new IonStringLite((IonTextLite) this, ionContext);
    }

    @Override // com.amazon.ion.impl.lite.IonTextLite, com.amazon.ion.impl.lite.IonValueLite
    /* JADX INFO: renamed from: clone */
    public IonStringLite mo245clone() {
        return new IonStringLite((IonTextLite) this, (IonContext) ContainerlessContext.wrap(this._context.getSystem()));
    }
}
