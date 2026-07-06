package kotlin.internal.jdk7;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import kotlin.collections.ArraysKt___ArraysJvmKt;
import kotlin.internal.PlatformImplementations;
import kotlin.jvm.JvmField;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public class JDK7PlatformImplementations extends PlatformImplementations {

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @SourceDebugExtension({"SMAP\nJDK7PlatformImplementations.kt\nKotlin\n*S Kotlin\n*F\n+ 1 JDK7PlatformImplementations.kt\nkotlin/internal/jdk7/JDK7PlatformImplementations$ReflectSdkVersion\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,38:1\n1#2:39\n*E\n"})
    public static final class ReflectSdkVersion {

        @NotNull
        public static final ReflectSdkVersion INSTANCE = new ReflectSdkVersion();

        @JvmField
        @Nullable
        public static final Integer sdkVersion;

        static {
            Object obj;
            Integer num = null;
            try {
                obj = Class.forName("android.os.Build$VERSION").getField("SDK_INT").get(null);
            } catch (Throwable unused) {
            }
            Integer num2 = obj instanceof Integer ? (Integer) obj : null;
            if (num2 != null && num2.intValue() > 0) {
                num = num2;
            }
            sdkVersion = num;
        }
    }

    private final boolean sdkIsNullOrAtLeast(int i) {
        Integer num = ReflectSdkVersion.sdkVersion;
        return num == null || num.intValue() >= i;
    }

    @Override // kotlin.internal.PlatformImplementations
    public void addSuppressed(@NotNull Throwable cause, @NotNull Throwable exception) throws IllegalAccessException, InvocationTargetException {
        Intrinsics.checkNotNullParameter(cause, "cause");
        Intrinsics.checkNotNullParameter(exception, "exception");
        if (sdkIsNullOrAtLeast(19)) {
            cause.addSuppressed(exception);
        } else {
            super.addSuppressed(cause, exception);
        }
    }

    @Override // kotlin.internal.PlatformImplementations
    @NotNull
    public List<Throwable> getSuppressed(@NotNull Throwable exception) {
        Intrinsics.checkNotNullParameter(exception, "exception");
        if (!sdkIsNullOrAtLeast(19)) {
            return super.getSuppressed(exception);
        }
        Throwable[] suppressed = exception.getSuppressed();
        Intrinsics.checkNotNullExpressionValue(suppressed, "getSuppressed(...)");
        return ArraysKt___ArraysJvmKt.asList(suppressed);
    }
}
