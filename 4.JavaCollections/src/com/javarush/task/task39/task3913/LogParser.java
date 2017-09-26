package com.javarush.task.task39.task3913;

import com.javarush.task.task39.task3913.query.DateQuery;
import com.javarush.task.task39.task3913.query.EventQuery;
import com.javarush.task.task39.task3913.query.IPQuery;
import com.javarush.task.task39.task3913.query.UserQuery;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class LogParser implements IPQuery, UserQuery, DateQuery, EventQuery {

    private Path logDir;
    private List<String> entries;

    public LogParser(Path logDir) {
        this.logDir = logDir;
        entries = getEntries();
    }

    private List<String> getEntries() {
        String[] files = logDir.toFile().list(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return name.endsWith(".log");
            }
        });

        List<String> lines = new ArrayList<>();
        for (String file : files) {
            try {
                lines.addAll(Files.readAllLines(Paths.get(logDir + File.separator + file), Charset.defaultCharset()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return lines;
    }

    private boolean isCompatibleDate(long lineDateTime, Date after, Date before) {
        if (after == null && before == null) {
            return true;
        } else if (after == null) {
            if (lineDateTime <= before.getTime()) {
                return true;
            }
        } else if (before == null) {
            if (lineDateTime >= after.getTime()) {
                return true;
            }
        } else {
            if (lineDateTime >= after.getTime() && lineDateTime <= before.getTime()) {
                return true;
            }
        }
        return false;
    }

    private Set<String> getEntriesBetweenDates(Date after, Date before) {
        Set<String> res = new HashSet<>();
        for (String entry : entries) {
            DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss", Locale.ENGLISH);
            String element[] = entry.split("\\t");
            try {
                Date date = dateFormat.parse(element[2]);
                if (after != null && date.before(after)) continue;
                if (before != null && date.after(before)) continue;
                res.add(entry);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return res;
    }

    private Set<String> getOnlyOkStatus(Set<String> usersTry) {
        Set<String> successAttempts = new HashSet<>();
        for (String entry : usersTry) {
            String[] elements = entry.split("\\t");
            if (elements[4].equals(Status.OK.toString()) && !successAttempts.contains(entry)) {
                successAttempts.add(elements[1]);
            }
        }
        return successAttempts;
    }

    @Override
    public int getNumberOfUniqueIPs(Date after, Date before) {
        return getUniqueIPs(after, before).size();
    }

    @Override
    public Set<String> getUniqueIPs(Date after, Date before) {
        Set<String> uniqueIPs = new HashSet<>();
        for (String entry : getEntriesBetweenDates(after, before)) {
            String ip = entry.split("\\t")[0];
            if (!uniqueIPs.contains(ip)) {
                uniqueIPs.add(ip);
            }
        }
        return uniqueIPs;
    }

    private void addDateEntity(Date after, Date before, Set<Date> enteties, String[] parts) {
        Date lineDate = getDate(parts[2]);
        long lineDateTime = getDate(parts[2]).getTime();

        if (isCompatibleDate(lineDateTime, after, before)) {
            enteties.add(lineDate);
        }
    }

    private Set<String> getSomeWithFilters(int someNum, int filterNum, String filter, Date after, Date before, boolean checkAll) {
        Set<String> expectedPart = new HashSet<>();
        for (String entry : getEntriesBetweenDates(after, before)) {
            String[] elements = entry.split("\\t");
            if (checkAll) {
                if (elements[filterNum].equals(filter) && !expectedPart.contains(elements[someNum])) {
                    expectedPart.add(elements[someNum]);
                }
            } else {
                if (elements[filterNum].startsWith(filter) && !expectedPart.contains(elements[someNum])) {
                    expectedPart.add(elements[someNum]);
                }
            }
        }
        return expectedPart;
    }

    @Override
    public Set<String> getIPsForUser(String user, Date after, Date before) {
        int elementInEntry = 1; // user
        int ipNum = 0;
        return getSomeWithFilters(ipNum, elementInEntry, user, after, before, true);
    }

    @Override
    public Set<String> getIPsForEvent(Event event, Date after, Date before) {
        int elementInEntry = 3; // event
        int ipNum = 0;
        return getSomeWithFilters(ipNum, elementInEntry, event.toString(), after, before, false);
    }

    @Override
    public Set<String> getIPsForStatus(Status status, Date after, Date before) {
        int elementInEntry = 4; // status
        int ipNum = 0;
        return getSomeWithFilters(ipNum, elementInEntry, status.toString(), after, before, true);
    }

    @Override
    public Set<String> getAllUsers() {
        Set<String> users = new HashSet<>();
        for (String entry : entries) {
            String user = entry.split("\\t")[1];
            if (!users.contains(user)) {
                users.add(user);
            }
        }
        return users;
    }

    @Override
    public int getNumberOfUsers(Date after, Date before) {
        Set<String> users = new HashSet<>();
        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss", Locale.ENGLISH);
        for (String entry : entries) {
            String user = entry.split("\\t")[1];
            Date date;
            try {
                date = dateFormat.parse(entry.split("\\t")[2]);
                if (after != null && date.before(after)) continue;
                if (before != null && date.after(before)) continue;
                if (!users.contains(user)) {
                    users.add(user);
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return users.size();
    }

    @Override
    public int getNumberOfUserEvents(String user, Date after, Date before) {
        int elementInEntry = 1; // user
        int eventNum = 3;
        return getSomeWithFilters(eventNum, elementInEntry, user, after, before, true).size();
    }

    @Override
    public Set<String> getUsersForIP(String ip, Date after, Date before) {
        int elementInEntry = 0; // ip
        int userNum = 1;
        return getSomeWithFilters(userNum, elementInEntry, ip, after, before, true);
    }

    @Override
    public Set<String> getLoggedUsers(Date after, Date before) {
        int elementInEntry = 3; // event
        int userNum = 1;
        Set<String> usersTry = getSomeWithFilters(userNum, elementInEntry,
                Event.LOGIN.toString(), after, before, true);
        return usersTry;
    }

    @Override
    public Set<String> getDownloadedPluginUsers(Date after, Date before) {
        int elementInEntry = 3; // event
        int userNum = 1;
        Set<String> usersTry = getSomeWithFilters(userNum, elementInEntry,
                Event.DOWNLOAD_PLUGIN.toString(), after, before, true);
        return usersTry;
    }

    @Override
    public Set<String> getWroteMessageUsers(Date after, Date before) {
        int elementInEntry = 3; // event
        int userNum = 1;
        Set<String> usersTry = getSomeWithFilters(userNum, elementInEntry,
                Event.WRITE_MESSAGE.toString(), after, before, true);
        return usersTry;
    }

    @Override
    public Set<String> getSolvedTaskUsers(Date after, Date before) {
        int elementInEntry = 3; // event
        int userNum = 1;
        Set<String> usersTry = getSomeWithFilters(userNum, elementInEntry,
                Event.SOLVE_TASK.toString(), after, before, false);
        return usersTry;
    }

    @Override
    public Set<String> getSolvedTaskUsers(Date after, Date before, int task) {
        int elementInEntry = 3; // event
        int userNum = 1;
        Set<String> usersTry = getSomeWithFilters(userNum, elementInEntry,
                Event.SOLVE_TASK.toString() + " " + task, after, before, false);
        return usersTry;
    }

    @Override
    public Set<String> getDoneTaskUsers(Date after, Date before) {
        int elementInEntry = 3; // event
        int userNum = 1;
        Set<String> usersTry = getSomeWithFilters(userNum, elementInEntry,
                Event.DONE_TASK.toString(), after, before, false);
        return usersTry;
    }

    @Override
    public Set<String> getDoneTaskUsers(Date after, Date before, int task) {
        int elementInEntry = 3; // event
        int userNum = 1;
        Set<String> usersTry = getSomeWithFilters(userNum, elementInEntry,
                Event.DONE_TASK.toString() + " " + task, after, before, false);
        return usersTry;
    }

    @Override
    public Set<Date> getDatesForUserAndEvent(String user, Event event, Date after, Date before) {
        Set<Date> datesForUserAndEvent = new HashSet<>();

        for (String line : entries) {
            String[] parts = line.split("\\t");
            if (user.equals(parts[1]) && event.toString().equals(parts[3].split(" ")[0])) {
                addDateEntity(after, before, datesForUserAndEvent, parts);
            }
        }
        return datesForUserAndEvent;
    }

    private Set<Date> getDatesFromFormattedStrings(Set<String> strings) {
        Set<Date> dates = new HashSet<>();
        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss", Locale.ENGLISH);
        for (String s : strings) {
            try {
                dates.add(dateFormat.parse(s));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return dates;
    }

    @Override
    public Set<Date> getDatesWhenSomethingFailed(Date after, Date before) {
        int elementInEntry = 4; // status
        int dataNum = 2;
        Set<String> usersTry = getSomeWithFilters(dataNum, elementInEntry,
                Status.FAILED.toString(), after, before, true);
        return getDatesFromFormattedStrings(usersTry);
    }

    @Override
    public Set<Date> getDatesWhenErrorHappened(Date after, Date before) {
        int elementInEntry = 4; // status
        int dataNum = 2;
        Set<String> usersTry = getSomeWithFilters(dataNum, elementInEntry,
                Status.ERROR.toString(), after, before, true);
        return getDatesFromFormattedStrings(usersTry);
    }

    private List<String> getEntriesForUser(String user) {
        List<String> filteredEntries = new ArrayList<>();
        for (String s : entries) {
            String someUser = s.split("\\t")[1];
            if (someUser.equals(user))
                filteredEntries.add(s);
        }
        return filteredEntries;
    }

    private Date getDate(String part) {
        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss", Locale.ENGLISH);
        Date date = null;
        try {
            date = dateFormat.parse(part);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    @Override
    public Date getDateWhenUserLoggedFirstTime(String user, Date after, Date before) {
        int elementInEntry = 3; // event
        int dataNum = 2;
        List<String> temp = new ArrayList<>(entries);
        entries = getEntriesForUser(user);
        Set<String> usersTry = getSomeWithFilters(dataNum, elementInEntry,
                Event.LOGIN.toString(), after, before, true);
        entries = temp;
        List<Date> dates = new ArrayList<>(getDatesFromFormattedStrings(usersTry));
        Collections.sort(dates);
        if (dates.size() > 0) return dates.get(0);
        return null;
    }

    @Override
    public Date getDateWhenUserSolvedTask(String user, int task, Date after, Date before) {
        int elementInEntry = 3; // event
        int dataNum = 2;
        List<String> temp = new ArrayList<>(entries);
        entries = getEntriesForUser(user);
        Set<String> usersTry = getSomeWithFilters(dataNum, elementInEntry,
                Event.SOLVE_TASK.toString() + " " + task, after, before, true);
        entries = temp;
        List<Date> dates = new ArrayList<>(getDatesFromFormattedStrings(usersTry));
        Collections.sort(dates);
        if (dates.size() > 0) return dates.get(0);
        return null;
    }

    @Override
    public Date getDateWhenUserDoneTask(String user, int task, Date after, Date before) {
        int elementInEntry = 3; // event
        int dataNum = 2;
        List<String> temp = new ArrayList<>(entries);
        entries = getEntriesForUser(user);
        Set<String> usersTry = getSomeWithFilters(dataNum, elementInEntry,
                Event.DONE_TASK.toString() + " " + task, after, before, true);
        entries = temp;
        List<Date> dates = new ArrayList<>(getDatesFromFormattedStrings(usersTry));
        Collections.sort(dates);
        if (dates.size() > 0) return dates.get(0);
        return null;
    }

    @Override
    public Set<Date> getDatesWhenUserWroteMessage(String user, Date after, Date before) {
        int elementInEntry = 3; // event
        int dataNum = 2;
        List<String> temp = new ArrayList<>(entries);
        entries = getEntriesForUser(user);
        Set<String> usersTry = getSomeWithFilters(dataNum, elementInEntry,
                Event.WRITE_MESSAGE.toString(), after, before, true);
        entries = temp;
        return getDatesFromFormattedStrings(usersTry);
    }

    @Override
    public Set<Date> getDatesWhenUserDownloadedPlugin(String user, Date after, Date before) {
        int elementInEntry = 3; // event
        int dataNum = 2;
        List<String> temp = new ArrayList<>(entries);
        entries = getEntriesForUser(user);
        Set<String> usersTry = getSomeWithFilters(dataNum, elementInEntry,
                Event.DOWNLOAD_PLUGIN.toString(), after, before, true);
        entries = temp;
        return getDatesFromFormattedStrings(usersTry);
    }

    @Override
    public int getNumberOfAllEvents(Date after, Date before) {
        return getAllEvents(after, before).size();
    }

    @Override
    public Set<Event> getAllEvents(Date after, Date before) {
        Set<Event> eventTypes = new HashSet<>();

        for (String line : entries) {
            String[] parts = line.split("\\t");
            addEventEntity(after, before, eventTypes, parts);
        }
        return eventTypes;
    }

    private void addEventEntity(Date after, Date before, Set<Event> entities, String[] parts) {
        Event lineEvent = Event.valueOf(parts[3].split(" ")[0]);
        long lineDateTime = getDate(parts[2]).getTime();

        if (isCompatibleDate(lineDateTime, after, before)) {
            entities.add(lineEvent);
        }
    }

    @Override
    public Set<Event> getEventsForIP(String ip, Date after, Date before) {
        Set<Event> EventsForIP = new HashSet<>();

        for (String line : entries) {
            String[] parts = line.split("\\t");
            if (ip.equals(parts[0])) {
                addEventEntity(after, before, EventsForIP, parts);
            }
        }
        return EventsForIP;
    }

    @Override
    public Set<Event> getEventsForUser(String user, Date after, Date before) {
        Set<Event> EventsForUser = new HashSet<>();

        for (String line : entries) {
            String[] parts = line.split("\\t");
            if (user.equals(parts[1])) {
                addEventEntity(after, before, EventsForUser, parts);
            }
        }
        return EventsForUser;
    }

    @Override
    public Set<Event> getFailedEvents(Date after, Date before) {
        Set<Event> FailedEvents = new HashSet<>();

        for (String line : entries) {
            String[] parts = line.split("\\t");
            if (Status.FAILED.toString().equals(parts[4])) {
                addEventEntity(after, before, FailedEvents, parts);
            }
        }
        return FailedEvents;
    }

    @Override
    public Set<Event> getErrorEvents(Date after, Date before) {
        Set<Event> ErrorEvents = new HashSet<>();

        for (String line : entries) {
            String[] parts = line.split("\\t");
            if (Status.ERROR.toString().equals(parts[4])) {
                addEventEntity(after, before, ErrorEvents, parts);
            }
        }
        return ErrorEvents;
    }

    @Override
    public int getNumberOfAttemptToSolveTask(int task, Date after, Date before) {
        int numberOfAttemptToSolveTask = 0;
        Set<String> fEntries = getEntriesBetweenDates(after, before);
        for (String line : fEntries) {
            String[] parts = line.split("\\t");
            if (Event.SOLVE_TASK.toString().equals(parts[3].split(" ")[0])
                    && task == Integer.valueOf(parts[3].split(" ")[1])) {
                numberOfAttemptToSolveTask++;
            }
        }
        return numberOfAttemptToSolveTask;
    }

    @Override
    public int getNumberOfSuccessfulAttemptToSolveTask(int task, Date after, Date before) {
        return getNumberOfAttemptToSolveTask(task, after, before);
    }

    private Map<Integer, Integer> getTasksMap(Event event, Date after, Date before) {
        Map<Integer, Integer> allTasksAndTheirNumber = new HashMap<>();
        int value;
        Set<String> set = getEntriesBetweenDates(after, before);
        for (String line : set) {
            String[] parts = line.split("\\t");
            if (parts[3].startsWith(event.toString())) {
                value = Integer.parseInt(parts[3].split(" ")[1]);
                if (allTasksAndTheirNumber.containsKey(value)) {
                    allTasksAndTheirNumber.put(value, allTasksAndTheirNumber.get(value) + 1);
                } else allTasksAndTheirNumber.put(value, 1);
            }
        }
        return allTasksAndTheirNumber;
    }

    @Override
    public Map<Integer, Integer> getAllSolvedTasksAndTheirNumber(Date after, Date before) {
        return getTasksMap(Event.SOLVE_TASK, after, before);
    }

    @Override
    public Map<Integer, Integer> getAllDoneTasksAndTheirNumber(Date after, Date before) {
        return getTasksMap(Event.DONE_TASK, after, before);
    }
}