package kotlinx.coroutines.internal;

import java.util.ArrayList;
import kotlin.Unit;
import kotlin.jvm.JvmInline;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
@JvmInline
public final class InlineList<E> {

    @Nullable
    public final Object holder;

    public /* synthetic */ InlineList(Object obj) {
        this.holder = obj;
    }

    /* JADX INFO: renamed from: box-impl, reason: not valid java name */
    public static final /* synthetic */ InlineList m2119boximpl(Object obj) {
        return new InlineList(obj);
    }

    /* JADX INFO: renamed from: constructor-impl$default, reason: not valid java name */
    public static Object m2121constructorimpl$default(Object obj, int i, DefaultConstructorMarker defaultConstructorMarker) {
        if ((i & 1) != 0) {
            return null;
        }
        return obj;
    }

    /* JADX INFO: renamed from: equals-impl, reason: not valid java name */
    public static boolean m2122equalsimpl(Object obj, Object obj2) {
        return (obj2 instanceof InlineList) && Intrinsics.areEqual(obj, ((InlineList) obj2).holder);
    }

    /* JADX INFO: renamed from: equals-impl0, reason: not valid java name */
    public static final boolean m2123equalsimpl0(Object obj, Object obj2) {
        return Intrinsics.areEqual(obj, obj2);
    }

    /* JADX INFO: renamed from: forEachReversed-impl, reason: not valid java name */
    public static final void m2124forEachReversedimpl(Object obj, @NotNull Function1<? super E, Unit> function1) {
        if (obj == null) {
            return;
        }
        if (!(obj instanceof ArrayList)) {
            function1.invoke(obj);
            return;
        }
        ArrayList arrayList = (ArrayList) obj;
        int size = arrayList.size();
        while (true) {
            size--;
            if (-1 >= size) {
                return;
            } else {
                function1.invoke((Object) arrayList.get(size));
            }
        }
    }

    /* JADX INFO: renamed from: hashCode-impl, reason: not valid java name */
    public static int m2125hashCodeimpl(Object obj) {
        if (obj == null) {
            return 0;
        }
        return obj.hashCode();
    }

    @NotNull
    /* JADX INFO: renamed from: plus-FjFbRPM, reason: not valid java name */
    public static final Object m2126plusFjFbRPM(Object obj, E e) {
        if (obj == null) {
            return e;
        }
        if (obj instanceof ArrayList) {
            ((ArrayList) obj).add(e);
            return obj;
        }
        ArrayList arrayList = new ArrayList(4);
        arrayList.add(obj);
        arrayList.add(e);
        return arrayList;
    }

    /* JADX INFO: renamed from: toString-impl, reason: not valid java name */
    public static String m2127toStringimpl(Object obj) {
        return "InlineList(holder=" + obj + ')';
    }

    public boolean equals(Object obj) {
        return m2122equalsimpl(this.holder, obj);
    }

    public int hashCode() {
        return m2125hashCodeimpl(this.holder);
    }

    public String toString() {
        return m2127toStringimpl(this.holder);
    }

    /* JADX INFO: renamed from: unbox-impl, reason: not valid java name */
    public final /* synthetic */ Object m2128unboximpl() {
        return this.holder;
    }

    @NotNull
    /* JADX INFO: renamed from: constructor-impl, reason: not valid java name */
    public static <E> Object m2120constructorimpl(@Nullable Object obj) {
        return obj;
    }
}
