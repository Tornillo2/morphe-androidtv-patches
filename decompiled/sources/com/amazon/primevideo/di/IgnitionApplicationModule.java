package com.amazon.primevideo.di;

import android.content.ComponentName;
import android.content.Context;
import com.amazon.android.ignitionx.BuildConfig;
import com.amazon.ignitionshared.DeviceAttestationService;
import com.amazon.ignitionshared.NoopDeviceAttestor;
import com.amazon.ignitionshared.ServiceInitializer;
import com.amazon.ignitionshared.pear.PearPlacement;
import com.amazon.ignitionshared.pear.PearRecommendationServiceInitializer;
import com.amazon.ignitionshared.recommendation.PersonalisedRecommendationPlacement;
import com.amazon.ignitionshared.watchnext.WatchNextPlacement;
import com.amazon.livingroom.di.ApplicationContext;
import com.amazon.livingroom.di.ApplicationScope;
import com.amazon.livingroom.di.Names;
import com.amazon.livingroom.voice.VoiceService;
import com.amazon.primevideo.R;
import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import dagger.multibindings.IntoMap;
import dagger.multibindings.IntoSet;
import dagger.multibindings.StringKey;
import javax.inject.Named;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@Module
public abstract class IgnitionApplicationModule {

    @NotNull
    public static final Companion Companion = new Companion();

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @Module
    public static final class Companion {
        public Companion() {
        }

        @Provides
        @Named(Names.RECOMMENDATION_APPLICATION_COLOR)
        public final int provideApplicationColor() {
            return R.color.pv_blue;
        }

        @Provides
        @Named(Names.IGNITION_APPLICATION_ID)
        @NotNull
        public final String provideApplicationID() {
            return "";
        }

        @Provides
        @Named(Names.RECOMMENDATION_APPLICATION_ICON)
        public final int provideApplicationIcon() {
            return R.drawable.recommendations_icon;
        }

        @Provides
        @Named(Names.RECOMMENDATION_APPLICATION_NAME)
        @NotNull
        public final String provideApplicationName(@ApplicationContext @NotNull Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            String string = context.getResources().getString(R.string.app_name);
            Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
            return string;
        }

        @Provides
        @Named(Names.RECOMMENDATION_APPLICATION_KEY)
        @NotNull
        public final String provideApplicationRecommendationName() {
            return BuildConfig.FLAVOR_App;
        }

        @Provides
        @Named(Names.IGNITION_APPLICATION_DEFAULT_DTID)
        @NotNull
        public final String provideDefaultDTID() {
            return "A2SNKIF736WF4T";
        }

        @ApplicationScope
        @Provides
        @NotNull
        public final DeviceAttestationService.Attestor provideDeviceAttestationAttestor() {
            return new NoopDeviceAttestor();
        }

        @Provides
        @Named(Names.DTID_ACM_CONFIG_NAME)
        @NotNull
        public final String provideDtidAcmEndpoint() {
            return "androidtv-primevideo-dtid-config";
        }

        @Provides
        @Named(Names.IGNITE_ASSETS_ARCHIVE_HASH)
        @NotNull
        public final String provideIgniteAssetsArchiveHash(@ApplicationContext @NotNull Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            String string = context.getString(com.amazon.android.ignitionx.R.string.igniteAssetsHash);
            Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
            return string;
        }

        @Provides
        @Named(Names.IGNITE_ASSETS_ARCHIVE_NAME)
        @NotNull
        public final String provideIgniteAssetsArchiveName() {
            return BuildConfig.IGNITE_ASSETS_FILE_NAME;
        }

        @ApplicationScope
        @Provides
        @Named(Names.MAIN_ACTIVITY_NAME)
        @NotNull
        public final ComponentName provideMainActivityName(@Named(Names.APPLICATION_PACKAGE_NAME) @Nullable String str) {
            Intrinsics.checkNotNull(str);
            return new ComponentName(str, "com.amazon.ignition.IgnitionActivity");
        }

        @Provides
        @Named(Names.PEAR_PLACEMENT_ID)
        @NotNull
        public final String providePearPlacementId() {
            return "d0742cd4-8d00-446b-ac42-ba77cd8de5f3";
        }

        @Provides
        @Named(Names.PEAR_RECOMMENDATIONS_ENABLED)
        public final boolean providePearRecommendationsEnabled() {
            return true;
        }

        @Provides
        @Named(Names.PEAR_WATCH_NEXT_ENABLED)
        public final boolean providePearWatchNextEnabled() {
            return true;
        }

        @Provides
        @StringKey(Names.PEAR_PERSONALISED_RECOMMENDATIONS_PLACEMENT_KEY)
        @Named(Names.PEAR_PLACEMENT_ID_MAP)
        @NotNull
        @IntoMap
        public final String providePersonalisedRecommendationsPlacementIdMap() {
            return "d0742cd4-8d00-446b-ac42-ba77cd8de5f3";
        }

        @Provides
        @Named(Names.RECOMMENDATION_DEFAULT_CHANNEL_ID)
        @NotNull
        public final String provideRecommendationDefaultChannelId() {
            return "primevideo:default:channel:id";
        }

        @Provides
        @StringKey(Names.PEAR_WATCH_NEXT_PLACEMENT_KEY)
        @Named(Names.PEAR_PLACEMENT_ID_MAP)
        @NotNull
        @IntoMap
        public final String provideWatchNextPlacementIdMap() {
            return "b5e9a5cb-2d0a-48b3-ab8b-a6ebca506477";
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
        }
    }

    @ApplicationScope
    @Binds
    @IntoSet
    @NotNull
    public abstract PearPlacement provideRecommendationPearPlacement(@NotNull PersonalisedRecommendationPlacement personalisedRecommendationPlacement);

    @ApplicationScope
    @Binds
    @IntoSet
    @NotNull
    public abstract ServiceInitializer provideRecommendationServiceInitializer(@NotNull PearRecommendationServiceInitializer pearRecommendationServiceInitializer);

    @ApplicationScope
    @Binds
    @IntoSet
    @NotNull
    public abstract ServiceInitializer provideVoiceService(@NotNull VoiceService voiceService);

    @ApplicationScope
    @Binds
    @IntoSet
    @NotNull
    public abstract PearPlacement provideWatchNextPearPlacement(@NotNull WatchNextPlacement watchNextPlacement);
}
