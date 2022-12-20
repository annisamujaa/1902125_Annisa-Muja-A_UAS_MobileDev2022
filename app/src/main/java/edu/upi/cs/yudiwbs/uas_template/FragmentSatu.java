package edu.upi.cs.yudiwbs.uas_template;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.HttpStatus;
import edu.upi.cs.yudiwbs.uas_template.databinding.FragmentSatuBinding;


public class FragmentSatu extends Fragment {

    private FragmentSatuBinding binding;

    public FragmentSatu() {
        // Required empty public constructor
    }

    public static FragmentSatu newInstance(String param1, String param2) {
        FragmentSatu fragment = new FragmentSatu();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentSatuBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        binding.loadPertanyaan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                binding.tvJawaban.setText("Tunggu sebentar, loading data harga bitcoin (USD)");
                //https://api.coindesk.com/v1/bpi/currentprice.json
               // https://official-joke-api.appspot.com/random_joke
                Log.d("general","onclick");
                ApiHargaBitcoin.get("random_joke", null, new JsonHttpResponseHandler() {
                    @Override
                    //hati2 success jsonobjek atau jsonarray
                    public void onSuccess(int statusCode,
                                          cz.msebera.android.httpclient.Header[] headers,
                                          org.json.JSONObject response) {
                        Log.d("general","onSuccess jsonobjek");

                        /* hasil jsonn
                        {"time":{"updated":"Dec 19, 2022 09:53:00 UTC","updatedISO":"2022-12-19T09:53:00+00:00",
                                "updateduk":"Dec 19, 2022 at 09:53 GMT"},

                        "disclaimer":"This data was produced from the CoinDesk Bitcoin Price Index (USD).
                              Non-USD currency data converted using hourly conversion rate from openexchangerates.org",
                         "chartName":"Bitcoin",
                         "bpi":{"USD":{"code":"USD","symbol":"&#36;","rate":"16,730.3955",
                                    "description":"United States Dollar","rate_float":16730.3955},
                                "GBP":{"code":"GBP","symbol":"&pound;","rate":"13,979.7846",
                                  "description":"British Pound Sterling","rate_float":13979.7846},      "EUR":{"code":"EUR","symbol":"&euro;","rate":"16,297.8478","description":"Euro","rate_float":16297.8478}}}
                         */

                        //ambil USD rate
                        String punchline="";
                        try {
                            JSONObject setup = response.getJSONObject("setup");
//                            JSONObject usd = bpi.getJSONObject("USD");
                            punchline = (String) setup.get("punchline");
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Log.e("general", "msg error" +":" +e.getMessage());
                        }
                        Log.d("general", "punchline" +":" +punchline);
                        binding.tvKeterangan.setText(punchline);
                    }

                    public void onSuccess(int statusCode,
                                          cz.msebera.android.httpclient.Header[] headers,
                                          org.json.JSONArray response) {

                        Log.d("general","onSuccess jsonarray");

                    }

                    @Override
                    public  void onFailure(int statusCode, cz.msebera.android.httpclient.Header[] headers, String err, Throwable throwable)  {
                        Log.e("general", "error " + ":" + statusCode +":"+ err);
                    }
                });

            }

//            lm = new LinearLayoutManager(getActivity());
//
//            //restore state recylerview
//        if (vmUserPref.getRecViewState().getValue()!=null) {
//                lm.onRestoreInstanceState(vmUserPref.getrecViewState().getValue());
//            }
//        rvDosen.setLayoutManager(lm);
        });

        return view;

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    public void onPause() {
        super.onPause();
        //simpan state ke viewmodel vmuserpref
//        vmUserPref.setRecViewState(lm.onSaveInstanceState());
    }


}



