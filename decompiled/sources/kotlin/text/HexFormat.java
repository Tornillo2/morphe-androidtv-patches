package kotlin.text;

import android.support.v4.media.MediaBrowserCompat$MediaBrowserImplBase$$ExternalSyntheticOutline0;
import androidx.core.accessibilityservice.AccessibilityServiceInfoCompat$$ExternalSyntheticOutline0;
import com.bumptech.glide.load.engine.GlideException;
import kotlin.ExperimentalStdlibApi;
import kotlin.PublishedApi;
import kotlin.SinceKotlin;
import kotlin.Unit;
import kotlin.internal.InlineOnly;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.serialization.json.JsonKt;
import org.apache.commons.lang3.CharUtils;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@SinceKotlin(version = "1.9")
@ExperimentalStdlibApi
public final class HexFormat {

    @NotNull
    public static final Companion Companion = new Companion();

    @NotNull
    public static final HexFormat Default;

    @NotNull
    public static final HexFormat UpperCase;

    @NotNull
    public final BytesHexFormat bytes;

    @NotNull
    public final NumberHexFormat number;
    public final boolean upperCase;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class Builder {

        @Nullable
        public BytesHexFormat.Builder _bytes;

        @Nullable
        public NumberHexFormat.Builder _number;
        public boolean upperCase;

        @PublishedApi
        public Builder() {
            HexFormat.Companion.getClass();
            this.upperCase = HexFormat.Default.upperCase;
        }

        @PublishedApi
        @NotNull
        public final HexFormat build() {
            BytesHexFormat bytesHexFormatBuild$kotlin_stdlib;
            NumberHexFormat numberHexFormatBuild$kotlin_stdlib;
            boolean z = this.upperCase;
            BytesHexFormat.Builder builder = this._bytes;
            if (builder != null) {
                bytesHexFormatBuild$kotlin_stdlib = builder.build$kotlin_stdlib();
            } else {
                BytesHexFormat.Companion.getClass();
                bytesHexFormatBuild$kotlin_stdlib = BytesHexFormat.Default;
            }
            NumberHexFormat.Builder builder2 = this._number;
            if (builder2 != null) {
                numberHexFormatBuild$kotlin_stdlib = builder2.build$kotlin_stdlib();
            } else {
                NumberHexFormat.Companion.getClass();
                numberHexFormatBuild$kotlin_stdlib = NumberHexFormat.Default;
            }
            return new HexFormat(z, bytesHexFormatBuild$kotlin_stdlib, numberHexFormatBuild$kotlin_stdlib);
        }

        @InlineOnly
        public final void bytes(Function1<? super BytesHexFormat.Builder, Unit> builderAction) {
            Intrinsics.checkNotNullParameter(builderAction, "builderAction");
            builderAction.invoke(getBytes());
        }

        @NotNull
        public final BytesHexFormat.Builder getBytes() {
            if (this._bytes == null) {
                this._bytes = new BytesHexFormat.Builder();
            }
            BytesHexFormat.Builder builder = this._bytes;
            Intrinsics.checkNotNull(builder);
            return builder;
        }

        @NotNull
        public final NumberHexFormat.Builder getNumber() {
            if (this._number == null) {
                this._number = new NumberHexFormat.Builder();
            }
            NumberHexFormat.Builder builder = this._number;
            Intrinsics.checkNotNull(builder);
            return builder;
        }

        public final boolean getUpperCase() {
            return this.upperCase;
        }

        @InlineOnly
        public final void number(Function1<? super NumberHexFormat.Builder, Unit> builderAction) {
            Intrinsics.checkNotNullParameter(builderAction, "builderAction");
            builderAction.invoke(getNumber());
        }

        public final void setUpperCase(boolean z) {
            this.upperCase = z;
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class BytesHexFormat {

        @NotNull
        public static final Companion Companion = new Companion();

        @NotNull
        public static final BytesHexFormat Default = new BytesHexFormat(Integer.MAX_VALUE, Integer.MAX_VALUE, GlideException.IndentedAppendable.INDENT, "", "", "");

        @NotNull
        public final String bytePrefix;

        @NotNull
        public final String byteSeparator;

        @NotNull
        public final String byteSuffix;
        public final int bytesPerGroup;
        public final int bytesPerLine;

        @NotNull
        public final String groupSeparator;
        public final boolean ignoreCase;
        public final boolean noLineAndGroupSeparator;
        public final boolean shortByteSeparatorNoPrefixAndSuffix;

        /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
        public static final class Builder {

            @NotNull
            public String bytePrefix;

            @NotNull
            public String byteSeparator;

            @NotNull
            public String byteSuffix;
            public int bytesPerGroup;
            public int bytesPerLine;

            @NotNull
            public String groupSeparator;

            public Builder() {
                Companion companion = BytesHexFormat.Companion;
                companion.getClass();
                this.bytesPerLine = BytesHexFormat.Default.bytesPerLine;
                companion.getClass();
                this.bytesPerGroup = BytesHexFormat.Default.bytesPerGroup;
                companion.getClass();
                this.groupSeparator = BytesHexFormat.Default.groupSeparator;
                companion.getClass();
                this.byteSeparator = BytesHexFormat.Default.byteSeparator;
                companion.getClass();
                this.bytePrefix = BytesHexFormat.Default.bytePrefix;
                companion.getClass();
                this.byteSuffix = BytesHexFormat.Default.byteSuffix;
            }

            @NotNull
            public final BytesHexFormat build$kotlin_stdlib() {
                return new BytesHexFormat(this.bytesPerLine, this.bytesPerGroup, this.groupSeparator, this.byteSeparator, this.bytePrefix, this.byteSuffix);
            }

            @NotNull
            public final String getBytePrefix() {
                return this.bytePrefix;
            }

            @NotNull
            public final String getByteSeparator() {
                return this.byteSeparator;
            }

            @NotNull
            public final String getByteSuffix() {
                return this.byteSuffix;
            }

            public final int getBytesPerGroup() {
                return this.bytesPerGroup;
            }

            public final int getBytesPerLine() {
                return this.bytesPerLine;
            }

            @NotNull
            public final String getGroupSeparator() {
                return this.groupSeparator;
            }

            public final void setBytePrefix(@NotNull String value) {
                Intrinsics.checkNotNullParameter(value, "value");
                if (StringsKt__StringsKt.contains$default((CharSequence) value, '\n', false, 2, (Object) null) || StringsKt__StringsKt.contains$default((CharSequence) value, CharUtils.CR, false, 2, (Object) null)) {
                    throw new IllegalArgumentException("LF and CR characters are prohibited in bytePrefix, but was ".concat(value));
                }
                this.bytePrefix = value;
            }

            public final void setByteSeparator(@NotNull String value) {
                Intrinsics.checkNotNullParameter(value, "value");
                if (StringsKt__StringsKt.contains$default((CharSequence) value, '\n', false, 2, (Object) null) || StringsKt__StringsKt.contains$default((CharSequence) value, CharUtils.CR, false, 2, (Object) null)) {
                    throw new IllegalArgumentException("LF and CR characters are prohibited in byteSeparator, but was ".concat(value));
                }
                this.byteSeparator = value;
            }

            public final void setByteSuffix(@NotNull String value) {
                Intrinsics.checkNotNullParameter(value, "value");
                if (StringsKt__StringsKt.contains$default((CharSequence) value, '\n', false, 2, (Object) null) || StringsKt__StringsKt.contains$default((CharSequence) value, CharUtils.CR, false, 2, (Object) null)) {
                    throw new IllegalArgumentException("LF and CR characters are prohibited in byteSuffix, but was ".concat(value));
                }
                this.byteSuffix = value;
            }

            public final void setBytesPerGroup(int i) {
                if (i <= 0) {
                    throw new IllegalArgumentException(MediaBrowserCompat$MediaBrowserImplBase$$ExternalSyntheticOutline0.m("Non-positive values are prohibited for bytesPerGroup, but was ", i));
                }
                this.bytesPerGroup = i;
            }

            public final void setBytesPerLine(int i) {
                if (i <= 0) {
                    throw new IllegalArgumentException(MediaBrowserCompat$MediaBrowserImplBase$$ExternalSyntheticOutline0.m("Non-positive values are prohibited for bytesPerLine, but was ", i));
                }
                this.bytesPerLine = i;
            }

            public final void setGroupSeparator(@NotNull String str) {
                Intrinsics.checkNotNullParameter(str, "<set-?>");
                this.groupSeparator = str;
            }
        }

        /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
        public static final class Companion {
            public Companion() {
            }

            @NotNull
            public final BytesHexFormat getDefault$kotlin_stdlib() {
                return BytesHexFormat.Default;
            }

            public Companion(DefaultConstructorMarker defaultConstructorMarker) {
            }
        }

        public BytesHexFormat(int i, int i2, @NotNull String groupSeparator, @NotNull String byteSeparator, @NotNull String bytePrefix, @NotNull String byteSuffix) {
            Intrinsics.checkNotNullParameter(groupSeparator, "groupSeparator");
            Intrinsics.checkNotNullParameter(byteSeparator, "byteSeparator");
            Intrinsics.checkNotNullParameter(bytePrefix, "bytePrefix");
            Intrinsics.checkNotNullParameter(byteSuffix, "byteSuffix");
            this.bytesPerLine = i;
            this.bytesPerGroup = i2;
            this.groupSeparator = groupSeparator;
            this.byteSeparator = byteSeparator;
            this.bytePrefix = bytePrefix;
            this.byteSuffix = byteSuffix;
            this.noLineAndGroupSeparator = i == Integer.MAX_VALUE && i2 == Integer.MAX_VALUE;
            this.shortByteSeparatorNoPrefixAndSuffix = bytePrefix.length() == 0 && byteSuffix.length() == 0 && byteSeparator.length() <= 1;
            this.ignoreCase = HexFormatKt.isCaseSensitive(groupSeparator) || HexFormatKt.isCaseSensitive(byteSeparator) || HexFormatKt.isCaseSensitive(bytePrefix) || HexFormatKt.isCaseSensitive(byteSuffix);
        }

        @NotNull
        public final StringBuilder appendOptionsTo$kotlin_stdlib(@NotNull StringBuilder sb, @NotNull String indent) {
            Intrinsics.checkNotNullParameter(sb, "sb");
            Intrinsics.checkNotNullParameter(indent, "indent");
            sb.append(indent);
            sb.append("bytesPerLine = ");
            sb.append(this.bytesPerLine);
            sb.append(",");
            sb.append('\n');
            sb.append(indent);
            sb.append("bytesPerGroup = ");
            sb.append(this.bytesPerGroup);
            sb.append(",");
            sb.append('\n');
            sb.append(indent);
            sb.append("groupSeparator = \"");
            sb.append(this.groupSeparator);
            sb.append("\",");
            sb.append('\n');
            sb.append(indent);
            sb.append("byteSeparator = \"");
            sb.append(this.byteSeparator);
            sb.append("\",");
            sb.append('\n');
            sb.append(indent);
            sb.append("bytePrefix = \"");
            sb.append(this.bytePrefix);
            sb.append("\",");
            sb.append('\n');
            sb.append(indent);
            sb.append("byteSuffix = \"");
            sb.append(this.byteSuffix);
            sb.append(ToStringStyle.JsonToStringStyle.FIELD_NAME_QUOTE);
            return sb;
        }

        @NotNull
        public final String getBytePrefix() {
            return this.bytePrefix;
        }

        @NotNull
        public final String getByteSeparator() {
            return this.byteSeparator;
        }

        @NotNull
        public final String getByteSuffix() {
            return this.byteSuffix;
        }

        public final int getBytesPerGroup() {
            return this.bytesPerGroup;
        }

        public final int getBytesPerLine() {
            return this.bytesPerLine;
        }

        @NotNull
        public final String getGroupSeparator() {
            return this.groupSeparator;
        }

        public final boolean getIgnoreCase$kotlin_stdlib() {
            return this.ignoreCase;
        }

        public final boolean getNoLineAndGroupSeparator$kotlin_stdlib() {
            return this.noLineAndGroupSeparator;
        }

        public final boolean getShortByteSeparatorNoPrefixAndSuffix$kotlin_stdlib() {
            return this.shortByteSeparatorNoPrefixAndSuffix;
        }

        @NotNull
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("BytesHexFormat(\n");
            appendOptionsTo$kotlin_stdlib(sb, JsonKt.defaultIndent);
            sb.append('\n');
            sb.append(")");
            return sb.toString();
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class Companion {
        public Companion() {
        }

        @NotNull
        public final HexFormat getDefault() {
            return HexFormat.Default;
        }

        @NotNull
        public final HexFormat getUpperCase() {
            return HexFormat.UpperCase;
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
        }
    }

    static {
        BytesHexFormat.Companion companion = BytesHexFormat.Companion;
        companion.getClass();
        BytesHexFormat bytesHexFormat = BytesHexFormat.Default;
        NumberHexFormat.Companion companion2 = NumberHexFormat.Companion;
        companion2.getClass();
        Default = new HexFormat(false, bytesHexFormat, NumberHexFormat.Default);
        companion.getClass();
        BytesHexFormat bytesHexFormat2 = BytesHexFormat.Default;
        companion2.getClass();
        UpperCase = new HexFormat(true, bytesHexFormat2, NumberHexFormat.Default);
    }

    public HexFormat(boolean z, @NotNull BytesHexFormat bytes, @NotNull NumberHexFormat number) {
        Intrinsics.checkNotNullParameter(bytes, "bytes");
        Intrinsics.checkNotNullParameter(number, "number");
        this.upperCase = z;
        this.bytes = bytes;
        this.number = number;
    }

    @NotNull
    public final BytesHexFormat getBytes() {
        return this.bytes;
    }

    @NotNull
    public final NumberHexFormat getNumber() {
        return this.number;
    }

    public final boolean getUpperCase() {
        return this.upperCase;
    }

    @NotNull
    public String toString() {
        StringBuilder sbM = AccessibilityServiceInfoCompat$$ExternalSyntheticOutline0.m("HexFormat(\n    upperCase = ");
        sbM.append(this.upperCase);
        sbM.append(",\n    bytes = BytesHexFormat(\n");
        this.bytes.appendOptionsTo$kotlin_stdlib(sbM, "        ");
        sbM.append('\n');
        sbM.append("    ),");
        sbM.append('\n');
        sbM.append("    number = NumberHexFormat(");
        sbM.append('\n');
        this.number.appendOptionsTo$kotlin_stdlib(sbM, "        ");
        sbM.append('\n');
        sbM.append("    )");
        sbM.append('\n');
        sbM.append(")");
        return sbM.toString();
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class NumberHexFormat {

        @NotNull
        public static final Companion Companion = new Companion();

        @NotNull
        public static final NumberHexFormat Default = new NumberHexFormat("", "", false, 1);
        public final boolean ignoreCase;
        public final boolean isDigitsOnly;
        public final boolean isDigitsOnlyAndNoPadding;
        public final int minLength;

        @NotNull
        public final String prefix;
        public final boolean removeLeadingZeros;

        @NotNull
        public final String suffix;

        /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
        public static final class Companion {
            public Companion() {
            }

            @NotNull
            public final NumberHexFormat getDefault$kotlin_stdlib() {
                return NumberHexFormat.Default;
            }

            public Companion(DefaultConstructorMarker defaultConstructorMarker) {
            }
        }

        public NumberHexFormat(@NotNull String prefix, @NotNull String suffix, boolean z, int i) {
            Intrinsics.checkNotNullParameter(prefix, "prefix");
            Intrinsics.checkNotNullParameter(suffix, "suffix");
            this.prefix = prefix;
            this.suffix = suffix;
            this.removeLeadingZeros = z;
            this.minLength = i;
            boolean z2 = prefix.length() == 0 && suffix.length() == 0;
            this.isDigitsOnly = z2;
            this.isDigitsOnlyAndNoPadding = z2 && i == 1;
            this.ignoreCase = HexFormatKt.isCaseSensitive(prefix) || HexFormatKt.isCaseSensitive(suffix);
        }

        @NotNull
        public final StringBuilder appendOptionsTo$kotlin_stdlib(@NotNull StringBuilder sb, @NotNull String indent) {
            Intrinsics.checkNotNullParameter(sb, "sb");
            Intrinsics.checkNotNullParameter(indent, "indent");
            sb.append(indent);
            sb.append("prefix = \"");
            sb.append(this.prefix);
            sb.append("\",");
            sb.append('\n');
            sb.append(indent);
            sb.append("suffix = \"");
            sb.append(this.suffix);
            sb.append("\",");
            sb.append('\n');
            sb.append(indent);
            sb.append("removeLeadingZeros = ");
            sb.append(this.removeLeadingZeros);
            sb.append(',');
            sb.append('\n');
            sb.append(indent);
            sb.append("minLength = ");
            sb.append(this.minLength);
            return sb;
        }

        public final boolean getIgnoreCase$kotlin_stdlib() {
            return this.ignoreCase;
        }

        public final int getMinLength() {
            return this.minLength;
        }

        @NotNull
        public final String getPrefix() {
            return this.prefix;
        }

        public final boolean getRemoveLeadingZeros() {
            return this.removeLeadingZeros;
        }

        @NotNull
        public final String getSuffix() {
            return this.suffix;
        }

        public final boolean isDigitsOnly$kotlin_stdlib() {
            return this.isDigitsOnly;
        }

        public final boolean isDigitsOnlyAndNoPadding$kotlin_stdlib() {
            return this.isDigitsOnlyAndNoPadding;
        }

        @NotNull
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("NumberHexFormat(\n");
            appendOptionsTo$kotlin_stdlib(sb, JsonKt.defaultIndent);
            sb.append('\n');
            sb.append(")");
            return sb.toString();
        }

        /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
        @SourceDebugExtension({"SMAP\nHexFormat.kt\nKotlin\n*S Kotlin\n*F\n+ 1 HexFormat.kt\nkotlin/text/HexFormat$NumberHexFormat$Builder\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,844:1\n1#2:845\n*E\n"})
        public static final class Builder {
            public int minLength;

            @NotNull
            public String prefix;
            public boolean removeLeadingZeros;

            @NotNull
            public String suffix;

            public Builder() {
                Companion companion = NumberHexFormat.Companion;
                companion.getClass();
                this.prefix = NumberHexFormat.Default.prefix;
                companion.getClass();
                this.suffix = NumberHexFormat.Default.suffix;
                companion.getClass();
                this.removeLeadingZeros = NumberHexFormat.Default.removeLeadingZeros;
                companion.getClass();
                this.minLength = NumberHexFormat.Default.minLength;
            }

            @NotNull
            public final NumberHexFormat build$kotlin_stdlib() {
                return new NumberHexFormat(this.prefix, this.suffix, this.removeLeadingZeros, this.minLength);
            }

            public final int getMinLength() {
                return this.minLength;
            }

            @NotNull
            public final String getPrefix() {
                return this.prefix;
            }

            public final boolean getRemoveLeadingZeros() {
                return this.removeLeadingZeros;
            }

            @NotNull
            public final String getSuffix() {
                return this.suffix;
            }

            public final void setMinLength(int i) {
                if (i <= 0) {
                    throw new IllegalArgumentException(MediaBrowserCompat$MediaBrowserImplBase$$ExternalSyntheticOutline0.m("Non-positive values are prohibited for minLength, but was ", i).toString());
                }
                this.minLength = i;
            }

            public final void setPrefix(@NotNull String value) {
                Intrinsics.checkNotNullParameter(value, "value");
                if (StringsKt__StringsKt.contains$default((CharSequence) value, '\n', false, 2, (Object) null) || StringsKt__StringsKt.contains$default((CharSequence) value, CharUtils.CR, false, 2, (Object) null)) {
                    throw new IllegalArgumentException("LF and CR characters are prohibited in prefix, but was ".concat(value));
                }
                this.prefix = value;
            }

            public final void setRemoveLeadingZeros(boolean z) {
                this.removeLeadingZeros = z;
            }

            public final void setSuffix(@NotNull String value) {
                Intrinsics.checkNotNullParameter(value, "value");
                if (StringsKt__StringsKt.contains$default((CharSequence) value, '\n', false, 2, (Object) null) || StringsKt__StringsKt.contains$default((CharSequence) value, CharUtils.CR, false, 2, (Object) null)) {
                    throw new IllegalArgumentException("LF and CR characters are prohibited in suffix, but was ".concat(value));
                }
                this.suffix = value;
            }

            @SinceKotlin(version = "2.0")
            public static /* synthetic */ void getMinLength$annotations() {
            }
        }

        @SinceKotlin(version = "2.0")
        public static /* synthetic */ void getMinLength$annotations() {
        }
    }
}
