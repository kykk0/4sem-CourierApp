package com.example.laba5;

import com.example.laba5.constans.CourierAbility;
import java.util.HashSet;
import java.util.Set;
import java.util.ArrayList;

public class Courier {
    public Courier(String FIO, String checkingAccount) {
        this.FIO = FIO;
        this.checkingAccount = checkingAccount;
    }
    public Courier(){}

    private String FIO;
    private String checkingAccount;
    private Set<CourierAbility> abilities = new HashSet<>();

    public void addAbility(CourierAbility ability) {
        abilities.add(ability);
    }
    public void removeAbility(CourierAbility ability) {
        abilities.remove(ability);
    }
    public boolean hasAbility(CourierAbility ability) {
        return abilities.contains(ability);
    }
    public Set<CourierAbility> getAbilities() {
        return abilities;
    }

    public String getFIO() {
        return FIO;
    }

    public String getCheckingAccount() {
        return checkingAccount;
    }


}
