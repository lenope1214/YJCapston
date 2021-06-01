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

public final class BasketMenuListBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final ImageView ivMenuImg2;

  @NonNull
  public final ConstraintLayout layoutMenu2;

  @NonNull
  public final TextView menuCount;

  @NonNull
  public final ImageView menuDelete;

  @NonNull
  public final ImageView menuMinus;

  @NonNull
  public final ImageView menuPlus;

  @NonNull
  public final TextView tvMenuName2;

  @NonNull
  public final TextView tvMenuPrice2;

  private BasketMenuListBinding(@NonNull ConstraintLayout rootView, @NonNull ImageView ivMenuImg2,
      @NonNull ConstraintLayout layoutMenu2, @NonNull TextView menuCount,
      @NonNull ImageView menuDelete, @NonNull ImageView menuMinus, @NonNull ImageView menuPlus,
      @NonNull TextView tvMenuName2, @NonNull TextView tvMenuPrice2) {
    this.rootView = rootView;
    this.ivMenuImg2 = ivMenuImg2;
    this.layoutMenu2 = layoutMenu2;
    this.menuCount = menuCount;
    this.menuDelete = menuDelete;
    this.menuMinus = menuMinus;
    this.menuPlus = menuPlus;
    this.tvMenuName2 = tvMenuName2;
    this.tvMenuPrice2 = tvMenuPrice2;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static BasketMenuListBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static BasketMenuListBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.basket_menu_list, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static BasketMenuListBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.iv_menu_img2;
      ImageView ivMenuImg2 = rootView.findViewById(id);
      if (ivMenuImg2 == null) {
        break missingId;
      }

      ConstraintLayout layoutMenu2 = (ConstraintLayout) rootView;

      id = R.id.menu_count;
      TextView menuCount = rootView.findViewById(id);
      if (menuCount == null) {
        break missingId;
      }

      id = R.id.menu_delete;
      ImageView menuDelete = rootView.findViewById(id);
      if (menuDelete == null) {
        break missingId;
      }

      id = R.id.menu_minus;
      ImageView menuMinus = rootView.findViewById(id);
      if (menuMinus == null) {
        break missingId;
      }

      id = R.id.menu_plus;
      ImageView menuPlus = rootView.findViewById(id);
      if (menuPlus == null) {
        break missingId;
      }

      id = R.id.tv_menu_name2;
      TextView tvMenuName2 = rootView.findViewById(id);
      if (tvMenuName2 == null) {
        break missingId;
      }

      id = R.id.tv_menu_price2;
      TextView tvMenuPrice2 = rootView.findViewById(id);
      if (tvMenuPrice2 == null) {
        break missingId;
      }

      return new BasketMenuListBinding((ConstraintLayout) rootView, ivMenuImg2, layoutMenu2,
          menuCount, menuDelete, menuMinus, menuPlus, tvMenuName2, tvMenuPrice2);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
