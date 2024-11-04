package library.management.system;

import javax.swing.JPanel;
import javax.swing.JLabel;

public class MenuItem {
    private String kind;
    private JPanel jpn;
    private JLabel jlb;
    
    public MenuItem(String kind, JPanel jpn, JLabel jlb) {
        this.kind = kind;
        this.jpn = jpn;
        this.jlb = jlb;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public JPanel getPanel() {
        return jpn;
    }

    public void setPanel(JPanel panel) {
        this.jpn = panel;
    }

    public JLabel getLabel() {
        return jlb;
    }

    public void setLabel(JLabel label) {
        this.jlb = label;
    }
}
