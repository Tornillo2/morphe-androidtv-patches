package com.amazon.livingroom.deviceproperties;

import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.display.DisplayManager;
import android.os.Handler;
import android.view.WindowManager;
import com.amazon.ignitionshared.GMBMessageSender;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@ScopeMetadata("com.amazon.livingroom.di.ApplicationScope")
@DaggerGenerated
@QualifierMetadata({"com.amazon.livingroom.di.ApplicationContext"})
public final class DisplayPropertiesProvider_Factory implements Factory<DisplayPropertiesProvider> {
    public final Provider<Context> contextProvider;
    public final Provider<DisplayManager> displayManagerProvider;
    public final Provider<GMBMessageSender> gmbMessageSenderProvider;
    public final Provider<Handler> handlerProvider;
    public final Provider<MaxVideoResolutionProvider> maxVideoResolutionProvider;
    public final Provider<PackageManager> packageManagerProvider;
    public final Provider<SystemProperties> systemPropertiesProvider;
    public final Provider<WindowManager> windowManagerProvider;

    public DisplayPropertiesProvider_Factory(Provider<WindowManager> provider, Provider<DisplayManager> provider2, Provider<PackageManager> provider3, Provider<SystemProperties> provider4, Provider<MaxVideoResolutionProvider> provider5, Provider<Context> provider6, Provider<GMBMessageSender> provider7, Provider<Handler> provider8) {
        this.windowManagerProvider = provider;
        this.displayManagerProvider = provider2;
        this.packageManagerProvider = provider3;
        this.systemPropertiesProvider = provider4;
        this.maxVideoResolutionProvider = provider5;
        this.contextProvider = provider6;
        this.gmbMessageSenderProvider = provider7;
        this.handlerProvider = provider8;
    }

    public static DisplayPropertiesProvider_Factory create(Provider<WindowManager> provider, Provider<DisplayManager> provider2, Provider<PackageManager> provider3, Provider<SystemProperties> provider4, Provider<MaxVideoResolutionProvider> provider5, Provider<Context> provider6, Provider<GMBMessageSender> provider7, Provider<Handler> provider8) {
        return new DisplayPropertiesProvider_Factory(provider, provider2, provider3, provider4, provider5, provider6, provider7, provider8);
    }

    public static DisplayPropertiesProvider newInstance(WindowManager windowManager, DisplayManager displayManager, PackageManager packageManager, Object obj, MaxVideoResolutionProvider maxVideoResolutionProvider, Context context, GMBMessageSender gMBMessageSender, Handler handler) {
        return new DisplayPropertiesProvider(windowManager, displayManager, packageManager, (SystemProperties) obj, maxVideoResolutionProvider, context, gMBMessageSender, handler);
    }

    @Override // javax.inject.Provider, jakarta.inject.Provider
    public DisplayPropertiesProvider get() {
        return newInstance(this.windowManagerProvider.get(), this.displayManagerProvider.get(), this.packageManagerProvider.get(), this.systemPropertiesProvider.get(), this.maxVideoResolutionProvider.get(), this.contextProvider.get(), this.gmbMessageSenderProvider.get(), this.handlerProvider.get());
    }
}
