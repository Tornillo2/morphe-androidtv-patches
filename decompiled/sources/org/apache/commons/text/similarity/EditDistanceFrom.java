package org.apache.commons.text.similarity;

import org.apache.commons.lang3.Validate;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
public class EditDistanceFrom<R> {
    public final EditDistance<R> editDistance;
    public final CharSequence left;

    public EditDistanceFrom(EditDistance<R> editDistance, CharSequence charSequence) {
        Validate.isTrue(editDistance != null, "The edit distance may not be null.", new Object[0]);
        this.editDistance = editDistance;
        this.left = charSequence;
    }

    public R apply(CharSequence charSequence) {
        return this.editDistance.apply(this.left, charSequence);
    }

    public EditDistance<R> getEditDistance() {
        return this.editDistance;
    }

    public CharSequence getLeft() {
        return this.left;
    }
}
