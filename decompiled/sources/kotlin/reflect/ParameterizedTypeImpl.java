package kotlin.reflect;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.List;
import kotlin.ExperimentalStdlibApi;
import kotlin.collections.ArraysKt___ArraysKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@ExperimentalStdlibApi
@SourceDebugExtension({"SMAP\nTypesJVM.kt\nKotlin\n*S Kotlin\n*F\n+ 1 TypesJVM.kt\nkotlin/reflect/ParameterizedTypeImpl\n+ 2 ArraysJVM.kt\nkotlin/collections/ArraysKt__ArraysJVMKt\n*L\n1#1,230:1\n37#2:231\n36#2,3:232\n*S KotlinDebug\n*F\n+ 1 TypesJVM.kt\nkotlin/reflect/ParameterizedTypeImpl\n*L\n190#1:231\n190#1:232,3\n*E\n"})
public final class ParameterizedTypeImpl implements ParameterizedType, TypeImpl {

    @Nullable
    public final Type ownerType;

    @NotNull
    public final Class<?> rawType;

    @NotNull
    public final Type[] typeArguments;

    public ParameterizedTypeImpl(@NotNull Class<?> rawType, @Nullable Type type, @NotNull List<? extends Type> typeArguments) {
        Intrinsics.checkNotNullParameter(rawType, "rawType");
        Intrinsics.checkNotNullParameter(typeArguments, "typeArguments");
        this.rawType = rawType;
        this.ownerType = type;
        this.typeArguments = (Type[]) typeArguments.toArray(new Type[0]);
    }

    public boolean equals(@Nullable Object obj) {
        if (!(obj instanceof ParameterizedType)) {
            return false;
        }
        ParameterizedType parameterizedType = (ParameterizedType) obj;
        return Intrinsics.areEqual(this.rawType, parameterizedType.getRawType()) && Intrinsics.areEqual(this.ownerType, parameterizedType.getOwnerType()) && Arrays.equals(this.typeArguments, parameterizedType.getActualTypeArguments());
    }

    @Override // java.lang.reflect.ParameterizedType
    @NotNull
    public Type[] getActualTypeArguments() {
        return this.typeArguments;
    }

    @Override // java.lang.reflect.ParameterizedType
    @Nullable
    public Type getOwnerType() {
        return this.ownerType;
    }

    @Override // java.lang.reflect.ParameterizedType
    @NotNull
    public Type getRawType() {
        return this.rawType;
    }

    @Override // java.lang.reflect.Type, kotlin.reflect.TypeImpl
    @NotNull
    public String getTypeName() throws IOException {
        StringBuilder sb = new StringBuilder();
        Type type = this.ownerType;
        if (type != null) {
            sb.append(TypesJVMKt.typeToString(type));
            sb.append("$");
            sb.append(this.rawType.getSimpleName());
        } else {
            sb.append(TypesJVMKt.typeToString(this.rawType));
        }
        Type[] typeArr = this.typeArguments;
        if (!(typeArr.length == 0)) {
            ArraysKt___ArraysKt.joinTo$default(typeArr, sb, (CharSequence) null, "<", ">", 0, (CharSequence) null, ParameterizedTypeImpl$getTypeName$1$1.INSTANCE, 50, (Object) null);
        }
        return sb.toString();
    }

    public int hashCode() {
        int iHashCode = this.rawType.hashCode();
        Type type = this.ownerType;
        return (iHashCode ^ (type != null ? type.hashCode() : 0)) ^ Arrays.hashCode(this.typeArguments);
    }

    @NotNull
    public String toString() {
        return getTypeName();
    }
}
