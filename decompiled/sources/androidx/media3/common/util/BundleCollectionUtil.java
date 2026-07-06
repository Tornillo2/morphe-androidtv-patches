package androidx.media3.common.util;

import android.os.Bundle;
import android.util.SparseArray;
import androidx.annotation.Nullable;
import com.google.common.base.Function;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.RegularImmutableMap;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@UnstableApi
public final class BundleCollectionUtil {
    public static HashMap<String, String> bundleToStringHashMap(Bundle bundle) {
        HashMap<String, String> map = new HashMap<>();
        if (bundle != Bundle.EMPTY) {
            for (String str : bundle.keySet()) {
                String string = bundle.getString(str);
                if (string != null) {
                    map.put(str, string);
                }
            }
        }
        return map;
    }

    public static ImmutableMap<String, String> bundleToStringImmutableMap(Bundle bundle) {
        return bundle == Bundle.EMPTY ? RegularImmutableMap.EMPTY : ImmutableMap.copyOf((Map) bundleToStringHashMap(bundle));
    }

    public static void ensureClassLoader(@Nullable Bundle bundle) {
        if (bundle != null) {
            ClassLoader classLoader = BundleCollectionUtil.class.getClassLoader();
            Util.castNonNull(classLoader);
            bundle.setClassLoader(classLoader);
        }
    }

    public static <T> ImmutableList<T> fromBundleList(Function<Bundle, T> function, List<Bundle> list) {
        ImmutableList.Builder builder = ImmutableList.builder();
        for (int i = 0; i < list.size(); i++) {
            Bundle bundle = list.get(i);
            bundle.getClass();
            builder.add(function.apply(bundle));
        }
        return builder.build();
    }

    public static <T> SparseArray<T> fromBundleSparseArray(Function<Bundle, T> function, SparseArray<Bundle> sparseArray) {
        SparseArray<T> sparseArray2 = new SparseArray<>(sparseArray.size());
        for (int i = 0; i < sparseArray.size(); i++) {
            sparseArray2.put(sparseArray.keyAt(i), function.apply(sparseArray.valueAt(i)));
        }
        return sparseArray2;
    }

    public static Bundle getBundleWithDefault(Bundle bundle, String str, Bundle bundle2) {
        Bundle bundle3 = bundle.getBundle(str);
        return bundle3 != null ? bundle3 : bundle2;
    }

    public static ArrayList<Integer> getIntegerArrayListWithDefault(Bundle bundle, String str, ArrayList<Integer> arrayList) {
        ArrayList<Integer> integerArrayList = bundle.getIntegerArrayList(str);
        return integerArrayList != null ? integerArrayList : arrayList;
    }

    public static Bundle stringMapToBundle(Map<String, String> map) {
        Bundle bundle = new Bundle();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            bundle.putString(entry.getKey(), entry.getValue());
        }
        return bundle;
    }

    public static <T> ArrayList<Bundle> toBundleArrayList(Collection<T> collection, Function<T, Bundle> function) {
        ArrayList<Bundle> arrayList = new ArrayList<>(collection.size());
        Iterator<T> it = collection.iterator();
        while (it.hasNext()) {
            arrayList.add(function.apply(it.next()));
        }
        return arrayList;
    }

    public static <T> ImmutableList<Bundle> toBundleList(List<T> list, Function<T, Bundle> function) {
        ImmutableList.Builder builder = ImmutableList.builder();
        for (int i = 0; i < list.size(); i++) {
            builder.add(function.apply(list.get(i)));
        }
        return builder.build();
    }

    public static <T> SparseArray<Bundle> toBundleSparseArray(SparseArray<T> sparseArray, Function<T, Bundle> function) {
        SparseArray<Bundle> sparseArray2 = new SparseArray<>(sparseArray.size());
        for (int i = 0; i < sparseArray.size(); i++) {
            sparseArray2.put(sparseArray.keyAt(i), function.apply(sparseArray.valueAt(i)));
        }
        return sparseArray2;
    }
}
