package com.bumptech.glide;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.widget.ImageView;
import androidx.annotation.CheckResult;
import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RawRes;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.BaseRequestOptions;
import com.bumptech.glide.request.ErrorRequestCoordinator;
import com.bumptech.glide.request.FutureTarget;
import com.bumptech.glide.request.Request;
import com.bumptech.glide.request.RequestCoordinator;
import com.bumptech.glide.request.RequestFutureTarget;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.SingleRequest;
import com.bumptech.glide.request.ThumbnailRequestCoordinator;
import com.bumptech.glide.request.target.PreloadTarget;
import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.request.target.ViewTarget;
import com.bumptech.glide.signature.AndroidResourceSignature;
import com.bumptech.glide.util.Executors;
import com.bumptech.glide.util.Preconditions;
import com.bumptech.glide.util.Util;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Executor;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public class RequestBuilder<TranscodeType> extends BaseRequestOptions<RequestBuilder<TranscodeType>> implements Cloneable, ModelTypes<RequestBuilder<TranscodeType>> {
    public static final RequestOptions DOWNLOAD_ONLY_OPTIONS = new RequestOptions().diskCacheStrategy(DiskCacheStrategy.DATA).priority(Priority.LOW).skipMemoryCache(true);
    public final Context context;

    @Nullable
    public RequestBuilder<TranscodeType> errorBuilder;
    public final Glide glide;
    public final GlideContext glideContext;
    public boolean isDefaultTransitionOptionsSet;
    public boolean isModelSet;
    public boolean isThumbnailBuilt;

    @Nullable
    public Object model;

    @Nullable
    public List<RequestListener<TranscodeType>> requestListeners;
    public final RequestManager requestManager;

    @Nullable
    public Float thumbSizeMultiplier;

    @Nullable
    public RequestBuilder<TranscodeType> thumbnailBuilder;
    public final Class<TranscodeType> transcodeClass;

    @NonNull
    public TransitionOptions<?, ? super TranscodeType> transitionOptions;

    /* JADX INFO: renamed from: com.bumptech.glide.RequestBuilder$1, reason: invalid class name */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static /* synthetic */ class AnonymousClass1 {
        public static final /* synthetic */ int[] $SwitchMap$android$widget$ImageView$ScaleType;
        public static final /* synthetic */ int[] $SwitchMap$com$bumptech$glide$Priority;

        static {
            int[] iArr = new int[Priority.values().length];
            $SwitchMap$com$bumptech$glide$Priority = iArr;
            try {
                iArr[Priority.LOW.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$bumptech$glide$Priority[Priority.NORMAL.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$bumptech$glide$Priority[Priority.HIGH.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$bumptech$glide$Priority[Priority.IMMEDIATE.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            int[] iArr2 = new int[ImageView.ScaleType.values().length];
            $SwitchMap$android$widget$ImageView$ScaleType = iArr2;
            try {
                iArr2[ImageView.ScaleType.CENTER_CROP.ordinal()] = 1;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$android$widget$ImageView$ScaleType[ImageView.ScaleType.CENTER_INSIDE.ordinal()] = 2;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$android$widget$ImageView$ScaleType[ImageView.ScaleType.FIT_CENTER.ordinal()] = 3;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$android$widget$ImageView$ScaleType[ImageView.ScaleType.FIT_START.ordinal()] = 4;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                $SwitchMap$android$widget$ImageView$ScaleType[ImageView.ScaleType.FIT_END.ordinal()] = 5;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                $SwitchMap$android$widget$ImageView$ScaleType[ImageView.ScaleType.FIT_XY.ordinal()] = 6;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                $SwitchMap$android$widget$ImageView$ScaleType[ImageView.ScaleType.CENTER.ordinal()] = 7;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                $SwitchMap$android$widget$ImageView$ScaleType[ImageView.ScaleType.MATRIX.ordinal()] = 8;
            } catch (NoSuchFieldError unused12) {
            }
        }
    }

    @SuppressLint({"CheckResult"})
    public RequestBuilder(@NonNull Glide glide, RequestManager requestManager, Class<TranscodeType> cls, Context context) {
        this.isDefaultTransitionOptionsSet = true;
        this.glide = glide;
        this.requestManager = requestManager;
        this.transcodeClass = cls;
        this.context = context;
        this.transitionOptions = requestManager.getDefaultTransitionOptions(cls);
        this.glideContext = glide.getGlideContext();
        initRequestListeners(requestManager.getDefaultRequestListeners());
        apply((BaseRequestOptions<?>) requestManager.getDefaultRequestOptions());
    }

    @NonNull
    @CheckResult
    public RequestBuilder<TranscodeType> addListener(@Nullable RequestListener<TranscodeType> requestListener) {
        if (requestListener != null) {
            if (this.requestListeners == null) {
                this.requestListeners = new ArrayList();
            }
            this.requestListeners.add(requestListener);
        }
        return this;
    }

    @Override // com.bumptech.glide.request.BaseRequestOptions
    @NonNull
    @CheckResult
    public /* bridge */ /* synthetic */ BaseRequestOptions apply(@NonNull BaseRequestOptions baseRequestOptions) {
        return apply((BaseRequestOptions<?>) baseRequestOptions);
    }

    public final Request buildRequest(Target<TranscodeType> target, @Nullable RequestListener<TranscodeType> requestListener, BaseRequestOptions<?> baseRequestOptions, Executor executor) {
        return buildRequestRecursive(new Object(), target, requestListener, null, this.transitionOptions, baseRequestOptions.priority, baseRequestOptions.overrideWidth, baseRequestOptions.overrideHeight, baseRequestOptions, executor);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    public final Request buildRequestRecursive(Object obj, Target<TranscodeType> target, @Nullable RequestListener<TranscodeType> requestListener, @Nullable RequestCoordinator requestCoordinator, TransitionOptions<?, ? super TranscodeType> transitionOptions, Priority priority, int i, int i2, BaseRequestOptions<?> baseRequestOptions, Executor executor) {
        RequestCoordinator requestCoordinator2;
        RequestCoordinator errorRequestCoordinator;
        Target<TranscodeType> target2;
        RequestListener<TranscodeType> requestListener2;
        TransitionOptions<?, ? super TranscodeType> transitionOptions2;
        Priority priority2;
        int i3;
        int i4;
        BaseRequestOptions<?> baseRequestOptions2;
        Executor executor2;
        RequestBuilder<TranscodeType> requestBuilder;
        Object obj2;
        if (this.errorBuilder != null) {
            errorRequestCoordinator = new ErrorRequestCoordinator(obj, requestCoordinator);
            requestCoordinator2 = errorRequestCoordinator;
            obj2 = obj;
            target2 = target;
            requestListener2 = requestListener;
            transitionOptions2 = transitionOptions;
            priority2 = priority;
            i3 = i;
            i4 = i2;
            baseRequestOptions2 = baseRequestOptions;
            executor2 = executor;
            requestBuilder = this;
        } else {
            requestCoordinator2 = null;
            errorRequestCoordinator = requestCoordinator;
            target2 = target;
            requestListener2 = requestListener;
            transitionOptions2 = transitionOptions;
            priority2 = priority;
            i3 = i;
            i4 = i2;
            baseRequestOptions2 = baseRequestOptions;
            executor2 = executor;
            requestBuilder = this;
            obj2 = obj;
        }
        Request requestBuildThumbnailRequestRecursive = requestBuilder.buildThumbnailRequestRecursive(obj2, target2, requestListener2, errorRequestCoordinator, transitionOptions2, priority2, i3, i4, baseRequestOptions2, executor2);
        if (requestCoordinator2 == null) {
            return requestBuildThumbnailRequestRecursive;
        }
        RequestBuilder<TranscodeType> requestBuilder2 = this.errorBuilder;
        int i5 = requestBuilder2.overrideWidth;
        int i6 = requestBuilder2.overrideHeight;
        if (Util.isValidDimensions(i, i2) && !this.errorBuilder.isValidOverride()) {
            i5 = baseRequestOptions2.overrideWidth;
            i6 = baseRequestOptions2.overrideHeight;
        }
        int i7 = i6;
        RequestBuilder requestBuilder3 = (RequestBuilder<TranscodeType>) this.errorBuilder;
        ErrorRequestCoordinator errorRequestCoordinator2 = requestCoordinator2;
        Request requestBuildRequestRecursive = requestBuilder3.buildRequestRecursive(obj, target, requestListener, errorRequestCoordinator2, requestBuilder3.transitionOptions, requestBuilder3.priority, i5, i7, requestBuilder3, executor);
        errorRequestCoordinator2.primary = requestBuildThumbnailRequestRecursive;
        errorRequestCoordinator2.error = requestBuildRequestRecursive;
        return errorRequestCoordinator2;
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
    public final Request buildThumbnailRequestRecursive(Object obj, Target<TranscodeType> target, RequestListener<TranscodeType> requestListener, @Nullable RequestCoordinator requestCoordinator, TransitionOptions<?, ? super TranscodeType> transitionOptions, Priority priority, int i, int i2, BaseRequestOptions<?> baseRequestOptions, Executor executor) {
        RequestBuilder<TranscodeType> requestBuilder = this.thumbnailBuilder;
        if (requestBuilder == null) {
            if (this.thumbSizeMultiplier == null) {
                return obtainRequest(obj, target, requestListener, baseRequestOptions, requestCoordinator, transitionOptions, priority, i, i2, executor);
            }
            ThumbnailRequestCoordinator thumbnailRequestCoordinator = new ThumbnailRequestCoordinator(obj, requestCoordinator);
            Request requestObtainRequest = obtainRequest(obj, target, requestListener, baseRequestOptions, thumbnailRequestCoordinator, transitionOptions, priority, i, i2, executor);
            Request requestObtainRequest2 = obtainRequest(obj, target, requestListener, baseRequestOptions.mo346clone().sizeMultiplier(this.thumbSizeMultiplier.floatValue()), thumbnailRequestCoordinator, transitionOptions, getThumbnailPriority(priority), i, i2, executor);
            thumbnailRequestCoordinator.full = requestObtainRequest;
            thumbnailRequestCoordinator.thumb = requestObtainRequest2;
            return thumbnailRequestCoordinator;
        }
        if (this.isThumbnailBuilt) {
            throw new IllegalStateException("You cannot use a request as both the main request and a thumbnail, consider using clone() on the request(s) passed to thumbnail()");
        }
        TransitionOptions<?, ? super TranscodeType> transitionOptions2 = requestBuilder.isDefaultTransitionOptionsSet ? transitionOptions : requestBuilder.transitionOptions;
        Priority thumbnailPriority = requestBuilder.isPrioritySet() ? this.thumbnailBuilder.priority : getThumbnailPriority(priority);
        RequestBuilder<TranscodeType> requestBuilder2 = this.thumbnailBuilder;
        int i3 = requestBuilder2.overrideWidth;
        int i4 = requestBuilder2.overrideHeight;
        if (Util.isValidDimensions(i, i2) && !this.thumbnailBuilder.isValidOverride()) {
            i3 = baseRequestOptions.overrideWidth;
            i4 = baseRequestOptions.overrideHeight;
        }
        int i5 = i4;
        ThumbnailRequestCoordinator thumbnailRequestCoordinator2 = new ThumbnailRequestCoordinator(obj, requestCoordinator);
        Request requestObtainRequest3 = obtainRequest(obj, target, requestListener, baseRequestOptions, thumbnailRequestCoordinator2, transitionOptions, priority, i, i2, executor);
        this.isThumbnailBuilt = true;
        RequestBuilder requestBuilder3 = (RequestBuilder<TranscodeType>) this.thumbnailBuilder;
        Request requestBuildRequestRecursive = requestBuilder3.buildRequestRecursive(obj, target, requestListener, thumbnailRequestCoordinator2, transitionOptions2, thumbnailPriority, i3, i5, requestBuilder3, executor);
        this.isThumbnailBuilt = false;
        thumbnailRequestCoordinator2.full = requestObtainRequest3;
        thumbnailRequestCoordinator2.thumb = requestBuildRequestRecursive;
        return thumbnailRequestCoordinator2;
    }

    @CheckResult
    @Deprecated
    public <Y extends Target<File>> Y downloadOnly(@NonNull Y y) {
        return (Y) getDownloadOnlyRequest().into(y);
    }

    @NonNull
    public RequestBuilder<TranscodeType> error(@Nullable RequestBuilder<TranscodeType> requestBuilder) {
        this.errorBuilder = requestBuilder;
        return this;
    }

    @NonNull
    @CheckResult
    public RequestBuilder<File> getDownloadOnlyRequest() {
        return new RequestBuilder(File.class, this).apply((BaseRequestOptions<?>) DOWNLOAD_ONLY_OPTIONS);
    }

    @NonNull
    public final Priority getThumbnailPriority(@NonNull Priority priority) {
        int i = AnonymousClass1.$SwitchMap$com$bumptech$glide$Priority[priority.ordinal()];
        if (i == 1) {
            return Priority.NORMAL;
        }
        if (i == 2) {
            return Priority.HIGH;
        }
        if (i == 3 || i == 4) {
            return Priority.IMMEDIATE;
        }
        throw new IllegalArgumentException("unknown priority: " + this.priority);
    }

    @SuppressLint({"CheckResult"})
    public final void initRequestListeners(List<RequestListener<Object>> list) {
        Iterator<RequestListener<Object>> it = list.iterator();
        while (it.hasNext()) {
            addListener((RequestListener) it.next());
        }
    }

    @NonNull
    public <Y extends Target<TranscodeType>> Y into(@NonNull Y y) {
        return (Y) into(y, null, Executors.mainThreadExecutor());
    }

    public final boolean isSkipMemoryCacheWithCompletePreviousRequest(BaseRequestOptions<?> baseRequestOptions, Request request) {
        return !baseRequestOptions.isCacheable && request.isComplete();
    }

    @NonNull
    @CheckResult
    public RequestBuilder<TranscodeType> listener(@Nullable RequestListener<TranscodeType> requestListener) {
        this.requestListeners = null;
        return addListener(requestListener);
    }

    @NonNull
    public final RequestBuilder<TranscodeType> loadGeneric(@Nullable Object obj) {
        this.model = obj;
        this.isModelSet = true;
        return this;
    }

    public final Request obtainRequest(Object obj, Target<TranscodeType> target, RequestListener<TranscodeType> requestListener, BaseRequestOptions<?> baseRequestOptions, RequestCoordinator requestCoordinator, TransitionOptions<?, ? super TranscodeType> transitionOptions, Priority priority, int i, int i2, Executor executor) {
        Context context = this.context;
        GlideContext glideContext = this.glideContext;
        return SingleRequest.obtain(context, glideContext, obj, this.model, this.transcodeClass, baseRequestOptions, i, i2, priority, target, requestListener, this.requestListeners, requestCoordinator, glideContext.getEngine(), transitionOptions.transitionFactory, executor);
    }

    @NonNull
    public Target<TranscodeType> preload(int i, int i2) {
        return into(PreloadTarget.obtain(this.requestManager, i, i2));
    }

    @NonNull
    public FutureTarget<TranscodeType> submit() {
        return submit(Integer.MIN_VALUE, Integer.MIN_VALUE);
    }

    @NonNull
    @CheckResult
    public RequestBuilder<TranscodeType> thumbnail(@Nullable RequestBuilder<TranscodeType> requestBuilder) {
        this.thumbnailBuilder = requestBuilder;
        return this;
    }

    @NonNull
    @CheckResult
    public RequestBuilder<TranscodeType> transition(@NonNull TransitionOptions<?, ? super TranscodeType> transitionOptions) {
        Preconditions.checkNotNull(transitionOptions, "Argument must not be null");
        this.transitionOptions = transitionOptions;
        this.isDefaultTransitionOptionsSet = false;
        return this;
    }

    @Override // com.bumptech.glide.request.BaseRequestOptions
    @NonNull
    @CheckResult
    public RequestBuilder<TranscodeType> apply(@NonNull BaseRequestOptions<?> baseRequestOptions) {
        Preconditions.checkNotNull(baseRequestOptions);
        return (RequestBuilder) super.apply(baseRequestOptions);
    }

    @CheckResult
    @Deprecated
    public FutureTarget<File> downloadOnly(int i, int i2) {
        return getDownloadOnlyRequest().submit(i, i2);
    }

    @NonNull
    public <Y extends Target<TranscodeType>> Y into(@NonNull Y y, @Nullable RequestListener<TranscodeType> requestListener, Executor executor) {
        into(y, requestListener, this, executor);
        return y;
    }

    @NonNull
    public FutureTarget<TranscodeType> submit(int i, int i2) {
        RequestFutureTarget requestFutureTarget = new RequestFutureTarget(i, i2);
        return (FutureTarget) into(requestFutureTarget, requestFutureTarget, Executors.directExecutor());
    }

    @NonNull
    @CheckResult
    public RequestBuilder<TranscodeType> thumbnail(@Nullable RequestBuilder<TranscodeType>... requestBuilderArr) {
        RequestBuilder<TranscodeType> requestBuilderThumbnail = null;
        if (requestBuilderArr == null || requestBuilderArr.length == 0) {
            return thumbnail((RequestBuilder) null);
        }
        for (int length = requestBuilderArr.length - 1; length >= 0; length--) {
            RequestBuilder<TranscodeType> requestBuilder = requestBuilderArr[length];
            if (requestBuilder != null) {
                requestBuilderThumbnail = requestBuilderThumbnail == null ? requestBuilder : requestBuilder.thumbnail(requestBuilderThumbnail);
            }
        }
        return thumbnail(requestBuilderThumbnail);
    }

    @Override // com.bumptech.glide.request.BaseRequestOptions
    @CheckResult
    /* JADX INFO: renamed from: clone, reason: merged with bridge method [inline-methods] */
    public RequestBuilder<TranscodeType> mo346clone() {
        RequestBuilder<TranscodeType> requestBuilder = (RequestBuilder) super.mo346clone();
        requestBuilder.transitionOptions = requestBuilder.transitionOptions.m347clone();
        return requestBuilder;
    }

    public final <Y extends Target<TranscodeType>> Y into(@NonNull Y y, @Nullable RequestListener<TranscodeType> requestListener, BaseRequestOptions<?> baseRequestOptions, Executor executor) {
        Preconditions.checkNotNull(y);
        if (this.isModelSet) {
            Request requestBuildRequest = buildRequest(y, requestListener, baseRequestOptions, executor);
            Request request = y.getRequest();
            if (requestBuildRequest.isEquivalentTo(request) && !isSkipMemoryCacheWithCompletePreviousRequest(baseRequestOptions, request)) {
                Preconditions.checkNotNull(request, "Argument must not be null");
                if (!request.isRunning()) {
                    request.begin();
                }
                return y;
            }
            this.requestManager.clear((Target<?>) y);
            y.setRequest(requestBuildRequest);
            this.requestManager.track(y, requestBuildRequest);
            return y;
        }
        throw new IllegalArgumentException("You must call #load() before calling #into()");
    }

    @NonNull
    public Target<TranscodeType> preload() {
        return preload(Integer.MIN_VALUE, Integer.MIN_VALUE);
    }

    @NonNull
    @CheckResult
    public RequestBuilder<TranscodeType> thumbnail(float f) {
        if (f >= 0.0f && f <= 1.0f) {
            this.thumbSizeMultiplier = Float.valueOf(f);
            return this;
        }
        throw new IllegalArgumentException("sizeMultiplier must be between 0 and 1");
    }

    @Override // com.bumptech.glide.ModelTypes
    @NonNull
    @CheckResult
    public RequestBuilder<TranscodeType> load(@Nullable Bitmap bitmap) {
        this.model = bitmap;
        this.isModelSet = true;
        return apply((BaseRequestOptions<?>) RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.NONE));
    }

    @SuppressLint({"CheckResult"})
    public RequestBuilder(Class<TranscodeType> cls, RequestBuilder<?> requestBuilder) {
        this(requestBuilder.glide, requestBuilder.requestManager, cls, requestBuilder.context);
        this.model = requestBuilder.model;
        this.isModelSet = requestBuilder.isModelSet;
        apply((BaseRequestOptions<?>) requestBuilder);
    }

    @Override // com.bumptech.glide.ModelTypes
    @NonNull
    @CheckResult
    public RequestBuilder<TranscodeType> load(@Nullable Drawable drawable) {
        this.model = drawable;
        this.isModelSet = true;
        return apply((BaseRequestOptions<?>) RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.NONE));
    }

    @NonNull
    public ViewTarget<ImageView, TranscodeType> into(@NonNull ImageView imageView) {
        BaseRequestOptions baseRequestOptionsOptionalCenterCrop;
        Util.assertMainThread();
        Preconditions.checkNotNull(imageView);
        if (!isTransformationSet() && this.isTransformationAllowed && imageView.getScaleType() != null) {
            switch (AnonymousClass1.$SwitchMap$android$widget$ImageView$ScaleType[imageView.getScaleType().ordinal()]) {
                case 1:
                    baseRequestOptionsOptionalCenterCrop = mo346clone().optionalCenterCrop();
                    break;
                case 2:
                    baseRequestOptionsOptionalCenterCrop = mo346clone().optionalCenterInside();
                    break;
                case 3:
                case 4:
                case 5:
                    baseRequestOptionsOptionalCenterCrop = mo346clone().optionalFitCenter();
                    break;
                case 6:
                    baseRequestOptionsOptionalCenterCrop = mo346clone().optionalCenterInside();
                    break;
                default:
                    baseRequestOptionsOptionalCenterCrop = this;
                    break;
            }
        } else {
            baseRequestOptionsOptionalCenterCrop = this;
        }
        ViewTarget<ImageView, TranscodeType> viewTargetBuildImageViewTarget = this.glideContext.buildImageViewTarget(imageView, this.transcodeClass);
        into(viewTargetBuildImageViewTarget, null, baseRequestOptionsOptionalCenterCrop, Executors.mainThreadExecutor());
        return viewTargetBuildImageViewTarget;
    }

    @Override // com.bumptech.glide.ModelTypes
    @NonNull
    @CheckResult
    public RequestBuilder<TranscodeType> load(@Nullable Uri uri) {
        this.model = uri;
        this.isModelSet = true;
        return this;
    }

    @Override // com.bumptech.glide.ModelTypes
    @NonNull
    @CheckResult
    public RequestBuilder<TranscodeType> load(@Nullable File file) {
        this.model = file;
        this.isModelSet = true;
        return this;
    }

    @Override // com.bumptech.glide.ModelTypes
    @NonNull
    @CheckResult
    public RequestBuilder<TranscodeType> load(@Nullable @DrawableRes @RawRes Integer num) {
        this.model = num;
        this.isModelSet = true;
        return apply((BaseRequestOptions<?>) RequestOptions.signatureOf(AndroidResourceSignature.obtain(this.context)));
    }

    @Override // com.bumptech.glide.ModelTypes
    @NonNull
    @CheckResult
    public RequestBuilder<TranscodeType> load(@Nullable Object obj) {
        this.model = obj;
        this.isModelSet = true;
        return this;
    }

    @Override // com.bumptech.glide.ModelTypes
    @NonNull
    @CheckResult
    public RequestBuilder<TranscodeType> load(@Nullable String str) {
        this.model = str;
        this.isModelSet = true;
        return this;
    }

    @Override // com.bumptech.glide.ModelTypes
    @CheckResult
    @Deprecated
    public RequestBuilder<TranscodeType> load(@Nullable URL url) {
        this.model = url;
        this.isModelSet = true;
        return this;
    }

    @Override // com.bumptech.glide.ModelTypes
    @NonNull
    @CheckResult
    public RequestBuilder<TranscodeType> load(@Nullable byte[] bArr) {
        this.model = bArr;
        this.isModelSet = true;
        RequestBuilder<TranscodeType> requestBuilderApply = !isDiskCacheStrategySet() ? apply((BaseRequestOptions<?>) RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.NONE)) : this;
        return !requestBuilderApply.isSkipMemoryCacheSet() ? requestBuilderApply.apply((BaseRequestOptions<?>) RequestOptions.skipMemoryCacheOf(true)) : requestBuilderApply;
    }

    @Deprecated
    public FutureTarget<TranscodeType> into(int i, int i2) {
        return submit(i, i2);
    }
}
