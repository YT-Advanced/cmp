package app.revanced.integrations.sponsorblock.ui;

import static app.revanced.integrations.utils.ResourceUtils.identifier;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.drawable.RippleDrawable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.ImageButton;

import app.revanced.integrations.patches.video.VideoInformation;
import app.revanced.integrations.settings.SettingsEnum;
import app.revanced.integrations.sponsorblock.SponsorBlockUtils;
import app.revanced.integrations.utils.ResourceType;

public final class NewSegmentLayout extends FrameLayout {
    private static final ColorStateList rippleColorStateList = new ColorStateList(
            new int[][]{new int[]{android.R.attr.state_enabled}},
            new int[]{0x33ffffff} // sets the ripple color to white
    );
    final int defaultBottomMargin;
    final int ctaBottomMargin;
    final int hiddenBottomMargin;
    private final int rippleEffectId;

    public NewSegmentLayout(final Context context) {
        this(context, null);
    }

    public NewSegmentLayout(final Context context, final AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public NewSegmentLayout(final Context context, final AttributeSet attributeSet, final int defStyleAttr) {
        this(context, attributeSet, defStyleAttr, 0);
    }

    public NewSegmentLayout(final Context context, final AttributeSet attributeSet,
                            final int defStyleAttr, final int defStyleRes) {
        super(context, attributeSet, defStyleAttr, defStyleRes);

        LayoutInflater.from(context).inflate(identifier("new_segment", ResourceType.LAYOUT, context), this, true);

        TypedValue rippleEffect = new TypedValue();
        context.getTheme().resolveAttribute(android.R.attr.selectableItemBackground, rippleEffect, true);
        rippleEffectId = rippleEffect.resourceId;

        initializeButton(
                context,
                "sb_new_segment_rewind",
                () -> VideoInformation.seekToRelative(-SettingsEnum.SB_ADJUST_NEW_SEGMENT_STEP.getInt())
        );

        initializeButton(
                context,
                "sb_new_segment_forward",
                () -> VideoInformation.seekToRelative(SettingsEnum.SB_ADJUST_NEW_SEGMENT_STEP.getInt())
        );

        initializeButton(
                context,
                "sb_new_segment_adjust",
                SponsorBlockUtils::onMarkLocationClicked
        );

        initializeButton(
                context,
                "sb_new_segment_compare",
                SponsorBlockUtils::onPreviewClicked
        );

        initializeButton(
                context,
                "sb_new_segment_edit",
                SponsorBlockUtils::onEditByHandClicked
        );

        initializeButton(
                context,
                "sb_new_segment_publish",
                SponsorBlockUtils::onPublishClicked
        );

        defaultBottomMargin = context.getResources().getDimensionPixelSize(identifier("brand_interaction_default_bottom_margin", ResourceType.DIMEN, context));
        ctaBottomMargin = context.getResources().getDimensionPixelSize(identifier("brand_interaction_cta_bottom_margin", ResourceType.DIMEN, context));
        hiddenBottomMargin = (int) Math.round((ctaBottomMargin) * 0.5);  // margin when the button container is hidden
    }

    /**
     * Initializes a segment button with the given resource identifier name with the given handler and a ripple effect.
     *
     * @param context                The context.
     * @param resourceIdentifierName The resource identifier name for the button.
     * @param handler                The handler for the button's click event.
     */
    private void initializeButton(final Context context, final String resourceIdentifierName,
                                  final ButtonOnClickHandlerFunction handler) {
        final ImageButton button = findViewById(identifier(resourceIdentifierName, ResourceType.ID, context));

        // Add ripple effect
        button.setBackgroundResource(rippleEffectId);
        RippleDrawable rippleDrawable = (RippleDrawable) button.getBackground();
        rippleDrawable.setColor(rippleColorStateList);

        button.setOnClickListener((v) -> handler.apply());
    }

    @FunctionalInterface
    public interface ButtonOnClickHandlerFunction {
        void apply();
    }
}