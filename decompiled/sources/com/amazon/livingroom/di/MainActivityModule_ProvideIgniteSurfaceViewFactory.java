package com.amazon.livingroom.di;

import android.view.SurfaceView;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@ScopeMetadata
@DaggerGenerated
@QualifierMetadata({"javax.inject.Named"})
public final class MainActivityModule_ProvideIgniteSurfaceViewFactory implements Factory<SurfaceView> {
    public final MainActivityModule module;

    public MainActivityModule_ProvideIgniteSurfaceViewFactory(MainActivityModule mainActivityModule) {
        this.module = mainActivityModule;
    }

    public static MainActivityModule_ProvideIgniteSurfaceViewFactory create(MainActivityModule mainActivityModule) {
        return new MainActivityModule_ProvideIgniteSurfaceViewFactory(mainActivityModule);
    }

    public static SurfaceView provideIgniteSurfaceView(MainActivityModule mainActivityModule) {
        SurfaceView surfaceViewProvideIgniteSurfaceView = mainActivityModule.provideIgniteSurfaceView();
        Preconditions.checkNotNullFromProvides(surfaceViewProvideIgniteSurfaceView);
        return surfaceViewProvideIgniteSurfaceView;
    }

    @Override // javax.inject.Provider, jakarta.inject.Provider
    public SurfaceView get() {
        return provideIgniteSurfaceView(this.module);
    }

    @Override // javax.inject.Provider, jakarta.inject.Provider
    public Object get() {
        return provideIgniteSurfaceView(this.module);
    }
}
