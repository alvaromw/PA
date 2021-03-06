package es.projectalpha.pa.sur.files;

import lombok.Getter;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Files {

    @Getter public static File fileUser = new File("plugins/PA-Survival/", "users.yml");
    public static YamlConfiguration user = YamlConfiguration.loadConfiguration(fileUser);

    @Getter public static File fileStone = new File("plugins/PA-Survival/", "stones.yml");
    public static YamlConfiguration stone = YamlConfiguration.loadConfiguration(fileStone);

    @Getter public static File fileConfig = new File("plugins/PA-Survival/", "config.yml");
    public static YamlConfiguration config = YamlConfiguration.loadConfiguration(fileConfig);
    private int nmr = Files.user.getInt("nmr");
    public void setupFiles() {
        if (!fileUser.exists()) {
            fileUser.mkdir();
            user.set("recaudado", 0);
            user.set("loteria", 100);
            user.set("Users", new ArrayList<>());
        }

        if (!fileStone.exists()) {
            fileStone.mkdir();
            stone.set("tstones", 0);
        }

        if(!fileConfig.exists()){
            fileConfig.mkdir();
            config.set("Experiencia.vender", 0.2);
            config.set("Experiencia.comprar", 2.5);
            config.set("numeros", new ArrayList<>());
            config.set("nmr", 2);
        }

        saveFiles();
    }

    public static void saveFiles() {
        try {
            user.save(fileUser);
            user.load(fileUser);

            stone.save(fileStone);
            stone.load(fileStone);

            config.save(fileConfig);
            config.load(fileConfig);
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
    }

    public YamlConfiguration getUser() {
        return YamlConfiguration.loadConfiguration(fileUser);
    }
    public YamlConfiguration getStone() {
        return YamlConfiguration.loadConfiguration(fileStone);
    }


}
