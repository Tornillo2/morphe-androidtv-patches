package androidx.work.impl.model;

import android.database.Cursor;
import android.os.CancellationSignal;
import androidx.collection.ArrayMap;
import androidx.core.accessibilityservice.AccessibilityServiceInfoCompat$$ExternalSyntheticOutline0;
import androidx.lifecycle.LiveData;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.room.util.StringUtil;
import androidx.sqlite.db.SupportSQLiteQuery;
import androidx.work.Data;
import androidx.work.impl.model.WorkSpec;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public final class RawWorkInfoDao_Impl implements RawWorkInfoDao {
    public final RoomDatabase __db;

    public RawWorkInfoDao_Impl(RoomDatabase __db) {
        this.__db = __db;
    }

    public final void __fetchRelationshipWorkProgressAsandroidxWorkData(final ArrayMap<String, ArrayList<Data>> _map) {
        ArrayList<Data> arrayList;
        int i;
        ArrayMap.KeySet<String> keySet = (ArrayMap.KeySet) _map.keySet();
        if (ArrayMap.this.isEmpty()) {
            return;
        }
        if (_map.size() > 999) {
            ArrayMap<String, ArrayList<Data>> arrayMap = new ArrayMap<>(RoomDatabase.MAX_BIND_PARAMETER_CNT);
            int size = _map.size();
            int i2 = 0;
            loop0: while (true) {
                i = 0;
                while (i2 < size) {
                    arrayMap.put(_map.keyAt(i2), _map.valueAt(i2));
                    i2++;
                    i++;
                    if (i == 999) {
                        break;
                    }
                }
                __fetchRelationshipWorkProgressAsandroidxWorkData(arrayMap);
                arrayMap = new ArrayMap<>(RoomDatabase.MAX_BIND_PARAMETER_CNT);
            }
            if (i > 0) {
                __fetchRelationshipWorkProgressAsandroidxWorkData(arrayMap);
                return;
            }
            return;
        }
        StringBuilder sbM = AccessibilityServiceInfoCompat$$ExternalSyntheticOutline0.m("SELECT `progress`,`work_spec_id` FROM `WorkProgress` WHERE `work_spec_id` IN (");
        int size2 = ArrayMap.this.size();
        StringUtil.appendPlaceholders(sbM, size2);
        sbM.append(")");
        RoomSQLiteQuery roomSQLiteQueryAcquire = RoomSQLiteQuery.acquire(sbM.toString(), size2);
        int i3 = 1;
        for (String str : keySet) {
            if (str == null) {
                roomSQLiteQueryAcquire.bindNull(i3);
            } else {
                roomSQLiteQueryAcquire.bindString(i3, str);
            }
            i3++;
        }
        Cursor cursorQuery = this.__db.query(roomSQLiteQueryAcquire, (CancellationSignal) null);
        try {
            int columnIndex = CursorUtil.getColumnIndex(cursorQuery, "work_spec_id");
            if (columnIndex == -1) {
                return;
            }
            while (cursorQuery.moveToNext()) {
                if (!cursorQuery.isNull(columnIndex) && (arrayList = _map.get(cursorQuery.getString(columnIndex))) != null) {
                    arrayList.add(Data.fromByteArray(cursorQuery.getBlob(0)));
                }
            }
        } finally {
            cursorQuery.close();
        }
    }

    public final void __fetchRelationshipWorkTagAsjavaLangString(final ArrayMap<String, ArrayList<String>> _map) {
        ArrayList<String> arrayList;
        int i;
        ArrayMap.KeySet<String> keySet = (ArrayMap.KeySet) _map.keySet();
        if (ArrayMap.this.isEmpty()) {
            return;
        }
        if (_map.size() > 999) {
            ArrayMap<String, ArrayList<String>> arrayMap = new ArrayMap<>(RoomDatabase.MAX_BIND_PARAMETER_CNT);
            int size = _map.size();
            int i2 = 0;
            loop0: while (true) {
                i = 0;
                while (i2 < size) {
                    arrayMap.put(_map.keyAt(i2), _map.valueAt(i2));
                    i2++;
                    i++;
                    if (i == 999) {
                        break;
                    }
                }
                __fetchRelationshipWorkTagAsjavaLangString(arrayMap);
                arrayMap = new ArrayMap<>(RoomDatabase.MAX_BIND_PARAMETER_CNT);
            }
            if (i > 0) {
                __fetchRelationshipWorkTagAsjavaLangString(arrayMap);
                return;
            }
            return;
        }
        StringBuilder sbM = AccessibilityServiceInfoCompat$$ExternalSyntheticOutline0.m("SELECT `tag`,`work_spec_id` FROM `WorkTag` WHERE `work_spec_id` IN (");
        int size2 = ArrayMap.this.size();
        StringUtil.appendPlaceholders(sbM, size2);
        sbM.append(")");
        RoomSQLiteQuery roomSQLiteQueryAcquire = RoomSQLiteQuery.acquire(sbM.toString(), size2);
        int i3 = 1;
        for (String str : keySet) {
            if (str == null) {
                roomSQLiteQueryAcquire.bindNull(i3);
            } else {
                roomSQLiteQueryAcquire.bindString(i3, str);
            }
            i3++;
        }
        Cursor cursorQuery = this.__db.query(roomSQLiteQueryAcquire, (CancellationSignal) null);
        try {
            int columnIndex = CursorUtil.getColumnIndex(cursorQuery, "work_spec_id");
            if (columnIndex == -1) {
                return;
            }
            while (cursorQuery.moveToNext()) {
                if (!cursorQuery.isNull(columnIndex) && (arrayList = _map.get(cursorQuery.getString(columnIndex))) != null) {
                    arrayList.add(cursorQuery.getString(0));
                }
            }
        } finally {
            cursorQuery.close();
        }
    }

    @Override // androidx.work.impl.model.RawWorkInfoDao
    public List<WorkSpec.WorkInfoPojo> getWorkInfoPojos(final SupportSQLiteQuery query) {
        this.__db.assertNotSuspendingTransaction();
        Cursor cursorQuery = DBUtil.query(this.__db, query, true, null);
        try {
            int columnIndex = CursorUtil.getColumnIndex(cursorQuery, "id");
            int columnIndex2 = CursorUtil.getColumnIndex(cursorQuery, "state");
            int columnIndex3 = CursorUtil.getColumnIndex(cursorQuery, "output");
            int columnIndex4 = CursorUtil.getColumnIndex(cursorQuery, "run_attempt_count");
            ArrayMap<String, ArrayList<String>> arrayMap = new ArrayMap<>();
            ArrayMap<String, ArrayList<Data>> arrayMap2 = new ArrayMap<>();
            while (cursorQuery.moveToNext()) {
                if (!cursorQuery.isNull(columnIndex)) {
                    String string = cursorQuery.getString(columnIndex);
                    if (arrayMap.get(string) == null) {
                        arrayMap.put(string, new ArrayList<>());
                    }
                }
                if (!cursorQuery.isNull(columnIndex)) {
                    String string2 = cursorQuery.getString(columnIndex);
                    if (arrayMap2.get(string2) == null) {
                        arrayMap2.put(string2, new ArrayList<>());
                    }
                }
            }
            cursorQuery.moveToPosition(-1);
            __fetchRelationshipWorkTagAsjavaLangString(arrayMap);
            __fetchRelationshipWorkProgressAsandroidxWorkData(arrayMap2);
            ArrayList arrayList = new ArrayList(cursorQuery.getCount());
            while (cursorQuery.moveToNext()) {
                ArrayList<String> arrayList2 = !cursorQuery.isNull(columnIndex) ? arrayMap.get(cursorQuery.getString(columnIndex)) : null;
                if (arrayList2 == null) {
                    arrayList2 = new ArrayList<>();
                }
                ArrayList<Data> arrayList3 = !cursorQuery.isNull(columnIndex) ? arrayMap2.get(cursorQuery.getString(columnIndex)) : null;
                if (arrayList3 == null) {
                    arrayList3 = new ArrayList<>();
                }
                WorkSpec.WorkInfoPojo workInfoPojo = new WorkSpec.WorkInfoPojo();
                if (columnIndex != -1) {
                    workInfoPojo.id = cursorQuery.getString(columnIndex);
                }
                if (columnIndex2 != -1) {
                    workInfoPojo.state = WorkTypeConverters.intToState(cursorQuery.getInt(columnIndex2));
                }
                if (columnIndex3 != -1) {
                    workInfoPojo.output = Data.fromByteArray(cursorQuery.getBlob(columnIndex3));
                }
                if (columnIndex4 != -1) {
                    workInfoPojo.runAttemptCount = cursorQuery.getInt(columnIndex4);
                }
                workInfoPojo.tags = arrayList2;
                workInfoPojo.progress = arrayList3;
                arrayList.add(workInfoPojo);
            }
            cursorQuery.close();
            return arrayList;
        } catch (Throwable th) {
            cursorQuery.close();
            throw th;
        }
    }

    @Override // androidx.work.impl.model.RawWorkInfoDao
    public LiveData<List<WorkSpec.WorkInfoPojo>> getWorkInfoPojosLiveData(final SupportSQLiteQuery query) {
        return this.__db.getInvalidationTracker().createLiveData(new String[]{"WorkTag", "WorkProgress", "WorkSpec"}, false, new Callable<List<WorkSpec.WorkInfoPojo>>() { // from class: androidx.work.impl.model.RawWorkInfoDao_Impl.1
            @Override // java.util.concurrent.Callable
            public List<WorkSpec.WorkInfoPojo> call() throws Exception {
                Cursor cursorQuery = DBUtil.query(RawWorkInfoDao_Impl.this.__db, query, true, null);
                try {
                    int columnIndex = CursorUtil.getColumnIndex(cursorQuery, "id");
                    int columnIndex2 = CursorUtil.getColumnIndex(cursorQuery, "state");
                    int columnIndex3 = CursorUtil.getColumnIndex(cursorQuery, "output");
                    int columnIndex4 = CursorUtil.getColumnIndex(cursorQuery, "run_attempt_count");
                    ArrayMap<String, ArrayList<String>> arrayMap = new ArrayMap<>();
                    ArrayMap<String, ArrayList<Data>> arrayMap2 = new ArrayMap<>();
                    while (cursorQuery.moveToNext()) {
                        if (!cursorQuery.isNull(columnIndex)) {
                            String string = cursorQuery.getString(columnIndex);
                            if (arrayMap.get(string) == null) {
                                arrayMap.put(string, new ArrayList<>());
                            }
                        }
                        if (!cursorQuery.isNull(columnIndex)) {
                            String string2 = cursorQuery.getString(columnIndex);
                            if (arrayMap2.get(string2) == null) {
                                arrayMap2.put(string2, new ArrayList<>());
                            }
                        }
                    }
                    cursorQuery.moveToPosition(-1);
                    RawWorkInfoDao_Impl.this.__fetchRelationshipWorkTagAsjavaLangString(arrayMap);
                    RawWorkInfoDao_Impl.this.__fetchRelationshipWorkProgressAsandroidxWorkData(arrayMap2);
                    ArrayList arrayList = new ArrayList(cursorQuery.getCount());
                    while (cursorQuery.moveToNext()) {
                        ArrayList<String> arrayList2 = !cursorQuery.isNull(columnIndex) ? arrayMap.get(cursorQuery.getString(columnIndex)) : null;
                        if (arrayList2 == null) {
                            arrayList2 = new ArrayList<>();
                        }
                        ArrayList<Data> arrayList3 = !cursorQuery.isNull(columnIndex) ? arrayMap2.get(cursorQuery.getString(columnIndex)) : null;
                        if (arrayList3 == null) {
                            arrayList3 = new ArrayList<>();
                        }
                        WorkSpec.WorkInfoPojo workInfoPojo = new WorkSpec.WorkInfoPojo();
                        if (columnIndex != -1) {
                            workInfoPojo.id = cursorQuery.getString(columnIndex);
                        }
                        if (columnIndex2 != -1) {
                            workInfoPojo.state = WorkTypeConverters.intToState(cursorQuery.getInt(columnIndex2));
                        }
                        if (columnIndex3 != -1) {
                            workInfoPojo.output = Data.fromByteArray(cursorQuery.getBlob(columnIndex3));
                        }
                        if (columnIndex4 != -1) {
                            workInfoPojo.runAttemptCount = cursorQuery.getInt(columnIndex4);
                        }
                        workInfoPojo.tags = arrayList2;
                        workInfoPojo.progress = arrayList3;
                        arrayList.add(workInfoPojo);
                    }
                    cursorQuery.close();
                    return arrayList;
                } catch (Throwable th) {
                    cursorQuery.close();
                    throw th;
                }
            }
        });
    }
}
