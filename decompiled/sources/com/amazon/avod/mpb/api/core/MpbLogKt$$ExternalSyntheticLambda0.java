package com.amazon.avod.mpb.api.core;

import com.amazon.avod.mpb.api.callback.LogCallback;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class MpbLogKt$$ExternalSyntheticLambda0 implements LogCallback {
    @Override // com.amazon.avod.mpb.api.callback.LogCallback
    public final void onLog(LogCallback.LogLevel logLevel, Throwable th, String str, Object[] objArr) {
        MpbLogKt.getDefaultLogginCallback$lambda$1(logLevel, th, str, objArr);
    }
}
