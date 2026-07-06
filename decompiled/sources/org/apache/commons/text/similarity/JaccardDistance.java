package org.apache.commons.text.similarity;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
public class JaccardDistance implements EditDistance<Double> {
    public final JaccardSimilarity jaccardSimilarity = new JaccardSimilarity();

    @Override // org.apache.commons.text.similarity.EditDistance, org.apache.commons.text.similarity.SimilarityScore
    public Double apply(CharSequence charSequence, CharSequence charSequence2) {
        if (charSequence == null || charSequence2 == null) {
            throw new IllegalArgumentException("Input cannot be null");
        }
        return Double.valueOf(1.0d - this.jaccardSimilarity.apply(charSequence, charSequence2).doubleValue());
    }
}
