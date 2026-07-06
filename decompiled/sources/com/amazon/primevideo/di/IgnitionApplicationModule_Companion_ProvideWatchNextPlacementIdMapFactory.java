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
public final class IgnitionApplicationModule_Companion_ProvideWatchNextPlacementIdMapFactory implements Factory<String> {

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class InstanceHolder {
        public static final IgnitionApplicationModule_Companion_ProvideWatchNextPlacementIdMapFactory INSTANCE = new IgnitionApplicationModule_Companion_ProvideWatchNextPlacementIdMapFactory();
    }

    public static IgnitionApplicationModule_Companion_ProvideWatchNextPlacementIdMapFactory create() {
        return InstanceHolder.INSTANCE;
    }

    public static String provideWatchNextPlacementIdMap() {
        IgnitionApplicationModule.Companion.getClass();
        return "b5e9a5cb-2d0a-48b3-ab8b-a6ebca506477";
    }

    @Override // javax.inject.Provider, jakarta.inject.Provider
    public /* bridge */ /* synthetic */ Object get() {
        get();
        return "b5e9a5cb-2d0a-48b3-ab8b-a6ebca506477";
    }

    @Override // javax.inject.Provider, jakarta.inject.Provider
    public String get() {
        provideWatchNextPlacementIdMap();
        return "b5e9a5cb-2d0a-48b3-ab8b-a6ebca506477";
    }
}
