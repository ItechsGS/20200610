package com.org.godspeed.imageTest;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.org.godspeed.R;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Ahmed Adel on 5/5/17.
 */

public class EmojiFragment extends androidx.fragment.app.Fragment implements EmojiAdapter.OnEmojiClickListener {

    RecyclerView emojiRecyclerView;
    private ArrayList<String> emojiIds;
    private PhotoEditorActivity photoEditorActivity;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        photoEditorActivity = (PhotoEditorActivity) getActivity();

        String[] emojis = photoEditorActivity.getResources().getStringArray(R.array.photo_editor_emoji);

        emojiIds = new ArrayList<>();
        Collections.addAll(emojiIds, emojis);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main_photo_edit_emoji, container, false);

        emojiRecyclerView = rootView.findViewById(R.id.fragment_main_photo_edit_emoji_rv);
        emojiRecyclerView.setHasFixedSize(true);
        emojiRecyclerView.setLayoutManager(new GridLayoutManager(photoEditorActivity, 4));
        EmojiAdapter adapter = new EmojiAdapter(photoEditorActivity, emojiIds);
        adapter.setOnEmojiClickListener(this);
        emojiRecyclerView.setAdapter(adapter);

        return rootView;
    }

    @Override
    public void onEmojiClickListener(String emojiName) {
        photoEditorActivity.addEmoji(emojiName);
    }
}
