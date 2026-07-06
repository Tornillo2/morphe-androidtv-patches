package androidx.tvprovider.media.tv;

import android.content.ContentValues;
import android.database.Cursor;
import android.os.Build;
import androidx.annotation.RestrictTo;
import androidx.tvprovider.media.tv.BasePreviewProgram;
import androidx.tvprovider.media.tv.BaseProgram;
import j$.util.Objects;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public final class PreviewProgram extends BasePreviewProgram {
    public static final int INVALID_INT_VALUE = -1;
    public static final long INVALID_LONG_VALUE = -1;

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public static final String[] PROJECTION = getProjection();

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class Builder extends BasePreviewProgram.Builder<Builder> {
        public Builder() {
        }

        public PreviewProgram build() {
            return new PreviewProgram((BaseProgram.Builder) this);
        }

        public Builder setChannelId(long j) {
            this.mValues.put("channel_id", Long.valueOf(j));
            return this;
        }

        public Builder setWeight(int i) {
            this.mValues.put("weight", Integer.valueOf(i));
            return this;
        }

        public Builder(PreviewProgram previewProgram) {
            this.mValues = new ContentValues(previewProgram.mValues);
        }
    }

    public PreviewProgram(Builder builder) {
        super((BaseProgram.Builder) builder);
    }

    public static PreviewProgram fromCursor(Cursor cursor) {
        Builder builder = new Builder();
        BasePreviewProgram.setFieldsFromCursor(cursor, (BasePreviewProgram.Builder) builder);
        int columnIndex = cursor.getColumnIndex("channel_id");
        if (columnIndex >= 0 && !cursor.isNull(columnIndex)) {
            builder.setChannelId(cursor.getLong(columnIndex));
        }
        int columnIndex2 = cursor.getColumnIndex("weight");
        if (columnIndex2 >= 0 && !cursor.isNull(columnIndex2)) {
            builder.setWeight(cursor.getInt(columnIndex2));
        }
        return new PreviewProgram((BaseProgram.Builder) builder);
    }

    public static String[] getProjection() {
        return (String[]) CollectionUtils.concatAll(BasePreviewProgram.PROJECTION, new String[]{"channel_id", "weight"});
    }

    @Override // androidx.tvprovider.media.tv.BasePreviewProgram, androidx.tvprovider.media.tv.BaseProgram
    public boolean equals(Object obj) {
        if (obj instanceof PreviewProgram) {
            return this.mValues.equals(((PreviewProgram) obj).mValues);
        }
        return false;
    }

    public long getChannelId() {
        Long asLong = this.mValues.getAsLong("channel_id");
        if (asLong == null) {
            return -1L;
        }
        return asLong.longValue();
    }

    public int getWeight() {
        Integer asInteger = this.mValues.getAsInteger("weight");
        if (asInteger == null) {
            return -1;
        }
        return asInteger.intValue();
    }

    public boolean hasAnyUpdatedValues(PreviewProgram previewProgram) {
        for (String str : previewProgram.mValues.keySet()) {
            if (!Objects.deepEquals(previewProgram.mValues.get(str), this.mValues.get(str))) {
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
        return "PreviewProgram{" + this.mValues.toString() + "}";
    }

    @Override // androidx.tvprovider.media.tv.BasePreviewProgram
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public ContentValues toContentValues(boolean z) {
        ContentValues contentValues = super.toContentValues(z);
        if (Build.VERSION.SDK_INT < 26) {
            contentValues.remove("channel_id");
            contentValues.remove("weight");
        }
        return contentValues;
    }
}
