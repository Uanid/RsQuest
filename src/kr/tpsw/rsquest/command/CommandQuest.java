package kr.tpsw.rsquest.command;

import kr.tpsw.rsquest.convert.RsQuestMessage;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandQuest implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (sender instanceof Player) {
			RsQuestMessage.runCommand((Player) sender, label, args);
		} else {
			sender.sendMessage("�� ��ɾ�� ���� �÷��̾ ����� �����մϴ�.");
		}
		return true;
	}// �ƹ��ų� �Է½� ������ ���� �������� ����Ʈ ���
}
