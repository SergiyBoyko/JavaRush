package com.javarush.task.task30.task3008;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Serhii Boiko on 09.06.2017.
 */
public class Server {

    private static Map<String, Connection> connectionMap = new ConcurrentHashMap<String, Connection>();

    public static void sendBroadcastMessage(Message message) {
        for(Map.Entry<String, Connection> map : connectionMap.entrySet()) {
            try {
                map.getValue().send(message);
            } catch (IOException e) {
                System.out.println("Не возможно отправить сообщение!");
            }
        }
    }

    public static void main(String[] args) throws IOException {
        int port = ConsoleHelper.readInt();
        ServerSocket serverSocket = new ServerSocket(port);
        ConsoleHelper.writeMessage("Сервер запущен!");

        try {
            while (true) {
                Socket socket = serverSocket.accept();
                Handler handler = new Handler(socket);
                handler.start();
            }
        } catch (Exception e) {
            serverSocket.close();
            e.printStackTrace();
        }

    }

    private static class Handler extends Thread {
        private Socket socket;

        @Override
        public void run() {
            String userName = null;
            System.out.println(socket.getRemoteSocketAddress());
            try (Connection connection = new Connection(socket)) {
                userName = serverHandshake(connection);
                Server.sendBroadcastMessage(new Message(MessageType.USER_ADDED, userName));
                sendListOfUsers(connection, userName);
                serverMainLoop(connection, userName);
                connection.close();

            } catch (IOException e){
                ConsoleHelper.writeMessage("Произошла ошибка при обмене данными с удаленным адресом!");
            } catch (ClassNotFoundException e) {
                ConsoleHelper.writeMessage("Произошла ошибка при обмене данными с удаленным адресом!");
            } finally {
                if (userName != null) {
                    connectionMap.remove(userName);
                    Server.sendBroadcastMessage(new Message(MessageType.USER_REMOVED, userName));
                }
                ConsoleHelper.writeMessage("Соединение с удаленным адресом закрыто!");
            }
        }

        public Handler(Socket socket) {
            this.socket = socket;
        }

        private String serverHandshake(Connection connection) throws IOException, ClassNotFoundException {
            Message message = null;
            do {
//                Message message = new Message(MessageType.NAME_REQUEST);
                connection.send(new Message(MessageType.NAME_REQUEST));
                message = connection.receive();
            } while (message.getType() != MessageType.USER_NAME || connectionMap.containsKey(message.getData())
                    || message.getData().isEmpty());
            connectionMap.put(message.getData(), connection);
            connection.send(new Message(MessageType.NAME_ACCEPTED));
            return  message.getData();
        }

        private void sendListOfUsers(Connection connection, String userName) throws IOException {
            for(Map.Entry<String, Connection> map : connectionMap.entrySet()) {
                String name = map.getKey();
                Message message = new Message(MessageType.USER_ADDED, name);
                if (!name.equals(userName)) connection.send(message);
            }
        }

        private void serverMainLoop(Connection connection, String userName) throws IOException, ClassNotFoundException {
            Message message;
            while (true) {
                message = connection.receive();
                if (message.getType() == MessageType.TEXT) {
                    String mes = userName + ": " + message.getData();
                    Server.sendBroadcastMessage(new Message(MessageType.TEXT, mes));
                } else ConsoleHelper.writeMessage("Сообщение имеет не текстовый тип!");
            }
        }
    }
}
