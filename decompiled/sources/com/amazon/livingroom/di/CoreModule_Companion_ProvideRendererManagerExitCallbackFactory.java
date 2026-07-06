package com.amazon.livingroom.di;

import com.amazon.ignitionshared.RendererManager;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@ScopeMetadata
@DaggerGenerated
@QualifierMetadata
public final class CoreModule_Companion_ProvideRendererManagerExitCallbackFactory implements Factory<RendererManager.ExitCallback> {

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class InstanceHolder {
        public static final CoreModule_Companion_ProvideRendererManagerExitCallbackFactory INSTANCE = new CoreModule_Companion_ProvideRendererManagerExitCallbackFactory();
    }

    public static CoreModule_Companion_ProvideRendererManagerExitCallbackFactory create() {
        return InstanceHolder.INSTANCE;
    }

    public static RendererManager.ExitCallback provideRendererManagerExitCallback() {
        CoreModule.Companion.getClass();
        return new CoreModule$Companion$provideRendererManagerExitCallback$1();
    }

    @Override // javax.inject.Provider, jakarta.inject.Provider
    public RendererManager.ExitCallback get() {
        return provideRendererManagerExitCallback();
    }

    @Override // javax.inject.Provider, jakarta.inject.Provider
    public Object get() {
        return provideRendererManagerExitCallback();
    }
}
