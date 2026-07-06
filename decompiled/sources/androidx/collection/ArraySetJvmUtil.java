package androidx.collection;

import java.lang.reflect.Array;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public class ArraySetJvmUtil {
    public static <T> T[] resizeForToArray(T[] tArr, int i) {
        if (tArr.length < i) {
            return (T[]) ((Object[]) Array.newInstance(tArr.getClass().getComponentType(), i));
        }
        if (tArr.length > i) {
            tArr[i] = null;
        }
        return tArr;
    }
}
