package com.google.common.cache;

import com.google.common.annotations.GwtCompatible;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@GwtCompatible
public enum RemovalCause {
    EXPLICIT { // from class: com.google.common.cache.RemovalCause.1
        @Override // com.google.common.cache.RemovalCause
        public boolean wasEvicted() {
            return false;
        }
    },
    REPLACED { // from class: com.google.common.cache.RemovalCause.2
        @Override // com.google.common.cache.RemovalCause
        public boolean wasEvicted() {
            return false;
        }
    },
    COLLECTED { // from class: com.google.common.cache.RemovalCause.3
        @Override // com.google.common.cache.RemovalCause
        public boolean wasEvicted() {
            return true;
        }
    },
    EXPIRED { // from class: com.google.common.cache.RemovalCause.4
        @Override // com.google.common.cache.RemovalCause
        public boolean wasEvicted() {
            return true;
        }
    },
    SIZE { // from class: com.google.common.cache.RemovalCause.5
        @Override // com.google.common.cache.RemovalCause
        public boolean wasEvicted() {
            return true;
        }
    };

    public abstract boolean wasEvicted();

    RemovalCause(AnonymousClass1 anonymousClass1) {
    }
}
