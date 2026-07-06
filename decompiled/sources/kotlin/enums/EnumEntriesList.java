package kotlin.enums;

import java.io.Serializable;
import java.lang.Enum;
import kotlin.SinceKotlin;
import kotlin.collections.AbstractList;
import kotlin.collections.ArraysKt___ArraysKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@SinceKotlin(version = "1.8")
public final class EnumEntriesList<T extends Enum<T>> extends AbstractList<T> implements EnumEntries<T>, Serializable {

    @NotNull
    public final T[] entries;

    public EnumEntriesList(@NotNull T[] entries) {
        Intrinsics.checkNotNullParameter(entries, "entries");
        this.entries = entries;
    }

    @Override // kotlin.collections.AbstractCollection, java.util.Collection
    public final /* bridge */ boolean contains(Object obj) {
        if (obj instanceof Enum) {
            return contains((Enum) obj);
        }
        return false;
    }

    @Override // kotlin.collections.AbstractList, kotlin.collections.AbstractCollection
    public int getSize() {
        return this.entries.length;
    }

    @Override // kotlin.collections.AbstractList, java.util.List
    public final /* bridge */ int indexOf(Object obj) {
        if (obj instanceof Enum) {
            return indexOf((Enum) obj);
        }
        return -1;
    }

    @Override // kotlin.collections.AbstractList, java.util.List
    public final int lastIndexOf(Object obj) {
        if (obj instanceof Enum) {
            return indexOf(obj);
        }
        return -1;
    }

    public final Object writeReplace() {
        return new EnumEntriesSerializationProxy(this.entries);
    }

    public boolean contains(@NotNull T element) {
        Intrinsics.checkNotNullParameter(element, "element");
        return ((Enum) ArraysKt___ArraysKt.getOrNull(this.entries, element.ordinal())) == element;
    }

    @Override // kotlin.collections.AbstractList, java.util.List
    @NotNull
    public T get(int i) {
        AbstractList.Companion.checkElementIndex$kotlin_stdlib(i, this.entries.length);
        return this.entries[i];
    }

    public int indexOf(@NotNull T element) {
        Intrinsics.checkNotNullParameter(element, "element");
        int iOrdinal = element.ordinal();
        if (((Enum) ArraysKt___ArraysKt.getOrNull(this.entries, iOrdinal)) == element) {
            return iOrdinal;
        }
        return -1;
    }

    public int lastIndexOf(@NotNull T element) {
        Intrinsics.checkNotNullParameter(element, "element");
        return indexOf((Object) element);
    }
}
