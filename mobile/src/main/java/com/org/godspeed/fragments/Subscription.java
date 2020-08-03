package com.org.godspeed.fragments;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.org.godspeed.R;
import com.org.godspeed.response_JsonS.getVideoClassCategory.GetVideoClassCategory;
import com.org.godspeed.utility.GodSpeedInterface;
import com.org.godspeed.utility.WebServices;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.org.godspeed.utility.UtilityClass.hide;

public class Subscription extends Fragment implements GodSpeedInterface {

    Context context;
    RecyclerView subsPlanList;

    List<GetVideoClassCategory> SubsItemArray = new ArrayList<>();

    String whichApiCalled = "";
    WebServices webServices = new WebServices();
    subsListAdapter subsListAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        context = getActivity();
        View view = inflater.inflate(R.layout.subscription, container, false);
        subsPlanList = view.findViewById(R.id.subsPlanList);

        subsPlanList.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));

        //SubsItemArray.add(new GetVideoClassCategory("1","Live","","",""));

        subsPlanList.setAdapter(new subsListAdapter(SubsItemArray));

        whichApiCalled = "getVideoClassCategory";

        webServices.getVideoClassCategory(context, this);


        return view;
    }

    @Override
    public void ApiResponse(String result) {
        if (result != null && result.trim().length() > 0) {
            if (whichApiCalled.equalsIgnoreCase("getVideoClassCategory")) {
                parseRequiredData(result);
            }
        } else {
            hide();
        }
    }

    private void parseRequiredData(String result) {
        String responseMessage = "";
        try {
            JSONObject jsonObj = new JSONObject(result);


            String respCode = jsonObj
                    .getString("responseCode");

            WebServices.responseCode = Integer.parseInt(respCode);

            responseMessage = jsonObj
                    .getString("responseMessage");


            if (WebServices.responseCode == 200) {


                JSONArray jsonDataArray = jsonObj
                        .getJSONArray("data");
                Gson gson = new Gson();
                if (jsonDataArray != null && jsonDataArray.length() > 0) {
                    if (whichApiCalled.equalsIgnoreCase("getVideoClassCategory")) {
                        SubsItemArray = new ArrayList<>(Arrays.asList(new Gson().fromJson(jsonDataArray.toString(), GetVideoClassCategory[].class)));

                        subsListAdapter = new subsListAdapter(SubsItemArray);

                        subsPlanList.setAdapter(subsListAdapter);

                        hide();
                    }
                } else {
                    hide();
                }
            } else {
                hide();
            }
        } catch (JSONException e) {
            e.printStackTrace();
            hide();
        } catch (Exception e) {
            e.printStackTrace();
            hide();
        }
    }

    private class subsListAdapter extends RecyclerView.Adapter<subsListViewHolder> {
        public static final int TEXT_MAX_SIZE = 140;
        public static final int TEXT_MIN_SIZE = 40;
        private static final int STEP = 4;
        List<GetVideoClassCategory> subsItemArray = new ArrayList<>();
        private int mBaseDistZoomIn;
        private int mBaseDistZoomOut;

        public subsListAdapter(List<GetVideoClassCategory> subsItemArray) {
            this.subsItemArray = subsItemArray;
        }

        int getDistanceFromEvent(MotionEvent event) {
            int dx = (int) (event.getX(0) - event.getX(1));
            int dy = (int) (event.getY(0) - event.getY(1));
            return (int) (Math.sqrt(dx * dx + dy * dy));
        }

        @NonNull
        @Override
        public subsListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater vi = LayoutInflater.from(parent.getContext());
            return new subsListViewHolder(vi.inflate(R.layout.subs_itemlist, parent, false));
        }

        @SuppressLint("ClickableViewAccessibility")
        @Override
        public void onBindViewHolder(@NonNull subsListViewHolder holder, int position) {
            Glide.with(context).load(WebServices.BASE_URL_FOR_IMAGES_ASSETS + subsItemArray.get(position).getCategoryImage())
                    .into(holder.catImage);

            holder.catImage.setOnTouchListener((v, event) -> {
                if (event.getPointerCount() == 2) {
                    int action = event.getAction();
                    int pure = action & MotionEvent.ACTION_MASK;

                    if (pure == MotionEvent.ACTION_POINTER_DOWN
                            && 18 <= TEXT_MAX_SIZE
                            && 18 >= TEXT_MIN_SIZE) {

                        mBaseDistZoomIn = getDistanceFromEvent(event);
                        mBaseDistZoomOut = getDistanceFromEvent(event);

                    } else {
                        int currentDistance = getDistanceFromEvent(event);
//                        if (currentDistance > mBaseDistZoomIn) {
//                            float finalSize = 18 + STEP;
//                            if (finalSize > TEXT_MAX_SIZE) {
//                                finalSize = TEXT_MAX_SIZE;
//                            }
//                            viewById.setTextSize(TypedValue.COMPLEX_UNIT_PX, finalSize);
//                        } else {
//                            if (currentDistance < mBaseDistZoomOut) {
//                                float finalSize = viewById.getTextSize() - STEP;
//                                if (finalSize < TEXT_MIN_SIZE) {
//                                    finalSize = TEXT_MIN_SIZE;
//                                }
//                                viewById.setTextSize(TypedValue.COMPLEX_UNIT_PX, finalSize);
//                            }
//                        }
                    }
                    return true;
                }
                return false;


            });
            holder.l1.setVisibility(View.VISIBLE);
            holder.l1Item.setVisibility(View.GONE);
            holder.catName.setText(subsItemArray.get(position).getCategoryName());
            holder.catsubItemList.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
            holder.catsubItemList.setAdapter(new subsItemListAdapter());
        }


        @Override
        public int getItemCount() {
            return subsItemArray.size();
        }


    }

    private class subsItemListAdapter extends RecyclerView.Adapter<subsListViewHolder> {
        public subsItemListAdapter() {
        }

        @NonNull
        @Override
        public subsListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater vi = LayoutInflater.from(parent.getContext());
            return new subsListViewHolder(vi.inflate(R.layout.subs_itemlist, parent, false));
        }

        @Override
        public void onBindViewHolder(@NonNull subsListViewHolder holder, int position) {
            holder.l1Item.setVisibility(View.VISIBLE);
            holder.l1.setVisibility(View.GONE);
            holder.catsubItem.setText("Helllo");
        }

        @Override
        public int getItemCount() {
            return 6;
        }
    }

    private class subsListViewHolder extends RecyclerView.ViewHolder {
        LinearLayout l1Item;
        CardView l1;
        TextView catsubItem, catName;
        RecyclerView catsubItemList;
        ImageView catImage;

        public subsListViewHolder(@NonNull View itemView) {
            super(itemView);
            l1 = itemView.findViewById(R.id.l1);

            l1Item = itemView.findViewById(R.id.l1Item);
            catsubItem = itemView.findViewById(R.id.catsubItem);
            catName = itemView.findViewById(R.id.catName);
            catsubItemList = itemView.findViewById(R.id.CatsubItemList);
            catImage = itemView.findViewById(R.id.CatImage);

            l1.setVisibility(View.VISIBLE);
            l1Item.setVisibility(View.VISIBLE);
        }
    }


}
