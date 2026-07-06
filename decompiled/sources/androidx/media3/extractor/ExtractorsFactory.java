package androidx.media3.extractor;

import android.net.Uri;
import androidx.media3.common.util.UnstableApi;
import androidx.media3.extractor.text.SubtitleParser;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.List;
import java.util.Map;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@UnstableApi
public interface ExtractorsFactory {
    public static final ExtractorsFactory EMPTY = new ExtractorsFactory$$ExternalSyntheticLambda0();

    Extractor[] createExtractors();

    Extractor[] createExtractors(Uri uri, Map<String, List<String>> map);

    @CanIgnoreReturnValue
    ExtractorsFactory experimentalSetTextTrackTranscodingEnabled(boolean z);

    ExtractorsFactory setSubtitleParserFactory(SubtitleParser.Factory factory);

    /* JADX INFO: renamed from: androidx.media3.extractor.ExtractorsFactory$-CC, reason: invalid class name */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public final /* synthetic */ class CC {
        public static /* synthetic */ Extractor[] lambda$static$0() {
            return new Extractor[0];
        }

        @CanIgnoreReturnValue
        public static ExtractorsFactory $default$experimentalSetTextTrackTranscodingEnabled(ExtractorsFactory extractorsFactory, boolean z) {
            return extractorsFactory;
        }

        public static ExtractorsFactory $default$setSubtitleParserFactory(ExtractorsFactory extractorsFactory, SubtitleParser.Factory factory) {
            return extractorsFactory;
        }
    }
}
