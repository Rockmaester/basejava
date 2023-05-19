import java.util.Arrays;
import java.util.Comparator;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    int size;
    Resume[] storage = new Resume[10000];

    void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    void save(Resume resume) {
        storage[size] = resume;
        size++;
    }

    Resume get(String uuid) {
        Resume resume = null;
        for (Resume element : getAll()) {
            if (element.uuid.equals(uuid)) resume = element;
        }
        return resume;
    }

    void delete(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].uuid.equals(uuid)) {
                storage[i] = storage[size - 1];
                storage[size - 1] = null;
                break;
            }
        }
        size--;
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        Resume[] arrayWithoutNull = Arrays.copyOfRange(storage, 0, size);
        return arrayWithoutNull;
    }

    int size() {
        return size;
    }
}
