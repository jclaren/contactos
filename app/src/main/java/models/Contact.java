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

    public Contact(String _name, String _email) {
        this.name = _name;
        this.email = _email;
    }

    public static ArrayList getCollection() {
        ArrayList<Contact> collection = new ArrayList<>();
        collection.add(new Contact("Joquin Claren", "joaquin.claren@desarrollomovil.cl"));
        return collection;
    }
    public static void injectContactsFromCloud(final QueueUtils.QueueObject o,
                                               final ArrayList<Contact> contacts,
                                               final MainActivity _interface) {
        String url = "https://jsonplaceholder.typicode.com/users";
        System.out.println("***" + url);
        JsonArrayRequest jsonObjectRequest = new JsonArrayRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        System.out.println("*** respuesta:" + response );
                            System.out.println("*** cumple" );
                            try {
                                JSONArray list = response;
                                for (int i=0; i < list.length(); i++) {
                                    JSONObject o = list.getJSONObject(i);
                                    contacts.add(new Contact(o.getString("name"),  o.getString("email")));
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                                System.out.println("*** " + e );
                            }
                            _interface.refreshList();
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        System.out.println("*** " + error );
                    }
                });
        o.addToRequestQueue(jsonObjectRequest);
    }
}
