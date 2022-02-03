package ru.javaops.webapp.storage;

import ru.javaops.webapp.model.Resume;

import java.util.Arrays;


/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private Resume[] storage = new Resume[10000];
    private int size = 0;

    public int check(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
        System.out.println("There are no resumes now.");
    }

    public void save(Resume r) {
        if (check(r.getUuid()) == -1) {
            System.out.println("The resume \"" + r + "\" is save.");
            storage[size] = r;
            size++;
        } else if (size == storage.length) {
            System.out.println("The storage is full.");
        } else {
            System.out.println("The resume \"" + r + "\" is already exists.");
        }
    }

    public void update(Resume r) {
        if (check(r.getUuid()) != -1) {
            System.out.println("The resume \"" + r + "\" is update.");
            storage[check(r.getUuid())] = r;
        } else {
            System.out.println("There is no \"" + r + "\" resume.");
        }
    }

    public Resume get(String uuid) {
        if (check(uuid) != -1) {
            System.out.println("Resume \"" + uuid + "\" is found.");
            return storage[check(uuid)];
        } else {
            System.out.println("There is no \"" + uuid + "\" such resume.");
        }
        return null;
    }

    public void delete(String uuid) {
        int i = check(uuid);
        Resume[] resumes = new Resume[10000];
        if (check(uuid) != -1) {
            System.out.println("Resume \"" + uuid + "\" is removed.");
            System.arraycopy(storage, 0, resumes, 0, i);
            System.arraycopy(storage, i + 1, resumes, i, storage.length - (i + 1));
            size--;
            storage = resumes;
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