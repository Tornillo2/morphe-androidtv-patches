package kotlin.io.path;

import com.google.common.io.MoreFiles$$ExternalSyntheticApiModelOutline1;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class ExceptionsCollector {

    @NotNull
    public final List<Exception> collectedExceptions;
    public final int limit;

    @Nullable
    public Path path;
    public int totalExceptions;

    public ExceptionsCollector() {
        this(0, 1, null);
    }

    public final void collect(@NotNull Exception exception) {
        Intrinsics.checkNotNullParameter(exception, "exception");
        this.totalExceptions++;
        if (this.collectedExceptions.size() < this.limit) {
            if (this.path != null) {
                MoreFiles$$ExternalSyntheticApiModelOutline1.m();
                Throwable thInitCause = ExceptionsCollector$$ExternalSyntheticApiModelOutline0.m(String.valueOf(this.path)).initCause(exception);
                Intrinsics.checkNotNull(thInitCause, "null cannot be cast to non-null type java.nio.file.FileSystemException");
                exception = ExceptionsCollector$$ExternalSyntheticApiModelOutline1.m(thInitCause);
            }
            this.collectedExceptions.add(exception);
        }
    }

    public final void enterEntry(@NotNull Path name) {
        Intrinsics.checkNotNullParameter(name, "name");
        Path path = this.path;
        this.path = path != null ? path.resolve(name) : null;
    }

    public final void exitEntry(@NotNull Path name) {
        Intrinsics.checkNotNullParameter(name, "name");
        Path path = this.path;
        if (!name.equals(path != null ? path.getFileName() : null)) {
            throw new IllegalArgumentException("Failed requirement.");
        }
        Path path2 = this.path;
        this.path = path2 != null ? path2.getParent() : null;
    }

    @NotNull
    public final List<Exception> getCollectedExceptions() {
        return this.collectedExceptions;
    }

    @Nullable
    public final Path getPath() {
        return this.path;
    }

    public final int getTotalExceptions() {
        return this.totalExceptions;
    }

    public final void setPath(@Nullable Path path) {
        this.path = path;
    }

    public ExceptionsCollector(int i) {
        this.limit = i;
        this.collectedExceptions = new ArrayList();
    }

    public /* synthetic */ ExceptionsCollector(int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? 64 : i);
    }
}
