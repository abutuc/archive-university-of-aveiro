import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class ContactsStorageText implements ContactsStorageInterface {
	private String fpath;

	public ContactsStorageText(String fpath) {
		this.fpath = fpath;
	}

	public String getFpath() {
		return fpath;
	}

	public void setFpath(String fpath) {
		this.fpath = fpath;
	}

	@Override
	public List<Contact> loadContacts() {
		List<Contact> contacts = new ArrayList<>();
		try (Scanner sc = new Scanner(new File(fpath))) {
			while (sc.hasNextLine()) {
				Contact contact = readContact(sc.nextLine());
				if (contact != null)
					contacts.add(contact);
				else {
					contacts = null;
					break;
				}
			}
		} catch (Exception e) {
			contacts = null;
		}
		return contacts;
	}

	private Contact readContact(String line) {
		Contact contact;
		try (Scanner sc = new Scanner(line)) {
			String name = sc.next();
			int phone = sc.nextInt();
			contact = new Contact(name, phone);
		} catch (Exception e) {
			contact = null;
		}
		return contact;
	}

	@Override
	public boolean saveContacts(List<Contact> list) {
		boolean success;
		String str = list.stream().map(c -> c.getName() + "\t" + c.getPhone() + "\n").collect(Collectors.joining());
		try (FileWriter fw = new FileWriter(fpath)) {
			fw.write(str);
			success = true;
		} catch (Exception e) {
			success = false;
		}
		return success;
	}
}
