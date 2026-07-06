package androidx.room;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteCallbackList;
import android.os.RemoteException;
import android.util.Log;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import androidx.room.IMultiInstanceInvalidationService;
import java.util.HashMap;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
public class MultiInstanceInvalidationService extends Service {
    public int mMaxClientId = 0;
    public final HashMap<Integer, String> mClientNames = new HashMap<>();
    public final RemoteCallbackList<IMultiInstanceInvalidationCallback> mCallbackList = new RemoteCallbackList<IMultiInstanceInvalidationCallback>() { // from class: androidx.room.MultiInstanceInvalidationService.1
        @Override // android.os.RemoteCallbackList
        public void onCallbackDied(IMultiInstanceInvalidationCallback iMultiInstanceInvalidationCallback, Object obj) {
            HashMap<Integer, String> map = MultiInstanceInvalidationService.this.mClientNames;
            Integer num = (Integer) obj;
            num.intValue();
            map.remove(num);
        }
    };
    public final IMultiInstanceInvalidationService.Stub mBinder = new IMultiInstanceInvalidationService.Stub() { // from class: androidx.room.MultiInstanceInvalidationService.2
        @Override // androidx.room.IMultiInstanceInvalidationService
        public void broadcastInvalidation(int i, String[] strArr) {
            synchronized (MultiInstanceInvalidationService.this.mCallbackList) {
                try {
                    String str = MultiInstanceInvalidationService.this.mClientNames.get(Integer.valueOf(i));
                    if (str == null) {
                        Log.w(Room.LOG_TAG, "Remote invalidation client ID not registered");
                        return;
                    }
                    int iBeginBroadcast = MultiInstanceInvalidationService.this.mCallbackList.beginBroadcast();
                    for (int i2 = 0; i2 < iBeginBroadcast; i2++) {
                        try {
                            Integer num = (Integer) MultiInstanceInvalidationService.this.mCallbackList.getBroadcastCookie(i2);
                            int iIntValue = num.intValue();
                            String str2 = MultiInstanceInvalidationService.this.mClientNames.get(num);
                            if (i != iIntValue && str.equals(str2)) {
                                try {
                                    ((IMultiInstanceInvalidationCallback) MultiInstanceInvalidationService.this.mCallbackList.getBroadcastItem(i2)).onInvalidation(strArr);
                                } catch (RemoteException e) {
                                    Log.w(Room.LOG_TAG, "Error invoking a remote callback", e);
                                }
                            }
                        } finally {
                            MultiInstanceInvalidationService.this.mCallbackList.finishBroadcast();
                        }
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
        }

        @Override // androidx.room.IMultiInstanceInvalidationService
        public int registerCallback(IMultiInstanceInvalidationCallback iMultiInstanceInvalidationCallback, String str) {
            if (str == null) {
                return 0;
            }
            synchronized (MultiInstanceInvalidationService.this.mCallbackList) {
                try {
                    MultiInstanceInvalidationService multiInstanceInvalidationService = MultiInstanceInvalidationService.this;
                    int i = multiInstanceInvalidationService.mMaxClientId + 1;
                    multiInstanceInvalidationService.mMaxClientId = i;
                    if (multiInstanceInvalidationService.mCallbackList.register(iMultiInstanceInvalidationCallback, Integer.valueOf(i))) {
                        MultiInstanceInvalidationService.this.mClientNames.put(Integer.valueOf(i), str);
                        return i;
                    }
                    MultiInstanceInvalidationService multiInstanceInvalidationService2 = MultiInstanceInvalidationService.this;
                    multiInstanceInvalidationService2.mMaxClientId--;
                    return 0;
                } catch (Throwable th) {
                    throw th;
                }
            }
        }

        @Override // androidx.room.IMultiInstanceInvalidationService
        public void unregisterCallback(IMultiInstanceInvalidationCallback iMultiInstanceInvalidationCallback, int i) {
            synchronized (MultiInstanceInvalidationService.this.mCallbackList) {
                MultiInstanceInvalidationService.this.mCallbackList.unregister(iMultiInstanceInvalidationCallback);
                MultiInstanceInvalidationService.this.mClientNames.remove(Integer.valueOf(i));
            }
        }
    };

    @Override // android.app.Service
    @Nullable
    public IBinder onBind(Intent intent) {
        return this.mBinder;
    }
}
