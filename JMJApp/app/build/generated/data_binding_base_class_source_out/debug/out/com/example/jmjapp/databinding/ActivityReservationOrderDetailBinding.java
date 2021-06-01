// Generated by view binder compiler. Do not edit!
package com.example.jmjapp.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import com.example.jmjapp.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityReservationOrderDetailBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final ConstraintLayout constraintLayout11;

  @NonNull
  public final ConstraintLayout constraintLayout12;

  @NonNull
  public final ConstraintLayout odConstraintLayout3;

  @NonNull
  public final ConstraintLayout odConstraintLayout5;

  @NonNull
  public final ConstraintLayout odConstraintLayout6;

  @NonNull
  public final TextView resOrderDetailAddr;

  @NonNull
  public final TextView resOrderDetailAddrDetail;

  @NonNull
  public final TextView resOrderDetailAddrTv;

  @NonNull
  public final TextView resOrderDetailIsAccept;

  @NonNull
  public final TextView resOrderDetailMenuWatch;

  @NonNull
  public final TextView resOrderDetailPayTime;

  @NonNull
  public final TextView resOrderDetailPayTimeTv;

  @NonNull
  public final TextView resOrderDetailPayment;

  @NonNull
  public final TextView resOrderDetailPaymentTv;

  @NonNull
  public final TextView resOrderDetailPaymentTv2;

  @NonNull
  public final TextView resOrderDetailRequest;

  @NonNull
  public final TextView resOrderDetailRequestTv;

  @NonNull
  public final TextView resOrderDetailResTime;

  @NonNull
  public final TextView resOrderDetailResTimeTv;

  @NonNull
  public final TextView resOrderDetailShopName;

  @NonNull
  public final Toolbar resOrderDetailToolbar;

  @NonNull
  public final TextView tvBell;

  private ActivityReservationOrderDetailBinding(@NonNull ConstraintLayout rootView,
      @NonNull ConstraintLayout constraintLayout11, @NonNull ConstraintLayout constraintLayout12,
      @NonNull ConstraintLayout odConstraintLayout3, @NonNull ConstraintLayout odConstraintLayout5,
      @NonNull ConstraintLayout odConstraintLayout6, @NonNull TextView resOrderDetailAddr,
      @NonNull TextView resOrderDetailAddrDetail, @NonNull TextView resOrderDetailAddrTv,
      @NonNull TextView resOrderDetailIsAccept, @NonNull TextView resOrderDetailMenuWatch,
      @NonNull TextView resOrderDetailPayTime, @NonNull TextView resOrderDetailPayTimeTv,
      @NonNull TextView resOrderDetailPayment, @NonNull TextView resOrderDetailPaymentTv,
      @NonNull TextView resOrderDetailPaymentTv2, @NonNull TextView resOrderDetailRequest,
      @NonNull TextView resOrderDetailRequestTv, @NonNull TextView resOrderDetailResTime,
      @NonNull TextView resOrderDetailResTimeTv, @NonNull TextView resOrderDetailShopName,
      @NonNull Toolbar resOrderDetailToolbar, @NonNull TextView tvBell) {
    this.rootView = rootView;
    this.constraintLayout11 = constraintLayout11;
    this.constraintLayout12 = constraintLayout12;
    this.odConstraintLayout3 = odConstraintLayout3;
    this.odConstraintLayout5 = odConstraintLayout5;
    this.odConstraintLayout6 = odConstraintLayout6;
    this.resOrderDetailAddr = resOrderDetailAddr;
    this.resOrderDetailAddrDetail = resOrderDetailAddrDetail;
    this.resOrderDetailAddrTv = resOrderDetailAddrTv;
    this.resOrderDetailIsAccept = resOrderDetailIsAccept;
    this.resOrderDetailMenuWatch = resOrderDetailMenuWatch;
    this.resOrderDetailPayTime = resOrderDetailPayTime;
    this.resOrderDetailPayTimeTv = resOrderDetailPayTimeTv;
    this.resOrderDetailPayment = resOrderDetailPayment;
    this.resOrderDetailPaymentTv = resOrderDetailPaymentTv;
    this.resOrderDetailPaymentTv2 = resOrderDetailPaymentTv2;
    this.resOrderDetailRequest = resOrderDetailRequest;
    this.resOrderDetailRequestTv = resOrderDetailRequestTv;
    this.resOrderDetailResTime = resOrderDetailResTime;
    this.resOrderDetailResTimeTv = resOrderDetailResTimeTv;
    this.resOrderDetailShopName = resOrderDetailShopName;
    this.resOrderDetailToolbar = resOrderDetailToolbar;
    this.tvBell = tvBell;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityReservationOrderDetailBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityReservationOrderDetailBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_reservation_order_detail, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityReservationOrderDetailBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.constraintLayout11;
      ConstraintLayout constraintLayout11 = rootView.findViewById(id);
      if (constraintLayout11 == null) {
        break missingId;
      }

      id = R.id.constraintLayout12;
      ConstraintLayout constraintLayout12 = rootView.findViewById(id);
      if (constraintLayout12 == null) {
        break missingId;
      }

      id = R.id.od_constraintLayout3;
      ConstraintLayout odConstraintLayout3 = rootView.findViewById(id);
      if (odConstraintLayout3 == null) {
        break missingId;
      }

      id = R.id.od_constraintLayout5;
      ConstraintLayout odConstraintLayout5 = rootView.findViewById(id);
      if (odConstraintLayout5 == null) {
        break missingId;
      }

      id = R.id.od_constraintLayout6;
      ConstraintLayout odConstraintLayout6 = rootView.findViewById(id);
      if (odConstraintLayout6 == null) {
        break missingId;
      }

      id = R.id.res_order_detail_addr;
      TextView resOrderDetailAddr = rootView.findViewById(id);
      if (resOrderDetailAddr == null) {
        break missingId;
      }

      id = R.id.res_order_detail_addr_detail;
      TextView resOrderDetailAddrDetail = rootView.findViewById(id);
      if (resOrderDetailAddrDetail == null) {
        break missingId;
      }

      id = R.id.res_order_detail_addr_tv;
      TextView resOrderDetailAddrTv = rootView.findViewById(id);
      if (resOrderDetailAddrTv == null) {
        break missingId;
      }

      id = R.id.res_order_detail_isAccept;
      TextView resOrderDetailIsAccept = rootView.findViewById(id);
      if (resOrderDetailIsAccept == null) {
        break missingId;
      }

      id = R.id.res_order_detail_menu_watch;
      TextView resOrderDetailMenuWatch = rootView.findViewById(id);
      if (resOrderDetailMenuWatch == null) {
        break missingId;
      }

      id = R.id.res_order_detail_payTime;
      TextView resOrderDetailPayTime = rootView.findViewById(id);
      if (resOrderDetailPayTime == null) {
        break missingId;
      }

      id = R.id.res_order_detail_payTime_tv;
      TextView resOrderDetailPayTimeTv = rootView.findViewById(id);
      if (resOrderDetailPayTimeTv == null) {
        break missingId;
      }

      id = R.id.res_order_detail_payment;
      TextView resOrderDetailPayment = rootView.findViewById(id);
      if (resOrderDetailPayment == null) {
        break missingId;
      }

      id = R.id.res_order_detail_payment_tv;
      TextView resOrderDetailPaymentTv = rootView.findViewById(id);
      if (resOrderDetailPaymentTv == null) {
        break missingId;
      }

      id = R.id.res_order_detail_payment_tv2;
      TextView resOrderDetailPaymentTv2 = rootView.findViewById(id);
      if (resOrderDetailPaymentTv2 == null) {
        break missingId;
      }

      id = R.id.res_order_detail_request;
      TextView resOrderDetailRequest = rootView.findViewById(id);
      if (resOrderDetailRequest == null) {
        break missingId;
      }

      id = R.id.res_order_detail_request_tv;
      TextView resOrderDetailRequestTv = rootView.findViewById(id);
      if (resOrderDetailRequestTv == null) {
        break missingId;
      }

      id = R.id.res_order_detail_resTime;
      TextView resOrderDetailResTime = rootView.findViewById(id);
      if (resOrderDetailResTime == null) {
        break missingId;
      }

      id = R.id.res_order_detail_resTime_tv;
      TextView resOrderDetailResTimeTv = rootView.findViewById(id);
      if (resOrderDetailResTimeTv == null) {
        break missingId;
      }

      id = R.id.res_order_detail_shopName;
      TextView resOrderDetailShopName = rootView.findViewById(id);
      if (resOrderDetailShopName == null) {
        break missingId;
      }

      id = R.id.res_order_detail_toolbar;
      Toolbar resOrderDetailToolbar = rootView.findViewById(id);
      if (resOrderDetailToolbar == null) {
        break missingId;
      }

      id = R.id.tv_bell;
      TextView tvBell = rootView.findViewById(id);
      if (tvBell == null) {
        break missingId;
      }

      return new ActivityReservationOrderDetailBinding((ConstraintLayout) rootView,
          constraintLayout11, constraintLayout12, odConstraintLayout3, odConstraintLayout5,
          odConstraintLayout6, resOrderDetailAddr, resOrderDetailAddrDetail, resOrderDetailAddrTv,
          resOrderDetailIsAccept, resOrderDetailMenuWatch, resOrderDetailPayTime,
          resOrderDetailPayTimeTv, resOrderDetailPayment, resOrderDetailPaymentTv,
          resOrderDetailPaymentTv2, resOrderDetailRequest, resOrderDetailRequestTv,
          resOrderDetailResTime, resOrderDetailResTimeTv, resOrderDetailShopName,
          resOrderDetailToolbar, tvBell);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
