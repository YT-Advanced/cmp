package app.revanced.integrations.patches.misc;

import static app.revanced.integrations.utils.ReVancedUtils.containsAny;

import app.revanced.integrations.settings.SettingsEnum;

public class ProtobufSpoofPatch {
    /**
     * Target Protobuf parameters.
     */
    private static final String[] PROTOBUF_PARAMETER_WHITELIST = {
            "8AEB", // Play video in shorts and stories
            "YAHIAQ", // Autoplay in feed
            "SAFgAxgB" // Autoplay in scrim
    };

    /**
     * Protobuf parameters used for general fixes.
     * Known issue: thumbnails not showing when tapping the seekbar
     */
    private static final String PROTOBUF_PARAMETER_GENERAL = "CgIQBg";

    /**
     * Protobuf parameters used by the player.
     * Known issue: captions are positioned above the player
     */
    private static final String PROTOBUF_PARAMETER_SHORTS = "8AEB";


    public static String getProtobufOverride(String original) {
        if (!SettingsEnum.ENABLE_PROTOBUF_SPOOF.getBoolean()
                || containsAny(original, PROTOBUF_PARAMETER_WHITELIST))
            return original;

        return SettingsEnum.SPOOFING_TYPE.getBoolean() ? PROTOBUF_PARAMETER_SHORTS : PROTOBUF_PARAMETER_GENERAL;
    }
}
