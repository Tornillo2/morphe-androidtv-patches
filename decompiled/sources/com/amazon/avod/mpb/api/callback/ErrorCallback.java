package com.amazon.avod.mpb.api.callback;

import com.amazon.avod.mpb.api.core.MediaPipelineBackendResultCode;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public interface ErrorCallback {

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class ErrorSeverity {
        public static final /* synthetic */ EnumEntries $ENTRIES;
        public static final /* synthetic */ ErrorSeverity[] $VALUES;
        public static final ErrorSeverity SEV_FATAL = new ErrorSeverity("SEV_FATAL", 0);
        public static final ErrorSeverity SEV_RECOVERABLE = new ErrorSeverity("SEV_RECOVERABLE", 1);

        public static final /* synthetic */ ErrorSeverity[] $values() {
            return new ErrorSeverity[]{SEV_FATAL, SEV_RECOVERABLE};
        }

        static {
            ErrorSeverity[] errorSeverityArr$values = $values();
            $VALUES = errorSeverityArr$values;
            $ENTRIES = EnumEntriesKt.enumEntries(errorSeverityArr$values);
        }

        public ErrorSeverity(String str, int i) {
        }

        @NotNull
        public static EnumEntries<ErrorSeverity> getEntries() {
            return $ENTRIES;
        }

        public static ErrorSeverity valueOf(String str) {
            return (ErrorSeverity) Enum.valueOf(ErrorSeverity.class, str);
        }

        public static ErrorSeverity[] values() {
            return (ErrorSeverity[]) $VALUES.clone();
        }
    }

    void onError(@NotNull String str, @NotNull MediaPipelineBackendResultCode mediaPipelineBackendResultCode, @NotNull String str2, @NotNull ErrorSeverity errorSeverity);
}
