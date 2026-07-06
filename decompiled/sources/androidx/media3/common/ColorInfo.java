package androidx.media3.common;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.concurrent.futures.AbstractResolvableFuture$$ExternalSyntheticOutline1;
import androidx.media3.common.Bundleable;
import androidx.media3.common.util.UnstableApi;
import androidx.media3.common.util.Util;
import com.amazon.minerva.identifiers.schemaid.SchemaId;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.Arrays;
import java.util.Locale;
import org.checkerframework.checker.nullness.qual.EnsuresNonNullIf;
import org.checkerframework.dataflow.qual.Pure;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@UnstableApi
public final class ColorInfo implements Bundleable {

    @Deprecated
    public static final Bundleable.Creator<ColorInfo> CREATOR;
    public static final String FIELD_CHROMA_BITDEPTH;
    public static final String FIELD_COLOR_RANGE;
    public static final String FIELD_COLOR_SPACE;
    public static final String FIELD_COLOR_TRANSFER;
    public static final String FIELD_HDR_STATIC_INFO;
    public static final String FIELD_LUMA_BITDEPTH;
    public static final ColorInfo SDR_BT709_LIMITED;
    public static final ColorInfo SRGB_BT709_FULL;
    public final int chromaBitdepth;
    public final int colorRange;
    public final int colorSpace;
    public final int colorTransfer;
    public int hashCode;

    @Nullable
    public final byte[] hdrStaticInfo;
    public final int lumaBitdepth;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class Builder {
        public int chromaBitdepth;
        public int colorRange;
        public int colorSpace;
        public int colorTransfer;

        @Nullable
        public byte[] hdrStaticInfo;
        public int lumaBitdepth;

        public ColorInfo build() {
            return new ColorInfo(this.colorSpace, this.colorRange, this.colorTransfer, this.hdrStaticInfo, this.lumaBitdepth, this.chromaBitdepth);
        }

        @CanIgnoreReturnValue
        public Builder setChromaBitdepth(int i) {
            this.chromaBitdepth = i;
            return this;
        }

        @CanIgnoreReturnValue
        public Builder setColorRange(int i) {
            this.colorRange = i;
            return this;
        }

        @CanIgnoreReturnValue
        public Builder setColorSpace(int i) {
            this.colorSpace = i;
            return this;
        }

        @CanIgnoreReturnValue
        public Builder setColorTransfer(int i) {
            this.colorTransfer = i;
            return this;
        }

        @CanIgnoreReturnValue
        public Builder setHdrStaticInfo(@Nullable byte[] bArr) {
            this.hdrStaticInfo = bArr;
            return this;
        }

        @CanIgnoreReturnValue
        public Builder setLumaBitdepth(int i) {
            this.lumaBitdepth = i;
            return this;
        }

        public Builder() {
            this.colorSpace = -1;
            this.colorRange = -1;
            this.colorTransfer = -1;
            this.lumaBitdepth = -1;
            this.chromaBitdepth = -1;
        }

        public Builder(ColorInfo colorInfo) {
            this.colorSpace = colorInfo.colorSpace;
            this.colorRange = colorInfo.colorRange;
            this.colorTransfer = colorInfo.colorTransfer;
            this.hdrStaticInfo = colorInfo.hdrStaticInfo;
            this.lumaBitdepth = colorInfo.lumaBitdepth;
            this.chromaBitdepth = colorInfo.chromaBitdepth;
        }
    }

    static {
        Builder builder = new Builder();
        builder.colorSpace = 1;
        builder.colorRange = 2;
        builder.colorTransfer = 3;
        SDR_BT709_LIMITED = builder.build();
        Builder builder2 = new Builder();
        builder2.colorSpace = 1;
        builder2.colorRange = 1;
        builder2.colorTransfer = 2;
        SRGB_BT709_FULL = builder2.build();
        FIELD_COLOR_SPACE = Util.intToStringMaxRadix(0);
        FIELD_COLOR_RANGE = Integer.toString(1, 36);
        FIELD_COLOR_TRANSFER = Integer.toString(2, 36);
        FIELD_HDR_STATIC_INFO = Integer.toString(3, 36);
        FIELD_LUMA_BITDEPTH = Integer.toString(4, 36);
        FIELD_CHROMA_BITDEPTH = Integer.toString(5, 36);
        CREATOR = new ColorInfo$$ExternalSyntheticLambda0();
    }

    public static String chromaBitdepthToString(int i) {
        if (i == -1) {
            return "NA";
        }
        return i + "bit Chroma";
    }

    public static String colorRangeToString(int i) {
        return i != -1 ? i != 1 ? i != 2 ? "Undefined color range" : "Limited range" : "Full range" : "Unset color range";
    }

    public static String colorSpaceToString(int i) {
        return i != -1 ? i != 6 ? i != 1 ? i != 2 ? "Undefined color space" : "BT601" : "BT709" : "BT2020" : "Unset color space";
    }

    public static String colorTransferToString(int i) {
        return i != -1 ? i != 10 ? i != 1 ? i != 2 ? i != 3 ? i != 6 ? i != 7 ? "Undefined color transfer" : "HLG" : "ST2084 PQ" : "SDR SMPTE 170M" : "sRGB" : "Linear" : "Gamma 2.2" : "Unset color transfer";
    }

    public static ColorInfo fromBundle(Bundle bundle) {
        return new ColorInfo(bundle.getInt(FIELD_COLOR_SPACE, -1), bundle.getInt(FIELD_COLOR_RANGE, -1), bundle.getInt(FIELD_COLOR_TRANSFER, -1), bundle.getByteArray(FIELD_HDR_STATIC_INFO), bundle.getInt(FIELD_LUMA_BITDEPTH, -1), bundle.getInt(FIELD_CHROMA_BITDEPTH, -1));
    }

    @EnsuresNonNullIf(expression = {"#1"}, result = false)
    public static boolean isEquivalentToAssumedSdrDefault(@Nullable ColorInfo colorInfo) {
        if (colorInfo == null) {
            return true;
        }
        int i = colorInfo.colorSpace;
        if (i != -1 && i != 1 && i != 2) {
            return false;
        }
        int i2 = colorInfo.colorRange;
        if (i2 != -1 && i2 != 2) {
            return false;
        }
        int i3 = colorInfo.colorTransfer;
        if ((i3 != -1 && i3 != 3) || colorInfo.hdrStaticInfo != null) {
            return false;
        }
        int i4 = colorInfo.chromaBitdepth;
        if (i4 != -1 && i4 != 8) {
            return false;
        }
        int i5 = colorInfo.lumaBitdepth;
        return i5 == -1 || i5 == 8;
    }

    public static boolean isTransferHdr(@Nullable ColorInfo colorInfo) {
        if (colorInfo == null) {
            return false;
        }
        int i = colorInfo.colorTransfer;
        return i == 7 || i == 6;
    }

    @Pure
    public static int isoColorPrimariesToColorSpace(int i) {
        if (i == 1) {
            return 1;
        }
        if (i != 9) {
            return (i == 4 || i == 5 || i == 6 || i == 7) ? 2 : -1;
        }
        return 6;
    }

    @Pure
    public static int isoTransferCharacteristicsToColorTransfer(int i) {
        if (i == 1) {
            return 3;
        }
        if (i == 4) {
            return 10;
        }
        if (i == 13) {
            return 2;
        }
        if (i == 16) {
            return 6;
        }
        if (i != 18) {
            return (i == 6 || i == 7) ? 3 : -1;
        }
        return 7;
    }

    public static String lumaBitdepthToString(int i) {
        if (i == -1) {
            return "NA";
        }
        return i + "bit Luma";
    }

    public Builder buildUpon() {
        return new Builder(this);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && ColorInfo.class == obj.getClass()) {
            ColorInfo colorInfo = (ColorInfo) obj;
            if (this.colorSpace == colorInfo.colorSpace && this.colorRange == colorInfo.colorRange && this.colorTransfer == colorInfo.colorTransfer && Arrays.equals(this.hdrStaticInfo, colorInfo.hdrStaticInfo) && this.lumaBitdepth == colorInfo.lumaBitdepth && this.chromaBitdepth == colorInfo.chromaBitdepth) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        if (this.hashCode == 0) {
            this.hashCode = ((((Arrays.hashCode(this.hdrStaticInfo) + ((((((527 + this.colorSpace) * 31) + this.colorRange) * 31) + this.colorTransfer) * 31)) * 31) + this.lumaBitdepth) * 31) + this.chromaBitdepth;
        }
        return this.hashCode;
    }

    public boolean isBitdepthValid() {
        return (this.lumaBitdepth == -1 || this.chromaBitdepth == -1) ? false : true;
    }

    public boolean isDataSpaceValid() {
        return (this.colorSpace == -1 || this.colorRange == -1 || this.colorTransfer == -1) ? false : true;
    }

    public boolean isValid() {
        return isBitdepthValid() || isDataSpaceValid();
    }

    @Override // androidx.media3.common.Bundleable
    public Bundle toBundle() {
        Bundle bundle = new Bundle();
        bundle.putInt(FIELD_COLOR_SPACE, this.colorSpace);
        bundle.putInt(FIELD_COLOR_RANGE, this.colorRange);
        bundle.putInt(FIELD_COLOR_TRANSFER, this.colorTransfer);
        bundle.putByteArray(FIELD_HDR_STATIC_INFO, this.hdrStaticInfo);
        bundle.putInt(FIELD_LUMA_BITDEPTH, this.lumaBitdepth);
        bundle.putInt(FIELD_CHROMA_BITDEPTH, this.chromaBitdepth);
        return bundle;
    }

    public String toLogString() {
        String str;
        String str2 = isDataSpaceValid() ? String.format(Locale.US, "%s/%s/%s", colorSpaceToString(this.colorSpace), colorRangeToString(this.colorRange), colorTransferToString(this.colorTransfer)) : "NA/NA/NA";
        if (isBitdepthValid()) {
            str = this.lumaBitdepth + SchemaId.METRIC_SCHEMA_ID_DELIMITER + this.chromaBitdepth;
        } else {
            str = "NA/NA";
        }
        return AbstractResolvableFuture$$ExternalSyntheticOutline1.m(str2, SchemaId.METRIC_SCHEMA_ID_DELIMITER, str);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("ColorInfo(");
        sb.append(colorSpaceToString(this.colorSpace));
        sb.append(", ");
        sb.append(colorRangeToString(this.colorRange));
        sb.append(", ");
        sb.append(colorTransferToString(this.colorTransfer));
        sb.append(", ");
        sb.append(this.hdrStaticInfo != null);
        sb.append(", ");
        sb.append(lumaBitdepthToString(this.lumaBitdepth));
        sb.append(", ");
        sb.append(chromaBitdepthToString(this.chromaBitdepth));
        sb.append(")");
        return sb.toString();
    }

    public ColorInfo(int i, int i2, int i3, @Nullable byte[] bArr, int i4, int i5) {
        this.colorSpace = i;
        this.colorRange = i2;
        this.colorTransfer = i3;
        this.hdrStaticInfo = bArr;
        this.lumaBitdepth = i4;
        this.chromaBitdepth = i5;
    }
}
