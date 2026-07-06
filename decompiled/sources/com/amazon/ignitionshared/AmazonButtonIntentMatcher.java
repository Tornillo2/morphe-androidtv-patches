package com.amazon.ignitionshared;

import android.content.Intent;
import j$.util.Objects;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public class AmazonButtonIntentMatcher {
    public static final String AMAZON_BUTTON_ACTION = "com.amazon.amazonvideo.livingroom.AMAZON_BUTTON";

    public static boolean match(Intent intent) {
        return Objects.equals(intent.getAction(), AMAZON_BUTTON_ACTION);
    }
}
