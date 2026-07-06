package androidx.media3.common;

import android.os.Bundle;
import android.os.Parcelable;
import androidx.annotation.CheckResult;
import androidx.annotation.Nullable;
import androidx.media3.common.Bundleable;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.BundleCollectionUtil;
import androidx.media3.common.util.Log;
import androidx.media3.common.util.UnstableApi;
import androidx.media3.common.util.Util;
import com.google.common.collect.ImmutableList;
import java.util.ArrayList;
import java.util.Arrays;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public final class TrackGroup implements Bundleable {
    public static final String TAG = "TrackGroup";
    public final Format[] formats;
    public int hashCode;

    @UnstableApi
    public final String id;

    @UnstableApi
    public final int length;

    @UnstableApi
    public final int type;
    public static final String FIELD_FORMATS = Util.intToStringMaxRadix(0);
    public static final String FIELD_ID = Integer.toString(1, 36);

    @UnstableApi
    @Deprecated
    public static final Bundleable.Creator<TrackGroup> CREATOR = new TrackGroup$$ExternalSyntheticLambda2();

    @UnstableApi
    public TrackGroup(Format... formatArr) {
        this("", formatArr);
    }

    @UnstableApi
    public static TrackGroup fromBundle(Bundle bundle) {
        ArrayList parcelableArrayList = bundle.getParcelableArrayList(FIELD_FORMATS);
        return new TrackGroup(bundle.getString(FIELD_ID, ""), (Format[]) (parcelableArrayList == null ? ImmutableList.of() : BundleCollectionUtil.fromBundleList(new TrackGroup$$ExternalSyntheticLambda1(), parcelableArrayList)).toArray(new Format[0]));
    }

    public static void logErrorMessage(String str, @Nullable String str2, @Nullable String str3, int i) {
        StringBuilder sbM = TrackGroup$$ExternalSyntheticOutline0.m("Different ", str, " combined in one TrackGroup: '", str2, "' (track 0) and '");
        sbM.append(str3);
        sbM.append("' (track ");
        sbM.append(i);
        sbM.append(")");
        Log.e("TrackGroup", "", new IllegalStateException(sbM.toString()));
    }

    public static String normalizeLanguage(@Nullable String str) {
        return (str == null || str.equals("und")) ? "" : str;
    }

    public static int normalizeRoleFlags(int i) {
        return i | 16384;
    }

    @CheckResult
    @UnstableApi
    public TrackGroup copyWithId(String str) {
        return new TrackGroup(str, this.formats);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && TrackGroup.class == obj.getClass()) {
            TrackGroup trackGroup = (TrackGroup) obj;
            if (this.id.equals(trackGroup.id) && Arrays.equals(this.formats, trackGroup.formats)) {
                return true;
            }
        }
        return false;
    }

    @UnstableApi
    public Format getFormat(int i) {
        return this.formats[i];
    }

    public int hashCode() {
        if (this.hashCode == 0) {
            this.hashCode = DrmInitData$SchemeData$$ExternalSyntheticOutline0.m(this.id, 527, 31) + Arrays.hashCode(this.formats);
        }
        return this.hashCode;
    }

    @UnstableApi
    public int indexOf(Format format) {
        int i = 0;
        while (true) {
            Format[] formatArr = this.formats;
            if (i >= formatArr.length) {
                return -1;
            }
            if (format == formatArr[i]) {
                return i;
            }
            i++;
        }
    }

    @Override // androidx.media3.common.Bundleable
    @UnstableApi
    public Bundle toBundle() {
        Bundle bundle = new Bundle();
        ArrayList<? extends Parcelable> arrayList = new ArrayList<>(this.formats.length);
        for (Format format : this.formats) {
            arrayList.add(format.toBundle(true));
        }
        bundle.putParcelableArrayList(FIELD_FORMATS, arrayList);
        bundle.putString(FIELD_ID, this.id);
        return bundle;
    }

    public final void verifyCorrectness() {
        String strNormalizeLanguage = normalizeLanguage(this.formats[0].language);
        int i = this.formats[0].roleFlags | 16384;
        int i2 = 1;
        while (true) {
            Format[] formatArr = this.formats;
            if (i2 >= formatArr.length) {
                return;
            }
            if (!strNormalizeLanguage.equals(normalizeLanguage(formatArr[i2].language))) {
                Format[] formatArr2 = this.formats;
                logErrorMessage("languages", formatArr2[0].language, formatArr2[i2].language, i2);
                return;
            } else {
                Format[] formatArr3 = this.formats;
                if (i != (formatArr3[i2].roleFlags | 16384)) {
                    logErrorMessage("role flags", Integer.toBinaryString(formatArr3[0].roleFlags), Integer.toBinaryString(this.formats[i2].roleFlags), i2);
                    return;
                }
                i2++;
            }
        }
    }

    @UnstableApi
    public TrackGroup(String str, Format... formatArr) {
        Assertions.checkArgument(formatArr.length > 0);
        this.id = str;
        this.formats = formatArr;
        this.length = formatArr.length;
        int trackType = MimeTypes.getTrackType(formatArr[0].sampleMimeType);
        this.type = trackType == -1 ? MimeTypes.getTrackType(formatArr[0].containerMimeType) : trackType;
        verifyCorrectness();
    }
}
