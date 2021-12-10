package lab1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static java.lang.Math.*;

public class MainFrame extends JFrame {
    private static final int WIDTH=400;
    private static final int HEIGHT=320;
    private double sum;
    private JTextField textFieldX;
    private JTextField textFieldY;
    private JTextField textFieldZ;
    private JTextField textFieldResult;
    private ButtonGroup radioButtons=new ButtonGroup();
    private Box hBoxFormulaType=Box.createHorizontalBox();
    private int formulaID=1;

    public MainFrame(){
        super("Вычисление формулы");
        setSize(WIDTH,HEIGHT);
        Toolkit kit=Toolkit.getDefaultToolkit();
        setLocation((kit.getScreenSize().width - WIDTH)/2,
                (kit.getScreenSize().height - HEIGHT)/2);
        hBoxFormulaType.add(Box.createHorizontalGlue());
        addRadioButton("Формула 1", 1);
        addRadioButton("Формула 2", 2);
        radioButtons.setSelected(radioButtons.getElements().nextElement().getModel(), true);
        hBoxFormulaType.add(Box.createHorizontalGlue());
        hBoxFormulaType.setBorder(BorderFactory.createLineBorder(Color.RED));
        JLabel labelForX=new JLabel("X:");
        textFieldX=new JTextField("0.0",10);
        textFieldX.setMaximumSize(textFieldX.getPreferredSize());
        JLabel labelForY=new JLabel("Y:");
        textFieldY =new JTextField("0.0",10);
        textFieldY.setMaximumSize(textFieldY.getPreferredSize());
        JLabel labelForZ=new JLabel("Z:");
        textFieldZ=new JTextField("0.0",10);
        textFieldZ.setMaximumSize(textFieldZ.getPreferredSize());


        Box hBoxVariables=Box.createHorizontalBox();
        hBoxVariables.setBorder(BorderFactory.createLineBorder(Color.RED));
        hBoxVariables.add(Box.createHorizontalGlue());
        hBoxVariables.add(labelForX);
        hBoxVariables.add(Box.createHorizontalStrut(10));
        hBoxVariables.add(textFieldX);
        hBoxVariables.add(Box.createHorizontalStrut(100));
        hBoxVariables.add(labelForY);
        hBoxVariables.add(Box.createHorizontalStrut(10));
        hBoxVariables.add(textFieldY);
        hBoxVariables.add(Box.createHorizontalStrut(100));
        hBoxVariables.add(labelForZ);
        hBoxVariables.add(Box.createHorizontalStrut(10));
        hBoxVariables.add(textFieldZ);
        hBoxVariables.add(Box.createHorizontalGlue());

        JLabel labelForResults=new JLabel("Результат:");
        textFieldResult=new JTextField("0.0",10);
        Box hBoxResult=Box.createHorizontalBox();
        hBoxResult.add(Box.createHorizontalGlue());
        hBoxResult.add(labelForResults);
        hBoxResult.add(Box.createHorizontalStrut(10));
        hBoxResult.add(textFieldResult);
        hBoxResult.add(Box.createHorizontalGlue());
        hBoxResult.setBorder(BorderFactory.createLineBorder(Color.BLUE));
        textFieldResult.setMaximumSize(textFieldResult.getPreferredSize());

        JButton buttonCalc=new JButton("Вычислить");
        buttonCalc.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    double x=Double.parseDouble(textFieldX.getText());
                    double y=Double.parseDouble(textFieldY.getText());
                    double z=Double.parseDouble(textFieldZ.getText());
                    double result;
                    if(formulaID==1){
                        result = formula1(x, y, z);
                    }else{
                        result = formula2(x, y, z);
                    }
                    textFieldResult.setText(Double.toString(result));
                }catch (NumberFormatException ex){
                    JOptionPane.showMessageDialog(MainFrame.this,"Ошибка в формате записи числа с плавающей запятой" ,
                            "Ошибоычный формат числа",JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        JButton buttonReset=new JButton("Очистить поля");
        buttonReset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textFieldX.setText("0.0");
                textFieldY.setText("0.0");
                textFieldResult.setText("0.0");
            }
        });

        JButton MC=new JButton("MC");
        MC.addActionListener(e -> sum=0);

        JButton MPlus=new JButton("M+");
        MPlus.addActionListener(e->{
            sum+=Double.valueOf(textFieldResult.getText());
            textFieldResult.setText(Double.toString(sum));
        });

        Box hBoxButtons=Box.createHorizontalBox();
        hBoxButtons.add(Box.createHorizontalGlue());
        hBoxButtons.add(buttonCalc);
        hBoxButtons.add(Box.createHorizontalStrut(10));
        hBoxButtons.add(buttonReset);
        hBoxButtons.add(Box.createHorizontalStrut(10));
        hBoxButtons.add(MC);
        hBoxButtons.add(Box.createHorizontalStrut(10));
        hBoxButtons.add(MPlus);
        hBoxButtons.add(Box.createHorizontalGlue());
        hBoxButtons.setBorder(BorderFactory.createLineBorder(Color.GREEN));

        Box contextBox=Box.createVerticalBox();
        contextBox.add(Box.createVerticalGlue());
        contextBox.add(hBoxFormulaType);
        contextBox.add(hBoxVariables);
        contextBox.add(hBoxResult);
        contextBox.add(hBoxButtons);
        contextBox.add(Box.createVerticalGlue());
        getContentPane().add(contextBox,BorderLayout.CENTER);
    }

    private void addRadioButton(String buttonName, final int formulaID){
        JRadioButton button=new JRadioButton(buttonName);
        button.addActionListener(e->this.formulaID=formulaID);
        radioButtons.add(button);
        hBoxFormulaType.add(button);
    }

    public double formula1(double x, double y, double z){
        return (sin(y)+pow(y,2)+exp(cos(y)))*pow(log(z)+sin(PI*x*x),0.25);
    }

    public double formula2(double x, double y, double z){
        return (tan(x*x)+sqrt(y))/(z*log(x+y));
    }
}
