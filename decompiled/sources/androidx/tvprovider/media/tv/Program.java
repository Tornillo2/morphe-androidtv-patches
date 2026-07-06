package androidx.tvprovider.media.tv;

import android.content.ContentValues;
import android.database.Cursor;
import android.os.Build;
import androidx.annotation.NonNull;
import androidx.annotation.RestrictTo;
import androidx.tvprovider.media.tv.BaseProgram;
import androidx.tvprovider.media.tv.TvContractCompat;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public final class Program extends BaseProgram implements Comparable<Program> {
    public static final long INVALID_LONG_VALUE = -1;
    public static final int IS_RECORDING_PROHIBITED = 1;

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public static final String[] PROJECTION = getProjection();

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static class Builder extends BaseProgram.Builder<Builder> {
        public Builder() {
        }

        public Program build() {
            return new Program((BaseProgram.Builder) this);
        }

        public Builder setBroadcastGenres(String[] strArr) {
            this.mValues.put("broadcast_genre", TvContractCompat.Programs.Genres.encode(strArr));
            return this;
        }

        public Builder setChannelId(long j) {
            this.mValues.put("channel_id", Long.valueOf(j));
            return this;
        }

        public Builder setEndTimeUtcMillis(long j) {
            this.mValues.put("end_time_utc_millis", Long.valueOf(j));
            return this;
        }

        public Builder setRecordingProhibited(boolean z) {
            this.mValues.put(TvContractCompat.Programs.COLUMN_RECORDING_PROHIBITED, Integer.valueOf(z ? 1 : 0));
            return this;
        }

        public Builder setStartTimeUtcMillis(long j) {
            this.mValues.put("start_time_utc_millis", Long.valueOf(j));
            return this;
        }

        public Builder(Program program) {
            this.mValues = new ContentValues(program.mValues);
        }
    }

    public Program(Builder builder) {
        super(builder);
    }

    public static Program fromCursor(Cursor cursor) {
        int columnIndex;
        Builder builder = new Builder();
        BaseProgram.setFieldsFromCursor(cursor, builder);
        int columnIndex2 = cursor.getColumnIndex("channel_id");
        if (columnIndex2 >= 0 && !cursor.isNull(columnIndex2)) {
            builder.setChannelId(cursor.getLong(columnIndex2));
        }
        int columnIndex3 = cursor.getColumnIndex("broadcast_genre");
        if (columnIndex3 >= 0 && !cursor.isNull(columnIndex3)) {
            builder.setBroadcastGenres(TvContractCompat.Programs.Genres.decode(cursor.getString(columnIndex3)));
        }
        int columnIndex4 = cursor.getColumnIndex("start_time_utc_millis");
        if (columnIndex4 >= 0 && !cursor.isNull(columnIndex4)) {
            builder.setStartTimeUtcMillis(cursor.getLong(columnIndex4));
        }
        int columnIndex5 = cursor.getColumnIndex("end_time_utc_millis");
        if (columnIndex5 >= 0 && !cursor.isNull(columnIndex5)) {
            builder.setEndTimeUtcMillis(cursor.getLong(columnIndex5));
        }
        if (Build.VERSION.SDK_INT >= 24 && (columnIndex = cursor.getColumnIndex(TvContractCompat.Programs.COLUMN_RECORDING_PROHIBITED)) >= 0 && !cursor.isNull(columnIndex)) {
            builder.setRecordingProhibited(cursor.getInt(columnIndex) == 1);
        }
        Program program = new Program();
        program.mValues = builder.mValues;
        return program;
    }

    private static String[] getProjection() {
        String[] strArr = {"channel_id", "broadcast_genre", "start_time_utc_millis", "end_time_utc_millis"};
        return Build.VERSION.SDK_INT >= 24 ? (String[]) CollectionUtils.concatAll(BaseProgram.PROJECTION, strArr, new String[]{TvContractCompat.Programs.COLUMN_RECORDING_PROHIBITED}) : (String[]) CollectionUtils.concatAll(BaseProgram.PROJECTION, strArr);
    }

    @Override // androidx.tvprovider.media.tv.BaseProgram
    public boolean equals(Object obj) {
        if (obj instanceof Program) {
            return this.mValues.equals(((Program) obj).mValues);
        }
        return false;
    }

    public String[] getBroadcastGenres() {
        return TvContractCompat.Programs.Genres.decode(this.mValues.getAsString("broadcast_genre"));
    }

    public long getChannelId() {
        Long asLong = this.mValues.getAsLong("channel_id");
        if (asLong == null) {
            return -1L;
        }
        return asLong.longValue();
    }

    public long getEndTimeUtcMillis() {
        Long asLong = this.mValues.getAsLong("end_time_utc_millis");
        if (asLong == null) {
            return -1L;
        }
        return asLong.longValue();
    }

    public long getStartTimeUtcMillis() {
        Long asLong = this.mValues.getAsLong("start_time_utc_millis");
        if (asLong == null) {
            return -1L;
        }
        return asLong.longValue();
    }

    @Override // androidx.tvprovider.media.tv.BaseProgram
    public int hashCode() {
        return this.mValues.hashCode();
    }

    public boolean isRecordingProhibited() {
        Integer asInteger = this.mValues.getAsInteger(TvContractCompat.Programs.COLUMN_RECORDING_PROHIBITED);
        return asInteger != null && asInteger.intValue() == 1;
    }

    @Override // androidx.tvprovider.media.tv.BaseProgram
    public ContentValues toContentValues() {
        ContentValues contentValues = super.toContentValues();
        if (Build.VERSION.SDK_INT < 24) {
            contentValues.remove(TvContractCompat.Programs.COLUMN_RECORDING_PROHIBITED);
        }
        return contentValues;
    }

    @Override // androidx.tvprovider.media.tv.BaseProgram
    public String toString() {
        return "Program{" + this.mValues.toString() + "}";
    }

    @Override // java.lang.Comparable
    public int compareTo(@NonNull Program program) {
        return Long.compare(this.mValues.getAsLong("start_time_utc_millis").longValue(), program.mValues.getAsLong("start_time_utc_millis").longValue());
    }
}
