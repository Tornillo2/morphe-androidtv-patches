package com.amazon.livingroom.deviceproperties.dtid;

import android.content.SharedPreferences;
import com.amazon.livingroom.di.ApplicationScope;
import com.amazon.livingroom.di.Names;
import com.amazon.reporting.Log;
import com.google.android.datatransport.runtime.scheduling.persistence.EventStoreConfig;
import javax.inject.Inject;
import javax.inject.Named;
import kotlin.Pair;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@ApplicationScope
@SourceDebugExtension({"SMAP\nDtidCache.kt\nKotlin\n*S Kotlin\n*F\n+ 1 DtidCache.kt\ncom/amazon/livingroom/deviceproperties/dtid/DtidCache\n+ 2 SharedPreferences.kt\nandroidx/core/content/SharedPreferencesKt\n*L\n1#1,43:1\n41#2,12:44\n*S KotlinDebug\n*F\n+ 1 DtidCache.kt\ncom/amazon/livingroom/deviceproperties/dtid/DtidCache\n*L\n29#1:44,12\n*E\n"})
public final class DtidCache {

    @NotNull
    public static final String CACHED_DTID_KEY = "DEVICE_TYPE_ID";

    @NotNull
    public static final String CACHED_DTID_TIMESTAMP = "CACHED_DTID_TIMESTAMP";

    @NotNull
    public static final Companion Companion = new Companion();
    public static final int SEVEN_DAYS_IN_MILLIS = 604800000;
    public static final String TAG = "DtidCache";

    @NotNull
    public final SharedPreferences sharedPreferences;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class Companion {
        public Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
        }
    }

    @Inject
    public DtidCache(@Named(Names.DEVICE_INFO) @NotNull SharedPreferences sharedPreferences) {
        Intrinsics.checkNotNullParameter(sharedPreferences, "sharedPreferences");
        this.sharedPreferences = sharedPreferences;
    }

    public final synchronized void cacheDtid(@NotNull String dtid) {
        Intrinsics.checkNotNullParameter(dtid, "dtid");
        long jCurrentTimeMillis = System.currentTimeMillis();
        Log.i(TAG, "Caching DTID: \"" + dtid + "\", timestamp: " + jCurrentTimeMillis + " ms");
        SharedPreferences.Editor editorEdit = this.sharedPreferences.edit();
        editorEdit.putString(CACHED_DTID_KEY, dtid);
        editorEdit.putLong(CACHED_DTID_TIMESTAMP, jCurrentTimeMillis);
        editorEdit.apply();
    }

    @NotNull
    public final synchronized Pair<String, Boolean> getCachedDtid() {
        String string = this.sharedPreferences.getString(CACHED_DTID_KEY, null);
        if (string == null) {
            return new Pair<>(null, Boolean.FALSE);
        }
        long j = this.sharedPreferences.getLong(CACHED_DTID_TIMESTAMP, 0L);
        boolean z = System.currentTimeMillis() - j >= EventStoreConfig.DURATION_ONE_WEEK_MS;
        Log.i(TAG, "Get cached DTID :\"" + string + "\", cachedTimestamp: " + j + ", isStale: " + z);
        return new Pair<>(string, Boolean.valueOf(z));
    }
}
