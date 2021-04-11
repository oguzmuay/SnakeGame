package com.Oguz.engine.Input;

import com.Oguz.engine.GameContainer;

public class CustomInput {
    private GameContainer gc;
    private int value;
    private final int positiveInput;
    private final int negativeInput;
    private final int alternativePositiveInput;
    private final int alternativeNegativeInput;
    public CustomInput(GameContainer gc,int positiveInput, int negativeInput)
    {
        this.gc = gc;
        this.positiveInput = positiveInput;
        this.negativeInput = negativeInput;
        this.alternativePositiveInput = positiveInput;
        this.alternativeNegativeInput = negativeInput;
    }
    public CustomInput(GameContainer gc,int positiveInput, int negativeInput,
                       int alternativePositiveInput,int alternativeNegativeInput)
    {
        this.gc = gc;
        this.positiveInput = positiveInput;
        this.negativeInput = negativeInput;
        this.alternativePositiveInput = alternativePositiveInput;
        this.alternativeNegativeInput = alternativeNegativeInput;
    }
    public void Update()
    {
        value = Boolean.compare((gc.getInput().isKey(positiveInput) || gc.getInput().isKey(alternativePositiveInput)),
                false) -
                Boolean.compare((gc.getInput().isKey(negativeInput) || gc.getInput().isKey(alternativeNegativeInput)),
                false);

    }

    public int getValue() {
        return value;
    }
}
