import javax.swing.*;
import java.awt.Color;

public class UI {
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
        StartButtonFirst = new JButton("Start1");
        StartButtonFirst.setBounds(80, 150, 75, 40);
        StartButtonFirst.setBackground(Color.GREEN); // Set the background color to green
        StartButtonSecond = new JButton("Start2");
        StartButtonSecond.setBounds(165, 150, 75, 40);
        StartButtonSecond.setBackground(Color.GREEN); // Set the background color to green
        // STOP
        StopButtonFirst = new JButton("Stop1");
        StopButtonFirst.setBounds(80, 100, 75, 40);
        StopButtonFirst.setBackground(Color.RED); // Set the background color to red
        StopButtonSecond = new JButton("Stop2");
        StopButtonSecond.setBounds(165, 100, 75, 40);
        StopButtonSecond.setBackground(Color.RED); // Set the background color to red


        Frame.add(StopButtonFirst);
        Frame.add(StopButtonSecond);
        Frame.add(StartButtonFirst);
        Frame.add(StartButtonSecond);
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