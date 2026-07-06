package androidx.media3.extractor.text;

import androidx.media3.common.text.Cue;
import androidx.media3.common.util.Consumer;
import androidx.media3.common.util.UnstableApi;
import androidx.media3.extractor.text.SubtitleParser;
import java.util.List;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@UnstableApi
public class LegacySubtitleUtil {
    public static int getStartIndex(Subtitle subtitle, SubtitleParser.OutputOptions outputOptions) {
        long j = outputOptions.startTimeUs;
        if (j == -9223372036854775807L) {
            return 0;
        }
        int nextEventTimeIndex = subtitle.getNextEventTimeIndex(j);
        return nextEventTimeIndex == -1 ? subtitle.getEventTimeCount() : (nextEventTimeIndex <= 0 || subtitle.getEventTime(nextEventTimeIndex + (-1)) != outputOptions.startTimeUs) ? nextEventTimeIndex : nextEventTimeIndex - 1;
    }

    public static void outputSubtitleEvent(Subtitle subtitle, int i, Consumer<CuesWithTiming> consumer) {
        long eventTime = subtitle.getEventTime(i);
        List<Cue> cues = subtitle.getCues(eventTime);
        if (cues.isEmpty()) {
            return;
        }
        if (i == subtitle.getEventTimeCount() - 1) {
            throw new IllegalStateException();
        }
        long eventTime2 = subtitle.getEventTime(i + 1) - subtitle.getEventTime(i);
        if (eventTime2 > 0) {
            consumer.accept(new CuesWithTiming(cues, eventTime, eventTime2));
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x0036  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static void toCuesWithTiming(androidx.media3.extractor.text.Subtitle r13, androidx.media3.extractor.text.SubtitleParser.OutputOptions r14, androidx.media3.common.util.Consumer<androidx.media3.extractor.text.CuesWithTiming> r15) {
        /*
            int r0 = getStartIndex(r13, r14)
            long r1 = r14.startTimeUs
            r3 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
            r5 = 0
            int r6 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1))
            if (r6 == 0) goto L36
            java.util.List r8 = r13.getCues(r1)
            long r1 = r13.getEventTime(r0)
            boolean r3 = r8.isEmpty()
            if (r3 != 0) goto L36
            int r3 = r13.getEventTimeCount()
            if (r0 >= r3) goto L36
            long r9 = r14.startTimeUs
            int r3 = (r9 > r1 ? 1 : (r9 == r1 ? 0 : -1))
            if (r3 >= 0) goto L36
            androidx.media3.extractor.text.CuesWithTiming r7 = new androidx.media3.extractor.text.CuesWithTiming
            long r11 = r1 - r9
            r7.<init>(r8, r9, r11)
            r15.accept(r7)
            r1 = 1
            goto L37
        L36:
            r1 = 0
        L37:
            r2 = r0
        L38:
            int r3 = r13.getEventTimeCount()
            if (r2 >= r3) goto L44
            outputSubtitleEvent(r13, r2, r15)
            int r2 = r2 + 1
            goto L38
        L44:
            boolean r2 = r14.outputAllCues
            if (r2 == 0) goto L70
            if (r1 == 0) goto L4c
            int r0 = r0 + (-1)
        L4c:
            if (r5 >= r0) goto L54
            outputSubtitleEvent(r13, r5, r15)
            int r5 = r5 + 1
            goto L4c
        L54:
            if (r1 == 0) goto L70
            androidx.media3.extractor.text.CuesWithTiming r6 = new androidx.media3.extractor.text.CuesWithTiming
            long r1 = r14.startTimeUs
            java.util.List r7 = r13.getCues(r1)
            long r8 = r13.getEventTime(r0)
            long r1 = r14.startTimeUs
            long r13 = r13.getEventTime(r0)
            long r10 = r1 - r13
            r6.<init>(r7, r8, r10)
            r15.accept(r6)
        L70:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media3.extractor.text.LegacySubtitleUtil.toCuesWithTiming(androidx.media3.extractor.text.Subtitle, androidx.media3.extractor.text.SubtitleParser$OutputOptions, androidx.media3.common.util.Consumer):void");
    }
}
