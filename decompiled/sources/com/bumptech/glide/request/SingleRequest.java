package com.bumptech.glide.request;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.support.v4.media.MediaBrowserCompat$MediaBrowserImplBase$MediaServiceConnection$$ExternalSyntheticOutline0;
import android.util.Log;
import androidx.annotation.DrawableRes;
import androidx.annotation.GuardedBy;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.bumptech.glide.GlideContext;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.Key;
import com.bumptech.glide.load.Options;
import com.bumptech.glide.load.Transformation;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.Engine;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.engine.Resource;
import com.bumptech.glide.load.resource.drawable.DrawableDecoderCompat;
import com.bumptech.glide.request.target.SizeReadyCallback;
import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.request.transition.TransitionFactory;
import com.bumptech.glide.util.LogTime;
import com.bumptech.glide.util.Util;
import com.bumptech.glide.util.pool.StateVerifier;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executor;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class SingleRequest<R> implements Request, SizeReadyCallback, ResourceCallback {
    public static final String GLIDE_TAG = "Glide";
    public final TransitionFactory<? super R> animationFactory;
    public final Executor callbackExecutor;
    public final Context context;
    public volatile Engine engine;

    @Nullable
    @GuardedBy("requestLock")
    public Drawable errorDrawable;

    @Nullable
    @GuardedBy("requestLock")
    public Drawable fallbackDrawable;
    public final GlideContext glideContext;

    @GuardedBy("requestLock")
    public int height;

    @GuardedBy("requestLock")
    public boolean isCallingCallbacks;

    @GuardedBy("requestLock")
    public Engine.LoadStatus loadStatus;

    @Nullable
    public final Object model;
    public final int overrideHeight;
    public final int overrideWidth;

    @Nullable
    @GuardedBy("requestLock")
    public Drawable placeholderDrawable;
    public final Priority priority;
    public final RequestCoordinator requestCoordinator;

    @Nullable
    public final List<RequestListener<R>> requestListeners;
    public final Object requestLock;
    public final BaseRequestOptions<?> requestOptions;

    @Nullable
    public RuntimeException requestOrigin;

    @GuardedBy("requestLock")
    public Resource<R> resource;

    @GuardedBy("requestLock")
    public long startTime;
    public final StateVerifier stateVerifier;

    @GuardedBy("requestLock")
    public Status status;

    @Nullable
    public final String tag;
    public final Target<R> target;

    @Nullable
    public final RequestListener<R> targetListener;
    public final Class<R> transcodeClass;

    @GuardedBy("requestLock")
    public int width;
    public static final String TAG = "Request";
    public static final boolean IS_VERBOSE_LOGGABLE = Log.isLoggable(TAG, 2);

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public enum Status {
        PENDING,
        RUNNING,
        WAITING_FOR_SIZE,
        COMPLETE,
        FAILED,
        CLEARED
    }

    public SingleRequest(Context context, GlideContext glideContext, @NonNull Object obj, @Nullable Object obj2, Class<R> cls, BaseRequestOptions<?> baseRequestOptions, int i, int i2, Priority priority, Target<R> target, @Nullable RequestListener<R> requestListener, @Nullable List<RequestListener<R>> list, RequestCoordinator requestCoordinator, Engine engine, TransitionFactory<? super R> transitionFactory, Executor executor) {
        this.tag = IS_VERBOSE_LOGGABLE ? String.valueOf(hashCode()) : null;
        this.stateVerifier = new StateVerifier.DefaultStateVerifier();
        this.requestLock = obj;
        this.context = context;
        this.glideContext = glideContext;
        this.model = obj2;
        this.transcodeClass = cls;
        this.requestOptions = baseRequestOptions;
        this.overrideWidth = i;
        this.overrideHeight = i2;
        this.priority = priority;
        this.target = target;
        this.targetListener = requestListener;
        this.requestListeners = list;
        this.requestCoordinator = requestCoordinator;
        this.engine = engine;
        this.animationFactory = transitionFactory;
        this.callbackExecutor = executor;
        this.status = Status.PENDING;
        if (this.requestOrigin == null && glideContext.isLoggingRequestOriginsEnabled()) {
            this.requestOrigin = new RuntimeException("Glide request origin trace");
        }
    }

    public static int maybeApplySizeMultiplier(int i, float f) {
        return i == Integer.MIN_VALUE ? i : Math.round(f * i);
    }

    public static <R> SingleRequest<R> obtain(Context context, GlideContext glideContext, Object obj, Object obj2, Class<R> cls, BaseRequestOptions<?> baseRequestOptions, int i, int i2, Priority priority, Target<R> target, RequestListener<R> requestListener, @Nullable List<RequestListener<R>> list, RequestCoordinator requestCoordinator, Engine engine, TransitionFactory<? super R> transitionFactory, Executor executor) {
        return new SingleRequest<>(context, glideContext, obj, obj2, cls, baseRequestOptions, i, i2, priority, target, requestListener, list, requestCoordinator, engine, transitionFactory, executor);
    }

    @GuardedBy("requestLock")
    public final void assertNotCallingCallbacks() {
        if (this.isCallingCallbacks) {
            throw new IllegalStateException("You can't start or clear loads in RequestListener or Target callbacks. If you're trying to start a fallback request when a load fails, use RequestBuilder#error(RequestBuilder). Otherwise consider posting your into() or clear() calls to the main thread using a Handler instead.");
        }
    }

    @Override // com.bumptech.glide.request.Request
    public void begin() {
        synchronized (this.requestLock) {
            try {
                assertNotCallingCallbacks();
                this.stateVerifier.throwIfRecycled();
                this.startTime = LogTime.getLogTime();
                if (this.model == null) {
                    if (Util.isValidDimensions(this.overrideWidth, this.overrideHeight)) {
                        this.width = this.overrideWidth;
                        this.height = this.overrideHeight;
                    }
                    onLoadFailed(new GlideException("Received null model"), getFallbackDrawable() == null ? 5 : 3);
                    return;
                }
                Status status = this.status;
                Status status2 = Status.RUNNING;
                if (status == status2) {
                    throw new IllegalArgumentException("Cannot restart a running request");
                }
                if (status == Status.COMPLETE) {
                    onResourceReady(this.resource, DataSource.MEMORY_CACHE);
                    return;
                }
                Status status3 = Status.WAITING_FOR_SIZE;
                this.status = status3;
                if (Util.isValidDimensions(this.overrideWidth, this.overrideHeight)) {
                    onSizeReady(this.overrideWidth, this.overrideHeight);
                } else {
                    this.target.getSize(this);
                }
                Status status4 = this.status;
                if ((status4 == status2 || status4 == status3) && canNotifyStatusChanged()) {
                    this.target.onLoadStarted(getPlaceholderDrawable());
                }
                if (IS_VERBOSE_LOGGABLE) {
                    logV("finished run method in " + LogTime.getElapsedMillis(this.startTime));
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    @GuardedBy("requestLock")
    public final boolean canNotifyCleared() {
        RequestCoordinator requestCoordinator = this.requestCoordinator;
        return requestCoordinator == null || requestCoordinator.canNotifyCleared(this);
    }

    @GuardedBy("requestLock")
    public final boolean canNotifyStatusChanged() {
        RequestCoordinator requestCoordinator = this.requestCoordinator;
        return requestCoordinator == null || requestCoordinator.canNotifyStatusChanged(this);
    }

    @GuardedBy("requestLock")
    public final boolean canSetResource() {
        RequestCoordinator requestCoordinator = this.requestCoordinator;
        return requestCoordinator == null || requestCoordinator.canSetImage(this);
    }

    @GuardedBy("requestLock")
    public final void cancel() {
        assertNotCallingCallbacks();
        this.stateVerifier.throwIfRecycled();
        this.target.removeCallback(this);
        Engine.LoadStatus loadStatus = this.loadStatus;
        if (loadStatus != null) {
            loadStatus.cancel();
            this.loadStatus = null;
        }
    }

    @Override // com.bumptech.glide.request.Request
    public void clear() {
        synchronized (this.requestLock) {
            try {
                assertNotCallingCallbacks();
                this.stateVerifier.throwIfRecycled();
                Status status = this.status;
                Status status2 = Status.CLEARED;
                if (status == status2) {
                    return;
                }
                cancel();
                Resource<R> resource = this.resource;
                if (resource != null) {
                    this.resource = null;
                } else {
                    resource = null;
                }
                if (canNotifyCleared()) {
                    this.target.onLoadCleared(getPlaceholderDrawable());
                }
                this.status = status2;
                if (resource != null) {
                    this.engine.release(resource);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    @GuardedBy("requestLock")
    public final Drawable getErrorDrawable() {
        int i;
        if (this.errorDrawable == null) {
            BaseRequestOptions<?> baseRequestOptions = this.requestOptions;
            Drawable drawable = baseRequestOptions.errorPlaceholder;
            this.errorDrawable = drawable;
            if (drawable == null && (i = baseRequestOptions.errorId) > 0) {
                this.errorDrawable = loadDrawable(i);
            }
        }
        return this.errorDrawable;
    }

    @GuardedBy("requestLock")
    public final Drawable getFallbackDrawable() {
        int i;
        if (this.fallbackDrawable == null) {
            BaseRequestOptions<?> baseRequestOptions = this.requestOptions;
            Drawable drawable = baseRequestOptions.fallbackDrawable;
            this.fallbackDrawable = drawable;
            if (drawable == null && (i = baseRequestOptions.fallbackId) > 0) {
                this.fallbackDrawable = loadDrawable(i);
            }
        }
        return this.fallbackDrawable;
    }

    @Override // com.bumptech.glide.request.ResourceCallback
    public Object getLock() {
        this.stateVerifier.throwIfRecycled();
        return this.requestLock;
    }

    @GuardedBy("requestLock")
    public final Drawable getPlaceholderDrawable() {
        int i;
        if (this.placeholderDrawable == null) {
            BaseRequestOptions<?> baseRequestOptions = this.requestOptions;
            Drawable drawable = baseRequestOptions.placeholderDrawable;
            this.placeholderDrawable = drawable;
            if (drawable == null && (i = baseRequestOptions.placeholderId) > 0) {
                this.placeholderDrawable = loadDrawable(i);
            }
        }
        return this.placeholderDrawable;
    }

    @Override // com.bumptech.glide.request.Request
    public boolean isAnyResourceSet() {
        boolean z;
        synchronized (this.requestLock) {
            z = this.status == Status.COMPLETE;
        }
        return z;
    }

    @Override // com.bumptech.glide.request.Request
    public boolean isCleared() {
        boolean z;
        synchronized (this.requestLock) {
            z = this.status == Status.CLEARED;
        }
        return z;
    }

    @Override // com.bumptech.glide.request.Request
    public boolean isComplete() {
        boolean z;
        synchronized (this.requestLock) {
            z = this.status == Status.COMPLETE;
        }
        return z;
    }

    @Override // com.bumptech.glide.request.Request
    public boolean isEquivalentTo(Request request) {
        int i;
        int i2;
        Object obj;
        Class<R> cls;
        BaseRequestOptions<?> baseRequestOptions;
        Priority priority;
        int size;
        int i3;
        int i4;
        Object obj2;
        Class<R> cls2;
        BaseRequestOptions<?> baseRequestOptions2;
        Priority priority2;
        int size2;
        if (!(request instanceof SingleRequest)) {
            return false;
        }
        synchronized (this.requestLock) {
            try {
                i = this.overrideWidth;
                i2 = this.overrideHeight;
                obj = this.model;
                cls = this.transcodeClass;
                baseRequestOptions = this.requestOptions;
                priority = this.priority;
                List<RequestListener<R>> list = this.requestListeners;
                size = list != null ? list.size() : 0;
            } finally {
            }
        }
        SingleRequest singleRequest = (SingleRequest) request;
        synchronized (singleRequest.requestLock) {
            try {
                i3 = singleRequest.overrideWidth;
                i4 = singleRequest.overrideHeight;
                obj2 = singleRequest.model;
                cls2 = singleRequest.transcodeClass;
                baseRequestOptions2 = singleRequest.requestOptions;
                priority2 = singleRequest.priority;
                List<RequestListener<R>> list2 = singleRequest.requestListeners;
                size2 = list2 != null ? list2.size() : 0;
            } finally {
            }
        }
        return i == i3 && i2 == i4 && Util.bothModelsNullEquivalentOrEquals(obj, obj2) && cls.equals(cls2) && baseRequestOptions.equals(baseRequestOptions2) && priority == priority2 && size == size2;
    }

    @GuardedBy("requestLock")
    public final boolean isFirstReadyResource() {
        RequestCoordinator requestCoordinator = this.requestCoordinator;
        return requestCoordinator == null || !requestCoordinator.getRoot().isAnyResourceSet();
    }

    @Override // com.bumptech.glide.request.Request
    public boolean isRunning() {
        boolean z;
        synchronized (this.requestLock) {
            try {
                Status status = this.status;
                z = status == Status.RUNNING || status == Status.WAITING_FOR_SIZE;
            } finally {
            }
        }
        return z;
    }

    @GuardedBy("requestLock")
    public final Drawable loadDrawable(@DrawableRes int i) {
        Resources.Theme theme = this.requestOptions.theme;
        if (theme == null) {
            theme = this.context.getTheme();
        }
        GlideContext glideContext = this.glideContext;
        return DrawableDecoderCompat.getDrawable(glideContext, glideContext, i, theme);
    }

    public final void logV(String str) {
        StringBuilder sbM = MediaBrowserCompat$MediaBrowserImplBase$MediaServiceConnection$$ExternalSyntheticOutline0.m(str, " this: ");
        sbM.append(this.tag);
        Log.v(TAG, sbM.toString());
    }

    @GuardedBy("requestLock")
    public final void notifyLoadFailed() {
        RequestCoordinator requestCoordinator = this.requestCoordinator;
        if (requestCoordinator != null) {
            requestCoordinator.onRequestFailed(this);
        }
    }

    @GuardedBy("requestLock")
    public final void notifyLoadSuccess() {
        RequestCoordinator requestCoordinator = this.requestCoordinator;
        if (requestCoordinator != null) {
            requestCoordinator.onRequestSuccess(this);
        }
    }

    @Override // com.bumptech.glide.request.ResourceCallback
    public void onLoadFailed(GlideException glideException) {
        onLoadFailed(glideException, 5);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.bumptech.glide.request.ResourceCallback
    public void onResourceReady(Resource<?> resource, DataSource dataSource) {
        this.stateVerifier.throwIfRecycled();
        Resource<?> resource2 = null;
        try {
            synchronized (this.requestLock) {
                try {
                    this.loadStatus = null;
                    if (resource == null) {
                        onLoadFailed(new GlideException("Expected to receive a Resource<R> with an object of " + this.transcodeClass + " inside, but instead got null."), 5);
                        return;
                    }
                    Object obj = resource.get();
                    try {
                        if (obj == null || !this.transcodeClass.isAssignableFrom(obj.getClass())) {
                            this.resource = null;
                            StringBuilder sb = new StringBuilder("Expected to receive an object of ");
                            sb.append(this.transcodeClass);
                            sb.append(" but instead got ");
                            sb.append(obj != null ? obj.getClass() : "");
                            sb.append("{");
                            sb.append(obj);
                            sb.append("} inside Resource{");
                            sb.append(resource);
                            sb.append("}.");
                            sb.append(obj != null ? "" : " To indicate failure return a null Resource object, rather than a Resource object containing null data.");
                            onLoadFailed(new GlideException(sb.toString()), 5);
                        } else if (canSetResource()) {
                            onResourceReady(resource, obj, dataSource);
                            return;
                        } else {
                            this.resource = null;
                            this.status = Status.COMPLETE;
                        }
                        this.engine.release(resource);
                    } catch (Throwable th) {
                        resource2 = resource;
                        th = th;
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            }
        } catch (Throwable th3) {
            if (resource2 != null) {
                this.engine.release(resource2);
            }
            throw th3;
        }
    }

    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$PrimitiveArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    @Override // com.bumptech.glide.request.target.SizeReadyCallback
    public void onSizeReady(int i, int i2) throws Throwable {
        Object obj;
        SingleRequest<R> singleRequest = this;
        singleRequest.stateVerifier.throwIfRecycled();
        Object obj2 = singleRequest.requestLock;
        synchronized (obj2) {
            try {
                try {
                    boolean z = IS_VERBOSE_LOGGABLE;
                    if (z) {
                        singleRequest.logV("Got onSizeReady in " + LogTime.getElapsedMillis(singleRequest.startTime));
                    }
                    if (singleRequest.status == Status.WAITING_FOR_SIZE) {
                        Status status = Status.RUNNING;
                        singleRequest.status = status;
                        float f = singleRequest.requestOptions.sizeMultiplier;
                        singleRequest.width = maybeApplySizeMultiplier(i, f);
                        singleRequest.height = maybeApplySizeMultiplier(i2, f);
                        if (z) {
                            singleRequest.logV("finished setup for calling load in " + LogTime.getElapsedMillis(singleRequest.startTime));
                        }
                        Engine engine = singleRequest.engine;
                        GlideContext glideContext = singleRequest.glideContext;
                        try {
                            Object obj3 = singleRequest.model;
                            BaseRequestOptions<?> baseRequestOptions = singleRequest.requestOptions;
                            Key key = baseRequestOptions.signature;
                            try {
                                int i3 = singleRequest.width;
                                int i4 = singleRequest.height;
                                Class<?> cls = baseRequestOptions.resourceClass;
                                try {
                                    Class<R> cls2 = singleRequest.transcodeClass;
                                    Priority priority = singleRequest.priority;
                                    DiskCacheStrategy diskCacheStrategy = baseRequestOptions.diskCacheStrategy;
                                    try {
                                        Map<Class<?>, Transformation<?>> map = baseRequestOptions.transformations;
                                        boolean z2 = baseRequestOptions.isTransformationRequired;
                                        boolean zIsScaleOnlyOrNoTransform = baseRequestOptions.isScaleOnlyOrNoTransform();
                                        BaseRequestOptions<?> baseRequestOptions2 = singleRequest.requestOptions;
                                        try {
                                            Options options = baseRequestOptions2.options;
                                            boolean z3 = baseRequestOptions2.isCacheable;
                                            boolean z4 = baseRequestOptions2.useUnlimitedSourceGeneratorsPool;
                                            boolean z5 = baseRequestOptions2.useAnimationPool;
                                            boolean z6 = baseRequestOptions2.onlyRetrieveFromCache;
                                            Executor executor = singleRequest.callbackExecutor;
                                            Object obj4 = obj2;
                                            try {
                                                singleRequest.loadStatus = engine.load(glideContext, obj3, key, i3, i4, cls, cls2, priority, diskCacheStrategy, map, z2, zIsScaleOnlyOrNoTransform, options, z3, z4, z5, z6, singleRequest, executor);
                                                if (singleRequest.status != status) {
                                                    singleRequest.loadStatus = null;
                                                }
                                                if (z) {
                                                    singleRequest.logV("finished onSizeReady in " + LogTime.getElapsedMillis(singleRequest.startTime));
                                                }
                                            } catch (Throwable th) {
                                                th = th;
                                                obj = obj4;
                                                throw th;
                                            }
                                        } catch (Throwable th2) {
                                            th = th2;
                                            obj = obj2;
                                        }
                                    } catch (Throwable th3) {
                                        th = th3;
                                        obj = obj2;
                                    }
                                } catch (Throwable th4) {
                                    th = th4;
                                    obj = obj2;
                                }
                            } catch (Throwable th5) {
                                th = th5;
                                obj = obj2;
                            }
                        } catch (Throwable th6) {
                            th = th6;
                            obj = obj2;
                        }
                    }
                } catch (Throwable th7) {
                    th = th7;
                    obj = singleRequest;
                }
            } catch (Throwable th8) {
                th = th8;
                obj = obj2;
            }
        }
    }

    @Override // com.bumptech.glide.request.Request
    public void pause() {
        synchronized (this.requestLock) {
            try {
                if (isRunning()) {
                    clear();
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    @GuardedBy("requestLock")
    public final void setErrorPlaceholder() {
        if (canNotifyStatusChanged()) {
            Drawable fallbackDrawable = this.model == null ? getFallbackDrawable() : null;
            if (fallbackDrawable == null) {
                fallbackDrawable = getErrorDrawable();
            }
            if (fallbackDrawable == null) {
                fallbackDrawable = getPlaceholderDrawable();
            }
            this.target.onLoadFailed(fallbackDrawable);
        }
    }

    public final void onLoadFailed(GlideException glideException, int i) {
        this.stateVerifier.throwIfRecycled();
        synchronized (this.requestLock) {
            try {
                glideException.exception = this.requestOrigin;
                int logLevel = this.glideContext.getLogLevel();
                if (logLevel <= i) {
                    Log.w("Glide", "Load failed for " + this.model + " with size [" + this.width + "x" + this.height + "]", glideException);
                    if (logLevel <= 4) {
                        glideException.logRootCauses("Glide");
                    }
                }
                this.loadStatus = null;
                this.status = Status.FAILED;
                this.isCallingCallbacks = true;
                try {
                    List<RequestListener<R>> list = this.requestListeners;
                    if (list != null) {
                        Iterator<RequestListener<R>> it = list.iterator();
                        while (it.hasNext()) {
                            it.next().onLoadFailed(glideException, this.model, this.target, isFirstReadyResource());
                        }
                    }
                    RequestListener<R> requestListener = this.targetListener;
                    if (requestListener != null) {
                        requestListener.onLoadFailed(glideException, this.model, this.target, isFirstReadyResource());
                    }
                    setErrorPlaceholder();
                    this.isCallingCallbacks = false;
                    notifyLoadFailed();
                } catch (Throwable th) {
                    this.isCallingCallbacks = false;
                    throw th;
                }
            } catch (Throwable th2) {
                throw th2;
            }
        }
    }

    @GuardedBy("requestLock")
    public final void onResourceReady(Resource<R> resource, R r, DataSource dataSource) {
        boolean zIsFirstReadyResource = isFirstReadyResource();
        this.status = Status.COMPLETE;
        this.resource = resource;
        if (this.glideContext.getLogLevel() <= 3) {
            Log.d("Glide", "Finished loading " + r.getClass().getSimpleName() + " from " + dataSource + " for " + this.model + " with size [" + this.width + "x" + this.height + "] in " + LogTime.getElapsedMillis(this.startTime) + " ms");
        }
        this.isCallingCallbacks = true;
        try {
            List<RequestListener<R>> list = this.requestListeners;
            if (list != null) {
                Iterator<RequestListener<R>> it = list.iterator();
                while (it.hasNext()) {
                    R r2 = r;
                    DataSource dataSource2 = dataSource;
                    it.next().onResourceReady(r2, this.model, this.target, dataSource2, zIsFirstReadyResource);
                    r = r2;
                    dataSource = dataSource2;
                }
            }
            R r3 = r;
            DataSource dataSource3 = dataSource;
            RequestListener<R> requestListener = this.targetListener;
            if (requestListener != null) {
                requestListener.onResourceReady(r3, this.model, this.target, dataSource3, zIsFirstReadyResource);
            }
            this.target.onResourceReady(r3, this.animationFactory.build(dataSource3, zIsFirstReadyResource));
            this.isCallingCallbacks = false;
            notifyLoadSuccess();
        } catch (Throwable th) {
            this.isCallingCallbacks = false;
            throw th;
        }
    }
}
