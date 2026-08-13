package jp.wolfx.gunmodule.api;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class ItemRegistry {
    private static final Map<String, Attachment> attachments = new HashMap<>();

    public static void registerAttachment(Attachment attachment) {
        if (attachment != null && attachment.getId() != null) {
            attachments.put(attachment.getId().toLowerCase(), attachment);
        }
    }

    public static Attachment getAttachment(String id) {
        return id != null ? attachments.get(id.toLowerCase()) : null;
    }

    public static Collection<Attachment> getAttachments() {
        return Collections.unmodifiableCollection(attachments.values());
    }
}
