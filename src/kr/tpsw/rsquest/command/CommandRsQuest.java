package kr.tpsw.rsquest.command;

import kr.tpsw.rsquest.RsQuest;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import rsquest.kr.tpsw.api.executable.Executable;

public class CommandRsQuest implements CommandExecutor {
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		sender.sendMessage("��6�÷����� �̸�:��c RsQuest");
		sender.sendMessage("��6����:��c " + RsQuest.version);
		sender.sendMessage("��6������:��c TPsw");
		sender.sendMessage("��6���Ⱥ��� ����:��c " + RsQuest.isEpicBossHooked);
		
		for (String line : Executable.getInfo()) {
			sender.sendMessage(line);
		}

		// ���� �����ִ� ��ɾ�
		return true;
	}

}
