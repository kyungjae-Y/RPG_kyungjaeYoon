package DAO;

import java.util.ArrayList;

import Utils.Utils;
import VO.Item;
import VO.Player;

public class InventoryDAO {

	public void InventoryMenu(Player p, GuildDAO gd) {
		while (true) {
			PrintInventoryMenu();
			int sel = Utils.getInt("선택 : ", 0, 3);
			sel = SelectInventoryMenu(sel, p, gd);
			if (sel == 0)
				return;
		}
	}

	private void PrintInventoryMenu() {
		System.out.println("===== 가방관리 =====");
		System.out.println("[1] 장착");
		System.out.println("[2] 탈착");
		System.out.println("[3] 판매");
		System.out.println("[0] 뒤로가기");
	}

	private int SelectInventoryMenu(int sel, Player p, GuildDAO gd) {
		if (sel == 1) {
			PutOn(p, gd);
		} else if (sel == 2) {
			TakeOff(p, gd);
		} else if (sel == 3) {
			SellItem(p);
		}
		return sel;
	}

	private void PutOn(Player p, GuildDAO gd) {
		if (p.getPlayerItem().size() == 0) {
			System.out.println("아이템이 없습니다");
			return;
		}
		ArrayList<Integer> partyIdx = null;
		partyIdx = partyPrint(gd);
		int unitIdx = Utils.getInt("선택 : ", 1, partyIdx.size()) - 1;
		if (unitIdx == -1)
			return;
		for (int i = 0; i < p.getPlayerItem().size(); i++) {
			p.getPlayerItem().get(i).printItem();
			System.out.println();
		}
		int itemIdx = Utils.getInt("선택 : ", 1, p.getPlayerItem().size()) - 1;
		if (itemIdx == -2)
			return;
		if (p.getPlayerItem().get(itemIdx).getKind() == Item.getWeapon()) {
			if (gd.getUnitList().get(partyIdx.get(unitIdx)).getWeapon() == null) {
				gd.getUnitList().get(partyIdx.get(unitIdx)).setWeapon(p.getPlayerItem().get(itemIdx));
			} else {
				System.out.println("이미 장비를 장착하고 있습니다");
			}
		} else if (p.getPlayerItem().get(itemIdx).getKind() == Item.getArmor()) {
			if (gd.getUnitList().get(partyIdx.get(unitIdx)).getArmor() == null) {
				gd.getUnitList().get(partyIdx.get(unitIdx)).setArmor(p.getPlayerItem().get(itemIdx));
			} else {
				System.out.println("이미 장비를 장착하고 있습니다");
			}
		} else if (p.getPlayerItem().get(itemIdx).getKind() == Item.getRing()) {
			if (gd.getUnitList().get(partyIdx.get(unitIdx)).getRing() == null) {
				gd.getUnitList().get(partyIdx.get(unitIdx)).setRing(p.getPlayerItem().get(itemIdx));
			} else {
				System.out.println("이미 장비를 장착하고 있습니다");
			}
		}
		p.getPlayerItem().remove(itemIdx);
		gd.getUnitList().get(partyIdx.get(unitIdx)).PrintStatus();
		gd.getUnitList().get(partyIdx.get(unitIdx)).PrintEquitedItem();
	}

	private ArrayList<Integer> partyPrint(GuildDAO gd) {
		ArrayList<Integer> partyIdx = new ArrayList<Integer>();
		for (int i = 0; i < gd.getUnitList().size(); i++) {
			if (gd.getUnitList().get(i).isParty()) {
				gd.getUnitList().get(i).printNewUnit();
				System.out.println();
				partyIdx.add(i);
			}
		}
		return partyIdx;
	}

	private void TakeOff(Player p, GuildDAO gd) {
		ArrayList<Integer> partyIdx = null;
		partyIdx = partyPrint(gd);
		int unitIdx = Utils.getInt("선택 : ", 1, partyIdx.size()) - 1;
		if (unitIdx == -2)
			return;
		System.out.println("== [탈착부위] ==");
		System.out.println("[1] 무기");
		System.out.println("[2] 갑옷");
		System.out.println("[3] 반지");
		int takeOffIdx = Utils.getInt("선택 : ", 1, 3) - 1;
		if (unitIdx == -1)
			return;

		if (takeOffIdx == 1) {
			if (gd.getUnitList().get(partyIdx.get(unitIdx)).getWeapon() != null) {
				p.getPlayerItem().add(gd.getUnitList().get(partyIdx.get(unitIdx)).getWeapon());
				gd.getUnitList().get(partyIdx.get(unitIdx)).setWeapon(null);
			} else {
				System.out.println("벗을 장비가 없습니다");
			}
		} else if (takeOffIdx == 2) {
			if (gd.getUnitList().get(partyIdx.get(unitIdx)).getArmor() != null) {
				p.getPlayerItem().add(gd.getUnitList().get(partyIdx.get(unitIdx)).getArmor());
				gd.getUnitList().get(partyIdx.get(unitIdx)).setArmor(null);
			} else {
				System.out.println("벗을 장비가 없습니다");
			}
		} else if (takeOffIdx == 3) {
			if (gd.getUnitList().get(partyIdx.get(unitIdx)).getRing() != null) {
				p.getPlayerItem().add(gd.getUnitList().get(partyIdx.get(unitIdx)).getRing());
				gd.getUnitList().get(partyIdx.get(unitIdx)).setRing(null);
			} else {
				System.out.println("벗을 장비가 없습니다");
			}
		}
	}

	private void SellItem(Player p) {
		for (int i = 0; i < p.getPlayerItem().size(); i++) {
			System.out.printf("[%d] ", i + 1);
			p.getPlayerItem().get(i).printItem();
			System.out.println();
		}
		int idx = Utils.getInt("선택 : ", 1, p.getPlayerItem().size()) - 1;
		if (idx == -2)
			return;
		p.setMoney(p.getMoney() + p.getPlayerItem().get(idx).getPrice() / 2);
		p.getPlayerItem().remove(idx);
		System.out.println("판매 완료");
	}
}