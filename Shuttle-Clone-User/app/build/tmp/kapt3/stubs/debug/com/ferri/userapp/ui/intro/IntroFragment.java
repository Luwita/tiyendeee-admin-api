package com.ferri.userapp.ui.intro;

@kotlin.Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\u0000h\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010\u001a\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u001b\u001a\u00020\u000bJ&\u0010\u001c\u001a\u0004\u0018\u00010\u001d2\u0006\u0010\u001e\u001a\u00020\u001f2\b\u0010 \u001a\u0004\u0018\u00010!2\b\u0010\"\u001a\u0004\u0018\u00010#H\u0016J\b\u0010$\u001a\u00020%H\u0016J\u0010\u0010&\u001a\u00020%2\u0006\u0010\'\u001a\u00020(H\u0007J\u001a\u0010)\u001a\u00020%2\u0006\u0010*\u001a\u00020\u001d2\b\u0010\"\u001a\u0004\u0018\u00010#H\u0016J\u000e\u0010+\u001a\u00020%2\u0006\u0010\u001b\u001a\u00020\u000bR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\u0006X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u0004\u0018\u00010\u0006X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u0004\u0018\u00010\u0006X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u0004\u0018\u00010\rX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u000e\u001a\u0004\u0018\u00010\rX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0016\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00040\u0010X\u0082\u0004\u00a2\u0006\u0004\n\u0002\u0010\u0011R\u001c\u0010\u0012\u001a\u0004\u0018\u00010\u0013X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017R\u000e\u0010\u0018\u001a\u00020\u0019X\u0082.\u00a2\u0006\u0002\n\u0000\u00a8\u0006,"}, d2 = {"Lcom/ferri/userapp/ui/intro/IntroFragment;", "Landroidx/fragment/app/Fragment;", "()V", "TAG", "", "animBTN", "Landroid/view/animation/Animation;", "animBgIMG", "animIMG", "animTV", "fragmentPosition", "", "introAnimIMG", "Landroid/widget/ImageView;", "introBackgroundIMG", "introText", "", "[Ljava/lang/String;", "introTextView", "Landroid/widget/TextView;", "getIntroTextView", "()Landroid/widget/TextView;", "setIntroTextView", "(Landroid/widget/TextView;)V", "letsGoBTN", "Landroid/widget/Button;", "getInstance", "position", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "onDestroyView", "", "onEventMainThread", "pusher", "Lcom/ferri/userapp/ui/events/ActionEvents;", "onViewCreated", "view", "startAnimation", "app_debug"})
public final class IntroFragment extends androidx.fragment.app.Fragment {
    @org.jetbrains.annotations.NotNull
    private final java.lang.String TAG = "IntroFragment";
    private int fragmentPosition = 0;
    @org.jetbrains.annotations.Nullable
    private android.widget.ImageView introBackgroundIMG;
    @org.jetbrains.annotations.Nullable
    private android.widget.ImageView introAnimIMG;
    @org.jetbrains.annotations.Nullable
    private android.view.animation.Animation animIMG;
    @org.jetbrains.annotations.Nullable
    private android.view.animation.Animation animBgIMG;
    @org.jetbrains.annotations.Nullable
    private android.view.animation.Animation animTV;
    @org.jetbrains.annotations.Nullable
    private android.view.animation.Animation animBTN;
    private android.widget.Button letsGoBTN;
    @org.jetbrains.annotations.Nullable
    private android.widget.TextView introTextView;
    @org.jetbrains.annotations.NotNull
    private final java.lang.String[] introText = {"Welcome To ", "Estimate Time Of Bus", "Live Location Of Buses", "Find Route In Local City"};
    
    public IntroFragment() {
        super();
    }
    
    @org.jetbrains.annotations.Nullable
    public final android.widget.TextView getIntroTextView() {
        return null;
    }
    
    public final void setIntroTextView(@org.jetbrains.annotations.Nullable
    android.widget.TextView p0) {
    }
    
    @org.jetbrains.annotations.Nullable
    public final androidx.fragment.app.Fragment getInstance(int position) {
        return null;
    }
    
    @java.lang.Override
    @org.jetbrains.annotations.Nullable
    public android.view.View onCreateView(@org.jetbrains.annotations.NotNull
    android.view.LayoutInflater inflater, @org.jetbrains.annotations.Nullable
    android.view.ViewGroup container, @org.jetbrains.annotations.Nullable
    android.os.Bundle savedInstanceState) {
        return null;
    }
    
    @java.lang.Override
    public void onViewCreated(@org.jetbrains.annotations.NotNull
    android.view.View view, @org.jetbrains.annotations.Nullable
    android.os.Bundle savedInstanceState) {
    }
    
    public final void startAnimation(int position) {
    }
    
    @org.greenrobot.eventbus.Subscribe(threadMode = org.greenrobot.eventbus.ThreadMode.MAIN)
    @android.annotation.TargetApi(value = android.os.Build.VERSION_CODES.JELLY_BEAN)
    public final void onEventMainThread(@org.jetbrains.annotations.NotNull
    com.ferri.userapp.ui.events.ActionEvents pusher) {
    }
    
    @java.lang.Override
    public void onDestroyView() {
    }
}