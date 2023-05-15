import java.util.Arrays;
import java.util.Comparator;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];

    void clear() {
    }

    void save(Resume r) {
        storage[size() + 1] = r;
    }

    Resume get(String uuid) {
        Resume resume = null;
        for(Resume element : getAll()){
            if(element.uuid.equals(uuid)) resume = element;
            break;
        }
        return resume;
    }

    void delete(String uuid) {
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {

        Arrays.sort(storage, new Comparator<Resume>() {
            @Override
            public int compare(Resume o1, Resume o2) {
                int result = 0;
                if(o1 == null && o2 == null){
                    result = 0;
                }
                if (o1 == null){
                    result = 1;
                }
                if (o2 == null){
                    result = -1;
                }
                return result;
            }
        });

        int count = -1;

        for(Resume element : storage){
            if(element != null) count++;
        }

        Resume[] arrayWithoutNull = Arrays.copyOfRange(storage, 0, count + 1);
        return arrayWithoutNull;
    }

    int size() {
        return getAll().length;
    }
}
