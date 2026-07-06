package androidx.core.graphics;

import android.graphics.PointF;
import androidx.annotation.NonNull;
import androidx.core.util.Preconditions;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public final class PathSegment {
    public final PointF mEnd;
    public final float mEndFraction;
    public final PointF mStart;
    public final float mStartFraction;

    public PathSegment(@NonNull PointF pointF, float f, @NonNull PointF pointF2, float f2) {
        Preconditions.checkNotNull(pointF, "start == null");
        this.mStart = pointF;
        this.mStartFraction = f;
        Preconditions.checkNotNull(pointF2, "end == null");
        this.mEnd = pointF2;
        this.mEndFraction = f2;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof PathSegment)) {
            return false;
        }
        PathSegment pathSegment = (PathSegment) obj;
        return Float.compare(this.mStartFraction, pathSegment.mStartFraction) == 0 && Float.compare(this.mEndFraction, pathSegment.mEndFraction) == 0 && this.mStart.equals(pathSegment.mStart) && this.mEnd.equals(pathSegment.mEnd);
    }

    @NonNull
    public PointF getEnd() {
        return this.mEnd;
    }

    public float getEndFraction() {
        return this.mEndFraction;
    }

    @NonNull
    public PointF getStart() {
        return this.mStart;
    }

    public float getStartFraction() {
        return this.mStartFraction;
    }

    public int hashCode() {
        int iHashCode = this.mStart.hashCode() * 31;
        float f = this.mStartFraction;
        int iHashCode2 = (this.mEnd.hashCode() + ((iHashCode + (f != 0.0f ? Float.floatToIntBits(f) : 0)) * 31)) * 31;
        float f2 = this.mEndFraction;
        return iHashCode2 + (f2 != 0.0f ? Float.floatToIntBits(f2) : 0);
    }

    public String toString() {
        return "PathSegment{start=" + this.mStart + ", startFraction=" + this.mStartFraction + ", end=" + this.mEnd + ", endFraction=" + this.mEndFraction + '}';
    }
}
