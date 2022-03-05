import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.image.AreaAveragingScaleFilter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Main{
    private final JTextField fuelsystemStreet;
    private final JTextField fuelsystemSport;
    private final JTextField fuelsystemRace;
    private final JTextField ignitionStreet;
    private final JTextField ignitionSport;
    private final JTextField ignitionRace;
    private JPanel rootPanel;
    private final JTextField airfilterStreet;
    private final JTextField airfilterSport;
    private final JTextField airfilterRace;
    private JLabel debugOutput;


    private String debug;
    private final ArrayList<String[][]> upgrades = new ArrayList<>();
    private final ArrayList<Float[][][]> result = new ArrayList<>();

    public Main() {
        rootPanel.setPreferredSize(new Dimension(579, 428));
        rootPanel.setLayout(null);

        makeLabel("Air Filter", 10, 10);
        airfilterStreet  = makeTextfield(10, 35, 35, 25);
        airfilterSport   = makeTextfield(50, 35, 35, 25);
        airfilterRace    = makeTextfield(90, 35, 35, 25);

        makeLabel("Fuel System", 10, 65);
        fuelsystemStreet = makeTextfield(10, 90, 35, 25);
        fuelsystemSport  = makeTextfield(50, 90, 35, 25);
        fuelsystemRace  = makeTextfield(90, 90, 35, 25);

        makeLabel("Ignition", 10, 120);
        ignitionStreet = makeTextfield(10, 145, 35, 25);
        ignitionSport  = makeTextfield(50, 145, 35, 25);
        ignitionRace  = makeTextfield(90, 145, 35, 25);

        makeButton("Calculate",425, 375, 145, 45).addActionListener(this::getResult);
    }

    private void getResult(ActionEvent e){
        upgrades.add(getValues(airfilterStreet, airfilterSport, airfilterRace));
        upgrades.add(getValues(fuelsystemStreet, fuelsystemSport, fuelsystemRace));
        upgrades.add(getValues(ignitionStreet, ignitionSport, ignitionRace));

        int count = 0;
        String[] type = {"Street","Sport","Race"};
        String[] upgrade_type = {"Air filter","Fuel system","Ignition"};
        for (String[][] a : upgrades) {
            ArrayList<Float> f = new ArrayList<>();
            for (int b = 0; b < 3; b++) {
                float PI = Integer.parseInt(a[b][0]);
                float HP = Integer.parseInt(a[b][1]);
                System.out.println(upgrade_type[count] + " " + type[b] + " HP/pi = " + (HP/PI) + " (PI:" + PI + " - HP:" + HP);
                f.add(HP / PI);
            }
            count++;
            f.sort(Collections.reverseOrder());
        }
    }

    public String[][] getValues(JTextField street, JTextField sport, JTextField race){
        return new String[][]{
                street.getText().split(","),
                sport.getText().split(","),
                race.getText().split(",")
        };
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (Exception ignored) {}

        JFrame frame = new JFrame ("Upgrade Calculator - root :)");
        frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add (new Main().rootPanel);
        frame.pack();
        frame.setVisible (true);
    }

    public JLabel makeLabel(String text, int x, int y){
        JLabel jl = new JLabel(text);
        jl.setBounds(x,y,500,35);
        rootPanel.add(jl);
        return jl;
    }

    public JButton makeButton(String text, int x, int y, int w, int h){
        JButton jb = new JButton(text);
        jb.setBounds(x,y,w,h);
        rootPanel.add(jb);
        return jb;
    }

    public JTextField makeTextfield(int x, int y, int w, int h){
        JTextField jt = new JTextField(5);
        jt.setBounds(x,y,w,h);
        rootPanel.add(jt);
        return jt;
    }
}
