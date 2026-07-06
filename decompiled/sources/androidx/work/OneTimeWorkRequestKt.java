package androidx.work;

import androidx.annotation.NonNull;
import androidx.work.OneTimeWorkRequest;
import kotlin.jvm.JvmClassMappingKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KClass;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public final class OneTimeWorkRequestKt {
    public static final <W extends ListenableWorker> OneTimeWorkRequest.Builder OneTimeWorkRequestBuilder() {
        Intrinsics.throwUndefinedForReified();
        throw null;
    }

    @NotNull
    public static final OneTimeWorkRequest.Builder setInputMerger(@NotNull OneTimeWorkRequest.Builder builder, @NonNull @NotNull KClass<? extends InputMerger> inputMerger) {
        Intrinsics.checkNotNullParameter(builder, "<this>");
        Intrinsics.checkNotNullParameter(inputMerger, "inputMerger");
        Class javaClass = JvmClassMappingKt.getJavaClass((KClass) inputMerger);
        builder.mWorkSpec.inputMergerClassName = javaClass.getName();
        return builder;
    }
}
