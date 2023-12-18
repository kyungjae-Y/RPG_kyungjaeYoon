package Utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;
import DAO.*;
import VO.Item;
import VO.Player;
import VO.Unit;
import java.io.IOException;

public class Utils {
	private static Scanner sc = new Scanner(System.in);
	private static final String CUR_PATH = System.getProperty("user.dir") + "//src//" + Utils.class.getPackageName()
			+ "//";

	public static int getInt(String msg, int start, int end) {
		System.out.println(msg);
		int sel = -1;
		try {
			sel = sc.nextInt();
			if (sel < start || sel > end) {
				System.out.println("선택 범위 오류");
				return -1;
			}
		} catch (Exception e) {
			System.out.println("숫자를 입력하세요");
		}
		sc.nextLine();
		return sel;
	}

	public static void FileSave(GuildDAO gd, ShopDAO sd, InventoryDAO id, Player p) {
		String gameData = "";
		String fileName = "gameData.txt";
		ArrayList<Unit> temp = gd.getUnitList();
		try (FileWriter fw = new FileWriter(CUR_PATH + fileName)) {
			gameData += p.getMoney();
			gameData += "\r\n";
			gameData += temp.size();
			gameData += "\r\n";
			for (int i = 0; i < temp.size(); i++) {
				gameData += temp.get(i).getName();
				gameData += "/";
				gameData += temp.get(i).getLevel();
				gameData += "/";
				gameData += temp.get(i).getMaxHp();
				gameData += "/";
				gameData += temp.get(i).getAtt();
				gameData += "/";
				gameData += temp.get(i).getDef();
				gameData += "/";
				gameData += temp.get(i).isParty();
				gameData += "\r\n";
				if (temp.get(i).getWeapon() == null) {
					gameData += temp.get(i).getWeapon();
				} else {
					Item item = temp.get(i).getWeapon();
					String weaponData = "";
					weaponData += item.getKind();
					weaponData += ",";
					weaponData += item.getName();
					weaponData += ",";
					weaponData += item.getPower();
					weaponData += ",";
					weaponData += item.getPrice();
					gameData += weaponData;
				}
				gameData += "/";
				if (temp.get(i).getArmor() == null) {
					gameData += temp.get(i).getArmor();
				} else {
					Item item = temp.get(i).getArmor();
					String weaponData = "";
					weaponData += item.getKind();
					weaponData += ",";
					weaponData += item.getName();
					weaponData += ",";
					weaponData += item.getPower();
					weaponData += ",";
					weaponData += item.getPrice();
					gameData += weaponData;
				}
				gameData += "/";
				if (temp.get(i).getRing() == null) {
					gameData += temp.get(i).getRing();
				} else {
					Item item = temp.get(i).getRing();
					String weaponData = "";
					weaponData += item.getKind();
					weaponData += ",";
					weaponData += item.getName();
					weaponData += ",";
					weaponData += item.getPower();
					weaponData += ",";
					weaponData += item.getPrice();
					gameData += weaponData;
				}
				gameData += "\r\n";
			}
			gameData += p.getPlayerItem().size();
			gameData += "\r\n";
			for (int i = 0; i < p.getPlayerItem().size(); i++) {
				Item item = p.getPlayerItem().get(i);
				gameData += item.getKind();
				gameData += ",";
				gameData += item.getName();
				gameData += ",";
				gameData += item.getPower();
				gameData += ",";
				gameData += item.getPrice();
				gameData += "\r\n";
			}
			fw.write(gameData);
			System.out.println(fileName + "저장 성공");
		} catch (Exception e) {
			System.out.println(fileName + "저장 실패");
		}
	}

	public static String FileLoad(GuildDAO gd, ShopDAO sd, InventoryDAO id, Player p) throws IOException {
		File file = null;
		FileReader reader = null;
		BufferedReader br = null;
		String fileName = "gameData.txt";
		String path = CUR_PATH + fileName;
		file = new File(path);
		if (file.exists()) {
			reader = new FileReader(path);
			br = new BufferedReader(reader);
			String money = br.readLine();
			p.setMoney(Integer.parseInt(money));
			String guildSize = br.readLine();
			int size = Integer.parseInt(guildSize);
			gd.getUnitList().clear();
			for (int i = 0; i < size; i++) {
				String unitData = br.readLine();
				String[] unitArr = unitData.split("/");
				String name = unitArr[0];
				int level = Integer.parseInt(unitArr[1]);
				int maxhp = Integer.parseInt(unitArr[2]);
				int att = Integer.parseInt(unitArr[3]);
				int def = Integer.parseInt(unitArr[4]);
				boolean party = Boolean.parseBoolean(unitArr[5]);
				Unit temp = new Unit(name, level, maxhp, att, def, party);
				gd.getUnitList().add(temp);
//				==================== item =======================
				String itemData = br.readLine();
				String itemArr[] = itemData.split("/");
				if (itemArr[0].equals("null")) {
					gd.getUnitList().get(i).setWeapon(null);
				} else {
					String[] weapon = itemArr[0].split(",");
					int itemKind = Integer.parseInt(weapon[0]);
					String itemName = weapon[1];
					int itemPower = Integer.parseInt(weapon[2]);
					int itemPrice = Integer.parseInt(weapon[3]);
					Item item = new Item(itemKind, itemName, itemPower, itemPrice);
					gd.getUnitList().get(i).setWeapon(item);
				}
				if (itemArr[1].equals("null")) {
					gd.getUnitList().get(i).setArmor(null);
				} else {
					String[] armor = itemArr[1].split(",");
					int itemKind = Integer.parseInt(armor[0]);
					String itemName = armor[1];
					int itemPower = Integer.parseInt(armor[2]);
					int itemPrice = Integer.parseInt(armor[3]);
					Item item = new Item(itemKind, itemName, itemPower, itemPrice);
					gd.getUnitList().get(i).setArmor(item);
				}
				if (itemArr[2].equals("null")) {
					gd.getUnitList().get(i).setRing(null);
				} else {
					String[] ring = itemArr[2].split(",");
					int itemKind = Integer.parseInt(ring[0]);
					String itemName = ring[1];
					int itemPower = Integer.parseInt(ring[2]);
					int itemPrice = Integer.parseInt(ring[3]);
					Item item = new Item(itemKind, itemName, itemPower, itemPrice);
					gd.getUnitList().get(i).setRing(item);
				}
			}
//			===================== item ============================
			String invenSize = br.readLine();
			int inSize = Integer.parseInt(invenSize);
			p.getPlayerItem().clear();
			for (int i = 0; i < inSize; i++) {
				String invenDate = br.readLine();
				String[] invenArr = invenDate.split("/");
				int itemKind = Integer.parseInt(invenArr[0]);
				String itemName = invenArr[1];
				int itemPower = Integer.parseInt(invenArr[2]);
				int itemPrice = Integer.parseInt(invenArr[3]);
				Item item = new Item(itemKind, itemName, itemPower, itemPrice);
				p.getPlayerItem().add(item);
			}
		}
		return fileName;
	}
}