package models;

import java.util.ArrayList;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import helpers.QueueUtils;
import com.example.robots.MainActivity;

public class Robot {
    public String first_name;
    public String last_name;

    public Robot(String _first_name, String _last_name) {
        this.first_name = _first_name;
        this.last_name = _last_name;
    }

    public static ArrayList getCollection() {
        ArrayList<Robot> collection = new ArrayList<>();
        collection.add(new Robot("Juan", "Bichito"));
        return collection;
    }
    public static void injectContactsFromCloud(final QueueUtils.QueueObject o,
                                               final ArrayList<Robot> contactos,
                                               final MainActivity _interface) {
        String url = "https://fipo.equisd.com/api/users.json";
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        if (response.has("objects")) {
                            try {
                                JSONArray list = response.getJSONArray("objects");
                                for (int i=0; i < list.length(); i++) {
                                    JSONObject o = list.getJSONObject(i);
                                    contactos.add(new Robot(o.getString("first_name"),  o.getString("last_name")));
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            _interface.refreshList();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                    }
                });
        o.addToRequestQueue(jsonObjectRequest);
    }
}