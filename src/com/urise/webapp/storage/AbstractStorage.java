package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStrorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;

import java.util.Comparator;
import java.util.List;
import java.util.logging.Logger;

public abstract class AbstractStorage<SK> implements Storage {

    // 1-й способ добавление логгера
//    protected final Logger log = Logger.getLogger(getClass().getName());
    //2-й способ (более распространенный):
    private static Logger LOG = Logger.getLogger(AbstractStorage.class.getName());
    // Здесь используется 2-й вариант, т.к. логирование будет только в данном классе, статический вариант.

    protected static final Comparator<Resume> RESUME_COMPARATOR =
            Comparator.comparing(Resume::getFullName).thenComparing(Resume::getUuid);

    protected abstract void clearStorage();

    protected abstract void doSave(Resume resume, SK searchKey);

    protected abstract void doUpdate(Resume resume, SK searchKey);

    protected abstract void doDelete(SK searchKey);

    protected abstract Resume doGet(SK searchKey);

    protected abstract List<Resume> doCopyAll();

    protected abstract SK getSearchKey(String uuid);

    protected abstract boolean isExist(SK searchKey);

    @Override
    public final void clear() {
        clearStorage();
    }

    @Override
    public final void save(Resume resume) {
        LOG.info("Save: " + resume);
        SK searchKey = getNotExistingSearchKey(resume.getUuid());
        doSave(resume, searchKey);
    }

    @Override
    public final void update(Resume resume) {
        LOG.info("Update: " + resume);
        SK searchKey = getExistingSearchKey(resume.getUuid());
        doUpdate(resume, searchKey);
    }

    @Override
    public final void delete(String uuid) {
        LOG.info("Delete: " + uuid);
        SK searchKey = getExistingSearchKey(uuid);
        doDelete(searchKey);
    }

    @Override
    public final Resume get(String uuid) {
        LOG.info("Get: " + uuid);
        SK searchKey = getExistingSearchKey(uuid);
        return doGet(searchKey);
    }

    @Override
    public final List<Resume> getAllSorted() {
        LOG.info("GetAllSorted");
        List<Resume> list = doCopyAll();
        list.sort(RESUME_COMPARATOR);
        return list;
    }

    private SK getExistingSearchKey(String uuid){
        SK searchKey = getSearchKey(uuid);
        if (isExist(searchKey)){
            return searchKey;
        } else {
            LOG.warning("Резюме с uuid \"" + uuid + "\" нет в списке!");
            throw new NotExistStorageException(uuid);
        }
    }

    private SK getNotExistingSearchKey(String uuid){
        SK searchKey = getSearchKey(uuid);
        if(!isExist(searchKey)){
            return searchKey;
        } else {
            LOG.warning("Резюме с таким uuid (" + uuid + ") уже существует под ключом: " + searchKey + ".");
            throw new ExistStrorageException(uuid, searchKey);
        }
    }
}