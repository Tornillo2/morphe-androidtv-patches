package kotlin.reflect;

import java.lang.reflect.GenericArrayType;
import java.lang.reflect.Type;
import kotlin.ExperimentalStdlibApi;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@ExperimentalStdlibApi
public final class GenericArrayTypeImpl implements GenericArrayType, TypeImpl {

    @NotNull
    public final Type elementType;

    public GenericArrayTypeImpl(@NotNull Type elementType) {
        Intrinsics.checkNotNullParameter(elementType, "elementType");
        this.elementType = elementType;
    }

    public boolean equals(@Nullable Object obj) {
        return (obj instanceof GenericArrayType) && Intrinsics.areEqual(this.elementType, ((GenericArrayType) obj).getGenericComponentType());
    }

    @Override // java.lang.reflect.GenericArrayType
    @NotNull
    public Type getGenericComponentType() {
        return this.elementType;
    }

    @Override // java.lang.reflect.Type, kotlin.reflect.TypeImpl
    @NotNull
    public String getTypeName() {
        return TypesJVMKt.typeToString(this.elementType) + "[]";
    }

    public int hashCode() {
        return this.elementType.hashCode();
    }

    @NotNull
    public String toString() {
        return getTypeName();
    }
}
