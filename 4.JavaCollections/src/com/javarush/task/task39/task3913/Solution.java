package com.javarush.task.task39.task3913;

import java.nio.file.Paths;
import java.util.Date;

public class Solution {
    public static void main(String[] args) {
        LogParser logParser = new LogParser(Paths.get("c:/logs/"));
        System.out.println(logParser.getNumberOfUniqueIPs(null, new Date()));
        System.out.println("getIPsForUserAmigo" + logParser.getIPsForUser("Amigo", null, null));
        System.out.println("getIPsForEventDONE_TASK" + logParser.getIPsForEvent(Event.DONE_TASK, null, null));
        System.out.println("getIPsForEventSOLVE_TASK" + logParser.getIPsForEvent(Event.SOLVE_TASK, null, new Date()));
        System.out.println("getIPsForStatusERROR" + logParser.getIPsForStatus(Status.ERROR, null, null));
        System.out.println("getNumberOfUserEvents" + logParser.getNumberOfUserEvents("Eduard Petrovich Morozko", null, null));
        System.out.println("getDoneTaskUsers" + logParser.getDoneTaskUsers(null, null));
        System.out.println("getDoneTaskUsers" + logParser.getDoneTaskUsers(null, null, 15));
        System.out.println("getSolvedTaskUsers" + logParser.getSolvedTaskUsers(null, null, 18));
        System.out.println("getDownloadedPluginUsers" + logParser.getDownloadedPluginUsers(null, null));
        System.out.println(logParser.getAllUsers());
        System.out.println("getDatesForUserAndEvent" +
                logParser.getDatesForUserAndEvent("Eduard Petrovich Morozko", Event.WRITE_MESSAGE, null, null));
        System.out.println("getDatesWhenSomethingFailed" +
                logParser.getDatesWhenSomethingFailed(null, new Date()));
        System.out.println("getDatesWhenErrorHappened" +
                logParser.getDatesWhenErrorHappened(null, new Date()));
        System.out.println("getDateWhenUserLoggedFirstTime" +
                logParser.getDateWhenUserLoggedFirstTime("Amigo", null, null));
        System.out.println("getDateWhenUserSolvedTask" +
                logParser.getDateWhenUserSolvedTask("Amigo", 18, null, null));
        System.out.println("getDateWhenUserDoneTask" +
                logParser.getDateWhenUserDoneTask("Amigo", 18, null, null));
        System.out.println("getDateWhenUserDoneTask" +
                logParser.getDateWhenUserDoneTask("Amigo", 15, null, null));
        System.out.println("getDatesWhenUserWroteMessage" +
                logParser.getDatesWhenUserWroteMessage("Amigo", null, null));
        System.out.println(logParser.getNumberOfSuccessfulAttemptToSolveTask(18, null, null));
        System.out.println(logParser.getAllSolvedTasksAndTheirNumber(null, new Date()));
    }
}