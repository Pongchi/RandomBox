package org.pongchi.randombox;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

public class RandomBoxCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;

            if (!p.isOp()) {
                p.sendMessage("You do not have permission to use this command.");
                return false;
            }

            if (args.length == 0) {
                StringBuilder sb = new StringBuilder();
                sb.append(ChatColor.GRAY + "-----------------------" + ChatColor.GOLD + "랜덤박스" + ChatColor.GRAY + "-----------------------\n");
                sb.append("\n");
                sb.append("/랜덤박스 생성 <name>\n");
                sb.append("/랜덤박스 보기 <name>\n");
                sb.append("/랜덤박스 아이템추가 <name>\n");
                sb.append("/랜덤박스 아이템삭제 <name>\n");
                sb.append("/랜덤박스 삭제 <name>\n");
                sb.append("\n");
                sb.append(ChatColor.GRAY + "-----------------------" + ChatColor.GOLD + "랜덤박스" + ChatColor.GRAY + "-----------------------\n");

                sender.sendMessage(sb.toString());
            }
            else if (args[0].equals("생성")) {
                if (args.length == 1) {
                    p.sendMessage("사용법: /랜덤박스 생성 <이름>");
                    return false;
                }
                else {
                    Box.create(p, args[1]);
                }
            }
            else if (args[0].equals("아이템추가")) {
                if (args.length == 1) {
                    p.sendMessage("사용법: /랜덤박스 아이템추가 <이름>");
                    return false;
                }
                else {
                    if (Box.addItem(args[1], p.getInventory().getItemInMainHand())) {
                        p.sendMessage("[랜덤박스] 아이템을 추가하였습니다.");
                    } else {
                        p.sendMessage("[랜덤박스] 아이템 추가에 실패하였습니다.");
                    }
                }
            }
        }
        else if (sender instanceof ConsoleCommandSender){
            sender.sendMessage("Warning - The console cannot run.");
            return false;
        }
        return true;
    }
}
