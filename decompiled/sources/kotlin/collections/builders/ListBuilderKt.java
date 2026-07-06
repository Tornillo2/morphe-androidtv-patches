package kotlin.collections.builders;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@SourceDebugExtension({"SMAP\nListBuilder.kt\nKotlin\n*S Kotlin\n*F\n+ 1 ListBuilder.kt\nkotlin/collections/builders/ListBuilderKt\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,718:1\n1#2:719\n*E\n"})
public final class ListBuilderKt {
    @NotNull
    public static final <E> E[] arrayOfUninitializedElements(int i) {
        if (i >= 0) {
            return (E[]) new Object[i];
        }
        throw new IllegalArgumentException("capacity must be non-negative.");
    }

    @NotNull
    public static final <T> T[] copyOfUninitializedElements(@NotNull T[] tArr, int i) {
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        T[] tArr2 = (T[]) Arrays.copyOf(tArr, i);
        Intrinsics.checkNotNullExpressionValue(tArr2, "copyOf(...)");
        return tArr2;
    }

    public static final <E> void resetAt(@NotNull E[] eArr, int i) {
        Intrinsics.checkNotNullParameter(eArr, "<this>");
        eArr[i] = null;
    }

    public static final <E> void resetRange(@NotNull E[] eArr, int i, int i2) {
        Intrinsics.checkNotNullParameter(eArr, "<this>");
        while (i < i2) {
            eArr[i] = null;
            i++;
        }
    }

    public static final <T> boolean subarrayContentEquals(T[] tArr, int i, int i2, List<?> list) {
        if (i2 != list.size()) {
            return false;
        }
        for (int i3 = 0; i3 < i2; i3++) {
            if (!Intrinsics.areEqual(tArr[i + i3], list.get(i3))) {
                return false;
            }
        }
        return true;
    }

    public static final <T> int subarrayContentHashCode(T[] tArr, int i, int i2) {
        int iHashCode = 1;
        for (int i3 = 0; i3 < i2; i3++) {
            T t = tArr[i + i3];
            iHashCode = (iHashCode * 31) + (t != null ? t.hashCode() : 0);
        }
        return iHashCode;
    }

    public static final <T> String subarrayContentToString(T[] tArr, int i, int i2, Collection<? extends T> collection) {
        StringBuilder sb = new StringBuilder((i2 * 3) + 2);
        sb.append("[");
        for (int i3 = 0; i3 < i2; i3++) {
            if (i3 > 0) {
                sb.append(", ");
            }
            T t = tArr[i + i3];
            if (t == collection) {
                sb.append("(this Collection)");
            } else {
                sb.append(t);
            }
        }
        sb.append("]");
        String string = sb.toString();
        Intrinsics.checkNotNullExpressionValue(string, "toString(...)");
        return string;
    }
}
