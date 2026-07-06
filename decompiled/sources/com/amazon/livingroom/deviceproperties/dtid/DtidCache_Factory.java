package com.amazon.livingroom.deviceproperties.dtid;

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
public final class DtidCache_Factory implements Factory<DtidCache> {
    public final Provider<SharedPreferences> sharedPreferencesProvider;

    public DtidCache_Factory(Provider<SharedPreferences> provider) {
        this.sharedPreferencesProvider = provider;
    }

    public static DtidCache_Factory create(Provider<SharedPreferences> provider) {
        return new DtidCache_Factory(provider);
    }

    public static DtidCache newInstance(SharedPreferences sharedPreferences) {
        return new DtidCache(sharedPreferences);
    }

    @Override // javax.inject.Provider, jakarta.inject.Provider
    public DtidCache get() {
        return new DtidCache(this.sharedPreferencesProvider.get());
    }
}
