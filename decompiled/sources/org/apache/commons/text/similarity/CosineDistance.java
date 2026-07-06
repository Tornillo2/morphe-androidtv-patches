package org.apache.commons.text.similarity;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
public class CosineDistance implements EditDistance<Double> {
    public final Tokenizer<CharSequence> tokenizer = new RegexTokenizer();
    public final CosineSimilarity cosineSimilarity = new CosineSimilarity();

    @Override // org.apache.commons.text.similarity.EditDistance, org.apache.commons.text.similarity.SimilarityScore
    public Double apply(CharSequence charSequence, CharSequence charSequence2) {
        CharSequence[] charSequenceArr = this.tokenizer.tokenize(charSequence);
        CharSequence[] charSequenceArr2 = this.tokenizer.tokenize(charSequence2);
        return Double.valueOf(1.0d - this.cosineSimilarity.cosineSimilarity(Counter.of(charSequenceArr), Counter.of(charSequenceArr2)).doubleValue());
    }
}
