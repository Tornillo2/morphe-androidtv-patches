package com.amazon.primevideo.di;

import android.content.Context;
import com.amazon.ignition.recommendation.contentprovider.RequestStructureContentProvider;
import com.amazon.livingroom.di.ApplicationComponent;
import com.amazon.livingroom.di.ApplicationScope;
import com.amazon.livingroom.di.CoreModule;
import com.amazon.livingroom.di.RecommendationModule;
import com.amazon.livingroom.di.WatchNextModule;
import com.amazon.primevideo.PrimeVideoApplication;
import com.amazon.primevideo.di.AndroidTvMainActivityComponent;
import com.amazon.primevideo.di.DaggerPrimeVideoApplicationComponent;
import com.amazon.primevideo.receiver.LocaleChangeReceiver;
import com.amazon.primevideo.service.UpdateRecommendationsJobService;
import dagger.Component;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@ApplicationScope
@Component(modules = {CoreModule.class, ApplicationModule.class, RecommendationModule.class, IgnitionApplicationModule.class, WatchNextModule.class})
public interface PrimeVideoApplicationComponent extends ApplicationComponent {

    @NotNull
    public static final Companion Companion = Companion.$$INSTANCE;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class Companion {
        public static final /* synthetic */ Companion $$INSTANCE = new Companion();
        public static PrimeVideoApplicationComponent instance;

        @NotNull
        public final PrimeVideoApplicationComponent getInstance() {
            PrimeVideoApplicationComponent primeVideoApplicationComponent = instance;
            if (primeVideoApplicationComponent != null) {
                return primeVideoApplicationComponent;
            }
            Intrinsics.throwUninitializedPropertyAccessException("instance");
            throw null;
        }

        public final void init(@NotNull Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            if (instance != null) {
                return;
            }
            DaggerPrimeVideoApplicationComponent.Builder builder = new DaggerPrimeVideoApplicationComponent.Builder();
            builder.applicationModule = new ApplicationModule(context);
            instance = builder.build();
            ApplicationComponent.Companion companion = ApplicationComponent.Companion;
            PrimeVideoApplicationComponent companion2 = getInstance();
            companion.getClass();
            ApplicationComponent.Companion.instance = companion2;
        }

        public final void setInstance(@NotNull PrimeVideoApplicationComponent primeVideoApplicationComponent) {
            Intrinsics.checkNotNullParameter(primeVideoApplicationComponent, "<set-?>");
            instance = primeVideoApplicationComponent;
        }
    }

    void inject(@NotNull RequestStructureContentProvider requestStructureContentProvider);

    void inject(@NotNull PrimeVideoApplication primeVideoApplication);

    void inject(@NotNull LocaleChangeReceiver localeChangeReceiver);

    void inject(@NotNull UpdateRecommendationsJobService updateRecommendationsJobService);

    @Override // com.amazon.livingroom.di.ApplicationComponent
    @NotNull
    AndroidTvMainActivityComponent.Builder mainActivityComponent();
}
