package com.jaaga.shared;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import static com.jaaga.shared.R.drawable.bookmark_dark;
import static com.jaaga.shared.R.drawable.bookmark_light;


public class ProductListFragment extends Fragment implements
        OnItemClickListener, OnItemLongClickListener {

    public static final String ARG_ITEM_ID = "product_list";

    Activity activity;
    ListView productListView;
    List<Product> products;
    ProductListAdapter productListAdapter;
    //ProductListAdapter.ViewHolder.productImage imgtxt;
    SharedPreference sharedPreference;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity = getActivity();
        sharedPreference = new SharedPreference();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_product_list, container,
                false);
        findViewsById(view);

        setProducts();

        productListAdapter = new ProductListAdapter(activity, products);
        productListView.setAdapter(productListAdapter);
        productListView.setOnItemClickListener(this);
        productListView.setOnItemLongClickListener(this);

        return view;
    }

    private void setProducts() {

        Product product1 = new Product(1, "Milk", R.drawable.milk,"good for bones");
        Product product2 = new Product(2, "Coconut",
                R.drawable.coconut, "Good For Brains");
        Product product3 = new Product(3, "coffee",
                R.drawable.coffee, "Dont let u sleep");
        Product product4 = new Product(4, "lemon",
                R.drawable.lemon, "rich in citric acid");


        products = new ArrayList<Product>();
        products.add(product1);
        products.add(product2);
        products.add(product3);
        products.add(product4);

    }



    private void findViewsById(View view) {
        productListView = (ListView) view.findViewById(R.id.list_product);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position,
                            long id) {
        Product product = (Product) parent.getItemAtPosition(position);
        Toast.makeText(activity, product.toString(), Toast.LENGTH_LONG).show();
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> arg0, View view,
                                   int position, long arg3) {
        ImageView button = (ImageView) view.findViewById(R.id.imgbtn_favorite);

        String tag = button.getTag().toString();
        if (tag.equalsIgnoreCase("grey")) {
            sharedPreference.addFavorite(activity, products.get(position));
            Toast.makeText(activity,
                    activity.getResources().getString(R.string.add_favr),
                    Toast.LENGTH_SHORT).show();

            button.setTag("red");
            button.setImageResource(bookmark_dark);
        } else {
            sharedPreference.removeFavorite(activity, products.get(position));
            button.setTag("grey");
            button.setImageResource(bookmark_light);
            Toast.makeText(activity,
                    activity.getResources().getString(R.string.remove_favr),
                    Toast.LENGTH_SHORT).show();
        }

        return true;
    }

    @Override
    public void onResume() {
        getActivity().setTitle(R.string.app_name);
        getActivity().getActionBar().setTitle(R.string.app_name);
        super.onResume();
    }
}