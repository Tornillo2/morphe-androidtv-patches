package com.amazon.livingroom.mediapipelinebackend;

import android.view.WindowManager;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@ScopeMetadata("com.amazon.livingroom.di.ApplicationScope")
@DaggerGenerated
@QualifierMetadata
public final class DisplayInformation_Factory implements Factory<DisplayInformation> {
    public final Provider<WindowManager> windowManagerProvider;

    public DisplayInformation_Factory(Provider<WindowManager> provider) {
        this.windowManagerProvider = provider;
    }

    public static DisplayInformation_Factory create(Provider<WindowManager> provider) {
        return new DisplayInformation_Factory(provider);
    }

    public static DisplayInformation newInstance(WindowManager windowManager) {
        return new DisplayInformation(windowManager);
    }

    @Override // javax.inject.Provider, jakarta.inject.Provider
    public DisplayInformation get() {
        return new DisplayInformation(this.windowManagerProvider.get());
    }
}
