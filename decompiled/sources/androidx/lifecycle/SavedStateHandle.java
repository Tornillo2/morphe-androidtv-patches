package androidx.lifecycle;

import android.os.Binder;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Size;
import android.util.SizeF;
import android.util.SparseArray;
import androidx.annotation.MainThread;
import androidx.annotation.RestrictTo;
import androidx.core.os.BundleKt;
import androidx.savedstate.SavedStateRegistry;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import kotlin.Pair;
import kotlin.collections.MapsKt__MapsKt;
import kotlin.collections.SetsKt___SetsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.coroutines.flow.FlowKt__ShareKt;
import kotlinx.coroutines.flow.MutableStateFlow;
import kotlinx.coroutines.flow.StateFlow;
import kotlinx.coroutines.flow.StateFlowKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@SourceDebugExtension({"SMAP\nSavedStateHandle.kt\nKotlin\n*S Kotlin\n*F\n+ 1 SavedStateHandle.kt\nandroidx/lifecycle/SavedStateHandle\n+ 2 Maps.kt\nkotlin/collections/MapsKt__MapsKt\n+ 3 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,450:1\n361#2,3:451\n364#2,4:455\n1#3:454\n*S KotlinDebug\n*F\n+ 1 SavedStateHandle.kt\nandroidx/lifecycle/SavedStateHandle\n*L\n198#1:451,3\n198#1:455,4\n*E\n"})
public final class SavedStateHandle {

    @NotNull
    public static final String KEYS = "keys";

    @NotNull
    public static final String VALUES = "values";

    @NotNull
    public final Map<String, MutableStateFlow<Object>> flows;

    @NotNull
    public final Map<String, SavingStateLiveData<?>> liveDatas;

    @NotNull
    public final Map<String, Object> regular;

    @NotNull
    public final SavedStateRegistry.SavedStateProvider savedStateProvider;

    @NotNull
    public final Map<String, SavedStateRegistry.SavedStateProvider> savedStateProviders;

    @NotNull
    public static final Companion Companion = new Companion();

    @NotNull
    public static final Class<? extends Object>[] ACCEPTABLE_CLASSES = {Boolean.TYPE, boolean[].class, Double.TYPE, double[].class, Integer.TYPE, int[].class, Long.TYPE, long[].class, String.class, String[].class, Binder.class, Bundle.class, Byte.TYPE, byte[].class, Character.TYPE, char[].class, CharSequence.class, CharSequence[].class, ArrayList.class, Float.TYPE, float[].class, Parcelable.class, Parcelable[].class, Serializable.class, Short.TYPE, short[].class, SparseArray.class, Size.class, SizeF.class};

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class Companion {
        public Companion() {
        }

        @JvmStatic
        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
        @NotNull
        public final SavedStateHandle createHandle(@Nullable Bundle bundle, @Nullable Bundle bundle2) {
            if (bundle == null) {
                if (bundle2 == null) {
                    return new SavedStateHandle();
                }
                HashMap map = new HashMap();
                for (String key : bundle2.keySet()) {
                    Intrinsics.checkNotNullExpressionValue(key, "key");
                    map.put(key, bundle2.get(key));
                }
                return new SavedStateHandle(map);
            }
            ArrayList parcelableArrayList = bundle.getParcelableArrayList(SavedStateHandle.KEYS);
            ArrayList parcelableArrayList2 = bundle.getParcelableArrayList("values");
            if (parcelableArrayList == null || parcelableArrayList2 == null || parcelableArrayList.size() != parcelableArrayList2.size()) {
                throw new IllegalStateException("Invalid bundle passed as restored state");
            }
            LinkedHashMap linkedHashMap = new LinkedHashMap();
            int size = parcelableArrayList.size();
            for (int i = 0; i < size; i++) {
                Object obj = parcelableArrayList.get(i);
                Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type kotlin.String");
                linkedHashMap.put((String) obj, parcelableArrayList2.get(i));
            }
            return new SavedStateHandle(linkedHashMap);
        }

        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
        public final boolean validateValue(@Nullable Object obj) {
            if (obj == null) {
                return true;
            }
            for (Class cls : SavedStateHandle.ACCEPTABLE_CLASSES) {
                Intrinsics.checkNotNull(cls);
                if (cls.isInstance(obj)) {
                    return true;
                }
            }
            return false;
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
        }
    }

    public SavedStateHandle(@NotNull Map<String, ? extends Object> initialState) {
        Intrinsics.checkNotNullParameter(initialState, "initialState");
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        this.regular = linkedHashMap;
        this.savedStateProviders = new LinkedHashMap();
        this.liveDatas = new LinkedHashMap();
        this.flows = new LinkedHashMap();
        this.savedStateProvider = new SavedStateRegistry.SavedStateProvider() { // from class: androidx.lifecycle.SavedStateHandle$$ExternalSyntheticLambda0
            @Override // androidx.savedstate.SavedStateRegistry.SavedStateProvider
            public final Bundle saveState() {
                return SavedStateHandle.savedStateProvider$lambda$0(this.f$0);
            }
        };
        linkedHashMap.putAll(initialState);
    }

    @JvmStatic
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    @NotNull
    public static final SavedStateHandle createHandle(@Nullable Bundle bundle, @Nullable Bundle bundle2) {
        return Companion.createHandle(bundle, bundle2);
    }

    public static final Bundle savedStateProvider$lambda$0(SavedStateHandle this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        for (Map.Entry entry : MapsKt__MapsKt.toMap(this$0.savedStateProviders).entrySet()) {
            this$0.set((String) entry.getKey(), ((SavedStateRegistry.SavedStateProvider) entry.getValue()).saveState());
        }
        Set<String> setKeySet = this$0.regular.keySet();
        ArrayList arrayList = new ArrayList(setKeySet.size());
        ArrayList arrayList2 = new ArrayList(arrayList.size());
        for (String str : setKeySet) {
            arrayList.add(str);
            arrayList2.add(this$0.regular.get(str));
        }
        return BundleKt.bundleOf(new Pair(KEYS, arrayList), new Pair("values", arrayList2));
    }

    @MainThread
    public final void clearSavedStateProvider(@NotNull String key) {
        Intrinsics.checkNotNullParameter(key, "key");
        this.savedStateProviders.remove(key);
    }

    @MainThread
    public final boolean contains(@NotNull String key) {
        Intrinsics.checkNotNullParameter(key, "key");
        return this.regular.containsKey(key);
    }

    @MainThread
    @Nullable
    public final <T> T get(@NotNull String key) {
        Intrinsics.checkNotNullParameter(key, "key");
        try {
            return (T) this.regular.get(key);
        } catch (ClassCastException unused) {
            remove(key);
            return null;
        }
    }

    @MainThread
    @NotNull
    public final <T> MutableLiveData<T> getLiveData(@NotNull String key) {
        Intrinsics.checkNotNullParameter(key, "key");
        return getLiveDataInternal(key, false, null);
    }

    public final <T> MutableLiveData<T> getLiveDataInternal(String str, boolean z, T t) {
        SavingStateLiveData<?> savingStateLiveData;
        SavingStateLiveData<?> savingStateLiveData2 = this.liveDatas.get(str);
        SavingStateLiveData<?> savingStateLiveData3 = savingStateLiveData2 instanceof MutableLiveData ? savingStateLiveData2 : null;
        if (savingStateLiveData3 != null) {
            return savingStateLiveData3;
        }
        if (this.regular.containsKey(str)) {
            savingStateLiveData = new SavingStateLiveData<>(this, str, this.regular.get(str));
        } else if (z) {
            this.regular.put(str, t);
            savingStateLiveData = new SavingStateLiveData<>(this, str, t);
        } else {
            savingStateLiveData = new SavingStateLiveData<>(this, str);
        }
        this.liveDatas.put(str, savingStateLiveData);
        return savingStateLiveData;
    }

    @MainThread
    @NotNull
    public final <T> StateFlow<T> getStateFlow(@NotNull String key, T t) {
        Intrinsics.checkNotNullParameter(key, "key");
        Map<String, MutableStateFlow<Object>> map = this.flows;
        MutableStateFlow<Object> MutableStateFlow = map.get(key);
        if (MutableStateFlow == null) {
            if (!this.regular.containsKey(key)) {
                this.regular.put(key, t);
            }
            MutableStateFlow = StateFlowKt.MutableStateFlow(this.regular.get(key));
            this.flows.put(key, MutableStateFlow);
            map.put(key, MutableStateFlow);
        }
        return FlowKt__ShareKt.asStateFlow(MutableStateFlow);
    }

    @MainThread
    @NotNull
    public final Set<String> keys() {
        return SetsKt___SetsKt.plus(SetsKt___SetsKt.plus((Set) this.regular.keySet(), (Iterable) this.savedStateProviders.keySet()), (Iterable) this.liveDatas.keySet());
    }

    @MainThread
    @Nullable
    public final <T> T remove(@NotNull String key) {
        Intrinsics.checkNotNullParameter(key, "key");
        T t = (T) this.regular.remove(key);
        SavingStateLiveData<?> savingStateLiveDataRemove = this.liveDatas.remove(key);
        if (savingStateLiveDataRemove != null) {
            savingStateLiveDataRemove.handle = null;
        }
        this.flows.remove(key);
        return t;
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    @NotNull
    public final SavedStateRegistry.SavedStateProvider savedStateProvider() {
        return this.savedStateProvider;
    }

    @MainThread
    public final <T> void set(@NotNull String key, @Nullable T t) {
        Intrinsics.checkNotNullParameter(key, "key");
        if (!Companion.validateValue(t)) {
            StringBuilder sb = new StringBuilder("Can't put value with type ");
            Intrinsics.checkNotNull(t);
            sb.append(t.getClass());
            sb.append(" into saved state");
            throw new IllegalArgumentException(sb.toString());
        }
        SavingStateLiveData<?> savingStateLiveData = this.liveDatas.get(key);
        SavingStateLiveData<?> savingStateLiveData2 = savingStateLiveData instanceof MutableLiveData ? savingStateLiveData : null;
        if (savingStateLiveData2 != null) {
            savingStateLiveData2.setValue(t);
        } else {
            this.regular.put(key, t);
        }
        MutableStateFlow<Object> mutableStateFlow = this.flows.get(key);
        if (mutableStateFlow == null) {
            return;
        }
        mutableStateFlow.setValue(t);
    }

    @MainThread
    public final void setSavedStateProvider(@NotNull String key, @NotNull SavedStateRegistry.SavedStateProvider provider) {
        Intrinsics.checkNotNullParameter(key, "key");
        Intrinsics.checkNotNullParameter(provider, "provider");
        this.savedStateProviders.put(key, provider);
    }

    @MainThread
    @NotNull
    public final <T> MutableLiveData<T> getLiveData(@NotNull String key, T t) {
        Intrinsics.checkNotNullParameter(key, "key");
        return getLiveDataInternal(key, true, t);
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class SavingStateLiveData<T> extends MutableLiveData<T> {

        @Nullable
        public SavedStateHandle handle;

        @NotNull
        public String key;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public SavingStateLiveData(@Nullable SavedStateHandle savedStateHandle, @NotNull String key, T t) {
            super(t);
            Intrinsics.checkNotNullParameter(key, "key");
            this.key = key;
            this.handle = savedStateHandle;
        }

        public final void detach() {
            this.handle = null;
        }

        @Override // androidx.lifecycle.MutableLiveData, androidx.lifecycle.LiveData
        public void setValue(T t) {
            SavedStateHandle savedStateHandle = this.handle;
            if (savedStateHandle != null) {
                savedStateHandle.regular.put(this.key, t);
                MutableStateFlow<Object> mutableStateFlow = savedStateHandle.flows.get(this.key);
                if (mutableStateFlow != null) {
                    mutableStateFlow.setValue(t);
                }
            }
            super.setValue(t);
        }

        public SavingStateLiveData(@Nullable SavedStateHandle savedStateHandle, @NotNull String key) {
            Intrinsics.checkNotNullParameter(key, "key");
            this.key = key;
            this.handle = savedStateHandle;
        }
    }

    public SavedStateHandle() {
        this.regular = new LinkedHashMap();
        this.savedStateProviders = new LinkedHashMap();
        this.liveDatas = new LinkedHashMap();
        this.flows = new LinkedHashMap();
        this.savedStateProvider = new SavedStateRegistry.SavedStateProvider() { // from class: androidx.lifecycle.SavedStateHandle$$ExternalSyntheticLambda0
            @Override // androidx.savedstate.SavedStateRegistry.SavedStateProvider
            public final Bundle saveState() {
                return SavedStateHandle.savedStateProvider$lambda$0(this.f$0);
            }
        };
    }
}
