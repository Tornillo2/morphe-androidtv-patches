package androidx.tvprovider.media.tv;

import android.media.tv.TvContentRating;
import android.text.TextUtils;
import android.util.Log;
import androidx.annotation.RestrictTo;
import java.util.ArrayList;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
public class TvContractUtils {
    public static final boolean DEBUG = false;
    public static final String DELIMITER = ",";
    public static final TvContentRating[] EMPTY = new TvContentRating[0];
    public static final String TAG = "TvContractUtils";

    public static String audioLanguagesToString(String[] strArr) {
        if (strArr == null || strArr.length == 0) {
            return null;
        }
        StringBuilder sb = new StringBuilder(strArr[0]);
        for (int i = 1; i < strArr.length; i++) {
            sb.append(",");
            sb.append(strArr[i]);
        }
        return sb.toString();
    }

    public static String contentRatingsToString(TvContentRating[] tvContentRatingArr) {
        if (tvContentRatingArr == null || tvContentRatingArr.length == 0) {
            return null;
        }
        StringBuilder sb = new StringBuilder(tvContentRatingArr[0].flattenToString());
        for (int i = 1; i < tvContentRatingArr.length; i++) {
            sb.append(",");
            sb.append(tvContentRatingArr[i].flattenToString());
        }
        return sb.toString();
    }

    public static String[] stringToAudioLanguages(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        return str.split("\\s*,\\s*");
    }

    public static TvContentRating[] stringToContentRatings(String str) {
        if (TextUtils.isEmpty(str)) {
            return EMPTY;
        }
        String[] strArrSplit = str.split("\\s*,\\s*", -1);
        ArrayList arrayList = new ArrayList(strArrSplit.length);
        for (String str2 : strArrSplit) {
            try {
                arrayList.add(TvContentRating.unflattenFromString(str2));
            } catch (IllegalArgumentException e) {
                Log.w(TAG, "Can't parse the content rating: '" + str2 + "', skipping", e);
            }
        }
        return arrayList.size() == 0 ? EMPTY : (TvContentRating[]) arrayList.toArray(new TvContentRating[arrayList.size()]);
    }
}
