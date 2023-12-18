package VO;

public class Unit {
	private String name;
	private int level;
	private int hp;
	private int maxHp;
	private int att;
	private int def;
	private boolean party;
	private Item weapon;
	private Item armor;
	private Item ring;

	public int getLevel() {
		return level;
	}

	public int getHp() {
		return hp;
	}

	public int getMaxHp() {
		return maxHp;
	}

	public int getDef() {
		return def;
	}

	public Item getWeapon() {
		return weapon;
	}

	public Item getArmor() {
		return armor;
	}

	public Item getRing() {
		return ring;
	}

	public void setWeapon(Item weapon) {
		this.weapon = weapon;
	}

	public void setArmor(Item armor) {
		this.armor = armor;
	}

	public void setRing(Item ring) {
		this.ring = ring;
	}

	public String getName() {
		return name;
	}

	public boolean isParty() {
		return party;
	}

	public int getAtt() {
		return att;
	}

	public void setParty(boolean party) {
		this.party = party;
	}

	public Unit(String n, int l, int h, int a, int d, Boolean p) {
		name = n;
		level = l;
		maxHp = h;
		att = a;
		def = d;
		hp = maxHp;
		party = p;
		weapon = null;
		armor = null;
		ring = null;
	}

	public void SetItem(Item w, Item a, Item r) {
		weapon = w;
		armor = a;
		ring = r;
	}

	public void PrintStatus() {
		System.out.println("[이름 : " + name + "]");
		System.out.println("[레벨 : " + level + "]");
		if (ring != null) {
			System.out.print("[체력 : " + hp + " + " + ring.getPower());
		} else {
			System.out.print("[체력 : " + hp);
		}
		if (ring != null) {
			System.out.println(" / " + maxHp + " + " + ring.getPower() + "]");
		} else {
			System.out.println(" / " + maxHp + "]");
		}
		if (weapon != null) {
			System.out.println("[공격력 : " + att + " + " + weapon.getPower() + "]");
		} else {
			System.out.println("[공격력 : " + att + "]");
		}
		if (armor != null) {
			System.out.println("[방어력 : " + def + " + " + armor.getPower() + "]");
		} else {
			System.out.println("[방어력 : " + def + "]");
		}
		System.out.println("[파티중 : " + party + "]");
	}

	public void PrintEquitedItem() {
		if (weapon == null) {
			System.out.println("[무기 : 없음]");
		} else {
			System.out.println("[무기 : " + weapon.getName() + "]");
		}
		if (armor == null) {
			System.out.println("[방어구 : 없음]");
		} else {
			System.out.println("[방어구 : " + armor.getName() + "]");
		}
		if (ring == null) {
			System.out.println("[반지 : 없음]");
		} else {
			System.out.println("[반지 : " + ring.getName() + "]");
		}
	}

	public void printNewUnit() {
		System.out.println("[이름 : " + name + "]");
		System.out.println("[레벨 : " + 1 + "]");
		System.out.print("[체력 : " + hp);
		System.out.println(" / " + hp + "]");
		System.out.println("[공격력 : " + att + "]");
		System.out.println("[방어력 : " + def + "]");
	}
}