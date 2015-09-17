package com.jaaga.shared;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;


public class ProductListAdapter extends ArrayAdapter<Product> {

    private Context context;
    List<Product> products;
    SharedPreference sharedPreference;

    public ProductListAdapter(Context context, List<Product> products) {
        super(context, R.layout.product_list_item, products);
        this.context = context;
        this.products = products;
        sharedPreference = new SharedPreference();
    }

    private class ViewHolder {
         ImageView productImage;
        TextView productNameTxt;
       // TextView productDescTxt;
        TextView productPriceTxt;
        ImageView favoriteImg;
        ImageView shareImg;
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
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.product_list_item, null);
            holder = new ViewHolder();
            holder.productNameTxt = (TextView) convertView
                    .findViewById(R.id.txt_pdt_name);
            holder.productImage=(ImageView)convertView.findViewById(R.id.product_image);
           /* holder.productDescTxt = (TextView) convertView
                    .findViewById(R.id.txt_pdt_desc);*/
            holder.productPriceTxt = (TextView) convertView
                    .findViewById(R.id.txt_pdt_price);
            holder.favoriteImg = (ImageView) convertView
                    .findViewById(R.id.imgbtn_favorite);
            holder.shareImg=(ImageView)convertView.findViewById(R.id.imgbtn_share);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        final Product product = (Product) getItem(position);
        holder.productNameTxt.setText(product.getName());
        holder.productImage.setImageResource(product.getImage());
        /*holder.productDescTxt.setText(product.getDescription());*/
        holder.productPriceTxt.setText(product.getPrice() + "");
        holder.shareImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("Delete Button Clicked", "**********");
                shareTextUrl();


            }
        });

		/*If a product exists in shared preferences then set heart_red drawable
		 * and set a tag*/
        if (checkFavoriteItem(product)) {
            holder.favoriteImg.setImageResource(R.drawable.bookmark_dark);
            holder.favoriteImg.setTag("red");
        } else {
            holder.favoriteImg.setImageResource(R.drawable.bookmark_light);
            holder.favoriteImg.setTag("grey");
        }

        return convertView;
    }

    private void shareTextUrl() {
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, "This is my text to send.");
        sendIntent.setType("text/plain");
        context.startActivity(sendIntent);
    }

    /*Checks whether a particular product exists in SharedPreferences*/
    public boolean checkFavoriteItem(Product checkProduct) {
        boolean check = false;
        List<Product> favorites = sharedPreference.getFavorites(context);
        if (favorites != null) {
            for (Product product : favorites) {
                if (product.equals(checkProduct)) {
                    check = true;
                    break;
                }
            }
        }
        return check;
    }

    @Override
    public void add(Product product) {
        super.add(product);
        products.add(product);
        notifyDataSetChanged();
    }

    @Override
    public void remove(Product product) {
        super.remove(product);
        products.remove(product);
        notifyDataSetChanged();
    }
}