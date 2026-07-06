package androidx.fragment.app;

import android.support.v4.media.MediaMetadataCompat$Builder$$ExternalSyntheticOutline0;
import androidx.annotation.NonNull;
import androidx.collection.SimpleArrayMap;
import androidx.fragment.app.Fragment;
import java.lang.reflect.InvocationTargetException;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public class FragmentFactory {
    public static final SimpleArrayMap<ClassLoader, SimpleArrayMap<String, Class<?>>> sClassCacheMap = new SimpleArrayMap<>();

    public static boolean isFragmentClass(@NonNull ClassLoader classLoader, @NonNull String str) {
        try {
            return Fragment.class.isAssignableFrom(loadClass(classLoader, str));
        } catch (ClassNotFoundException unused) {
            return false;
        }
    }

    @NonNull
    public static Class<?> loadClass(@NonNull ClassLoader classLoader, @NonNull String str) throws ClassNotFoundException {
        SimpleArrayMap<ClassLoader, SimpleArrayMap<String, Class<?>>> simpleArrayMap = sClassCacheMap;
        SimpleArrayMap<String, Class<?>> simpleArrayMap2 = simpleArrayMap.get(classLoader);
        if (simpleArrayMap2 == null) {
            simpleArrayMap2 = new SimpleArrayMap<>();
            simpleArrayMap.put(classLoader, simpleArrayMap2);
        }
        Class<?> cls = simpleArrayMap2.get(str);
        if (cls != null) {
            return cls;
        }
        Class<?> cls2 = Class.forName(str, false, classLoader);
        simpleArrayMap2.put(str, cls2);
        return cls2;
    }

    @NonNull
    public static Class<? extends Fragment> loadFragmentClass(@NonNull ClassLoader classLoader, @NonNull String str) {
        try {
            return loadClass(classLoader, str);
        } catch (ClassCastException e) {
            throw new Fragment.InstantiationException(MediaMetadataCompat$Builder$$ExternalSyntheticOutline0.m("Unable to instantiate fragment ", str, ": make sure class is a valid subclass of Fragment"), (Throwable) e);
        } catch (ClassNotFoundException e2) {
            throw new Fragment.InstantiationException(MediaMetadataCompat$Builder$$ExternalSyntheticOutline0.m("Unable to instantiate fragment ", str, ": make sure class name exists"), (Throwable) e2);
        }
    }

    @NonNull
    public Fragment instantiate(@NonNull ClassLoader classLoader, @NonNull String str) {
        try {
            return loadFragmentClass(classLoader, str).getConstructor(null).newInstance(null);
        } catch (IllegalAccessException e) {
            throw new Fragment.InstantiationException(MediaMetadataCompat$Builder$$ExternalSyntheticOutline0.m("Unable to instantiate fragment ", str, ": make sure class name exists, is public, and has an empty constructor that is public"), (Throwable) e);
        } catch (InstantiationException e2) {
            throw new Fragment.InstantiationException(MediaMetadataCompat$Builder$$ExternalSyntheticOutline0.m("Unable to instantiate fragment ", str, ": make sure class name exists, is public, and has an empty constructor that is public"), (Throwable) e2);
        } catch (NoSuchMethodException e3) {
            throw new Fragment.InstantiationException(MediaMetadataCompat$Builder$$ExternalSyntheticOutline0.m("Unable to instantiate fragment ", str, ": could not find Fragment constructor"), (Throwable) e3);
        } catch (InvocationTargetException e4) {
            throw new Fragment.InstantiationException(MediaMetadataCompat$Builder$$ExternalSyntheticOutline0.m("Unable to instantiate fragment ", str, ": calling Fragment constructor caused an exception"), (Throwable) e4);
        }
    }
}
