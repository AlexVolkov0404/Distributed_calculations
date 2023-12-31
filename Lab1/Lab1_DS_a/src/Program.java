import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

class Program {
    MyThread threadFirst;
    MyThread threadSecond;
    UI ui;
    SpinnerEventChanger spinnerEventChanger;

    public Program() {
        ui = new UI();
        threadFirst = new MyThread(ui.SliderThread, 10);
        threadSecond = new MyThread(ui.SliderThread, 90);
        spinnerEventChanger = new SpinnerEventChanger(threadFirst, threadSecond, ui);

        ui.StartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                threadFirst.start();
                threadSecond.start();
            }
        });

        ui.SpinnerFirstThread.addChangeListener(spinnerEventChanger);
        ui.SpinnerSecondThread.addChangeListener(spinnerEventChanger);

        ui.Frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                threadFirst.CloseThreadCycle();
                threadSecond.CloseThreadCycle();
                System.exit(0);
            }
        });
    }
}
