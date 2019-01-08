package kr.tpsw.rsquest.convert;

import java.util.LinkedList;
import java.util.List;

import kr.tpsw.rsquest.RsQuest;
import kr.tpsw.rsquest.api.QsPlayer;
import kr.tpsw.rsquest.api.QsPlayerAPI;
import kr.tpsw.rsquest.api.QuestData;
import kr.tpsw.rsquest.quest.Quest;

import org.bukkit.entity.Player;

import rsquest.kr.tpsw.api.publica.YamlConfiguration;

public class RsQuestMessage {

	private static YamlConfiguration msg = new YamlConfiguration("plugins\\" + RsQuest.pluginName + "\\message.yml");

	private static String LINK_QUEST_COUNT;// ����Ʈ ���
	private static List<String> LINK_QUEST;
	private static String DO_NOT_HAVE_LINK_QUEST;// ������ �ִ� ������ ����

	private static String RANDOM_QUEST_COUNT;// ����Ʈ ���
	private static List<String> RANDOM_QUEST;
	private static String DO_NOT_HAVE_RANDOM_QUEST;// ������ �ִ� ������ ����

	private static String IS_ACHIVED_RANDOM_QUEST;// ���� ����Ʈ �޼� ����
	private static String ALREADY_ACHIVED_RANDOM_QUEST;// �̹� ������ �޼�
	private static String ALREADY_USED_REFESH;// �̹� ���ΰ�ħ ���
	private static String HOW_TO_REFRESH_RANDOM_QUEST;// ����Ʈ ���� ��ħ�� ����Ϸ���...
	private static String USE_RANDOM_QUEST_REFRESH;// ����Ʈ�� ���� �ο��޽��ϴ�.

	private static String FIRST_LINK_QUEST;// 1) ������
	private static String SECOND_RANDOM_QUEST;// 2) ������
	private static String LINEEEEEEEEEE;// ================
	
	public static String RANDOM_QUEST_UPDATE_MSG;

	public static boolean initLoadMessage() {
		if (msg.isList("quest.RANDOM_QUEST")) {
			RANDOM_QUEST = msg.getStringList("quest.RANDOM_QUEST");
		} else {
			return false;
		}
		if (msg.isList("quest.LINK_QUEST")) {
			LINK_QUEST = msg.getStringList("quest.LINK_QUEST");
		} else {
			return false;
		}
		LINK_QUEST_COUNT = msg.getString("quest.LINK_QUEST_COUNT");
		DO_NOT_HAVE_LINK_QUEST = msg.getString("quest.DO_NOT_HAVE_LINK_QUEST");
		RANDOM_QUEST_COUNT = msg.getString("quest.RANDOM_QUEST_COUNT");
		DO_NOT_HAVE_RANDOM_QUEST = msg.getString("quest.DO_NOT_HAVE_RANDOM_QUEST");
		IS_ACHIVED_RANDOM_QUEST = msg.getString("quest.IS_ACHIVED_RANDOM_QUEST");
		ALREADY_ACHIVED_RANDOM_QUEST = msg.getString("quest.ALREADY_ACHIVED_RANDOM_QUEST");
		ALREADY_USED_REFESH = msg.getString("quest.ALREADY_USED_REFESH");
		HOW_TO_REFRESH_RANDOM_QUEST = msg.getString("quest.HOW_TO_REFRESH_RANDOM_QUEST");
		USE_RANDOM_QUEST_REFRESH = msg.getString("quest.USE_RANDOM_QUEST_REFRESH");
		FIRST_LINK_QUEST = msg.getString("quest.FIRST_LINK_QUEST");
		SECOND_RANDOM_QUEST = msg.getString("quest.SECOND_RANDOM_QUEST");
		LINEEEEEEEEEE = msg.getString("quest.LINEEEEEEEEEE");
		RANDOM_QUEST_UPDATE_MSG = msg.getString("qsadmin.RANDOM_QUEST_UPDATE_MSG");
		return true;
	}

	public static void saveDefaultMessage() {
		msg.set("quest.FIRST_LINK_QUEST", "��b1) ��a���� ����Ʈ");
		msg.set("quest.LINK_QUEST_COUNT", "��a����Ʈ ���: ��7(<clearcount>/<updatecount>)");
		List<String> list = new LinkedList<String>();
		list.add("��6����: ��f<display>");
		list.add("��6����: ��f<description>");
		list.add("��6����: ��f<reward>");
		msg.set("quest.LINK_QUEST", list);
		msg.set("quest.DO_NOT_HAVE_LINK_QUEST", "��6������ �ִ� ���� ����Ʈ�� �����ϴ�.");
		msg.set("quest.LINEEEEEEEEEE", "��7============================");
		
		msg.set("quest.ALREADY_USED_REFESH", "��6�̹� ���ΰ�ħ�� ����߽��ϴ�.");
		msg.set("quest.USE_RANDOM_QUEST_REFRESH", "��6����Ʈ�� ���� �ο��޽��ϴ�.");
		msg.set("quest.ALREADY_ACHIVED_RANDOM_QUEST", "��6�̹� ���� ����Ʈ�� �޼��߽��ϴ�.");
		
		msg.set("quest.SECOND_RANDOM_QUEST", "��b2) ��a���� ����Ʈ");
		msg.set("quest.IS_ACHIVED_RANDOM_QUEST", "��a���� ����Ʈ �޼� ����: <boolean>");
		msg.set("quest.RANDOM_QUEST_COUNT", "��a����Ʈ ���: ��7(<clearcount>/<updatecount>)");
		msg.set("quest.RANDOM_QUEST", new LinkedList<String>(list));
		msg.set("quest.DO_NOT_HAVE_RANDOM_QUEST", "��6������ �ִ� ���� ����Ʈ�� �����ϴ�.");
		msg.set("quest.HOW_TO_REFRESH_RANDOM_QUEST", "��7����Ʈ ���� ��ħ�� ����Ϸ��� ��e/<cmd>");
		
		msg.set("qsadmin.RANDOM_QUEST_UPDATE_MSG", "��c[RsQuest] ��6��� ������ ���� ����Ʈ�� ���� �ο��߽��ϴ�.");
		msg.saveYaml();
	}

	public static void runCommand(Player sender, String label, String[] args) {
		QsPlayer qp = QsPlayerAPI.getPlayer(sender.getName());

		QuestData qd = qp.getLinkQuestData();
		sender.sendMessage(FIRST_LINK_QUEST);
		sender.sendMessage(LINK_QUEST_COUNT.replace("<clearcount>", String.valueOf(qd.getquestClearCount())).replace("<updatecount>", String.valueOf(qd.getquestUpdateCount())));
		if (qd.hasQuest()) {
			Quest qs = qd.getQuest();
			sender.sendMessage(LINK_QUEST.get(0).replace("<display>", qs.getDisplay()));
			sender.sendMessage(LINK_QUEST.get(1).replace("<description>", qs.getDescription(qd.getEndCount())));
			sender.sendMessage(LINK_QUEST.get(2).replace("<reward>", qs.getReward().getDisplayMsg()));
		} else {
			sender.sendMessage(DO_NOT_HAVE_LINK_QUEST);
		}
		// ��ũ ����Ʈ
		sender.sendMessage(LINEEEEEEEEEE);

		if (args.length == 1 && (args[0].equals("refresh") || args[0].equals("���ΰ�ħ"))) {
			if (qp.isRefreshedRandomQuest()) {
				sender.sendMessage(ALREADY_USED_REFESH);
			} else if (!qp.isAchievedRandomQuest() && qp.getRandomQuestData().hasQuest()) {
				qp.setNewRandomQuest(true);
				sender.sendMessage(USE_RANDOM_QUEST_REFRESH);
			} else {
				sender.sendMessage(ALREADY_ACHIVED_RANDOM_QUEST);
			}
			return;
		}// ��������
		String bool = qp.isAchievedRandomQuest() ? "��btrue" : "��cfalse";
		qd = qp.getRandomQuestData();
		sender.sendMessage(SECOND_RANDOM_QUEST);
		sender.sendMessage(IS_ACHIVED_RANDOM_QUEST.replace("<boolean>", String.valueOf(bool)));
		sender.sendMessage(RANDOM_QUEST_COUNT.replace("<clearcount>", String.valueOf(qd.getquestClearCount())).replace("<updatecount>", String.valueOf(qd.getquestUpdateCount())));
		if (!qp.isAchievedRandomQuest() && qd.hasQuest()) {
			Quest qs = qd.getQuest();
			sender.sendMessage(RANDOM_QUEST.get(0).replace("<display>", qs.getDisplay()));
			sender.sendMessage(RANDOM_QUEST.get(1).replace("<description>", qs.getDescription(qd.getEndCount())));
			sender.sendMessage(RANDOM_QUEST.get(2).replace("<reward>", qs.getReward().getDisplayMsg()));
			if (qp.isRefreshedRandomQuest()) {
				sender.sendMessage(ALREADY_USED_REFESH);
			} else {
				String str = label.equals("quest") ? "quest refresh" : "����Ʈ ���ΰ�ħ";
				sender.sendMessage(HOW_TO_REFRESH_RANDOM_QUEST.replace("<cmd>", str));
			}
		} else if (qp.isAchievedRandomQuest()) {
			sender.sendMessage(ALREADY_ACHIVED_RANDOM_QUEST);
		} else {
			sender.sendMessage(DO_NOT_HAVE_RANDOM_QUEST);
		}

		// ���� ����Ʈ

	}
}
