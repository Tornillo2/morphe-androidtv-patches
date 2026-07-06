package com.google.common.collect;

import com.google.common.base.Predicate;
import java.util.Map;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class Maps$FilteredEntryBiMap$$ExternalSyntheticLambda0 implements Predicate {
    public final /* synthetic */ Predicate f$0;

    public /* synthetic */ Maps$FilteredEntryBiMap$$ExternalSyntheticLambda0(Predicate predicate) {
        this.f$0 = predicate;
    }

    @Override // com.google.common.base.Predicate
    public final boolean apply(Object obj) {
        Map.Entry entry = (Map.Entry) obj;
        return this.f$0.apply(new ImmutableEntry(entry.getValue(), entry.getKey()));
    }
}
