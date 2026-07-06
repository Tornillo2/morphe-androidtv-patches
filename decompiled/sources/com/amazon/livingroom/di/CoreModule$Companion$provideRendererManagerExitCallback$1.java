package com.amazon.livingroom.di;

import com.amazon.ignitionshared.RendererManager;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public final class CoreModule$Companion$provideRendererManagerExitCallback$1 implements RendererManager.ExitCallback {
    @Override // com.amazon.ignitionshared.RendererManager.ExitCallback
    /* JADX INFO: renamed from: onRenderingExit, reason: collision with other method in class */
    public /* bridge */ /* synthetic */ void mo257onRenderingExit(int i) {
        onRenderingExit(i);
        throw null;
    }

    public Void onRenderingExit(int i) {
        System.exit(i);
        throw new RuntimeException("System.exit returned normally, while it was supposed to halt JVM.");
    }
}
