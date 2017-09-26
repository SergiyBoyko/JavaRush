package com.javarush.task.task37.task3711;

/**
 * Created by Serhii Boiko on 07.09.2017.
 */
public class Computer {
    private CPU cpu;
    private Memory memory;
    private HardDrive hardDrive;

    public Computer() {
        this.cpu = new CPU();
        this.memory = new Memory();
        this.hardDrive = new HardDrive();
    }

    void run() {
        cpu.calculate();
        memory.allocate();
        hardDrive.storeData();
    }
}
