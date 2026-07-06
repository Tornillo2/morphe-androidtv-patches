package com.google.android.datatransport.runtime.scheduling.persistence;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabaseLockedException;
import android.os.SystemClock;
import android.util.Base64;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import androidx.annotation.VisibleForTesting;
import androidx.annotation.WorkerThread;
import androidx.tvprovider.media.tv.ChannelLogoUtils$$ExternalSyntheticOutline0;
import com.amazon.minerva.client.thirdparty.serializer.MetricBatchJsonSerializer;
import com.google.android.datatransport.Encoding;
import com.google.android.datatransport.Priority;
import com.google.android.datatransport.runtime.AutoValue_EventInternal;
import com.google.android.datatransport.runtime.AutoValue_TransportContext;
import com.google.android.datatransport.runtime.EncodedPayload;
import com.google.android.datatransport.runtime.EventInternal;
import com.google.android.datatransport.runtime.TransportContext;
import com.google.android.datatransport.runtime.firebase.transport.ClientMetrics;
import com.google.android.datatransport.runtime.firebase.transport.GlobalMetrics;
import com.google.android.datatransport.runtime.firebase.transport.LogEventDropped;
import com.google.android.datatransport.runtime.firebase.transport.LogSourceMetrics;
import com.google.android.datatransport.runtime.firebase.transport.StorageMetrics;
import com.google.android.datatransport.runtime.firebase.transport.TimeWindow;
import com.google.android.datatransport.runtime.logging.Logging;
import com.google.android.datatransport.runtime.synchronization.SynchronizationException;
import com.google.android.datatransport.runtime.synchronization.SynchronizationGuard;
import com.google.android.datatransport.runtime.time.Clock;
import com.google.android.datatransport.runtime.time.Monotonic;
import com.google.android.datatransport.runtime.time.WallTime;
import com.google.android.datatransport.runtime.util.PriorityMapping;
import j$.util.Objects;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Set;
import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Provider;
import javax.inject.Singleton;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@Singleton
@WorkerThread
public class SQLiteEventStore implements EventStore, SynchronizationGuard, ClientHealthMetricsStore {
    public static final int LOCK_RETRY_BACK_OFF_MILLIS = 50;
    public static final String LOG_TAG = "SQLiteEventStore";
    public static final int MAX_RETRIES = 16;
    public static final Encoding PROTOBUF_ENCODING = new Encoding("proto");
    public final EventStoreConfig config;
    public final Clock monotonicClock;
    public final Provider<String> packageName;
    public final SchemaManager schemaManager;
    public final Clock wallClock;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public interface Function<T, U> {
        U apply(T t);
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static class Metadata {
        public final String key;
        public final String value;

        public Metadata(String str, String str2) {
            this.key = str;
            this.value = str2;
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public interface Producer<T> {
        T produce();
    }

    /* JADX INFO: renamed from: $r8$lambda$-2sc8nDh-80TMJWoAz3fuM4SGoA, reason: not valid java name */
    public static TimeWindow m355$r8$lambda$2sc8nDh80TMJWoAz3fuM4SGoA(long j, Cursor cursor) {
        cursor.moveToNext();
        long j2 = cursor.getLong(0);
        TimeWindow.Builder builderNewBuilder = TimeWindow.newBuilder();
        builderNewBuilder.start_ms_ = j2;
        builderNewBuilder.end_ms_ = j;
        return builderNewBuilder.build();
    }

    /* JADX INFO: renamed from: $r8$lambda$-c5yRpmc2Pusxok-ESpsItypEVU, reason: not valid java name */
    public static /* synthetic */ List m356$r8$lambda$c5yRpmc2PusxokESpsItypEVU(SQLiteEventStore sQLiteEventStore, TransportContext transportContext, SQLiteDatabase sQLiteDatabase) {
        List<PersistedEvent> listLoadEvents = sQLiteEventStore.loadEvents(sQLiteDatabase, transportContext, sQLiteEventStore.config.getLoadBatchSize());
        for (Priority priority : Priority.values()) {
            if (priority != transportContext.getPriority()) {
                ArrayList arrayList = (ArrayList) listLoadEvents;
                int loadBatchSize = sQLiteEventStore.config.getLoadBatchSize() - arrayList.size();
                if (loadBatchSize <= 0) {
                    break;
                }
                arrayList.addAll(sQLiteEventStore.loadEvents(sQLiteDatabase, transportContext.withPriority(priority), loadBatchSize));
            }
        }
        sQLiteEventStore.join(listLoadEvents, sQLiteEventStore.loadMetadata(sQLiteDatabase, listLoadEvents));
        return listLoadEvents;
    }

    public static List $r8$lambda$5Uvct5cGMjQeavljSmguhr62CK8(Cursor cursor) {
        ArrayList arrayList = new ArrayList();
        while (cursor.moveToNext()) {
            TransportContext.Builder builder = TransportContext.builder();
            builder.setBackendName(cursor.getString(1));
            AutoValue_TransportContext.Builder builder2 = (AutoValue_TransportContext.Builder) builder;
            builder2.priority = PriorityMapping.valueOf(cursor.getInt(2));
            builder2.extras = maybeBase64Decode(cursor.getString(3));
            arrayList.add(builder.build());
        }
        return arrayList;
    }

    public static /* synthetic */ Object $r8$lambda$8LOwVn7k0hj_OWr4Ylfbk4bxfj8(SQLiteEventStore sQLiteEventStore, Cursor cursor) {
        sQLiteEventStore.getClass();
        while (cursor.moveToNext()) {
            sQLiteEventStore.recordLogEventDropped(cursor.getInt(0), LogEventDropped.Reason.MAX_RETRIES_REACHED, cursor.getString(1));
        }
        return null;
    }

    public static SQLiteDatabase $r8$lambda$DYhwaGroa6Bx2rA3UTBVIryQQQM(Throwable th) {
        throw new SynchronizationException("Timed out while trying to open db.", th);
    }

    public static /* synthetic */ Object $r8$lambda$FPCWqOWJeVGVZGsEViC6KdIzvC4(SQLiteEventStore sQLiteEventStore, Cursor cursor) {
        sQLiteEventStore.getClass();
        while (cursor.moveToNext()) {
            sQLiteEventStore.recordLogEventDropped(cursor.getInt(0), LogEventDropped.Reason.MESSAGE_TOO_OLD, cursor.getString(1));
        }
        return null;
    }

    public static /* synthetic */ Object $r8$lambda$FWBHKI0M7buhnYqPq5piDyOvkh8(final SQLiteEventStore sQLiteEventStore, String str, String str2, SQLiteDatabase sQLiteDatabase) {
        sQLiteEventStore.getClass();
        sQLiteDatabase.compileStatement(str).execute();
        tryWithCursor(sQLiteDatabase.rawQuery(str2, null), new Function() { // from class: com.google.android.datatransport.runtime.scheduling.persistence.SQLiteEventStore$$ExternalSyntheticLambda9
            @Override // com.google.android.datatransport.runtime.scheduling.persistence.SQLiteEventStore.Function
            public final Object apply(Object obj) {
                SQLiteEventStore.$r8$lambda$8LOwVn7k0hj_OWr4Ylfbk4bxfj8(this.f$0, (Cursor) obj);
                return null;
            }
        });
        sQLiteDatabase.compileStatement("DELETE FROM events WHERE num_attempts >= 16").execute();
        return null;
    }

    /* JADX INFO: renamed from: $r8$lambda$G8uJ-S54jP30MGd1qBdzDbSf55k, reason: not valid java name */
    public static /* synthetic */ Boolean m357$r8$lambda$G8uJS54jP30MGd1qBdzDbSf55k(SQLiteEventStore sQLiteEventStore, TransportContext transportContext, SQLiteDatabase sQLiteDatabase) {
        Long transportContextId = sQLiteEventStore.getTransportContextId(sQLiteDatabase, transportContext);
        return transportContextId == null ? Boolean.FALSE : (Boolean) tryWithCursor(sQLiteEventStore.getDb().rawQuery("SELECT 1 FROM events WHERE context_id = ? LIMIT 1", new String[]{transportContextId.toString()}), new SQLiteEventStore$$ExternalSyntheticLambda4());
    }

    public static /* synthetic */ Object $r8$lambda$GHMqzva9B_XnmPprSGyr5JoZ7b8(long j, TransportContext transportContext, SQLiteDatabase sQLiteDatabase) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("next_request_ms", Long.valueOf(j));
        if (sQLiteDatabase.update("transport_contexts", contentValues, "backend_name = ? and priority = ?", new String[]{transportContext.getBackendName(), String.valueOf(PriorityMapping.toInt(transportContext.getPriority()))}) < 1) {
            contentValues.put("backend_name", transportContext.getBackendName());
            contentValues.put("priority", Integer.valueOf(PriorityMapping.toInt(transportContext.getPriority())));
            sQLiteDatabase.insert("transport_contexts", null, contentValues);
        }
        return null;
    }

    /* JADX INFO: renamed from: $r8$lambda$MhWEBJkj-Z48pXSpbh-iwCbV5kA, reason: not valid java name */
    public static /* synthetic */ byte[] m358$r8$lambda$MhWEBJkjZ48pXSpbhiwCbV5kA(Cursor cursor) {
        ArrayList arrayList = new ArrayList();
        int length = 0;
        while (cursor.moveToNext()) {
            byte[] blob = cursor.getBlob(0);
            arrayList.add(blob);
            length += blob.length;
        }
        byte[] bArr = new byte[length];
        int length2 = 0;
        for (int i = 0; i < arrayList.size(); i++) {
            byte[] bArr2 = (byte[]) arrayList.get(i);
            System.arraycopy(bArr2, 0, bArr, length2, bArr2.length);
            length2 += bArr2.length;
        }
        return bArr;
    }

    public static /* synthetic */ List $r8$lambda$OYeq4gZ6BLfEujKfNIuFZurIZak(SQLiteDatabase sQLiteDatabase) {
        return (List) tryWithCursor(sQLiteDatabase.rawQuery("SELECT distinct t._id, t.backend_name, t.priority, t.extras FROM transport_contexts AS t, events AS e WHERE e.context_id = t._id", new String[0]), new SQLiteEventStore$$ExternalSyntheticLambda21());
    }

    /* JADX INFO: renamed from: $r8$lambda$RbOgr-U7H41WTgNTtm08JTmKZL8, reason: not valid java name */
    public static ClientMetrics m359$r8$lambda$RbOgrU7H41WTgNTtm08JTmKZL8(SQLiteEventStore sQLiteEventStore, Map map, ClientMetrics.Builder builder, Cursor cursor) {
        sQLiteEventStore.getClass();
        while (cursor.moveToNext()) {
            String string = cursor.getString(0);
            LogEventDropped.Reason reasonConvertToReason = sQLiteEventStore.convertToReason(cursor.getInt(1));
            long j = cursor.getLong(2);
            if (!map.containsKey(string)) {
                map.put(string, new ArrayList());
            }
            List list = (List) map.get(string);
            LogEventDropped.Builder builderNewBuilder = LogEventDropped.newBuilder();
            builderNewBuilder.reason_ = reasonConvertToReason;
            builderNewBuilder.events_dropped_count_ = j;
            list.add(builderNewBuilder.build());
        }
        sQLiteEventStore.populateLogSourcesMetrics(builder, map);
        builder.window_ = sQLiteEventStore.getTimeWindow();
        builder.global_metrics_ = sQLiteEventStore.getGlobalMetrics();
        builder.app_namespace_ = sQLiteEventStore.packageName.get();
        return builder.build();
    }

    public static /* synthetic */ Object $r8$lambda$Ywc_XdJ71otWx32TGVXHNljPxcI(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.delete(MetricBatchJsonSerializer.EVENTS, null, new String[0]);
        sQLiteDatabase.delete("transport_contexts", null, new String[0]);
        return null;
    }

    /* JADX INFO: renamed from: $r8$lambda$Yygha28Nx4WElzmz7AGC_M3n-iQ, reason: not valid java name */
    public static Long m360$r8$lambda$Yygha28Nx4WElzmz7AGC_M3niQ(SQLiteEventStore sQLiteEventStore, EventInternal eventInternal, TransportContext transportContext, SQLiteDatabase sQLiteDatabase) {
        if (sQLiteEventStore.isStorageAtLimit()) {
            sQLiteEventStore.recordLogEventDropped(1L, LogEventDropped.Reason.CACHE_FULL, eventInternal.getTransportName());
            return -1L;
        }
        long jEnsureTransportContext = sQLiteEventStore.ensureTransportContext(sQLiteDatabase, transportContext);
        int maxBlobByteSizePerRow = sQLiteEventStore.config.getMaxBlobByteSizePerRow();
        byte[] bArr = eventInternal.getEncodedPayload().bytes;
        boolean z = bArr.length <= maxBlobByteSizePerRow;
        ContentValues contentValues = new ContentValues();
        contentValues.put("context_id", Long.valueOf(jEnsureTransportContext));
        contentValues.put("transport_name", eventInternal.getTransportName());
        contentValues.put("timestamp_ms", Long.valueOf(eventInternal.getEventMillis()));
        contentValues.put("uptime_ms", Long.valueOf(eventInternal.getUptimeMillis()));
        contentValues.put("payload_encoding", eventInternal.getEncodedPayload().encoding.name);
        contentValues.put("code", eventInternal.getCode());
        contentValues.put("num_attempts", (Integer) 0);
        contentValues.put("inline", Boolean.valueOf(z));
        contentValues.put("payload", z ? bArr : new byte[0]);
        long jInsert = sQLiteDatabase.insert(MetricBatchJsonSerializer.EVENTS, null, contentValues);
        if (!z) {
            int iCeil = (int) Math.ceil(((double) bArr.length) / ((double) maxBlobByteSizePerRow));
            for (int i = 1; i <= iCeil; i++) {
                byte[] bArrCopyOfRange = Arrays.copyOfRange(bArr, (i - 1) * maxBlobByteSizePerRow, Math.min(i * maxBlobByteSizePerRow, bArr.length));
                ContentValues contentValues2 = new ContentValues();
                contentValues2.put("event_id", Long.valueOf(jInsert));
                contentValues2.put("sequence_num", Integer.valueOf(i));
                contentValues2.put("bytes", bArrCopyOfRange);
                sQLiteDatabase.insert("event_payloads", null, contentValues2);
            }
        }
        for (Map.Entry<String, String> entry : eventInternal.getMetadata().entrySet()) {
            ContentValues contentValues3 = new ContentValues();
            contentValues3.put("event_id", Long.valueOf(jInsert));
            contentValues3.put("name", entry.getKey());
            contentValues3.put("value", entry.getValue());
            sQLiteDatabase.insert("event_metadata", null, contentValues3);
        }
        return Long.valueOf(jInsert);
    }

    /* JADX INFO: renamed from: $r8$lambda$bBQL4XWV8rqSJshoDiUR95y-_jM, reason: not valid java name */
    public static /* synthetic */ Object m361$r8$lambda$bBQL4XWV8rqSJshoDiUR95y_jM(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.beginTransaction();
        return null;
    }

    public static /* synthetic */ Long $r8$lambda$cAnhwi1GNvuKjmAGBcKUf1ZtJ8M(Cursor cursor) {
        if (cursor.moveToNext()) {
            return Long.valueOf(cursor.getLong(0));
        }
        return null;
    }

    public static Object $r8$lambda$cMN1kDUWPXuzjr1P3QAWoeACqG8(Throwable th) {
        throw new SynchronizationException("Timed out while trying to acquire the lock.", th);
    }

    /* JADX INFO: renamed from: $r8$lambda$m2Cid-bNtM3iXXm7FvzU6_pgJWc, reason: not valid java name */
    public static Object m362$r8$lambda$m2CidbNtM3iXXm7FvzU6_pgJWc(String str, LogEventDropped.Reason reason, long j, SQLiteDatabase sQLiteDatabase) {
        if (((Boolean) tryWithCursor(sQLiteDatabase.rawQuery("SELECT 1 FROM log_event_dropped WHERE log_source = ? AND reason = ?", new String[]{str, Integer.toString(reason.number_)}), new SQLiteEventStore$$ExternalSyntheticLambda12())).booleanValue()) {
            sQLiteDatabase.execSQL(ChannelLogoUtils$$ExternalSyntheticOutline0.m("UPDATE log_event_dropped SET events_dropped_count = events_dropped_count + ", j, " WHERE log_source = ? AND reason = ?"), new String[]{str, Integer.toString(reason.number_)});
        } else {
            ContentValues contentValues = new ContentValues();
            contentValues.put("log_source", str);
            contentValues.put("reason", Integer.valueOf(reason.number_));
            contentValues.put("events_dropped_count", Long.valueOf(j));
            sQLiteDatabase.insert("log_event_dropped", null, contentValues);
        }
        return null;
    }

    public static /* synthetic */ Object $r8$lambda$mObue6vDcpMgJg6tRUgbT0MgVDo(Map map, Cursor cursor) {
        while (cursor.moveToNext()) {
            long j = cursor.getLong(0);
            Set hashSet = (Set) map.get(Long.valueOf(j));
            if (hashSet == null) {
                hashSet = new HashSet();
                map.put(Long.valueOf(j), hashSet);
            }
            hashSet.add(new Metadata(cursor.getString(1), cursor.getString(2)));
        }
        return null;
    }

    public static /* synthetic */ Integer $r8$lambda$oWyA6AJGDizu9ZmA0KcBicBGgCY(final SQLiteEventStore sQLiteEventStore, long j, SQLiteDatabase sQLiteDatabase) {
        sQLiteEventStore.getClass();
        String[] strArr = {String.valueOf(j)};
        tryWithCursor(sQLiteDatabase.rawQuery("SELECT COUNT(*), transport_name FROM events WHERE timestamp_ms < ? GROUP BY transport_name", strArr), new Function() { // from class: com.google.android.datatransport.runtime.scheduling.persistence.SQLiteEventStore$$ExternalSyntheticLambda27
            @Override // com.google.android.datatransport.runtime.scheduling.persistence.SQLiteEventStore.Function
            public final Object apply(Object obj) {
                SQLiteEventStore.$r8$lambda$FPCWqOWJeVGVZGsEViC6KdIzvC4(this.f$0, (Cursor) obj);
                return null;
            }
        });
        return Integer.valueOf(sQLiteDatabase.delete(MetricBatchJsonSerializer.EVENTS, "timestamp_ms < ?", strArr));
    }

    public static Object $r8$lambda$rhfHXis96N9wATghbYmJ6PQO9G0(SQLiteEventStore sQLiteEventStore, List list, TransportContext transportContext, Cursor cursor) {
        sQLiteEventStore.getClass();
        while (cursor.moveToNext()) {
            long j = cursor.getLong(0);
            boolean z = cursor.getInt(7) != 0;
            EventInternal.Builder builder = EventInternal.builder();
            builder.setTransportName(cursor.getString(1));
            builder.setEventMillis(cursor.getLong(2));
            builder.setUptimeMillis(cursor.getLong(3));
            if (z) {
                ((AutoValue_EventInternal.Builder) builder).encodedPayload = new EncodedPayload(toEncoding(cursor.getString(4)), cursor.getBlob(5));
            } else {
                ((AutoValue_EventInternal.Builder) builder).encodedPayload = new EncodedPayload(toEncoding(cursor.getString(4)), sQLiteEventStore.readPayload(j));
            }
            if (!cursor.isNull(6)) {
                ((AutoValue_EventInternal.Builder) builder).code = Integer.valueOf(cursor.getInt(6));
            }
            list.add(new AutoValue_PersistedEvent(j, transportContext, builder.build()));
        }
        return null;
    }

    /* JADX INFO: renamed from: $r8$lambda$tjc_qlQgTB-9tdANbPFpY4kEz4U, reason: not valid java name */
    public static /* synthetic */ Object m363$r8$lambda$tjc_qlQgTB9tdANbPFpY4kEz4U(SQLiteEventStore sQLiteEventStore, SQLiteDatabase sQLiteDatabase) {
        sQLiteEventStore.getClass();
        sQLiteDatabase.compileStatement("DELETE FROM log_event_dropped").execute();
        sQLiteDatabase.compileStatement("UPDATE global_log_event_state SET last_metrics_upload_ms=" + sQLiteEventStore.wallClock.getTime()).execute();
        return null;
    }

    public static /* synthetic */ ClientMetrics $r8$lambda$wkPqtdo8abVVVntIT0LL_IpuNhI(final SQLiteEventStore sQLiteEventStore, String str, final Map map, final ClientMetrics.Builder builder, SQLiteDatabase sQLiteDatabase) {
        sQLiteEventStore.getClass();
        return (ClientMetrics) tryWithCursor(sQLiteDatabase.rawQuery(str, new String[0]), new Function() { // from class: com.google.android.datatransport.runtime.scheduling.persistence.SQLiteEventStore$$ExternalSyntheticLambda15
            @Override // com.google.android.datatransport.runtime.scheduling.persistence.SQLiteEventStore.Function
            public final Object apply(Object obj) {
                return SQLiteEventStore.m359$r8$lambda$RbOgrU7H41WTgNTtm08JTmKZL8(this.f$0, map, builder, (Cursor) obj);
            }
        });
    }

    public static /* synthetic */ Long $r8$lambda$zCKpHwX4pX2SAl7zbN4WB6el84g(Cursor cursor) {
        if (cursor.moveToNext()) {
            return Long.valueOf(cursor.getLong(0));
        }
        return 0L;
    }

    public static /* synthetic */ TimeWindow $r8$lambda$zOq4Ptcu6jxQh2yulOulwMnkbKw(final long j, SQLiteDatabase sQLiteDatabase) {
        return (TimeWindow) tryWithCursor(sQLiteDatabase.rawQuery("SELECT last_metrics_upload_ms FROM global_log_event_state LIMIT 1", new String[0]), new Function() { // from class: com.google.android.datatransport.runtime.scheduling.persistence.SQLiteEventStore$$ExternalSyntheticLambda26
            @Override // com.google.android.datatransport.runtime.scheduling.persistence.SQLiteEventStore.Function
            public final Object apply(Object obj) {
                return SQLiteEventStore.m355$r8$lambda$2sc8nDh80TMJWoAz3fuM4SGoA(j, (Cursor) obj);
            }
        });
    }

    @Inject
    public SQLiteEventStore(@WallTime Clock clock, @Monotonic Clock clock2, EventStoreConfig eventStoreConfig, SchemaManager schemaManager, @Named("PACKAGE_NAME") Provider<String> provider) {
        this.schemaManager = schemaManager;
        this.wallClock = clock;
        this.monotonicClock = clock2;
        this.config = eventStoreConfig;
        this.packageName = provider;
    }

    public static byte[] maybeBase64Decode(@Nullable String str) {
        if (str == null) {
            return null;
        }
        return Base64.decode(str, 0);
    }

    public static Encoding toEncoding(@Nullable String str) {
        return str == null ? PROTOBUF_ENCODING : new Encoding(str);
    }

    public static String toIdList(Iterable<PersistedEvent> iterable) {
        StringBuilder sb = new StringBuilder("(");
        Iterator<PersistedEvent> it = iterable.iterator();
        while (it.hasNext()) {
            sb.append(it.next().getId());
            if (it.hasNext()) {
                sb.append(',');
            }
        }
        sb.append(')');
        return sb.toString();
    }

    @VisibleForTesting
    public static <T> T tryWithCursor(Cursor cursor, Function<Cursor, T> function) {
        try {
            return function.apply(cursor);
        } finally {
            cursor.close();
        }
    }

    @Override // com.google.android.datatransport.runtime.scheduling.persistence.EventStore
    public int cleanUp() {
        final long time = this.wallClock.getTime() - this.config.getEventCleanUpAge();
        return ((Integer) inTransaction(new Function() { // from class: com.google.android.datatransport.runtime.scheduling.persistence.SQLiteEventStore$$ExternalSyntheticLambda19
            @Override // com.google.android.datatransport.runtime.scheduling.persistence.SQLiteEventStore.Function
            public final Object apply(Object obj) {
                return SQLiteEventStore.$r8$lambda$oWyA6AJGDizu9ZmA0KcBicBGgCY(this.f$0, time, (SQLiteDatabase) obj);
            }
        })).intValue();
    }

    @RestrictTo({RestrictTo.Scope.TESTS})
    public void clearDb() {
        inTransaction(new SQLiteEventStore$$ExternalSyntheticLambda5());
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        this.schemaManager.close();
    }

    public final LogEventDropped.Reason convertToReason(int i) {
        LogEventDropped.Reason reason = LogEventDropped.Reason.REASON_UNKNOWN;
        if (i == reason.number_) {
            return reason;
        }
        LogEventDropped.Reason reason2 = LogEventDropped.Reason.MESSAGE_TOO_OLD;
        if (i == reason2.number_) {
            return reason2;
        }
        LogEventDropped.Reason reason3 = LogEventDropped.Reason.CACHE_FULL;
        if (i == reason3.number_) {
            return reason3;
        }
        LogEventDropped.Reason reason4 = LogEventDropped.Reason.PAYLOAD_TOO_BIG;
        if (i == reason4.number_) {
            return reason4;
        }
        LogEventDropped.Reason reason5 = LogEventDropped.Reason.MAX_RETRIES_REACHED;
        if (i == reason5.number_) {
            return reason5;
        }
        LogEventDropped.Reason reason6 = LogEventDropped.Reason.INVALID_PAYLOD;
        if (i == reason6.number_) {
            return reason6;
        }
        LogEventDropped.Reason reason7 = LogEventDropped.Reason.SERVER_ERROR;
        if (i == reason7.number_) {
            return reason7;
        }
        Logging.d(LOG_TAG, "%n is not valid. No matched LogEventDropped-Reason found. Treated it as REASON_UNKNOWN", Integer.valueOf(i));
        return reason;
    }

    public final void ensureBeginTransaction(final SQLiteDatabase sQLiteDatabase) {
        retryIfDbLocked(new Producer() { // from class: com.google.android.datatransport.runtime.scheduling.persistence.SQLiteEventStore$$ExternalSyntheticLambda2
            @Override // com.google.android.datatransport.runtime.scheduling.persistence.SQLiteEventStore.Producer
            public final Object produce() {
                SQLiteEventStore.m361$r8$lambda$bBQL4XWV8rqSJshoDiUR95y_jM(sQLiteDatabase);
                return null;
            }
        }, new SQLiteEventStore$$ExternalSyntheticLambda3());
    }

    public final long ensureTransportContext(SQLiteDatabase sQLiteDatabase, TransportContext transportContext) {
        Long transportContextId = getTransportContextId(sQLiteDatabase, transportContext);
        if (transportContextId != null) {
            return transportContextId.longValue();
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put("backend_name", transportContext.getBackendName());
        contentValues.put("priority", Integer.valueOf(PriorityMapping.toInt(transportContext.getPriority())));
        contentValues.put("next_request_ms", (Integer) 0);
        if (transportContext.getExtras() != null) {
            contentValues.put("extras", Base64.encodeToString(transportContext.getExtras(), 0));
        }
        return sQLiteDatabase.insert("transport_contexts", null, contentValues);
    }

    @VisibleForTesting
    public long getByteSize() {
        return getPageSize() * getPageCount();
    }

    @VisibleForTesting
    public SQLiteDatabase getDb() {
        final SchemaManager schemaManager = this.schemaManager;
        Objects.requireNonNull(schemaManager);
        return (SQLiteDatabase) retryIfDbLocked(new Producer() { // from class: com.google.android.datatransport.runtime.scheduling.persistence.SQLiteEventStore$$ExternalSyntheticLambda7
            @Override // com.google.android.datatransport.runtime.scheduling.persistence.SQLiteEventStore.Producer
            public final Object produce() {
                return schemaManager.getWritableDatabase();
            }
        }, new SQLiteEventStore$$ExternalSyntheticLambda8());
    }

    public final GlobalMetrics getGlobalMetrics() {
        GlobalMetrics.Builder builderNewBuilder = GlobalMetrics.newBuilder();
        StorageMetrics.Builder builderNewBuilder2 = StorageMetrics.newBuilder();
        builderNewBuilder2.current_cache_size_bytes_ = getByteSize();
        builderNewBuilder2.max_cache_size_bytes_ = EventStoreConfig.DEFAULT.getMaxStorageSizeInBytes();
        builderNewBuilder.storage_metrics_ = builderNewBuilder2.build();
        return builderNewBuilder.build();
    }

    @Override // com.google.android.datatransport.runtime.scheduling.persistence.EventStore
    public long getNextCallTime(TransportContext transportContext) {
        return ((Long) tryWithCursor(getDb().rawQuery("SELECT next_request_ms FROM transport_contexts WHERE backend_name = ? and priority = ?", new String[]{transportContext.getBackendName(), String.valueOf(PriorityMapping.toInt(transportContext.getPriority()))}), new SQLiteEventStore$$ExternalSyntheticLambda25())).longValue();
    }

    public final long getPageCount() {
        return getDb().compileStatement("PRAGMA page_count").simpleQueryForLong();
    }

    public final long getPageSize() {
        return getDb().compileStatement("PRAGMA page_size").simpleQueryForLong();
    }

    public final TimeWindow getTimeWindow() {
        final long time = this.wallClock.getTime();
        return (TimeWindow) inTransaction(new Function() { // from class: com.google.android.datatransport.runtime.scheduling.persistence.SQLiteEventStore$$ExternalSyntheticLambda24
            @Override // com.google.android.datatransport.runtime.scheduling.persistence.SQLiteEventStore.Function
            public final Object apply(Object obj) {
                return SQLiteEventStore.$r8$lambda$zOq4Ptcu6jxQh2yulOulwMnkbKw(time, (SQLiteDatabase) obj);
            }
        });
    }

    @Nullable
    public final Long getTransportContextId(SQLiteDatabase sQLiteDatabase, TransportContext transportContext) {
        StringBuilder sb = new StringBuilder("backend_name = ? and priority = ?");
        ArrayList arrayList = new ArrayList(Arrays.asList(transportContext.getBackendName(), String.valueOf(PriorityMapping.toInt(transportContext.getPriority()))));
        if (transportContext.getExtras() != null) {
            sb.append(" and extras = ?");
            arrayList.add(Base64.encodeToString(transportContext.getExtras(), 0));
        } else {
            sb.append(" and extras is null");
        }
        return (Long) tryWithCursor(sQLiteDatabase.query("transport_contexts", new String[]{"_id"}, sb.toString(), (String[]) arrayList.toArray(new String[0]), null, null, null), new SQLiteEventStore$$ExternalSyntheticLambda11());
    }

    @Override // com.google.android.datatransport.runtime.scheduling.persistence.EventStore
    public boolean hasPendingEventsFor(final TransportContext transportContext) {
        return ((Boolean) inTransaction(new Function() { // from class: com.google.android.datatransport.runtime.scheduling.persistence.SQLiteEventStore$$ExternalSyntheticLambda14
            @Override // com.google.android.datatransport.runtime.scheduling.persistence.SQLiteEventStore.Function
            public final Object apply(Object obj) {
                return SQLiteEventStore.m357$r8$lambda$G8uJS54jP30MGd1qBdzDbSf55k(this.f$0, transportContext, (SQLiteDatabase) obj);
            }
        })).booleanValue();
    }

    @VisibleForTesting
    public <T> T inTransaction(Function<SQLiteDatabase, T> function) {
        SQLiteDatabase db = getDb();
        db.beginTransaction();
        try {
            T tApply = function.apply(db);
            db.setTransactionSuccessful();
            return tApply;
        } finally {
            db.endTransaction();
        }
    }

    public final boolean isStorageAtLimit() {
        return getPageSize() * getPageCount() >= this.config.getMaxStorageSizeInBytes();
    }

    public final List<PersistedEvent> join(List<PersistedEvent> list, Map<Long, Set<Metadata>> map) {
        ListIterator<PersistedEvent> listIterator = list.listIterator();
        while (listIterator.hasNext()) {
            PersistedEvent next = listIterator.next();
            if (map.containsKey(Long.valueOf(next.getId()))) {
                EventInternal.Builder builder = next.getEvent().toBuilder();
                for (Metadata metadata : map.get(Long.valueOf(next.getId()))) {
                    builder.addMetadata(metadata.key, metadata.value);
                }
                listIterator.set(new AutoValue_PersistedEvent(next.getId(), next.getTransportContext(), builder.build()));
            }
        }
        return list;
    }

    @Override // com.google.android.datatransport.runtime.scheduling.persistence.EventStore
    public Iterable<TransportContext> loadActiveContexts() {
        return (Iterable) inTransaction(new SQLiteEventStore$$ExternalSyntheticLambda23());
    }

    @Override // com.google.android.datatransport.runtime.scheduling.persistence.EventStore
    public Iterable<PersistedEvent> loadBatch(final TransportContext transportContext) {
        return (Iterable) inTransaction(new Function() { // from class: com.google.android.datatransport.runtime.scheduling.persistence.SQLiteEventStore$$ExternalSyntheticLambda6
            @Override // com.google.android.datatransport.runtime.scheduling.persistence.SQLiteEventStore.Function
            public final Object apply(Object obj) {
                return SQLiteEventStore.m356$r8$lambda$c5yRpmc2PusxokESpsItypEVU(this.f$0, transportContext, (SQLiteDatabase) obj);
            }
        });
    }

    @Override // com.google.android.datatransport.runtime.scheduling.persistence.ClientHealthMetricsStore
    public ClientMetrics loadClientMetrics() {
        final ClientMetrics.Builder builderNewBuilder = ClientMetrics.newBuilder();
        final HashMap map = new HashMap();
        final String str = "SELECT log_source, reason, events_dropped_count FROM log_event_dropped";
        return (ClientMetrics) inTransaction(new Function() { // from class: com.google.android.datatransport.runtime.scheduling.persistence.SQLiteEventStore$$ExternalSyntheticLambda0
            @Override // com.google.android.datatransport.runtime.scheduling.persistence.SQLiteEventStore.Function
            public final Object apply(Object obj) {
                return SQLiteEventStore.$r8$lambda$wkPqtdo8abVVVntIT0LL_IpuNhI(this.f$0, "SELECT log_source, reason, events_dropped_count FROM log_event_dropped", map, builderNewBuilder, (SQLiteDatabase) obj);
            }
        });
    }

    public final List<PersistedEvent> loadEvents(SQLiteDatabase sQLiteDatabase, final TransportContext transportContext, int i) {
        final ArrayList arrayList = new ArrayList();
        Long transportContextId = getTransportContextId(sQLiteDatabase, transportContext);
        if (transportContextId == null) {
            return arrayList;
        }
        tryWithCursor(sQLiteDatabase.query(MetricBatchJsonSerializer.EVENTS, new String[]{"_id", "transport_name", "timestamp_ms", "uptime_ms", "payload_encoding", "payload", "code", "inline"}, "context_id = ?", new String[]{transportContextId.toString()}, null, null, null, String.valueOf(i)), new Function() { // from class: com.google.android.datatransport.runtime.scheduling.persistence.SQLiteEventStore$$ExternalSyntheticLambda10
            @Override // com.google.android.datatransport.runtime.scheduling.persistence.SQLiteEventStore.Function
            public final Object apply(Object obj) {
                SQLiteEventStore.$r8$lambda$rhfHXis96N9wATghbYmJ6PQO9G0(this.f$0, arrayList, transportContext, (Cursor) obj);
                return null;
            }
        });
        return arrayList;
    }

    public final Map<Long, Set<Metadata>> loadMetadata(SQLiteDatabase sQLiteDatabase, List<PersistedEvent> list) {
        final HashMap map = new HashMap();
        StringBuilder sb = new StringBuilder("event_id IN (");
        for (int i = 0; i < list.size(); i++) {
            sb.append(list.get(i).getId());
            if (i < list.size() - 1) {
                sb.append(',');
            }
        }
        sb.append(')');
        tryWithCursor(sQLiteDatabase.query("event_metadata", new String[]{"event_id", "name", "value"}, sb.toString(), null, null, null, null), new Function() { // from class: com.google.android.datatransport.runtime.scheduling.persistence.SQLiteEventStore$$ExternalSyntheticLambda18
            @Override // com.google.android.datatransport.runtime.scheduling.persistence.SQLiteEventStore.Function
            public final Object apply(Object obj) {
                SQLiteEventStore.$r8$lambda$mObue6vDcpMgJg6tRUgbT0MgVDo(map, (Cursor) obj);
                return null;
            }
        });
        return map;
    }

    @Override // com.google.android.datatransport.runtime.scheduling.persistence.EventStore
    @Nullable
    public PersistedEvent persist(final TransportContext transportContext, final EventInternal eventInternal) {
        Logging.d(LOG_TAG, "Storing event with priority=%s, name=%s for destination %s", transportContext.getPriority(), eventInternal.getTransportName(), transportContext.getBackendName());
        long jLongValue = ((Long) inTransaction(new Function() { // from class: com.google.android.datatransport.runtime.scheduling.persistence.SQLiteEventStore$$ExternalSyntheticLambda13
            @Override // com.google.android.datatransport.runtime.scheduling.persistence.SQLiteEventStore.Function
            public final Object apply(Object obj) {
                return SQLiteEventStore.m360$r8$lambda$Yygha28Nx4WElzmz7AGC_M3niQ(this.f$0, eventInternal, transportContext, (SQLiteDatabase) obj);
            }
        })).longValue();
        if (jLongValue < 1) {
            return null;
        }
        return new AutoValue_PersistedEvent(jLongValue, transportContext, eventInternal);
    }

    public final void populateLogSourcesMetrics(ClientMetrics.Builder builder, Map<String, List<LogEventDropped>> map) {
        for (Map.Entry<String, List<LogEventDropped>> entry : map.entrySet()) {
            LogSourceMetrics.Builder builderNewBuilder = LogSourceMetrics.newBuilder();
            builderNewBuilder.log_source_ = entry.getKey();
            builderNewBuilder.log_event_dropped_ = entry.getValue();
            builder.addLogSourceMetrics(builderNewBuilder.build());
        }
    }

    public final byte[] readPayload(long j) {
        return (byte[]) tryWithCursor(getDb().query("event_payloads", new String[]{"bytes"}, "event_id = ?", new String[]{String.valueOf(j)}, null, null, "sequence_num"), new SQLiteEventStore$$ExternalSyntheticLambda22());
    }

    @Override // com.google.android.datatransport.runtime.scheduling.persistence.EventStore
    public void recordFailure(Iterable<PersistedEvent> iterable) {
        if (iterable.iterator().hasNext()) {
            final String str = "UPDATE events SET num_attempts = num_attempts + 1 WHERE _id in " + toIdList(iterable);
            final String str2 = "SELECT COUNT(*), transport_name FROM events WHERE num_attempts >= 16 GROUP BY transport_name";
            inTransaction(new Function() { // from class: com.google.android.datatransport.runtime.scheduling.persistence.SQLiteEventStore$$ExternalSyntheticLambda1
                @Override // com.google.android.datatransport.runtime.scheduling.persistence.SQLiteEventStore.Function
                public final Object apply(Object obj) {
                    SQLiteEventStore.$r8$lambda$FWBHKI0M7buhnYqPq5piDyOvkh8(this.f$0, str, "SELECT COUNT(*), transport_name FROM events WHERE num_attempts >= 16 GROUP BY transport_name", (SQLiteDatabase) obj);
                    return null;
                }
            });
        }
    }

    @Override // com.google.android.datatransport.runtime.scheduling.persistence.ClientHealthMetricsStore
    public void recordLogEventDropped(final long j, final LogEventDropped.Reason reason, final String str) {
        inTransaction(new Function() { // from class: com.google.android.datatransport.runtime.scheduling.persistence.SQLiteEventStore$$ExternalSyntheticLambda20
            @Override // com.google.android.datatransport.runtime.scheduling.persistence.SQLiteEventStore.Function
            public final Object apply(Object obj) {
                SQLiteEventStore.m362$r8$lambda$m2CidbNtM3iXXm7FvzU6_pgJWc(str, reason, j, (SQLiteDatabase) obj);
                return null;
            }
        });
    }

    @Override // com.google.android.datatransport.runtime.scheduling.persistence.EventStore
    public void recordNextCallTime(final TransportContext transportContext, final long j) {
        inTransaction(new Function() { // from class: com.google.android.datatransport.runtime.scheduling.persistence.SQLiteEventStore$$ExternalSyntheticLambda16
            @Override // com.google.android.datatransport.runtime.scheduling.persistence.SQLiteEventStore.Function
            public final Object apply(Object obj) {
                SQLiteEventStore.$r8$lambda$GHMqzva9B_XnmPprSGyr5JoZ7b8(j, transportContext, (SQLiteDatabase) obj);
                return null;
            }
        });
    }

    @Override // com.google.android.datatransport.runtime.scheduling.persistence.EventStore
    public void recordSuccess(Iterable<PersistedEvent> iterable) {
        if (iterable.iterator().hasNext()) {
            getDb().compileStatement("DELETE FROM events WHERE _id in " + toIdList(iterable)).execute();
        }
    }

    @Override // com.google.android.datatransport.runtime.scheduling.persistence.ClientHealthMetricsStore
    public void resetClientMetrics() {
        inTransaction(new Function() { // from class: com.google.android.datatransport.runtime.scheduling.persistence.SQLiteEventStore$$ExternalSyntheticLambda17
            @Override // com.google.android.datatransport.runtime.scheduling.persistence.SQLiteEventStore.Function
            public final Object apply(Object obj) {
                SQLiteEventStore.m363$r8$lambda$tjc_qlQgTB9tdANbPFpY4kEz4U(this.f$0, (SQLiteDatabase) obj);
                return null;
            }
        });
    }

    public final <T> T retryIfDbLocked(Producer<T> producer, Function<Throwable, T> function) {
        long time = this.monotonicClock.getTime();
        while (true) {
            try {
                return producer.produce();
            } catch (SQLiteDatabaseLockedException e) {
                if (this.monotonicClock.getTime() >= ((long) this.config.getCriticalSectionEnterTimeoutMs()) + time) {
                    return function.apply(e);
                }
                SystemClock.sleep(50L);
            }
        }
    }

    @Override // com.google.android.datatransport.runtime.synchronization.SynchronizationGuard
    public <T> T runCriticalSection(SynchronizationGuard.CriticalSection<T> criticalSection) {
        SQLiteDatabase db = getDb();
        ensureBeginTransaction(db);
        try {
            T tExecute = criticalSection.execute();
            db.setTransactionSuccessful();
            return tExecute;
        } finally {
            db.endTransaction();
        }
    }
}
