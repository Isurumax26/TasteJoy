package com.tastejoy.app.entity;


import lombok.*;

@NoArgsConstructor
@Getter
@Setter
@Data
public class Drink {

    public static final String TYPE = "drink";

    private int id;
    private int price;
    private String name;
}
