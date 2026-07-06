package androidx.work;

import android.annotation.SuppressLint;
import androidx.annotation.NonNull;
import androidx.work.WorkInfo;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public final class WorkQuery {
    public final List<UUID> mIds;
    public final List<WorkInfo.State> mStates;
    public final List<String> mTags;
    public final List<String> mUniqueWorkNames;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class Builder {
        public List<UUID> mIds = new ArrayList();
        public List<String> mUniqueWorkNames = new ArrayList();
        public List<String> mTags = new ArrayList();
        public List<WorkInfo.State> mStates = new ArrayList();

        @NonNull
        @SuppressLint({"BuilderSetStyle"})
        public static Builder fromIds(@NonNull List<UUID> ids) {
            Builder builder = new Builder();
            builder.addIds(ids);
            return builder;
        }

        @NonNull
        @SuppressLint({"BuilderSetStyle"})
        public static Builder fromStates(@NonNull List<WorkInfo.State> states) {
            Builder builder = new Builder();
            builder.addStates(states);
            return builder;
        }

        @NonNull
        @SuppressLint({"BuilderSetStyle"})
        public static Builder fromTags(@NonNull List<String> tags) {
            Builder builder = new Builder();
            builder.addTags(tags);
            return builder;
        }

        @NonNull
        @SuppressLint({"BuilderSetStyle"})
        public static Builder fromUniqueWorkNames(@NonNull List<String> uniqueWorkNames) {
            Builder builder = new Builder();
            builder.addUniqueWorkNames(uniqueWorkNames);
            return builder;
        }

        @NonNull
        public Builder addIds(@NonNull List<UUID> ids) {
            this.mIds.addAll(ids);
            return this;
        }

        @NonNull
        public Builder addStates(@NonNull List<WorkInfo.State> states) {
            this.mStates.addAll(states);
            return this;
        }

        @NonNull
        public Builder addTags(@NonNull List<String> tags) {
            this.mTags.addAll(tags);
            return this;
        }

        @NonNull
        public Builder addUniqueWorkNames(@NonNull List<String> uniqueWorkNames) {
            this.mUniqueWorkNames.addAll(uniqueWorkNames);
            return this;
        }

        @NonNull
        public WorkQuery build() {
            if (this.mIds.isEmpty() && this.mUniqueWorkNames.isEmpty() && this.mTags.isEmpty() && this.mStates.isEmpty()) {
                throw new IllegalArgumentException("Must specify ids, uniqueNames, tags or states when building a WorkQuery");
            }
            return new WorkQuery(this);
        }
    }

    public WorkQuery(@NonNull Builder builder) {
        this.mIds = builder.mIds;
        this.mUniqueWorkNames = builder.mUniqueWorkNames;
        this.mTags = builder.mTags;
        this.mStates = builder.mStates;
    }

    @NonNull
    public List<UUID> getIds() {
        return this.mIds;
    }

    @NonNull
    public List<WorkInfo.State> getStates() {
        return this.mStates;
    }

    @NonNull
    public List<String> getTags() {
        return this.mTags;
    }

    @NonNull
    public List<String> getUniqueWorkNames() {
        return this.mUniqueWorkNames;
    }
}
