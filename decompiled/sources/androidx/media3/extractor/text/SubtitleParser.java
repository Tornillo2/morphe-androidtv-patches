package androidx.media3.extractor.text;

import androidx.media3.common.Format;
import androidx.media3.common.util.Consumer;
import androidx.media3.common.util.UnstableApi;
import com.google.common.collect.ImmutableList;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@UnstableApi
public interface SubtitleParser {

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public interface Factory {
        public static final Factory UNSUPPORTED = new AnonymousClass1();

        /* JADX INFO: renamed from: androidx.media3.extractor.text.SubtitleParser$Factory$1, reason: invalid class name */
        /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
        public class AnonymousClass1 implements Factory {
            @Override // androidx.media3.extractor.text.SubtitleParser.Factory
            public SubtitleParser create(Format format) {
                throw new IllegalStateException("This SubtitleParser.Factory doesn't support any formats.");
            }

            @Override // androidx.media3.extractor.text.SubtitleParser.Factory
            public int getCueReplacementBehavior(Format format) {
                return 1;
            }

            @Override // androidx.media3.extractor.text.SubtitleParser.Factory
            public boolean supportsFormat(Format format) {
                return false;
            }
        }

        SubtitleParser create(Format format);

        int getCueReplacementBehavior(Format format);

        boolean supportsFormat(Format format);
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static class OutputOptions {
        public static final OutputOptions ALL = new OutputOptions(-9223372036854775807L, false);
        public final boolean outputAllCues;
        public final long startTimeUs;

        public OutputOptions(long j, boolean z) {
            this.startTimeUs = j;
            this.outputAllCues = z;
        }

        public static OutputOptions allCues() {
            return ALL;
        }

        public static OutputOptions cuesAfterThenRemainingCuesBefore(long j) {
            return new OutputOptions(j, true);
        }

        public static OutputOptions onlyCuesAfter(long j) {
            return new OutputOptions(j, false);
        }
    }

    int getCueReplacementBehavior();

    void parse(byte[] bArr, int i, int i2, OutputOptions outputOptions, Consumer<CuesWithTiming> consumer);

    void parse(byte[] bArr, OutputOptions outputOptions, Consumer<CuesWithTiming> consumer);

    Subtitle parseToLegacySubtitle(byte[] bArr, int i, int i2);

    void reset();

    /* JADX INFO: renamed from: androidx.media3.extractor.text.SubtitleParser$-CC, reason: invalid class name */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public final /* synthetic */ class CC {
        public static Subtitle $default$parseToLegacySubtitle(SubtitleParser subtitleParser, byte[] bArr, int i, int i2) {
            final ImmutableList.Builder builder = ImmutableList.builder();
            subtitleParser.parse(bArr, i, i2, OutputOptions.ALL, new Consumer() { // from class: androidx.media3.extractor.text.SubtitleParser$$ExternalSyntheticLambda0
                @Override // androidx.media3.common.util.Consumer
                public final void accept(Object obj) {
                    builder.add((CuesWithTiming) obj);
                }
            });
            return new CuesWithTimingSubtitle(builder.build());
        }

        public static void $default$reset(SubtitleParser subtitleParser) {
        }
    }
}
