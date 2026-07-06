package org.apache.commons.text.similarity;

import j$.util.Objects;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
public class LevenshteinResults {
    public final Integer deleteCount;
    public final Integer distance;
    public final Integer insertCount;
    public final Integer substituteCount;

    public LevenshteinResults(Integer num, Integer num2, Integer num3, Integer num4) {
        this.distance = num;
        this.insertCount = num2;
        this.deleteCount = num3;
        this.substituteCount = num4;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && getClass() == obj.getClass()) {
            LevenshteinResults levenshteinResults = (LevenshteinResults) obj;
            if (Objects.equals(this.distance, levenshteinResults.distance) && Objects.equals(this.insertCount, levenshteinResults.insertCount) && Objects.equals(this.deleteCount, levenshteinResults.deleteCount) && Objects.equals(this.substituteCount, levenshteinResults.substituteCount)) {
                return true;
            }
        }
        return false;
    }

    public Integer getDeleteCount() {
        return this.deleteCount;
    }

    public Integer getDistance() {
        return this.distance;
    }

    public Integer getInsertCount() {
        return this.insertCount;
    }

    public Integer getSubstituteCount() {
        return this.substituteCount;
    }

    public int hashCode() {
        return Objects.hash(this.distance, this.insertCount, this.deleteCount, this.substituteCount);
    }

    public String toString() {
        return "Distance: " + this.distance + ", Insert: " + this.insertCount + ", Delete: " + this.deleteCount + ", Substitute: " + this.substituteCount;
    }
}
