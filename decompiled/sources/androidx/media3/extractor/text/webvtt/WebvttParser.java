package androidx.media3.extractor.text.webvtt;

import android.text.TextUtils;
import androidx.media3.common.ParserException;
import androidx.media3.common.util.Consumer;
import androidx.media3.common.util.ParsableByteArray;
import androidx.media3.common.util.UnstableApi;
import androidx.media3.extractor.text.CuesWithTiming;
import androidx.media3.extractor.text.LegacySubtitleUtil;
import androidx.media3.extractor.text.Subtitle;
import androidx.media3.extractor.text.SubtitleParser;
import com.google.common.base.Charsets;
import java.util.ArrayList;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@UnstableApi
public final class WebvttParser implements SubtitleParser {
    public static final String COMMENT_START = "NOTE";
    public static final int CUE_REPLACEMENT_BEHAVIOR = 1;
    public static final int EVENT_COMMENT = 1;
    public static final int EVENT_CUE = 3;
    public static final int EVENT_END_OF_FILE = 0;
    public static final int EVENT_NONE = -1;
    public static final int EVENT_STYLE_BLOCK = 2;
    public static final String STYLE_START = "STYLE";
    public final ParsableByteArray parsableWebvttData = new ParsableByteArray();
    public final WebvttCssParser cssParser = new WebvttCssParser();

    public static int getNextEvent(ParsableByteArray parsableByteArray) {
        int i = -1;
        int i2 = 0;
        while (i == -1) {
            i2 = parsableByteArray.position;
            String line = parsableByteArray.readLine(Charsets.UTF_8);
            i = line == null ? 0 : "STYLE".equals(line) ? 2 : line.startsWith("NOTE") ? 1 : 3;
        }
        parsableByteArray.setPosition(i2);
        return i;
    }

    public static void skipComment(ParsableByteArray parsableByteArray) {
        do {
            parsableByteArray.getClass();
        } while (!TextUtils.isEmpty(parsableByteArray.readLine(Charsets.UTF_8)));
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

    @Override // androidx.media3.extractor.text.SubtitleParser
    public void parse(byte[] bArr, int i, int i2, SubtitleParser.OutputOptions outputOptions, Consumer<CuesWithTiming> consumer) {
        ParsableByteArray parsableByteArray;
        WebvttCueInfo cue;
        this.parsableWebvttData.reset(bArr, i2 + i);
        this.parsableWebvttData.setPosition(i);
        ArrayList arrayList = new ArrayList();
        try {
            WebvttParserUtil.validateWebvttHeaderLine(this.parsableWebvttData);
            do {
                parsableByteArray = this.parsableWebvttData;
                parsableByteArray.getClass();
            } while (!TextUtils.isEmpty(parsableByteArray.readLine(Charsets.UTF_8)));
            ArrayList arrayList2 = new ArrayList();
            while (true) {
                int nextEvent = getNextEvent(this.parsableWebvttData);
                if (nextEvent == 0) {
                    LegacySubtitleUtil.toCuesWithTiming(new WebvttSubtitle(arrayList2), outputOptions, consumer);
                    return;
                }
                if (nextEvent == 1) {
                    skipComment(this.parsableWebvttData);
                } else if (nextEvent == 2) {
                    if (!arrayList2.isEmpty()) {
                        throw new IllegalArgumentException("A style block was found after the first cue.");
                    }
                    this.parsableWebvttData.readLine();
                    arrayList.addAll(this.cssParser.parseBlock(this.parsableWebvttData));
                } else if (nextEvent == 3 && (cue = WebvttCueParser.parseCue(this.parsableWebvttData, arrayList)) != null) {
                    arrayList2.add(cue);
                }
            }
        } catch (ParserException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override // androidx.media3.extractor.text.SubtitleParser
    public /* synthetic */ void reset() {
    }
}
