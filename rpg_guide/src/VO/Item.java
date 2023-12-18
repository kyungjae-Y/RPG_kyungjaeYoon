package VO;

public class Item {
	private static final int WEAPON = 1;
	private static final int ARMOR = 2;
	private static final int RING = 3;

	public static int getWeapon() {
		return WEAPON;
	}

	public static int getArmor() {
		return ARMOR;
	}

	public static int getRing() {
		return RING;
	}

	private int kind;
	private String name;
	private int power;
	private int price;

	public Item(int k, String n, int p, int pr) {
		kind = k;
		name = n;
		power = p;
		price = pr;
	}

	public int getKind() {
		return kind;
	}

	public void setKind(int kind) {
		this.kind = kind;
	}

	public String getName() {
		return name;
	}

	public int getPower() {
		return power;
	}

	public int getPrice() {
		return price;
	}

	public void printItem() {
		System.out.println("[이름 : " + name + "]");
		System.out.println("[능력 : " + power + "]");
		System.out.println("[가격 : " + price + "]");
	}

}