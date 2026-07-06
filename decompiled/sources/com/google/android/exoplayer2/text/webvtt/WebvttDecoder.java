package com.google.android.exoplayer2.text.webvtt;

import android.text.TextUtils;
import com.google.android.exoplayer2.ParserException;
import com.google.android.exoplayer2.text.SimpleSubtitleDecoder;
import com.google.android.exoplayer2.text.Subtitle;
import com.google.android.exoplayer2.text.SubtitleDecoderException;
import com.google.android.exoplayer2.util.ParsableByteArray;
import com.google.common.base.Charsets;
import java.util.ArrayList;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class WebvttDecoder extends SimpleSubtitleDecoder {
    public static final String COMMENT_START = "NOTE";
    public static final int EVENT_COMMENT = 1;
    public static final int EVENT_CUE = 3;
    public static final int EVENT_END_OF_FILE = 0;
    public static final int EVENT_NONE = -1;
    public static final int EVENT_STYLE_BLOCK = 2;
    public static final String STYLE_START = "STYLE";
    public final WebvttCssParser cssParser;
    public final ParsableByteArray parsableWebvttData;

    public WebvttDecoder() {
        super("WebvttDecoder");
        this.parsableWebvttData = new ParsableByteArray();
        this.cssParser = new WebvttCssParser();
    }

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

    @Override // com.google.android.exoplayer2.text.SimpleSubtitleDecoder
    public Subtitle decode(byte[] bArr, int i, boolean z) throws SubtitleDecoderException {
        ParsableByteArray parsableByteArray;
        WebvttCueInfo cue;
        this.parsableWebvttData.reset(bArr, i);
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
                    return new WebvttSubtitle(arrayList2);
                }
                if (nextEvent == 1) {
                    skipComment(this.parsableWebvttData);
                } else if (nextEvent == 2) {
                    if (!arrayList2.isEmpty()) {
                        throw new SubtitleDecoderException("A style block was found after the first cue.");
                    }
                    this.parsableWebvttData.readLine();
                    arrayList.addAll(this.cssParser.parseBlock(this.parsableWebvttData));
                } else if (nextEvent == 3 && (cue = WebvttCueParser.parseCue(this.parsableWebvttData, arrayList)) != null) {
                    arrayList2.add(cue);
                }
            }
        } catch (ParserException e) {
            throw new SubtitleDecoderException(e);
        }
    }
}
