package com.amazon.ignitionshared.deeplink;

import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.ignitionshared.deeplink.DeepLinkIntentParser;
import com.amazon.livingroom.di.ApplicationScope;
import javax.inject.Inject;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@ApplicationScope
public class AndroidTVDeepLinkIntentParser implements DeepLinkIntentParser {
    @Inject
    public AndroidTVDeepLinkIntentParser() {
    }

    public static String buildDeepLinkParameters(String str, String str2, String str3) {
        StringBuilder sb = new StringBuilder();
        if (str != null) {
            sb.append(str);
        }
        if (!TextUtils.isEmpty(str2)) {
            sb.append('?');
            sb.append(str2);
        }
        if (!TextUtils.isEmpty(str3)) {
            sb.append('#');
            sb.append(str3);
        }
        String string = sb.toString();
        if (string.isEmpty()) {
            return null;
        }
        return string;
    }

    public static String getFragment(Uri uri) {
        return uri.getEncodedFragment();
    }

    public static String getPath(Uri uri) {
        String encodedPath = uri.getEncodedPath();
        if (TextUtils.isEmpty(encodedPath)) {
            return null;
        }
        return encodedPath.replaceFirst("^/", "");
    }

    public static String getQuery(Uri uri) {
        return uri.getEncodedQuery();
    }

    @Nullable
    public static String parseInternalDeepLink(@NonNull Intent intent) {
        String stringExtra = intent.getStringExtra(DeepLinkIntentParser.CC.getInternalDeepLinkKey());
        if (TextUtils.isEmpty(stringExtra)) {
            return null;
        }
        return stringExtra;
    }

    @Nullable
    public static String parseUriDeepLink(@NonNull Intent intent) {
        Uri data = intent.getData();
        if (data == null || !data.isHierarchical()) {
            return null;
        }
        return buildDeepLinkParameters(getPath(data), data.getEncodedQuery(), data.getEncodedFragment());
    }

    @Override // com.amazon.ignitionshared.deeplink.DeepLinkIntentParser
    @Nullable
    public String parse(@Nullable Intent intent) {
        if (intent == null) {
            return null;
        }
        String uriDeepLink = parseUriDeepLink(intent);
        return uriDeepLink != null ? uriDeepLink : parseInternalDeepLink(intent);
    }
}
