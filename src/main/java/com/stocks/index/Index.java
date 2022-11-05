package com.stocks.index;

import lombok.*;

@ToString
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class Index {
    
    @Getter @Setter private String id;
    @Getter @Setter private int numberOfStocks;
    @Getter @Setter private String name;

    //Empty constructor for Jackson to create values.
    public Index() {
    }
    
}
