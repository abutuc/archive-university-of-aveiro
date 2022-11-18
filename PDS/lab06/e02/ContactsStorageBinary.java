import java.io.*;
import java.util.*;

public class ContactsStorageBinary implements ContactsStorageInterface {
	private String fpath;
	private int nBytesName;

	public ContactsStorageBinary(String fpath, int nBytesName) {
		this.fpath = fpath;
		this.nBytesName = nBytesName;
	}

	public String getFpath() {
		return fpath;
	}

	public int getnBytesName() {
		return nBytesName;
	}

	public void setFpath(String fpath) {
		this.fpath = fpath;
	}

	public void setnBytesName(int nBytesName) {
		this.nBytesName = nBytesName;
	}

	@Override
	public List<Contact> loadContacts() {
		List<Contact> contacts = new ArrayList<>();
		try (FileInputStream fos = new FileInputStream(fpath);
				BufferedInputStream bos = new BufferedInputStream(fos);
				DataInputStream dos = new DataInputStream(bos)) {
			while (true) {
				Contact contact = new Contact(new String(dos.readNBytes(nBytesName)), dos.readInt());
				contacts.add(contact);
			}
		} catch (EOFException e) {
			// No more contacts to read
		} catch (Exception e) {
			contacts = null;
		}
		return contacts;
	}

	@Override
	public boolean saveContacts(List<Contact> list) {
		boolean success;
		try (FileOutputStream fos = new FileOutputStream(fpath);
				BufferedOutputStream bos = new BufferedOutputStream(fos);
				DataOutputStream dos = new DataOutputStream(bos)) {
			for (Contact contact : list) {
				String name = contact.getName();
				byte[] nameArr = new byte[nBytesName];
				for (int i = 0; i < nBytesName; i++) {
					if (i < name.length())
						nameArr[i] = (byte) name.charAt(i);
					else
						break;
				}
				dos.write(nameArr);
				dos.writeInt(contact.getPhone());
			}
			success = true;
		} catch (Exception e) {
			success = false;
		}
		return success;
	}

}
