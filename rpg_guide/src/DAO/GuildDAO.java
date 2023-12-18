package DAO;

import java.util.ArrayList;
import java.util.Random;

import VO.Unit;
import VO.Player;

import Utils.Utils;

public class GuildDAO {
	private ArrayList<Unit> unitList;
	private final int PARTY_SIZE = 4;
	private int cnt;

	public ArrayList<Unit> getUnitList() {
		return unitList;
	}

	public GuildDAO() {
		unitList = new ArrayList<Unit>();
		cnt = 0;
	}

	public int getPARTY_SIZE() {
		return PARTY_SIZE;
	}

	public void GuildMenu(Player p) {
		while (true) {
			PrintGuildMenu();
			int sel = Utils.getInt("선택 : ", 0, 5);
			if (sel == -1 || sel == 0)
				return;
			sel = SelectGuildMenu(sel, p);
		}
	}

	private void PrintGuildMenu() {
		System.out.println("===== 길드관리 =====");
		System.out.println("[1] 길드원고용");
		System.out.println("[2] 길드원삭제");
		System.out.println("[3] 길드원목록");
		System.out.println("[4] 파티원교체");
		System.out.println("[5] 정렬");
		System.out.println("[0] 뒤로가기");
	}

	private int SelectGuildMenu(int sel, Player p) {
		if (sel == 1) {
			BuyUnit(p);
		} else if (sel == 2) {
			SellUnit(p);
		} else if (sel == 3) {
			UnitList(p);
		} else if (sel == 4) {
			PartyChange();
		} else if (sel == 5) {
			GuildSort();
		}
		return sel;
	}

	private void BuyUnit(Player p) {
		if (p.getMoney() < 5000) {
			System.out.println("돈이 부족합니다");
			return;
		}
		if (cnt < PARTY_SIZE)
			System.out.println("길드원 4명 이전 까지 기본으로 파티원으로 지정 됩니다");
		Random rd = new Random();

		String[] n1 = { "박", "이", "김", "최", "유", "지", "오" };
		String[] n2 = { "명", "기", "종", "민", "재", "석", "광" };
		String[] n3 = { "수", "자", "민", "수", "석", "민", "철" };

		String name = n1[rd.nextInt(n1.length)];
		name += n2[rd.nextInt(n2.length)];
		name += n3[rd.nextInt(n3.length)];
		int rdNum = rd.nextInt(8) + 2;
		int hp = rdNum * 11;
		int att = rdNum + 1;
		int def = rdNum / 2 + 1;

		Unit temp = new Unit(name, 1, hp, att, def, cnt++ < PARTY_SIZE ? true : false);
		System.out.println("=====================================");
		temp.printNewUnit();
		System.out.println("=====================================");
		unitList.add(temp);
		p.setMoney(p.getMoney() - 5000);
	}

	private void SellUnit(Player p) {
		UnitList(p);
		int sel = Utils.getInt("선택>>", 1, unitList.size());
		if (sel == -1)
			return;
		if (unitList.get(sel - 1).isParty()) {
			System.out.println("파티중인 멤버는 삭제할수 없습니다");
		} else {
			System.out.println("=================================");
			System.out.print("[이름 : " + unitList.get(sel - 1).getName() + "]");
			System.out.println("길드원을 삭제합니다(반값)");
			System.out.println("=================================");
			unitList.remove(sel - 1);
			p.setMoney(p.getMoney() + 2500);
		}
		cnt--;
	}

	private void UnitList(Player p) {
		System.out.println("=========================================");
		System.out.println("[골드 : " + p.getMoney() + "]");
		System.out.println("================ [길드원] =================");
		for (int i = 0; i < unitList.size(); i++) {
			System.out.printf("[%d번]", i + 1);
			unitList.get(i).PrintStatus();
			System.out.println();
		}
		System.out.println("=========================================");
	}

	private void PartyChange() {
		if (unitList.size() < PARTY_SIZE + 1) {
			System.out.println((PARTY_SIZE + 1) + "명 이상일때 교체 가능합니다");
			return;
		}
		ArrayList<Integer> partyIdx = printParty();
		int partyNum = Utils.getInt("교체할 번호 : ", 1, partyIdx.size()) - 1;
		ArrayList<Integer> nonPartyIdx = printNonParty();
		int nonPartyNum = Utils.getInt("교체할 번호 : ", 1, nonPartyIdx.size()) - 1;
		unitList.get(partyIdx.get(partyNum)).setParty(false);
		unitList.get(nonPartyIdx.get(nonPartyNum)).setParty(true);

		System.out.println("====================================");
		System.out.print("[이름 : " + unitList.get(partyIdx.get(partyNum)).getName() + "]");
		System.out.print("에서 ");
		System.out.print("[이름 : " + unitList.get(nonPartyIdx.get(nonPartyNum)).getName() + "]");
		System.out.println("으로 교체 합니다");
		System.out.println("====================================");
	}

	private ArrayList<Integer> printParty() {
		ArrayList<Integer> partyIdx = new ArrayList<Integer>();
		System.out.println("================ [파티원] =================");
		int idx = 1;
		for (int i = 0; i < unitList.size(); i++) {
			if (unitList.get(i).isParty()) {
				System.out.printf("[%d번]", idx++);
				unitList.get(i).PrintStatus();
				System.out.println();
				partyIdx.add(i);
			}
		}
		return partyIdx;
	}

	private ArrayList<Integer> printNonParty() {
		ArrayList<Integer> nonPartyIdx = new ArrayList<Integer>();
		System.out.println("================ [길드원] =================");
		int idx = 1;
		for (int i = 0; i < unitList.size(); i++) {
			if (!unitList.get(i).isParty()) {
				System.out.printf("[%d번]", idx++);
				unitList.get(i).PrintStatus();
				System.out.println();
				nonPartyIdx.add(i);
			}
		}
		return nonPartyIdx;
	}

	private void GuildSort() {
		while (true) {
			PrintGuildSortMenu();
			int sel = Utils.getInt("선택>>", 0, 3);
			if (sel == -1 || sel == 0)
				return;
			sel = SelectGuildSortMenu(sel);
		}
	}

	private void PrintGuildSortMenu() {
		System.out.println("===== 정렬 =====");
		System.out.println("[1] 이름순");
		System.out.println("[2] 파티우선");
		System.out.println("[3] 공격력");
		System.out.println("[0] 뒤로가기");
	}

	private int SelectGuildSortMenu(int sel) {
		if (sel == 1) {
			NameSort();
		} else if (sel == 2) {
			PartySort();
		} else if (sel == 3) {
			AttackSort();
		}
		return sel;
	}

	private void NameSort() {
		for (int i = 0; i < unitList.size(); i++) {
			for (int k = i; k < unitList.size(); k++) {
				if (unitList.get(i).getName().charAt(0) > unitList.get(k).getName().charAt(0)) {
					Unit temp = unitList.get(i);
					unitList.set(i, unitList.get(k));
					unitList.set(k, temp);
				}
			}
		}
	}

	private void PartySort() {
		for (int i = 0; i < unitList.size(); i++) {
			for (int k = i; k < unitList.size(); k++) {
				if (i != k && unitList.get(k).isParty()) {
					Unit temp = unitList.get(i);
					unitList.set(i, unitList.get(k));
					unitList.set(k, temp);
				}
			}
		}
	}

	private void AttackSort() {
		for (int i = 0; i < unitList.size(); i++) {
			for (int k = i; k < unitList.size(); k++) {
				if (unitList.get(i).getAtt() < unitList.get(k).getAtt()) {
					Unit temp = unitList.get(i);
					unitList.set(i, unitList.get(k));
					unitList.set(k, temp);
				}
			}
		}
	}
}