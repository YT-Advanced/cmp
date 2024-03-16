package app.revanced.integrations.youtube.patches.utils;

import static app.revanced.integrations.youtube.utils.VideoHelpers.download;

import android.app.Activity;
import android.content.Context;

import androidx.annotation.Nullable;
import java.lang.ref.WeakReference;

import app.revanced.integrations.youtube.settings.SettingsEnum;
import app.revanced.integrations.youtube.utils.ReVancedUtils;

public class HookDownloadButtonPatch {    
    private static WeakReference<Activity> activityRef = new WeakReference<>(null);

    /**
     * Injection point.
     */
    public static void activityCreated(Activity mainActivity) {
        activityRef = new WeakReference<>(mainActivity);
    }

    private static Context getContext() {
        // If possible, use the main activity as the context.
        // Otherwise fall back on using the application context.
        Context context = activityRef.get();
        return (context == null) ? ReVancedUtils.getContext() : context;
    }
    
    /**
     * Injection point.
     *
     * @param videoId id of the video that user want to download
     */
    public static boolean startVideoDownloadActivity(@Nullable String videoId) {
        if (videoId == null || !SettingsEnum.HOOK_DOWNLOAD_BUTTON.getBoolean())
            return false;
        
        download(getContext(), videoId, false);
        return true;
    }

    /**
     * Injection point.
     *
     * @param playlistId id of the playlist that user want to download
     */
    public static boolean startPlaylistDownloadActivity(@Nullable String playlistId) {
        if (playlistId == null || !SettingsEnum.HOOK_DOWNLOAD_BUTTON.getBoolean())
            return false;
        download(getContext(), playlistId, true);
        return true;
    }
}
