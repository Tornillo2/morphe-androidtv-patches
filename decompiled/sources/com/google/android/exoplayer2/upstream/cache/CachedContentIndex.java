package com.google.android.exoplayer2.upstream.cache;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.support.v4.media.MediaBrowserCompat$MediaBrowserImplBase$$ExternalSyntheticOutline0;
import android.util.SparseArray;
import android.util.SparseBooleanArray;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import androidx.annotation.WorkerThread;
import androidx.core.app.RemoteInput$$ExternalSyntheticOutline0;
import com.google.android.exoplayer2.database.DatabaseIOException;
import com.google.android.exoplayer2.database.DatabaseProvider;
import com.google.android.exoplayer2.database.VersionTable;
import com.google.android.exoplayer2.upstream.cache.ContentMetadata;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.AtomicFile;
import com.google.android.exoplayer2.util.Util;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.UnmodifiableIterator;
import j$.util.DesugarCollections;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public class CachedContentIndex {
    public static final String FILE_NAME_ATOMIC = "cached_content_index.exi";
    public static final int INCREMENTAL_METADATA_READ_LENGTH = 10485760;
    public final SparseArray<String> idToKey;
    public final HashMap<String, CachedContent> keyToContent;
    public final SparseBooleanArray newIds;

    @Nullable
    public Storage previousStorage;
    public final SparseBooleanArray removedIds;
    public Storage storage;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class DatabaseStorage implements Storage {
        public static final String[] COLUMNS = {"id", "key", "metadata"};
        public static final String COLUMN_ID = "id";
        public static final int COLUMN_INDEX_ID = 0;
        public static final int COLUMN_INDEX_KEY = 1;
        public static final int COLUMN_INDEX_METADATA = 2;
        public static final String COLUMN_KEY = "key";
        public static final String COLUMN_METADATA = "metadata";
        public static final String TABLE_PREFIX = "ExoPlayerCacheIndex";
        public static final String TABLE_SCHEMA = "(id INTEGER PRIMARY KEY NOT NULL,key TEXT NOT NULL,metadata BLOB NOT NULL)";
        public static final int TABLE_VERSION = 1;
        public static final String WHERE_ID_EQUALS = "id = ?";
        public final DatabaseProvider databaseProvider;
        public String hexUid;
        public final SparseArray<CachedContent> pendingUpdates = new SparseArray<>();
        public String tableName;

        public DatabaseStorage(DatabaseProvider databaseProvider) {
            this.databaseProvider = databaseProvider;
        }

        public static void delete(DatabaseProvider databaseProvider, long j) throws DatabaseIOException {
            delete(databaseProvider, Long.toHexString(j));
        }

        public static void dropTable(SQLiteDatabase sQLiteDatabase, String str) {
            sQLiteDatabase.execSQL("DROP TABLE IF EXISTS " + str);
        }

        public static String getTableName(String str) {
            return RemoteInput$$ExternalSyntheticOutline0.m("ExoPlayerCacheIndex", str);
        }

        public final void addOrUpdateRow(SQLiteDatabase sQLiteDatabase, CachedContent cachedContent) throws IOException {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            CachedContentIndex.writeContentMetadata(cachedContent.metadata, new DataOutputStream(byteArrayOutputStream));
            byte[] byteArray = byteArrayOutputStream.toByteArray();
            ContentValues contentValues = new ContentValues();
            contentValues.put("id", Integer.valueOf(cachedContent.id));
            contentValues.put("key", cachedContent.key);
            contentValues.put("metadata", byteArray);
            String str = this.tableName;
            str.getClass();
            sQLiteDatabase.replaceOrThrow(str, null, contentValues);
        }

        public final void deleteRow(SQLiteDatabase sQLiteDatabase, int i) {
            String str = this.tableName;
            str.getClass();
            sQLiteDatabase.delete(str, "id = ?", new String[]{Integer.toString(i)});
        }

        @Override // com.google.android.exoplayer2.upstream.cache.CachedContentIndex.Storage
        public boolean exists() throws DatabaseIOException {
            try {
                SQLiteDatabase readableDatabase = this.databaseProvider.getReadableDatabase();
                String str = this.hexUid;
                str.getClass();
                return VersionTable.getVersion(readableDatabase, 1, str) != -1;
            } catch (SQLException e) {
                throw new DatabaseIOException((Throwable) e);
            }
        }

        public final Cursor getCursor() {
            SQLiteDatabase readableDatabase = this.databaseProvider.getReadableDatabase();
            String str = this.tableName;
            str.getClass();
            return readableDatabase.query(str, COLUMNS, null, null, null, null, null);
        }

        @Override // com.google.android.exoplayer2.upstream.cache.CachedContentIndex.Storage
        public void initialize(long j) {
            String hexString = Long.toHexString(j);
            this.hexUid = hexString;
            this.tableName = getTableName(hexString);
        }

        public final void initializeTable(SQLiteDatabase sQLiteDatabase) throws DatabaseIOException {
            String str = this.hexUid;
            str.getClass();
            VersionTable.setVersion(sQLiteDatabase, 1, str, 1);
            String str2 = this.tableName;
            str2.getClass();
            dropTable(sQLiteDatabase, str2);
            sQLiteDatabase.execSQL("CREATE TABLE " + this.tableName + " (id INTEGER PRIMARY KEY NOT NULL,key TEXT NOT NULL,metadata BLOB NOT NULL)");
        }

        @Override // com.google.android.exoplayer2.upstream.cache.CachedContentIndex.Storage
        public void load(HashMap<String, CachedContent> map, SparseArray<String> sparseArray) throws IOException {
            Assertions.checkState(this.pendingUpdates.size() == 0);
            try {
                SQLiteDatabase readableDatabase = this.databaseProvider.getReadableDatabase();
                String str = this.hexUid;
                str.getClass();
                if (VersionTable.getVersion(readableDatabase, 1, str) != 1) {
                    SQLiteDatabase writableDatabase = this.databaseProvider.getWritableDatabase();
                    writableDatabase.beginTransactionNonExclusive();
                    try {
                        initializeTable(writableDatabase);
                        writableDatabase.setTransactionSuccessful();
                        writableDatabase.endTransaction();
                    } catch (Throwable th) {
                        writableDatabase.endTransaction();
                        throw th;
                    }
                }
                Cursor cursor = getCursor();
                while (cursor.moveToNext()) {
                    try {
                        int i = cursor.getInt(0);
                        String string = cursor.getString(1);
                        string.getClass();
                        map.put(string, new CachedContent(i, string, CachedContentIndex.readContentMetadata(new DataInputStream(new ByteArrayInputStream(cursor.getBlob(2))))));
                        sparseArray.put(i, string);
                    } finally {
                    }
                }
                cursor.close();
            } catch (SQLiteException e) {
                map.clear();
                sparseArray.clear();
                throw new DatabaseIOException((Throwable) e);
            }
        }

        @Override // com.google.android.exoplayer2.upstream.cache.CachedContentIndex.Storage
        public void onRemove(CachedContent cachedContent, boolean z) {
            if (z) {
                this.pendingUpdates.delete(cachedContent.id);
            } else {
                this.pendingUpdates.put(cachedContent.id, null);
            }
        }

        @Override // com.google.android.exoplayer2.upstream.cache.CachedContentIndex.Storage
        public void onUpdate(CachedContent cachedContent) {
            this.pendingUpdates.put(cachedContent.id, cachedContent);
        }

        @Override // com.google.android.exoplayer2.upstream.cache.CachedContentIndex.Storage
        public void storeFully(HashMap<String, CachedContent> map) throws IOException {
            try {
                SQLiteDatabase writableDatabase = this.databaseProvider.getWritableDatabase();
                writableDatabase.beginTransactionNonExclusive();
                try {
                    initializeTable(writableDatabase);
                    Iterator<CachedContent> it = map.values().iterator();
                    while (it.hasNext()) {
                        addOrUpdateRow(writableDatabase, it.next());
                    }
                    writableDatabase.setTransactionSuccessful();
                    this.pendingUpdates.clear();
                    writableDatabase.endTransaction();
                } catch (Throwable th) {
                    writableDatabase.endTransaction();
                    throw th;
                }
            } catch (SQLException e) {
                throw new DatabaseIOException((Throwable) e);
            }
        }

        @Override // com.google.android.exoplayer2.upstream.cache.CachedContentIndex.Storage
        public void storeIncremental(HashMap<String, CachedContent> map) throws IOException {
            if (this.pendingUpdates.size() == 0) {
                return;
            }
            try {
                SQLiteDatabase writableDatabase = this.databaseProvider.getWritableDatabase();
                writableDatabase.beginTransactionNonExclusive();
                for (int i = 0; i < this.pendingUpdates.size(); i++) {
                    try {
                        CachedContent cachedContentValueAt = this.pendingUpdates.valueAt(i);
                        if (cachedContentValueAt == null) {
                            deleteRow(writableDatabase, this.pendingUpdates.keyAt(i));
                        } else {
                            addOrUpdateRow(writableDatabase, cachedContentValueAt);
                        }
                    } catch (Throwable th) {
                        writableDatabase.endTransaction();
                        throw th;
                    }
                }
                writableDatabase.setTransactionSuccessful();
                this.pendingUpdates.clear();
                writableDatabase.endTransaction();
            } catch (SQLException e) {
                throw new DatabaseIOException((Throwable) e);
            }
        }

        @Override // com.google.android.exoplayer2.upstream.cache.CachedContentIndex.Storage
        public void delete() throws DatabaseIOException {
            DatabaseProvider databaseProvider = this.databaseProvider;
            String str = this.hexUid;
            str.getClass();
            delete(databaseProvider, str);
        }

        public static void delete(DatabaseProvider databaseProvider, String str) throws DatabaseIOException {
            try {
                String tableName = getTableName(str);
                SQLiteDatabase writableDatabase = databaseProvider.getWritableDatabase();
                writableDatabase.beginTransactionNonExclusive();
                try {
                    VersionTable.removeVersion(writableDatabase, 1, str);
                    dropTable(writableDatabase, tableName);
                    writableDatabase.setTransactionSuccessful();
                } finally {
                    writableDatabase.endTransaction();
                }
            } catch (SQLException e) {
                throw new DatabaseIOException((Throwable) e);
            }
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public interface Storage {
        void delete() throws IOException;

        boolean exists() throws IOException;

        void initialize(long j);

        void load(HashMap<String, CachedContent> map, SparseArray<String> sparseArray) throws IOException;

        void onRemove(CachedContent cachedContent, boolean z);

        void onUpdate(CachedContent cachedContent);

        void storeFully(HashMap<String, CachedContent> map) throws IOException;

        void storeIncremental(HashMap<String, CachedContent> map) throws IOException;
    }

    public CachedContentIndex(DatabaseProvider databaseProvider) {
        this(databaseProvider, null, null, false, false);
    }

    @WorkerThread
    public static void delete(DatabaseProvider databaseProvider, long j) throws DatabaseIOException {
        DatabaseStorage.delete(databaseProvider, j);
    }

    @SuppressLint({"GetInstance"})
    public static Cipher getCipher() throws NoSuchPaddingException, NoSuchAlgorithmException {
        if (Util.SDK_INT == 18) {
            try {
                return Cipher.getInstance("AES/CBC/PKCS5PADDING", "BC");
            } catch (Throwable unused) {
            }
        }
        return Cipher.getInstance("AES/CBC/PKCS5PADDING");
    }

    @VisibleForTesting
    public static int getNewId(SparseArray<String> sparseArray) {
        int size = sparseArray.size();
        int i = 0;
        int iKeyAt = size == 0 ? 0 : sparseArray.keyAt(size - 1) + 1;
        if (iKeyAt >= 0) {
            return iKeyAt;
        }
        while (i < size && i == sparseArray.keyAt(i)) {
            i++;
        }
        return i;
    }

    public static boolean isIndexFile(String str) {
        return str.startsWith("cached_content_index.exi");
    }

    public static DefaultContentMetadata readContentMetadata(DataInputStream dataInputStream) throws IOException {
        int i = dataInputStream.readInt();
        HashMap map = new HashMap();
        for (int i2 = 0; i2 < i; i2++) {
            String utf = dataInputStream.readUTF();
            int i3 = dataInputStream.readInt();
            if (i3 < 0) {
                throw new IOException(MediaBrowserCompat$MediaBrowserImplBase$$ExternalSyntheticOutline0.m("Invalid value size: ", i3));
            }
            int iMin = Math.min(i3, 10485760);
            byte[] bArrCopyOf = Util.EMPTY_BYTE_ARRAY;
            int i4 = 0;
            while (i4 != i3) {
                int i5 = i4 + iMin;
                bArrCopyOf = Arrays.copyOf(bArrCopyOf, i5);
                dataInputStream.readFully(bArrCopyOf, i4, iMin);
                iMin = Math.min(i3 - i5, 10485760);
                i4 = i5;
            }
            map.put(utf, bArrCopyOf);
        }
        return new DefaultContentMetadata(map);
    }

    public static void writeContentMetadata(DefaultContentMetadata defaultContentMetadata, DataOutputStream dataOutputStream) throws IOException {
        Set<Map.Entry<String, byte[]>> setEntrySet = defaultContentMetadata.metadata.entrySet();
        dataOutputStream.writeInt(setEntrySet.size());
        for (Map.Entry<String, byte[]> entry : setEntrySet) {
            dataOutputStream.writeUTF(entry.getKey());
            byte[] value = entry.getValue();
            dataOutputStream.writeInt(value.length);
            dataOutputStream.write(value);
        }
    }

    public final CachedContent addNew(String str) {
        int newId = getNewId(this.idToKey);
        CachedContent cachedContent = new CachedContent(newId, str);
        this.keyToContent.put(str, cachedContent);
        this.idToKey.put(newId, str);
        this.newIds.put(newId, true);
        this.storage.onUpdate(cachedContent);
        return cachedContent;
    }

    public void applyContentMetadataMutations(String str, ContentMetadataMutations contentMetadataMutations) {
        CachedContent orAdd = getOrAdd(str);
        if (orAdd.applyMetadataMutations(contentMetadataMutations)) {
            this.storage.onUpdate(orAdd);
        }
    }

    public int assignIdForKey(String str) {
        return getOrAdd(str).id;
    }

    @Nullable
    public CachedContent get(String str) {
        return this.keyToContent.get(str);
    }

    public Collection<CachedContent> getAll() {
        return DesugarCollections.unmodifiableCollection(this.keyToContent.values());
    }

    public ContentMetadata getContentMetadata(String str) {
        CachedContent cachedContent = get(str);
        return cachedContent != null ? cachedContent.metadata : DefaultContentMetadata.EMPTY;
    }

    @Nullable
    public String getKeyForId(int i) {
        return this.idToKey.get(i);
    }

    public Set<String> getKeys() {
        return this.keyToContent.keySet();
    }

    public CachedContent getOrAdd(String str) {
        CachedContent cachedContent = this.keyToContent.get(str);
        return cachedContent == null ? addNew(str) : cachedContent;
    }

    @WorkerThread
    public void initialize(long j) throws IOException {
        Storage storage;
        this.storage.initialize(j);
        Storage storage2 = this.previousStorage;
        if (storage2 != null) {
            storage2.initialize(j);
        }
        if (this.storage.exists() || (storage = this.previousStorage) == null || !storage.exists()) {
            this.storage.load(this.keyToContent, this.idToKey);
        } else {
            this.previousStorage.load(this.keyToContent, this.idToKey);
            this.storage.storeFully(this.keyToContent);
        }
        Storage storage3 = this.previousStorage;
        if (storage3 != null) {
            storage3.delete();
            this.previousStorage = null;
        }
    }

    public void maybeRemove(String str) {
        CachedContent cachedContent = this.keyToContent.get(str);
        if (cachedContent != null && cachedContent.cachedSpans.isEmpty() && cachedContent.lockedRanges.isEmpty()) {
            this.keyToContent.remove(str);
            int i = cachedContent.id;
            boolean z = this.newIds.get(i);
            this.storage.onRemove(cachedContent, z);
            if (z) {
                this.idToKey.remove(i);
                this.newIds.delete(i);
            } else {
                this.idToKey.put(i, null);
                this.removedIds.put(i, true);
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void removeEmpty() {
        UnmodifiableIterator it = ImmutableSet.copyOf((Collection) this.keyToContent.keySet()).iterator();
        while (it.hasNext()) {
            maybeRemove((String) it.next());
        }
    }

    @WorkerThread
    public void store() throws IOException {
        this.storage.storeIncremental(this.keyToContent);
        int size = this.removedIds.size();
        for (int i = 0; i < size; i++) {
            this.idToKey.remove(this.removedIds.keyAt(i));
        }
        this.removedIds.clear();
        this.newIds.clear();
    }

    public CachedContentIndex(@Nullable DatabaseProvider databaseProvider, @Nullable File file, @Nullable byte[] bArr, boolean z, boolean z2) {
        Assertions.checkState((databaseProvider == null && file == null) ? false : true);
        this.keyToContent = new HashMap<>();
        this.idToKey = new SparseArray<>();
        this.removedIds = new SparseBooleanArray();
        this.newIds = new SparseBooleanArray();
        DatabaseStorage databaseStorage = databaseProvider != null ? new DatabaseStorage(databaseProvider) : null;
        LegacyStorage legacyStorage = file != null ? new LegacyStorage(new File(file, "cached_content_index.exi"), bArr, z) : null;
        if (databaseStorage != null && (legacyStorage == null || !z2)) {
            this.storage = databaseStorage;
            this.previousStorage = legacyStorage;
        } else {
            Util.castNonNull(legacyStorage);
            this.storage = legacyStorage;
            this.previousStorage = databaseStorage;
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static class LegacyStorage implements Storage {
        public static final int FLAG_ENCRYPTED_INDEX = 1;
        public static final int VERSION = 2;
        public static final int VERSION_METADATA_INTRODUCED = 2;
        public final AtomicFile atomicFile;

        @Nullable
        public ReusableBufferedOutputStream bufferedOutputStream;
        public boolean changed;

        @Nullable
        public final Cipher cipher;
        public final boolean encrypt;

        @Nullable
        public final SecureRandom random;

        @Nullable
        public final SecretKeySpec secretKeySpec;

        public LegacyStorage(File file, @Nullable byte[] bArr, boolean z) {
            Cipher cipher;
            SecretKeySpec secretKeySpec;
            Assertions.checkState((bArr == null && z) ? false : true);
            if (bArr != null) {
                Assertions.checkArgument(bArr.length == 16);
                try {
                    cipher = CachedContentIndex.getCipher();
                    secretKeySpec = new SecretKeySpec(bArr, "AES");
                } catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
                    throw new IllegalStateException(e);
                }
            } else {
                Assertions.checkArgument(!z);
                cipher = null;
                secretKeySpec = null;
            }
            this.encrypt = z;
            this.cipher = cipher;
            this.secretKeySpec = secretKeySpec;
            this.random = z ? new SecureRandom() : null;
            this.atomicFile = new AtomicFile(file);
        }

        @Override // com.google.android.exoplayer2.upstream.cache.CachedContentIndex.Storage
        public void delete() {
            this.atomicFile.delete();
        }

        @Override // com.google.android.exoplayer2.upstream.cache.CachedContentIndex.Storage
        public boolean exists() {
            return this.atomicFile.exists();
        }

        public final int hashCachedContent(CachedContent cachedContent, int i) {
            int iHashCode = cachedContent.key.hashCode() + (cachedContent.id * 31);
            if (i < 2) {
                long contentLength = ContentMetadata.CC.getContentLength(cachedContent.metadata);
                return (iHashCode * 31) + ((int) (contentLength ^ (contentLength >>> 32)));
            }
            return cachedContent.metadata.hashCode() + (iHashCode * 31);
        }

        @Override // com.google.android.exoplayer2.upstream.cache.CachedContentIndex.Storage
        public void load(HashMap<String, CachedContent> map, SparseArray<String> sparseArray) {
            Assertions.checkState(!this.changed);
            if (readFile(map, sparseArray)) {
                return;
            }
            map.clear();
            sparseArray.clear();
            this.atomicFile.delete();
        }

        @Override // com.google.android.exoplayer2.upstream.cache.CachedContentIndex.Storage
        public void onRemove(CachedContent cachedContent, boolean z) {
            this.changed = true;
        }

        @Override // com.google.android.exoplayer2.upstream.cache.CachedContentIndex.Storage
        public void onUpdate(CachedContent cachedContent) {
            this.changed = true;
        }

        public final CachedContent readCachedContent(int i, DataInputStream dataInputStream) throws IOException {
            DefaultContentMetadata contentMetadata;
            int i2 = dataInputStream.readInt();
            String utf = dataInputStream.readUTF();
            if (i < 2) {
                long j = dataInputStream.readLong();
                ContentMetadataMutations contentMetadataMutations = new ContentMetadataMutations();
                contentMetadataMutations.checkAndSet("exo_len", Long.valueOf(j));
                contentMetadata = DefaultContentMetadata.EMPTY.copyWithMutationsApplied(contentMetadataMutations);
            } else {
                contentMetadata = CachedContentIndex.readContentMetadata(dataInputStream);
            }
            return new CachedContent(i2, utf, contentMetadata);
        }

        public final boolean readFile(HashMap<String, CachedContent> map, SparseArray<String> sparseArray) throws Throwable {
            BufferedInputStream bufferedInputStream;
            DataInputStream dataInputStream;
            if (!this.atomicFile.exists()) {
                return true;
            }
            DataInputStream dataInputStream2 = null;
            try {
                bufferedInputStream = new BufferedInputStream(this.atomicFile.openRead());
                dataInputStream = new DataInputStream(bufferedInputStream);
            } catch (IOException unused) {
            } catch (Throwable th) {
                th = th;
            }
            try {
                int i = dataInputStream.readInt();
                if (i >= 0 && i <= 2) {
                    if ((dataInputStream.readInt() & 1) != 0) {
                        if (this.cipher == null) {
                            Util.closeQuietly(dataInputStream);
                            return false;
                        }
                        byte[] bArr = new byte[16];
                        dataInputStream.readFully(bArr);
                        IvParameterSpec ivParameterSpec = new IvParameterSpec(bArr);
                        try {
                            Cipher cipher = this.cipher;
                            SecretKeySpec secretKeySpec = this.secretKeySpec;
                            Util.castNonNull(secretKeySpec);
                            cipher.init(2, secretKeySpec, ivParameterSpec);
                            dataInputStream = new DataInputStream(new CipherInputStream(bufferedInputStream, this.cipher));
                        } catch (InvalidAlgorithmParameterException e) {
                            e = e;
                            throw new IllegalStateException(e);
                        } catch (InvalidKeyException e2) {
                            e = e2;
                            throw new IllegalStateException(e);
                        }
                    } else if (this.encrypt) {
                        this.changed = true;
                    }
                    int i2 = dataInputStream.readInt();
                    int iHashCachedContent = 0;
                    for (int i3 = 0; i3 < i2; i3++) {
                        CachedContent cachedContent = readCachedContent(i, dataInputStream);
                        map.put(cachedContent.key, cachedContent);
                        sparseArray.put(cachedContent.id, cachedContent.key);
                        iHashCachedContent += hashCachedContent(cachedContent, i);
                    }
                    int i4 = dataInputStream.readInt();
                    boolean z = dataInputStream.read() == -1;
                    if (i4 == iHashCachedContent && z) {
                        Util.closeQuietly(dataInputStream);
                        return true;
                    }
                    Util.closeQuietly(dataInputStream);
                    return false;
                }
                Util.closeQuietly(dataInputStream);
                return false;
            } catch (IOException unused2) {
                dataInputStream2 = dataInputStream;
                if (dataInputStream2 != null) {
                    Util.closeQuietly(dataInputStream2);
                }
                return false;
            } catch (Throwable th2) {
                th = th2;
                dataInputStream2 = dataInputStream;
                if (dataInputStream2 != null) {
                    Util.closeQuietly(dataInputStream2);
                }
                throw th;
            }
        }

        @Override // com.google.android.exoplayer2.upstream.cache.CachedContentIndex.Storage
        public void storeFully(HashMap<String, CachedContent> map) throws Throwable {
            writeFile(map);
            this.changed = false;
        }

        @Override // com.google.android.exoplayer2.upstream.cache.CachedContentIndex.Storage
        public void storeIncremental(HashMap<String, CachedContent> map) throws Throwable {
            if (this.changed) {
                storeFully(map);
            }
        }

        public final void writeCachedContent(CachedContent cachedContent, DataOutputStream dataOutputStream) throws IOException {
            dataOutputStream.writeInt(cachedContent.id);
            dataOutputStream.writeUTF(cachedContent.key);
            CachedContentIndex.writeContentMetadata(cachedContent.metadata, dataOutputStream);
        }

        public final void writeFile(HashMap<String, CachedContent> map) throws Throwable {
            ReusableBufferedOutputStream reusableBufferedOutputStream;
            DataOutputStream dataOutputStream;
            DataOutputStream dataOutputStream2 = null;
            try {
                OutputStream outputStreamStartWrite = this.atomicFile.startWrite();
                ReusableBufferedOutputStream reusableBufferedOutputStream2 = this.bufferedOutputStream;
                if (reusableBufferedOutputStream2 == null) {
                    this.bufferedOutputStream = new ReusableBufferedOutputStream(outputStreamStartWrite);
                } else {
                    reusableBufferedOutputStream2.reset(outputStreamStartWrite);
                }
                reusableBufferedOutputStream = this.bufferedOutputStream;
                dataOutputStream = new DataOutputStream(reusableBufferedOutputStream);
            } catch (Throwable th) {
                th = th;
            }
            try {
                dataOutputStream.writeInt(2);
                dataOutputStream.writeInt(this.encrypt ? 1 : 0);
                if (this.encrypt) {
                    byte[] bArr = new byte[16];
                    SecureRandom secureRandom = this.random;
                    Util.castNonNull(secureRandom);
                    secureRandom.nextBytes(bArr);
                    dataOutputStream.write(bArr);
                    try {
                        this.cipher.init(1, this.secretKeySpec, new IvParameterSpec(bArr));
                        dataOutputStream.flush();
                        dataOutputStream = new DataOutputStream(new CipherOutputStream(reusableBufferedOutputStream, this.cipher));
                    } catch (InvalidAlgorithmParameterException e) {
                        e = e;
                        throw new IllegalStateException(e);
                    } catch (InvalidKeyException e2) {
                        e = e2;
                        throw new IllegalStateException(e);
                    }
                }
                dataOutputStream.writeInt(map.size());
                int iHashCachedContent = 0;
                for (CachedContent cachedContent : map.values()) {
                    writeCachedContent(cachedContent, dataOutputStream);
                    iHashCachedContent += hashCachedContent(cachedContent, 2);
                }
                dataOutputStream.writeInt(iHashCachedContent);
                AtomicFile atomicFile = this.atomicFile;
                atomicFile.getClass();
                dataOutputStream.close();
                atomicFile.backupName.delete();
                Util.closeQuietly(null);
            } catch (Throwable th2) {
                th = th2;
                dataOutputStream2 = dataOutputStream;
                Util.closeQuietly(dataOutputStream2);
                throw th;
            }
        }

        @Override // com.google.android.exoplayer2.upstream.cache.CachedContentIndex.Storage
        public void initialize(long j) {
        }
    }
}
