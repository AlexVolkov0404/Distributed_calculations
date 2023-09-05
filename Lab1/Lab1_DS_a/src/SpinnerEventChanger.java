import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

class SpinnerEventChanger implements ChangeListener {
    MyThread threadFirst;
    MyThread threadSecond;
    UI ui;

    public SpinnerEventChanger(MyThread threadFirst, MyThread threadSecond, UI ui) {
        this.threadFirst = threadFirst;
        this.threadSecond = threadSecond;
        this.ui = ui;
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        JSpinner source = (JSpinner) e.getSource();
        int value = (int) source.getValue();

        if (source.equals(ui.SpinnerFirstThread)) {
            threadFirst.setPriority(value);
        } else if (source.equals(ui.SpinnerSecondThread)) {
            threadSecond.setPriority(value);
        }
    }
}