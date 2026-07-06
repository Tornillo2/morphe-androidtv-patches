package androidx.lifecycle;

import androidx.annotation.CallSuper;
import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.arch.core.internal.SafeIterableMap;
import java.util.Iterator;
import java.util.Map;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public class MediatorLiveData<T> extends MutableLiveData<T> {
    public SafeIterableMap<LiveData<?>, Source<?>> mSources;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static class Source<V> implements Observer<V> {
        public final LiveData<V> mLiveData;
        public final Observer<? super V> mObserver;
        public int mVersion = -1;

        public Source(LiveData<V> liveData, Observer<? super V> observer) {
            this.mLiveData = liveData;
            this.mObserver = observer;
        }

        @Override // androidx.lifecycle.Observer
        public void onChanged(@Nullable V v) {
            if (this.mVersion != this.mLiveData.getVersion()) {
                this.mVersion = this.mLiveData.getVersion();
                this.mObserver.onChanged(v);
            }
        }

        public void plug() {
            this.mLiveData.observeForever(this);
        }

        public void unplug() {
            this.mLiveData.removeObserver(this);
        }
    }

    public MediatorLiveData(T t) {
        super(t);
        this.mSources = new SafeIterableMap<>();
    }

    @MainThread
    public <S> void addSource(@NonNull LiveData<S> liveData, @NonNull Observer<? super S> observer) {
        if (liveData == null) {
            throw new NullPointerException("source cannot be null");
        }
        Source<?> source = new Source<>(liveData, observer);
        Source<?> sourcePutIfAbsent = this.mSources.putIfAbsent(liveData, source);
        if (sourcePutIfAbsent != null && sourcePutIfAbsent.mObserver != observer) {
            throw new IllegalArgumentException("This source was already added with the different observer");
        }
        if (sourcePutIfAbsent == null && hasActiveObservers()) {
            source.plug();
        }
    }

    @Override // androidx.lifecycle.LiveData
    @CallSuper
    public void onActive() {
        Iterator<Map.Entry<LiveData<?>, Source<?>>> it = this.mSources.iterator();
        while (true) {
            SafeIterableMap.ListIterator listIterator = (SafeIterableMap.ListIterator) it;
            if (!listIterator.hasNext()) {
                return;
            } else {
                ((Source) listIterator.next().getValue()).plug();
            }
        }
    }

    @Override // androidx.lifecycle.LiveData
    @CallSuper
    public void onInactive() {
        Iterator<Map.Entry<LiveData<?>, Source<?>>> it = this.mSources.iterator();
        while (true) {
            SafeIterableMap.ListIterator listIterator = (SafeIterableMap.ListIterator) it;
            if (!listIterator.hasNext()) {
                return;
            } else {
                ((Source) listIterator.next().getValue()).unplug();
            }
        }
    }

    @MainThread
    public <S> void removeSource(@NonNull LiveData<S> liveData) {
        Source<?> sourceRemove = this.mSources.remove(liveData);
        if (sourceRemove != null) {
            sourceRemove.unplug();
        }
    }

    public MediatorLiveData() {
        this.mSources = new SafeIterableMap<>();
    }
}
