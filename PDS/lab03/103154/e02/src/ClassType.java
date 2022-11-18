
public enum ClassType {
	E("Executiva"), T("Turística");

	private String label;

	private ClassType(String label) {
		this.label = label;
	}

	public String getLabel() {
		return label;
	}

}
