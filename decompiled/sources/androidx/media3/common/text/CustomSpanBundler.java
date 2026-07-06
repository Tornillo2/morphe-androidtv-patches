package androidx.media3.common.text;

import android.os.Bundle;
import android.text.Spannable;
import android.text.Spanned;
import androidx.annotation.Nullable;
import androidx.media3.common.util.Util;
import java.util.ArrayList;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public final class CustomSpanBundler {
    public static final int HORIZONTAL_TEXT_IN_VERTICAL_CONTEXT = 3;
    public static final int RUBY = 1;
    public static final int TEXT_EMPHASIS = 2;
    public static final int UNKNOWN = -1;
    public static final String FIELD_START_INDEX = Util.intToStringMaxRadix(0);
    public static final String FIELD_END_INDEX = Integer.toString(1, 36);
    public static final String FIELD_FLAGS = Integer.toString(2, 36);
    public static final String FIELD_TYPE = Integer.toString(3, 36);
    public static final String FIELD_PARAMS = Integer.toString(4, 36);

    public static ArrayList<Bundle> bundleCustomSpans(Spanned spanned) {
        ArrayList<Bundle> arrayList = new ArrayList<>();
        for (RubySpan rubySpan : (RubySpan[]) spanned.getSpans(0, spanned.length(), RubySpan.class)) {
            arrayList.add(spanToBundle(spanned, rubySpan, 1, rubySpan.toBundle()));
        }
        for (TextEmphasisSpan textEmphasisSpan : (TextEmphasisSpan[]) spanned.getSpans(0, spanned.length(), TextEmphasisSpan.class)) {
            arrayList.add(spanToBundle(spanned, textEmphasisSpan, 2, textEmphasisSpan.toBundle()));
        }
        for (HorizontalTextInVerticalContextSpan horizontalTextInVerticalContextSpan : (HorizontalTextInVerticalContextSpan[]) spanned.getSpans(0, spanned.length(), HorizontalTextInVerticalContextSpan.class)) {
            arrayList.add(spanToBundle(spanned, horizontalTextInVerticalContextSpan, 3, null));
        }
        return arrayList;
    }

    public static Bundle spanToBundle(Spanned spanned, Object obj, int i, @Nullable Bundle bundle) {
        Bundle bundle2 = new Bundle();
        bundle2.putInt(FIELD_START_INDEX, spanned.getSpanStart(obj));
        bundle2.putInt(FIELD_END_INDEX, spanned.getSpanEnd(obj));
        bundle2.putInt(FIELD_FLAGS, spanned.getSpanFlags(obj));
        bundle2.putInt(FIELD_TYPE, i);
        if (bundle != null) {
            bundle2.putBundle(FIELD_PARAMS, bundle);
        }
        return bundle2;
    }

    public static void unbundleAndApplyCustomSpan(Bundle bundle, Spannable spannable) {
        int i = bundle.getInt(FIELD_START_INDEX);
        int i2 = bundle.getInt(FIELD_END_INDEX);
        int i3 = bundle.getInt(FIELD_FLAGS);
        int i4 = bundle.getInt(FIELD_TYPE, -1);
        Bundle bundle2 = bundle.getBundle(FIELD_PARAMS);
        if (i4 == 1) {
            bundle2.getClass();
            spannable.setSpan(RubySpan.fromBundle(bundle2), i, i2, i3);
        } else if (i4 == 2) {
            bundle2.getClass();
            spannable.setSpan(TextEmphasisSpan.fromBundle(bundle2), i, i2, i3);
        } else {
            if (i4 != 3) {
                return;
            }
            spannable.setSpan(new HorizontalTextInVerticalContextSpan(), i, i2, i3);
        }
    }
}
