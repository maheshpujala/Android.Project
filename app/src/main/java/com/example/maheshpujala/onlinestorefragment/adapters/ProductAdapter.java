package com.example.maheshpujala.onlinestorefragment.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.maheshpujala.onlinestorefragment.R;
import com.example.maheshpujala.onlinestorefragment.model.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductAdapter extends BaseAdapter{
    private static final String TAG = "ProductAdapter";

    private List<Product> products = new ArrayList<Product>();

    private final Context context;

    public ProductAdapter(Context context) {
        this.context = context;
    }

    public void updateProducts(List<Product> products) {
        this.products.addAll(products);
        notifyDataSetChanged();
    }


    @Override
    public int getCount() {
        return products.size();
    }

    @Override
    public Product getItem(int position) {
        return products.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Spinner Quantity;
        TextView productname,description,rate,delivery,avail_size;
        ImageView productimage;
        Button wishlist,remove;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.adapter_product, parent, false);
            Quantity =(Spinner) convertView.findViewById(R.id.spinner);
            productname = (TextView) convertView.findViewById(R.id.tvProductName);
            description = (TextView) convertView.findViewById(R.id.tvDescription);
            rate = (TextView) convertView.findViewById(R.id.tvRate);
           delivery = (TextView) convertView.findViewById(R.id.tvDelivery);
            avail_size = (TextView) convertView.findViewById(R.id.tvAvailSize);
          productimage = (ImageView) convertView.findViewById(R.id.tvProductImage);
            convertView.setTag(new ViewHolder(Quantity,productname, description, rate,delivery, avail_size, productimage ));
        } else {
            ViewHolder viewHolder = (ViewHolder)convertView.getTag();
            productname = viewHolder.tvProductName;
            description = viewHolder.tvDescription;
            rate = viewHolder.tvRate;
            delivery  = viewHolder.tvProductName;
            avail_size = viewHolder.tvAvailSize;
            productimage = viewHolder.tvProductImage;
            Quantity =viewHolder.spinner;
        }

        final Product product = getItem(position);
       // tvName.setText(product.getName());
      //  tvPrice.setText(Constant.CURRENCY+String.valueOf(product.getPrice().setScale(2, BigDecimal.ROUND_HALF_UP)));
        Log.d(TAG, "Context package name: " + context.getPackageName());
       // ivImage.setImageResource(context.getResources().getIdentifier(
            //    product.getImageName(), "drawable", context.getPackageName()));
//        bView.setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(context, ProductActivity.class);
//                Bundle bundle = new Bundle();
//                bundle.putSerializable("product", product);
//                Log.d(TAG, "This product hashCode: " + product.hashCode());
//                intent.putExtras(bundle);
//                context.startActivity(intent);
//            }
//        });

        return convertView;
    }


    private static class ViewHolder {
        public final TextView tvProductName;
        public final TextView tvDescription;
        public final TextView tvRate;
        public final TextView tvDelivery;
        public final TextView tvAvailSize;
        public final ImageView tvProductImage;
        public final Spinner spinner;


        public ViewHolder(Spinner Quantity,TextView productname,TextView description, TextView rate,TextView delivery, TextView avail_size,ImageView productimage) {
           this.spinner=Quantity;
            this.tvProductName = productname;
            this.tvDescription = description;
            this.tvRate= rate;
            this.tvDelivery =delivery ;
            this.tvAvailSize = avail_size;
            this.tvProductImage = productimage;
        }
    }
}
