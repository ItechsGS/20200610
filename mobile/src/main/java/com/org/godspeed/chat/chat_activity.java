package com.org.godspeed.chat;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.org.godspeed.R;
import com.org.godspeed.utility.GodSpeedInterface;
import com.org.godspeed.utility.WebServices;

import java.util.ArrayList;
import java.util.List;

import static android.view.View.GONE;
import static com.org.godspeed.service.SchoolDataService.LoginJson;

public class chat_activity extends Activity implements GodSpeedInterface {
    TextView textViewScreenName;
    Bundle b = new Bundle();
    ImageView imageViewBackArrow, audio_rec, cmr;
    EditText msgInputText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        setContentView(R.layout.activity_chat_activity);

        // Get RecyclerView object.
        final RecyclerView msgRecyclerView = findViewById(R.id.chat_recycler_view);
        textViewScreenName = findViewById(R.id.textViewScreenName);
        imageViewBackArrow = findViewById(R.id.imageViewBackArrow);
        audio_rec = findViewById(R.id.audio_rec);
        cmr = findViewById(R.id.cmr);

        imageViewBackArrow.setOnClickListener(view -> {
            onBackPressed();
        });
        b = getIntent().getExtras();
        String name = b.getString("client_name");

        textViewScreenName.setText("CHAT - " + name);

        List<ChatAppMsgDTO> msgDtoList = new ArrayList<ChatAppMsgDTO>();
        if (b != null && !b.getString("client_message").equalsIgnoreCase("")) {
            ChatAppMsgDTO msgDto = new ChatAppMsgDTO(ChatAppMsgDTO.MSG_TYPE_RECEIVED, b.getString("client_message"));
            // msgDtoList.add(msgDto);
        }
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        msgRecyclerView.setLayoutManager(linearLayoutManager);

        // Create the initial data list.


        // Create the data adapter with above data list.
        final ChatAppMsgAdapter chatAppMsgAdapter = new ChatAppMsgAdapter(msgDtoList);

        // Set data adapter to RecyclerView.
        msgRecyclerView.setAdapter(chatAppMsgAdapter);

        msgInputText = findViewById(R.id.chat_input_msg);

        msgInputText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (msgInputText.length() > 0) {
                    audio_rec.setVisibility(GONE);
                    cmr.setVisibility(GONE);
                } else {
                    audio_rec.setVisibility(View.VISIBLE);
                    cmr.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        ImageView msgSendButton = findViewById(R.id.chat_send_msg);

        msgSendButton.setOnClickListener(view -> {
            String msgContent = msgInputText.getText().toString();
            if (!TextUtils.isEmpty(msgContent)) {

                ChatAppMsgDTO msgDto1 = new ChatAppMsgDTO(ChatAppMsgDTO.MSG_TYPE_SENT, msgContent);
                msgDtoList.add(msgDto1);


                int newMsgPosition = msgDtoList.size() - 1;

                // Notify recycler view insert one new data.
                chatAppMsgAdapter.notifyItemInserted(newMsgPosition);

                // Scroll RecyclerView to the last message.
                msgRecyclerView.scrollToPosition(newMsgPosition);

                WebServices webServices = new WebServices();
                webServices.send_PushNotification(
                        ""
                        , b.getString("client_id"),
                        msgContent,
                        LoginJson.get(0).getUserId(), this, chat_activity.this);

                // Empty the input edit text box.
                msgInputText.setText("");
            }
        });
    }

    @Override
    public void ApiResponse(String result) {

    }

    public class ChatAppMsgAdapter extends RecyclerView.Adapter<ChatAppMsgViewHolder> {

        private List<ChatAppMsgDTO> msgDtoList = null;

        public ChatAppMsgAdapter(List<ChatAppMsgDTO> msgDtoList) {
            this.msgDtoList = msgDtoList;
        }

        @Override
        public void onBindViewHolder(ChatAppMsgViewHolder holder, int position) {
            ChatAppMsgDTO msgDto = this.msgDtoList.get(position);
            if (ChatAppMsgDTO.MSG_TYPE_RECEIVED.equals(msgDto.getMsgType())) {
                holder.leftMsgLayout.setVisibility(LinearLayout.VISIBLE);
                holder.leftMsgTextView.setText(msgDto.getMsgContent());
                holder.rightMsgLayout.setVisibility(GONE);
            } else if (ChatAppMsgDTO.MSG_TYPE_SENT.equals(msgDto.getMsgType())) {
                holder.rightMsgLayout.setVisibility(LinearLayout.VISIBLE);
                holder.rightMsgTextView.setText(msgDto.getMsgContent());
                holder.leftMsgLayout.setVisibility(GONE);
            }
        }

        @Override
        public ChatAppMsgViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
            View view = layoutInflater.inflate(R.layout.activity_chat_app_item_view, parent, false);
            return new ChatAppMsgViewHolder(view);
        }

        @Override
        public int getItemCount() {
            if (msgDtoList == null) {
                msgDtoList = new ArrayList<>();
            }
            return msgDtoList.size();
        }
    }

    public class ChatAppMsgViewHolder extends RecyclerView.ViewHolder {

        RelativeLayout leftMsgLayout;

        RelativeLayout rightMsgLayout;

        TextView leftMsgTextView;

        TextView rightMsgTextView;

        public ChatAppMsgViewHolder(View itemView) {
            super(itemView);

            if (itemView != null) {
                leftMsgLayout = itemView.findViewById(R.id.chat_left_msg_layout);
                rightMsgLayout = itemView.findViewById(R.id.chat_right_msg_layout);
                leftMsgTextView = itemView.findViewById(R.id.chat_left_msg_text_view);
                rightMsgTextView = itemView.findViewById(R.id.chat_right_msg_text_view);
            }
        }
    }


}
