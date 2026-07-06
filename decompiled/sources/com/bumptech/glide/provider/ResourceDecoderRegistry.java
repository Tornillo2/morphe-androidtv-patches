package com.bumptech.glide.provider;

import androidx.annotation.NonNull;
import com.bumptech.glide.load.ResourceDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public class ResourceDecoderRegistry {
    public final List<String> bucketPriorityList = new ArrayList();
    public final Map<String, List<Entry<?, ?>>> decoders = new HashMap();

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static class Entry<T, R> {
        public final Class<T> dataClass;
        public final ResourceDecoder<T, R> decoder;
        public final Class<R> resourceClass;

        public Entry(@NonNull Class<T> cls, @NonNull Class<R> cls2, ResourceDecoder<T, R> resourceDecoder) {
            this.dataClass = cls;
            this.resourceClass = cls2;
            this.decoder = resourceDecoder;
        }

        public boolean handles(@NonNull Class<?> cls, @NonNull Class<?> cls2) {
            return this.dataClass.isAssignableFrom(cls) && cls2.isAssignableFrom(this.resourceClass);
        }
    }

    public synchronized <T, R> void append(@NonNull String str, @NonNull ResourceDecoder<T, R> resourceDecoder, @NonNull Class<T> cls, @NonNull Class<R> cls2) {
        getOrAddEntryList(str).add(new Entry<>(cls, cls2, resourceDecoder));
    }

    @NonNull
    public synchronized <T, R> List<ResourceDecoder<T, R>> getDecoders(@NonNull Class<T> cls, @NonNull Class<R> cls2) {
        ArrayList arrayList;
        arrayList = new ArrayList();
        Iterator<String> it = this.bucketPriorityList.iterator();
        while (it.hasNext()) {
            List<Entry<?, ?>> list = this.decoders.get(it.next());
            if (list != null) {
                for (Entry<?, ?> entry : list) {
                    if (entry.handles(cls, cls2)) {
                        arrayList.add(entry.decoder);
                    }
                }
            }
        }
        return arrayList;
    }

    @NonNull
    public final synchronized List<Entry<?, ?>> getOrAddEntryList(@NonNull String str) {
        List<Entry<?, ?>> arrayList;
        try {
            if (!this.bucketPriorityList.contains(str)) {
                this.bucketPriorityList.add(str);
            }
            arrayList = this.decoders.get(str);
            if (arrayList == null) {
                arrayList = new ArrayList<>();
                this.decoders.put(str, arrayList);
            }
        } catch (Throwable th) {
            throw th;
        }
        return arrayList;
    }

    @NonNull
    public synchronized <T, R> List<Class<R>> getResourceClasses(@NonNull Class<T> cls, @NonNull Class<R> cls2) {
        ArrayList arrayList;
        arrayList = new ArrayList();
        Iterator<String> it = this.bucketPriorityList.iterator();
        while (it.hasNext()) {
            List<Entry<?, ?>> list = this.decoders.get(it.next());
            if (list != null) {
                for (Entry<?, ?> entry : list) {
                    if (entry.handles(cls, cls2) && !arrayList.contains(entry.resourceClass)) {
                        arrayList.add(entry.resourceClass);
                    }
                }
            }
        }
        return arrayList;
    }

    public synchronized <T, R> void prepend(@NonNull String str, @NonNull ResourceDecoder<T, R> resourceDecoder, @NonNull Class<T> cls, @NonNull Class<R> cls2) {
        getOrAddEntryList(str).add(0, new Entry<>(cls, cls2, resourceDecoder));
    }

    public synchronized void setBucketPriorityList(@NonNull List<String> list) {
        try {
            ArrayList arrayList = new ArrayList(this.bucketPriorityList);
            this.bucketPriorityList.clear();
            Iterator<String> it = list.iterator();
            while (it.hasNext()) {
                this.bucketPriorityList.add(it.next());
            }
            int size = arrayList.size();
            int i = 0;
            while (i < size) {
                Object obj = arrayList.get(i);
                i++;
                String str = (String) obj;
                if (!list.contains(str)) {
                    this.bucketPriorityList.add(str);
                }
            }
        } catch (Throwable th) {
            throw th;
        }
    }
}
