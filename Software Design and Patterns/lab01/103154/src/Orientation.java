public enum Orientation {

	U("Up"), UR("UpRight"), R("Right"), DR("DownRight"), D("Down"), DL("DownLeft"), L("Left"), UL("UpLeft");

	private String label;

	private Orientation(String label) {
		this.label = label;
	}

	public String getLabel() {
		return label;
	}

}