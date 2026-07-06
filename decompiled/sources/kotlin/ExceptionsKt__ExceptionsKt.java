package kotlin;

import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import kotlin.internal.HidesMembers;
import kotlin.internal.InlineOnly;
import kotlin.internal.PlatformImplementationsKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public class ExceptionsKt__ExceptionsKt {
    @SinceKotlin(version = "1.1")
    @HidesMembers
    public static void addSuppressed(@NotNull Throwable th, @NotNull Throwable exception) throws IllegalAccessException, InvocationTargetException {
        Intrinsics.checkNotNullParameter(th, "<this>");
        Intrinsics.checkNotNullParameter(exception, "exception");
        if (th != exception) {
            PlatformImplementationsKt.IMPLEMENTATIONS.addSuppressed(th, exception);
        }
    }

    @NotNull
    public static final StackTraceElement[] getStackTrace(@NotNull Throwable th) {
        Intrinsics.checkNotNullParameter(th, "<this>");
        StackTraceElement[] stackTrace = th.getStackTrace();
        Intrinsics.checkNotNull(stackTrace);
        return stackTrace;
    }

    @NotNull
    public static final List<Throwable> getSuppressedExceptions(@NotNull Throwable th) {
        Intrinsics.checkNotNullParameter(th, "<this>");
        return PlatformImplementationsKt.IMPLEMENTATIONS.getSuppressed(th);
    }

    @InlineOnly
    public static final void printStackTrace(Throwable th) {
        Intrinsics.checkNotNullParameter(th, "<this>");
        th.printStackTrace();
    }

    @SinceKotlin(version = "1.4")
    @NotNull
    public static final String stackTraceToString(@NotNull Throwable th) {
        Intrinsics.checkNotNullParameter(th, "<this>");
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        th.printStackTrace(printWriter);
        printWriter.flush();
        String string = stringWriter.toString();
        Intrinsics.checkNotNullExpressionValue(string, "toString(...)");
        return string;
    }

    @InlineOnly
    public static final void printStackTrace(Throwable th, PrintWriter writer) {
        Intrinsics.checkNotNullParameter(th, "<this>");
        Intrinsics.checkNotNullParameter(writer, "writer");
        th.printStackTrace(writer);
    }

    @InlineOnly
    public static final void printStackTrace(Throwable th, PrintStream stream) {
        Intrinsics.checkNotNullParameter(th, "<this>");
        Intrinsics.checkNotNullParameter(stream, "stream");
        th.printStackTrace(stream);
    }

    public static /* synthetic */ void getStackTrace$annotations(Throwable th) {
    }

    @SinceKotlin(version = "1.4")
    public static /* synthetic */ void getSuppressedExceptions$annotations(Throwable th) {
    }
}
