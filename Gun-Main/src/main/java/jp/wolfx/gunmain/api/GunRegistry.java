package jp.wolfx.gunmain.api;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class GunRegistry {
    private static final Map<String, CustomGun> guns = new HashMap<>();

    public static void registerGun(CustomGun gun) {
        if (gun != null && gun.getId() != null) {
            guns.put(gun.getId().toLowerCase(), gun);
        }
    }

    public static CustomGun getGun(String id) {
        return id != null ? guns.get(id.toLowerCase()) : null;
    }

    public static Collection<CustomGun> getGuns() {
        return Collections.unmodifiableCollection(guns.values());
    }
}
