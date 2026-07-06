package androidx.work.impl;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.RestrictTo;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import androidx.sqlite.db.framework.FrameworkSQLiteOpenHelperFactory;
import androidx.work.Data;
import androidx.work.impl.WorkDatabaseMigrations;
import androidx.work.impl.model.Dependency;
import androidx.work.impl.model.DependencyDao;
import androidx.work.impl.model.Preference;
import androidx.work.impl.model.PreferenceDao;
import androidx.work.impl.model.RawWorkInfoDao;
import androidx.work.impl.model.SystemIdInfo;
import androidx.work.impl.model.SystemIdInfoDao;
import androidx.work.impl.model.WorkName;
import androidx.work.impl.model.WorkNameDao;
import androidx.work.impl.model.WorkProgress;
import androidx.work.impl.model.WorkProgressDao;
import androidx.work.impl.model.WorkSpec;
import androidx.work.impl.model.WorkSpecDao;
import androidx.work.impl.model.WorkTag;
import androidx.work.impl.model.WorkTagDao;
import androidx.work.impl.model.WorkTypeConverters;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@TypeConverters({Data.class, WorkTypeConverters.class})
@Database(entities = {Dependency.class, WorkSpec.class, WorkTag.class, SystemIdInfo.class, WorkName.class, WorkProgress.class, Preference.class}, version = 12)
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
public abstract class WorkDatabase extends RoomDatabase {
    public static final String PRUNE_SQL_FORMAT_PREFIX = "DELETE FROM workspec WHERE state IN (2, 3, 5) AND (period_start_time + minimum_retention_duration) < ";
    public static final String PRUNE_SQL_FORMAT_SUFFIX = " AND (SELECT COUNT(*)=0 FROM dependency WHERE     prerequisite_id=id AND     work_spec_id NOT IN         (SELECT id FROM workspec WHERE state IN (2, 3, 5)))";
    public static final long PRUNE_THRESHOLD_MILLIS = TimeUnit.DAYS.toMillis(1);

    /* JADX INFO: renamed from: androidx.work.impl.WorkDatabase$2, reason: invalid class name */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public class AnonymousClass2 extends RoomDatabase.Callback {
        @Override // androidx.room.RoomDatabase.Callback
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            db.beginTransaction();
            try {
                db.execSQL(WorkDatabase.getPruneSQL());
                db.setTransactionSuccessful();
            } finally {
                db.endTransaction();
            }
        }
    }

    @NonNull
    public static WorkDatabase create(@NonNull final Context context, @NonNull Executor queryExecutor, boolean useTestDatabase) {
        RoomDatabase.Builder builderDatabaseBuilder;
        if (useTestDatabase) {
            builderDatabaseBuilder = Room.inMemoryDatabaseBuilder(context, WorkDatabase.class);
            builderDatabaseBuilder.mAllowMainThreadQueries = true;
        } else {
            WorkDatabasePathHelper.getWorkDatabaseName();
            builderDatabaseBuilder = Room.databaseBuilder(context, WorkDatabase.class, WorkDatabasePathHelper.WORK_DATABASE_NAME);
            builderDatabaseBuilder.mFactory = new SupportSQLiteOpenHelper.Factory() { // from class: androidx.work.impl.WorkDatabase.1
                @Override // androidx.sqlite.db.SupportSQLiteOpenHelper.Factory
                @NonNull
                public SupportSQLiteOpenHelper create(@NonNull SupportSQLiteOpenHelper.Configuration configuration) {
                    SupportSQLiteOpenHelper.Configuration.Builder builder = new SupportSQLiteOpenHelper.Configuration.Builder(context);
                    builder.mName = configuration.name;
                    builder.mCallback = configuration.callback;
                    builder.mUseNoBackUpDirectory = true;
                    return new FrameworkSQLiteOpenHelperFactory().create(builder.build());
                }
            };
        }
        return (WorkDatabase) builderDatabaseBuilder.setQueryExecutor(queryExecutor).addCallback(new AnonymousClass2()).addMigrations(WorkDatabaseMigrations.MIGRATION_1_2).addMigrations(new WorkDatabaseMigrations.RescheduleMigration(context, 2, 3)).addMigrations(WorkDatabaseMigrations.MIGRATION_3_4).addMigrations(WorkDatabaseMigrations.MIGRATION_4_5).addMigrations(new WorkDatabaseMigrations.RescheduleMigration(context, 5, 6)).addMigrations(WorkDatabaseMigrations.MIGRATION_6_7).addMigrations(WorkDatabaseMigrations.MIGRATION_7_8).addMigrations(WorkDatabaseMigrations.MIGRATION_8_9).addMigrations(new WorkDatabaseMigrations.WorkMigration9To10(context)).addMigrations(new WorkDatabaseMigrations.RescheduleMigration(context, 10, 11)).addMigrations(WorkDatabaseMigrations.MIGRATION_11_12).fallbackToDestructiveMigration().build();
    }

    public static RoomDatabase.Callback generateCleanupCallback() {
        return new AnonymousClass2();
    }

    public static long getPruneDate() {
        return System.currentTimeMillis() - PRUNE_THRESHOLD_MILLIS;
    }

    @NonNull
    public static String getPruneSQL() {
        return PRUNE_SQL_FORMAT_PREFIX + getPruneDate() + PRUNE_SQL_FORMAT_SUFFIX;
    }

    @NonNull
    public abstract DependencyDao dependencyDao();

    @NonNull
    public abstract PreferenceDao preferenceDao();

    @NonNull
    public abstract RawWorkInfoDao rawWorkInfoDao();

    @NonNull
    public abstract SystemIdInfoDao systemIdInfoDao();

    @NonNull
    public abstract WorkNameDao workNameDao();

    @NonNull
    public abstract WorkProgressDao workProgressDao();

    @NonNull
    public abstract WorkSpecDao workSpecDao();

    @NonNull
    public abstract WorkTagDao workTagDao();
}
