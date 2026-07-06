package com.google.android.exoplayer2;

import java.util.HashSet;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class ExoPlayerLibraryInfo {
    public static final boolean ASSERTIONS_ENABLED = true;
    public static final String TAG = "ExoPlayerLib";
    public static final boolean TRACE_ENABLED = true;
    public static final String VERSION = "2.18.7";
    public static final int VERSION_INT = 2018007;
    public static final String VERSION_SLASHY = "AmznExoPlayerLib/2.18.7";
    public static final HashSet<String> registeredModules = new HashSet<>();
    public static String registeredModulesString = "goog.exo.core";

    public static synchronized void registerModule(String str) {
        if (registeredModules.add(str)) {
            registeredModulesString += ", " + str;
        }
    }

    public static synchronized String registeredModules() {
        return registeredModulesString;
    }
}
