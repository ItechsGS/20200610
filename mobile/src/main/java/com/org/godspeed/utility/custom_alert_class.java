package com.org.godspeed.utility;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.TextWatcher;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.org.godspeed.R;

import static android.view.View.GONE;

public class custom_alert_class extends Dialog {

    LinearLayout BeginTraining, Cancel, workoutSummary, SingleRow, CancelXc, YesX, TripleLayout;
    TextView EventName, EventNameX, begin, workout, cancel, Yes, CancelX;
    EditText EditTextMessage;
    RelativeLayout EditextLayout;
    ImageView SendMessage;
    private String message;
    private String title;
    private int icon = 0;
    private View.OnClickListener btYesListener = null;
    private View.OnClickListener btNoListener = null;

    private View.OnClickListener SendMessageCoach = null;

    private TextWatcher textWatcher = null;
    private String btYesText;
    private String btNoText;

    private Boolean OneButton = false;
    private Boolean CancelAble = true;
    private Boolean EditTextView = false;

    public custom_alert_class(Context context) {
        super(context);
    }

    public custom_alert_class(Context context, int themeResId) {
        super(context, themeResId);
    }

    protected custom_alert_class(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.custom_dialog_box_for_training_dialog);

        setCanceledOnTouchOutside(CancelAble);
        getWindow().setDimAmount(0);
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        BeginTraining = findViewById(R.id.BeginTraining);
        Cancel = findViewById(R.id.Cancel);
        workoutSummary = findViewById(R.id.workoutSummary);
        begin = findViewById(R.id.begin);
        workout = findViewById(R.id.workout);
        cancel = findViewById(R.id.cancel);
        SingleRow = findViewById(R.id.SingleRow);


        EditTextMessage = findViewById(R.id.EditTextMessage);
        EditextLayout = findViewById(R.id.EditextLayout);
        SendMessage = findViewById(R.id.SendMessage);
        TripleLayout = findViewById(R.id.TripleLayout);


        CancelXc = findViewById(R.id.CancelXc);


        CancelXc.setOnClickListener(viewM -> dismiss());

        SingleRow.setVisibility(View.GONE);


        EventName = findViewById(R.id.EventName);

        EventNameX = findViewById(R.id.EventNameX);

        EventNameX.setText(message);


        cancel.setText(btNoText);
        begin.setText(btYesText);
//        showDialogofTraining(context,0,0,GetTeam.get(position).getTeamName(),"",0);
        EventName.setTypeface(CustomTypeface.load_Montserrat_Bold_Fonts(getContext()));
        EventNameX.setTypeface(CustomTypeface.load_Montserrat_Regular_Fonts(getContext()));
        begin.setTypeface(CustomTypeface.load_Montserrat_Regular_Fonts(getContext()));
        workout.setTypeface(CustomTypeface.load_Montserrat_Regular_Fonts(getContext()));
        cancel.setTypeface(CustomTypeface.load_Montserrat_Regular_Fonts(getContext()));
        EditTextMessage.setTypeface(CustomTypeface.load_Montserrat_Regular_Fonts(getContext()));


        BeginTraining.setVisibility(View.VISIBLE);
        workoutSummary.setVisibility(GONE);
        Cancel.setVisibility(View.VISIBLE);
        BeginTraining.setOnClickListener(btYesListener);

        workoutSummary.setOnClickListener(btYesListener);

        SendMessage.setOnClickListener(SendMessageCoach);

        EditTextMessage.addTextChangedListener(textWatcher);


        EditTextMessage.addTextChangedListener(textWatcher);

        Cancel.setOnClickListener(btNoListener);

        if (OneButton) {
            BeginTraining.setVisibility(GONE);
        }
        if (EditTextView) {
            TripleLayout.setVisibility(GONE);
            EditextLayout.setVisibility(View.VISIBLE);
        }
        // show();


    }

    public void editTextScreen(Boolean EditTextView) {
        this.EditTextView = EditTextView;
    }


    public String getTitle() {
        return title;
    }


    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public void OneButton(Boolean OneButton) {
        this.OneButton = OneButton;
    }

    public void SetCancel(Boolean cancelAble) {
        this.CancelAble = cancelAble;
    }

    public void setPositveButton(String yes, View.OnClickListener onClickListener) {
        dismiss();
        //begin.setText(yes);
        this.btYesText = yes;
        this.btYesListener = onClickListener;
    }


    public void setEditTextListner(TextWatcher textListner) {
        this.textWatcher = textListner;
    }

    public void setNegativeButton(String no, View.OnClickListener onClickListener) {
        dismiss();
        this.btNoText = no;
        //cancel.setText(no);
        this.btNoListener = onClickListener;
    }

    public void setCoachMessageButton(View.OnClickListener onClickListener) {
        dismiss();

        this.SendMessageCoach = onClickListener;
    }


}