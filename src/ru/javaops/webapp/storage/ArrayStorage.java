package ru.javaops.webapp.storage;

import ru.javaops.webapp.model.Resume;

import java.util.Arrays;


/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private Resume[] storage = new Resume[10000];
    private int size = 0;
    private boolean check = false;

    public void checkResume(Resume r) {
        check = false;
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(r.getUuid())) {
                check = true;
                break;
            }
        }
    }

    public void checkString(String uuid) {
        check = false;
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                check = true;
                break;
            }
        }
    }

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
        System.out.println("There are no resumes now.");
    }


    public void save(Resume r) {
        checkResume(r);
        if (check) {
            System.out.println("The resume \"" + r + "\" is already exists.");
        } else if (size + 1 < storage.length) {
            System.out.println("The resume \"" + r + "\" is save.");
            storage[size] = r;
            size++;
        } else {
            System.out.println("The storage is fool.");
        }
    }

    public void update(Resume r) {
        checkResume(r);
        if (check) {
            for (int i = 0; i < size; i++) {
                if (storage[i] == r) {
                    System.out.println("The resume \"" + r + "\" is update.");
                    storage[i] = r;
                    break;
                }
            }
        } else {
            System.out.println("There is no \"" + r + "\" resume.");
        }
    }

    public Resume get(String uuid) {
        checkString(uuid);
        if (check) {
            for (int i = 0; i < size; i++) {
                if (storage[i].getUuid().equals(uuid)) {
                    System.out.println("Resume \"" + uuid + "\" is found.");
                    return storage[i];
                }
            }
        } else {
            System.out.println("There is no \"" + uuid + "\" such resume.");
        }
        return null;
    }

    public void delete(String uuid) {
        checkString(uuid);
        if (check) {
            for (int i = 0; i < size; i++) {
                if (storage[i].getUuid().equals(uuid)) {
                    System.out.println("Resume \"" + uuid + "\" is delete.");
                    for (int j = i; j < size - 1; j++) {
                        storage[j] = storage[j + 1];
                        i++;
                    }
                    size--;
                    storage[size] = null;
                }
            }
        } else {
            System.out.println("There is no \"" + uuid + "\" such resume.");
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    public int size() {
        return size;
    }
}