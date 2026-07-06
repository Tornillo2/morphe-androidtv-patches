package androidx.core.content;

import android.content.UriMatcher;
import android.net.Uri;
import androidx.annotation.NonNull;
import androidx.core.util.Predicate;
import androidx.core.util.Predicate$$ExternalSyntheticLambda4;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public class UriMatcherCompat {
    /* JADX INFO: renamed from: $r8$lambda$tJdd-VixAtHRJol3Fq4O2TOk7Zs, reason: not valid java name */
    public static /* synthetic */ boolean m43$r8$lambda$tJddVixAtHRJol3Fq4O2TOk7Zs(UriMatcher uriMatcher, Uri uri) {
        return uriMatcher.match(uri) != -1;
    }

    @NonNull
    public static Predicate<Uri> asPredicate(@NonNull final UriMatcher uriMatcher) {
        return new Predicate() { // from class: androidx.core.content.UriMatcherCompat$$ExternalSyntheticLambda0
            @Override // androidx.core.util.Predicate
            public /* synthetic */ Predicate and(Predicate predicate) {
                return Predicate.CC.$default$and(this, predicate);
            }

            @Override // androidx.core.util.Predicate
            public Predicate negate() {
                return new Predicate$$ExternalSyntheticLambda4(this);
            }

            @Override // androidx.core.util.Predicate
            public /* synthetic */ Predicate or(Predicate predicate) {
                return Predicate.CC.$default$or(this, predicate);
            }

            @Override // androidx.core.util.Predicate
            public final boolean test(Object obj) {
                return UriMatcherCompat.m43$r8$lambda$tJddVixAtHRJol3Fq4O2TOk7Zs(uriMatcher, (Uri) obj);
            }
        };
    }
}
