// Generated by view binder compiler. Do not edit!
package com.example.jmjapp.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import com.example.jmjapp.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class RestaurantItemViewBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final ConstraintLayout layoutRestaurant;

  @NonNull
  public final ImageView resImage;

  @NonNull
  public final TextView tvRestaurantMenu;

  @NonNull
  public final TextView tvRestaurantName;

  @NonNull
  public final TextView tvStatus;

  private RestaurantItemViewBinding(@NonNull ConstraintLayout rootView,
      @NonNull ConstraintLayout layoutRestaurant, @NonNull ImageView resImage,
      @NonNull TextView tvRestaurantMenu, @NonNull TextView tvRestaurantName,
      @NonNull TextView tvStatus) {
    this.rootView = rootView;
    this.layoutRestaurant = layoutRestaurant;
    this.resImage = resImage;
    this.tvRestaurantMenu = tvRestaurantMenu;
    this.tvRestaurantName = tvRestaurantName;
    this.tvStatus = tvStatus;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static RestaurantItemViewBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static RestaurantItemViewBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.restaurant_item_view, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static RestaurantItemViewBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      ConstraintLayout layoutRestaurant = (ConstraintLayout) rootView;

      id = R.id.res_image;
      ImageView resImage = rootView.findViewById(id);
      if (resImage == null) {
        break missingId;
      }

      id = R.id.tv_restaurant_menu;
      TextView tvRestaurantMenu = rootView.findViewById(id);
      if (tvRestaurantMenu == null) {
        break missingId;
      }

      id = R.id.tv_restaurant_name;
      TextView tvRestaurantName = rootView.findViewById(id);
      if (tvRestaurantName == null) {
        break missingId;
      }

      id = R.id.tv_status;
      TextView tvStatus = rootView.findViewById(id);
      if (tvStatus == null) {
        break missingId;
      }

      return new RestaurantItemViewBinding((ConstraintLayout) rootView, layoutRestaurant, resImage,
          tvRestaurantMenu, tvRestaurantName, tvStatus);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
