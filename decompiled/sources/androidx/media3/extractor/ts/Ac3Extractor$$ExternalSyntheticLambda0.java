package androidx.media3.extractor.ts;

import android.net.Uri;
import androidx.media3.extractor.Extractor;
import androidx.media3.extractor.ExtractorsFactory;
import androidx.media3.extractor.text.SubtitleParser;
import java.util.Map;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class Ac3Extractor$$ExternalSyntheticLambda0 implements ExtractorsFactory {
    @Override // androidx.media3.extractor.ExtractorsFactory
    public final Extractor[] createExtractors() {
        return Ac3Extractor.m185$r8$lambda$UBU3oZ0gNDecgBYBKoAxaebyw0();
    }

    @Override // androidx.media3.extractor.ExtractorsFactory
    public /* synthetic */ ExtractorsFactory experimentalSetTextTrackTranscodingEnabled(boolean z) {
        ExtractorsFactory.CC.$default$experimentalSetTextTrackTranscodingEnabled(this, z);
        return this;
    }

    @Override // androidx.media3.extractor.ExtractorsFactory
    public /* synthetic */ ExtractorsFactory setSubtitleParserFactory(SubtitleParser.Factory factory) {
        ExtractorsFactory.CC.$default$setSubtitleParserFactory(this, factory);
        return this;
    }

    @Override // androidx.media3.extractor.ExtractorsFactory
    public /* synthetic */ Extractor[] createExtractors(Uri uri, Map map) {
        return createExtractors();
    }
}
