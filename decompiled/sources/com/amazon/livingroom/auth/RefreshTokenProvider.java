package com.amazon.livingroom.auth;

import android.text.TextUtils;
import androidx.annotation.Nullable;
import com.amazon.ignitionshared.filesystem.LocalStorage;
import com.amazon.livingroom.di.ApplicationScope;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicReference;
import javax.inject.Inject;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@ApplicationScope
public class RefreshTokenProvider {
    public final LocalStorage localStorage;
    public RefreshTokenParser parser;
    public AtomicReference<String> data = new AtomicReference<>();
    public final List<RefreshTokenChangeListener> changeListeners = new CopyOnWriteArrayList();

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public interface RefreshTokenChangeListener {
        void onChange();
    }

    @Inject
    public RefreshTokenProvider(LocalStorage localStorage, RefreshTokenParser refreshTokenParser) {
        this.parser = refreshTokenParser;
        this.localStorage = localStorage;
        localStorage.addWriteListener(new LocalStorage.WriteListener() { // from class: com.amazon.livingroom.auth.RefreshTokenProvider$$ExternalSyntheticLambda0
            @Override // com.amazon.ignitionshared.filesystem.LocalStorage.WriteListener
            public final void onWrite() {
                this.f$0.updatedRefreshTokenAndNotifyChangeListeners();
            }
        });
        this.data.set(readData());
    }

    public void addRefreshTokenChangeListener(RefreshTokenChangeListener refreshTokenChangeListener) {
        this.changeListeners.add(refreshTokenChangeListener);
    }

    @Nullable
    public String getRefreshToken() {
        return this.data.get();
    }

    public boolean isEmpty() {
        return TextUtils.isEmpty(this.data.get());
    }

    @Nullable
    public final String readData() {
        byte[] bArr = this.localStorage.read();
        if (bArr == null) {
            return null;
        }
        return this.parser.parse(bArr);
    }

    public final void updatedRefreshTokenAndNotifyChangeListeners() {
        String data = readData();
        if (TextUtils.equals(this.data.get(), data)) {
            return;
        }
        this.data.set(data);
        Iterator<RefreshTokenChangeListener> it = this.changeListeners.iterator();
        while (it.hasNext()) {
            it.next().onChange();
        }
    }
}
