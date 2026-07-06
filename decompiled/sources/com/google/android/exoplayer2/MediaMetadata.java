package com.google.android.exoplayer2;

import android.net.Uri;
import android.os.Bundle;
import androidx.annotation.IntRange;
import androidx.annotation.Nullable;
import com.google.android.exoplayer2.Bundleable;
import com.google.android.exoplayer2.metadata.Metadata;
import com.google.android.exoplayer2.util.Util;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class MediaMetadata implements Bundleable {
    public static final int FOLDER_TYPE_ALBUMS = 2;
    public static final int FOLDER_TYPE_ARTISTS = 3;
    public static final int FOLDER_TYPE_GENRES = 4;
    public static final int FOLDER_TYPE_MIXED = 0;
    public static final int FOLDER_TYPE_NONE = -1;
    public static final int FOLDER_TYPE_PLAYLISTS = 5;
    public static final int FOLDER_TYPE_TITLES = 1;
    public static final int FOLDER_TYPE_YEARS = 6;
    public static final int MEDIA_TYPE_ALBUM = 10;
    public static final int MEDIA_TYPE_ARTIST = 11;
    public static final int MEDIA_TYPE_AUDIO_BOOK = 15;
    public static final int MEDIA_TYPE_AUDIO_BOOK_CHAPTER = 2;
    public static final int MEDIA_TYPE_FOLDER_ALBUMS = 21;
    public static final int MEDIA_TYPE_FOLDER_ARTISTS = 22;
    public static final int MEDIA_TYPE_FOLDER_AUDIO_BOOKS = 26;
    public static final int MEDIA_TYPE_FOLDER_GENRES = 23;
    public static final int MEDIA_TYPE_FOLDER_MIXED = 20;
    public static final int MEDIA_TYPE_FOLDER_MOVIES = 35;
    public static final int MEDIA_TYPE_FOLDER_NEWS = 32;
    public static final int MEDIA_TYPE_FOLDER_PLAYLISTS = 24;
    public static final int MEDIA_TYPE_FOLDER_PODCASTS = 27;
    public static final int MEDIA_TYPE_FOLDER_RADIO_STATIONS = 31;
    public static final int MEDIA_TYPE_FOLDER_TRAILERS = 34;
    public static final int MEDIA_TYPE_FOLDER_TV_CHANNELS = 28;
    public static final int MEDIA_TYPE_FOLDER_TV_SERIES = 29;
    public static final int MEDIA_TYPE_FOLDER_TV_SHOWS = 30;
    public static final int MEDIA_TYPE_FOLDER_VIDEOS = 33;
    public static final int MEDIA_TYPE_FOLDER_YEARS = 25;
    public static final int MEDIA_TYPE_GENRE = 12;
    public static final int MEDIA_TYPE_MIXED = 0;
    public static final int MEDIA_TYPE_MOVIE = 8;
    public static final int MEDIA_TYPE_MUSIC = 1;
    public static final int MEDIA_TYPE_NEWS = 5;
    public static final int MEDIA_TYPE_PLAYLIST = 13;
    public static final int MEDIA_TYPE_PODCAST = 16;
    public static final int MEDIA_TYPE_PODCAST_EPISODE = 3;
    public static final int MEDIA_TYPE_RADIO_STATION = 4;
    public static final int MEDIA_TYPE_TRAILER = 7;
    public static final int MEDIA_TYPE_TV_CHANNEL = 17;
    public static final int MEDIA_TYPE_TV_SEASON = 19;
    public static final int MEDIA_TYPE_TV_SERIES = 18;
    public static final int MEDIA_TYPE_TV_SHOW = 9;
    public static final int MEDIA_TYPE_VIDEO = 6;
    public static final int MEDIA_TYPE_YEAR = 14;
    public static final int PICTURE_TYPE_ARTIST_PERFORMER = 8;
    public static final int PICTURE_TYPE_A_BRIGHT_COLORED_FISH = 17;
    public static final int PICTURE_TYPE_BACK_COVER = 4;
    public static final int PICTURE_TYPE_BAND_ARTIST_LOGO = 19;
    public static final int PICTURE_TYPE_BAND_ORCHESTRA = 10;
    public static final int PICTURE_TYPE_COMPOSER = 11;
    public static final int PICTURE_TYPE_CONDUCTOR = 9;
    public static final int PICTURE_TYPE_DURING_PERFORMANCE = 15;
    public static final int PICTURE_TYPE_DURING_RECORDING = 14;
    public static final int PICTURE_TYPE_FILE_ICON = 1;
    public static final int PICTURE_TYPE_FILE_ICON_OTHER = 2;
    public static final int PICTURE_TYPE_FRONT_COVER = 3;
    public static final int PICTURE_TYPE_ILLUSTRATION = 18;
    public static final int PICTURE_TYPE_LEAD_ARTIST_PERFORMER = 7;
    public static final int PICTURE_TYPE_LEAFLET_PAGE = 5;
    public static final int PICTURE_TYPE_LYRICIST = 12;
    public static final int PICTURE_TYPE_MEDIA = 6;
    public static final int PICTURE_TYPE_MOVIE_VIDEO_SCREEN_CAPTURE = 16;
    public static final int PICTURE_TYPE_OTHER = 0;
    public static final int PICTURE_TYPE_PUBLISHER_STUDIO_LOGO = 20;
    public static final int PICTURE_TYPE_RECORDING_LOCATION = 13;

    @Nullable
    public final CharSequence albumArtist;

    @Nullable
    public final CharSequence albumTitle;

    @Nullable
    public final CharSequence artist;

    @Nullable
    public final byte[] artworkData;

    @Nullable
    public final Integer artworkDataType;

    @Nullable
    public final Uri artworkUri;

    @Nullable
    public final CharSequence compilation;

    @Nullable
    public final CharSequence composer;

    @Nullable
    public final CharSequence conductor;

    @Nullable
    public final CharSequence description;

    @Nullable
    public final Integer discNumber;

    @Nullable
    public final CharSequence displayTitle;

    @Nullable
    public final Bundle extras;

    @Nullable
    public final Integer folderType;

    @Nullable
    public final CharSequence genre;

    @Nullable
    public final Boolean isBrowsable;

    @Nullable
    public final Boolean isPlayable;

    @Nullable
    public final Integer mediaType;

    @Nullable
    public final Rating overallRating;

    @Nullable
    public final Integer recordingDay;

    @Nullable
    public final Integer recordingMonth;

    @Nullable
    public final Integer recordingYear;

    @Nullable
    public final Integer releaseDay;

    @Nullable
    public final Integer releaseMonth;

    @Nullable
    public final Integer releaseYear;

    @Nullable
    public final CharSequence station;

    @Nullable
    public final CharSequence subtitle;

    @Nullable
    public final CharSequence title;

    @Nullable
    public final Integer totalDiscCount;

    @Nullable
    public final Integer totalTrackCount;

    @Nullable
    public final Integer trackNumber;

    @Nullable
    public final Rating userRating;

    @Nullable
    public final CharSequence writer;

    @Nullable
    @Deprecated
    public final Integer year;
    public static final MediaMetadata EMPTY = new MediaMetadata(new Builder());
    public static final String FIELD_TITLE = Util.intToStringMaxRadix(0);
    public static final String FIELD_ARTIST = Integer.toString(1, 36);
    public static final String FIELD_ALBUM_TITLE = Integer.toString(2, 36);
    public static final String FIELD_ALBUM_ARTIST = Integer.toString(3, 36);
    public static final String FIELD_DISPLAY_TITLE = Integer.toString(4, 36);
    public static final String FIELD_SUBTITLE = Integer.toString(5, 36);
    public static final String FIELD_DESCRIPTION = Integer.toString(6, 36);
    public static final String FIELD_USER_RATING = Integer.toString(8, 36);
    public static final String FIELD_OVERALL_RATING = Integer.toString(9, 36);
    public static final String FIELD_ARTWORK_DATA = Integer.toString(10, 36);
    public static final String FIELD_ARTWORK_URI = Integer.toString(11, 36);
    public static final String FIELD_TRACK_NUMBER = Integer.toString(12, 36);
    public static final String FIELD_TOTAL_TRACK_COUNT = Integer.toString(13, 36);
    public static final String FIELD_FOLDER_TYPE = Integer.toString(14, 36);
    public static final String FIELD_IS_PLAYABLE = Integer.toString(15, 36);
    public static final String FIELD_RECORDING_YEAR = Integer.toString(16, 36);
    public static final String FIELD_RECORDING_MONTH = Integer.toString(17, 36);
    public static final String FIELD_RECORDING_DAY = Integer.toString(18, 36);
    public static final String FIELD_RELEASE_YEAR = Integer.toString(19, 36);
    public static final String FIELD_RELEASE_MONTH = Integer.toString(20, 36);
    public static final String FIELD_RELEASE_DAY = Integer.toString(21, 36);
    public static final String FIELD_WRITER = Integer.toString(22, 36);
    public static final String FIELD_COMPOSER = Integer.toString(23, 36);
    public static final String FIELD_CONDUCTOR = Integer.toString(24, 36);
    public static final String FIELD_DISC_NUMBER = Integer.toString(25, 36);
    public static final String FIELD_TOTAL_DISC_COUNT = Integer.toString(26, 36);
    public static final String FIELD_GENRE = Integer.toString(27, 36);
    public static final String FIELD_COMPILATION = Integer.toString(28, 36);
    public static final String FIELD_ARTWORK_DATA_TYPE = Integer.toString(29, 36);
    public static final String FIELD_STATION = Integer.toString(30, 36);
    public static final String FIELD_MEDIA_TYPE = Integer.toString(31, 36);
    public static final String FIELD_IS_BROWSABLE = Integer.toString(32, 36);
    public static final String FIELD_EXTRAS = Integer.toString(1000, 36);
    public static final Bundleable.Creator<MediaMetadata> CREATOR = new MediaMetadata$$ExternalSyntheticLambda0();

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class Builder {

        @Nullable
        public CharSequence albumArtist;

        @Nullable
        public CharSequence albumTitle;

        @Nullable
        public CharSequence artist;

        @Nullable
        public byte[] artworkData;

        @Nullable
        public Integer artworkDataType;

        @Nullable
        public Uri artworkUri;

        @Nullable
        public CharSequence compilation;

        @Nullable
        public CharSequence composer;

        @Nullable
        public CharSequence conductor;

        @Nullable
        public CharSequence description;

        @Nullable
        public Integer discNumber;

        @Nullable
        public CharSequence displayTitle;

        @Nullable
        public Bundle extras;

        @Nullable
        public Integer folderType;

        @Nullable
        public CharSequence genre;

        @Nullable
        public Boolean isBrowsable;

        @Nullable
        public Boolean isPlayable;

        @Nullable
        public Integer mediaType;

        @Nullable
        public Rating overallRating;

        @Nullable
        public Integer recordingDay;

        @Nullable
        public Integer recordingMonth;

        @Nullable
        public Integer recordingYear;

        @Nullable
        public Integer releaseDay;

        @Nullable
        public Integer releaseMonth;

        @Nullable
        public Integer releaseYear;

        @Nullable
        public CharSequence station;

        @Nullable
        public CharSequence subtitle;

        @Nullable
        public CharSequence title;

        @Nullable
        public Integer totalDiscCount;

        @Nullable
        public Integer totalTrackCount;

        @Nullable
        public Integer trackNumber;

        @Nullable
        public Rating userRating;

        @Nullable
        public CharSequence writer;

        public MediaMetadata build() {
            return new MediaMetadata(this);
        }

        @CanIgnoreReturnValue
        public Builder maybeSetArtworkData(byte[] bArr, int i) {
            if (this.artworkData != null && !Util.areEqual(Integer.valueOf(i), 3) && Util.areEqual(this.artworkDataType, 3)) {
                return this;
            }
            this.artworkData = (byte[]) bArr.clone();
            this.artworkDataType = Integer.valueOf(i);
            return this;
        }

        @CanIgnoreReturnValue
        public Builder populate(@Nullable MediaMetadata mediaMetadata) {
            if (mediaMetadata != null) {
                CharSequence charSequence = mediaMetadata.title;
                if (charSequence != null) {
                    this.title = charSequence;
                }
                CharSequence charSequence2 = mediaMetadata.artist;
                if (charSequence2 != null) {
                    this.artist = charSequence2;
                }
                CharSequence charSequence3 = mediaMetadata.albumTitle;
                if (charSequence3 != null) {
                    this.albumTitle = charSequence3;
                }
                CharSequence charSequence4 = mediaMetadata.albumArtist;
                if (charSequence4 != null) {
                    this.albumArtist = charSequence4;
                }
                CharSequence charSequence5 = mediaMetadata.displayTitle;
                if (charSequence5 != null) {
                    this.displayTitle = charSequence5;
                }
                CharSequence charSequence6 = mediaMetadata.subtitle;
                if (charSequence6 != null) {
                    this.subtitle = charSequence6;
                }
                CharSequence charSequence7 = mediaMetadata.description;
                if (charSequence7 != null) {
                    this.description = charSequence7;
                }
                Rating rating = mediaMetadata.userRating;
                if (rating != null) {
                    this.userRating = rating;
                }
                Rating rating2 = mediaMetadata.overallRating;
                if (rating2 != null) {
                    this.overallRating = rating2;
                }
                byte[] bArr = mediaMetadata.artworkData;
                if (bArr != null) {
                    setArtworkData(bArr, mediaMetadata.artworkDataType);
                }
                Uri uri = mediaMetadata.artworkUri;
                if (uri != null) {
                    this.artworkUri = uri;
                }
                Integer num = mediaMetadata.trackNumber;
                if (num != null) {
                    this.trackNumber = num;
                }
                Integer num2 = mediaMetadata.totalTrackCount;
                if (num2 != null) {
                    this.totalTrackCount = num2;
                }
                Integer num3 = mediaMetadata.folderType;
                if (num3 != null) {
                    this.folderType = num3;
                }
                Boolean bool = mediaMetadata.isBrowsable;
                if (bool != null) {
                    this.isBrowsable = bool;
                }
                Boolean bool2 = mediaMetadata.isPlayable;
                if (bool2 != null) {
                    this.isPlayable = bool2;
                }
                Integer num4 = mediaMetadata.year;
                if (num4 != null) {
                    this.recordingYear = num4;
                }
                Integer num5 = mediaMetadata.recordingYear;
                if (num5 != null) {
                    this.recordingYear = num5;
                }
                Integer num6 = mediaMetadata.recordingMonth;
                if (num6 != null) {
                    this.recordingMonth = num6;
                }
                Integer num7 = mediaMetadata.recordingDay;
                if (num7 != null) {
                    this.recordingDay = num7;
                }
                Integer num8 = mediaMetadata.releaseYear;
                if (num8 != null) {
                    this.releaseYear = num8;
                }
                Integer num9 = mediaMetadata.releaseMonth;
                if (num9 != null) {
                    this.releaseMonth = num9;
                }
                Integer num10 = mediaMetadata.releaseDay;
                if (num10 != null) {
                    this.releaseDay = num10;
                }
                CharSequence charSequence8 = mediaMetadata.writer;
                if (charSequence8 != null) {
                    this.writer = charSequence8;
                }
                CharSequence charSequence9 = mediaMetadata.composer;
                if (charSequence9 != null) {
                    this.composer = charSequence9;
                }
                CharSequence charSequence10 = mediaMetadata.conductor;
                if (charSequence10 != null) {
                    this.conductor = charSequence10;
                }
                Integer num11 = mediaMetadata.discNumber;
                if (num11 != null) {
                    this.discNumber = num11;
                }
                Integer num12 = mediaMetadata.totalDiscCount;
                if (num12 != null) {
                    this.totalDiscCount = num12;
                }
                CharSequence charSequence11 = mediaMetadata.genre;
                if (charSequence11 != null) {
                    this.genre = charSequence11;
                }
                CharSequence charSequence12 = mediaMetadata.compilation;
                if (charSequence12 != null) {
                    this.compilation = charSequence12;
                }
                CharSequence charSequence13 = mediaMetadata.station;
                if (charSequence13 != null) {
                    this.station = charSequence13;
                }
                Integer num13 = mediaMetadata.mediaType;
                if (num13 != null) {
                    this.mediaType = num13;
                }
                Bundle bundle = mediaMetadata.extras;
                if (bundle != null) {
                    this.extras = bundle;
                }
            }
            return this;
        }

        @CanIgnoreReturnValue
        public Builder populateFromMetadata(Metadata metadata) {
            int i = 0;
            while (true) {
                Metadata.Entry[] entryArr = metadata.entries;
                if (i >= entryArr.length) {
                    return this;
                }
                entryArr[i].populateMediaMetadata(this);
                i++;
            }
        }

        @CanIgnoreReturnValue
        public Builder setAlbumArtist(@Nullable CharSequence charSequence) {
            this.albumArtist = charSequence;
            return this;
        }

        @CanIgnoreReturnValue
        public Builder setAlbumTitle(@Nullable CharSequence charSequence) {
            this.albumTitle = charSequence;
            return this;
        }

        @CanIgnoreReturnValue
        public Builder setArtist(@Nullable CharSequence charSequence) {
            this.artist = charSequence;
            return this;
        }

        @CanIgnoreReturnValue
        @Deprecated
        public Builder setArtworkData(@Nullable byte[] bArr) {
            setArtworkData(bArr, null);
            return this;
        }

        @CanIgnoreReturnValue
        public Builder setArtworkUri(@Nullable Uri uri) {
            this.artworkUri = uri;
            return this;
        }

        @CanIgnoreReturnValue
        public Builder setCompilation(@Nullable CharSequence charSequence) {
            this.compilation = charSequence;
            return this;
        }

        @CanIgnoreReturnValue
        public Builder setComposer(@Nullable CharSequence charSequence) {
            this.composer = charSequence;
            return this;
        }

        @CanIgnoreReturnValue
        public Builder setConductor(@Nullable CharSequence charSequence) {
            this.conductor = charSequence;
            return this;
        }

        @CanIgnoreReturnValue
        public Builder setDescription(@Nullable CharSequence charSequence) {
            this.description = charSequence;
            return this;
        }

        @CanIgnoreReturnValue
        public Builder setDiscNumber(@Nullable Integer num) {
            this.discNumber = num;
            return this;
        }

        @CanIgnoreReturnValue
        public Builder setDisplayTitle(@Nullable CharSequence charSequence) {
            this.displayTitle = charSequence;
            return this;
        }

        @CanIgnoreReturnValue
        public Builder setExtras(@Nullable Bundle bundle) {
            this.extras = bundle;
            return this;
        }

        @CanIgnoreReturnValue
        public Builder setFolderType(@Nullable Integer num) {
            this.folderType = num;
            return this;
        }

        @CanIgnoreReturnValue
        public Builder setGenre(@Nullable CharSequence charSequence) {
            this.genre = charSequence;
            return this;
        }

        @CanIgnoreReturnValue
        public Builder setIsBrowsable(@Nullable Boolean bool) {
            this.isBrowsable = bool;
            return this;
        }

        @CanIgnoreReturnValue
        public Builder setIsPlayable(@Nullable Boolean bool) {
            this.isPlayable = bool;
            return this;
        }

        @CanIgnoreReturnValue
        public Builder setMediaType(@Nullable Integer num) {
            this.mediaType = num;
            return this;
        }

        @CanIgnoreReturnValue
        public Builder setOverallRating(@Nullable Rating rating) {
            this.overallRating = rating;
            return this;
        }

        @CanIgnoreReturnValue
        public Builder setRecordingDay(@IntRange(from = 1, to = 31) @Nullable Integer num) {
            this.recordingDay = num;
            return this;
        }

        @CanIgnoreReturnValue
        public Builder setRecordingMonth(@IntRange(from = 1, to = 12) @Nullable Integer num) {
            this.recordingMonth = num;
            return this;
        }

        @CanIgnoreReturnValue
        public Builder setRecordingYear(@Nullable Integer num) {
            this.recordingYear = num;
            return this;
        }

        @CanIgnoreReturnValue
        public Builder setReleaseDay(@IntRange(from = 1, to = 31) @Nullable Integer num) {
            this.releaseDay = num;
            return this;
        }

        @CanIgnoreReturnValue
        public Builder setReleaseMonth(@IntRange(from = 1, to = 12) @Nullable Integer num) {
            this.releaseMonth = num;
            return this;
        }

        @CanIgnoreReturnValue
        public Builder setReleaseYear(@Nullable Integer num) {
            this.releaseYear = num;
            return this;
        }

        @CanIgnoreReturnValue
        public Builder setStation(@Nullable CharSequence charSequence) {
            this.station = charSequence;
            return this;
        }

        @CanIgnoreReturnValue
        public Builder setSubtitle(@Nullable CharSequence charSequence) {
            this.subtitle = charSequence;
            return this;
        }

        @CanIgnoreReturnValue
        public Builder setTitle(@Nullable CharSequence charSequence) {
            this.title = charSequence;
            return this;
        }

        @CanIgnoreReturnValue
        public Builder setTotalDiscCount(@Nullable Integer num) {
            this.totalDiscCount = num;
            return this;
        }

        @CanIgnoreReturnValue
        public Builder setTotalTrackCount(@Nullable Integer num) {
            this.totalTrackCount = num;
            return this;
        }

        @CanIgnoreReturnValue
        public Builder setTrackNumber(@Nullable Integer num) {
            this.trackNumber = num;
            return this;
        }

        @CanIgnoreReturnValue
        public Builder setUserRating(@Nullable Rating rating) {
            this.userRating = rating;
            return this;
        }

        @CanIgnoreReturnValue
        public Builder setWriter(@Nullable CharSequence charSequence) {
            this.writer = charSequence;
            return this;
        }

        @CanIgnoreReturnValue
        @Deprecated
        public Builder setYear(@Nullable Integer num) {
            this.recordingYear = num;
            return this;
        }

        public Builder() {
        }

        @CanIgnoreReturnValue
        public Builder setArtworkData(@Nullable byte[] bArr, @Nullable Integer num) {
            this.artworkData = bArr == null ? null : (byte[]) bArr.clone();
            this.artworkDataType = num;
            return this;
        }

        public Builder(MediaMetadata mediaMetadata) {
            this.title = mediaMetadata.title;
            this.artist = mediaMetadata.artist;
            this.albumTitle = mediaMetadata.albumTitle;
            this.albumArtist = mediaMetadata.albumArtist;
            this.displayTitle = mediaMetadata.displayTitle;
            this.subtitle = mediaMetadata.subtitle;
            this.description = mediaMetadata.description;
            this.userRating = mediaMetadata.userRating;
            this.overallRating = mediaMetadata.overallRating;
            this.artworkData = mediaMetadata.artworkData;
            this.artworkDataType = mediaMetadata.artworkDataType;
            this.artworkUri = mediaMetadata.artworkUri;
            this.trackNumber = mediaMetadata.trackNumber;
            this.totalTrackCount = mediaMetadata.totalTrackCount;
            this.folderType = mediaMetadata.folderType;
            this.isBrowsable = mediaMetadata.isBrowsable;
            this.isPlayable = mediaMetadata.isPlayable;
            this.recordingYear = mediaMetadata.recordingYear;
            this.recordingMonth = mediaMetadata.recordingMonth;
            this.recordingDay = mediaMetadata.recordingDay;
            this.releaseYear = mediaMetadata.releaseYear;
            this.releaseMonth = mediaMetadata.releaseMonth;
            this.releaseDay = mediaMetadata.releaseDay;
            this.writer = mediaMetadata.writer;
            this.composer = mediaMetadata.composer;
            this.conductor = mediaMetadata.conductor;
            this.discNumber = mediaMetadata.discNumber;
            this.totalDiscCount = mediaMetadata.totalDiscCount;
            this.genre = mediaMetadata.genre;
            this.compilation = mediaMetadata.compilation;
            this.station = mediaMetadata.station;
            this.mediaType = mediaMetadata.mediaType;
            this.extras = mediaMetadata.extras;
        }

        @CanIgnoreReturnValue
        public Builder populateFromMetadata(List<Metadata> list) {
            for (int i = 0; i < list.size(); i++) {
                Metadata metadata = list.get(i);
                int i2 = 0;
                while (true) {
                    Metadata.Entry[] entryArr = metadata.entries;
                    if (i2 < entryArr.length) {
                        entryArr[i2].populateMediaMetadata(this);
                        i2++;
                    }
                }
            }
            return this;
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.LOCAL_VARIABLE, ElementType.TYPE_USE})
    @Documented
    @Retention(RetentionPolicy.SOURCE)
    public @interface FolderType {
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @Target({ElementType.TYPE_USE})
    @Documented
    @Retention(RetentionPolicy.SOURCE)
    public @interface MediaType {
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.LOCAL_VARIABLE, ElementType.TYPE_USE})
    @Documented
    @Retention(RetentionPolicy.SOURCE)
    public @interface PictureType {
    }

    public static MediaMetadata fromBundle(Bundle bundle) {
        Bundle bundle2;
        Bundle bundle3;
        Builder builder = new Builder();
        builder.title = bundle.getCharSequence(FIELD_TITLE);
        builder.artist = bundle.getCharSequence(FIELD_ARTIST);
        builder.albumTitle = bundle.getCharSequence(FIELD_ALBUM_TITLE);
        builder.albumArtist = bundle.getCharSequence(FIELD_ALBUM_ARTIST);
        builder.displayTitle = bundle.getCharSequence(FIELD_DISPLAY_TITLE);
        builder.subtitle = bundle.getCharSequence(FIELD_SUBTITLE);
        builder.description = bundle.getCharSequence(FIELD_DESCRIPTION);
        byte[] byteArray = bundle.getByteArray(FIELD_ARTWORK_DATA);
        String str = FIELD_ARTWORK_DATA_TYPE;
        builder.setArtworkData(byteArray, bundle.containsKey(str) ? Integer.valueOf(bundle.getInt(str)) : null);
        builder.artworkUri = (Uri) bundle.getParcelable(FIELD_ARTWORK_URI);
        builder.writer = bundle.getCharSequence(FIELD_WRITER);
        builder.composer = bundle.getCharSequence(FIELD_COMPOSER);
        builder.conductor = bundle.getCharSequence(FIELD_CONDUCTOR);
        builder.genre = bundle.getCharSequence(FIELD_GENRE);
        builder.compilation = bundle.getCharSequence(FIELD_COMPILATION);
        builder.station = bundle.getCharSequence(FIELD_STATION);
        builder.extras = bundle.getBundle(FIELD_EXTRAS);
        String str2 = FIELD_USER_RATING;
        if (bundle.containsKey(str2) && (bundle3 = bundle.getBundle(str2)) != null) {
            builder.userRating = (Rating) Rating.CREATOR.fromBundle(bundle3);
        }
        String str3 = FIELD_OVERALL_RATING;
        if (bundle.containsKey(str3) && (bundle2 = bundle.getBundle(str3)) != null) {
            builder.overallRating = (Rating) Rating.CREATOR.fromBundle(bundle2);
        }
        String str4 = FIELD_TRACK_NUMBER;
        if (bundle.containsKey(str4)) {
            builder.trackNumber = Integer.valueOf(bundle.getInt(str4));
        }
        String str5 = FIELD_TOTAL_TRACK_COUNT;
        if (bundle.containsKey(str5)) {
            builder.totalTrackCount = Integer.valueOf(bundle.getInt(str5));
        }
        String str6 = FIELD_FOLDER_TYPE;
        if (bundle.containsKey(str6)) {
            builder.folderType = Integer.valueOf(bundle.getInt(str6));
        }
        String str7 = FIELD_IS_BROWSABLE;
        if (bundle.containsKey(str7)) {
            builder.isBrowsable = Boolean.valueOf(bundle.getBoolean(str7));
        }
        String str8 = FIELD_IS_PLAYABLE;
        if (bundle.containsKey(str8)) {
            builder.isPlayable = Boolean.valueOf(bundle.getBoolean(str8));
        }
        String str9 = FIELD_RECORDING_YEAR;
        if (bundle.containsKey(str9)) {
            builder.recordingYear = Integer.valueOf(bundle.getInt(str9));
        }
        String str10 = FIELD_RECORDING_MONTH;
        if (bundle.containsKey(str10)) {
            builder.recordingMonth = Integer.valueOf(bundle.getInt(str10));
        }
        String str11 = FIELD_RECORDING_DAY;
        if (bundle.containsKey(str11)) {
            builder.recordingDay = Integer.valueOf(bundle.getInt(str11));
        }
        String str12 = FIELD_RELEASE_YEAR;
        if (bundle.containsKey(str12)) {
            builder.releaseYear = Integer.valueOf(bundle.getInt(str12));
        }
        String str13 = FIELD_RELEASE_MONTH;
        if (bundle.containsKey(str13)) {
            builder.releaseMonth = Integer.valueOf(bundle.getInt(str13));
        }
        String str14 = FIELD_RELEASE_DAY;
        if (bundle.containsKey(str14)) {
            builder.releaseDay = Integer.valueOf(bundle.getInt(str14));
        }
        String str15 = FIELD_DISC_NUMBER;
        if (bundle.containsKey(str15)) {
            builder.discNumber = Integer.valueOf(bundle.getInt(str15));
        }
        String str16 = FIELD_TOTAL_DISC_COUNT;
        if (bundle.containsKey(str16)) {
            builder.totalDiscCount = Integer.valueOf(bundle.getInt(str16));
        }
        String str17 = FIELD_MEDIA_TYPE;
        if (bundle.containsKey(str17)) {
            builder.mediaType = Integer.valueOf(bundle.getInt(str17));
        }
        return new MediaMetadata(builder);
    }

    public static int getFolderTypeFromMediaType(int i) {
        switch (i) {
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
            case 12:
            case 13:
            case 14:
            case 15:
            case 16:
            case 17:
            case 18:
            case 19:
            case 31:
            case 32:
            case 33:
            case 34:
            case 35:
                return 1;
            case 20:
            case 26:
            case 27:
            case 28:
            case 29:
            case 30:
            default:
                return 0;
            case 21:
                return 2;
            case 22:
                return 3;
            case 23:
                return 4;
            case 24:
                return 5;
            case 25:
                return 6;
        }
    }

    public static int getMediaTypeFromFolderType(int i) {
        switch (i) {
            case 1:
                return 0;
            case 2:
                return 21;
            case 3:
                return 22;
            case 4:
                return 23;
            case 5:
                return 24;
            case 6:
                return 25;
            default:
                return 20;
        }
    }

    public Builder buildUpon() {
        return new Builder(this);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && MediaMetadata.class == obj.getClass()) {
            MediaMetadata mediaMetadata = (MediaMetadata) obj;
            if (Util.areEqual(this.title, mediaMetadata.title) && Util.areEqual(this.artist, mediaMetadata.artist) && Util.areEqual(this.albumTitle, mediaMetadata.albumTitle) && Util.areEqual(this.albumArtist, mediaMetadata.albumArtist) && Util.areEqual(this.displayTitle, mediaMetadata.displayTitle) && Util.areEqual(this.subtitle, mediaMetadata.subtitle) && Util.areEqual(this.description, mediaMetadata.description) && Util.areEqual(this.userRating, mediaMetadata.userRating) && Util.areEqual(this.overallRating, mediaMetadata.overallRating) && Arrays.equals(this.artworkData, mediaMetadata.artworkData) && Util.areEqual(this.artworkDataType, mediaMetadata.artworkDataType) && Util.areEqual(this.artworkUri, mediaMetadata.artworkUri) && Util.areEqual(this.trackNumber, mediaMetadata.trackNumber) && Util.areEqual(this.totalTrackCount, mediaMetadata.totalTrackCount) && Util.areEqual(this.folderType, mediaMetadata.folderType) && Util.areEqual(this.isBrowsable, mediaMetadata.isBrowsable) && Util.areEqual(this.isPlayable, mediaMetadata.isPlayable) && Util.areEqual(this.recordingYear, mediaMetadata.recordingYear) && Util.areEqual(this.recordingMonth, mediaMetadata.recordingMonth) && Util.areEqual(this.recordingDay, mediaMetadata.recordingDay) && Util.areEqual(this.releaseYear, mediaMetadata.releaseYear) && Util.areEqual(this.releaseMonth, mediaMetadata.releaseMonth) && Util.areEqual(this.releaseDay, mediaMetadata.releaseDay) && Util.areEqual(this.writer, mediaMetadata.writer) && Util.areEqual(this.composer, mediaMetadata.composer) && Util.areEqual(this.conductor, mediaMetadata.conductor) && Util.areEqual(this.discNumber, mediaMetadata.discNumber) && Util.areEqual(this.totalDiscCount, mediaMetadata.totalDiscCount) && Util.areEqual(this.genre, mediaMetadata.genre) && Util.areEqual(this.compilation, mediaMetadata.compilation) && Util.areEqual(this.station, mediaMetadata.station) && Util.areEqual(this.mediaType, mediaMetadata.mediaType)) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{this.title, this.artist, this.albumTitle, this.albumArtist, this.displayTitle, this.subtitle, this.description, this.userRating, this.overallRating, Integer.valueOf(Arrays.hashCode(this.artworkData)), this.artworkDataType, this.artworkUri, this.trackNumber, this.totalTrackCount, this.folderType, this.isBrowsable, this.isPlayable, this.recordingYear, this.recordingMonth, this.recordingDay, this.releaseYear, this.releaseMonth, this.releaseDay, this.writer, this.composer, this.conductor, this.discNumber, this.totalDiscCount, this.genre, this.compilation, this.station, this.mediaType});
    }

    @Override // com.google.android.exoplayer2.Bundleable
    public Bundle toBundle() {
        Bundle bundle = new Bundle();
        CharSequence charSequence = this.title;
        if (charSequence != null) {
            bundle.putCharSequence(FIELD_TITLE, charSequence);
        }
        CharSequence charSequence2 = this.artist;
        if (charSequence2 != null) {
            bundle.putCharSequence(FIELD_ARTIST, charSequence2);
        }
        CharSequence charSequence3 = this.albumTitle;
        if (charSequence3 != null) {
            bundle.putCharSequence(FIELD_ALBUM_TITLE, charSequence3);
        }
        CharSequence charSequence4 = this.albumArtist;
        if (charSequence4 != null) {
            bundle.putCharSequence(FIELD_ALBUM_ARTIST, charSequence4);
        }
        CharSequence charSequence5 = this.displayTitle;
        if (charSequence5 != null) {
            bundle.putCharSequence(FIELD_DISPLAY_TITLE, charSequence5);
        }
        CharSequence charSequence6 = this.subtitle;
        if (charSequence6 != null) {
            bundle.putCharSequence(FIELD_SUBTITLE, charSequence6);
        }
        CharSequence charSequence7 = this.description;
        if (charSequence7 != null) {
            bundle.putCharSequence(FIELD_DESCRIPTION, charSequence7);
        }
        byte[] bArr = this.artworkData;
        if (bArr != null) {
            bundle.putByteArray(FIELD_ARTWORK_DATA, bArr);
        }
        Uri uri = this.artworkUri;
        if (uri != null) {
            bundle.putParcelable(FIELD_ARTWORK_URI, uri);
        }
        CharSequence charSequence8 = this.writer;
        if (charSequence8 != null) {
            bundle.putCharSequence(FIELD_WRITER, charSequence8);
        }
        CharSequence charSequence9 = this.composer;
        if (charSequence9 != null) {
            bundle.putCharSequence(FIELD_COMPOSER, charSequence9);
        }
        CharSequence charSequence10 = this.conductor;
        if (charSequence10 != null) {
            bundle.putCharSequence(FIELD_CONDUCTOR, charSequence10);
        }
        CharSequence charSequence11 = this.genre;
        if (charSequence11 != null) {
            bundle.putCharSequence(FIELD_GENRE, charSequence11);
        }
        CharSequence charSequence12 = this.compilation;
        if (charSequence12 != null) {
            bundle.putCharSequence(FIELD_COMPILATION, charSequence12);
        }
        CharSequence charSequence13 = this.station;
        if (charSequence13 != null) {
            bundle.putCharSequence(FIELD_STATION, charSequence13);
        }
        Rating rating = this.userRating;
        if (rating != null) {
            bundle.putBundle(FIELD_USER_RATING, rating.toBundle());
        }
        Rating rating2 = this.overallRating;
        if (rating2 != null) {
            bundle.putBundle(FIELD_OVERALL_RATING, rating2.toBundle());
        }
        Integer num = this.trackNumber;
        if (num != null) {
            bundle.putInt(FIELD_TRACK_NUMBER, num.intValue());
        }
        Integer num2 = this.totalTrackCount;
        if (num2 != null) {
            bundle.putInt(FIELD_TOTAL_TRACK_COUNT, num2.intValue());
        }
        Integer num3 = this.folderType;
        if (num3 != null) {
            bundle.putInt(FIELD_FOLDER_TYPE, num3.intValue());
        }
        Boolean bool = this.isBrowsable;
        if (bool != null) {
            bundle.putBoolean(FIELD_IS_BROWSABLE, bool.booleanValue());
        }
        Boolean bool2 = this.isPlayable;
        if (bool2 != null) {
            bundle.putBoolean(FIELD_IS_PLAYABLE, bool2.booleanValue());
        }
        Integer num4 = this.recordingYear;
        if (num4 != null) {
            bundle.putInt(FIELD_RECORDING_YEAR, num4.intValue());
        }
        Integer num5 = this.recordingMonth;
        if (num5 != null) {
            bundle.putInt(FIELD_RECORDING_MONTH, num5.intValue());
        }
        Integer num6 = this.recordingDay;
        if (num6 != null) {
            bundle.putInt(FIELD_RECORDING_DAY, num6.intValue());
        }
        Integer num7 = this.releaseYear;
        if (num7 != null) {
            bundle.putInt(FIELD_RELEASE_YEAR, num7.intValue());
        }
        Integer num8 = this.releaseMonth;
        if (num8 != null) {
            bundle.putInt(FIELD_RELEASE_MONTH, num8.intValue());
        }
        Integer num9 = this.releaseDay;
        if (num9 != null) {
            bundle.putInt(FIELD_RELEASE_DAY, num9.intValue());
        }
        Integer num10 = this.discNumber;
        if (num10 != null) {
            bundle.putInt(FIELD_DISC_NUMBER, num10.intValue());
        }
        Integer num11 = this.totalDiscCount;
        if (num11 != null) {
            bundle.putInt(FIELD_TOTAL_DISC_COUNT, num11.intValue());
        }
        Integer num12 = this.artworkDataType;
        if (num12 != null) {
            bundle.putInt(FIELD_ARTWORK_DATA_TYPE, num12.intValue());
        }
        Integer num13 = this.mediaType;
        if (num13 != null) {
            bundle.putInt(FIELD_MEDIA_TYPE, num13.intValue());
        }
        Bundle bundle2 = this.extras;
        if (bundle2 != null) {
            bundle.putBundle(FIELD_EXTRAS, bundle2);
        }
        return bundle;
    }

    public MediaMetadata(Builder builder) {
        Boolean boolValueOf = builder.isBrowsable;
        Integer numValueOf = builder.folderType;
        Integer numValueOf2 = builder.mediaType;
        if (boolValueOf != null) {
            if (!boolValueOf.booleanValue()) {
                numValueOf = -1;
            } else if (numValueOf == null || numValueOf.intValue() == -1) {
                numValueOf = Integer.valueOf(numValueOf2 != null ? getFolderTypeFromMediaType(numValueOf2.intValue()) : 0);
            }
        } else if (numValueOf != null) {
            boolean z = numValueOf.intValue() != -1;
            boolValueOf = Boolean.valueOf(z);
            if (z && numValueOf2 == null) {
                numValueOf2 = Integer.valueOf(getMediaTypeFromFolderType(numValueOf.intValue()));
            }
        }
        this.title = builder.title;
        this.artist = builder.artist;
        this.albumTitle = builder.albumTitle;
        this.albumArtist = builder.albumArtist;
        this.displayTitle = builder.displayTitle;
        this.subtitle = builder.subtitle;
        this.description = builder.description;
        this.userRating = builder.userRating;
        this.overallRating = builder.overallRating;
        this.artworkData = builder.artworkData;
        this.artworkDataType = builder.artworkDataType;
        this.artworkUri = builder.artworkUri;
        this.trackNumber = builder.trackNumber;
        this.totalTrackCount = builder.totalTrackCount;
        this.folderType = numValueOf;
        this.isBrowsable = boolValueOf;
        this.isPlayable = builder.isPlayable;
        Integer num = builder.recordingYear;
        this.year = num;
        this.recordingYear = num;
        this.recordingMonth = builder.recordingMonth;
        this.recordingDay = builder.recordingDay;
        this.releaseYear = builder.releaseYear;
        this.releaseMonth = builder.releaseMonth;
        this.releaseDay = builder.releaseDay;
        this.writer = builder.writer;
        this.composer = builder.composer;
        this.conductor = builder.conductor;
        this.discNumber = builder.discNumber;
        this.totalDiscCount = builder.totalDiscCount;
        this.genre = builder.genre;
        this.compilation = builder.compilation;
        this.station = builder.station;
        this.mediaType = numValueOf2;
        this.extras = builder.extras;
    }
}
