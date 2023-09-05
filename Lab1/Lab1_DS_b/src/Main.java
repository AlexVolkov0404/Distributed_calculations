import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {
    public static void main(String[] args) {
        Program program = new Program();
    }
}

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
    }

    void CloseThread(MyThread thread) {
        if (thread != null) thread.CloseThreadCycle();
    }
}

class UI {
    public JFrame Frame;
    public JButton StartButtonFirst;
    public JButton StartButtonSecond;
    public JButton StopButtonFirst;
    public JButton StopButtonSecond;
    public JSlider SliderThread;

    public UI() {
        Frame = new JFrame();
        // Buttons
        // START
        StartButtonFirst = new JButton("StartFirst");
        StartButtonFirst.setBounds(80, 150, 75, 40);
        StartButtonSecond = new JButton("StartSecond");
        StartButtonSecond.setBounds(165, 150, 75, 40);
        // STOP
        StopButtonFirst = new JButton("Stop1");
        StopButtonFirst.setBounds(80, 100, 75, 40);
        StopButtonSecond = new JButton("Stop2");
        StopButtonSecond.setBounds(165, 100, 75, 40);

        Frame.add(StartButtonFirst);
        Frame.add(StartButtonSecond);
        Frame.add(StopButtonFirst);
        Frame.add(StopButtonSecond);
        // Slider
        SliderThread = new JSlider(0, 100, 50);
        SliderThread.setBounds(10, 50, 300, 40);
        SliderThread.setPaintTrack(true);
        SliderThread.setPaintTicks(true);
        SliderThread.setPaintLabels(true);
        SliderThread.setMajorTickSpacing(10);
        Frame.add(SliderThread);
        // Layout
        Frame.setSize(350, 300);
        Frame.setLayout(null);
        Frame.setVisible(true);
    }
}

class SpinnerEventChanger implements ChangeListener {
    MyThread threadFirst;
    MyThread threadSecond;
    UI ui;

    public SpinnerEventChanger(MyThread threadFirst, MyThread threadSecond, UI ui) {
        this.threadFirst = threadFirst;
        this.threadSecond = threadSecond;
        this.ui = ui;
    }

    public void stateChanged(ChangeEvent e) {
        JSpinner source = (JSpinner) e.getSource();
        if ((int) source.getValue() > 10)
            source.setValue(10);
        else if ((int) source.getValue() <= 0)
            source.setValue(1);
        int spinnerValue = (int) source.getValue();
    }
}

class MyThread extends Thread {
    JSlider SliderThread;
    int numberToSlider;
    boolean IsRunning = false;
    static boolean IsFree = true;

    public static boolean IsThreadFree() {
        return IsFree;
    }

    public void CloseThreadCycle() {
        if (IsRunning) {
            IsRunning = false;
            IsFree = true;
        }
    }

    public void run() {
        if (IsFree) {
            IsRunning = true;
            IsFree = false;
            while (IsRunning) {
                try {
                    int newSliderNumber = (int) SliderThread.getValue();
                    if (newSliderNumber > numberToSlider)
                        newSliderNumber -= 1;
                    else if (newSliderNumber < numberToSlider)
                        newSliderNumber += 1;
                    SliderThread.setValue(newSliderNumber);
                    Thread.sleep(10);
                } catch (Exception e) {
                    System.out.println("Exception is caught");
                }
            }
        }
    }

    public MyThread(JSlider SliderThread, int numberToSlider) {
        this.SliderThread = SliderThread;
        this.numberToSlider = numberToSlider;
    }
}