package androidx.work;

import android.net.Uri;
import androidx.annotation.NonNull;
import androidx.annotation.RestrictTo;
import java.util.HashSet;
import java.util.Set;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
public final class ContentUriTriggers {
    public final Set<Trigger> mTriggers = new HashSet();

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class Trigger {
        public final boolean mTriggerForDescendants;

        @NonNull
        public final Uri mUri;

        public Trigger(@NonNull Uri uri, boolean triggerForDescendants) {
            this.mUri = uri;
            this.mTriggerForDescendants = triggerForDescendants;
        }

        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o != null && Trigger.class == o.getClass()) {
                Trigger trigger = (Trigger) o;
                if (this.mTriggerForDescendants == trigger.mTriggerForDescendants && this.mUri.equals(trigger.mUri)) {
                    return true;
                }
            }
            return false;
        }

        @NonNull
        public Uri getUri() {
            return this.mUri;
        }

        public int hashCode() {
            return (this.mUri.hashCode() * 31) + (this.mTriggerForDescendants ? 1 : 0);
        }

        public boolean shouldTriggerForDescendants() {
            return this.mTriggerForDescendants;
        }
    }

    public void add(@NonNull Uri uri, boolean triggerForDescendants) {
        this.mTriggers.add(new Trigger(uri, triggerForDescendants));
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || ContentUriTriggers.class != o.getClass()) {
            return false;
        }
        return this.mTriggers.equals(((ContentUriTriggers) o).mTriggers);
    }

    @NonNull
    public Set<Trigger> getTriggers() {
        return this.mTriggers;
    }

    public int hashCode() {
        return this.mTriggers.hashCode();
    }

    public int size() {
        return this.mTriggers.size();
    }
}
