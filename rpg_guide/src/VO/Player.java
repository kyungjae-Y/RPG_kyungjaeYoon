package VO;

import java.util.ArrayList;

public class Player {
	private int money = 100000;

	private ArrayList<Item> playerItem;

	public Player() {
		playerItem = new ArrayList<Item>();
	}

	public int getMoney() {
		return money;
	}

	public void setMoney(int money) {
		this.money = money;
	}

	public ArrayList<Item> getPlayerItem() {
		return playerItem;
	}

}