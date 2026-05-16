package patches.androidtv

import patches.core.ParameterizedFingerprint

object HideAdsFingerprint : ParameterizedFingerprint(
    "invoke-static {.*}, Lcom/google/ads/interactivemedia/v3/impl/data/zzbr;->create\\(Landroid/net/Uri;Ljava/lang/String;Ljava/lang/String;Lcom/google/ads/interactivemedia/v3/internal/zzpm;\\)Lcom/google/ads/interactivemedia/v3/impl/data/zzbr;",
    "move-result-object (\\w+)",
    "invoke-virtual {.*}, Lcom/google/ads/interactivemedia/v3/internal/zzew;->zzb\\(Landroid/content/Context;Lcom/google/ads/interactivemedia/v3/impl/data/zzbr;Lcom/google/ads/interactivemedia/v3/impl/data/TestingConfiguration;Ljava/util/concurrent/ExecutorService;\\)Lcom/google/ads/interactivemedia/v3/internal/zzev;"
)