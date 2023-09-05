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
        spinnerEventChanger = new SpinnerEventChanger(threadFirst, threadSecond, ui);
        ui.StartButtonFirst.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CloseThread(threadSecond);
                if (MyThread.IsThreadFree()) {
                    threadFirst = new MyThread(ui.SliderThread, 10);
                    threadFirst.setPriority(1);
                    threadFirst.start();
                }
            }
        });
        ui.StartButtonSecond.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CloseThread(threadFirst);
                if (MyThread.IsThreadFree()) {
                    threadSecond = new MyThread(ui.SliderThread, 90);
                    threadSecond.setPriority(10);
                    threadSecond.start();
                }
            }
        });
        ui.StopButtonFirst.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CloseThread(threadFirst);
            }
        });
        ui.StopButtonSecond.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CloseThread(threadSecond);
            }
        });

        ui.Frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                threadFirst.CloseThreadCycle();
                threadSecond.CloseThreadCycle();
                System.exit(0);
            }
        });
    }

    void CloseThread(MyThread thread) {
        if (thread != null) thread.CloseThreadCycle();
    }
}
