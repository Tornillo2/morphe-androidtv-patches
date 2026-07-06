package com.amazon.livingroom.di;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import java.util.List;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@ScopeMetadata
@DaggerGenerated
@QualifierMetadata({"javax.inject.Named"})
public final class CoreModule_Companion_ProvideSupportedLocaleLanguageTagsFactory implements Factory<List<String>> {

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class InstanceHolder {
        public static final CoreModule_Companion_ProvideSupportedLocaleLanguageTagsFactory INSTANCE = new CoreModule_Companion_ProvideSupportedLocaleLanguageTagsFactory();
    }

    public static CoreModule_Companion_ProvideSupportedLocaleLanguageTagsFactory create() {
        return InstanceHolder.INSTANCE;
    }

    public static List<String> provideSupportedLocaleLanguageTags() {
        List<String> listProvideSupportedLocaleLanguageTags = CoreModule.Companion.provideSupportedLocaleLanguageTags();
        Preconditions.checkNotNullFromProvides(listProvideSupportedLocaleLanguageTags);
        return listProvideSupportedLocaleLanguageTags;
    }

    @Override // javax.inject.Provider, jakarta.inject.Provider
    public Object get() {
        return provideSupportedLocaleLanguageTags();
    }

    @Override // javax.inject.Provider, jakarta.inject.Provider
    public List<String> get() {
        return provideSupportedLocaleLanguageTags();
    }
}
