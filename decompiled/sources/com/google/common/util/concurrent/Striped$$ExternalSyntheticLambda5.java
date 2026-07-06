package com.google.common.util.concurrent;

import com.google.common.base.Supplier;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class Striped$$ExternalSyntheticLambda5 implements Supplier {
    @Override // com.google.common.base.Supplier
    public final Object get() {
        return new ReentrantReadWriteLock();
    }
}
