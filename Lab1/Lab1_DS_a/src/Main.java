import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

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
                System.exit(0); // Завершение программы после закрытия окна
            }
        });
    }
}

class UI {
    public JFrame Frame;
    public JButton StartButton;
    public JSlider SliderThread;
    public JSpinner SpinnerFirstThread;
    public JSpinner SpinnerSecondThread;

    public UI() {
        Frame = new JFrame("Thread Example"); // Добавлено название окна
        StartButton = new JButton("Start!");
        StartButton.setBounds(80, 150, 150, 40);
        Frame.add(StartButton);

        SliderThread = new JSlider(0, 100, 50);
        SliderThread.setBounds(10, 50, 300, 40);
        SliderThread.setPaintTrack(true);
        SliderThread.setPaintTicks(true);
        SliderThread.setPaintLabels(true);
        SliderThread.setMajorTickSpacing(10);
        Frame.add(SliderThread);

        SpinnerFirstThread = new JSpinner();
        SpinnerSecondThread = new JSpinner();
        SpinnerFirstThread.setBounds(50, 100, 75, 40);
        SpinnerSecondThread.setBounds(200, 100, 75, 40);
        SpinnerFirstThread.setValue(1);
        SpinnerSecondThread.setValue(1);
        Frame.add(SpinnerFirstThread);
        Frame.add(SpinnerSecondThread);

        Frame.setSize(350, 300);
        Frame.setLayout(null);
        Frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Завершение программы при закрытии окна
        Frame.setVisible(true);
    }
}

class MyThread extends Thread {
    JSlider SliderThread;
    int numberToSlider;

    boolean IsContinue = true;


    public void CloseThreadCycle() {
        IsContinue = false;
    }

    public void run() {
        while (IsContinue) {
            try {
                int newSliderNumber = (int) SliderThread.getValue();
                if (newSliderNumber > numberToSlider)
                    newSliderNumber -= 1;
                else if (newSliderNumber < numberToSlider)
                    newSliderNumber += 1;
                SliderThread.setValue(newSliderNumber);
                //Thread.sleep(10);
            } catch (Exception e) {
                System.out.println("Exception is caught");
            }
        }
    }

    public MyThread(JSlider SliderThread, int numberToSlider) {
        this.SliderThread = SliderThread;
        this.numberToSlider = numberToSlider;
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