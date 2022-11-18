import java.io.*;

public class Main {

	private static int nBytesName = 12; // To store in a binary file

	public static void main(String[] args) {
		// Create text file with contacts
		String fpath = "contacts1.txt";
		try (FileWriter fw = new FileWriter(fpath)) {
			fw.write("John\t967482831\n");
			fw.write("Murphy\t938746721\n");
		} catch (IOException e) {
			System.err.println("Error creating file \"" + fpath + "\".");
			System.exit(1);
		}
		System.out.println("Successfully created file \"" + fpath + "\"!");
		// Create binary file with contacts
		fpath = "contacts1.bin";
		try (FileOutputStream fos = new FileOutputStream(fpath);
				BufferedOutputStream bos = new BufferedOutputStream(fos);
				DataOutputStream dos = new DataOutputStream(bos)) {
			// Add first contact
			String name = "Randy";
			byte[] nameArr = new byte[nBytesName];
			for (int i = 0; i < nBytesName; i++) {
				if (i < name.length())
					nameArr[i] = (byte) name.charAt(i);
				else
					break;
			}
			dos.write(nameArr);
			dos.writeInt(912323487);
			// Add second contact
			name = "Jason";
			nameArr = new byte[nBytesName];
			for (int i = 0; i < nBytesName; i++) {
				if (i < name.length())
					nameArr[i] = (byte) name.charAt(i);
				else
					break;
			}
			dos.write(nameArr);
			dos.writeInt(943628421);
		} catch (IOException e) {
			System.err.println("Error creating file \"" + fpath + "\".");
			System.exit(1);
		}
		System.out.println("Successfully created file \"" + fpath + "\"!");
		System.out.println();

		ContactsInterface agenda = new Agenda(); // Create Agenda instance
		agenda.openAndLoad(new ContactsStorageText("contacts1.txt")); // Open contacts from text file
		agenda.openAndLoad(new ContactsStorageBinary("contacts1.bin", nBytesName)); // Open contacts form binary file

		// Get contact
		Contact contact = agenda.getByName("John");
		System.out.println("Obtained contact " + contact);
		// Remove contact
		System.out.print("Removing contact... ");
		agenda.remove(contact);
		if (agenda.exist(contact))
			System.out.println("Something went wrong.");
		else
			System.out.println("He was actually removed.");
		// Add contact
		System.out.print("Adding contact again... ");
		agenda.add(contact);
		if (agenda.exist(contact)) {
			System.out.println("He is back.");
		} else {
			System.out.println("Apparently, he's still gone.");
		}
		System.out.println();

		// Add new contacts
		System.out.println("Adding new contacts... ");
		agenda.add(new Contact("Tessa", 987321654));
		agenda.add(new Contact("Mike", 918273645));
		agenda.add(new Contact("Fabian", 978645312));
		System.out.println();

		// Save contacts to text file
		agenda.saveAndClose(new ContactsStorageText("contacts2.txt"));
		agenda.saveAndClose(new ContactsStorageBinary("contacts2.bin", nBytesName));
	}
}
