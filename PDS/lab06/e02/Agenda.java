import java.util.ArrayList;
import java.util.List;

public class Agenda implements ContactsInterface {
	private List<Contact> contacts;
	private ContactsStorageInterface store;

	public Agenda() {
		this.contacts = new ArrayList<>();
	}

	@Override
	public void openAndLoad(ContactsStorageInterface store) {
		List<Contact> contacts = store.loadContacts();
		if (contacts != null) {
			this.store = store;
			this.contacts.addAll(contacts);
		} else {
			System.err.println("Error loading contacts.");
			System.exit(1);
		}
	}

	@Override
	public void saveAndClose() {
		if (store.saveContacts(contacts)) {
			System.out.println("Successfully saved contacts!");
		} else {
			System.err.println("Error saving contacts.");
			System.exit(1);
		}
	}

	@Override
	public void saveAndClose(ContactsStorageInterface store) {
		if (store.saveContacts(contacts)) {
			System.out.println("Successfully saved contacts!");
		}
		else {
			System.err.println("Error saving contacts.");
			System.exit(1);
		}
	}

	@Override
	public boolean exist(Contact contact) {
		return contacts.contains(contact);
	}

	@Override
	public Contact getByName(String name) {
		Contact contact = null;
		for (Contact c : contacts) {
			if (c.getName().equals(name)) {
				contact = c;
				break;
			}
		}
		return contact;
	}

	@Override
	public boolean add(Contact contact) {
		// No need for error message here
		// Client can do it with return value
		return contacts.add(contact);
	}

	@Override
	public boolean remove(Contact contact) {
		// Same here
		return contacts.remove(contact);
	}
}
