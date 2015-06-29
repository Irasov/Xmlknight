package com.epam.irasov.xmlknight.reporter;


import com.epam.irasov.xmlknight.entity.Ammunition;
import com.epam.irasov.xmlknight.entity.Knight;

import java.util.List;

public class Report {

    public static void report(Knight knight){
        System.out.println(knight.toString());
    }

    public static void report(List<Ammunition> a){
        StringBuilder sb = new StringBuilder();
        sb.append("ammunition elements from the range: \n");
        for(Ammunition element:a){
            sb.append(element.toString()+"\n");
        }
        System.out.println(sb);
    }

}
