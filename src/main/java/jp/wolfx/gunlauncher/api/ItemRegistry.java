package jp.wolfx.gunlauncher.api;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class ItemRegistry {
    private static final Map<String, Ammunition> ammoMap = new HashMap<>();
    private static final Map<String, Attachment> attachmentMap = new HashMap<>();

    public static void registerAmmo(Ammunition ammo) {
        if (ammo != null && ammo.getId() != null) {
            ammoMap.put(ammo.getId().toLowerCase(), ammo);
        }
    }

    public static Ammunition getAmmo(String id) {
        return id != null ? ammoMap.get(id.toLowerCase()) : null;
    }

    public static Collection<Ammunition> getAmmos() {
        return Collections.unmodifiableCollection(ammoMap.values());
    }

    public static void registerAttachment(Attachment attachment) {
        if (attachment != null && attachment.getId() != null) {
            attachmentMap.put(attachment.getId().toLowerCase(), attachment);
        }
    }

    public static Attachment getAttachment(String id) {
        return id != null ? attachmentMap.get(id.toLowerCase()) : null;
    }

    public static Collection<Attachment> getAttachments() {
        return Collections.unmodifiableCollection(attachmentMap.values());
    }
}
