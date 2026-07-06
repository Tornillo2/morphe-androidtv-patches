package com.amazon.ignitionshared.applauncher;

import android.content.Context;
import com.amazon.ignitionshared.GMBMessageProcessor;
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
public final class AppLauncherServiceInitializer_Factory implements Factory<AppLauncherServiceInitializer> {
    public final Provider<Context> contextProvider;
    public final Provider<GMBMessageProcessor> gmbMessageProcessorProvider;
    public final Provider<GMBMessageSender> gmbMessageSenderProvider;

    public AppLauncherServiceInitializer_Factory(Provider<Context> provider, Provider<GMBMessageProcessor> provider2, Provider<GMBMessageSender> provider3) {
        this.contextProvider = provider;
        this.gmbMessageProcessorProvider = provider2;
        this.gmbMessageSenderProvider = provider3;
    }

    public static AppLauncherServiceInitializer_Factory create(Provider<Context> provider, Provider<GMBMessageProcessor> provider2, Provider<GMBMessageSender> provider3) {
        return new AppLauncherServiceInitializer_Factory(provider, provider2, provider3);
    }

    public static AppLauncherServiceInitializer newInstance(Context context, GMBMessageProcessor gMBMessageProcessor, GMBMessageSender gMBMessageSender) {
        return new AppLauncherServiceInitializer(context, gMBMessageProcessor, gMBMessageSender);
    }

    @Override // javax.inject.Provider, jakarta.inject.Provider
    public AppLauncherServiceInitializer get() {
        return new AppLauncherServiceInitializer(this.contextProvider.get(), this.gmbMessageProcessorProvider.get(), this.gmbMessageSenderProvider.get());
    }
}
