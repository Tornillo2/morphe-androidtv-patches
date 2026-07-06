package androidx.work.impl.utils;

import androidx.annotation.NonNull;
import androidx.sqlite.db.SimpleSQLiteQuery;
import androidx.sqlite.db.SupportSQLiteQuery;
import androidx.work.WorkInfo;
import androidx.work.WorkQuery;
import androidx.work.impl.model.WorkTypeConverters;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public final class RawQueries {
    public static void bindings(@NonNull StringBuilder builder, int count) {
        if (count <= 0) {
            return;
        }
        builder.append("?");
        for (int i = 1; i < count; i++) {
            builder.append(",");
            builder.append("?");
        }
    }

    @NonNull
    public static SupportSQLiteQuery workQueryToRawQuery(@NonNull WorkQuery querySpec) {
        String str;
        ArrayList arrayList = new ArrayList();
        StringBuilder sb = new StringBuilder("SELECT * FROM workspec");
        List<WorkInfo.State> list = querySpec.mStates;
        String str2 = " AND";
        if (list.isEmpty()) {
            str = " WHERE";
        } else {
            ArrayList arrayList2 = new ArrayList(list.size());
            Iterator<WorkInfo.State> it = list.iterator();
            while (it.hasNext()) {
                arrayList2.add(Integer.valueOf(WorkTypeConverters.stateToInt(it.next())));
            }
            sb.append(" WHERE state IN (");
            bindings(sb, arrayList2.size());
            sb.append(")");
            arrayList.addAll(arrayList2);
            str = " AND";
        }
        List<UUID> list2 = querySpec.mIds;
        if (!list2.isEmpty()) {
            ArrayList arrayList3 = new ArrayList(list2.size());
            Iterator<UUID> it2 = list2.iterator();
            while (it2.hasNext()) {
                arrayList3.add(it2.next().toString());
            }
            sb.append(str);
            sb.append(" id IN (");
            bindings(sb, list2.size());
            sb.append(")");
            arrayList.addAll(arrayList3);
            str = " AND";
        }
        List<String> list3 = querySpec.mTags;
        if (list3.isEmpty()) {
            str2 = str;
        } else {
            sb.append(str);
            sb.append(" id IN (SELECT work_spec_id FROM worktag WHERE tag IN (");
            bindings(sb, list3.size());
            sb.append("))");
            arrayList.addAll(list3);
        }
        List<String> list4 = querySpec.mUniqueWorkNames;
        if (!list4.isEmpty()) {
            sb.append(str2);
            sb.append(" id IN (SELECT work_spec_id FROM workname WHERE name IN (");
            bindings(sb, list4.size());
            sb.append("))");
            arrayList.addAll(list4);
        }
        sb.append(";");
        return new SimpleSQLiteQuery(sb.toString(), arrayList.toArray());
    }
}
