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
    public String phone;
    public String nickname;

    public Robot(String _phone, String _nickname) {
        this.phone = _phone;
        this.nickname = _nickname;
    }

    public static ArrayList getCollection() {
        ArrayList<Robot> collection = new ArrayList<>();
        collection.add(new Robot("981999923", "Bichito"));
        collection.add(new Robot("9859913923", "Plaga"));
        collection.add(new Robot("981914213", "Libelula"));
        return collection;
    }
    public static void injectContactsFromCloud(final QueueUtils.QueueObject o,
                                               final ArrayList<Robot> contactos,
                                               final MainActivity _interface) {
        String url = "http://fipo.equisd.com/api/users.json";
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        if (response.has("data")) {

                            try {
                                JSONArray list = response.getJSONArray("data");
                                for (int i=0; i < list.length(); i++) {
                                    JSONObject o = list.getJSONObject(i);
                                    contactos.add(new Robot(o.getString("first_name"),  o.getString("last_name")));
                                }

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            _interface.refreshList(); // Esta función debemos implementarla
                            // en nuestro activity
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