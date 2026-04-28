# Razorpay Proguard Rules
-keep class com.razorpay.** {*;}
-dontwarn com.razorpay.**

# Hilt Proguard Rules
-keep class dagger.hilt.android.internal.** { *; }

# AdMob Proguard Rules
-keep class com.google.android.gms.ads.** { *; }
-keep class com.google.ads.** { *; }
