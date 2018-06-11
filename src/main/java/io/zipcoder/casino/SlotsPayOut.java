package io.zipcoder.casino;

import java.util.HashMap;
import java.util.Map;

public class SlotsPayOut {
    public Map<String, Integer> payOutSchedule = new HashMap<String, Integer>() {
    };

    public SlotsPayOut() {
        payOutSchedule.put("3W",30);
        payOutSchedule.put("3S",25);
        payOutSchedule.put("2S/W",18);
        payOutSchedule.put("3B",14);
        payOutSchedule.put("2B/W",10);
        payOutSchedule.put("B/2W",7);
        payOutSchedule.put("3C",4);
        payOutSchedule.put("2C/W",2);
        payOutSchedule.put("C/2W",1);
    }

    public Integer getValue(String key){
        return payOutSchedule.get(key);
    }
}

