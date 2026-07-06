package com.amazon.primevideo.di;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@ScopeMetadata
@DaggerGenerated
@QualifierMetadata({"javax.inject.Named"})
public final class IgnitionApplicationModule_Companion_ProvidePearRecommendationsEnabledFactory implements Factory<Boolean> {

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class InstanceHolder {
        public static final IgnitionApplicationModule_Companion_ProvidePearRecommendationsEnabledFactory INSTANCE = new IgnitionApplicationModule_Companion_ProvidePearRecommendationsEnabledFactory();
    }

    public static IgnitionApplicationModule_Companion_ProvidePearRecommendationsEnabledFactory create() {
        return InstanceHolder.INSTANCE;
    }

    public static boolean providePearRecommendationsEnabled() {
        IgnitionApplicationModule.Companion.getClass();
        return true;
    }

    @Override // javax.inject.Provider, jakarta.inject.Provider
    public /* bridge */ /* synthetic */ Object get() {
        get();
        return Boolean.TRUE;
    }

    @Override // javax.inject.Provider, jakarta.inject.Provider
    public Boolean get() {
        providePearRecommendationsEnabled();
        return Boolean.TRUE;
    }
}
