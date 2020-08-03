package com.org.godspeed.adapter;

import android.annotation.SuppressLint;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.facebook.share.model.ShareHashtag;
import com.facebook.share.widget.ShareDialog;
import com.org.godspeed.R;
import com.org.godspeed.loginData.AppMenu;
import com.org.godspeed.utility.CustomTypeface;
import com.org.godspeed.utility.WebServices;

import java.util.ArrayList;
import java.util.List;


public class AppMenuAdapter extends BaseAdapter {
    public static Object ViewHolder;
    Context context;
    List<AppMenu> Items = new ArrayList<>();
    AppMenu menuItem;
    ShareDialog shareDialog;
    ShareDialog shareDialogX;
    private ImageView dividerOne;


    public AppMenuAdapter(Context context, List<AppMenu> items) {
        this.context = context;
        this.Items = items;

    }

    public List<AppMenu> getItems() {
        return this.Items;
    }

    @Override
    public int getViewTypeCount() {
        return getCount();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public int getCount() {
        return Items.size();
    }


    @Override
    public Object getItem(int position) {
        return Items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        View view = convertView;
        if (view == null) {
            LayoutInflater layoutInflater = LayoutInflater.from(context);
            view = layoutInflater.inflate(R.layout.adapter_menu, null);

            holder = new ViewHolder();
            assert view != null;

            holder.rlyMain = view.findViewById(R.id.lyt_menu_items);
            holder.itemName = view.findViewById(R.id.txt_menu_item_name);
            holder.itemImage = view.findViewById(R.id.img_menu_item);
            holder.social = view.findViewById(R.id.social);
            holder.facebook = view.findViewById(R.id.facebook);
            holder.twitter = view.findViewById(R.id.twitter);
            holder.insta = view.findViewById(R.id.insta);
            dividerOne = view.findViewById(R.id.dividerOne);
            // holder.txtCount = (TextView) view.findViewById(R.id.txt_count);

            holder.itemName.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));
            //FontCache.setupFont(context, holder.itemName, FontCache.SFUITextLight);
            //FontCache.setupFont(context, holder.txtCount, FontCache.SFUITextLight);

            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        holder.position = position;

        menuItem = this.Items.get(position);
        holder.itemName.setText(menuItem.getMenuName());


        if (menuItem.getMenuName().equalsIgnoreCase("SOCIAL MEDIA")) {
            holder.social.setVisibility(View.VISIBLE);
        }


        if (menuItem.getMenuName().equalsIgnoreCase("My Profile")) {
            holder.itemName.setText("MY PROFILE");
            RelativeLayout.LayoutParams paramsX = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            paramsX.setMargins(0, 0, 0, 0);
            holder.rlyMain.setLayoutParams(paramsX);
            holder.itemName.setBackgroundResource(android.R.color.transparent);
            holder.itemName.setTextColor(Color.parseColor("#FFFFFF"));
            holder.itemName.setPadding(10, 0, 0, 0);
            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            params.setMargins(5, -10, 0, 0);
            holder.itemName.setLayoutParams(params);
            holder.itemName.setTextSize(20);
            dividerOne.setVisibility(View.GONE);
            holder.itemName.setClickable(false);
            holder.itemName.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));
        }
        ShareHashtag shareHashTag = new ShareHashtag.Builder().setHashtag("Hi... i just found an gym app called GodSpeed. \n!its awesome.").build();
//        holder.facebook.setOnClickListener(view1 -> {
//            ShareFB();
//        });
//
//        holder.insta.setOnClickListener(view12 -> {  Intent shareOnAppIntent = new Intent();
//        try{
//            shareOnAppIntent .setAction(Intent.ACTION_SEND);
//            shareOnAppIntent .putExtra(Intent.EXTRA_TEXT,"TETTTSTTSS");
//            shareOnAppIntent .setType("text/plain");
//            shareOnAppIntent .setPackage("com.instagram.android");
//            context.startActivity(shareOnAppIntent );
//        } catch (Exception e) {
//            e.printStackTrace();
//            Toast.makeText(context, "APP is not installed", Toast.LENGTH_LONG).show();
//        }
////            Uri uri = Uri.parse("https://www.instagram.com/accounts/login/?hl=en");
////            Intent likeIng = new Intent(Intent.ACTION_VIEW, uri);
////
////            likeIng.setPackage("com.instagram.android");
////
////            try {
////                context.startActivity(likeIng);
////            } catch (ActivityNotFoundException e) {
////                context.startActivity(new Intent(Intent.ACTION_VIEW,
////                        Uri.parse("https://www.instagram.com/accounts/login/?hl=en")));
////            }
//
//        });


        holder.facebook.setOnClickListener(view1 -> {
            String facebookUrl = "https://www.facebook.com/iTechInSolutions";
            try {
                int versionCode = context.getPackageManager().getPackageInfo("com.facebook.katana", 0).versionCode;
                if (versionCode >= 3002850) {
                    Uri uri = Uri.parse("fb://facewebmodal/f?href=" + facebookUrl);
                    try {
                        context.startActivity(new Intent(Intent.ACTION_VIEW, uri));


                    } catch (Exception e) {
                        context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("fb://page/iTechInSolutions/")));

                    }


                } else {
                    // open the Facebook app using the old method (fb://profile/id or fb://page/id)
                    context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("fb://page/iTechInSolutions/")));
                }
            } catch (PackageManager.NameNotFoundException e) {
                // Facebook is not installed. Open the browser
                context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(facebookUrl)));
            }


        });

        holder.insta.setOnClickListener(view12 -> {
            Uri uri = Uri.parse("https://www.instagram.com/accounts/login/?hl=en");
            Intent likeIng = new Intent(Intent.ACTION_VIEW, uri);

            likeIng.setPackage("com.instagram.android");

            try {
                context.startActivity(likeIng);
            } catch (ActivityNotFoundException e) {
                context.startActivity(new Intent(Intent.ACTION_VIEW,
                        Uri.parse("https://www.instagram.com/accounts/login/?hl=en")));
            }

        });

        holder.twitter.setOnClickListener(view13 -> {
            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_VIEW);
            // intent.addCategory(Intent.CATEGORY_BROWSABLE);
            intent.setData(Uri.parse("http://www.twitter.com"));
            context.startActivity(intent);

            ////Toast.makeText(context, "hahha", Toast.LENGTH_SHORT).show();
        });
        // //Toast.makeText(context, menuItem.getName(), Toast.LENGTH_SHORT).show();

        if (!menuItem.getMenuName().equalsIgnoreCase("LOGOUT")) {
            Glide.with(context).load(
                    WebServices.BASE_URL_FOR_IMAGES_ASSETS + menuItem.getMenuIcon())
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(holder.itemImage);
        } else {
            holder.itemImage.setImageDrawable(context.getResources().getDrawable(R.drawable.logout));
        }
        //holder.facebook.setImageDrawable(menuItem.getName());

        //holder.txtCount.setVisibility(View.GONE);
        /*if (menuItem.getId() == R.string.id_notifications) {
            holder.txtCount.setVisibility(View.VISIBLE);
        }*/

        view.setId(position);

        return view;
    }

    //holder.facebook.setImageDrawable(menuItem.getName());

    //holder.txtCount.setVisibility(View.GONE);
        /*if (menuItem.getId() == R.string.id_notifications) {
            holder.txtCount.setVisibility(View.VISIBLE);
        }*/


    public static class ViewHolder {
        int position;
        RelativeLayout rlyMain;
        LinearLayout social;
        TextView itemName, txtCount, google;
        ImageView itemImage, facebook, insta, twitter;
        ListView listview;
    }
}