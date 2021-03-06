package es.projectalpha.pa.lobby.utils;

import es.projectalpha.pa.core.PACore;
import es.projectalpha.pa.core.api.PAData;
import es.projectalpha.pa.core.api.PAUser;
import es.projectalpha.pa.core.cmd.PACmd;
import es.projectalpha.pa.core.utils.ScoreboardUtil;
import es.projectalpha.pa.core.utils.Utils;
import es.projectalpha.pa.lobby.PALobby;
import lombok.Getter;
import org.bukkit.scheduler.BukkitRunnable;

public class Helpers {

    @Getter private PAUser u;

    public Helpers(PAUser u) {
        this.u = u;
    }

    public void lobbyScoreboard() {
        ScoreboardUtil board = new ScoreboardUtil(PAData.LOBBY.getPrefix(), "lobby");
        new BukkitRunnable() {
            @Override
            public void run() {
                if (u.getPlayer() == null) {
                    board.reset();
                    cancel();
                    return;
                }

                if (!u.isOnline()) {
                    board.reset();
                    cancel();
                    return;
                }

                board.setName(PAData.LOBBY.getOldPrefix());
                board.text(3, "§d ");
                board.text(2, "Rango: §" + PACmd.Grupo.groupColor(u.getUserData().getGrupo()) + u.getUserData().getGrupo().toString());
                board.text(1, "§d ");
                board.text(0, PACore.getIP().replace('&', '§'));
                board.build(u.getPlayer());
            }
        }.runTaskTimer(PALobby.getInstance(), 1, 20);
    }

    public void sendToSpawn() {
        u.teleport(Utils.stringToLocation(PALobby.getInstance().getConfig().getString("spawn")));
    }
}
