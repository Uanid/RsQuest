package kr.tpsw.rsquest.api;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import kr.tpsw.rsquest.quest.QsAPI;
import kr.tpsw.rsquest.quest.Quest;

import org.bukkit.Bukkit;

import rsquest.kr.tpsw.api.publica.YamlConfiguration;

public class QsPlayer {

	private String name;
	private QuestData randomQuest = null;
	private QuestData linkQuest = null;
	// ����Ʈ ���൵�� �����ϴ� ��ü

	private boolean isAchievedRandomQuest = false;
	private boolean isRefreshedRandomQuest = false;
	private List<Integer> achievedQuestUUID = null;

	public QsPlayer(String name) {
		this.name = name;
		this.achievedQuestUUID = new ArrayList<Integer>();
		this.randomQuest = new QuestData();
		this.linkQuest = new QuestData();
		this.setNewRandomQuest(false);
		// ó�� ������ ���� ��������
	}

	@SuppressWarnings({ "unchecked" })
	public QsPlayer(String name, YamlConfiguration dataMap) {// ������
		this.name = name;
		this.randomQuest = new QuestData((Map<String, Object>) dataMap.getMap("randomQuest"));
		this.linkQuest = new QuestData((Map<String, Object>) dataMap.getMap("linkQuest"));
		this.isAchievedRandomQuest = dataMap.getBoolean("isAchievedRandomQuest");
		this.isRefreshedRandomQuest = dataMap.getBoolean("isRefreshedRandomQuest");
		this.achievedQuestUUID = (List<Integer>) dataMap.getList("achievedQuestUUID");
	}

	public Map<String, Object> getDataMap() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("name", name);
		map.put("randomQuest", randomQuest.getDataMap());
		map.put("linkQuest", linkQuest.getDataMap());
		map.put("isAchievedRandomQuest", isAchievedRandomQuest);
		map.put("isRefreshedRandomQuest", isRefreshedRandomQuest);
		map.put("achievedQuestUUID", achievedQuestUUID);
		return map;
	}

	public int isEndCountOver(QuestData qd) {
		if (qd.isEndCountOver()) {
			if (qd == randomQuest) {
				setQuestToAchived(randomQuest);
				isAchievedRandomQuest = true;
			} else if (qd == linkQuest) {
				setQuestToAchived(linkQuest);
			} else {
				throw new IllegalArgumentException();
			}
			return 1;
		} else {
			return 0;
		}
	}// ����Ʈ �޼� ���η� ����

	private void setQuestToAchived(QuestData qd) {
		if (!achievedQuestUUID.contains(qd.getQuest().getUUID())) {
			achievedQuestUUID.add(qd.getQuest().getUUID());
		}
		try {
			qd.getQuest().getReward().run(Bukkit.getPlayer(this.name));
		} catch (Exception e) {
			System.out.println("[RsQuest] Executable���� �� ���� �߻�");
			System.out.println("[RsQuest] Display= " + qd.getQuest().getReward().getDisplayMsg());
			e.printStackTrace();
		}
		qd.setEndCount(0);
		qd.addQuestClearCount();
		qd.setQuest(null);
	}// �޼��� ����

	public List<Integer> getAchievedQuestUUID() {
		return achievedQuestUUID;
	}

	public void setNewRandomQuest(boolean isRefresh) {
		isAchievedRandomQuest = false;
		isRefreshedRandomQuest = isRefresh;

		Quest qs = QsAPI.getRandomQuest();
		randomQuest.setQuest(qs);
		this.randomQuest.setEndCount(0);

		if (qs != null) {
			this.randomQuest.addQuestUpdateCount();
		}
	}// ��������Ʈ ������Ʈ

	public String getName() {
		return name;
	}

	public boolean isAchievedRandomQuest() {
		return isAchievedRandomQuest;
	}

	public boolean isRefreshedRandomQuest() {
		return isRefreshedRandomQuest;
	}

	public QuestData getRandomQuestData() {
		return randomQuest;
	}

	public QuestData getLinkQuestData() {
		return linkQuest;
	}// getter�޼��� 5��

	public void setIsAchievedRandomQuest(boolean bool) {// ����Ʈ �޼�
		isAchievedRandomQuest = bool;
	}

	public void setIsRefreshedRandomQuest(boolean bool) {// ����Ʈ �޼�
		isRefreshedRandomQuest = bool;
	}// setter�޼��� 2��
}
