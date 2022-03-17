package br.com.tulio.swresistancesocialnetwork.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ItemType {
    WEAPON ("Weapon", 4),
    AMMO ("Ammo", 3),
    WATER ("Water", 2),
    FOOD ("Food", 1);

    final String name;
    final int points;

}
