package jp.wolfx.gunmain;

import jp.wolfx.gunmain.api.GunRegistry;
import jp.wolfx.gunmain.api.CustomGun;
import org.bukkit.NamespacedKey;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.jar.JarFile;

public class GunMainPlugin extends JavaPlugin {
    private static GunMainPlugin instance;

    @Override
    public void onEnable() {
        instance = this;
        getLogger().info("=== Gun-Main (Core & Official Patch) Enabled ===");

        // Load external modules/addons from plugins/Gun-Main/modules/
        loadModules();

        getLogger().info("Total Registered Guns in Main Core: " + GunRegistry.getGuns().size());
    }

    @Override
    public void onDisable() {
        getLogger().info("Gun-Main Disabled.");
    }

    public static GunMainPlugin getInstance() {
        return instance;
    }

    private void loadModules() {
        File dir = new File(getDataFolder(), "modules");
        if (!dir.exists()) {
            dir.mkdirs();
            return;
        }

        File[] files = dir.listFiles((d, name) -> name.endsWith(".jar"));
        if (files == null) return;

        for (File file : files) {
            try {
                URLClassLoader loader = new URLClassLoader(new URL[]{file.toURI().toURL()}, getClass().getClassLoader());
                JarFile jar = new JarFile(file);
                jar.stream().filter(e -> e.getName().endsWith(".class")).forEach(e -> {
                    String className = e.getName().replace('/', '.').substring(0, e.getName().length() - 6);
                    try {
                        Class<?> clazz = loader.loadClass(className);
                        if (CustomGun.class.isAssignableFrom(clazz) && !clazz.isInterface()) {
                            CustomGun gun = (CustomGun) clazz.getDeclaredConstructor().newInstance();
                            GunRegistry.registerGun(gun);
                            getLogger().info("[Official Patch/Module] Loaded gun: " + gun.getId());
                        }
                    } catch (Throwable ignored) {}
                });
                jar.close();
            } catch (Exception e) {
                getLogger().warning("Failed to load module jar: " + file.getName());
            }
        }
    }
}
