package androidx.media3.extractor.text.subrip;

import android.text.Html;
import android.text.Spanned;
import android.text.TextUtils;
import androidx.annotation.VisibleForTesting;
import androidx.media3.common.util.Consumer;
import androidx.media3.common.util.Log;
import androidx.media3.common.util.ParsableByteArray;
import androidx.media3.common.util.UnstableApi;
import androidx.media3.extractor.text.CuesWithTiming;
import androidx.media3.extractor.text.Subtitle;
import androidx.media3.extractor.text.SubtitleParser;
import com.google.common.base.Charsets;
import com.google.common.collect.ImmutableList;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.lang3.time.DateUtils;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@UnstableApi
public final class SubripParser implements SubtitleParser {
    public static final String ALIGN_BOTTOM_LEFT = "{\\an1}";
    public static final String ALIGN_BOTTOM_MID = "{\\an2}";
    public static final String ALIGN_BOTTOM_RIGHT = "{\\an3}";
    public static final String ALIGN_MID_LEFT = "{\\an4}";
    public static final String ALIGN_MID_MID = "{\\an5}";
    public static final String ALIGN_MID_RIGHT = "{\\an6}";
    public static final String ALIGN_TOP_LEFT = "{\\an7}";
    public static final String ALIGN_TOP_MID = "{\\an8}";
    public static final String ALIGN_TOP_RIGHT = "{\\an9}";
    public static final int CUE_REPLACEMENT_BEHAVIOR = 1;
    public static final float END_FRACTION = 0.92f;
    public static final float MID_FRACTION = 0.5f;
    public static final float START_FRACTION = 0.08f;
    public static final String SUBRIP_ALIGNMENT_TAG = "\\{\\\\an[1-9]\\}";
    public static final String SUBRIP_TIMECODE = "(?:(\\d+):)?(\\d+):(\\d+)(?:,(\\d+))?";
    public static final String TAG = "SubripParser";
    public static final Pattern SUBRIP_TIMING_LINE = Pattern.compile("\\s*((?:(\\d+):)?(\\d+):(\\d+)(?:,(\\d+))?)\\s*-->\\s*((?:(\\d+):)?(\\d+):(\\d+)(?:,(\\d+))?)\\s*");
    public static final Pattern SUBRIP_TAG_PATTERN = Pattern.compile("\\{\\\\.*?\\}");
    public final StringBuilder textBuilder = new StringBuilder();
    public final ArrayList<String> tags = new ArrayList<>();
    public final ParsableByteArray parsableByteArray = new ParsableByteArray();

    private Charset detectUtfCharset(ParsableByteArray parsableByteArray) {
        Charset utfCharsetFromBom = parsableByteArray.readUtfCharsetFromBom();
        return utfCharsetFromBom != null ? utfCharsetFromBom : Charsets.UTF_8;
    }

    @VisibleForTesting(otherwise = 2)
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
    /* JADX WARN: Removed duplicated region for block: B:25:0x0060  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x006e  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x0071  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x008f  */
    /* JADX WARN: Removed duplicated region for block: B:54:0x00b5  */
    /* JADX WARN: Removed duplicated region for block: B:55:0x00b8  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final androidx.media3.common.text.Cue buildCue(android.text.Spanned r14, @androidx.annotation.Nullable java.lang.String r15) {
        /*
            Method dump skipped, instruction units count: 286
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media3.extractor.text.subrip.SubripParser.buildCue(android.text.Spanned, java.lang.String):androidx.media3.common.text.Cue");
    }

    @Override // androidx.media3.extractor.text.SubtitleParser
    public int getCueReplacementBehavior() {
        return 1;
    }

    @Override // androidx.media3.extractor.text.SubtitleParser
    public /* synthetic */ void parse(byte[] bArr, SubtitleParser.OutputOptions outputOptions, Consumer consumer) {
        parse(bArr, 0, bArr.length, outputOptions, consumer);
    }

    @Override // androidx.media3.extractor.text.SubtitleParser
    public /* synthetic */ Subtitle parseToLegacySubtitle(byte[] bArr, int i, int i2) {
        return SubtitleParser.CC.$default$parseToLegacySubtitle(this, bArr, i, i2);
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

    @Override // androidx.media3.extractor.text.SubtitleParser
    public void parse(byte[] bArr, int i, int i2, SubtitleParser.OutputOptions outputOptions, Consumer<CuesWithTiming> consumer) {
        int i3;
        String str;
        this.parsableByteArray.reset(bArr, i + i2);
        this.parsableByteArray.setPosition(i);
        Charset charsetDetectUtfCharset = detectUtfCharset(this.parsableByteArray);
        long j = -9223372036854775807L;
        ArrayList arrayList = (outputOptions.startTimeUs == -9223372036854775807L || !outputOptions.outputAllCues) ? null : new ArrayList();
        while (true) {
            String line = this.parsableByteArray.readLine(charsetDetectUtfCharset);
            i3 = 0;
            if (line == null) {
                break;
            }
            if (line.length() != 0) {
                try {
                    Integer.parseInt(line);
                    String line2 = this.parsableByteArray.readLine(charsetDetectUtfCharset);
                    if (line2 == null) {
                        Log.w(TAG, "Unexpected end");
                        break;
                    }
                    Matcher matcher = SUBRIP_TIMING_LINE.matcher(line2);
                    if (matcher.matches()) {
                        long timecode = parseTimecode(matcher, 1);
                        long timecode2 = parseTimecode(matcher, 6);
                        this.textBuilder.setLength(0);
                        this.tags.clear();
                        String line3 = this.parsableByteArray.readLine(charsetDetectUtfCharset);
                        while (!TextUtils.isEmpty(line3)) {
                            if (this.textBuilder.length() > 0) {
                                this.textBuilder.append("<br>");
                            }
                            this.textBuilder.append(processLine(line3, this.tags));
                            line3 = this.parsableByteArray.readLine(charsetDetectUtfCharset);
                        }
                        Spanned spannedFromHtml = Html.fromHtml(this.textBuilder.toString());
                        while (true) {
                            if (i3 >= this.tags.size()) {
                                str = null;
                                break;
                            }
                            str = this.tags.get(i3);
                            if (str.matches("\\{\\\\an[1-9]\\}")) {
                                break;
                            } else {
                                i3++;
                            }
                        }
                        long j2 = j;
                        long j3 = outputOptions.startTimeUs;
                        if (j3 == j2 || timecode >= j3) {
                            consumer.accept(new CuesWithTiming(ImmutableList.of(buildCue(spannedFromHtml, str)), timecode, timecode2 - timecode));
                        } else if (arrayList != null) {
                            arrayList.add(new CuesWithTiming(ImmutableList.of(buildCue(spannedFromHtml, str)), timecode, timecode2 - timecode));
                        }
                        j = j2;
                    } else {
                        Log.w(TAG, "Skipping invalid timing: ".concat(line2));
                    }
                } catch (NumberFormatException unused) {
                    Log.w(TAG, "Skipping invalid index: ".concat(line));
                }
            }
        }
        if (arrayList != null) {
            int size = arrayList.size();
            while (i3 < size) {
                Object obj = arrayList.get(i3);
                i3++;
                consumer.accept((CuesWithTiming) obj);
            }
        }
    }

    @Override // androidx.media3.extractor.text.SubtitleParser
    public /* synthetic */ void reset() {
    }
}
