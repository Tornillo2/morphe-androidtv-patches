package com.amazon.ion.impl.lite;

import com.amazon.ion.IonBool;
import com.amazon.ion.IonType;
import com.amazon.ion.IonWriter;
import com.amazon.ion.NullValueException;
import com.amazon.ion.ValueVisitor;
import com.amazon.ion.impl._Private_IonValue;
import java.io.IOException;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public final class IonBoolLite extends IonValueLite implements IonBool {
    public static final int FALSE_HASH;
    public static final int HASH_SIGNATURE;
    public static final int TRUE_HASH;

    static {
        int iHashCode = IonType.BOOL.toString().hashCode();
        HASH_SIGNATURE = iHashCode;
        TRUE_HASH = (Boolean.TRUE.hashCode() * 16777619) ^ iHashCode;
        FALSE_HASH = iHashCode ^ (Boolean.FALSE.hashCode() * 16777619);
    }

    public IonBoolLite(ContainerlessContext containerlessContext, boolean z) {
        super(containerlessContext, z);
    }

    @Override // com.amazon.ion.impl.lite.IonValueLite, com.amazon.ion.IonValue
    public void accept(ValueVisitor valueVisitor) throws Exception {
        valueVisitor.visit(this);
    }

    @Override // com.amazon.ion.IonBool
    public boolean booleanValue() throws NullValueException {
        validateThisNotNull();
        return is_true(8);
    }

    @Override // com.amazon.ion.impl.lite.IonValueLite, com.amazon.ion.IonValue
    public IonType getType() {
        return IonType.BOOL;
    }

    @Override // com.amazon.ion.impl.lite.IonValueLite
    public int hashCode(_Private_IonValue.SymbolTableProvider symbolTableProvider) {
        int i = HASH_SIGNATURE;
        if (!is_true(4)) {
            i = booleanValue() ? TRUE_HASH : FALSE_HASH;
        }
        return hashTypeAnnotations(i, symbolTableProvider);
    }

    @Override // com.amazon.ion.IonBool
    public void setValue(boolean z) {
        setValue(Boolean.valueOf(z));
    }

    @Override // com.amazon.ion.impl.lite.IonValueLite
    public final void writeBodyTo(IonWriter ionWriter, _Private_IonValue.SymbolTableProvider symbolTableProvider) throws IOException {
        if (is_true(4)) {
            ionWriter.writeNull(IonType.BOOL);
        } else {
            ionWriter.writeBool(is_true(8));
        }
    }

    public IonBoolLite(IonBoolLite ionBoolLite, IonContext ionContext) {
        super(ionBoolLite, ionContext);
    }

    @Override // com.amazon.ion.IonBool
    public void setValue(Boolean bool) {
        checkForLock();
        if (bool == null) {
            clear_flag(8);
            set_flag(4);
        } else {
            _isBoolTrue(bool.booleanValue());
            clear_flag(4);
        }
    }

    @Override // com.amazon.ion.impl.lite.IonValueLite
    public IonBoolLite clone(IonContext ionContext) {
        return new IonBoolLite((IonValueLite) this, ionContext);
    }

    @Override // com.amazon.ion.impl.lite.IonValueLite
    public IonValueLite clone(IonContext ionContext) {
        return new IonBoolLite((IonValueLite) this, ionContext);
    }

    @Override // com.amazon.ion.impl.lite.IonValueLite
    /* JADX INFO: renamed from: clone */
    public IonBoolLite mo245clone() {
        return new IonBoolLite((IonValueLite) this, (IonContext) ContainerlessContext.wrap(this._context.getSystem()));
    }
}
