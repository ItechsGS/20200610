package com.org.godspeed.ProgramPurchase;

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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.VolleyLog;
import com.braintreepayments.api.dropin.DropInActivity;
import com.braintreepayments.api.dropin.DropInRequest;
import com.braintreepayments.api.dropin.DropInResult;
import com.braintreepayments.api.models.PaymentMethodNonce;
import com.braintreepayments.api.models.ThreeDSecureAdditionalInformation;
import com.braintreepayments.api.models.ThreeDSecurePostalAddress;
import com.braintreepayments.api.models.ThreeDSecureRequest;
import com.chauthai.swipereveallayout.SwipeRevealLayout;
import com.chauthai.swipereveallayout.ViewBinderHelper;
import com.google.gson.Gson;
import com.org.godspeed.R;
import com.org.godspeed.allOtherClasses.CoachNevigationDrawerScreen;
import com.org.godspeed.response_JsonS.ProgramsJSON.Program;
import com.org.godspeed.response_JsonS.ProgramsJSON.ProgramsPurchase;
import com.org.godspeed.utility.ExoPlayerActivity;
import com.org.godspeed.utility.GodSpeedInterface;
import com.org.godspeed.utility.UtilityClass;
import com.org.godspeed.utility.WebServices;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static com.facebook.GraphRequest.TAG;
import static com.org.godspeed.allOtherClasses.LoginScreen.userId;
import static com.org.godspeed.service.SchoolDataService.LoginJson;
import static com.org.godspeed.utility.UtilityClass.hide;
import static com.org.godspeed.utility.UtilityClass.urlEncoded;

//import com.paypal.android.sdk.payments.PayPalConfiguration;
//import com.paypal.android.sdk.payments.PayPalPayment;
//import com.paypal.android.sdk.payments.PayPalService;
//import com.paypal.android.sdk.payments.PaymentActivity;
//import com.paypal.android.sdk.payments.PaymentConfirmation;

public class purchase_membership extends Activity implements GodSpeedInterface {
    private static final int REQUEST_CODE = 1234;
    RecyclerView purchase_membership;
    List<ProgramsPurchase> programs = new ArrayList<>();
    List<Program> programList = new ArrayList<>();
    TextView textViewScreenName;
    ImageView imageViewBackArrow, imageViewSave;
    String API_GET_TOKEN = WebServices.BASE_URL + "Webservices/Braintreepayment_gateway/get";
    String API_CHECKOUT = WebServices.BASE_URL + "Webservices/Braintreepayment_gateway/payment_checkout";
    String token, amount;
    HashMap<String, String> paramsHash;
    Button btn_pay;
    EditText edit_amount;
    LinearLayout group_payment;
    Context context;
    String program_id = "";
    WebServices webServices = new WebServices();
    private int PAYPAL_REQUEST_CODE = 7777;
    //    private PayPalConfiguration config = new PayPalConfiguration()
//            .environment(PayPalConfiguration.ENVIRONMENT_SANDBOX)
//            .clientId(PaypalConfig.PAYPAL_CLIENT_ID);
    private String ItemID = "";
    private String whichApiCalled = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_puchase_membership);
        getToken();
        purchase_membership = findViewById(R.id.purchase_membership);
        textViewScreenName = findViewById(R.id.textViewScreenName);
        imageViewSave = findViewById(R.id.imageViewSave);
        imageViewBackArrow = findViewById(R.id.imageViewBackArrow);

        textViewScreenName.setText("");
        imageViewSave.setVisibility(View.GONE);
        imageViewBackArrow.setOnClickListener(view -> {
            onBackPressed();
        });
        try {
            textViewScreenName.setText(getIntent().getStringExtra("ScreenName"));
            programs = new ArrayList<>(Arrays.asList(new Gson().fromJson(getIntent().getStringExtra("Program_Plan"), ProgramsPurchase.class)));
            programList = new ArrayList<>(programs.get(0).getProgram());

        } catch (Exception v) {
        }

        purchase_membership.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        purchase_membership.setAdapter(new ProgramsRecycler(this));

////        Intent intent = new Intent(this, PayPalService.class);
////        intent.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION, config);
//        startService(intent);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.ltr_anim, R.anim.rtl_anim);
    }

    @Override
    public void ApiResponse(String result) {
        Log.e(VolleyLog.TAG, whichApiCalled + "       ********ApiResponse: " + result);
        if (result != null && result.trim().length() > 0) {
            //UtilityClass.SetPreferences(this, getString(R.string.programs), result);
            String responseMessage = "";
            try {
                JSONObject jsonObj = new JSONObject(result);


                String respCode = jsonObj
                        .getString("responseCode");

                WebServices.responseCode = Integer.parseInt(respCode);

                responseMessage = jsonObj
                        .getString("responseMessage");

                Log.e("**********", "" + responseMessage);
                if (WebServices.responseCode == 200) {

                    if (whichApiCalled.equalsIgnoreCase("deleteTrainingProgramFromSale")) {
                        //JSONArray jsonDataArray = jsonObj.getJSONArray("data");
                        hide();
                        UtilityClass.showAlertDailog(this, responseMessage);
                    } else if (whichApiCalled.equalsIgnoreCase("GET_TOKEN")) {
                        try {
                            token = jsonObj.getString("data");
                        } catch (Exception b) {
                        }

                        Log.d(TAG, "MYTOKEN: " + token);
                    } else if (whichApiCalled.equalsIgnoreCase("sendPayments")) {
                        UtilityClass.showAlertDailog(this, responseMessage);
                        Log.d(TAG, "ApiResponse: " + jsonObj.getJSONArray("data"));
                        getToken();
                    }


                } else {
                    UtilityClass.showAlertDailog(this, responseMessage);
                }
            } catch (JSONException e) {

                e.printStackTrace();
            } catch (Exception e) {

                e.printStackTrace();
            }
            hide();
        } else {

        }
        hide();

    }

    private void processPayment(String price, String itemName, String id) {
        ItemID = id;
        program_id = id;
        amount = price;

//        String payValue = edit_amount.getText().toString();
//        if (!payValue.isEmpty()) {
        // Card nonce that we wish to upgrade to a 3DS nonce.
        //   CardNonce cardNonce = (CardNonce) paymentMethodNonce;

        ThreeDSecurePostalAddress address = new ThreeDSecurePostalAddress()
                .givenName("Jill") // ASCII-printable characters required, else will throw a validation error
                .surname("Doe") // ASCII-printable characters required, else will throw a validation error
                .phoneNumber("5551234567")
                .streetAddress("555 Smith St")
                .extendedAddress("#2")
                .locality("Chicago")
                .region("IL")
                .postalCode("12345")
                .countryCodeAlpha2("US");

// For best results, provide as many additional elements as possible.
        ThreeDSecureAdditionalInformation additionalInformation = new ThreeDSecureAdditionalInformation()
                .shippingAddress(address);

        ThreeDSecureRequest threeDSecureRequest = new ThreeDSecureRequest()
                .amount(price)
                .email("btanveerkhan@gmail.com")
                .billingAddress(address)
                .versionRequested(ThreeDSecureRequest.VERSION_2)
                .additionalInformation(additionalInformation);

        DropInRequest dropInRequest = new DropInRequest()
                .clientToken(token);

        startActivityForResult(dropInRequest.getIntent(this), REQUEST_CODE);
//        } else{}
        // Toast.makeText(this, "Enter a valid amount for payment", Toast.LENGTH_SHORT).show();
//        PayPalPayment payPalPayment = new PayPalPayment(new BigDecimal(String.valueOf(price)), "USD",
//                itemName, PayPalPayment.PAYMENT_INTENT_SALE);
//        Intent intent = new Intent(this, PaymentActivity.class);
//        intent.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION, config);
//        intent.putExtra(PaymentActivity.EXTRA_PAYMENT, payPalPayment);
//        startActivityForResult(intent, PAYPAL_REQUEST_CODE);
//        overridePendingTransition(R.anim.exit, R.anim.enter);
    }

    private void getToken() {
        whichApiCalled = "GET_TOKEN";
        webServices.GET_TOKEN(this, this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                DropInResult result = data.getParcelableExtra(DropInResult.EXTRA_DROP_IN_RESULT);

                Log.d(TAG, "onActivityResult: " + new Gson().toJson(result));
                PaymentMethodNonce nonce = result.getPaymentMethodNonce();
                String strNounce = nonce.getNonce();


                paramsHash = new HashMap<>();
                paramsHash.put("amount", amount);
                paramsHash.put("program_id", program_id);
                paramsHash.put("payment_method_nonce", strNounce);

                whichApiCalled = "sendPayments";
                webServices.sendPayments(amount, strNounce, program_id, userId, this, this);
                //  getToken();
            } else if (resultCode == RESULT_CANCELED) {
                getToken();
                Toast.makeText(this, "User canceled", Toast.LENGTH_SHORT).show();
            } else {
                getToken();
                Exception error = (Exception) data.getSerializableExtra(DropInActivity.EXTRA_ERROR);
                Log.d("Err", error.toString());
            }
        }
    }

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        if (requestCode == PAYPAL_REQUEST_CODE) {
//            if (resultCode == RESULT_OK) {
//                PaymentConfirmation confirmation = data.getParcelableExtra(PaymentActivity.EXTRA_RESULT_CONFIRMATION);
//                if (confirmation != null) {
//                    try {
//                        String paymentDetails = confirmation.toJSONObject().toString(4);
//                        Log.d(VolleyLog.TAG, "onActivityResult: " + requestCode + "  " + resultCode + "  " + new Gson().toJson(data));
//                        WebServices webServices = new WebServices();
//                        whichApiCalled = "setPurchaseDetail";
//                        webServices.setPurchaseDetail(LoginJson.get(0).getUserId(), ItemID, new Gson().toJson(data), this, this);
//                    } catch (JSONException e) {
//                        e.printStackTrace();
//                    }
//                }
//            } else if (resultCode == Activity.RESULT_CANCELED) {
//                overridePendingTransition(R.anim.ltr_anim, R.anim.rtl_anim);
//            }
//            //Toast.makeText(this, "Cancel", Toast.LENGTH_SHORT).show();
//        } else if (resultCode == PaymentActivity.RESULT_EXTRAS_INVALID) {
//            overridePendingTransition(R.anim.ltr_anim, R.anim.rtl_anim);
//        }
//        //Toast.makeText(this, "Invalid", Toast.LENGTH_SHORT).show();
//    }

    private class ProgramsRecycler extends RecyclerView.Adapter<RecyclerViewHolder2> {
        Context context;
        private ViewBinderHelper binderHelper = new ViewBinderHelper();

        public ProgramsRecycler(Context context) {
            this.context = context;
        }

        @Override
        public RecyclerViewHolder2 onCreateViewHolder(ViewGroup viewGroup, int i) {
            LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
            View view = layoutInflater.inflate(R.layout.layout_exercise_list_days_based, viewGroup, false);
            return new RecyclerViewHolder2(view);
        }

        @Override
        public void onBindViewHolder(final RecyclerViewHolder2 holder, int i) {
            binderHelper.setOpenOnlyOne(true);
            binderHelper.bind(holder.swipe_layoutMain, programList.get(i).getId());
            holder.textViewExerciseName.setText(programList.get(i).getProgramName());
            holder.textViewWatchVideo.setText("BUY - " + programList.get(i).getPrice());

            holder.swipe_layoutMain.setDragEdge(SwipeRevealLayout.DRAG_EDGE_RIGHT);
            holder.swipe_layoutMain.setLayoutMode(SwipeRevealLayout.MODE_SAME_LEVEL);

            holder.imageViewSelectUnSelectExerciseType.setOnClickListener(view -> {
                Intent mIntent = ExoPlayerActivity.getStartIntent(context, urlEncoded(programList.get(i).getVideo()));
                startActivity(mIntent);
                overridePendingTransition(R.anim.exit, R.anim.enter);
            });
            if (!LoginJson.get(0).getUserType().equalsIgnoreCase("1")) {
                holder.swipe_layoutMain.setLockDrag(true);
            }

            holder.myLaX.setOnClickListener(view -> {
                holder.swipe_layoutMain.close(true);
                CoachNevigationDrawerScreen.CAllAPI = true;
                whichApiCalled = "deleteTrainingProgramFromSale";
                WebServices webServices = new WebServices();
                webServices.deleteTrainingProgramFromSale(programList.get(i).getId(), context, purchase_membership.this);

                programList.remove(i);
                notifyItemRemoved(i);
            });


            holder.rLayoutForWatchVideo.setOnClickListener(view -> {
                if (programList.get(i).getPrice() == null) {
                    return;
                }
                processPayment(programList.get(i).getPrice(), programList.get(i).getProgramName(), programList.get(i).getId());
            });
        }

        @Override
        public int getItemCount() {
            return programList.size();
        }
    }

    private class RecyclerViewHolder2 extends RecyclerView.ViewHolder {
        LinearLayout myLaX;
        SwipeRevealLayout swipe_layoutMain;
        private CardView rLayoutForWatchVideo;
        private TextView textViewExerciseName, textViewWatchVideo;
        private ImageView imageViewSelectUnSelectExerciseType;

        public RecyclerViewHolder2(@NonNull View itemView) {
            super(itemView);
            textViewExerciseName = itemView.findViewById(R.id.textViewExerciseName);
            myLaX = itemView.findViewById(R.id.myLaX);
            imageViewSelectUnSelectExerciseType = itemView.findViewById(R.id.imageViewSelectUnSelectExerciseType);
            textViewWatchVideo = itemView.findViewById(R.id.textViewWatchVideo);
            rLayoutForWatchVideo = itemView.findViewById(R.id.rLayoutForWatchVideo);
            swipe_layoutMain = itemView.findViewById(R.id.swipe_layoutMain);
        }
    }
}
