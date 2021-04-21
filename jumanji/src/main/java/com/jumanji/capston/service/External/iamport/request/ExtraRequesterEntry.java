package com.jumanji.capston.service.External.iamport.request;

import com.google.gson.annotations.SerializedName;

public class ExtraRequesterEntry {

    @SerializedName("requester")
    private String requester;

    public ExtraRequesterEntry(String requester) {
        this.requester = requester;
    }

    public String getRequester() {
        return requester;
    }
}
