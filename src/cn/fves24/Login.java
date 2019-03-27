package cn.fves24;

import com.intellij.openapi.ui.DialogWrapper;
import com.intellij.openapi.ui.Messages;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

/**
 * 登录
 * @author fves
 */
public class Login extends DialogWrapper {
    private JPanel jpanel;
    private JTextField accountField;
    private JPasswordField passwordField;
    private JButton loginButton;


    Login() {
        super(true);
        init();
        setTitle("登录");

        loginButton.addActionListener(e->{
            String account = accountField.getText();
            String password = accountField.getText();
            if ("".equals(account) || "".equals(password)) {
                Messages.showWarningDialog("账号或密码不能为空", "登录提示");
            } else {
                Messages.showInfoMessage("登录成功", "登录提示");
                Constants.isLogin = true;
                this.close(0);
            }
        });
    }

    @Nullable
    @Override
    protected JComponent createCenterPanel() {
        return jpanel;
    }

    @NotNull
    @Override
    protected Action[] createActions() {
        return new Action[0];
    }
}
