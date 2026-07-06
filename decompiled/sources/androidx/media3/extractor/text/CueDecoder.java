package androidx.media3.extractor.text;

import android.os.Bundle;
import android.os.Parcel;
import androidx.media3.common.text.CueGroup$$ExternalSyntheticLambda1;
import androidx.media3.common.util.BundleCollectionUtil;
import androidx.media3.common.util.UnstableApi;
import java.util.ArrayList;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@UnstableApi
public final class CueDecoder {
    public static final String BUNDLE_FIELD_CUES = "c";
    public static final String BUNDLE_FIELD_DURATION_US = "d";

    public CuesWithTiming decode(long j, byte[] bArr) {
        return decode(j, bArr, 0, bArr.length);
    }

    public CuesWithTiming decode(long j, byte[] bArr, int i, int i2) {
        Parcel parcelObtain = Parcel.obtain();
        parcelObtain.unmarshall(bArr, i, i2);
        parcelObtain.setDataPosition(0);
        Bundle bundle = parcelObtain.readBundle(Bundle.class.getClassLoader());
        parcelObtain.recycle();
        ArrayList parcelableArrayList = bundle.getParcelableArrayList("c");
        parcelableArrayList.getClass();
        return new CuesWithTiming(BundleCollectionUtil.fromBundleList(new CueGroup$$ExternalSyntheticLambda1(), parcelableArrayList), j, bundle.getLong("d"));
    }
}
