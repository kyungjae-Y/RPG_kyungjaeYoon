package Controller;

import Utils.Utils;
import VO.Player;

import java.io.IOException;

import DAO.GuildDAO;
import DAO.InventoryDAO;
import DAO.ShopDAO;

public class Controller {
	GuildDAO gd;
	ShopDAO sd;
	Player p;
	InventoryDAO id;

	public Controller() {
		gd = new GuildDAO();
		sd = new ShopDAO();
		id = new InventoryDAO();
		p = new Player();
	}

	public void run() {
		MainMenu();
	}

	private void MainMenu() {
		while (true) {
			PrintMainMenu();
			int sel = Utils.getInt("선택 : ", 0, 5);
			sel = SelectMainMenu(sel);
			if (sel == 0) {
				System.out.println("종료");
				return;
			}
		}
	}

	private int SelectMainMenu(int sel) {
		if (sel == 1) {
			gd.GuildMenu(p);
		} else if (sel == 2) {
			sd.ShopMenu(p);
		} else if (sel == 3) {
			id.InventoryMenu(p, gd);
		} else if (sel == 4) {
			Utils.FileSave(gd, sd, id, p);
		} else if (sel == 5) {
			String name = "";
			try {
				name = Utils.FileLoad(gd, sd, id, p);
				System.out.println(name + "로드 완료");
			} catch (IOException e) {
				System.out.println(name + "로드 실패");
			}
		}
		return sel;
	}

	private void PrintMainMenu() {
		System.out.println("===== 메인 메뉴 =====");
		System.out.println("[1] 길드관리");
		System.out.println("[2] 상점");
		System.out.println("[3] 인벤토리");
		System.out.println("[4] 저장");
		System.out.println("[5] 로드");
		System.out.println("[0] 종료");
	}
}