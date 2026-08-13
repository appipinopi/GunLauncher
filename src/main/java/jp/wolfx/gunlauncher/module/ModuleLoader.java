package jp.wolfx.gunlauncher.module;

import jp.wolfx.gunlauncher.GunLauncherPlugin;
import jp.wolfx.gunlauncher.api.CustomGun;
import jp.wolfx.gunlauncher.api.GunRegistry;
import jp.wolfx.gunlauncher.api.ItemRegistry;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class ModuleLoader {

    public static void loadModules(GunLauncherPlugin plugin) {
        File modulesDir = new File(plugin.getDataFolder(), "modules");
        if (!modulesDir.exists()) {
            modulesDir.mkdirs();
            plugin.getLogger().info("Created 'modules' folder at: " + modulesDir.getAbsolutePath());
            return;
        }

        File[] files = modulesDir.listFiles((dir, name) -> name.endsWith(".jar"));
        if (files == null || files.length == 0) {
            plugin.getLogger().info("No external gun modules (.jar) found in modules folder.");
            return;
        }

        for (File file : files) {
            try {
                plugin.getLogger().info("Loading gun module: " + file.getName());
                URLClassLoader classLoader = new URLClassLoader(
                        new URL[]{file.toURI().toURL()},
                        plugin.getClass().getClassLoader()
                );

                JarFile jar = new JarFile(file);
                jar.stream().filter(entry -> entry.getName().endsWith(".class")).forEach(entry -> {
                    String className = entry.getName()
                            .replace('/', '.')
                            .substring(0, entry.getName().length() - 6);
                    try {
                        Class<?> clazz = classLoader.loadClass(className);
                        if (CustomGun.class.isAssignableFrom(clazz) && !clazz.isInterface()) {
                            CustomGun gun = (CustomGun) clazz.getDeclaredConstructor().newInstance();
                            GunRegistry.registerGun(gun);
                            plugin.getLogger().info("Successfully loaded gun from module: " + gun.getId());
                        }
                    } catch (Throwable ignored) {
                    }
                });
                jar.close();
            } catch (Exception e) {
                plugin.getLogger().warning("Failed to load module " + file.getName() + ": " + e.getMessage());
            }
        }
    }
}
