package models;


import java.util.ArrayList;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import helpers.QueueUtils;
import com.example.robots.MainActivity;

public class Contact {
    public String name;
    public String email;
    public String username;
    public String phone;
    public String website;

    public Contact(String _name, String _email, String _username, String _phone, String _website) {
        this.name = _name;
        this.email = _email;
        this.username = _username;
        this.phone = _phone;
        this.website = _website;
    }

    public static void injectContactsFromCloud(final QueueUtils.QueueObject o,
                                               final ArrayList<Contact> contacts,
                                               final MainActivity _interface) {
        String url = "https://jsonplaceholder.typicode.com/users";
        JsonArrayRequest jsonObjectRequest = new JsonArrayRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                            try {
                                JSONArray list = response;
                                for (int i=0; i < list.length(); i++) {
                                    JSONObject o = list.getJSONObject(i);
                                    contacts.add(new Contact(
                                            o.getString("name"),
                                            o.getString("email"),
                                            o.getString("username"),
                                            o.getString("phone"),
                                            o.getString("website")
                                    ));
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            _interface.refreshList();
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                    }
                });
        o.addToRequestQueue(jsonObjectRequest);
    }

}
