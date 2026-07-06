package com.amazon.ion.impl.lite;

import com.amazon.ion.IonNull;
import com.amazon.ion.IonType;
import com.amazon.ion.IonWriter;
import com.amazon.ion.ValueVisitor;
import com.amazon.ion.impl._Private_IonValue;
import java.io.IOException;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public final class IonNullLite extends IonValueLite implements IonNull {
    public static final int HASH_SIGNATURE = IonType.NULL.toString().hashCode();

    public IonNullLite(ContainerlessContext containerlessContext) {
        super(containerlessContext, true);
    }

    @Override // com.amazon.ion.impl.lite.IonValueLite, com.amazon.ion.IonValue
    public void accept(ValueVisitor valueVisitor) throws Exception {
        valueVisitor.visit(this);
    }

    @Override // com.amazon.ion.impl.lite.IonValueLite, com.amazon.ion.IonValue
    public IonType getType() {
        return IonType.NULL;
    }

    @Override // com.amazon.ion.impl.lite.IonValueLite
    public int hashCode(_Private_IonValue.SymbolTableProvider symbolTableProvider) {
        return hashTypeAnnotations(HASH_SIGNATURE, symbolTableProvider);
    }

    @Override // com.amazon.ion.impl.lite.IonValueLite
    public final void writeBodyTo(IonWriter ionWriter, _Private_IonValue.SymbolTableProvider symbolTableProvider) throws IOException {
        ionWriter.writeNull();
    }

    public IonNullLite(IonNullLite ionNullLite, IonContext ionContext) {
        super(ionNullLite, ionContext);
    }

    @Override // com.amazon.ion.impl.lite.IonValueLite
    public IonNullLite clone(IonContext ionContext) {
        return new IonNullLite((IonValueLite) this, ionContext);
    }

    @Override // com.amazon.ion.impl.lite.IonValueLite
    public IonValueLite clone(IonContext ionContext) {
        return new IonNullLite((IonValueLite) this, ionContext);
    }

    @Override // com.amazon.ion.impl.lite.IonValueLite
    /* JADX INFO: renamed from: clone */
    public IonNullLite mo245clone() {
        return new IonNullLite((IonValueLite) this, (IonContext) ContainerlessContext.wrap(this._context.getSystem()));
    }
}
