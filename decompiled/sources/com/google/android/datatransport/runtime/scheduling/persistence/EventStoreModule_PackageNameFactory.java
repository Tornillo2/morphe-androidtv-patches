package com.google.android.datatransport.runtime.scheduling.persistence;

import android.content.Context;
import com.google.android.datatransport.runtime.dagger.internal.Factory;
import com.google.android.datatransport.runtime.dagger.internal.Preconditions;
import javax.inject.Provider;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class EventStoreModule_PackageNameFactory implements Factory<String> {
    public final Provider<Context> contextProvider;

    public EventStoreModule_PackageNameFactory(Provider<Context> provider) {
        this.contextProvider = provider;
    }

    public static EventStoreModule_PackageNameFactory create(Provider<Context> provider) {
        return new EventStoreModule_PackageNameFactory(provider);
    }

    public static String packageName(Context context) {
        String packageName = context.getPackageName();
        Preconditions.checkNotNull(packageName, "Cannot return null from a non-@Nullable @Provides method");
        return packageName;
    }

    @Override // javax.inject.Provider, jakarta.inject.Provider
    public String get() {
        return packageName(this.contextProvider.get());
    }
}
