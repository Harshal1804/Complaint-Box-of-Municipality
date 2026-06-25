package com.example.khaddamuktabharat;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;
import java.util.List;

public class CustomAdapter extends ArrayAdapter<UserData> {

    private Context context;
    private int resource;
    private List<UserData> userDataList;

    public CustomAdapter(Context context, int resource, List<UserData> userDataList) {
        super(context, resource, userDataList);
        this.context = context;
        this.resource = resource;
        this.userDataList = userDataList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(resource, parent, false);
        }
        ImageView imageView = convertView.findViewById(R.id.imageView);
        TextView nameTextView = convertView.findViewById(R.id.nameTextView);
        TextView mobileNoTextView = convertView.findViewById(R.id.mobileNoTextView);
        TextView addressTextView = convertView.findViewById(R.id.addressTextView);
        TextView emailTextView = convertView.findViewById(R.id.emailTextView);
        TextView dateTextView = convertView.findViewById(R.id.dateTextView);
        TextView reviewTextView = convertView.findViewById(R.id.reviewTextView);
       // TextView MsgTextView = convertView.findViewById(R.id.MsgTextView);

        UserData userData = userDataList.get(position);

        if (userData.getImageUrl() != null && !userData.getImageUrl().isEmpty()) {
            Picasso.get().load(userData.getImageUrl()).into(imageView);
        }

        nameTextView.setText("Name= "+userData.getName());
        mobileNoTextView.setText("Mobile no= "+userData.getMobileNo());
        addressTextView.setText("Location= "+userData.getLocationgps());
        emailTextView.setText("Email id= "+userData.getEmailid());
        dateTextView.setText("Date= "+userData.getDate());
        reviewTextView.setText("review= "+userData.getReview());


        return convertView;
    }
}
