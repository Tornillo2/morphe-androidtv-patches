package com.android.billingclient.api;

import androidx.annotation.NonNull;
import j$.util.DesugarCollections;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public final class InAppMessageParams {
    public final ArrayList zza;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class Builder {
        public final Set zza = new HashSet();

        @NonNull
        public Builder addAllInAppMessageCategoriesToShow() {
            this.zza.add(2);
            return this;
        }

        @NonNull
        public Builder addInAppMessageCategoryToShow(int i) {
            this.zza.add(Integer.valueOf(i));
            return this;
        }

        @NonNull
        public InAppMessageParams build() {
            return new InAppMessageParams(this.zza, null);
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @Retention(RetentionPolicy.SOURCE)
    public @interface InAppMessageCategoryId {
        public static final int TRANSACTIONAL = 2;
        public static final int UNKNOWN_IN_APP_MESSAGE_CATEGORY_ID = 0;

        @zzl
        public static final int UPSELL = 4;
    }

    public /* synthetic */ InAppMessageParams(Set set, zzcw zzcwVar) {
        this.zza = new ArrayList(DesugarCollections.unmodifiableList(new ArrayList(set)));
    }

    @NonNull
    public static Builder newBuilder() {
        return new Builder();
    }

    public final ArrayList zza() {
        return this.zza;
    }
}
