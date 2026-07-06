package androidx.core.content;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.minerva.identifiers.schemaid.SchemaId;
import com.google.common.net.MediaType;
import java.util.ArrayList;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public final class MimeTypeFilter {
    public static boolean matches(@Nullable String str, @NonNull String str2) {
        if (str == null) {
            return false;
        }
        return mimeTypeAgainstFilter(str.split(SchemaId.METRIC_SCHEMA_ID_DELIMITER), str2.split(SchemaId.METRIC_SCHEMA_ID_DELIMITER));
    }

    @NonNull
    public static String[] matchesMany(@Nullable String[] strArr, @NonNull String str) {
        if (strArr == null) {
            return new String[0];
        }
        ArrayList arrayList = new ArrayList();
        String[] strArrSplit = str.split(SchemaId.METRIC_SCHEMA_ID_DELIMITER);
        for (String str2 : strArr) {
            if (mimeTypeAgainstFilter(str2.split(SchemaId.METRIC_SCHEMA_ID_DELIMITER), strArrSplit)) {
                arrayList.add(str2);
            }
        }
        return (String[]) arrayList.toArray(new String[arrayList.size()]);
    }

    public static boolean mimeTypeAgainstFilter(@NonNull String[] strArr, @NonNull String[] strArr2) {
        if (strArr2.length != 2) {
            throw new IllegalArgumentException("Ill-formatted MIME type filter. Must be type/subtype.");
        }
        if (strArr2[0].isEmpty() || strArr2[1].isEmpty()) {
            throw new IllegalArgumentException("Ill-formatted MIME type filter. Type or subtype empty.");
        }
        if (strArr.length != 2) {
            return false;
        }
        if (MediaType.WILDCARD.equals(strArr2[0]) || strArr2[0].equals(strArr[0])) {
            return MediaType.WILDCARD.equals(strArr2[1]) || strArr2[1].equals(strArr[1]);
        }
        return false;
    }

    @Nullable
    public static String matches(@Nullable String str, @NonNull String[] strArr) {
        if (str == null) {
            return null;
        }
        String[] strArrSplit = str.split(SchemaId.METRIC_SCHEMA_ID_DELIMITER);
        for (String str2 : strArr) {
            if (mimeTypeAgainstFilter(strArrSplit, str2.split(SchemaId.METRIC_SCHEMA_ID_DELIMITER))) {
                return str2;
            }
        }
        return null;
    }

    @Nullable
    public static String matches(@Nullable String[] strArr, @NonNull String str) {
        if (strArr == null) {
            return null;
        }
        String[] strArrSplit = str.split(SchemaId.METRIC_SCHEMA_ID_DELIMITER);
        for (String str2 : strArr) {
            if (mimeTypeAgainstFilter(str2.split(SchemaId.METRIC_SCHEMA_ID_DELIMITER), strArrSplit)) {
                return str2;
            }
        }
        return null;
    }
}
