package androidx.tvprovider.media.tv;

import android.content.ComponentName;
import android.content.ContentUris;
import android.content.Context;
import android.media.tv.TvContract;
import android.net.Uri;
import android.os.Build;
import android.provider.BaseColumns;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public final class TvContractCompat {

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public static final String ACTION_CHANNEL_BROWSABLE_REQUESTED = "android.media.tv.action.CHANNEL_BROWSABLE_REQUESTED";
    public static final String ACTION_INITIALIZE_PROGRAMS = "android.media.tv.action.INITIALIZE_PROGRAMS";
    public static final String ACTION_PREVIEW_PROGRAM_ADDED_TO_WATCH_NEXT = "android.media.tv.action.PREVIEW_PROGRAM_ADDED_TO_WATCH_NEXT";
    public static final String ACTION_PREVIEW_PROGRAM_BROWSABLE_DISABLED = "android.media.tv.action.PREVIEW_PROGRAM_BROWSABLE_DISABLED";
    public static final String ACTION_REQUEST_CHANNEL_BROWSABLE = "android.media.tv.action.REQUEST_CHANNEL_BROWSABLE";
    public static final String ACTION_WATCH_NEXT_PROGRAM_BROWSABLE_DISABLED = "android.media.tv.action.WATCH_NEXT_PROGRAM_BROWSABLE_DISABLED";
    public static final String AUTHORITY = "android.media.tv";
    public static final String EXTRA_CHANNEL_ID = "android.media.tv.extra.CHANNEL_ID";

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public static final String EXTRA_COLUMN_NAME = "android.media.tv.extra.COLUMN_NAME";

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public static final String EXTRA_DATA_TYPE = "android.media.tv.extra.DATA_TYPE";

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public static final String EXTRA_DEFAULT_VALUE = "android.media.tv.extra.DEFAULT_VALUE";

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public static final String EXTRA_EXISTING_COLUMN_NAMES = "android.media.tv.extra.EXISTING_COLUMN_NAMES";

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public static final String EXTRA_PACKAGE_NAME = "android.media.tv.extra.PACKAGE_NAME";
    public static final String EXTRA_PREVIEW_PROGRAM_ID = "android.media.tv.extra.PREVIEW_PROGRAM_ID";
    public static final String EXTRA_WATCH_NEXT_PROGRAM_ID = "android.media.tv.extra.WATCH_NEXT_PROGRAM_ID";

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public static final String METHOD_ADD_COLUMN = "add_column";

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public static final String METHOD_GET_COLUMNS = "get_columns";

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public static final String PARAM_BROWSABLE_ONLY = "browsable_only";

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public static final String PARAM_CANONICAL_GENRE = "canonical_genre";

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public static final String PARAM_CHANNEL = "channel";

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public static final String PARAM_END_TIME = "end_time";

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public static final String PARAM_INPUT = "input";

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public static final String PARAM_START_TIME = "start_time";
    public static final String PATH_CHANNEL = "channel";
    public static final String PATH_PASSTHROUGH = "passthrough";
    public static final String PATH_PREVIEW_PROGRAM = "preview_program";
    public static final String PATH_PROGRAM = "program";
    public static final String PATH_RECORDED_PROGRAM = "recorded_program";
    public static final String PATH_WATCH_NEXT_PROGRAM = "watch_next_program";

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public static final String PERMISSION_READ_TV_LISTINGS = "android.permission.READ_TV_LISTINGS";

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public interface BaseTvColumns extends BaseColumns {
        public static final String COLUMN_PACKAGE_NAME = "package_name";
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class Channels implements BaseTvColumns {
        public static final String COLUMN_APP_LINK_COLOR = "app_link_color";
        public static final String COLUMN_APP_LINK_ICON_URI = "app_link_icon_uri";
        public static final String COLUMN_APP_LINK_INTENT_URI = "app_link_intent_uri";
        public static final String COLUMN_APP_LINK_POSTER_ART_URI = "app_link_poster_art_uri";
        public static final String COLUMN_APP_LINK_TEXT = "app_link_text";
        public static final String COLUMN_BROWSABLE = "browsable";
        public static final String COLUMN_CONFIGURATION_DISPLAY_ORDER = "configuration_display_order";
        public static final String COLUMN_DESCRIPTION = "description";
        public static final String COLUMN_DISPLAY_NAME = "display_name";
        public static final String COLUMN_DISPLAY_NUMBER = "display_number";
        public static final String COLUMN_INPUT_ID = "input_id";
        public static final String COLUMN_INTERNAL_PROVIDER_DATA = "internal_provider_data";
        public static final String COLUMN_INTERNAL_PROVIDER_FLAG1 = "internal_provider_flag1";
        public static final String COLUMN_INTERNAL_PROVIDER_FLAG2 = "internal_provider_flag2";
        public static final String COLUMN_INTERNAL_PROVIDER_FLAG3 = "internal_provider_flag3";
        public static final String COLUMN_INTERNAL_PROVIDER_FLAG4 = "internal_provider_flag4";
        public static final String COLUMN_INTERNAL_PROVIDER_ID = "internal_provider_id";
        public static final String COLUMN_LOCKED = "locked";
        public static final String COLUMN_NETWORK_AFFILIATION = "network_affiliation";
        public static final String COLUMN_ORIGINAL_NETWORK_ID = "original_network_id";
        public static final String COLUMN_SEARCHABLE = "searchable";
        public static final String COLUMN_SERVICE_ID = "service_id";
        public static final String COLUMN_SERVICE_TYPE = "service_type";

        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
        public static final String COLUMN_SYSTEM_APPROVED = "system_approved";
        public static final String COLUMN_SYSTEM_CHANNEL_KEY = "system_channel_key";
        public static final String COLUMN_TRANSIENT = "transient";
        public static final String COLUMN_TRANSPORT_STREAM_ID = "transport_stream_id";
        public static final String COLUMN_TYPE = "type";
        public static final String COLUMN_VERSION_NUMBER = "version_number";
        public static final String COLUMN_VIDEO_FORMAT = "video_format";
        public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/channel";
        public static final String CONTENT_TYPE = "vnd.android.cursor.dir/channel";
        public static final Uri CONTENT_URI = Uri.parse("content://android.media.tv/channel");
        public static final String SERVICE_TYPE_AUDIO = "SERVICE_TYPE_AUDIO";
        public static final String SERVICE_TYPE_AUDIO_VIDEO = "SERVICE_TYPE_AUDIO_VIDEO";
        public static final String SERVICE_TYPE_OTHER = "SERVICE_TYPE_OTHER";
        public static final String TYPE_1SEG = "TYPE_1SEG";
        public static final String TYPE_ATSC_C = "TYPE_ATSC_C";
        public static final String TYPE_ATSC_M_H = "TYPE_ATSC_M_H";
        public static final String TYPE_ATSC_T = "TYPE_ATSC_T";
        public static final String TYPE_CMMB = "TYPE_CMMB";
        public static final String TYPE_DTMB = "TYPE_DTMB";
        public static final String TYPE_DVB_C = "TYPE_DVB_C";
        public static final String TYPE_DVB_C2 = "TYPE_DVB_C2";
        public static final String TYPE_DVB_H = "TYPE_DVB_H";
        public static final String TYPE_DVB_S = "TYPE_DVB_S";
        public static final String TYPE_DVB_S2 = "TYPE_DVB_S2";
        public static final String TYPE_DVB_SH = "TYPE_DVB_SH";
        public static final String TYPE_DVB_T = "TYPE_DVB_T";
        public static final String TYPE_DVB_T2 = "TYPE_DVB_T2";
        public static final String TYPE_ISDB_C = "TYPE_ISDB_C";
        public static final String TYPE_ISDB_S = "TYPE_ISDB_S";
        public static final String TYPE_ISDB_T = "TYPE_ISDB_T";
        public static final String TYPE_ISDB_TB = "TYPE_ISDB_TB";
        public static final String TYPE_NTSC = "TYPE_NTSC";
        public static final String TYPE_OTHER = "TYPE_OTHER";
        public static final String TYPE_PAL = "TYPE_PAL";
        public static final String TYPE_PREVIEW = "TYPE_PREVIEW";
        public static final String TYPE_SECAM = "TYPE_SECAM";
        public static final String TYPE_S_DMB = "TYPE_S_DMB";
        public static final String TYPE_T_DMB = "TYPE_T_DMB";
        public static final String VIDEO_FORMAT_1080I = "VIDEO_FORMAT_1080I";
        public static final String VIDEO_FORMAT_1080P = "VIDEO_FORMAT_1080P";
        public static final String VIDEO_FORMAT_2160P = "VIDEO_FORMAT_2160P";
        public static final String VIDEO_FORMAT_240P = "VIDEO_FORMAT_240P";
        public static final String VIDEO_FORMAT_360P = "VIDEO_FORMAT_360P";
        public static final String VIDEO_FORMAT_4320P = "VIDEO_FORMAT_4320P";
        public static final String VIDEO_FORMAT_480I = "VIDEO_FORMAT_480I";
        public static final String VIDEO_FORMAT_480P = "VIDEO_FORMAT_480P";
        public static final String VIDEO_FORMAT_576I = "VIDEO_FORMAT_576I";
        public static final String VIDEO_FORMAT_576P = "VIDEO_FORMAT_576P";
        public static final String VIDEO_FORMAT_720P = "VIDEO_FORMAT_720P";
        public static final Map<String, String> VIDEO_FORMAT_TO_RESOLUTION_MAP;
        public static final String VIDEO_RESOLUTION_ED = "VIDEO_RESOLUTION_ED";
        public static final String VIDEO_RESOLUTION_FHD = "VIDEO_RESOLUTION_FHD";
        public static final String VIDEO_RESOLUTION_HD = "VIDEO_RESOLUTION_HD";
        public static final String VIDEO_RESOLUTION_SD = "VIDEO_RESOLUTION_SD";
        public static final String VIDEO_RESOLUTION_UHD = "VIDEO_RESOLUTION_UHD";

        /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
        public static final class Logo {
            public static final String CONTENT_DIRECTORY = "logo";
        }

        /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
        @Retention(RetentionPolicy.SOURCE)
        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
        public @interface ServiceType {
        }

        /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
        @Retention(RetentionPolicy.SOURCE)
        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
        public @interface Type {
        }

        /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
        @Retention(RetentionPolicy.SOURCE)
        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
        public @interface VideoFormat {
        }

        /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
        @Retention(RetentionPolicy.SOURCE)
        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
        public @interface VideoResolution {
        }

        static {
            HashMap map = new HashMap();
            VIDEO_FORMAT_TO_RESOLUTION_MAP = map;
            map.put(VIDEO_FORMAT_480I, VIDEO_RESOLUTION_SD);
            map.put(VIDEO_FORMAT_480P, VIDEO_RESOLUTION_ED);
            map.put(VIDEO_FORMAT_576I, VIDEO_RESOLUTION_SD);
            map.put(VIDEO_FORMAT_576P, VIDEO_RESOLUTION_ED);
            map.put(VIDEO_FORMAT_720P, VIDEO_RESOLUTION_HD);
            map.put(VIDEO_FORMAT_1080I, VIDEO_RESOLUTION_HD);
            map.put(VIDEO_FORMAT_1080P, VIDEO_RESOLUTION_FHD);
            map.put(VIDEO_FORMAT_2160P, VIDEO_RESOLUTION_UHD);
            map.put(VIDEO_FORMAT_4320P, VIDEO_RESOLUTION_UHD);
        }

        @Nullable
        public static String getVideoResolution(String str) {
            return VIDEO_FORMAT_TO_RESOLUTION_MAP.get(str);
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public interface PreviewProgramColumns {
        public static final int ASPECT_RATIO_16_9 = 0;
        public static final int ASPECT_RATIO_1_1 = 3;
        public static final int ASPECT_RATIO_2_3 = 4;
        public static final int ASPECT_RATIO_3_2 = 1;
        public static final int ASPECT_RATIO_4_3 = 2;
        public static final int ASPECT_RATIO_MOVIE_POSTER = 5;
        public static final int AVAILABILITY_AVAILABLE = 0;
        public static final int AVAILABILITY_FREE = 4;
        public static final int AVAILABILITY_FREE_WITH_SUBSCRIPTION = 1;
        public static final int AVAILABILITY_PAID_CONTENT = 2;
        public static final int AVAILABILITY_PURCHASED = 3;
        public static final String COLUMN_AUTHOR = "author";
        public static final String COLUMN_AVAILABILITY = "availability";
        public static final String COLUMN_BROWSABLE = "browsable";
        public static final String COLUMN_CONTENT_ID = "content_id";
        public static final String COLUMN_DURATION_MILLIS = "duration_millis";
        public static final String COLUMN_END_TIME_UTC_MILLIS = "end_time_utc_millis";
        public static final String COLUMN_GENRE = "genre";
        public static final String COLUMN_INTENT_URI = "intent_uri";
        public static final String COLUMN_INTERACTION_COUNT = "interaction_count";
        public static final String COLUMN_INTERACTION_TYPE = "interaction_type";
        public static final String COLUMN_INTERNAL_PROVIDER_ID = "internal_provider_id";
        public static final String COLUMN_ITEM_COUNT = "item_count";
        public static final String COLUMN_LAST_PLAYBACK_POSITION_MILLIS = "last_playback_position_millis";
        public static final String COLUMN_LIVE = "live";
        public static final String COLUMN_LOGO_CONTENT_DESCRIPTION = "logo_content_description";
        public static final String COLUMN_LOGO_URI = "logo_uri";
        public static final String COLUMN_OFFER_PRICE = "offer_price";
        public static final String COLUMN_POSTER_ART_ASPECT_RATIO = "poster_art_aspect_ratio";
        public static final String COLUMN_PREVIEW_AUDIO_URI = "preview_audio_uri";
        public static final String COLUMN_PREVIEW_VIDEO_URI = "preview_video_uri";
        public static final String COLUMN_RELEASE_DATE = "release_date";
        public static final String COLUMN_STARTING_PRICE = "starting_price";
        public static final String COLUMN_START_TIME_UTC_MILLIS = "start_time_utc_millis";
        public static final String COLUMN_THUMBNAIL_ASPECT_RATIO = "poster_thumbnail_aspect_ratio";
        public static final String COLUMN_TRANSIENT = "transient";
        public static final String COLUMN_TV_SERIES_ITEM_TYPE = "tv_series_item_type";
        public static final String COLUMN_TYPE = "type";
        public static final int INTERACTION_TYPE_FANS = 3;
        public static final int INTERACTION_TYPE_FOLLOWERS = 2;
        public static final int INTERACTION_TYPE_LIKES = 4;
        public static final int INTERACTION_TYPE_LISTENS = 1;
        public static final int INTERACTION_TYPE_THUMBS = 5;
        public static final int INTERACTION_TYPE_VIEWERS = 6;
        public static final int INTERACTION_TYPE_VIEWS = 0;
        public static final int TV_SERIES_ITEM_TYPE_CHAPTER = 1;
        public static final int TV_SERIES_ITEM_TYPE_EPISODE = 0;
        public static final int TYPE_ALBUM = 8;
        public static final int TYPE_ARTIST = 9;
        public static final int TYPE_CHANNEL = 6;
        public static final int TYPE_CLIP = 4;
        public static final int TYPE_EVENT = 5;
        public static final int TYPE_GAME = 12;
        public static final int TYPE_MOVIE = 0;
        public static final int TYPE_PLAYLIST = 10;
        public static final int TYPE_STATION = 11;
        public static final int TYPE_TRACK = 7;
        public static final int TYPE_TV_EPISODE = 3;
        public static final int TYPE_TV_SEASON = 2;
        public static final int TYPE_TV_SERIES = 1;
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class PreviewPrograms implements BaseTvColumns, ProgramColumns, PreviewProgramColumns {
        public static final String COLUMN_CHANNEL_ID = "channel_id";
        public static final String COLUMN_WEIGHT = "weight";
        public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/preview_program";
        public static final String CONTENT_TYPE = "vnd.android.cursor.dir/preview_program";
        public static final Uri CONTENT_URI = Uri.parse("content://android.media.tv/preview_program");
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public interface ProgramColumns {
        public static final String COLUMN_AUDIO_LANGUAGE = "audio_language";
        public static final String COLUMN_CANONICAL_GENRE = "canonical_genre";
        public static final String COLUMN_CONTENT_RATING = "content_rating";
        public static final String COLUMN_EPISODE_DISPLAY_NUMBER = "episode_display_number";
        public static final String COLUMN_EPISODE_TITLE = "episode_title";
        public static final String COLUMN_INTERNAL_PROVIDER_DATA = "internal_provider_data";
        public static final String COLUMN_INTERNAL_PROVIDER_FLAG1 = "internal_provider_flag1";
        public static final String COLUMN_INTERNAL_PROVIDER_FLAG2 = "internal_provider_flag2";
        public static final String COLUMN_INTERNAL_PROVIDER_FLAG3 = "internal_provider_flag3";
        public static final String COLUMN_INTERNAL_PROVIDER_FLAG4 = "internal_provider_flag4";
        public static final String COLUMN_LONG_DESCRIPTION = "long_description";
        public static final String COLUMN_POSTER_ART_URI = "poster_art_uri";
        public static final String COLUMN_REVIEW_RATING = "review_rating";
        public static final String COLUMN_REVIEW_RATING_STYLE = "review_rating_style";
        public static final String COLUMN_SEARCHABLE = "searchable";
        public static final String COLUMN_SEASON_DISPLAY_NUMBER = "season_display_number";
        public static final String COLUMN_SEASON_TITLE = "season_title";
        public static final String COLUMN_SHORT_DESCRIPTION = "short_description";
        public static final String COLUMN_THUMBNAIL_URI = "thumbnail_uri";
        public static final String COLUMN_TITLE = "title";
        public static final String COLUMN_VERSION_NUMBER = "version_number";
        public static final String COLUMN_VIDEO_HEIGHT = "video_height";
        public static final String COLUMN_VIDEO_WIDTH = "video_width";
        public static final int REVIEW_RATING_STYLE_PERCENTAGE = 2;
        public static final int REVIEW_RATING_STYLE_STARS = 0;
        public static final int REVIEW_RATING_STYLE_THUMBS_UP_DOWN = 1;
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class Programs implements BaseTvColumns, ProgramColumns {
        public static final String COLUMN_BROADCAST_GENRE = "broadcast_genre";
        public static final String COLUMN_CHANNEL_ID = "channel_id";
        public static final String COLUMN_END_TIME_UTC_MILLIS = "end_time_utc_millis";

        @Deprecated
        public static final String COLUMN_EPISODE_NUMBER = "episode_number";
        public static final String COLUMN_RECORDING_PROHIBITED = "recording_prohibited";

        @Deprecated
        public static final String COLUMN_SEASON_NUMBER = "season_number";
        public static final String COLUMN_START_TIME_UTC_MILLIS = "start_time_utc_millis";
        public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/program";
        public static final String CONTENT_TYPE = "vnd.android.cursor.dir/program";
        public static final Uri CONTENT_URI = Uri.parse("content://android.media.tv/program");

        /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
        public static final class Genres {
            public static final String ANIMAL_WILDLIFE = "ANIMAL_WILDLIFE";
            public static final String ARTS = "ARTS";
            public static final HashSet<String> CANONICAL_GENRES;
            public static final String COMEDY = "COMEDY";
            public static final char COMMA = ',';
            public static final String DELIMITER = ",";
            public static final char DOUBLE_QUOTE = '\"';
            public static final String DRAMA = "DRAMA";
            public static final String EDUCATION = "EDUCATION";
            public static final String[] EMPTY_STRING_ARRAY;
            public static final String ENTERTAINMENT = "ENTERTAINMENT";
            public static final String FAMILY_KIDS = "FAMILY_KIDS";
            public static final String GAMING = "GAMING";
            public static final String LIFE_STYLE = "LIFE_STYLE";
            public static final String MOVIES = "MOVIES";
            public static final String MUSIC = "MUSIC";
            public static final String NEWS = "NEWS";
            public static final String PREMIER = "PREMIER";
            public static final String SHOPPING = "SHOPPING";
            public static final String SPORTS = "SPORTS";
            public static final String TECH_SCIENCE = "TECH_SCIENCE";
            public static final String TRAVEL = "TRAVEL";

            /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
            @Retention(RetentionPolicy.SOURCE)
            @RestrictTo({RestrictTo.Scope.LIBRARY})
            public @interface Genre {
            }

            static {
                HashSet<String> hashSet = new HashSet<>();
                CANONICAL_GENRES = hashSet;
                hashSet.add(FAMILY_KIDS);
                hashSet.add(SPORTS);
                hashSet.add(SHOPPING);
                hashSet.add(MOVIES);
                hashSet.add(COMEDY);
                hashSet.add(TRAVEL);
                hashSet.add(DRAMA);
                hashSet.add(EDUCATION);
                hashSet.add(ANIMAL_WILDLIFE);
                hashSet.add(NEWS);
                hashSet.add(GAMING);
                hashSet.add(ARTS);
                hashSet.add(ENTERTAINMENT);
                hashSet.add(LIFE_STYLE);
                hashSet.add(MUSIC);
                hashSet.add(PREMIER);
                hashSet.add(TECH_SCIENCE);
                EMPTY_STRING_ARRAY = new String[0];
            }

            /* JADX WARN: Removed duplicated region for block: B:25:0x005c  */
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct code enable 'Show inconsistent code' option in preferences
            */
            public static java.lang.String[] decode(@androidx.annotation.NonNull java.lang.String r9) {
                /*
                    boolean r0 = android.text.TextUtils.isEmpty(r9)
                    if (r0 == 0) goto L9
                    java.lang.String[] r9 = androidx.tvprovider.media.tv.TvContractCompat.Programs.Genres.EMPTY_STRING_ARRAY
                    return r9
                L9:
                    r0 = 44
                    int r1 = r9.indexOf(r0)
                    r2 = 34
                    r3 = -1
                    if (r1 != r3) goto L23
                    int r1 = r9.indexOf(r2)
                    if (r1 != r3) goto L23
                    java.lang.String r9 = r9.trim()
                    java.lang.String[] r9 = new java.lang.String[]{r9}
                    return r9
                L23:
                    java.lang.StringBuilder r1 = new java.lang.StringBuilder
                    r1.<init>()
                    java.util.ArrayList r3 = new java.util.ArrayList
                    r3.<init>()
                    int r4 = r9.length()
                    r5 = 0
                    r6 = 0
                    r7 = 0
                L34:
                    if (r6 >= r4) goto L63
                    char r8 = r9.charAt(r6)
                    if (r8 == r2) goto L58
                    if (r8 == r0) goto L3f
                    goto L5c
                L3f:
                    if (r7 != 0) goto L5c
                    java.lang.String r1 = r1.toString()
                    java.lang.String r1 = r1.trim()
                    int r8 = r1.length()
                    if (r8 <= 0) goto L52
                    r3.add(r1)
                L52:
                    java.lang.StringBuilder r1 = new java.lang.StringBuilder
                    r1.<init>()
                    goto L60
                L58:
                    if (r7 != 0) goto L5c
                    r7 = 1
                    goto L60
                L5c:
                    r1.append(r8)
                    r7 = 0
                L60:
                    int r6 = r6 + 1
                    goto L34
                L63:
                    java.lang.String r9 = r1.toString()
                    java.lang.String r9 = r9.trim()
                    int r0 = r9.length()
                    if (r0 <= 0) goto L74
                    r3.add(r9)
                L74:
                    int r9 = r3.size()
                    java.lang.String[] r9 = new java.lang.String[r9]
                    java.lang.Object[] r9 = r3.toArray(r9)
                    java.lang.String[] r9 = (java.lang.String[]) r9
                    return r9
                */
                throw new UnsupportedOperationException("Method not decompiled: androidx.tvprovider.media.tv.TvContractCompat.Programs.Genres.decode(java.lang.String):java.lang.String[]");
            }

            public static String encode(@NonNull String... strArr) {
                if (strArr == null) {
                    return null;
                }
                StringBuilder sb = new StringBuilder();
                int length = strArr.length;
                String str = "";
                int i = 0;
                while (i < length) {
                    String str2 = strArr[i];
                    sb.append(str);
                    sb.append(encodeToCsv(str2));
                    i++;
                    str = ",";
                }
                return sb.toString();
            }

            public static String encodeToCsv(String str) {
                StringBuilder sb = new StringBuilder();
                int length = str.length();
                for (int i = 0; i < length; i++) {
                    char cCharAt = str.charAt(i);
                    if (cCharAt == '\"') {
                        sb.append('\"');
                    } else if (cCharAt == ',') {
                        sb.append('\"');
                    }
                    sb.append(cCharAt);
                }
                return sb.toString();
            }

            public static boolean isCanonical(String str) {
                return CANONICAL_GENRES.contains(str);
            }
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class RecordedPrograms implements BaseTvColumns, ProgramColumns {
        public static final String COLUMN_BROADCAST_GENRE = "broadcast_genre";
        public static final String COLUMN_CHANNEL_ID = "channel_id";
        public static final String COLUMN_END_TIME_UTC_MILLIS = "end_time_utc_millis";
        public static final String COLUMN_INPUT_ID = "input_id";
        public static final String COLUMN_RECORDING_DATA_BYTES = "recording_data_bytes";
        public static final String COLUMN_RECORDING_DATA_URI = "recording_data_uri";
        public static final String COLUMN_RECORDING_DURATION_MILLIS = "recording_duration_millis";
        public static final String COLUMN_RECORDING_EXPIRE_TIME_UTC_MILLIS = "recording_expire_time_utc_millis";
        public static final String COLUMN_START_TIME_UTC_MILLIS = "start_time_utc_millis";
        public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/recorded_program";
        public static final String CONTENT_TYPE = "vnd.android.cursor.dir/recorded_program";
        public static final Uri CONTENT_URI = Uri.parse("content://android.media.tv/recorded_program");
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class WatchNextPrograms implements BaseTvColumns, ProgramColumns, PreviewProgramColumns {
        public static final String COLUMN_LAST_ENGAGEMENT_TIME_UTC_MILLIS = "last_engagement_time_utc_millis";
        public static final String COLUMN_WATCH_NEXT_TYPE = "watch_next_type";
        public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/watch_next_program";
        public static final String CONTENT_TYPE = "vnd.android.cursor.dir/watch_next_program";
        public static final Uri CONTENT_URI = Uri.parse("content://android.media.tv/watch_next_program");
        public static final int WATCH_NEXT_TYPE_CONTINUE = 0;
        public static final int WATCH_NEXT_TYPE_NEW = 2;
        public static final int WATCH_NEXT_TYPE_NEXT = 1;
        public static final int WATCH_NEXT_TYPE_WATCHLIST = 3;
    }

    public static Uri buildChannelLogoUri(long j) {
        return TvContract.buildChannelLogoUri(j);
    }

    public static Uri buildChannelUri(long j) {
        return TvContract.buildChannelUri(j);
    }

    public static Uri buildChannelUriForPassthroughInput(String str) {
        return TvContract.buildChannelUriForPassthroughInput(str);
    }

    public static Uri buildChannelsUriForInput(@Nullable String str) {
        return TvContract.buildChannelsUriForInput(str);
    }

    public static String buildInputId(ComponentName componentName) {
        return TvContract.buildInputId(componentName);
    }

    public static Uri buildPreviewProgramUri(long j) {
        return ContentUris.withAppendedId(PreviewPrograms.CONTENT_URI, j);
    }

    public static Uri buildPreviewProgramsUriForChannel(long j) {
        return PreviewPrograms.CONTENT_URI.buildUpon().appendQueryParameter("channel", String.valueOf(j)).build();
    }

    public static Uri buildProgramUri(long j) {
        return TvContract.buildProgramUri(j);
    }

    public static Uri buildProgramsUriForChannel(long j) {
        return TvContract.buildProgramsUriForChannel(j);
    }

    public static Uri buildRecordedProgramUri(long j) {
        return Build.VERSION.SDK_INT >= 24 ? TvContract.buildRecordedProgramUri(j) : ContentUris.withAppendedId(RecordedPrograms.CONTENT_URI, j);
    }

    public static Uri buildWatchNextProgramUri(long j) {
        return ContentUris.withAppendedId(WatchNextPrograms.CONTENT_URI, j);
    }

    public static boolean isChannelUri(Uri uri) {
        return Build.VERSION.SDK_INT >= 24 ? TvContract.isChannelUri(uri) : isChannelUriForTunerInput(uri) || isChannelUriForPassthroughInput(uri);
    }

    public static boolean isChannelUriForPassthroughInput(Uri uri) {
        return Build.VERSION.SDK_INT >= 24 ? TvContract.isChannelUriForPassthroughInput(uri) : isTvUri(uri) && isTwoSegmentUriStartingWith(uri, PATH_PASSTHROUGH);
    }

    public static boolean isChannelUriForTunerInput(Uri uri) {
        return Build.VERSION.SDK_INT >= 24 ? TvContract.isChannelUriForTunerInput(uri) : isTvUri(uri) && isTwoSegmentUriStartingWith(uri, "channel");
    }

    public static boolean isProgramUri(Uri uri) {
        return Build.VERSION.SDK_INT >= 24 ? TvContract.isProgramUri(uri) : isTvUri(uri) && isTwoSegmentUriStartingWith(uri, PATH_PROGRAM);
    }

    public static boolean isRecordedProgramUri(Uri uri) {
        return isTvUri(uri) && isTwoSegmentUriStartingWith(uri, PATH_RECORDED_PROGRAM);
    }

    public static boolean isTvUri(Uri uri) {
        return uri != null && "content".equals(uri.getScheme()) && AUTHORITY.equals(uri.getAuthority());
    }

    public static boolean isTwoSegmentUriStartingWith(Uri uri, String str) {
        List<String> pathSegments = uri.getPathSegments();
        return pathSegments.size() == 2 && str.equals(pathSegments.get(0));
    }

    public static void requestChannelBrowsable(Context context, long j) {
        if (Build.VERSION.SDK_INT >= 26) {
            TvContract.requestChannelBrowsable(context, j);
        }
    }

    public static Uri buildChannelLogoUri(Uri uri) {
        return TvContract.buildChannelLogoUri(uri);
    }

    public static Uri buildProgramsUriForChannel(Uri uri) {
        return TvContract.buildProgramsUriForChannel(uri);
    }

    public static Uri buildPreviewProgramsUriForChannel(Uri uri) {
        if (isChannelUriForTunerInput(uri)) {
            return buildPreviewProgramsUriForChannel(ContentUris.parseId(uri));
        }
        throw new IllegalArgumentException("Not a channel: " + uri);
    }

    public static Uri buildProgramsUriForChannel(long j, long j2, long j3) {
        return TvContract.buildProgramsUriForChannel(j, j2, j3);
    }

    public static Uri buildProgramsUriForChannel(Uri uri, long j, long j2) {
        return TvContract.buildProgramsUriForChannel(uri, j, j2);
    }
}
