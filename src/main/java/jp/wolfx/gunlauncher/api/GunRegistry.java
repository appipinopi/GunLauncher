package jp.wolfx.gunlauncher.api;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class GunRegistry {
    private static final Map<String, CustomGun> registeredGuns = new HashMap<>();

    public static void registerGun(CustomGun gun) {
        if (gun == null || gun.getId() == null) return;
        registeredGuns.put(gun.getId().toLowerCase(), gun);
    }

    public static void unregisterGun(String id) {
        if (id != null) {
            registeredGuns.remove(id.toLowerCase());
        }
    }

    public static CustomGun getGun(String id) {
        if (id == null) return null;
        return registeredGuns.get(id.toLowerCase());
    }

    public static Collection<CustomGun> getGuns() {
        return Collections.unmodifiableCollection(registeredGuns.values());
    }
}
