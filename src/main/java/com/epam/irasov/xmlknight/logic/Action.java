package com.epam.irasov.xmlknight.logic;

import com.epam.irasov.xmlknight.entity.Ammunition;
import com.epam.irasov.xmlknight.entity.NamedEntity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Action {
    public static final String CONST_SORT_PRICE = "price";
    public static final String CONST_SORT_WEIGHT = "weight";
    public static final String CONST_SORT_NAME = "name";

    public static void sortAmmunition(List<Ammunition> a, String variant) {
        if (variant == CONST_SORT_WEIGHT) {
            Collections.sort(a, Ammunition.WEIGHT_ORDER);
        }
        if (variant == CONST_SORT_PRICE) {
            Collections.sort(a, Ammunition.PRICE_ORDER);
        }
        if (variant == CONST_SORT_NAME) {
            Collections.sort(a, NamedEntity.NAME_ORDER);
        }
    }

    public static List<Ammunition> searchPrice(List<Ammunition> a, int min, int max) {
        List<Ammunition> selectedAmmunition = new ArrayList<Ammunition>();
        for (Ammunition ammunition : a) {
            if (ammunition.getPrice() >= min && ammunition.getPrice() <= max) {
                selectedAmmunition.add(ammunition);
            }
        }
        return selectedAmmunition;
    }
}
