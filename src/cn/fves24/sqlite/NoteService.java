package cn.fves24.sqlite;

import cn.fves24.Constants;
import cn.fves24.vo.Note;

/**
 * @author fves
 */
public class NoteService {
    private NoteDao noteDao = new NoteDao();

    public void addNote(String title, String content) {
        Note note = new Note(title, content);
        noteDao.saveNote(note);
        Constants.noteMap.put(title, note);
    }

    public void delNote(String title) {
        noteDao.deleteNote(title);
        Note note = Constants.noteMap.get(title);
        note.setDel(1);
        Constants.noteMap.put(title, note);
    }

    public void cleanAll(){
        noteDao.cleanNote();
    }
}
