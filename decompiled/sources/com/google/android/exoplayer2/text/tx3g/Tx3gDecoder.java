package com.google.android.exoplayer2.text.tx3g;

import android.support.v4.media.MediaBrowserCompat$CustomActionResultReceiver$$ExternalSyntheticOutline0;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.text.style.TypefaceSpan;
import android.text.style.UnderlineSpan;
import androidx.collection.ObjectListKt$$ExternalSyntheticOutline0;
import com.google.android.exoplayer2.text.Cue;
import com.google.android.exoplayer2.text.SimpleSubtitleDecoder;
import com.google.android.exoplayer2.text.Subtitle;
import com.google.android.exoplayer2.text.SubtitleDecoderException;
import com.google.android.exoplayer2.util.Log;
import com.google.android.exoplayer2.util.ParsableByteArray;
import com.google.android.exoplayer2.util.Util;
import com.google.common.base.Ascii;
import com.google.common.base.Charsets;
import java.nio.charset.Charset;
import java.util.List;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class Tx3gDecoder extends SimpleSubtitleDecoder {
    public static final int DEFAULT_COLOR = -1;
    public static final int DEFAULT_FONT_FACE = 0;
    public static final String DEFAULT_FONT_FAMILY = "sans-serif";
    public static final float DEFAULT_VERTICAL_PLACEMENT = 0.85f;
    public static final int FONT_FACE_BOLD = 1;
    public static final int FONT_FACE_ITALIC = 2;
    public static final int FONT_FACE_UNDERLINE = 4;
    public static final int SIZE_ATOM_HEADER = 8;
    public static final int SIZE_SHORT = 2;
    public static final int SIZE_STYLE_RECORD = 12;
    public static final int SPAN_PRIORITY_HIGH = 0;
    public static final int SPAN_PRIORITY_LOW = 16711680;
    public static final String TAG = "Tx3gDecoder";
    public static final String TX3G_SERIF = "Serif";
    public static final int TYPE_STYL = 1937013100;
    public static final int TYPE_TBOX = 1952608120;
    public final int calculatedVideoTrackHeight;
    public final boolean customVerticalPlacement;
    public final int defaultColorRgba;
    public final int defaultFontFace;
    public final String defaultFontFamily;
    public final float defaultVerticalPlacement;
    public final ParsableByteArray parsableByteArray;

    public Tx3gDecoder(List<byte[]> list) {
        super(TAG);
        this.parsableByteArray = new ParsableByteArray();
        if (list.size() != 1 || (list.get(0).length != 48 && list.get(0).length != 53)) {
            this.defaultFontFace = 0;
            this.defaultColorRgba = -1;
            this.defaultFontFamily = "sans-serif";
            this.customVerticalPlacement = false;
            this.defaultVerticalPlacement = 0.85f;
            this.calculatedVideoTrackHeight = -1;
            return;
        }
        byte[] bArr = list.get(0);
        this.defaultFontFace = bArr[24];
        this.defaultColorRgba = ((bArr[26] & 255) << 24) | ((bArr[27] & 255) << 16) | ((bArr[28] & 255) << 8) | (bArr[29] & 255);
        this.defaultFontFamily = "Serif".equals(Util.fromUtf8Bytes(bArr, 43, bArr.length - 43)) ? "serif" : "sans-serif";
        int i = bArr[25] * Ascii.DC4;
        this.calculatedVideoTrackHeight = i;
        boolean z = (bArr[0] & 32) != 0;
        this.customVerticalPlacement = z;
        if (z) {
            this.defaultVerticalPlacement = Util.constrainValue(((bArr[11] & 255) | ((bArr[10] & 255) << 8)) / i, 0.0f, 0.95f);
        } else {
            this.defaultVerticalPlacement = 0.85f;
        }
    }

    public static void assertTrue(boolean z) throws SubtitleDecoderException {
        if (!z) {
            throw new SubtitleDecoderException("Unexpected subtitle format.");
        }
    }

    public static void attachColor(SpannableStringBuilder spannableStringBuilder, int i, int i2, int i3, int i4, int i5) {
        if (i != i2) {
            spannableStringBuilder.setSpan(new ForegroundColorSpan((i >>> 8) | ((i & 255) << 24)), i3, i4, i5 | 33);
        }
    }

    public static void attachFontFace(SpannableStringBuilder spannableStringBuilder, int i, int i2, int i3, int i4, int i5) {
        if (i != i2) {
            int i6 = i5 | 33;
            boolean z = (i & 1) != 0;
            boolean z2 = (i & 2) != 0;
            if (z) {
                if (z2) {
                    spannableStringBuilder.setSpan(new StyleSpan(3), i3, i4, i6);
                } else {
                    spannableStringBuilder.setSpan(new StyleSpan(1), i3, i4, i6);
                }
            } else if (z2) {
                spannableStringBuilder.setSpan(new StyleSpan(2), i3, i4, i6);
            }
            boolean z3 = (i & 4) != 0;
            if (z3) {
                spannableStringBuilder.setSpan(new UnderlineSpan(), i3, i4, i6);
            }
            if (z3 || z || z2) {
                return;
            }
            spannableStringBuilder.setSpan(new StyleSpan(0), i3, i4, i6);
        }
    }

    public static void attachFontFamily(SpannableStringBuilder spannableStringBuilder, String str, int i, int i2) {
        if (str != "sans-serif") {
            spannableStringBuilder.setSpan(new TypefaceSpan(str), i, i2, 16711713);
        }
    }

    public static String readSubtitleText(ParsableByteArray parsableByteArray) throws SubtitleDecoderException {
        assertTrue(parsableByteArray.bytesLeft() >= 2);
        int unsignedShort = parsableByteArray.readUnsignedShort();
        if (unsignedShort == 0) {
            return "";
        }
        int i = parsableByteArray.position;
        Charset utfCharsetFromBom = parsableByteArray.readUtfCharsetFromBom();
        int i2 = unsignedShort - (parsableByteArray.position - i);
        if (utfCharsetFromBom == null) {
            utfCharsetFromBom = Charsets.UTF_8;
        }
        return parsableByteArray.readString(i2, utfCharsetFromBom);
    }

    public final void applyStyleRecord(ParsableByteArray parsableByteArray, SpannableStringBuilder spannableStringBuilder) throws SubtitleDecoderException {
        assertTrue(parsableByteArray.bytesLeft() >= 12);
        int unsignedShort = parsableByteArray.readUnsignedShort();
        int unsignedShort2 = parsableByteArray.readUnsignedShort();
        parsableByteArray.skipBytes(2);
        int unsignedByte = parsableByteArray.readUnsignedByte();
        parsableByteArray.skipBytes(1);
        int i = parsableByteArray.readInt();
        if (unsignedShort2 > spannableStringBuilder.length()) {
            StringBuilder sbM = MediaBrowserCompat$CustomActionResultReceiver$$ExternalSyntheticOutline0.m("Truncating styl end (", unsignedShort2, ") to cueText.length() (");
            sbM.append(spannableStringBuilder.length());
            sbM.append(").");
            Log.w(TAG, sbM.toString());
            unsignedShort2 = spannableStringBuilder.length();
        }
        int i2 = unsignedShort2;
        if (unsignedShort >= i2) {
            Log.w(TAG, ObjectListKt$$ExternalSyntheticOutline0.m("Ignoring styl with start (", unsignedShort, ") >= end (", i2, ")."));
        } else {
            attachFontFace(spannableStringBuilder, unsignedByte, this.defaultFontFace, unsignedShort, i2, 0);
            attachColor(spannableStringBuilder, i, this.defaultColorRgba, unsignedShort, i2, 0);
        }
    }

    @Override // com.google.android.exoplayer2.text.SimpleSubtitleDecoder
    public Subtitle decode(byte[] bArr, int i, boolean z) throws SubtitleDecoderException {
        this.parsableByteArray.reset(bArr, i);
        String subtitleText = readSubtitleText(this.parsableByteArray);
        if (subtitleText.isEmpty()) {
            return Tx3gSubtitle.EMPTY;
        }
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(subtitleText);
        attachFontFace(spannableStringBuilder, this.defaultFontFace, 0, 0, spannableStringBuilder.length(), 16711680);
        attachColor(spannableStringBuilder, this.defaultColorRgba, -1, 0, spannableStringBuilder.length(), 16711680);
        attachFontFamily(spannableStringBuilder, this.defaultFontFamily, 0, spannableStringBuilder.length());
        float fConstrainValue = this.defaultVerticalPlacement;
        while (this.parsableByteArray.bytesLeft() >= 8) {
            ParsableByteArray parsableByteArray = this.parsableByteArray;
            int i2 = parsableByteArray.position;
            int i3 = parsableByteArray.readInt();
            int i4 = this.parsableByteArray.readInt();
            if (i4 == 1937013100) {
                assertTrue(this.parsableByteArray.bytesLeft() >= 2);
                int unsignedShort = this.parsableByteArray.readUnsignedShort();
                for (int i5 = 0; i5 < unsignedShort; i5++) {
                    applyStyleRecord(this.parsableByteArray, spannableStringBuilder);
                }
            } else if (i4 == 1952608120 && this.customVerticalPlacement) {
                assertTrue(this.parsableByteArray.bytesLeft() >= 2);
                fConstrainValue = Util.constrainValue(this.parsableByteArray.readUnsignedShort() / this.calculatedVideoTrackHeight, 0.0f, 0.95f);
            }
            this.parsableByteArray.setPosition(i2 + i3);
        }
        Cue.Builder builder = new Cue.Builder();
        builder.text = spannableStringBuilder;
        builder.line = fConstrainValue;
        builder.lineType = 0;
        builder.lineAnchor = 0;
        return new Tx3gSubtitle(builder.build());
    }
}
