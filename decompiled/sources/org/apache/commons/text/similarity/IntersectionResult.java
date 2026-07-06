package org.apache.commons.text.similarity;

import android.support.v4.media.MediaBrowserCompat$MediaBrowserImplBase$$ExternalSyntheticOutline0;
import j$.util.Objects;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
public class IntersectionResult {
    public final int intersection;
    public final int sizeA;
    public final int sizeB;

    public IntersectionResult(int i, int i2, int i3) {
        if (i < 0) {
            throw new IllegalArgumentException(MediaBrowserCompat$MediaBrowserImplBase$$ExternalSyntheticOutline0.m("Set size |A| is not positive: ", i));
        }
        if (i2 < 0) {
            throw new IllegalArgumentException(MediaBrowserCompat$MediaBrowserImplBase$$ExternalSyntheticOutline0.m("Set size |B| is not positive: ", i2));
        }
        if (i3 < 0 || i3 > Math.min(i, i2)) {
            throw new IllegalArgumentException(MediaBrowserCompat$MediaBrowserImplBase$$ExternalSyntheticOutline0.m("Invalid intersection of |A| and |B|: ", i3));
        }
        this.sizeA = i;
        this.sizeB = i2;
        this.intersection = i3;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && getClass() == obj.getClass()) {
            IntersectionResult intersectionResult = (IntersectionResult) obj;
            if (this.sizeA == intersectionResult.sizeA && this.sizeB == intersectionResult.sizeB && this.intersection == intersectionResult.intersection) {
                return true;
            }
        }
        return false;
    }

    public int getIntersection() {
        return this.intersection;
    }

    public int getSizeA() {
        return this.sizeA;
    }

    public int getSizeB() {
        return this.sizeB;
    }

    public int hashCode() {
        return Objects.hash(Integer.valueOf(this.sizeA), Integer.valueOf(this.sizeB), Integer.valueOf(this.intersection));
    }

    public String toString() {
        return "Size A: " + this.sizeA + ", Size B: " + this.sizeB + ", Intersection: " + this.intersection;
    }
}
