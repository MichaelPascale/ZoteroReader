package dev.psyc.zotero;

import android.content.Context;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class ZoteroAPI {

    private final RequestQueue requestQueue;
    private final Context context;
    private final String base_url;
    private String api_key;

    public ZoteroAPI(Context context, String base_url, String api_key) {
        this.context = context;
        this.api_key = api_key;
        this.base_url = base_url;
        this.requestQueue = Volley.newRequestQueue(context);
    }

    public void updateAPIKey(String key) {
        this.api_key = key;
    }

    public JSONObject GET(String endpoint, JSONObject body, Function<?, String> callback) {
        final JsonObjectRequest req = new JsonObjectRequest(
                Request.Method.GET,
                base_url + endpoint,
                body,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
//                        callback(response.toString());
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }
        ) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<String, String>();
                headers.put("User-Agent", "ZoteroAPI.java");
                headers.put("Zotero-API-Version", "3");
                headers.put("Zotero-API-Key", api_key);
                return headers;
            }
        };

        requestQueue.add(req);
        return new JSONObject();
    }
}
