package com.javarush.task.task26.task2613.command;

import com.javarush.task.task26.task2613.ConsoleHelper;
import com.javarush.task.task26.task2613.CurrencyManipulatorFactory;
import com.javarush.task.task26.task2613.exception.InterruptOperationException;

/**
 * Created by Serhii Boiko on 25.09.2017.
 */
class DepositCommand implements Command {
    @Override
    public void execute() throws InterruptOperationException {
        try {
            String curCode = ConsoleHelper.askCurrencyCode();
            String[] str = ConsoleHelper.getValidTwoDigits(curCode);
            CurrencyManipulatorFactory.getManipulatorByCurrencyCode(curCode).addAmount(Integer.parseInt(str[0]), Integer.parseInt(str[1]));
        } catch (NumberFormatException ex) {
            ex.printStackTrace();
        }
    }
}
