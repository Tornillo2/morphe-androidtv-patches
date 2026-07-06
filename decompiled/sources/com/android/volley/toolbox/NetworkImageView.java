package com.android.volley.toolbox;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ImageView;
import androidx.annotation.MainThread;
import androidx.annotation.Nullable;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public class NetworkImageView extends ImageView {

    @Nullable
    public Bitmap mDefaultImageBitmap;

    @Nullable
    public Drawable mDefaultImageDrawable;
    public int mDefaultImageId;

    @Nullable
    public Bitmap mErrorImageBitmap;

    @Nullable
    public Drawable mErrorImageDrawable;
    public int mErrorImageId;
    public ImageLoader.ImageContainer mImageContainer;
    public ImageLoader mImageLoader;
    public String mUrl;

    /* JADX INFO: renamed from: com.android.volley.toolbox.NetworkImageView$1, reason: invalid class name */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public class AnonymousClass1 implements ImageLoader.ImageListener {
        public final /* synthetic */ boolean val$isInLayoutPass;

        public AnonymousClass1(boolean z) {
            this.val$isInLayoutPass = z;
        }

        @Override // com.android.volley.Response.ErrorListener
        public void onErrorResponse(VolleyError volleyError) {
            if (NetworkImageView.this.mErrorImageId != 0) {
                NetworkImageView networkImageView = NetworkImageView.this;
                networkImageView.setImageResource(networkImageView.mErrorImageId);
                return;
            }
            NetworkImageView networkImageView2 = NetworkImageView.this;
            Drawable drawable = networkImageView2.mErrorImageDrawable;
            if (drawable != null) {
                networkImageView2.setImageDrawable(drawable);
                return;
            }
            Bitmap bitmap = networkImageView2.mErrorImageBitmap;
            if (bitmap != null) {
                networkImageView2.setImageBitmap(bitmap);
            }
        }

        @Override // com.android.volley.toolbox.ImageLoader.ImageListener
        public void onResponse(final ImageLoader.ImageContainer imageContainer, boolean z) {
            if (z && this.val$isInLayoutPass) {
                NetworkImageView.this.post(new Runnable() { // from class: com.android.volley.toolbox.NetworkImageView.1.1
                    @Override // java.lang.Runnable
                    public void run() {
                        AnonymousClass1.this.onResponse(imageContainer, false);
                    }
                });
                return;
            }
            if (imageContainer.getBitmap() != null) {
                NetworkImageView.this.setImageBitmap(imageContainer.getBitmap());
                return;
            }
            if (NetworkImageView.this.mDefaultImageId != 0) {
                NetworkImageView networkImageView = NetworkImageView.this;
                networkImageView.setImageResource(networkImageView.mDefaultImageId);
                return;
            }
            NetworkImageView networkImageView2 = NetworkImageView.this;
            Drawable drawable = networkImageView2.mDefaultImageDrawable;
            if (drawable != null) {
                networkImageView2.setImageDrawable(drawable);
                return;
            }
            Bitmap bitmap = networkImageView2.mDefaultImageBitmap;
            if (bitmap != null) {
                networkImageView2.setImageBitmap(bitmap);
            }
        }
    }

    public NetworkImageView(Context context) {
        super(context, null, 0);
    }

    @Override // android.widget.ImageView, android.view.View
    public void drawableStateChanged() {
        super.drawableStateChanged();
        invalidate();
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x0031  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x0041  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x004f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void loadImageIfNecessary(boolean r9) {
        /*
            r8 = this;
            int r0 = r8.getWidth()
            int r1 = r8.getHeight()
            android.widget.ImageView$ScaleType r7 = r8.getScaleType()
            android.view.ViewGroup$LayoutParams r2 = r8.getLayoutParams()
            r3 = 1
            r4 = 0
            if (r2 == 0) goto L2a
            android.view.ViewGroup$LayoutParams r2 = r8.getLayoutParams()
            int r2 = r2.width
            r5 = -2
            if (r2 != r5) goto L1f
            r2 = 1
            goto L20
        L1f:
            r2 = 0
        L20:
            android.view.ViewGroup$LayoutParams r6 = r8.getLayoutParams()
            int r6 = r6.height
            if (r6 != r5) goto L2b
            r5 = 1
            goto L2c
        L2a:
            r2 = 0
        L2b:
            r5 = 0
        L2c:
            if (r2 == 0) goto L31
            if (r5 == 0) goto L31
            goto L32
        L31:
            r3 = 0
        L32:
            if (r0 != 0) goto L39
            if (r1 != 0) goto L39
            if (r3 != 0) goto L39
            goto L67
        L39:
            java.lang.String r3 = r8.mUrl
            boolean r3 = android.text.TextUtils.isEmpty(r3)
            if (r3 == 0) goto L4f
            com.android.volley.toolbox.ImageLoader$ImageContainer r9 = r8.mImageContainer
            if (r9 == 0) goto L4b
            r9.cancelRequest()
            r9 = 0
            r8.mImageContainer = r9
        L4b:
            r8.setDefaultImageOrNull()
            return
        L4f:
            com.android.volley.toolbox.ImageLoader$ImageContainer r3 = r8.mImageContainer
            if (r3 == 0) goto L70
            java.lang.String r3 = r3.getRequestUrl()
            if (r3 == 0) goto L70
            com.android.volley.toolbox.ImageLoader$ImageContainer r3 = r8.mImageContainer
            java.lang.String r3 = r3.getRequestUrl()
            java.lang.String r6 = r8.mUrl
            boolean r3 = r3.equals(r6)
            if (r3 == 0) goto L68
        L67:
            return
        L68:
            com.android.volley.toolbox.ImageLoader$ImageContainer r3 = r8.mImageContainer
            r3.cancelRequest()
            r8.setDefaultImageOrNull()
        L70:
            if (r2 == 0) goto L73
            r0 = 0
        L73:
            if (r5 == 0) goto L77
            r6 = 0
            goto L78
        L77:
            r6 = r1
        L78:
            com.android.volley.toolbox.ImageLoader r2 = r8.mImageLoader
            java.lang.String r3 = r8.mUrl
            com.android.volley.toolbox.NetworkImageView$1 r4 = new com.android.volley.toolbox.NetworkImageView$1
            r4.<init>(r9)
            r5 = r0
            com.android.volley.toolbox.ImageLoader$ImageContainer r9 = r2.get(r3, r4, r5, r6, r7)
            r8.mImageContainer = r9
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.volley.toolbox.NetworkImageView.loadImageIfNecessary(boolean):void");
    }

    @Override // android.widget.ImageView, android.view.View
    public void onDetachedFromWindow() {
        ImageLoader.ImageContainer imageContainer = this.mImageContainer;
        if (imageContainer != null) {
            imageContainer.cancelRequest();
            setImageBitmap(null);
            this.mImageContainer = null;
        }
        super.onDetachedFromWindow();
    }

    @Override // android.view.View
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        loadImageIfNecessary(true);
    }

    public void setDefaultImageBitmap(Bitmap bitmap) {
        this.mDefaultImageId = 0;
        this.mDefaultImageDrawable = null;
        this.mDefaultImageBitmap = bitmap;
    }

    public void setDefaultImageDrawable(@Nullable Drawable drawable) {
        this.mDefaultImageId = 0;
        this.mDefaultImageBitmap = null;
        this.mDefaultImageDrawable = drawable;
    }

    public final void setDefaultImageOrNull() {
        int i = this.mDefaultImageId;
        if (i != 0) {
            setImageResource(i);
            return;
        }
        Drawable drawable = this.mDefaultImageDrawable;
        if (drawable != null) {
            setImageDrawable(drawable);
            return;
        }
        Bitmap bitmap = this.mDefaultImageBitmap;
        if (bitmap != null) {
            setImageBitmap(bitmap);
        } else {
            setImageBitmap(null);
        }
    }

    public void setDefaultImageResId(int i) {
        this.mDefaultImageBitmap = null;
        this.mDefaultImageDrawable = null;
        this.mDefaultImageId = i;
    }

    public void setErrorImageBitmap(Bitmap bitmap) {
        this.mErrorImageId = 0;
        this.mErrorImageDrawable = null;
        this.mErrorImageBitmap = bitmap;
    }

    public void setErrorImageDrawable(@Nullable Drawable drawable) {
        this.mErrorImageId = 0;
        this.mErrorImageBitmap = null;
        this.mErrorImageDrawable = drawable;
    }

    public void setErrorImageResId(int i) {
        this.mErrorImageBitmap = null;
        this.mErrorImageDrawable = null;
        this.mErrorImageId = i;
    }

    @MainThread
    public void setImageUrl(String str, ImageLoader imageLoader) {
        Threads.throwIfNotOnMainThread();
        this.mUrl = str;
        this.mImageLoader = imageLoader;
        loadImageIfNecessary(false);
    }

    public NetworkImageView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet, 0);
    }

    public NetworkImageView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }
}
