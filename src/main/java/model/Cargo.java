package model;

public enum Cargo {
	
	NONE("NINGUNO"),DIRECTIVO("DIRECTIVO"), ASISTENCIAL("ASISTENCIAL"), OPERATIVO("OPERATIVO");

	private final String name;

	private Cargo(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return name;
	}

}
