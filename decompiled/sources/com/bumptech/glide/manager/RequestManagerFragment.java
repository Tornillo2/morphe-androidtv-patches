package com.bumptech.glide.manager;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Fragment;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import j$.util.DesugarCollections;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@Deprecated
public class RequestManagerFragment extends Fragment {
    public static final String TAG = "RMFragment";
    public final Set<RequestManagerFragment> childRequestManagerFragments;
    public final ActivityFragmentLifecycle lifecycle;

    @Nullable
    public Fragment parentFragmentHint;

    @Nullable
    public RequestManager requestManager;
    public final RequestManagerTreeNode requestManagerTreeNode;

    @Nullable
    public RequestManagerFragment rootRequestManagerFragment;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public class FragmentRequestManagerTreeNode implements RequestManagerTreeNode {
        public FragmentRequestManagerTreeNode() {
        }

        @Override // com.bumptech.glide.manager.RequestManagerTreeNode
        @NonNull
        public Set<RequestManager> getDescendants() {
            Set<RequestManagerFragment> descendantRequestManagerFragments = RequestManagerFragment.this.getDescendantRequestManagerFragments();
            HashSet hashSet = new HashSet(descendantRequestManagerFragments.size());
            for (RequestManagerFragment requestManagerFragment : descendantRequestManagerFragments) {
                if (requestManagerFragment.getRequestManager() != null) {
                    hashSet.add(requestManagerFragment.getRequestManager());
                }
            }
            return hashSet;
        }

        public String toString() {
            return super.toString() + "{fragment=" + RequestManagerFragment.this + "}";
        }
    }

    public RequestManagerFragment() {
        this(new ActivityFragmentLifecycle());
    }

    public final void addChildRequestManagerFragment(RequestManagerFragment requestManagerFragment) {
        this.childRequestManagerFragments.add(requestManagerFragment);
    }

    @NonNull
    @TargetApi(17)
    public Set<RequestManagerFragment> getDescendantRequestManagerFragments() {
        if (equals(this.rootRequestManagerFragment)) {
            return DesugarCollections.unmodifiableSet(this.childRequestManagerFragments);
        }
        if (this.rootRequestManagerFragment == null) {
            return Collections.EMPTY_SET;
        }
        HashSet hashSet = new HashSet();
        for (RequestManagerFragment requestManagerFragment : this.rootRequestManagerFragment.getDescendantRequestManagerFragments()) {
            if (isDescendant(requestManagerFragment.getParentFragment())) {
                hashSet.add(requestManagerFragment);
            }
        }
        return DesugarCollections.unmodifiableSet(hashSet);
    }

    @NonNull
    public ActivityFragmentLifecycle getGlideLifecycle() {
        return this.lifecycle;
    }

    @Nullable
    @TargetApi(17)
    public final Fragment getParentFragmentUsingHint() {
        Fragment parentFragment = getParentFragment();
        return parentFragment != null ? parentFragment : this.parentFragmentHint;
    }

    @Nullable
    public RequestManager getRequestManager() {
        return this.requestManager;
    }

    @NonNull
    public RequestManagerTreeNode getRequestManagerTreeNode() {
        return this.requestManagerTreeNode;
    }

    @TargetApi(17)
    public final boolean isDescendant(@NonNull Fragment fragment) {
        Fragment parentFragment = getParentFragment();
        while (true) {
            Fragment parentFragment2 = fragment.getParentFragment();
            if (parentFragment2 == null) {
                return false;
            }
            if (parentFragment2.equals(parentFragment)) {
                return true;
            }
            fragment = fragment.getParentFragment();
        }
    }

    @Override // android.app.Fragment
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            registerFragmentWithRoot(activity);
        } catch (IllegalStateException e) {
            if (Log.isLoggable(TAG, 5)) {
                Log.w(TAG, "Unable to register fragment with root", e);
            }
        }
    }

    @Override // android.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        this.lifecycle.onDestroy();
        unregisterFragmentWithRoot();
    }

    @Override // android.app.Fragment
    public void onDetach() {
        super.onDetach();
        unregisterFragmentWithRoot();
    }

    @Override // android.app.Fragment
    public void onStart() {
        super.onStart();
        this.lifecycle.onStart();
    }

    @Override // android.app.Fragment
    public void onStop() {
        super.onStop();
        this.lifecycle.onStop();
    }

    public final void registerFragmentWithRoot(@NonNull Activity activity) {
        unregisterFragmentWithRoot();
        RequestManagerFragment requestManagerFragment = Glide.get(activity).getRequestManagerRetriever().getRequestManagerFragment(activity);
        this.rootRequestManagerFragment = requestManagerFragment;
        if (equals(requestManagerFragment)) {
            return;
        }
        this.rootRequestManagerFragment.addChildRequestManagerFragment(this);
    }

    public final void removeChildRequestManagerFragment(RequestManagerFragment requestManagerFragment) {
        this.childRequestManagerFragments.remove(requestManagerFragment);
    }

    public void setParentFragmentHint(@Nullable Fragment fragment) {
        this.parentFragmentHint = fragment;
        if (fragment == null || fragment.getActivity() == null) {
            return;
        }
        registerFragmentWithRoot(fragment.getActivity());
    }

    public void setRequestManager(@Nullable RequestManager requestManager) {
        this.requestManager = requestManager;
    }

    @Override // android.app.Fragment
    public String toString() {
        return super.toString() + "{parent=" + getParentFragmentUsingHint() + "}";
    }

    public final void unregisterFragmentWithRoot() {
        RequestManagerFragment requestManagerFragment = this.rootRequestManagerFragment;
        if (requestManagerFragment != null) {
            requestManagerFragment.removeChildRequestManagerFragment(this);
            this.rootRequestManagerFragment = null;
        }
    }

    @SuppressLint({"ValidFragment"})
    @VisibleForTesting
    public RequestManagerFragment(@NonNull ActivityFragmentLifecycle activityFragmentLifecycle) {
        this.requestManagerTreeNode = new FragmentRequestManagerTreeNode();
        this.childRequestManagerFragments = new HashSet();
        this.lifecycle = activityFragmentLifecycle;
    }
}
