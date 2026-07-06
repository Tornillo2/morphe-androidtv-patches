package androidx.tvprovider.media.tv;

import android.content.ContentValues;
import android.database.Cursor;
import android.os.Build;
import androidx.annotation.RestrictTo;
import androidx.tvprovider.media.tv.BasePreviewProgram;
import androidx.tvprovider.media.tv.BaseProgram;
import androidx.tvprovider.media.tv.TvContractCompat;
import j$.util.Objects;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public final class WatchNextProgram extends BasePreviewProgram {
    public static final int INVALID_INT_VALUE = -1;
    public static final long INVALID_LONG_VALUE = -1;

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public static final String[] PROJECTION = getProjection();
    public static final int WATCH_NEXT_TYPE_UNKNOWN = -1;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class Builder extends BasePreviewProgram.Builder<Builder> {
        public Builder() {
        }

        public WatchNextProgram build() {
            return new WatchNextProgram((BaseProgram.Builder) this);
        }

        public Builder setLastEngagementTimeUtcMillis(long j) {
            this.mValues.put(TvContractCompat.WatchNextPrograms.COLUMN_LAST_ENGAGEMENT_TIME_UTC_MILLIS, Long.valueOf(j));
            return this;
        }

        public Builder setWatchNextType(int i) {
            this.mValues.put(TvContractCompat.WatchNextPrograms.COLUMN_WATCH_NEXT_TYPE, Integer.valueOf(i));
            return this;
        }

        public Builder(WatchNextProgram watchNextProgram) {
            this.mValues = new ContentValues(watchNextProgram.mValues);
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @Retention(RetentionPolicy.SOURCE)
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public @interface WatchNextType {
    }

    public WatchNextProgram(Builder builder) {
        super((BaseProgram.Builder) builder);
    }

    public static WatchNextProgram fromCursor(Cursor cursor) {
        Builder builder = new Builder();
        BasePreviewProgram.setFieldsFromCursor(cursor, (BasePreviewProgram.Builder) builder);
        int columnIndex = cursor.getColumnIndex(TvContractCompat.WatchNextPrograms.COLUMN_WATCH_NEXT_TYPE);
        if (columnIndex >= 0 && !cursor.isNull(columnIndex)) {
            builder.setWatchNextType(cursor.getInt(columnIndex));
        }
        int columnIndex2 = cursor.getColumnIndex(TvContractCompat.WatchNextPrograms.COLUMN_LAST_ENGAGEMENT_TIME_UTC_MILLIS);
        if (columnIndex2 >= 0 && !cursor.isNull(columnIndex2)) {
            builder.setLastEngagementTimeUtcMillis(cursor.getLong(columnIndex2));
        }
        return new WatchNextProgram((BaseProgram.Builder) builder);
    }

    private static String[] getProjection() {
        return (String[]) CollectionUtils.concatAll(BasePreviewProgram.PROJECTION, new String[]{TvContractCompat.WatchNextPrograms.COLUMN_WATCH_NEXT_TYPE, TvContractCompat.WatchNextPrograms.COLUMN_LAST_ENGAGEMENT_TIME_UTC_MILLIS});
    }

    @Override // androidx.tvprovider.media.tv.BasePreviewProgram, androidx.tvprovider.media.tv.BaseProgram
    public boolean equals(Object obj) {
        if (obj instanceof WatchNextProgram) {
            return this.mValues.equals(((WatchNextProgram) obj).mValues);
        }
        return false;
    }

    public long getLastEngagementTimeUtcMillis() {
        Long asLong = this.mValues.getAsLong(TvContractCompat.WatchNextPrograms.COLUMN_LAST_ENGAGEMENT_TIME_UTC_MILLIS);
        if (asLong == null) {
            return -1L;
        }
        return asLong.longValue();
    }

    public int getWatchNextType() {
        Integer asInteger = this.mValues.getAsInteger(TvContractCompat.WatchNextPrograms.COLUMN_WATCH_NEXT_TYPE);
        if (asInteger == null) {
            return -1;
        }
        return asInteger.intValue();
    }

    public boolean hasAnyUpdatedValues(WatchNextProgram watchNextProgram) {
        for (String str : watchNextProgram.mValues.keySet()) {
            if (!Objects.deepEquals(watchNextProgram.mValues.get(str), this.mValues.get(str))) {
                return true;
            }
        }
        return false;
    }

    @Override // androidx.tvprovider.media.tv.BasePreviewProgram, androidx.tvprovider.media.tv.BaseProgram
    public ContentValues toContentValues() {
        return toContentValues(false);
    }

    @Override // androidx.tvprovider.media.tv.BaseProgram
    public String toString() {
        return "WatchNextProgram{" + this.mValues.toString() + "}";
    }

    @Override // androidx.tvprovider.media.tv.BasePreviewProgram
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public ContentValues toContentValues(boolean z) {
        ContentValues contentValues = super.toContentValues(z);
        if (Build.VERSION.SDK_INT < 26) {
            contentValues.remove(TvContractCompat.WatchNextPrograms.COLUMN_WATCH_NEXT_TYPE);
            contentValues.remove(TvContractCompat.WatchNextPrograms.COLUMN_LAST_ENGAGEMENT_TIME_UTC_MILLIS);
        }
        return contentValues;
    }
}
