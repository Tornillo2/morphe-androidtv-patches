package androidx.collection;

import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@SourceDebugExtension({"SMAP\nObjectList.kt\nKotlin\n*S Kotlin\n*F\n+ 1 ObjectList.kt\nandroidx/collection/ObjectListKt\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 3 ObjectList.kt\nandroidx/collection/MutableObjectList\n*L\n1#1,1618:1\n1#2:1619\n948#3,2:1620\n948#3,2:1622\n948#3,2:1624\n948#3,2:1626\n948#3,2:1628\n948#3,2:1630\n*S KotlinDebug\n*F\n+ 1 ObjectList.kt\nandroidx/collection/ObjectListKt\n*L\n1587#1:1620,2\n1596#1:1622,2\n1597#1:1624,2\n1607#1:1626,2\n1608#1:1628,2\n1609#1:1630,2\n*E\n"})
public final class ObjectListKt {

    @NotNull
    public static final Object[] EmptyArray = new Object[0];

    @NotNull
    public static final ObjectList<Object> EmptyObjectList = new MutableObjectList(0);

    public static final void checkIndex(List<?> list, int i) {
        int size = list.size();
        if (i < 0 || i >= size) {
            throw new IndexOutOfBoundsException(ObjectListKt$$ExternalSyntheticOutline0.m("Index ", i, " is out of bounds. The list has ", size, " elements."));
        }
    }

    public static final void checkSubIndex(List<?> list, int i, int i2) {
        int size = list.size();
        if (i > i2) {
            throw new IllegalArgumentException(ObjectListKt$$ExternalSyntheticOutline0.m("Indices are out of order. fromIndex (", i, ") is greater than toIndex (", i2, ")."));
        }
        if (i < 0) {
            throw new IndexOutOfBoundsException(ObjectListKt$$ExternalSyntheticOutline1.m("fromIndex (", i, ") is less than 0."));
        }
        if (i2 <= size) {
            return;
        }
        throw new IndexOutOfBoundsException("toIndex (" + i2 + ") is more than than the list size (" + size + ')');
    }

    @NotNull
    public static final <E> ObjectList<E> emptyObjectList() {
        ObjectList<E> objectList = (ObjectList<E>) EmptyObjectList;
        Intrinsics.checkNotNull(objectList, "null cannot be cast to non-null type androidx.collection.ObjectList<E of androidx.collection.ObjectListKt.emptyObjectList>");
        return objectList;
    }

    @NotNull
    public static final <E> MutableObjectList<E> mutableObjectListOf() {
        return new MutableObjectList<>(0, 1, null);
    }

    @NotNull
    public static final <E> ObjectList<E> objectListOf() {
        ObjectList<E> objectList = (ObjectList<E>) EmptyObjectList;
        Intrinsics.checkNotNull(objectList, "null cannot be cast to non-null type androidx.collection.ObjectList<E of androidx.collection.ObjectListKt.objectListOf>");
        return objectList;
    }

    @NotNull
    public static final <E> MutableObjectList<E> mutableObjectListOf(E e) {
        MutableObjectList<E> mutableObjectList = new MutableObjectList<>(1);
        mutableObjectList.add(e);
        return mutableObjectList;
    }

    @NotNull
    public static final <E> ObjectList<E> objectListOf(E e) {
        return mutableObjectListOf(e);
    }

    @NotNull
    public static final <E> ObjectList<E> objectListOf(E e, E e2) {
        return mutableObjectListOf(e, e2);
    }

    @NotNull
    public static final <E> ObjectList<E> objectListOf(E e, E e2, E e3) {
        return mutableObjectListOf(e, e2, e3);
    }

    @NotNull
    public static final <E> MutableObjectList<E> mutableObjectListOf(E e, E e2) {
        MutableObjectList<E> mutableObjectList = new MutableObjectList<>(2);
        mutableObjectList.add(e);
        mutableObjectList.add(e2);
        return mutableObjectList;
    }

    @NotNull
    public static final <E> ObjectList<E> objectListOf(@NotNull E... elements) {
        Intrinsics.checkNotNullParameter(elements, "elements");
        MutableObjectList mutableObjectList = new MutableObjectList(elements.length);
        mutableObjectList.plusAssign((Object[]) elements);
        return mutableObjectList;
    }

    @NotNull
    public static final <E> MutableObjectList<E> mutableObjectListOf(E e, E e2, E e3) {
        MutableObjectList<E> mutableObjectList = new MutableObjectList<>(3);
        mutableObjectList.add(e);
        mutableObjectList.add(e2);
        mutableObjectList.add(e3);
        return mutableObjectList;
    }

    @NotNull
    public static final <E> MutableObjectList<E> mutableObjectListOf(@NotNull E... elements) {
        Intrinsics.checkNotNullParameter(elements, "elements");
        MutableObjectList<E> mutableObjectList = new MutableObjectList<>(elements.length);
        mutableObjectList.plusAssign((Object[]) elements);
        return mutableObjectList;
    }
}
