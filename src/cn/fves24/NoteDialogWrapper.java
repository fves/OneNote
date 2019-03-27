package cn.fves24;

import com.intellij.openapi.ui.DialogWrapper;

abstract class NoteDialogWrapper extends DialogWrapper {

    NoteDialogWrapper(boolean canBeParent) {
        super(canBeParent);
        setHorizontalStretch(2.5f);
        setVerticalStretch(2.0f);
    }
}
