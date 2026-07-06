package androidx.media3.common.text;

import android.os.Bundle;
import androidx.media3.common.Bundleable;
import androidx.media3.common.util.BundleCollectionUtil;
import androidx.media3.common.util.UnstableApi;
import androidx.media3.common.util.Util;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.RegularImmutableList;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public final class CueGroup implements Bundleable {
    public final ImmutableList<Cue> cues;

    @UnstableApi
    public final long presentationTimeUs;

    @UnstableApi
    public static final CueGroup EMPTY_TIME_ZERO = new CueGroup(ImmutableList.of(), 0);
    public static final String FIELD_CUES = Util.intToStringMaxRadix(0);
    public static final String FIELD_PRESENTATION_TIME_US = Integer.toString(1, 36);

    @UnstableApi
    @Deprecated
    public static final Bundleable.Creator<CueGroup> CREATOR = new CueGroup$$ExternalSyntheticLambda0();

    @UnstableApi
    public CueGroup(List<Cue> list, long j) {
        this.cues = ImmutableList.copyOf((Collection) list);
        this.presentationTimeUs = j;
    }

    public static ImmutableList<Cue> filterOutBitmapCues(List<Cue> list) {
        ImmutableList.Builder builder = ImmutableList.builder();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).bitmap == null) {
                builder.add(list.get(i));
            }
        }
        return builder.build();
    }

    @UnstableApi
    public static CueGroup fromBundle(Bundle bundle) {
        ArrayList parcelableArrayList = bundle.getParcelableArrayList(FIELD_CUES);
        return new CueGroup(parcelableArrayList == null ? RegularImmutableList.EMPTY : BundleCollectionUtil.fromBundleList(new CueGroup$$ExternalSyntheticLambda1(), parcelableArrayList), bundle.getLong(FIELD_PRESENTATION_TIME_US));
    }

    @Override // androidx.media3.common.Bundleable
    @UnstableApi
    public Bundle toBundle() {
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList(FIELD_CUES, BundleCollectionUtil.toBundleArrayList(filterOutBitmapCues(this.cues), new CueGroup$$ExternalSyntheticLambda2()));
        bundle.putLong(FIELD_PRESENTATION_TIME_US, this.presentationTimeUs);
        return bundle;
    }
}
