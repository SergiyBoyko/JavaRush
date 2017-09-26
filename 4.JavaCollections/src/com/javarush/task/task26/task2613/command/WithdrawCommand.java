package com.javarush.task.task26.task2613.command;

import com.javarush.task.task26.task2613.ConsoleHelper;
import com.javarush.task.task26.task2613.CurrencyManipulator;
import com.javarush.task.task26.task2613.CurrencyManipulatorFactory;
import com.javarush.task.task26.task2613.exception.InterruptOperationException;
import com.javarush.task.task26.task2613.exception.NotEnoughMoneyException;

import java.util.Map;

/**
 * Created by Serhii Boiko on 25.09.2017.
 */
class WithdrawCommand implements Command{
    @Override
    public void execute() throws InterruptOperationException {
        String currencyCode = ConsoleHelper.askCurrencyCode();
        CurrencyManipulator manipulator = CurrencyManipulatorFactory.getManipulatorByCurrencyCode(currencyCode);
        while (true)
        {
            try
            {
                ConsoleHelper.writeMessage("Please, enter a sum");
                int sum = Integer.parseInt(ConsoleHelper.readString());
                if (sum < 0)
                {
                    throw new NumberFormatException();
                }
                if (manipulator.isAmountAvailable(sum))
                {
                    Map<Integer, Integer> result = manipulator.withdrawAmount(sum);
                    for (Map.Entry<Integer, Integer> pair : result.entrySet())
                    {
                        ConsoleHelper.writeMessage(String.format("\t%s - %s", pair.getKey(), pair.getValue()));
                    }
                    ConsoleHelper.writeMessage("Transaction was done success");
                    break;
                }
                else
                {
                    throw new NotEnoughMoneyException();
                }
            }
            catch(NumberFormatException e)
            {
                ConsoleHelper.writeMessage("Incorrect data. Please, try again");
                continue;
            }
            catch (NotEnoughMoneyException e)
            {
                ConsoleHelper.writeMessage("Incorrect sum: it's not enough money or aliquant sum. Please, try again");
                continue;
            }
        }
    }
}
