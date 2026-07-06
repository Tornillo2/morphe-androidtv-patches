package kotlin.jvm.internal;

import androidx.concurrent.futures.AbstractResolvableFuture$$ExternalSyntheticOutline1;
import com.google.common.net.MediaType;
import java.lang.annotation.Annotation;
import java.util.List;
import kotlin.NoWhenBranchMatchedException;
import kotlin.SinceKotlin;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.collections.EmptyList;
import kotlin.jvm.JvmClassMappingKt;
import kotlin.jvm.functions.Function1;
import kotlin.reflect.KClass;
import kotlin.reflect.KClassifier;
import kotlin.reflect.KType;
import kotlin.reflect.KTypeProjection;
import kotlin.reflect.KVariance;
import kotlinx.serialization.internal.CollectionDescriptorsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@SinceKotlin(version = "1.4")
public final class TypeReference implements KType {

    @NotNull
    public static final Companion Companion = new Companion();
    public static final int IS_MARKED_NULLABLE = 1;
    public static final int IS_MUTABLE_COLLECTION_TYPE = 2;
    public static final int IS_NOTHING_TYPE = 4;

    @NotNull
    public final List<KTypeProjection> arguments;

    @NotNull
    public final KClassifier classifier;
    public final int flags;

    @Nullable
    public final KType platformTypeUpperBound;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class Companion {
        public Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[KVariance.values().length];
            try {
                iArr[KVariance.INVARIANT.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[KVariance.IN.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[KVariance.OUT.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    @SinceKotlin(version = "1.6")
    public TypeReference(@NotNull KClassifier classifier, @NotNull List<KTypeProjection> arguments, @Nullable KType kType, int i) {
        Intrinsics.checkNotNullParameter(classifier, "classifier");
        Intrinsics.checkNotNullParameter(arguments, "arguments");
        this.classifier = classifier;
        this.arguments = arguments;
        this.platformTypeUpperBound = kType;
        this.flags = i;
    }

    public static final CharSequence asString$lambda$0(TypeReference typeReference, KTypeProjection it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return typeReference.asString(it);
    }

    public final String asString(boolean z) {
        String name;
        KClassifier kClassifier = this.classifier;
        KClass kClass = kClassifier instanceof KClass ? (KClass) kClassifier : null;
        Class<?> javaClass = kClass != null ? JvmClassMappingKt.getJavaClass(kClass) : null;
        if (javaClass == null) {
            name = this.classifier.toString();
        } else if ((this.flags & 4) != 0) {
            name = "kotlin.Nothing";
        } else if (javaClass.isArray()) {
            name = getArrayClassName(javaClass);
        } else if (z && javaClass.isPrimitive()) {
            KClassifier kClassifier2 = this.classifier;
            Intrinsics.checkNotNull(kClassifier2, "null cannot be cast to non-null type kotlin.reflect.KClass<*>");
            name = JvmClassMappingKt.getJavaObjectType((KClass) kClassifier2).getName();
        } else {
            name = javaClass.getName();
        }
        String strM = AbstractResolvableFuture$$ExternalSyntheticOutline1.m(name, this.arguments.isEmpty() ? "" : CollectionsKt___CollectionsKt.joinToString$default(this.arguments, ", ", "<", ">", 0, null, new Function1() { // from class: kotlin.jvm.internal.TypeReference$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return TypeReference.asString$lambda$0(this.f$0, (KTypeProjection) obj);
            }
        }, 24, null), isMarkedNullable() ? "?" : "");
        KType kType = this.platformTypeUpperBound;
        if (!(kType instanceof TypeReference)) {
            return strM;
        }
        String strAsString = ((TypeReference) kType).asString(true);
        if (Intrinsics.areEqual(strAsString, strM)) {
            return strM;
        }
        if (Intrinsics.areEqual(strAsString, strM + '?')) {
            return strM + '!';
        }
        return "(" + strM + ".." + strAsString + ')';
    }

    public boolean equals(@Nullable Object obj) {
        if (!(obj instanceof TypeReference)) {
            return false;
        }
        TypeReference typeReference = (TypeReference) obj;
        return Intrinsics.areEqual(this.classifier, typeReference.classifier) && Intrinsics.areEqual(this.arguments, typeReference.arguments) && Intrinsics.areEqual(this.platformTypeUpperBound, typeReference.platformTypeUpperBound) && this.flags == typeReference.flags;
    }

    @Override // kotlin.reflect.KAnnotatedElement
    @NotNull
    public List<Annotation> getAnnotations() {
        return EmptyList.INSTANCE;
    }

    @Override // kotlin.reflect.KType
    @NotNull
    public List<KTypeProjection> getArguments() {
        return this.arguments;
    }

    public final String getArrayClassName(Class<?> cls) {
        return Intrinsics.areEqual(cls, boolean[].class) ? "kotlin.BooleanArray" : Intrinsics.areEqual(cls, char[].class) ? "kotlin.CharArray" : Intrinsics.areEqual(cls, byte[].class) ? "kotlin.ByteArray" : Intrinsics.areEqual(cls, short[].class) ? "kotlin.ShortArray" : Intrinsics.areEqual(cls, int[].class) ? "kotlin.IntArray" : Intrinsics.areEqual(cls, float[].class) ? "kotlin.FloatArray" : Intrinsics.areEqual(cls, long[].class) ? "kotlin.LongArray" : Intrinsics.areEqual(cls, double[].class) ? "kotlin.DoubleArray" : CollectionDescriptorsKt.ARRAY_NAME;
    }

    @Override // kotlin.reflect.KType
    @NotNull
    public KClassifier getClassifier() {
        return this.classifier;
    }

    public final int getFlags$kotlin_stdlib() {
        return this.flags;
    }

    @Nullable
    public final KType getPlatformTypeUpperBound$kotlin_stdlib() {
        return this.platformTypeUpperBound;
    }

    public int hashCode() {
        return ((this.arguments.hashCode() + (this.classifier.hashCode() * 31)) * 31) + this.flags;
    }

    @Override // kotlin.reflect.KType
    public boolean isMarkedNullable() {
        return (this.flags & 1) != 0;
    }

    @NotNull
    public String toString() {
        return asString(false) + Reflection.REFLECTION_NOT_AVAILABLE;
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public TypeReference(@NotNull KClassifier classifier, @NotNull List<KTypeProjection> arguments, boolean z) {
        this(classifier, arguments, null, z ? 1 : 0);
        Intrinsics.checkNotNullParameter(classifier, "classifier");
        Intrinsics.checkNotNullParameter(arguments, "arguments");
    }

    @SinceKotlin(version = "1.6")
    public static /* synthetic */ void getFlags$kotlin_stdlib$annotations() {
    }

    @SinceKotlin(version = "1.6")
    public static /* synthetic */ void getPlatformTypeUpperBound$kotlin_stdlib$annotations() {
    }

    public final String asString(KTypeProjection kTypeProjection) {
        String strValueOf;
        if (kTypeProjection.variance == null) {
            return MediaType.WILDCARD;
        }
        KType kType = kTypeProjection.type;
        TypeReference typeReference = kType instanceof TypeReference ? (TypeReference) kType : null;
        if (typeReference == null || (strValueOf = typeReference.asString(true)) == null) {
            strValueOf = String.valueOf(kTypeProjection.type);
        }
        KVariance kVariance = kTypeProjection.variance;
        int i = kVariance == null ? -1 : WhenMappings.$EnumSwitchMapping$0[kVariance.ordinal()];
        if (i == 1) {
            return strValueOf;
        }
        if (i == 2) {
            return "in ".concat(strValueOf);
        }
        if (i == 3) {
            return "out ".concat(strValueOf);
        }
        throw new NoWhenBranchMatchedException();
    }
}
