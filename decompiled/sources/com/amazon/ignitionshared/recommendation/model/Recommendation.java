package com.amazon.ignitionshared.recommendation.model;

import androidx.media3.common.DrmInitData$SchemeData$$ExternalSyntheticOutline0;
import androidx.media3.common.TrackGroup$$ExternalSyntheticOutline0;
import java.util.Arrays;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public final class Recommendation {

    @NotNull
    public final String actionData;

    @Nullable
    public final String backgroundImageUrl;

    @NotNull
    public final String cardImageUrl;

    @Nullable
    public final String description;

    @NotNull
    public final String group;
    public final int groupPosition;
    public final int hashedId;

    @NotNull
    public final String id;
    public final int position;

    @NotNull
    public final String title;

    public Recommendation(@NotNull String title, @Nullable String str, @NotNull String group, @Nullable String str2, @NotNull String cardImageUrl, @NotNull String actionData, int i, int i2) {
        Intrinsics.checkNotNullParameter(title, "title");
        Intrinsics.checkNotNullParameter(group, "group");
        Intrinsics.checkNotNullParameter(cardImageUrl, "cardImageUrl");
        Intrinsics.checkNotNullParameter(actionData, "actionData");
        this.title = title;
        this.description = str;
        this.group = group;
        this.backgroundImageUrl = str2;
        this.cardImageUrl = cardImageUrl;
        this.actionData = actionData;
        this.position = i;
        this.groupPosition = i2;
        String str3 = String.format("%s-%s", Arrays.copyOf(new Object[]{group, actionData}, 2));
        this.id = str3;
        this.hashedId = str3.hashCode();
    }

    public static /* synthetic */ Recommendation copy$default(Recommendation recommendation, String str, String str2, String str3, String str4, String str5, String str6, int i, int i2, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            str = recommendation.title;
        }
        if ((i3 & 2) != 0) {
            str2 = recommendation.description;
        }
        if ((i3 & 4) != 0) {
            str3 = recommendation.group;
        }
        if ((i3 & 8) != 0) {
            str4 = recommendation.backgroundImageUrl;
        }
        if ((i3 & 16) != 0) {
            str5 = recommendation.cardImageUrl;
        }
        if ((i3 & 32) != 0) {
            str6 = recommendation.actionData;
        }
        if ((i3 & 64) != 0) {
            i = recommendation.position;
        }
        if ((i3 & 128) != 0) {
            i2 = recommendation.groupPosition;
        }
        int i4 = i;
        int i5 = i2;
        String str7 = str5;
        String str8 = str6;
        return recommendation.copy(str, str2, str3, str4, str7, str8, i4, i5);
    }

    @NotNull
    public final String component1() {
        return this.title;
    }

    @Nullable
    public final String component2() {
        return this.description;
    }

    @NotNull
    public final String component3() {
        return this.group;
    }

    @Nullable
    public final String component4() {
        return this.backgroundImageUrl;
    }

    @NotNull
    public final String component5() {
        return this.cardImageUrl;
    }

    @NotNull
    public final String component6() {
        return this.actionData;
    }

    public final int component7() {
        return this.position;
    }

    public final int component8() {
        return this.groupPosition;
    }

    @NotNull
    public final Recommendation copy(@NotNull String title, @Nullable String str, @NotNull String group, @Nullable String str2, @NotNull String cardImageUrl, @NotNull String actionData, int i, int i2) {
        Intrinsics.checkNotNullParameter(title, "title");
        Intrinsics.checkNotNullParameter(group, "group");
        Intrinsics.checkNotNullParameter(cardImageUrl, "cardImageUrl");
        Intrinsics.checkNotNullParameter(actionData, "actionData");
        return new Recommendation(title, str, group, str2, cardImageUrl, actionData, i, i2);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Recommendation)) {
            return false;
        }
        Recommendation recommendation = (Recommendation) obj;
        return Intrinsics.areEqual(this.title, recommendation.title) && Intrinsics.areEqual(this.description, recommendation.description) && Intrinsics.areEqual(this.group, recommendation.group) && Intrinsics.areEqual(this.backgroundImageUrl, recommendation.backgroundImageUrl) && Intrinsics.areEqual(this.cardImageUrl, recommendation.cardImageUrl) && Intrinsics.areEqual(this.actionData, recommendation.actionData) && this.position == recommendation.position && this.groupPosition == recommendation.groupPosition;
    }

    @NotNull
    public final String getActionData() {
        return this.actionData;
    }

    @Nullable
    public final String getBackgroundImageUrl() {
        return this.backgroundImageUrl;
    }

    @NotNull
    public final String getCardImageUrl() {
        return this.cardImageUrl;
    }

    @Nullable
    public final String getDescription() {
        return this.description;
    }

    @NotNull
    public final String getGroup() {
        return this.group;
    }

    public final int getGroupPosition() {
        return this.groupPosition;
    }

    public final int getHashedId() {
        return this.hashedId;
    }

    @NotNull
    public final String getId() {
        return this.id;
    }

    public final int getPosition() {
        return this.position;
    }

    @NotNull
    public final String getTitle() {
        return this.title;
    }

    public int hashCode() {
        int iHashCode = this.title.hashCode() * 31;
        String str = this.description;
        int iM = DrmInitData$SchemeData$$ExternalSyntheticOutline0.m(this.group, (iHashCode + (str == null ? 0 : str.hashCode())) * 31, 31);
        String str2 = this.backgroundImageUrl;
        return ((DrmInitData$SchemeData$$ExternalSyntheticOutline0.m(this.actionData, DrmInitData$SchemeData$$ExternalSyntheticOutline0.m(this.cardImageUrl, (iM + (str2 != null ? str2.hashCode() : 0)) * 31, 31), 31) + this.position) * 31) + this.groupPosition;
    }

    @NotNull
    public String toString() {
        String str = this.title;
        String str2 = this.description;
        String str3 = this.group;
        String str4 = this.backgroundImageUrl;
        String str5 = this.cardImageUrl;
        String str6 = this.actionData;
        int i = this.position;
        int i2 = this.groupPosition;
        StringBuilder sbM = TrackGroup$$ExternalSyntheticOutline0.m("Recommendation(title=", str, ", description=", str2, ", group=");
        sbM.append(str3);
        sbM.append(", backgroundImageUrl=");
        sbM.append(str4);
        sbM.append(", cardImageUrl=");
        sbM.append(str5);
        sbM.append(", actionData=");
        sbM.append(str6);
        sbM.append(", position=");
        sbM.append(i);
        sbM.append(", groupPosition=");
        sbM.append(i2);
        sbM.append(")");
        return sbM.toString();
    }
}
