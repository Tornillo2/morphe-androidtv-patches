package kotlin.jvm.internal;

import android.support.v4.media.MediaMetadataCompat$Builder$$ExternalSyntheticOutline0;
import androidx.concurrent.futures.AbstractResolvableFuture$$ExternalSyntheticOutline0;
import androidx.media3.common.TrackGroup$$ExternalSyntheticOutline0;
import com.amazon.avod.mpb.media.ExternalFourCCMapper;
import java.util.Arrays;
import kotlin.KotlinNullPointerException;
import kotlin.SinceKotlin;
import kotlin.UninitializedPropertyAccessException;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public class Intrinsics {

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @SinceKotlin(version = "1.4")
    public static class Kotlin {
    }

    public static boolean areEqual(Object obj, Object obj2) {
        return obj == null ? obj2 == null : obj.equals(obj2);
    }

    public static void checkExpressionValueIsNotNull(Object obj, String str) {
        if (obj != null) {
            return;
        }
        IllegalStateException illegalStateException = new IllegalStateException(AbstractResolvableFuture$$ExternalSyntheticOutline0.m(str, " must not be null"));
        sanitizeStackTrace(illegalStateException, Intrinsics.class.getName());
        throw illegalStateException;
    }

    public static void checkFieldIsNotNull(Object obj, String str, String str2) {
        if (obj != null) {
            return;
        }
        IllegalStateException illegalStateException = new IllegalStateException("Field specified as non-null is null: " + str + ExternalFourCCMapper.CODEC_NAME_SPLITTER + str2);
        sanitizeStackTrace(illegalStateException, Intrinsics.class.getName());
        throw illegalStateException;
    }

    public static void checkHasClass(String str) throws ClassNotFoundException {
        String strReplace = str.replace('/', '.');
        try {
            Class.forName(strReplace);
        } catch (ClassNotFoundException e) {
            ClassNotFoundException classNotFoundException = new ClassNotFoundException(MediaMetadataCompat$Builder$$ExternalSyntheticOutline0.m("Class ", strReplace, " is not found. Please update the Kotlin runtime to the latest version"), e);
            sanitizeStackTrace(classNotFoundException, Intrinsics.class.getName());
            throw classNotFoundException;
        }
    }

    public static void checkNotNull(Object obj) {
        if (obj != null) {
            return;
        }
        throwJavaNpe();
        throw null;
    }

    public static void checkNotNullExpressionValue(Object obj, String str) {
        if (obj != null) {
            return;
        }
        NullPointerException nullPointerException = new NullPointerException(AbstractResolvableFuture$$ExternalSyntheticOutline0.m(str, " must not be null"));
        sanitizeStackTrace(nullPointerException, Intrinsics.class.getName());
        throw nullPointerException;
    }

    public static void checkNotNullParameter(Object obj, String str) {
        if (obj != null) {
            return;
        }
        throwParameterIsNullNPE(str);
        throw null;
    }

    public static void checkParameterIsNotNull(Object obj, String str) {
        if (obj != null) {
            return;
        }
        throwParameterIsNullIAE(str);
        throw null;
    }

    public static void checkReturnedValueIsNotNull(Object obj, String str, String str2) {
        if (obj != null) {
            return;
        }
        IllegalStateException illegalStateException = new IllegalStateException("Method specified as non-null returned null: " + str + ExternalFourCCMapper.CODEC_NAME_SPLITTER + str2);
        sanitizeStackTrace(illegalStateException, Intrinsics.class.getName());
        throw illegalStateException;
    }

    public static int compare(int i, int i2) {
        if (i < i2) {
            return -1;
        }
        return i == i2 ? 0 : 1;
    }

    public static String createParameterIsNullExceptionMessage(String str) {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        String name = Intrinsics.class.getName();
        int i = 0;
        while (!stackTrace[i].getClassName().equals(name)) {
            i++;
        }
        while (stackTrace[i].getClassName().equals(name)) {
            i++;
        }
        StackTraceElement stackTraceElement = stackTrace[i];
        StringBuilder sbM = TrackGroup$$ExternalSyntheticOutline0.m("Parameter specified as non-null is null: method ", stackTraceElement.getClassName(), ExternalFourCCMapper.CODEC_NAME_SPLITTER, stackTraceElement.getMethodName(), ", parameter ");
        sbM.append(str);
        return sbM.toString();
    }

    public static void needClassReification() {
        throwUndefinedForReified();
        throw null;
    }

    public static void reifiedOperationMarker(int i, String str) {
        throwUndefinedForReified();
        throw null;
    }

    public static <T extends Throwable> T sanitizeStackTrace(T t) {
        sanitizeStackTrace(t, Intrinsics.class.getName());
        return t;
    }

    public static String stringPlus(String str, Object obj) {
        return str + obj;
    }

    public static void throwAssert() {
        AssertionError assertionError = new AssertionError();
        sanitizeStackTrace(assertionError, Intrinsics.class.getName());
        throw assertionError;
    }

    public static void throwIllegalArgument() {
        IllegalArgumentException illegalArgumentException = new IllegalArgumentException();
        sanitizeStackTrace(illegalArgumentException, Intrinsics.class.getName());
        throw illegalArgumentException;
    }

    public static void throwIllegalState() {
        IllegalStateException illegalStateException = new IllegalStateException();
        sanitizeStackTrace(illegalStateException, Intrinsics.class.getName());
        throw illegalStateException;
    }

    @SinceKotlin(version = "1.4")
    public static void throwJavaNpe() {
        NullPointerException nullPointerException = new NullPointerException();
        sanitizeStackTrace(nullPointerException, Intrinsics.class.getName());
        throw nullPointerException;
    }

    public static void throwNpe() {
        KotlinNullPointerException kotlinNullPointerException = new KotlinNullPointerException();
        sanitizeStackTrace(kotlinNullPointerException, Intrinsics.class.getName());
        throw kotlinNullPointerException;
    }

    public static void throwParameterIsNullIAE(String str) {
        IllegalArgumentException illegalArgumentException = new IllegalArgumentException(createParameterIsNullExceptionMessage(str));
        sanitizeStackTrace(illegalArgumentException, Intrinsics.class.getName());
        throw illegalArgumentException;
    }

    public static void throwParameterIsNullNPE(String str) {
        NullPointerException nullPointerException = new NullPointerException(createParameterIsNullExceptionMessage(str));
        sanitizeStackTrace(nullPointerException, Intrinsics.class.getName());
        throw nullPointerException;
    }

    public static void throwUndefinedForReified() {
        throwUndefinedForReified("This function has a reified type parameter and thus can only be inlined at compilation time, not called directly.");
        throw null;
    }

    public static void throwUninitializedProperty(String str) {
        UninitializedPropertyAccessException uninitializedPropertyAccessException = new UninitializedPropertyAccessException(str);
        sanitizeStackTrace(uninitializedPropertyAccessException, Intrinsics.class.getName());
        throw uninitializedPropertyAccessException;
    }

    public static void throwUninitializedPropertyAccessException(String str) {
        throwUninitializedProperty("lateinit property " + str + " has not been initialized");
        throw null;
    }

    @SinceKotlin(version = "1.1")
    public static boolean areEqual(Double d, Double d2) {
        return d == null ? d2 == null : d2 != null && d.doubleValue() == d2.doubleValue();
    }

    public static void checkNotNull(Object obj, String str) {
        if (obj != null) {
            return;
        }
        throwJavaNpe(str);
        throw null;
    }

    public static int compare(long j, long j2) {
        if (j < j2) {
            return -1;
        }
        return j == j2 ? 0 : 1;
    }

    public static void needClassReification(String str) {
        throwUndefinedForReified(str);
        throw null;
    }

    public static void reifiedOperationMarker(int i, String str, String str2) {
        throwUndefinedForReified(str2);
        throw null;
    }

    public static <T extends Throwable> T sanitizeStackTrace(T t, String str) {
        StackTraceElement[] stackTrace = t.getStackTrace();
        int length = stackTrace.length;
        int i = -1;
        for (int i2 = 0; i2 < length; i2++) {
            if (str.equals(stackTrace[i2].getClassName())) {
                i = i2;
            }
        }
        t.setStackTrace((StackTraceElement[]) Arrays.copyOfRange(stackTrace, i + 1, length));
        return t;
    }

    public static void throwUndefinedForReified(String str) {
        throw new UnsupportedOperationException(str);
    }

    @SinceKotlin(version = "1.1")
    public static boolean areEqual(Double d, double d2) {
        return d != null && d.doubleValue() == d2;
    }

    @SinceKotlin(version = "1.1")
    public static boolean areEqual(double d, Double d2) {
        return d2 != null && d == d2.doubleValue();
    }

    public static void checkFieldIsNotNull(Object obj, String str) {
        if (obj != null) {
            return;
        }
        IllegalStateException illegalStateException = new IllegalStateException(str);
        sanitizeStackTrace(illegalStateException, Intrinsics.class.getName());
        throw illegalStateException;
    }

    public static void checkReturnedValueIsNotNull(Object obj, String str) {
        if (obj != null) {
            return;
        }
        IllegalStateException illegalStateException = new IllegalStateException(str);
        sanitizeStackTrace(illegalStateException, Intrinsics.class.getName());
        throw illegalStateException;
    }

    public static void throwAssert(String str) {
        AssertionError assertionError = new AssertionError(str);
        sanitizeStackTrace(assertionError, Intrinsics.class.getName());
        throw assertionError;
    }

    public static void throwIllegalArgument(String str) {
        IllegalArgumentException illegalArgumentException = new IllegalArgumentException(str);
        sanitizeStackTrace(illegalArgumentException, Intrinsics.class.getName());
        throw illegalArgumentException;
    }

    public static void throwIllegalState(String str) {
        IllegalStateException illegalStateException = new IllegalStateException(str);
        sanitizeStackTrace(illegalStateException, Intrinsics.class.getName());
        throw illegalStateException;
    }

    @SinceKotlin(version = "1.4")
    public static void throwJavaNpe(String str) {
        NullPointerException nullPointerException = new NullPointerException(str);
        sanitizeStackTrace(nullPointerException, Intrinsics.class.getName());
        throw nullPointerException;
    }

    @SinceKotlin(version = "1.1")
    public static boolean areEqual(Float f, Float f2) {
        return f == null ? f2 == null : f2 != null && f.floatValue() == f2.floatValue();
    }

    public static void throwNpe(String str) {
        KotlinNullPointerException kotlinNullPointerException = new KotlinNullPointerException(str);
        sanitizeStackTrace(kotlinNullPointerException, Intrinsics.class.getName());
        throw kotlinNullPointerException;
    }

    @SinceKotlin(version = "1.1")
    public static boolean areEqual(Float f, float f2) {
        return f != null && f.floatValue() == f2;
    }

    @SinceKotlin(version = "1.1")
    public static boolean areEqual(float f, Float f2) {
        return f2 != null && f == f2.floatValue();
    }

    public static void checkHasClass(String str, String str2) throws ClassNotFoundException {
        String strReplace = str.replace('/', '.');
        try {
            Class.forName(strReplace);
        } catch (ClassNotFoundException e) {
            ClassNotFoundException classNotFoundException = new ClassNotFoundException("Class " + strReplace + " is not found: this code requires the Kotlin runtime of version at least " + str2, e);
            sanitizeStackTrace(classNotFoundException, Intrinsics.class.getName());
            throw classNotFoundException;
        }
    }
}
