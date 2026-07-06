package androidx.media3.extractor.text.webvtt;

import androidx.media3.common.text.Cue;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Consumer;
import androidx.media3.common.util.ParsableByteArray;
import androidx.media3.common.util.UnstableApi;
import androidx.media3.common.util.Util;
import androidx.media3.extractor.text.CuesWithTiming;
import androidx.media3.extractor.text.Subtitle;
import androidx.media3.extractor.text.SubtitleParser;
import java.util.ArrayList;
import java.util.Collections;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@UnstableApi
public final class Mp4WebvttParser implements SubtitleParser {
    public static final int BOX_HEADER_SIZE = 8;
    public static final int CUE_REPLACEMENT_BEHAVIOR = 2;
    public static final int TYPE_payl = 1885436268;
    public static final int TYPE_sttg = 1937011815;
    public static final int TYPE_vttc = 1987343459;
    public final ParsableByteArray parsableByteArray = new ParsableByteArray();

    public static Cue parseVttCueBox(ParsableByteArray parsableByteArray, int i) {
        CharSequence cueText = null;
        Cue.Builder cueSettingsList = null;
        while (i > 0) {
            Assertions.checkArgument(i >= 8, "Incomplete vtt cue box header found.");
            int i2 = parsableByteArray.readInt();
            int i3 = parsableByteArray.readInt();
            int i4 = i2 - 8;
            String strFromUtf8Bytes = Util.fromUtf8Bytes(parsableByteArray.data, parsableByteArray.position, i4);
            parsableByteArray.skipBytes(i4);
            i = (i - 8) - i4;
            if (i3 == 1937011815) {
                cueSettingsList = WebvttCueParser.parseCueSettingsList(strFromUtf8Bytes);
            } else if (i3 == 1885436268) {
                cueText = WebvttCueParser.parseCueText(null, strFromUtf8Bytes.trim(), Collections.EMPTY_LIST);
            }
        }
        if (cueText == null) {
            cueText = "";
        }
        if (cueSettingsList == null) {
            return WebvttCueParser.newCueForText(cueText);
        }
        cueSettingsList.text = cueText;
        return cueSettingsList.build();
    }

    @Override // androidx.media3.extractor.text.SubtitleParser
    public int getCueReplacementBehavior() {
        return 2;
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
        this.parsableByteArray.reset(bArr, i2 + i);
        this.parsableByteArray.setPosition(i);
        ArrayList arrayList = new ArrayList();
        while (this.parsableByteArray.bytesLeft() > 0) {
            Assertions.checkArgument(this.parsableByteArray.bytesLeft() >= 8, "Incomplete Mp4Webvtt Top Level box header found.");
            int i3 = this.parsableByteArray.readInt();
            if (this.parsableByteArray.readInt() == 1987343459) {
                arrayList.add(parseVttCueBox(this.parsableByteArray, i3 - 8));
            } else {
                this.parsableByteArray.skipBytes(i3 - 8);
            }
        }
        consumer.accept(new CuesWithTiming(arrayList, -9223372036854775807L, -9223372036854775807L));
    }

    @Override // androidx.media3.extractor.text.SubtitleParser
    public /* synthetic */ void reset() {
    }
}
