package com.google.android.exoplayer2.util;

import android.util.SparseBooleanArray;
import androidx.annotation.Nullable;
import com.google.errorprone.annotations.CanIgnoreReturnValue;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class FlagSet {
    public final SparseBooleanArray flags;

    public boolean contains(int i) {
        return this.flags.get(i);
    }

    public boolean containsAny(int... iArr) {
        for (int i : iArr) {
            if (this.flags.get(i)) {
                return true;
            }
        }
        return false;
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof FlagSet)) {
            return false;
        }
        FlagSet flagSet = (FlagSet) obj;
        if (Util.SDK_INT >= 24) {
            return this.flags.equals(flagSet.flags);
        }
        if (this.flags.size() != flagSet.flags.size()) {
            return false;
        }
        for (int i = 0; i < this.flags.size(); i++) {
            if (get(i) != flagSet.get(i)) {
                return false;
            }
        }
        return true;
    }

    public int get(int i) {
        Assertions.checkIndex(i, 0, this.flags.size());
        return this.flags.keyAt(i);
    }

    public int hashCode() {
        if (Util.SDK_INT >= 24) {
            return this.flags.hashCode();
        }
        int size = this.flags.size();
        for (int i = 0; i < this.flags.size(); i++) {
            size = (size * 31) + get(i);
        }
        return size;
    }

    public int size() {
        return this.flags.size();
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class Builder {
        public boolean buildCalled;
        public final SparseBooleanArray flags = new SparseBooleanArray();

        @CanIgnoreReturnValue
        public Builder add(int i) {
            Assertions.checkState(!this.buildCalled);
            this.flags.append(i, true);
            return this;
        }

        @CanIgnoreReturnValue
        public Builder addAll(int... iArr) {
            for (int i : iArr) {
                add(i);
            }
            return this;
        }

        @CanIgnoreReturnValue
        public Builder addIf(int i, boolean z) {
            if (z) {
                add(i);
            }
            return this;
        }

        public FlagSet build() {
            Assertions.checkState(!this.buildCalled);
            this.buildCalled = true;
            return new FlagSet(this.flags);
        }

        @CanIgnoreReturnValue
        public Builder remove(int i) {
            Assertions.checkState(!this.buildCalled);
            this.flags.delete(i);
            return this;
        }

        @CanIgnoreReturnValue
        public Builder removeAll(int... iArr) {
            for (int i : iArr) {
                remove(i);
            }
            return this;
        }

        @CanIgnoreReturnValue
        public Builder removeIf(int i, boolean z) {
            if (z) {
                remove(i);
            }
            return this;
        }

        @CanIgnoreReturnValue
        public Builder addAll(FlagSet flagSet) {
            for (int i = 0; i < flagSet.flags.size(); i++) {
                add(flagSet.get(i));
            }
            return this;
        }
    }

    public FlagSet(SparseBooleanArray sparseBooleanArray) {
        this.flags = sparseBooleanArray;
    }
}
