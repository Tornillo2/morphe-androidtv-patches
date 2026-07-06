package kotlin.reflect;

import androidx.core.app.RemoteInput$$ExternalSyntheticOutline0;
import java.lang.annotation.Annotation;
import java.lang.reflect.GenericDeclaration;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.ExperimentalStdlibApi;
import kotlin.NotImplementedError;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@ExperimentalStdlibApi
@SourceDebugExtension({"SMAP\nTypesJVM.kt\nKotlin\n*S Kotlin\n*F\n+ 1 TypesJVM.kt\nkotlin/reflect/TypeVariableImpl\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 3 ArraysJVM.kt\nkotlin/collections/ArraysKt__ArraysJVMKt\n*L\n1#1,230:1\n1563#2:231\n1634#2,3:232\n37#3:235\n36#3,3:236\n*S KotlinDebug\n*F\n+ 1 TypesJVM.kt\nkotlin/reflect/TypeVariableImpl\n*L\n116#1:231\n116#1:232,3\n116#1:235\n116#1:236,3\n*E\n"})
public final class TypeVariableImpl implements TypeVariable<GenericDeclaration>, TypeImpl {

    @NotNull
    public final KTypeParameter typeParameter;

    public TypeVariableImpl(@NotNull KTypeParameter typeParameter) {
        Intrinsics.checkNotNullParameter(typeParameter, "typeParameter");
        this.typeParameter = typeParameter;
    }

    public boolean equals(@Nullable Object obj) {
        if (!(obj instanceof TypeVariable) || !Intrinsics.areEqual(this.typeParameter.getName(), ((TypeVariable) obj).getName())) {
            return false;
        }
        getGenericDeclaration();
        throw null;
    }

    @Nullable
    public final <T extends Annotation> T getAnnotation(@NotNull Class<T> annotationClass) {
        Intrinsics.checkNotNullParameter(annotationClass, "annotationClass");
        return null;
    }

    @NotNull
    public final Annotation[] getAnnotations() {
        return new Annotation[0];
    }

    @Override // java.lang.reflect.TypeVariable
    @NotNull
    public Type[] getBounds() {
        List<KType> upperBounds = this.typeParameter.getUpperBounds();
        ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(upperBounds, 10));
        Iterator<T> it = upperBounds.iterator();
        while (it.hasNext()) {
            arrayList.add(TypesJVMKt.computeJavaType((KType) it.next(), true));
        }
        return (Type[]) arrayList.toArray(new Type[0]);
    }

    @NotNull
    public final Annotation[] getDeclaredAnnotations() {
        return new Annotation[0];
    }

    @Override // java.lang.reflect.TypeVariable
    @NotNull
    public GenericDeclaration getGenericDeclaration() {
        throw new NotImplementedError(RemoteInput$$ExternalSyntheticOutline0.m("An operation is not implemented: ", "getGenericDeclaration() is not yet supported for type variables created from KType: " + this.typeParameter));
    }

    @Override // java.lang.reflect.TypeVariable
    @NotNull
    public String getName() {
        return this.typeParameter.getName();
    }

    @Override // java.lang.reflect.Type, kotlin.reflect.TypeImpl
    @NotNull
    public String getTypeName() {
        return this.typeParameter.getName();
    }

    public int hashCode() {
        this.typeParameter.getName().getClass();
        getGenericDeclaration();
        throw null;
    }

    @NotNull
    public String toString() {
        return this.typeParameter.getName();
    }
}
