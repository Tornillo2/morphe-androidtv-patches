package com.amazon.livingroom.deviceproperties;

import android.content.ContentResolver;
import android.provider.Settings;
import com.amazon.livingroom.di.ApplicationScope;
import javax.inject.Inject;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@ApplicationScope
public final class DisplayModeProperties {

    @NotNull
    public final ContentResolver contentResolver;

    @Inject
    public DisplayModeProperties(@NotNull ContentResolver contentResolver) {
        Intrinsics.checkNotNullParameter(contentResolver, "contentResolver");
        this.contentResolver = contentResolver;
    }

    public final boolean isFrameRateMatchingEnabled() {
        return Settings.Global.getInt(this.contentResolver, DisplayModePropertiesKt.FIRE_TV_CINEMA_MODE_STATUS, 0) == 1;
    }
}
