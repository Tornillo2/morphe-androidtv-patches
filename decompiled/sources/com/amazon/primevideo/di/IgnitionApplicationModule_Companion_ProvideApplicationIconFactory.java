package com.amazon.primevideo.di;

import com.amazon.primevideo.R;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@ScopeMetadata
@DaggerGenerated
@QualifierMetadata({"javax.inject.Named"})
public final class IgnitionApplicationModule_Companion_ProvideApplicationIconFactory implements Factory<Integer> {

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class InstanceHolder {
        public static final IgnitionApplicationModule_Companion_ProvideApplicationIconFactory INSTANCE = new IgnitionApplicationModule_Companion_ProvideApplicationIconFactory();
    }

    public static IgnitionApplicationModule_Companion_ProvideApplicationIconFactory create() {
        return InstanceHolder.INSTANCE;
    }

    public static int provideApplicationIcon() {
        IgnitionApplicationModule.Companion.getClass();
        return R.drawable.recommendations_icon;
    }

    @Override // javax.inject.Provider, jakarta.inject.Provider
    public Integer get() {
        return Integer.valueOf(provideApplicationIcon());
    }
}
