/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];
    int size = 0;

    void clear() {
        for (int i = 0; i < size(); i++) {
            storage[i] = null;
        }
        System.out.println("Now resumes no.");
    }

    void save(Resume r) {
                storage[size] = r;
                System.out.println("Resume " + r + " is save.");
                size++;
    }

    Resume get(String uuid) {

        for (int i = 0; i < size; i++) {
            if (storage[i].uuid.equals(uuid)) {
                System.out.println("Resume " + uuid + " is found.");
                return storage[i];
            }
        }
        return null;
    }

    void delete(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].uuid.equals(uuid)) {
                System.out.println("Resume " + uuid + " is delete.");
                for (int j = i + 1; j <= size; j++) {
                    storage[i] = storage[j];
                    i++;
                }
                size--;
                storage[size] = null;
            }
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        System.out.println("List of resumes:");
        Resume[] resumes = new Resume[size()];
        for (int i = 0; i < size(); i++) {
            resumes[i] = storage[i];
        }
        return resumes;
    }

    int size() {
        int sizeOfStorage = 0;
        for (int i = 0; i < storage.length; i++) {
            if (storage[i] != null) {
                sizeOfStorage = i + 1;
            }
        }

        return sizeOfStorage;
    }
}