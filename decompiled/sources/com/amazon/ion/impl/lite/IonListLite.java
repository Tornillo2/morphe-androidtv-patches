package com.amazon.ion.impl.lite;

import com.amazon.ion.ContainedValueException;
import com.amazon.ion.IonList;
import com.amazon.ion.IonType;
import com.amazon.ion.IonValue;
import com.amazon.ion.ValueVisitor;
import com.amazon.ion.impl._Private_IonValue;
import java.util.Collection;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public final class IonListLite extends IonSequenceLite implements IonList {
    public static final int HASH_SIGNATURE = IonType.LIST.toString().hashCode();

    public IonListLite(IonListLite ionListLite, IonContext ionContext) {
        super(ionListLite, ionContext);
    }

    @Override // com.amazon.ion.impl.lite.IonContainerLite, com.amazon.ion.impl.lite.IonValueLite, com.amazon.ion.IonValue
    public void accept(ValueVisitor valueVisitor) throws Exception {
        valueVisitor.visit(this);
    }

    @Override // com.amazon.ion.impl.lite.IonValueLite, com.amazon.ion.IonValue
    public IonType getType() {
        return IonType.LIST;
    }

    @Override // com.amazon.ion.impl.lite.IonValueLite
    public int hashCode(_Private_IonValue.SymbolTableProvider symbolTableProvider) {
        return sequenceHashCode(HASH_SIGNATURE, symbolTableProvider);
    }

    public IonListLite(ContainerlessContext containerlessContext, boolean z) {
        super(containerlessContext, z);
    }

    public IonListLite(ContainerlessContext containerlessContext, Collection<? extends IonValue> collection) throws ContainedValueException {
        super(containerlessContext, collection);
    }

    @Override // com.amazon.ion.impl.lite.IonValueLite
    public IonListLite clone(IonContext ionContext) {
        return new IonListLite((IonSequenceLite) this, ionContext);
    }

    @Override // com.amazon.ion.impl.lite.IonValueLite
    public IonValueLite clone(IonContext ionContext) {
        return new IonListLite((IonSequenceLite) this, ionContext);
    }

    @Override // com.amazon.ion.impl.lite.IonSequenceLite, com.amazon.ion.impl.lite.IonContainerLite, com.amazon.ion.impl.lite.IonValueLite
    /* JADX INFO: renamed from: clone */
    public IonListLite mo245clone() {
        return new IonListLite((IonSequenceLite) this, (IonContext) ContainerlessContext.wrap(this.ionSystem));
    }
}
