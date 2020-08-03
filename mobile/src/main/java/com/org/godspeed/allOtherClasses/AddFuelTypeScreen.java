package com.org.godspeed.allOtherClasses;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import com.android.volley.VolleyLog;
import com.org.godspeed.R;
import com.org.godspeed.utility.CustomTypeface;
import com.org.godspeed.utility.ExerciseTypeClass;

import java.util.Vector;

public class AddFuelTypeScreen extends Activity {

    private Vector<ExerciseTypeClass> vectorExerciseType;
    private Context context;
    private RelativeLayout rLayoutForActivationTitle;
    private RelativeLayout rLayoutForSkillsTitle;
    private RelativeLayout rLayoutForBuildTitle;
    private RelativeLayout rLayoutForEnergyTitle;
    private TextView textViewRecent;
    private TextView textViewFrequent;
    private TextView textViewMyFoods;
    private TextView textViewMeals;
    private View.OnClickListener pillarSelectionBackgroundClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            rLayoutForActivationTitle.setBackgroundResource(R.drawable.unselected_transparent_button_icon);
            rLayoutForBuildTitle.setBackgroundResource(R.drawable.unselected_transparent_button_icon);
            rLayoutForEnergyTitle.setBackgroundResource(R.drawable.unselected_transparent_button_icon);
            rLayoutForSkillsTitle.setBackgroundResource(R.drawable.unselected_transparent_button_icon);

            textViewRecent.setTextColor(ContextCompat.getColor(context, R.color.color_gray_for_health_profile_button_unselected));
            textViewMyFoods.setTextColor(ContextCompat.getColor(context, R.color.color_gray_for_health_profile_button_unselected));
            textViewMeals.setTextColor(ContextCompat.getColor(context, R.color.color_gray_for_health_profile_button_unselected));
            textViewFrequent.setTextColor(ContextCompat.getColor(context, R.color.color_gray_for_health_profile_button_unselected));

            switch (view.getId()) {

                case R.id.rLayoutForActivationTitle:
                    rLayoutForActivationTitle.setBackgroundResource(R.drawable.health_profile_selected_button_icon);
                    textViewRecent.setTextColor(ContextCompat.getColor(context, R.color.color_black_for_health_profile_button_selected));
                    break;
                case R.id.rLayoutForBuildTitle:
                    rLayoutForBuildTitle.setBackgroundResource(R.drawable.health_profile_selected_button_icon);
                    textViewMeals.setTextColor(ContextCompat.getColor(context, R.color.color_black_for_health_profile_button_selected));
                    break;
                case R.id.rLayoutForEnergyTitle:
                    rLayoutForEnergyTitle.setBackgroundResource(R.drawable.health_profile_selected_button_icon);
                    textViewMyFoods.setTextColor(ContextCompat.getColor(context, R.color.color_black_for_health_profile_button_selected));
                    break;
                case R.id.rLayoutForSkillsTitle:
                    rLayoutForSkillsTitle.setBackgroundResource(R.drawable.health_profile_selected_button_icon);
                    textViewFrequent.setTextColor(ContextCompat.getColor(context, R.color.color_black_for_health_profile_button_selected));
                    break;


            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.layout_listview_for_add_fuel_type);
        context = this;
        Log.e("Screen", "Add Fuel Type");
        ListViewAdapter adapter = new ListViewAdapter(context);
        ListView listViewFuelItems = findViewById(R.id.listViewFuelTypes);
        listViewFuelItems.setAdapter(adapter);
        listViewFuelItems.setDivider(null);

        ImageView imageViewBackArrow = findViewById(R.id.imageViewBackArrow);
        imageViewBackArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
                overridePendingTransition(R.anim.ltr_anim, R.anim.rtl_anim);
            }
        });

        TextView textViewScreenName = findViewById(R.id.textViewScreenName);
        textViewScreenName.setTypeface(CustomTypeface.load_AGENCYB_Fonts(context));

        textViewRecent = findViewById(R.id.textViewRecent);
        textViewRecent.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));

        textViewFrequent = findViewById(R.id.textViewFrequent);
        textViewFrequent.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));

        textViewMyFoods = findViewById(R.id.textViewMyFoods);
        textViewMyFoods.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));

        textViewMeals = findViewById(R.id.textViewMeals);
        textViewMeals.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));

        TextView textViewQuickAdd = findViewById(R.id.textViewQuickAdd);
        textViewQuickAdd.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));

        TextView textViewCreateFood = findViewById(R.id.textViewCreateFood);
        textViewCreateFood.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));


        setRelativeLayoutControl();

    }

    private void setRelativeLayoutControl() {
        rLayoutForActivationTitle = findViewById(R.id.rLayoutForActivationTitle);
        rLayoutForActivationTitle.setBackgroundResource(R.drawable.health_profile_selected_button_icon);
        rLayoutForActivationTitle.setOnClickListener(pillarSelectionBackgroundClickListener);

        rLayoutForSkillsTitle = findViewById(R.id.rLayoutForSkillsTitle);
        rLayoutForSkillsTitle.setBackgroundResource(R.drawable.unselected_transparent_button_icon);
        rLayoutForSkillsTitle.setOnClickListener(pillarSelectionBackgroundClickListener);

        rLayoutForBuildTitle = findViewById(R.id.rLayoutForBuildTitle);
        rLayoutForBuildTitle.setBackgroundResource(R.drawable.unselected_transparent_button_icon);
        rLayoutForBuildTitle.setOnClickListener(pillarSelectionBackgroundClickListener);

        rLayoutForEnergyTitle = findViewById(R.id.rLayoutForEnergyTitle);
        rLayoutForEnergyTitle.setBackgroundResource(R.drawable.unselected_transparent_button_icon);
        rLayoutForEnergyTitle.setOnClickListener(pillarSelectionBackgroundClickListener);

        RelativeLayout rLayoutForButtonCreateFood = findViewById(R.id.rLayoutForButtonCreateFood);
        rLayoutForButtonCreateFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putString("screeName", getString(R.string.create_food));
                startActivity(new Intent(AddFuelTypeScreen.this, Create_Quick_Add_Food_Fuel_Screen.class).putExtras(bundle));
                overridePendingTransition(R.anim.exit, R.anim.enter);

                Log.d(VolleyLog.TAG, "*************** Create_Quick_Add_Food_Fuel_Screen *************");


            }
        });

        RelativeLayout rLAyoutButtonQuickAdd = findViewById(R.id.rLAyoutButtonQuickAdd);
        rLAyoutButtonQuickAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putString("screeName", getString(R.string.quick_add));
                startActivity(new Intent(AddFuelTypeScreen.this, Create_Quick_Add_Food_Fuel_Screen.class).putExtras(bundle));
                overridePendingTransition(R.anim.exit, R.anim.enter);
                Log.d(VolleyLog.TAG, "*************** Create_Quick_Add_Food_Fuel_Screen *************");

            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    public class ViewHolder {
        TextView textViewItemName, textViewItemDescription, textViewPrice;
    }

    public class ListViewAdapter extends BaseAdapter {
        ViewHolder holder;
        private Context context;
        private LayoutInflater vi;


        public ListViewAdapter(Context context) {
            this.context = context;
            vi = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        }


        @Override
        public int getCount() {
            return 7;
        }

        @Override
        public Object getItem(int position) {
            return position;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                holder = new AddFuelTypeScreen.ViewHolder();
                convertView = vi.inflate(R.layout.list_items_fuel_expandable, null);
                holder.textViewItemName = convertView.findViewById(R.id.textViewItemName);
                holder.textViewItemName.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));

                holder.textViewItemDescription = convertView.findViewById(R.id.textViewItemDescription);
                holder.textViewItemDescription.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));

                holder.textViewPrice = convertView.findViewById(R.id.textViewPrice);
                holder.textViewPrice.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));

                convertView.setTag(holder);
            } else {
                holder = (AddFuelTypeScreen.ViewHolder) convertView.getTag();
            }

            return convertView;
        }
    }
}
