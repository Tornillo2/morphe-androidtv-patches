package com.google.android.exoplayer2.source;

import android.os.Bundle;
import android.os.Parcelable;
import androidx.annotation.CheckResult;
import androidx.annotation.Nullable;
import androidx.media3.common.DrmInitData$SchemeData$$ExternalSyntheticOutline0;
import androidx.media3.common.TrackGroup$$ExternalSyntheticOutline0;
import com.google.android.exoplayer2.Bundleable;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.BundleableUtil;
import com.google.android.exoplayer2.util.Log;
import com.google.android.exoplayer2.util.MimeTypes;
import com.google.android.exoplayer2.util.Util;
import com.google.common.collect.ImmutableList;
import java.util.ArrayList;
import java.util.Arrays;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class TrackGroup implements Bundleable {
    public static final String TAG = "TrackGroup";
    public final Format[] formats;
    public int hashCode;
    public final String id;
    public final int length;
    public final int type;
    public static final String FIELD_FORMATS = Util.intToStringMaxRadix(0);
    public static final String FIELD_ID = Integer.toString(1, 36);
    public static final Bundleable.Creator<TrackGroup> CREATOR = new TrackGroup$$ExternalSyntheticLambda0();

    public static /* synthetic */ TrackGroup $r8$lambda$dVnoGSbKWiu48dumLD_6svpA8pI(Bundle bundle) {
        ArrayList parcelableArrayList = bundle.getParcelableArrayList(FIELD_FORMATS);
        return new TrackGroup(bundle.getString(FIELD_ID, ""), (Format[]) (parcelableArrayList == null ? ImmutableList.of() : BundleableUtil.fromBundleList(Format.CREATOR, parcelableArrayList)).toArray(new Format[0]));
    }

    public TrackGroup(Format... formatArr) {
        this("", formatArr);
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

    public Format getFormat(int i) {
        return this.formats[i];
    }

    public int hashCode() {
        if (this.hashCode == 0) {
            this.hashCode = DrmInitData$SchemeData$$ExternalSyntheticOutline0.m(this.id, 527, 31) + Arrays.hashCode(this.formats);
        }
        return this.hashCode;
    }

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

    @Override // com.google.android.exoplayer2.Bundleable
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
