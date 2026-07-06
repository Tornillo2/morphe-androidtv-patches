package androidx.room;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import java.io.File;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public class SQLiteCopyOpenHelperFactory implements SupportSQLiteOpenHelper.Factory {

    @Nullable
    public final String mCopyFromAssetPath;

    @Nullable
    public final File mCopyFromFile;

    @NonNull
    public final SupportSQLiteOpenHelper.Factory mDelegate;

    public SQLiteCopyOpenHelperFactory(@Nullable String str, @Nullable File file, @NonNull SupportSQLiteOpenHelper.Factory factory) {
        this.mCopyFromAssetPath = str;
        this.mCopyFromFile = file;
        this.mDelegate = factory;
    }

    @Override // androidx.sqlite.db.SupportSQLiteOpenHelper.Factory
    public SupportSQLiteOpenHelper create(SupportSQLiteOpenHelper.Configuration configuration) {
        return new SQLiteCopyOpenHelper(configuration.context, this.mCopyFromAssetPath, this.mCopyFromFile, configuration.callback.version, this.mDelegate.create(configuration));
    }
}
