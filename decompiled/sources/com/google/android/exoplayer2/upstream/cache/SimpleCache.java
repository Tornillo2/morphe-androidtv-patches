package com.google.android.exoplayer2.upstream.cache;

import android.os.ConditionVariable;
import androidx.annotation.Nullable;
import androidx.annotation.WorkerThread;
import androidx.concurrent.futures.AbstractResolvableFuture$$ExternalSyntheticOutline0;
import com.google.android.exoplayer2.database.DatabaseIOException;
import com.google.android.exoplayer2.database.DatabaseProvider;
import com.google.android.exoplayer2.mediacodec.MediaCodecUtil$$ExternalSyntheticOutline0;
import com.google.android.exoplayer2.upstream.cache.Cache;
import com.google.android.exoplayer2.upstream.cache.CachedContentIndex;
import com.google.android.exoplayer2.upstream.cache.ContentMetadata;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.Log;
import com.google.android.exoplayer2.util.Util;
import java.io.File;
import java.io.IOException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.NavigableSet;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class SimpleCache implements Cache {
    public static final int SUBDIRECTORY_COUNT = 10;
    public static final String TAG = "SimpleCache";
    public static final String UID_FILE_SUFFIX = ".uid";
    public static final HashSet<File> lockedCacheDirs = new HashSet<>();
    public final File cacheDir;
    public final CachedContentIndex contentIndex;
    public final CacheEvictor evictor;

    @Nullable
    public final CacheFileMetadataIndex fileIndex;
    public Cache.CacheException initializationException;
    public final HashMap<String, ArrayList<Cache.Listener>> listeners;
    public final Random random;
    public boolean released;
    public long totalSpace;
    public final boolean touchCacheSpans;
    public long uid;

    @Deprecated
    public SimpleCache(File file, CacheEvictor cacheEvictor) {
        this(file, cacheEvictor, (byte[]) null, false);
    }

    public static void createCacheDirectories(File file) throws Cache.CacheException {
        if (file.mkdirs() || file.isDirectory()) {
            return;
        }
        String str = "Failed to create cache directory: " + file;
        Log.e("SimpleCache", str);
        throw new Cache.CacheException(str);
    }

    public static long createUid(File file) throws IOException {
        long jNextLong = new SecureRandom().nextLong();
        long jAbs = jNextLong == Long.MIN_VALUE ? 0L : Math.abs(jNextLong);
        File file2 = new File(file, AbstractResolvableFuture$$ExternalSyntheticOutline0.m(Long.toString(jAbs, 16), ".uid"));
        if (file2.createNewFile()) {
            return jAbs;
        }
        throw new IOException("Failed to create UID file: " + file2);
    }

    @WorkerThread
    public static void delete(File file, @Nullable DatabaseProvider databaseProvider) {
        if (file.exists()) {
            File[] fileArrListFiles = file.listFiles();
            if (fileArrListFiles == null) {
                file.delete();
                return;
            }
            if (databaseProvider != null) {
                long jLoadUid = loadUid(fileArrListFiles);
                if (jLoadUid != -1) {
                    try {
                        CacheFileMetadataIndex.delete(databaseProvider, jLoadUid);
                    } catch (DatabaseIOException unused) {
                        Log.w("SimpleCache", "Failed to delete file metadata: " + jLoadUid);
                    }
                    try {
                        CachedContentIndex.DatabaseStorage.delete(databaseProvider, jLoadUid);
                    } catch (DatabaseIOException unused2) {
                        Log.w("SimpleCache", "Failed to delete file metadata: " + jLoadUid);
                    }
                }
            }
            Util.recursiveDelete(file);
        }
    }

    public static synchronized boolean isCacheFolderLocked(File file) {
        return lockedCacheDirs.contains(file.getAbsoluteFile());
    }

    public static long loadUid(File[] fileArr) {
        int length = fileArr.length;
        for (int i = 0; i < length; i++) {
            File file = fileArr[i];
            String name = file.getName();
            if (name.endsWith(".uid")) {
                try {
                    return parseUid(name);
                } catch (NumberFormatException unused) {
                    Log.e("SimpleCache", "Malformed UID file: " + file);
                    file.delete();
                }
            }
        }
        return -1L;
    }

    public static synchronized boolean lockFolder(File file) {
        return lockedCacheDirs.add(file.getAbsoluteFile());
    }

    public static long parseUid(String str) {
        return Long.parseLong(str.substring(0, str.indexOf(46)), 16);
    }

    public static synchronized void unlockFolder(File file) {
        lockedCacheDirs.remove(file.getAbsoluteFile());
    }

    @Override // com.google.android.exoplayer2.upstream.cache.Cache
    public synchronized NavigableSet<CacheSpan> addListener(String str, Cache.Listener listener) {
        try {
            Assertions.checkState(!this.released);
            str.getClass();
            listener.getClass();
            ArrayList<Cache.Listener> arrayList = this.listeners.get(str);
            if (arrayList == null) {
                arrayList = new ArrayList<>();
                this.listeners.put(str, arrayList);
            }
            arrayList.add(listener);
        } catch (Throwable th) {
            throw th;
        }
        return getCachedSpans(str);
    }

    public final void addSpan(SimpleCacheSpan simpleCacheSpan) {
        this.contentIndex.getOrAdd(simpleCacheSpan.key).addSpan(simpleCacheSpan);
        this.totalSpace += simpleCacheSpan.length;
        notifySpanAdded(simpleCacheSpan);
    }

    @Override // com.google.android.exoplayer2.upstream.cache.Cache
    public synchronized void applyContentMetadataMutations(String str, ContentMetadataMutations contentMetadataMutations) throws Cache.CacheException {
        Assertions.checkState(!this.released);
        checkInitialization();
        this.contentIndex.applyContentMetadataMutations(str, contentMetadataMutations);
        try {
            this.contentIndex.store();
        } catch (IOException e) {
            throw new Cache.CacheException(e);
        }
    }

    public synchronized void checkInitialization() throws Cache.CacheException {
        Cache.CacheException cacheException = this.initializationException;
        if (cacheException != null) {
            throw cacheException;
        }
    }

    @Override // com.google.android.exoplayer2.upstream.cache.Cache
    public synchronized void commitFile(File file, long j) throws Cache.CacheException {
        boolean z = true;
        Assertions.checkState(!this.released);
        if (file.exists()) {
            if (j == 0) {
                file.delete();
                return;
            }
            SimpleCacheSpan simpleCacheSpanCreateCacheEntry = SimpleCacheSpan.createCacheEntry(file, j, this.contentIndex);
            simpleCacheSpanCreateCacheEntry.getClass();
            CachedContent cachedContent = this.contentIndex.get(simpleCacheSpanCreateCacheEntry.key);
            cachedContent.getClass();
            Assertions.checkState(cachedContent.isFullyLocked(simpleCacheSpanCreateCacheEntry.position, simpleCacheSpanCreateCacheEntry.length));
            long contentLength = ContentMetadata.CC.getContentLength(cachedContent.metadata);
            if (contentLength != -1) {
                if (simpleCacheSpanCreateCacheEntry.position + simpleCacheSpanCreateCacheEntry.length > contentLength) {
                    z = false;
                }
                Assertions.checkState(z);
            }
            if (this.fileIndex == null) {
                addSpan(simpleCacheSpanCreateCacheEntry);
                this.contentIndex.store();
                notifyAll();
                return;
            }
            try {
                this.fileIndex.set(file.getName(), simpleCacheSpanCreateCacheEntry.length, simpleCacheSpanCreateCacheEntry.lastTouchTimestamp);
                addSpan(simpleCacheSpanCreateCacheEntry);
                try {
                    this.contentIndex.store();
                    notifyAll();
                    return;
                } catch (IOException e) {
                    throw new Cache.CacheException(e);
                }
            } catch (IOException e2) {
                throw new Cache.CacheException(e2);
            }
        }
    }

    @Override // com.google.android.exoplayer2.upstream.cache.Cache
    public synchronized long getCacheSpace() {
        Assertions.checkState(!this.released);
        return this.totalSpace;
    }

    @Override // com.google.android.exoplayer2.upstream.cache.Cache
    public synchronized long getCachedBytes(String str, long j, long j2) {
        long j3;
        long j4 = j2 == -1 ? Long.MAX_VALUE : j2 + j;
        long j5 = j4 >= 0 ? j4 : Long.MAX_VALUE;
        long j6 = j;
        j3 = 0;
        while (j6 < j5) {
            String str2 = str;
            long cachedLength = getCachedLength(str2, j6, j5 - j6);
            if (cachedLength > 0) {
                j3 += cachedLength;
            } else {
                cachedLength = -cachedLength;
            }
            j6 += cachedLength;
            str = str2;
        }
        return j3;
    }

    @Override // com.google.android.exoplayer2.upstream.cache.Cache
    public synchronized long getCachedLength(String str, long j, long j2) {
        CachedContent cachedContent;
        Assertions.checkState(!this.released);
        if (j2 == -1) {
            j2 = Long.MAX_VALUE;
        }
        cachedContent = this.contentIndex.get(str);
        return cachedContent != null ? cachedContent.getCachedBytesLength(j, j2) : -j2;
    }

    @Override // com.google.android.exoplayer2.upstream.cache.Cache
    public synchronized NavigableSet<CacheSpan> getCachedSpans(String str) {
        CachedContent cachedContent;
        try {
            Assertions.checkState(!this.released);
            cachedContent = this.contentIndex.get(str);
        } catch (Throwable th) {
            throw th;
        }
        return (cachedContent == null || cachedContent.cachedSpans.isEmpty()) ? new TreeSet() : new TreeSet((Collection) cachedContent.cachedSpans);
    }

    @Override // com.google.android.exoplayer2.upstream.cache.Cache
    public synchronized ContentMetadata getContentMetadata(String str) {
        Assertions.checkState(!this.released);
        return this.contentIndex.getContentMetadata(str);
    }

    @Override // com.google.android.exoplayer2.upstream.cache.Cache
    public synchronized Set<String> getKeys() {
        Assertions.checkState(!this.released);
        return new HashSet(this.contentIndex.getKeys());
    }

    public final SimpleCacheSpan getSpan(String str, long j, long j2) {
        SimpleCacheSpan span;
        CachedContent cachedContent = this.contentIndex.get(str);
        if (cachedContent == null) {
            return SimpleCacheSpan.createHole(str, j, j2);
        }
        while (true) {
            span = cachedContent.getSpan(j, j2);
            if (!span.isCached || span.file.length() == span.length) {
                break;
            }
            removeStaleSpans();
        }
        return span;
    }

    @Override // com.google.android.exoplayer2.upstream.cache.Cache
    public synchronized long getUid() {
        return this.uid;
    }

    public final void initialize() {
        if (!this.cacheDir.exists()) {
            try {
                createCacheDirectories(this.cacheDir);
            } catch (Cache.CacheException e) {
                this.initializationException = e;
                return;
            }
        }
        File[] fileArrListFiles = this.cacheDir.listFiles();
        if (fileArrListFiles == null) {
            String str = "Failed to list cache directory files: " + this.cacheDir;
            Log.e("SimpleCache", str);
            this.initializationException = new Cache.CacheException(str);
            return;
        }
        long jLoadUid = loadUid(fileArrListFiles);
        this.uid = jLoadUid;
        if (jLoadUid == -1) {
            try {
                this.uid = createUid(this.cacheDir);
            } catch (IOException e2) {
                String str2 = "Failed to create cache UID: " + this.cacheDir;
                Log.e("SimpleCache", str2, e2);
                this.initializationException = new Cache.CacheException(str2, e2);
                return;
            }
        }
        try {
            this.contentIndex.initialize(this.uid);
            CacheFileMetadataIndex cacheFileMetadataIndex = this.fileIndex;
            if (cacheFileMetadataIndex != null) {
                cacheFileMetadataIndex.initialize(this.uid);
                Map<String, CacheFileMetadata> all = this.fileIndex.getAll();
                loadDirectory(this.cacheDir, true, fileArrListFiles, all);
                this.fileIndex.removeAll(((HashMap) all).keySet());
            } else {
                loadDirectory(this.cacheDir, true, fileArrListFiles, null);
            }
            this.contentIndex.removeEmpty();
            try {
                this.contentIndex.store();
            } catch (IOException e3) {
                Log.e("SimpleCache", "Storing index file failed", e3);
            }
        } catch (IOException e4) {
            String str3 = "Failed to initialize cache indices: " + this.cacheDir;
            Log.e("SimpleCache", str3, e4);
            this.initializationException = new Cache.CacheException(str3, e4);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x001b  */
    @Override // com.google.android.exoplayer2.upstream.cache.Cache
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public synchronized boolean isCached(java.lang.String r3, long r4, long r6) {
        /*
            r2 = this;
            monitor-enter(r2)
            boolean r0 = r2.released     // Catch: java.lang.Throwable -> L19
            r1 = 1
            r0 = r0 ^ r1
            com.google.android.exoplayer2.util.Assertions.checkState(r0)     // Catch: java.lang.Throwable -> L19
            com.google.android.exoplayer2.upstream.cache.CachedContentIndex r0 = r2.contentIndex     // Catch: java.lang.Throwable -> L19
            com.google.android.exoplayer2.upstream.cache.CachedContent r3 = r0.get(r3)     // Catch: java.lang.Throwable -> L19
            if (r3 == 0) goto L1b
            long r3 = r3.getCachedBytesLength(r4, r6)     // Catch: java.lang.Throwable -> L19
            int r5 = (r3 > r6 ? 1 : (r3 == r6 ? 0 : -1))
            if (r5 < 0) goto L1b
            goto L1c
        L19:
            r3 = move-exception
            goto L1e
        L1b:
            r1 = 0
        L1c:
            monitor-exit(r2)
            return r1
        L1e:
            monitor-exit(r2)     // Catch: java.lang.Throwable -> L19
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.upstream.cache.SimpleCache.isCached(java.lang.String, long, long):boolean");
    }

    public final void loadDirectory(File file, boolean z, @Nullable File[] fileArr, @Nullable Map<String, CacheFileMetadata> map) {
        long j;
        long j2;
        if (fileArr == null || fileArr.length == 0) {
            if (z) {
                return;
            }
            file.delete();
            return;
        }
        for (File file2 : fileArr) {
            String name = file2.getName();
            if (z && name.indexOf(46) == -1) {
                loadDirectory(file2, false, file2.listFiles(), map);
            } else if (!z || (!name.startsWith("cached_content_index.exi") && !name.endsWith(".uid"))) {
                CacheFileMetadata cacheFileMetadataRemove = map != null ? map.remove(name) : null;
                if (cacheFileMetadataRemove != null) {
                    j = cacheFileMetadataRemove.length;
                    j2 = cacheFileMetadataRemove.lastTouchTimestamp;
                } else {
                    j = -1;
                    j2 = -9223372036854775807L;
                }
                SimpleCacheSpan simpleCacheSpanCreateCacheEntry = SimpleCacheSpan.createCacheEntry(file2, j, j2, this.contentIndex);
                if (simpleCacheSpanCreateCacheEntry != null) {
                    addSpan(simpleCacheSpanCreateCacheEntry);
                } else {
                    file2.delete();
                }
            }
        }
    }

    public final void notifySpanAdded(SimpleCacheSpan simpleCacheSpan) {
        ArrayList<Cache.Listener> arrayList = this.listeners.get(simpleCacheSpan.key);
        if (arrayList != null) {
            for (int size = arrayList.size() - 1; size >= 0; size--) {
                arrayList.get(size).onSpanAdded(this, simpleCacheSpan);
            }
        }
        this.evictor.onSpanAdded(this, simpleCacheSpan);
    }

    public final void notifySpanRemoved(CacheSpan cacheSpan) {
        ArrayList<Cache.Listener> arrayList = this.listeners.get(cacheSpan.key);
        if (arrayList != null) {
            for (int size = arrayList.size() - 1; size >= 0; size--) {
                arrayList.get(size).onSpanRemoved(this, cacheSpan);
            }
        }
        this.evictor.onSpanRemoved(this, cacheSpan);
    }

    public final void notifySpanTouched(SimpleCacheSpan simpleCacheSpan, CacheSpan cacheSpan) {
        ArrayList<Cache.Listener> arrayList = this.listeners.get(simpleCacheSpan.key);
        if (arrayList != null) {
            for (int size = arrayList.size() - 1; size >= 0; size--) {
                arrayList.get(size).onSpanTouched(this, simpleCacheSpan, cacheSpan);
            }
        }
        this.evictor.onSpanTouched(this, simpleCacheSpan, cacheSpan);
    }

    @Override // com.google.android.exoplayer2.upstream.cache.Cache
    public synchronized void release() {
        File file;
        if (this.released) {
            return;
        }
        this.listeners.clear();
        removeStaleSpans();
        try {
            try {
                this.contentIndex.store();
                file = this.cacheDir;
            } catch (IOException e) {
                Log.e("SimpleCache", "Storing index file failed", e);
                file = this.cacheDir;
            }
            unlockFolder(file);
            this.released = true;
        } catch (Throwable th) {
            unlockFolder(this.cacheDir);
            this.released = true;
            throw th;
        }
    }

    @Override // com.google.android.exoplayer2.upstream.cache.Cache
    public synchronized void releaseHoleSpan(CacheSpan cacheSpan) {
        Assertions.checkState(!this.released);
        CachedContent cachedContent = this.contentIndex.get(cacheSpan.key);
        cachedContent.getClass();
        cachedContent.unlockRange(cacheSpan.position);
        this.contentIndex.maybeRemove(cachedContent.key);
        notifyAll();
    }

    @Override // com.google.android.exoplayer2.upstream.cache.Cache
    public synchronized void removeListener(String str, Cache.Listener listener) {
        if (this.released) {
            return;
        }
        ArrayList<Cache.Listener> arrayList = this.listeners.get(str);
        if (arrayList != null) {
            arrayList.remove(listener);
            if (arrayList.isEmpty()) {
                this.listeners.remove(str);
            }
        }
    }

    @Override // com.google.android.exoplayer2.upstream.cache.Cache
    public synchronized void removeResource(String str) {
        Assertions.checkState(!this.released);
        Iterator<CacheSpan> it = getCachedSpans(str).iterator();
        while (it.hasNext()) {
            removeSpanInternal(it.next());
        }
    }

    @Override // com.google.android.exoplayer2.upstream.cache.Cache
    public synchronized void removeSpan(CacheSpan cacheSpan) {
        Assertions.checkState(!this.released);
        removeSpanInternal(cacheSpan);
    }

    public final void removeSpanInternal(CacheSpan cacheSpan) {
        CachedContent cachedContent = this.contentIndex.get(cacheSpan.key);
        if (cachedContent == null || !cachedContent.removeSpan(cacheSpan)) {
            return;
        }
        this.totalSpace -= cacheSpan.length;
        if (this.fileIndex != null) {
            String name = cacheSpan.file.getName();
            try {
                this.fileIndex.remove(name);
            } catch (IOException unused) {
                MediaCodecUtil$$ExternalSyntheticOutline0.m("Failed to remove file index entry for: ", name, "SimpleCache");
            }
        }
        this.contentIndex.maybeRemove(cachedContent.key);
        notifySpanRemoved(cacheSpan);
    }

    public final void removeStaleSpans() {
        ArrayList arrayList = new ArrayList();
        Iterator<CachedContent> it = this.contentIndex.getAll().iterator();
        while (it.hasNext()) {
            for (SimpleCacheSpan simpleCacheSpan : it.next().cachedSpans) {
                if (simpleCacheSpan.file.length() != simpleCacheSpan.length) {
                    arrayList.add(simpleCacheSpan);
                }
            }
        }
        for (int i = 0; i < arrayList.size(); i++) {
            removeSpanInternal((CacheSpan) arrayList.get(i));
        }
    }

    @Override // com.google.android.exoplayer2.upstream.cache.Cache
    public synchronized File startFile(String str, long j, long j2) throws Throwable {
        Throwable th;
        try {
            try {
                Assertions.checkState(!this.released);
                checkInitialization();
                CachedContent cachedContent = this.contentIndex.get(str);
                cachedContent.getClass();
                Assertions.checkState(cachedContent.isFullyLocked(j, j2));
                if (!this.cacheDir.exists()) {
                    try {
                        createCacheDirectories(this.cacheDir);
                        removeStaleSpans();
                    } catch (Throwable th2) {
                        th = th2;
                        throw th;
                    }
                }
                this.evictor.onStartFile(this, str, j, j2);
                File file = new File(this.cacheDir, Integer.toString(this.random.nextInt(10)));
                if (!file.exists()) {
                    createCacheDirectories(file);
                }
                return SimpleCacheSpan.getCacheFile(file, cachedContent.id, j, System.currentTimeMillis());
            } catch (Throwable th3) {
                th = th3;
                th = th;
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            th = th;
            throw th;
        }
    }

    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:19:? -> B:10:0x001e). Please report as a decompilation issue!!! */
    @Override // com.google.android.exoplayer2.upstream.cache.Cache
    public synchronized CacheSpan startReadWrite(String str, long j, long j2) throws Throwable {
        try {
            Assertions.checkState(!this.released);
            checkInitialization();
            while (true) {
                CacheSpan cacheSpanStartReadWriteNonBlocking = startReadWriteNonBlocking(str, j, j2);
                long j3 = j2;
                long j4 = j;
                String str2 = str;
                if (cacheSpanStartReadWriteNonBlocking != null) {
                    return cacheSpanStartReadWriteNonBlocking;
                }
                try {
                    wait();
                    str = str2;
                    j = j4;
                    j2 = j3;
                } catch (Throwable th) {
                    th = th;
                    throw th;
                }
            }
        } catch (Throwable th2) {
            th = th2;
            throw th;
        }
    }

    @Override // com.google.android.exoplayer2.upstream.cache.Cache
    @Nullable
    public synchronized CacheSpan startReadWriteNonBlocking(String str, long j, long j2) throws Throwable {
        try {
            try {
                Assertions.checkState(!this.released);
                checkInitialization();
                SimpleCacheSpan span = getSpan(str, j, j2);
                if (span.isCached) {
                    return touchSpan(str, span);
                }
                if (this.contentIndex.getOrAdd(str).lockRange(j, span.length)) {
                    return span;
                }
                return null;
            } catch (Throwable th) {
                th = th;
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }

    public final SimpleCacheSpan touchSpan(String str, SimpleCacheSpan simpleCacheSpan) {
        boolean z;
        if (!this.touchCacheSpans) {
            return simpleCacheSpan;
        }
        File file = simpleCacheSpan.file;
        file.getClass();
        String name = file.getName();
        long j = simpleCacheSpan.length;
        long jCurrentTimeMillis = System.currentTimeMillis();
        CacheFileMetadataIndex cacheFileMetadataIndex = this.fileIndex;
        if (cacheFileMetadataIndex != null) {
            try {
                cacheFileMetadataIndex.set(name, j, jCurrentTimeMillis);
            } catch (IOException unused) {
                Log.w("SimpleCache", "Failed to update index with new touch timestamp.");
            }
            z = false;
        } else {
            z = true;
        }
        SimpleCacheSpan lastTouchTimestamp = this.contentIndex.get(str).setLastTouchTimestamp(simpleCacheSpan, jCurrentTimeMillis, z);
        notifySpanTouched(simpleCacheSpan, lastTouchTimestamp);
        return lastTouchTimestamp;
    }

    @Deprecated
    public SimpleCache(File file, CacheEvictor cacheEvictor, @Nullable byte[] bArr) {
        this(file, cacheEvictor, bArr, bArr != null);
    }

    @Deprecated
    public SimpleCache(File file, CacheEvictor cacheEvictor, @Nullable byte[] bArr, boolean z) {
        this(file, cacheEvictor, null, bArr, z, true);
    }

    public SimpleCache(File file, CacheEvictor cacheEvictor, DatabaseProvider databaseProvider) {
        this(file, cacheEvictor, databaseProvider, null, false, false);
    }

    public SimpleCache(File file, CacheEvictor cacheEvictor, @Nullable DatabaseProvider databaseProvider, @Nullable byte[] bArr, boolean z, boolean z2) {
        this(file, cacheEvictor, new CachedContentIndex(databaseProvider, file, bArr, z, z2), (databaseProvider == null || z2) ? null : new CacheFileMetadataIndex(databaseProvider));
    }

    public SimpleCache(File file, CacheEvictor cacheEvictor, CachedContentIndex cachedContentIndex, @Nullable CacheFileMetadataIndex cacheFileMetadataIndex) {
        if (lockFolder(file)) {
            this.cacheDir = file;
            this.evictor = cacheEvictor;
            this.contentIndex = cachedContentIndex;
            this.fileIndex = cacheFileMetadataIndex;
            this.listeners = new HashMap<>();
            this.random = new Random();
            this.touchCacheSpans = cacheEvictor.requiresCacheSpanTouches();
            this.uid = -1L;
            final ConditionVariable conditionVariable = new ConditionVariable();
            new Thread("ExoPlayer:SimpleCacheInit") { // from class: com.google.android.exoplayer2.upstream.cache.SimpleCache.1
                @Override // java.lang.Thread, java.lang.Runnable
                public void run() {
                    synchronized (SimpleCache.this) {
                        conditionVariable.open();
                        SimpleCache.this.initialize();
                        SimpleCache.this.evictor.getClass();
                    }
                }
            }.start();
            conditionVariable.block();
            return;
        }
        throw new IllegalStateException("Another SimpleCache instance uses the folder: " + file);
    }
}
