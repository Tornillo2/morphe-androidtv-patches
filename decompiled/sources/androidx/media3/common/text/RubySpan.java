package androidx.media3.common.text;

import android.os.Bundle;
import androidx.media3.common.util.UnstableApi;
import androidx.media3.common.util.Util;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@UnstableApi
public final class RubySpan implements LanguageFeatureSpan {
    public final int position;
    public final String rubyText;
    public static final String FIELD_TEXT = Util.intToStringMaxRadix(0);
    public static final String FIELD_POSITION = Integer.toString(1, 36);

    public RubySpan(String str, int i) {
        this.rubyText = str;
        this.position = i;
    }

    public static RubySpan fromBundle(Bundle bundle) {
        String string = bundle.getString(FIELD_TEXT);
        string.getClass();
        return new RubySpan(string, bundle.getInt(FIELD_POSITION));
    }

    public Bundle toBundle() {
        Bundle bundle = new Bundle();
        bundle.putString(FIELD_TEXT, this.rubyText);
        bundle.putInt(FIELD_POSITION, this.position);
        return bundle;
    }
}
