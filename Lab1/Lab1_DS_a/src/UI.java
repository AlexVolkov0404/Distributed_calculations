import javax.swing.*;

class UI {
    public JFrame Frame;
    public JButton StartButton;
    public JSlider SliderThread;
    public JSpinner SpinnerFirstThread;
    public JSpinner SpinnerSecondThread;

    public UI() {
        Frame = new JFrame("Thread Example");
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
        Frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Frame.setVisible(true);
    }
}