package androidx.collection;

import java.util.Iterator;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Unit;
import kotlin.collections.IntIterator;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.markers.KMappedMarker;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public final class SparseArrayKt {

    /* JADX INFO: Add missing generic type declarations: [T] */
    /* JADX INFO: renamed from: androidx.collection.SparseArrayKt$valueIterator$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class C00331<T> implements Iterator<T>, KMappedMarker {
        public final /* synthetic */ SparseArrayCompat<T> $this_valueIterator;
        public int index;

        public C00331(SparseArrayCompat<T> sparseArrayCompat) {
            this.$this_valueIterator = sparseArrayCompat;
        }

        public final int getIndex() {
            return this.index;
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.index < this.$this_valueIterator.size();
        }

        @Override // java.util.Iterator
        public T next() {
            SparseArrayCompat<T> sparseArrayCompat = this.$this_valueIterator;
            int i = this.index;
            this.index = i + 1;
            return sparseArrayCompat.valueAt(i);
        }

        @Override // java.util.Iterator
        public void remove() {
            throw new UnsupportedOperationException("Operation is not supported for read-only collection");
        }

        public final void setIndex(int i) {
            this.index = i;
        }
    }

    public static final <T> boolean contains(@NotNull SparseArrayCompat<T> sparseArrayCompat, int i) {
        Intrinsics.checkNotNullParameter(sparseArrayCompat, "<this>");
        return sparseArrayCompat.containsKey(i);
    }

    public static final <T> void forEach(@NotNull SparseArrayCompat<T> sparseArrayCompat, @NotNull Function2<? super Integer, ? super T, Unit> action) {
        Intrinsics.checkNotNullParameter(sparseArrayCompat, "<this>");
        Intrinsics.checkNotNullParameter(action, "action");
        int size = sparseArrayCompat.size();
        for (int i = 0; i < size; i++) {
            action.invoke(Integer.valueOf(sparseArrayCompat.keyAt(i)), sparseArrayCompat.valueAt(i));
        }
    }

    public static final <T> T getOrDefault(@NotNull SparseArrayCompat<T> sparseArrayCompat, int i, T t) {
        Intrinsics.checkNotNullParameter(sparseArrayCompat, "<this>");
        return sparseArrayCompat.get(i, t);
    }

    public static final <T> T getOrElse(@NotNull SparseArrayCompat<T> sparseArrayCompat, int i, @NotNull Function0<? extends T> defaultValue) {
        Intrinsics.checkNotNullParameter(sparseArrayCompat, "<this>");
        Intrinsics.checkNotNullParameter(defaultValue, "defaultValue");
        T t = sparseArrayCompat.get(i);
        return t == null ? defaultValue.invoke() : t;
    }

    public static final <T> int getSize(@NotNull SparseArrayCompat<T> sparseArrayCompat) {
        Intrinsics.checkNotNullParameter(sparseArrayCompat, "<this>");
        return sparseArrayCompat.size();
    }

    public static final <T> boolean isNotEmpty(@NotNull SparseArrayCompat<T> sparseArrayCompat) {
        Intrinsics.checkNotNullParameter(sparseArrayCompat, "<this>");
        return !sparseArrayCompat.isEmpty();
    }

    @NotNull
    public static final <T> IntIterator keyIterator(@NotNull final SparseArrayCompat<T> sparseArrayCompat) {
        Intrinsics.checkNotNullParameter(sparseArrayCompat, "<this>");
        return new IntIterator() { // from class: androidx.collection.SparseArrayKt.keyIterator.1
            public int index;

            public final int getIndex() {
                return this.index;
            }

            @Override // java.util.Iterator
            public boolean hasNext() {
                return this.index < sparseArrayCompat.size();
            }

            /* JADX WARN: Type inference fix 'apply assigned field type' failed
            java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$PrimitiveArg
            	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
            	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
            	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
            	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
            	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
            	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
            	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
             */
            @Override // kotlin.collections.IntIterator
            public int nextInt() {
                SparseArrayCompat<T> sparseArrayCompat2 = sparseArrayCompat;
                int i = this.index;
                this.index = i + 1;
                return sparseArrayCompat2.keyAt(i);
            }

            public final void setIndex(int i) {
                this.index = i;
            }
        };
    }

    @NotNull
    public static final <T> SparseArrayCompat<T> plus(@NotNull SparseArrayCompat<T> sparseArrayCompat, @NotNull SparseArrayCompat<T> other) {
        Intrinsics.checkNotNullParameter(sparseArrayCompat, "<this>");
        Intrinsics.checkNotNullParameter(other, "other");
        SparseArrayCompat<T> sparseArrayCompat2 = new SparseArrayCompat<>(other.size() + sparseArrayCompat.size());
        sparseArrayCompat2.putAll(sparseArrayCompat);
        sparseArrayCompat2.putAll(other);
        return sparseArrayCompat2;
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Replaced with member function. Remove extension import!")
    public static final /* synthetic */ boolean remove(SparseArrayCompat sparseArrayCompat, int i, Object obj) {
        Intrinsics.checkNotNullParameter(sparseArrayCompat, "<this>");
        return sparseArrayCompat.remove(i, obj);
    }

    public static final <T> void set(@NotNull SparseArrayCompat<T> sparseArrayCompat, int i, T t) {
        Intrinsics.checkNotNullParameter(sparseArrayCompat, "<this>");
        sparseArrayCompat.put(i, t);
    }

    @NotNull
    public static final <T> Iterator<T> valueIterator(@NotNull SparseArrayCompat<T> sparseArrayCompat) {
        Intrinsics.checkNotNullParameter(sparseArrayCompat, "<this>");
        return new C00331(sparseArrayCompat);
    }
}
