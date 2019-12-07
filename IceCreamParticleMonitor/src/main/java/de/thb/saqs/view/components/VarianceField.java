package de.thb.saqs.view.components;

import javax.swing.*;
import java.awt.*;

//TODO no calculation in component ... make a service for the logic
public class VarianceField extends JTextField {

    public VarianceField(Integer targetValue){
        setEditable(false);
        setForegroundColor(targetValue);
        setText(targetValue.toString().replace("-",""));
    }

    private void setForegroundColor(Integer value){
        if(value>0){
            setForeground(Color.GREEN);
        }else if(value<0){
            setForeground(Color.RED);
        }else{
            setForeground(Color.BLACK);
        }
    }
}
