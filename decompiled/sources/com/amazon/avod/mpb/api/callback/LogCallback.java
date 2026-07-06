package com.amazon.avod.mpb.api.callback;

import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public interface LogCallback {

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class LogLevel {
        public static final /* synthetic */ EnumEntries $ENTRIES;
        public static final /* synthetic */ LogLevel[] $VALUES;
        public static final LogLevel LEVEL_VERBOSE = new LogLevel("LEVEL_VERBOSE", 0);
        public static final LogLevel LEVEL_TRACE = new LogLevel("LEVEL_TRACE", 1);
        public static final LogLevel LEVEL_INFO = new LogLevel("LEVEL_INFO", 2);
        public static final LogLevel LEVEL_WARN = new LogLevel("LEVEL_WARN", 3);
        public static final LogLevel LEVEL_ERROR = new LogLevel("LEVEL_ERROR", 4);

        public static final /* synthetic */ LogLevel[] $values() {
            return new LogLevel[]{LEVEL_VERBOSE, LEVEL_TRACE, LEVEL_INFO, LEVEL_WARN, LEVEL_ERROR};
        }

        static {
            LogLevel[] logLevelArr$values = $values();
            $VALUES = logLevelArr$values;
            $ENTRIES = EnumEntriesKt.enumEntries(logLevelArr$values);
        }

        public LogLevel(String str, int i) {
        }

        @NotNull
        public static EnumEntries<LogLevel> getEntries() {
            return $ENTRIES;
        }

        public static LogLevel valueOf(String str) {
            return (LogLevel) Enum.valueOf(LogLevel.class, str);
        }

        public static LogLevel[] values() {
            return (LogLevel[]) $VALUES.clone();
        }
    }

    void onLog(@NotNull LogLevel logLevel, @Nullable Throwable th, @NotNull String str, @NotNull Object... objArr);
}
