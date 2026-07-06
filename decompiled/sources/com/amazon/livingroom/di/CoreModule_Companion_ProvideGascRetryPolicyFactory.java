package com.amazon.livingroom.di;

import com.android.volley.RetryPolicy;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@ScopeMetadata
@DaggerGenerated
@QualifierMetadata({"javax.inject.Named"})
public final class CoreModule_Companion_ProvideGascRetryPolicyFactory implements Factory<RetryPolicy> {

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class InstanceHolder {
        public static final CoreModule_Companion_ProvideGascRetryPolicyFactory INSTANCE = new CoreModule_Companion_ProvideGascRetryPolicyFactory();
    }

    public static CoreModule_Companion_ProvideGascRetryPolicyFactory create() {
        return InstanceHolder.INSTANCE;
    }

    public static RetryPolicy provideGascRetryPolicy() {
        return CoreModule.Companion.provideGascRetryPolicy();
    }

    @Override // javax.inject.Provider, jakarta.inject.Provider
    public RetryPolicy get() {
        return CoreModule.Companion.provideGascRetryPolicy();
    }

    @Override // javax.inject.Provider, jakarta.inject.Provider
    public Object get() {
        return CoreModule.Companion.provideGascRetryPolicy();
    }
}
