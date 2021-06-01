package helpers;
import android.graphics.Bitmap;
import android.content.Context;
import android.util.LruCache;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

public class QueueUtils {
    private static QueueObject uniqueInstance;
    public static class QueueObject {
        private RequestQueue mRequestQueue;
        private static Context mCtx;
        private QueueObject(Context context) {
            mCtx = context;
            mRequestQueue = getRequestQueue();
        }

        public RequestQueue getRequestQueue() {
            if (mRequestQueue == null) {
                mRequestQueue = Volley.newRequestQueue(mCtx.getApplicationContext());
            }
            return mRequestQueue;
        }

        public <T> void addToRequestQueue(Request<T> req) {
            getRequestQueue().add(req);
        }

    }

    public static synchronized QueueObject getInstance(Context context) {
        if (uniqueInstance == null) {
            uniqueInstance = new QueueObject(context);
        }
        return uniqueInstance;
    }
}
