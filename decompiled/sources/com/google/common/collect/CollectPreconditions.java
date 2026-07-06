package com.google.common.collect;

import com.amazon.livingroom.mediapipelinebackend.MediaPipelineBackendEngineManager$$ExternalSyntheticOutline2;
import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Preconditions;
import com.google.errorprone.annotations.CanIgnoreReturnValue;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@GwtCompatible
public final class CollectPreconditions {
    public static void checkEntryNotNull(Object key, Object value) {
        if (key == null) {
            throw new NullPointerException(MediaPipelineBackendEngineManager$$ExternalSyntheticOutline2.m("null key in entry: null=", value));
        }
        if (value != null) {
            return;
        }
        throw new NullPointerException("null value in entry: " + key + "=null");
    }

    @CanIgnoreReturnValue
    public static int checkNonnegative(int value, String name) {
        if (value >= 0) {
            return value;
        }
        throw new IllegalArgumentException(name + " cannot be negative but was: " + value);
    }

    public static void checkPositive(int value, String name) {
        if (value > 0) {
            return;
        }
        throw new IllegalArgumentException(name + " must be positive but was: " + value);
    }

    public static void checkRemove(boolean canRemove) {
        Preconditions.checkState(canRemove, "no calls to next() since the last call to remove()");
    }

    @CanIgnoreReturnValue
    public static long checkNonnegative(long value, String name) {
        if (value >= 0) {
            return value;
        }
        throw new IllegalArgumentException(name + " cannot be negative but was: " + value);
    }
}
