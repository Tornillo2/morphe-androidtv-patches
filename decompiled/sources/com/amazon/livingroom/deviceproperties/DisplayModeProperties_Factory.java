package com.amazon.livingroom.deviceproperties;

import android.content.ContentResolver;
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
public final class DisplayModeProperties_Factory implements Factory<DisplayModeProperties> {
    public final Provider<ContentResolver> contentResolverProvider;

    public DisplayModeProperties_Factory(Provider<ContentResolver> provider) {
        this.contentResolverProvider = provider;
    }

    public static DisplayModeProperties_Factory create(Provider<ContentResolver> provider) {
        return new DisplayModeProperties_Factory(provider);
    }

    public static DisplayModeProperties newInstance(ContentResolver contentResolver) {
        return new DisplayModeProperties(contentResolver);
    }

    @Override // javax.inject.Provider, jakarta.inject.Provider
    public DisplayModeProperties get() {
        return new DisplayModeProperties(this.contentResolverProvider.get());
    }
}
