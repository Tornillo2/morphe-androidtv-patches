package androidx.tvprovider.media.tv;

import android.content.ContentValues;
import android.database.Cursor;
import android.media.tv.TvContentRating;
import android.net.Uri;
import android.os.Build;
import androidx.annotation.RestrictTo;
import androidx.tvprovider.media.tv.TvContractCompat;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@RestrictTo({RestrictTo.Scope.LIBRARY})
public abstract class BaseProgram {
    public static final int INVALID_INT_VALUE = -1;
    public static final long INVALID_LONG_VALUE = -1;
    public static final int IS_SEARCHABLE = 1;

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public static final String[] PROJECTION = getProjection();
    public static final int REVIEW_RATING_STYLE_UNKNOWN = -1;

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public ContentValues mValues;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static abstract class Builder<T extends Builder> {

        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
        public ContentValues mValues;

        public Builder() {
            this.mValues = new ContentValues();
        }

        public T setAudioLanguages(String[] strArr) {
            this.mValues.put(TvContractCompat.ProgramColumns.COLUMN_AUDIO_LANGUAGE, TvContractUtils.audioLanguagesToString(strArr));
            return this;
        }

        public T setCanonicalGenres(String[] strArr) {
            this.mValues.put("canonical_genre", TvContractCompat.Programs.Genres.encode(strArr));
            return this;
        }

        public T setContentRatings(TvContentRating[] tvContentRatingArr) {
            this.mValues.put(TvContractCompat.ProgramColumns.COLUMN_CONTENT_RATING, TvContractUtils.contentRatingsToString(tvContentRatingArr));
            return this;
        }

        public T setDescription(String str) {
            this.mValues.put(TvContractCompat.ProgramColumns.COLUMN_SHORT_DESCRIPTION, str);
            return this;
        }

        public T setEpisodeNumber(int i) {
            setEpisodeNumber(String.valueOf(i), i);
            return this;
        }

        public T setEpisodeTitle(String str) {
            this.mValues.put(TvContractCompat.ProgramColumns.COLUMN_EPISODE_TITLE, str);
            return this;
        }

        public T setId(long j) {
            this.mValues.put("_id", Long.valueOf(j));
            return this;
        }

        public T setInternalProviderData(byte[] bArr) {
            this.mValues.put("internal_provider_data", bArr);
            return this;
        }

        public T setInternalProviderFlag1(long j) {
            this.mValues.put("internal_provider_flag1", Long.valueOf(j));
            return this;
        }

        public T setInternalProviderFlag2(long j) {
            this.mValues.put("internal_provider_flag2", Long.valueOf(j));
            return this;
        }

        public T setInternalProviderFlag3(long j) {
            this.mValues.put("internal_provider_flag3", Long.valueOf(j));
            return this;
        }

        public T setInternalProviderFlag4(long j) {
            this.mValues.put("internal_provider_flag4", Long.valueOf(j));
            return this;
        }

        public T setLongDescription(String str) {
            this.mValues.put(TvContractCompat.ProgramColumns.COLUMN_LONG_DESCRIPTION, str);
            return this;
        }

        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
        public T setPackageName(String str) {
            this.mValues.put(TvContractCompat.BaseTvColumns.COLUMN_PACKAGE_NAME, str);
            return this;
        }

        public T setPosterArtUri(Uri uri) {
            this.mValues.put(TvContractCompat.ProgramColumns.COLUMN_POSTER_ART_URI, uri == null ? null : uri.toString());
            return this;
        }

        public T setReviewRating(String str) {
            this.mValues.put(TvContractCompat.ProgramColumns.COLUMN_REVIEW_RATING, str);
            return this;
        }

        public T setReviewRatingStyle(int i) {
            this.mValues.put(TvContractCompat.ProgramColumns.COLUMN_REVIEW_RATING_STYLE, Integer.valueOf(i));
            return this;
        }

        public T setSearchable(boolean z) {
            this.mValues.put("searchable", Integer.valueOf(z ? 1 : 0));
            return this;
        }

        public T setSeasonNumber(int i) {
            setSeasonNumber(String.valueOf(i), i);
            return this;
        }

        public T setSeasonTitle(String str) {
            this.mValues.put(TvContractCompat.ProgramColumns.COLUMN_SEASON_TITLE, str);
            return this;
        }

        public T setThumbnailUri(Uri uri) {
            this.mValues.put(TvContractCompat.ProgramColumns.COLUMN_THUMBNAIL_URI, uri == null ? null : uri.toString());
            return this;
        }

        public T setTitle(String str) {
            this.mValues.put("title", str);
            return this;
        }

        public T setVideoHeight(int i) {
            this.mValues.put(TvContractCompat.ProgramColumns.COLUMN_VIDEO_HEIGHT, Integer.valueOf(i));
            return this;
        }

        public T setVideoWidth(int i) {
            this.mValues.put(TvContractCompat.ProgramColumns.COLUMN_VIDEO_WIDTH, Integer.valueOf(i));
            return this;
        }

        public T setEpisodeNumber(String str, int i) {
            if (Build.VERSION.SDK_INT >= 24) {
                this.mValues.put(TvContractCompat.ProgramColumns.COLUMN_EPISODE_DISPLAY_NUMBER, str);
                return this;
            }
            this.mValues.put(TvContractCompat.Programs.COLUMN_EPISODE_NUMBER, Integer.valueOf(i));
            return this;
        }

        public T setSeasonNumber(String str, int i) {
            if (Build.VERSION.SDK_INT >= 24) {
                this.mValues.put(TvContractCompat.ProgramColumns.COLUMN_SEASON_DISPLAY_NUMBER, str);
                return this;
            }
            this.mValues.put(TvContractCompat.Programs.COLUMN_SEASON_NUMBER, Integer.valueOf(i));
            return this;
        }

        public Builder(BaseProgram baseProgram) {
            this.mValues = new ContentValues(baseProgram.mValues);
        }
    }

    public BaseProgram(Builder builder) {
        this.mValues = builder.mValues;
    }

    private static String[] getProjection() {
        int i = Build.VERSION.SDK_INT;
        String[] strArr = {"_id", TvContractCompat.BaseTvColumns.COLUMN_PACKAGE_NAME, "title", TvContractCompat.ProgramColumns.COLUMN_EPISODE_TITLE, i >= 24 ? TvContractCompat.ProgramColumns.COLUMN_SEASON_DISPLAY_NUMBER : TvContractCompat.Programs.COLUMN_SEASON_NUMBER, i >= 24 ? TvContractCompat.ProgramColumns.COLUMN_EPISODE_DISPLAY_NUMBER : TvContractCompat.Programs.COLUMN_EPISODE_NUMBER, TvContractCompat.ProgramColumns.COLUMN_SHORT_DESCRIPTION, TvContractCompat.ProgramColumns.COLUMN_LONG_DESCRIPTION, TvContractCompat.ProgramColumns.COLUMN_POSTER_ART_URI, TvContractCompat.ProgramColumns.COLUMN_THUMBNAIL_URI, TvContractCompat.ProgramColumns.COLUMN_AUDIO_LANGUAGE, "canonical_genre", TvContractCompat.ProgramColumns.COLUMN_CONTENT_RATING, TvContractCompat.ProgramColumns.COLUMN_VIDEO_WIDTH, TvContractCompat.ProgramColumns.COLUMN_VIDEO_HEIGHT, "internal_provider_data"};
        String[] strArr2 = {"searchable", "internal_provider_flag1", "internal_provider_flag2", "internal_provider_flag3", "internal_provider_flag4"};
        String[] strArr3 = {TvContractCompat.ProgramColumns.COLUMN_SEASON_TITLE};
        return i >= 26 ? (String[]) CollectionUtils.concatAll(strArr, strArr2, strArr3, new String[]{TvContractCompat.ProgramColumns.COLUMN_REVIEW_RATING, TvContractCompat.ProgramColumns.COLUMN_REVIEW_RATING_STYLE}) : i >= 24 ? (String[]) CollectionUtils.concatAll(strArr, strArr2, strArr3) : i >= 23 ? (String[]) CollectionUtils.concatAll(strArr, strArr2) : strArr;
    }

    public static void setFieldsFromCursor(Cursor cursor, Builder builder) {
        int columnIndex;
        int columnIndex2 = cursor.getColumnIndex("_id");
        if (columnIndex2 >= 0 && !cursor.isNull(columnIndex2)) {
            builder.setId(cursor.getLong(columnIndex2));
        }
        int columnIndex3 = cursor.getColumnIndex(TvContractCompat.BaseTvColumns.COLUMN_PACKAGE_NAME);
        if (columnIndex3 >= 0 && !cursor.isNull(columnIndex3)) {
            builder.setPackageName(cursor.getString(columnIndex3));
        }
        int columnIndex4 = cursor.getColumnIndex("title");
        if (columnIndex4 >= 0 && !cursor.isNull(columnIndex4)) {
            builder.setTitle(cursor.getString(columnIndex4));
        }
        int columnIndex5 = cursor.getColumnIndex(TvContractCompat.ProgramColumns.COLUMN_EPISODE_TITLE);
        if (columnIndex5 >= 0 && !cursor.isNull(columnIndex5)) {
            builder.setEpisodeTitle(cursor.getString(columnIndex5));
        }
        int i = Build.VERSION.SDK_INT;
        if (i >= 24) {
            int columnIndex6 = cursor.getColumnIndex(TvContractCompat.ProgramColumns.COLUMN_SEASON_DISPLAY_NUMBER);
            if (columnIndex6 >= 0 && !cursor.isNull(columnIndex6)) {
                builder.setSeasonNumber(cursor.getString(columnIndex6), -1);
            }
        } else {
            int columnIndex7 = cursor.getColumnIndex(TvContractCompat.Programs.COLUMN_SEASON_NUMBER);
            if (columnIndex7 >= 0 && !cursor.isNull(columnIndex7)) {
                builder.setSeasonNumber(cursor.getInt(columnIndex7));
            }
        }
        if (i >= 24) {
            int columnIndex8 = cursor.getColumnIndex(TvContractCompat.ProgramColumns.COLUMN_EPISODE_DISPLAY_NUMBER);
            if (columnIndex8 >= 0 && !cursor.isNull(columnIndex8)) {
                builder.setEpisodeNumber(cursor.getString(columnIndex8), -1);
            }
        } else {
            int columnIndex9 = cursor.getColumnIndex(TvContractCompat.Programs.COLUMN_EPISODE_NUMBER);
            if (columnIndex9 >= 0 && !cursor.isNull(columnIndex9)) {
                builder.setEpisodeNumber(cursor.getInt(columnIndex9));
            }
        }
        int columnIndex10 = cursor.getColumnIndex(TvContractCompat.ProgramColumns.COLUMN_SHORT_DESCRIPTION);
        if (columnIndex10 >= 0 && !cursor.isNull(columnIndex10)) {
            builder.setDescription(cursor.getString(columnIndex10));
        }
        int columnIndex11 = cursor.getColumnIndex(TvContractCompat.ProgramColumns.COLUMN_LONG_DESCRIPTION);
        if (columnIndex11 >= 0 && !cursor.isNull(columnIndex11)) {
            builder.setLongDescription(cursor.getString(columnIndex11));
        }
        int columnIndex12 = cursor.getColumnIndex(TvContractCompat.ProgramColumns.COLUMN_POSTER_ART_URI);
        if (columnIndex12 >= 0 && !cursor.isNull(columnIndex12)) {
            builder.setPosterArtUri(Uri.parse(cursor.getString(columnIndex12)));
        }
        int columnIndex13 = cursor.getColumnIndex(TvContractCompat.ProgramColumns.COLUMN_THUMBNAIL_URI);
        if (columnIndex13 >= 0 && !cursor.isNull(columnIndex13)) {
            builder.setThumbnailUri(Uri.parse(cursor.getString(columnIndex13)));
        }
        int columnIndex14 = cursor.getColumnIndex(TvContractCompat.ProgramColumns.COLUMN_AUDIO_LANGUAGE);
        if (columnIndex14 >= 0 && !cursor.isNull(columnIndex14)) {
            builder.setAudioLanguages(TvContractUtils.stringToAudioLanguages(cursor.getString(columnIndex14)));
        }
        int columnIndex15 = cursor.getColumnIndex("canonical_genre");
        if (columnIndex15 >= 0 && !cursor.isNull(columnIndex15)) {
            builder.setCanonicalGenres(TvContractCompat.Programs.Genres.decode(cursor.getString(columnIndex15)));
        }
        int columnIndex16 = cursor.getColumnIndex(TvContractCompat.ProgramColumns.COLUMN_CONTENT_RATING);
        if (columnIndex16 >= 0 && !cursor.isNull(columnIndex16)) {
            builder.setContentRatings(TvContractUtils.stringToContentRatings(cursor.getString(columnIndex16)));
        }
        int columnIndex17 = cursor.getColumnIndex(TvContractCompat.ProgramColumns.COLUMN_VIDEO_WIDTH);
        if (columnIndex17 >= 0 && !cursor.isNull(columnIndex17)) {
            builder.setVideoWidth((int) cursor.getLong(columnIndex17));
        }
        int columnIndex18 = cursor.getColumnIndex(TvContractCompat.ProgramColumns.COLUMN_VIDEO_HEIGHT);
        if (columnIndex18 >= 0 && !cursor.isNull(columnIndex18)) {
            builder.setVideoHeight((int) cursor.getLong(columnIndex18));
        }
        int columnIndex19 = cursor.getColumnIndex("internal_provider_data");
        if (columnIndex19 >= 0 && !cursor.isNull(columnIndex19)) {
            builder.setInternalProviderData(cursor.getBlob(columnIndex19));
        }
        if (i >= 23) {
            int columnIndex20 = cursor.getColumnIndex("searchable");
            if (columnIndex20 >= 0 && !cursor.isNull(columnIndex20)) {
                builder.setSearchable(cursor.getInt(columnIndex20) == 1);
            }
            int columnIndex21 = cursor.getColumnIndex("internal_provider_flag1");
            if (columnIndex21 >= 0 && !cursor.isNull(columnIndex21)) {
                builder.setInternalProviderFlag1(cursor.getLong(columnIndex21));
            }
            int columnIndex22 = cursor.getColumnIndex("internal_provider_flag2");
            if (columnIndex22 >= 0 && !cursor.isNull(columnIndex22)) {
                builder.setInternalProviderFlag2(cursor.getLong(columnIndex22));
            }
            int columnIndex23 = cursor.getColumnIndex("internal_provider_flag3");
            if (columnIndex23 >= 0 && !cursor.isNull(columnIndex23)) {
                builder.setInternalProviderFlag3(cursor.getLong(columnIndex23));
            }
            int columnIndex24 = cursor.getColumnIndex("internal_provider_flag4");
            if (columnIndex24 >= 0 && !cursor.isNull(columnIndex24)) {
                builder.setInternalProviderFlag4(cursor.getLong(columnIndex24));
            }
        }
        if (i >= 24 && (columnIndex = cursor.getColumnIndex(TvContractCompat.ProgramColumns.COLUMN_SEASON_TITLE)) >= 0 && !cursor.isNull(columnIndex)) {
            builder.setSeasonTitle(cursor.getString(columnIndex));
        }
        if (i >= 26) {
            int columnIndex25 = cursor.getColumnIndex(TvContractCompat.ProgramColumns.COLUMN_REVIEW_RATING_STYLE);
            if (columnIndex25 >= 0 && !cursor.isNull(columnIndex25)) {
                builder.setReviewRatingStyle(cursor.getInt(columnIndex25));
            }
            int columnIndex26 = cursor.getColumnIndex(TvContractCompat.ProgramColumns.COLUMN_REVIEW_RATING);
            if (columnIndex26 < 0 || cursor.isNull(columnIndex26)) {
                return;
            }
            builder.setReviewRating(cursor.getString(columnIndex26));
        }
    }

    public boolean equals(Object obj) {
        if (obj instanceof BaseProgram) {
            return this.mValues.equals(((BaseProgram) obj).mValues);
        }
        return false;
    }

    public String[] getAudioLanguages() {
        return TvContractUtils.stringToAudioLanguages(this.mValues.getAsString(TvContractCompat.ProgramColumns.COLUMN_AUDIO_LANGUAGE));
    }

    public String[] getCanonicalGenres() {
        return TvContractCompat.Programs.Genres.decode(this.mValues.getAsString("canonical_genre"));
    }

    public TvContentRating[] getContentRatings() {
        return TvContractUtils.stringToContentRatings(this.mValues.getAsString(TvContractCompat.ProgramColumns.COLUMN_CONTENT_RATING));
    }

    public String getDescription() {
        return this.mValues.getAsString(TvContractCompat.ProgramColumns.COLUMN_SHORT_DESCRIPTION);
    }

    public String getEpisodeNumber() {
        return Build.VERSION.SDK_INT >= 24 ? this.mValues.getAsString(TvContractCompat.ProgramColumns.COLUMN_EPISODE_DISPLAY_NUMBER) : this.mValues.getAsString(TvContractCompat.Programs.COLUMN_EPISODE_NUMBER);
    }

    public String getEpisodeTitle() {
        return this.mValues.getAsString(TvContractCompat.ProgramColumns.COLUMN_EPISODE_TITLE);
    }

    public long getId() {
        Long asLong = this.mValues.getAsLong("_id");
        if (asLong == null) {
            return -1L;
        }
        return asLong.longValue();
    }

    public byte[] getInternalProviderDataByteArray() {
        return this.mValues.getAsByteArray("internal_provider_data");
    }

    public Long getInternalProviderFlag1() {
        return this.mValues.getAsLong("internal_provider_flag1");
    }

    public Long getInternalProviderFlag2() {
        return this.mValues.getAsLong("internal_provider_flag2");
    }

    public Long getInternalProviderFlag3() {
        return this.mValues.getAsLong("internal_provider_flag3");
    }

    public Long getInternalProviderFlag4() {
        return this.mValues.getAsLong("internal_provider_flag4");
    }

    public String getLongDescription() {
        return this.mValues.getAsString(TvContractCompat.ProgramColumns.COLUMN_LONG_DESCRIPTION);
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public String getPackageName() {
        return this.mValues.getAsString(TvContractCompat.BaseTvColumns.COLUMN_PACKAGE_NAME);
    }

    public Uri getPosterArtUri() {
        String asString = this.mValues.getAsString(TvContractCompat.ProgramColumns.COLUMN_POSTER_ART_URI);
        if (asString == null) {
            return null;
        }
        return Uri.parse(asString);
    }

    public String getReviewRating() {
        return this.mValues.getAsString(TvContractCompat.ProgramColumns.COLUMN_REVIEW_RATING);
    }

    public int getReviewRatingStyle() {
        Integer asInteger = this.mValues.getAsInteger(TvContractCompat.ProgramColumns.COLUMN_REVIEW_RATING_STYLE);
        if (asInteger == null) {
            return -1;
        }
        return asInteger.intValue();
    }

    public String getSeasonNumber() {
        return Build.VERSION.SDK_INT >= 24 ? this.mValues.getAsString(TvContractCompat.ProgramColumns.COLUMN_SEASON_DISPLAY_NUMBER) : this.mValues.getAsString(TvContractCompat.Programs.COLUMN_SEASON_NUMBER);
    }

    public String getSeasonTitle() {
        return this.mValues.getAsString(TvContractCompat.ProgramColumns.COLUMN_SEASON_TITLE);
    }

    public Uri getThumbnailUri() {
        String asString = this.mValues.getAsString(TvContractCompat.ProgramColumns.COLUMN_POSTER_ART_URI);
        if (asString == null) {
            return null;
        }
        return Uri.parse(asString);
    }

    public String getTitle() {
        return this.mValues.getAsString("title");
    }

    public int getVideoHeight() {
        Integer asInteger = this.mValues.getAsInteger(TvContractCompat.ProgramColumns.COLUMN_VIDEO_HEIGHT);
        if (asInteger == null) {
            return -1;
        }
        return asInteger.intValue();
    }

    public int getVideoWidth() {
        Integer asInteger = this.mValues.getAsInteger(TvContractCompat.ProgramColumns.COLUMN_VIDEO_WIDTH);
        if (asInteger == null) {
            return -1;
        }
        return asInteger.intValue();
    }

    public int hashCode() {
        return this.mValues.hashCode();
    }

    public boolean isSearchable() {
        Integer asInteger = this.mValues.getAsInteger("searchable");
        return asInteger == null || asInteger.intValue() == 1;
    }

    public ContentValues toContentValues() {
        ContentValues contentValues = new ContentValues(this.mValues);
        int i = Build.VERSION.SDK_INT;
        if (i < 23) {
            contentValues.remove("searchable");
            contentValues.remove("internal_provider_flag1");
            contentValues.remove("internal_provider_flag2");
            contentValues.remove("internal_provider_flag3");
            contentValues.remove("internal_provider_flag4");
        }
        if (i < 24) {
            contentValues.remove(TvContractCompat.ProgramColumns.COLUMN_SEASON_TITLE);
        }
        if (i < 26) {
            contentValues.remove(TvContractCompat.ProgramColumns.COLUMN_REVIEW_RATING_STYLE);
            contentValues.remove(TvContractCompat.ProgramColumns.COLUMN_REVIEW_RATING);
        }
        return contentValues;
    }

    public String toString() {
        return "BaseProgram{" + this.mValues.toString() + "}";
    }
}
