package com.google.common.collect;

import j$.util.stream.Stream;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class Streams$$ExternalSyntheticLambda5 implements Runnable {
    public final /* synthetic */ Stream f$0;

    public /* synthetic */ Streams$$ExternalSyntheticLambda5(Stream stream) {
        this.f$0 = stream;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.f$0.close();
    }
}
