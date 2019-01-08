package kr.tpsw.rsquest.quest;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import kr.tpsw.rsquest.api.QsPlayer;
import kr.tpsw.rsquest.api.QuestData;
import rsquest.kr.tpsw.api.executable.ExCommand;
import rsquest.kr.tpsw.api.executable.Executable;

public abstract class Quest implements Serializable {

	private static final long serialVersionUID = 8160920153910211282L;
	// ����Ʈ�� ���� ������ ����
	// ����Ʈ ���൵ üũ�� �� Ŭ��������
	// ����Ʈ ���൵ ������ QsPlayerŬ������

	protected String display;
	protected String description;
	protected String name;

	protected Executable reward;
	protected int end;
	protected int uuid;
	protected List<Integer> requireUUID;// ���� ����Ʈ, -1�� ��� ������
	protected boolean isRandom;

	public Quest(String name) {
		this.name = name;
		this.display = name + "����Ʈ";
		this.description = name + "�� �޼��ϼ���.";
		this.reward = new ExCommand("say ����Ʈ �Ϸ�", "cmdcon");
		this.requireUUID = new ArrayList<Integer>();
		this.end = 1;
		this.uuid = QsAPI.createRandomUUID();
		this.isRandom = true;
	}// ����Ʈ �ű� ����

	@SuppressWarnings("unchecked")
	public Quest(Map<String, Object> dataMap) {
		this.name = (String) dataMap.get("name");
		this.description = (String) dataMap.get("description");
		this.display = (String) dataMap.get("display");
		this.end = (int) dataMap.get("end");
		this.uuid = (int) dataMap.get("uuid");
		Object obj;
		if ((obj = dataMap.get("requireUUID")) instanceof List) {
			this.requireUUID = new ArrayList<Integer>((List<Integer>) obj);
		} else {
			this.requireUUID = new ArrayList<Integer>();
		}
		this.reward = Executable.getExecutable((Map<String, Object>) dataMap.get("executable"));
		this.isRandom = (boolean) dataMap.get("isRandom");
	}

	public String getName() {
		return name;
	}

	public int getUUID() {
		return uuid;
	}

	public Executable getReward() {
		return reward;
	}

	public List<Integer> getRequireQuestUUID() {
		return requireUUID;
	}

	public int getEnd() {
		return end;
	}

	public String getDisplay() {
		return this.display;
	}

	public boolean isRandom() {
		return isRandom;
	}

	abstract public String getDescription(int count);

	// getter�޼��� ����, 8��

	public void setEnd(int end) {
		this.end = end;
	}

	public void setDisplay(String display) {
		this.display = display;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setReward(Executable ex) {
		this.reward = ex;
	}

	public void addRequireUUID(int uuid) {
		this.requireUUID.add(uuid);
	}

	public void setIsRandom(boolean bool) {
		this.isRandom = bool;
	}

	// setter�޼��� ����, 6��

	public String toString() {
		return "��aName=��e" + this.name + "��a, end=��e" + this.end + "��a, Type=��enull��a, Reward=��enull";
	}

	protected void setQuestToMap(Map<String, Object> map) {
		map.put("name", name);
		map.put("end", end);
		map.put("uuid", uuid);
		map.put("isRandom", isRandom);
		map.put("requireUUID", requireUUID);
		map.put("display", display);
		map.put("description", description);
		map.put("executable", reward.toMapForSave(new LinkedHashMap<String, Object>()));
	}

	abstract public int updateQuestStatus(Object event, QsPlayer qp, QuestData qd);

	// ����Ʈ ī��Ʈ ���� ���� �Ǻ�

	abstract public Map<String, Object> toMapForSave(Map<String, Object> map);
	// ������ ����
}
