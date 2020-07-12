package com.example.dell.miss;

import java.io.Serializable;
import java.util.ArrayList;

public class FriendQinyouMessage implements Serializable{
    public int img;
    public String name;
    public String time;
    public String possition;
    public String description;
    public String shangping="";
    public int lookNum ;

    @Override
    public String toString() {
        return "FriendQinyouMessage{" +
                "img=" + img +
                ", name='" + name + '\'' +
                ", time='" + time + '\'' +
                ", possition='" + possition + '\'' +
                ", description='" + description + '\'' +
                ", lookNum=" + lookNum +
                '}';
    }
}
