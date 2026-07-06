package com.google.android.exoplayer2.source.mediaparser;

import android.media.MediaFormat;
import android.media.MediaParser;
import android.media.metrics.LogSessionId;
import androidx.annotation.DoNotInline;
import androidx.annotation.RequiresApi;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.analytics.PlayerId;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class MediaParserUtil {
    public static final String PARAMETER_EAGERLY_EXPOSE_TRACK_TYPE = "android.media.mediaparser.eagerlyExposeTrackType";
    public static final String PARAMETER_EXPOSE_CAPTION_FORMATS = "android.media.mediaParser.exposeCaptionFormats";
    public static final String PARAMETER_EXPOSE_CHUNK_INDEX_AS_MEDIA_FORMAT = "android.media.mediaParser.exposeChunkIndexAsMediaFormat";
    public static final String PARAMETER_EXPOSE_DUMMY_SEEK_MAP = "android.media.mediaparser.exposeDummySeekMap";
    public static final String PARAMETER_IGNORE_TIMESTAMP_OFFSET = "android.media.mediaparser.ignoreTimestampOffset";
    public static final String PARAMETER_INCLUDE_SUPPLEMENTAL_DATA = "android.media.mediaparser.includeSupplementalData";
    public static final String PARAMETER_IN_BAND_CRYPTO_INFO = "android.media.mediaparser.inBandCryptoInfo";
    public static final String PARAMETER_OVERRIDE_IN_BAND_CAPTION_DECLARATIONS = "android.media.mediaParser.overrideInBandCaptionDeclarations";

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @RequiresApi(31)
    public static final class Api31 {
        @DoNotInline
        public static void setLogSessionIdOnMediaParser(MediaParser mediaParser, PlayerId playerId) {
            LogSessionId logSessionId = playerId.getLogSessionId();
            if (logSessionId.equals(LogSessionId.LOG_SESSION_ID_NONE)) {
                return;
            }
            mediaParser.setLogSessionId(logSessionId);
        }
    }

    @RequiresApi(31)
    public static void setLogSessionIdOnMediaParser(MediaParser mediaParser, PlayerId playerId) {
        Api31.setLogSessionIdOnMediaParser(mediaParser, playerId);
    }

    public static MediaFormat toCaptionsMediaFormat(Format format) {
        MediaFormat mediaFormat = new MediaFormat();
        mediaFormat.setString("mime", format.sampleMimeType);
        int i = format.accessibilityChannel;
        if (i != -1) {
            mediaFormat.setInteger("caption-service-number", i);
        }
        return mediaFormat;
    }
}
