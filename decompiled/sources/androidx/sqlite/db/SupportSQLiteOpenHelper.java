package androidx.sqlite.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.text.TextUtils;
import android.util.Log;
import android.util.Pair;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.emoji2.text.flatbuffer.Utf8$UnpairedSurrogateException$$ExternalSyntheticOutline0;
import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public interface SupportSQLiteOpenHelper extends Closeable {

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static class Configuration {

        @NonNull
        public final Callback callback;

        @NonNull
        public final Context context;

        @Nullable
        public final String name;
        public final boolean useNoBackupDirectory;

        /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
        public static class Builder {
            public Callback mCallback;
            public Context mContext;
            public String mName;
            public boolean mUseNoBackUpDirectory;

            public Builder(@NonNull Context context) {
                this.mContext = context;
            }

            @NonNull
            public Configuration build() {
                if (this.mCallback == null) {
                    throw new IllegalArgumentException("Must set a callback to create the configuration.");
                }
                if (this.mContext == null) {
                    throw new IllegalArgumentException("Must set a non-null context to create the configuration.");
                }
                if (this.mUseNoBackUpDirectory && TextUtils.isEmpty(this.mName)) {
                    throw new IllegalArgumentException("Must set a non-null database name to a configuration that uses the no backup directory.");
                }
                return new Configuration(this.mContext, this.mName, this.mCallback, this.mUseNoBackUpDirectory);
            }

            @NonNull
            public Builder callback(@NonNull Callback callback) {
                this.mCallback = callback;
                return this;
            }

            @NonNull
            public Builder name(@Nullable String str) {
                this.mName = str;
                return this;
            }

            @NonNull
            public Builder noBackupDirectory(boolean z) {
                this.mUseNoBackUpDirectory = z;
                return this;
            }
        }

        public Configuration(@NonNull Context context, @Nullable String str, @NonNull Callback callback) {
            this(context, str, callback, false);
        }

        @NonNull
        public static Builder builder(@NonNull Context context) {
            return new Builder(context);
        }

        public Configuration(@NonNull Context context, @Nullable String str, @NonNull Callback callback, boolean z) {
            this.context = context;
            this.name = str;
            this.callback = callback;
            this.useNoBackupDirectory = z;
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public interface Factory {
        @NonNull
        SupportSQLiteOpenHelper create(@NonNull Configuration configuration);
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    void close();

    @Nullable
    String getDatabaseName();

    SupportSQLiteDatabase getReadableDatabase();

    SupportSQLiteDatabase getWritableDatabase();

    @RequiresApi(api = 16)
    void setWriteAheadLoggingEnabled(boolean z);

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static abstract class Callback {
        public static final String TAG = "SupportSQLite";
        public final int version;

        public Callback(int i) {
            this.version = i;
        }

        public final void deleteDatabaseFile(String str) {
            if (str.equalsIgnoreCase(":memory:") || str.trim().length() == 0) {
                return;
            }
            Log.w(TAG, "deleting the database file: ".concat(str));
            try {
                SQLiteDatabase.deleteDatabase(new File(str));
            } catch (Exception e) {
                Log.w(TAG, "delete failed: ", e);
            }
        }

        public void onCorruption(@NonNull SupportSQLiteDatabase supportSQLiteDatabase) {
            Log.e(TAG, "Corruption reported by sqlite on database: " + supportSQLiteDatabase.getPath());
            if (!supportSQLiteDatabase.isOpen()) {
                deleteDatabaseFile(supportSQLiteDatabase.getPath());
                return;
            }
            List<Pair<String, String>> attachedDbs = null;
            try {
                try {
                    attachedDbs = supportSQLiteDatabase.getAttachedDbs();
                } finally {
                    if (attachedDbs != null) {
                        Iterator<Pair<String, String>> it = attachedDbs.iterator();
                        while (it.hasNext()) {
                            deleteDatabaseFile((String) it.next().second);
                        }
                    } else {
                        deleteDatabaseFile(supportSQLiteDatabase.getPath());
                    }
                }
            } catch (SQLiteException unused) {
            }
            try {
                supportSQLiteDatabase.close();
            } catch (IOException unused2) {
            }
        }

        public abstract void onCreate(@NonNull SupportSQLiteDatabase supportSQLiteDatabase);

        public void onDowngrade(@NonNull SupportSQLiteDatabase supportSQLiteDatabase, int i, int i2) {
            throw new SQLiteException(Utf8$UnpairedSurrogateException$$ExternalSyntheticOutline0.m("Can't downgrade database from version ", i, " to ", i2));
        }

        public abstract void onUpgrade(@NonNull SupportSQLiteDatabase supportSQLiteDatabase, int i, int i2);

        public void onConfigure(@NonNull SupportSQLiteDatabase supportSQLiteDatabase) {
        }

        public void onOpen(@NonNull SupportSQLiteDatabase supportSQLiteDatabase) {
        }
    }
}
