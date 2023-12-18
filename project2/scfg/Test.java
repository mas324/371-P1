package scfg;

public class Test {

	public static void main(String[] args) {
		UnitRules S = new UnitRules("S-aA");
		UnitRules A = new UnitRules("A-aa|0");

		System.out.println(S);
		System.out.println(A);

		A.removeNull();

		System.out.println(A);

		S.append("a0");
		S.removeNull();

		System.out.println(S);
		System.out.println(S.hasNull());

	}

}
