package com.Oguz.engine.Input;

import com.Oguz.engine.GameContainer;

import java.util.HashMap;
import java.util.Map;

public class InputSystem {
    //private GameContainer gc;
    private final Map<String,CustomInput> customInputs = new HashMap<String,CustomInput>();
    public InputSystem(GameContainer gc)
    {
        //this.gc = gc;
        customInputs.put("Vertical",new CustomInput(gc,Keycode.S,Keycode.W));
        customInputs.put("Horizontal",new CustomInput(gc,Keycode.D,Keycode.A));
    }
    public void Update()
    {
        for (Map.Entry<String, CustomInput> entry : customInputs.entrySet()) {
            entry.getValue().Update();
        }
    }
    public int GetValue(String key)
    {
        return customInputs.get(key).getValue();
    }
}
