package com.amazon.primevideo.di;

import android.content.Context;
import com.amazon.ignitionshared.EngineAssetsHashKeys;
import com.amazon.ignitionshared.IgniteRenderer;
import com.amazon.ignitionshared.ServiceInitializer;
import com.amazon.ignitionshared.deeplink.AndroidTVDeepLinkIntentParser;
import com.amazon.ignitionshared.deeplink.DeepLinkIntentParser;
import com.amazon.ignitionshared.migration.IgniteMigrationManager;
import com.amazon.ignitionshared.migration.MigrationManager;
import com.amazon.livingroom.accessibility.AndroidTextToSpeechEngine;
import com.amazon.livingroom.accessibility.TextToSpeechEngine;
import com.amazon.livingroom.deviceproperties.AdvertisingProperties;
import com.amazon.livingroom.deviceproperties.BillingProperties;
import com.amazon.livingroom.deviceproperties.PlatformProperty;
import com.amazon.livingroom.di.ApplicationContext;
import com.amazon.livingroom.di.Names;
import com.amazon.livingroom.mediapipelinebackend.MediaEventHandler;
import com.amazon.primevideo.BuildConfig;
import com.amazon.primevideo.advertising.GoogleAdvertisingProperties;
import com.amazon.primevideo.nativebilling.BillingServiceInitializer;
import com.amazon.primevideo.nativebilling.GoogleBillingProperties;
import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import dagger.multibindings.IntoSet;
import java.io.File;
import java.util.List;
import javax.inject.Named;
import kotlin.collections.EmptyList;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@Module(includes = {Bindings.class}, subcomponents = {AndroidTvMainActivityComponent.class})
public final class ApplicationModule {
    public final Context applicationContext;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @Module
    public interface Bindings {
        @Binds
        @NotNull
        AdvertisingProperties bindAdvertisingProperties(@NotNull GoogleAdvertisingProperties googleAdvertisingProperties);

        @Binds
        @NotNull
        BillingProperties bindBillingProperties(@NotNull GoogleBillingProperties googleBillingProperties);

        @Binds
        @IntoSet
        @NotNull
        ServiceInitializer bindBillingServiceInitializer(@NotNull BillingServiceInitializer billingServiceInitializer);

        @Binds
        @NotNull
        DeepLinkIntentParser bindDeepLinkIntentParser(@NotNull AndroidTVDeepLinkIntentParser androidTVDeepLinkIntentParser);

        @Binds
        @Named(Names.IGNITE_ASSETS_ARCHIVE_EXTRACTION_DIR)
        @NotNull
        File bindIgniteAssetBundleExtractionDir(@Named(Names.IGNITE_ROOT_DIR) @NotNull File file);

        @Binds
        @NotNull
        MediaEventHandler bindMediaEventHandler(@NotNull IgniteRenderer.EventHandler eventHandler);

        @Binds
        @NotNull
        MigrationManager bindMigrationManager(@NotNull IgniteMigrationManager igniteMigrationManager);

        @Binds
        @NotNull
        TextToSpeechEngine bindTextToSpeechEngine(@NotNull AndroidTextToSpeechEngine androidTextToSpeechEngine);
    }

    public ApplicationModule(@NotNull Context contextParam) {
        Intrinsics.checkNotNullParameter(contextParam, "contextParam");
        this.applicationContext = contextParam.getApplicationContext();
    }

    @Provides
    @Named(Names.ACTION_OVERRIDE_DEVICE_PROPERTIES)
    @NotNull
    public final String provideActionOverrideDeviceProperties() {
        return BuildConfig.ACTION_OVERRIDE_DEVICE_PROPERTIES;
    }

    @Provides
    @Named(Names.ALLOW_LOCAL_PROPERTY_OVERRIDES)
    public final boolean provideAllowLocalPropertyOverrides() {
        return false;
    }

    @ApplicationContext
    @Provides
    @NotNull
    public final Context provideApplicationContext() {
        Context applicationContext = this.applicationContext;
        Intrinsics.checkNotNullExpressionValue(applicationContext, "applicationContext");
        return applicationContext;
    }

    @Provides
    @Named(Names.IGNITE_ROOT_DIR)
    @NotNull
    public final File provideIgniteDataDir() {
        File filesDir = this.applicationContext.getFilesDir();
        Intrinsics.checkNotNullExpressionValue(filesDir, "getFilesDir(...)");
        return filesDir;
    }

    @Provides
    @Named(Names.IGNITE_EXTRACTED_ASSETS_HASH_KEY)
    @NotNull
    public final String provideIgniteExtractedAssetsHashKey() {
        return EngineAssetsHashKeys.IGNITE_ASSETS_HASH_KEY;
    }

    @Provides
    @Named(Names.PLATFORM_PROPERTIES)
    @NotNull
    public final List<PlatformProperty<?>> providePlatformProperties() {
        return EmptyList.INSTANCE;
    }
}
