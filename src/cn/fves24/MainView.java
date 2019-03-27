package cn.fves24;


import cn.fves24.sqlite.NoteService;
import cn.fves24.vo.Note;
import com.intellij.openapi.ui.Messages;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Map;

/**
 * Note list
 *
 * @author fves
 */
public class MainView {

    private JPanel content;
    private JButton addButton;
    private JButton delButton;
    private JButton syncButton;
    private JTabbedPane tabbedPane1;
    private JList<String> noteList;
    private JList<String> trashList;
    private JButton loginButton;
    private JButton cleanButton;
    private JLabel loginStatus;

    private NoteService noteService = new NoteService();


    MainView() {
        init();
        /* 添加各种点击事件 */
        syncButtonClick();
        delButtonClick();
        addButtonClick();
        loginButtonClick();
        showListItemClick();
        cleanButtonClick();
    }

    private void cleanButtonClick() {
        cleanButton.addActionListener(e -> {
            int ret = Messages.showOkCancelDialog("确认删除？", "清空提示", "确认", "取消", Messages.getQuestionIcon());
            if (ret == Messages.OK) {
                noteService.cleanAll();
                DefaultListModel<String> model = (DefaultListModel<String>) trashList.getModel();
                model.clear();
                trashList.setModel(model);
            }
        });
    }

    /**
     * 初始化List数据
     */
    private void init() {
        DefaultListModel<String> noteListModel = new DefaultListModel<>();
        DefaultListModel<String> trashListModel = new DefaultListModel<>();
        for (Map.Entry<String, Note> noteEntry : Constants.noteMap.entrySet()) {
            if (noteEntry.getValue().getDel() == 0) {
                noteListModel.addElement(noteEntry.getKey());
            }
            if (noteEntry.getValue().getDel() == 1) {
                trashListModel.addElement(noteEntry.getKey());
            }
        }
        noteList.setModel(noteListModel);
        trashList.setModel(trashListModel);
    }

    /**
     * 同步数据到服务器
     */
    private void syncButtonClick() {
        syncButton.addActionListener(e -> {

        });
    }

    /**
     * 显示点击的Note
     */
    void showListItemClick() {
        noteList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    String key = noteList.getSelectedValue();
                    Note note = Constants.noteMap.get(key);
                    if (note != null) {
                        new NoteShow(key, note.getContent()).show();
                    }
                }
            }
        });
        trashList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    String key = trashList.getSelectedValue();
                    Note note = Constants.noteMap.get(key);
                    if (note != null) {
                        new NoteShow(key, note.getContent()).show();
                    }
                }
            }
        });
    }

    /**
     * 删除Note
     */
    private void delButtonClick() {
        delButton.addActionListener(e -> {
            int selectedIndex = noteList.getSelectedIndex();
            DefaultListModel<String> noteListModel = (DefaultListModel<String>) noteList.getModel();
            if (selectedIndex >= 0 && selectedIndex < noteListModel.size()) {
                String title = noteListModel.get(selectedIndex);
                // 将删除的元素 添加到 回收List中
                DefaultListModel<String> trashListModel = (DefaultListModel<String>) trashList.getModel();
                trashListModel.addElement(title);
                // 从NoteList中删除元素
                noteListModel.removeElementAt(selectedIndex);
                noteList.setModel(noteListModel);

                noteService.delNote(title);
            }
        });
    }

    /**
     * 添加Note
     */
    private void addButtonClick() {
        addButton.addActionListener(e -> new NoteItem().show());
    }


    /**
     * 登录
     */
    private void loginButtonClick() {
        loginButton.addActionListener(e -> new Login().show());
    }

    /**
     * 添加List
     */
    public void addNote(Note note) {
        DefaultListModel<String> model = (DefaultListModel<String>) noteList.getModel();
        model.addElement(note.getTitle());
        noteList.setModel(model);
    }

    JPanel getContent() {
        return content;
    }
}
