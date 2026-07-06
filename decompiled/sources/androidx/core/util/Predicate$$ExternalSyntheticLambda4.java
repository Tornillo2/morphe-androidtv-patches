package androidx.core.util;

import androidx.core.util.Predicate;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class Predicate$$ExternalSyntheticLambda4 implements Predicate {
    public final /* synthetic */ Predicate f$0;

    public /* synthetic */ Predicate$$ExternalSyntheticLambda4(Predicate predicate) {
        this.f$0 = predicate;
    }

    @Override // androidx.core.util.Predicate
    public /* synthetic */ Predicate and(Predicate predicate) {
        return Predicate.CC.$default$and(this, predicate);
    }

    @Override // androidx.core.util.Predicate
    public Predicate negate() {
        return new Predicate$$ExternalSyntheticLambda4(this);
    }

    @Override // androidx.core.util.Predicate
    public /* synthetic */ Predicate or(Predicate predicate) {
        return Predicate.CC.$default$or(this, predicate);
    }

    @Override // androidx.core.util.Predicate
    public final boolean test(Object obj) {
        return Predicate.CC.$private$lambda$negate$1(this.f$0, obj);
    }
}
