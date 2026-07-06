package androidx.media3.common;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.media3.common.util.UnstableApi;
import androidx.media3.common.util.Util;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@UnstableApi
public class Label {
    public static final String FIELD_LANGUAGE_INDEX = Util.intToStringMaxRadix(0);
    public static final String FIELD_VALUE_INDEX = Integer.toString(1, 36);

    @Nullable
    public final String language;
    public final String value;

    public Label(@Nullable String str, String str2) {
        this.language = Util.normalizeLanguageCode(str);
        this.value = str2;
    }

    public static Label fromBundle(Bundle bundle) {
        String string = bundle.getString(FIELD_LANGUAGE_INDEX);
        String string2 = bundle.getString(FIELD_VALUE_INDEX);
        string2.getClass();
        return new Label(string, string2);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && getClass() == obj.getClass()) {
            Label label = (Label) obj;
            if (Util.areEqual(this.language, label.language) && Util.areEqual(this.value, label.value)) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        int iHashCode = this.value.hashCode() * 31;
        String str = this.language;
        return iHashCode + (str != null ? str.hashCode() : 0);
    }

    public Bundle toBundle() {
        Bundle bundle = new Bundle();
        String str = this.language;
        if (str != null) {
            bundle.putString(FIELD_LANGUAGE_INDEX, str);
        }
        bundle.putString(FIELD_VALUE_INDEX, this.value);
        return bundle;
    }
}
