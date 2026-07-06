package com.google.common.util.concurrent;

import com.google.common.base.Function;
import java.util.List;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class FuturesGetChecked$$ExternalSyntheticLambda0 implements Function {
    @Override // com.google.common.base.Function
    public final Object apply(Object obj) {
        return Boolean.valueOf(((List) obj).contains(String.class));
    }
}
