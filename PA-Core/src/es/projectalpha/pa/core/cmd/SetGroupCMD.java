package es.projectalpha.pa.core.cmd;

import es.projectalpha.pa.core.api.PAData;
import es.projectalpha.pa.core.api.PAServer;
import es.projectalpha.pa.core.api.PAUser;
import es.projectalpha.pa.core.utils.Utils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;

import java.util.List;

public class SetGroupCMD extends PACmd {

    public SetGroupCMD() {
        super("setgroup", Grupo.Admin, "dargrupo", "setgrupo", "rango");
    }

    @Override
    public void run(PAUser user, String label, String[] args) {
        if (args.length == 0) {
            user.sendMessage(PAData.CORE.getPrefix() + "&6Tu Rango: &" + Grupo.groupColor(user.getUserData().getGrupo()) + user.getUserData().getGrupo().toString());
            return;
        }

        if (args.length == 1) {
            Integer i;
            try {
                i = Integer.parseInt(args[0]);
            } catch (NumberFormatException ex) {
                PAUser target = PAServer.getUser(plugin.getServer().getPlayer(args[0]));
                Grupo g = target.getUserData().getGrupo();
                user.sendMessage(PAData.CORE.getPrefix() + "&6El rango de &c" + target.getName() + " &6es &" + Grupo.groupColor(g) + g.toString());
                return;
            }

            if (i > Grupo.values().length - 1) {
                user.sendMessage(PAData.CORE.getPrefix() + "&cEste número es más grande de los rangos que hay");
                return;
            }

            user.getUserData().setGrupo(Grupo.values()[i]);
            user.save();
            user.sendMessage(PAData.CORE.getPrefix() + "&3Rango cambiado: &" + Grupo.groupColor(Grupo.values()[i]) + Grupo.values()[i].toString());
            return;
        }

        if (args.length == 2) {
            Integer i;
            try {
                i = Integer.parseInt(args[1]);
            } catch (NumberFormatException ex) {
                user.sendMessage(PAData.CORE.getPrefix() + "&cEl rango no es un número");
                return;
            }

            if (i > Grupo.values().length - 1) {
                user.sendMessage(PAData.CORE.getPrefix() + "&cEste número es más grande de los rangos que hay");
                return;
            }
            PAUser target = PAServer.getUser(plugin.getServer().getPlayer(args[0]));

            if (target == null || !target.isOnline()) {
                userNotOnline(user);
                return;
            }

            target.getUserData().setGrupo(Grupo.values()[i]);
            target.save();
            user.sendMessage(PAData.CORE.getPrefix() + "&3Rango cambiado a &c" + target.getName() + " &3: &" + Grupo.groupColor(Grupo.values()[i]) + Grupo.values()[i].toString());
            target.sendMessage(PAData.CORE.getPrefix() + "&6Tu rango ha sido cambiado a &" + Grupo.groupColor(Grupo.values()[i]) + Grupo.values()[i].toString());
        }
    }

    @Override
    public void run(ConsoleCommandSender sender, String label, String[] args) {
        if (args.length == 2) {
            Integer i;
            try {
                i = Integer.parseInt(args[1]);
            } catch (NumberFormatException ex) {
                sender.sendMessage(PAData.CORE.getPrefix() + "&cEl rango no es un número");
                return;
            }

            if (i > Grupo.values().length - 1) {
                sender.sendMessage(PAData.CORE.getPrefix() + "&cEste número es más grande de los rangos que hay");
                return;
            }
            PAUser target = PAServer.getUser(plugin.getServer().getPlayer(args[0]));

            if (target == null || !target.isOnline()) {
                sender.sendMessage(Utils.colorize(PAData.CORE.getPrefix() + "&cEl jugador no está conectado"));
                return;
            }

            target.getUserData().setGrupo(Grupo.values()[i]);
            target.save();
            sender.sendMessage(Utils.colorize(PAData.CORE.getPrefix() + "&3Rango cambiado a &c" + target.getName() + " &3: &" + Grupo.groupColor(Grupo.values()[i]) + Grupo.values()[i].toString()));
            target.sendMessage(PAData.CORE.getPrefix() + "&6Tu rango ha sido cambiado a &" + Grupo.groupColor(Grupo.values()[i]) + Grupo.values()[i].toString());
        }
    }

    @Override
    public List<String> onTabComplete(CommandSender cs, Command cmd, String alias, String[] args, String curs, Integer curn) {
        return null;
    }
}
