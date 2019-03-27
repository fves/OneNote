package cn.fves24;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public class NoteShow extends NoteDialogWrapper {
    private JPanel panel;
    private JTextField textField1;
    private JTextArea textArea1;

    NoteShow(String key, String value) {
        super(true);
        init();
        setTitle("详细Note");
        textField1.setText(key);
        textArea1.setText(value);
    }

    @Nullable
    @Override
    protected JComponent createCenterPanel() {
        return panel;
    }

    @NotNull
    @Override
    protected Action[] createActions() {
        return new Action[]{this.getOKAction()};
    }
}
