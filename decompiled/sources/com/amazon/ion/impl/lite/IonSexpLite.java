package com.amazon.ion.impl.lite;

import com.amazon.ion.ContainedValueException;
import com.amazon.ion.IonSexp;
import com.amazon.ion.IonType;
import com.amazon.ion.IonValue;
import com.amazon.ion.ValueVisitor;
import com.amazon.ion.impl._Private_IonValue;
import java.util.Collection;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public final class IonSexpLite extends IonSequenceLite implements IonSexp {
    public static final int HASH_SIGNATURE = IonType.SEXP.toString().hashCode();

    public IonSexpLite(IonSexpLite ionSexpLite, IonContext ionContext) {
        super(ionSexpLite, ionContext);
    }

    @Override // com.amazon.ion.impl.lite.IonContainerLite, com.amazon.ion.impl.lite.IonValueLite, com.amazon.ion.IonValue
    public void accept(ValueVisitor valueVisitor) throws Exception {
        valueVisitor.visit(this);
    }

    @Override // com.amazon.ion.impl.lite.IonValueLite, com.amazon.ion.IonValue
    public IonType getType() {
        return IonType.SEXP;
    }

    @Override // com.amazon.ion.impl.lite.IonValueLite
    public int hashCode(_Private_IonValue.SymbolTableProvider symbolTableProvider) {
        return sequenceHashCode(HASH_SIGNATURE, symbolTableProvider);
    }

    public IonSexpLite(ContainerlessContext containerlessContext, boolean z) {
        super(containerlessContext, z);
    }

    public IonSexpLite(ContainerlessContext containerlessContext, Collection<? extends IonValue> collection) throws ContainedValueException {
        super(containerlessContext, collection);
    }

    @Override // com.amazon.ion.impl.lite.IonValueLite
    public IonSexpLite clone(IonContext ionContext) {
        return new IonSexpLite((IonSequenceLite) this, ionContext);
    }

    @Override // com.amazon.ion.impl.lite.IonValueLite
    public IonValueLite clone(IonContext ionContext) {
        return new IonSexpLite((IonSequenceLite) this, ionContext);
    }

    @Override // com.amazon.ion.impl.lite.IonSequenceLite, com.amazon.ion.impl.lite.IonContainerLite, com.amazon.ion.impl.lite.IonValueLite
    /* JADX INFO: renamed from: clone */
    public IonSexpLite mo245clone() {
        return new IonSexpLite((IonSequenceLite) this, (IonContext) ContainerlessContext.wrap(this.ionSystem));
    }
}
