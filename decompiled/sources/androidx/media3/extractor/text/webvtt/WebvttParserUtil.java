package androidx.media3.extractor.text.webvtt;

import androidx.annotation.Nullable;
import androidx.media3.common.ParserException;
import androidx.media3.common.util.ParsableByteArray;
import androidx.media3.common.util.UnstableApi;
import androidx.media3.common.util.Util;
import com.google.common.base.Charsets;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@UnstableApi
public final class WebvttParserUtil {
    public static final Pattern COMMENT = Pattern.compile("^NOTE([ \t].*)?$");
    public static final String WEBVTT_HEADER = "WEBVTT";

    @Nullable
    public static Matcher findNextCueHeader(ParsableByteArray parsableByteArray) {
        String line;
        while (true) {
            parsableByteArray.getClass();
            String line2 = parsableByteArray.readLine(Charsets.UTF_8);
            if (line2 == null) {
                return null;
            }
            if (COMMENT.matcher(line2).matches()) {
                do {
                    line = parsableByteArray.readLine(Charsets.UTF_8);
                    if (line != null) {
                    }
                } while (!line.isEmpty());
            } else {
                Matcher matcher = WebvttCueParser.CUE_HEADER_PATTERN.matcher(line2);
                if (matcher.matches()) {
                    return matcher;
                }
            }
        }
    }

    public static boolean isWebvttHeaderLine(ParsableByteArray parsableByteArray) {
        parsableByteArray.getClass();
        String line = parsableByteArray.readLine(Charsets.UTF_8);
        return line != null && line.startsWith("WEBVTT");
    }

    public static float parsePercentage(String str) throws NumberFormatException {
        if (str.endsWith("%")) {
            return Float.parseFloat(str.substring(0, str.length() - 1)) / 100.0f;
        }
        throw new NumberFormatException("Percentages must end with %");
    }

    public static long parseTimestampUs(String str) throws NumberFormatException {
        String[] strArrSplitAtFirst = Util.splitAtFirst(str, "\\.");
        long j = 0;
        for (String str2 : strArrSplitAtFirst[0].split(":", -1)) {
            j = (j * 60) + Long.parseLong(str2);
        }
        long j2 = j * 1000;
        if (strArrSplitAtFirst.length == 2) {
            j2 += Long.parseLong(strArrSplitAtFirst[1]);
        }
        return j2 * 1000;
    }

    public static void validateWebvttHeaderLine(ParsableByteArray parsableByteArray) throws ParserException {
        int i = parsableByteArray.position;
        if (isWebvttHeaderLine(parsableByteArray)) {
            return;
        }
        parsableByteArray.setPosition(i);
        throw ParserException.createForMalformedContainer("Expected WEBVTT. Got " + parsableByteArray.readLine(Charsets.UTF_8), null);
    }
}
