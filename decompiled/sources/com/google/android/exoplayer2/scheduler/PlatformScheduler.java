package com.google.android.exoplayer2.scheduler;

import android.app.job.JobInfo;
import android.app.job.JobParameters;
import android.app.job.JobScheduler;
import android.app.job.JobService;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.PersistableBundle;
import androidx.annotation.RequiresApi;
import androidx.annotation.RequiresPermission;
import com.google.android.exoplayer2.AudioFocusManager$$ExternalSyntheticOutline0;
import com.google.android.exoplayer2.util.Log;
import com.google.android.exoplayer2.util.Util;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@RequiresApi(21)
public final class PlatformScheduler implements Scheduler {
    public static final String KEY_REQUIREMENTS = "requirements";
    public static final String KEY_SERVICE_ACTION = "service_action";
    public static final String KEY_SERVICE_PACKAGE = "service_package";
    public static final int SUPPORTED_REQUIREMENTS;
    public static final String TAG = "PlatformScheduler";
    public final int jobId;
    public final JobScheduler jobScheduler;
    public final ComponentName jobServiceComponentName;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class PlatformSchedulerService extends JobService {
        @Override // android.app.job.JobService
        public boolean onStartJob(JobParameters jobParameters) {
            PersistableBundle extras = jobParameters.getExtras();
            int notMetRequirements = new Requirements(extras.getInt("requirements")).getNotMetRequirements(this);
            if (notMetRequirements != 0) {
                AudioFocusManager$$ExternalSyntheticOutline0.m("Requirements not met: ", notMetRequirements, "PlatformScheduler");
                jobFinished(jobParameters, true);
                return false;
            }
            String string = extras.getString("service_action");
            string.getClass();
            String string2 = extras.getString("service_package");
            string2.getClass();
            Util.startForegroundService(this, new Intent(string).setPackage(string2));
            return false;
        }

        @Override // android.app.job.JobService
        public boolean onStopJob(JobParameters jobParameters) {
            return false;
        }
    }

    static {
        SUPPORTED_REQUIREMENTS = (Util.SDK_INT >= 26 ? 16 : 0) | 15;
    }

    @RequiresPermission("android.permission.RECEIVE_BOOT_COMPLETED")
    public PlatformScheduler(Context context, int i) {
        Context applicationContext = context.getApplicationContext();
        this.jobId = i;
        this.jobServiceComponentName = new ComponentName(applicationContext, (Class<?>) PlatformSchedulerService.class);
        JobScheduler jobScheduler = (JobScheduler) applicationContext.getSystemService("jobscheduler");
        jobScheduler.getClass();
        this.jobScheduler = jobScheduler;
    }

    public static JobInfo buildJobInfo(int i, ComponentName componentName, Requirements requirements, String str, String str2) {
        Requirements requirementsFilterRequirements = requirements.filterRequirements(SUPPORTED_REQUIREMENTS);
        if (!requirementsFilterRequirements.equals(requirements)) {
            Log.w("PlatformScheduler", "Ignoring unsupported requirements: " + (requirementsFilterRequirements.requirements ^ requirements.requirements));
        }
        JobInfo.Builder builder = new JobInfo.Builder(i, componentName);
        if (requirements.isUnmeteredNetworkRequired()) {
            builder.setRequiredNetworkType(2);
        } else if (requirements.isNetworkRequired()) {
            builder.setRequiredNetworkType(1);
        }
        builder.setRequiresDeviceIdle(requirements.isIdleRequired());
        builder.setRequiresCharging(requirements.isChargingRequired());
        if (Util.SDK_INT >= 26 && requirements.isStorageNotLowRequired()) {
            builder.setRequiresStorageNotLow(true);
        }
        builder.setPersisted(true);
        PersistableBundle persistableBundle = new PersistableBundle();
        persistableBundle.putString("service_action", str);
        persistableBundle.putString("service_package", str2);
        persistableBundle.putInt("requirements", requirements.requirements);
        builder.setExtras(persistableBundle);
        return builder.build();
    }

    @Override // com.google.android.exoplayer2.scheduler.Scheduler
    public boolean cancel() {
        this.jobScheduler.cancel(this.jobId);
        return true;
    }

    @Override // com.google.android.exoplayer2.scheduler.Scheduler
    public Requirements getSupportedRequirements(Requirements requirements) {
        return requirements.filterRequirements(SUPPORTED_REQUIREMENTS);
    }

    @Override // com.google.android.exoplayer2.scheduler.Scheduler
    public boolean schedule(Requirements requirements, String str, String str2) {
        return this.jobScheduler.schedule(buildJobInfo(this.jobId, this.jobServiceComponentName, requirements, str2, str)) == 1;
    }
}
