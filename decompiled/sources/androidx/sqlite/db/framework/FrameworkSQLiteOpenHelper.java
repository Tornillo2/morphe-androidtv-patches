package androidx.sqlite.db.framework;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import androidx.annotation.RequiresApi;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import java.io.File;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public class FrameworkSQLiteOpenHelper implements SupportSQLiteOpenHelper {
    public final SupportSQLiteOpenHelper.Callback mCallback;
    public final Context mContext;
    public OpenHelper mDelegate;
    public final Object mLock;
    public final String mName;
    public final boolean mUseNoBackupDirectory;
    public boolean mWriteAheadLoggingEnabled;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static class OpenHelper extends SQLiteOpenHelper {
        public final SupportSQLiteOpenHelper.Callback mCallback;
        public final FrameworkSQLiteDatabase[] mDbRef;
        public boolean mMigrated;

        public OpenHelper(Context context, String str, final FrameworkSQLiteDatabase[] frameworkSQLiteDatabaseArr, final SupportSQLiteOpenHelper.Callback callback) {
            super(context, str, null, callback.version, new DatabaseErrorHandler() { // from class: androidx.sqlite.db.framework.FrameworkSQLiteOpenHelper.OpenHelper.1
                @Override // android.database.DatabaseErrorHandler
                public void onCorruption(SQLiteDatabase sQLiteDatabase) {
                    callback.onCorruption(OpenHelper.getWrappedDb(frameworkSQLiteDatabaseArr, sQLiteDatabase));
                }
            });
            this.mCallback = callback;
            this.mDbRef = frameworkSQLiteDatabaseArr;
        }

        @Override // android.database.sqlite.SQLiteOpenHelper, java.lang.AutoCloseable
        public synchronized void close() {
            super.close();
            this.mDbRef[0] = null;
        }

        public synchronized SupportSQLiteDatabase getReadableSupportDatabase() {
            this.mMigrated = false;
            SQLiteDatabase readableDatabase = super.getReadableDatabase();
            if (!this.mMigrated) {
                return getWrappedDb(readableDatabase);
            }
            close();
            return getReadableSupportDatabase();
        }

        public FrameworkSQLiteDatabase getWrappedDb(SQLiteDatabase sQLiteDatabase) {
            return getWrappedDb(this.mDbRef, sQLiteDatabase);
        }

        public synchronized SupportSQLiteDatabase getWritableSupportDatabase() {
            this.mMigrated = false;
            SQLiteDatabase writableDatabase = super.getWritableDatabase();
            if (!this.mMigrated) {
                return getWrappedDb(writableDatabase);
            }
            close();
            return getWritableSupportDatabase();
        }

        @Override // android.database.sqlite.SQLiteOpenHelper
        public void onConfigure(SQLiteDatabase sQLiteDatabase) {
            SupportSQLiteOpenHelper.Callback callback = this.mCallback;
            getWrappedDb(sQLiteDatabase);
            callback.getClass();
        }

        @Override // android.database.sqlite.SQLiteOpenHelper
        public void onCreate(SQLiteDatabase sQLiteDatabase) {
            this.mCallback.onCreate(getWrappedDb(sQLiteDatabase));
        }

        @Override // android.database.sqlite.SQLiteOpenHelper
        public void onDowngrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
            this.mMigrated = true;
            this.mCallback.onDowngrade(getWrappedDb(sQLiteDatabase), i, i2);
        }

        @Override // android.database.sqlite.SQLiteOpenHelper
        public void onOpen(SQLiteDatabase sQLiteDatabase) {
            if (this.mMigrated) {
                return;
            }
            this.mCallback.onOpen(getWrappedDb(sQLiteDatabase));
        }

        @Override // android.database.sqlite.SQLiteOpenHelper
        public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
            this.mMigrated = true;
            this.mCallback.onUpgrade(getWrappedDb(sQLiteDatabase), i, i2);
        }

        public static FrameworkSQLiteDatabase getWrappedDb(FrameworkSQLiteDatabase[] frameworkSQLiteDatabaseArr, SQLiteDatabase sQLiteDatabase) {
            FrameworkSQLiteDatabase frameworkSQLiteDatabase = frameworkSQLiteDatabaseArr[0];
            if (frameworkSQLiteDatabase == null || !frameworkSQLiteDatabase.isDelegate(sQLiteDatabase)) {
                frameworkSQLiteDatabaseArr[0] = new FrameworkSQLiteDatabase(sQLiteDatabase);
            }
            return frameworkSQLiteDatabaseArr[0];
        }
    }

    public FrameworkSQLiteOpenHelper(Context context, String str, SupportSQLiteOpenHelper.Callback callback) {
        this(context, str, callback, false);
    }

    @Override // androidx.sqlite.db.SupportSQLiteOpenHelper, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        getDelegate().close();
    }

    @Override // androidx.sqlite.db.SupportSQLiteOpenHelper
    public String getDatabaseName() {
        return this.mName;
    }

    public final OpenHelper getDelegate() {
        OpenHelper openHelper;
        synchronized (this.mLock) {
            try {
                if (this.mDelegate == null) {
                    FrameworkSQLiteDatabase[] frameworkSQLiteDatabaseArr = new FrameworkSQLiteDatabase[1];
                    if (Build.VERSION.SDK_INT < 23 || this.mName == null || !this.mUseNoBackupDirectory) {
                        this.mDelegate = new OpenHelper(this.mContext, this.mName, frameworkSQLiteDatabaseArr, this.mCallback);
                    } else {
                        this.mDelegate = new OpenHelper(this.mContext, new File(this.mContext.getNoBackupFilesDir(), this.mName).getAbsolutePath(), frameworkSQLiteDatabaseArr, this.mCallback);
                    }
                    this.mDelegate.setWriteAheadLoggingEnabled(this.mWriteAheadLoggingEnabled);
                }
                openHelper = this.mDelegate;
            } catch (Throwable th) {
                throw th;
            }
        }
        return openHelper;
    }

    @Override // androidx.sqlite.db.SupportSQLiteOpenHelper
    public SupportSQLiteDatabase getReadableDatabase() {
        return getDelegate().getReadableSupportDatabase();
    }

    @Override // androidx.sqlite.db.SupportSQLiteOpenHelper
    public SupportSQLiteDatabase getWritableDatabase() {
        return getDelegate().getWritableSupportDatabase();
    }

    @Override // androidx.sqlite.db.SupportSQLiteOpenHelper
    @RequiresApi(api = 16)
    public void setWriteAheadLoggingEnabled(boolean z) {
        synchronized (this.mLock) {
            try {
                OpenHelper openHelper = this.mDelegate;
                if (openHelper != null) {
                    openHelper.setWriteAheadLoggingEnabled(z);
                }
                this.mWriteAheadLoggingEnabled = z;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public FrameworkSQLiteOpenHelper(Context context, String str, SupportSQLiteOpenHelper.Callback callback, boolean z) {
        this.mContext = context;
        this.mName = str;
        this.mCallback = callback;
        this.mUseNoBackupDirectory = z;
        this.mLock = new Object();
    }
}
