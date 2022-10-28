package dev.tim.warnings.utils;

import com.google.gson.Gson;
import dev.tim.warnings.Main;
import dev.tim.warnings.models.Warning;
import org.bukkit.entity.Player;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WarningStorageUtil {

    private static ArrayList<Warning> warnings = new ArrayList<>();

    public static void createWarning(Player player, Player whoWarned, String reason){

        Warning warning = new Warning(player.getName(), whoWarned.getName(), reason);
        warnings.add(warning);

        try {
            saveWarnings();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Warning findWarningById(String id){
        for (Warning warning : warnings){
            if(warning.getId().equals(id)){
                return warning;
            }
        }
        return null;
    }

    public static Warning findWarningByPlayerName(String playerName){
        for (Warning warning : warnings){
            if(warning.getPlayerName().equals(playerName)){
                return warning;
            }
        }
        return null;
    }

    public static void deleteWarning(String id){
        for (Warning warning : warnings){
            if(warning.getId().equals(id)){
                warnings.remove(warning);
                break;
            }
        }
        try {
            saveWarnings();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<Warning> findAllWarnings() { return warnings; }

    public static void saveWarnings() throws IOException {

        Gson gson = new Gson();
        File file = new File(Main.getPlugin().getDataFolder().getAbsolutePath() + "/warnings.json");
        file.getParentFile().mkdir(); // maakt de folder voor de plugin aan
        file.createNewFile();   // maakt de file aan
        Writer writer = new FileWriter(file, false); // een Writer zodat we de data in de file kunnen schrijven
        gson.toJson(warnings, writer);
        writer.flush(); // zorgt ervoor dat het in de file wordt geschreven
        writer.close();

    }

    public static void loadWarnings() throws FileNotFoundException {

        Gson gson = new Gson();
        File file = new File(Main.getPlugin().getDataFolder().getAbsolutePath() + "/warnings.json");
        if(file.exists()){
            Reader reader = new FileReader(file);
            Warning[] n = gson.fromJson(reader, Warning[].class);
            warnings = new ArrayList<>(Arrays.asList(n));
        }

    }

}
