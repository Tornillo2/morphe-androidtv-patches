package com.google.common.eventbus;

import com.google.common.base.Function;
import com.google.common.collect.ImmutableSet;
import com.google.common.reflect.TypeToken;
import java.util.Collection;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class SubscriberRegistry$$ExternalSyntheticLambda1 implements Function {
    @Override // com.google.common.base.Function
    public final Object apply(Object obj) {
        return ImmutableSet.copyOf((Collection) new TypeToken.TypeSet().rawTypes());
    }
}
