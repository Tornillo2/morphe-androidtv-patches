package com.amazon.primevideo.di;

import com.amazon.livingroom.di.ActivityScope;
import com.amazon.livingroom.di.MainActivityComponent;
import com.amazon.livingroom.di.MainActivityModule;
import dagger.Subcomponent;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@ActivityScope
@Subcomponent(modules = {MainActivityModule.class, AndroidTvMainActivityModule.class})
public interface AndroidTvMainActivityComponent extends MainActivityComponent {

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @Subcomponent.Builder
    public interface Builder extends MainActivityComponent.Builder {
        @Override // com.amazon.livingroom.di.MainActivityComponent.Builder
        @NotNull
        AndroidTvMainActivityComponent build();

        @Override // com.amazon.livingroom.di.MainActivityComponent.Builder
        @NotNull
        Builder mainActivityModule(@NotNull MainActivityModule mainActivityModule);
    }
}
