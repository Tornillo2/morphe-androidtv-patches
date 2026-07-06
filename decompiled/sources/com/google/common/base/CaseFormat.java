package com.google.common.base;

import com.amazonaws.mobileconnectors.remoteconfiguration.Attributes;
import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.J2ktIncompatible;
import com.google.common.base.CharMatcher;
import java.io.Serializable;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@GwtCompatible
public abstract class CaseFormat {
    public final CharMatcher wordBoundary;
    public final String wordSeparator;
    public static final CaseFormat LOWER_HYPHEN = new AnonymousClass1("LOWER_HYPHEN", 0, new CharMatcher.Is('-'), "-");
    public static final CaseFormat LOWER_UNDERSCORE = new AnonymousClass2("LOWER_UNDERSCORE", 1, new CharMatcher.Is('_'), Attributes.PREDEFINED_ATTRIBUTE_PREFIX);
    public static final CaseFormat LOWER_CAMEL = new AnonymousClass3("LOWER_CAMEL", 2, new CharMatcher.InRange('A', 'Z'), "");
    public static final CaseFormat UPPER_CAMEL = new AnonymousClass4("UPPER_CAMEL", 3, new CharMatcher.InRange('A', 'Z'), "");
    public static final CaseFormat UPPER_UNDERSCORE = new AnonymousClass5("UPPER_UNDERSCORE", 4, new CharMatcher.Is('_'), Attributes.PREDEFINED_ATTRIBUTE_PREFIX);
    public static final /* synthetic */ CaseFormat[] $VALUES = $values();

    /* JADX INFO: renamed from: com.google.common.base.CaseFormat$1, reason: invalid class name */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public enum AnonymousClass1 extends CaseFormat {
        public AnonymousClass1(String $enum$name, int $enum$ordinal, CharMatcher wordBoundary, String wordSeparator) {
            super($enum$name, $enum$ordinal, wordBoundary, wordSeparator);
        }

        @Override // com.google.common.base.CaseFormat
        public String convert(CaseFormat format, String s) {
            return format == CaseFormat.LOWER_UNDERSCORE ? s.replace('-', '_') : format == CaseFormat.UPPER_UNDERSCORE ? Ascii.toUpperCase(s.replace('-', '_')) : super.convert(format, s);
        }

        @Override // com.google.common.base.CaseFormat
        public String normalizeWord(String word) {
            return Ascii.toLowerCase(word);
        }
    }

    /* JADX INFO: renamed from: com.google.common.base.CaseFormat$2, reason: invalid class name */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public enum AnonymousClass2 extends CaseFormat {
        public AnonymousClass2(String $enum$name, int $enum$ordinal, CharMatcher wordBoundary, String wordSeparator) {
            super($enum$name, $enum$ordinal, wordBoundary, wordSeparator);
        }

        @Override // com.google.common.base.CaseFormat
        public String convert(CaseFormat format, String s) {
            return format == CaseFormat.LOWER_HYPHEN ? s.replace('_', '-') : format == CaseFormat.UPPER_UNDERSCORE ? Ascii.toUpperCase(s) : super.convert(format, s);
        }

        @Override // com.google.common.base.CaseFormat
        public String normalizeWord(String word) {
            return Ascii.toLowerCase(word);
        }
    }

    /* JADX INFO: renamed from: com.google.common.base.CaseFormat$3, reason: invalid class name */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public enum AnonymousClass3 extends CaseFormat {
        public AnonymousClass3(String $enum$name, int $enum$ordinal, CharMatcher wordBoundary, String wordSeparator) {
            super($enum$name, $enum$ordinal, wordBoundary, wordSeparator);
        }

        @Override // com.google.common.base.CaseFormat
        public String normalizeFirstWord(String word) {
            return Ascii.toLowerCase(word);
        }

        @Override // com.google.common.base.CaseFormat
        public String normalizeWord(String word) {
            return CaseFormat.firstCharOnlyToUpper(word);
        }
    }

    /* JADX INFO: renamed from: com.google.common.base.CaseFormat$4, reason: invalid class name */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public enum AnonymousClass4 extends CaseFormat {
        public AnonymousClass4(String $enum$name, int $enum$ordinal, CharMatcher wordBoundary, String wordSeparator) {
            super($enum$name, $enum$ordinal, wordBoundary, wordSeparator);
        }

        @Override // com.google.common.base.CaseFormat
        public String normalizeWord(String word) {
            return CaseFormat.firstCharOnlyToUpper(word);
        }
    }

    /* JADX INFO: renamed from: com.google.common.base.CaseFormat$5, reason: invalid class name */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public enum AnonymousClass5 extends CaseFormat {
        public AnonymousClass5(String $enum$name, int $enum$ordinal, CharMatcher wordBoundary, String wordSeparator) {
            super($enum$name, $enum$ordinal, wordBoundary, wordSeparator);
        }

        @Override // com.google.common.base.CaseFormat
        public String convert(CaseFormat format, String s) {
            return format == CaseFormat.LOWER_HYPHEN ? Ascii.toLowerCase(s.replace('_', '-')) : format == CaseFormat.LOWER_UNDERSCORE ? Ascii.toLowerCase(s) : super.convert(format, s);
        }

        @Override // com.google.common.base.CaseFormat
        public String normalizeWord(String word) {
            return Ascii.toUpperCase(word);
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class StringConverter extends Converter<String, String> implements Serializable {

        @J2ktIncompatible
        @GwtIncompatible
        public static final long serialVersionUID = 0;
        public final CaseFormat sourceFormat;
        public final CaseFormat targetFormat;

        public StringConverter(CaseFormat sourceFormat, CaseFormat targetFormat) {
            super(true);
            sourceFormat.getClass();
            this.sourceFormat = sourceFormat;
            targetFormat.getClass();
            this.targetFormat = targetFormat;
        }

        @Override // com.google.common.base.Converter, com.google.common.base.Function
        public boolean equals(Object object) {
            if (object instanceof StringConverter) {
                StringConverter stringConverter = (StringConverter) object;
                if (this.sourceFormat.equals(stringConverter.sourceFormat) && this.targetFormat.equals(stringConverter.targetFormat)) {
                    return true;
                }
            }
            return false;
        }

        public int hashCode() {
            return this.sourceFormat.hashCode() ^ this.targetFormat.hashCode();
        }

        public String toString() {
            return this.sourceFormat + ".converterTo(" + this.targetFormat + ")";
        }

        @Override // com.google.common.base.Converter
        public String doBackward(String s) {
            return this.targetFormat.to(this.sourceFormat, s);
        }

        @Override // com.google.common.base.Converter
        public String doForward(String s) {
            return this.sourceFormat.to(this.targetFormat, s);
        }
    }

    public static /* synthetic */ CaseFormat[] $values() {
        return new CaseFormat[]{LOWER_HYPHEN, LOWER_UNDERSCORE, LOWER_CAMEL, UPPER_CAMEL, UPPER_UNDERSCORE};
    }

    public /* synthetic */ CaseFormat(String str, int i, CharMatcher charMatcher, String str2, AnonymousClass1 anonymousClass1) {
        this(str, i, charMatcher, str2);
    }

    public static String firstCharOnlyToUpper(String word) {
        if (word.isEmpty()) {
            return word;
        }
        return Ascii.toUpperCase(word.charAt(0)) + Ascii.toLowerCase(word.substring(1));
    }

    public static CaseFormat valueOf(String name) {
        return (CaseFormat) Enum.valueOf(CaseFormat.class, name);
    }

    public static CaseFormat[] values() {
        return (CaseFormat[]) $VALUES.clone();
    }

    public String convert(CaseFormat format, String s) {
        StringBuilder sb = null;
        int length = 0;
        int iIndexIn = -1;
        while (true) {
            iIndexIn = this.wordBoundary.indexIn(s, iIndexIn + 1);
            if (iIndexIn == -1) {
                break;
            }
            if (length == 0) {
                sb = new StringBuilder((format.wordSeparator.length() * 4) + s.length());
                sb.append(format.normalizeFirstWord(s.substring(length, iIndexIn)));
            } else {
                j$.util.Objects.requireNonNull(sb);
                sb.append(format.normalizeWord(s.substring(length, iIndexIn)));
            }
            sb.append(format.wordSeparator);
            length = this.wordSeparator.length() + iIndexIn;
        }
        if (length == 0) {
            return format.normalizeFirstWord(s);
        }
        j$.util.Objects.requireNonNull(sb);
        sb.append(format.normalizeWord(s.substring(length)));
        return sb.toString();
    }

    public Converter<String, String> converterTo(CaseFormat targetFormat) {
        return new StringConverter(this, targetFormat);
    }

    public String normalizeFirstWord(String word) {
        return normalizeWord(word);
    }

    public abstract String normalizeWord(String word);

    public final String to(CaseFormat format, String str) {
        format.getClass();
        str.getClass();
        return format == this ? str : convert(format, str);
    }

    public CaseFormat(String $enum$name, int $enum$ordinal, CharMatcher wordBoundary, String wordSeparator) {
        this.wordBoundary = wordBoundary;
        this.wordSeparator = wordSeparator;
    }
}
