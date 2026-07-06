package kotlin.jvm.internal;

import android.support.v4.media.MediaBrowserCompat$CustomActionResultReceiver$$ExternalSyntheticOutline0;
import java.io.Serializable;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Function;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function10;
import kotlin.jvm.functions.Function11;
import kotlin.jvm.functions.Function12;
import kotlin.jvm.functions.Function13;
import kotlin.jvm.functions.Function14;
import kotlin.jvm.functions.Function15;
import kotlin.jvm.functions.Function16;
import kotlin.jvm.functions.Function17;
import kotlin.jvm.functions.Function18;
import kotlin.jvm.functions.Function19;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function20;
import kotlin.jvm.functions.Function21;
import kotlin.jvm.functions.Function22;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.functions.Function5;
import kotlin.jvm.functions.Function6;
import kotlin.jvm.functions.Function7;
import kotlin.jvm.functions.Function8;
import kotlin.jvm.functions.Function9;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@Deprecated(level = DeprecationLevel.ERROR, message = "This class is no longer supported, do not use it.")
@Deprecated
public abstract class FunctionImpl implements Function, Serializable, Function0, Function1, Function2, Function3, Function4, Function5, Function6, Function7, Function8, Function9, Function10, Function11, Function12, Function13, Function14, Function15, Function16, Function17, Function18, Function19, Function20, Function21, Function22 {
    public final void checkArity(int i) {
        if (getArity() == i) {
            return;
        }
        throwWrongArity(i);
        throw null;
    }

    public abstract int getArity();

    @Override // kotlin.jvm.functions.Function0
    public Object invoke() {
        checkArity(0);
        invokeVararg(new Object[0]);
        throw null;
    }

    public Object invokeVararg(Object... objArr) {
        throw new UnsupportedOperationException();
    }

    public final void throwWrongArity(int i) {
        StringBuilder sbM = MediaBrowserCompat$CustomActionResultReceiver$$ExternalSyntheticOutline0.m("Wrong function arity, expected: ", i, ", actual: ");
        sbM.append(getArity());
        throw new IllegalStateException(sbM.toString());
    }

    @Override // kotlin.jvm.functions.Function1
    public Object invoke(Object obj) {
        checkArity(1);
        invokeVararg(obj);
        throw null;
    }

    @Override // kotlin.jvm.functions.Function2
    public Object invoke(Object obj, Object obj2) {
        checkArity(2);
        invokeVararg(obj, obj2);
        throw null;
    }

    @Override // kotlin.jvm.functions.Function3
    public Object invoke(Object obj, Object obj2, Object obj3) {
        checkArity(3);
        invokeVararg(obj, obj2, obj3);
        throw null;
    }

    @Override // kotlin.jvm.functions.Function4
    public Object invoke(Object obj, Object obj2, Object obj3, Object obj4) {
        checkArity(4);
        invokeVararg(obj, obj2, obj3, obj4);
        throw null;
    }

    @Override // kotlin.jvm.functions.Function5
    public Object invoke(Object obj, Object obj2, Object obj3, Object obj4, Object obj5) {
        checkArity(5);
        invokeVararg(obj, obj2, obj3, obj4, obj5);
        throw null;
    }

    @Override // kotlin.jvm.functions.Function6
    public Object invoke(Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6) {
        checkArity(6);
        invokeVararg(obj, obj2, obj3, obj4, obj5, obj6);
        throw null;
    }

    @Override // kotlin.jvm.functions.Function7
    public Object invoke(Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, Object obj7) {
        checkArity(7);
        invokeVararg(obj, obj2, obj3, obj4, obj5, obj6, obj7);
        throw null;
    }

    @Override // kotlin.jvm.functions.Function8
    public Object invoke(Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, Object obj7, Object obj8) {
        checkArity(8);
        invokeVararg(obj, obj2, obj3, obj4, obj5, obj6, obj7, obj8);
        throw null;
    }

    @Override // kotlin.jvm.functions.Function9
    public Object invoke(Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, Object obj7, Object obj8, Object obj9) {
        checkArity(9);
        invokeVararg(obj, obj2, obj3, obj4, obj5, obj6, obj7, obj8, obj9);
        throw null;
    }

    @Override // kotlin.jvm.functions.Function10
    public Object invoke(Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, Object obj7, Object obj8, Object obj9, Object obj10) {
        checkArity(10);
        invokeVararg(obj, obj2, obj3, obj4, obj5, obj6, obj7, obj8, obj9, obj10);
        throw null;
    }

    @Override // kotlin.jvm.functions.Function11
    public Object invoke(Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, Object obj7, Object obj8, Object obj9, Object obj10, Object obj11) {
        checkArity(11);
        invokeVararg(obj, obj2, obj3, obj4, obj5, obj6, obj7, obj8, obj9, obj10, obj11);
        throw null;
    }

    @Override // kotlin.jvm.functions.Function12
    public Object invoke(Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, Object obj7, Object obj8, Object obj9, Object obj10, Object obj11, Object obj12) {
        checkArity(12);
        invokeVararg(obj, obj2, obj3, obj4, obj5, obj6, obj7, obj8, obj9, obj10, obj11, obj12);
        throw null;
    }

    @Override // kotlin.jvm.functions.Function13
    public Object invoke(Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, Object obj7, Object obj8, Object obj9, Object obj10, Object obj11, Object obj12, Object obj13) {
        checkArity(13);
        invokeVararg(obj, obj2, obj3, obj4, obj5, obj6, obj7, obj8, obj9, obj10, obj11, obj12, obj13);
        throw null;
    }

    @Override // kotlin.jvm.functions.Function14
    public Object invoke(Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, Object obj7, Object obj8, Object obj9, Object obj10, Object obj11, Object obj12, Object obj13, Object obj14) {
        checkArity(14);
        invokeVararg(obj, obj2, obj3, obj4, obj5, obj6, obj7, obj8, obj9, obj10, obj11, obj12, obj13, obj14);
        throw null;
    }

    @Override // kotlin.jvm.functions.Function15
    public Object invoke(Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, Object obj7, Object obj8, Object obj9, Object obj10, Object obj11, Object obj12, Object obj13, Object obj14, Object obj15) {
        checkArity(15);
        invokeVararg(obj, obj2, obj3, obj4, obj5, obj6, obj7, obj8, obj9, obj10, obj11, obj12, obj13, obj14, obj15);
        throw null;
    }

    @Override // kotlin.jvm.functions.Function16
    public Object invoke(Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, Object obj7, Object obj8, Object obj9, Object obj10, Object obj11, Object obj12, Object obj13, Object obj14, Object obj15, Object obj16) {
        checkArity(16);
        invokeVararg(obj, obj2, obj3, obj4, obj5, obj6, obj7, obj8, obj9, obj10, obj11, obj12, obj13, obj14, obj15, obj16);
        throw null;
    }

    @Override // kotlin.jvm.functions.Function17
    public Object invoke(Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, Object obj7, Object obj8, Object obj9, Object obj10, Object obj11, Object obj12, Object obj13, Object obj14, Object obj15, Object obj16, Object obj17) {
        checkArity(17);
        invokeVararg(obj, obj2, obj3, obj4, obj5, obj6, obj7, obj8, obj9, obj10, obj11, obj12, obj13, obj14, obj15, obj16, obj17);
        throw null;
    }

    @Override // kotlin.jvm.functions.Function18
    public Object invoke(Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, Object obj7, Object obj8, Object obj9, Object obj10, Object obj11, Object obj12, Object obj13, Object obj14, Object obj15, Object obj16, Object obj17, Object obj18) {
        checkArity(18);
        invokeVararg(obj, obj2, obj3, obj4, obj5, obj6, obj7, obj8, obj9, obj10, obj11, obj12, obj13, obj14, obj15, obj16, obj17, obj18);
        throw null;
    }

    @Override // kotlin.jvm.functions.Function19
    public Object invoke(Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, Object obj7, Object obj8, Object obj9, Object obj10, Object obj11, Object obj12, Object obj13, Object obj14, Object obj15, Object obj16, Object obj17, Object obj18, Object obj19) {
        checkArity(19);
        invokeVararg(obj, obj2, obj3, obj4, obj5, obj6, obj7, obj8, obj9, obj10, obj11, obj12, obj13, obj14, obj15, obj16, obj17, obj18, obj19);
        throw null;
    }

    @Override // kotlin.jvm.functions.Function20
    public Object invoke(Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, Object obj7, Object obj8, Object obj9, Object obj10, Object obj11, Object obj12, Object obj13, Object obj14, Object obj15, Object obj16, Object obj17, Object obj18, Object obj19, Object obj20) {
        checkArity(20);
        invokeVararg(obj, obj2, obj3, obj4, obj5, obj6, obj7, obj8, obj9, obj10, obj11, obj12, obj13, obj14, obj15, obj16, obj17, obj18, obj19, obj20);
        throw null;
    }

    @Override // kotlin.jvm.functions.Function21
    public Object invoke(Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, Object obj7, Object obj8, Object obj9, Object obj10, Object obj11, Object obj12, Object obj13, Object obj14, Object obj15, Object obj16, Object obj17, Object obj18, Object obj19, Object obj20, Object obj21) {
        checkArity(21);
        invokeVararg(obj, obj2, obj3, obj4, obj5, obj6, obj7, obj8, obj9, obj10, obj11, obj12, obj13, obj14, obj15, obj16, obj17, obj18, obj19, obj20, obj21);
        throw null;
    }

    @Override // kotlin.jvm.functions.Function22
    public Object invoke(Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, Object obj7, Object obj8, Object obj9, Object obj10, Object obj11, Object obj12, Object obj13, Object obj14, Object obj15, Object obj16, Object obj17, Object obj18, Object obj19, Object obj20, Object obj21, Object obj22) {
        checkArity(22);
        invokeVararg(obj, obj2, obj3, obj4, obj5, obj6, obj7, obj8, obj9, obj10, obj11, obj12, obj13, obj14, obj15, obj16, obj17, obj18, obj19, obj20, obj21, obj22);
        throw null;
    }
}
