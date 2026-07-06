package com.amazon.livingroom.di;

import androidx.appcompat.app.AppCompatActivity;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@ScopeMetadata
@DaggerGenerated
@QualifierMetadata
public final class MainActivityModule_ProvideActivityFactory implements Factory<AppCompatActivity> {
    public final MainActivityModule module;

    public MainActivityModule_ProvideActivityFactory(MainActivityModule mainActivityModule) {
        this.module = mainActivityModule;
    }

    public static MainActivityModule_ProvideActivityFactory create(MainActivityModule mainActivityModule) {
        return new MainActivityModule_ProvideActivityFactory(mainActivityModule);
    }

    public static AppCompatActivity provideActivity(MainActivityModule mainActivityModule) {
        AppCompatActivity appCompatActivityProvideActivity = mainActivityModule.provideActivity();
        Preconditions.checkNotNullFromProvides(appCompatActivityProvideActivity);
        return appCompatActivityProvideActivity;
    }

    @Override // javax.inject.Provider, jakarta.inject.Provider
    public AppCompatActivity get() {
        return provideActivity(this.module);
    }

    @Override // javax.inject.Provider, jakarta.inject.Provider
    public Object get() {
        return provideActivity(this.module);
    }
}
