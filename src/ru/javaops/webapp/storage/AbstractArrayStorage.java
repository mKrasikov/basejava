package ru.javaops.webapp.storage;

import ru.javaops.webapp.model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage implements Storage {
    protected static final int STORAGE_LIMIT = 10000;
    protected final Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    protected abstract void fillDeletedElement(int index);

    protected abstract void insertElement(Resume r, int index);

    protected abstract int getIndex(String uuid);

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
        System.out.println("There are no resumes now.");
    }

    public void save(Resume r) {
        int index = getIndex(r.getUuid());
        if (index >= 0) {
            System.out.println("The resume \"" + r + "\" is already exists.");
        } else if (size == STORAGE_LIMIT) {
            System.out.println("The storage is full.");
        } else {
            insertElement(r, index);
            size++;
        }
    }

    public void update(Resume r) {
        int index = getIndex(r.getUuid());
        if (index < 0) {
            System.out.println("There is no \"" + r + "\" resume.");
        } else {
            storage[index] = r;
            System.out.println("The resume \"" + r + "\" is update.");
        }
    }

    public Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index < 0) {
            System.out.println("There is no \"" + uuid + "\" such resume.");
            return null;
        }
        System.out.println("Resume \"" + uuid + "\" is found.");
        return storage[index];
    }

    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index < 0) {
            System.out.println("There is no \"" + uuid + "\" such resume.");
        } else {
            fillDeletedElement(index);
            System.out.println("Resume \"" + uuid + "\" is removed.");
            size--;
        }
    }

    public int size() {
        return size;
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        return Arrays.copyOfRange(storage, 0, size);
    }
}