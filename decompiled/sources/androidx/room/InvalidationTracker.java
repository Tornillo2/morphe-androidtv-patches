package androidx.room;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteException;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import androidx.annotation.VisibleForTesting;
import androidx.annotation.WorkerThread;
import androidx.arch.core.internal.SafeIterableMap;
import androidx.lifecycle.LiveData;
import androidx.room.MultiInstanceInvalidationClient;
import androidx.sqlite.db.SimpleSQLiteQuery;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.amazonaws.mobileconnectors.remoteconfiguration.Attributes;
import j$.util.DesugarCollections;
import java.lang.ref.WeakReference;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Lock;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public class InvalidationTracker {
    public static final String CREATE_TRACKING_TABLE_SQL = "CREATE TEMP TABLE room_table_modification_log(table_id INTEGER PRIMARY KEY, invalidated INTEGER NOT NULL DEFAULT 0)";
    public static final String INVALIDATED_COLUMN_NAME = "invalidated";

    @VisibleForTesting
    public static final String RESET_UPDATED_TABLES_SQL = "UPDATE room_table_modification_log SET invalidated = 0 WHERE invalidated = 1 ";

    @VisibleForTesting
    public static final String SELECT_UPDATED_TABLES_SQL = "SELECT * FROM room_table_modification_log WHERE invalidated = 1;";
    public static final String TABLE_ID_COLUMN_NAME = "table_id";
    public static final String[] TRIGGERS = {"UPDATE", "DELETE", "INSERT"};
    public static final String UPDATE_TABLE_NAME = "room_table_modification_log";
    public volatile SupportSQLiteStatement mCleanupStatement;
    public final RoomDatabase mDatabase;
    public volatile boolean mInitialized;
    public final InvalidationLiveDataContainer mInvalidationLiveDataContainer;
    public MultiInstanceInvalidationClient mMultiInstanceInvalidationClient;
    public ObservedTableTracker mObservedTableTracker;

    @SuppressLint({"RestrictedApi"})
    @VisibleForTesting
    public final SafeIterableMap<Observer, ObserverWrapper> mObserverMap;
    public AtomicBoolean mPendingRefresh;

    @VisibleForTesting
    public Runnable mRefreshRunnable;

    @NonNull
    public final HashMap<String, Integer> mTableIdLookup;
    public final String[] mTableNames;

    @NonNull
    public Map<String, Set<String>> mViewTables;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static class ObservedTableTracker {
        public static final int ADD = 1;
        public static final int NO_OP = 0;
        public static final int REMOVE = 2;
        public boolean mNeedsSync;
        public boolean mPendingSync;
        public final long[] mTableObservers;
        public final int[] mTriggerStateChanges;
        public final boolean[] mTriggerStates;

        public ObservedTableTracker(int i) {
            long[] jArr = new long[i];
            this.mTableObservers = jArr;
            boolean[] zArr = new boolean[i];
            this.mTriggerStates = zArr;
            this.mTriggerStateChanges = new int[i];
            Arrays.fill(jArr, 0L);
            Arrays.fill(zArr, false);
        }

        @Nullable
        public int[] getTablesToSync() {
            synchronized (this) {
                try {
                    if (this.mNeedsSync && !this.mPendingSync) {
                        int length = this.mTableObservers.length;
                        int i = 0;
                        while (true) {
                            int i2 = 1;
                            if (i >= length) {
                                this.mPendingSync = true;
                                this.mNeedsSync = false;
                                return this.mTriggerStateChanges;
                            }
                            boolean z = this.mTableObservers[i] > 0;
                            boolean[] zArr = this.mTriggerStates;
                            if (z != zArr[i]) {
                                int[] iArr = this.mTriggerStateChanges;
                                if (!z) {
                                    i2 = 2;
                                }
                                iArr[i] = i2;
                            } else {
                                this.mTriggerStateChanges[i] = 0;
                            }
                            zArr[i] = z;
                            i++;
                        }
                    }
                    return null;
                } finally {
                }
            }
        }

        public boolean onAdded(int... iArr) {
            boolean z;
            synchronized (this) {
                try {
                    z = false;
                    for (int i : iArr) {
                        long[] jArr = this.mTableObservers;
                        long j = jArr[i];
                        jArr[i] = 1 + j;
                        if (j == 0) {
                            z = true;
                            this.mNeedsSync = true;
                        }
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
            return z;
        }

        public boolean onRemoved(int... iArr) {
            boolean z;
            synchronized (this) {
                try {
                    z = false;
                    for (int i : iArr) {
                        long[] jArr = this.mTableObservers;
                        long j = jArr[i];
                        jArr[i] = j - 1;
                        if (j == 1) {
                            z = true;
                            this.mNeedsSync = true;
                        }
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
            return z;
        }

        public void onSyncCompleted() {
            synchronized (this) {
                this.mPendingSync = false;
            }
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static class ObserverWrapper {
        public final Observer mObserver;
        public final Set<String> mSingleTableSet;
        public final int[] mTableIds;
        public final String[] mTableNames;

        public ObserverWrapper(Observer observer, int[] iArr, String[] strArr) {
            this.mObserver = observer;
            this.mTableIds = iArr;
            this.mTableNames = strArr;
            if (iArr.length != 1) {
                this.mSingleTableSet = null;
                return;
            }
            HashSet hashSet = new HashSet();
            hashSet.add(strArr[0]);
            this.mSingleTableSet = DesugarCollections.unmodifiableSet(hashSet);
        }

        public void notifyByTableInvalidStatus(Set<Integer> set) {
            int length = this.mTableIds.length;
            Set<String> hashSet = null;
            for (int i = 0; i < length; i++) {
                if (set.contains(Integer.valueOf(this.mTableIds[i]))) {
                    if (length == 1) {
                        hashSet = this.mSingleTableSet;
                    } else {
                        if (hashSet == null) {
                            hashSet = new HashSet<>(length);
                        }
                        hashSet.add(this.mTableNames[i]);
                    }
                }
            }
            if (hashSet != null) {
                this.mObserver.onInvalidated(hashSet);
            }
        }

        public void notifyByTableNames(String[] strArr) {
            Set<String> set = null;
            if (this.mTableNames.length == 1) {
                int length = strArr.length;
                int i = 0;
                while (true) {
                    if (i >= length) {
                        break;
                    }
                    if (strArr[i].equalsIgnoreCase(this.mTableNames[0])) {
                        set = this.mSingleTableSet;
                        break;
                    }
                    i++;
                }
            } else {
                HashSet hashSet = new HashSet();
                for (String str : strArr) {
                    String[] strArr2 = this.mTableNames;
                    int length2 = strArr2.length;
                    int i2 = 0;
                    while (true) {
                        if (i2 < length2) {
                            String str2 = strArr2[i2];
                            if (str2.equalsIgnoreCase(str)) {
                                hashSet.add(str2);
                                break;
                            }
                            i2++;
                        }
                    }
                }
                if (hashSet.size() > 0) {
                    set = hashSet;
                }
            }
            if (set != null) {
                this.mObserver.onInvalidated(set);
            }
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static class WeakObserver extends Observer {
        public final WeakReference<Observer> mDelegateRef;
        public final InvalidationTracker mTracker;

        public WeakObserver(InvalidationTracker invalidationTracker, Observer observer) {
            super(observer.mTables);
            this.mTracker = invalidationTracker;
            this.mDelegateRef = new WeakReference<>(observer);
        }

        @Override // androidx.room.InvalidationTracker.Observer
        public void onInvalidated(@NonNull Set<String> set) {
            Observer observer = this.mDelegateRef.get();
            if (observer == null) {
                this.mTracker.removeObserver(this);
            } else {
                observer.onInvalidated(set);
            }
        }
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
    public InvalidationTracker(RoomDatabase roomDatabase, String... strArr) {
        this(roomDatabase, new HashMap(), Collections.EMPTY_MAP, strArr);
    }

    public static void appendTriggerName(StringBuilder sb, String str, String str2) {
        sb.append("`");
        sb.append("room_table_modification_trigger_");
        sb.append(str);
        sb.append(Attributes.PREDEFINED_ATTRIBUTE_PREFIX);
        sb.append(str2);
        sb.append("`");
    }

    @SuppressLint({"RestrictedApi"})
    @WorkerThread
    public void addObserver(@NonNull Observer observer) {
        ObserverWrapper observerWrapperPutIfAbsent;
        String[] strArrResolveViews = resolveViews(observer.mTables);
        int[] iArr = new int[strArrResolveViews.length];
        int length = strArrResolveViews.length;
        for (int i = 0; i < length; i++) {
            Integer num = this.mTableIdLookup.get(strArrResolveViews[i].toLowerCase(Locale.US));
            if (num == null) {
                throw new IllegalArgumentException("There is no table with name " + strArrResolveViews[i]);
            }
            iArr[i] = num.intValue();
        }
        ObserverWrapper observerWrapper = new ObserverWrapper(observer, iArr, strArrResolveViews);
        synchronized (this.mObserverMap) {
            observerWrapperPutIfAbsent = this.mObserverMap.putIfAbsent(observer, observerWrapper);
        }
        if (observerWrapperPutIfAbsent == null && this.mObservedTableTracker.onAdded(iArr)) {
            syncTriggers();
        }
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
    public void addWeakObserver(Observer observer) {
        addObserver(new WeakObserver(this, observer));
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
    @Deprecated
    public <T> LiveData<T> createLiveData(String[] strArr, Callable<T> callable) {
        return createLiveData(strArr, false, callable);
    }

    public boolean ensureInitialization() {
        if (!this.mDatabase.isOpen()) {
            return false;
        }
        if (!this.mInitialized) {
            this.mDatabase.getOpenHelper().getWritableDatabase();
        }
        if (this.mInitialized) {
            return true;
        }
        Log.e(Room.LOG_TAG, "database is not initialized even though it is open");
        return false;
    }

    public void internalInit(SupportSQLiteDatabase supportSQLiteDatabase) {
        synchronized (this) {
            try {
                if (this.mInitialized) {
                    Log.e(Room.LOG_TAG, "Invalidation tracker is initialized twice :/.");
                    return;
                }
                supportSQLiteDatabase.execSQL("PRAGMA temp_store = MEMORY;");
                supportSQLiteDatabase.execSQL("PRAGMA recursive_triggers='ON';");
                supportSQLiteDatabase.execSQL(CREATE_TRACKING_TABLE_SQL);
                syncTriggers(supportSQLiteDatabase);
                this.mCleanupStatement = supportSQLiteDatabase.compileStatement(RESET_UPDATED_TABLES_SQL);
                this.mInitialized = true;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY})
    @VisibleForTesting(otherwise = 3)
    public void notifyObserversByTableNames(String... strArr) {
        synchronized (this.mObserverMap) {
            try {
                Iterator<Map.Entry<Observer, ObserverWrapper>> it = this.mObserverMap.iterator();
                while (((SafeIterableMap.ListIterator) it).hasNext()) {
                    Map.Entry next = ((SafeIterableMap.ListIterator) it).next();
                    if (!((Observer) next.getKey()).isRemote()) {
                        ((ObserverWrapper) next.getValue()).notifyByTableNames(strArr);
                    }
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public void refreshVersionsAsync() {
        if (this.mPendingRefresh.compareAndSet(false, true)) {
            this.mDatabase.getQueryExecutor().execute(this.mRefreshRunnable);
        }
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
    @WorkerThread
    public void refreshVersionsSync() {
        syncTriggers();
        this.mRefreshRunnable.run();
    }

    @SuppressLint({"RestrictedApi"})
    @WorkerThread
    public void removeObserver(@NonNull Observer observer) {
        ObserverWrapper observerWrapperRemove;
        synchronized (this.mObserverMap) {
            observerWrapperRemove = this.mObserverMap.remove(observer);
        }
        if (observerWrapperRemove == null || !this.mObservedTableTracker.onRemoved(observerWrapperRemove.mTableIds)) {
            return;
        }
        syncTriggers();
    }

    public final String[] resolveViews(String[] strArr) {
        HashSet hashSet = new HashSet();
        for (String str : strArr) {
            String lowerCase = str.toLowerCase(Locale.US);
            if (this.mViewTables.containsKey(lowerCase)) {
                hashSet.addAll(this.mViewTables.get(lowerCase));
            } else {
                hashSet.add(str);
            }
        }
        return (String[]) hashSet.toArray(new String[hashSet.size()]);
    }

    public void startMultiInstanceInvalidation(Context context, String str) {
        this.mMultiInstanceInvalidationClient = new MultiInstanceInvalidationClient(context, str, this, this.mDatabase.getQueryExecutor());
    }

    public final void startTrackingTable(SupportSQLiteDatabase supportSQLiteDatabase, int i) {
        supportSQLiteDatabase.execSQL("INSERT OR IGNORE INTO room_table_modification_log VALUES(" + i + ", 0)");
        String str = this.mTableNames[i];
        StringBuilder sb = new StringBuilder();
        for (String str2 : TRIGGERS) {
            sb.setLength(0);
            sb.append("CREATE TEMP TRIGGER IF NOT EXISTS ");
            appendTriggerName(sb, str, str2);
            sb.append(" AFTER ");
            sb.append(str2);
            sb.append(" ON `");
            sb.append(str);
            sb.append("` BEGIN UPDATE ");
            sb.append(UPDATE_TABLE_NAME);
            sb.append(" SET ");
            sb.append(INVALIDATED_COLUMN_NAME);
            sb.append(" = 1");
            sb.append(" WHERE ");
            sb.append(TABLE_ID_COLUMN_NAME);
            sb.append(" = ");
            sb.append(i);
            sb.append(" AND ");
            sb.append(INVALIDATED_COLUMN_NAME);
            sb.append(" = 0");
            sb.append("; END");
            supportSQLiteDatabase.execSQL(sb.toString());
        }
    }

    public void stopMultiInstanceInvalidation() {
        MultiInstanceInvalidationClient multiInstanceInvalidationClient = this.mMultiInstanceInvalidationClient;
        if (multiInstanceInvalidationClient != null) {
            multiInstanceInvalidationClient.stop();
            this.mMultiInstanceInvalidationClient = null;
        }
    }

    public final void stopTrackingTable(SupportSQLiteDatabase supportSQLiteDatabase, int i) {
        String str = this.mTableNames[i];
        StringBuilder sb = new StringBuilder();
        for (String str2 : TRIGGERS) {
            sb.setLength(0);
            sb.append("DROP TRIGGER IF EXISTS ");
            appendTriggerName(sb, str, str2);
            supportSQLiteDatabase.execSQL(sb.toString());
        }
    }

    public void syncTriggers(SupportSQLiteDatabase supportSQLiteDatabase) {
        if (supportSQLiteDatabase.inTransaction()) {
            return;
        }
        while (true) {
            try {
                Lock closeLock = this.mDatabase.getCloseLock();
                closeLock.lock();
                try {
                    int[] tablesToSync = this.mObservedTableTracker.getTablesToSync();
                    if (tablesToSync == null) {
                        return;
                    }
                    int length = tablesToSync.length;
                    supportSQLiteDatabase.beginTransaction();
                    for (int i = 0; i < length; i++) {
                        try {
                            int i2 = tablesToSync[i];
                            if (i2 == 1) {
                                startTrackingTable(supportSQLiteDatabase, i);
                            } else if (i2 == 2) {
                                stopTrackingTable(supportSQLiteDatabase, i);
                            }
                        } finally {
                        }
                    }
                    supportSQLiteDatabase.setTransactionSuccessful();
                    supportSQLiteDatabase.endTransaction();
                    this.mObservedTableTracker.onSyncCompleted();
                } finally {
                    closeLock.unlock();
                }
            } catch (SQLiteException | IllegalStateException e) {
                Log.e(Room.LOG_TAG, "Cannot run invalidation tracker. Is the db closed?", e);
                return;
            }
        }
    }

    public final String[] validateAndResolveTableNames(String[] strArr) {
        String[] strArrResolveViews = resolveViews(strArr);
        for (String str : strArrResolveViews) {
            if (!this.mTableIdLookup.containsKey(str.toLowerCase(Locale.US))) {
                throw new IllegalArgumentException("There is no table with name ".concat(str));
            }
        }
        return strArrResolveViews;
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
    public InvalidationTracker(RoomDatabase roomDatabase, Map<String, String> map, Map<String, Set<String>> map2, String... strArr) {
        this.mPendingRefresh = new AtomicBoolean(false);
        this.mInitialized = false;
        this.mObserverMap = new SafeIterableMap<>();
        this.mRefreshRunnable = new Runnable() { // from class: androidx.room.InvalidationTracker.1
            public final Set<Integer> checkUpdatedTable() {
                HashSet hashSet = new HashSet();
                Cursor cursorQuery = InvalidationTracker.this.mDatabase.query(new SimpleSQLiteQuery(InvalidationTracker.SELECT_UPDATED_TABLES_SQL, null));
                while (cursorQuery.moveToNext()) {
                    try {
                        hashSet.add(Integer.valueOf(cursorQuery.getInt(0)));
                    } catch (Throwable th) {
                        cursorQuery.close();
                        throw th;
                    }
                }
                cursorQuery.close();
                if (!hashSet.isEmpty()) {
                    InvalidationTracker.this.mCleanupStatement.executeUpdateDelete();
                }
                return hashSet;
            }

            @Override // java.lang.Runnable
            public void run() {
                Lock closeLock = InvalidationTracker.this.mDatabase.getCloseLock();
                Set<Integer> setCheckUpdatedTable = null;
                try {
                    try {
                        closeLock.lock();
                    } finally {
                        closeLock.unlock();
                    }
                } catch (SQLiteException e) {
                    e = e;
                    Log.e(Room.LOG_TAG, "Cannot run invalidation tracker. Is the db closed?", e);
                } catch (IllegalStateException e2) {
                    e = e2;
                    Log.e(Room.LOG_TAG, "Cannot run invalidation tracker. Is the db closed?", e);
                }
                if (!InvalidationTracker.this.ensureInitialization()) {
                    closeLock.unlock();
                    return;
                }
                if (!InvalidationTracker.this.mPendingRefresh.compareAndSet(true, false)) {
                    closeLock.unlock();
                    return;
                }
                if (InvalidationTracker.this.mDatabase.inTransaction()) {
                    closeLock.unlock();
                    return;
                }
                RoomDatabase roomDatabase2 = InvalidationTracker.this.mDatabase;
                if (roomDatabase2.mWriteAheadLoggingEnabled) {
                    SupportSQLiteDatabase writableDatabase = roomDatabase2.getOpenHelper().getWritableDatabase();
                    writableDatabase.beginTransaction();
                    try {
                        setCheckUpdatedTable = checkUpdatedTable();
                        writableDatabase.setTransactionSuccessful();
                        writableDatabase.endTransaction();
                    } catch (Throwable th) {
                        writableDatabase.endTransaction();
                        throw th;
                    }
                } else {
                    setCheckUpdatedTable = checkUpdatedTable();
                }
                closeLock.unlock();
                if (setCheckUpdatedTable == null || setCheckUpdatedTable.isEmpty()) {
                    return;
                }
                synchronized (InvalidationTracker.this.mObserverMap) {
                    try {
                        Iterator<Map.Entry<Observer, ObserverWrapper>> it = InvalidationTracker.this.mObserverMap.iterator();
                        while (((SafeIterableMap.ListIterator) it).hasNext()) {
                            ((ObserverWrapper) ((SafeIterableMap.ListIterator) it).next().getValue()).notifyByTableInvalidStatus(setCheckUpdatedTable);
                        }
                    } finally {
                    }
                }
            }
        };
        this.mDatabase = roomDatabase;
        this.mObservedTableTracker = new ObservedTableTracker(strArr.length);
        this.mTableIdLookup = new HashMap<>();
        this.mViewTables = map2;
        this.mInvalidationLiveDataContainer = new InvalidationLiveDataContainer(roomDatabase);
        int length = strArr.length;
        this.mTableNames = new String[length];
        for (int i = 0; i < length; i++) {
            String str = strArr[i];
            Locale locale = Locale.US;
            String lowerCase = str.toLowerCase(locale);
            this.mTableIdLookup.put(lowerCase, Integer.valueOf(i));
            String str2 = map.get(strArr[i]);
            if (str2 != null) {
                this.mTableNames[i] = str2.toLowerCase(locale);
            } else {
                this.mTableNames[i] = lowerCase;
            }
        }
        for (Map.Entry<String, String> entry : map.entrySet()) {
            String value = entry.getValue();
            Locale locale2 = Locale.US;
            String lowerCase2 = value.toLowerCase(locale2);
            if (this.mTableIdLookup.containsKey(lowerCase2)) {
                String lowerCase3 = entry.getKey().toLowerCase(locale2);
                HashMap<String, Integer> map3 = this.mTableIdLookup;
                map3.put(lowerCase3, map3.get(lowerCase2));
            }
        }
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
    public <T> LiveData<T> createLiveData(String[] strArr, boolean z, Callable<T> callable) {
        return this.mInvalidationLiveDataContainer.create(validateAndResolveTableNames(strArr), z, callable);
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static abstract class Observer {
        public final String[] mTables;

        public Observer(@NonNull String str, String... strArr) {
            String[] strArr2 = (String[]) Arrays.copyOf(strArr, strArr.length + 1);
            this.mTables = strArr2;
            strArr2[strArr.length] = str;
        }

        public boolean isRemote() {
            return this instanceof MultiInstanceInvalidationClient.AnonymousClass6;
        }

        public abstract void onInvalidated(@NonNull Set<String> set);

        public Observer(@NonNull String[] strArr) {
            this.mTables = (String[]) Arrays.copyOf(strArr, strArr.length);
        }
    }

    public void syncTriggers() {
        if (this.mDatabase.isOpen()) {
            syncTriggers(this.mDatabase.getOpenHelper().getWritableDatabase());
        }
    }
}
