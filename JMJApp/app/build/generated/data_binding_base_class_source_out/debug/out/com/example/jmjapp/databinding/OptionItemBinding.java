// Generated by view binder compiler. Do not edit!
package com.example.jmjapp.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import com.example.jmjapp.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class OptionItemBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final ConstraintLayout layoutMenu2;

  @NonNull
  public final RecyclerView menuOptionOptionList;

  @NonNull
  public final TextView optionOptionGroupAdd;

  @NonNull
  public final TextView optionOptionGroupName;

  private OptionItemBinding(@NonNull ConstraintLayout rootView,
      @NonNull ConstraintLayout layoutMenu2, @NonNull RecyclerView menuOptionOptionList,
      @NonNull TextView optionOptionGroupAdd, @NonNull TextView optionOptionGroupName) {
    this.rootView = rootView;
    this.layoutMenu2 = layoutMenu2;
    this.menuOptionOptionList = menuOptionOptionList;
    this.optionOptionGroupAdd = optionOptionGroupAdd;
    this.optionOptionGroupName = optionOptionGroupName;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static OptionItemBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static OptionItemBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.option_item, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static OptionItemBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      ConstraintLayout layoutMenu2 = (ConstraintLayout) rootView;

      id = R.id.menu_option_option_list;
      RecyclerView menuOptionOptionList = rootView.findViewById(id);
      if (menuOptionOptionList == null) {
        break missingId;
      }

      id = R.id.option_option_group_add;
      TextView optionOptionGroupAdd = rootView.findViewById(id);
      if (optionOptionGroupAdd == null) {
        break missingId;
      }

      id = R.id.option_option_group_name;
      TextView optionOptionGroupName = rootView.findViewById(id);
      if (optionOptionGroupName == null) {
        break missingId;
      }

      return new OptionItemBinding((ConstraintLayout) rootView, layoutMenu2, menuOptionOptionList,
          optionOptionGroupAdd, optionOptionGroupName);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
