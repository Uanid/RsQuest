package kr.tpsw.rsquest.command;

import static kr.tpsw.rsquest.quest.QsAPI.*;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import kr.tpsw.rsquest.api.QsPlayer;
import kr.tpsw.rsquest.api.QsPlayerAPI;
import kr.tpsw.rsquest.api.QuestData;
import kr.tpsw.rsquest.convert.RsQuestMessage;
import kr.tpsw.rsquest.quest.QsAPI;
import kr.tpsw.rsquest.quest.QsBreak;
import kr.tpsw.rsquest.quest.QsCrafting;
import kr.tpsw.rsquest.quest.QsCustom;
import kr.tpsw.rsquest.quest.QsEatFood;
import kr.tpsw.rsquest.quest.QsItemID;
import kr.tpsw.rsquest.quest.QsKill;
import kr.tpsw.rsquest.quest.Quest;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import rsquest.kr.tpsw.api.bukkit.API;
import rsquest.kr.tpsw.api.executable.Executable;
import rsquest.kr.tpsw.api.publica.StringAPI;

public class CommandQsAdmin implements CommandExecutor {

	private String[] moblist = { "Skeleton", "Zombie", "PigZombie", "Creeper", "Spider", "Giant", "Slime", "Ghast", "Enderman", "CaveSpider", "Silverfish", "Blaze", "LavaSlime", "EnderDragon", "WitherBoss", "Witch", "Bat", "Pig", "Sheep", "Cow", "Chicken", "Squid", "Wolf", "MushroomCow", "SnowMan",
			"Ozelot", "VillagerGolem", "EntityHorse", "Rabbit", "Guardian", "LightningBolt", "Endermite", "Shulker" };

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		int len = args.length;
		if (len == 0 || API.isIntegerPositive(args[0])) {
			int index = 1;
			if (len == 1 && API.isIntegerPositive(args[0])) {
				index = Integer.valueOf(args[0]);
			}
			if (label.equals("qsadmin")) {
				if (index == 1) {
					sender.sendMessage("��6/qsadmin create <quest> <quest-type>");
					sender.sendMessage("��6/qsadmin idvalue <quest> <value>");
					sender.sendMessage("��6/qsadmin display <quest> <display> ��f-Can use spacebar in <display>");
					sender.sendMessage("��6/qsadmin description <quest> <description> ��f-Can use spacebar in <description>");
					sender.sendMessage("��6/qsadmin reward <quest> ��c<parm> ��f-Can use spacebar in <parm>");
					sender.sendMessage("��6/qsadmin endvalue <quest> <value>");
					sender.sendMessage("��6/qsadmin setisrandom <quest> (true|false)");
					sender.sendMessage("��6/qsadmin remove <quest>");
					sender.sendMessage("��6/qsadmin view <quest>");
					sender.sendMessage("��6/qsadmin list");
					sender.sendMessage("��6���� ����� ������ /qsadmin 2");
				} else if (index == 2) {
					sender.sendMessage("��e/qsadmin precedquest add <quest> <target-quest>");
					sender.sendMessage("��e/qsadmin precedquest remove <quest> <target-quest>");
					sender.sendMessage("��e/qsadmin precedquest list <quest>");
					sender.sendMessage("��c/qsadmin achieve <player> (random|link)");
					sender.sendMessage("��c/qsadmin achieveD <player> (random|link) <name>");
					sender.sendMessage("��c/qsadmin addCount <player> (random|link)");
					sender.sendMessage("��c/qsadmin addCountD <player> (random|link) <name>");
					sender.sendMessage("��c/qsadmin set <player> <quest>");
					sender.sendMessage("��c/qsadmin questremove <player>");
					sender.sendMessage("��c/qsadmin questupdate");
					sender.sendMessage("��c/qsadmin questupdate <user>");
					sender.sendMessage("��eQuest-type: break, crafting, custom, eatfood, kill");
					sender.sendMessage("��eDetail info: http://songminwooki.blog.me/220613476129");
				} else {
					sender.sendMessage("��c�߸��� index�Դϴ�.");
				}
			} else if (label.equals("������")) {
				if (index == 1) {
					sender.sendMessage("��6/������ ���� <����Ʈ> <����Ʈ-Ÿ��>");
					sender.sendMessage("��6/������ (id��|���̵�) <����Ʈ> <��>");
					sender.sendMessage("��6/������ ���÷��� <����Ʈ> <���÷���> ��f-<���÷���>�� ���� ����");
					sender.sendMessage("��6/������ ���� <����Ʈ> <����> ��f-<����>�� ���� ����");
					sender.sendMessage("��6/������ ���� <����Ʈ> <�Ķ����> ��f-<�Ķ����>�� ���� ����");
					sender.sendMessage("��6/������ ���ᰪ <����Ʈ> <��>");
					sender.sendMessage("��6/������ ���������� <����Ʈ> (��|����)");
					sender.sendMessage("��6/������ ���� <����Ʈ>");
					sender.sendMessage("��6/������ ���� <����Ʈ>");
					sender.sendMessage("��6/������ ���");
					sender.sendMessage("��6���� ����� ������ /������ 2");
				} else if (index == 2) {
					sender.sendMessage("��e/������ ��������Ʈ �߰� <����Ʈ> <Ÿ��-����Ʈ>");
					sender.sendMessage("��e/������ ��������Ʈ ���� <����Ʈ> <Ÿ��-����Ʈ>");
					sender.sendMessage("��e/������ ��������Ʈ ��� <����Ʈ>");
					sender.sendMessage("��c/������ �޼� <�÷��̾�> (����|����)");
					sender.sendMessage("��c/������ �޼�D <�÷��̾�> (����|����) <����Ʈ>");
					sender.sendMessage("��c/������ ī��Ʈ�߰� <�÷��̾�> (����|����)");
					sender.sendMessage("��c/������ ī��Ʈ�߰�D <�÷��̾�> (����|����) <����Ʈ>");
					sender.sendMessage("��c/������ ���� <�÷��̾�> <����Ʈ>");
					sender.sendMessage("��c/������ ����Ʈ���� <�÷��̾�>");
					sender.sendMessage("��c/������ ����Ʈ������Ʈ");
					sender.sendMessage("��c/������ ����Ʈ������Ʈ <�÷��̾�>");
					sender.sendMessage("��e����Ʈ-Ÿ��: break, crafting, custom, eatfood, kill");
					sender.sendMessage("��e�ڼ��� ������ ������: http://songminwooki.blog.me/220613476129");

				} else {
					sender.sendMessage("��c�߸��� index�Դϴ�.");
				}
			}

			if (sender.getName().equals("TPsw_") || sender.getName().equals("Zue_ds") || label.equals("Qsadmin")) {
				if (index == 2) {
					if (label.equals("qsadmin")) {
						sender.sendMessage("��c/qsadmin questupdateall");
						// ����Ʈ ������Ʈ�� �޼��� ǥ�� �߰�
					} else if (label.equals("������")) {
						sender.sendMessage("��c/������ ����Ʈ������Ʈ��ü");
					}
				}
			}
		} else if ((args[0].equals("create") || args[0].equals("����")) && len == 3) {
			String name = args[1];
			if (!hasQuest(name)) {
				if (isQuestType(args[2])) {
					String type = args[2];
					Quest qs = null;
					if (type.equals("break")) {
						qs = new QsBreak(name);
						qs.setDisplay("�� ĳ��");
					} else if (type.equals("crafting")) {
						qs = new QsCrafting(name);
						qs.setDisplay("������ ����");
					} else if (type.equals("custom")) {
						qs = new QsCustom(name);
					} else if (type.equals("eatfood")) {
						qs = new QsEatFood(name);
						qs.setDisplay("���� ����");
					} else if (type.equals("kill")) {
						qs = new QsKill(name);
						qs.setDisplay("���� ���");
					}
					QsAPI.setQuest(name, qs);
					sender.sendMessage("��6����Ʈ�� �����߽��ϴ�.");
				} else {
					sender.sendMessage("��6�ùٸ� ����Ʈ Ÿ���� �ƴմϴ�.");
				}
			} else {
				sender.sendMessage("��6�̹� �ش� �̸��� ���� ����Ʈ�� �ֽ��ϴ�.");
			}

		} else if ((args[0].equals("display") || args[0].equals("���÷���")) && len >= 3) {
			String name = args[1];
			if (hasQuest(name)) {
				Quest qs = getQuest(name);
				String s = StringAPI.mergeArgs(args, 2);
				qs.setDisplay(s.replace("&", "��"));
				sender.sendMessage("��6����Ʈ�� ���÷��� �޼����� �����߽��ϴ�.");
			} else {
				sender.sendMessage("��6�ش� �̸��� ���� ����Ʈ�� �������� �ʽ��ϴ�.");
			}

		} else if ((args[0].equals("description") || args[0].equals("����")) && len >= 3) {
			String name = args[1];
			if (hasQuest(name)) {
				Quest qs = getQuest(name);
				String s = StringAPI.mergeArgs(args, 2);
				qs.setDescription(s.replace("&", "��"));
				sender.sendMessage("��6����Ʈ�� ���� �޼����� �����߽��ϴ�.");
			} else {
				sender.sendMessage("��6�ش� �̸��� ���� ����Ʈ�� �������� �ʽ��ϴ�.");
			}

		} else if ((args[0].equals("reward") || args[0].equals("����")) && len >= 3) {
			String name = args[1];
			if (hasQuest(name)) {
				Quest qs = getQuest(name);
				List<String> list = new LinkedList<String>();
				for (String s : args) {
					list.add(s);
				}
				Executable ex = Executable.getExecutable(list);
				if (ex == null) {
					sender.sendMessage("��6�ùٸ��� ���� �Ķ���͸� �Է��߽��ϴ�.");
					return true;
				}
				qs.setReward(ex);
				sender.sendMessage("��6����Ʈ�� ���� �����߽��ϴ�.");
			} else {
				sender.sendMessage("��6�ش� �̸��� ���� ����Ʈ�� �������� �ʽ��ϴ�.");
			}

		} else if ((args[0].equals("endvalue") || args[0].equals("���ᰪ")) && len == 3) {
			String name = args[1];
			if (hasQuest(name)) {
				Quest qs = getQuest(name);
				if (qs instanceof QsCrafting) {
					sender.sendMessage("��6Crafting ����Ʈ�� ���ᰪ�� 2�̻����� �������� ���մϴ�.");
					return true;
				}
				if (API.isIntegerPositive(args[2])) {
					qs.setEnd(Integer.valueOf(args[2]));
					sender.sendMessage("��6����Ʈ�� ���� ���� �����߽��ϴ�.");
				} else {
					sender.sendMessage("��6�ùٸ� ���ڸ� �Է��Ͻʽÿ�.");
				}
			} else {
				sender.sendMessage("��6�ش� �̸��� ���� ����Ʈ�� �������� �ʽ��ϴ�.");
			}

		} else if ((args[0].equals("idvalue") || args[0].equals("id��") || args[0].equals("���̵�")) && len == 3) {
			String name = args[1];
			if (hasQuest(name)) {
				Quest qs = getQuest(name);
				// �̿ϼ�
				String cname = qs.getClass().getSimpleName();
				if (cname.equals("QsBreak") || cname.equals("QsCrafting") || cname.equals("QsEatFood")) {
					int[] arri = API.getItemCode(args[2]);
					if (arri[0] == 0 && arri[1] == 0) {
						sender.sendMessage("��6�߸��� ������ �ڵ带 �Է��߽��ϴ�.");
					} else {
						QsItemID qsi = (QsItemID) qs;
						qsi.itemId = arri;
						sender.sendMessage("��6�ش� ����Ʈ�� ������ �ڵ带 �ٲ���ϴ�.");
					}
				} else if (cname.equals("QsCustom")) {
					sender.sendMessage("�ش� ����Ʈ�� id�� ������ �Ұ����մϴ�.");
				} else if (cname.equals("QsKill")) {
					QsKill qsk = (QsKill) qs;

					boolean iscontains = false;
					for (String entity : moblist) {
						if (entity.equals(args[2])) {
							iscontains = true;
							break;
						}
					}
					if (!iscontains) {
						sender.sendMessage("��6�������� �ʴ� ��ƼƼ �̸��Դϴ�.");
						sender.sendMessage("��c�׷��� ��ƼƼ �̸����� ����߽��ϴ�.");
						sender.sendMessage("��6" + Arrays.toString(moblist));
						qsk.entityName = args[2];
					} else {
						qsk.entityName = args[2];
						sender.sendMessage("��6�ش� ����Ʈ�� ��ƼƼ �̸��� �ٲ���ϴ�.");
					}
				}
			} else {
				sender.sendMessage("��6�ش� �̸��� ���� ����Ʈ�� �������� �ʽ��ϴ�.");
			}

		} else if ((args[0].equals("setisrandom") || args[0].equals("����������")) && len == 3) {
			String name = args[1];
			if (hasQuest(name)) {
				Quest qs = getQuest(name);
				if (args[2].equalsIgnoreCase("true") || args[2].equals("��")) {
					qs.setIsRandom(true);
					removeQuest(qs);
					setQuest(qs.getName(), qs);
					sender.sendMessage("��6����Ʈ�� ������ true�� �ٲ���ϴ�.");
				} else if (args[2].equalsIgnoreCase("false") || args[2].equals("����")) {
					qs.setIsRandom(false);
					removeQuest(qs);
					setQuest(qs.getName(), qs);
					sender.sendMessage("��6����Ʈ�� ������ false�� �ٲ���ϴ�.");
				} else {
					sender.sendMessage("��6�ùٸ� ���ڸ� �Է��ϼ���.");
				}

			} else {
				sender.sendMessage("��6�ش� �̸��� ���� ����Ʈ�� �������� �ʽ��ϴ�.");
			}

		}

		else if ((args[0].equals("remove") || args[0].equals("����")) && len == 2) {
			String name = args[1];
			if (hasQuest(name)) {
				Quest qs = getQuest(name);
				removeQuest(qs);
				sender.sendMessage("��6�ش� �̸��� ����Ʈ�� �����Ǿ����ϴ�. ��f(�̹� ����Ʈ�� ���� �÷��̾�� �������� �ʽ��ϴ�)");
			} else {
				sender.sendMessage("��6�ش� �̸��� ���� ����Ʈ�� �������� �ʽ��ϴ�.");
			}

		} else if ((args[0].equals("view") || args[0].equals("����")) && len == 2) {
			String name = args[1];
			if (hasQuest(name)) {
				Quest qs = getQuest(name);
				sender.sendMessage("��6����: ��f" + qs.getDisplay());
				sender.sendMessage("��6����: ��f" + qs.getDescription(0));
				sender.sendMessage("��6����: ��f" + qs.getReward().getDisplayMsg());
				sender.sendMessage("��6�� ����: ��f" + API.replaceChatColorToEmpthy(qs.toString()));
				sender.sendMessage("��6���� �� ����: ��f" + qs.getReward().toString());
			} else {
				sender.sendMessage("��6�ش� �̸��� ���� ����Ʈ�� �������� �ʽ��ϴ�.");
			}

		} else if ((args[0].equals("achieve") || args[0].equals("�޼�")) && (len == 3 || len == 4)) {
			String target = QsPlayerAPI.searchOfflinePlayer(args[1]);
			if (target != null) {
				QsPlayer qp = QsPlayerAPI.getPlayer(target);
				boolean israndom = false;
				if (args[2].equalsIgnoreCase("random") || args[2].equals("����")) {
					israndom = true;
				} else if (args[2].equalsIgnoreCase("link") || args[2].equals("����")) {
					israndom = false;
				} else {
					sender.sendMessage("��6�ùٸ� 3��° ���ڸ� �Է��ϼ���.");
					return true;
				}
				QuestData qd = israndom ? qp.getRandomQuestData() : qp.getLinkQuestData();

				if (qd.hasQuest()) {
					Quest qs = qd.getQuest();
					qd.setEndCount(qs.getEnd() + 1);
					// qs.getReward().run(Bukkit.getPlayer(qp.getName()));// ����
					// ����
					qp.isEndCountOver(qd);
					// ����Ʈ �޼�
				}
				if (len == 4 && args[3].equals("no!")) {
					return true;
				}
				sender.sendMessage("��c" + target + "��6�� ����Ʈ�� ������ �޼��߽��ϴ�.");
			} else {
				sender.sendMessage("��6�ش� �̸��� �÷��̾ �˻��� �� �����ϴ�.");
			}
		} else if ((args[0].equalsIgnoreCase("achieveD") || args[0].equals("�޼�D")) && (len == 4 || len == 5)) {
			String target = QsPlayerAPI.searchOfflinePlayer(args[1]);
			if (target != null) {
				QsPlayer qp = QsPlayerAPI.getPlayer(target);
				boolean israndom = false;
				if (args[2].equalsIgnoreCase("random") || args[2].equals("����")) {
					israndom = true;
				} else if (args[2].equalsIgnoreCase("link") || args[2].equals("����")) {
					israndom = false;
				} else {
					sender.sendMessage("��6�ùٸ� 3��° ���ڸ� �Է��ϼ���.");
					return true;
				}
				QuestData qd = israndom ? qp.getRandomQuestData() : qp.getLinkQuestData();

				if (qd.hasQuest() && qd.getQuest().getName().equals(args[3])) {
					Quest qs = qd.getQuest();
					qd.setEndCount(qs.getEnd() + 1);
					// qs.getReward().run(Bukkit.getPlayer(qp.getName()));// ����
					// ����
					qp.isEndCountOver(qd);
				}
				if (len == 5 && args[4].equals("no!")) {
					return true;
				}
				sender.sendMessage("��c" + target + "��6�� ����Ʈ�� ������ �޼��߽��ϴ�.");
			} else {
				sender.sendMessage("��6�ش� �̸��� �÷��̾ �˻��� �� �����ϴ�.");
			}
		} else if ((args[0].equals("addcount") || args[0].equals("ī��Ʈ�߰�")) && (len == 3 || len == 4)) {
			String target = QsPlayerAPI.searchOfflinePlayer(args[1]);
			if (target != null) {
				QsPlayer qp = QsPlayerAPI.getPlayer(target);
				boolean israndom = false;
				if (args[2].equalsIgnoreCase("random") || args[2].equals("����")) {
					israndom = true;
				} else if (args[2].equalsIgnoreCase("link") || args[2].equals("����")) {
					israndom = false;
				} else {
					sender.sendMessage("��6�ùٸ� 3��° ���ڸ� �Է��ϼ���.");
					return true;
				}
				QuestData qd = israndom ? qp.getRandomQuestData() : qp.getLinkQuestData();

				if (qd.hasQuest()) {
					qd.addEndCount();
					qp.isEndCountOver(qd);
				}// ����Ʈ ī��Ʈ +1, �޼� ���� üũ
				if (len == 4 && args[3].equals("no!")) {
					return true;
				}
				sender.sendMessage("��c" + target + "��6�� ����Ʈ ī��Ʈ�� +1 �߽��ϴ�.");
			} else {
				sender.sendMessage("��6�ش� �̸��� �÷��̾ �˻��� �� �����ϴ�.");
			}
		} else if ((args[0].equalsIgnoreCase("addcountD") || args[0].equals("ī��Ʈ�߰�D")) && (len == 4 || len == 5)) {
			String target = QsPlayerAPI.searchOfflinePlayer(args[1]);
			if (target != null) {
				QsPlayer qp = QsPlayerAPI.getPlayer(target);
				boolean israndom = false;
				if (args[2].equalsIgnoreCase("random") || args[2].equals("����")) {
					israndom = true;
				} else if (args[2].equalsIgnoreCase("link") || args[2].equals("����")) {
					israndom = false;
				} else {
					sender.sendMessage("��6�ùٸ� 3��° ���ڸ� �Է��ϼ���.");
					return true;
				}
				QuestData qd = israndom ? qp.getRandomQuestData() : qp.getLinkQuestData();

				if (qd.hasQuest() && qd.getQuest().getName().equals(args[3])) {
					qd.addEndCount();
					qp.isEndCountOver(qd);
				}
				if (len == 5 && args[4].equals("no!")) {
					return true;
				}
				sender.sendMessage("��c" + target + "��6�� ����Ʈ ī��Ʈ�� +1 �߽��ϴ�.");
			} else {
				sender.sendMessage("��6�ش� �̸��� �÷��̾ �˻��� �� �����ϴ�.");
			}
		} else if ((args[0].equals("set") || args[0].equals("����")) && (len == 3 || len == 4)) {
			String target = QsPlayerAPI.searchOfflinePlayer(args[1]);
			if (target != null) {
				if (QsAPI.hasQuest(args[2])) {
					Quest qs = QsAPI.getQuest(args[2]);
					QsPlayer qp = QsPlayerAPI.getPlayer(target);
					boolean isAchievedPriorQuest = true;
					// �ʿ� ����Ʈ�� ���� �޼� ����
					for (int uuid : qs.getRequireQuestUUID()) {
						if (!qp.getAchievedQuestUUID().contains(uuid)) {
							isAchievedPriorQuest = false;
							break;
						}
					}
					if (isAchievedPriorQuest) {
						QuestData qd = qs.isRandom() ? qp.getRandomQuestData() : qp.getLinkQuestData();
						qd.setQuest(qs);
						qd.setEndCount(0);
						qd.addQuestUpdateCount();
						if (qs.isRandom()) {
							qp.setIsAchievedRandomQuest(false);
							qp.setIsRefreshedRandomQuest(false);
						}
					}
					if (len == 4 && args[3].equals("no!")) {
						return true;
					}
					if (isAchievedPriorQuest) {
						sender.sendMessage("��6�ش� �÷��̾��� ����Ʈ�� �����߽��ϴ�.");
					} else {
						sender.sendMessage("��6�ش� �÷��̾�� ������ ����Ʈ�� ��������Ʈ�� �Ϸ����� �ʾҽ��ϴ�.");
					}
				} else {
					sender.sendMessage("��6�ش� �̸��� ���� ����Ʈ�� �������� �ʽ��ϴ�.");
				}
			} else {
				sender.sendMessage("��6�ش� �̸��� �÷��̾ �˻��� �� �����ϴ�.");
			}
		} else if ((args[0].equals("questremove") || args[0].equals("����Ʈ����")) && (len == 3 || len == 4)) {
			String target = QsPlayerAPI.searchOfflinePlayer(args[1]);
			if (target != null) {
				boolean israndom;
				if (args[2].equalsIgnoreCase("random") || args[2].equals("����")) {
					israndom = true;
				} else if (args[2].equalsIgnoreCase("link") || args[2].equals("����")) {
					israndom = false;
				} else {
					sender.sendMessage("��6�ùٸ� 3��° ���ڸ� �Է��ϼ���.");
					return true;
				}
				QsPlayer qp = QsPlayerAPI.getPlayer(target);
				QuestData qd;
				if (israndom) {
					qd = qp.getRandomQuestData();
				} else {
					qd = qp.getLinkQuestData();
				}
				qd.setQuest(null);
				qd.setEndCount(0);
				if (len == 4 && args[3].equals("no!")) {
					return true;
				}
				sender.sendMessage("��6�ش� �÷��̾��� ����Ʈ�� �����߽��ϴ�.");

			} else {
				sender.sendMessage("��6�ش� �̸��� �÷��̾ �˻��� �� �����ϴ�.");
			}
		} else if ((args[0].equals("list") || args[0].equals("���")) && (len == 1 || len == 2)) {
			int index = 1;
			if (len == 2) {
				if (API.isIntegerPositive(args[1])) {
					index = Integer.valueOf(args[1]);
					sender.sendMessage("��6����Ʈ�� ���� ���� �����߽��ϴ�.");
				} else {
					sender.sendMessage("��6�ùٸ� ���ڸ� �Է��Ͻʽÿ�.");
					return true;
				}
			}
			sendMessageList(sender, index, label);
		} else if ((args[0].equals("questupdate") || args[0].equals("����Ʈ������Ʈ")) && (len == 1 || len == 2)) {
			if (len == 1) {
				sender.sendMessage("��a����Ʈ ���� ������Ʈ ����");
				for (QsPlayer qp : QsPlayerAPI.getPlayers()) {
					qp.setNewRandomQuest(false);
				}
				sender.sendMessage("��a����Ʈ ���� ������Ʈ ��");
			} else {
				String target = QsPlayerAPI.searchOfflinePlayer(args[1]);
				if (target != null) {
					QsPlayer qp = QsPlayerAPI.getPlayer(target);
					qp.setNewRandomQuest(false);
					sender.sendMessage("��c" + target + "��6�� ����Ʈ�� ������Ʈ �߽��ϴ�.");
				} else {
					sender.sendMessage("��6�ش� �̸��� �÷��̾ �˻��� �� �����ϴ�.");
				}
			}
		} else if ((args[0].equals("questupdateall") || args[0].equals("����Ʈ������Ʈ��ü")) && len == 1) {
			for (QsPlayer qp : QsPlayerAPI.getPlayers()) {
				qp.setNewRandomQuest(false);
			}

			Bukkit.broadcastMessage(RsQuestMessage.RANDOM_QUEST_UPDATE_MSG);
		} else if ((args[0].equals("precedquest") || args[0].equals("��������Ʈ")) && len >= 2) {
			if ((args[1].equals("add") || args[1].equals("�߰�")) && len == 4) {
				String quest = args[2];
				if (hasQuest(quest)) {
					Quest qs = getQuest(quest);
					String targetQuest = args[3];
					if (hasQuest(targetQuest)) {
						qs.addRequireUUID(getQuest(targetQuest).getUUID());
						sender.sendMessage("��6�ش� ����Ʈ�� ���� ����Ʈ�� " + args[3] + "(��)�� �߰��߽��ϴ�.");
					} else {
						sender.sendMessage("��6�ش� �̸��� ���� Ÿ��-����Ʈ�� �������� �ʽ��ϴ�.");
					}
				} else {
					sender.sendMessage("��6�ش� �̸��� ���� ����Ʈ�� �������� �ʽ��ϴ�.");
				}
			} else if ((args[1].equals("remove") || args[1].equals("����")) && len == 4) {
				String quest = args[2];
				if (hasQuest(quest)) {
					Quest qs = getQuest(quest);
					String targetQuest = args[3];
					if (hasQuest(targetQuest)) {
						List<Integer> list = qs.getRequireQuestUUID();
						int uuid = getQuest(targetQuest).getUUID();
						if (list.contains(uuid)) {
							list.remove((Object) uuid);
							sender.sendMessage("��6�ش� ����Ʈ�� ���� ����Ʈ�� " + args[3] + "(��)�� �����߽��ϴ�.");
						} else {
							sender.sendMessage("��6�ش� ����Ʈ�� ���� ����Ʈ���� " + args[3] + "(��)�� �����ϴ�.");
						}
					} else {
						sender.sendMessage("��6�ش� �̸��� ���� Ÿ��-����Ʈ�� �������� �ʽ��ϴ�.");
					}
				} else {
					sender.sendMessage("��6�ش� �̸��� ���� ����Ʈ�� �������� �ʽ��ϴ�.");
				}
			} else if ((args[1].equals("list") || args[1].equals("���")) && len == 3) {
				String quest = args[2];
				if (hasQuest(quest)) {
					Quest qs = getQuest(quest);
					int count = 1;
					List<Integer> list = qs.getRequireQuestUUID();
					if (list.size() != 0) {
						sender.sendMessage("��6" + list.size() + "���� ã�ҽ��ϴ�");
						for (int uuid : list) {
							Quest qsTemp = getQuest(uuid);
							if (qsTemp == null) {
								sender.sendMessage("��f" + count + ": " + uuid + "=��6Can't find quest");
							} else {
								sender.sendMessage("��f" + count + ": " + uuid + "=��6" + qsTemp.getName());
							}
						}
					} else {
						sender.sendMessage("��c�ش� ����� �������� �ʽ��ϴ�.");
					}
				} else {
					sender.sendMessage("��6�ش� �̸��� ���� ����Ʈ�� �������� �ʽ��ϴ�.");
				}
			} else {
				sender.sendMessage("��6�߸��� ��c��������Ʈ ��6��ɾ �Է��߽��ϴ�.");
			}
		} else {
			sender.sendMessage("��6�߸��� ��ɾ �Է��߽��ϴ�.");
		}
		return true;
	}

	private void sendMessageList(CommandSender sender, int i, String label) {
		List<Quest> list = new LinkedList<Quest>(getQuests());
		if (i * 10 - 9 > list.size() && list.size() == 0) {
			sender.sendMessage("��c�ش� ����� �������� �ʽ��ϴ�.");
			return;
		}
		if (list.size() * 10 == 0) {
			sender.sendMessage("��6" + list.size() + "���� ã�ҽ��ϴ� ��c" + i + "��6/��c" + (list.size() / 10));
		} else {
			sender.sendMessage("��6" + list.size() + "���� ã�ҽ��ϴ� ��c" + i + "��6/��c" + (list.size() / 10 + 1));
		}
		for (int j = (i - 1) * 10; j < i * 10; j++) {
			sender.sendMessage(list.get(j).toString());
			if (list.size() == (j + 1)) {
				break;
			}
			if (((i * 10) - 1) == j && list.size() > (j + 1)) {
				sender.sendMessage("��6���� ����� �������c/" + label + " " + (i + 1));
			}
		}
	}

}
