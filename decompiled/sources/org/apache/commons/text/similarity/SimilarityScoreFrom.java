package org.apache.commons.text.similarity;

import org.apache.commons.lang3.Validate;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
public class SimilarityScoreFrom<R> {
    public final CharSequence left;
    public final SimilarityScore<R> similarityScore;

    public SimilarityScoreFrom(SimilarityScore<R> similarityScore, CharSequence charSequence) {
        Validate.isTrue(similarityScore != null, "The edit distance may not be null.", new Object[0]);
        this.similarityScore = similarityScore;
        this.left = charSequence;
    }

    public R apply(CharSequence charSequence) {
        return this.similarityScore.apply(this.left, charSequence);
    }

    public CharSequence getLeft() {
        return this.left;
    }

    public SimilarityScore<R> getSimilarityScore() {
        return this.similarityScore;
    }
}
