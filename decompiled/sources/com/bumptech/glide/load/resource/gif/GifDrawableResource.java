package com.bumptech.glide.load.resource.gif;

import androidx.annotation.NonNull;
import com.bumptech.glide.load.engine.Initializable;
import com.bumptech.glide.load.resource.drawable.DrawableResource;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public class GifDrawableResource extends DrawableResource<GifDrawable> implements Initializable {
    public GifDrawableResource(GifDrawable gifDrawable) {
        super(gifDrawable);
    }

    @Override // com.bumptech.glide.load.engine.Resource
    @NonNull
    public Class<GifDrawable> getResourceClass() {
        return GifDrawable.class;
    }

    @Override // com.bumptech.glide.load.engine.Resource
    public int getSize() {
        return ((GifDrawable) this.drawable).getSize();
    }

    @Override // com.bumptech.glide.load.resource.drawable.DrawableResource, com.bumptech.glide.load.engine.Initializable
    public void initialize() {
        ((GifDrawable) this.drawable).getFirstFrame().prepareToDraw();
    }

    @Override // com.bumptech.glide.load.engine.Resource
    public void recycle() {
        ((GifDrawable) this.drawable).stop();
        ((GifDrawable) this.drawable).recycle();
    }
}
