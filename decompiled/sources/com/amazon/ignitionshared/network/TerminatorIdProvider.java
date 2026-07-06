package com.amazon.ignitionshared.network;

import android.os.Build;
import javax.inject.Inject;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public final class TerminatorIdProvider {
    @Inject
    public TerminatorIdProvider() {
    }

    @NotNull
    public final String getGenericTerminatorId() {
        int i = Build.VERSION.SDK_INT;
        return i < 29 ? "abzzpdkgwfbs" : i == 29 ? "abbfmcmp8tye" : "ab8mt4dd97et";
    }

    @NotNull
    public final String getPearTerminatorId() {
        int i = Build.VERSION.SDK_INT;
        return i < 29 ? "threeplr-abdx8mxr6q3e" : i == 29 ? "threeplr-ab8swqfd2t98" : "threeplr-ab5w7agdemte";
    }
}
