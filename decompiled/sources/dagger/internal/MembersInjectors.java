package dagger.internal;

import dagger.MembersInjector;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class MembersInjectors {

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public enum NoOpMembersInjector implements MembersInjector<Object> {
        INSTANCE;

        public static /* synthetic */ NoOpMembersInjector[] $values() {
            return new NoOpMembersInjector[]{INSTANCE};
        }

        @Override // dagger.MembersInjector
        public void injectMembers(Object instance) {
            Preconditions.checkNotNull(instance, "Cannot inject members into a null reference");
        }
    }

    public static <T> MembersInjector<T> noOp() {
        return NoOpMembersInjector.INSTANCE;
    }
}
