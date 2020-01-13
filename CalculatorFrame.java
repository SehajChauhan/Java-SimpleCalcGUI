import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
/**
This program is a calculator with basic operations.
@author Sehaj Chauhan 215939838
*/
public class CalculatorFrame extends JFrame
{
private JLabel display;
private double lastValue;
private String lastOperator;
private boolean startNewValue;
private static final int FRAME_WIDTH = 300;
private static final int FRAME_HEIGHT = 500;

public CalculatorFrame()        //basic frame method
{
        createButtonPanel();
        display = new JLabel("0");
        JScrollPane scrollPane = new JScrollPane(display, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        add(scrollPane, BorderLayout.CENTER);
        setVisible(true);
        setSize(FRAME_WIDTH, FRAME_HEIGHT-50);

        lastValue = 0;
        lastOperator = "=";
        startNewValue = true;
}

private void createButtonPanel()       //method to create various buttons
{
        JPanel buttonPanel = new JPanel();
        JPanel buttonPane2 = new JPanel();

            buttonPane2.setLayout(new GridLayout(1,2));
            buttonPanel.setLayout(new GridLayout(4, 4));

            buttonPane2.add(makeOperatorButton("Clear Last"));
            buttonPane2.add(makeOperatorButton("Clear All"));

            buttonPanel.add(makeDigitButton("7"));
            buttonPanel.add(makeDigitButton("8"));
            buttonPanel.add(makeDigitButton("9"));
            buttonPanel.add(makeOperatorButton("+"));
            buttonPanel.add(makeDigitButton("4"));
            buttonPanel.add(makeDigitButton("5"));
            buttonPanel.add(makeDigitButton("6"));
            buttonPanel.add(makeOperatorButton("-"));
            buttonPanel.add(makeDigitButton("1"));
            buttonPanel.add(makeDigitButton("2"));
            buttonPanel.add(makeDigitButton("3"));
            buttonPanel.add(makeOperatorButton("*"));
            buttonPanel.add(makeDigitButton("0"));
            buttonPanel.add(makeDigitButton("."));
            buttonPanel.add(makeOperatorButton("="));
            buttonPanel.add(makeOperatorButton("/"));

     add(buttonPanel, BorderLayout.SOUTH);
     add(buttonPane2, BorderLayout.NORTH);
}



public double calculate(double value1, double value2, String op)    //engine for the calculator, contains logic for
    {
          if (op.equals("+"))  // for addition
             {
                 return value1 + value2;
             }
          else if (op.equals("-"))   //for subtraction
             {
                 return value1 - value2;
             }
          else if (op.equals("*"))     //for multiplication
             {
                 return value1 * value2;
             }
          else if (op.equals("/"))     //for division
             {
                 return value1 / value2;
             }
          else //        //to get answer
             {
                 return value2;
             }
     }


class DigitButtonListener implements ActionListener
    {
        private String digit;
        public DigitButtonListener(String aDigit)    //store digits
        {
           digit = aDigit;
        }

        public void actionPerformed(ActionEvent event)
        {
           if (startNewValue)      //when new digit is started
           {
               display.setText("");
               startNewValue = false;
          }
           display.setText(display.getText() + digit);   //display digit
        }
    }

        public JButton makeDigitButton(String digit)    //get input from  digit buttons.
   {
            JButton button = new JButton(digit);
            ActionListener listener = new DigitButtonListener(digit);
            button.addActionListener(listener);
            return button;
    }





class OperatorButtonListener implements ActionListener
{
            private String operator;
            public OperatorButtonListener(String anOperator)
           {
               operator = anOperator;
           }
            public void actionPerformed(ActionEvent event)
           {
               if(operator.equals("Clear All"))    //for clear all button action
               {
                   startNewValue=true;
                   lastValue = 0;
                   lastOperator = "=";
                   display.setText("0");
               }

              if(operator.equals("Clear Last"))   //for clear last
             {
                lastOperator=operator;
             }
              if (!startNewValue)    //to display calculated answer
             {
                 double value = Double.parseDouble(display.getText());
                 lastValue = calculate(lastValue, value, lastOperator);
                 display.setText("" + lastValue);
                 startNewValue = true;
             }
        lastOperator = operator;  //so that when new operator is selected, the calculations is done simultaneously.
}
}

          public JButton makeOperatorButton(String op)      //get input from operator buttons.
          {
                  JButton button = new JButton(op);
                  ActionListener listener = new OperatorButtonListener(op);
                  button.addActionListener(listener);
                  return button;
          }
}
