package com.google.android.exoplayer2.text.subrip;

import android.text.Html;
import android.text.Spanned;
import android.text.TextUtils;
import com.google.android.exoplayer2.text.Cue;
import com.google.android.exoplayer2.text.SimpleSubtitleDecoder;
import com.google.android.exoplayer2.text.Subtitle;
import com.google.android.exoplayer2.util.Log;
import com.google.android.exoplayer2.util.LongArray;
import com.google.android.exoplayer2.util.ParsableByteArray;
import com.google.common.base.Charsets;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.lang3.time.DateUtils;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class SubripDecoder extends SimpleSubtitleDecoder {
    public static final String ALIGN_BOTTOM_LEFT = "{\\an1}";
    public static final String ALIGN_BOTTOM_MID = "{\\an2}";
    public static final String ALIGN_BOTTOM_RIGHT = "{\\an3}";
    public static final String ALIGN_MID_LEFT = "{\\an4}";
    public static final String ALIGN_MID_MID = "{\\an5}";
    public static final String ALIGN_MID_RIGHT = "{\\an6}";
    public static final String ALIGN_TOP_LEFT = "{\\an7}";
    public static final String ALIGN_TOP_MID = "{\\an8}";
    public static final String ALIGN_TOP_RIGHT = "{\\an9}";
    public static final float END_FRACTION = 0.92f;
    public static final float MID_FRACTION = 0.5f;
    public static final float START_FRACTION = 0.08f;
    public static final String SUBRIP_ALIGNMENT_TAG = "\\{\\\\an[1-9]\\}";
    public static final String SUBRIP_TIMECODE = "(?:(\\d+):)?(\\d+):(\\d+)(?:,(\\d+))?";
    public static final String TAG = "SubripDecoder";
    public final ArrayList<String> tags;
    public final StringBuilder textBuilder;
    public static final Pattern SUBRIP_TIMING_LINE = Pattern.compile("\\s*((?:(\\d+):)?(\\d+):(\\d+)(?:,(\\d+))?)\\s*-->\\s*((?:(\\d+):)?(\\d+):(\\d+)(?:,(\\d+))?)\\s*");
    public static final Pattern SUBRIP_TAG_PATTERN = Pattern.compile("\\{\\\\.*?\\}");

    public SubripDecoder() {
        super(TAG);
        this.textBuilder = new StringBuilder();
        this.tags = new ArrayList<>();
    }

    private Charset detectUtfCharset(ParsableByteArray parsableByteArray) {
        Charset utfCharsetFromBom = parsableByteArray.readUtfCharsetFromBom();
        return utfCharsetFromBom != null ? utfCharsetFromBom : Charsets.UTF_8;
    }

    public static float getFractionalPositionForAnchorType(int i) {
        if (i == 0) {
            return 0.08f;
        }
        if (i == 1) {
            return 0.5f;
        }
        if (i == 2) {
            return 0.92f;
        }
        throw new IllegalArgumentException();
    }

    public static long parseTimecode(Matcher matcher, int i) {
        String strGroup = matcher.group(i + 1);
        long j = strGroup != null ? Long.parseLong(strGroup) * DateUtils.MILLIS_PER_HOUR : 0L;
        String strGroup2 = matcher.group(i + 2);
        strGroup2.getClass();
        long j2 = (Long.parseLong(strGroup2) * 60000) + j;
        String strGroup3 = matcher.group(i + 3);
        strGroup3.getClass();
        long j3 = (Long.parseLong(strGroup3) * 1000) + j2;
        String strGroup4 = matcher.group(i + 4);
        if (strGroup4 != null) {
            j3 += Long.parseLong(strGroup4);
        }
        return j3 * 1000;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    /* JADX WARN: Removed duplicated region for block: B:25:0x0057  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x0065  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x0068  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x0086  */
    /* JADX WARN: Removed duplicated region for block: B:54:0x00ac  */
    /* JADX WARN: Removed duplicated region for block: B:55:0x00af  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final com.google.android.exoplayer2.text.Cue buildCue(android.text.Spanned r14, @androidx.annotation.Nullable java.lang.String r15) {
        /*
            Method dump skipped, instruction units count: 276
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.text.subrip.SubripDecoder.buildCue(android.text.Spanned, java.lang.String):com.google.android.exoplayer2.text.Cue");
    }

    @Override // com.google.android.exoplayer2.text.SimpleSubtitleDecoder
    public Subtitle decode(byte[] bArr, int i, boolean z) {
        String str;
        ArrayList arrayList = new ArrayList();
        LongArray longArray = new LongArray();
        ParsableByteArray parsableByteArray = new ParsableByteArray(bArr, i);
        Charset utfCharsetFromBom = parsableByteArray.readUtfCharsetFromBom();
        if (utfCharsetFromBom == null) {
            utfCharsetFromBom = Charsets.UTF_8;
        }
        while (true) {
            String line = parsableByteArray.readLine(utfCharsetFromBom);
            int i2 = 0;
            if (line == null) {
                break;
            }
            if (line.length() != 0) {
                try {
                    Integer.parseInt(line);
                    String line2 = parsableByteArray.readLine(utfCharsetFromBom);
                    if (line2 == null) {
                        Log.w(TAG, "Unexpected end");
                        break;
                    }
                    Matcher matcher = SUBRIP_TIMING_LINE.matcher(line2);
                    if (matcher.matches()) {
                        longArray.add(parseTimecode(matcher, 1));
                        longArray.add(parseTimecode(matcher, 6));
                        this.textBuilder.setLength(0);
                        this.tags.clear();
                        for (String line3 = parsableByteArray.readLine(utfCharsetFromBom); !TextUtils.isEmpty(line3); line3 = parsableByteArray.readLine(utfCharsetFromBom)) {
                            if (this.textBuilder.length() > 0) {
                                this.textBuilder.append("<br>");
                            }
                            this.textBuilder.append(processLine(line3, this.tags));
                        }
                        Spanned spannedFromHtml = Html.fromHtml(this.textBuilder.toString());
                        while (true) {
                            if (i2 >= this.tags.size()) {
                                str = null;
                                break;
                            }
                            str = this.tags.get(i2);
                            if (str.matches("\\{\\\\an[1-9]\\}")) {
                                break;
                            }
                            i2++;
                        }
                        arrayList.add(buildCue(spannedFromHtml, str));
                        arrayList.add(Cue.EMPTY);
                    } else {
                        Log.w(TAG, "Skipping invalid timing: ".concat(line2));
                    }
                } catch (NumberFormatException unused) {
                    Log.w(TAG, "Skipping invalid index: ".concat(line));
                }
            }
        }
        return new SubripSubtitle((Cue[]) arrayList.toArray(new Cue[0]), longArray.toArray());
    }

    public final String processLine(String str, ArrayList<String> arrayList) {
        String strTrim = str.trim();
        StringBuilder sb = new StringBuilder(strTrim);
        Matcher matcher = SUBRIP_TAG_PATTERN.matcher(strTrim);
        int i = 0;
        while (matcher.find()) {
            String strGroup = matcher.group();
            arrayList.add(strGroup);
            int iStart = matcher.start() - i;
            int length = strGroup.length();
            sb.replace(iStart, iStart + length, "");
            i += length;
        }
        return sb.toString();
    }
}
