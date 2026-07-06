package org.apache.commons.text.similarity;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
public interface EditDistance<R> extends SimilarityScore<R> {
    @Override // org.apache.commons.text.similarity.SimilarityScore
    R apply(CharSequence charSequence, CharSequence charSequence2);
}
