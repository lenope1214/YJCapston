// Generated by view binder compiler. Do not edit!
package com.example.jmjapp.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import com.example.jmjapp.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityBasketBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final Button basketReservationBtn;

  @NonNull
  public final Toolbar basketToolbar;

  @NonNull
  public final RecyclerView rvBasketList;

  @NonNull
  public final TextView tvBasket;

  private ActivityBasketBinding(@NonNull ConstraintLayout rootView,
      @NonNull Button basketReservationBtn, @NonNull Toolbar basketToolbar,
      @NonNull RecyclerView rvBasketList, @NonNull TextView tvBasket) {
    this.rootView = rootView;
    this.basketReservationBtn = basketReservationBtn;
    this.basketToolbar = basketToolbar;
    this.rvBasketList = rvBasketList;
    this.tvBasket = tvBasket;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityBasketBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityBasketBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_basket, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityBasketBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.basket_reservation_btn;
      Button basketReservationBtn = rootView.findViewById(id);
      if (basketReservationBtn == null) {
        break missingId;
      }

      id = R.id.basket_toolbar;
      Toolbar basketToolbar = rootView.findViewById(id);
      if (basketToolbar == null) {
        break missingId;
      }

      id = R.id.rv_basket_list;
      RecyclerView rvBasketList = rootView.findViewById(id);
      if (rvBasketList == null) {
        break missingId;
      }

      id = R.id.tv_basket;
      TextView tvBasket = rootView.findViewById(id);
      if (tvBasket == null) {
        break missingId;
      }

      return new ActivityBasketBinding((ConstraintLayout) rootView, basketReservationBtn,
          basketToolbar, rvBasketList, tvBasket);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
