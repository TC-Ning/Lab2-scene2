package image;

import javax.swing.*;
//C4上的第二个修改
public class demoimage {
        public static void setAdjustmentWindow(JFrame frame)
        {
            JLabel jl3=new JLabel(new ImageIcon("99cfb.jpg"));
            frame.add(jl3);
            jl3.setBounds(800, 100, 554, 386);

        }
}
