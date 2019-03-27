package cn.fves24;

import cn.fves24.sqlite.NoteDao;
import cn.fves24.vo.Note;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowFactory;
import com.intellij.ui.content.Content;
import com.intellij.ui.content.ContentFactory;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * note
 *
 * @author fves
 */
public class NoteToolWindowFactory implements ToolWindowFactory {
    private NoteDao noteDao = new NoteDao();

    @Override
    public void init(ToolWindow window) {
        noteDao.init();

        List<Note> notes = noteDao.getNotes();
        Constants.noteMap.clear();
        for (Note note : notes) {
            Constants.noteMap.put(note.getTitle(), note);
        }
    }

    @Override
    public void createToolWindowContent(@NotNull Project project, @NotNull ToolWindow toolWindow) {
        MainView mainView = new MainView();
        Constants.mainView = mainView;

        ContentFactory contentFactory = ContentFactory.SERVICE.getInstance();
        Content content = contentFactory.createContent(mainView.getContent(), "", false);
        toolWindow.getContentManager().addContent(content);
    }
}
