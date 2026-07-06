package androidx.work.impl;

import android.content.Context;
import android.os.Build;
import androidx.annotation.NonNull;
import androidx.annotation.RestrictTo;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.work.impl.utils.IdGenerator;
import androidx.work.impl.utils.PreferenceUtils;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
public class WorkDatabaseMigrations {
    public static final String CREATE_INDEX_PERIOD_START_TIME = "CREATE INDEX IF NOT EXISTS `index_WorkSpec_period_start_time` ON `workspec` (`period_start_time`)";
    public static final String CREATE_OUT_OF_QUOTA_POLICY = "ALTER TABLE workspec ADD COLUMN `out_of_quota_policy` INTEGER NOT NULL DEFAULT 0";
    public static final String CREATE_PREFERENCE = "CREATE TABLE IF NOT EXISTS `Preference` (`key` TEXT NOT NULL, `long_value` INTEGER, PRIMARY KEY(`key`))";
    public static final String CREATE_RUN_IN_FOREGROUND = "ALTER TABLE workspec ADD COLUMN `run_in_foreground` INTEGER NOT NULL DEFAULT 0";
    public static final String CREATE_SYSTEM_ID_INFO = "CREATE TABLE IF NOT EXISTS `SystemIdInfo` (`work_spec_id` TEXT NOT NULL, `system_id` INTEGER NOT NULL, PRIMARY KEY(`work_spec_id`), FOREIGN KEY(`work_spec_id`) REFERENCES `WorkSpec`(`id`) ON UPDATE CASCADE ON DELETE CASCADE )";
    public static final String CREATE_WORK_PROGRESS = "CREATE TABLE IF NOT EXISTS `WorkProgress` (`work_spec_id` TEXT NOT NULL, `progress` BLOB NOT NULL, PRIMARY KEY(`work_spec_id`), FOREIGN KEY(`work_spec_id`) REFERENCES `WorkSpec`(`id`) ON UPDATE CASCADE ON DELETE CASCADE )";
    public static final String INSERT_PREFERENCE = "INSERT OR REPLACE INTO `Preference` (`key`, `long_value`) VALUES (@key, @long_value)";
    public static final String MIGRATE_ALARM_INFO_TO_SYSTEM_ID_INFO = "INSERT INTO SystemIdInfo(work_spec_id, system_id) SELECT work_spec_id, alarm_id AS system_id FROM alarmInfo";
    public static final String PERIODIC_WORK_SET_SCHEDULE_REQUESTED_AT = "UPDATE workspec SET schedule_requested_at=0 WHERE state NOT IN (2, 3, 5) AND schedule_requested_at=-1 AND interval_duration<>0";
    public static final String REMOVE_ALARM_INFO = "DROP TABLE IF EXISTS alarmInfo";
    public static final int VERSION_1 = 1;
    public static final int VERSION_10 = 10;
    public static final int VERSION_11 = 11;
    public static final int VERSION_12 = 12;
    public static final int VERSION_2 = 2;
    public static final int VERSION_3 = 3;
    public static final int VERSION_4 = 4;
    public static final int VERSION_5 = 5;
    public static final int VERSION_6 = 6;
    public static final int VERSION_7 = 7;
    public static final int VERSION_8 = 8;
    public static final int VERSION_9 = 9;
    public static final String WORKSPEC_ADD_TRIGGER_MAX_CONTENT_DELAY = "ALTER TABLE workspec ADD COLUMN `trigger_max_content_delay` INTEGER NOT NULL DEFAULT -1";
    public static final String WORKSPEC_ADD_TRIGGER_UPDATE_DELAY = "ALTER TABLE workspec ADD COLUMN `trigger_content_update_delay` INTEGER NOT NULL DEFAULT -1";

    @NonNull
    public static Migration MIGRATION_1_2 = new AnonymousClass1(1, 2);

    @NonNull
    public static Migration MIGRATION_3_4 = new AnonymousClass2(3, 4);

    @NonNull
    public static Migration MIGRATION_4_5 = new AnonymousClass3(4, 5);

    @NonNull
    public static Migration MIGRATION_6_7 = new AnonymousClass4(6, 7);

    @NonNull
    public static Migration MIGRATION_7_8 = new AnonymousClass5(7, 8);

    @NonNull
    public static Migration MIGRATION_8_9 = new AnonymousClass6(8, 9);

    @NonNull
    public static Migration MIGRATION_11_12 = new AnonymousClass7(11, 12);

    /* JADX INFO: renamed from: androidx.work.impl.WorkDatabaseMigrations$1, reason: invalid class name */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public class AnonymousClass1 extends Migration {
        public AnonymousClass1(int startVersion, int endVersion) {
            super(startVersion, endVersion);
        }

        @Override // androidx.room.migration.Migration
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL(WorkDatabaseMigrations.CREATE_SYSTEM_ID_INFO);
            database.execSQL(WorkDatabaseMigrations.MIGRATE_ALARM_INFO_TO_SYSTEM_ID_INFO);
            database.execSQL(WorkDatabaseMigrations.REMOVE_ALARM_INFO);
            database.execSQL("INSERT OR IGNORE INTO worktag(tag, work_spec_id) SELECT worker_class_name AS tag, id AS work_spec_id FROM workspec");
        }
    }

    /* JADX INFO: renamed from: androidx.work.impl.WorkDatabaseMigrations$2, reason: invalid class name */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public class AnonymousClass2 extends Migration {
        public AnonymousClass2(int startVersion, int endVersion) {
            super(startVersion, endVersion);
        }

        @Override // androidx.room.migration.Migration
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            if (Build.VERSION.SDK_INT >= 23) {
                database.execSQL(WorkDatabaseMigrations.PERIODIC_WORK_SET_SCHEDULE_REQUESTED_AT);
            }
        }
    }

    /* JADX INFO: renamed from: androidx.work.impl.WorkDatabaseMigrations$3, reason: invalid class name */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public class AnonymousClass3 extends Migration {
        public AnonymousClass3(int startVersion, int endVersion) {
            super(startVersion, endVersion);
        }

        @Override // androidx.room.migration.Migration
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL(WorkDatabaseMigrations.WORKSPEC_ADD_TRIGGER_UPDATE_DELAY);
            database.execSQL(WorkDatabaseMigrations.WORKSPEC_ADD_TRIGGER_MAX_CONTENT_DELAY);
        }
    }

    /* JADX INFO: renamed from: androidx.work.impl.WorkDatabaseMigrations$4, reason: invalid class name */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public class AnonymousClass4 extends Migration {
        public AnonymousClass4(int startVersion, int endVersion) {
            super(startVersion, endVersion);
        }

        @Override // androidx.room.migration.Migration
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL(WorkDatabaseMigrations.CREATE_WORK_PROGRESS);
        }
    }

    /* JADX INFO: renamed from: androidx.work.impl.WorkDatabaseMigrations$5, reason: invalid class name */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public class AnonymousClass5 extends Migration {
        public AnonymousClass5(int startVersion, int endVersion) {
            super(startVersion, endVersion);
        }

        @Override // androidx.room.migration.Migration
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL(WorkDatabaseMigrations.CREATE_INDEX_PERIOD_START_TIME);
        }
    }

    /* JADX INFO: renamed from: androidx.work.impl.WorkDatabaseMigrations$6, reason: invalid class name */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public class AnonymousClass6 extends Migration {
        public AnonymousClass6(int startVersion, int endVersion) {
            super(startVersion, endVersion);
        }

        @Override // androidx.room.migration.Migration
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL(WorkDatabaseMigrations.CREATE_RUN_IN_FOREGROUND);
        }
    }

    /* JADX INFO: renamed from: androidx.work.impl.WorkDatabaseMigrations$7, reason: invalid class name */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public class AnonymousClass7 extends Migration {
        public AnonymousClass7(int startVersion, int endVersion) {
            super(startVersion, endVersion);
        }

        @Override // androidx.room.migration.Migration
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL(WorkDatabaseMigrations.CREATE_OUT_OF_QUOTA_POLICY);
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static class RescheduleMigration extends Migration {
        public final Context mContext;

        public RescheduleMigration(@NonNull Context context, int startVersion, int endVersion) {
            super(startVersion, endVersion);
            this.mContext = context;
        }

        @Override // androidx.room.migration.Migration
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            if (this.endVersion >= 10) {
                database.execSQL(WorkDatabaseMigrations.INSERT_PREFERENCE, new Object[]{PreferenceUtils.KEY_RESCHEDULE_NEEDED, 1});
            } else {
                this.mContext.getSharedPreferences(PreferenceUtils.PREFERENCES_FILE_NAME, 0).edit().putBoolean(PreferenceUtils.KEY_RESCHEDULE_NEEDED, true).apply();
            }
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static class WorkMigration9To10 extends Migration {
        public final Context mContext;

        public WorkMigration9To10(@NonNull Context context) {
            super(9, 10);
            this.mContext = context;
        }

        @Override // androidx.room.migration.Migration
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL(WorkDatabaseMigrations.CREATE_PREFERENCE);
            PreferenceUtils.migrateLegacyPreferences(this.mContext, database);
            IdGenerator.migrateLegacyIdGenerator(this.mContext, database);
        }
    }
}
