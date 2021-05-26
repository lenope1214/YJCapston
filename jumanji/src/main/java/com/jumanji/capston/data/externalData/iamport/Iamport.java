package com.jumanji.capston.data.externalData.iamport;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import net.minidev.json.annotate.JsonIgnore;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class Iamport {
    public static class AuthData {
        @SerializedName("imp_key")
        private String api_key;

        @SerializedName("imp_secret")
        private String api_secret;

        public AuthData(String api_key, String api_secret) {
            this.api_key = api_key;
            this.api_secret = api_secret;
        }
    }

    @Getter
    public static class CancelData implements Serializable {

        @SerializedName("imp_uid")
        private String imp_uid;

        @SerializedName("merchant_uid")
        private String merchant_uid;

        @SerializedName("amount")
        private BigDecimal amount;

        @SerializedName("reason")
        private String reason;

        @SerializedName("refund_holder")
        private String refund_holder;

        @SerializedName("refund_bank")
        private String refund_bank;

        @SerializedName("refund_account")
        private String refund_account;

        public CancelData(String uid, boolean imp_uid_or_not) {
            System.out.println("uid : " + uid);
            if ( imp_uid_or_not ) {
                this.imp_uid = uid;
            } else {
                this.merchant_uid = uid;
            }
        }

        public CancelData(String uid, boolean imp_uid_or_not, BigDecimal amount) {
            this(uid, imp_uid_or_not);
            this.amount = amount;
        }

        public void setReason(String reason) {
            this.reason = reason;
        }

        public void setRefund_holder(String refund_holder) {
            this.refund_holder = refund_holder;
        }

        public void setRefund_bank(String refund_bank) {
            this.refund_bank = refund_bank;
        }

        public void setRefund_account(String refund_account) {
            this.refund_account = refund_account;
        }

    }


    public static class AccessToken {
        @SerializedName("access_token")
        String token;

        @SerializedName("expired_at")
        int expired_at;

        @SerializedName("now")
        int now;

        public String getToken() {
            return this.token;
        }
    }


    public static class IamportResponse<T> {
        @SerializedName("code")
        int code;

        @SerializedName("message")
        String message;

        @SerializedName("response")
        T response;

        public int getCode() {
            return code;
        }

        public String getMessage() {
            return message;
        }

        public T getResponse() {
            return response;
        }
    }


    public static class Payment {
        @SerializedName("imp_uid")
        String imp_uid;

        @SerializedName("merchant_uid")
        String merchant_uid;

        @SerializedName("pay_method")
        String pay_method;

        @SerializedName("pg_provider")
        String pg_provider;

        @SerializedName("pg_tid")
        String pg_tid;

        @SerializedName("escrow")
        boolean escrow;

        @SerializedName("apply_num")
        String apply_num;

        @SerializedName("card_name")
        String card_name;

        @SerializedName("card_quota")
        int card_quota;

        @SerializedName("vbank_name")
        String vbank_name;

        @SerializedName("vbank_num")
        String vbank_num;

        @SerializedName("vbank_holder")
        String vbank_holder;

        @SerializedName("vbank_date")
        long vbank_date;

        @SerializedName("name")
        String name;

        @SerializedName("amount")
        BigDecimal amount;

        @SerializedName("cancel_amount")
        BigDecimal cancel_amount;

        @SerializedName("buyer_name")
        String buyer_name;

        @SerializedName("buyer_email")
        String buyer_email;

        @SerializedName("buyer_tel")
        String buyer_tel;

        @SerializedName("buyer_addr")
        String buyer_addr;

        @SerializedName("buyer_postcode")
        String buyer_postcode;

        @SerializedName("custom_data")
        String custom_data;

        @SerializedName("status")
        String status;

        @SerializedName("paid_at")
        long paid_at;

        @SerializedName("failed_at")
        long failed_at;

        @SerializedName("cancelled_at")
        long cancelled_at;

        @SerializedName("fail_reason")
        String fail_reason;

        @SerializedName("cancel_reason")
        String cancel_reason;

        @SerializedName("receipt_url")
        String receipt_url;

        public String getImpUid() {
            return imp_uid;
        }

        public String getMerchantUid() {
            return merchant_uid;
        }

        public String getPayMethod() {
            return pay_method;
        }

        public String getPgProvider() {
            return pg_provider;
        }

        public String getPgTid() {
            return pg_tid;
        }

        public boolean isEscrow() {
            return escrow;
        }

        public String getApplyNum() {
            return apply_num;
        }

        public String getCardName() {
            return card_name;
        }

        public int getCardQuota() {
            return card_quota;
        }

        public String getVbankName() {
            return vbank_name;
        }

        public String getVbankNum() {
            return vbank_num;
        }

        public String getVbankHolder() {
            return vbank_holder;
        }

        public Date getVbankDate() {
            return new Date( vbank_date * 1000L );
        }

        public String getName() {
            return name;
        }

        public BigDecimal getAmount() {
            return amount;
        }

        public BigDecimal getCancelAmount() {
            return cancel_amount;
        }

        public String getBuyerName() {
            return buyer_name;
        }

        public String getBuyerEmail() {
            return buyer_email;
        }

        public String getBuyerTel() {
            return buyer_tel;
        }

        public String getBuyerAddr() {
            return buyer_addr;
        }

        public String getBuyerPostcode() {
            return buyer_postcode;
        }

        public String getCustomData() {
            return custom_data;
        }

        public String getStatus() {
            return status;
        }

        public Date getPaidAt() {
            return new Date( paid_at * 1000L );
        }

        public Date getFailedAt() {
            return new Date( failed_at * 1000L );
        }

        public Date getCancelledAt() {
            return new Date( cancelled_at * 1000L );
        }

        public String getFailReason() {
            return fail_reason;
        }

        public String getCancelReason() {
            return cancel_reason;
        }

        public String getReceiptUrl() {
            return receipt_url;
        }
    }
}
