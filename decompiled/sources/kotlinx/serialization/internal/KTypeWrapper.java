package kotlinx.serialization.internal;

import java.lang.annotation.Annotation;
import java.util.List;
import kotlin.jvm.JvmClassMappingKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KClass;
import kotlin.reflect.KClassifier;
import kotlin.reflect.KType;
import kotlin.reflect.KTypeProjection;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
public final class KTypeWrapper implements KType {

    @NotNull
    public final KType origin;

    public KTypeWrapper(@NotNull KType origin) {
        Intrinsics.checkNotNullParameter(origin, "origin");
        this.origin = origin;
    }

    public boolean equals(@Nullable Object obj) {
        if (obj == null) {
            return false;
        }
        KType kType = this.origin;
        KTypeWrapper kTypeWrapper = obj instanceof KTypeWrapper ? (KTypeWrapper) obj : null;
        if (!Intrinsics.areEqual(kType, kTypeWrapper != null ? kTypeWrapper.origin : null)) {
            return false;
        }
        KClassifier classifier = this.origin.getClassifier();
        if (classifier instanceof KClass) {
            KType kType2 = obj instanceof KType ? (KType) obj : null;
            KClassifier classifier2 = kType2 != null ? kType2.getClassifier() : null;
            if (classifier2 != null && (classifier2 instanceof KClass)) {
                return JvmClassMappingKt.getJavaClass((KClass) classifier).equals(JvmClassMappingKt.getJavaClass((KClass) classifier2));
            }
        }
        return false;
    }

    @Override // kotlin.reflect.KAnnotatedElement
    @NotNull
    public List<Annotation> getAnnotations() {
        return this.origin.getAnnotations();
    }

    @Override // kotlin.reflect.KType
    @NotNull
    public List<KTypeProjection> getArguments() {
        return this.origin.getArguments();
    }

    @Override // kotlin.reflect.KType
    @Nullable
    public KClassifier getClassifier() {
        return this.origin.getClassifier();
    }

    public int hashCode() {
        return this.origin.hashCode();
    }

    @Override // kotlin.reflect.KType
    public boolean isMarkedNullable() {
        return this.origin.isMarkedNullable();
    }

    @NotNull
    public String toString() {
        return "KTypeWrapper: " + this.origin;
    }
}
