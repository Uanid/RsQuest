package kr.tpsw.rsquest.convert;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import kr.tpsw.rsquest.WordPressParsing;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

public class TimeCheck {

	private static boolean hasRsTimer = false;

	public static void shutDownTimeCheck() {
		if (hasRsTimer) {
			TimeCheck2.shutDownRegister();
		}
	}

	public static boolean initTimeCheck(Plugin pl) {
		hasRsTimer = false;
		Plugin rstimer = Bukkit.getPluginManager().getPlugin("RsTimer");
		if (rstimer == null) {
			File file = new File("plugins\\RsTimer.jar");
			if (!file.exists()) {
				try {
					System.out.println("[RsQuest] RsTimer �÷������� ã������ �����ϴ�! �ٿ�ε� ��...");
					downLoadRsTimer(file);
					System.out.println("[RsQuest] RsTimer �÷������� �ٿ�ε� �Ϸ�");
				} catch (Exception e) {
					System.out.println("[RsQuest] RsTimer �÷����� �ٿ�ε� ���� ���� �߻�! ���� �α״� �Ʒ� ÷���մϴ�.");
					e.printStackTrace();
					return false;
				}
			} else {
				System.out.println("[RsQuest] �̹� RsTimer.jar������ ���������� ���������� ã������ �����ϴ�.");
				System.out.println("[RsQuest] ���� ����Ʈ �ڵ� ������Ʈ�� ����ϰ� �����ø�, �ش� ������ �����Ŀ� ������ �ٽ� �����ּ���.");
				return false;
			}// ���� �ٿ�ε�

			try {
				Bukkit.getPluginManager().loadPlugin(file);
				System.out.println("[RsQuest] RsTimer �÷������� ������ �ҷ����µ� �����߽��ϴ�.");
			} catch (Exception e) {
				System.out.println("[RsQuest] RsTimer �÷����� �ε� ���� ���� �߻�! ���� �α״� �Ʒ��� ÷���մϴ�.");
				e.printStackTrace();
				return false;
			}

			rstimer = Bukkit.getPluginManager().getPlugin("RsTimer");
			if (rstimer == null) {
				System.out.println("[RsQuest] RsTimer �÷������� �������� ã������ �����ϴ�! �߸��� ������ �ٿ�ε� �Ǿ����ϴ�.");
				return false;
			} else {
				System.out.println("[RsQuest] RsTimer �÷������� �������� ã�ҽ��ϴ�.");
			}

			Bukkit.getPluginManager().enablePlugin(rstimer);
			if (rstimer.isEnabled()) {
				System.out.println("[RsQuest] RsTimer �÷������� Ȱ��ȭ �� ���� Ȯ���߽��ϴ�.");
			} else {
				System.out.println("[RsQuest] RsTimer �÷������� Ȱ��ȭ ���� �ʾҽ��ϴ�.");
				return false;
			}
		}// �÷����� �ٿ�ε� �� �ε�
		
		String needv = "1.0.4";
		double needd = WordPressParsing.trasferVersion(needv);

		String targetv = rstimer.getDescription().getVersion();
		double targetd = WordPressParsing.trasferVersion(targetv);
		if (targetd < needd) {// Ÿ�� ������ �ʿ� �������� ���� ���
			System.out.println("[RsQuest] RsTimer �÷������� ������ ������ �ʿ��� �������� �����ϴ�. �ʿ� ����:" + needv);
			System.out.println("[RsQuest] RsTimer �÷����� ������Ʈ�� ���� �ֿܼ� [timeupdate true]��ɾ �Է����ּ���.");
			return false;
		}

		try {
			TimeCheck2.initRegister(rstimer);
			System.out.println("[RsQuest] RsTimer �÷����ΰ� ���� ����!");
		} catch (Exception e) {
			System.out.println("[RsQuest] RsTimer �÷����ΰ� ���� ���� ���� �߻�! ���� �α״� �Ʒ��� ÷���մϴ�.");
			e.printStackTrace();
		}
		hasRsTimer = true;
		// rstimer�� ���� ��� �������ϱ� Ŭ���� ����
		return true;
	}

	private static void downLoadRsTimer(File file) throws Exception {
		String PLUGIN_NAME = "RsTimer";
		String PLUGIN_NAME_LOWER = PLUGIN_NAME.toLowerCase();
		String PLUGIN_UPDATE_URL = null;

		String postid = "202";
		String posturl = "http://tpsw.or.kr/" + postid;
		StringBuilder sb = new StringBuilder();

		int i1 = 0;
		int i2 = 0;
		StringBuilder sb2;

		StringBuilder builder = new StringBuilder();
		try {
			URL url = new URL(posturl);
			BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream(), "utf-8"));
			String line;
			while ((line = br.readLine()) != null) {
				builder.append(line).append('\n');
			}
		} catch (Exception e) {
			throw e;
		}

		i1 = builder.indexOf("<div class=\"post-content\">");
		builder.delete(0, i1);
		i1 = builder.indexOf("<p>");
		builder.delete(0, i1 + 3);
		i1 = builder.indexOf("</div>");
		String[] args = builder.substring(0, i1).replace("&nbsp;", " ").replace("&lt;", "<").replace("&gt;", ">").replace("<p>", "").replace("\n", "").trim().split("</p>");
		for (String line : args) {
			sb.append(line).append('\n');
		}

		{
			i1 = sb.indexOf("<" + PLUGIN_NAME_LOWER + ">");
			i2 = sb.indexOf("</" + PLUGIN_NAME_LOWER + ">");
			sb2 = new StringBuilder(sb.substring(i1 + PLUGIN_NAME_LOWER.length() + 3, i2 - 1));
		}// �ڸ���

		{
			i1 = sb2.indexOf("<download>");
			i2 = sb2.indexOf("</download>");
			if (i1 == -1 && i2 == -1) {
				// �Ѿ��
				PLUGIN_UPDATE_URL = null;
			} else {
				PLUGIN_UPDATE_URL = sb2.substring(i1 + 8 + 3, i2 - 1);
			}
		}// ������Ʈ �ּ�
		wordPressDownload(PLUGIN_UPDATE_URL, new File(file.getPath()));
	}

	private static void wordPressDownload(String surl, File file) {
		try {
			URL url = new URL(surl);
			URLConnection urlc = url.openConnection();
			InputStream is = urlc.getInputStream();
			BufferedInputStream bis = new BufferedInputStream(is);

			FileOutputStream fos = new FileOutputStream(file);
			BufferedOutputStream bos = new BufferedOutputStream(fos);

			int r;
			byte[] buffer = new byte[1024];
			while ((r = bis.read(buffer, 0, buffer.length)) != -1) {
				bos.write(buffer, 0, r);
			}
			bos.close();
			bis.close();
		} catch (Exception e) {
			throw new IllegalArgumentException();
		}
	}
}
