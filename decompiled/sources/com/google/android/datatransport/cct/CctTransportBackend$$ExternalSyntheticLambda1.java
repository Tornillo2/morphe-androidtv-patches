package com.google.android.datatransport.cct;

import com.google.android.datatransport.cct.CctTransportBackend;
import com.google.android.datatransport.runtime.retries.RetryStrategy;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class CctTransportBackend$$ExternalSyntheticLambda1 implements RetryStrategy {
    @Override // com.google.android.datatransport.runtime.retries.RetryStrategy
    public final Object shouldRetry(Object obj, Object obj2) {
        return CctTransportBackend.m348$r8$lambda$ZhERf3WZxxKL1vqOg2FJv71EaQ((CctTransportBackend.HttpRequest) obj, (CctTransportBackend.HttpResponse) obj2);
    }
}
