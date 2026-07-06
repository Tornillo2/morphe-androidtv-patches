package androidx.core.content;

import android.content.ContentValues;
import kotlin.Pair;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public final class ContentValuesKt {
    /* JADX WARN: Multi-variable type inference failed */
    @NotNull
    public static final ContentValues contentValuesOf(@NotNull Pair<String, ? extends Object>... pairArr) {
        ContentValues contentValues = new ContentValues(pairArr.length);
        for (Pair<String, ? extends Object> pair : pairArr) {
            String str = pair.first;
            B b = pair.second;
            if (b == 0) {
                contentValues.putNull(str);
            } else if (b instanceof String) {
                contentValues.put(str, (String) b);
            } else if (b instanceof Integer) {
                contentValues.put(str, (Integer) b);
            } else if (b instanceof Long) {
                contentValues.put(str, (Long) b);
            } else if (b instanceof Boolean) {
                contentValues.put(str, (Boolean) b);
            } else if (b instanceof Float) {
                contentValues.put(str, (Float) b);
            } else if (b instanceof Double) {
                contentValues.put(str, (Double) b);
            } else if (b instanceof byte[]) {
                contentValues.put(str, (byte[]) b);
            } else if (b instanceof Byte) {
                contentValues.put(str, (Byte) b);
            } else {
                if (!(b instanceof Short)) {
                    throw new IllegalArgumentException("Illegal value type " + b.getClass().getCanonicalName() + " for key \"" + str + '\"');
                }
                contentValues.put(str, (Short) b);
            }
        }
        return contentValues;
    }
}
