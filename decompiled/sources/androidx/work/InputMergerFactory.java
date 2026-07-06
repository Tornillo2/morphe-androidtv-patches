package androidx.work;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public abstract class InputMergerFactory {

    /* JADX INFO: renamed from: androidx.work.InputMergerFactory$1, reason: invalid class name */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public class AnonymousClass1 extends InputMergerFactory {
        @Override // androidx.work.InputMergerFactory
        @Nullable
        public InputMerger createInputMerger(@NonNull String className) {
            return null;
        }
    }

    @NonNull
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public static InputMergerFactory getDefaultInputMergerFactory() {
        return new AnonymousClass1();
    }

    @Nullable
    public abstract InputMerger createInputMerger(@NonNull String className);

    @Nullable
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public final InputMerger createInputMergerWithDefaultFallback(@NonNull String className) {
        return InputMerger.fromClassName(className);
    }
}
