package com.google.common.base;

import com.google.common.annotations.GwtCompatible;
import java.lang.ref.WeakReference;
import java.util.Locale;
import java.util.regex.Pattern;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@GwtCompatible(emulated = true)
public final class Platform {
    public static final PatternCompiler patternCompiler = new JdkPatternCompiler();

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class JdkPatternCompiler implements PatternCompiler {
        public JdkPatternCompiler() {
        }

        @Override // com.google.common.base.PatternCompiler
        public CommonPattern compile(String pattern) {
            return new JdkPattern(Pattern.compile(pattern));
        }

        @Override // com.google.common.base.PatternCompiler
        public boolean isPcreLike() {
            return true;
        }

        public JdkPatternCompiler(AnonymousClass1 anonymousClass1) {
        }
    }

    public static CommonPattern compilePattern(String pattern) {
        pattern.getClass();
        return patternCompiler.compile(pattern);
    }

    public static String emptyToNull(String string) {
        if (stringIsNullOrEmpty(string)) {
            return null;
        }
        return string;
    }

    public static String formatCompact4Digits(double value) {
        return String.format(Locale.ROOT, "%.4g", Double.valueOf(value));
    }

    public static <T extends Enum<T>> Optional<T> getEnumIfPresent(Class<T> enumClass, String value) {
        WeakReference<? extends Enum<?>> weakReference = Enums.getEnumConstants(enumClass).get(value);
        return weakReference == null ? Absent.INSTANCE : Optional.fromNullable(enumClass.cast(weakReference.get()));
    }

    public static String lenientFormat(String template, Object... args) {
        return Strings.lenientFormat(template, args);
    }

    public static PatternCompiler loadPatternCompiler() {
        return new JdkPatternCompiler();
    }

    public static String nullToEmpty(String string) {
        return string == null ? "" : string;
    }

    public static boolean patternCompilerIsPcreLike() {
        patternCompiler.getClass();
        return true;
    }

    public static CharMatcher precomputeCharMatcher(CharMatcher matcher) {
        return matcher.precomputedInternal();
    }

    public static boolean stringIsNullOrEmpty(String string) {
        return string == null || string.isEmpty();
    }

    public static String stringValueOf(Object o) {
        return String.valueOf(o);
    }
}
