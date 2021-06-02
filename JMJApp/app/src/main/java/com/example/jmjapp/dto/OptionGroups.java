package com.example.jmjapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OptionGroups {
    private String optionGroupId;
    private String ogName;
    private int ogMin;
    private int ogMax;
    private String menuId;

    public OptionGroups(String ogName, int ogMin, int ogMax) {
        this.ogName = ogName;
        this.ogMin = ogMin;
        this.ogMax = ogMax;
    }
}
