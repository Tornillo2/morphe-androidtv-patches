package com.amazon.ignitionshared.pear;

import android.content.SharedPreferences;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@ScopeMetadata("com.amazon.livingroom.di.ApplicationScope")
@DaggerGenerated
@QualifierMetadata({"javax.inject.Named"})
public final class PearUpdateStructure_Factory implements Factory<PearUpdateStructure> {
    public final Provider<SharedPreferences> sharedPreferencesProvider;

    public PearUpdateStructure_Factory(Provider<SharedPreferences> provider) {
        this.sharedPreferencesProvider = provider;
    }

    public static PearUpdateStructure_Factory create(Provider<SharedPreferences> provider) {
        return new PearUpdateStructure_Factory(provider);
    }

    public static PearUpdateStructure newInstance(SharedPreferences sharedPreferences) {
        return new PearUpdateStructure(sharedPreferences);
    }

    @Override // javax.inject.Provider, jakarta.inject.Provider
    public PearUpdateStructure get() {
        return new PearUpdateStructure(this.sharedPreferencesProvider.get());
    }
}
