package com.org.godspeed;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.braintreepayments.api.BraintreeFragment;
import com.braintreepayments.api.dropin.DropInActivity;
import com.braintreepayments.api.dropin.DropInRequest;
import com.braintreepayments.api.dropin.DropInResult;
import com.braintreepayments.api.models.PaymentMethodNonce;
import com.braintreepayments.api.models.ThreeDSecureAdditionalInformation;
import com.braintreepayments.api.models.ThreeDSecurePostalAddress;
import com.braintreepayments.api.models.ThreeDSecureRequest;
import com.google.gson.Gson;
import com.org.godspeed.utility.WebServices;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import static com.facebook.GraphRequest.TAG;

public class WalletActivity extends Activity {


    private static final int REQUEST_CODE = 1234;
    String API_GET_TOKEN = WebServices.BASE_URL + "Webservices/Braintreepayment_gateway/get";
    String API_CHECKOUT = WebServices.BASE_URL + "Webservices/Braintreepayment_gateway/payment_checkout";

    String token, amount;
    HashMap<String, String> paramsHash;
    Button btn_pay;
    EditText edit_amount;
    LinearLayout group_payment;
    Context context;
    TextView yu;


    String logTest = "";
    private BraintreeFragment mBraintreeFragment;
    private String mAuthorization;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wallet);

        context = this;
        edit_amount = findViewById(R.id.edit_amount);
        yu = findViewById(R.id.yu);
        btn_pay = findViewById(R.id.btn_pay);
        group_payment = findViewById(R.id.payment_group);

        getToken();

        btn_pay.setOnClickListener(view -> submitPayment());
    }


    private void setLog() {
        yu.setText(logTest);
    }

    private void getToken() {
        try {


            RequestQueue MyRequestQueue;
            Map<String, String> MyData = new HashMap<>();
            StringRequest MyStringRequest;

            String url = API_GET_TOKEN;


            MyRequestQueue = Volley.newRequestQueue(this);

            Log.d("TAGG", "sendAndRequestResponse: " + url + "    ******************* \n" + MyData);

            //Create an error listener to handle errors appropriately.
            MyStringRequest = new StringRequest(Request.Method.POST, url,
                    response -> {


                        try {

                            JSONObject jsonObject = new JSONObject(response);
                            token = jsonObject.getString("data");
                            Log.d("TAGG", "sendAndRequestResponse: " + response);

                        } catch (Exception m) {

                        }
                    }, error -> {
                try {
                    Log.d("TAG", "sendAndRequestResponse: " + error);
                } catch (Exception m) {
                }

            }) {
                protected Map<String, String> getParams() {
                    return MyData;
                }
            };
            MyStringRequest.setRetryPolicy(new DefaultRetryPolicy(
                    60000,
                    DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                    DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            MyRequestQueue.add(MyStringRequest);
        } catch (Exception v) {
        }
    }

    private void submitPayment() {
        String payValue = edit_amount.getText().toString();
        if (!payValue.isEmpty()) {
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
                    .amount(payValue)
                    .email("btanveerkhan@gmail.com")
                    .billingAddress(address)
                    .versionRequested(ThreeDSecureRequest.VERSION_2)
                    .additionalInformation(additionalInformation);

            DropInRequest dropInRequest = new DropInRequest()
                    .clientToken(token);

            startActivityForResult(dropInRequest.getIntent(this), REQUEST_CODE);
        } else
            Toast.makeText(this, "Enter a valid amount for payment", Toast.LENGTH_SHORT).show();

    }

    private void sendPayments() {

        Log.d(VolleyLog.TAG, "sendPayments: " + paramsHash);
        RequestQueue queue = Volley.newRequestQueue(WalletActivity.this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, API_CHECKOUT,
                response -> {
                    if (response.contains("Successful")) {
                        Toast.makeText(WalletActivity.this, "Payment Success", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(WalletActivity.this, "Payment Failed", Toast.LENGTH_SHORT).show();
                    }
                    yu.setText(API_CHECKOUT + "\n" + response);


                    logTest = logTest + API_CHECKOUT + "\n" + response;
                    setLog();
                    getToken();
                    Log.d("Response", response);
                }, error -> {
            logTest = logTest + API_CHECKOUT + "\n" + error.toString();
            setLog();
            Log.d("Err", error.toString());
        }) {
            @Override
            protected Map<String, String> getParams() {
                if (paramsHash == null)
                    return null;
                Map<String, String> params = new HashMap<>();
                for (String key : paramsHash.keySet()) {
                    params.put(key, paramsHash.get(key));
                }
                return params;
            }

            @Override
            public Map<String, String> getHeaders() {
                Map<String, String> params = new HashMap<>();
                params.put("Content-type", "application/x-www-form-urlencoded");
                return params;
            }
        };
        RetryPolicy mRetryPolicy = new DefaultRetryPolicy(0, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        stringRequest.setRetryPolicy(mRetryPolicy);
        queue.add(stringRequest);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                DropInResult result = data.getParcelableExtra(DropInResult.EXTRA_DROP_IN_RESULT);

                Log.d(TAG, "onActivityResult: " + new Gson().toJson(result));
                PaymentMethodNonce nonce = result.getPaymentMethodNonce();
                String strNounce = nonce.getNonce();

                yu.setText(new Gson().toJson(result));
                if (!edit_amount.getText().toString().isEmpty()) {
                    amount = edit_amount.getText().toString();
                    paramsHash = new HashMap<>();
                    paramsHash.put("amount", amount);
                    // paramsHash.put("program_id", "1");
                    paramsHash.put("payment_method_nonce", strNounce);

                    logTest = logTest + "\n" + new Gson().toJson(result);
                    setLog();
                    sendPayments();
                } else {
                    Toast.makeText(this, "Please enter a valid amount", Toast.LENGTH_SHORT).show();
                }
            } else if (resultCode == RESULT_CANCELED) {
                Toast.makeText(this, "User canceled", Toast.LENGTH_SHORT).show();
            } else {
                Exception error = (Exception) data.getSerializableExtra(DropInActivity.EXTRA_ERROR);
                Log.d("Err", error.toString());
            }
        }
    }

}