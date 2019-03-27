package cn.fves24;

import cn.fves24.sqlite.NoteDao;
import cn.fves24.vo.Note;
import com.intellij.openapi.ui.ValidationInfo;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

/**
 * Note Item
 *
 * @author fves
 */
public class NoteItem extends NoteDialogWrapper {

    private JPanel jpanel;

    private JTextField titleField;
    private JTextArea contentArea;

    private NoteDao noteDao = new NoteDao();

    NoteItem() {
        super(true);
        init();
        setTitle("添加Note");
    }

    @Nullable
    @Override
    protected JComponent createCenterPanel() {
        return jpanel;
    }

    @Override
    protected void doOKAction() {
        String title = titleField.getText();
        String content = contentArea.getText();
        Note note = new Note(title, content);
        noteDao.saveNote(note);
        Constants.noteMap.put(title, note);
        Constants.mainView.addNote(note);
        super.doOKAction();
    }

    @Nullable
    @Override
    protected ValidationInfo doValidate() {
        String title = titleField.getText();
        String content = contentArea.getText();
        if (Constants.noteMap.containsKey(title)) {
            return new ValidationInfo("标题已存在");
        }
        return null;
    }
}
