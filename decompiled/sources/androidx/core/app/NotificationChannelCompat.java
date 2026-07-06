package androidx.core.app;

import android.app.Notification;
import android.app.NotificationChannel;
import android.media.AudioAttributes;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public class NotificationChannelCompat {
    public static final String DEFAULT_CHANNEL_ID = "miscellaneous";
    public static final int DEFAULT_LIGHT_COLOR = 0;
    public static final boolean DEFAULT_SHOW_BADGE = true;
    public AudioAttributes mAudioAttributes;
    public boolean mBypassDnd;
    public boolean mCanBubble;
    public String mConversationId;
    public String mDescription;
    public String mGroupId;

    @NonNull
    public final String mId;
    public int mImportance;
    public boolean mImportantConversation;
    public int mLightColor;
    public boolean mLights;
    public int mLockscreenVisibility;
    public CharSequence mName;
    public String mParentId;
    public boolean mShowBadge;
    public Uri mSound;
    public boolean mVibrationEnabled;
    public long[] mVibrationPattern;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @RequiresApi(26)
    public static class Api26Impl {
        public static boolean canBypassDnd(NotificationChannel notificationChannel) {
            return notificationChannel.canBypassDnd();
        }

        public static boolean canShowBadge(NotificationChannel notificationChannel) {
            return notificationChannel.canShowBadge();
        }

        public static NotificationChannel createNotificationChannel(String str, CharSequence charSequence, int i) {
            return new NotificationChannel(str, charSequence, i);
        }

        public static void enableLights(NotificationChannel notificationChannel, boolean z) {
            notificationChannel.enableLights(z);
        }

        public static void enableVibration(NotificationChannel notificationChannel, boolean z) {
            notificationChannel.enableVibration(z);
        }

        public static AudioAttributes getAudioAttributes(NotificationChannel notificationChannel) {
            return notificationChannel.getAudioAttributes();
        }

        public static String getDescription(NotificationChannel notificationChannel) {
            return notificationChannel.getDescription();
        }

        public static String getGroup(NotificationChannel notificationChannel) {
            return notificationChannel.getGroup();
        }

        public static String getId(NotificationChannel notificationChannel) {
            return notificationChannel.getId();
        }

        public static int getImportance(NotificationChannel notificationChannel) {
            return notificationChannel.getImportance();
        }

        public static int getLightColor(NotificationChannel notificationChannel) {
            return notificationChannel.getLightColor();
        }

        public static int getLockscreenVisibility(NotificationChannel notificationChannel) {
            return notificationChannel.getLockscreenVisibility();
        }

        public static CharSequence getName(NotificationChannel notificationChannel) {
            return notificationChannel.getName();
        }

        public static Uri getSound(NotificationChannel notificationChannel) {
            return notificationChannel.getSound();
        }

        public static long[] getVibrationPattern(NotificationChannel notificationChannel) {
            return notificationChannel.getVibrationPattern();
        }

        public static void setDescription(NotificationChannel notificationChannel, String str) {
            notificationChannel.setDescription(str);
        }

        public static void setGroup(NotificationChannel notificationChannel, String str) {
            notificationChannel.setGroup(str);
        }

        public static void setLightColor(NotificationChannel notificationChannel, int i) {
            notificationChannel.setLightColor(i);
        }

        public static void setShowBadge(NotificationChannel notificationChannel, boolean z) {
            notificationChannel.setShowBadge(z);
        }

        public static void setSound(NotificationChannel notificationChannel, Uri uri, AudioAttributes audioAttributes) {
            notificationChannel.setSound(uri, audioAttributes);
        }

        public static void setVibrationPattern(NotificationChannel notificationChannel, long[] jArr) {
            notificationChannel.setVibrationPattern(jArr);
        }

        public static boolean shouldShowLights(NotificationChannel notificationChannel) {
            return notificationChannel.shouldShowLights();
        }

        public static boolean shouldVibrate(NotificationChannel notificationChannel) {
            return notificationChannel.shouldVibrate();
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @RequiresApi(29)
    public static class Api29Impl {
        public static boolean canBubble(NotificationChannel notificationChannel) {
            return notificationChannel.canBubble();
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @RequiresApi(30)
    public static class Api30Impl {
        public static String getConversationId(NotificationChannel notificationChannel) {
            return notificationChannel.getConversationId();
        }

        public static String getParentChannelId(NotificationChannel notificationChannel) {
            return notificationChannel.getParentChannelId();
        }

        public static boolean isImportantConversation(NotificationChannel notificationChannel) {
            return notificationChannel.isImportantConversation();
        }

        public static void setConversationId(NotificationChannel notificationChannel, String str, String str2) {
            notificationChannel.setConversationId(str, str2);
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static class Builder {
        public final NotificationChannelCompat mChannel;

        public Builder(@NonNull String str, int i) {
            this.mChannel = new NotificationChannelCompat(str, i);
        }

        @NonNull
        public NotificationChannelCompat build() {
            return this.mChannel;
        }

        @NonNull
        public Builder setConversationId(@NonNull String str, @NonNull String str2) {
            if (Build.VERSION.SDK_INT >= 30) {
                NotificationChannelCompat notificationChannelCompat = this.mChannel;
                notificationChannelCompat.mParentId = str;
                notificationChannelCompat.mConversationId = str2;
            }
            return this;
        }

        @NonNull
        public Builder setDescription(@Nullable String str) {
            this.mChannel.mDescription = str;
            return this;
        }

        @NonNull
        public Builder setGroup(@Nullable String str) {
            this.mChannel.mGroupId = str;
            return this;
        }

        @NonNull
        public Builder setImportance(int i) {
            this.mChannel.mImportance = i;
            return this;
        }

        @NonNull
        public Builder setLightColor(int i) {
            this.mChannel.mLightColor = i;
            return this;
        }

        @NonNull
        public Builder setLightsEnabled(boolean z) {
            this.mChannel.mLights = z;
            return this;
        }

        @NonNull
        public Builder setName(@Nullable CharSequence charSequence) {
            this.mChannel.mName = charSequence;
            return this;
        }

        @NonNull
        public Builder setShowBadge(boolean z) {
            this.mChannel.mShowBadge = z;
            return this;
        }

        @NonNull
        public Builder setSound(@Nullable Uri uri, @Nullable AudioAttributes audioAttributes) {
            NotificationChannelCompat notificationChannelCompat = this.mChannel;
            notificationChannelCompat.mSound = uri;
            notificationChannelCompat.mAudioAttributes = audioAttributes;
            return this;
        }

        @NonNull
        public Builder setVibrationEnabled(boolean z) {
            this.mChannel.mVibrationEnabled = z;
            return this;
        }

        @NonNull
        public Builder setVibrationPattern(@Nullable long[] jArr) {
            NotificationChannelCompat notificationChannelCompat = this.mChannel;
            notificationChannelCompat.mVibrationEnabled = jArr != null && jArr.length > 0;
            notificationChannelCompat.mVibrationPattern = jArr;
            return this;
        }
    }

    public NotificationChannelCompat(@NonNull String str, int i) {
        this.mShowBadge = true;
        this.mSound = Settings.System.DEFAULT_NOTIFICATION_URI;
        this.mLightColor = 0;
        str.getClass();
        this.mId = str;
        this.mImportance = i;
        this.mAudioAttributes = Notification.AUDIO_ATTRIBUTES_DEFAULT;
    }

    public boolean canBubble() {
        return this.mCanBubble;
    }

    public boolean canBypassDnd() {
        return this.mBypassDnd;
    }

    public boolean canShowBadge() {
        return this.mShowBadge;
    }

    @Nullable
    public AudioAttributes getAudioAttributes() {
        return this.mAudioAttributes;
    }

    @Nullable
    public String getConversationId() {
        return this.mConversationId;
    }

    @Nullable
    public String getDescription() {
        return this.mDescription;
    }

    @Nullable
    public String getGroup() {
        return this.mGroupId;
    }

    @NonNull
    public String getId() {
        return this.mId;
    }

    public int getImportance() {
        return this.mImportance;
    }

    public int getLightColor() {
        return this.mLightColor;
    }

    public int getLockscreenVisibility() {
        return this.mLockscreenVisibility;
    }

    @Nullable
    public CharSequence getName() {
        return this.mName;
    }

    public NotificationChannel getNotificationChannel() {
        String str;
        String str2;
        int i = Build.VERSION.SDK_INT;
        if (i < 26) {
            return null;
        }
        NotificationChannel notificationChannelCreateNotificationChannel = Api26Impl.createNotificationChannel(this.mId, this.mName, this.mImportance);
        Api26Impl.setDescription(notificationChannelCreateNotificationChannel, this.mDescription);
        Api26Impl.setGroup(notificationChannelCreateNotificationChannel, this.mGroupId);
        Api26Impl.setShowBadge(notificationChannelCreateNotificationChannel, this.mShowBadge);
        Api26Impl.setSound(notificationChannelCreateNotificationChannel, this.mSound, this.mAudioAttributes);
        Api26Impl.enableLights(notificationChannelCreateNotificationChannel, this.mLights);
        Api26Impl.setLightColor(notificationChannelCreateNotificationChannel, this.mLightColor);
        Api26Impl.setVibrationPattern(notificationChannelCreateNotificationChannel, this.mVibrationPattern);
        Api26Impl.enableVibration(notificationChannelCreateNotificationChannel, this.mVibrationEnabled);
        if (i >= 30 && (str = this.mParentId) != null && (str2 = this.mConversationId) != null) {
            Api30Impl.setConversationId(notificationChannelCreateNotificationChannel, str, str2);
        }
        return notificationChannelCreateNotificationChannel;
    }

    @Nullable
    public String getParentChannelId() {
        return this.mParentId;
    }

    @Nullable
    public Uri getSound() {
        return this.mSound;
    }

    @Nullable
    public long[] getVibrationPattern() {
        return this.mVibrationPattern;
    }

    public boolean isImportantConversation() {
        return this.mImportantConversation;
    }

    public boolean shouldShowLights() {
        return this.mLights;
    }

    public boolean shouldVibrate() {
        return this.mVibrationEnabled;
    }

    @NonNull
    public Builder toBuilder() {
        Builder builder = new Builder(this.mId, this.mImportance);
        CharSequence charSequence = this.mName;
        NotificationChannelCompat notificationChannelCompat = builder.mChannel;
        notificationChannelCompat.mName = charSequence;
        notificationChannelCompat.mDescription = this.mDescription;
        notificationChannelCompat.mGroupId = this.mGroupId;
        notificationChannelCompat.mShowBadge = this.mShowBadge;
        builder.setSound(this.mSound, this.mAudioAttributes);
        boolean z = this.mLights;
        NotificationChannelCompat notificationChannelCompat2 = builder.mChannel;
        notificationChannelCompat2.mLights = z;
        notificationChannelCompat2.mLightColor = this.mLightColor;
        notificationChannelCompat2.mVibrationEnabled = this.mVibrationEnabled;
        builder.setVibrationPattern(this.mVibrationPattern);
        builder.setConversationId(this.mParentId, this.mConversationId);
        return builder;
    }

    @RequiresApi(26)
    public NotificationChannelCompat(@NonNull NotificationChannel notificationChannel) {
        this(Api26Impl.getId(notificationChannel), Api26Impl.getImportance(notificationChannel));
        this.mName = Api26Impl.getName(notificationChannel);
        this.mDescription = Api26Impl.getDescription(notificationChannel);
        this.mGroupId = Api26Impl.getGroup(notificationChannel);
        this.mShowBadge = Api26Impl.canShowBadge(notificationChannel);
        this.mSound = Api26Impl.getSound(notificationChannel);
        this.mAudioAttributes = Api26Impl.getAudioAttributes(notificationChannel);
        this.mLights = Api26Impl.shouldShowLights(notificationChannel);
        this.mLightColor = Api26Impl.getLightColor(notificationChannel);
        this.mVibrationEnabled = Api26Impl.shouldVibrate(notificationChannel);
        this.mVibrationPattern = Api26Impl.getVibrationPattern(notificationChannel);
        int i = Build.VERSION.SDK_INT;
        if (i >= 30) {
            this.mParentId = Api30Impl.getParentChannelId(notificationChannel);
            this.mConversationId = Api30Impl.getConversationId(notificationChannel);
        }
        this.mBypassDnd = Api26Impl.canBypassDnd(notificationChannel);
        this.mLockscreenVisibility = Api26Impl.getLockscreenVisibility(notificationChannel);
        if (i >= 29) {
            this.mCanBubble = Api29Impl.canBubble(notificationChannel);
        }
        if (i >= 30) {
            this.mImportantConversation = Api30Impl.isImportantConversation(notificationChannel);
        }
    }
}
