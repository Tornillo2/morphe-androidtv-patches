package androidx.media3.datasource.cache;

import android.support.v4.media.session.MediaSessionCompat$QueueItem$$ExternalSyntheticOutline0;
import androidx.annotation.Nullable;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Util;
import com.amazon.avod.mpb.media.ExternalFourCCMapper;
import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public final class SimpleCacheSpan extends CacheSpan {
    public static final Pattern CACHE_FILE_PATTERN_V1 = Pattern.compile("^(.+)\\.(\\d+)\\.(\\d+)\\.v1\\.exo$", 32);
    public static final Pattern CACHE_FILE_PATTERN_V2 = Pattern.compile("^(.+)\\.(\\d+)\\.(\\d+)\\.v2\\.exo$", 32);
    public static final Pattern CACHE_FILE_PATTERN_V3 = Pattern.compile("^(\\d+)\\.(\\d+)\\.(\\d+)\\.v3\\.exo$", 32);
    public static final String COMMON_SUFFIX = ".exo";
    public static final String SUFFIX = ".v3.exo";

    public SimpleCacheSpan(String str, long j, long j2, long j3, @Nullable File file) {
        super(str, j, j2, j3, file);
    }

    @Nullable
    public static SimpleCacheSpan createCacheEntry(File file, long j, CachedContentIndex cachedContentIndex) {
        return createCacheEntry(file, j, -9223372036854775807L, cachedContentIndex);
    }

    public static SimpleCacheSpan createHole(String str, long j, long j2) {
        return new SimpleCacheSpan(str, j, j2, -9223372036854775807L, null);
    }

    public static SimpleCacheSpan createLookup(String str, long j) {
        return new SimpleCacheSpan(str, j, -1L, -9223372036854775807L, null);
    }

    public static File getCacheFile(File file, int i, long j, long j2) {
        StringBuilder sb = new StringBuilder();
        sb.append(i);
        sb.append(ExternalFourCCMapper.CODEC_NAME_SPLITTER);
        sb.append(j);
        sb.append(ExternalFourCCMapper.CODEC_NAME_SPLITTER);
        return new File(file, MediaSessionCompat$QueueItem$$ExternalSyntheticOutline0.m(sb, j2, ".v3.exo"));
    }

    @Nullable
    public static File upgradeFile(File file, CachedContentIndex cachedContentIndex) {
        String strGroup;
        String name = file.getName();
        Matcher matcher = CACHE_FILE_PATTERN_V2.matcher(name);
        if (matcher.matches()) {
            String strGroup2 = matcher.group(1);
            strGroup2.getClass();
            strGroup = Util.unescapeFileName(strGroup2);
        } else {
            matcher = CACHE_FILE_PATTERN_V1.matcher(name);
            if (matcher.matches()) {
                strGroup = matcher.group(1);
                strGroup.getClass();
            } else {
                strGroup = null;
            }
        }
        if (strGroup != null) {
            File parentFile = file.getParentFile();
            Assertions.checkStateNotNull(parentFile);
            int iAssignIdForKey = cachedContentIndex.assignIdForKey(strGroup);
            String strGroup3 = matcher.group(2);
            strGroup3.getClass();
            long j = Long.parseLong(strGroup3);
            String strGroup4 = matcher.group(3);
            strGroup4.getClass();
            File cacheFile = getCacheFile(parentFile, iAssignIdForKey, j, Long.parseLong(strGroup4));
            if (file.renameTo(cacheFile)) {
                return cacheFile;
            }
        }
        return null;
    }

    public SimpleCacheSpan copyWithFileAndLastTouchTimestamp(File file, long j) {
        Assertions.checkState(this.isCached);
        return new SimpleCacheSpan(this.key, this.position, this.length, j, file);
    }

    @Nullable
    public static SimpleCacheSpan createCacheEntry(File file, long j, long j2, CachedContentIndex cachedContentIndex) {
        long j3;
        String name = file.getName();
        if (!name.endsWith(".v3.exo")) {
            file = upgradeFile(file, cachedContentIndex);
            if (file == null) {
                return null;
            }
            name = file.getName();
        }
        File file2 = file;
        Matcher matcher = CACHE_FILE_PATTERN_V3.matcher(name);
        if (!matcher.matches()) {
            return null;
        }
        String strGroup = matcher.group(1);
        strGroup.getClass();
        String keyForId = cachedContentIndex.getKeyForId(Integer.parseInt(strGroup));
        if (keyForId == null) {
            return null;
        }
        if (j == -1) {
            j = file2.length();
        }
        long j4 = j;
        if (j4 == 0) {
            return null;
        }
        String strGroup2 = matcher.group(2);
        strGroup2.getClass();
        long j5 = Long.parseLong(strGroup2);
        if (j2 == -9223372036854775807L) {
            String strGroup3 = matcher.group(3);
            strGroup3.getClass();
            j3 = Long.parseLong(strGroup3);
        } else {
            j3 = j2;
        }
        return new SimpleCacheSpan(keyForId, j5, j4, j3, file2);
    }
}
