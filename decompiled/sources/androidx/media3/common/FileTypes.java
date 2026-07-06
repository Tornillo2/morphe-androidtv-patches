package androidx.media3.common;

import android.net.Uri;
import androidx.annotation.VisibleForTesting;
import androidx.media3.common.util.UnstableApi;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.List;
import java.util.Map;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@UnstableApi
public final class FileTypes {
    public static final int AC3 = 0;
    public static final int AC4 = 1;
    public static final int ADTS = 2;
    public static final int AMR = 3;
    public static final int AVI = 16;
    public static final int BMP = 19;
    public static final String EXTENSION_AAC = ".aac";
    public static final String EXTENSION_AC3 = ".ac3";
    public static final String EXTENSION_AC4 = ".ac4";
    public static final String EXTENSION_ADTS = ".adts";
    public static final String EXTENSION_AMR = ".amr";
    public static final String EXTENSION_AVI = ".avi";
    public static final String EXTENSION_BMP = ".bmp";
    public static final String EXTENSION_DIB = ".dib";
    public static final String EXTENSION_EC3 = ".ec3";
    public static final String EXTENSION_FLAC = ".flac";
    public static final String EXTENSION_FLV = ".flv";
    public static final String EXTENSION_HEIC = ".heic";
    public static final String EXTENSION_JPEG = ".jpeg";
    public static final String EXTENSION_JPG = ".jpg";
    public static final String EXTENSION_M2P = ".m2p";
    public static final String EXTENSION_MID = ".mid";
    public static final String EXTENSION_MIDI = ".midi";
    public static final String EXTENSION_MP3 = ".mp3";
    public static final String EXTENSION_MP4 = ".mp4";
    public static final String EXTENSION_MPEG = ".mpeg";
    public static final String EXTENSION_MPG = ".mpg";
    public static final String EXTENSION_OPUS = ".opus";
    public static final String EXTENSION_PNG = ".png";
    public static final String EXTENSION_PREFIX_CMF = ".cmf";
    public static final String EXTENSION_PREFIX_M4 = ".m4";
    public static final String EXTENSION_PREFIX_MK = ".mk";
    public static final String EXTENSION_PREFIX_MP4 = ".mp4";
    public static final String EXTENSION_PREFIX_OG = ".og";
    public static final String EXTENSION_PREFIX_TS = ".ts";
    public static final String EXTENSION_PS = ".ps";
    public static final String EXTENSION_SMF = ".smf";
    public static final String EXTENSION_TS = ".ts";
    public static final String EXTENSION_VTT = ".vtt";
    public static final String EXTENSION_WAV = ".wav";
    public static final String EXTENSION_WAVE = ".wave";
    public static final String EXTENSION_WEBM = ".webm";
    public static final String EXTENSION_WEBP = ".webp";
    public static final String EXTENSION_WEBVTT = ".webvtt";
    public static final int FLAC = 4;
    public static final int FLV = 5;

    @VisibleForTesting
    public static final String HEADER_CONTENT_TYPE = "Content-Type";
    public static final int HEIF = 20;
    public static final int JPEG = 14;
    public static final int MATROSKA = 6;
    public static final int MIDI = 15;
    public static final int MP3 = 7;
    public static final int MP4 = 8;
    public static final int OGG = 9;
    public static final int PNG = 17;
    public static final int PS = 10;
    public static final int TS = 11;
    public static final int UNKNOWN = -1;
    public static final int WAV = 12;
    public static final int WEBP = 18;
    public static final int WEBVTT = 13;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @Target({ElementType.TYPE_USE})
    @Documented
    @Retention(RetentionPolicy.SOURCE)
    public @interface Type {
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    /* JADX WARN: Removed duplicated region for block: B:7:0x003a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static int inferFileTypeFromMimeType(@androidx.annotation.Nullable java.lang.String r23) {
        /*
            Method dump skipped, instruction units count: 666
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media3.common.FileTypes.inferFileTypeFromMimeType(java.lang.String):int");
    }

    public static int inferFileTypeFromResponseHeaders(Map<String, List<String>> map) {
        List<String> list = map.get("Content-Type");
        return inferFileTypeFromMimeType((list == null || list.isEmpty()) ? null : list.get(0));
    }

    public static int inferFileTypeFromUri(Uri uri) {
        String lastPathSegment = uri.getLastPathSegment();
        if (lastPathSegment == null) {
            return -1;
        }
        if (lastPathSegment.endsWith(".ac3") || lastPathSegment.endsWith(".ec3")) {
            return 0;
        }
        if (lastPathSegment.endsWith(".ac4")) {
            return 1;
        }
        if (lastPathSegment.endsWith(".adts") || lastPathSegment.endsWith(".aac")) {
            return 2;
        }
        if (lastPathSegment.endsWith(".amr")) {
            return 3;
        }
        if (lastPathSegment.endsWith(".flac")) {
            return 4;
        }
        if (lastPathSegment.endsWith(".flv")) {
            return 5;
        }
        if (lastPathSegment.endsWith(".mid") || lastPathSegment.endsWith(".midi") || lastPathSegment.endsWith(".smf")) {
            return 15;
        }
        if (lastPathSegment.startsWith(".mk", lastPathSegment.length() - 4) || lastPathSegment.endsWith(".webm")) {
            return 6;
        }
        if (lastPathSegment.endsWith(".mp3")) {
            return 7;
        }
        if (lastPathSegment.endsWith(".mp4") || lastPathSegment.startsWith(".m4", lastPathSegment.length() - 4) || lastPathSegment.startsWith(".mp4", lastPathSegment.length() - 5) || lastPathSegment.startsWith(".cmf", lastPathSegment.length() - 5)) {
            return 8;
        }
        if (lastPathSegment.startsWith(".og", lastPathSegment.length() - 4) || lastPathSegment.endsWith(".opus")) {
            return 9;
        }
        if (lastPathSegment.endsWith(".ps") || lastPathSegment.endsWith(".mpeg") || lastPathSegment.endsWith(".mpg") || lastPathSegment.endsWith(".m2p")) {
            return 10;
        }
        if (lastPathSegment.endsWith(".ts") || lastPathSegment.startsWith(".ts", lastPathSegment.length() - 4)) {
            return 11;
        }
        if (lastPathSegment.endsWith(".wav") || lastPathSegment.endsWith(".wave")) {
            return 12;
        }
        if (lastPathSegment.endsWith(".vtt") || lastPathSegment.endsWith(".webvtt")) {
            return 13;
        }
        if (lastPathSegment.endsWith(".jpg") || lastPathSegment.endsWith(".jpeg")) {
            return 14;
        }
        if (lastPathSegment.endsWith(".avi")) {
            return 16;
        }
        if (lastPathSegment.endsWith(EXTENSION_PNG)) {
            return 17;
        }
        if (lastPathSegment.endsWith(EXTENSION_WEBP)) {
            return 18;
        }
        if (lastPathSegment.endsWith(EXTENSION_BMP) || lastPathSegment.endsWith(EXTENSION_DIB)) {
            return 19;
        }
        return lastPathSegment.endsWith(EXTENSION_HEIC) ? 20 : -1;
    }
}
