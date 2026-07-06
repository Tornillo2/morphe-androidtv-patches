package kotlin.reflect;

import java.lang.reflect.Type;
import java.lang.reflect.WildcardType;
import java.util.Arrays;
import kotlin.ExperimentalStdlibApi;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@ExperimentalStdlibApi
public final class WildcardTypeImpl implements WildcardType, TypeImpl {

    @NotNull
    public static final Companion Companion = new Companion();

    @NotNull
    public static final WildcardTypeImpl STAR = new WildcardTypeImpl(null, null);

    @Nullable
    public final Type lowerBound;

    @Nullable
    public final Type upperBound;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class Companion {
        public Companion() {
        }

        @NotNull
        public final WildcardTypeImpl getSTAR() {
            return WildcardTypeImpl.STAR;
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
        }
    }

    public WildcardTypeImpl(@Nullable Type type, @Nullable Type type2) {
        this.upperBound = type;
        this.lowerBound = type2;
    }

    public boolean equals(@Nullable Object obj) {
        if (!(obj instanceof WildcardType)) {
            return false;
        }
        WildcardType wildcardType = (WildcardType) obj;
        return Arrays.equals(getUpperBounds(), wildcardType.getUpperBounds()) && Arrays.equals(getLowerBounds(), wildcardType.getLowerBounds());
    }

    @Override // java.lang.reflect.WildcardType
    @NotNull
    public Type[] getLowerBounds() {
        Type type = this.lowerBound;
        return type == null ? new Type[0] : new Type[]{type};
    }

    @Override // java.lang.reflect.Type, kotlin.reflect.TypeImpl
    @NotNull
    public String getTypeName() {
        if (this.lowerBound != null) {
            return "? super " + TypesJVMKt.typeToString(this.lowerBound);
        }
        Type type = this.upperBound;
        if (type == null || Intrinsics.areEqual(type, Object.class)) {
            return "?";
        }
        return "? extends " + TypesJVMKt.typeToString(this.upperBound);
    }

    @Override // java.lang.reflect.WildcardType
    @NotNull
    public Type[] getUpperBounds() {
        Type type = this.upperBound;
        if (type == null) {
            type = Object.class;
        }
        return new Type[]{type};
    }

    public int hashCode() {
        return Arrays.hashCode(getUpperBounds()) ^ Arrays.hashCode(getLowerBounds());
    }

    @NotNull
    public String toString() {
        return getTypeName();
    }
}
