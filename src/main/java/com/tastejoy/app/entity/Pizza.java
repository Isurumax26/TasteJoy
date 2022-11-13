package com.tastejoy.app.entity;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
public class Pizza {

    public static final String TYPE = "pizza";

    private int id;
    private String info;
    private int size;
    private int price;
}
