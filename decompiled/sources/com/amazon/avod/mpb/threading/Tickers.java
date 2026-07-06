package com.amazon.avod.mpb.threading;

import com.google.common.base.Ticker;
import kotlin.jvm.JvmStatic;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public final class Tickers {

    @NotNull
    public static final Tickers INSTANCE = new Tickers();

    @NotNull
    public static final Ticker ANDROID_TICKER = new Tickers$ANDROID_TICKER$1();

    @JvmStatic
    @NotNull
    public static final Ticker androidTicker() {
        return ANDROID_TICKER;
    }
}
