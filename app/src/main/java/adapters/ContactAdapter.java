package adapters;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.util.List;
import  com.example.robots.R;
import models.Contact;

public class ContactAdapter extends ArrayAdapter<Contact> {
    Context context;
    private class ViewHolder {
        TextView name;
        TextView email;

        private ViewHolder() {
        }
    }
    public ContactAdapter(Context context, List<Contact> items) {
        super(context, 0, items);
        this.context = context;
    }
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        final Contact rowItem = (Contact) getItem(position);
        LayoutInflater mInflater = (LayoutInflater) this.context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.contact_item, null);
            holder = new ViewHolder();
            holder.name = (TextView) convertView.findViewById(R.id.list_name);
            holder.email = (TextView) convertView.findViewById(R.id.list_email);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.name.setText(rowItem.name);
        holder.email.setText(rowItem.email);
        return convertView;
    }
}