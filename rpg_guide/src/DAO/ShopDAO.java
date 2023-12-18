package DAO;

import java.util.ArrayList;

import Utils.Utils;
import VO.Item;
import VO.Player;

public class ShopDAO {
	String[] n1 = { "동", "철", "금" };
	ArrayList<Item> itemList;

	public ShopDAO() {
		itemList = new ArrayList<Item>();
		SetItem();
	}

	private void SetItem() {
		int num = 5;
		int mul = 100;
		for (int k = 0; k < n1.length; k++) {
			Item add = new Item(Item.getWeapon(), n1[k] + "검", num * k + 1, num++ * mul);
			mul += 500;
			itemList.add(add);
		}
		mul = 100;
		for (int k = 0; k < n1.length; k++) {
			Item add = new Item(Item.getArmor(), n1[k] + "갑옷", num * k + 1, num++ * mul);
			mul += 500;
			itemList.add(add);
		}
		mul = 100;
		for (int k = 0; k < n1.length; k++) {
			Item add = new Item(Item.getRing(), n1[k] + "반지", num * k + 1, num++ * mul);
			mul += 500;
			itemList.add(add);
		}
	}

	public void ShopMenu(Player p) {
		while (true) {
			PrintShopMenu();
			int sel = Utils.getInt("선택 : ", 0, 3);
			if (sel == 0 || sel == -1)
				return;
			sel = SelectShopMenu(sel, p);
		}
	}

	private void PrintShopMenu() {
		System.out.println("===== 상점 =====");
		System.out.println("[1] 무기");
		System.out.println("[2] 갑옷");
		System.out.println("[3] 반지");
		System.out.println("[0] 뒤로가기");
	}

	private int SelectShopMenu(int sel, Player p) {
		ArrayList<Integer> itemNum = null;
		if (sel == 1) {
			itemNum = Weapon();
		} else if (sel == 2) {
			itemNum = Armor();
		} else if (sel == 3) {
			itemNum = Ring();
		}
		int select = Utils.getInt("선택 : ", 1, itemNum.size()) - 1;
		if (select != -1) {
			Item add = new Item(itemList.get(itemNum.get(select)).getKind(),
					itemList.get(itemNum.get(select)).getName(), itemList.get(itemNum.get(select)).getPower(),
					itemList.get(itemNum.get(select)).getPrice());
			if (p.getMoney() < add.getPrice()) {
				System.out.println("돈이 부족합니다");
				return -1;
			}
			p.getPlayerItem().add(add);
			p.setMoney(p.getMoney() - add.getPrice());
		}
		System.out.println("구매 완료");
		return sel;
	}

	private ArrayList<Integer> Weapon() {
		ArrayList<Integer> itemNum = new ArrayList<Integer>();
		int idx = 0;
		for (int i = 0; i < itemList.size(); i++) {
			if (itemList.get(i).getKind() == Item.getWeapon()) {
				System.out.printf("[%d번]", (idx++) + 1);
				itemList.get(i).printItem();
				itemNum.add(i);
				System.out.println();
			}
		}
		return itemNum;
	}

	private ArrayList<Integer> Armor() {
		ArrayList<Integer> itemNum = new ArrayList<Integer>();
		int idx = 0;
		for (int i = 0; i < itemList.size(); i++) {
			if (itemList.get(i).getKind() == Item.getArmor()) {
				System.out.printf("[%d번]", (idx++) + 1);
				itemList.get(i).printItem();
				itemNum.add(i);
				System.out.println();
			}
		}
		return itemNum;
	}

	private ArrayList<Integer> Ring() {
		ArrayList<Integer> itemNum = new ArrayList<Integer>();
		int idx = 0;
		for (int i = 0; i < itemList.size(); i++) {
			if (itemList.get(i).getKind() == Item.getRing()) {
				System.out.printf("[%d번]", (idx++) + 1);
				itemList.get(i).printItem();
				itemNum.add(i);
				System.out.println();
			}
		}
		return itemNum;
	}

}